package com.example.liuj.liujdemo.view;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.liuj.liujdemo.home.MyApplication;


/**
 * Created by liuj on 2018/1/4.
 */
public class CommonUtil {

    public static int getWidth(Context context) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getHeight(Context context) {
        if (context == null) {
            return 0;
        }

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    //dp转px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }    //dp转px

    public static int dip2px(float dpValue) {
        final float scale = MyApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context context, int res) {
        float dpValue = context.getResources().getDimensionPixelSize(res);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
