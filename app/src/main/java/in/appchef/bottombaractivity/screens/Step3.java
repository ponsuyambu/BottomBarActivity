package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.FIntent;
import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingStep3;

/**
 * Created by root on 17/10/16.
 */

public class Step3 extends BaseScreenFragment<BindingStep3> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Step 3");
        binding.tvStep.setText(getArguments().getString(ParamKeys.KEY_TAB) + " - Step 3 ");
        binding.btnGoStep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new FIntent(Step4.class,getArguments()));
            }
        });
        binding.btnHideBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideBackButton();
            }
        });

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step3;
    }
}
