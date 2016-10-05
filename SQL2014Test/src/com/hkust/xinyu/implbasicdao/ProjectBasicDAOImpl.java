package com.hkust.xinyu.implbasicdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.basicdao.ProjectBasicDAO;
import com.hkust.xinyu.bean.Project;
import com.hkust.xinyu.util.DBConnection;

public class ProjectBasicDAOImpl implements ProjectBasicDAO{
	@Override
	public boolean addProject(Project project) {
		Connection conn			=DBConnection.getConnection();
		String addProjectSQL	="INSERT INTO Tb_Project(Project_Name,Project_Quate,Project_Requirement,Teacher_ID) VALUES(?,?,?,?)";
		PreparedStatement prtmt	=null;
		try{
			prtmt=conn.prepareStatement(addProjectSQL);
			prtmt.setString	(1, project.getName());
			prtmt.setInt	(2, project.getQuate());
			prtmt.setString	(3, project.getRequirement());
			prtmt.setInt	(4, project.getTeacherID());
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
	public boolean updateProject(Project project) {
		Connection conn=DBConnection.getConnection();
		String updateProjectSQL="UPDATE Tb_Project SET Project_Name=?, Project_Quate=?, Project_Requirement=?, Teacher_ID=? WHERE Project_ID=?";
		PreparedStatement prtmt=null;
		try{
			prtmt=conn.prepareStatement(updateProjectSQL);
			prtmt.setString	(1, project.getName());
			prtmt.setInt	(2, project.getQuate());
			prtmt.setString	(3, project.getRequirement());
			prtmt.setInt	(4, project.getTeacherID());
			prtmt.setInt	(5, project.getID());
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

	//checked on 2015/12/16
	@Override
	public boolean deleteProject(int id) {
		//Get COnnection
		Connection conn=DBConnection.getConnection();
		//Add SQL
		String deleteProjectSQL="DELETE FROM Tb_Project WHERE Project_ID=?";
		//Prepared Statement
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(deleteProjectSQL);
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
	public Project findProjectById(int id) {
		Connection conn			=DBConnection.getConnection();
		String findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_ID=?";
		Project result			=new Project();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findProjectSQL);
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			while(set.next()){
				result.setID			(set.getInt		(1));
				result.setName			(set.getString	(2));
				result.setQuate			(set.getInt		(3));
				result.setRequirement	(set.getString	(4));
				result.setTeacherID		(set.getInt		(5));
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
