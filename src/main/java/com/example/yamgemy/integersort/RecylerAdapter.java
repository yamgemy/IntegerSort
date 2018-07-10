package com.example.yamgemy.integersort;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yamgemy.integersort.Util.SharePrefWorker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<ArrayList<Stack<Integer>>> savedQueryList;
    private Context ctx;

    public RecylerAdapter(Context ctx){
        mInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
        refreshList();
    }

    public void refreshList(){
        savedQueryList = SharePrefWorker.getInstance(ctx).getSavedQueryList();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.query_history_card_layout, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ArrayList<Stack<Integer>> element = savedQueryList.get(position);
        //pool
        Stack<Integer> stack1 = element.get(0);
        String pool = stack1.toString();
        holder.tv_pool.setText(pool);

        //max
        Stack<Integer> stack2 = element.get(1);
        String maxes = stack2.toString();
        holder.tv_max.setText(maxes);

        //sum
        int s = stack2.get(0) + stack2.get(1);
        holder.tv_sum.setText(String.valueOf(s));

        //bg
        if (element.size()!=3){return;}
        Stack<Integer> stack3 = element.get(2);
        int qtype = stack3.get(0);
        if (qtype==SharePrefWorker.QTYPE_MANUAL){
            holder.qcard_bg.setBackgroundResource(R.color.DarkSlateBlue);
        }else{
            holder.qcard_bg.setBackgroundResource(R.color.grass_darker);
        }

        //time
        /***
        if (element.get(2).size()==1){return;}
        int subtime = element.get(2).get(1);
        boolean pm = false;
        if (subtime<0){pm = true;}
        String subtimeStr = String.valueOf(subtime);
        String year = subtimeStr.substring(0,4);
        String mon = subtimeStr.substring(4,6);
        String day = subtimeStr.substring(6,8);
        String hour = subtimeStr.substring(8,10);
        String min = subtimeStr.substring(10,12);
        String pmstr = pm==true?" pm": " am";
        holder.tv_time.setText(year+"-"+mon+"-"+day+" "+hour+":"+min+pmstr);
         **/
    }

    @Override
    public int getItemCount() {
        if (savedQueryList==null){
            return 0;
        }
        return savedQueryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_pool;
        public TextView tv_max;
        public TextView tv_sum;
        public RelativeLayout qcard_bg;
        //public TextView tv_time;

        public MyViewHolder(View card){
            super(card);
            tv_pool= card.findViewById(R.id.qcard_tv_pool_display);
            tv_max = card.findViewById(R.id.qcard_tv_max_display);
            tv_sum = card.findViewById(R.id.qcard_tv_sum_display);
            qcard_bg = card.findViewById(R.id.qcard_bg);
            //tv_time = card.findViewById(R.id.qcard_tv_time_display);
        }
    }
}
