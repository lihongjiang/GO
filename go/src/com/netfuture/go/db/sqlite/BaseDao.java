package com.netfuture.go.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BaseDao {
	public Context context;
	private DBHelper conn = null;
	public SQLiteDatabase db;

	public BaseDao(Context context) {
		this.context = context;
		conn = new DBHelper(context);
	}

	public void getConn() {
		if (db.isOpen()) {
		} else {
			db = conn.getReadableDatabase();
		}

	}

	public void close() {
		if (db.isOpen()) {
			db.close();
		}

	}
}
