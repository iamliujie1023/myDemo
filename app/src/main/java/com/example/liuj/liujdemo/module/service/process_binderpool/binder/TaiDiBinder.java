package com.example.liuj.liujdemo.module.service.process_binderpool.binder;

import android.os.RemoteException;

import com.example.liuj.liujdemo.module.service.process_binderpool.ITaiDi;
import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2018/1/15.
 */

public class TaiDiBinder extends ITaiDi.Stub {
    @Override
    public void fuckAir() throws RemoteException {
        LogUtils.i("i'm taidi fuck sky fuck ground fuck air");
    }
}
