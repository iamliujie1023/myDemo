package com.example.liuj.liujdemo.module.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.liuj.sdk.LogUtils;

public class MyTestViewGroup extends RelativeLayout {

    public MyTestViewGroup(Context context) {
        super(context);
    }

    public MyTestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.i("ViewGroup onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.i("ViewGroup dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("ViewGroup onTouchEvent");
        return super.onTouchEvent(event);
    }

}
