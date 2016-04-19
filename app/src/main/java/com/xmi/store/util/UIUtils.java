package com.xmi.store.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.xmi.store.R;
import com.xmi.store.XmiApp;
import com.xmi.store.tripartite.SystemBarTintManager;

import java.util.Random;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 14:20
 * FIXME
 */
public class UIUtils {
    /**
     * 全局Context
     *
     * @return
     */
    public static Context getContext() {
        return XmiApp.getApplicaion();
    }


    public static int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    public static int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 沉浸式activity
     */
    public static void initImmersionStyle(Activity act) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = act.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager mTintManager = new SystemBarTintManager(act);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.status_bar_bg);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isMianThread()) {
            runnable.run();
        } else {
            AppInfo.getUiHandler().post(runnable);
        }

    }

    private static boolean isMianThread() {
        return XmiApp.tid == android.os.Process.myTid();
    }


    public static GradientDrawable getbackground(int color) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(1, Color.TRANSPARENT);
        drawable.setCornerRadius(sp2px(5));
        drawable.setColor(color);
        return drawable;

    }

    public static int getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(200) + 20, random.nextInt(200) + 20, random.nextInt(200) + 20);
    }


    public static StateListDrawable getSelectbackground(GradientDrawable pree,GradientDrawable normal) {

        StateListDrawable stateListDrawable =new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},pree);
        stateListDrawable.addState(new int[]{},normal);
        return stateListDrawable;


    }


}
