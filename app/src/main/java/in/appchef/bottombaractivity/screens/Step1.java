package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import in.ps.bottombaractivity.BaseScreenFragment;
import in.ps.bottombaractivity.FIntent;
import in.appchef.bottombaractivity.R;
import in.appchef.bottombaractivity.databinding.BindingStep1;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by root on 17/10/16.
 */

public class Step1 extends BaseScreenFragment<BindingStep1> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_step1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Step 1");
        inflateMenu(R.menu.menu_step1);
        binding.tvStep.setText(getArguments().getString(ParamKeys.KEY_TAB) + " - Step 1 ");
        binding.btnGoStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new FIntent(Step2.class,getArguments()));
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            // Do Sometihng
            LayoutInflater layoutInflater
                    = (LayoutInflater)getContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.popup_window, null);
            final PopupWindow popupWindow = new PopupWindow(
                    popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
            btnDismiss.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }});

            popupWindow.showAsDropDown(getToolBar(), 50, -30);

            return true;
            //and so on...
        }
        return super.onKeyDown(keyCode, event);
    }

}
