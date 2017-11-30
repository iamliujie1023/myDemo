package com.example.liuj.liujdemo.module.dexclassloader;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.tools.LogUtils;

/**
 * Created by liuj on 2017/11/21.
 */

public class MainActivity extends BaseAct {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClassLoader loader = MainActivity.class.getClassLoader();
        while (loader != null) {
            LogUtils.i(loader.toString());
            loader = loader.getParent();
        }
    }

}
