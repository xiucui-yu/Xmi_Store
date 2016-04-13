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
import com.xmi.store.moudle.GameTabBean;
import com.xmi.store.net.RequestParams;
import com.xmi.store.protocol.AppDetailProtocol;
import com.xmi.store.protocol.GameTabProtocol;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 18:23
 * FIXME
 */
public class GameTabFragment extends BaseFramgment {

    @Bind(R.id.mlistview)
    ListView mlistview;

    private RequestParams mRequestParams = new RequestParams();
    private GameTabProtocol mProtocol = new GameTabProtocol();

    @Override
    protected void initData() {
        mRequestParams.putParams("index", 0);
        mProtocol.getGame(mRequestParams, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                GameTabBean appTabBean = mProtocol.setDate(response.body().string(), GameTabBean.class);
            }
        });
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
