package com.example.liuj.liujdemo.module.service.process_binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.liuj.liujdemo.module.service.process_binderpool.binder.AnimalBinderFac;

/**
 * Created by liuj on 2018/1/15.
 */
public class BinderPoolService extends Service {

    private AnimalBinderFac mBinder = new AnimalBinderFac();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}