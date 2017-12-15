package com.example.liuj.liujdemo.module.recyclerview.overscroll;

import android.view.MotionEvent;

/**
 * Created by liuj on 2017/12/15.
 */
public interface IDecoratorState {

    /** No over-scroll is in-effect. */
    int STATE_IDLE = 0;

    /** User is actively touch-dragging, thus enabling over-scroll at the view's <i>start</i> side. */
    int STATE_DRAG_START_SIDE = 1;

    /** User is actively touch-dragging, thus enabling over-scroll at the view's <i>end</i> side. */
    int STATE_DRAG_END_SIDE = 2;

    /** User has released their touch, thus throwing the view back into place via bounce-back animation. */
    int STATE_BOUNCE_BACK = 3;


    /**
     * Handle a motion (touch) event.
     *
     * @param event The event from onTouch.
     * @return Return value for onTouch.
     */
    boolean handleMoveTouchEvent(MotionEvent event);

    /**
     * Handle up / touch-cancel events.
     *
     * @param event The event from onTouch.
     * @return Return value for onTouch.
     */
    boolean handleUpOrCancelTouchEvent(MotionEvent event);

    /**
     * Handle a transition onto this state, as it becomes 'current' state.
     * @param fromState
     */
    void handleEntryTransition(IDecoratorState fromState);

    /**
     * The client-perspective ID of the state associated with this (internal) one. ID's
     */
    int getStateId();

}