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

    private MoreHolder mMoreHolder;

    private LoadMoreListener listener;


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
            return mData.size() + 1;
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


    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (getCount() - 1 == position) {
            return TYPE_LOAD_MORE;
        } else {
            return getItemType(position);
        }
    }

    protected int getItemType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHolder mHolder = null;
        if (getItemViewType(position) == TYPE_LOAD_MORE) {
            mHolder = getMoreHolder();
        } else {
            if (convertView == null) {
                mHolder = getHolder();
            } else {
                mHolder = (BaseHolder) convertView.getTag();
            }
            mHolder.setDate(getItem(position));
        }

        return mHolder.getConvertView();
    }

    protected MoreHolder getMoreHolder() {
        if (mMoreHolder == null) {
            mMoreHolder = new MoreHolder(mFragment, this, hasLoadMore());
        }
        return mMoreHolder;

    }

    @SuppressWarnings("unchecked")
    public void setMoreHolderType(MoreHolder.LoadMoreType type) {
        getMoreHolder().setDate(type);
    }

    /**
     * 是否需要加载更多功能
     * 默认：不需要
     */
    protected MoreHolder.LoadMoreType hasLoadMore() {
        return MoreHolder.LoadMoreType.unLoadMore;
    }

    protected abstract BaseHolder<T> getHolder();

    public void setMoreListener(LoadMoreListener listener) {
        this.listener = listener;

    }

    public void loadMore() {

        if (listener != null) {
            listener.onLoadMore();
        }
    }

    ;

    public interface LoadMoreListener {
        void onLoadMore();
    }

    public void complete(){
        getMoreHolder().complete();
    }
}
