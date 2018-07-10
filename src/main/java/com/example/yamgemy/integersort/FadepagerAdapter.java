package com.example.yamgemy.integersort;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yamgemy.integersort.Globals.Globals;

import java.util.ArrayList;

import static com.example.yamgemy.integersort.Globals.Globals.frag_m;
import static com.example.yamgemy.integersort.Globals.Globals.frag_q;
import static com.example.yamgemy.integersort.Globals.Globals.frag_r;

public class FadepagerAdapter extends FragmentPagerAdapter {

    public FadepagerAdapter(FragmentManager fm){
        super(fm);
        this.initItems();
    }

    private ArrayList<Fragment> fragsList;

    private void initItems(){
        this.fragsList = new ArrayList<>();
        frag_q = new Fragment_QueryHistory();
        frag_r = new Fragment_Randomize();
        frag_m = new Fragment_Manual();

        this.fragsList.add(frag_q);
        this.fragsList.add(frag_r);
        this.fragsList.add(frag_m);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        return fragsList.get(position);
    }


}
