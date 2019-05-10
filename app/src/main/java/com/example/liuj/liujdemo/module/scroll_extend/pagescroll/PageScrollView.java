package com.example.liuj.liujdemo.module.scroll_extend.pagescroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.view.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class PageScrollView extends ScrollView {

    final String TAG = "NSV";

    private List<OnScrollChangedListener> IOnScrollChangedLsnList;

    private PageAlignListener mPageAlignListener;

    public interface OnScrollChangedListener {
        void onScrollChanged(int l, int scrollY, int oldl, int oldScrollY);
    }

    public PageScrollView(Context context) {
        super(context);
        init(context);
    }

    public PageScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PageScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        if (null == onScrollChangedListener) {
            return;
        }
        if (null == IOnScrollChangedLsnList) {
            IOnScrollChangedLsnList = new ArrayList<>();
        }
        IOnScrollChangedLsnList.add(onScrollChangedListener);
    }

    public void dispathOnScrollChangedLsn(int l, int scrollY, int oldl, int oldScrollY) {
        if (null == IOnScrollChangedLsnList) {
            return;
        }
        for (int i = 0; i < IOnScrollChangedLsnList.size(); i++) {
            IOnScrollChangedLsnList.get(i).onScrollChanged(l, scrollY, oldl, oldScrollY);
        }
    }

    /**
     * 更换成外部自定义title
     *
     * @param title
     */
    public void setDefaultTitle(String title) {
        promots[2] = "释放返回" + title;
        promots[3] = "下拉返回" + title;
    }

    private AutoAlign mAutoAlign;
    public View mDivider;
    private TextView mDividerTV;
    private ImageView mDividerImage;
    private final String[] promots = new String[]{
            "上拉查看图文详情", // align down
            "释放查看图文详情", // align up
            "释放返回商品详情", // align down
            "下拉返回商品详情", // align up
    };

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.scroll_extent_pdt_pages_layout, this, true);

        mAutoAlign = new AutoAlign();

        final View defaultPageContainer = findViewById(R.id.default_page_container);
        mDivider = findViewById(R.id.divider);
        final View moreContainer = findViewById(R.id.tuwen_detail_container);
        mDividerTV = (TextView) findViewById(R.id.divider_text);
        mDividerImage = (ImageView) findViewById(R.id.divider_img);
        mDividerTV.setText("上拉查看图文详情");

        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                moreContainer.getLayoutParams().height = PageScrollView.this.getHeight();

                mAutoAlign.nsv = PageScrollView.this;
                mAutoAlign.decoratorHeight = PageScrollView.this.getHeight();
                mAutoAlign.page0Top = 0;
                mAutoAlign.devidecHeight = mDivider.getHeight();

                defaultPageContainer.setMinimumHeight(PageScrollView.this.getHeight());
                mAutoAlign.page1Top = Math.max((PageScrollView.this.getHeight() + mDivider.getHeight()), mDivider.getBottom());

//                nsv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @Override
    protected void onScrollChanged(int l, int scrollY, int oldl, int oldScrollY) {
        super.onScrollChanged(l, scrollY, oldl, oldScrollY);
        dispathOnScrollChangedLsn(l, scrollY, oldl, oldScrollY);

//        Log.e(TAG, "onScrollChange scrollY:" + scrollY + " | oldScrollY:" + oldScrollY);
        if (mAutoAlign.detectPageLimitFling(scrollY, oldScrollY)) {
            return;
        }
//        Log.e(TAG, "onScrollChange continue");

        if (mAutoAlign.page1Top == scrollY) {
            if (mPageAlignListener != null) {
                mPageAlignListener.onPage1Align(mAutoAlign.decoratorHeight);
            }
        } else {
            if (mPageAlignListener != null) {
                mPageAlignListener.onPage1Out(scrollY);
            }
        }

        if (Math.abs((mAutoAlign.page1Top - mAutoAlign.decoratorHeight) - scrollY) < 10) {
            mDividerTV.setText(promots[0]);
        }

        mAutoAlign.changePromtText(scrollY);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        Log.e(TAG, "requestDisallowInterceptTouchEvent: " + disallowIntercept );
//        if (!disallowIntercept) {
//            Log.e(TAG, "requestDisallowInterceptTouchEvent: false");
//        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public void fling(int velocityY) {
//        Log.e(TAG, "fling");

        if (!mAutoAlign.flingAction(velocityY, this.getScrollY())) {
//            Log.e(TAG, "fling super");
            super.fling(velocityY);
        }
    }

    private int mPageIndex = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (mAutoAlign.page1Top == this.getScrollY()) {
                mPageIndex = 1;
            } else {
                mPageIndex = 0;
            }
            mAutoAlign.resetState();
        }

        boolean retval = super.onInterceptTouchEvent(ev);
//        Log.e(TAG, "onInterceptionTouchEvent:" + retval + " : " + MotionEvent.actionToString(ev.getAction()));
        return retval;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean retval = super.onTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_UP ||
                ev.getAction() == MotionEvent.ACTION_CANCEL) {
            int scrollY = this.getScrollY();
            mAutoAlign.touchUpAutoAlign(scrollY);
        }
//        Log.e(TAG, "onTouchEvent:" + retval + " : " + MotionEvent.actionToString(ev.getAction()));
        return retval;
    }


    public void setPageAlignListener(PageAlignListener listener) {
        mPageAlignListener = listener;
    }

    public void alignPage1() {
        mAutoAlign.alignPage1();
    }

    public void alignPage0() {
        mAutoAlign.alignPage0();
    }

    public void resetState() {
        mAutoAlign.resetState();
    }

    public void centerAlignView(View view) {
        mAutoAlign.alignView(view);
    }

    public boolean isChildVisiable(View child) {
        int screenTop = getScrollY();
        int screenBottom = screenTop + mAutoAlign.decoratorHeight;
        int childTop = child.getTop();
        int childBottom = child.getBottom();
        if (childTop > screenTop && childBottom < screenBottom) {
            return true;
        }
        return false;
    }



    public View addPage1Content(LayoutInflater inflater, int layoutId) {
        ViewGroup page1 = getPage1();
        View retval = inflater.inflate(layoutId, page1, false);
        page1.addView(retval, Math.max(0, page1.getChildCount() - 1));
        return retval;
    }

    public View getPage1BackToTopView() {
        return getPage1().findViewById(R.id.img_back_top);
    }

    public void setPageBackToTopViewUp() {
        View view = getPage1().findViewById(R.id.img_back_top);
        LayoutParams layoutParams = new LayoutParams(CommonUtil.dip2px(41f), CommonUtil.dip2px(41f));
        layoutParams.setMargins(0, 0, CommonUtil.dip2px(15f), CommonUtil.dip2px(59f));
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        view.setLayoutParams(layoutParams);
    }


    public ViewGroup getPage1() {
        return (ViewGroup) this.findViewById(R.id.tuwen_detail_container);
    }

    public ViewGroup getPage0() {
        return (ViewGroup) this.findViewById(R.id.default_page_container);
    }

    private class AutoAlign {
        public int decoratorHeight;
        public int page0Top;
        public int page1Top;
        public int devidecHeight;
        public ScrollView nsv;
        public boolean isPageLimitFling;
        private boolean isFling;

        public AutoAlign() {
            resetState();
        }

        public String logPage1Visibility(int scrollY) {
            return isPage1Visiable(scrollY) ? "page1 visiable" : "page1 invisiable";
        }

        public boolean isPage1Visiable(int scrollY) {
            return scrollY + decoratorHeight > page1Top;
        }

        public void alignPage1() {
            resetState();
            nsv.smoothScrollTo(0, page1Top);
        }

        public void touchUpAutoAlign(int scrollY) {
            if (isFling) return;

            if (!isPage1Visiable(scrollY)) return;


            if (page1Top - scrollY > (decoratorHeight / 2)) {
//                Log.e(TAG, "align page0");
                nsv.post(new Runnable() {
                    @Override
                    public void run() {
                        nsv.smoothScrollTo(0, page1Top - decoratorHeight);
                    }
                });
            } else {
//                Log.e(TAG, "align page1");
                nsv.post(new Runnable() {
                    @Override
                    public void run() {
                        nsv.smoothScrollTo(0, page1Top);
                    }
                });
            }
        }

        public boolean flingAction(float velocityY, int scrollY) {
//            Log.e(TAG, "Y: " + velocityY);

            isFling = true;

            if (!isPage1Visiable(scrollY)) {
                isPageLimitFling = true;
                return false;
            } else { // page1 edge visiable, handly it manually
//                Log.e(TAG, "fling auto align");
                if (velocityY > 0) { // downward => page1
                    // align page1
//                    Log.e(TAG, "fling auto align pag1");
                    nsv.post(new Runnable() {
                        @Override
                        public void run() {
                            nsv.smoothScrollTo(0, page1Top);
                        }
                    });

                } else { // upward => page0
//                    Log.e(TAG, "fling auto align pag0 ");
                    nsv.post(new Runnable() {
                        @Override
                        public void run() {
                            nsv.smoothScrollTo(0, page1Top - decoratorHeight);
                        }
                    });

                }

                return true;
            }


        }

        public void resetState() {
            isFling = false;
            isPageLimitFling = false;
        }

        public boolean detectPageLimitFling(int scrollY, int oldScrollY) {
            if (isFling && isPageLimitFling && scrollY + decoratorHeight > page1Top) {
                int limitY = page1Top - decoratorHeight;
//                Log.e(TAG, "detected fling set force to page1:" + limitY);
                nsv.scrollTo(0, limitY);
                return true;
            }
            return false;
        }

        public void changePromtText(int scrollY) {
            if (!isPage1Visiable(scrollY)) return;

            int changeLimit = decoratorHeight / 10;
            int top = (decoratorHeight / 2) - (changeLimit / 2);
            int bottom = (decoratorHeight / 2) + (changeLimit / 2);

            float percent = 0;
            int currScroll = (page1Top - scrollY);
            if (currScroll < top) {
                percent = 0;
            } else if (currScroll > bottom) {
                percent = 1;
            } else {
                percent = 1f * (currScroll - top) / changeLimit;
            }
//            Log.e(TAG, String.format("changeLimit: %d |top: %d |bottom: %d |currScroll: %d| per: %f", changeLimit, top, bottom, currScroll, percent));
            if (mPageIndex == 1) {
                mDividerImage.setRotation(percent * 180 + 180);
            } else {
                percent = 1 - percent;
                mDividerImage.setRotation(percent * 180);
            }
            if (page1Top - scrollY > (decoratorHeight / 2)) { // page0
                mDividerTV.setText(promots[mPageIndex * 2 + 0]);
            } else { // page1
                mDividerTV.setText(promots[mPageIndex * 2 + 1]);
            }
        }

        public void alignPage0() {
            resetState();
            nsv.smoothScrollTo(0, 0);
        }

        public void alignView(View view) {
            int top = view.getTop();
            int height = view.getHeight();

            resetState();
            nsv.smoothScrollTo(0, top - ((decoratorHeight - height) / 2));
        }
    }


    public interface PageAlignListener {
        void onPage1Align(int page1Height);

        void onPage1Out(int scrollY);
    }

}
