package com.example.liuj.liujdemo.module.webview.action;

import android.content.Context;
import android.webkit.WebView;

import com.example.liuj.liujdemo.module.webview.action.base.IAction;
import com.example.liuj.liujdemo.module.webview.action.base.IActionCallback;
import com.example.liuj.sdk.ToastHelper;

import org.json.JSONObject;

/**
 * Created by jliu on 2018/3/12.
 */

public class LiujActionToast implements IAction {
    @Override
    public void doAction(JSONObject params, WebView webView, Context context, IActionCallback callback) {
        ToastHelper.toast(context, params.toString());
        callback.callback(null, "result");
    }

}