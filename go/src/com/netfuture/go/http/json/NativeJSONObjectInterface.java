package com.netfuture.go.http.json;

import org.json.JSONObject;

public interface NativeJSONObjectInterface<T> {

	public JSONObject toJSONObject(T obj);

	public T to(JSONObject obj);
}
