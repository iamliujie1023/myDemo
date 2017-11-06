package com.example.liuj.liujdemo.tools;

import android.content.Context;
import android.widget.Toast;

import com.example.liuj.liujdemo.home.MyApplication;

/**
 * Created by liuj on 2017/11/6.
 */

public class ToastHelper {

    public static final void toast(Context context, String strMsg) {
        Toast.makeText(context, strMsg, Toast.LENGTH_SHORT);
    }

    public static final void toast(String strMsg) {
        toast(MyApplication.getInstance(), strMsg);
    }
}
