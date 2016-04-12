package com.xmi.store.net;

import java.util.TreeMap;

/**
 * User: xiucui.yu
 * Date: 2016-04-11
 * Time: 13:56
 * FIXME
 */
public class RequestParams {
    public TreeMap<String,Object> requestParams;

    public RequestParams(){
        requestParams = new TreeMap<String,Object>();
    }
    public void putParams(String key, Object value){
        if(requestParams!=null){
            requestParams.put(key,value);
        }
    }

    public void removeParams(String key){
        if(requestParams!=null){
            requestParams.remove(key);
        }
    }

    public int size(){
        if(requestParams!=null){
          return requestParams.size();
        }
        return 0;
    }

    public TreeMap<String, Object> getRequestParams() {
        return requestParams;
    }

    //添加公用的参数

    public void addSign(){


    }
}
