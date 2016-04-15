package com.xmi.store.holder;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xmi.store.R;
import com.xmi.store.adapter.HomeHeaderAdapter;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.moudle.TopPicInfo;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.util.UIUtils;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-12
 * Time: 18:18
 * FIXME
 */
public class HomeHeaderHolder extends BaseHolder<List<TopPicInfo>> {

    ViewPager viewPager;

    TextView eventTitle;

    LinearLayout llPointParent;

    private HomeHeaderAdapter homeHeaderAdapter;

    public HomeHeaderHolder(BaseFramgment mFragment) {
        super(mFragment);
    }

    @Override
    protected View initView() {
        convertView = View.inflate(mFragment.getActivity(), R.layout.holder_home_header_item, null);
        viewPager = (ViewPager) convertView.findViewById(R.id.view_pager);
        eventTitle = (TextView) convertView.findViewById(R.id.event_title);
        // convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, UIUtils.sp2px(140)));
        llPointParent = (LinearLayout) convertView.findViewById(R.id.ll_point_parent);
        initScoll();
        return convertView;
    }

    private void initScoll() {

        try {
            //反射机制   控制 viewpager滑动时间  为1000
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mFragment.getActivity());
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    class FixedSpeedScroller extends Scroller {
        private int mDuration = 1000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy,
                                int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }

    }

    ;

    @Override
    protected void initData(List<TopPicInfo> data) {
        initPoint(data);
        if (homeHeaderAdapter == null) {
            homeHeaderAdapter = new HomeHeaderAdapter(data, mFragment);
            viewPager.setAdapter(homeHeaderAdapter);
        } else {
            homeHeaderAdapter.setData(data);
        }
        startAutoLooper();
        initScollListener();
    }

    private void initScollListener() {
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            private int upPosition = 0;

            @Override
            public void onPageSelected(int position) {
                eventTitle.setText(data.get(position).getPicDes());
                llPointParent.getChildAt(upPosition).setEnabled(false);
                llPointParent.getChildAt(position).setEnabled(true);
                upPosition = position;
            }

        });
    }

    private void initPoint(List<TopPicInfo> data) {
        if (llPointParent != null) {
            llPointParent.removeAllViews();
        }
        for (int i = 0; i < data.size(); i++) {
            View pointView = new View(mFragment.getActivity());
            pointView.setBackgroundResource(R.drawable.point_drawable);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
            layoutParams.leftMargin = 15;
            pointView.setLayoutParams(layoutParams);
            llPointParent.addView(pointView);
            if (i == 0) {
                pointView.setEnabled(true);
            } else {
                pointView.setEnabled(false);
            }
        }
    }

    private LooperTask runTask;

    private void startAutoLooper() {
        if (runTask == null) {
            runTask = new LooperTask(new Handler());
        } else {
            runTask.stop();
        }
        runTask.start();
    }

    class LooperTask implements Runnable {
        private Handler mHanler;

        private boolean isRun;


        public final int Time = 2000;

        public LooperTask(Handler mMainHandler) {
            mHanler = mMainHandler;
        }


        public void start() {
            if (!isRun) {
                isRun = true;
                mHanler.postDelayed(this, Time);
            }
        }

        public void stop() {
            if (isRun) {
                isRun = false;
                mHanler.removeCallbacks(this);
            }
        }

        @Override
        public void run() {
            int currIndex = viewPager.getCurrentItem();
            if (++currIndex == homeHeaderAdapter.getCount()) {
                viewPager.setCurrentItem(0, false);//禁掉滚动事件
            } else {
                viewPager.setCurrentItem(currIndex, true);
            }
            mHanler.postDelayed(this, Time);


        }
    }


}
