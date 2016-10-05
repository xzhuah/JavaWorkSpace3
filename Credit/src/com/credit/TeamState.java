package com.credit;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.*;


public class TeamState {

	/**
	 * @param args
	 */
	public int S0001=0,S0388=0,S0005=0,S0386=0,S3988=0;
	public int R0001=0,R0388=0,R0005=0,R0386=0,R3988=0;
	
	public double cash=0;
	public long time;
	public double Rmoney=0;
	
	public TeamState(boolean fromDB){
		
	}
	
	public TeamState(){
		String theurl="http://cis2016-teamtracker.herokuapp.com/api/teams/"+Main.uid+"?next_stage";
		try {
			String result=PostGet.sentGet(theurl);
			time=System.currentTimeMillis();
			JSONObject obj = new JSONObject(result);
			S0001=obj.get("0001").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0001").toString());
			S0005=obj.get("0005").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0005").toString());
			S0388=obj.get("0388").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0388").toString());
			S0386=obj.get("0386").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0386").toString());
			S3988=obj.get("3988").toString().equals("null")?0:(int)Double.parseDouble(obj.get("3988").toString());
			
			R0001=obj.get("0001_reserved").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0001_reserved").toString());
			R0005=obj.get("0005_reserved").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0005_reserved").toString());
			R0388=obj.get("0388_reserved").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0388_reserved").toString());
			R0386=obj.get("0386_reserved").toString().equals("null")?0:(int)Double.parseDouble(obj.get("0386_reserved").toString());
			R3988=obj.get("3988_reserved").toString().equals("null")?0:(int)Double.parseDouble(obj.get("3988_reserved").toString());
			
			cash=obj.getDouble("cash");
			Rmoney=obj.getDouble("reserved_cash");
			this.writeToDB();
			//obj.get("3988_reserved");
			//System.out.println(obj);
			//String pageName = obj.getJSONObject("pageInfo").getString("pageName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String toString(){
		String result="[Time: "+time+" Cash: "+cash+", Rmoney "+Rmoney+"\n";
		result+="0001: "+S0001+", "+R0001+"\n";
		result+="0005: "+S0005+", "+R0005+"\n";
		result+="0386: "+S0386+", "+R0386+"\n";
		result+="0388: "+S0388+", "+R0388+"\n";
		result+="3988: "+S3988+", "+R3988+"]\n";
		return result;
		
	}
	public boolean writeToDB(){
		Connection conn			=DBConnection.getConnection();
		String addAdmitSQL	="INSERT INTO TeamInfo(Time,Cash,S0001,S0005,S0388,S0386,S3988,R0001,R0005,R0388,R0386,R3988,Rmoney) VALUES("+time+","+cash+","+S0001+","+S0005+","+S0388+","+S0386+","+S3988+","+R0001+","+R0005+","+R0388+","+R0386+","+R3988+","+Rmoney+")";
		PreparedStatement prtmt	=null;
		try{
			prtmt=conn.prepareStatement(addAdmitSQL);
			
		
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new TeamState().toString());
	}

}
