package com.example.liuj.sdk;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by liuj on 2017/11/16.
 */

public class LogUtils {

    public static final String MY_TAG = "liujie";

    public static void i(String msg) {
        Log.i(MY_TAG, msg);
    }

    public static void e(String msg) {
        Logger.e(MY_TAG, msg);
    }


    public static void d(String h, String msg) {
        Logger.d(h, msg);
    }

    public static final void println(String msg) {
        System.out.println(msg);
    }

    public static final void print(String msg) {
        System.out.print(msg);
    }

}
