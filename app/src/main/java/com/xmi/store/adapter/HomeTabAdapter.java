package com.xmi.store.adapter;

import android.content.Context;
import android.view.View;

import com.xmi.store.adapter.base.BaseListAdapter;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.HomeTabHolder;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.moudle.AppInfo;
import com.xmi.store.moudle.HomeTabBean;

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
        return new HomeTabHolder(mFragment);
    }
}
