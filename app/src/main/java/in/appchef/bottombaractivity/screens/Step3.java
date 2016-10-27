package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.R;

/**
 * Created by root on 17/10/16.
 */

public class Step3 extends BaseScreenFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showBackButton();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step3;
    }
}
