package in.appchef.bottombaractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 26/10/16.
 */

public abstract class BaseScreenFragment extends BaseFragment {

    private Toolbar mToolbar;
    private ViewGroup mRootView;
    private View mScreenView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.screen_action_bar,container,false);
        mScreenView = inflater.inflate(getLayoutResource(), mRootView, false);
        setMarginForScreen(getAndroidActionBarHeight());
        mRootView.addView(mScreenView);
        return mRootView;
    }

    private void setMarginForScreen(int actionBarHeight){
        CoordinatorLayout.LayoutParams screenViewLayoutParams = (CoordinatorLayout.LayoutParams) mScreenView.getLayoutParams();
        screenViewLayoutParams.topMargin = actionBarHeight;
        mScreenView.setLayoutParams(screenViewLayoutParams);
    }

    private int getAndroidActionBarHeight(){
        // Calculate ActionBar height
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    protected abstract int getLayoutResource();

    protected ActionBar getSupportActionBar(){
        return getAppCompatActivity().getSupportActionBar();
    }

    protected AppCompatActivity getAppCompatActivity(){
        return ((AppCompatActivity)getActivity());
    }

    protected Toolbar getToolBar(){
        return mToolbar;
    }

    protected void hideToolBar(){
        mToolbar.setVisibility(View.GONE);
        setMarginForScreen(0);

    }

    protected void hideBackButton(){
        mToolbar.setNavigationIcon(null);
        mToolbar.setNavigationOnClickListener(null);
    }


    protected void showBackButton(){
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new BackNavigationClickListener(this));
    }

    protected void setTitle(String title){
        mToolbar.setTitle(title);
    }

    protected void setSupportActionBar(Toolbar toolbar){
        getAppCompatActivity().setSupportActionBar(toolbar);
    }

    private static class BackNavigationClickListener implements View.OnClickListener{

        BaseScreenFragment mHandlingFragment;

        public BackNavigationClickListener(BaseScreenFragment handlingFragment){
            mHandlingFragment = handlingFragment;
        }

        @Override
        public void onClick(View view) {
            mHandlingFragment.getAppCompatActivity().onBackPressed();
        }
    }

}
