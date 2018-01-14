package com.example.liuj.liujdemo.module.IntentService;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.liuj.sdk.LogUtils;

/**
 * Created by liuj on 2018/1/14.
 * 比较好的一篇文章： https://www.jianshu.com/p/8a3c44a9173a
 */
public class DemoIntentService extends IntentService {

    public static final String ACTION = "com.liuj.intent_service";
    private static final String EXTRA_IMG_PATH = "intentservice.extra.IMG_PATH";

    public static void startService(Context context, String tag) {
        Intent intent = new Intent(context, DemoIntentService.class);
        intent.putExtra("TAG", tag);
        context.startService(intent);
    }

//    public static void startService(Context context, String path) {
//        Intent intent = new Intent(context, DemoIntentService.class);
//        intent.setAction(ACTION);
//        intent.putExtra(EXTRA_IMG_PATH, path);
//        context.startService(intent);
//    }


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DemoIntentService(String name) {
        super(name);
    }

    public DemoIntentService() {
        super("liuj.demo.intentservice");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.i("intentService onCreate ");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        String tag = intent.getStringExtra("TAG");
        LogUtils.i("intentService onStart tag:" + tag);

        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String tag = intent.getStringExtra("TAG");

        LogUtils.i("intentService onStartCommand tag:" + tag);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("intentService onDestroy ");
    }





    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        if (TextUtils.equals(intent.getAction(), ACTION)) {
            final String path = intent.getStringExtra(EXTRA_IMG_PATH);
            handleUploadImg(path);
        }
    }

    private void handleUploadImg(String path) {
        try {
            //模拟上传耗时
            Thread.sleep(3000);
            Intent intent = new Intent(IntentServiceAct.RESULT);
            intent.putExtra(EXTRA_IMG_PATH, path);

            sendBroadcast(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}