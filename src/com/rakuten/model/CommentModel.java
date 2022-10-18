package com.rakuten.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rakuten.entity.CommentBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class CommentModel extends BaseModel{
public long add(CommentBase cbase)throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("INSERT INTO st_comment (id,resourceid,text,user_id,resourcename) values(?,?,?,?,?)");
			ps.setLong(1, nextNonBusinessPK("st_comment"));
			ps.setLong(2, cbase.getResourceId());
			ps.setString(3, cbase.getText());
			ps.setLong(4, cbase.getUserId());
			ps.setString(5, cbase.getResourceName());
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (SQLException e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("CommentBean : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return 0;
	}

public long delete(CommentBase cbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = cbase.getId();

		if (findByPk(cbase) == null) {
			throw new NoRecordFoundException("CommentBean Exception : no record found");
		}
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM st_comment WHERE ID=?");
			ps.setLong(1, pk);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("CommentBean : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return pk;
	}

public long update(CommentBase cbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = cbase.getId();
		if (findByPk(cbase) == null) {
			throw new NoRecordFoundException("CommentBean Exception : no record found");
		}
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("UPDATE st_comment SET RESOURCEID=?,TEXT=?,RESOURCENAME=? WHERE ID=?");
			ps.setLong(1, cbase.getResourceId());
			ps.setString(2, cbase.getText());
			ps.setString(3, cbase.getResourceName());
			ps.setLong(4, pk);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("CommentBase : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return pk;

	}

public CommentBase findByPk(CommentBase cbase) throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long pk = cbase.getId();
		cbase = null;
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM st_comment WHERE ID=?");
			ps.setLong(1, pk);
			rs = ps.executeQuery();

			while (rs.next()) {
				cbase = new CommentBase();
				cbase.setId(rs.getLong(1));
				cbase.setResourceId(rs.getLong(2));
				cbase.setText(rs.getString(3));
				cbase.setUserId(rs.getLong(5));
				cbase.setResourceName(rs.getString(6));
			}

			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("CommentBean : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return cbase;
	}

public ArrayList<CommentBase> search(CommentBase cbase, int pageNo, int pageSize) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CommentBase> list = new ArrayList<CommentBase>();
		StringBuffer query = new StringBuffer("SELECT * FROM st_comment WHERE 1=1 ");
		if (cbase != null) {
			if (cbase.getId() > 0) {
				query.append(" and id =" + cbase.getId());
			}
			if (cbase.getResourceId() > 0) {
				query.append(" and resourceid =" + cbase.getResourceId());
			}
			if (cbase.getResourceName() != null && cbase.getResourceName().trim().length() > 0) {
				query.append(" and resourcename like'" + cbase.getResourceName() + "%'");

			}
			if (cbase.getUserId() > 0) {
				query.append(" and user_id =" + cbase.getUserId());
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
				cbase = new CommentBase();
				cbase.setId(rs.getLong(1));
				cbase.setResourceId(rs.getLong(2));
				cbase.setText(rs.getString(3));
				cbase.setUserId(rs.getLong(5));
				cbase.setResourceName(rs.getString(6));
				list.add(cbase);
			}
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("CommentBase : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}

}
