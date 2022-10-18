package com.rakuten.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rakuten.entity.EResourceBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class EresourceModel extends BaseModel{
public long add(EResourceBase erbase) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = nextNonBusinessPK("ST_ERESOURCE");
		String query = "INSERT INTO ST_ERESOURCE (ID,TABLES_CONTAINS,NAME,DETAIL,created_by,modified_by) VALUES(?,?,?,?,?,?)";
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setLong(1, pk);
			ps.setString(2, erbase.getTableContains());
			ps.setString(3, erbase.getName());
			ps.setString(4, erbase.getDetail());
			ps.setString(5, erbase.getCreatedBy());
			ps.setString(6, erbase.getModifiedBy());
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();

			throw new ApplicationException("EresourceBean :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

public long delete(EResourceBase erbase) throws NoRecordFoundException, ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;
		if (findByPk(erbase) == null) {
			throw new NoRecordFoundException("EresourceBean : no record found");
		}
		long pk = erbase.getId();
		String query = "DELETE FROM ST_ERESOURCE WHERE ID=?";
	
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setLong(1, pk);
			ps.executeUpdate();
			con.commit();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("EresourceBean : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return pk;
	}

public long update(EResourceBase erbase) throws NoRecordFoundException, ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		if (findByPk(erbase) == null) {
			throw new NoRecordFoundException("EresourceBean : no record found");
		}
		long pk = erbase.getId();

		String query = "UPDATE ST_ERESOURCE SET tables_contains=?,name=?,detail=? WHERE ID=?";
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setString(1, erbase.getTableContains());
			ps.setString(2, erbase.getName());
			ps.setString(3, erbase.getDetail());
			ps.setLong(4, pk);
			ps.executeUpdate();
			con.commit();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("EresourceBean : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return pk;

	}

public EResourceBase findByPk(EResourceBase erbase) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long pk = erbase.getId();
		erbase = null;
		String query = "SELECT * FROM ST_ERESOURCE WHERE ID=?";
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, pk);
			rs = ps.executeQuery();
			while (rs.next()) {
				erbase = new EResourceBase();
				erbase.setId(rs.getLong(1));
				erbase.setTableContains(rs.getString(2));
				erbase.setName(rs.getString(3));
				erbase.setDetail(rs.getString(4));

			}

		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("EresourceBean : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return erbase;

	}

public ArrayList<EResourceBase> search(EResourceBase erbase, int pageNo, int pageSize)
			throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<EResourceBase> list = new ArrayList<EResourceBase>();
		StringBuffer query = new StringBuffer("SELECT * FROM st_eresource WHERE 1=1");

		if (erbase != null) {
			if (erbase.getId() > 0) {
				query.append(" AND id=" + erbase.getId());
			}
			if (erbase.getName() != null && erbase.getName().trim().length() > 0) {
				query.append(" AND name like '" + erbase.getName() + "%'");
			}
			if (erbase.getTableContains() != null && erbase.getTableContains().trim().length() > 0) {
				query.append(" AND tables_contains like '" + erbase.getTableContains() + "%'");
			}
			if (erbase.getDetail() != null && erbase.getDetail().trim().length() > 0) {
				query.append(" AND detail like '" + erbase.getDetail() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.append(" limit " + pageNo + "," + pageSize);
		}

		
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement(query.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				erbase = new EResourceBase();
				erbase.setId(rs.getLong(1));
				erbase.setTableContains(rs.getString(2));
				erbase.setName(rs.getString(3));
				erbase.setDetail(rs.getString(4));
				list.add(erbase);

			}

		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("EresourceBean : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return list;

	}
}
