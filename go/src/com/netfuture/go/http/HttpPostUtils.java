package com.netfuture.go.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.InputStreamEntity;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.netfuture.go.utils.LogUtils;

import java.io.ByteArrayInputStream;

import java.net.URI;

/**
 * @description POST请求管理工具类
 * @author li Hongjiang
 * @Date 2014-4-4
 * @since 3.0
 * 
 * @description 协议
 * @description 所有接口均基于http协议，上传、下载数据均为json格式， 所有接口均遵循以下规则：
 * @description 1 数据编码：UTF-8
 * @description 2 请求header参数：
 * @description authType：认证方式（1.系统内部用户认证；2.接口用户认证）
 * @description auth：认证码
 * @description 3 上传数据：
 * @description 数据:json字符流
 * @description 请求头信息： Content-Type：application/json; charset=UTF-8
 * @description 4 下载数据：
 * @description 数据:json字符流
 * @description 请求头信息： Accept：application/json
 */
public class HttpPostUtils {

	// 上传协议头
	public static final String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";
	public static final int TIMEOUT = 10000;

	// TODO 上传单个数据,下载单个数据
	public static String upLoadJSON(String buf, String url) {

		LogUtils.Log("上传地址:" + url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost http_post = new HttpPost();
		try {
			http_post.setURI(URI.create(url));
			// 设置内容长度和实体,或者设置块传输,不设置长度,保证持久连接有效
			byte[] data = buf.getBytes("UTF-8");
			InputStreamEntity in = new InputStreamEntity(
					new ByteArrayInputStream(data), data.length);
			in.setContentType("application/json;charset=UTF-8");
			http_post.setEntity(in);
			// 设置其它参数,如超时
			HttpParams params = new BasicHttpParams();
			params.setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,
					TIMEOUT);
			http_post.setParams(params);
			// 执行
			HttpResponse response = httpclient.execute(http_post);
			HttpEntity resEntity = response.getEntity();
			if (response != null) {
				// 服务器成功状态码200-299
				int code = response.getStatusLine().getStatusCode();
				switch (code) {
				case 200:
					LogUtils.Log("响应码:" + code + " 含义:成功");
					break;
				case 401:
					LogUtils.Log("响应码:" + code + " 用户认证失败");
					break;
				case 403:
					LogUtils.Log("响应码:" + code + " 用户授权失败");
					break;
				case 404:
					LogUtils.Log("响应码:" + code + " 请求地址错误");
					break;
				case 405:
					LogUtils.Log("响应码:" + code + " 请求方法错误");
					break;
				case 400:
					LogUtils.Log("响应码:" + code + " 请求数据格式错误");
					break;
				case 500:
					LogUtils.Log("响应码:" + code + "系统内部错误");
					break;
				}
				if (code == 200) {
					String response1 = EntityUtils.toString(resEntity);
					LogUtils.Log("返回数据:" + response1);
					return response1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上传json流数据
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static String upLoadJSON(URI url, String json) {
		LogUtils.Log("上传地址:" + url.toString());
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost http_post = new HttpPost();
		try {
			http_post.setURI(url);
			// 设置内容长度和实体,或者设置块传输,不设置长度,保证持久连接有效
			byte[] data = json.getBytes("UTF-8");
			InputStreamEntity in = new InputStreamEntity(
					new ByteArrayInputStream(data), data.length);
			in.setContentType("application/json;charset=UTF-8");
			http_post.setEntity(in);
			// 设置其它参数,如超时
			HttpParams params = new BasicHttpParams();
			params.setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,
					TIMEOUT);
			http_post.setParams(params);
			// 执行
			HttpResponse response = httpclient.execute(http_post);
			HttpEntity resEntity = response.getEntity();
			if (response != null) {
				// 服务器成功状态码200-299
				int code = response.getStatusLine().getStatusCode();
				LogUtils.Log("响应码:" + code);
				if (code == 200) {
					String response1 = EntityUtils.toString(resEntity);
					LogUtils.Log("返回数据:" + response1);
					return response1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上传json数据
	 * 
	 * @param buf
	 * @param url
	 * @param bean
	 */
	public static void upLoadJSON(String buf, String url, ResponseBean bean) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost http_post = new HttpPost();
		try {
			http_post.setURI(URI.create(url));
			// 设置内容长度和实体,或者设置块传输,不设置长度,保证持久连接有效
			byte[] data = buf.getBytes("UTF-8");
			InputStreamEntity in = new InputStreamEntity(
					new ByteArrayInputStream(data), data.length);
			in.setContentType("application/json;charset=UTF-8");
			http_post.setEntity(in);
			// 设置其它参数,如超时
			HttpParams params = new BasicHttpParams();
			params.setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,
					TIMEOUT);
			http_post.setHeader("Accept", "application/json");
			http_post.setParams(params);
			// 执行
			HttpResponse response = httpclient.execute(http_post);
			HttpEntity resEntity = response.getEntity();

			if (response != null) {
				// 服务器成功状态码200-299
				int code = response.getStatusLine().getStatusCode();

				if (code == 200) {
					bean.response = EntityUtils.toString(resEntity);
					bean.responseCode = code;
				} else {
					bean.responseCode = code;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.responseCode = -1;
	}

}
