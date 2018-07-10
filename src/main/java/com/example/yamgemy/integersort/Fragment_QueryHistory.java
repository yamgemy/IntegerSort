package com.example.yamgemy.integersort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yamgemy.integersort.Globals.Globals;

public class Fragment_QueryHistory extends Fragment {
    private RecyclerView rv;
    private RecylerAdapter ra;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag1_query_history, container, false);
        rv = v.findViewById(R.id.frag1_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ra = new RecylerAdapter(getActivity());
        rv.setAdapter(ra);

        v. findViewById(R.id.fab_random).setOnClickListener(View ->{
            Globals.main.getMainVp().setCurrentItem(1);});
        v. findViewById(R.id.fab_manual).setOnClickListener(View ->{Globals.main.getMainVp().setCurrentItem(2);});
        return v;
    }

    public void refresh(){
        ra.refreshList();
        ra.notifyDataSetChanged();
        rv.setAdapter(ra);
    }
}
