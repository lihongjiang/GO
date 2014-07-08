package com.netfuture.go.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneUtil {

	/**
	 * 调用系统发短信界面
	 * 
	 * @param activity
	 * @param phoneNumber
	 * @param smsContent
	 */
	public static void callMessage(Context activity, String phoneNumber,
			String smsContent) {
		if (phoneNumber == null || phoneNumber.length() < 4) {
			return;
		}
		Uri uri = Uri.parse("smsto:" + phoneNumber);
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		it.putExtra("sms_body", smsContent);
		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(it);

	}

	/**
	 * 调用系统打电话界面
	 * 
	 * @param mContext
	 * @param phoneNumber
	 */
	public static void callPhones(Context mContext, String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() < 1) {
			return;
		}
		Uri uri = Uri.parse("tel:" + phoneNumber);
		Intent intent = new Intent(Intent.ACTION_CALL, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}

}
