package com.credit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;






public class PostGet {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
	}
	

		
		 public static String sendPost(String urladdress,String pic) throws URISyntaxException, IOException {
	            URL obj = new URL(urladdress);
	            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	            con.setRequestMethod("POST");        
	            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	            con.setRequestProperty("content-type", "application/json");
	            con.setDoOutput(true);
	            
	            
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            
	            
	            wr.writeBytes(pic);
	            wr.flush();
	            wr.close();
	            BufferedReader in = new BufferedReader(
	                    new InputStreamReader(con.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();

	            return response.toString();
	    }
		 
		 
		 public static String sentGet(String urlToRead) throws Exception {
		      StringBuilder result = new StringBuilder();
		      URL url = new URL(urlToRead);
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("GET");
		      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = rd.readLine()) != null) {
		         result.append(line);
		      }
		      rd.close();
		      return result.toString();
		   }
		 
		 public static String sentDelete(String urlToRead) throws Exception {
		      StringBuilder result = new StringBuilder();
		      URL url = new URL(urlToRead);
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("DELETE");
		      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = rd.readLine()) != null) {
		         result.append(line);
		      }
		      rd.close();
		      return result.toString();
		   }


	

}
