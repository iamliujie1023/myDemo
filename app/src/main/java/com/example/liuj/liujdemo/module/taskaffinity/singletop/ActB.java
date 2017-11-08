package com.example.liuj.liujdemo.module.taskaffinity.singletop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.liujdemo.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.liujdemo.tools.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/6.
 */
public class ActB extends BaseAct {

    @BindView(R.id.singletop_b_tv1)
    TextView mTvGoToA;

    @BindView(R.id.singletop_b_tv2)
    TextView mTvGoToB;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("liujie", "onNewIntent $$" + ActB.class.getName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SingleTop ActB");
        setContentView(R.layout.taskaffinity_singletop_b_act);
        ButterKnife.bind(this);

        mTvGoToA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActB.this, ActA.class);
            }
        });

        mTvGoToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActB.this, ActB.class);
            }
        });

        Log.i("liujie", "onCreate $$" + ActB.class.getName());
    }


}