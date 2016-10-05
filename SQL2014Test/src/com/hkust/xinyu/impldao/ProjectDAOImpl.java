package com.hkust.xinyu.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.bean.Project;
import com.hkust.xinyu.dao.ProjectDAO;
import com.hkust.xinyu.implbasicdao.ProjectBasicDAOImpl;
import com.hkust.xinyu.util.DBConnection;

public class ProjectDAOImpl extends ProjectBasicDAOImpl implements ProjectDAO{
	public final static short HIGHERTHAN=0,LOWERTHAN=1,EQUALTO=2,HIGHEROREQUAL=3,LOWEROREQUAL=4,NOTEQUAL=5;
	@Override
	public Project findProjectByName(String name) {
		Connection conn			=DBConnection.getConnection();
		String findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Name=?";
		Project result			=new Project();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findProjectSQL);
			pstmt.setString(1, name);
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

	@Override
	public Project[] findProjectByTeacherId(int id) {
		Connection conn			=DBConnection.getConnection();
		String findProjectSQL	="SELECT * FROM Tb_Project WHERE Teacher_ID=?";
		Project result[]		=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findProjectSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Project[length];
			int i=0;
			while(set.next()){
				result[i]=new Project();
				result[i].setID				(set.getInt		(1));
				result[i].setName			(set.getString	(2));
				result[i].setQuate			(set.getInt		(3));
				result[i].setRequirement	(set.getString	(4));
				result[i].setTeacherID		(set.getInt		(5));
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
	public Project[] findProjectByQuate(int quate) {
		Connection conn			=DBConnection.getConnection();
		String findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate=?";
		Project result[]		=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findProjectSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, quate);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Project[length];
			int i=0;
			while(set.next()){
				result[i]=new Project();
				result[i].setID				(set.getInt		(1));
				result[i].setName			(set.getString	(2));
				result[i].setQuate			(set.getInt		(3));
				result[i].setRequirement	(set.getString	(4));
				result[i].setTeacherID		(set.getInt		(5));
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
	public Project[] findProjectByQuate(int quate, short compare) {
		Connection conn			=DBConnection.getConnection();
		String findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate=?";
		switch(compare){
		case HIGHERTHAN:
			findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate>?";
			break;
		case LOWERTHAN:
			findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate<?";
			break;
		case EQUALTO:
			findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate=?";
			break;
		case HIGHEROREQUAL:
			findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate>=?";
			break;
		case LOWEROREQUAL:
			findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate<=?";
			break;
		case NOTEQUAL:
			findProjectSQL	="SELECT * FROM Tb_Project WHERE Project_Quate<>?";
			break;
		default:
			break;
		}
		Project result[]		=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findProjectSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, quate);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Project[length];
			int i=0;
			while(set.next()){
				result[i]=new Project();
				result[i].setID				(set.getInt		(1));
				result[i].setName			(set.getString	(2));
				result[i].setQuate			(set.getInt		(3));
				result[i].setRequirement	(set.getString	(4));
				result[i].setTeacherID		(set.getInt		(5));
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

}
