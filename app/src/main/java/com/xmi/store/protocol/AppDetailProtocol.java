package com.xmi.store.protocol;

import com.google.gson.Gson;
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


    public AppInfo homeTabUrl(RequestParams requestParams) {
        return torequest(getUrl(), HttpClient.METHOD_GET, requestParams, AppInfo.class);

    }

    protected String getUrl() {
        return RequestUrlUtils.getCurrentUrl()+"detail";
    }
}
