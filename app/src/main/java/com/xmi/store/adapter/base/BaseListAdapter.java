package com.xmi.store.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.holder.MoreHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * User: xiucui.yu
 * Date: 2016-04-08
 * Time: 14:56
 * FIXME
 * <p>
 * de:
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected List<T> mData;

    protected BaseFramgment mFragment;

    private BaseHolder mMoreHolder;


    protected static final int TYPE_ITEM = 0;
    protected static final int TYPE_LOAD_MORE = 1;


    public BaseListAdapter(BaseFramgment mFragment, List<T> mData) {
        this.mFragment = mFragment;
        mData = new ArrayList<T>();
        if (mData != null) {
            mData.addAll(mData);
        }

    }

    public void setData(List<T> data) {
        if (mData == null) {
            mData = new ArrayList<T>();
        }
        if (mData != null) {
            mData.clear();
        }
        mData.addAll(data);
        notifyDataSetChanged();

    }


    public void addData(List<T> data) {
        if (mData == null) {
            mData = new ArrayList<T>();
        }
        mData.addAll(data);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (mData != null && mData.size() > 0) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

/*
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //���һ��item
        *//*if (getCount() - 1 == position) {
            return TYPE_LOAD_MORE;
        } else {*//*
            return getItemType(position);
       // }
    }

    protected int getItemType(int position) {
        return TYPE_ITEM;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHolder mHolder = null;
      /*  if (getItemViewType(position) == TYPE_LOAD_MORE) {
            mHolder = getMoreHolder();
        }*/
        if (convertView == null) {
            mHolder = getHolder();
        } else {
            mHolder = (BaseHolder) convertView.getTag();
        }
        mHolder.setDate(getItem(position));

        return mHolder.getConvertView();
    }

    public BaseHolder<T> getMoreHolder() {
        if (mMoreHolder == null) {
            mMoreHolder = new MoreHolder(mFragment);
        }
        return mMoreHolder;

    }


    protected abstract BaseHolder<T> getHolder();


}
