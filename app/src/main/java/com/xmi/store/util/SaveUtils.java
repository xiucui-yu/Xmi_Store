package com.xmi.store.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * User: xiucui.yu
 * Date: 2016-04-25
 * Time: 18:20
 * FIXME
 */
public class SaveUtils {

    private static final String dirName = "Cache";

    public static SharedPreferences getSharedPreferences(Context mContext) {
        return mContext.getSharedPreferences(dirName, Context.MODE_PRIVATE);
    }

    public static void putBoolean(Context mContext, String key, boolean value) {
        getSharedPreferences(mContext).edit().putBoolean(key, value).commit();
    }

    public static void putInt(Context mContext, String key, int value) {
        getSharedPreferences(mContext).edit().putInt(key, value).commit();
    }

    public static void putString(Context mContext, String key, String value) {
        getSharedPreferences(mContext).edit().putString(key, value).commit();
    }


    public static boolean getBoolean(Context mContext, String key) {
        return getSharedPreferences(mContext).getBoolean(key, false);
    }

    public static int getInt(Context mContext, String key) {
        return getSharedPreferences(mContext).getInt(key, 0);
    }

    public static String getString(Context mContext, String key) {
        return getSharedPreferences(mContext).getString(key, "");
    }

}
