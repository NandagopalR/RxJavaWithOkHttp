package com.nanda.rxandroidexample.network;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class NetworkCalls {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    public String getData(String url) {
        OkHttpClient client = new OkHttpClient();
        Response response = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            response = client.newCall(request).execute();

            response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isSuccessful())
            return response.body().toString();
        else
            return "No Data Found";
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
