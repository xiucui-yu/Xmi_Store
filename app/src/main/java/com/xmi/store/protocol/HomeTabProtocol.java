package com.xmi.store.protocol;

import com.squareup.okhttp.Callback;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.HttpClient;
import com.xmi.store.net.RequestParams;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.protocol.base.BaseProtocol;


/**
 * User: xiucui.yu
 * Date: 2016-04-11
 * Time: 16:09
 * FIXME
 */
public class HomeTabProtocol extends BaseProtocol<HomeTabBean> {
    public void homeTabUrl(RequestParams requestParams, Callback mCallback) {
        torequest(getUrl(), HttpClient.METHOD_GET, requestParams,mCallback);
    }

    private String getUrl() {
        return RequestUrlUtils.getCurrentUrl() + "home";
    }
}
