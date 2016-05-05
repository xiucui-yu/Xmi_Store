package com.xmi.store.activity;

import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmi.store.R;
import com.xmi.store.activity.base.BaseFragmentActivity;
import com.xmi.store.db.User;
import com.xmi.store.db.UserTableContract;
import com.xmi.store.db.XmiDao;
import com.xmi.store.db.XmiDbHelper;
import com.xmi.store.util.SaveUtils;
import com.xmi.store.view.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-25
 * Time: 10:34
 * FIXME
 */
public class MyHomeActivity extends BaseFragmentActivity implements View.OnClickListener {

    @Bind(R.id.user_img)
    CircleImageView userImg;
    @Bind(R.id.userName)
    TextView userName;
    @Bind(R.id.level_text)
    TextView levelText;
    @Bind(R.id.setting_mainpage_msgpush)
    TextView settingMainpageMsgpush;
    private XmiDao xmiDao;
    private User user;

    private ContentObserver contentObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home_page_layout);
        ButterKnife.bind(this);
        xmiDao = new XmiDao(this);
        initData();
        userImg.setOnClickListener(this);
    }

    public void initData() {
        //检测用户是否登录
        checkUserInfo();

        contentObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                if (UserTableContract.URLAndId.equals(uri)) {
                    //更新本地
                    checkUserInfo();
                }
            }
        };
        this.getContentResolver().registerContentObserver(UserTableContract.URLs, true, contentObserver);
    }

    private void checkUserInfo() {
        if (isLogin()) {
            String headUrl = user.getHeadUrl();
            if (TextUtils.isEmpty(headUrl)) {
                userImg.setImageResource(R.mipmap.ic_launcher);
            } else {
                Glide.with(this).load(headUrl).into(userImg);
            }
            userName.setText(user.getNickname());
            levelText.setText(User.getTypeText(user.getUserType()));
        } else {
            userImg.setImageResource(R.mipmap.ic_launcher);
            userName.setText("登录");
            levelText.setText("未知用户");
        }
    }

    public boolean isLogin() {
        String uesrId = SaveUtils.getString(this, UserTableContract.UserTable.user_id);
        user = xmiDao.find(uesrId);
        if (TextUtils.isEmpty(uesrId) || user == null) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_img:

                Intent intent = new Intent();
                if (isLogin()) {
                    intent.setClass(MyHomeActivity.this, UserInfoActivity.class);
                } else {
                    intent.setClass(MyHomeActivity.this, LoginActivty.class);
                }
                startActivity(intent);
                break;


        }
    }
}
