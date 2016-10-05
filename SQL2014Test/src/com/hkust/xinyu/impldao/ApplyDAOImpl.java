package com.hkust.xinyu.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.bean.Apply;
import com.hkust.xinyu.dao.ApplyDAO;
import com.hkust.xinyu.implbasicdao.ApplyBasicDAOImpl;
import com.hkust.xinyu.util.DBConnection;

public class ApplyDAOImpl extends ApplyBasicDAOImpl implements ApplyDAO{
	
	//checked 2015/12/15
	@Override
	public Apply[] findApplyByStudentId(int id) {
		Connection conn			=DBConnection.getConnection();
		String findApplySQL	="SELECT * FROM Tb_Apply WHERE Student_Id=?";
		Apply result[]			=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findApplySQL,  ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);			 
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Apply[length];
			int i=0;
			while(set.next()){
				result[i]=new Apply();
				result[i].setID			(set.getInt		(1));
				result[i].setProjectID	(set.getInt		(2));
				result[i].setStudentID	(set.getInt		(3));
				result[i].setApplyRank	(set.getInt		(4));
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(set);
		}
		return result;
	}

	@Override
	public Apply[] findApplyByProjectId(int id) {
		Connection conn			=DBConnection.getConnection();
		String findApplySQL	="SELECT * FROM Tb_Apply WHERE Project_Id=?";
		Apply result[]			=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findApplySQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Apply[length];
			int i=0;
			while(set.next()){
				result[i]=new Apply();
				result[i].setID			(set.getInt		(1));
				result[i].setProjectID	(set.getInt		(2));
				result[i].setStudentID	(set.getInt		(3));
				result[i].setApplyRank	(set.getInt		(4));
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
	public Apply[] findApplyByProjectId(int id, int rank) {
		Connection conn			=DBConnection.getConnection();
		String findApplySQL	="SELECT * FROM Tb_Apply WHERE Project_Id=? AND Apply_Rank=?";
		Apply result[]			=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findApplySQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, id);
			pstmt.setInt(2, rank);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Apply[length];
			int i=0;
			while(set.next()){
				result[i]=new Apply();
				result[i].setID			(set.getInt		(1));
				result[i].setProjectID	(set.getInt		(2));
				result[i].setStudentID	(set.getInt		(3));
				result[i].setApplyRank	(set.getInt		(4));
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
	public Apply[] findApplyByRank(int rank) {
		Connection conn			=DBConnection.getConnection();
		String findApplySQL	="SELECT * FROM Tb_Apply WHERE Apply_Rank=?";
		Apply result[]			=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findApplySQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, rank);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Apply[length];
			int i=0;
			while(set.next()){
				result[i]=new Apply();
				result[i].setID			(set.getInt		(1));
				result[i].setProjectID	(set.getInt		(2));
				result[i].setStudentID	(set.getInt		(3));
				result[i].setApplyRank	(set.getInt		(4));
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
