package com.example.liuj.liujdemo.module.handlerthread;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;
import com.example.liuj.sdk.LogUtils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/14.
 */
public class HandlerThreadAct extends BaseAct {

    HandlerThread mHandlerThread;

    Handler mWorkHandler;
    Handler mMyUIHandler;

    @BindView(R.id.tv_1)
    TextView mTv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_thread_demo_act);
        ButterKnife.bind(this);

        LogUtils.i("Main thread_id = " + Thread.currentThread().getId());
        init();
    }

    @SuppressLint("HandlerLeak")
    private void init() {
        mHandlerThread = new HandlerThread("myHandlerThreadDemo");
        mHandlerThread.start();
        mWorkHandler = new Handler(mHandlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                LogUtils.i("mWorkHandler thread_id = " + Thread.currentThread().getId());
                mMyUIHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return false;
            }
        });

        mMyUIHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtils.i("mMyUIHandler thread_id = " + Thread.currentThread().getId());

                String strMsg = String.format("隔三秒生产一个随机数 value:%s", new Random().nextInt(100));
                mTv1.setText(strMsg);
            }
        };

        for (int i = 0; i < 7; i++) {
            mWorkHandler.sendEmptyMessage(0);
        }
    }

}