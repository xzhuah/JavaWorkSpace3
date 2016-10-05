package com.hkust.xinyu.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.bean.Admit;
import com.hkust.xinyu.dao.AdmitDAO;
import com.hkust.xinyu.implbasicdao.AdmitBasicDAOImpl;
import com.hkust.xinyu.util.DBConnection;

public class AdmitDAOImpl extends AdmitBasicDAOImpl implements AdmitDAO{

	@Override
	public Admit[] findAdmitByProjectID(int id) {
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM Tb_Admit WHERE Project_ID=?";
		Admit result[]			=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findAdmitSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Admit[length];
			int i=0;
			while(set.next()){
				result[i]=new Admit();
				result[i].setID			(set.getInt		(1));
				result[i].setProjectID	(set.getInt		(2));
				result[i].setStudentID	(set.getInt		(3));
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
	public Admit[] findAdmitByStudentID(int id) {
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM Tb_Admit WHERE Student_ID=?";
		Admit result[]			=null;
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		try{
			pstmt=conn.prepareStatement(findAdmitSQL , ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY);	
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			set.last();
			int length=set.getRow();
			set.beforeFirst();
			result=new Admit[length];
			int i=0;
			while(set.next()){
				result[i]=new Admit();
				result[i].setID			(set.getInt		(1));
				result[i].setProjectID	(set.getInt		(2));
				result[i].setStudentID	(set.getInt		(3));
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
