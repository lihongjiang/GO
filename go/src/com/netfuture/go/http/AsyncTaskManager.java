package com.netfuture.go.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.netfuture.go.utils.AppUtils;

/**
 * @description异步任务管理类
 * @author LHJ
 * @version 3.0.0
 * 
 */
public class AsyncTaskManager {

	private ExecutorService mPool = null;
	private static AsyncTaskManager mTask;
	// 声明TASK函数类
	private AsyncTaskManager(int nThreads) {
		mPool = Executors.newFixedThreadPool(nThreads);
		// 初始化网络Task类
	}

	public static AsyncTaskManager getInstance() {
		if (mTask == null) {
			int nThreads = AppUtils.getNumCores();
			mTask = new AsyncTaskManager(nThreads * 2);
		}
		return mTask;
	}

}
