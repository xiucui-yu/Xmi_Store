package com.xmi.store.net.sign;


import com.xmi.store.net.RequestParams;

/**
 * 接口验证的算法接口
 * Created by Ljb on 2015/12/11.
 */
public interface SignatureInterface {
    /**
     * 添加验证成功返回true ，否则false
     * */
   boolean signatuer(String url, String method, RequestParams params);
}
