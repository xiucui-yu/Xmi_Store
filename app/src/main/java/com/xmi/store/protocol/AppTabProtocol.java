package com.xmi.store.protocol;

import com.squareup.okhttp.Callback;
import com.xmi.store.moudle.AppTabBean;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.HttpClient;
import com.xmi.store.net.RequestParams;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.protocol.base.BaseProtocol;


/**
 * Created by Ljb on 2015/11/9.
 */
public class AppTabProtocol extends BaseProtocol<AppTabBean> {

    public void getAppTab(RequestParams requestParams, Callback mCallback) {

        torequest(getUrl(), HttpClient.METHOD_GET, requestParams,mCallback);
    }

    private String getUrl() {
        return RequestUrlUtils.getCurrentUrl() + "app";
    }
}
