package in.appchef.bottombaractivity.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.appchef.bottombaractivity.BaseScreenFragment;
import in.appchef.bottombaractivity.R;

/**
 * Created by root on 17/10/16.
 */

public class SepaStep1 extends BaseScreenFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }
    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_sepa_step1,container,false);
//    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sepa_step1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        view.findViewById(R.id.btnGoStep2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.rlTabFragmentContainer,new SepaStep2())
                        .commit();
            }
        });
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected ActionBar getSupportActionBar(){
        return getAppCompatActivity().getSupportActionBar();
    }

    protected AppCompatActivity getAppCompatActivity(){
        return ((AppCompatActivity)getActivity());
    }

    protected void setSupportActionBar(Toolbar toolbar){
        getAppCompatActivity().setSupportActionBar(toolbar);
    }
}
