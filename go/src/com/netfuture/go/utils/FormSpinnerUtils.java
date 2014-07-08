package com.netfuture.go.utils;

import android.content.Context;

public class FormSpinnerUtils {

	{
		// 必填项string[]=new String[]{"无","查看"};
		// 可填项string[]=new String[]{"查看"};
	}

	/**
	 * 根据是否必填和值,得到在数组中的下标映射关系
	 * 
	 * @param array
	 * @param str
	 * @param defaultIndex
	 * @return
	 */
	public static Integer getIndex(int[] array, String str,
			Integer defaultIndex, boolean isbi) {
		for (int i = 0; i < array.length; i++) {
			if (str != null && (array[i] + "").equals(str)) {
				return i;
			}
		}
		if (isbi) {
			return defaultIndex;
		}
		return 0;
	}

	public static Integer getIndex(Context context, int resId, String vaule,
			Integer defaultIndex, boolean isbi) {
		return getIndex(context.getResources().getIntArray(resId), vaule,
				defaultIndex, isbi);
	}

	/**
	 * 是否必填来构造字符串数组,用于适配器初始化
	 * 
	 * @param array
	 * @param isbi
	 * @return
	 */
	public static String[] setStringSingleChoice(String[] array, boolean isbi) {
		String[] temp = new String[array.length + 1];
		if (isbi) {
			return array;
		} else {
			temp[0] = "无";
			for (int i = 1; i < temp.length; i++) {
				temp[i] = array[i - 1];
			}
		}
		return temp;

	}

	/**
	 * 得到下标对应的数字值,根据是否必填,在spinner事件向对象里设置值调用
	 * 
	 * @param context
	 * @param resId
	 * @param index
	 * @param isbi
	 * @return
	 */
	public static Integer setIndex(Context context, int resId, int index,
			boolean isbi) {
		if (isbi) {
			return context.getResources().getIntArray(resId)[index];
		} else {
			if (index == 0) {
				return null;// 保存null
			}
			// 第一个值无效,从n-1开始,否则数组越界
			return context.getResources().getIntArray(resId)[index - 1];//
		}
	}

	/**
	 * 是否必填初始化spinner值
	 * 
	 * @param context
	 * @param resId
	 * @param index
	 * @param defaultIndex
	 * @param isbi
	 * @return
	 */
	public static Integer setInitSpinnerIndex(Context context, int resId,
			String index, Integer defaultIndex, boolean isbi) {
		if (isbi) {
			if (index == null) {
				return defaultIndex;
			}
			return Integer.parseInt(index);
		} else {
			if (index == null) {
				return 0;
			}
			return getIndex(context.getResources().getIntArray(resId), index,
					defaultIndex, isbi) + 1;
		}
	}

	/**
	 * 是否必填初始化spinner值
	 * 
	 * @param context
	 * @param resId
	 * @param index
	 * @param defaultIndex
	 * @param isbi
	 * @return
	 */
	public static String setIndexToString(Context context, int resId,
			String index, Integer defaultIndex, boolean isbi, int strId) {

		String[] str = context.getResources().getStringArray(strId);

		if (isbi) {
			if (index == null) {
				return str[defaultIndex];
			}
			return str[Integer.parseInt(index)];
		} else {
			if (index == null) {
				return "无";
			}
			return str[getIndex(context.getResources().getIntArray(resId),
					index, defaultIndex, isbi)];
		}
	}

}
