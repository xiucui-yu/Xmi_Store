package com.xmi.store.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.media.Image;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.moudle.TopPicInfo;
import com.xmi.store.net.RequestUrlUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * User: xiucui.yu
 * Date: 2016-04-12
 * Time: 18:23
 * FIXME
 */
public class HomeHeaderAdapter extends PagerAdapter {
    private List<TopPicInfo> mDData;


    private LinkedList<ImageView> mCacheList = new LinkedList<>();


    private BaseFramgment mFragment;

    public HomeHeaderAdapter(List<TopPicInfo> lists, BaseFramgment mFragment) {
        this.mFragment = mFragment;
        if (mDData == null) {
            mDData = new LinkedList<TopPicInfo>();
        }
        if (lists != null) {
            mDData.addAll(lists);
        }
    }

    public void setData(List<TopPicInfo> lists) {
        if (mDData == null) {
            mDData = new LinkedList<TopPicInfo>();
        }
        mDData.clear();
        if (lists != null) {
            mDData.addAll(lists);
        }
        notifyDataSetChanged();

    }


    /// 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量

    @Override

    public int getCount() {

        return mDData.size();

    }


// 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可

    @Override

    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;

    }


// PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁

    @Override

    public void destroyItem(ViewGroup view, int position, Object object) {

        view.removeView((View) object);

        mCacheList.add((ImageView) object);

    }


// 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可

    @Override

    public Object instantiateItem(ViewGroup view, int position) {
        ImageView mImage = null;

        String picUrl = mDData.get(position).getPicUrl();
        if (mCacheList.size() > 0) {
            mImage = mCacheList.remove(0);
        } else {
            mImage = new ImageView(mFragment.getActivity());
            mImage.setLayoutParams(new ViewPager.LayoutParams());
            mImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }
        Glide.with(mFragment)
                .load(RequestUrlUtils.getImageUrl(picUrl))
                //淡入淡出动画
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.default_image)
                .into(mImage);
        view.addView(mImage);
        return mImage;

    }

}
