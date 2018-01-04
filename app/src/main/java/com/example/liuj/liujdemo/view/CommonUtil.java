package com.example.liuj.liujdemo.view;

import android.content.Context;
import android.util.DisplayMetrics;


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

}
