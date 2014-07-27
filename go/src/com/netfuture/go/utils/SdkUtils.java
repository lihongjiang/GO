package com.netfuture.go.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images;

public class SdkUtils {

	/**
	 * 判断是否安装腾讯、新浪等指定的分享应用
	 * 
	 * @param packageName
	 *            应用的包名
	 */
	public boolean checkInstallation(Context context, String packageName) {
		try {
			context.getPackageManager().getPackageInfo(packageName,
					PackageManager.GET_ACTIVITIES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	// 去Google Play下载应用
	public void goGooglePlay(Context context, String pagename) {
		Uri uri = Uri.parse("market://details?id=" + pagename);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(it);
	}

	public static final String SINA_WEIBO = "com.sina.weibo";
	public static final String QQ_WEIBO = "com.sina.weibo";
	public static final String WX = "com.sina.weibo";

	/**
	 * 分享文本，<span
	 * style="color: blue;">若想分享图片信息需要设置setType为“image/*”，传递一个类型为Uri的参数Intent
	 * .EXTRA_STREAM。</span>
	 * 
	 * @param context
	 * @param title
	 * @param text
	 */

	public static void shareText(Context context, String title, String text) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
		intent.putExtra(Intent.EXTRA_TEXT, text);
		intent.putExtra(Intent.EXTRA_TITLE, title);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, title));
	}

	/**
	 * 分享图片
	 * 
	 * @param context
	 * @param pagename
	 * @param file
	 */
	public void sharePic(Context context, String pagename, File file) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("image/*");
		shareIntent.setPackage(pagename);
		Uri uri = Uri.fromFile(file);
		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(shareIntent, "请选择"));
	}

	/**
	 * 分享多张照片
	 */
	private void sendMultiple(Context context,String text,String title) {
		Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		intent.setType("image/*");
		intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,
				getUriListForImages(context));
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
		intent.putExtra(Intent.EXTRA_TEXT, text);
		intent.putExtra(Intent.EXTRA_TITLE, title);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, "请选择"));
	}

	/**
	 * 设置需要分享的照片放入Uri类型的集合里
	 */
	private ArrayList<Uri> getUriListForImages(Context context) {
		ArrayList<Uri> myList = new ArrayList<Uri>();
		String imageDirectoryPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/DCIM/100ANDRO/";
		File imageDirectory = new File(imageDirectoryPath);
		String[] fileList = imageDirectory.list();
		if (fileList.length != 0) {
			for (int i = 0; i < 5; i++) {
				try {
					ContentValues values = new ContentValues(7);
					values.put(Images.Media.TITLE, fileList[i]);
					values.put(Images.Media.DISPLAY_NAME, fileList[i]);
					values.put(Images.Media.DATE_TAKEN, new Date().getTime());
					values.put(Images.Media.MIME_TYPE, "image/jpeg");
					values.put(Images.ImageColumns.BUCKET_ID,
							imageDirectoryPath.hashCode());
					values.put(Images.ImageColumns.BUCKET_DISPLAY_NAME,
							fileList[i]);
					values.put("_data", imageDirectoryPath + fileList[i]);
					ContentResolver contentResolver = context
							.getContentResolver();
					Uri uri = contentResolver.insert(
							Images.Media.EXTERNAL_CONTENT_URI, values);
					myList.add(uri);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return myList;
	}
}
