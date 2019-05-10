package com.example.liuj.liujdemo.module.transformation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.ScaleAnimation;

import com.example.liuj.liujdemo.base.BaseActivity;

/**
 * Created by jliu on 2018/3/12.
 */

public class A extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);

    }

}
