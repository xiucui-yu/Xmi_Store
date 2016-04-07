package com.xmi.store;

import android.app.Application;

import com.xmi.store.util.AppInfo;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 14:21
 * FIXME
 */
public class XmiApp extends Application {

    private static XmiApp applicaion;

    @Override
    public void onCreate() {
        super.onCreate();
        applicaion = this;
        //获取设备信息
        AppInfo.getInstance(applicaion);


    }

    public static XmiApp getApplicaion() {
        return applicaion;
    }

}
