package com.credit;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BuyDetail {

	/**
	 * @param args
	 */
	public long time;
	public String name;
	public double prize;
	public int qty;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public BuyDetail(){
		
	}
	public BuyDetail(long time, String name, double prize, int qty) {
		super();
		this.time = time;
		this.name = name;
		this.prize = prize;
		this.qty = qty;
		writeToDB();
	}
	public String toString(){
		return "[Time:"+time+", Name:"+name+", Prize:"+prize+", Number:"+qty+"]";
	}
	public boolean writeToDB(){
		Connection conn			=DBConnection.getConnection();
		String addAdmitSQL	="INSERT INTO BuyDetail(Time,Name,BuyPrize,BuyQty) VALUES("+time+","+name+","+prize+","+qty+")";
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
