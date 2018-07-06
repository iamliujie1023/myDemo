package com.example.liuj.liujdemo.module.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerAct extends BaseActivity {


    @BindView(R.id.banner1)
    Banner mBanner1;

    @BindView(R.id.banner2)
    Banner mBanner2;

    @BindView(R.id.banner3)
    Banner mBanner3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_main_act);
        ButterKnife.bind(this);

        String[] images = getResources().getStringArray(R.array.image_urls);

        List<String> list = Arrays.asList(images);

        mBanner1.setImages(list).setImageLoader(new GlideImageLoader()).isAutoPlay(true).start();

    }


}
