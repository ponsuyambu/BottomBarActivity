package in.appchef.bottombaractivity.screens.tablet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingMultiPane;
import in.appchef.bottombaractivity.screens.ParamKeys;
import in.appchef.bottombaractivity.screens.Step1;
import in.ps.bottombaractivity.BaseScreenFragment;
import in.ps.bottombaractivity.TabFragment;

/**
 * @author Ponsuyambu
 * @since 7/11/16.
 */

public class MultiPaneFragment extends TabFragment<BindingMultiPane> implements BaseScreenFragment.BaseScreenCommunicator {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isAlreadyCreated()) {
            Bundle screen1Bundle = new Bundle();
            screen1Bundle.putString(ParamKeys.KEY_TAB, "Tab 1");
            BaseScreenFragment rightPane = new Step1();
            rightPane.setArguments(screen1Bundle);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.llLeftPane, new LeftPane())
                    .replace(R.id.llRightPane, rightPane)
                    .commit();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_multi_pane;
    }

    @Override
    public int getContainerId() {
        return R.id.llRightPane;
    }
}
