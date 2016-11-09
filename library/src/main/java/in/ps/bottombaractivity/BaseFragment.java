package in.ps.bottombaractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by root on 17/10/16.
 */

public class BaseFragment extends Fragment {

    protected static final String TAG_LIFE_CYCLE = "LifeCycle";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG_LIFE_CYCLE, "onViewCreated - " + this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG_LIFE_CYCLE, "onCreate - " + this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG_LIFE_CYCLE, "onResume - " + this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG_LIFE_CYCLE, "onStop - " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFE_CYCLE, "onDestroy - " + this);
    }

    public boolean onBackPressed(){
        return false;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d(TAG_LIFE_CYCLE, "onCreateAnimation - " + this);
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
