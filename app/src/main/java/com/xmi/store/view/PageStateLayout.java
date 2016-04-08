package com.xmi.store.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xmi.store.R;
import com.xmi.store.callback.ErrorClickListener;
import com.xmi.store.util.UIUtils;

/**
 * User: xiucui.yu
 * Date: 2016-04-07
 * Time: 16:04
 * FIXME
 */
public class PageStateLayout extends FrameLayout {
    /**
     * 点击error 回调
     */
    private ErrorClickListener listener;
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

        showPage();
    }

    private void showPage() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updatePage();
            }
        });
    }

    public void setStatus(int status) {
        this.mState = status;
        updatePage();
    }

    private void updatePage() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }

        if (mEmptyView != null) {
            mEmptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }

        if (mSucceedView != null) {
            mSucceedView.setVisibility(mState == STATE_SUCCEED ? View.VISIBLE : View.INVISIBLE);
        }

    }

    public void setContextView(View view) {
        if (mSucceedView != null) {
            removeView(mSucceedView);
        }
        mSucceedView = view;
        mSucceedView.setVisibility(View.GONE);
        addView(mSucceedView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setLoadingView(View view) {
        if (mLoadingView != null) {
            removeView(mLoadingView);
        }
        mLoadingView = view;
        mLoadingView.setVisibility(View.GONE);
        addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }


    public void setErrorView(View view) {
        if (mErrorView != null) {
            removeView(mErrorView);
        }
        mErrorView = view;
        mErrorView.setVisibility(View.GONE);
        addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setEmptyView(View view) {
        if (mEmptyView != null) {
            removeView(mEmptyView);
        }
        mEmptyView = view;
        mEmptyView.setVisibility(View.GONE);
        addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mEmptyView.setOnClickListener(clickListener);
    }


    public View initLoadingView() {
        mLoadingView = View.inflate(mContext, R.layout.loading_page_load, null);
        return mLoadingView;
    }

    public View initErrorView() {
        mErrorView = View.inflate(mContext, R.layout.loading_page_error, null);
        mErrorView.setOnClickListener(clickListener);
        return mErrorView;
    }

    public View initEmptyView() {
        mEmptyView = View.inflate(mContext, R.layout.loading_page_empty, null);
        return mEmptyView;
    }

    public void setErrorClickListener(ErrorClickListener listener) {
        this.listener = listener;
    }


    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.callback();
            }
        }
    };

}
