package com.hkust.xinyu.util.sqlutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.hkust.xinyu.util.DBConnection;
import com.hkust.xinyu.util.TxtReader;

public class SpecialCommand {
	public static boolean recoverDataBase(String username,String password){
		TxtReader read=new TxtReader();
		Connection conn=DBConnection.getSuperConnection(username,password);
		try {
			String recoverCommand=read.getTextFromTxt("Source/recover.txt");	
			conn.prepareStatement(recoverCommand).execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;		
		}finally{
			DBConnection.close(conn);
		}
		return true;
	}
	//Checked on 2015/12/15
	public static boolean deleteTable(String tablename,boolean ResetSeed){
		Connection conn=DBConnection.getConnection();
		String deleteSQL="DELETE FROM "+tablename;
		try{
			conn.prepareStatement(deleteSQL).execute();
			if(ResetSeed){
				conn.prepareStatement("DBCC CHECKIDENT( "+tablename+", RESEED, 0 )").execute();			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;		
		}finally{
			DBConnection.close(conn);
		}
		return true;
	}
	
	public static boolean truncateTable(String tablename){
		Connection conn=DBConnection.getConnection();
		String truncateSQL="TRUNCATE TABLE "+tablename;
		try{
			conn.prepareStatement(truncateSQL).execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;		
		}finally{
			DBConnection.close(conn);
		}
		return true;
	}
	public static boolean CompressDB(){
		Connection conn=DBConnection.getConnection();
		String truncateSQL="DBCC SHRINKDATABASE(db_projectApplication)";
		try{
			conn.prepareStatement(truncateSQL).execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;		
		}finally{
			DBConnection.close(conn);
		}
		return true;
	}
	public static String excuteSQL(String sql){
		Connection conn=DBConnection.getConnection();
		String result="";
		ResultSet set=null;	
		try{
			set=conn.prepareStatement(sql).executeQuery();
			ResultSetMetaData rsmd =set.getMetaData();
			int col = rsmd.getColumnCount();
			while(set.next()){
				for(int i=1;i<=col;i++){
					result+=set.getString(i)+"\t";
				}
				result+="\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";		
		}finally{
			DBConnection.close(conn);
		}
		return result;
	}
	
}
