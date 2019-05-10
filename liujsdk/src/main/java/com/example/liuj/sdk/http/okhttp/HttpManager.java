package com.example.liuj.sdk.http.okhttp;


import com.example.liuj.sdk.LogUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpManager {

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    static {
        X509TrustManager x509m = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType)  {
            }
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType)  {
            }
        };

        try {
            SSLContext sslCtx;
            try {
                sslCtx = SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException e) {
                try {
                    sslCtx = SSLContext.getInstance("TLSv1.1");
                } catch (NoSuchAlgorithmException e1) {
                    sslCtx = SSLContext.getInstance("TLS");
                }
            }
            sslCtx.init(null, new TrustManager[]{x509m}, new SecureRandom());

            okHttpClient = new OkHttpClient.Builder().sslSocketFactory(sslCtx.getSocketFactory(), x509m).build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
            LogUtils.i("NoSuchAlgorithmException KeyManagementException error = " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.i("Exception error = " + e.toString());
        }
    }

    public static void addRequest(final NetRequest netRequest) {
        Request request = netRequest.build();

        Call call;
        call = HttpManager.getOkHttpClient().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.i(" onFailure " + e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.i(" onResponse ");
            }
        });
    }


}