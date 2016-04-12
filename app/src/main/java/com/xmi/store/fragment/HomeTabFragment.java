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


    @Override
    protected void initData() {

        adapter = new HomeTabAdapter(this, null);
        mlistview.setAdapter(adapter);
        mRequestParams.putParams("index", 0);
        mProtocol.homeTabUrl(mRequestParams, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("xiaoxiao", e.toString());
                setStatus(PageStateLayout.STATE_ERROR);

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                SystemClock.sleep(1500);
                final HomeTabBean homeTabBean = mProtocol.setDate(response.body().string(), HomeTabBean.class);
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (homeTabBean == null) {
                            setStatus(PageStateLayout.STATE_EMPTY);
                        } else {
                            setStatus(PageStateLayout.STATE_SUCCEED);
                            adapter.setData(homeTabBean.getList());
                        }

                    }
                });


            }
        });
    }

    @Override
    protected void initView() {
        mMainView = View.inflate(getActivity(), R.layout.fragment_home_layout, null);




        mlistview.addHeaderView();

    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onMore() {

    }


}
