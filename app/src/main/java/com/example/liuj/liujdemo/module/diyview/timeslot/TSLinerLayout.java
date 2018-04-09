package com.example.liuj.liujdemo.module.diyview.timeslot;

import android.content.Context;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.sdk.LogUtils;

import java.util.List;

/**
 * Created by jliu on 2018/4/3.
 */
public class TSLinerLayout extends LinearLayout {
    private static final String TAG = "HorizontalSnapView";

//    public interface Callback {
//        void onScroll(int firstVisibleChildIndex, float visiblePercent, ViewGroup parent);
//
//        View getView(ViewGroup parent, int index);
//
//        int getViewCount();
//
//        void onItemSelected(int index, ViewGroup parent);
//    }
//
//    private Callback mCallback;

    private int mLastTouchX;
    private final int mTouchSlop;
    private Scroller mScroller;
    private int mPreviousSelectedIndex = 0;

    public TSLinerLayout(Context context) {
        this(context, null);
    }

    public TSLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setOrientation(HORIZONTAL);
    }

//    public void setCallback(Callback callback) {
//        mCallback = callback;
//        removeAllViews();
//        scrollTo(0,0);
//        if (mCallback != null) {
//            int viewCount = mCallback.getViewCount();
//            for (int i = 0; i < viewCount; i++) {
//                View child = mCallback.getView(this, i);
//                addView(child);
//            }
//        }
//    }

    public void resetData(List<TLModel> list) {
        removeAllViews();
        for (int i = 0, leghth = list.size(); i < leghth; i++) {
            TLModel tlModel = list.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.diyview_ts_item, this, false);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);
            tvTitle.setText(tlModel.title);
            tvDesc.setText(tlModel.mSubTitle);

            addView(view);
        }

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //当这里是EXACTLY时，如果前一个View把我们剩余的空间全占了时，下一个子View的测量会有问题
        //就需要重新测量
        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
            final int myWidth = MeasureSpec.getSize(widthMeasureSpec);
            final int childCount = getChildCount();
            int maxChildHeight = 0;

            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            }
            setMeasuredDimension(myWidth, resolveSize(maxChildHeight, heightMeasureSpec));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                int delta = x - mLastTouchX;
                scrollBy(-delta, 0);
                mLastTouchX = x;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                performSnapScroll();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int newX = mScroller.getCurrX();
            scrollTo(newX, 0);
//            invalidate();
        }
    }

    private void performSnapScroll() {
        int firstVisibleChildIndex = findFirstVisibleChild();
        if (firstVisibleChildIndex != -1) {
            View firstVisibleChild = getChildAt(firstVisibleChildIndex);
            final int viewRight = firstVisibleChild.getRight();
            final int visibleWidth = viewRight - getScrollX();
            boolean needScrollToNext = determineScrollToNext(visibleWidth, firstVisibleChild.getWidth());

            if (needScrollToNext) {
                View nextView = getChildAt(firstVisibleChildIndex + 1);
                mScroller.startScroll(getScrollX(), 0, nextView.getLeft() - getScrollX(), 0, 300);
            } else {
                mScroller.startScroll(getScrollX(), 0, firstVisibleChild.getLeft() - getScrollX(), 0, 300);
            }
            dispatchItemSelected(firstVisibleChildIndex, needScrollToNext);

            postInvalidate();
        }
    }


    /**
     * 是否需要滑向下一个View
     *
     * @param visibleWidth 当前第一个可见view的可见宽度
     * @param totalWidth   当前第一个可见view的总宽度
     * @return true滑至下一个View，false滑至当前view
     */
    protected boolean determineScrollToNext(int visibleWidth, int totalWidth) {
        return visibleWidth < (totalWidth / 2); //如果可见宽度不足该View的一半，就滑到下一个View
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!shouldDelayChildPressedState()) {
            return false;
        }
        boolean intercepted = false;
        int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastTouchX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                if (Math.abs(x - mLastTouchX) >= mTouchSlop) {
                    intercepted = true;
                    ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            default:
                break;
        }
        return intercepted;
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        int myWidth = getWidth();
        final int childCount = getChildCount();

//        calculateScrollPercent();

        if (childCount != 0) {
            View lastChild = getChildAt(childCount - 1);
            int rightOfLastChild = lastChild.getRight();
            int maxScrollX = rightOfLastChild - myWidth;
            int newX = Math.min(maxScrollX, x); //确保不会滑到最后一个child的右边
            newX = Math.max(newX, 0); //确保不会滑到第一个child的左边
            super.scrollTo(newX, y);
        } else {
            super.scrollTo(x, y);
        }
        invalidate();
    }

//    private void calculateScrollPercent() {
//        int childIndex = findFirstVisibleChild();
//        if (childIndex != -1) {
//            View child = getChildAt(childIndex);
//
//            int scrollX = getScrollX();
//
//            final int visibleWidth = child.getRight() - scrollX;
//
//            final float visiblePercent = visibleWidth / (float) child.getWidth();
//
//            dispatchScroll(childIndex, visiblePercent);
//        }
//    }

    @Override
    public boolean shouldDelayChildPressedState() {
        int childCount = getChildCount();
        if (childCount != 0) {
            View view = getChildAt(childCount - 1);

            int rightOfLastChild = view.getRight();
            int myWidth = getWidth();

            return rightOfLastChild > myWidth; //如果子view已经超出了我们的显示范围,我们就是可以滑动的了
        } else {
            return false;
        }
    }

    public int getSelectedIndex() {
        return mPreviousSelectedIndex;
    }

    public int findFirstVisibleChild() {
        int childCount = getChildCount();
        final int scrollX = getScrollX();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            final int childRight = child.getRight();
            if (childRight > scrollX) {
                return i;
            }
        }
        return -1;
    }

    public void scrollToView(int viewIndex) {
        int childCount = getChildCount();
        if (viewIndex < childCount) {
            View view = getChildAt(viewIndex);
            int viewLeft = view.getLeft();
            mScroller.startScroll(getScrollX(), 0, viewLeft - getScrollX(), 500);
//            dispatchItemSelected(viewIndex, false);
            postInvalidate();
        } else {
            LogUtils.i("view index out of bounds");
        }
    }

    public void scrollToNext() {
        int curIndex = findFirstVisibleChild();
        scrollToView(curIndex + 1);
    }

    private void dispatchItemSelected(int firstVisibleChildIndex, boolean needScrollToNext) {
//        if (mCallback != null) {
//            final int selectedIndex = needScrollToNext ? firstVisibleChildIndex + 1 : firstVisibleChildIndex;
//            if (mPreviousSelectedIndex != selectedIndex) {
//                mPreviousSelectedIndex = selectedIndex;
////                Log.e(TAG, "item selected with index : " + mPreviousSelectedIndex);
//                mCallback.onItemSelected(mPreviousSelectedIndex, this);
//            }
//        }
    }

    protected void dispatchScroll(int firstVisibleChildIndex, float visiblePercent) {
//        if (mCallback != null) {
//            mCallback.onScroll(firstVisibleChildIndex, visiblePercent, this);
//        }
    }
}