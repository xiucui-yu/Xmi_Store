package com.xmi.store.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xmi.store.R;
import com.xmi.store.activity.base.BaseFragmentActivity;
import com.xmi.store.db.User;
import com.xmi.store.db.UserTableContract;
import com.xmi.store.db.XmiDao;
import com.xmi.store.util.SaveUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-25
 * Time: 11:19
 * FIXME
 */
public class LoginActivty extends BaseFragmentActivity {

    @Bind(R.id.iv_del)
    ImageView ivDel;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.tv_forgot_pwd)
    TextView tvForgotPwd;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;


    private XmiDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder_login);
        ButterKnife.bind(this);
        initData();
        dao = new XmiDao(this);
    }

    private void initData() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etUsernameStr = etUsername.getText().toString();
                String etPasswordStr = etPassword.getText().toString();
                if (!TextUtils.isEmpty(etUsernameStr) && !TextUtils.isEmpty(etPasswordStr)) {

                    String str = "admin";
                    if (etUsernameStr.equals(str) && etPasswordStr.equals(str)) {
                        //int userId, String userName, String nickname, int gender, int userType, String headUrl, int onLine
                        User user = new User(1001, "admin", "andy", 2, 0, "", 1);
                        User user1 = dao.find(user.getUserId() + "");
                        if (user1 == null) {
                            dao.insert(user);
                        } else {
                            dao.updata(user);
                        }
                        SaveUtils.putString(LoginActivty.this, UserTableContract.UserTable.user_id, user.getUserId() + "");
                        loginSuccess();
                    } else {
                        Toast.makeText(LoginActivty.this, "用户名密码不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivty.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    private void loginSuccess() {
        this.finish();
    }
}
