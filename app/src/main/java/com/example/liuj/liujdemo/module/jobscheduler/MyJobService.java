package com.example.liuj.liujdemo.module.jobscheduler;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.liuj.sdk.LogUtils;
import com.example.liuj.sdk.ToastHelper;

import static com.example.liuj.liujdemo.module.jobscheduler.MainAct.MESSENGER_INTENT_KEY;
import static com.example.liuj.liujdemo.module.jobscheduler.MainAct.MSG_COLOR_START;
import static com.example.liuj.liujdemo.module.jobscheduler.MainAct.MSG_COLOR_STOP;
import static com.example.liuj.liujdemo.module.jobscheduler.MainAct.WORK_DURATION_KEY;

/**
 * Created by liuj on 2017/12/20.
 * JobService运行在你的主线程
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private Messenger mActivityMessenger;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i("Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("Service destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mActivityMessenger = intent.getParcelableExtra(MESSENGER_INTENT_KEY);
        return START_STICKY;
    }

    /**
     * @param params
     * @return false 系统假定你已经执行完毕。
     * true系统认为这个任务正要被执行，执行任务的重担就落在了你的肩上。
     * 当任务执行完毕时你需要调用jobFinished(JobParameters params, boolean needsRescheduled)来通知系统
     */
    @Override
    public boolean onStartJob(final JobParameters params) {
        // The work that this service "does" is simply wait for a certain duration and finish
        // the job (on another thread).
        sendMessage(MSG_COLOR_START, params.getJobId());

        long duration = params.getExtras().getLong(WORK_DURATION_KEY);

        // Uses a handler to delay the execution of jobFinished().
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendMessage(MSG_COLOR_STOP, params.getJobId());
                //第二个参数表示 执行过程中被取消，是否要重新加入到schedule列表中，false表示忽略
                jobFinished(params, false);
            }
        }, duration);
        LogUtils.i("Service onStartJob: " + params.getJobId());

        ToastHelper.toastLong(this, "onStartJob _ " + params.getJobId());

        // Return true as there's more work to be done with this job.
        return true;
    }

    /**
     * return false 不会被调用
     * return true 只有在onstartJob执行过程中，被 cancel 被取消才会去调用这个方法
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        // Stop tracking these job parameters, as we've 'finished' executing.
        sendMessage(MSG_COLOR_STOP, params.getJobId());
        LogUtils.i("xxxx Service onStopJob: " + params.getJobId());

        // Return false to drop the job.
        ToastHelper.toastLong(this, "onStopJob _ " + params.getJobId());
        return true;
    }

    private void sendMessage(int messageID, @Nullable Object params) {
        // If this service is launched by the JobScheduler, there's no callback Messenger. It
        // only exists when the MainActivity calls startService() with the callback in the Intent.
        if (mActivityMessenger == null) {
            LogUtils.i("Service is bound, not started. There's no callback to send a message to.");
            return;
        }
        Message m = Message.obtain();
        m.what = messageID;
        m.obj = params;
        try {
            mActivityMessenger.send(m);
        } catch (RemoteException e) {
            LogUtils.i("Error passing service object back to activity.");
        }
    }

}