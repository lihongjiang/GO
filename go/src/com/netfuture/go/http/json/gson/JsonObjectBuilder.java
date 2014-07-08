package com.netfuture.go.http.json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author 李洪江
 * @description 构建JsonObject对象
 * 
 */
public class JsonObjectBuilder {

	private Gson gson = new Gson();
	private JsonObject jsonObject = new JsonObject();
    
	public JsonObjectBuilder() {
		super();
	}

	public JsonObject get() {
		return jsonObject;
	}

	// 基本的键值对属性
	public JsonObjectBuilder append(String key, String value) {
		jsonObject.addProperty(key, value);
		return this;
	}

	public JsonObjectBuilder append(String key, Number value) {
		jsonObject.addProperty(key, value);
		return this;
	}

	public JsonObjectBuilder append(String key, Boolean value) {
		jsonObject.addProperty(key, value);
		return this;
	}

	// 添加扩展键值对
	public JsonObjectBuilder append(String key, JsonElement value) {
		jsonObject.add(key, value);
		return this;
	}

	public JsonObjectBuilder append(String key, JsonObject value) {
		jsonObject.add(key, value);
		return this;
	}

	public JsonObjectBuilder append(String key, JsonArray value) {
		jsonObject.add(key, value);
		return this;
	}

	public JsonObjectBuilder append(String key, JsonNull value) {
		jsonObject.add(key, value);
		return this;
	}

	public JsonObjectBuilder append(String key, JsonPrimitive value) {
		jsonObject.add(key, value);
		return this;
	}

	@Override
	public String toString() {
		return gson.toJson(jsonObject);
	}
}