package com.hkust.xinyu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	final private static String username="db_projectApplication_Manager";
	final private static String password="20151209";
	private static final String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DBURL="jdbc:sqlserver://localhost:1434;DataBaseName=db_projectApplication";
	public static void main(String argsp[]){
		
	}
	public static Connection getSuperConnection(String username,String password){
		Connection conn=null;
		try{
			Class.forName(DBDRIVER);
			conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1434;DataBaseName=master",username,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName(DBDRIVER);
			conn=DriverManager.getConnection(DBURL,username,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {
		if(conn != null) {				
			try {
				conn.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {				
			try {
				pstmt.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs) {
		if(rs != null) {				
			try {
				rs.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
