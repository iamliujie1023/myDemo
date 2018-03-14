package com.example.liuj.liujdemo.module.customview.base_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

/**
 * Created by jliu on 2018/3/14.
 */
public class ViewDemo extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_base_view_demo);
    }


}
