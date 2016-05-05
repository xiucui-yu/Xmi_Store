package com.xmi.store.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.xmi.store.R;
import com.xmi.store.colorful.Colorful;
import com.xmi.store.colorful.setter.ViewGroupSetter;
import com.xmi.store.util.ImageResource;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * User: xiucui.yu
 * Date: 2016-04-06
 * Time: 18:23
 * FIXME
 */
public class WelfareTabFrahment extends Fragment {


    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @Bind(R.id.btn)
    Button btn;

    Colorful mColorful;
    boolean isNight = false;

    private ImageResource mImageRes;

    private MyAdapter mAdapter;


    private LinearLayoutManager mLayoutManager;

    private View mMainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = View.inflate(getActivity(), R.layout.fragment_wekfare_layout, null);
        ButterKnife.bind(this, mMainView);
        mImageRes = ImageResource.getImageResource();
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeThemeWithColorful();
            }
        });

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        myRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        mAdapter = new MyAdapter(mImageRes.getIconBitmap());
        myRecyclerView.setAdapter(mAdapter);

        setupColorful();


        return mMainView;


    }


    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 设置各个视图与颜色属性的关联
     */
    private void setupColorful() {
        ViewGroupSetter recyclerViewSetter = new ViewGroupSetter(myRecyclerView,
                0);
        recyclerViewSetter.childViewBgColor(R.id.imageView,
                R.attr.text_color);

        mColorful = new Colorful.Builder(this)
                .backgroundColor(R.id.root_view, R.attr.root_view_bg)
                        // 设置view的背景图片
                .backgroundColor(R.id.btn, R.attr.btn_bg)

                .setter(recyclerViewSetter) // 手动设置setter
                .create(); // 设置文本颜色
    }

    private void changeThemeWithColorful() {
        if (!isNight) {
            mColorful.setTheme(R.style.DayTheme);
        } else {
            mColorful.setTheme(R.style.NightTheme);
        }
        isNight = !isNight;
    }


}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<Bitmap> bitmapResource;

    public MyAdapter(List<Bitmap> bitmapResource) {
        this.bitmapResource = bitmapResource;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cls, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.mImageView.setImageBitmap(bitmapResource.get(i));
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return bitmapResource.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }


}