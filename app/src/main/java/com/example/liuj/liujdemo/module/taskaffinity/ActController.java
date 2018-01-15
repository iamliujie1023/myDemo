package com.example.liuj.liujdemo.module.taskaffinity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.sdk.IntentUtil;
import com.example.liuj.sdk.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/6.
 */

public class ActController extends BaseActivity {

    @BindView(R.id.taskaffinity_tv1)
    TextView mTv1;

    @BindView(R.id.taskaffinity_tv2)
    TextView mTv2;

    @BindView(R.id.taskaffinity_tv3)
    TextView mTv3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ActController");
        setContentView(R.layout.taskaffinity_controller_act);
        ButterKnife.bind(this);

        mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActController.this, com.example.liuj.liujdemo.module.taskaffinity.singletop.ActA.class);
            }
        });

        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActController.this, com.example.liuj.liujdemo.module.taskaffinity.singletask.ActA.class);
            }
        });

        mTv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    ComponentName componentName = new ComponentName("com.example.liuj.router", "com.example.liuj.router.ActX");
                    intent.setComponent(componentName);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastHelper.toastLong(ActController.this, e.toString());
                }
            }
        });

    }

}