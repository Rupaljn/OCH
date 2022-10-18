package com.rakuten.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rakuten.entity.StaffBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.DuplicateRecordException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class StaffModel extends BaseModel{
public long add(StaffBase stfbase) {

		if (StaffModel.findByPk(stfbase) != null) {
			throw new DuplicateRecordException("Exception : Id(pk) already exitst");
		}
		if (StaffModel.findByUserId(stfbase) != null) {
			throw new DuplicateRecordException("Exception : UserId already exitst");
		}

		Connection con = null;
		PreparedStatement ps = null;
		long UserId = stfbase.getUserId();
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("INSERT INTO ST_STAFF (ID,FIRST_NAME,LAST_NAME,"
					+ "FATHER_NAME,MOTHER_NAME,COLLEGE_ID,DEPARTEMENT,"
					+ "SEMESTER,YEAR,DATE_OF_BIRTH,GENDER,MOBILE_NO,ADDRESS,USER_ID) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setLong(1, nextNonBusinessPK("ST_STAFF"));
			ps.setString(2, stfbase.getFirstName());
			ps.setString(3, stfbase.getLastName());
			ps.setString(4, stfbase.getFatherName());
			ps.setString(5, stfbase.getMotherName());
			ps.setLong(6, stfbase.getCollegeId());
			ps.setString(7, stfbase.getDepartement());
			ps.setInt(8, stfbase.getSemester());
			ps.setInt(9, stfbase.getYear());
			ps.setDate(10, (Date) stfbase.getDateOfBirth());
			ps.setString(11, stfbase.getGender());
			ps.setString(12, stfbase.getMobileNo());
			ps.setString(13, stfbase.getAddress());
			ps.setLong(14, stfbase.getUserId());

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

public long delete(StaffBase stfbase) {

		if (StaffModel.findByPk(stfbase) == null) {
			throw new NoRecordFoundException("Exception : no record found");
		}

		long id = stfbase.getId();
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM ST_STAFF WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			con.commit();
			ps.close();

		} 
		catch (Exception e) {
			throw new ApplicationException("Exception : Exception in delete Student" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		
			return id;
		
	}

public static StaffBase findByPk(StaffBase stfbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long id = stfbase.getId();
		stfbase = null;
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM ST_STAFF WHERE ID=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				stfbase = new StaffBase();
				stfbase.setId(rs.getLong(1));
				stfbase.setFirstName(rs.getString(2));
				stfbase.setLastName(rs.getString(3));
				stfbase.setFatherName(rs.getString(4));
				stfbase.setMotherName(rs.getString(5));
				stfbase.setCollegeId(rs.getLong(6));
				stfbase.setDepartement(rs.getString(7));
				stfbase.setSemester(rs.getInt(8));
				stfbase.setYear(rs.getInt(9));
				stfbase.setDateOfBirth(rs.getDate(10));
				stfbase.setGender(rs.getString(11));
				stfbase.setMobileNo(rs.getString(12));
				stfbase.setAddress(rs.getString(13));
				stfbase.setUserId(rs.getLong(14));

			}

		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException(e.getMessage());
		}
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return stfbase;

	}

public static StaffBase findByUserId(StaffBase stfbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long userId = stfbase.getUserId();
		stfbase = null;
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM ST_STAFF WHERE USER_ID=?");
			ps.setLong(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				stfbase = new StaffBase();
				stfbase.setId(rs.getLong(1));
				stfbase.setFirstName(rs.getString(2));
				stfbase.setLastName(rs.getString(3));
				stfbase.setFatherName(rs.getString(4));
				stfbase.setMotherName(rs.getString(5));
				stfbase.setCollegeId(rs.getLong(6));
				stfbase.setDepartement(rs.getString(7));
				stfbase.setSemester(rs.getInt(8));
				stfbase.setYear(rs.getInt(9));
				stfbase.setDateOfBirth(rs.getDate(10));
				stfbase.setGender(rs.getString(11));
				stfbase.setMobileNo(rs.getString(12));
				stfbase.setAddress(rs.getString(13));
				stfbase.setUserId(rs.getLong(14));

			}

		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException(e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return stfbase;

	}

public ArrayList<StaffBase> search(StaffBase stfbase, int pageNo, int pageSize)
			throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
ArrayList<StaffBase> list = new ArrayList<StaffBase>();
		StringBuffer query = new StringBuffer("SELECT * FROM ST_STAFF WHERE 1=1");

		/* logic for dynamic search or for creating query */
		if (stfbase != null) {
			if (stfbase.getId() > 0) {
				query.append(" AND id =" + stfbase.getId());
			}
			if (stfbase.getFirstName() != null && stfbase.getFirstName().trim().length() > 0) {
				query.append(" AND first_name like '" + stfbase.getFirstName() + "%'");
			}
			if (stfbase.getLastName() != null && stfbase.getLastName().trim().length() > 0) {
				query.append(" AND last_name like '" + stfbase.getLastName() + "%'");
			}
			if (stfbase.getFatherName() != null && stfbase.getFatherName().trim().length() > 0) {
				query.append(" AND father_name like '" + stfbase.getFatherName() + "%'");
			}
			if (stfbase.getMotherName() != null && stfbase.getMotherName().trim().length() > 0) {
				query.append(" AND mother_name like '" + stfbase.getMotherName() + "%'");
			}
			if (stfbase.getMobileNo() != null && stfbase.getMobileNo().trim().length() > 0) {
				query.append(" AND mobile_no like '" + stfbase.getMobileNo() + "%'");
			}
			if (stfbase.getGender() != null && stfbase.getGender().trim().length() > 0) {
				query.append(" AND gender like '" + stfbase.getGender() + "%'");
			}
			if (stfbase.getSemester() > 0) {
				query.append(" AND semester = " + stfbase.getSemester());
			}
			if (stfbase.getYear() > 0) {
				query.append(" AND year = " + stfbase.getYear());
			}
			if (stfbase.getDepartement() != null && stfbase.getDepartement().trim().length() > 0) {
				query.append(" AND departement like '" + stfbase.getDepartement() + "%'");
			}
			if (stfbase.getCollegeId() > 0) {
				query.append(" AND college_id = " + stfbase.getCollegeId());
			}
			if (stfbase.getUserId() > 0) {
				query.append(" AND user_id = " + stfbase.getUserId());
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.append(" limit " + pageNo + "," + pageSize);
		}
	System.out.println(query);
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				stfbase = new StaffBase();
				stfbase.setId(rs.getLong(1));
				stfbase.setFirstName(rs.getString(2));
				stfbase.setLastName(rs.getString(3));
				stfbase.setFatherName(rs.getString(4));
				stfbase.setMotherName(rs.getString(5));
				stfbase.setCollegeId(rs.getLong(6));
				stfbase.setDepartement(rs.getString(7));
				stfbase.setSemester(rs.getInt(8));
				stfbase.setYear(rs.getInt(9));
				stfbase.setDateOfBirth(rs.getDate(10));
				stfbase.setGender(rs.getString(11));
				stfbase.setMobileNo(rs.getString(12));
				stfbase.setAddress(rs.getString(13));
				stfbase.setUserId(rs.getLong(14));
				list.add(stfbase);

			}

			con.setAutoCommit(true);
			ps.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception from StaffModel");
		}
		return list;

	}

public long update(StaffBase stfbase) throws NoRecordFoundException, ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		String query = "UPDATE ST_STAFF SET FIRST_NAME=?,LAST_NAME=?,FATHER_NAME=?,MOTHER_NAME=?,"
				+ "COLLEGE_ID=?,DEPARTEMENT=?,SEMESTER=?,YEAR=?,date_of_birth=?,GENDER=?,MOBILE_NO=?,ADDRESS=? WHERE ID=?";

		long pk = stfbase.getId();

		if (StaffModel.findByPk(stfbase) == null) {
			throw new NoRecordFoundException("Exception : No record found");
		}
		if (StaffModel.findByUserId(stfbase) == null) {
			throw new NoRecordFoundException("Exception : No record found");
		}

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setString(1, stfbase.getFirstName());
			ps.setString(2, stfbase.getLastName());
			ps.setString(3, stfbase.getFatherName());
			ps.setString(4, stfbase.getMotherName());
			ps.setLong(5, stfbase.getCollegeId());
			ps.setString(6, stfbase.getDepartement());
			ps.setInt(7, stfbase.getSemester());
			ps.setInt(8, stfbase.getYear());
			ps.setDate(9, (Date) stfbase.getDateOfBirth());
			ps.setString(10, stfbase.getGender());
			ps.setString(11, stfbase.getMobileNo());
			ps.setString(12, stfbase.getAddress());
			ps.setLong(13, pk);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException(e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;

	}	

}
