package com.example.liuj.liujdemo.module.android_base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.sdk.LogUtils;

/**
 * Created by jliu on 2018/3/6.
 */
public class ConfigChangeCaseAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("ConfigChangeCaseAct onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("ConfigChangeCaseAct onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("ConfigChangeCaseAct onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("ConfigChangeCaseAct onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("ConfigChangeCaseAct onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.i("ConfigChangeCaseAct onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.i("ConfigChangeCaseAct onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("ConfigChangeCaseAct onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("ConfigChangeCaseAct onDestroy");
    }

}
