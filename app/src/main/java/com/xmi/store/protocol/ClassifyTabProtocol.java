package com.xmi.store.protocol;

import com.xmi.store.moudle.ClassifyInfo;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.HttpClient;
import com.xmi.store.net.RequestParams;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.protocol.base.BaseProtocol;


/**
 * Created by Ljb on 2015/11/12.
 */
public class ClassifyTabProtocol extends BaseProtocol<ClassifyInfo> {

    public ClassifyInfo homeTabUrl(RequestParams requestParams) {
        return torequest(getUrl(), HttpClient.METHOD_GET, requestParams, ClassifyInfo.class);

    }

    private String getUrl() {
        return RequestUrlUtils.getCurrentUrl() + "category";
    }
}
