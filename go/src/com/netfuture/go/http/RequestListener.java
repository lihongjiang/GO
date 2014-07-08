package com.netfuture.go.http;


/**
 * @author 李洪江
 * @description 回调基础类 配合handler进行界面更新,下面两个方法在线程中执行
 * 
 */
public abstract class RequestListener<T extends ResponseBean> {
	// TODO 在请求数据前处理业务
	public abstract void onStart();

	// TODO 请求数据完成处理业务
	public abstract void onComplete(T bean);
}
