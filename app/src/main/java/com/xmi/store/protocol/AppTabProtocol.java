package com.xmi.store.protocol;

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

    public AppTabBean homeTabUrl(RequestParams requestParams) {
        return torequest(getUrl(), HttpClient.METHOD_GET, requestParams, AppTabBean.class);

    }

    private String getUrl() {
        return RequestUrlUtils.getCurrentUrl() + "app";
    }
}
