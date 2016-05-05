package com.xmi.store.db;

import com.xmi.store.R;
import com.xmi.store.XmiApp;



/**
 * Created by Ljb on 2015/11/20.
 */
public class User {
    public static final int GENDER_GG = 1;
    public static final int GENDER_MM = 2;
    public static final int ON_LINE = 1;
    public static final int OFF_LINE = 0;

    public class UserType {
        public static final int TYPE_DEBUG = -1;
        public static final int TYPE_DEFAULT = 0;

   }

    public int userId;
    public String userName;
    public String nickname;
    public int gender;
    public int userType;
    public String headUrl;
    /**
     * 0:未登录
     * 1：已登录
     * */
    public int onLine ;


    public static String getTypeText(int userType) {
        String typeText = XmiApp.getApplicaion().getApplicationContext().getResources().getString(R.string.unknow_user);
        switch (userType){
            case UserType.TYPE_DEBUG:
                typeText = XmiApp.getApplicaion().getApplicationContext().getResources().getString(R.string.type_user_debug);
                break;
            case UserType.TYPE_DEFAULT:
                typeText = XmiApp.getApplicaion().getApplicationContext().getResources().getString(R.string.type_user_wmi);
                break;

        }
        return typeText;
    }

    public User() {
    }

    public User(int userId, String userName, String nickname, int gender, int userType, String headUrl, int onLine) {
        this.userId = userId;
        this.userName = userName;
        this.nickname = nickname;
        this.gender = gender;
        this.userType = userType;
        this.headUrl = headUrl;
        this.onLine = onLine;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getOnLine() {
        return onLine;
    }

    public void setOnLine(int onLine) {
        this.onLine = onLine;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
