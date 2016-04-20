package com.xmi.store.adapter;

import com.xmi.store.adapter.base.BaseListAdapter;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.AppDetailHolder;
import com.xmi.store.holder.MoreHolder;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.moudle.AppInfo;

import java.util.List;

/**
 * User: xiucui.yu
 * Date: 2016-04-12
 * Time: 13:51
 * FIXME
 */
public class HomeTabAdapter extends BaseListAdapter<AppInfo> {
    public HomeTabAdapter(BaseFramgment mFragment, List<AppInfo> mData) {
        super(mFragment, mData);
        this.mFragment = mFragment;

    }

    @Override
    protected BaseHolder getHolder() {
        return new AppDetailHolder(mFragment);
    }


    protected MoreHolder.LoadMoreType hasLoadMore() {
        return MoreHolder.LoadMoreType.loadMore;
    }


}
