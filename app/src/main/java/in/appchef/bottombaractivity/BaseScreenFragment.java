package in.appchef.bottombaractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 26/10/16.
 */

public abstract class BaseScreenFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.screen_action_bar,container,false);
        View screenView = inflater.inflate(getLayoutResource(), rootView, false);
        rootView.addView(screenView);

        return rootView;
    }

    protected abstract int getLayoutResource();
}
