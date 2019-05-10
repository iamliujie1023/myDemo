package com.example.liuj.sdk.http.okhttp;

import okhttp3.Response;

public interface IRequset {

    void deliverResponse(Response response, String parse);

    void deliverError(Exception e);

}
