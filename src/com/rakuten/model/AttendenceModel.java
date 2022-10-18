package com.rakuten.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rakuten.entity.AttendenceBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class AttendenceModel extends BaseModel{
public long add(AttendenceBase abase) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = nextNonBusinessPK("st_attendence");

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("insert into st_attendence (id,STUDENT_ID,STUDENT_NAME,BRANCH_NAME,MONTH,YEAR,SUBJECT1,SUBJECT2,SUBJECT3,SUBJECT4,SUBJECT5,SUBJECT6,SUBJECT7,SUBJECT8,SUBJECT9,SUBJECT10,ATTENDENCE1,ATTENDENCE2,ATTENDENCE3,ATTENDENCE4,ATTENDENCE5,ATTENDENCE6,ATTENDENCE7,ATTENDENCE8,ATTENDENCE9,ATTENDENCE10,created_by,modified_by)"
							+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setLong(1, pk);
			ps.setLong(2, abase.getStudentId());
			ps.setString(3, abase.getStudentName());
			ps.setString(4, abase.getBranchName());
			ps.setString(5, abase.getMonth());
			ps.setInt(6, abase.getYear());
			ps.setString(7, abase.getSubject1());
			ps.setString(8, abase.getSubject2());
			ps.setString(9, abase.getSubject3());
			ps.setString(10, abase.getSubject4());
			ps.setString(11, abase.getSubject5());
			ps.setString(12, abase.getSubject6());
			ps.setString(13, abase.getSubject7());
			ps.setString(14, abase.getSubject8());
			ps.setString(15, abase.getSubject9());
			ps.setString(16, abase.getSubject10());
			ps.setInt(17, abase.getAttendence1());
			ps.setInt(18, abase.getAttendence2());
			ps.setInt(19, abase.getAttendence3());
			ps.setInt(20, abase.getAttendence4());
			ps.setInt(21, abase.getAttendence5());
			ps.setInt(22, abase.getAttendence6());
			ps.setInt(23, abase.getAttendence7());
			ps.setInt(24, abase.getAttendence8());
			ps.setInt(25, abase.getAttendence9());
			ps.setInt(26, abase.getAttendence10());
			ps.setString(27, abase.getCreatedBy());
			ps.setString(28, abase.getModifiedBy());
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		  catch (Exception e) {
		   JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("AttendenceBase Exception :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return 0;
	}

public long delete(AttendenceBase abase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = abase.getId();
		if (findByPk(abase) == null) {
			throw new NoRecordFoundException("AttendenceBase Exception : no record found");
		}
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM st_attendence WHERE ID=?");
			ps.setLong(1, pk);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

public long update(AttendenceBase abase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = abase.getId();
		if (findByPk(abase) == null) {
			throw new NoRecordFoundException("AttendenceBase Exception : no record found");
		}

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("UPDATE ST_ATTENDENCE  SET STUDENT_ID=?,"
					+ "STUDENT_NAME=?,BRANCH_NAME=?,MONTH=?,YEAR=?,SUBJECT1=?,"
					+ "SUBJECT2=?,SUBJECT3=?,SUBJECT4=?,SUBJECT5=?,SUBJECT6=?,"
					+ "SUBJECT7=?,SUBJECT8=?,SUBJECT9=?,SUBJECT10=?,ATTENDENCE1=?,"
					+ "ATTENDENCE2=?,ATTENDENCE3=?,ATTENDENCE4=?,ATTENDENCE5=?,"
					+ "ATTENDENCE6=?,ATTENDENCE7=?,ATTENDENCE8=?," + "ATTENDENCE9=?,ATTENDENCE10=?,created_by=?,modified_by=? WHERE ID=?");

			ps.setLong(1, abase.getStudentId());
			ps.setString(2, abase.getStudentName());
			ps.setString(3, abase.getBranchName());
			ps.setString(4, abase.getMonth());
			ps.setInt(5, abase.getYear());
			ps.setString(6, abase.getSubject1());
			ps.setString(7, abase.getSubject2());
			ps.setString(8, abase.getSubject3());
			ps.setString(9, abase.getSubject4());
			ps.setString(10, abase.getSubject5());
			ps.setString(11, abase.getSubject6());
			ps.setString(12, abase.getSubject7());
			ps.setString(13, abase.getSubject8());
			ps.setString(14, abase.getSubject9());
			ps.setString(15, abase.getSubject10());
			ps.setInt(16, abase.getAttendence1());
			ps.setInt(17, abase.getAttendence2());
			ps.setInt(18, abase.getAttendence3());
			ps.setInt(19, abase.getAttendence4());
			ps.setInt(20, abase.getAttendence5());
			ps.setInt(21, abase.getAttendence6());
			ps.setInt(22, abase.getAttendence7());
			ps.setInt(23, abase.getAttendence8());
			ps.setInt(24, abase.getAttendence9());
			ps.setInt(25, abase.getAttendence10());
			ps.setString(26, abase.getCreatedBy());
			ps.setString(27, abase.getModifiedBy());
			ps.setLong(28, pk);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		  catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("AttendenceBean Exception" + e.getMessage());
		} 
		  finally {
			JDBCDataSource.closeConnection(con);
		}

		return 0;
	}

public AttendenceBase findByPk(AttendenceBase abase) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long pk = abase.getId();
		abase = null;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT * FROM st_attendence WHERE ID=?");
			ps.setLong(1, pk);
			rs = ps.executeQuery();
			while (rs.next()) {
				abase = new AttendenceBase();
				abase.setId(rs.getLong(1));
				abase.setStudentId(rs.getLong(2));
				abase.setStudentName(rs.getString(3));
				abase.setBranchName(rs.getString(4));
				abase.setMonth(rs.getString(5));
				abase.setYear(rs.getInt(6));
				abase.setSubject1(rs.getString(7));
				abase.setSubject2(rs.getString(8));
				abase.setSubject3(rs.getString(9));
				abase.setSubject4(rs.getString(10));
				abase.setSubject5(rs.getString(11));
				abase.setSubject6(rs.getString(12));
				abase.setSubject7(rs.getString(13));
				abase.setSubject8(rs.getString(14));
				abase.setSubject9(rs.getString(15));
				abase.setSubject10(rs.getString(16));
				abase.setAttendence1(rs.getInt(17));
				abase.setAttendence2(rs.getInt(18));
				abase.setAttendence3(rs.getInt(19));
				abase.setAttendence4(rs.getInt(20));
				abase.setAttendence5(rs.getInt(21));
				abase.setAttendence6(rs.getInt(22));
				abase.setAttendence7(rs.getInt(23));
				abase.setAttendence8(rs.getInt(24));
				abase.setAttendence9(rs.getInt(25));
				abase.setAttendence10(rs.getInt(26));

			}
			con.commit();
			ps.close();
		} 
		  catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("AttendenceBase Exception" + e.getMessage());
		}  
		   finally {
			 JDBCDataSource.closeConnection(con);
		}

		return abase;
	}

public ArrayList<AttendenceBase> search(AttendenceBase abase, int pageNo, int pageSize)
			throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
ArrayList<AttendenceBase> list = new ArrayList<AttendenceBase>();
StringBuffer query = new StringBuffer("SELECT * FROM st_attendence WHERE 1=1 ");
		if (abase != null) {
			if (abase.getId() > 0) {
				query.append(" AND ID = " + abase.getId());
			}
			if (abase.getStudentId() > 0) {
				query.append(" AND STUDENT_ID =" + abase.getStudentId());
			}
			if (abase.getStudentName() != null && abase.getStudentName().length() > 0) {
				query.append(" AND STUDENT_NAME LIKE '" + abase.getStudentName() + "%'");
			}
		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * (pageSize-1);
			query.append(" Limit " + pageNo + ", " + pageSize);

		}
	

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				abase = new AttendenceBase();
				abase.setId(rs.getLong(1));
				abase.setStudentId(rs.getLong(2));
				abase.setStudentName(rs.getString(3));
				abase.setBranchName(rs.getString(4));
				abase.setMonth(rs.getString(5));
				abase.setYear(rs.getInt(6));
				abase.setSubject1(rs.getString(7));
				abase.setSubject2(rs.getString(8));
				abase.setSubject3(rs.getString(9));
				abase.setSubject4(rs.getString(10));
				abase.setSubject5(rs.getString(11));
				abase.setSubject6(rs.getString(12));
				abase.setSubject7(rs.getString(13));
				abase.setSubject8(rs.getString(14));
				abase.setSubject9(rs.getString(15));
				abase.setSubject10(rs.getString(16));
				abase.setAttendence1(rs.getInt(17));
				abase.setAttendence2(rs.getInt(18));
				abase.setAttendence3(rs.getInt(19));
				abase.setAttendence4(rs.getInt(20));
				abase.setAttendence5(rs.getInt(21));
				abase.setAttendence6(rs.getInt(22));
				abase.setAttendence7(rs.getInt(23));
				abase.setAttendence8(rs.getInt(24));
				abase.setAttendence9(rs.getInt(25));
				abase.setAttendence10(rs.getInt(26));
				list.add(abase);

			}
			  con.commit();
			  ps.close();
		} 
		  catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("AttendenceBean Exception" + e.getMessage());
		} 
		  finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}
}
