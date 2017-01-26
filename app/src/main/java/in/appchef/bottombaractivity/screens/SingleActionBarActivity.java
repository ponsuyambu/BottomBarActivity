package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;

import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.screens.tablet.MultiPaneFragment;
import in.ps.bottombaractivity.BottomBarActivity;

public class SingleActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
;
        setContentView(R.layout.activity_single_action_bar);
                Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

//    @Override
//    public Map<Integer, BottomNavigationViewScreen> getScreens() {
//        Map<Integer,BottomNavigationViewScreen> screens = new HashMap<>();
//        Bundle screen1Bundle = new Bundle();
//        screen1Bundle.putString(ParamKeys.KEY_TAB,"Tab 1");
//        BottomNavigationViewScreen screen1 = new BottomNavigationViewScreen(MultiPaneFragment.class, screen1Bundle);
//        Bundle screen2Bundle = new Bundle();
//        screen2Bundle.putString(ParamKeys.KEY_TAB,"Tab 2");
//        BottomNavigationViewScreen screen2 = new BottomNavigationViewScreen(Step1.class,screen2Bundle);
//        Bundle screen3Bundle = new Bundle();
//        screen3Bundle.putString(ParamKeys.KEY_TAB,"Tab 3");
//        BottomNavigationViewScreen screen3 = new BottomNavigationViewScreen(Step1.class,screen3Bundle);
//        Bundle screen4Bundle = new Bundle();
//        screen4Bundle.putString(ParamKeys.KEY_TAB,"Tab 4");
//        BottomNavigationViewScreen screen4 = new BottomNavigationViewScreen(Step1.class,screen4Bundle);
//        Bundle screen5Bundle = new Bundle();
//        screen5Bundle.putString(ParamKeys.KEY_TAB,"Tab 5");
//        BottomNavigationViewScreen screen5 = new BottomNavigationViewScreen(Step1.class,screen5Bundle);
//
//        screens.put(in.ps.bottombaractivity.R.id.action_one,screen1);
//        screens.put(in.ps.bottombaractivity.R.id.action_two,screen2);
//        screens.put(in.ps.bottombaractivity.R.id.action_three,screen3);
//        screens.put(in.ps.bottombaractivity.R.id.action_four,screen4);
//        screens.put(in.ps.bottombaractivity.R.id.action_five,screen5);
//        return screens;
//    }
}
