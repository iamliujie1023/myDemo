package com.example.liuj.liujdemo.module.diyview.ptv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jliu on 2018/4/12.
 */

public class PTvMainAct extends BaseActivity {

    @BindView(R.id.ptv_1)
    PTextView mPtv1;
    @BindView(R.id.ptv_2)
    PTextView mPtv2;
    @BindView(R.id.ptv_3)
    PTextView mPtv3;
    @BindView(R.id.ptv_4)
    PTextView mPtv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diyview_ptv_main_act);
        ButterKnife.bind(this);

        int color1 = ContextCompat.getColor(this, R.color.start_received);
        int color2 = ContextCompat.getColor(this, R.color.text_main_984aff);
        int color4 = ContextCompat.getColor(this, R.color.coloractionMode);



        mPtv1.setPriceTextColor(color1);
        mPtv1.setTagColor(color1);
        mPtv1.setPrice(111111);

        mPtv2.setPrice(222222);
        mPtv2.setTagColor(color2);
        mPtv2.setPriceTextColor(color2);

        mPtv3.setTagColor(color2);
        mPtv3.setPriceTextColor(color2);
        mPtv3.setPrice(333333);

        mPtv4.setTagColor(color4);
        mPtv4.setPriceTextColor(color4);
        mPtv4.setPrice(444444);

    }


}
