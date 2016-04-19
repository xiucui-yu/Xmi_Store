package com.xmi.store.holder;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmi.store.R;
import com.xmi.store.adapter.base.BaseListAdapter;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.tripartite.ProgressWheel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-08
 * Time: 15:26
 * FIXME
 */
public class MoreHolder extends BaseHolder<MoreHolder.LoadMoreType> {
    @Bind(R.id.loading_pb)
    ProgressWheel loadingPb;
    @Bind(R.id.loading_txt)
    TextView loadingTxt;
    @Bind(R.id.rl_more_loading)
    RelativeLayout rlMoreLoading;
    @Bind(R.id.loading_error_txt)
    TextView loadingErrorTxt;
    @Bind(R.id.rl_more_error)
    RelativeLayout rlMoreError;

    private BaseListAdapter mListAdapter;

    private boolean idLoading = false;

    public <T> MoreHolder(BaseFramgment fragment, BaseListAdapter mListAdapter, LoadMoreType loadMoreType) {
        super(fragment);
        this.mListAdapter = mListAdapter;
        setDate(loadMoreType);
    }

    @Override
    protected View initView() {
        convertView = View.inflate(mFragment.getActivity(), R.layout.holder_load_more, null);
        ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    protected void initData(LoadMoreType data) {

        getConvertView().setVisibility(data == LoadMoreType.unLoadMore ? View.GONE : View.INVISIBLE);
        rlMoreLoading.setVisibility(data == LoadMoreType.loadMore ? View.GONE : View.INVISIBLE);
        rlMoreError.setVisibility(data == LoadMoreType.error ? View.GONE : View.INVISIBLE);

    }

    @Override
    public View getConvertView() {
        if (getData() == LoadMoreType.loadMore) {
            //¼ÓÔØ¸ü¶à
            loadMore();
        }
        return super.getConvertView();
    }

    protected void loadMore() {
        if (!idLoading && mListAdapter != null) {
            idLoading = true;
            mListAdapter.loadMore();

        }
    }

    public enum LoadMoreType {
        loadMore, unLoadMore, error
    }


}
