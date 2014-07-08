package com.netfuture.go.http;


/**
 * @author 李洪江
 * @description 响应处理类 对返回数据进行封装和处理
 * 
 */
public class ResponseBean {
	/**
	 * 响应成功,这里放置json数据
	 */
	public String response = null;
	/**
	 * 响应码
	 * <p>
	 * 参考{HttpStatus}类
	 * </p>
	 * 
	 */
	public int responseCode = 0;

	public ResponseBean() {
		super();
	}

	/**
	 * 
	 * @param response
	 *            传入请求结果
	 */
	public ResponseBean(String response) {
	}
}