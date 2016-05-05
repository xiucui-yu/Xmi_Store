package com.xmi.store.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * User: xiucui.yu
 * Date: 2016-04-07
 * Time: 11:09
 * FIXME
 */
public class AppInfo {
    private static AppInfo instance = null;
    private Application app;                      // 当前app实例
    private Thread uiThread;                     // 当前app启动的ui线程
    private boolean appInBackground = true;    // app处于后台
    private boolean debuging = true;           // 当前是debug模式
    private Handler uiHandler = null;           // 全局ui线程handler

    private Toast globalToast = null;          // 全局的toast实例
    //设备信息
    public static String IMEI = "";             // 设备标示IMEI
    public static String mac = "";              // mac地址
    public static int versionCode;      // 版本号
    //屏幕信息
    public static float density;                // 屏幕密度
    public static int screenWidthForPortrait;   // 屏幕宽度
    public static int screenHeightForPortrait;  // 屏幕高度
    public static int screenStatusBarHeight;    //屏幕通知栏高度

    public AppInfo(Application app) {
        this.app = app;
        this.uiThread = Thread.currentThread();
        /**
         * 获取当前进程的looper对象，类似的 Looper.getMainLooper() 用于获取主线程的Looper对象。
         */
        this.uiHandler = new Handler(Looper.myLooper());

        this.globalToast = Toast.makeText(app, "", Toast.LENGTH_SHORT);
    }

    /**
     * 在Activity onCreate  第一时间进行初始化
     *
     * @param activity
     */
    public static void initScreenInfo(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ////将当前窗口的一些信息放在DisplayMetrics类中
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        density = displayMetrics.density;

        screenWidthForPortrait = displayMetrics.widthPixels;

        screenHeightForPortrait = displayMetrics.heightPixels;


        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            screenStatusBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId);
        }

    }


    public static AppInfo getInstance(Application app) {
        if (null == instance) {
            synchronized (AppInfo.class) {
                if (null == instance) {
                    instance = new AppInfo(app);
                    instance.initDeviceInfo();
                }
            }

        }
        return instance;
    }

    /**
     * 初始化设备信息
     */
    private void initDeviceInfo() {
        TelephonyManager tm = ((TelephonyManager) app.getSystemService(Context.TELEPHONY_SERVICE));
        IMEI = tm.getDeviceId();

        WifiManager wifi = (WifiManager) app.getSystemService(Context.WIFI_SERVICE);

        mac = wifi.getConnectionInfo().getMacAddress();

        if (IMEI == null) {
            IMEI = mac;
        }
        PackageManager packageManager = app.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(app.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Toast getGlobalToast() {
        return instance.globalToast;
    }

    public static Thread getUiThread() {
        return instance.uiThread;
    }


    public static boolean isAppInBackground() {
        return instance.appInBackground;
    }

    public static Handler getUiHandler() {
        return instance.uiHandler;
    }


}
