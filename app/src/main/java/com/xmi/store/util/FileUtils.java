package com.xmi.store.util;

import android.os.Environment;

import com.xmi.store.XmiApp;

import java.io.File;

/**
 * User: xiucui.yu
 * Date: 2016-05-03
 * Time: 16:29
 * FIXME
 */
public class FileUtils {
    private static String picDir;


    private static final String ROOT_DIR = "XmiCache";

    public static String getPicDir() {
        return getDir("cache" + File.separator + "pic_xgo");
    }

    public static String getPicClipDir() {
        return getDir("clip");
    }

    private static String getDir(String s) {
        if (isSDAvailable()) {
            return getSDDir(s);

        } else {
            return getDateDir(s);
        }
    }


    private static String getDateDir(String s) {
        String s1 = XmiApp.getApplicaion().getCacheDir().getAbsolutePath() + File.separator + s;
        File file = new File(s1);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            } else {
                return "";
            }
        }
        return file.getAbsolutePath();
    }

    private static String getSDDir(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();//»ñÈ¡¸úÄ¿Â¼
        stringBuffer.append(absolutePath);
        stringBuffer.append(File.separator).append(ROOT_DIR).append(File.separator).append(s);
        File file = new File(stringBuffer.toString());
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getAbsolutePath();
            } else {
                return "";
            }
        }
        return file.getAbsolutePath();
    }

    private static boolean isSDAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) ? true : false;
    }


}
