package com.example.liuj.sdk;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liuj on 2017/11/6.
 */

public class ToastHelper {

    public static final void toast(Context context, String strMsg) {
        Toast.makeText(context, strMsg, Toast.LENGTH_SHORT);
    }

    public static final void toastLong(Context context, String strMsg) {
        Toast.makeText(context, strMsg, Toast.LENGTH_SHORT);
    }

//    public static final void toast(String strMsg) {
//        toast(MyApplication.getInstance(), strMsg);
//    }
}
