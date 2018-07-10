package com.example.yamgemy.integersort.Util;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yamgemy.integersort.R;

public class TVMaker {

    public static TextView getSoftGenTv(Context ctx){
        TextView tvx = new TextView(ctx);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);
        tvx.setLayoutParams(params);
        Drawable d = ctx.getDrawable(R.drawable.rounded_tv_v1);
        tvx.setBackground(d);
        tvx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        tvx.setTextAppearance(Typeface.BOLD);
        tvx.setMinWidth(dptoPixel(ctx, 50));
        return tvx;
    }


    private static int dptoPixel(Context ctx, int dp){
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        float mydpi = metrics.density;
        int mypx = (int)(dp*(mydpi/160));
        return mypx;
    }
}
