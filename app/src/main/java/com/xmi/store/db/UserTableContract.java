package com.xmi.store.db;

import android.net.Uri;
import android.provider.*;

import com.xmi.store.XmiApp;

import java.net.URI;

/**
 * User: xiucui.yu
 * Date: 2016-04-25
 * Time: 15:28
 * FIXME
 */
public class UserTableContract {

    public final static Uri URLs;

    public final static Uri URLAndId;

    static {
        URLs = Uri.parse("content://" + XmiApp.getApplicaion().getPackageName() + UserTable.TAB_NAME);
        URLAndId = Uri.withAppendedPath(URLs, "change_id");
    }

    public static class UserTable implements android.provider.BaseColumns {
        public static final String TAB_NAME = "tab_name";
        public static final String user_NAME = "user_NAME";
        public static final String user_id = "user_id";
        public static final String nick_name = "nick_name";
        public static final String user_sex = "user_sex";
        public static final String HEAD_URL="head_url";
        public static final String on_line = "on_line";
        public static final String USER_TYPE = "user_type";


    }

}
