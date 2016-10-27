package in.appchef.bottombaractivity;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * FIntent class contains data about the fragment transaction. This has animation info for transaction, fragment to commit and additional
 * back stack flags.
 *
 * @author Ponsuyambu V
 * @since 08/07/2016
 *
 */
public class FIntent {

	private String tag = null;
	public static final int FLAG_NO_HISTORY = 1;
	public static final int FLAG_CLEAR_ALLL_HISTORY = 2;
	public static final int FLAG_BRING_TO_FRONT = 3;
	public static final int FLAG_NEW_INSTANCE = 4;
	public static final int FLAG_TRANSACTION_ADD = 5;

	private int enterAnimation = 0;
	private int exitAnimation = 0;
	private int popEnterAnimation = 0;
	private int popExitAnimation = 0;
	
	private List<Integer> flags = new ArrayList<Integer>();
	private Class<? extends BaseScreenFragment> fragmentClass = null;
	private Bundle bundle = null;

	private BaseScreenFragment fragmentObject = null;
	private String transactionName = null;

	private int parentFragmentPlaceHolderId = 0;
	

	/**
	 * Instantiates the FIntent.
	 *
	 * @param fragmentClass fragment class
	 */
	public FIntent(Class<? extends BaseScreenFragment> fragmentClass) {
		this.fragmentClass = fragmentClass;
		tag = this.fragmentClass.getName();
	}

	/**
	 * Instantiates the FIntent.
	 *
	 * @param fragmentClass fragment class
	 * @param bundle data bundle
	 */
	public FIntent(Class<? extends BaseScreenFragment> fragmentClass, Bundle bundle) {
		this.fragmentClass = fragmentClass;
		this.bundle = bundle;
		tag = this.fragmentClass.getName();
	}

	/**
	 * Instantiates the FIntent with fragment object.
	 *
	 * @param fragmentObject instance of the fragment
	 */
	public FIntent(BaseScreenFragment fragmentObject) {
		this.fragmentObject = fragmentObject;
		tag = fragmentObject.getClass().getName();
	}

	/**
	 * Gets the fragment class.
	 *
	 * @return fragment class
	 */
	public Class<? extends BaseScreenFragment> getFragmentClass() {
		return fragmentClass;
	}

	/**
	 * Sets the fragment class.
	 *
	 * @param fragmentClass the new fragment class
	 */
	public void setFragmentClass(Class<? extends BaseScreenFragment> fragmentClass) {
		this.fragmentClass = fragmentClass;
	}

	/**
	 * Gets the data bundle.
	 *
	 * @return data bundle
	 */
	public Bundle getBundle() {
		return bundle;
	}

	/**
	 * Sets the data bundle.
	 *
	 * @param bundle the data bundle
	 */
	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * Gets the parent fragment place holder id.
	 *
	 * @return the parent fragment place holder id
	 */
	public int getParentFragmentPlaceHolderId() {
		return parentFragmentPlaceHolderId;
	}

	/**
	 * Sets the parent fragment place holder id.
	 *
	 * @param parentFragmentPlaceHolderId the new parent fragment place holder id
	 */
	public void setParentFragmentPlaceHolderId(int parentFragmentPlaceHolderId) {
		this.parentFragmentPlaceHolderId = parentFragmentPlaceHolderId;
	}

	/**
	 * Gets the transaction name.
	 *
	 * @return the transaction name
	 */
	public String getTransactionName() {
		return transactionName;
	}

	/**
	 * Sets the transaction name.
	 *
	 * @param transactionName the new transaction name
	 */
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag the new tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	/**
	 * Gets the flags.
	 *
	 * @return the flags
	 */
	List<Integer> getFlags() {
		return flags;
	}

	/**
	 * Gets the result fragment.
	 *
	 * @return the result fragment
	 */
	public BaseScreenFragment getResultFragment() {
		BaseScreenFragment fragment = null;
		if (fragmentObject != null) {
			fragment = fragmentObject;
		} else if (fragmentClass != null) {

			try {
				fragment = fragmentClass.newInstance();
			} catch (InstantiationException e) {
				Log.e(tag, "Not able to instantiate " + e.getMessage());
			} catch (IllegalAccessException e) {
				Log.e(tag, "Illegal Access " + e.getMessage());
			}
		}
		
		if (bundle != null && fragment !=null) {
				fragment.setArguments(bundle);
		}
		return fragment;
	}

	/**
	 * Adds the flag.
	 *
	 * @param flag the flag
	 */
	public void addFlag(int flag) {
		if (!flags.contains(flag)) {
			flags.add(flag);
		}
	}

	/**
	 * Gets the enter animation.
	 *
	 * @return the enter animation
	 */
	public @AnimRes int getEnterAnimation() {
		return enterAnimation;
	}

	/**
	 * Gets the exit animation.
	 *
	 * @return the exit animation
	 */
	public @AnimRes int getExitAnimation() {
		return exitAnimation;
	}

	/**
	 * Gets the pop enter animation.
	 *
	 * @return the pop enter animation
	 */
	public @AnimRes int getPopEnterAnimation() {
		return popEnterAnimation;
	}

	/**
	 * Gets the pop exit animation.
	 *
	 * @return the pop exit animation
	 */
	public @AnimRes int getPopExitAnimation() {
		return popExitAnimation;
	}

	/**
	 * Sets the enter animation.
	 *
	 * @param enterAnimation the new enter animation
	 */
	public void setEnterAnimation(int enterAnimation) {
		this.enterAnimation = enterAnimation;
	}

	/**
	 * Sets the exit animation.
	 *
	 * @param exitAnimation the new exit animation
	 */
	public void setExitAnimation(int exitAnimation) {
		this.exitAnimation = exitAnimation;
	}

	/**
	 * Sets the pop enter animation.
	 *
	 * @param popEnterAnimation the new pop enter animation
	 */
	public void setPopEnterAnimation(int popEnterAnimation) {
		this.popEnterAnimation = popEnterAnimation;
	}

	/**
	 * Sets the pop exit animation.
	 *
	 * @param popExitAnimation the new pop exit animation
	 */
	public void setPopExitAnimation(int popExitAnimation) {
		this.popExitAnimation = popExitAnimation;
	}

	
}
