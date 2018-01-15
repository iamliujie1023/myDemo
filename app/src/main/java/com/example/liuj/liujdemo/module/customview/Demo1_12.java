package com.example.liuj.liujdemo.module.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.module.customview.view.View1;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/29.
 */
public class Demo1_12 extends BaseAct {

    @BindView(R.id.view1)
    View1 mView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_demo1_12_act);
        ButterKnife.bind(this);

        new Thread(mView1).start();

        Thread thread = new Thread();
    }

}