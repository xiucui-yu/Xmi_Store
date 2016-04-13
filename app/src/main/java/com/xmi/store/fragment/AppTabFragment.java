package com.xmi.store.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.moudle.AppInfo;
import com.xmi.store.moudle.AppTabBean;
import com.xmi.store.moudle.HomeTabBean;
import com.xmi.store.net.RequestParams;
import com.xmi.store.protocol.AppDetailProtocol;
import com.xmi.store.protocol.HomeTabProtocol;

import java.io.IOException;

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

    private RequestParams mRequestParams = new RequestParams();
    private AppDetailProtocol mProtocol = new AppDetailProtocol();

    @Override
    protected void initData() {
        mRequestParams.putParams("index", 0);

    }


    @Override
    protected void initViewId() {
        mMainView = View.inflate(getActivity(), R.layout.fragment_home_layout, null);

    }

    @Override
    protected void initAddition() {

    }


    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onMore() {

    }
}
