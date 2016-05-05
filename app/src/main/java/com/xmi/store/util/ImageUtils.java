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
     *在部分Android手机（如MT788、Note2）上，使用Camera拍照以后，得到的照片会被自动旋转（90°、180°、270°），
     * 这个情况很不符合预期。仔细分析了一下，因为照片属性中是存储了旋转信息的，所以要解决这个问题，可以在onActivityResult方法中，
     * 获取到照片数据后，读取它的旋转信息，如果不是0，说明这个照片已经被旋转过了，那么再使用android.graphics.Matrix将照片旋转回去即可。
     *
     */
/* ------------------------------------------------*/

    /**
     * 读取图片的旋转的角度
     *
     * @param path
     *            图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
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
     * 将图片按照某个角度进行旋转
     *
     * @param bm
     *            需要旋转的图片
     * @param degree
     *            旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
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
