package com.example.liuj.sdk.http.okhttp;

import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetRequest implements IRequset {

    public enum RequestType {
        GET,
        POST,
        PUT,
        DELETE,
        UPLOAD
    }

    private String mUrl;
    private Map<String, Object> mParams;
    private byte[] mBody;
    private String mTag;
    private int mCacheTime;
    private Map<String, String> mHeader = new HashMap<>();
    boolean mLoadCacheIfNetError;
    private RequestType mType = RequestType.GET;
    private IRequset mCallback;
    public boolean isFinished;

    public NetRequest url(String url) {
        mUrl = url;
        return this;
    }

    public NetRequest body(Map<String, Object> params) {
        mParams = params;
        return this;
    }

    public NetRequest body(byte[] body) {
        mBody = body;
        return this;
    }

    public NetRequest type(RequestType type) {
        mType = type;
        return this;
    }

    public NetRequest tag(String tag) {
        mTag = tag;
        return this;
    }

    public NetRequest cacheTime(int time) {
        mCacheTime = time;
        return this;
    }

    public NetRequest loadCacheIfNetError() {
        mLoadCacheIfNetError = true;
        return this;
    }


    public NetRequest addHeader(String name, String value) {
        mHeader.put(name, value);
        return this;
    }

    public NetRequest addCallback(IRequset callback) {
        mCallback = callback;
        return this;
    }

    public boolean isFinish() {
        return isFinished;
    }

    public void finish() {
        isFinished = true;
        mCallback = null;
    }

    public Request build() {
        if (TextUtils.isEmpty(mUrl)) {
            throw new IllegalArgumentException("url为空");
        }
        Log.d("request", mUrl);
        Request.Builder builder = new Request.Builder();
        builder.url(mUrl);
        if (TextUtils.isEmpty(mTag)) {
            builder.tag(mUrl);
        } else {
            builder.tag(mTag);
        }
        for (Map.Entry<String, String> set : mHeader.entrySet()) {
            builder.addHeader(set.getKey(), set.getValue());
        }
        switch (mType) {
            case POST:
                builder.post(RequestBody.create(null, getParams()));
                break;
            case PUT:
                builder.post(RequestBody.create(null, getParams()));
                break;
            case DELETE:
                builder.delete(RequestBody.create(null, getParams()));
                break;
            case UPLOAD:
                //nothing
                break;
            default:
                builder.get();
        }
        builder.cacheControl(generateCacheControl());
//        tryAddLastModifiedIfNeed(builder); //
        mRequest = builder.build();
        return mRequest;
    }

    public Request mRequest;

    private CacheControl generateCacheControl() {
        if (mCacheTime != 0) {
            CacheControl.Builder builder = new CacheControl.Builder();
            builder.maxStale(mCacheTime, TimeUnit.SECONDS);
            return builder.build();
        } else {
            return CacheControl.FORCE_NETWORK;
        }
    }

    private byte[] getParams() {
        if (mBody == null) {
            return encodeParameters(mParams, "UTF-8");
        }
        return mBody;
    }

    private byte[] encodeParameters(Map<String, Object> params, String paramsEncoding) {
        if (params == null) return new byte[0];

        StringBuilder encodedParams = new StringBuilder();

        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(String.valueOf(entry.getValue()), paramsEncoding));
                encodedParams.append('&');
            }
            int length = encodedParams.length();
            if (length > 0) {
                return encodedParams.substring(0, length - 1).getBytes(paramsEncoding);
            }
            return new byte[0];
        } catch (UnsupportedEncodingException var6) {
            return new byte[0];
        }
    }

    @Override
    public void deliverResponse(Response response, String parse) {
        if (mCallback != null) {
            mCallback.deliverResponse(response, parse);
        }
    }

    @Override
    public void deliverError(Exception e) {
        if (mCallback != null) {
            mCallback.deliverError(e);
        }
    }

    public boolean notModifiedCacheEnable(){ //是否开启not modified 缓存机制
        return false;
    }

//    private void tryAddLastModifiedIfNeed(Request.Builder builder) {
//        if(notModifiedCacheEnable()){
//            Request tempRequest = builder.build(); //tempRequest只用于获取lastModified
//            String lastModified = HttpManager.getLastModifiedTime(tempRequest);
//            if(!TextUtils.isEmpty(lastModified)){
//                builder.addHeader("If-Modified-Since",lastModified);
//            }
//        }
//    }


//    public static String getLastModifiedTime(Request request){
//        try {
//            InternalCache responseCache = Internal.instance.setCache(HttpManager.getOkHttpClient(), )
//            Response cacheCandidate = responseCache != null ? responseCache.get(request) : null;
//            if (cacheCandidate != null) {
//                return cacheCandidate.header("Last-Modified");
//            }
//        } catch (Exception e){
//
//        }
//        return null;
//    }
}
