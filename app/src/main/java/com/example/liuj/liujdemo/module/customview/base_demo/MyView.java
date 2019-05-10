package com.example.liuj.liujdemo.module.customview.base_demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jliu on 2018/3/14.
 */

public class MyView extends View {

    private int mDefWidth;
    private int mDefHeight;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDefWidth = 200;
        mDefHeight = 200;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecModel = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecModel = MeasureSpec.getMode(widthMeasureSpec);

        if (widthSpecModel == MeasureSpec.AT_MOST && heightSpecModel == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefWidth, mDefHeight);
        } else if (widthSpecModel == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefWidth, heightSize);
        } else if (heightSpecModel == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mDefHeight);
        }
    }
}
