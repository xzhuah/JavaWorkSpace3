package com.hkust.xinyu.implbasicdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.basicdao.ManagerBasicDAO;
import com.hkust.xinyu.bean.Manager;
import com.hkust.xinyu.util.DBConnection;

public class ManagerBasicDAOImpl implements ManagerBasicDAO{

	@Override
	public boolean addManager(Manager manager) {
		Connection conn			=DBConnection.getConnection();
		String addManagerSQL	="INSERT INTO Tb_Manager(Manager_Username,Manager_Password) VALUES(?,?)";
		PreparedStatement prtmt	=null;
		try{
			prtmt=conn.prepareStatement(addManagerSQL);
			prtmt.setString	(1, manager.getUsername());
			prtmt.setString	(2, manager.getPassword());
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
	public boolean updateManager(Manager manager) {
		Connection conn=DBConnection.getConnection();
		String updateManagerSQL="UPDATE Tb_Manager SET Manager_Username=?, Manager_Password=? WHERE Manager_ID=?";
		PreparedStatement prtmt=null;
		try{
			prtmt=conn.prepareStatement(updateManagerSQL);
			prtmt.setString	(1, manager.getUsername());
			prtmt.setString	(2, manager.getPassword());
			prtmt.setInt	(3, manager.getID());
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
	public boolean deleteManager(int id) {
		Connection conn=DBConnection.getConnection();
		//Add SQL
		String deleteManagerSQL="DELETE FROM Tb_Manager WHERE Manager_ID=?";
		//Prepared Statement
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(deleteManagerSQL);
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
	public Manager findManagerById(int id) {
		Connection conn			=DBConnection.getConnection();
		String findManagerSQL	="SELECT * FROM Tb_Manager WHERE Manager_ID=?";
		Manager result			=new Manager();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findManagerSQL);
			pstmt.setInt(1, id);
			set=pstmt.executeQuery();
			while(set.next()){
				result.setID		(set.getInt		(1));
				result.setUsername	(set.getString	(2));
				result.setPassword	(set.getString	(3));
				
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
