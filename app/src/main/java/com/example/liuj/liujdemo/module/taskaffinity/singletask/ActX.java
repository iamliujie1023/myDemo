package com.example.liuj.liujdemo.module.taskaffinity.singletask;

import android.content.Intent;
import android.os.Build;
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

public class ActX extends BaseAct {

    @BindView(R.id.singletop_tv1)
    TextView mTvGoToB;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("liujie", "onNewIntent $$" + ActX.class.getName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SingleTask ActX");
        setContentView(R.layout.taskaffinity_singletop_a_act);
        ButterKnife.bind(this);

        mTvGoToB.setText("跳转到ActY");
        mTvGoToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActX.this, ActY.class);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("liujie", "onDestroy $$" + ActX.class.getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        }
    }

}
