package com.netfuture.go.utils;

import com.netfuture.go.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.database.Cursor;
import android.net.Uri;

/**
 * 需要权限:
 * 
 * {<uses-permission
 * android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
 * <uses-permission
 * android:name="com.android.launcher.permission.UNINSTALL_SHORTCUT" />}
 * 
 * @author Administrator
 * 
 */
public class ShortCutUtils {
	/**
	 * 检测是否存在快捷键
	 * 
	 * @param activity
	 * @return
	 */
	private boolean hasShortcut(Activity activity) {
		boolean isInstallShortcut = false;
		final ContentResolver cr = activity.getContentResolver();
		final String AUTHORITY = "com.android.launcher.settings";
		final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
				+ "/favorites?notify=true");
		Cursor c = cr.query(CONTENT_URI,
				new String[] { "title", "iconResource" }, "title=?",
				new String[] { activity.getString(R.string.app_name).trim() },
				null);
		if (c != null && c.getCount() > 0) {
			isInstallShortcut = true;
		}
		return isInstallShortcut;
	}

	/**
	 * 为程序创建桌面快捷方式
	 */
	
	private static void addShortcut(Activity activity) {
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		// 快捷方式的名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				activity.getString(R.string.app_name));
		shortcut.putExtra("duplicate", false); // 不允许重复创建
		Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
		shortcutIntent.setClassName(activity, activity.getClass().getName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		// 快捷方式的图标
		ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(
				activity, R.drawable.ic_launcher);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

		activity.sendBroadcast(shortcut);
	}

	/**
	 * 删除程序的快捷方式
	 * 
	 * @param activity
	 */
	private void delShortcut(Activity activity) {
		Intent shortcut = new Intent(
				"com.android.launcher.action.UNINSTALL_SHORTCUT");
		// 快捷方式的名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				activity.getString(R.string.app_name));
		String appClass = activity.getPackageName() + "."
				+ activity.getLocalClassName();
		ComponentName comp = new ComponentName(activity.getPackageName(),
				appClass);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(
				Intent.ACTION_MAIN).setComponent(comp));
		activity.sendBroadcast(shortcut);
	}
}
