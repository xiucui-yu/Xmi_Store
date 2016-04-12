package com.xmi.store.net;

/**
 * User: xiucui.yu
 * Date: 2016-04-11
 * Time: 15:21
 * FIXME
 */
public class RequestUrlUtils {

    public static boolean isDebug = true;

    public static String PREURL = "http://127.0.0.1:8090/";

    public static String FORMALURL = "";

    public static String getCurrentUrl() {
        return isDebug ? PREURL : FORMALURL;
    }

    public static String getImageUrl(String path){
        return getCurrentUrl() + "image?name="+ path;
    }
}
