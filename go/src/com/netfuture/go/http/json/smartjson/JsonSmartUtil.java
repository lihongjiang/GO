package com.netfuture.go.http.json.smartjson;

import java.util.List;
import java.util.Map;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class JsonSmartUtil {
	// list,set,数组是[]
	public static String toJSONString(Object bean) {
		return JSONValue.toJSONString(bean);
	}

	public static <T> T parse(String json, Class<T> bean) {
		return JSONValue.parse(json, bean);
	}

	// 检测json结构是否对
	public static boolean isValidJsonStrict(String json) {
		return JSONValue.isValidJsonStrict(json);
	}

	public static String toJSONString(List<Object> json) {
		return JSONArray.toJSONString(json);
	}

	public static String toJSONString(Map<String, Object> json) {
		return JSONObject.toJSONString(json);
	}
}
