package com.xmi.store.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: xiucui.yu
 * Date: 2016-04-25
 * Time: 14:02
 * FIXME
 */
public class XmiDbHelper extends SQLiteOpenHelper {
    private final static int mVersion = 1;
    private final static String tabName = "xmi_table.db";

    public XmiDbHelper(Context context) {
        super(context, tabName, null, mVersion);

    }

    //1 男 2 女  0未知
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + UserTableContract.UserTable.TAB_NAME + "(" +
                        UserTableContract.UserTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        UserTableContract.UserTable.user_NAME + " TEXT," +
                        UserTableContract.UserTable.user_id + " TEXT," +
                        UserTableContract.UserTable.user_sex + " INTEGER," +
                        UserTableContract.UserTable.on_line + " INTEGER," +
                        UserTableContract.UserTable.nick_name + " TEXT," +
                        UserTableContract.UserTable.HEAD_URL + " TEXT," +
                        UserTableContract.UserTable.USER_TYPE + " TEXT" + ");"
        );
    }

    //删除用户表
    private static final String deleteTab = "drop table if exists " + UserTableContract.UserTable.TAB_NAME;

    //版本变更才会调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(deleteTab);
        onCreate(db);
    }
}

