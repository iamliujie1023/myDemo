package com.example.liuj.liujdemo.module.webview.action.base;

import android.content.Context;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.liuj.liujdemo.home.MyApplication;
import com.example.liuj.sdk.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jliu on 2018/3/12.
 */
public class HBWbHybridAction {
    private WeakReference<WebView> webViewRef;
    private WeakReference<Context> contextRef;
    private static Set<String> packagePrefix = new HashSet<>();

    public HBWbHybridAction(WebView webView) {
        this.webViewRef = new WeakReference<>(webView);
        this.contextRef = new WeakReference<>(webView.getContext());

        // 添加配置
        addHybridPackage(MyApplication.getInstance().getPackageName()  + ".liujdemo.module.webview.action");
    }

    public static void addHybridPackage(String packageName) {
        packagePrefix.add(packageName + ".LiujAction");
    }

    public static Class getClassForTarget(String target) {
        for (String name : packagePrefix) {
            try {
                return Class.forName(name + target.substring(0, 1).toUpperCase() + target.substring(1));
            } catch (ClassNotFoundException e) {
            }
        }
        return null;
    }

    @JavascriptInterface
    public void invoke(String message) {
        try {
            WebView webView = webViewRef.get();
            if (webView == null) {
                LogUtils.i( "webview is recycled....");
                return;
            }

            LogUtils.i( message);
            JSONObject msgJSON = new JSONObject(message);
            final int messageId = msgJSON.getInt("id");
            final String target = msgJSON.getString("target");
            final JSONObject data = msgJSON.optJSONObject("data") != null ? msgJSON.optJSONObject("data") : new JSONObject();
            LogUtils.i("target: " + target + ", data: " + data.toString());
            Class actionClass = getClassForTarget(target);
            final IAction action = (IAction) actionClass.newInstance();

            webView.post(new Runnable() {
                @Override
                public void run() {
                    final WebView webView = webViewRef.get();
                    Context context = contextRef.get();
                    if (webView == null || context == null) {
                        return;
                    }
                    action.doAction(data, webView, context, new IActionCallback() {
                        @Override
                        public void callback(IActionError error, final Object result) {
                            String callbackUrl = "javascript:callback";
                            if (error != null) {
                                final JSONObject errorJSON = new JSONObject();
                                try {
                                    errorJSON.put("code", error.code);
                                    errorJSON.put("message", error.message);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                callbackUrl += String.format("(%s, %s)", messageId, errorJSON);
                            } else {
                                if (result instanceof String) {
                                    callbackUrl += String.format("(%s, '%s')", messageId, result);
                                } else {
                                    callbackUrl += String.format("(%s, %s)", messageId, result);
                                }
                            }
                            final String finalCallbackUrl = callbackUrl;
                            if (webView != null) {
                                webView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                                webView.evaluateJavascript(finalCallbackUrl, null);
                                            } else {
                                                webView.loadUrl(finalCallbackUrl);
                                            }
                                        } catch (Throwable e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static class HybridException extends Exception {
        public HybridException(String msg) {
            super(msg);
        }
    }
}
