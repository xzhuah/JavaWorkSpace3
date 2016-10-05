package com.hkust.xinyu.implbasicdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.basicdao.TeacherBasicDAO;
import com.hkust.xinyu.bean.Teacher;
import com.hkust.xinyu.util.DBConnection;

public class TeacherBasicDAOImpl implements TeacherBasicDAO{
	@Override
	public boolean addTeacher(Teacher teacher) {
		Connection conn			=DBConnection.getConnection();
		String addTeacherSQL	="INSERT INTO Tb_Teacher(Teacher_Name,Teacher_UserName,Teacher_Password) VALUES(?,?,?)";
		PreparedStatement prtmt	=null;
		try{
			prtmt=conn.prepareStatement(addTeacherSQL);
			prtmt.setString	(1, teacher.getName());
			prtmt.setString	(2, teacher.getUserName());
			prtmt.setString	(3, teacher.getPassword());
			prtmt.execute();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(prtmt);
		}
		return true;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		Connection conn=DBConnection.getConnection();
		String updateTeacherSQL="UPDATE Tb_Teacher SET Teacher_Name=?, Teacher_UserName=?, Teacher_Password=? WHERE Teacher_ID=?";
		PreparedStatement prtmt=null;
		try{
			prtmt=conn.prepareStatement(updateTeacherSQL);
			prtmt.setString	(1, teacher.getName());
			prtmt.setString	(2, teacher.getUserName());
			prtmt.setString	(3, teacher.getPassword());
			prtmt.setInt	(4, teacher.getID());
			prtmt.execute();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(prtmt);
		}
		return true;
	}

	@Override
	public boolean deleteTeacher(int id) {
		//Get COnnection
		Connection conn=DBConnection.getConnection();
		//Add SQL
		String deleteTeacherSQL="DELETE FROM Tb_Teacher WHERE Teacher_ID=?";
		//Prepared Statement
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(deleteTeacherSQL);
			pstmt.setInt(1,id);
			pstmt.execute();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
		return true;
	}

	@Override
	public Teacher findTeacherById(int id) {
		Connection conn			=DBConnection.getConnection();
		String findTeacherSQL	="SELECT * FROM Tb_Teacher WHERE Teacher_ID=?";
		Teacher result			=new Teacher();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findTeacherSQL);
			pstmt.setInt(1, id);
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
