package in.appchef.bottombaractivity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by root on 26/10/16.
 */

public abstract class BaseScreenFragment<T extends ViewDataBinding> extends BaseFragment implements Toolbar.OnMenuItemClickListener{

    private Toolbar mToolbar;
    private ViewGroup mRootView;
    private View mScreenView;
    private @IdRes int containerId;
    private FragmentTransaction transaction = null;
    private FragmentManager manager = null;
    protected  final String TAG = getClass().getSimpleName();
    protected T binding;
    private static final String IS_ALREADY_CREATED = "BSF_IS_ALREADY_CREATED";
    private boolean isAlreadyCreated = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.screen_action_bar,container,false);
        binding = DataBindingUtil.inflate(inflater,getLayoutResource(),mRootView,false);
        mScreenView = binding.getRoot();
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
        if(getParentFragment() != null){
            if(!(getParentFragment() instanceof BaseScreenCommunicator)){ //if parent fragment not implements communicator class, throw exception
                throw new IllegalStateException("Parent fragment "+getParentFragment()+" should implement BaseScreenCommunicator");
            }else{
                BaseScreenCommunicator communicator = (BaseScreenCommunicator) getParentFragment();
                containerId = communicator.getContainerId();
            }
        }
        isAlreadyCreated = true;
    }

    protected abstract @LayoutRes int getLayoutResource();

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
//        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new BackNavigationClickListener(this));
    }

    protected void setTitle(String title){
        mToolbar.setTitle(title);
    }

    protected void inflateMenu(@MenuRes int menuRes){
        mToolbar.inflateMenu(menuRes);
        mToolbar.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    protected void startFragment(){

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



    public interface BaseScreenCommunicator{
        @IdRes int getContainerId();
    }


    /////////////////////////////
    /**
     * Starts the next fragment specified in the FIntent.
     *
     * @param fIntent
     *            the f intent
     * @return the int
     */
    public int startFragment(FIntent fIntent) {
        Log.i(TAG, "Starting " + fIntent.getClass().getName());
        return startFragment(fIntent, containerId, getFragmentManager());

    }

    /**
     * Starts the next fragment specified in the FIntent.
     *
     * @param fIntent
     *            the f intent
     * @return the int
     */
    public int startFragment(FIntent fIntent, FragmentManager manager) {
        Log.i(TAG, "Starting " + fIntent.getClass().getName());
        return startFragment(fIntent, containerId, manager);

    }

    /**
     * Starts the next fragment specified in the FIntent.
     *
     * @param fIntent
     *            the f intent
     * @param isAnimate
     *            the is animate
     * @return the int
     */
    public int startFragment(FIntent fIntent, boolean isAnimate) {
        Log.i(TAG, "Starting " + fIntent.getClass().getName());
        return startFragment(fIntent, containerId,
                getFragmentManager(), isAnimate);

    }

    /**
     * Checks if is orientation allowed.
     *
     * @return true, if is orientation allowed
     */
    public boolean isOrientationAllowed() {
        //Default implementation is true.
        return true;
    }

    /**
     * Starts the fragment in the specified container id.
     *
     * @param fIntent
     *            the f intent
     * @param containerId
     *            the container id
     * @param manager
     *            the manager
     * @param isAnimate
     *            the is animate
     * @return the int
     */
    public int startFragment(FIntent fIntent, int containerId,
                             FragmentManager manager, boolean isAnimate) {
        Log.i(TAG, "Starting " + fIntent.getClass().getName());
        transaction = manager.beginTransaction();
        createFragmentTransaction(fIntent, containerId, manager, transaction,
                isAnimate);
//        if (isAppMinimised || (isConfigChanged && isOrientationAllowed())) {
//            isTransactionNotCommitted = true;
//            Log.w("LifeCycle",
//                    "start fragment : Commit is not done : isAppMinimised>>>"
//                            + isAppMinimised + " :isConfigChanged>>>"
//                            + isConfigChanged);
//            txnList.add(transaction);
//            return -1;
//        } else {
//            Log.w("LifeCycle",
//                    "start fragment : Commit is  done Successfully : isAppMinimised>>>"
//                            + isAppMinimised + " :isConfigChanged>>>"
//                            + isConfigChanged);
//            return transaction.commit();
//        }
        return transaction.commit();
    }

    /**
     * Starts the fragment in the specified container id.
     *
     * @param fIntent
     *            the f intent
     * @param containerId
     *            the container id
     * @param manager
     *            the manager
     * @return the int
     */
    public int startFragment(FIntent fIntent, int containerId,
                             FragmentManager manager) {
        Log.i(TAG, "Starting " + fIntent.getClass().getName());
        transaction = manager.beginTransaction();
        createFragmentTransaction(fIntent, containerId, manager, transaction,
                true);
//        if (isAppMinimised ||(isConfigChanged && isOrientationAllowed())) {
//            Log.w("LifeCycle",
//                    "start fragment : Commit is not done : isAppMinimised>>>"
//                            + isAppMinimised + " :isConfigChanged>>>"
//                            + isConfigChanged);
//            isTransactionNotCommitted = true;
//            txnList.add(transaction);
//            return -1;
//        } else {
//            Log.w("LifeCycle",
//                    "start fragment : Commit is  done Successfully : isAppMinimised>>>"
//                            + isAppMinimised + " :isConfigChanged>>>"
//                            + isConfigChanged);
//            return transaction.commit();
//        }
        return transaction.commit();
    }

    /**
     * Creates the fragment transaction.
     *
     * @param fIntent
     *            the f intent
     * @param containerId
     *            the container id
     * @param manager
     *            the manager
     * @param transaction
     *            the transaction
     * @param isAnimate
     *            the is animate
     * @return the fragment transaction
     */
    private FragmentTransaction createFragmentTransaction(FIntent fIntent,
                                                          int containerId, FragmentManager manager,
                                                          FragmentTransaction transaction, boolean isAnimate) {

        Log.i("CustomVmc", "Tag " + fIntent.getTag());
        BaseScreenFragment newFragment = null;
        @AnimRes int enterAnimation = 0;
        @AnimRes int exitAnimation = 0;
        @AnimRes int popEnterAnimation = 0;
        @AnimRes int popExitAnimation = 0;

        if (fIntent.getFlags().contains(FIntent.FLAG_BRING_TO_FRONT)) {
            newFragment = (BaseScreenFragment) manager.findFragmentByTag(fIntent
                    .getTag());
        }

        if (fIntent.getFlags().contains(FIntent.FLAG_NEW_INSTANCE)) {
            newFragment = fIntent.getResultFragment();
        }

        if (newFragment == null) {
            Log.i("CustomVmc", "newFragment " + fIntent.getTag());
            newFragment = fIntent.getResultFragment();
        }
        if (isAnimate) {
            Log.i("BACK STACK", "Transaction setCustomAnimations");
            enterAnimation = fIntent.getEnterAnimation() == 0 ? R.anim.right_to_left_in
                    : fIntent.getEnterAnimation();
            exitAnimation = fIntent.getExitAnimation() == 0 ? R.anim.right_to_left_exit
                    : fIntent.getExitAnimation();
            popEnterAnimation = fIntent.getPopEnterAnimation() == 0 ? R.anim.left_to_right_in
                    : fIntent.getPopEnterAnimation();
            popExitAnimation = fIntent.getPopExitAnimation() == 0 ? R.anim.left_to_right_exit
                    : fIntent.getPopExitAnimation();

            transaction.setCustomAnimations(enterAnimation, exitAnimation,
                    popEnterAnimation, popExitAnimation);
        }
        if (!fIntent.getFlags().contains(FIntent.FLAG_NO_HISTORY)) {
            Log.i("BACK STACK", "Transaction added to backstack");
            transaction.addToBackStack(fIntent.getTransactionName());
        } else {
            Log.i("BACK STACK", "Transaction not added to backstack");
        }

        if (fIntent.getFlags().contains(FIntent.FLAG_CLEAR_ALLL_HISTORY)) {
            clearBackStack();
        }

        if (fIntent.getFlags().contains(FIntent.FLAG_TRANSACTION_ADD)) {
            return transaction.add(containerId, newFragment, fIntent.getTag());
        } else {
            return transaction.replace(containerId, newFragment,
                    fIntent.getTag());
        }
    }

    /**
     * Clear back stack.
     */
    protected void clearBackStack() {
        getFragmentManager().popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d("onCreateAnimation","onCreateAnimation: transit: "+transit +";; enter: "+enter+";; nextAnim: "+nextAnim+";; "+this);
         Animation animation = super.onCreateAnimation(transit, enter, nextAnim);
        if(!SharedStorage.PLAY_NEXT_ENTER_ANIMATION && enter){
            animation = new Animation(){};
            animation.setDuration(0);
            SharedStorage.PLAY_NEXT_ENTER_ANIMATION = true;
        }
        return animation;
    }
}
