package com.example.liuj.liujdemo.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuj.R;
import com.example.liuj.liujdemo.application.MyApplication;
import com.example.liuj.liujdemo.module.banner.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerHolder extends BaseHolder {

    @BindView(R.id.banner1)
    Banner mBanner;

    public static BannerHolder obtain(Context context, ViewGroup viewGroup) {
        return new BannerHolder(LayoutInflater.from(context).inflate(
                R.layout.rv_item_banner_layout,
                viewGroup,
                false)
        );
    }

    public BannerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(Object o) {
        String[] images = MyApplication.getInstance().getResources().getStringArray(R.array.image_urls);
        List<String> list = Arrays.asList(images);
        mBanner.setImages(list).setImageLoader(new GlideImageLoader()).isAutoPlay(true).start();
    }

}
