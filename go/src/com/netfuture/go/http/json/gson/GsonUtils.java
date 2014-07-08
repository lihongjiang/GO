package com.netfuture.go.http.json.gson;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author 李洪江
 * @description gson解析工具类
 * 
 */
public class GsonUtils {
	// Gson解析器
	public static Gson gson = new GsonBuilder()
			.enableComplexMapKeySerialization()
			.excludeFieldsWithoutExposeAnnotation()// 不导出实体中没有用@Expose注解的属性
			.serializeNulls()// 支持Map的key为复杂对象的形式
			.setDateFormat("yyyy-MM-dd HH:mm:ss") // 时间转化为特定格式
			.setPrettyPrinting().create();

	/**
	 * 从父JsonObject对象中获取子JsonObject对象
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static JsonObject getJsonObject(JsonObject obj, String key) {
		JsonElement element = obj.get(key);
		return element.getAsJsonObject();
	}

	/**
	 * 从父JsonObject对象中获取子JsonArray对象
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static JsonArray getJsonArray(JsonObject obj, String key) {
		JsonElement element = obj.get(key);
		return element.getAsJsonArray();
	}

	/**
	 * 得到根JsonObject
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static JsonObject getRootJsonObject(String jsonStr) {
		JsonObject obj = new JsonParser().parse(jsonStr).getAsJsonObject();
		return obj;
	}

	/**
	 * 得到根JsonArray
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static JsonArray getRootJsonArray(String jsonStr) {
		JsonArray obj = new JsonParser().parse(jsonStr).getAsJsonArray();
		return obj;
	}

	/**
	 * 从JsonObject对象中获取键值元素
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static JsonElement getKeyValue(JsonObject obj, String key) {
		JsonElement element = obj.get(key);
		return element;
	}

	/**
	 * 从JsonArrary数组到List<JsonObject>
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static List<JsonObject> JsonArrayToList(JsonArray jsonArray) {
		List<JsonObject> obj = new ArrayList<JsonObject>();
		for (int i = 0; i < jsonArray.size(); i++) {
			obj.add(jsonArray.get(i).getAsJsonObject());
		}
		return obj;
	}

	public Gson getGson() {
		return gson;
	}

	/**
	 * 带泛型的List<Bean>转化为json
	 * 
	 * @param json
	 * @return
	 */
	public static <T> String toJsonString(List<T> json) {
		return gson.toJson(json);
	}

	/**
	 * 带泛型的Bean转化为json
	 * 
	 * @param json
	 * @return
	 */
	public static <T> String toJsonString(T json) {
		return gson.toJson(json);
	}

	/**
	 * bean转化为jsonObject
	 * 
	 * @param json
	 * @return
	 */
	public static <T> JsonObject beanToJsonObject(T json) {
		return getRootJsonObject(toJsonString(json));
	}

	/**
	 * List<bean>转化为jsonArrary
	 * 
	 * @param json
	 * @return
	 */
	public static <T> JsonArray ListToJsonArrary(List<T> json) {
		return getRootJsonArray(toJsonString(json));
	}

	/**
	 * 转换JSONObject对象中的JSONObject为Bean
	 * 
	 * @param obj
	 * @param key
	 * @param type
	 * @return
	 */
	public static <T> T toBean(JsonObject obj, String key, Class<T> type) {
		return gson.fromJson(obj.get(key).toString(), type);
	}

	/**
	 * 转换JSONObject对象中的JsonArray为List<Bean>
	 * 
	 * @param obj
	 * @param key
	 * @param type
	 * @return
	 */
	public static <T> List<T> toList(JsonObject obj, String key, Class<T> type) {
		return JsonArrayToListBean(obj.get(key).getAsJsonArray(), type);
	}

	/**
	 * 直接把JsonArrary对象转化为List<Bean>
	 * 
	 * @param jsonArray
	 * @param type
	 * @return
	 */
	public static <T> List<T> JsonArrayToListBean(JsonArray jsonArray,
			Class<T> type) {
		List<T> obj = new ArrayList<T>();
		for (int i = 0; i < jsonArray.size(); i++) {
			obj.add(gson.fromJson(jsonArray.get(i).toString(), type));
		}
		return obj;
	}

	/**
	 * 直接把json对象转化为bean
	 * 
	 * @param jsonObject
	 * @param type
	 * @return
	 */
	public static <T> T JsonObjectToBean(JsonObject jsonObject, Class<T> type) {
		return gson.fromJson(jsonObject.toString(), type);
	}

	/**
	 * 检查对象是否存在
	 * 
	 * @param root
	 * @param key
	 * @return
	 */
	public static boolean checkJsonObejct(JsonObject root, String key) {
		if ((!getKeyValue(root, key).isJsonNull())
				&& getKeyValue(root, key).isJsonObject()) {
			return true;
		}
		return false;

	}

	/**
	 * 检查数组是否存在
	 * 
	 * @param root
	 * @param key
	 * @return
	 */
	public static boolean checkJsonArray(JsonObject root, String key) {
		if ((!getKeyValue(root, key).isJsonNull())
				&& getKeyValue(root, key).isJsonArray()) {
			return true;
		}
		return false;
	}

	/**
	 * 从流中解析json数组
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static JsonArray getRootJsonArrayByInputStream(Reader jsonStr) {
		JsonArray obj = new JsonParser().parse(jsonStr).getAsJsonArray();
		return obj;
	}
}
