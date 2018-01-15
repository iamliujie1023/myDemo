package com.example.liuj.liujdemo.module.taskaffinity.singletask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.sdk.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/6.
 */
public class ActZ extends BaseActivity {

    @BindView(R.id.singletop_b_tv1)
    TextView mTv1;

    @BindView(R.id.singletop_b_tv2)
    TextView mTv2;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("liujie", "onNewIntent $$" + ActZ.class.getName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SingleTask ActZ");
        setContentView(R.layout.taskaffinity_singletop_b_act);
        ButterKnife.bind(this);

        mTv1.setText("跳转到ActX");
        mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActZ.this, ActX.class);
            }
        });

        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActZ.this, ActZ.class);
            }
        });

        Log.i("liujie", "onCreate $$" + ActZ.class.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("liujie", "onDestroy $$" + ActZ.class.getName());
    }

}