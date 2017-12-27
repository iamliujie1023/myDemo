package com.example.liuj.liujdemo.module.jump_other_app_act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseAct;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/12/27.
 */
public class DemoWebView extends BaseAct {

    @BindView(R.id.webview)
    WebView mWebView;

    @BindView(R.id.webview_progressBar)
    ProgressBar mBar;

    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_act);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
//        mWebView.setWebChromeClient(new MyWebChromeClient());
//        mWebView.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        //设置在WebView内部是否允许访问文件，默认允许访问。
        webSettings.setAllowFileAccess(true);
        ///将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        //// 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        // 如果访问的页面中有Javascript，则webview必须设置支持Javascript。
        webSettings.setJavaScriptEnabled(true);
        // 允许js弹出窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置是否使用最后一次缓存
        //优先使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 不使用缓存
//		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //当webview调用requestFocus时为webview设置节点
        webSettings.setNeedInitialFocus(true);

        mWebView.loadUrl("file:///android_asset/mydemo.html");
    }

}
