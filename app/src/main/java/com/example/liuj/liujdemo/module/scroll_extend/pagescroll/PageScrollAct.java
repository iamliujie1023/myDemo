package com.example.liuj.liujdemo.module.scroll_extend.pagescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.liujdemo.tools.ColorUtils;
import com.example.liuj.liujdemo.view.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageScrollAct extends BaseActivity {

    @BindView(R.id.psv)
    PageScrollView mPageScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_extent_pagescroll_act);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        for (int i = 0; i < 10; i++) {
            int color = ColorUtils.getRandomColor();
            FrameLayout frameLayout = new FrameLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    CommonUtil.dip2px(60F));
            frameLayout.setBackgroundColor(color);
            frameLayout.setLayoutParams(layoutParams);

            mPageScrollView.getPage0().addView(frameLayout);
        }
    }


}