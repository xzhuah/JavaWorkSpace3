package com.hkust.xinyu.impldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hkust.xinyu.bean.Manager;
import com.hkust.xinyu.dao.ManagerDAO;
import com.hkust.xinyu.implbasicdao.ManagerBasicDAOImpl;
import com.hkust.xinyu.util.DBConnection;

public class ManagerDAOImpl extends ManagerBasicDAOImpl implements ManagerDAO{

	@Override
	public Manager findManagerByUsername(String username) {
		Connection conn			=DBConnection.getConnection();
		String findManagerSQL	="SELECT * FROM Tb_Manager WHERE Manager_Username=?";
		Manager result			=new Manager();
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		result.setID(-1);
		try{
			pstmt=conn.prepareStatement(findManagerSQL);
			pstmt.setString(1, username);
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
