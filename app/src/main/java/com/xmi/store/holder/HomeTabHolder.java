package com.xmi.store.holder;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xmi.store.R;
import com.xmi.store.fragment.base.BaseFramgment;
import com.xmi.store.holder.base.BaseHolder;
import com.xmi.store.moudle.AppInfo;
import com.xmi.store.net.RequestUrlUtils;
import com.xmi.store.util.XgoFileUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-12
 * Time: 13:54
 * FIXME
 */
public class HomeTabHolder extends BaseHolder<AppInfo> {
    ImageView itemIcon;
    TextView itemTitle;
    RatingBar itemRating;
    TextView itemSize;
    TextView itemBottom;


    public HomeTabHolder(BaseFramgment mFragment) {
        super(mFragment);
    }


    @Override
    protected View initView() {
        convertView = View.inflate(mFragment.getActivity(), R.layout.holder_list_item, null);
        itemIcon = (ImageView) convertView.findViewById(R.id.item_icon);
        itemTitle = (TextView) convertView.findViewById(R.id.item_title);
        itemSize = (TextView) convertView.findViewById(R.id.item_size);
        itemBottom = (TextView) convertView.findViewById(R.id.item_bottom);
        itemRating = (RatingBar) convertView.findViewById(R.id.item_rating);
        return convertView;
    }

    @Override
    protected void initData(AppInfo data) {

        itemTitle.setText(data.getName());
        Glide.with(mFragment).load(RequestUrlUtils.getImageUrl(data.getIconUrl())).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_launcher).into(itemIcon);
        itemSize.setText(XgoFileUtils.getFormatSize(data.getSize()));
        itemBottom.setText(data.getDes());
        itemRating.setRating(data.getStars());
    }


}
