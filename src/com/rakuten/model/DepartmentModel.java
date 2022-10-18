package com.rakuten.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rakuten.entity.DepartmentBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.NoRecordFoundException;
import com.rakuten.util.JDBCDataSource;

public class DepartmentModel extends BaseModel{
public long add(DepartmentBase dbase) {

		Connection con = null;
		PreparedStatement ps = null;
		long pk = 0;
		try {
			
			con = JDBCDataSource.getConnection();
			pk = nextNonBusinessPK("ST_Department");
			con.setAutoCommit(false);
			ps = con.prepareStatement("INSERT INTO ST_Department(ID,CODE,NAME,DECRIPTION,COLLEGE_ID) VALUES(?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setLong(2, dbase.getCode());
			ps.setString(3, dbase.getName());
			ps.setString(4, dbase.getDescription());
			ps.setString(5, dbase.getCollegeId());
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

public long delete(DepartmentBase dbase) {
		Connection con = null;
		PreparedStatement ps = null;
		if (findByPk(dbase) == null) {
			throw new NoRecordFoundException("Exception : no record found");
		}
		long pk = dbase.getId();

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("DELETE FROM ST_Department WHERE ID=?");
			ps.setLong(1, pk);
			ps.executeUpdate();
			con.commit();
			ps.close();

		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			throw new ApplicationException("Exception : Department " + e.getMessage());
		}
		finally {
			JDBCDataSource.closeConnection(con);
		}
		return pk;
	}

public static DepartmentBase findByPk(DepartmentBase dbase) throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;
		long pk = dbase.getId();
		dbase=null;
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM ST_Department WHERE ID=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dbase = new DepartmentBase();
				dbase.setId(rs.getLong(1));
				dbase.setCode(rs.getInt(2));
				dbase.setName(rs.getString(3));
				dbase.setDescription(rs.getString(4));
				dbase.setCollegeId(rs.getString(5));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

		return dbase;
	}

public ArrayList<DepartmentBase> search(DepartmentBase dbase, int pageNo, int pageSize)
			throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<DepartmentBase> list = new ArrayList<DepartmentBase>();

		StringBuffer query = new StringBuffer("SELECT * FROM ST_Department WHERE 1=1 ");
		if (dbase != null) {
			if (dbase.getId() > 0) {
				query.append(" AND id =" + dbase.getId());
			}
			if (dbase.getCode() > 0) {
				query.append(" AND code =" + dbase.getCode());
			}
			if (dbase.getName() != null && dbase.getName().trim().length() > 0) {
				query.append(" AND name like '" + dbase.getName() + "%'");
			}
			if (dbase.getCollegeId() != null && dbase.getCollegeId().trim().length() > 0) {
				query.append(" AND college_id like '" + dbase.getCollegeId() + "%'");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.append(" limit" + pageNo + "," + pageSize);
		}

		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dbase = new DepartmentBase();
				dbase.setId(rs.getLong(1));
				dbase.setCode(rs.getInt(2));
				dbase.setName(rs.getString(3));
				dbase.setDescription(rs.getString(4));
				dbase.setCollegeId(rs.getString(5));
				list.add(dbase);
			}
		} 
		
		catch (Exception e) {

			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

		return list;
	}
public long update(DepartmentBase dbase)throws ApplicationException,NoRecordFoundException{
		
		Connection con=null;
		PreparedStatement ps=null;
		if(DepartmentModel.findByPk(dbase)==null){
			throw new NoRecordFoundException("Exception : no record found");
		}
		long pk=dbase.getId();
		try {
			con=JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps=con.prepareStatement("UPDATE ST_Department SET CODE=?,NAME=?,DECRIPTION=?,COLLEGE_ID=? WHERE ID=?");
			ps.setLong(1,dbase.getCode());
			ps.setString(2,dbase.getName());
			ps.setString(3,dbase.getDescription());
			ps.setString(4, dbase.getCollegeId());
			ps.setLong(5, pk);
			
System.out.println(ps.executeUpdate()+"test = "+dbase.getId());
			con.commit();
			ps.close();
		
		} 
		catch (Exception e) {
			JDBCDataSource.trnRollback(con);
			e.printStackTrace();
		throw new ApplicationException("de"+e.getMessage());
		}
		finally{
			JDBCDataSource.closeConnection(con);
		}
		
		return pk;
	}
}
