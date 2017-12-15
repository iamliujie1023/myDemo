package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liuj on 2017/12/15.
 */
public class OverScrollHlper implements View.OnTouchListener {

    protected final OverScrollStartAttributes mStartAttr = new OverScrollStartAttributes();
    protected final MotionAttributes mCurAttr = new MotionAttributes();

    private IdleState mIdleState;
    private OverScrollerState mOverScrollerState;
    private BounceBackState mBounceBackState;
    private IDecoratorState mCurrentState;

    public OverScrollHlper() {
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

    protected static class OverScrollStartAttributes {

    }

    public static class MotionAttributes {
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

}