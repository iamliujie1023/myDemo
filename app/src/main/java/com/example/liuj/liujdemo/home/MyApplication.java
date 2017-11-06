package com.example.liuj.liujdemo.home;

import android.app.Application;

/**
 * Created by liuj on 2017/11/6.
 */

public class MyApplication extends Application {

    private static MyApplication application;

    public static synchronized MyApplication getInstance() {
        if (null == application) {
            application = new MyApplication();
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
