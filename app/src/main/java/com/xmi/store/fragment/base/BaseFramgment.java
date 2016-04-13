package com.xmi.store.fragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmi.store.R;
import com.xmi.store.callback.ErrorClickListener;
import com.xmi.store.view.PageStateLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

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


    private PageStateLayout stateLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewId();
        ButterKnife.bind(this, mMainView);
        stateLayout = new PageStateLayout(getActivity());
        stateLayout.setContextView(mMainView);
        stateLayout.setStatus(PageStateLayout.STATE_LOADING);
        stateLayout.setErrorClickListener(listener);
        swipeRefresh.setColorSchemeResources(R.color.theme_orange);
        swipeRefresh.setSize(SwipeRefreshLayout.LARGE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseFramgment.this.onRefresh();
            }
        });
        initAddition();
        return stateLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract void initViewId();

    protected abstract void initAddition();


    protected abstract void onRefresh();


    protected abstract void onMore();


    ErrorClickListener listener = new ErrorClickListener() {
        @Override
        public void callback() {
            stateLayout.setStatus(PageStateLayout.STATE_LOADING);
            initData();
        }
    };

    public void setStatus(int status) {
        stateLayout.setStatus(status);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
