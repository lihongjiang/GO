package com.netfuture.go.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

public class NetUtil {

	// TODO 判断网络连接是否打开,包括移动数据连接
	public static boolean isNetworkAvailable(Context context) {
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity
						.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (info != null && info.isConnectedOrConnecting()) {
					LogUtils.Log("当前网络:" + info.getTypeName());
					LogUtils.Log("WIFI连接开启");
					return true;
				} else {
					LogUtils.Log("WIFI连接未开启");
				}
				info = connectivity
						.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (info != null
						&& info.isConnectedOrConnecting()
						&& telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED) {
					LogUtils.Log("2G数据连接开启");
					return true;
				} else {
					LogUtils.Log("2G数据连接未开启");
				}
			}

		} catch (Exception e) {
			return false;
		}
		return false;

	}

	// TOOD GPS是否打开
	public static boolean isGpsEnabled(Context context) {
		LocationManager lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	// TOOD 检测当前打开的网络类型是否WIFI
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	// TOOD 检测当前打开的网络类型是否3G
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	/**
	 * 只是判断WIFI
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailableWiFi(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			return true;
		return false;

	}

	// IP地址校验
	public static boolean isIP(String ip) {
		Pattern pattern = Pattern
				.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	// IP转化成int数字
	public static int ipToInt(String addr) {
		String[] addrArray = addr.split("\\.");
		int num = 0;
		for (int i = 0; i < addrArray.length; i++) {
			int power = 3 - i;
			num += ((Integer.parseInt(addrArray[i]) % 256 * Math
					.pow(256, power)));
		}
		return num;
	}

}
