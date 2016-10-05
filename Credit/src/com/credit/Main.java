package com.credit;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static String uid="st5irvK-CWyVPyCmJTjRzg";
	
	public static String stockDetail="http://cis2016-exchange1.herokuapp.com/api/market_data/";
	
	public static String getStockURL(String postFix){
		return stockDetail+postFix;
	}
	
	public static void main(String[] args) throws InterruptedException {
	
		while(true){
			
			try {
			new TeamState();
			new MarketInfo();
			new StockDetail("0001");
			new StockDetail("0005");	
			new StockDetail("0388");	
			new StockDetail("0386");	
			new StockDetail("3988");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				Thread.sleep(1000);
			}
		}
	}

}
