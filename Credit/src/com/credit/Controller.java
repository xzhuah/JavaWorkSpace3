package com.credit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;


public class Controller {

	/**
	 * @param args
	 */
	
	static String[] stocks={"0001","0005","0386","0388","3988"};
	static String target="http://cis2016-exchange1.herokuapp.com/api/orders?next_stage";
	
	static String team_uid=Main.uid;
	static String symbol=stocks[1];
	static String side="buy";//"sell""buy"
	static String qty="1";
	static String order_type="limit";//"limit","market"
	static String price="80";
	public static void main(String[] args) throws URISyntaxException, IOException, JSONException {
		long now=System.currentTimeMillis();
		long period=10000;
//		System.out.print("Buy Detail");System.out.println(Controller.getBuyDetail(now-period,now));
//		System.out.print("Sell Detail");System.out.println(Controller.getSellDetail(now-period,now));
//		
//		System.out.print("StockInfo");System.out.println(Controller.getStockInfo(now-period,now));
//		System.out.print("TeamState");System.out.println(Controller.getTeamState(now-period,now));
		
		//System.out.println(buyStock());
		
		decide(30*1000);
	}
	public static Vector<BuyDetail> filterByName(String name,Vector<BuyDetail> data){
		Vector<BuyDetail> result=new Vector<BuyDetail>();
		for(BuyDetail a:data){
			if(a.name.equals(name)){
				result.add(a);
			}
		}
		return result;
	}
	public static Vector<Vector<BuyDetail>> splitByTime(Vector<BuyDetail> data){
		
		Vector<Vector<BuyDetail>> result=new Vector<Vector<BuyDetail>>();
		long currentTime=data.get(0).time;
		Vector<BuyDetail> temp=new Vector<BuyDetail>();
		temp.add(data.get(0));
		for(int i=1;i<data.size();i++){
			if(data.get(i).time!=currentTime){
				result.add(temp);
				temp=new Vector<BuyDetail>();
				temp.add(data.get(i));
			}else{
				temp.add(data.get(i));
			}
		}
		result.add(temp);
		return result;
	}
	
	public static Vector<SellDetail> filterByName2(String name,Vector<SellDetail> data){
		Vector<SellDetail> result=new Vector<SellDetail>();
		for(SellDetail a:data){
			if(a.name.equals(name)){
				result.add(a);
			}
		}
		return result;
	}
	public static Vector<Vector<SellDetail>> splitByTime2(Vector<SellDetail> data){
		Vector<Vector<SellDetail>> result=new Vector<Vector<SellDetail>>();
		long currentTime=data.get(0).time;
		Vector<SellDetail> temp=new Vector<SellDetail>();
		temp.add(data.get(0));
		for(int i=1;i<data.size();i++){
			if(data.get(i).time!=currentTime){
				result.add(temp);
				temp=new Vector<SellDetail>();
				temp.add(data.get(i));
			}else{
				temp.add(data.get(i));
			}
		}
		result.add(temp);
		return result;
	}
	
	public static void decide(long history){
		while(true){
		//for(int idex=0;idex<1;idex++){
			try{
				/*Deside Buy, we sell if buy is increasing*/
				
				long now=System.currentTimeMillis();
				long period=history;
				Vector<BuyDetail> data = Controller.getBuyDetail(now-period,now);
				//System.out.println(data);
				
				
				for(int i=0;i<stocks.length;i++){
					
					String target=stocks[i];
					
					Vector<Double> prize=new Vector<Double>();
					Vector<Double> Time=new Vector<Double>();
					
					
					Vector<BuyDetail> temp = filterByName(target,data);
					if(temp.size()<=0){
						continue;
					}else{
						//System.out.println(temp);
					}
					Vector<Vector<BuyDetail>> mdata=splitByTime(temp);
					
					Vector<BuyDetail> buydetail=new Vector<BuyDetail>();
					for(int j=0;j<mdata.size();j++){
						prize.add(mdata.get(j).get(0).prize);
						buydetail.add(mdata.get(j).get(0));
						Time.add(mdata.get(j).get(0).time*1.0);
						
					}
					buydetail=DataAnalysis.cleanData(buydetail, prize);
					//System.out.println("clean:"+buydetail);
					Vector<Double> medians=new Vector<Double>();
					
					for(int j=0;j<buydetail.size();j++){
						medians.add(buydetail.get(j).qty*1.0);
					}
					//System.out.println("clean:"+medians);
					

					
					
					long future2=2000;
					long future1=1000;
					double futurePrice2=DataAnalysis.predict(prize, Time, future2);
					double futurePrice1=DataAnalysis.predict(prize, Time, future1);
					double midnum=DataAnalysis.midian(medians);
					/*System.out.println("Target:"+target);
					System.out.println("Input: "+prize);
					System.out.println("Predict: "+futurePrice2);
					System.out.println("Mid Num:"+(int)midnum);*/
					double param=futurePrice2-futurePrice1;
					double number=midnum>100?100:midnum;
					//System.out.println("Decide To Sell? "+param);
					if(param>0.2){//!important
						
						
						
						team_uid=Main.uid;
						symbol=stocks[i];
						side="sell";//"sell""buy"
						qty=number+"";
						order_type="limit";//"limit","market"
						price=""+futurePrice2;
						System.out.println("Decide To Sell? Yes!!!");
						System.out.println("symbol:"+symbol+" limit:"+futurePrice2+" num"+qty);
						System.out.println(buyStock());
					}
				}
					
					
					
					//Sell Here
					
					
					now=System.currentTimeMillis();
					period=history;
					Vector<SellDetail> data2 = Controller.getSellDetail(now-period,now);
					//System.out.println(data);
					for(int i=0;i<stocks.length;i++){
						
						String target=stocks[i];
						
						Vector<Double> prize=new Vector<Double>();
						Vector<Double> Time=new Vector<Double>();
						
						
						Vector<SellDetail> temp = filterByName2(target,data2);
						if(temp.size()<=0){
							continue;
						}else{
							//System.out.println(temp);
						}
						Vector<Vector<SellDetail>> mdata=splitByTime2(temp);
						
						Vector<SellDetail> buydetail=new Vector<SellDetail>();
						for(int j=0;j<mdata.size();j++){
							prize.add(mdata.get(j).get(0).prize);
							buydetail.add(mdata.get(j).get(0));
							Time.add(mdata.get(j).get(0).time*1.0);
							
						}
						buydetail=DataAnalysis.cleanData2(buydetail, prize);
						//System.out.println("clean:"+buydetail);
						Vector<Double> medians=new Vector<Double>();
						
						for(int j=0;j<buydetail.size();j++){
							medians.add(buydetail.get(j).qty*1.0);
						}
						//System.out.println("clean:"+medians);
						

						
						
						long future2=2000;
						long future1=1000;
						double futurePrice2=DataAnalysis.predict(prize, Time, future2);
						double futurePrice1=DataAnalysis.predict(prize, Time, future1);
						double midnum=DataAnalysis.midian(medians);
						/*System.out.println("Target:"+target);
						System.out.println("Input: "+prize);
						System.out.println("Predict: "+futurePrice2);
						System.out.println("Mid Num:"+(int)midnum);*/
						double param=futurePrice2-futurePrice1;
						double number=midnum>100?100:midnum;
						//System.out.println("Decide To Buy? "+param);
						if(param<-0.2){//!important
							
							
							
							team_uid=Main.uid;
							symbol=stocks[i];
							side="buy";//"sell""buy"
							qty=number+"";
							order_type="limit";//"limit","market"
							price=""+futurePrice2;
							System.out.println("Decide To Buy? Yes!!!");
							System.out.println("symbol:"+symbol+" limit:"+futurePrice2+" num"+qty);
							System.out.println(buyStock());
						}
					
					
				}
				
				
				
				
				
				/*Deside Sell*/
				
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static String genJson() throws JSONException{
		JSONObject jo=new JSONObject();
		jo.put("team_uid", team_uid);
		jo.put("symbol", symbol);
		jo.put("side", side);
		jo.put("qty", qty);
		jo.put("order_type", order_type);
		jo.put("price", price);
		return jo.toString();
	}
	public static String buyStock() throws URISyntaxException, IOException, JSONException{
		return PostGet.sendPost(target, genJson());
	}
	
	public static Vector<BuyDetail> getBuyDetail(long begin,long end){
		Vector<BuyDetail> result=new Vector<BuyDetail> ();
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM BuyDetail WHERE Time>"+begin+" and Time<"+end+" order by Time DESC,Name,BuyPrize DESC";
		
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		
		try{
			pstmt=conn.prepareStatement(findAdmitSQL);
			
			set=pstmt.executeQuery();
			while(set.next()){
				BuyDetail result1			=new BuyDetail();
				result1.time=set.getLong(1);
				result1.name=set.getString(2);
				while(result1.name.length()<4){
					result1.name="0"+result1.name;
				}
				
				result1.prize=set.getDouble(3);
				result1.qty=set.getInt(4);
				if(result1.prize>1&&result1.prize<1000){
					result.add(result1);
				}
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
	public static Vector<SellDetail> getSellDetail(long begin,long end){
		Vector<SellDetail> result=new Vector<SellDetail> ();
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM SellDetail WHERE Time>"+begin+" and Time<"+end+" order by Time DESC,Name,SellPrize ASC";
		
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		
		try{
			pstmt=conn.prepareStatement(findAdmitSQL);
			
			set=pstmt.executeQuery();
			while(set.next()){
				SellDetail result1			=new SellDetail();
				result1.time=set.getLong(1);
				result1.name=set.getString(2);
				while(result1.name.length()<4){
					result1.name="0"+result1.name;
				}
				
				result1.prize=set.getDouble(3);
				result1.qty=set.getInt(4);
				
				if(result1.prize>1&&result1.prize<1000){
					result.add(result1);
				}
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
	public static Vector<TeamState> getTeamState(long begin,long end){
		Vector<TeamState> result=new Vector<TeamState> ();
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM TeamInfo WHERE Time>"+begin+" and Time<"+end+" order by Time DESC";
		
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		
		try{
			pstmt=conn.prepareStatement(findAdmitSQL);
			
			set=pstmt.executeQuery();
			while(set.next()){
				TeamState result1			=new TeamState(true);
				result1.time=set.getLong(1);
				result1.cash=set.getDouble(2);
				result1.S0001=set.getInt(3);
				result1.S0005=set.getInt(4);
				result1.S0388=set.getInt(5);
				result1.S0386=set.getInt(6);
				result1.S3988=set.getInt(7);
				
				result1.R0001=set.getInt(8);
				result1.R0005=set.getInt(9);
				result1.R0388=set.getInt(10);
				result1.R0386=set.getInt(11);
				result1.R3988=set.getInt(12);
				result1.Rmoney=set.getDouble(13);
				
				
			
				
				result.add(result1);
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
	public static Vector<StockInfo> getStockInfo(long begin,long end){
		Vector<StockInfo> result=new Vector<StockInfo> ();
		Connection conn			=DBConnection.getConnection();
		String findAdmitSQL	="SELECT * FROM StockInfo WHERE Time>"+begin+" and Time<"+end+" order by Time DESC";
		
		PreparedStatement pstmt	=null;
		ResultSet set			=null;
		
		try{
			pstmt=conn.prepareStatement(findAdmitSQL);
			
			set=pstmt.executeQuery();
			while(set.next()){
				StockInfo result1			=new StockInfo();
				result1.time=set.getLong(1);
				result1.name=set.getString(2);
				while(result1.name.length()<4){
					result1.name="0"+result1.name;
				}
				result1.bid=set.getDouble(3);
				result1.ask=set.getDouble(4);
				
				if(result1.bid>1&&result1.bid<1000&&result1.ask>1&&result1.ask<1000&&Math.abs(result1.bid-result1.ask)<10){
					result.add(result1);
				}
				
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
