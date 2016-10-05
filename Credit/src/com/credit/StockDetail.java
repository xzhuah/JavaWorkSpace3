package com.credit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;

public class StockDetail {

	/**
	 * @param args
	 */
	String detailURL="http://cis2016-exchange1.herokuapp.com/api/market_data/";
	
	public long time;
	public String name;
	public HashMap<Double,Integer> buy;
	public HashMap<Double,Integer> sell;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new StockDetail("0388");	
	}
	public StockDetail(String name) throws Exception{
		this.name=name;
		
		buy=new HashMap<Double,Integer>();
		sell=new HashMap<Double,Integer>();
		String str=PostGet.sentGet(detailURL+name);
		this.time=System.currentTimeMillis();
		JSONObject obj = new JSONObject(str);
		
		JSONObject objbuy=obj.getJSONObject("buy");
		JSONObject objsell=obj.getJSONObject("sell");
		
		
		Iterator itbuy = objbuy.keys();
		while(itbuy.hasNext()){
			String key=itbuy.next().toString();
			buy.put(Double.parseDouble(key), objbuy.getInt(key));
		}
		
		Iterator itsell = objsell.keys();
		while(itsell.hasNext()){
			String key=itsell.next().toString();
			sell.put(Double.parseDouble(key), objsell.getInt(key));
		}
		writeToDB();
	}
	
	public void writeToDB(){
		Iterator<Double> itbuy=this.buy.keySet().iterator();
		while(itbuy.hasNext()){
			double key=itbuy.next();
			new BuyDetail(time,name,key,buy.get(key));
		}
		
		Iterator<Double> itsell=this.sell.keySet().iterator();
		while(itsell.hasNext()){
			double key=itsell.next();
			new SellDetail(time,name,key,sell.get(key));
		}
	}

}
