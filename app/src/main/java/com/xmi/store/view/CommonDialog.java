package com.xmi.store.view;

import android.app.AlertDialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmi.store.R;
import com.xmi.store.util.UIUtils;

/**
 * User: xiucui.yu
 * Date: 2016-04-26
 * Time: 16:53
 * FIXME
 */
public class CommonDialog {

    private AlertDialog mDialog;

    private LinearLayout viewParent;

    private Context mContext;

    private int TabNum = 0;


    private int mWidth = UIUtils.sp2px(200);


    public CommonDialog(Context mContext) {

        this.mContext = mContext;
        initData(mContext);
    }

    private void initAttr() {

        //默认在中间就好
        WindowManager.LayoutParams attributes = mDialog.getWindow().getAttributes();
        attributes.width = mWidth;
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mDialog.getWindow().setAttributes(attributes);
        // mDialog.setCanceledOnTouchOutside(true);
        mDialog.setContentView(viewParent);
    }

    private void initData(Context context) {
        mDialog = new AlertDialog.Builder(context).create();
        viewParent = (LinearLayout) View.inflate(context, R.layout.dialog_parent_layout, null);

    }

    public CommonDialog addItem(String tabName, final View.OnClickListener listener) {
        if (TabNum > 0) {
            //中间加一条横线
            View view = new View(mContext);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.px2sp(1));
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.color.btn_blue_pressed);
            viewParent.addView(view);
        }
        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(UIUtils.sp2px(15), UIUtils.sp2px(15), UIUtils.sp2px(15), UIUtils.sp2px(15));
        textView.setTextColor(mContext.getResources().getColor(R.color.btn_blue_pressed));
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setText(tabName);
        textView.setBackgroundResource(R.drawable.selector_list_item_bg);
        //sp 作为单位
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dismiss();
            }
        });

        TabNum++;

        viewParent.addView(textView);
        return this;

    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {

            mDialog.show();
            initAttr();
        }

    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


}
