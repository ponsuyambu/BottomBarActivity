package in.appchef.bottombaractivity.screens.tablet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingRightPane;
import in.ps.bottombaractivity.BaseScreenFragment;

/**
 * Created by root on 7/11/16.
 */

public class RightPane extends BaseScreenFragment<BindingRightPane> {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Description");
        inflateMenu(R.menu.menu_step1);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_right_pane;
    }
}
