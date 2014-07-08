package com.netfuture.go.http.json.fastjson;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastjsonUtil {
	// 把JSON文本parse为JSONObject或者JSONArray
	public static JSONObject parseJSONObject(String text) {
		return (JSONObject) JSON.parse(text);
	}

	// 把JSON文本parse为JSONObject或者JSONArray
	public static JSONArray parseJSONArray(String text) {
		return (JSONArray) JSON.parse(text);
	}

	// 把JSON文本parse成JSONObject
	public static JSONObject parseObject(String text) {
		return JSON.parseObject(text);
	}

	// 把JSON文本parse为JavaBean
	public static <T> T parseObject(String text, Class<T> clazz) {
		return (T) JSON.parseObject(text);
	}

	// 把JSON文本parse成JSONArray
	public static JSONArray parseArray(String text) {
		return JSON.parseArray(text);
	}

	// 把JSON文本parse成JavaBean集合
	public static <T> List parseArray(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	// 将JavaBean序列化为JSON文本
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	// 将JavaBean序列化为带格式的JSON文本
	public static String toJSONString(Object object, boolean prettyFormat) {
		return JSON.toJSONString(object, prettyFormat);
	}

	// 将JavaBean转换为JSONObject或者JSONArray。
	public static JSONObject toJSONObject(Object object) {
		return (JSONObject) JSON.toJSON(object);
	}

	// 将JavaBean转换为JSONObject或者JSONArray。
	public static JSONArray toJSONArray(Object object) {
		return (JSONArray) JSON.toJSON(object);
	}
}
