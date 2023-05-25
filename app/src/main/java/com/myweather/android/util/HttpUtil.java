package com.myweather.android.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendMultiOkHttpRequest(List<String> addressList,
                                              MultiRequestCallback callback) {
        List<Response> responseList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(addressList.size());

        OkHttpClient client = new OkHttpClient();
        for(String address: addressList) {
            Request request = new Request.Builder().url(address).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    responseList.add(response);
                    if (count.decrementAndGet() == 0) {
                        callback.onResponse(responseList);
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    if (count.decrementAndGet() == 0) {
                        callback.onFailure(e);
                    }
                }
            });
        }
    }
}
