package in.ps.bottombaractivity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import java.util.Map;

public class BottomBarActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, TabHost.OnTabChangeListener {

    public static final String TAG = "BottomBarActivity";
    private static final String KEY_BOTTOM_BAR_SELECTED_INDEX = "BBA_BB_SELECTED_INDEX";
    private FragmentTabHost mTabHost;
    private BottomNavigationView mBottomNavigationView;
    private int mNumberOfBottomOptions;
    private String mCurrentFragmentTag; //TODO: save state
    private int mBBSelectedIndex = -1;
    private AsyncTask mChangecanPlayNextEnterAnimationAsyncTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            onRestoreState(savedInstanceState);
        }
        setContentView(R.layout.activity_main);
        PopupMenu p  = new PopupMenu(this,null);
        Menu menu = p.getMenu();

        getMenuInflater().inflate(getBottomMenu(),menu);
        mNumberOfBottomOptions = menu.size();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        mBottomNavigationView.inflateMenu(getBottomMenu());
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.setOnTabChangedListener(this);

        for(int i =0; i<mNumberOfBottomOptions;i++){
            MenuItem item = menu.getItem(i);
            Bundle extrasBundle = new Bundle();
            extrasBundle.putInt("ID",i);
            extrasBundle.putString("TAB_NAME",""+i);
            extrasBundle.putInt(TabFragment.KEY_MENU_ID,item.getItemId());
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab"+i).setIndicator("Tab "+i, null),
                    TabFragment.class, extrasBundle);
        }
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_one){
            mTabHost.setCurrentTab(0);
            mBBSelectedIndex = 0;
        }else if(id == R.id.action_two){
            mTabHost.setCurrentTab(1);
            mBBSelectedIndex = 1;
        }
        else if(id == R.id.action_three){
            mTabHost.setCurrentTab(2);
            mBBSelectedIndex = 2;
        }else if(id == R.id.action_four){
            mBBSelectedIndex = 3;
            mTabHost.setCurrentTab(3);
        }else if(id == R.id.action_five){
            mBBSelectedIndex = 4;
            mTabHost.setCurrentTab(4);
        }
        return false;
    }

    public @MenuRes int getBottomMenu(){
        return R.menu.menu_bottom_navigation;
    }

    @Override
    public void onTabChanged(String tabId) {
        mCurrentFragmentTag = tabId;
        //DevHelp: If Tab is changed, block the fragments' enter animation(SharedStorage.CAN_PLAY_NEXT_ENTER_ANIMATION = false).
        //Since we are not about the level of nested fragments, as temp work around the flag value set true after the particular time.
        //TODO: AsyncTask logic has to improvised.
        if (mChangecanPlayNextEnterAnimationAsyncTask != null &&
                mChangecanPlayNextEnterAnimationAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            mChangecanPlayNextEnterAnimationAsyncTask.cancel(true);
            mChangecanPlayNextEnterAnimationAsyncTask = null;
        }
        SharedStorage.CAN_PLAY_NEXT_ENTER_ANIMATION = false;//on Tab change, block all fragments enter animation
        mChangecanPlayNextEnterAnimationAsyncTask = new ChangecanPlayNextEnterAnimationAsyncTask();
        mChangecanPlayNextEnterAnimationAsyncTask.execute();
    }

    ;

    public Map<Integer,BottomNavigationViewScreen> getScreens(){
       return null;
    }

    @Override
    public void onBackPressed() {
        int currentTab = mTabHost.getCurrentTab();
        TabFragment tabFragment = (TabFragment) getSupportFragmentManager().findFragmentByTag("tab"+currentTab);
        if(!tabFragment.onBackPressed()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            callSuperBackPressed();
                        }
                    })
                    .setTitle("Exit?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.create().show();
        }
    }

    private void callSuperBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_BOTTOM_BAR_SELECTED_INDEX,mBBSelectedIndex);
        super.onSaveInstanceState(outState);

    }

    public void onRestoreState(@NonNull Bundle savedState){
        mBBSelectedIndex = savedState.getInt(KEY_BOTTOM_BAR_SELECTED_INDEX,-1);//TODO: Have to select the BB item! Bug raised to Google.
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        TabFragment currentTabFragment = (TabFragment) getSupportFragmentManager().findFragmentByTag(
                mCurrentFragmentTag);
        getSupportFragmentManager().beginTransaction().detach(currentTabFragment).attach(currentTabFragment).commit();
    }

    static class ChangecanPlayNextEnterAnimationAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            int counter = 1;
            while (counter <= 600) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
                ++counter;
            }
            SharedStorage.CAN_PLAY_NEXT_ENTER_ANIMATION = true;
            Log.d("onCreateAnimation", "CAN_PLAY_NEXT_ENTER_ANIMATION value changed: " + SharedStorage.CAN_PLAY_NEXT_ENTER_ANIMATION);
            return null;
        }
    }

    public static class BottomNavigationViewScreen {
        Class<? extends Fragment> fragmentClass;
        Bundle extras;

        public BottomNavigationViewScreen(Class<? extends Fragment> fragmentClass) {
            this.fragmentClass = fragmentClass;
        }

        public BottomNavigationViewScreen(Class<? extends Fragment> fragmentClass, Bundle extras) {
            this.fragmentClass = fragmentClass;
            this.extras = extras;
        }
    }
}
