package com.example.liuj.liujdemo.module.taskaffinity.singletask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.sdk.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/6.
 */

public class ActA extends BaseAct {

    @BindView(R.id.singletop_tv1)
    TextView mTvGoToB;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("liujie", "onNewIntent $$" + ActA.class.getName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SingleTask ActA");
        setContentView(R.layout.taskaffinity_singletop_a_act);
        ButterKnife.bind(this);

        mTvGoToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActA.this, ActB.class);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("liujie", "onDestroy $$" + ActA.class.getName());
    }
}
