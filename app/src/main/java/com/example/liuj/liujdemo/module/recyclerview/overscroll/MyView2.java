package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2017/12/14.
 */

public class MyView2 extends RecyclerView {

    int mLastMotionX;
    float mTranX;
    LinearLayoutManager linearLayoutManager;
    private boolean mIsConsum;

    int mTouchSlop;

    public MyView2(Context context) {
        this(context, null);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTranX = getTranslationX();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIsConsum = false;
                mLastMotionX = (int) ev.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                handlerMoveEvent(ev);

                if (isConsum()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                float curTranX = getTranslationX();
                if (curTranX != mTranX) {
                    ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(this, "translationX", curTranX, mTranX);
                    animatorTranslateX.setDuration(300);
                    animatorTranslateX.start();
                }
                break;
            default:
                break;
        }

        return super.onTouchEvent(ev);
    }

    public void handlerMoveEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int dire = x - mLastMotionX;

        boolean isFirstPosVisible = linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
        boolean isLastPosVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition() == (getAdapter().getItemCount() - 1);

        if (isOverScroll(dire, isFirstPosVisible, isLastPosVisible)) {
            mIsConsum = true;
            setTranslationX(getTranslationX() + dire / 2);

            LogUtils.i("isFirstPosVisiable=" + isFirstPosVisible + ", curTranX =" + getTranslationX() + ", dire = " + dire);
        } else {
            mIsConsum = false;
        }


        mLastMotionX = (int) ev.getX();
    }

    private boolean isOverScroll(int dire, boolean isFirstPosVisible, boolean isLastPosVisible) {
        if (dire > 0 && isFirstPosVisible) {
            return true;
        }
        if (dire < 0 && isLastPosVisible) {
            return true;
        }

        return isOverScrollBack(dire);
    }

    private boolean isOverScrollBack(int dire) {
//        float delatTranX = getTranslationX() - mTranX;
//        if (isAAA(delatTranX)) {
//            return true;
//        }

        int curTranX = (int) getTranslationX();
        if (dire < 0 && curTranX > mTranX) {
            return true;
        }
        if (dire > 0 && curTranX < mTranX) {
            return true;
        }
        return false;
    }

    private boolean isAAA(float delatTranX) {
        int temp = (int) Math.abs(delatTranX);
        if (temp <= mTouchSlop ) {
            return true;
        }
        return false;
    }

    public boolean isConsum() {
        return mIsConsum;
    }




}