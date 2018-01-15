package com.example.liuj.liujdemo.module.service.process_binderpool.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.liuj.liujdemo.module.service.process_binderpool.BinderPoolService;
import com.example.liuj.liujdemo.module.service.process_binderpool.IAnimal;
import com.example.liuj.sdk.LogUtils;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liuj on 2018/1/15.
 */
public class BinderPool {

    private Context mContext;
    @SuppressWarnings("all")
    private static BinderPool sInstance;
    private CountDownLatch mCountDownLatch;
    private IAnimal mAnimalPool;


    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }

    private synchronized void connectBinderPoolService() {
        mCountDownLatch = new CountDownLatch(1);
        Intent intent = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAnimalPool = IAnimal.Stub.asInterface(service);
            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.i("onServiceDisconnected: ");
        }
    };

    public IBinder queryAnimal(int animalCode) {
        IBinder binder = null;
        try {
            if (mAnimalPool != null) {
                binder = mAnimalPool.queryAnimal(animalCode);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

}