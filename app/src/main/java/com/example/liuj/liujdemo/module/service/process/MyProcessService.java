package com.example.liuj.liujdemo.module.service.process;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.liuj.liujdemo.module.service.ILiujAidlInterface;
import com.example.liuj.liujdemo.module.service.ILiujCallback;
import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2018/1/15.
 */
public class MyProcessService extends Service {

    private AidlBinder mBinder = new AidlBinder(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void serviceMethod(String str) {
        LogUtils.i("receive msg from activity: " + str);

        mBinder.responseAct("hi Activity");
    }

    public class AidlBinder extends ILiujAidlInterface.Stub {

        private MyProcessService mService;
        private RemoteCallbackList<ILiujCallback> mRemoteCallbackList = new RemoteCallbackList<>();

        public AidlBinder(MyProcessService service) {
            this.mService = service;
        }

        public void responseAct(String msg) {
            try {
                // 调用mListenerList里面所有已注册的监听
                int count = mRemoteCallbackList.beginBroadcast();
                for (int i = 0; i < count; i++) {
                    mRemoteCallbackList.getBroadcastItem(i).onRespond(msg);
                }
                mRemoteCallbackList.finishBroadcast();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void testMethod(String str) throws RemoteException {
            mService.serviceMethod(str);
        }

        @Override
        public void registerCallback(ILiujCallback callback) throws RemoteException {
            mRemoteCallbackList.register(callback);
        }

        @Override
        public void unRegisterCallback(ILiujCallback iLiujCallback) throws RemoteException{
            mRemoteCallbackList.unregister(iLiujCallback);
        }

    }


}