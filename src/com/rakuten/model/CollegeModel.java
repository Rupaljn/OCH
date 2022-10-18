package com.rakuten.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rakuten.entity.CollegeBase;
import com.rakuten.exceptions.ApplicationException;
import com.rakuten.exceptions.DuplicateRecordException;
import com.rakuten.util.JDBCDataSource;

public class CollegeModel extends BaseModel{
public long add(CollegeBase cbase) throws ApplicationException {
    String sql = "INSERT INTO ST_COLLEGE(id,College_name,address,state,city,phno,created_By,modified_by,created_datetime,modified_datetime,College_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	Connection con = null;
	long pk = 0;
	if(findByName(cbase.getName())!=null){
		throw new DuplicateRecordException("CollegeName is already exist");
	}
	if(findByCollegeId(cbase)!=null){
		throw new DuplicateRecordException("CollegeId is already exist");
	}
	  try {
			con = JDBCDataSource.getConnection();
			pk = nextNonBusinessPK("st_college");
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, cbase.getName());
			pstmt.setString(3, cbase.getAddress());
			pstmt.setString(4, cbase.getState());
			pstmt.setString(5, cbase.getCity());
			pstmt.setString(6, cbase.getPhoneNo());
			pstmt.setString(7, cbase.getCreatedBy());
			pstmt.setString(8, cbase.getModifiedBy());
			pstmt.setTimestamp(9, cbase.getCreatedDateTime());
			pstmt.setTimestamp(10, cbase.getModifiedDateTime());
			pstmt.setString(11, cbase.getCollegeId());

			int c = pstmt.executeUpdate();
			System.out.println("Rows Inserted" + c);
			con.commit();
			pstmt.close();
			} 
	         catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Exception in Inserting Data ");
			} 
	         finally {
				JDBCDataSource.closeConnection(con);
			}
			return pk;
		}

public void delete(CollegeBase cbase) throws ApplicationException {

	Connection con = null;
	try {
		String query = "DELETE FROM ST_COLLEGE WHERE ID=?";
		con = JDBCDataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setLong(1, cbase.getId());
		pstmt.executeUpdate();
		pstmt.close();

		} 
	      catch (Exception e) {
				JDBCDataSource.trnRollback(con);
				throw new ApplicationException(""+e.getMessage());
			} 
	         finally {
				JDBCDataSource.closeConnection(con);
			}
		}

public CollegeBase findByName(String name) throws ApplicationException {
			Connection con = null;
			CollegeBase cbase = null;
			try {
				con = JDBCDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ST_COLLEGE WHERE COLLEGE_NAME = ?");
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					cbase = new CollegeBase();

					cbase.setId(rs.getLong(1));
					cbase.setCollegeId(rs.getString(2));
					cbase.setName(rs.getString(3));
					cbase.setAddress(rs.getString(4));
					cbase.setState(rs.getString(5));
					cbase.setCity(rs.getString(6));
					cbase.setPhoneNo(rs.getString(7));
					cbase.setCreatedBy(rs.getString(8));
					cbase.setModifiedBy(rs.getString(9));
					cbase.setCreatedDateTime(rs.getTimestamp(10));
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in getting College by Name");
			} 
			finally {
				JDBCDataSource.closeConnection(con);
			}
			return cbase;
		}

public CollegeBase findByCollegeId(CollegeBase cbase) throws ApplicationException {
			Connection con = null;
			String collegeId = cbase.getCollegeId();
			cbase=null;
			try {
				con = JDBCDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM ST_COLLEGE WHERE COLLEGE_ID = ?");
				pstmt.setString(1, collegeId);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					cbase = new CollegeBase();
					cbase.setId(rs.getLong(1));
					cbase.setCollegeId(rs.getString(2));
					cbase.setName(rs.getString(3));
					cbase.setAddress(rs.getString(4));
					cbase.setState(rs.getString(5));
					cbase.setCity(rs.getString(6));
					cbase.setPhoneNo(rs.getString(7));
					cbase.setCreatedBy(rs.getString(8));
					cbase.setModifiedBy(rs.getString(9));
					cbase.setCreatedDateTime(rs.getTimestamp(10));
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in getting College by Name");
			} 
			finally {
				JDBCDataSource.closeConnection(con);
			}
			return cbase;
		}

public CollegeBase findByPK(CollegeBase cbase) throws ApplicationException {
 StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE ID=?");
 Connection con = null;
			
 long pk = cbase.getId();
 cbase = null;
	try {

		con = JDBCDataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		pstmt.setLong(1, pk);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			cbase = new CollegeBase();
			cbase.setId(rs.getLong(1));
			cbase.setCollegeId(rs.getString(2));
			cbase.setName(rs.getString(3));
			cbase.setAddress(rs.getString(4));
			cbase.setState(rs.getString(5));
			cbase.setCity(rs.getString(6));
			cbase.setPhoneNo(rs.getString(7));
			cbase.setCreatedBy(rs.getString(8));
			cbase.setModifiedBy(rs.getString(9));
			cbase.setCreatedDateTime(rs.getTimestamp(10));
			cbase.setModifiedDateTime(rs.getTimestamp(11));
				}
				rs.close();
				pstmt.close();
			} 
	          catch (Exception e) {
				throw new ApplicationException("Exception :"+e.getMessage());
			} 
	          finally {
				JDBCDataSource.closeConnection(con);
			}
			return cbase;
		}

public void update(CollegeBase cbase) throws ApplicationException,DuplicateRecordException{

CollegeBase cbeanExist = findByName(cbase.getName());
Connection con = null;

if (cbeanExist != null && cbeanExist.getId() != cbase.getId()) {

	throw new DuplicateRecordException("College is already exist");
	}

	try {
		
		String query = "UPDATE ST_COLLEGE SET COLLEGE_NAME=?,ADDRESS=?,STATE=?,CITY=?,PHNO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,COLLEGE_ID=? WHERE ID=?";
		con = JDBCDataSource.getConnection();

		con.setAutoCommit(false); 
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, cbase.getName());
		pstmt.setString(2, cbase.getAddress());
		pstmt.setString(3, cbase.getState());
		pstmt.setString(4, cbase.getCity());
		pstmt.setString(5, cbase.getPhoneNo());
		pstmt.setString(6, cbase.getCreatedBy());
		pstmt.setString(7, cbase.getModifiedBy());
		pstmt.setTimestamp(8, cbase.getCreatedDateTime());
		pstmt.setTimestamp(9, cbase.getModifiedDateTime());
		pstmt.setString(10,cbase.getCollegeId());
		pstmt.setLong(11, cbase.getId());
		int c = pstmt.executeUpdate();
		con.commit(); 
		pstmt.close();

	} 
	  catch (Exception e) {
		e.printStackTrace();
		throw new ApplicationException("Exception : Delete rollback exception " + e.getMessage());
		} 
	      finally {
				JDBCDataSource.closeConnection(con);
			}
		}

public ArrayList<CollegeBase> search(CollegeBase cbase, int pageNo, int pageSize) throws ApplicationException {

  Connection con = null;
  StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE 1=1 ");

	if (cbase != null) {
	
	if (cbase.getName() != null && cbase.getName().trim().length() > 0) {
		sql.append(" AND COLLEGE_NAME like '" + cbase.getName() + "%'");
	}
				
	}

       if (pageSize > 0) {
	    pageNo = (pageNo - 1) * pageSize;
		sql.append(" Limit " + pageNo + ", " + pageSize);
			}

		ArrayList<CollegeBase> list = new ArrayList<CollegeBase>();

			try {
				con = JDBCDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				System.out.println(sql);
				while (rs.next()) {
					cbase = new CollegeBase();
					cbase.setId(rs.getLong(1));
					cbase.setCollegeId(rs.getString(2));
					cbase.setName(rs.getString(3));
					cbase.setAddress(rs.getString(4));
					cbase.setState(rs.getString(5));
					cbase.setCity(rs.getString(6));
					cbase.setPhoneNo(rs.getString(7));
					cbase.setCreatedBy(rs.getString(8));
					cbase.setModifiedBy(rs.getString(9));
					cbase.setCreatedDateTime(rs.getTimestamp(10));
					cbase.setModifiedDateTime(rs.getTimestamp(11));
					list.add(cbase);
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in search college");
			} 
			finally {
				JDBCDataSource.closeConnection(con);
			}

			return list;
		}

public ArrayList<CollegeBase> list(int pageNo, int pageSize) throws ApplicationException {
	Connection con = null;
	ArrayList<CollegeBase> list = new ArrayList<CollegeBase>();

	StringBuffer sql = new StringBuffer("select * from ST_COLLEGE where 1=1 ");
	if (pageSize > 0) {
	pageNo = (pageNo - 1) * pageSize;
	sql.append(" limit " + pageNo + "," + pageSize);
			
	}

	  try {
			
		  con = JDBCDataSource.getConnection();
		  PreparedStatement pstmt = con.prepareStatement(sql.toString());
		  ResultSet rs = pstmt.executeQuery();
		  while (rs.next()) {
		CollegeBase cbase = new CollegeBase();
		cbase.setId(rs.getLong(1));
		cbase.setCollegeId(rs.getString(2));
		cbase.setName(rs.getString(3));
		cbase.setAddress(rs.getString(4));
		cbase.setState(rs.getString(5));
		cbase.setCity(rs.getString(6));
		cbase.setPhoneNo(rs.getString(7));
		cbase.setCreatedBy(rs.getString(8));
		cbase.setModifiedBy(rs.getString(9));
		cbase.setCreatedDateTime(rs.getTimestamp(10));
		cbase.setModifiedDateTime(rs.getTimestamp(11));
		list.add(cbase);
		}
				rs.close();
			} 
	  catch (Exception e) {
				throw new ApplicationException("Exception : Exception in getting list of users");
			} 
	  finally {
				JDBCDataSource.closeConnection(con);
			}

			return list;

		}
public void main(String[] args) {

	CollegeBase cbase = new CollegeBase();
	cbase.setCollegeId("collegeId");
	cbase.setName("name");
	cbase.setAddress("address");
	cbase.setState("state");
	cbase.setCity("city");
	cbase.setPhoneNo("phoneNo");
	System.out.println(CollegeModel.add(cbase));
		
	}
}
