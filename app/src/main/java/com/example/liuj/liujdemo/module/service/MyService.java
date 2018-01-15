package com.example.liuj.liujdemo.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2018/1/15.
 */
public class MyService extends Service{

    // 第4步，实例化一个MyBinder对象
    private MyBinder mBinder = new MyBinder(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(" onBind ");

        return mBinder;
    }

    public void serviceMethod(String str){
        LogUtils.i( "receive msg from activity: " + str);
    }

    public static class MyBinder extends Binder {
        private static final String TAG = "zjy";
        private MyService mService;
        private OnTestListener mListener;

        public MyBinder(MyService service) {
            this.mService = service;
        }


        public void testMethod(String str) {
            // Activity通过Binder来调用Service的方法将消息传给Service
            mService.serviceMethod(str);
            // 并回调mListener.onTest告诉Activity已收到消息
            mListener.onTest("hi, activity.");
        }

        // MyBinder 里面提供一个注册回调的方法
        public void setOnTestListener(OnTestListener listener) {
            this.mListener = listener;
        }

        //自定义一个回调接口
        public interface OnTestListener {
            void onTest(String str);
        }
    }


}
