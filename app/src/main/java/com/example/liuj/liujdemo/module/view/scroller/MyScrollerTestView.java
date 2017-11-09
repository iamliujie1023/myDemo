package com.example.liuj.liujdemo.module.view.scroller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by liuj on 2017/11/9.
 */

public class MyScrollerTestView extends FrameLayout {

    Scroller mScroller;

    public MyScrollerTestView(@NonNull Context context) {
        this(context, null);
    }

    public MyScrollerTestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        //1000ms 内想deltax 移动
        mScroller.startScroll(scrollX, 0, delta, 0, 100);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

}
