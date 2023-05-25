package com.myweather.android.util;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;


public interface MultiRequestCallback {
    void onResponse(List<Response> responseList) throws IOException;
    void onFailure(IOException e);
}
