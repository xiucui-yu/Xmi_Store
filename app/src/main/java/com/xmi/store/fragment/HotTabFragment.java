package com.xmi.store.fragment;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.moudle.HotTabBean;
import com.xmi.store.net.RequestParams;
import com.xmi.store.protocol.HotTabProtocol;
import com.xmi.store.util.UIUtils;
import com.xmi.store.view.HotLabelLayout;
import com.xmi.store.view.PageStateLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 18:23
 * FIXME
 */
public class HotTabFragment extends BaseFramgment {


    HotTabBean hotTabBean = new HotTabBean();
    @Bind(R.id.parent_view)
    ScrollView parentView;
    private RequestParams mRequestParams = new RequestParams();
    private HotTabProtocol mProtocol = new HotTabProtocol();

    HotLabelLayout hotLabelLayout;

    private int mCurrentIndex=0;

    @Override
    protected void initData(int mCurrentIndex) {
        mRequestParams.putParams("index", mCurrentIndex);
        mProtocol.getHot(mRequestParams, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                setStatus(PageStateLayout.STATE_ERROR);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                SystemClock.sleep(1000);
                try {
                    List<String> tabNames = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String tabName = jsonArray.getString(i);
                        tabNames.add(tabName);
                    }
                    hotTabBean.setTabNames(tabNames);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (hotTabBean != null && hotTabBean.getTabNames().size() > 0) {
                            setStatus(PageStateLayout.STATE_SUCCEED);
                            upladView(hotTabBean.getTabNames());

                        } else {
                            setStatus(PageStateLayout.STATE_EMPTY);
                        }
                    }
                });
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void upladView(List<String> strs) {
        if (hotLabelLayout != null) {
            hotLabelLayout.removeAllViews();
        }

        //���µ�shape
        GradientDrawable pressedDrawable = UIUtils.getbackground(0xffcecece);
        for (int i = 0; i < strs.size(); i++) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setText(strs.get(i));
            int tv_pading_w = UIUtils.sp2px(12);
            int tv_pading_h = UIUtils.sp2px(10);
            textView.setPadding(tv_pading_w, tv_pading_h, tv_pading_w, tv_pading_h);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            GradientDrawable normalground = UIUtils.getbackground(UIUtils.getRandomColor());

            textView.setBackground(UIUtils.getSelectbackground(pressedDrawable, normalground));

            textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));

            hotLabelLayout.addView(textView);

        }
        parentView.addView(hotLabelLayout);


    }

    @Override
    protected void initViewId() {
        mMainView = View.inflate(getActivity(), R.layout.fragment_hot_layout, null);

    }

    @Override
    protected void initAddition() {
        hotLabelLayout = new HotLabelLayout(getActivity());
        hotLabelLayout.setBackgroundResource(R.mipmap.grid_item_bg_normal);
        int pading = UIUtils.sp2px(13);
        hotLabelLayout.setPadding(pading, pading, pading, pading);

    }


    @Override
    protected void onRefresh() {
        mCurrentIndex=0;
        initData(mCurrentIndex);

    }

    @Override
    protected void onMore() {

    }

}
