package com.xmi.store.tripartite;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmi.store.R;
import com.xmi.store.util.FileUtils;
import com.xmi.store.view.ClipView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;




/**
 * ����˼���ǣ���ȡ��Ļ�Ľ�ͼ��Ȼ���ȡ���ο������ͼƬ
 */
public class ClipPictureActivity extends Activity implements OnTouchListener,
		OnClickListener
{
	public static final String TAG_URL = "iamge_url";
	public static final String TAG_CLIPED_URL = "clip_iamge_url";

	//private BitmapUtils mBitmapUtils;
	private ImageView srcPic;
	private ClipView clipview;

	int statusBarHeight = 0;
	int titleBarHeight = 0;
	private String mImageUrl;

	// These matrices will be used to move and zoom image
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();

	// We can be in one of these 3 states
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	private TextView tv_bottom_ok;
	int mode = NONE;

	// Remember some things for zooming
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_clip_photo);

		//mBitmapUtils = new BitmapUtils(this);
		mImageUrl = getIntent().getStringExtra(TAG_URL);

		srcPic = (ImageView) findViewById(R.id.src_pic);
		tv_bottom_ok =(TextView) findViewById(R.id.tv_bottom_ok);
		Glide.with(this).load(mImageUrl).into(srcPic);
		//mBitmapUtils.display(srcPic, mImageUrl);

		srcPic.setOnTouchListener(this);
		tv_bottom_ok.setOnClickListener(this);
	}

	/*����ʵ���˶�㴥���Ŵ���С���͵����ƶ�ͼƬ�Ĺ��ܣ��ο�����̳�Ĵ���*/
	public boolean onTouch(View v, MotionEvent event)
	{
		ImageView view = (ImageView) v;
		// Handle touch events here...
		switch (event.getAction() & MotionEvent.ACTION_MASK)
			{
			case MotionEvent.ACTION_DOWN:
				savedMatrix.set(matrix);
				// �O�ó�ʼ�cλ��
				start.set(event.getX(), event.getY());
				mode = DRAG;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				oldDist = spacing(event);
				if (oldDist > 10f)
				{
					savedMatrix.set(matrix);
					midPoint(mid, event);
					mode = ZOOM;
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				mode = NONE;
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == DRAG)
				{
					// ...
					matrix.set(savedMatrix);
					matrix.postTranslate(event.getX() - start.x, event.getY()
							- start.y);
				} else if (mode == ZOOM)
				{
					float newDist = spacing(event);
					if (newDist > 10f)
					{
						matrix.set(savedMatrix);
						float scale = newDist / oldDist;
						matrix.postScale(scale, scale, mid.x, mid.y);
					}
				}
				break;
			}

		view.setImageMatrix(matrix);
		return true; // indicate event was handled
	}

	/** Determine the space between the first two fingers */
	private float spacing(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return (int)Math.sqrt(x * x + y * y);
	}

	/** Calculate the mid point of the first two fingers */
	private void midPoint(PointF point, MotionEvent event)
	{
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	/*������*/
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.tv_bottom_ok:

			Bitmap fianBitmap = getBitmap();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			fianBitmap.compress(CompressFormat.JPEG, 100, baos);
			byte[] bitmapByte = baos.toByteArray();
			Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length);
			save(bitmap , new File(mImageUrl).getName());
		   break;

		default:
			break;
		}
	}

	public void save(Bitmap bitmap, String fileName) {

		//�ü�ͼƬ�����ַ
		File file = new File(FileUtils.getPicClipDir(), System.currentTimeMillis()+"_"+fileName);

		try {
			OutputStream out = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, out);
			out.close();

			Intent data = new Intent();
			data.putExtra(TAG_CLIPED_URL, file.getAbsolutePath());
			setResult(-1, data);
			back();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void back() {
		finish();
		overridePendingTransition(R.anim.tran_right_in , R.anim.tran_right_out);
	}

	@Override
	public void onBackPressed() {
		back();
	}

	/*��ȡ���������ڵĽ�ͼ*/
	private Bitmap getBitmap()
	{
		getBarHeight();
		Bitmap screenShoot = takeScreenShot();

		clipview = (ClipView)this.findViewById(R.id.clipview);
		Bitmap finalBitmap = Bitmap.createBitmap(screenShoot, clipview.getTopX(), clipview.getTopY() + titleBarHeight + statusBarHeight, clipview.VIEW_WIDTH, clipview.VIEW_HEIGHT);

		return finalBitmap;
	}

	private void getBarHeight()
	{
		// ��ȡ״̬���߶�
		Rect frame = new Rect();
		this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		statusBarHeight = frame.top;

		int contenttop = this.getWindow()
				.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		// statusBarHeight�����������״̬���ĸ߶�
		titleBarHeight = contenttop - statusBarHeight;

	}

	/**
	 *  ��ȡActivity�Ľ���
	 * @return
	 */
	private Bitmap takeScreenShot()
	{
		View view = this.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		return view.getDrawingCache();
	}

}