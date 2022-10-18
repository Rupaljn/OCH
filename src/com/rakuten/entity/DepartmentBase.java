package com.rakuten.entity;

public class DepartmentBase extends Base{
private long code = 0;
private String name = null;
private String description = null;
private String collegeId = null;
public long getCode() {
	return code;
}
public void setCode(long code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCollegeId() {
	return collegeId;
}
public void setCollegeId(String collegeId) {
	this.collegeId = collegeId;
}


}
