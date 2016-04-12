package com.xmi.store.net;

import android.telecom.Call;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.xmi.store.net.sign.Signature;
import com.xmi.store.net.sign.SignatureInterface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * User: xiucui.yu
 * Date: 2016-04-11
 * Time: 13:49
 * FIXME
 */
public class HttpClient {

    public static final String TAG = "HttpClient";

    public static String METHOD_GET = "GET";
    public static String METHOD_POST = "POST";
    public static String METHOD_PUT = "PUT";
    public static String METHOD_DELETE = "DELETE";


    public static HttpClient mClient;

    public static SignatureInterface signature;


    public static OkHttpClient mOkHttpClien = new OkHttpClient();

    static {
        mOkHttpClien.setConnectTimeout(5000, TimeUnit.MILLISECONDS);

        signature = new Signature();
    }

    public static HttpClient getClient() {
        if (mClient == null) {

            synchronized (HttpClient.class) {

                if (mClient == null) {

                    mClient = new HttpClient();
                }
            }
        }
        return mClient;
    }

    public String execute2String(Request request) {
        Response response = null;
        // Execute the request and retrieve the response.
        try {
            response = mOkHttpClien.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                return response.body().toString();
            } else {
                throw new IOException("Unexpected code " + response.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void enqueue(Request request, Callback mCall) {

        // Execute the request and retrieve the response.

        mOkHttpClien.newCall(request).enqueue(mCall);

    }


    public Request getRequset(String url, String method, RequestParams requestParams, boolean hasSign) {
        if (requestParams == null) {
            requestParams = new RequestParams();
        }
        Request.Builder build = new Request.Builder();
        if (hasSign) {
            signature.signatuer(url, method, requestParams);
        }
        //get 请求
        if (method.equalsIgnoreCase(METHOD_GET)) {
            build.url(initGetParams(url, requestParams)).get();
        }
        //post 请求
        else if (method.equalsIgnoreCase(METHOD_POST)) {
            build.url(url).post(initPostBody(requestParams));
        }
        //put
        else if (method.equalsIgnoreCase(METHOD_PUT)) {
            build.url(url).put(initPostBody(requestParams));
        }
        //delete
        else if (method.equalsIgnoreCase(METHOD_DELETE)) {
            if (requestParams.size() == 0) {
                build.url(url).delete();
            } else {
                build.url(url).delete(initPostBody(requestParams));
            }
        }
        return build.build();
    }

    private String initGetParams(String url, RequestParams requestParams) {
        StringBuffer sb = new StringBuffer(url);
        if (requestParams.size() > 0) {
            sb.append("?");
            Set<Map.Entry<String, Object>> objectMap = requestParams.getRequestParams().entrySet();
            int count = 0;
            for (Map.Entry<String, Object> itemMap : objectMap) {
                ++count;
                sb.append(itemMap.getKey()).append("=").append(itemMap.getValue());
                if (count == requestParams.size()) {
                    break;
                }

                sb.append("&");
            }
        } else {
            Log.e(TAG, "request params null!!!");
        }


        return sb.toString();
    }

    private RequestBody initPostBody(RequestParams requestParams) {
        MultipartBuilder formBody = new MultipartBuilder().type(MultipartBuilder.FORM);
        if (requestParams.size() > 0) {
            Set<Map.Entry<String, Object>> objectMap = requestParams.getRequestParams().entrySet();
            for (Map.Entry<String, Object> entry : objectMap) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof File) {
                    File file = (File) value;
                    //获取文件类型  /*MediaType.parse("image/png")*/
                    FileNameMap fileNameMap = HttpURLConnection.getFileNameMap();
                    String contentTypeFor = fileNameMap.getContentTypeFor(file.getAbsolutePath());
                    formBody.addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse(contentTypeFor), file));

                } else {
                    formBody.addFormDataPart(key, value.toString());
                }

            }
        } else {
            Log.e(TAG, "request params null!!!");
        }
        return formBody.build();
    }


}
