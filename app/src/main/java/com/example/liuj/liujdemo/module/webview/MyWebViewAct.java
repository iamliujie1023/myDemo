package com.example.liuj.liujdemo.module.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuj on 2017/11/16.
 */

public class MyWebViewAct extends BaseActivity {

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
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());

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

        mWebView.loadUrl(mUrl);
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    view.getContext());

            builder.setMessage(message).setPositiveButton("确定", null);

//                // 禁止响应按back键的事件
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            result.confirm();
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mBar.setProgress(newProgress);
//                if (newProgress == 100) {
//                    mBar.setVisibility(View.INVISIBLE);
//                } else {
//                    if (View.INVISIBLE == mBar.getVisibility()) {
//                        mBar.setVisibility(View.VISIBLE);
//                    }
//                }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
        }
    }

    private class MyWebViewClient extends WebViewClient {
        /**
         * 这个事件，将在用户点击链接时触发。 通过判断url，可确定如何操作，如果返回true，表示我们已经处理了这个request，
         * 如果返回false，表示没有处理，那么浏览器将会根据url获取网页
         * 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }

        // 网页加载出错
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }

}
