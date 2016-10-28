package in.appchef.bottombaractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by root on 17/10/16.
 */

public class BaseFragment extends Fragment {

    private static final String LIFE_CYCLE = "LifeCycle";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(LIFE_CYCLE,"onViewCreated - "+this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LIFE_CYCLE,"onCreate - "+this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LIFE_CYCLE,"onResume - "+this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LIFE_CYCLE,"onStop - "+this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LIFE_CYCLE,"onDestroy - "+this);
    }

    public boolean onBackPressed(){
        return false;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d(LIFE_CYCLE,"onCreateAnimation - "+this);
        return super.onCreateAnimation(transit, enter, nextAnim);
    }
}
