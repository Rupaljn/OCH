package com.rakuten.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rakuten.entity.NoticeBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class NoticeModel extends BaseModel{
public long add(NoticeBase nbase) throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;

		Date createdOn = new java.sql.Date(nbase.getCreateOn().getTime());
		Date expireDate = new java.sql.Date(nbase.getExpireDate().getTime());

		long pk = nextNonBusinessPK("st_notice");
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(
					"INSERT INTO st_notice  (ID,SUBJECT,DETAILS,CREATED_ON,EXPIRE_DATE) VALUES(?,?,?,?,?) ");
			ps.setLong(1, pk);
			ps.setString(2, nbase.getSubject());
			ps.setString(3, nbase.getDetails());
			ps.setDate(4, createdOn);
			ps.setDate(5, expireDate);

			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("NoteiceBean" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

public long delete(NoticeBase nbase) throws ApplicationException, NoRecordFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		long pk = nbase.getId();
		if (findByPk(nbase) == null) {
			throw new NoRecordFoundException("NoticeBean Exception : no record found");
		}

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM ST_NOTICE WHERE ID=?");
			ps.setLong(1, pk);
			
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("NoteiceBean" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}
public long update(NoticeBase nbase) throws ApplicationException, NoRecordFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		long pk = nbase.getId();
		if (findByPk(nbase) == null) {
			throw new NoRecordFoundException("NoticeBean Exception : no record found");
		}

Date createdOn = new java.sql.Date(nbase.getCreateOn().getTime());
Date expireDate = new java.sql.Date(nbase.getExpireDate().getTime());

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("UPDATE ST_NOTICE SET SUBJECT=?,DETAILS=?,created_on=?,EXPIRE_DATE=? WHERE ID=?");

			ps.setString(1, nbase.getSubject());
			ps.setString(2, nbase.getDetails());
			ps.setDate(3, createdOn);
			ps.setDate(4, expireDate);
			ps.setLong(5, pk);

			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("NoteiceBean" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

public NoticeBase findByPk(NoticeBase nbase) throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long pk = nbase.getId();
		nbase = null;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT * FROM st_notice WHERE ID=?");
			ps.setLong(1, pk);
			rs = ps.executeQuery();
			while (rs.next()) {
				nbase = new NoticeBase();
				nbase.setId(rs.getLong(1));
				nbase.setSubject(rs.getString(2));
				nbase.setDetails(rs.getString(3));
				nbase.setCreateOn(rs.getDate(4));
				nbase.setExpireDate(rs.getDate(5));
			}

			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("NoteiceBean" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return nbase;
	}

public ArrayList<NoticeBase> search(NoticeBase nbase, int pageNo, int pageSize) throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<NoticeBase> list = new ArrayList<NoticeBase>();
		StringBuffer query = new StringBuffer("SELECT * FROM st_notice WHERE 1=1");
		if (nbase != null) {
			if (nbase.getId() > 0) {
				query.append(" and id=" + nbase.getId());
			}
			if (nbase.getSubject() != null && nbase.getSubject().trim().length() > 0) {
				query.append(" and subject like '" + nbase.getSubject() + "%'");
			}
			if (nbase.getCreateOn() != null) {
				query.append(" and created_on =" + nbase.getCreateOn());
			}
			if (nbase.getExpireDate() != null) {
				query.append(" and expire_date =" + nbase.getExpireDate());
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
				nbase = new NoticeBase();
				nbase.setId(rs.getLong(1));
				nbase.setSubject(rs.getString(2));
				nbase.setDetails(rs.getString(3));
				nbase.setCreateOn(rs.getDate(4));
				nbase.setExpireDate(rs.getDate(5));
				list.add(nbase);
			}

			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("NoteiceBean" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}
}
