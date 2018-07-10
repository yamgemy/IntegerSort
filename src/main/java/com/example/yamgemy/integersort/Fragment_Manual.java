package com.example.yamgemy.integersort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatEditText;
import com.example.yamgemy.integersort.Util.MyCalculator;
import com.example.yamgemy.integersort.Util.SharePrefWorker;
import com.example.yamgemy.integersort.Util.TVMaker;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Stack;

public class Fragment_Manual extends Fragment {

    private FlexboxLayout fbox;
    private FlatEditText ed1;
    private FloatingActionButton fabx;
    private TextView warn_tv;
    private FlatButton fin_btn;

    private ArrayList<Integer> max2nums;
    private Stack<Integer> inputStack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        max2nums = new ArrayList<>();
        inputStack = new Stack<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag3_manual_input, container, false);

        ed1 = v.findViewById(R.id.Inputs_edtxt);
        ed1.getAttributes().setTheme(R.array.dark,getResources());
        ed1.onThemeChange();
        ScrollView sv = v.findViewById(R.id.input_scrollview);

        fabx = v.findViewById(R.id.inputs_fab_enter);
        fbox = v.findViewById(R.id.inputs_tv_container);
        warn_tv = v.findViewById(R.id.InputNum_warning);
        fin_btn = v.findViewById(R.id.input_cal_btn);
        fin_btn.setEnabled(false);

        fbox.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View view, View view1) {
                int c = fbox.getChildCount();
                if (c>=2){
                    fin_btn.setEnabled(true);
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
                int c = fbox.getChildCount();
                if (c<2){
                    fin_btn.setEnabled(false);
                }
            }
        });

        setupFabListener(fabx);
        setupEdListener(ed1);
        setup_FinishBtnClicked();
        return v;
    }

    private void setup_FinishBtnClicked(){
        fin_btn.setOnClickListener(v->{
            max2nums = new MyCalculator().get2MaxNumsfromStack(inputStack);
            SortSumResultDialog d = new SortSumResultDialog().setMaxNums(max2nums);
            d.show(getChildFragmentManager(), "resultD");
            //step 3 (optional save to share pref)
            SharePrefWorker.getInstance(getActivity()).saveQueriedStack(inputStack, max2nums, SharePrefWorker.QTYPE_MANUAL);
        });
    }

    private void setupFabListener(FloatingActionButton fab){
        fab.setOnClickListener(view ->{
            //get num, , make tv, add view settext
            //1
            String s = ed1.getText().toString();

            //2
            if (isValidInteger(s)){
                TextView t = TVMaker.getSoftGenTv(getActivity());
                fbox.addView(t);
                t.setText(s); //todo

                //3 add to stack
                inputStack.add(Integer.parseInt(s));
                ed1.setText("");
            }
        });
    }

    private boolean isValidInteger(String s){
        if (s!=null && !"".equals(s)){
            try{
                int t = Integer.parseInt(s);
                return true;
            }catch(Exception e){
                return false;
            }
        }else{
            warn_tv.setVisibility(TextView.INVISIBLE);//if no str, clear warn
        }
        return false;
    }

    private void setupEdListener(FlatEditText ed){
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String s = charSequence.toString();
                    if (isValidInteger(s)){
                        fabx.setEnabled(true);
                        warn_tv.setVisibility(TextView.INVISIBLE);
                    }else{
                        fabx.setEnabled(false);
                        warn_tv.setVisibility(TextView.VISIBLE);
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void clearStackAndContainer(){
        inputStack.clear();
        max2nums.clear();
        fbox.removeAllViews();
    }
}
