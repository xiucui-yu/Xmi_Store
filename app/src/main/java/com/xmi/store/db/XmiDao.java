package com.xmi.store.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * User: xiucui.yu
 * Date: 2016-04-25
 * Time: 16:22
 * FIXME
 */
public class XmiDao {

    private Context context;
    private XmiDbHelper xmiDbHelper;

    public XmiDao(Context mContext) {
        context = mContext;
        this.xmiDbHelper = new XmiDbHelper(context);
    }

    public void insert(User user) {
        SQLiteDatabase writableDatabase = xmiDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTableContract.UserTable.user_NAME, user.getUserName());
        contentValues.put(UserTableContract.UserTable.user_id, user.getUserId());
        contentValues.put(UserTableContract.UserTable.user_sex, user.getGender());
        contentValues.put(UserTableContract.UserTable.on_line, user.getOnLine());
        contentValues.put(UserTableContract.UserTable.HEAD_URL, user.getHeadUrl());
        contentValues.put(UserTableContract.UserTable.USER_TYPE, user.getUserType());
        contentValues.put(UserTableContract.UserTable.nick_name, user.getNickname());
        writableDatabase.insert(UserTableContract.UserTable.TAB_NAME, null, contentValues);
        writableDatabase.close();

        context.getContentResolver().notifyChange(UserTableContract.URLAndId, null);

    }

    public void delete(int userId) {

        SQLiteDatabase writableDatabase = xmiDbHelper.getWritableDatabase();
        writableDatabase.delete(UserTableContract.UserTable.TAB_NAME, UserTableContract.UserTable.user_id + "=?", new String[]{String.valueOf(userId)});
        writableDatabase.close();

    }

    public void updata(User user) {
        SQLiteDatabase writableDatabase = xmiDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTableContract.UserTable.user_NAME, user.getUserName());
        contentValues.put(UserTableContract.UserTable.user_id, user.getUserId());
        contentValues.put(UserTableContract.UserTable.user_sex, user.getGender());
        contentValues.put(UserTableContract.UserTable.on_line, user.getOnLine());
        contentValues.put(UserTableContract.UserTable.HEAD_URL, user.getHeadUrl());
        contentValues.put(UserTableContract.UserTable.USER_TYPE, user.getUserType());
        contentValues.put(UserTableContract.UserTable.nick_name, user.getNickname());
        writableDatabase.update(UserTableContract.UserTable.TAB_NAME, contentValues, UserTableContract.UserTable.user_id + "=?", new String[]{String.valueOf(user.getUserId())});

        context.getContentResolver().notifyChange(UserTableContract.URLAndId, null);

    }

    public User find(String userId) {
        SQLiteDatabase readableDatabase = xmiDbHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query(UserTableContract.UserTable.TAB_NAME, null, UserTableContract.UserTable.user_id + "=?", new String[]{String.valueOf(userId)}, null, null, null);
        User user = null;

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String userName = cursor.getString(cursor.getColumnIndex(UserTableContract.UserTable.user_NAME));
                int UserIds = cursor.getInt(cursor.getColumnIndex(UserTableContract.UserTable.user_id));
                int gender = cursor.getInt(cursor.getColumnIndex(UserTableContract.UserTable.user_sex));
                String headUrl = cursor.getString(cursor.getColumnIndex(UserTableContract.UserTable.HEAD_URL));
                int onLine = cursor.getInt(cursor.getColumnIndex(UserTableContract.UserTable.on_line));
                int userType = cursor.getInt(cursor.getColumnIndex(UserTableContract.UserTable.USER_TYPE));
                String nickname = cursor.getString(cursor.getColumnIndex(UserTableContract.UserTable.nick_name));
                cursor.close();
                readableDatabase.close();
                user = new User(UserIds, userName, nickname, gender, userType, headUrl, onLine);
            }
        }
        return user;
    }
}
