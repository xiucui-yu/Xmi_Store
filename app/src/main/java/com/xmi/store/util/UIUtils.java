package com.xmi.store.util;

import android.content.Context;

import com.xmi.store.XmiApp;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 14:20
 * FIXME
 */
public class UIUtils {
    /**
     * ȫ�������Ļ���
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

    /**
     * ��spֵת��Ϊpxֵ����֤���ִ�С����
     *
     * @param spValue
     * @param spValue ��DisplayMetrics��������scaledDensity��
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
