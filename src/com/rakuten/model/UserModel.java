 package com.rakuten.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.management.Query;

import com.rakuten.entity.Base;
import com.rakuten.entity.StaffBase;
import com.rakuten.entity.Student;
import com.rakuten.entity.StudentBase;
import com.rakuten.entity.User;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.DuplicateRecordException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class UserModel extends BaseModel{
public long add(User user) throws ApplicationException, DuplicateRecordException {

    Long uid = nextNonBusinessPK(user.getTableName());
	String firstName = user.getFirstName();
	String lastName = user.getLastName();
	String fatherName = user.getFatherName();
	String motherName = user.getMotherName();
	String login = user.getLogin();
	String collegeId = user.getCollegeId();
	String password = user.getPassword();
	String department = user.getDepartment();
	Integer semester = user.getSemester();
	Date dateOfBirth = user.getDateOfBirth();
	String gender = user.getGender();
	String mobileNumber = user.getMobileNumber();
	String address = user.getAddress();
	Date lastlogin = user.getLastLoginDate();
	String userLock = user.getUserLock();
	String registredIp = user.getRegistredIp();
	String lastLoginIp = user.getLastLoginIp();
	long roleId = user.getRoleId();
	Integer unSuccessfulLogin = user.getUnSuccessfulLogin();
	Integer year = user.getYear();
	String createdBy = user.getCreatedBy();
	
	if (this.findByLogin(login) != null) {
		throw new DuplicateRecordException("Exception : User Record already exitst");
	}
	
	int rowCount = 0;
	Connection con = null;
	PreparedStatement ps = null;
	String query = "INSERT INTO user (uid,first_name,last_name,father_name,mother_name,"
			+ "login,password,college_id,department,semester,year," + "dob,gender,mobile_number,address,registered_ip,"
			+ "last_login_date,user_lock,last_login_ip,unSuccessful_login,created_by,role_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	
	try{
		con = JDBCDataSource.getConnection();
		con.setAutoCommit(false);
		ps = con.prepareStatement(query);
		ps.setLong(1, uid);
		ps.setString(2, firstName);
		ps.setString(3, lastName);
		ps.setString(4, fatherName);
		ps.setString(5, motherName);
		ps.setString(6, login);
		ps.setString(7, password);
		ps.setString(8, collegeId);
		ps.setString(9, department);
		ps.setInt(10, semester);
		ps.setInt(11, year);
		ps.setString(13, gender);
		ps.setString(14, mobileNumber);
		ps.setString(15, address);
		ps.setString(16, registredIp);
		ps.setDate(17, (java.sql.Date) lastlogin);
		ps.setString(18, userLock);
		ps.setString(19, lastLoginIp);
		ps.setInt(20, unSuccessfulLogin);
		ps.setString(21, createdBy);
		ps.setLong(22, roleId);
		int row = ps.executeUpdate();
		
		rowCount = ps.executeUpdate();
		con.setAutoCommit(true);
		ps.close();
		con.close();
	}
	
	catch (Exception e) {
		e.printStackTrace();
		JDBCDataSource.trnRollback(con);
		throw new ApplicationException(e.getMessage());
	}
	
	if(roleId==AppRole.STUDENT){
		StudentBase sbase = new StudentBase();
		Long spk = nextNonBusinessPK(sbase.getTableName());
		sbase.setId(spk);
		 sbase.setUserId(uid);
		sbase.setFirstName(firstName);
		sbase.setLastName(lastName);
		sbase.setFatherName(fatherName);
		sbase.setMotherName(motherName);
		sbase.setCollegeId(collegeId);
		sbase.setDepartment(department);
		sbase.setSemester(semester);
		sbase.setYear(year);
		sbase.setDateOfBirth((java.sql.Date) dateOfBirth);
		sbase.setGender(gender);
		sbase.setMobileNumber(mobileNumber);
		sbase.setAddress(address);
		 
		if (this.findByLogin(login) != null) {
			throw new DuplicateRecordException("Exception : Record already exitst");
		}
		
		StudentModel.addStudent(sbase);
	}

	else if (roleId == AppRole.STAFF) {
		StaffBase stfbase = new StaffBase();
		Long staffpk = nextNonBusinessPK(stfbase.getTableName());
		stfbase.setUserId(uid);
		stfbase.setFirstName(firstName);
		stfbase.setLastName(lastName);
		stfbase.setFatherName(fatherName);
		stfbase.setMotherName(motherName);
		stfbase.setCollegeId(collegeId);
		stfbase.setDepartement(department);
		stfbase.setSemester(semester);
		stfbase.setYear(year);
		stfbase.setDateOfBirth((java.sql.Date) dateOfBirth);
		stfbase.setGender(gender);
		stfbase.setMobileNo(mobileNumber);
		stfbase.setAddress(address);
         
		StaffModel stafm = new StaffModel();
		stafm.add(stfbase);
	}

	if (rowCount > 0) {
		return uid;
	} 
	
	else {
		return -1;
	}
}

	

//public  long delete(User ubase) throws ApplicationException,NoRecordFoundException {
// 
//	int rowCount = 0;
//	Connection con = null;
//	PreparedStatement ps = null;
//	long id=ubase.getId();
//	if (UserModel.findByPk(id) == null) {
//		throw new NoRecordFoundException("Exception : No Corresponding record ");
//	}
//
//	try {
//		con = JDBCDataSource.getConnection();
//		con.setAutoCommit(false);
//		ps = con.prepareStatement("DELETE FROM ST_USER WHERE ID=?");
//		ps.setLong(1, id);
//		rowCount = ps.executeUpdate();
//		con.setAutoCommit(true);
//		ps.close();
//	} catch (Exception e) {
//		JDBCDataSource.trnRollback(con);
//		throw new ApplicationException(e.getMessage());
//	} finally {
//		JDBCDataSource.closeConnection(con);
//	}
//return id;
//}
//
//public User findByPk(long id)throws ApplicationException {
//
//	Connection con = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
//	User ubase = null;
//
//	try {
//		con = JDBCDataSource.getConnection();
//		ps = con.prepareStatement("SELECT * FROM ST_USER WHERE ID=?");
//		ps.setLong(1, id);
//		rs = ps.executeQuery();
//
//		while (rs.next()) {
//			User ubase = new User();
//			ubase.setId(rs.getLong(1));
//			ubase.setFirstName(rs.getString(2));
//			ubase.setLastName(rs.getString(3));
//			ubase.setFatherName(rs.getString(4));
//			ubase.setMotherName(rs.getString(5));
//			ubase.setLogin(rs.getString(6));
//			ubase.setCollegeId(rs.getString(8));
//			ubase.setDepartment(rs.getString(9));
//			ubase.setSemester(rs.getInt(10));
//			ubase.setYear(rs.getInt(11));
//			ubase.setDateOfBirth(rs.getDate(12));
//			ubase.setGender(rs.getString(13));
//			ubase.setMobileNumber(rs.getString(14));
//			ubase.setAddress(rs.getString(15));
//			ubase.setRoleId(rs.getLong(20));
//
//		}
//		
//		ps.close();
//
//	}
//	
//	catch (Exception e) {
//		JDBCDataSource.trnRollback(con);
//		e.printStackTrace();
//		throw new ApplicationException(e.getMessage());
//	} 
//	
//	finally {
//		JDBCDataSource.closeConnection(con);
//	}
//
//	return ubase;
//}
//
//public  User findByRoleId(long id)throws ApplicationException {
//
//	Connection con = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
//	User ubase = null;
//
//	try {
//		con = JDBCDataSource.getConnection();
//		ps = con.prepareStatement("SELECT * FROM ST_USER WHERE role_id=?");
//		ps.setLong(1, id);
//		rs = ps.executeQuery();
//
//		while (rs.next()) {
//			ubase = new User();
//			ubase.setId(rs.getLong(1));
//			ubase.setFirstName(rs.getString(2));
//			ubase.setLastName(rs.getString(3));
//			ubase.setFatherName(rs.getString(4));
//			ubase.setMotherName(rs.getString(5));
//			ubase.setLogin(rs.getString(6));
//			ubase.setCollegeId(rs.getString(8));
//			ubase.setDepartment(rs.getString(9));
//			ubase.setSemester(rs.getInt(10));
//			ubase.setYear(rs.getInt(11));
//			ubase.setDateOfBirth(rs.getDate(12));
//			ubase.setGender(rs.getString(13));
//			ubase.setMobileNumber(rs.getString(14));
//			ubase.setAddress(rs.getString(15));
//			ubase.setRoleId(rs.getLong(20));
//
//		}
//		ps.close();
//		
//	} 
//	
//	catch (Exception e) {
//		JDBCDataSource.trnRollback(con);
//		e.printStackTrace();
//		throw new ApplicationException(e.getMessage());
//	} 
//	
//	finally {
//		JDBCDataSource.closeConnection(con);
//	}
//
//	return ubase;
//}
//
public User findByLogin(String login) throws ApplicationException {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	User ubase = null;

	try {
		con = JDBCDataSource.getConnection();
		ps = con.prepareStatement("SELECT * FROM ST_USER WHERE login=?");
		ps.setString(1, login);
		rs = ps.executeQuery();

		while (rs.next()) {
			ubase = new User();
			ubase.setId(rs.getLong(1));
			ubase.setFirstName(rs.getString(2));
			ubase.setLastName(rs.getString(3));
			ubase.setFatherName(rs.getString(4));
			ubase.setMotherName(rs.getString(5));

			ubase.setCollegeId(rs.getString(8));
			ubase.setDepartment(rs.getString(9));
			ubase.setSemester(rs.getInt(10));
			ubase.setYear(rs.getInt(11));
			ubase.setDateOfBirth(rs.getDate(12));
			ubase.setGender(rs.getString(13));
			ubase.setMobileNumber(rs.getString(14));
			ubase.setAddress(rs.getString(14));

		}

	} 
	
	catch (Exception e) {
		JDBCDataSource.trnRollback(con);
		throw new ApplicationException(e.getMessage());
	} 
	
	finally {
		JDBCDataSource.closeConnection(con);
	}

	return ubase;
}

//public long update(User ubase) throws NoRecordFoundException, ApplicationException {
//
//	Connection con = null;
//	PreparedStatement ps = null;
//	int rowCount = 0;
//	
//	Date dob = new java.sql.Date(ubase.getDateOfBirth().getTime());
//	
//	String query = "UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,"
//			+ "FATHER_NAME=?,MOTHER_NAME=?,LOGIN=?,"
//			+ "COLLEGE_ID=?,DEPARTEMENT=?,SEMESTER=?,YEAR=?,DOB=?," + "GENDER=?,MOBILE_NO=?,ADDRESS=?,LAST_LOGIN=?,"
//			+ "USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?," + "ROLE_ID=?,UNSUCCESSFUL_LOGIN=? WHERE ID=?";
//
//	long pk = ubase.getId();
//
//	if (UserModel.findByPk(pk) == null) {
//		throw new NoRecordFoundException("Exception : No record found");
//
//	}
//	
//	try {
//		con = JDBCDataSource.getConnection();
//		con.setAutoCommit(false);
//		ps = con.prepareStatement(query);
//		ps.setString(1, ubase.getFirstName());
//		ps.setString(2, ubase.getLastName());
//		ps.setString(3, ubase.getFatherName());
//		ps.setString(4, ubase.getMotherName());
//		ps.setString(5, ubase.getLogin());
//		ps.setString(6, ubase.getCollegeId());
//		ps.setString(7, ubase.getDepartment());
//		ps.setInt(8, ubase.getSemester());
//		ps.setInt(9, ubase.getYear());
//		java.sql.Date dateOfBirth;
//		ps.setDate(10, dateOfBirth);
//		ps.setString(11, ubase.getGender());
//		ps.setString(12, ubase.getMobileNumber());
//		ps.setString(13, ubase.getAddress());
//		ps.setDate(14, (java.sql.Date)ubase.getLastlogin());
//		ps.setString(15, ubase.getUserLock());
//		ps.setString(16, ubase.getRegistredIp());
//		ps.setString(17, ubase.getLastLoginIp());
//		ps.setLong(18, ubase.getRoleId());
//		ps.setInt(19, ubase.getUnSuccessfulLogin());
//		ps.setLong(20, ubase.getId());
//
//		rowCount = ps.executeUpdate();
//		con.setAutoCommit(true);
//		ps.close();
//	} 
//	
//	catch (Exception e) {
//		JDBCDataSource.trnRollback(con);
//		e.printStackTrace();
//		throw new ApplicationException("User  Exception"+e.getMessage());
//	} 
//	
//	finally {
//		JDBCDataSource.closeConnection(con);
//	}
//System.out.println("pk from bean="+pk);
//	return pk;
//}
//
//public  User authenticate(User ubase) throws ApplicationException {
//
//	Connection con = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
//	
//
//	String password = ubase.getPassword();
//	String login = ubase.getLogin();
//	ubase=null;
//	try {
//		con = JDBCDataSource.getConnection();
//		ps = con.prepareStatement("SELECT * FROM ST_USER WHERE LOGIN=? AND  PASSWORD =?");
//		ps.setString(1, login);
//		ps.setString(2, password);
//		rs = ps.executeQuery();
//
//		while (rs.next()) {
//			ubase = new User();
//			ubase.setId(rs.getLong(1));
//			ubase.setFirstName(rs.getString(2));
//			ubase.setLastName(rs.getString(3));
//			ubase.setFatherName(rs.getString(4));
//			ubase.setMotherName(rs.getString(5));
//			ubase.setLogin(rs.getString(6));
//			ubase.setCollegeId(rs.getString(8));
//			ubase.setDepartment(rs.getString(9));
//			ubase.setSemester(rs.getInt(10));
//			ubase.setYear(rs.getInt(11));
//			ubase.setDateOfBirth(rs.getDate(12));
//			ubase.setGender(rs.getString(13));
//			ubase.setMobileNumber(rs.getString(14));
//			ubase.setAddress(rs.getString(15));
//			ubase.setLastLogin(rs.getDate(16));
//			ubase.setRoleId(rs.getLong(20));
//			ubase.setCreatedBy(rs.getString(22));
//			ubase.setModifiedBy(rs.getString(23));
//			ubase.setCreatedDateTime(rs.getTimestamp(24));
//			ubase.setModifiedDateTime(rs.getTimestamp(25));
//		}
//
//		ps.close();
//	} 
//	catch (Exception e) {
//		JDBCDataSource.trnRollback(con);
//		e.printStackTrace();
//		throw new ApplicationException(e.getMessage());
//	} 
//	finally {
//		JDBCDataSource.closeConnection(con);
//	}
//
//	return ubean;
//}
//
//public ArrayList<UserBase> search(UserBase ubase, int pageNo, int pageSize) throws ApplicationException {
//
//	Connection con = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
//	ArrayList<UserBase> list = new ArrayList<UserBase>();
//	StringBuffer query = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1 ");
//
//	
//	if (ubase != null) {
//		if (ubase.getId() > 0) {
//			query.append(" AND id =" + ubase.getId());
//		}
//		if (ubase.getFirstName() != null && ubase.getFirstName().trim().length() > 0) {
//			query.append(" AND first_name like '" + ubase.getFirstName() + "%'");
//		}
//		if (ubase.getLastName() != null && ubase.getLastName().trim().length() > 0) {
//			query.append(" AND last_name like '" + ubase.getLastName() + "%'");
//		}
//		if (ubase.getFatherName() != null && ubase.getFatherName().trim().length() > 0) {
//			query.append(" AND father_name like '" + ubase.getFatherName() + "%'");
//		}
//		if (ubase.getMotherName() != null && ubase.getMotherName().trim().length() > 0) {
//			query.append(" AND mother_name like '" + ubase.getMotherName() + "%'");
//		}
//		if (ubase.getRoleId() == 1) {
//			query.append(" AND role_id = " + ubase.getRoleId());
//		}
//		if (ubase.getLogin() != null && ubase.getLogin().trim().length() > 0) {
//			query.append(" AND login like '" + ubase.getLogin() + "%'");
//		}
//		if (ubase.getMobileNo() != null && ubase.getMobileNo().trim().length() > 0) {
//			query.append(" AND mobile_no like '" + ubase.getMobileNo() + "%'");
//		}
//		if (ubase.getGender() != null && ubase.getGender().trim().length() > 0) {
//			query.append(" AND gender like '" + ubase.getGender() + "%'");
//		}
//		if (ubase.getSemester() > 0) {
//			query.append(" AND semester = " + ubase.getSemester());
//		}
//		if (ubase.getYear() > 0) {
//			query.append(" AND year = " + ubase.getYear());
//		}
//		if (ubase.getDepartment() != null && ubase.getDepartment().trim().length() > 0) {
//			query.append(" AND departement like '" + ubase.getDepartment() + "%'");
//		}
//		if (ubase.getCollegeId() > 0) {
//			query.append(" AND college_id = " + ubase.getCollegeId());
//		}
//
//	}
//
//	if (pageSize > 0) {
//		pageNo = (pageNo - 1) * pageSize;
//		query.append("limit " + pageNo + "," + pageSize);
//	}
//
//	try {
//		con = JDBCDataSource.getConnection();
//		con.setAutoCommit(false);
//		ps = con.prepareStatement(query.toString());
//		rs = ps.executeQuery();
//
//		while (rs.next()) {
//			ubase = new User();
//			ubase.setId(rs.getLong(1));
//			ubase.setFirstName(rs.getString(2));
//			ubase.setLastName(rs.getString(3));
//			ubase.setFatherName(rs.getString(4));
//			ubase.setMotherName(rs.getString(5));
//			ubase.setLogin(rs.getString(6));
//			ubase.setCollegeId(rs.getLong(8));
//			ubase.setDepartement(rs.getString(9));
//			ubase.setSemester(rs.getInt(10));
//			ubase.setYear(rs.getInt(11));
//			ubase.setDateOfBirth(rs.getDate(12));
//			ubase.setGender(rs.getString(13));
//			ubase.setMobileNo(rs.getString(14));
//			ubase.setAddress(rs.getString(15));
//			ubase.setLastLogin(rs.getDate(16));
//			ubase.setUserLock(rs.getString(17));
//			ubase.setLastLoginIp(rs.getString(19));
//			ubase.setRoleId(rs.getLong(20));
//
//			list.add(ubase);
//
//		}
//
//		con.setAutoCommit(true);
//		ps.close();
//	} 
//	
//	catch (Exception e) {
//		e.printStackTrace();
//		throw new ApplicationException("Exception from usermodel");
//	}
//	return list;
//}
//
//public  ArrayList<User> getList(User ubase, int pageNo, int pageSize) throws ApplicationException {
//
//	Connection con = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
//	ArrayList<User> list = new ArrayList<User>();
//	StringBuffer query = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1 ");
//
//	 if (ubase != null) {
//		if (ubase.getId() > 0) {
//			query.append(" AND id =" + ubase.getId());
//		}
//		if (ubase.getFirstName() != null && ubase.getFirstName().trim().length() > 0) {
//			query.append(" AND first_name like '" + ubase.getFirstName() + "%'");
//		}
//		if (ubase.getLastName() != null && ubase.getLastName().trim().length() > 0) {
//			query.append(" AND last_name like '" + ubase.getLastName() + "%'");
//		}
//		if (ubase.getLogin() != null && ubase.getLogin().trim().length() > 0) {
//			query.append(" AND login like '" + ubase.getLogin() + "%'");
//		}
//	}
//
//	if (pageSize > 0) {
//		pageNo = (pageNo - 1) * pageSize;
//		query.append(" limit " + pageNo + "," + pageSize);
//	}
//	
//
//	try {
//		con = JDBCDataSource.getConnection();
//		con.setAutoCommit(false);
//		ps = con.prepareStatement(query.toString());
//		rs = ps.executeQuery();
//
//		while (rs.next()) {
//			ubase = new User();
//			ubase.setId(rs.getLong(1));
//			ubase.setFirstName(rs.getString(2));
//			ubase.setLastName(rs.getString(3));
//			ubase.setFatherName(rs.getString(4));
//			ubase.setMotherName(rs.getString(5));
//			ubase.setLogin(rs.getString(6));
//			ubase.setCollegeId(rs.getString(8));
//			ubase.setDepartment(rs.getString(9));
//			ubase.setSemester(rs.getInt(10));
//			ubase.setYear(rs.getInt(11));
//			ubase.setDateOfBirth(rs.getDateOfBirth(12));
//			ubase.setGender(rs.getString(13));
//			ubase.setMobileNumber(rs.getString(14));
//			ubase.setAddress(rs.getString(15));
//			ubase.setLastLogin(rs.getDate(16));
//			ubase.setUserLock(rs.getString(17));
//			ubase.setLastLoginIp(rs.getString(19));
//			ubase.setRoleId(rs.getLong(20));
//
//			list.add(ubase);
//
//		}
//
//		con.setAutoCommit(true);
//		ps.close();
//	} 
//	
//	catch (Exception e) {
//		e.printStackTrace();
//		throw new ApplicationException("Exception from usermodel");
//	}
//	return list;
//}
//
//public boolean changePassword(String login, String oldPassword, String newPassword)
//		throws NoRecordFoundException, ApplicationException {
//	User ubase = new User();
//	ubase.setLogin(login);
//	ubase.setPassword(oldPassword);
//	if (UserModel.authenticate(ubase) == null) {
//		throw new NoRecordFoundException("Not a valid user");
//	}
//	Connection con = null;
//	PreparedStatement ps = null;
//	int rowCount = 0;
//	String query = "UPDATE ST_USER SET PASSWORD=? WHERE LOGIN=?";
//
//	try {
//		con = JDBCDataSource.getConnection();
//		con.setAutoCommit(false);
//		ps = con.prepareStatement(query);
//		ps.setString(1, newPassword);
//		ps.setString(2, login);
//		rowCount = ps.executeUpdate();
//		con.setAutoCommit(true);
//		ps.close();
//	} 
//	catch (Exception e) {
//		JDBCDataSource.trnRollback(con);
//		e.printStackTrace();
//		throw new ApplicationException("Exception from changePassword");
//	} 
//	finally {
//		JDBCDataSource.closeConnection(con);
//	}
//
//	if (rowCount > 0) {
//		return true;
//	} else {
//		return false;
//	}
//}
//
//public boolean forgetPassword(String login) throws NoRecordFoundException, ApplicationException {
//	if (UserModel.findByLogin(login) == null) {
//		throw new NoRecordFoundException("Not a valid login id ");
//	}
//	
//	return false;
//}

public static void main(String[] args) {
	User testUser = new User();
     testUser.setFirstName("tofique pagal");
     testUser.setLastName("khan");
     testUser.setAddress("Indore");
     testUser.setFatherName("Mr.khan");
     testUser.setMotherName("mrs. khan");
 	 testUser.setLogin("Welcome@123");
 	 testUser.setCollegeId("collegeId");
 	testUser.setPassword("India"); 
 	testUser.setDepartment("");
 	testUser.setSemester(1);
 //	testUser.setDateOfBirth(3-11-1994);
 	testUser.setGender ("male");
 	testUser.setMobileNumber("3456789");
 //	testUser.setLastLoginDate(3-12-1234);;
 	testUser.setUserLock("Active");
 	testUser.setRegistredIp("RegistredIp");
 	testUser.setLastLoginIp("LoginIp");
 	testUser.setRoleId(1);
 	testUser.setUnSuccessfulLogin(1);
 	testUser.setYear(2004);
 	testUser.setCreatedBy("Rupal");
 	
    UserModel um = new UserModel();
    um.add(testUser);
}


}


 

