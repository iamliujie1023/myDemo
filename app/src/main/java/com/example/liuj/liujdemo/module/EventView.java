package com.example.liuj.liujdemo.module;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by jliu on 2018/3/22.
 */

public class EventView extends LinearLayout {
    public EventView(Context context) {
        super(context);
    }

    public EventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("liujie", "onTouchEvent");
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("liujie", "dispatchTouchEvent");

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("liujie", "onInterceptTouchEvent");

        return super.onInterceptTouchEvent(ev);
    }

}
