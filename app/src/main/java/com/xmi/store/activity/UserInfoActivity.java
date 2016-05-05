package com.xmi.store.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmi.store.R;
import com.xmi.store.activity.base.BaseFragmentActivity;
import com.xmi.store.db.User;
import com.xmi.store.db.UserTableContract;
import com.xmi.store.db.XmiDao;
import com.xmi.store.tripartite.ClipPictureActivity;
import com.xmi.store.util.AppInfo;
import com.xmi.store.util.ImageUtils;
import com.xmi.store.util.SaveUtils;
import com.xmi.store.util.VolleyImageUtils;
import com.xmi.store.view.CircleImageView;
import com.xmi.store.view.CommonDialog;
import com.xmi.store.view.UserHeadDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: xiucui.yu
 * Date: 2016-04-25
 * Time: 11:53
 * FIXME
 */
public class UserInfoActivity extends BaseFragmentActivity {


    @Bind(R.id.iv_head_image)
    CircleImageView ivHeadImage;

    @Bind(R.id.tv_nickname)
    TextView tvNickname;

    @Bind(R.id.tv_username)
    TextView tvUsername;

    private XmiDao dao;
    private File takePhotoFile;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;

    private static final int IMAGE_CLIP_RESULT = 2;

    private static final int RESULT_LOAD_IMAGE = 3;


    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_layout);
        ButterKnife.bind(this);
        dao = new XmiDao(this);

        user = dao.find(SaveUtils.getString(this, UserTableContract.UserTable.user_id));
        initDate(user);

        listenLoginState();
    }

    private void initDate(User data) {
        if (TextUtils.isEmpty(data.getHeadUrl())) {
            ivHeadImage.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(this).load(data.getHeadUrl()).into(ivHeadImage);
        }
        tvNickname.setText(data.getNickname());
        tvUsername.setText(data.getUserName());
        ivHeadImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new CommonDialog(UserInfoActivity.this)
                        .addItem("相机", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getCamera();
                            }
                        })
                        .addItem("相册", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getPhotoAlbum();
                                //相册
                            }
                        }).show();
                return true;
            }
        });
        ivHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击显示大图片
                UserHeadDialog dialog = new UserHeadDialog(UserInfoActivity.this, user);
                dialog.show();
            }
        });

    }

    private void getCamera() {
        // 利用系统自带的相机应用:拍照
        takePhotoFile = ImageUtils.getImageFile();
        Uri imageFileUri = Uri.fromFile(takePhotoFile);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        // 此处这句intent的值设置关系到后面的onActivityResult中会进入那个分支，即关系到data是否为null，如果此处指定，则后来的data为null
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        overridePendingTransition(R.anim.tran_left_in, R.anim.tran_left_out);
    }


    private ContentObserver mContentObserver;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拍照
        if (CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            takePicResult();
        }
        //裁剪
        if (IMAGE_CLIP_RESULT == requestCode && data != null) {
            getPicClipResult(data);
        }
        //选择相册
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            getPhotoAlbumResult(data);

        }
    }

    private void getPhotoAlbumResult(Intent data) {

        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();


        Intent intent = new Intent(this,
                ClipPictureActivity.class);
        intent.putExtra(ClipPictureActivity.TAG_URL, picturePath);
        startActivityForResult(intent, IMAGE_CLIP_RESULT);
    }

    private void getPicClipResult(Intent data) {
        String stringExtra = data.getStringExtra(ClipPictureActivity.TAG_CLIPED_URL);
        user.setHeadUrl(stringExtra);
        dao.updata(user);
    }

    private void takePicResult() {
        //解决部分手机拍完照旋转的问题
        int bitmapDegree = ImageUtils.getBitmapDegree(takePhotoFile.getAbsolutePath());
        if (bitmapDegree != 0) {
            Bitmap scaledBitmap = VolleyImageUtils.getScaledBitmap(takePhotoFile, AppInfo.screenWidthForPortrait, AppInfo.screenStatusBarHeight);
            ImageUtils.rotateBitmapByDegree(scaledBitmap, bitmapDegree);

            try {
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(takePhotoFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        // 其次把文件插入到系统图库
        // Save the screenshot to the MediaStore
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.ImageColumns.DATA, takePhotoFile.getAbsolutePath());
        values.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/png");
        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        // 最后通知图库更新

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(takePhotoFile.getPath()))));


        Intent intent = new Intent(this,
                ClipPictureActivity.class);
        intent.putExtra(ClipPictureActivity.TAG_URL, takePhotoFile.getAbsolutePath());
        startActivityForResult(intent, IMAGE_CLIP_RESULT);
    }

    public void getPhotoAlbum() {

        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }


    private void listenLoginState() {

        mContentObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {

                if (UserTableContract.URLAndId.equals(uri)) {
                    User user = dao.find(SaveUtils.getString(UserInfoActivity.this, UserTableContract.UserTable.user_id));
                    if (user != null) {
                        initDate(user);
                    }
                }
            }
        };
        getContentResolver().registerContentObserver(UserTableContract.URLs, true
                , mContentObserver);
    }

}
