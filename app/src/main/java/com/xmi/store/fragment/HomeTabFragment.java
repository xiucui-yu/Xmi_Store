package com.xmi.store.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.load.LoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    protected void initView() {
        ButterKnife.bind(this, mMainView);



    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onMore() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
