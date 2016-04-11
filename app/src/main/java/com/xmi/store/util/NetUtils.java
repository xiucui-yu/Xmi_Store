package com.xmi.store.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ljb on 2016/1/25.
 */
public class NetUtils {
    /**
     * 判断是否是网络资源
     * @param url
     * @return
     */
    public static boolean isNetUrl(String url){
        if(url!=null&&url.toLowerCase().startsWith("http")||url.toLowerCase().startsWith("rtsp")
                ||url.toLowerCase().startsWith("mms")){
            return true;
        }
        return false;
    }

    /**
     * 网络是否可用
     * <p>
     * This method requires the caller to hold the permission
     * {@link android.Manifest.permission#ACCESS_NETWORK_STATE}.
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }
        return false;
    }

    /**
     * 网络连接类型
     *
     * @param context
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String getAPN(Context context) {
        String apn = "";
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null) {
            if (ConnectivityManager.TYPE_WIFI == info.getType()) {
                apn = info.getTypeName();
                if (apn == null) {
                    apn = "wifi";
                }
            } else {
                apn = info.getExtraInfo().toLowerCase();
                if (apn == null) {
                    apn = "mobile";
                }
            }
        }
        return apn;
    }
}
