package com.rakuten.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rakuten.entity.RoleBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.DuplicateRecordException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class RoleModel extends BaseModel{
public static RoleBase findByRoleName(RoleBase rbase) throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = rbase.getName();
		rbase = null;
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("select * from st_role where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				rbase = new RoleBase();
				rbase.setId(rs.getLong(1));
				rbase.setName(rs.getString(2));
				rbase.setDescription(rs.getString(3));
				rbase.setCreatedBy(rs.getString(4));
				rbase.setModifiedBy(rs.getString(5));
				rbase.setCreatedDateTime(rs.getTimestamp(6));
				rbase.setModifiedDateTime(rs.getTimestamp(7));

			}
			ps.close();
		} 
		catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return rbase;

	}

public long add(RoleBase rbase) throws ApplicationException, DuplicateRecordException {
		Connection con = null;
		PreparedStatement ps = null;
		long pk = nextNonBusinessPK("st_role");

		if (RoleModel.findByRoleName(rbase) != null) {
			throw new DuplicateRecordException("RoleBase Exception : Record already exitst");
		}
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("INSERT INTO st_role (id,NAME,description,created_by,modified_by) VALUES (?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, rbase.getName());
			ps.setString(3, rbase.getDescription());
			ps.setString(4, rbase.getCreatedBy());
			ps.setString(5, rbase.getModifiedBy());
			ps.executeUpdate();
			con.setAutoCommit(true);

		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("RoleBase Exception : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return pk;

	}

public long delete(RoleBase rbase) throws ApplicationException, NoRecordFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = rbase.getId();

		if (findByPK(rbase) == null) {
			throw new NoRecordFoundException("RoleBase Exception : NoRecordFoundException ");
		}
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM st_role WHERE id =?");
			ps.setLong(1, pk);
			ps.executeUpdate();
			con.setAutoCommit(true);
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("RoleBean Exception : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

public static long update(RoleBase rbase) throws ApplicationException, NoRecordFoundException {

		if (RoleModel.findByPK(rbase) == null) {
			throw new NoRecordFoundException("Exception : no record found");
		}

		Connection con = null;
		PreparedStatement ps = null;
		String name = rbase.getName();
		String description = rbase.getDescription();
		long pk = rbase.getId();

		try {
			con = JDBCDataSource.getConnection();

			con.setAutoCommit(false);
			ps = con.prepareStatement("UPDATE ST_ROLE SET NAME=?,DESCRIPTION=? WHERE ID=?");
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setLong(3, pk);

			ps.executeUpdate();

			con.commit();
			ps.close();

		} 
		catch (Exception e) {

			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("RoleBean Exception :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk;
	}

	
public ArrayList<RoleBase> getList(RoleBase rbase, int pageNo, int pageSize) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<RoleBase> list = new ArrayList<RoleBase>();
		StringBuffer query = new StringBuffer("SELECT * FROM ST_ROLE WHERE 1=1");

		if (rbase.getId() > 0) {
			query.append(" AND id = " + rbase.getId());
		}
		if (rbase.getName() != null && rbase.getName().trim().length() > 0) {
			query.append(" AND NAME like '" + rbase.getName() + "%'");
		}
		if (rbase.getDescription() != null && rbase.getDescription().trim().length() > 0) {
			query.append(" AND DESCRIPTION like '" + rbase.getDescription() + "%'");
		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;
			query.append(" Limit " + pageNo + ", " + pageSize);

		}

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rbase = new RoleBase();
				rbase.setId(rs.getLong(1));
				rbase.setName(rs.getString(2));
				rbase.setDescription(rs.getString(3));
				rbase.setCreatedBy(rs.getString(4));
				rbase.setModifiedBy(rs.getString(5));
				rbase.setCreatedDateTime(rs.getTimestamp(6));
				rbase.setModifiedDateTime(rs.getTimestamp(7));
				list.add(rbase);

			}
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("RoleBean Exception : " + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}

public ArrayList<RoleBase> search(RoleBase rbase, int pageNo, int pageSize) throws ApplicationException {

		ArrayList<RoleBase> list = new ArrayList<RoleBase>();
		Connection con = null;
		PreparedStatement ps = null;
		StringBuffer query = new StringBuffer("select id,name,description from st_role where 1=1 ");

		if (rbase.getId() > 0) {
			query.append(" AND id=" + rbase.getId());
		}

		if (rbase.getName() != null && rbase.getName().trim().length() > 0) {
			query.append(" AND name like'" + rbase.getName() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.append(" limit " + pageNo + "," + pageSize);
		}
		System.out.println("Rolebean :" + query);
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rbase = new RoleBase();
				rbase.setId(rs.getLong(1));
				rbase.setName(rs.getString(2));
				rbase.setDescription(rs.getString(3));
				list.add(rbase);
			}
			con.commit();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("RoleBean Exception :" + e.getMessage());

		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}

public ArrayList<RoleBase> list() throws ApplicationException {

		ArrayList<RoleBase> list = new ArrayList<RoleBase>();
		Connection con = null;
		PreparedStatement ps = null;
		StringBuffer query = new StringBuffer("select id,name,description from st_role where 1=1 ");

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RoleBase rbase = new RoleBase();
				rbase.setId(rs.getLong("id"));
				rbase.setName(rs.getString("name"));
				rbase.setDescription(rs.getString("description"));
				list.add(rbase);
			}
			con.commit();
			ps.close();
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
			throw new ApplicationException("RoleBean Exception :" + e.getMessage());

		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}

public static RoleBase findByPK(RoleBase rbase) throws ApplicationException {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = rbase.getId();
		rbase = null;
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("select * from st_role where id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				rbase = new RoleBase();
				rbase.setId(rs.getLong(1));
				rbase.setName(rs.getString(2));
				rbase.setDescription(rs.getString(3));
				rbase.setCreatedBy(rs.getString(4));
				rbase.setModifiedBy(rs.getString(5));
				rbase.setCreatedDateTime(rs.getTimestamp(6));
				rbase.setModifiedDateTime(rs.getTimestamp(7));
			}

		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("RoleBean Exception :" + e.getMessage());
		} 
		finally {
			JDBCDataSource.closeConnection(con);
		}

		return rbase;
	}
}
