package com.xmi.store.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmi.store.R;
import com.xmi.store.activity.base.BaseFragmentActivity;
import com.xmi.store.fragment.SuperAwesomeCardFragment;
import com.xmi.store.tripartite.PagerSlidingTabStrip;
import com.xmi.store.tripartite.SystemBarTintManager;
import com.xmi.store.util.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseFragmentActivity {
    @Bind(R.id.iv_info)
    ImageView ivInfo;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.iv_tab_menu)
    ImageView ivTabMenu;
    @Bind(R.id.ll_tab_menu)
    LinearLayout llTabMenu;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //沉浸式activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.price_color);//通知栏所需颜色

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void init() {
        ButterKnife.bind(this);
        MyFragmentViewpager viewpager = new MyFragmentViewpager(getSupportFragmentManager());
        pager.setAdapter(viewpager);
        tabs.setTextSize(UIUtils.sp2px(15));
        tabs.setTextColorResource(R.color.text_color_black);
        tabs.setViewPager(pager);
        tabs.setIndicatorColorResource(R.color.text_color_blue);
        tabs.setCurrentItem(0);
    }


    private class MyFragmentViewpager extends FragmentPagerAdapter {
        private final String[] TITLES = {"Home", "Apps", "Game", "Topics", "Classify", " Fiery", "Welfare"};

        public MyFragmentViewpager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }
}
