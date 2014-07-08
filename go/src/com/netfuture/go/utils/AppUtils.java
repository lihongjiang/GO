package com.netfuture.go.utils;

import java.io.File;

import java.io.FileFilter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * @author 李洪江
 * @description app工具类
 * 
 */
public class AppUtils {

	// TODO 得到软件版本号
	public static int getVerCode(Context c) {
		int verCode = -1;
		try {
			String packageName = c.getPackageName();
			verCode = c.getPackageManager().getPackageInfo(packageName, 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
		}
		return verCode;
	}

	// TODO 得到软件显示版本信息
	public static String getVerName(Context c) {
		String verName = "";
		try {
			String packageName = c.getPackageName();
			verName = c.getPackageManager().getPackageInfo(packageName, 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
		}
		return verName;
	}

	// TODO 安装apk
	public static void installApk(Context context, File file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	// TODO 安装apk
	public static void installApk(Context context, Uri file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(file, "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	// TODO 卸载apk
	public static void uninstallApk(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		Uri packageURI = Uri.parse("package:" + packageName);
		intent.setData(packageURI);
		context.startActivity(intent);
	}

	// TODO 检测服务是否运行
	public static boolean isServiceRunning(Context ctx, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) ctx
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> servicesList = activityManager
				.getRunningServices(Integer.MAX_VALUE);
		Iterator<RunningServiceInfo> l = servicesList.iterator();
		while (l.hasNext()) {
			RunningServiceInfo si = (RunningServiceInfo) l.next();
			if (className.equals(si.service.getClassName())) {
				isRunning = true;
			}
		}
		return isRunning;
	}

	// TODO 停止运行服务
	public static boolean stopRunningService(Context ctx, String className) {
		Intent intent_service = null;
		boolean ret = false;
		try {
			intent_service = new Intent(ctx, Class.forName(className));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (intent_service != null) {
			ret = ctx.stopService(intent_service);
		}
		return ret;
	}

	// TODO 得到CPU核心数
	public static int getNumCores() {
		try {
			File dir = new File("/sys/devices/system/cpu/");
			File[] files = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (Pattern.matches("cpu[0-9]", pathname.getName())) {
						return true;
					}
					return false;
				}
			});
			return files.length;
		} catch (Exception e) {
			return 1;
		}
	}

}
