package com.example.liuj.liujdemo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liuj on 2017/12/21.
 */
public class RvForWrapperParent extends RecyclerView {

    public RvForWrapperParent(Context context) {
        super(context);
    }

    public RvForWrapperParent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RvForWrapperParent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重写该方法，达到使RecyclerView适应ScrollView的效果
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}