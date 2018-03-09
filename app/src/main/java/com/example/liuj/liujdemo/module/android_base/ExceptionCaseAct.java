package com.example.liuj.liujdemo.module.android_base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.sdk.LogUtils;

/**
 * Created by jliu on 2018/3/6.
 */
public class ExceptionCaseAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("ExceptionCaseAct onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("ExceptionCaseAct onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("ExceptionCaseAct onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("ExceptionCaseAct onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("ExceptionCaseAct onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.i("ExceptionCaseAct onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.i("ExceptionCaseAct onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("ExceptionCaseAct onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("ExceptionCaseAct onDestroy");
    }

}
