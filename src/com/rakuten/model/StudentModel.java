package com.rakuten.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rakuten.entity.Base;
import com.rakuten.entity.StudentBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.DuplicateRecordException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class StudentModel extends BaseModel{
public static long addStudent(StudentBase sbase) {

if (StudentModel.findByPk(sbase) != null) {
throw new DuplicateRecordException("Exception : Id(pk) already exitst");
}

if (StudentModel.findByPk(sbase) != null) {
throw new DuplicateRecordException("Exception : UserId already exitst");
}

		Connection con = null;
		PreparedStatement ps = null;
		long UserId = sbase.getUserId();
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("INSERT INTO ST_STUDENT (ID,FIRST_NAME,LAST_NAME,"
					+ "FATHER_NAME,MOTHER_NAME,COLLEGE_ID,DEPARTEMENT,"
					+ "SEMESTER,YEAR,DATE_OF_BIRTH,GENDER,MOBILE_NO,ADDRESS,USER_ID) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setLong(1, nextNonBusinessPK("st_student"));
			ps.setString(2, sbase.getFirstName());
			ps.setString(3, sbase.getLastName());
			ps.setString(4, sbase.getFatherName());
			ps.setString(5, sbase.getMotherName());
			ps.setString(6, sbase.getCollegeId());
			ps.setString(7, sbase.getDepartment());
			ps.setInt(8, sbase.getSemester());
			ps.setInt(9, sbase.getYear());
			ps.setDate(10, (Date) sbase.getDateOfBirth());
			ps.setString(11, sbase.getGender());
			ps.setString(12, sbase.getMobileNumber());
			ps.setString(13, sbase.getAddress());
			ps.setLong(14, sbase.getUserId());

			ps.executeUpdate();
			con.commit();

		} 
		
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} 
		
		finally {
			JDBCDataSource.closeConnection(con);
		}
		
		return UserId;
	}

public static long delete(StudentBase sbase) {

 if (StudentModel.findByPk(sbase) == null) {
     throw new NoRecordFoundException("Exception : no record found");
		
 }

		long id = sbase.getId();
		Connection con = null;
		PreparedStatement ps = null;
		int rowCount = 0;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM ST_STUDENT WHERE ID=?");
			ps.setLong(1, id);
			rowCount = ps.executeUpdate();
			con.commit();
			ps.close();

		} 
		
		catch (Exception e) {
			throw new ApplicationException("Exception : Exception in delete Student" + e.getMessage());
		} 
		
		finally {
			JDBCDataSource.closeConnection(con);
		}
		
		if (rowCount > 0) {
			return id;
		} 
		
		else {
			return -1;
		}
	}
public static long findByPk(StudentBase sbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long id = sbase.getId();
		sbase = null;
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM ST_STUDENT WHERE ID=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				sbase = new StudentBase();
				sbase.setId(rs.getLong(1));
				sbase.setFirstName(rs.getString(2));
				sbase.setLastName(rs.getString(3));
				sbase.setFatherName(rs.getString(4));
				sbase.setMotherName(rs.getString(5));
				sbase.setCollegeId(rs.getString(6));
				sbase.setDepartment(rs.getString(7));
				sbase.setSemester(rs.getInt(8));
				sbase.setYear(rs.getInt(9));
				sbase.setDateOfBirth(rs.getDate(10));
				sbase.setGender(rs.getString(11));
				sbase.setMobileNumber(rs.getString(12));
				sbase.setAddress(rs.getString(13));
				sbase.setUserId(rs.getLong(14));

			}

		} 
		
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException(e.getMessage());
		} 
		
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return sbase;

StudentBase findByUserId(StudentBase sbase) throws ApplicationException, NoRecordFoundException {

			Connection con1 = null;
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			long userId = sbase.getUserId();
			sbase = null;
			try {
				con1 = JDBCDataSource.getConnection();
				ps1 = con1.prepareStatement("SELECT * FROM ST_STUDENT WHERE USER_ID=?");
				ps1.setLong(1, userId);
				rs1 = ps1.executeQuery();

				while (rs1.next()) {
					sbase = new StudentBase();
					sbase.setId(rs1.getLong(1));
					sbase.setFirstName(rs1.getString(2));
					sbase.setLastName(rs1.getString(3));
					sbase.setFatherName(rs1.getString(4));
					sbase.setMotherName(rs1.getString(5));
					sbase.setCollegeId(rs1.getString(6));
					sbase.setDepartment(rs1.getString(7));
					sbase.setSemester(rs1.getInt(8));
					sbase.setYear(rs1.getInt(9));
					sbase.setDateOfBirth(rs1.getDate(10));
					sbase.setGender(rs1.getString(11));
					sbase.setMobileNumber(rs1.getString(12));
					sbase.setAddress(rs1.getString(13));
					sbase.setUserId(rs1.getLong(14));

				}

			} 
			
			catch (Exception e) {
				JDBCDataSource.trnRollback(con1);
				throw new com.rakuten.exceptions.ApplicationException(e.getMessage());
			} 
			
			finally {
				JDBCDataSource.closeConnection(con1);
			}

			return sbase;

		}

		
ArrayList<StudentBase> search(StudentBase sbase1, int pageNo, int pageSize)
throws ApplicationException, NoRecordFoundException {

			Connection con1 = null;
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			
			ArrayList<StudentBase> list = new ArrayList<StudentBase>();
			StringBuffer query = new StringBuffer("SELECT * FROM ST_STUDENT WHERE 1=1");

			if (sbase != null) {
				if (sbase.getId() > 0) {
					query.append(" AND id =" + sbase.getId());
				}
				if (sbase.getFirstName() != null && sbase.getFirstName().trim().length() > 0) {
					query.append(" AND first_name like '" + sbase.getFirstName() + "%'");
				}
				if (sbase.getLastName() != null && sbase.getLastName().trim().length() > 0) {
					query.append(" AND last_name like '" + sbase.getLastName() + "%'");
				}
				if (sbase.getFatherName() != null && sbase.getFatherName().trim().length() > 0) {
					query.append(" AND father_name like '" + sbase.getFatherName() + "%'");
				}
				if (sbase.getMotherName() != null && sbase.getMotherName().trim().length() > 0) {
					query.append(" AND mother_name like '" + sbase.getMotherName() + "%'");
				}
				if (sbase.getMobileNumber() != null && sbase.getMobileNumber().trim().length() > 0) {
					query.append(" AND mobile_number like '" + sbase.getMobileNumber() + "%'");
				}
				if (sbase.getGender() != null && sbase.getGender().trim().length() > 0) {
					query.append(" AND gender like '" + sbase.getGender() + "%'");
				}
				if (sbase.getSemester() > 0) {
					query.append(" AND semester = " + sbase.getSemester());
				}
				if (sbase.getYear() > 0) {
					query.append(" AND year = " + sbase.getYear());
				}
				if (sbase.getDepartment() != null && sbase.getDepartment().trim().length() > 0) {
					query.append(" AND department like '" + sbase.getDepartment() + "%'");
				}
				if (sbase.getCollegeId()!= null) {
					query.append(" AND college_id = " + sbase.getCollegeId());
				}
				if (sbase.getUserId() > 0) {
					query.append(" AND user_id = " + sbase.getUserId());
				}

			}
			if (pageNo > 0) {
				int pageSize;
				pageNo = (pageNo - 1) * pageSize;
				query.append(" limit " + pageNo + "," + pageSize);
			}
			System.out.println("test =" + query);
			try {
				con1 = JDBCDataSource.getConnection();
				con1.setAutoCommit(false);
				ps1 = con1.prepareStatement(query.toString());
				rs1 = ps1.executeQuery();

				while (rs1.next()) {
					sbase = new StudentBase();
					sbase.setId(rs1.getLong(1));
					sbase.setFirstName(rs1.getString(2));
					sbase.setLastName(rs1.getString(3));
					sbase.setFatherName(rs1.getString(4));
					sbase.setMotherName(rs1.getString(5));
					sbase.setCollegeId(rs1.getString(6));
					sbase.setDepartment(rs1.getString(7));
					sbase.setSemester(rs1.getInt(8));
					sbase.setYear(rs1.getInt(9));
					sbase.setDateOfBirth(rs1.getDate(10));
					sbase.setGender(rs1.getString(11));
					sbase.setMobileNumber(rs1.getString(12));
					sbase.setAddress(rs1.getString(13));
					sbase.setUserId(rs1.getLong(14));
					list.add(sbase);

				}

				con1.setAutoCommit(true);
				ps1.close();
			} 
			
			catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Exception from Studentmodel");
			}
			return list;

		}

		
public static long update(StudentBase sbase) throws NoRecordFoundException1, ApplicationException {

			Connection con1 = null;
			PreparedStatement ps1 = null;
			String query = "UPDATE ST_STUDENT SET FIRST_NAME=?,LAST_NAME=?,FATHER_NAME=?,MOTHER_NAME=?,"
					+ "COLLEGE_ID=?,DEPARTEMENT=?,SEMESTER=?,YEAR=?,date_of_birth=?,GENDER=?,MOBILE_NO=?,ADDRESS=? WHERE ID=?";
	System.out.println(query);
			long pk = sbase.getId();

			if (StudentModel.findByPk(sbase) == null) {
				throw new NoRecordFoundException("Exception : No record found");
			}
			if (StudentModel.findByPk(sbase) == null) {
				throw new NoRecordFoundException("Exception : No record found");
			}

			try {
				con1 = JDBCDataSource.getConnection();
				con1.setAutoCommit(false);
				ps1 = con1.prepareStatement(query);
				ps1.setString(1, sbase.getFirstName());
				ps1.setString(2, sbase.getLastName());
				ps1.setString(3, sbase.getFatherName());
				ps1.setString(4, sbase.getMotherName());
				ps1.setString(5, sbase.getCollegeId());
				ps1.setString(6, sbase.getDepartment());
				ps1.setInt(7, sbase.getSemester());
				ps1.setInt(8, sbase.getYear());
				ps1.setDate(9, (Date) sbase.getDateOfBirth());
				ps1.setString(10, sbase.getGender());
				ps1.setString(11, sbase.getMobileNumber());
				ps1.setString(12, sbase.getAddress());
				ps1.setLong(13, pk);
				ps1.executeUpdate();
				con1.commit();
				ps1.close();
			} 
			
			catch (Exception e) {
				JDBCDataSource.trnRollback(con1);
				throw new com.rakuten.exceptions.ApplicationException(e.getMessage());
			} 
			
			finally {
				JDBCDataSource.closeConnection(con1);
			}

			return pk;

		}

	}
 
}
