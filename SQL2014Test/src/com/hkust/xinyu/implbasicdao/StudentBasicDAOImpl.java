package com.hkust.xinyu.implbasicdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.util.DBConnection;
import com.hkust.xinyu.basicdao.StudentBasicDAO;
import com.hkust.xinyu.bean.Student;

public class StudentBasicDAOImpl implements StudentBasicDAO{
	@Override
	public boolean addStudent(Student student) {
		//Get COnnection
		Connection conn=DBConnection.getConnection();
		//Add SQL
		String addStudentSQL="INSERT INTO Tb_Student(Student_Name,Student_UserName,Student_Password,Student_Gpa,Student_Gender,Student_Grade,Student_Major) VALUES(?,?,?,?,?,?,?)";
		//Prepared Statement
		PreparedStatement pstmt=null;
		//Result Set
		//ResultSet result=null;
		try{
			pstmt=conn.prepareStatement(addStudentSQL);
			pstmt.setString	(1,student.getName());
			pstmt.setString	(2,student.getUserName());
			pstmt.setString	(3,student.getPassword());
			pstmt.setFloat	(4,student.getGpa());
			pstmt.setBoolean(5,student.isGender());
			pstmt.setInt	(6,student.getGrade());
			pstmt.setString	(7,student.getMajor());
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
	//checked 2015/12/15
	@Override
	public boolean updateStudent(Student student) {
		//Get COnnection
		Connection conn			=DBConnection.getConnection();
		//Add SQL
		String updateStudentSQL	="UPDATE Tb_Student SET Student_Name=?,Student_UserName=?, Student_Password=?, Student_Gpa=?," +
								"Student_Gender=?, Student_Grade=?, Student_Major=? WHERE Student_ID=?";
		//Prepared Statement
		PreparedStatement pstmt	=null;
		//Result Set
		//ResultSet result=null;
		try{
			pstmt=conn.prepareStatement(updateStudentSQL);
			pstmt.setString	(1,student.getName());
			pstmt.setString	(2,student.getUserName());
			pstmt.setString	(3,student.getPassword());
			pstmt.setFloat	(4,student.getGpa());
			pstmt.setBoolean(5,student.isGender());
			pstmt.setInt	(6,student.getGrade());
			pstmt.setString	(7,student.getMajor());
			pstmt.setInt	(8, student.getID());
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
	public boolean deleteStudent(int id) {
		//Get COnnection
		Connection conn=DBConnection.getConnection();
		//Add SQL
		String deleteStudentSQL="DELETE FROM Tb_Student WHERE Student_ID=?";
		//Prepared Statement
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(deleteStudentSQL);
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
	public Student findStudentById(int id) {
		Connection conn			=DBConnection.getConnection();
		String findStudentSQL	="SELECT * FROM Tb_Student WHERE Student_ID=?";
		ResultSet result		=null;
		PreparedStatement pstmt	=null;
		Student student			=new Student();
		student.setID(-1);
		try{
			pstmt=conn.prepareStatement(findStudentSQL);
			pstmt.setInt(1, id);
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

}
