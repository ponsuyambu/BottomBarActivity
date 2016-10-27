package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.FIntent;
import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingStep2;

/**
 * Created by root on 17/10/16.
 */

public class Step2 extends BaseScreenFragment<BindingStep2> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step2;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnGoStep3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new FIntent(Step3.class));
            }
        });
        setTitle("Step 2");
        showBackButton();
    }
}
