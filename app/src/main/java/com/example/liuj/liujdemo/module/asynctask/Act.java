package com.example.liuj.liujdemo.module.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.liujdemo.tools.TimeUtils;
import com.example.liuj.sdk.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2018/1/13.
 */

public class Act extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tv_1)
    TextView mTv1;

    @BindView(R.id.tv_2)
    TextView mTv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_act);
        ButterKnife.bind(this);

        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mTv1) {
            tv1Click();
        } else if (v == mTv2) {
            tv2Click();
        }
    }

    private void tv1Click() {
        new MyTask1("  task1").execute("");
        new MyTask1("  task2").execute("");
        new MyTask1("  task3").execute("");
        new MyTask1("  task4").execute("");
    }

    private void tv2Click() {
        new MyTask1(" THREAD_POOL_EXECUTOR task1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyTask1(" THREAD_POOL_EXECUTOR task2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyTask1(" THREAD_POOL_EXECUTOR task3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyTask1(" THREAD_POOL_EXECUTOR task4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }


    private static class MyTask1 extends AsyncTask<String, Integer, String> {

        private String mTaskName;
        public MyTask1(String task) {
            this.mTaskName = task;
        }

        @Override
        protected String doInBackground(String... integers) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            LogUtils.i(String.format(" ** %s, time:%s", mTaskName, TimeUtils.getYYYYMMDDHHMMSS(System.currentTimeMillis())));
        }
    }


}