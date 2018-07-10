package com.example.yamgemy.integersort;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yamgemy.integersort.Globals.Globals;
import com.example.yamgemy.integersort.Util.FadeTransformer;
import com.example.yamgemy.integersort.Util.SharePrefWorker;

public class MainActivity extends AppCompatActivity {
    private ViewPager mainvp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainvp = findViewById(R.id.vp_main);
        FragmentManager fm = getSupportFragmentManager();
        mainvp.setAdapter(new FadepagerAdapter(fm));
        mainvp.setPageTransformer(false, new FadeTransformer());
        mainvp.setOffscreenPageLimit(2);

        mainvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    Globals.frag_q.refresh();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //          .setAction("Action", null).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Globals.main = this;
    }

    @Override
    public void onBackPressed() {
        if (mainvp.getCurrentItem() == 0){
            super.onBackPressed();
            this.finish();
        }else{
            mainvp.setCurrentItem(0);
        }
    }

    public ViewPager getMainVp(){
        return this.mainvp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            SharePrefWorker.getInstance(this).clearSavedQuery();
            Globals.frag_q.refresh();
        }

        return super.onOptionsItemSelected(item);
    }
}
