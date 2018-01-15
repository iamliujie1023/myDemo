package com.example.liuj.liujdemo.module.service.process_binderpool.binder;

import android.os.RemoteException;

import com.example.liuj.liujdemo.module.service.process_binderpool.IBird;
import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2018/1/15.
 */
public class BirdBinder extends IBird.Stub {
    @Override
    public void fly() throws RemoteException {
        LogUtils.i("I'm bird, I can fly.");
    }
}
