package com.netfuture.go.db.contentprovider;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/**
 * 短信数据库操作
 * 
 * @author Administrator
 * 
 */
public class SmsContentResolverDao {

	// 内容观察者
	public ContentResolver visitor = null;

	// 资源访问地址
	private static final String ROOTURI = "content://sms/";
	@SuppressWarnings("unused")
	private Context context;

	// 监控资源地址
	private static final String strUri = "content://sms/";

	public SmsContentResolverDao(Activity context) {
		this.context = context;
		if (visitor == null) {
			visitor = context.getContentResolver();
			visitor.registerContentObserver(Uri.parse(strUri), true,
					new myContentObserver(new Handler()));
		}
	}

	public void addSmsInfo(SmsInfo sms) {
		Uri inserturi = Uri.parse(ROOTURI);
		ContentValues values = new ContentValues();
		values.put("date", sms.getDate());
		values.put("address", sms.getAddress());
		values.put("type", sms.getType());
		values.put("body", sms.getBody());
		visitor.insert(inserturi, values);
	}

	public Integer deleteSmsInfo(Integer rowNum) {
		Uri inserturi = Uri.parse(ROOTURI);
		if (rowNum != null) {
			Uri deleteuri = ContentUris.withAppendedId(inserturi, rowNum);
			return visitor.delete(deleteuri, null, null);
		} else {
			return visitor.delete(inserturi, null, null);
		}

	}

	public Integer updateSmsInfo(Integer rowNum, SmsInfo sms) {
		Uri inserturi = Uri.parse(ROOTURI);
		Uri updateuri = ContentUris.withAppendedId(inserturi, rowNum);
		ContentValues values = new ContentValues();
		values.put("date", sms.getDate());
		values.put("address", sms.getAddress());
		values.put("type", sms.getType());
		values.put("body", sms.getBody());
		return visitor.update(updateuri, values, null, null);
	}

	public Cursor selectSmsInfo(Integer rowNum) {
		Uri inserturi = Uri.parse(ROOTURI);
		if (rowNum == null) {
			return visitor.query(inserturi, null, null, null, null);
		} else {
			Uri selecturi = ContentUris.withAppendedId(inserturi, rowNum);
			return visitor.query(selecturi, null, null, null, null);
		}
	}

	public SmsInfo selectSmsInfoObject(Integer rowNum) {
		Cursor cursor = selectSmsInfo(rowNum);
		SmsInfo coll = null;
		while (cursor.moveToNext()) {
			coll = new SmsInfo(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("address")),
					cursor.getLong(cursor.getColumnIndex("date")),
					cursor.getInt(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("body")));
		}
		return coll;
	}

	public List<SmsInfo> selectAllSmsInfoObject() {
		Cursor cursor = selectSmsInfo(null);
		List<SmsInfo> coll = new ArrayList<SmsInfo>();
		while (cursor.moveToNext()) {
			coll.add(new SmsInfo(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("address")), cursor
							.getLong(cursor.getColumnIndex("date")), cursor
							.getInt(cursor.getColumnIndex("type")), cursor
							.getString(cursor.getColumnIndex("body"))));
		}
		return coll;
	}

	private class myContentObserver extends ContentObserver {

		public myContentObserver(Handler handler) {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			// 短信数据库有变化
		}

	}

}
