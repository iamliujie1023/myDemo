package com.example.liuj.liujdemo.module.webview.action.base;

import android.content.Context;
import android.webkit.WebView;

import org.json.JSONObject;

/**
 * Created by jliu on 2018/3/12.
 */

public interface IAction {
    public void doAction(JSONObject params, WebView webView, Context context, IActionCallback callback);
}
