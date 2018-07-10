package com.example.yamgemy.integersort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cengalabs.flatui.views.FlatButton;
import com.example.yamgemy.integersort.Util.MyCalculator;
import com.example.yamgemy.integersort.Util.SharePrefWorker;
import com.example.yamgemy.integersort.Util.TVMaker;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Fragment_Randomize extends Fragment {

    private FloatingActionButton fab_add;
    private FlexboxLayout tvs_container;
    private FlatButton flatbtn_finish;

    private Stack<Integer> generatedIntStack;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generatedIntStack = new Stack();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag2_randomize, container, false);
        tvs_container = v.findViewById(R.id.randomtv_container);
        fab_add = v.findViewById(R.id.randomize_fab_addnum);
        flatbtn_finish = v.findViewById(R.id.randomize_cal_btn);
        flatbtn_finish.setEnabled(false);
        ScrollView sv = v.findViewById(R.id.randomize_scrollview);

        tvs_container.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View view, View view1) {
                int c = tvs_container.getChildCount();
                if (c>=2){
                    flatbtn_finish.setEnabled(true);
                }

                sv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                },10);

            }

            @Override
            public void onChildViewRemoved(View view, View view1) {
                int c = tvs_container.getChildCount();
                if (c<2){
                    flatbtn_finish.setEnabled(false);
                }
            }
        });
        setup_FabAddClicked();
        setup_FinishBtnClicked();

        return v;
    }

    private void setup_FabAddClicked(){

        fab_add.setOnClickListener(view -> {
            int x = getRandomInt();
            TextView t = TVMaker.getSoftGenTv(getActivity());
            generatedIntStack.add(x);
            tvs_container.addView(t);
            t.setText(String.valueOf(x));
        });
    }

    private void setup_FinishBtnClicked(){
        flatbtn_finish.setOnClickListener(view -> {
            //step 1
            max2nums = new MyCalculator().get2MaxNumsfromStack(generatedIntStack);
            //step 2
            SortSumResultDialog d = new SortSumResultDialog().setMaxNums(max2nums);
            d.show(getChildFragmentManager(), "resultD");
            //step 3 (optional save to share pref)
            SharePrefWorker.getInstance(getActivity()).saveQueriedStack(generatedIntStack, max2nums, SharePrefWorker.QTYPE_RANDOM);
        });
    }

    private ArrayList<Integer> max2nums = null;

    public ArrayList<Integer> getFoundMaxNums(){
        return this.max2nums;
    }


    public void clearStackAndContainer(){
        generatedIntStack.clear();
        max2nums.clear();
        tvs_container.removeAllViews();
    }

    private int getRandomInt(){

        int randomNum = ThreadLocalRandom.current().nextInt(-100000, 100000 + 1);

        return randomNum;
    }
}