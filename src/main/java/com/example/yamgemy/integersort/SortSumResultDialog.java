package com.example.yamgemy.integersort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cengalabs.flatui.views.FlatButton;
import com.example.yamgemy.integersort.Globals.Globals;
import com.example.yamgemy.integersort.Util.TVMaker;

import java.util.ArrayList;

public class SortSumResultDialog extends DialogFragment {

    private TextView tv_result;
    private ArrayList<Integer> max2nums_passed;

    public SortSumResultDialog setMaxNums(ArrayList<Integer> x){
        max2nums_passed = x;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.result_dialog_frag_layout, container, false);
        FlatButton ok = v.findViewById(R.id.result_diag_OK);

        ok.setOnClickListener(View -> {
            this.dismiss();
            int c = Globals.main.getMainVp().getCurrentItem();
            if (c==1){
                Globals.frag_r.clearStackAndContainer();
            }else if (c==2){
                Globals.frag_m.clearStackAndContainer();
            }

        });

        LinearLayout tv_container = v.findViewById(R.id.result_tv_container);
        tv_result  = v.findViewById(R.id.result_tv_sum);
        this.addTvs(tv_container, max2nums_passed);

        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.setCancelable(false);
    }


    private void addTvs(LinearLayout tv_container,  ArrayList<Integer> max2nums){
        if (max2nums==null){return;}

        TextView tv1 = TVMaker.getSoftGenTv(getActivity());
        TextView tv2 = TVMaker.getSoftGenTv(getActivity());
        tv_container.addView(tv1);
        tv_container.addView(tv2);

        tv1.setText(String.valueOf(max2nums.get(0)));
        tv2.setText(String.valueOf(max2nums.get(1)));

        int r = max2nums.get(0)+ max2nums.get(1);
        tv_result.setText(String.valueOf(r));
    }



}
