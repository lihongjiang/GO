package com.netfuture.go.utils;

public class FormMultiChoiceUtils {

	public static Integer getIndex(int[] array, String str) {
		for (int i = 0; i < array.length; i++) {
			if (str != null && (array[i] + "").equals(str)) {
				return i;
			}
		}
		return -1;//
	}

	/**
	 * 多选值显示出来
	 * 
	 * @param key  1,2,3
	 * @param list  ["测试","语文","数学"]
	 * @param value [1,2,3,4]
	 * @return
	 */
	public static String queryValue(String key, String[] list, int[] value) {
		if (key != null && key.length() > 0) {
			String[] s = key.split(",");
			String values = "";
			for (int i = 0; i < s.length; i++) {
				if (s[i].length() > 0) {
					values += list[getIndex(value, s[i])] + ",";
				}
			}
			return values.substring(0, values.length() - 1);
		}
		return "";
	}

	/**
	 * 多选值初始化
	 * 
	 * @param key
	 *            1,2,3
	 * @param values
	 *            [1,2,3,4,5,6,7,8]
	 * @return
	 */
	public static boolean[] dataJudge(String key, int[] values) {
		boolean[] b = new boolean[values.length];
		if (key != null && !"".equals(key)) {
			String[] s = key.split(",");
			for (int i = 0; i < s.length; i++) {
				String dis = getIndex(values, s[i].trim()) + "";
				if (dis != null) {
					b[Integer.valueOf(dis)] = true;
				}
			}
		}
		return b;
	}
}
