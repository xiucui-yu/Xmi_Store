package com.xmi.store.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xmi.store.R;

/**
 * User: xiucui.yu
 * Date: 2016-04-07
 * Time: 16:04
 * FIXME
 */
public class PageStateLayout extends FrameLayout {

    /**
     * 未知状态
     */
    public static final int STATE_UNKNOW = 0;
    /**
     * 正在加载状态
     */
    public static final int STATE_LOADING = 1;
    /**
     * 错误状态
     */
    public static final int STATE_ERROR = 2;
    /**
     * 空数据状态
     */
    public static final int STATE_EMPTY = 3;
    /**
     * 成功状态
     */
    public static final int STATE_SUCCEED = 4;

    private int mState = STATE_UNKNOW;

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSucceedView;

    private Context mContext;

    public PageStateLayout(Context context) {
        this(context, null, 0);
    }

    public PageStateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mLoadingView = initLoadingView();
        mErrorView = initErrorView();
        mEmptyView = initEmptyView();

        if (mLoadingView != null) {
            addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        if (mErrorView != null) {
            addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        if (mEmptyView != null) {
            addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }


    public View initLoadingView() {
        mLoadingView = View.inflate(mContext, R.layout.loading_page_load, null);
        return mLoadingView;
    }

    public View initErrorView() {
        mErrorView = View.inflate(mContext, R.layout.loading_page_error, null);
        return mErrorView;
    }

    public View initEmptyView() {
        mEmptyView = View.inflate(mContext, R.layout.loading_page_empty, null);
        return mEmptyView;
    }


}
