package com.xmi.store.net.sign;

import com.xmi.store.net.RequestParams;

/**
 * User: xiucui.yu
 * Date: 2016-04-11
 * Time: 15:13
 * FIXME
 */
public class Signature implements SignatureInterface {
    @Override
    public boolean signatuer(String url, String method, RequestParams params) {
        params.getRequestParams();
        return true;
    }
}
