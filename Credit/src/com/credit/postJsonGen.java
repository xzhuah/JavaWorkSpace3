package com.credit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONException;
import org.json.JSONObject;




public class postJsonGen {

	/**
	 * @param args
	 */
	private static String createTeamURL="http://cis2016-teamtracker.herokuapp.com/api/teams";
	public static String validateURL="http://cis2016-teamtracker.herokuapp.com/api/teams/validate/";
	
	
	public static String validateTeam() throws Exception{
		String url=validateURL+Main.uid;
		String result=PostGet.sentGet(url);
		return result;
	}
	public static String createTeam() throws JSONException{
		String result="";
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM Team";
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		
		String name = "";
		String[] members=new String[5];;
		try{
			pstmt=conn.prepareStatement(findAdmitSQL);
			
			set=pstmt.executeQuery();
			while(set.next()){
				name=set.getString(1);
				for(int i=0;i<members.length;i++){
					members[i]=set.getString(i+2);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(set);
		}
		Team t=new Team(name,members);
		System.out.println(t.toJson());
		
		try {
			result=PostGet.sendPost(createTeamURL, t.toJson());
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.contains("uid")){
			result=result.substring(result.indexOf("uid")+6,result.lastIndexOf("\"}"));
			
			Main.uid=result;
		}
		return result;

	}
	
	public static void main(String[] args) throws Exception {
		System.out.print(validateTeam());

	}
	
	

}
