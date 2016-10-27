package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.R;

/**
 * Created by root on 17/10/16.
 */

public class Step2 extends BaseScreenFragment {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step2;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getSupportActionBar().hide();
        view.findViewById(R.id.btnGoStep3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.rlTabFragmentContainer,new Step3())
                        .commit();
            }
        });
        setTitle("Step 2");
        showBackButton();
    }
}
