package com.hkust.xinyu.implbasicdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.basicdao.AdmitBasicDAO;
import com.hkust.xinyu.bean.Admit;
import com.hkust.xinyu.util.DBConnection;

public class AdmitBasicDAOImpl implements AdmitBasicDAO{
	@Override
	public boolean addAdmit(Admit admit) {
		Connection conn			=DBConnection.getConnection();
		String addAdmitSQL	="INSERT INTO Tb_Admit(Project_ID,Student_ID) VALUES(?,?)";
		PreparedStatement prtmt	=null;
		try{
			prtmt=conn.prepareStatement(addAdmitSQL);
			prtmt.setInt	(1, admit.getProjectID());
			prtmt.setInt	(2, admit.getStudentID());	
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
	public boolean updateAdmit(Admit admit) {
		Connection conn=DBConnection.getConnection();
		String updateAdmitSQL="UPDATE Tb_Admit SET Project_ID=?,Student_ID=? WHERE Admit_ID=?";
		PreparedStatement prtmt=null;
		try{
			prtmt=conn.prepareStatement(updateAdmitSQL);
			prtmt.setInt	(1, admit.getProjectID());
			prtmt.setInt	(2, admit.getStudentID());	
			prtmt.setInt	(3, admit.getID());	
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
	public boolean deleteAdmit(int id) {
		//Get COnnection
		Connection conn=DBConnection.getConnection();
		//Add SQL
		String deleteAdmitSQL="DELETE FROM Tb_Admit WHERE Admit_ID=?";
		//Prepared Statement
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(deleteAdmitSQL);
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
	public Admit findAdmitById(int id) {
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM Tb_Admit WHERE Admit_ID=?";
		Admit result			=new Admit();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findAdmitSQL);
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			while(set.next()){
				result.setID		(set.getInt		(1));
				result.setProjectID	(set.getInt		(2));
				result.setStudentID	(set.getInt		(3));
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
