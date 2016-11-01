package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.ps.bottombaractivity.BaseScreenFragment;
import in.ps.bottombaractivity.FIntent;
import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingStep4;

/**
 * Created by root on 17/10/16.
 */

public class Step4 extends BaseScreenFragment<BindingStep4> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Step 4");
        binding.tvStep.setText(getArguments().getString(ParamKeys.KEY_TAB) + " - Step 4 ");
        binding.btnGoStep5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new FIntent(Step5.class,getArguments()));
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step4;
    }
}
