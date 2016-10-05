package com.hkust.xinyu.implbasicdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.basicdao.ApplyBasicDAO;
import com.hkust.xinyu.bean.Apply;
import com.hkust.xinyu.util.DBConnection;
//checked on 2015/12/16
public class ApplyBasicDAOImpl implements ApplyBasicDAO{
	//checked 2015/12/15
	@Override
	public boolean addApply(Apply apply) {
		Connection conn			=DBConnection.getConnection();
		String addApplySQL	="INSERT INTO Tb_Apply(Project_ID,Student_ID,Apply_Rank) VALUES(?,?,?)";
		PreparedStatement prtmt	=null;
		try{
			prtmt=conn.prepareStatement(addApplySQL);
			prtmt.setInt	(1, apply.getProjectID());
			prtmt.setInt	(2, apply.getStudentID());	
			prtmt.setInt	(3, apply.getApplyRank());
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
	//checked 2015/12/15
	@Override
	public boolean updateApply(Apply apply) {
		Connection conn=DBConnection.getConnection();
		String updateApplySQL="UPDATE Tb_Apply SET Project_ID=?,Student_ID=?,Apply_Rank=? WHERE Apply_ID=?";
		PreparedStatement prtmt=null;
		try{
			prtmt=conn.prepareStatement(updateApplySQL);
			prtmt.setInt	(1, apply.getProjectID());
			prtmt.setInt	(2, apply.getStudentID());	
			prtmt.setInt	(3, apply.getApplyRank());
			prtmt.setInt	(4, apply.getID());
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
	public boolean deleteApply(int id) {
		//Get COnnection
		Connection conn=DBConnection.getConnection();
		//Add SQL
		String deleteApplySQL="DELETE FROM Tb_Apply WHERE Apply_ID=?";
		//Prepared Statement
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(deleteApplySQL);
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
	//checked 2015/12/15
	@Override
	public Apply findApplyById(int id) {
		Connection conn			=DBConnection.getConnection();
		String findApplySQL	="SELECT * FROM Tb_Apply WHERE Apply_ID=?";
		Apply result			=new Apply();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findApplySQL);
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			while(set.next()){
				result.setID		(set.getInt		(1));
				result.setProjectID	(set.getInt		(2));
				result.setStudentID	(set.getInt		(3));
				result.setApplyRank	(set.getInt		(4));
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
