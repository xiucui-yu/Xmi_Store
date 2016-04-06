package com.xmi.store;

import android.app.Application;

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
        applicaion=this;
    }
    public static XmiApp getApplicaion() {
        return applicaion;
    }

}
