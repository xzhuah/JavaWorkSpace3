package com.credit;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StockInfo {

	/**
	 * @param args
	 */
	public long time;
	public String name;
	public double bid;
	public double ask;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public StockInfo(){
		
	}
	public String toString(){
		return "[Time:"+time+", Name:"+name+", Bid:"+bid+", Ask:"+ask+"]";
	}
	
	public StockInfo(long time, String name, double bid,double ask){
		this.time=time;
		this.name=name;
		this.bid=bid;
		this.ask=ask;
		writeToDB();
	}
	public boolean writeToDB(){
		Connection conn			=DBConnection.getConnection();
		String addAdmitSQL	="INSERT INTO StockInfo(Time,Name,Bid,Ask) VALUES("+time+","+name+","+bid+","+ask+")";
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

}
