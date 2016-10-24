package in.appchef.bottombaractivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
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

import java.util.HashMap;
import java.util.Map;

import in.appchef.bottombaractivity.screens.SepaStep1;
import in.appchef.bottombaractivity.screens.SepaStep2;
import in.appchef.bottombaractivity.screens.SepaStep3;

public class BottomBarActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private FragmentTabHost mTabHost;
    private BottomNavigationView mBottomNavigationView;
    private int mNumberOfBottomOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PopupMenu p  = new PopupMenu(this,null);
        Menu menu = p.getMenu();

        getMenuInflater().inflate(getBottomMenu(),menu);
        mNumberOfBottomOptions = menu.size();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        mBottomNavigationView.inflateMenu(getBottomMenu());
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

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
        }else if(id == R.id.action_two){
            mTabHost.setCurrentTab(1);
        }
        else if(id == R.id.action_three){
            mTabHost.setCurrentTab(2);
        }
        return false;
    }

    public @MenuRes int getBottomMenu(){
        return R.menu.menu_bottom_navigation;
    }

    public static class BottomNavigationViewScreen{
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

    public Map<Integer,BottomNavigationViewScreen> getScreens(){
        Map<Integer,BottomNavigationViewScreen> screens = new HashMap<>();
        BottomNavigationViewScreen screen1 = new BottomNavigationViewScreen(SepaStep1.class);
        BottomNavigationViewScreen screen2 = new BottomNavigationViewScreen(SepaStep2.class);
        BottomNavigationViewScreen screen3 = new BottomNavigationViewScreen(SepaStep3.class);
        screens.put(R.id.action_one,screen1);
        screens.put(R.id.action_two,screen2);
        screens.put(R.id.action_three,screen3);
        return screens;
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
}
