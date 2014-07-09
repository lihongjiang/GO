package com.netfuture.go.db.ormlite;

import java.sql.Savepoint;

import android.content.Context;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.netfuture.go.utils.LogUtils;

/**
 * @author bslee
 * @description 数据库事务操作类
 * 
 */
public class SqliteTransactionDao {
	public DatabaseHelper helper;
	public SqliteTransactionDao(Context context) {
		try {
			helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean doTransTask(TransCallBack callback) {
		AndroidDatabaseConnection connection = null;
		String pointName = "savepoint";
		Savepoint savepoint = null;
		try {
			connection = new AndroidDatabaseConnection(
					helper.getWritableDatabase(), true);
			connection.setAutoCommit(false);
			// 开始事务
			savepoint = connection.setSavePoint(pointName);
			callback.doTrans();
			// 提交事务
			connection.commit(savepoint);
			return true;
		} catch (Exception e) {
			LogUtils.Log("事务异常");
			try {
				connection.rollback(savepoint);
				LogUtils.Log("异常开始回滚");
			} catch (Exception e1) {
				LogUtils.Log("回滚出现异常");
			}
			return false;
		}
	}

}
