package com.xmi.store.adapter;

import com.xmi.store.adapter.base.BaseListAdapter;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.AppDetailHolder;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.moudle.AppInfo;
import com.xmi.store.moudle.AppTabBean;

import java.util.List;



/**
 * Created by Ljb on 2015/11/9.
 */
public class AppTabAdapter extends BaseListAdapter<AppInfo> {

    public AppTabAdapter(BaseFramgment mFragment, List<AppInfo> mData) {
        super(mFragment, mData);
    }

    @Override
    protected BaseHolder<AppInfo> getHolder() {
        return new AppDetailHolder(mFragment);
    }

}
