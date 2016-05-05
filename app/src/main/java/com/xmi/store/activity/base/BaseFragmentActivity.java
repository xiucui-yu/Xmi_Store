package com.xmi.store.activity.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xmi.store.util.AppInfo;
import com.xmi.store.util.UIUtils;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 10:49
 * FIXME
 */
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.initImmersionStyle(this);
        AppInfo.initScreenInfo(this);
    }
}
