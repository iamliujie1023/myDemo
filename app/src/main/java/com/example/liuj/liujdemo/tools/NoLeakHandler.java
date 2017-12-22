package com.example.liuj.liujdemo.tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

public class NoLeakHandler extends Handler {
    private WeakReference<MsgHandler> mHandler;

    public NoLeakHandler(MsgHandler msgHandler) {
        super(Looper.getMainLooper());
        mHandler = new WeakReference<>(msgHandler);
    }

    @Override
    public void handleMessage(Message msg) {
        MsgHandler realHandler = mHandler.get();
        if (realHandler != null) {
            realHandler.handleMessage(msg);
        }
    }

    public interface MsgHandler {
        void handleMessage(Message msg);
    }
}
