package com.example.liuj.liujdemo.module.http;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;
import com.example.liuj.sdk.LogUtils;
import com.example.liuj.sdk.http.okhttp.HttpManager;
import com.example.liuj.sdk.http.okhttp.NetRequest;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainAct extends BaseActivity implements View.OnClickListener {

    public static final String url = "http://39.104.113.50/api/productdetail/get?id=10001";

    private static OkHttpClient okHttpClient = new OkHttpClient();

    @BindView(R.id.test_1)
    TextView mTv1;
    @BindView(R.id.test_2)
    TextView mTv2;
    @BindView(R.id.test_3)
    TextView mTv3;
    @BindView(R.id.test_4)
    TextView mTv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_main_act);
        ButterKnife.bind(this);

        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);
        mTv4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mTv1) {
            tv1Click();
        }
    }

    private void tv1Click() {
        NetRequest netRequest = new NetRequest();
        netRequest.url(url);
        HttpManager.addRequest(netRequest);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Request request = new Request.Builder().url(url).build();
//                Call call = okHttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        LogUtils.i("onFailure error=" + e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        if (response.isSuccessful()) { //请求成功
//                            LogUtils.i("success result = " + response.toString());
//                        } else if (response.isRedirect()) {
//                            LogUtils.i("当前请求被劫持");
//                        } else {
//                            LogUtils.i("error");
//                        }
//                    }
//                });
//
//                try {
//                    Response response = okHttpClient.newCall(request).execute();
//                    LogUtils.i("success2 result = " + response.body().toString());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    private void tv2Click() {

    }


}
