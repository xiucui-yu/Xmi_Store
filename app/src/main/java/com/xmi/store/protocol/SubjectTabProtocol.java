package com.xmi.store.protocol;


import com.squareup.okhttp.Callback;
import com.xmi.store.moudle.HotTabBean;
import com.xmi.store.moudle.SubjectTabBean;
import com.xmi.store.net.HttpClient;
import com.xmi.store.net.RequestParams;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.protocol.base.BaseProtocol;


/**
 * Created by Ljb on 2015/11/6.
 */
public class SubjectTabProtocol extends BaseProtocol<SubjectTabBean> {


    public void getSubject(RequestParams requestParams, Callback mCallback) {
        torequest(getUrl(), HttpClient.METHOD_GET, requestParams, mCallback);
    }

    private String getUrl() {
        return RequestUrlUtils.getCurrentUrl() + "subject";
    }
}
