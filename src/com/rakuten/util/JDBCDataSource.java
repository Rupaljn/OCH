package com.rakuten.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDataSource {
public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/OCH", "root", "root");
			
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}


public void closeConnection(Connection con){
	try{
	con.close();
	}
	
	catch(SQLException e){
		e.printStackTrace();
	}
	
	
}

}
