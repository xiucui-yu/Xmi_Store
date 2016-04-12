package com.xmi.store.protocol.base;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.xmi.store.net.HttpClient;
import com.xmi.store.net.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * User: xiucui.yu
 * Date: 2016-04-08
 * Time: 16:31
 * FIXME
 */
public class BaseProtocol<T> {
    private Gson mGson;

    public BaseProtocol() {
        mGson = new Gson();
    }

    public void torequest(String url, final String method, final RequestParams params, Callback mCallBack) {
        Request requset = HttpClient.getClient().getRequset(url, method, params, false);
        HttpClient.getClient().enqueue(requset, mCallBack);
        // return setDate(execute2String, clazz);
    }

    public T setDate(String execute2String, Class<T> clazz) {
        if (TextUtils.isEmpty(execute2String)) {
            Log.e("xiaoxiao", "respons null !!!");
        }
        if (clazz != null) {
            return json2pojo(execute2String, clazz, false);
        }
        return null;
    }

    private T json2pojo(String execute2String, Class<T> clazz, boolean b) {
        if (TextUtils.isEmpty(execute2String)) {
            return null;
        }
        //将顶层的先解析出来  re || msg
        if (b) {
            execute2String = getNorData(execute2String);
        }

        return mGson.fromJson(execute2String, clazz);
    }


    //将顶层的先解析出来  re || msg
    private String getNorData(String execute2String) {
        String resultJson = null;
        JSONObject mJsonObject = new JSONObject();
        try {
            int code = mJsonObject.getInt("code");
            if (code == 0) {
                resultJson = mJsonObject.getString("re");
            } else {
                String msg = mJsonObject.getString("msg");
                throw new Throwable("This is a failed request:\r\n" + msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return resultJson;
    }


}
