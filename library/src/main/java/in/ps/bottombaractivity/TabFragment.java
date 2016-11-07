package in.ps.bottombaractivity;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;

import java.util.Map;

import in.ps.bottombaractivity.databinding.BindingTab;


/**
 * Created by root on 17/10/16.
 */

public class TabFragment<T extends ViewDataBinding> extends BaseScreenFragment<BindingTab> implements BaseScreenFragment.BaseScreenCommunicator, FragmentManager.OnBackStackChangedListener {
    public static final String KEY_MENU_ID = "MENU_ID";
    public static final String KEY_INITIAL_FRAGMENT = "INITIAL_FRAGMENT";
    private static final String KEY_TAB_NAME = "TAB_NAME";
    private static final String KEY_ID = "ID";
    private String mTabName = "TAB";
    private int mId = 0;
    private
    @IdRes
    int menuId;

    public static TabFragment newInstance(int uniqueId, String text) {
        Bundle args = new Bundle();
        args.putString(KEY_TAB_NAME, text);
        args.putInt(KEY_ID, uniqueId);

        TabFragment sampleFragment = new TabFragment();
        sampleFragment.setArguments(args);

        return sampleFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof BottomBarActivity)) {
            throw new IllegalArgumentException("Unsupported activity");
        }
    }

    protected BottomBarActivity getBottomBarActivity() {
        return (BottomBarActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanAddActionBar(false);
        if (getArguments() != null) {
            if (getArguments().getString(KEY_TAB_NAME) != null) {
                mTabName = mTabName + " - " + getArguments().getString(KEY_TAB_NAME);
            }
            mId = getArguments().getInt(KEY_ID, 0);
            menuId = getArguments().getInt(KEY_MENU_ID, 0);
        }
        getChildFragmentManager().addOnBackStackChangedListener(this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!isAlreadyCreated()) {
            commitFragment();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.tab_fragment;
    }

    private void commitFragment() {
        Map<Integer, BottomBarActivity.BottomNavigationViewScreen> screens = getBottomBarActivity().getScreens();
        if (screens != null) {
            BottomBarActivity.BottomNavigationViewScreen screen = screens.get(menuId);
            if (screen != null) {
                Class<? extends Fragment> fragmentClass = screen.fragmentClass;
                if (fragmentClass != null) {
                    try {
                        Fragment fragment = fragmentClass.newInstance();

                        if (fragment != null) {
                            if (screen.extras != null) {
                                fragment.setArguments(screen.extras);
                            }
                            getChildFragmentManager().beginTransaction()
                                    .replace(getContainerId(), fragment)
                                    .commit();
                        }
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        if (mId == 1) {

//
        }
    }

    public boolean onBackPressed() {
        boolean isBackHandled = false;
        if (getChildFragmentManager().findFragmentById(getContainerId()) == null) { //if no fragment is available present in the container
            return isBackHandled;
        } else {
            BaseFragment baseFragment = (BaseFragment) getChildFragmentManager().findFragmentById(getContainerId());
            isBackHandled = baseFragment.onBackPressed();
            if (!isBackHandled) { //if child not handled
                if (getChildFragmentManager().getBackStackEntryCount() > 0) {
                    getChildFragmentManager().popBackStack();
                    isBackHandled = true;
                } else {
                    isBackHandled = false;
                }

            }
        }
        return isBackHandled;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (getChildFragmentManager().findFragmentById(getContainerId()) != null) {
            BaseFragment baseFragment = (BaseFragment) getChildFragmentManager().findFragmentById(getContainerId());
            return baseFragment.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getContainerId() {
        return R.id.rlTabFragmentContainer;
    }

    @Override
    public void onBackStackChanged() {
        if (getChildFragmentManager().findFragmentById(getContainerId()) != null) {
            TabContainerCommunicator tabContainerCommunicator = (TabContainerCommunicator) getChildFragmentManager().findFragmentById(getContainerId());
            tabContainerCommunicator.onBackStackChanged(getChildFragmentManager().getBackStackEntryCount());
        }
    }
}
