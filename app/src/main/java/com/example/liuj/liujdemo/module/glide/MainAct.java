package com.example.liuj.liujdemo.module.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAct extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.test_1)
    TextView mTv1;
    @BindView(R.id.test_2)
    TextView mTv2;
    @BindView(R.id.test_3)
    TextView mTv3;
    @BindView(R.id.test_4)
    TextView mTv4;

    @BindView(R.id.iv_1)
    ImageView mIv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_main_act);
        ButterKnife.bind(this);

        String url = "http://img3.imgtn.bdimg.com/it/u=4271053251,2424464488&fm=23&gp=0.jpg";
        Glide.with(this).load(url).into(mIv1);

        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);
        mTv4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mTv1) {
            tv1Click();
        }
    }

    private void tv1Click() {
        String url = Images.getRomdanImgUrl();
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher);
        Glide.with(this).load(url).apply(requestOptions).into(mIv1);
    }

}
