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


    /// ��ȡҪ�����Ŀؼ��������������������Ի����Ĺ����Ϊ������ô�����Ӧ����չʾ�Ĺ��ͼƬ��ImageView����

    @Override

    public int getCount() {

        return mDData.size();

    }


// ���ж���ʾ���Ƿ���ͬһ��ͼƬ���������ǽ�����������ȽϷ��ؼ���

    @Override

    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;

    }


// PagerAdapterֻ��������Ҫ��ʾ��ͼƬ�����������ͼƬ�����˻���ķ�Χ���ͻ���������������ͼƬ����

    @Override

    public void destroyItem(ViewGroup view, int position, Object object) {

        view.removeView((View) object);

        mCacheList.add((ImageView) object);

    }


// ��Ҫ��ʾ��ͼƬ���Խ��л����ʱ�򣬻�����������������ʾͼƬ�ĳ�ʼ�������ǽ�Ҫ��ʾ��ImageView���뵽ViewGroup�У�Ȼ����Ϊ����ֵ���ؼ���

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
        Glide.with(mFragment).load(RequestUrlUtils.getImageUrl(picUrl)).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.default_image).into(mImage);
        view.addView(mImage);
        return mImage;

    }

}
