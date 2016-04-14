package com.xmi.store.protocol;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.xmi.store.moudle.AppInfo;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.HttpClient;
import com.xmi.store.net.RequestParams;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.protocol.base.BaseProtocol;


/**
 * Created by Ljb on 2015/11/11.
 */
public class AppDetailProtocol extends BaseProtocol<AppInfo> {


    public void getAppDetail(RequestParams requestParams, Callback mCallback) {
        torequest(getUrl(), HttpClient.METHOD_GET, requestParams, mCallback);

    }

    protected String getUrl() {
        return RequestUrlUtils.getCurrentUrl() + "detail";
    }
}
