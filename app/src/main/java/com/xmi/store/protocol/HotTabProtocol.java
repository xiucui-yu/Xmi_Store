package com.xmi.store.protocol;

import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.moudle.HotTabBean;
import com.xmi.store.net.HttpClient;
import com.xmi.store.net.RequestParams;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.protocol.base.BaseProtocol;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Ljb on 2015/11/12.
 */
public class HotTabProtocol  extends BaseProtocol<HotTabBean> {
    public HotTabBean homeTabUrl(RequestParams requestParams) {
        return torequest(getUrl(), HttpClient.METHOD_GET, requestParams, HotTabBean.class);

    }

    private String getUrl() {
        return RequestUrlUtils.getCurrentUrl() + "hot";
    }
}
