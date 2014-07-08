package com.netfuture.go.entity;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;

/**
 * @description	ID实体类
 * @author LHJ
 * @version 3.0.0
 * 
 */


public class IdEntity  implements Serializable {

	private static final long serialVersionUID = 3945022862739623898L;
	@Expose
    @DatabaseField(columnName = "ID", generatedId = true)
    private Integer  id;

    public IdEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
