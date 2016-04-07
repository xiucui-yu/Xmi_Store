package com.xmi.store.fragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmi.store.R;
import com.xmi.store.fragment.HomeTabFragment;

import butterknife.Bind;

/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 18:23
 * FIXME
 */
public abstract class BaseFramgment extends Fragment {
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    protected View mMainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView();
        swipeRefresh.setColorSchemeResources(R.color.theme_orange);
        swipeRefresh.setSize(SwipeRefreshLayout.LARGE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseFramgment.this.onRefresh();
            }
        });

        return mMainView;
    }

    protected abstract void initView();


    protected abstract void onRefresh();


    protected abstract void onMore();
}
