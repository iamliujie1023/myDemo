package com.example.liuj.liujdemo.module.view.scroller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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

    private ICallback mCallback;
    public void setCallback(ICallback callback) {
        mCallback = callback;
    }

    private boolean isBeginSmooth = false;
    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;

        int scrollY = getScrollY();
        int deltaY = destY - scrollY;

        //1000ms 向deltax 移动
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 100);
        invalidate();
        isBeginSmooth = true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
            Log.i("liujie", " mScroller.computeScrollOffset = true" );
        } else {
            Log.i("liujie", " mScroller.computeScrollOffset = false" );
            if (isBeginSmooth) {
                isBeginSmooth = false;
                if (null != mCallback) {
                    mCallback.onSmoothFinish();
                }
            }
        }
    }

    public interface ICallback{
        void onSmoothFinish();
    }

}
