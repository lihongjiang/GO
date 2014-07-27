package com.netfuture.go.db.ormlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author bslee
 * @description 数据库创建和升级
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "yuxin.db";
	public static final int DATABASE_VERSION = 1;
	//上下文
	private Context context;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase,
			ConnectionSource connectionSource) {
		try {
			// 表创建
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase,
			ConnectionSource connectionSource, int oldVer, int newVer) {
		// 迭代升级数据库版本
		for (int j = oldVer + 1; j <= newVer; j++) {
			switch (j) {
			// case处理升级
			default:
				break;
			}
		}
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onDowngrade(db, oldVersion, newVersion);

	}

	private void ClearTableSpace(SQLiteDatabase db) {
		try{
		//查询所有表
		List<String>  names=new ArrayList<String>();
		db.beginTransaction();
		Cursor  cursor=db.rawQuery("SELECT * FROM sqlite_master WHERE type='table' ORDER BY name; ",new String[]{});	
		while (cursor.moveToNext()) {
			names.add(cursor.getString(cursor.getColumnIndex("name")));	
		}
		//清空数据和自增长ID
		for (int i = 0; i < names.size(); i++) {
			if (!"sqlite_sequence".equals(names.get(i))) {
				db.execSQL("delete from "+names.get(i));
				db.execSQL("update sqlite_sequence set seq=0 where name= "+names.get(i));
			}
		}
		//释放空间
		db.execSQL("vacuum;");
	    db.setTransactionSuccessful();
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		db.endTransaction();// 由事务的标志决定是提交事务，还是回滚事务
	}
	}
}
