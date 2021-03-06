package com.example.liuj.liujdemo.module.taskaffinity.singletask;

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

public class ActB extends BaseActivity {

    @BindView(R.id.singletop_tv1)
    TextView mTvGoToB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SingleTask ActB");
        setContentView(R.layout.taskaffinity_singletop_a_act);
        ButterKnife.bind(this);

        mTvGoToB.setText("跳到ActC");
        mTvGoToB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.goToTargetAct(ActB.this, ActC.class);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("liujie", "onDestroy $$" + ActB.class.getName());
    }

}
