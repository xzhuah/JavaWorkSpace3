package com.hkust.xinyu.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.bean.Teacher;
import com.hkust.xinyu.dao.TeacherDAO;
import com.hkust.xinyu.implbasicdao.TeacherBasicDAOImpl;
import com.hkust.xinyu.util.DBConnection;

public class TeacherDAOImpl extends TeacherBasicDAOImpl implements TeacherDAO{

	@Override
	public Teacher[] findTeacherByName(String name) {
		Connection conn			=DBConnection.getConnection();
		String findTeacherSQL	="SELECT * FROM Tb_Teacher WHERE Teacher_Name=?";
		Teacher result[]		=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findTeacherSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setString(1, name);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Teacher[length];
			int i=0;
			while(set.next()){
				result[i]=new Teacher();
				result[i].setID			(set.getInt		(1));
				result[i].setName		(set.getString	(2));
				result[i].setUserName	(set.getString	(3));
				result[i].setPassword	(set.getString	(4));
				i++;
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(set);
		}
		return result;
	}

	@Override
	public Teacher findTeacherByUsername(String username) {
		Connection conn			=DBConnection.getConnection();
		String findTeacherSQL	="SELECT * FROM Tb_Teacher WHERE Teacher_UserName=?";
		Teacher result			=new Teacher();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findTeacherSQL);
			pstmt.setString(1, username);
			set=pstmt.executeQuery();
			while(set.next()){
				result.setID		(set.getInt		(1));
				result.setName		(set.getString	(2));
				result.setUserName	(set.getString	(3));
				result.setPassword	(set.getString	(4));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(set);
		}
		return result;
	}

}
