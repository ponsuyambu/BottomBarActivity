package in.appchef.bottombaractivity.screens.tablet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingLeftPane1;
import in.appchef.bottombaractivity.screens.ParamKeys;
import in.appchef.bottombaractivity.screens.Step3;
import in.ps.bottombaractivity.BaseScreenFragment;
import in.ps.bottombaractivity.FIntent;

/**
 * Created by root on 7/11/16.
 */

public class LeftPane extends BaseScreenFragment<BindingLeftPane1> {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Country List");
        inflateMenu(R.menu.menu_bottom_navigation);
        binding.btnShowStep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle screenBundle = new Bundle();
                screenBundle.putString(ParamKeys.KEY_TAB, "");
                ((BaseScreenFragment) getParentFragment()).startFragment(new FIntent(Step3.class, screenBundle));
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_left_pane;
    }
}
