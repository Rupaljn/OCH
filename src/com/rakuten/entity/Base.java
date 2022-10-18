package com.rakuten.entity;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

public abstract class Base implements Serializable{
private Long id;
private Timestamp createdDateTime;
private String createdBy;
private String modifiedBy;
private Timestamp modifiedDateTime;


public Timestamp getCreatedDateTime() {
	return createdDateTime;
}
public void setCreatedDateTime(Timestamp createdDateTime) {
	this.createdDateTime = createdDateTime;
}
public String getModifiedBy() {
	return modifiedBy;
}
public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}
public Timestamp getModifiedDateTime() {
	return modifiedDateTime;
}
public void setModifiedDateTime(Timestamp modifiedDateTime) {
	this.modifiedDateTime = modifiedDateTime;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String string) {
	this.createdBy = createdBy;
}

public abstract String getTableName();

}
