package com.netfuture.go.db.sqlite;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
/**
 * @author bslee
 * @description 数据库创建和升级
 */
public class DBHelper extends SQLiteOpenHelper {
	
	private static final String TRAFFIC_TABLE = "create table trafficstatus("
			+ "_id integer primary key autoincrement ,"
			+ "date  datetime not null," + "wifi_mobie integer not null,"
			+ "updata integer not null," + "downdata integer not null,"
			+ "shutdown  boolean not null)";
	private static final String DATABASE = "test.db";
	private static final int VERSION = 1;

	public DBHelper(Context context) {
		super(context, DATABASE, null, VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(TRAFFIC_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
