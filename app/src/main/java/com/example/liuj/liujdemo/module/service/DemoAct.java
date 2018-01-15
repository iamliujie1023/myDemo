package com.example.liuj.liujdemo.module.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.sdk.LogUtils;
import com.example.liuj.sdk.ToastHelper;

/**
 * Created by liuj on 2018/1/15.
 */
public class DemoAct extends BaseActivity{

    public MyService.MyBinder mBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            LogUtils.i(" onServiceConnected ");

            //第5步所说的在Activity里面取得Service里的binder对象
            mBinder = (MyService.MyBinder)iBinder;
            //第6步注册自定义回调
            mBinder.setOnTestListener(new MyService.MyBinder.OnTestListener() {
                @Override
                public void onTest(String str) {
                    LogUtils.i("receive msg from service: "+str);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.i(" onServiceDisconnected ");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservice_act);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DemoAct.this, MyService.class);
                bindService(intent, mConnection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (null == mBinder) {
                    ToastHelper.toast(DemoAct.this, "需要先启动Service");
                    return;
                }
                //点击按钮调用mBinder里面的方法，发送消息给Service
                mBinder.testMethod("hi, service.");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

}
