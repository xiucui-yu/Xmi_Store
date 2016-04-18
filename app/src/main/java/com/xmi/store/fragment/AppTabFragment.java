package com.xmi.store.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xmi.store.R;
import com.xmi.store.adapter.AppTabAdapter;
import com.xmi.store.adapter.HomeTabAdapter;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.HomeHeaderHolder;
import com.xmi.store.moudle.AppInfo;
import com.xmi.store.moudle.AppTabBean;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.RequestParams;
import com.xmi.store.protocol.AppDetailProtocol;
import com.xmi.store.protocol.AppTabProtocol;
import com.xmi.store.protocol.HomeTabProtocol;
import com.xmi.store.util.UIUtils;
import com.xmi.store.view.PageStateLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class AppTabFragment extends BaseFramgment {

    @Bind(R.id.mlistview)
    ListView mlistview;

    private AppTabBean appTabBean = new AppTabBean();

    private AppTabAdapter homeTabAdapter;

    private RequestParams mRequestParams = new RequestParams();
    private AppTabProtocol mProtocol = new AppTabProtocol();

    @Override
    protected void initData() {
        mRequestParams.putParams("index", 0);
        mProtocol.getAppTab(mRequestParams, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("xiaoxiao", e.toString());
                setStatus(PageStateLayout.STATE_ERROR);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                SystemClock.sleep(1500);
                List<AppInfo> list = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String packageName = jsonObject.getString("packageName");
                        String iconUrl = jsonObject.getString("iconUrl");
                        double stars = jsonObject.getDouble("stars");
                        int size = jsonObject.getInt("size");
                        String downloadUrl = jsonObject.getString("downloadUrl");
                        String des = jsonObject.getString("des");
                        list.add(new AppInfo(id, name, packageName, iconUrl, (float) stars, size, downloadUrl, des));

                    }
                    appTabBean.setList(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //½âÎöÊý¾Ý
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (appTabBean == null) {
                            setStatus(PageStateLayout.STATE_EMPTY);
                        } else {
                            setStatus(PageStateLayout.STATE_SUCCEED);
                            homeTabAdapter.setData(appTabBean.getList());
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void initViewId() {
        mMainView = View.inflate(getActivity(), R.layout.fragment_app_layout, null);
    }

    @Override
    protected void initAddition() {
        homeTabAdapter = new AppTabAdapter(this, null);
        mlistview.setAdapter(homeTabAdapter);

    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onMore() {

    }
}
