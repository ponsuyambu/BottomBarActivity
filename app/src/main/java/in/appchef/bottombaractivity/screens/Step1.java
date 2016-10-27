package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.R;

/**
 * Created by root on 17/10/16.
 */

public class Step1 extends BaseScreenFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getToolBar().setTitle("Step 1");
        getToolBar().inflateMenu(R.menu.menu_bottom_navigation);
        showBackButton();
        view.findViewById(R.id.btnGoStep2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.rlTabFragmentContainer,new Step2())
                        .commit();
            }
        });

        view.findViewById(R.id.btnHideBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideBackButton();
            }
        });
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
