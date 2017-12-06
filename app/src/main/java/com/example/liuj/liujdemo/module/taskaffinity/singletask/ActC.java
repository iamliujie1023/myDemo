package com.example.liuj.liujdemo.module.taskaffinity.singletask;

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
public class ActC extends BaseAct {

    @BindView(R.id.singletop_b_tv1)
    TextView mTvGoToA;

    @BindView(R.id.singletop_b_tv2)
    TextView mTvGoToB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SingleTask ActC");
        setContentView(R.layout.taskaffinity_singletop_b_act);
        ButterKnife.bind(this);

        mTvGoToA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActC.this, ActA.class);
            }
        });

        mTvGoToB.setText("跳转到 ActX");
        mTvGoToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActC.this, ActX.class);
            }
        });

        Log.i("liujie", "onCreate $$" + ActC.class.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("liujie", "onDestroy $$" + ActC.class.getName());
    }

}