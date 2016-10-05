package com.credit;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AbstractVerifier;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;





import java.security.KeyStore;


import javax.net.ssl.SSLException;
    /*
     * This class provide the doPost_Server method to help sent a string to the server through https and SSL technique
     */
	
	public class HttpClient{
    public KeyStore myKs;	
   
    /*
     * Load the BKS document for the identity checking
     */
	public HttpClient() {
		super();
		
		
	}
	
	/*
	 * Sent jstring to the web server specified by urlReq through https technique
	 * return the response or "Failed" or the exception attribute if exceptions occur
	 */
	public String doPost_Server(String urlReq,String jString){
		String result ="";
		org.apache.http.client.HttpClient httpClient = HCBuilder_37178();;
		HttpPost postMethod = new HttpPost(urlReq);
		postMethod.setHeader("Content-Type", "application/json"); //Set the header of the request, using json format
		try {
			postMethod.setEntity(new StringEntity(jString,"utf-8"));
			HttpResponse httpResponse=httpClient.execute(postMethod);		
	        if(httpResponse != null )//&& httpResponse.getStatusLine().getStatusCode() == 200)
	       {  
	            HttpEntity httpEntity = httpResponse.getEntity();  
				result = EntityUtils.toString(httpEntity);
	        }
		}catch (ClientProtocolException e) {
			return e.toString();
		} catch (IOException e) {
			return e.toString();
		}
		
		//Log.d("Get",result);	
		if(result!=null)
		return result;
		else return "Failed";
		
	}
	//Sent get request to the server and return the responce
		public String doGet_Server(String urlReq,String jString){
			String result ="";
			org.apache.http.client.HttpClient httpClient = HCBuilder_37178();;
			HttpGet GetMethod = new HttpGet(urlReq);
			GetMethod.setHeader("Content-Type", "application/json"); //Set the header of the request, using json format
			try {
				//GetMethod.setEntity(new StringEntity(jString,"utf-8"));
				HttpResponse httpResponse=httpClient.execute(GetMethod);		
		        if(httpResponse != null )//&& httpResponse.getStatusLine().getStatusCode() == 200)
		       {  
		            HttpEntity httpEntity = httpResponse.getEntity();  
					result = EntityUtils.toString(httpEntity);
		        }
			}catch (ClientProtocolException e) {
				return e.toString();
			} catch (IOException e) {
				return e.toString();
			}
			
			//Log.d("Get",result);	
			if(result!=null)
			return result;
			else return "Failed";
			
		}
	//return the HttpClient with the BKS document
	 org.apache.http.client.HttpClient HCBuilder_37178(){
    	javax.net.ssl.SSLContext sslContext = null; 
		X509HostnameVerifier verifier = null;
		try {
	    	sslContext = SSLContexts.custom()
	    	        .useTLS()
	    	        .loadTrustMaterial(myKs)
	    	        .build();
	    	
		} catch (Exception e) {	
			e.printStackTrace();
		}	
		org.apache.http.client.HttpClient httpclient = HttpClients.custom()
    	        .setSslcontext(sslContext)
    	        .setHostnameVerifier(verifier)
    	    .build();
		return httpclient;
    }
}
