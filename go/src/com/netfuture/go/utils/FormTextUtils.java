package com.netfuture.go.utils;

import android.widget.EditText;
import android.widget.TextView;

public class FormTextUtils {
	/**
	 * 空检查
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean CheckStrNull(String obj) {
		return (obj == null) || (obj.trim().length() == 0);
	}

	public static boolean CheckNull(EditText edittext) {
		String obj = edittext.getText().toString().trim();
		return CheckStrNull(obj);
	}

	public static boolean CheckNull(TextView textview) {
		String obj = textview.getText().toString().trim();
		return CheckStrNull(obj);
	}

	/**
	 * 字符串设置缺省值,给EditText控件设置值
	 * 
	 * @param obj
	 * @param defaulevalue
	 * @return
	 */
	public static String CheckStrNullSetWithDefaultVaule(String obj,
			String defaulevalue) {
		return CheckStrNull(obj) ? defaulevalue : obj;
	}

	/**
	 * 从控件EditText上获取值
	 * 
	 * @param obj
	 * @param defauleVaule
	 * @return
	 */
	public static Integer CheckNullGetInt(EditText obj, Integer defauleVaule) {
		return CheckNull(obj) ? Integer.parseInt(obj.getText().toString()
				.trim()) : defauleVaule;
	}

	public static Long CheckNullGetLong(EditText obj, Long defauleVaule) {
		return CheckNull(obj) ? Long.decode(obj.getText().toString().trim())
				: defauleVaule;
	}

	public static Double CheckNullGetDouble(EditText obj, Double defauleVaule) {
		return CheckNull(obj) ? Double.parseDouble(obj.getText().toString()
				.trim()) : defauleVaule;
	}

	public static String CheckNullGetString(EditText obj, String defauleVaule) {
		return CheckNull(obj) ? obj.getText().toString().trim() : defauleVaule;
	}

	public static Integer CheckNullGetInt(TextView obj, Integer defauleVaule) {
		return CheckNull(obj) ? Integer.parseInt(obj.getText().toString()
				.trim()) : defauleVaule;
	}

	public static Long CheckNullGetLong(TextView obj, Long defauleVaule) {
		return CheckNull(obj) ? Long.decode(obj.getText().toString().trim())
				: defauleVaule;
	}

	public static Double CheckNullGetDouble(TextView obj, Double defauleVaule) {
		return CheckNull(obj) ? Double.parseDouble(obj.getText().toString()
				.trim()) : defauleVaule;
	}

	public static String CheckNullGetString(TextView obj, String defauleVaule) {
		return CheckNull(obj) ? obj.getText().toString().trim() : defauleVaule;
	}
}
