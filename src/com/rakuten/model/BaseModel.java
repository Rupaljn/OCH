package com.rakuten.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.rakuten.exceptions.ApplicationException;
import com.rakuten.util.JDBCDataSource;

public class BaseModel {
public static long nextNonBusinessPK(String tableName) throws ApplicationException{
	Connection con=null;
	long id = 0;
	
	try{
		con = JDBCDataSource.getConnection();
		Statement statement = con.createStatement();
		String query = "select max(uid) from "+tableName;
		ResultSet rs = statement.executeQuery(query);
		while(rs.next()){
				id = rs.getLong(1);
				
			}		
	}
			catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			} 
			
			finally {
				JDBCDataSource.closeConnection(con);
			}
	
       return id + 1;
	
}
}
