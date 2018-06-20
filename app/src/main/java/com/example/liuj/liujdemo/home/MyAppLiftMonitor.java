package com.example.liuj.liujdemo.home;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;

import com.example.liuj.sdk.LogUtils;

import java.lang.ref.WeakReference;

public class MyAppLiftMonitor implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "MyAppLiftMonitor";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mPaused = true;
    private Runnable mCheckForegroundRunnable;
    private boolean mForeground = false;
    private static MyAppLiftMonitor sInstance;
    //当前Activity的弱引用
    private WeakReference<Activity> mActivityReference;

    public static final int ACTIVITY_ON_RESUME = 0;
    public static final int ACTIVITY_ON_PAUSE = 1;
    /**
     * act 改变广播
     */
    public static final String ACTION_CHANGE = "com.example.liuj.act_change";
    /**
     * act 完整的包路径名称
     */
    public static final String EXTRA_ACT_NAME = "act_name";
    /**
     * act 状态
     */
    public static final String EXTRA_ACT_LIFE = "act_name";


    private MyAppLiftMonitor() {
    }

    public static synchronized MyAppLiftMonitor getInstance() {
        if (sInstance == null) {
            sInstance = new MyAppLiftMonitor();
        }
        return sInstance;
    }

    public Activity getCurrentActivity() {
        if (mActivityReference != null) {
            return mActivityReference.get();
        }
        return null;
    }

    public boolean isForground() {
        return mForeground;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtils.d(TAG, activity.getLocalClassName() + " " + "onActivityCreated");
        mActivityReference = new WeakReference<Activity>(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.d(TAG, activity.getLocalClassName() + " " + "onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtils.d(TAG, activity.getLocalClassName() + " " + "onActivityResumed");
        String activityName = activity.getClass().getName();
        notifyActivityChanged(activityName, ACTIVITY_ON_RESUME);
        mPaused = false;
        if (!mForeground) {
            LogUtils.d(TAG, "App to Foregroud");
        }
        mForeground = true;
        if (mCheckForegroundRunnable != null) {
            mHandler.removeCallbacks(mCheckForegroundRunnable);
        }
        mActivityReference = new WeakReference<Activity>(activity);
    }

    @Override
    public void onActivityPaused(final Activity activity) {//pause事件后是否在前台要分情况判断
        LogUtils.d(TAG, activity.getLocalClassName() + " " + "onActivityPaused");
        notifyActivityChanged(activity.getClass().getName(), ACTIVITY_ON_PAUSE);
        mPaused = true;
        if (mCheckForegroundRunnable != null) {
            mHandler.removeCallbacks(mCheckForegroundRunnable);
        }
        mHandler.postDelayed(mCheckForegroundRunnable = new Runnable() {
            @Override
            public void run() {
                if (mPaused && mForeground) {
                    LogUtils.d(TAG, "App to Backgroud");
                    mForeground = false;
                }
            }
        }, 1000);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.d(TAG, activity.getLocalClassName() + " " + "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.d(TAG, activity.getLocalClassName() + " " + "onActivityDestroyed");
    }

    private void notifyActivityChanged(String activityName, int lifeState) {
        Intent intent = new Intent(ACTION_CHANGE);
        intent.putExtra(EXTRA_ACT_NAME, activityName);
        intent.putExtra(EXTRA_ACT_LIFE, lifeState);
        LocalBroadcastManager.getInstance(MyApplication.getInstance()).sendBroadcast(intent);
    }

}
