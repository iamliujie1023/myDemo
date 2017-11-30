package com.example.liuj.liujdemo.tools;

import android.util.Log;

/**
 * Created by liuj on 2017/11/16.
 */

public class LogUtils {

    public static final String MY_TAG = "liujie";

    public static void i(String msg) {
        Log.i(MY_TAG, msg);
    }

    public static void e(String msg) {
        Log.e(MY_TAG, msg);
    }

}
