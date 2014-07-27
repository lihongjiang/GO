package com.netfuture.go.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ActivityUtils {

	public void startActivityByCleanTop(Context context, Class<Activity> da) {
		Intent intent = new Intent(context, da)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	public void startActivityForNewTask(Context context, Class<Activity> da) {
		Intent intent = new Intent(context, da)
				.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	// 从通知栏返回应用
	public PendingIntent startActivityByNotifi(Context context,
			Class<Activity> da) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setClass(context, da);// 主类
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		PendingIntent contextIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		return contextIntent;
	}

	// 从通知栏返回应用某个Activity,此Activity会更新
	public PendingIntent startActivity(Context context, Class<Activity> da) {
		Intent intent = new Intent(context, da);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent contextIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		return contextIntent;
	}
	public void startActivity2(Context context, Class<Activity> da) {
		Intent intent = new Intent(context,da);  
		context.startActivity(intent);
	}

	public void startActivity1(Context context, Class<Activity> da) {
		Intent intent = new Intent(context,da);  
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		context.startActivity(intent);
	}

	public void startActivity3(Context context, Class<Activity> da) {
		Intent intent = new Intent(context,da);  
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //跟activity启动模式有关,是否重复利用
		context.startActivity(intent);
	}

	public void startActivity4(Context context, Class<Activity> da) {
		Intent intent = new Intent(context,da);  
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);  //重复利用
		context.startActivity(intent);
	}
	
	public void startActivity5(Context context, Class<Activity> da) {
		Intent intent = new Intent(context,da);  
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);  //设置Activity在从后台出来的时候清楚
		context.startActivity(intent);
	}
	

}
