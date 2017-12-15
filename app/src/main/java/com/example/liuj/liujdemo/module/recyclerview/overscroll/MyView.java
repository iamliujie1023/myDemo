package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.TranslateAnimation;

import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2017/12/14.
 */

public class MyView extends RecyclerView {

    private float mFirstMotionX;
    private float mFirstMotionY;
    float mLastMotionX;

    int lastX;

    int mTouchSlop;
    float mTranX;
    LinearLayoutManager linearLayoutManager;

    private Rect dirty = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
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
        LogUtils.i("tranX= " + mTranX);
    }

    private void reqParentDisallowInterceptTouchEvent(boolean bln) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(bln);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                int dire = (x - lastX);
                if (Math.abs(dire) >= mTouchSlop || dire == 0) {
                    break;
                }
                LogUtils.i("ACTION_MOVE ");
                if (dire > 0 && this.canScrollHorizontally(-1) || dire < 0 && this.canScrollHorizontally(1)) {
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        LogUtils.i(" onInterceptTouchEvent ACTION_MOVE intercepted = " + intercepted);
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        super.onTouchEvent(ev);

        int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mFirstMotionX = ev.getX();
                mFirstMotionY = ev.getY();
                break;
//            case MotionEvent.ACTION_MOVE:
//                this.pullEvent(ev);
//                if (isNeedAnimation()) {
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                dirty.setEmpty();
//                break;
//            case MotionEvent.ACTION_UP:
//                if (isNeedAnimation()) {
//                    backAnimation();
//                }
//                break;

            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                int dir = (int) (x - mLastMotionX);
                int diff = Math.abs(dir);

                boolean isFirstPosVisible = linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
                boolean isLastPosVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition() == (getAdapter().getItemCount() - 1);
                if (isLastPosVisible && dir < 0 ) {
                    setTranslationX(getTranslationX() - diff / 2);
                } else if (isFirstPosVisible && dir > 0 ) {
                    setTranslationX(getTranslationX() + diff / 2);
                }
                LogUtils.i("onTouchEvent ACTION_MOVE = " + diff);
                mLastMotionX = x;
                break;
            case MotionEvent.ACTION_UP:
                float curTranX = getTranslationX();
                ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(this, "translationX", curTranX, mTranX);
                animatorTranslateX.start();
                break;
            default:
                break;
        }

        return super.onTouchEvent(ev);
    }

    private boolean isNeedMove(int dir) {
        int offsetX = getMeasuredWidth() - getWidth();
        int scrollX = getScrollX();

        // view的内容在最左边，且向右拖拽
        if (scrollX == 0 && (this.getLeft() - dir / 2) > 0) {
            return true;
        }
        // view的内容在最右边，且向左拖拽
        if (scrollX == offsetX && (this.getRight() - dir / 2) < this.getWidth()) {
            return true;
        }
        return false;
    }

    public void pullEvent(MotionEvent event) {
        float curMotionX = event.getX();
        int curScrollValue;
        curScrollValue = mLastMotionX == 0 ? 0 : (int) (mLastMotionX - curMotionX);
        mLastMotionX = event.getX();
        setScroll(curScrollValue);
    }

    private void setScroll(int curScrollValue) {
        // 初始化头部矩形
        if (isNeedMove(curScrollValue)) {
            if (dirty.isEmpty()) {
                // 保存正常的布局位置
                dirty.set(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
            }
            this.layout(this.getLeft() - curScrollValue / 2, this.getTop(), this.getRight() - curScrollValue / 2, this.getBottom());
        } else {
            dirty.setEmpty();
        }
    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !dirty.isEmpty();
    }

    public void backAnimation() {
        TranslateAnimation animation = new TranslateAnimation(this.getLeft(), dirty.left, 0, 0);
        animation.setDuration(200);
        this.startAnimation(animation);

        this.layout(0, dirty.top, this.getWidth(), dirty.bottom);
        mLastMotionX = 0;
        dirty.setEmpty();
    }

}
