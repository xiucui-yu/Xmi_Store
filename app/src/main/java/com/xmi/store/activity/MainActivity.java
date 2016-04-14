package com.xmi.store.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xmi.store.R;
import com.xmi.store.activity.base.BaseFragmentActivity;
import com.xmi.store.tripartite.PagerSlidingTabStrip;
import com.xmi.store.util.TabFragmentFactory;
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
    private long oneOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
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
            return TabFragmentFactory.getTabFragemntInstance(position);
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


    @Override
    public void onBackPressed() {
        if (oneOnClick == 0) {
            oneOnClick = System.currentTimeMillis();
            Toast.makeText(this, "再按一次即退出程序", Toast.LENGTH_LONG).show();
        } else {
            long twoOnClick = System.currentTimeMillis();
            long porr = twoOnClick - oneOnClick;
            if (porr < 5000) {
                System.exit(0);
            } else {
                oneOnClick = System.currentTimeMillis();
                Toast.makeText(this, "再按一次即退出程序", Toast.LENGTH_LONG).show();
            }
        }
    }


}
