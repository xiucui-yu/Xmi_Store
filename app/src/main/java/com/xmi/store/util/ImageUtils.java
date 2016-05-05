package com.xmi.store.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: xiucui.yu
 * Date: 2016-05-03
 * Time: 16:26
 * FIXME
 */
public class ImageUtils {

    public static File getImageFile() {
        String dir = FileUtils.getPicDir();
        String yyyyMMdd_hHmmssSSS = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        return new File(dir + File.separator + "img" + yyyyMMdd_hHmmssSSS + ".jpg");
    }


    /* ------------------------------------------------*/
    /**
     *
     *�ڲ���Android�ֻ�����MT788��Note2���ϣ�ʹ��Camera�����Ժ󣬵õ�����Ƭ�ᱻ�Զ���ת��90�㡢180�㡢270�㣩��
     * �������ܲ�����Ԥ�ڡ���ϸ������һ�£���Ϊ��Ƭ�������Ǵ洢����ת��Ϣ�ģ�����Ҫ���������⣬������onActivityResult�����У�
     * ��ȡ����Ƭ���ݺ󣬶�ȡ������ת��Ϣ���������0��˵�������Ƭ�Ѿ�����ת���ˣ���ô��ʹ��android.graphics.Matrix����Ƭ��ת��ȥ���ɡ�
     *
     */
/* ------------------------------------------------*/

    /**
     * ��ȡͼƬ����ת�ĽǶ�
     *
     * @param path
     *            ͼƬ����·��
     * @return ͼƬ����ת�Ƕ�
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // ��ָ��·���¶�ȡͼƬ������ȡ��EXIF��Ϣ
            ExifInterface exifInterface = new ExifInterface(path);
            // ��ȡͼƬ����ת��Ϣ
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }



    /**
     * ��ͼƬ����ĳ���ǶȽ�����ת
     *
     * @param bm
     *            ��Ҫ��ת��ͼƬ
     * @param degree
     *            ��ת�Ƕ�
     * @return ��ת���ͼƬ
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // ������ת�Ƕȣ�������ת����
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // ��ԭʼͼƬ������ת���������ת�����õ��µ�ͼƬ
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }


}
