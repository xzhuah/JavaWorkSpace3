package com.credit;

import org.json.JSONArray;
import org.json.JSONObject;

public class MarketInfo {

	String url="http://cis2016-exchange1.herokuapp.com/api/market_data?next_stage";
	public static void main(String[] args) throws Exception {
		new MarketInfo();
	}
	
	public MarketInfo() throws Exception{
		String str=PostGet.sentGet(url);
		JSONObject obj = new JSONObject(str);
		JSONArray arr = obj.getJSONArray("market_data");
		for(int i=0;i<arr.length();i++){
			new StockInfo(arr.getJSONObject(i).getLong("time"),arr.getJSONObject(i).getString("symbol"),
					arr.getJSONObject(i).getDouble("bid"),arr.getJSONObject(i).getDouble("ask"));
		}
	}
	

}
