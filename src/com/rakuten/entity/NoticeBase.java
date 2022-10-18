package com.rakuten.entity;

import java.util.Date;

public class NoticeBase extends Base{
private String subject = null;
private String details = null;
private Date createOn = null;
private Date expireDate = null;
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}
public Date getCreateOn() {
	return createOn;
}
public void setCreateOn(Date createOn) {
	this.createOn = createOn;
}
public Date getExpireDate() {
	return expireDate;
}
public void setExpireDate(Date expireDate) {
	this.expireDate = expireDate;
}


}
