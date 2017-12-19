package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liuj on 2017/12/15.
 * 参考github上某位大神的代码，进行简单（ctrl c + ctrl v）的修改
 * 大神源码地址：https://github.com/EverythingMe/overscroll-decor
 */
public class RvHorOverScrollHelper implements View.OnTouchListener {

    protected final OverScrollStartAttributes mStartAttr;

    private IdleState mIdleState;
    private OverScrollerState mOverScrollerState;
    private BounceBackState mBounceBackState;

    private IDecoratorState mCurrentState;

    private RecyclerView mRecyclerView;

    public static void attach(RecyclerView recyclerView){
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) layoutManager).getOrientation() != LinearLayoutManager.HORIZONTAL) {
                return;
            }
        }
        new RvHorOverScrollHelper(recyclerView);
    }

    public RvHorOverScrollHelper(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setOnTouchListener(this);

        mStartAttr = new OverScrollStartAttributes();

        mIdleState = new IdleState();
        mOverScrollerState = new OverScrollerState();
        mBounceBackState = new BounceBackState();

        mCurrentState = mIdleState;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                return mCurrentState.handleMoveTouchEvent(event);
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                return mCurrentState.handleUpOrCancelTouchEvent(event);
        }

        return false;
    }

    private void issueStateTransition(IDecoratorState state) {
        IDecoratorState oldState = mCurrentState;
        mCurrentState = state;
        mCurrentState.handleEntryTransition(oldState);
    }

    private class OverScrollStartAttributes {
        protected int mPointerId;   //不支持不同的手指去玩
        protected float mAbsOffset; //一开始rv的Tranx值
        protected boolean mDir; // True = 'forward', false = 'backwards'.
    }

    class MotionAttributes {
        public float mAbsOffset;   // curTranx
        public float mDeltaOffset; //
        public boolean mDir;       // True = 'forward', false = 'backwards'.

        public boolean init(View view, MotionEvent event) {
            // We must have history available to calc the dx. Normally it's there - if it isn't temporarily,
            // we declare the event 'invalid' and expect it in consequent events.
            if (event.getHistorySize() == 0) {
                return false;
            }

            // Allow for counter-orientation-direction operations (e.g. item swiping) to run fluently.
            final float dy = event.getY(0) - event.getHistoricalY(0, 0);
            final float dx = event.getX(0) - event.getHistoricalX(0, 0);
            if (Math.abs(dx) < Math.abs(dy)) {
                return false;
            }

            mAbsOffset = view.getTranslationX();
            mDeltaOffset = dx;
            mDir = mDeltaOffset > 0;

            return true;
        }
    }

    class IdleState implements IDecoratorState {
        MotionAttributes mMoveAttr;

        public IdleState() {
            mMoveAttr = new MotionAttributes();
        }

        @Override
        public boolean handleMoveTouchEvent(MotionEvent event) {
            final View view = mRecyclerView;
            if (!mMoveAttr.init(view, event)) {
                return false;
            }

            // Has over-scrolling officially started?
            if ((!view.canScrollHorizontally(-1) && mMoveAttr.mDir) ||
                    (!view.canScrollHorizontally(1) && !mMoveAttr.mDir)) {

                // Save initial over-scroll attributes for future reference.
                mStartAttr.mPointerId = event.getPointerId(0);
                mStartAttr.mAbsOffset = mMoveAttr.mAbsOffset;
                mStartAttr.mDir = mMoveAttr.mDir;

                issueStateTransition(mOverScrollerState);
                return mOverScrollerState.handleMoveTouchEvent(event);
            }

            return false;
        }

        @Override
        public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
            return false;
        }

        @Override
        public void handleEntryTransition(IDecoratorState fromState) {
        }

        @Override
        public int getStateId() {
            return STATE_IDLE;
        }
    }

    class OverScrollerState implements IDecoratorState {

        protected final float mTouchDragRatioFwd = 3F;
        protected final float mTouchDragRatioBck = 1F;

        MotionAttributes mMoveAttr;

        int mCurrDragState;

        public OverScrollerState() {
            mMoveAttr = new MotionAttributes();
        }

        @Override
        public boolean handleMoveTouchEvent(MotionEvent event) {
            // Switching 'pointers' (e.g. fingers) on-the-fly isn't supported -- abort over-scroll
            // smoothly using the default bounce-back animation in this case.
            if (mStartAttr.mPointerId != event.getPointerId(0)) {
                issueStateTransition(mBounceBackState);
                return true;
            }

            final View view = mRecyclerView;
            if (!mMoveAttr.init(view, event)) {
                // Keep intercepting the touch event as long as we're still over-scrolling...
                return true;
            }

            float deltaOffset = mMoveAttr.mDeltaOffset / (mMoveAttr.mDir == mStartAttr.mDir ? mTouchDragRatioFwd : mTouchDragRatioBck);
            float newOffset = mMoveAttr.mAbsOffset + deltaOffset;

            // If moved in counter direction onto a potential under-scroll state -- don't. Instead, abort
            // over-scrolling abruptly, thus returning control to which-ever touch handlers there
            // are waiting (e.g. regular scroller handlers).
            if ( (mStartAttr.mDir && !mMoveAttr.mDir && (newOffset <= mStartAttr.mAbsOffset)) ||
                    (!mStartAttr.mDir && mMoveAttr.mDir && (newOffset >= mStartAttr.mAbsOffset)) ) {
                view.setTranslationX(mStartAttr.mAbsOffset);
                event.offsetLocation(mStartAttr.mAbsOffset - event.getX(0), 0f);
                issueStateTransition(mIdleState);
                return true;
            }

            if (view.getParent() != null) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
            }

            view.setTranslationX(newOffset);

            return true;
        }

        @Override
        public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
            issueStateTransition(mBounceBackState);
            return false;
        }

        @Override
        public void handleEntryTransition(IDecoratorState fromState) {
            mCurrDragState = (mStartAttr.mDir ? STATE_DRAG_START_SIDE : STATE_DRAG_END_SIDE);
        }

        @Override
        public int getStateId() {
            return mCurrDragState;
        }
    }

    private class BounceBackState implements IDecoratorState , Animator.AnimatorListener{

        public BounceBackState() {
        }

        @Override
        public boolean handleMoveTouchEvent(MotionEvent event) {
            return true;
        }

        @Override
        public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
            return true;
        }

        @Override
        public void handleEntryTransition(IDecoratorState fromState) {
            View view = mRecyclerView;
            float curTranX = view.getTranslationX();
            ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(view, "translationX", curTranX, mStartAttr.mAbsOffset);
            animatorTranslateX.addListener(this);
            animatorTranslateX.setDuration(300);
            animatorTranslateX.start();
        }

        @Override
        public int getStateId() {
            return STATE_BOUNCE_BACK;
        }

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            issueStateTransition(mIdleState);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }

}