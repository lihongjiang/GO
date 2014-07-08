package com.netfuture.go.http;

import org.apache.http.NameValuePair;

import java.util.List;



/**
 * @author 李洪江
 * @description 请求准备上传数据
 * 
 */
public abstract class RequestParam {
	public abstract List<NameValuePair> getParams();

}
