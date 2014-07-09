package com.netfuture.go.utils;


import android.util.Log;
/**
 * @author bslee
 * @description 应用调试管理
 * 
 */
public class LogUtils {

	public static  boolean debug = true;

	public static boolean isDebug() {
		return debug;
	}

	public static void Log(String data) {
		if (debug) {
			Log.d("调试",data);
		}
	}

}
