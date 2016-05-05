package com.xmi.store.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmi.store.R;
import com.xmi.store.db.User;
import com.xmi.store.util.AppInfo;
import com.xmi.store.util.UIUtils;

/**
 * User: xiucui.yu
 * Date: 2016-05-04
 * Time: 14:04
 * FIXME
 */
public class UserHeadDialog {

    ImageView headImg;

    TextView etUsernameText;

    RelativeLayout headLayout;

    ImageView dialogCancelIv;
    private Context mContext;

    private User mUser;
    private int mWidth;
    Dialog dialog = null;

    View inflate = null;

    public UserHeadDialog(Context context, User user) {
        mContext = context;
        inflate = View.inflate(context, R.layout.head_top_dialog_layout, null);
        mUser = user;
        initView();
    }

    private void initView() {

        dialog = new Dialog(mContext);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        mWidth = (int) (AppInfo.screenWidthForPortrait * 0.9);

        headImg = (ImageView) inflate.findViewById(R.id.head_img);

        headLayout = (RelativeLayout) inflate.findViewById(R.id.head_layout);

        dialogCancelIv = (ImageView) inflate.findViewById(R.id.dialog_cancel_iv);

        etUsernameText = (TextView) inflate.findViewById(R.id.et_username_text);

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams2.setMargins(0, UIUtils.sp2px(10), UIUtils.sp2px(10), 0);
        dialogCancelIv.setLayoutParams(layoutParams2);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.width = mWidth;

        layoutParams.height = mWidth;

        headLayout.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams1.width = mWidth;

        layoutParams1.height = mWidth;

        headImg.setLayoutParams(layoutParams1);
        headImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(mUser.getHeadUrl()).into(headImg);
        etUsernameText.setText(mUser.getNickname());
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(true);
        dialogCancelIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }

        });
    }

    public void show() {
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = (int) (AppInfo.screenWidthForPortrait * 0.9);
        dialog.getWindow().setAttributes(attributes);
        dialog.show();
    }


    private void close() {
        dialog.cancel();

    }

}
