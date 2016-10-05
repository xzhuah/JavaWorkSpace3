package com.hkust.xinyu.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.bean.Student;
import com.hkust.xinyu.dao.StudentDAO;
import com.hkust.xinyu.implbasicdao.StudentBasicDAOImpl;
import com.hkust.xinyu.util.DBConnection;

public class StudentDAOImpl extends StudentBasicDAOImpl implements StudentDAO{
	public final static short HIGHERTHAN=0,LOWERTHAN=1,EQUALTO=2,HIGHEROREQUAL=3,LOWEROREQUAL=4,NOTEQUAL=5;
	@Override
	public Student[] findStudentByName(String name) {
		Connection conn			=DBConnection.getConnection();
		String findStudentSQL	="SELECT * FROM Tb_Student WHERE Student_Name=?";
		ResultSet result		=null;
		PreparedStatement pstmt	=null;
		Student student[]		=null;
		try{
			pstmt=conn.prepareStatement(findStudentSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setString(1, name);
			result=pstmt.executeQuery();
			result.last();
			int length=result.getRow();
			result.beforeFirst();
			student=new Student[length];
			int i=0;
			while(result.next()){
				student[i]=new Student();
				student[i].setID		(result.getInt		(1));
				student[i].setName		(result.getString	(2));
				student[i].setUserName	(result.getString	(3));
				student[i].setPassword	(result.getString	(4));
				student[i].setGpa		(result.getFloat	(5));
				student[i].setGender	(result.getBoolean	(6));
				student[i].setGrade	(result.getInt		(7));
				student[i].setMajor	(result.getString	(8));
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(result);
			DBConnection.close(pstmt);
		}
		return student;
	}

	@Override
	public Student findStudentByUserName(String name) {
		Connection conn			=DBConnection.getConnection();
		String findStudentSQL	="SELECT * FROM Tb_Student WHERE Student_UserName=?";
		ResultSet result		=null;
		PreparedStatement pstmt	=null;
		Student student			=new Student();
		student.setID(-1);
		try{
			pstmt=conn.prepareStatement(findStudentSQL);
			pstmt.setString(1, name);
			result=pstmt.executeQuery();
			while(result.next()){
				student.setID		(result.getInt		(1));
				student.setName		(result.getString	(2));
				student.setUserName	(result.getString	(3));
				student.setPassword	(result.getString	(4));
				student.setGpa		(result.getFloat	(5));
				student.setGender	(result.getBoolean	(6));
				student.setGrade	(result.getInt		(7));
				student.setMajor	(result.getString	(8));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(result);
			DBConnection.close(pstmt);
		}
		return student;
	}

	@Override
	public Student[] findStudentByGpa(double gpa, short compare) {
		Connection conn			=DBConnection.getConnection();
		String findStudentSQL	="SELECT * FROM Tb_Student WHERE Student_Gpa=?";
		ResultSet result		=null;
		PreparedStatement pstmt	=null;
		Student student[]		=null;
		switch(compare){
		case HIGHERTHAN:
			findStudentSQL	="SELECT * FROM Tb_Project WHERE Project_Quate>?";
			break;
		case LOWERTHAN:
			findStudentSQL	="SELECT * FROM Tb_Project WHERE Project_Quate<?";
			break;
		case EQUALTO:
			findStudentSQL	="SELECT * FROM Tb_Project WHERE Project_Quate=?";
			break;
		case HIGHEROREQUAL:
			findStudentSQL	="SELECT * FROM Tb_Project WHERE Project_Quate>=?";
			break;
		case LOWEROREQUAL:
			findStudentSQL	="SELECT * FROM Tb_Project WHERE Project_Quate<=?";
			break;
		case NOTEQUAL:
			findStudentSQL	="SELECT * FROM Tb_Project WHERE Project_Quate<>?";
			break;
		default:
			break;
		}
		try{
			pstmt=conn.prepareStatement(findStudentSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setDouble(1, gpa);
			result=pstmt.executeQuery();
			result.last();
			int length=result.getRow();
			result.beforeFirst();
			student=new Student[length];
			int i=0;
			while(result.next()){
				student[i]=new Student();
				student[i].setID		(result.getInt		(1));
				student[i].setName		(result.getString	(2));
				student[i].setUserName	(result.getString	(3));
				student[i].setPassword	(result.getString	(4));
				student[i].setGpa		(result.getFloat	(5));
				student[i].setGender	(result.getBoolean	(6));
				student[i].setGrade	(result.getInt		(7));
				student[i].setMajor	(result.getString	(8));
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(result);
			DBConnection.close(pstmt);
		}
		return student;
	}

	@Override
	public Student[] findStudentByGrade(int grade) {
		Connection conn			=DBConnection.getConnection();
		String findStudentSQL	="SELECT * FROM Tb_Student WHERE Student_Gpa=?";
		ResultSet result		=null;
		PreparedStatement pstmt	=null;
		Student student[]		=null;
		try{
			pstmt=conn.prepareStatement(findStudentSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, grade);
			result=pstmt.executeQuery();
			result.last();
			int length=result.getRow();
			result.beforeFirst();
			student=new Student[length];
			int i=0;
			while(result.next()){
				student[i]=new Student();
				student[i].setID		(result.getInt		(1));
				student[i].setName		(result.getString	(2));
				student[i].setUserName	(result.getString	(3));
				student[i].setPassword	(result.getString	(4));
				student[i].setGpa		(result.getFloat	(5));
				student[i].setGender	(result.getBoolean	(6));
				student[i].setGrade	(result.getInt		(7));
				student[i].setMajor	(result.getString	(8));
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(result);
			DBConnection.close(pstmt);
		}
		return student;
	}

	@Override
	public Student[] findStudentByMajor(String major) {
		Connection conn			=DBConnection.getConnection();
		String findStudentSQL	="SELECT * FROM Tb_Student WHERE Student_Major=?";
		ResultSet result		=null;
		PreparedStatement pstmt	=null;
		Student student[]		=null;
		try{
			pstmt=conn.prepareStatement(findStudentSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setString(1, major);
			result=pstmt.executeQuery();
			result.last();
			int length=result.getRow();
			result.beforeFirst();
			student=new Student[length];
			int i=0;
			while(result.next()){
				student[i].setID		(result.getInt		(1));
				student[i].setName		(result.getString	(2));
				student[i].setUserName	(result.getString	(3));
				student[i].setPassword	(result.getString	(4));
				student[i].setGpa		(result.getFloat	(5));
				student[i].setGender	(result.getBoolean	(6));
				student[i].setGrade	(result.getInt		(7));
				student[i].setMajor	(result.getString	(8));
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(result);
			DBConnection.close(pstmt);
		}
		return student;
	}

}
