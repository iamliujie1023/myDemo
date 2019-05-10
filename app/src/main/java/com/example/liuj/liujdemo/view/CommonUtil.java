package com.example.liuj.liujdemo.view;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.liuj.liujdemo.application.MyApplication;


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

    /**
     * format the int to string
     *
     * @param value
     * @return
     */
    public static String deRoundWith100AndDot(int value) {
        float unit = 100f;
        final String format = "%.2f";
        return String.format(format, value / unit);
    }

    /**
     * 保留小数点后两位, 并去除尾部多余的0
     */
    public static String deRound(int value, int unit) {
        String ret = deRound(value, unit, 2);
        // 去掉小数点尾部多余的0
        int point = 0;
        if ((point = ret.indexOf(".")) >= 0) {
            int tail = ret.length() - 1;
            while (ret.charAt(tail) == '0') {
                --tail;
            }

            ret = ret.substring(0, tail + 1);
        }

        return ret;
    }

    /**
     * format the int to string
     */
    public static String deRound(int value, int unit, int pres) {
        if (value % unit == 0) {
            return String.valueOf(value / unit);
        }

        final String format = "%." + pres + "f";
        return String.format(format, value / (double) unit);
    }

}
