package com.rakuten.entity;

import java.util.Date;

public class TimeTableBase extends Base {
private String branch =null;
private int semester =0;
private int year=0;
private String subject=null;
private Date date=null;
private String time=null;
private long facultyId=0;
public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
}
public int getSemester() {
	return semester;
}
public void setSemester(int semester) {
	this.semester = semester;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public long getFacultyId() {
	return facultyId;
}
public void setFacultyId(long facultyId) {
	this.facultyId = facultyId;
}

}
