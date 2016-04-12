package com.xmi.store.holder.base;

import android.content.Context;
import android.view.View;

import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;

/**
 * User: xiucui.yu
 * Date: 2016-04-08
 * Time: 15:22
 * FIXME
 */
public abstract class BaseHolder<T> {

    protected Context context;

    protected View convertView;

    protected T data;

    protected BaseFramgment mFragment;


    public BaseHolder(BaseFramgment mFragment) {
        this.mFragment = mFragment;
        convertView = initView();
        convertView.setTag(this);
    }

    public void setDate(T itemBean) {
        this.data = itemBean;
        initData(data);
    }

    /**
     * 初始化布局
     *
     * @return
     */

    protected abstract View initView();


    /**
     * 子类实现数据相关操作
     */
    protected abstract void initData(T data);

    public View getConvertView() {
        return convertView;
    }

}
