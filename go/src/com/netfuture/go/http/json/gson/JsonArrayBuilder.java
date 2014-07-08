package com.netfuture.go.http.json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



/**
 * @author 李洪江
 * @description 构建JsonArray对象
 * 
 */
public class JsonArrayBuilder {
	private Gson gson = new Gson();
	private JsonArray jsonObject = new JsonArray();

	public JsonArrayBuilder() {
		super();
	}

	public JsonArray get() {
		return jsonObject;
	}

	public JsonArrayBuilder append(JsonObject value) {
		jsonObject.add(value);
		return this;
	}

	@Override
	public String toString() {
		return gson.toJson(jsonObject);
	}
}