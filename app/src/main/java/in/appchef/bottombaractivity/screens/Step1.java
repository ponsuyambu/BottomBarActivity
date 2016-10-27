package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.FIntent;
import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingStep1;

/**
 * Created by root on 17/10/16.
 */

public class Step1 extends BaseScreenFragment<BindingStep1> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Step 1");
        inflateMenu(R.menu.menu_bottom_navigation);
        binding.btnGoStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new FIntent(Step2.class));
            }
        });

        view.findViewById(R.id.btnHideBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideBackButton();
            }
        });
    }


}
