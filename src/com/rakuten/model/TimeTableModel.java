package com.rakuten.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.rakuten.entity.TimeTableBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class TimeTableModel extends BaseModel{
public long add(TimeTableBase ttbase) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = nextNonBusinessPK("st_timetable");
		Date date = null;
		if (ttbase != null && ttbase.getDate()!=null) {
			date = new Date(ttbase.getDate().getTime());
		}
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("INSERT INTO ST_TIMETABLE(ID,BRANCH,SEMESTER,YEAR,SUBJECT,DATE,TIME,FACULTY_ID) VALUES(?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, ttbase.getBranch());
			ps.setInt(3, ttbase.getSemester());
			ps.setInt(4, ttbase.getYear());
			ps.setString(5, ttbase.getSubject());
			ps.setDate(6, (java.sql.Date) date);
			ps.setString(7, ttbase.getTime());
			ps.setLong(8, ttbase.getFacultyId());
			ps.executeUpdate();
			con.commit();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("TimeTableBean :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

	public long delete(TimeTableBase ttbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = ttbase.getId();
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
			ps.setLong(1, pk);
			ps.executeUpdate();
			con.commit();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("TimeTableBean :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

public long update(TimeTableBase ttbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = ttbase.getId();
		Date date = new Date(ttbase.getDate().getTime());
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"UPDDATE ST_TIMETABLE SET BRANCH=?,SEMESTER=?,YEAR=?,SUBJECT=?,DATE=?,TIME=?,FACULTY_ID=? WHERE ID=?");
			ps.setString(1, ttbase.getBranch());
			ps.setInt(2, ttbase.getSemester());
			ps.setInt(3, ttbase.getYear());
			ps.setString(4, ttbase.getSubject());
			ps.setDate(5, (java.sql.Date) date);
			ps.setString(6, ttbase.getTime());
			ps.setLong(7, ttbase.getFacultyId());
			ps.setLong(8, pk);

			con.commit();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("TimeTableBean :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

public static TimeTableBase findByPk(TimeTableBase ttbase) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		long pk = ttbase.getId();
		ttbase = null;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT * FROM ST_TIMETABLE WHERE ID=?");
			ps.setLong(1, pk);
			rs = ps.executeQuery();
			while (rs.next()) {
				ttbase = new TimeTableBase();
				ttbase.setId(rs.getLong(1));
				ttbase.setBranch(rs.getString(2));
				ttbase.setSemester(rs.getInt(3));
				ttbase.setYear(rs.getInt(4));
				ttbase.setSubject(rs.getString(5));
				ttbase.setDate(rs.getDate(6));
				ttbase.setTime(rs.getString(7));
				ttbase.setFacultyId(rs.getLong(8));

			}

			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("TimeTableBean :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return ttbase;
	}

	public ArrayList<TimeTableBase> search(TimeTableBase ttbase, int pageNo, int pageSize)
			throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<TimeTableBase> list = new ArrayList<TimeTableBase>();
		StringBuffer query = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");

		if (ttbase != null) {
			if (ttbase.getId() > 0) {
				query.append(" and id =" + ttbase.getId());
			}
			if (ttbase.getBranch() != null && ttbase.getBranch().trim().length() > 0) {
				query.append(" and branch like '" + ttbase.getBranch() + "%'");
			}
			if (ttbase.getSemester() > 0) {
				query.append(" and semester =" + ttbase.getSemester());
			}
			if (ttbase.getYear() > 0) {
				query.append(" and year =" + ttbase.getYear());
			}
			if (ttbase.getSubject() != null && ttbase.getSubject().trim().length() > 0) {
				query.append(" and subject like '" + ttbase.getSubject() + "%'");
			}
			if (ttbase.getDate() != null) {
				query.append(" and date = " + ttbase.getDate());
			}
			if (ttbase.getTime() != null) {
				query.append(" and time =" + ttbase.getTime());
			}
			if (ttbase.getFacultyId() > 0) {
				query.append(" and faculty_id =" + ttbase.getFacultyId());
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.append(" limit " + pageNo + "," + pageSize);
		}

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				ttbase = new TimeTableBase();
				ttbase.setId(rs.getLong(1));
				ttbase.setBranch(rs.getString(2));
				ttbase.setSemester(rs.getInt(3));
				ttbase.setYear(rs.getInt(4));
				ttbase.setSubject(rs.getString(5));
				ttbase.setDate(rs.getDate(6));
				ttbase.setTime(rs.getString(7));
				ttbase.setFacultyId(rs.getLong(8));
				list.add(ttbase);
			}

			con.commit();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("TimeTableBean :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}

}
