package com.example.liuj.liujdemo.module.diyview.timeslot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liuj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jliu on 2018/4/3.
 */
public class TSScrollView extends HorizontalScrollView {

    private LinearLayout mLinearLayout;
    private List<TLModel> mData;

    public TSScrollView(Context context) {
        this(context, null);
    }

    public TSScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setFillViewport(true);

        mData = new ArrayList<>();
        removeAllViews();
        mLinearLayout = new LinearLayout(getContext());
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLinearLayout.setLayoutParams(new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mLinearLayout);
    }

    public void resetData(List<TLModel> list) {
        mData.clear();
        mData.addAll(list);

        mLinearLayout.removeAllViews();
        for (int i = 0, leghth = list.size(); i < leghth; i++) {
            TLModel tlModel = list.get(i);

            View view = LayoutInflater.from(getContext()).inflate(R.layout.diyview_ts_item, mLinearLayout, false);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);
            tvTitle.setText(tlModel.title);
            tvDesc.setText(tlModel.mSubTitle);

            mLinearLayout.addView(view);
        }

        invalidate();
    }

    public void scrollToPos(int pos) {
        int count = mLinearLayout.getChildCount();
        if (pos >= count) {
            return;
        }
        View view = mLinearLayout.getChildAt(pos);
        int newScrollX = view.getLeft();

//        LogUtils.i("view=" + view.getMeasuredWidth());
//        LogUtils.i("viewDP=" + CommonUtil.dip2px(80F));
//        LogUtils.i("curScroll=" + getScrollX());
//        LogUtils.i("newScrollX=" + newScrollX);

        smoothScrollTo(newScrollX, 0);
        invalidate();
    }

}