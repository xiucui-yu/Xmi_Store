package com.xmi.store.holder;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.moudle.TopPicInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-12
 * Time: 18:18
 * FIXME
 */
public class HomeHeaderHolder extends BaseHolder<TopPicInfo> {

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.event_title)
    TextView eventTitle;
    @Bind(R.id.ll_point_parent)
    LinearLayout llPointParent;

    public HomeHeaderHolder(BaseFramgment mFragment) {
        super(mFragment);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mFragment.getActivity(), R.layout.holder_home_header_item, null);
        ButterKnife.bind(mFragment, view);
        return view;
    }

    @Override
    protected void initData(TopPicInfo data) {



    }

}
