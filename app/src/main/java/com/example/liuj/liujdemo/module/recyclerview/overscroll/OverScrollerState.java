package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.view.MotionEvent;

import com.example.liuj.liujdemo.module.recyclerview.overscroll.IDecoratorState;

/**
 * Created by liuj on 2017/12/15.
 */

public class OverScrollerState implements IDecoratorState {

    @Override
    public boolean handleMoveTouchEvent(MotionEvent event) {
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
        return 0;
    }

}
