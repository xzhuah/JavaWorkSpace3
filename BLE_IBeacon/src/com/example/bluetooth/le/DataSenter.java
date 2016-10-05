package com.example.bluetooth.le;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.util.Log;
/*
 * This class is used to send information about the i-beacon to the data base server
 */
public class DataSenter implements Runnable{
	private Thread t;
	private String MAC;
	private double longitude,latitude,accuracy;
	private int RSSI;
	private String reporter;
	public Activity context;
	//public final String postAddress="https://eek123.ust.hk/Demo/clientAPI/loggy";
	public final String postAddress="http://10.89.116.121:8079/urop/beaconData.jsp";
	public final String getAddress="https://eek123.ust.hk/Demo/clientAPI/loggy_poscal/";
	
	private final int calcPeriod=60;
	/*
	 * Sent data in a new thread
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		startSent();
	}
	//Get the row data when being constructed
	public DataSenter(String MAC,double longitude,double latitude,int RSSI,String reporter,double accuracy,Activity context) throws Exception{
		this.MAC=MAC;
		this.longitude=longitude;
		this.latitude=latitude;
		this.RSSI=RSSI;
		this.context=context;
		this.reporter=reporter;
		this.accuracy=accuracy;
		t=new Thread(this);
		t.start();
	}
	/*
	 *Generate a json form string using the data 
	 */
	public synchronized void startSent(){
		try {		
			JSONObject jsonEmployee = new JSONObject();
			try {
				jsonEmployee.put("longitude", longitude);
				jsonEmployee.put("latitude", latitude);
				jsonEmployee.put("MAC", MAC);
				jsonEmployee.put("RSSI", RSSI);
				jsonEmployee.put("Reporter", reporter);
				jsonEmployee.put("GPSAccuracy", accuracy);
				jsonEmployee.put("Time", System.currentTimeMillis());
				jsonEmployee.put("testMac",DeviceScanActivity.TestMac);
			} catch (JSONException e) {
				return;
			}		
			String result=jsonEmployee.toString(); //Get the string in a json form
			//Log.d("OUT", result);					//Log the string for debug
			//Check the data before sent
			if(result.length()>40&&MAC!=""&&this.accuracy<DeviceScanActivity.thread&&this.RSSI>DeviceScanActivity.rssiThread){
				//sentHttps(result);	For real urop
				//For my own
				sentToMe();
			}
		} catch (Exception e) {}
	}
	
	private void sentToMe(){
		String request=postAddress;
		//10.89.116.121:8079/urop/beaconData.jsp?MAC=78:A5:04:13:3C:AF&Reporter=78:A5:04:13:3C:AF&longitude=12&latitude=12&GPSAccuracy=11&RSSI=-85&Time=1424&testMac=ABC
		request+="?MAC="+this.MAC+"&Reporter="+this.reporter+"&longitude="+this.longitude+"&latitude="+this.latitude+"&GPSAccuracy="+this.accuracy+"&RSSI="+this.RSSI+"&Time="+System.currentTimeMillis()+"&testMac="+DeviceScanActivity.TestMac;
		//new HttpClient(context).doGet_Server(request,null);
		try {
			BufferedReader buf=new BufferedReader(new InputStreamReader(new URL(request).openConnection().getInputStream()));
			File dir = context.getExternalCacheDir();
       	 	File outFile = new File(dir, "info.txt");            
            writeToFile(outFile, request+"\n\r"+buf.readLine());
			buf.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Sent info to the target server, info should be in Json form
	 */
	public String sentHttps(final String info) throws IOException{
		try{ 
		   String i="TEST";
		  
		   i= new HttpClient(context).doPost_Server(postAddress,info);
		   
		   //i=new HttpClient(context).doPost_Server("https://eek123.ust.hk/svnsmartwifi/branch/Demo/clientAPI/reporter", info);
		   // sentHttpsRegister("ss");
		  // Log.d("TRUE","The feedback is "+i);
		  i= new HttpClient(context).doGet_Server(getAddress+this.MAC+"/"+calcPeriod,null);
		 // Log.d("TRUE","Update result is "+getAddress+this.MAC+"/"+calcPeriod+" "+i);
	    
       }catch(Exception e1){
    	  // Log.d("FALSE","FALSE"); 
    	  return "FALSE";
       }
		//Log.d("TRU","TRUE"); 
		return "TRUE";
	}
	/*
	 * Sent info to the target server, info should be in Json form
	 */
	public String sentHttpsRegister(final String info) throws IOException{
		try{ 
		   String i="{\"mac\":\"78:A5:04:13:3C:AF\",\"description\":\"Test\"}";
		   String ii="{\"mac\":\"78:A5:04:13:3D:D5\",\"description\":\"Test\"}";
		   String j="{\"mac\":\"78:A5:04:13:3F:AB\",\"description\":\"Test\"}";
		   String jj="{\"mac\":\"78:A5:04:13:3B:6E\",\"description\":\"Test\"}";
		   String jjj="{\"mac\":\"78:A5:04:13:3D:E8\",\"description\":\"Test\"}";
		   i= new HttpClient(context).doPost_Server(postAddress,i);
		   i= new HttpClient(context).doPost_Server(postAddress,ii);
		   i= new HttpClient(context).doPost_Server(postAddress,j);
		   i= new HttpClient(context).doPost_Server(postAddress,jj);
		   i= new HttpClient(context).doPost_Server(postAddress,jjj);
		  // Log.d("TRUE",i);
		 
	    
       }catch(Exception e1){
    	   //Log.d("FALSE","FALSE"); 
    	  return "FALSE";
       }
		//Log.d("TRU","TRUE"); 
		return "TRUE";
	}
	
	private void writeToFile(File fout, String data) {
        FileOutputStream osw = null;
        try {
            osw = new FileOutputStream(fout);
            osw.write(data.getBytes());
            osw.flush();
        } catch (Exception e) {
            ;
        } finally {
            try {
                osw.close();
            } catch (Exception e) {
                ;
            }
        }
    }
}
