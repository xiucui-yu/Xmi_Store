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
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.HomeHeaderHolder;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.RequestParams;
import com.xmi.store.protocol.HomeTabProtocol;
import com.xmi.store.util.UIUtils;
import com.xmi.store.view.PageStateLayout;

import java.io.IOException;

import butterknife.Bind;


/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 18:23
 * FIXME
 */
public class HomeTabFragment extends BaseFramgment {


    @Bind(R.id.mlistview)
    ListView mlistview;

    private HomeTabAdapter adapter;


    private HomeTabProtocol mProtocol = new HomeTabProtocol();

    private RequestParams mRequestParams = new RequestParams();

    private HomeTabBean homeTabBean;
    private int mCurrentIndex = 0;

    @Override
    protected void initData(int mCurrentIndex) {
        mRequestParams.putParams("index", mCurrentIndex);
        mProtocol.homeTabUrl(mRequestParams, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("xiaoxiao", e.toString());
                setStatus(PageStateLayout.STATE_ERROR);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                SystemClock.sleep(1500);
                homeTabBean = mProtocol.setDate(response.body().string(), HomeTabBean.class);
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (homeTabBean == null) {
                            setStatus(PageStateLayout.STATE_EMPTY);
                        } else {
                            setStatus(PageStateLayout.STATE_SUCCEED);
                            adapter.setData(homeTabBean.getList());
                            homeHeaderHolder.setDate(homeTabBean.getPicture());
                        }

                    }
                });
            }
        });
    }

    private HomeHeaderHolder homeHeaderHolder;

    @Override
    protected void initViewId() {
        mMainView = View.inflate(getActivity(), R.layout.fragment_home_layout, null);
    }

    @Override
    protected void initAddition() {
        adapter = new HomeTabAdapter(this, null);
        mlistview.setAdapter(adapter);

        homeHeaderHolder = new HomeHeaderHolder(this);
        mlistview.addHeaderView(homeHeaderHolder.getConvertView());
    }

    @Override
    protected void onRefresh() {
        mCurrentIndex = 0;
        initData(mCurrentIndex);

    }

    @Override
    protected void onMore() {

    }


}
