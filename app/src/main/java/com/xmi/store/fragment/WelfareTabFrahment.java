package com.xmi.store.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 18:23
 * FIXME
 */
public class WelfareTabFrahment extends BaseFramgment {
    @Bind(R.id.mlistview)
    ListView mlistview;

    @Override
    protected void initData(int index) {

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
