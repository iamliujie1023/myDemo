package com.example.liuj.liujdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liuj on 2017/11/6.
 */
public class BaseAct extends AppCompatActivity {

    protected String PAGE_TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getClass().getSimpleName());
        PAGE_TAG = getClass().getSimpleName();

    }
}
