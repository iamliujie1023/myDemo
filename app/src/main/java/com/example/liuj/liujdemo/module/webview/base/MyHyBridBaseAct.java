package com.example.liuj.liujdemo.module.webview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jliu on 2018/3/12.
 */
public class MyHyBridBaseAct extends BaseActivity{

    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_act);
        ButterKnife.bind(this);

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JsInterface(), "control");

        mWebView.loadUrl("file:///android_asset/annotation_demo.html");
    }

    public static class JsInterface{
        @JavascriptInterface
        public void invoke(String msg) {

        }
    }

}
