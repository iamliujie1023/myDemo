package com.example.liuj.liujdemo.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jliu on 2018/4/11.
 */
public class BaseNativePageAdapter extends PagerAdapter {

    private List<Integer> mData;
    private Context mContext;

    public BaseNativePageAdapter(Context context) {
        mData = new ArrayList<>();
        this.mContext = context;
    }

    public void reset(List<Integer> data) {
        this.mData.clear();
        this.mData.addAll(data);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int rImg = mData.get(position);
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(rImg);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



}