package com.rakuten.entity;

import java.util.Date;

public class CommentBase extends Base{
private long resourceId = 0;
private String text = null;
private Date createOn = null;
private long userId = 0;
private String resourceName = null;
public long getResourceId() {
	return resourceId;
}
public void setResourceId(long resourceId) {
	this.resourceId = resourceId;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public Date getCreateOn() {
	return createOn;
}
public void setCreateOn(Date createOn) {
	this.createOn = createOn;
}
public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}
public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}

}
