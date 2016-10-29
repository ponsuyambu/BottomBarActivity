package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingStep5;

/**
 * Created by root on 17/10/16.
 */

public class Step5 extends BaseScreenFragment<BindingStep5> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Step 5");
        binding.tvStep.setText(getArguments().getString(ParamKeys.KEY_TAB) + " - Step 5 ");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step5;
    }
}
