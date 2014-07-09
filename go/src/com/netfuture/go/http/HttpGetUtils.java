package com.netfuture.go.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.netfuture.go.utils.LogUtils;

import java.io.InputStream;

/**
 * @description GET请求管理工具类
 * @author bslee

 */

public class HttpGetUtils {

	// GET获取JSON数据
	public static String httpClientGetJson(String url) {
		LogUtils.Log("httpget:" + url);
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpEntity entity;
		String result = null;
		try {
			httpClient.getParams().setIntParameter(
					HttpConnectionParams.CONNECTION_TIMEOUT, 5000);
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = response.getEntity();
				result = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// GET获取流
	public static InputStream httpClientGetInputStream(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpEntity entity;
		InputStream result = null;
		try {
			httpClient.getParams().setIntParameter(
					HttpConnectionParams.CONNECTION_TIMEOUT, 5000);
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = response.getEntity();
				result = entity.getContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
