package com.xmi.store.util;

import com.xmi.store.fragment.AppTabFragment;
import com.xmi.store.fragment.ClassifyTabFragment;
import com.xmi.store.fragment.GameTabFragment;
import com.xmi.store.fragment.HomeTabFragment;
import com.xmi.store.fragment.HotTabFragment;
import com.xmi.store.fragment.SubjectTabFragment;
import com.xmi.store.fragment.WelfareTabFrahment;
import com.xmi.store.fragment.base.BaseFramgment;

import java.util.HashMap;
import java.util.Map;

/**
 * User: xiucui.yu
 * Date: 2016-04-07
 * Time: 10:23
 * FIXME
 */
public class TabFragmentFactory {
    /**
     * 首页
     */
    public static final int TAB_ID_HOME = 0;
    /**
     * 应用
     */
    public static final int TAB_ID_APP = 1;
    /**
     * 游戏
     */
    public static final int TAB_ID_GAME = 2;
    /**
     * 专题
     */
    public static final int TAB_ID_SUBJECT = 3;
    /**
     * 分类
     */
    public static final int TAB_ID_CLASSIFT = 4;
    /**
     * 火热
     */
    public static final int TAB_ID_HOT = 5;
    /**
     * 福利
     */
    public static final int TAB_ID_WELFARE = 6;
    //保证只有一个对象
    private static Map<Integer, BaseFramgment> mTabFragments = new HashMap<Integer, BaseFramgment>();

    public static BaseFramgment getTabFragemntInstance(int tabId) {
        BaseFramgment initBaseFramgment = mTabFragments.get(tabId);
        if (initBaseFramgment == null) {
            switch (tabId) {
                case TAB_ID_HOME:
                    initBaseFramgment = new HomeTabFragment();
                    break;
                case TAB_ID_APP:
                    initBaseFramgment = new AppTabFragment();
                    break;
                case TAB_ID_GAME:
                    initBaseFramgment = new GameTabFragment();
                    break;
                case TAB_ID_SUBJECT:
                    initBaseFramgment = new SubjectTabFragment();
                    break;
                case TAB_ID_CLASSIFT:
                    initBaseFramgment = new ClassifyTabFragment();
                    break;
                case TAB_ID_HOT:
                    initBaseFramgment = new HotTabFragment();
                    break;
                case TAB_ID_WELFARE:
                    initBaseFramgment = new WelfareTabFrahment();
                    break;
            }
            mTabFragments.put(tabId, initBaseFramgment);
        }

        return initBaseFramgment;
    }

}
