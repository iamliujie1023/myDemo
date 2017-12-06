package com.example.liuj.liujdemo.module.android_base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/22.
 */

public class ActB extends BaseAct{

    private static final String TAG = ActB.class.getSimpleName();

    @BindView(R.id.tv_1)
    TextView tv1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acta_act);
        ButterKnife.bind(this);

        tv1.setText(TAG);
        Log.i("liujie", TAG + " onCreate");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("liujie", TAG + " onStop");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("liujie", TAG + " onPause");

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("liujie", TAG + " onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("liujie", TAG + " onResume");

    }
}
