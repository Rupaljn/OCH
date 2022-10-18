package com.rakuten.entity;

import java.util.Date;

public class StudentBase extends Base{
private static final long serialVersionUID = 1L;
private long id=0;
private String firstName = null;
private String lastName = null;
private String fatherName = null;
private String motherName = null;
private String collegeId = null;
private String department = null;
private int semester = 0;
private int year = 0;
private Date dateOfBirth = null;
private String gender = null;
private String mobileNumber = null;
private String address = null;
private long userId = 0;
		
		

public Long getId() {
   return id;
}

public void setId(long id) {
			this.id = id;
		}

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

		public String getCollegeId() {
			return collegeId;
		}

		public void setCollegeId(String collegeId) {
			this.collegeId = collegeId;
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

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
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

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}

		
		public String getKey() {
			
			return null;
		}

		
		public String getValue() {
			return null;
		}

		
		@Override
			public String getTableName() {
			return "student";	
			}
		
	}


