package com.netfuture.go.db.sqlite;

import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
/**
 * @author bslee
 * @description 数据库创建和升级
 */
public class TrafficDao extends BaseDao {

	public TrafficDao(Context context) {
		super(context);
	}

	public HashMap<String, Long> findDay3GTrafficEntry(String date,
			int wifi_mobile) {
		HashMap<String, Long> packs = new HashMap<String, Long>();
		getConn();
		String sql = "select  sum(updata) as up,sum(downdata) as down,sum(updata+downdata)  as alldata from trafficstatus where  wifi_mobie=? and date=?";
		Cursor cursor = db.rawQuery(sql,
				new String[] { wifi_mobile + "", date });
		while (cursor.moveToNext()) {
			packs.put("up", cursor.getLong(cursor.getColumnIndex("up")));
			packs.put("down", cursor.getLong(cursor.getColumnIndex("down")));
			packs.put("alldata",
					cursor.getLong(cursor.getColumnIndex("alldata")));
		}
		cursor.close();
		// close();
		return packs;
	}

	public void insertTrafficEntry(Object traffic) {
		getConn();
		String sql = "insert into trafficstatus(date,wifi_mobie,updata,downdata,shutdown) values(?,?,?,?,?)";
		db.execSQL(sql, new Object[] {});
		close();
	}

}
