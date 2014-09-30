package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class CommonDAO{
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/assignment";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	static Connection getConnection() throws Exception{
		Connection con = null;
		try{
			Class.forName(DRIVER_NAME);
			con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	static void close(Connection con){
		try{
			if(con!=null){
				con.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}