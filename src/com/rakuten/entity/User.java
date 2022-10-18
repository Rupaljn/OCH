package com.rakuten.entity;

import java.util.Date;

public class User extends Base{
private static final long serialVersionUID = 1L;	
private String firstName;
private String lastName;
private String fatherName;
private String motherName;
private String login;
private String collegeId;
private String password;
private String department;
private int semester;
private Date dateOfBirth;
private String gender;
private String mobileNumber;
private String address;
private Date lastLoginDate;
private String userLock;
private String registredIp;
private String lastLoginIp;
private long roleId;
private int unSuccessfulLogin;
private int year;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getMotherName() {
	return motherName;
}
public void setMotherName(String motherName) {
	this.motherName = motherName;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getCollegeId() {
	return collegeId;
}
public void setCollegeId(String collegeId) {
	this.collegeId = collegeId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public int getSemester() {
	return semester;
}
public void setSemester(int semester) {
	this.semester = semester;
}
public Date getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getLastLoginDate() {
	return lastLoginDate;
}
public void setLastlogin(Date lastLoginDate) {
	this.lastLoginDate = lastLoginDate;
}
public String getUserLock() {
	return userLock;
}
public void setUserLock(String userLock) {
	this.userLock = userLock;
}
public String getRegistredIp() {
	return registredIp;
}
public void setRegistredIp(String registredIp) {
	this.registredIp = registredIp;
}
public String getLastLoginIp() {
	return lastLoginIp;
}
public void setLastLoginIp(String lastLoginIp) {
	this.lastLoginIp = lastLoginIp;
}
public long getRoleId() {
	return roleId;
}
public void setRoleId(long roleId) {
	this.roleId = roleId;
}
public int getUnSuccessfulLogin() {
	return unSuccessfulLogin;
}
public void setUnSuccessfulLogin(int unSuccessfulLogin) {
	this.unSuccessfulLogin = unSuccessfulLogin;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public String getRegistredIp1() {
	
	return null;
}
public String getUserLock1() {
	return null;
}

@Override
	public String getTableName() {
		return "user";
	}
}
