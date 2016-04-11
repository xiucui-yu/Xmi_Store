package com.xmi.store.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.load.LoadingView;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.RequestParams;
import com.xmi.store.protocol.HomeTabProtocol;

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
    @Bind(R.id.loadView)
    LoadingView loadView;

    private HomeTabProtocol mProtocol =new HomeTabProtocol();

    private RequestParams mRequestParams=new RequestParams();


    @Override
    protected void initData() {
        mRequestParams.putParams("index",0);
        mProtocol.homeTabUrl(mRequestParams, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                HomeTabBean homeTabBean = mProtocol.setDate(response.toString(), HomeTabBean.class);
            }
        });
    }

    @Override
    protected void initView() {
        mMainView = View.inflate(getActivity(), R.layout.fragment_home_layout, null);
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onMore() {

    }





}
