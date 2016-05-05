package com.xmi.store.fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xmi.store.R;
import com.xmi.store.adapter.HomeTabAdapter;
import com.xmi.store.adapter.base.BaseListAdapter;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.moudle.AppInfo;
import com.xmi.store.moudle.GameTabBean;
import com.xmi.store.net.RequestParams;
import com.xmi.store.protocol.GameTabProtocol;
import com.xmi.store.util.UIUtils;
import com.xmi.store.view.PageStateLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 18:23
 * FIXME
 */
public class GameTabFragment extends BaseFramgment {

    @Bind(R.id.mlistview)
    ListView mlistview;

    private GameTabBean gameTabBean = new GameTabBean();

    private HomeTabAdapter homeTabAdapter;

    private RequestParams mRequestParams = new RequestParams();
    private GameTabProtocol mProtocol = new GameTabProtocol();

    private int mCurrentPage = 0;

    @Override
    protected void initData(final int mCurrentPage) {
        mRequestParams.putParams("index", mCurrentPage);
        mProtocol.getGame(mRequestParams, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("xiaoxiao", e.toString());
                setStatus(PageStateLayout.STATE_ERROR);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                SystemClock.sleep(1500);
                // gameTabBean = mProtocol.setDate(response.body().string(), GameTabBean.class);
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                gameTabBean.setList(list);


                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (gameTabBean == null) {
                            setStatus(PageStateLayout.STATE_EMPTY);
                            swipeRefresh.setRefreshing(false);
                        } else {
                            setStatus(PageStateLayout.STATE_SUCCEED);
                            if (mCurrentPage == 0) {
                                homeTabAdapter.setData(gameTabBean.getList());
                            } else {
                                homeTabAdapter.addData(gameTabBean.getList());
                            }
                        }
                    }
                });
            }
        });
    }
    @Override
    protected void initViewId() {
        mMainView = View.inflate(getActivity(), R.layout.fragment_home_layout, null);
    }
    @Override
    protected void initAddition() {
        homeTabAdapter = new HomeTabAdapter(this, null);
        //加载更多的回调
        homeTabAdapter.setMoreListener(new BaseListAdapter.LoadMoreListener() {

            @Override
            public void onLoadMore() {
                initData(mCurrentPage);
            }
        });
        mlistview.setAdapter(homeTabAdapter);
    }
    @Override
    protected void onRefresh() {

        mCurrentPage = 0;
        initData(mCurrentPage);
    }
    @Override
    protected void onMore() {

    }
}
