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
import android.widget.ImageView;

import com.xmi.store.R;

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
    android.support.v7.widget.RecyclerView myRecyclerView;

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


        //����Ĭ�ϵ�����LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //�������ȷ��ÿ��item�ĸ߶��ǹ̶��ģ��������ѡ������������
        myRecyclerView.setHasFixedSize(true);
        //����������Adapter
        mAdapter = new MyAdapter(mImageRes.getIconBitmap());
        myRecyclerView.setAdapter(mAdapter);


        return mMainView;


    }


    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<Bitmap> bitmapResource;

    public MyAdapter(List<Bitmap> bitmapResource) {
        this.bitmapResource = bitmapResource;
    }

    //������View����LayoutManager������
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cls, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //�������������а󶨵Ĳ���
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.mImageView.setImageBitmap(bitmapResource.get(i));
    }

    //��ȡ���ݵ�����
    @Override
    public int getItemCount() {
        return bitmapResource.size();
    }

    //�Զ����ViewHolder������ÿ��Item�ĵ����н���Ԫ��
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}