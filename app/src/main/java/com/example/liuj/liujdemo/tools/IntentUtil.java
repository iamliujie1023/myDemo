package com.example.liuj.liujdemo.tools;

import android.content.Context;
import android.content.Intent;

/**
 * Created by liuj on 2017/11/7.
 */

public class IntentUtil {

    public static void goToTargetAct(Context context, Class<?> aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
    }

}
