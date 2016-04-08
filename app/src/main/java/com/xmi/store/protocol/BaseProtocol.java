package com.xmi.store.protocol;

import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * User: xiucui.yu
 * Date: 2016-04-08
 * Time: 16:31
 * FIXME
 */
public class BaseProtocol {



    /*public BaseProtocol(String url, int timeOut) {
        this(url, , timeOut);
    }

    public BaseProtocol(String url, RequestParams parms, int timeOut) {
        this(url, , parms, timeOut);
    }*/

   /* public BaseProtocol(String url, HttpRequest.HttpMethod method, RequestParams parms, int timeOut){

    }*/

    /**
     * 网路加载数据
     *//*
    private String loadNet(int index) {
        String responseResult = null;

        OkHttpClient client = new OkHttpClient();

        // Create request for remote resource.
        Request request = new Request.Builder()
                .url("")
                .build();

        // Execute the request and retrieve the response.
        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        return null;
    }
*/

}
