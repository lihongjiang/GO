package com.netfuture.go.db.ormlite;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * @author bslee
 * @description 数据库操作类
 * 
 */
public abstract class BaseDao<T,K> {

	
    public Dao<T, K> jdbcTemplate;
    public DatabaseHelper  helper;

    public BaseDao(Context context, Class<T> object) {
        if (jdbcTemplate == null) {
            try {
            	helper=OpenHelperManager.getHelper(context, DatabaseHelper.class);
                jdbcTemplate = helper.getDao(object);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
