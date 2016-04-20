package com.xmi.store.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.xmi.store.R;
import com.xmi.store.XmiApp;

import java.util.ArrayList;
import java.util.List;


public class ImageResource {

    private List<Bitmap> bitmapResource;
    private static ImageResource imageResource = new ImageResource();

    private static int mPers[];
    private static int mAfters[];

    private ImageResource() {
        bitmapResource = new ArrayList<Bitmap>();
        mPers = new int[]{R.mipmap.pre1, R.mipmap.pre2, R.mipmap.pre3,
                R.mipmap.pre4, R.mipmap.pre5, R.mipmap.pre6, R.mipmap.pre7,
                R.mipmap.pre8, R.mipmap.pre9, R.mipmap.pre10, R.mipmap.pre11,
                R.mipmap.pre12, R.mipmap.pre13, R.mipmap.pre14, R.mipmap.pre15,
                R.mipmap.pre16, R.mipmap.pre17, R.mipmap.pre18
        };
        mAfters = new int[]{R.mipmap.after1, R.mipmap.after2, R.mipmap.after3,
                R.mipmap.after4, R.mipmap.after5, R.mipmap.after6,
                R.mipmap.after7, R.mipmap.after8, R.mipmap.after9,
                R.mipmap.after10, R.mipmap.after11, R.mipmap.after12,
                R.mipmap.after13, R.mipmap.after14, R.mipmap.after15,
                R.mipmap.after16, R.mipmap.after17, R.mipmap.after18
        };

        loadingBitmap(AppInfo.screenWidthForPortrait, 5);
    }

    public static ImageResource getImageResource() {
        return imageResource;
    }

    public Bitmap getAfterBitmap(Resources resources, int num) {//构造方法

        Bitmap bitmap = BitmapFactory.decodeResource(resources, mAfters[num]);

        return bitmap;
    }

    public Bitmap getPreBitmap(Resources resources, int num) {//构造方法

        Bitmap bitmap = BitmapFactory.decodeResource(resources, mPers[num]);//加载图片

        return bitmap;//返回BitMap类
    }

    public void loadingBitmap(int width, int num) {

        BitmapFactory.Options opts = new BitmapFactory.Options();//BitmapFactory.Options这个类
        //仅返回图片的 宽高  这样就不会占用太多的内存，也就不会那么频繁的发生OOM了。
        opts.inJustDecodeBounds = true;//该值设为true那么将不返回实际的bitmap对象，不给其分配内存空间但是可以得到一些解码边界信息即图片大小等信息。
        Bitmap temp = BitmapFactory.decodeResource(XmiApp.getApplicaion().getResources(), mPers[0], opts);//加载图片 缩放 mPers【】中第一位开始
        int radio = (int) Math.ceil(opts.outWidth / (width * 1.0 / num - 30));//向上取整 结果是7
        opts.inSampleSize = radio;//属性值inSampleSize表示缩略图大小为原始图片大小的几分之一
        if (null != temp) {
            temp.recycle();//回收
        }
        System.out.println(radio);

        opts.inJustDecodeBounds = false;//inJustDecodeBounds设为false，就可以根据已经得到的缩放比例得到自己想要的图片缩放图了。

        for (int i = 0; i < mPers.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(XmiApp.getApplicaion().getResources(), mPers[i], opts);//载入图片
            bitmapResource.add(bitmap);//循环添加到集合中
        }
    }

    public Bitmap getIconBitmap(int num) {
        return bitmapResource.get(num);
    }


    public List<Bitmap>  getIconBitmap() {
        return bitmapResource;
    }

    public int size() {
        return bitmapResource.size();
    }

    public int getProgress() {
        return (int) (100.0 * bitmapResource.size() / mPers.length);//得到文件当前大小
    }
}