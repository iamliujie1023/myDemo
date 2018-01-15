package com.example.liuj.liujdemo.module.service.process_binderpool.binder;

import android.os.RemoteException;

import com.example.liuj.liujdemo.module.service.process_binderpool.IFish;
import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2018/1/15.
 */

public class FishBinder extends IFish.Stub {
    @Override
    public void swim() throws RemoteException {
        LogUtils.i("I'm fish, I can swim. ");
    }
}
