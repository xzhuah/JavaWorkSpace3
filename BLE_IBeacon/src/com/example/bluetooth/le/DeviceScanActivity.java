/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bluetooth.le;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import com.example.bluetooth.le.iBeaconClass.iBeacon;

/**
 * Activity for scanning and displaying available Bluetooth LE devices.
 *  
 */
public class DeviceScanActivity extends ListActivity {
	private final static String TAG = DeviceScanActivity.class.getSimpleName();
	private final static String UUID_KEY_DATA = "0000ffe1-0000-1000-8000-00805f9b34fb";

	public static String LocalMac;
    private LeDeviceListAdapter mLeDeviceListAdapter;
    /**搜索BLE终端*/
    private BluetoothAdapter mBluetoothAdapter;
    
    public static double longitude=0;
    public static double latitude=0;
    public static double accuracy=0;
    public static int thread=100;
    public static int rssiThread=-120;
    public static String TestMac="None";
   
    public static LocationManager lm;
    
  
    private boolean mScanning;
    private Handler mHandler;
    // Stops start after SCAN_PERIOD ms;
    private static long SCAN_PERIOD = 1000;
    private static final long interval=3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////////////////Update thread///////////////////////
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
    	WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,DeviceScanActivity.class.getName());
    	wakeLock.acquire();
        try{
        	 File dir = this.getExternalCacheDir();
        	 File outFile = new File(dir, "thread.txt");    
        	 thread=Integer.parseInt(readFromFile(outFile));
        	 //Log.d("Read","ReadThreadSuccess "+thread);
        }catch(Exception e){
        	if (isExtStorageWritable()){
        		File dir = this.getExternalCacheDir();
           	 	File outFile = new File(dir, "thread.txt");            
                writeToFile(outFile, ""+thread);                          
            }
        }
        
        try{
       	 File dir = this.getExternalCacheDir();
       	 File outFile = new File(dir, "Rssithread.txt");    
       	rssiThread=Integer.parseInt(readFromFile(outFile));
       	 //Log.d("Read","ReadThreadSuccess "+rssiThread);
       }catch(Exception e){
       	if (isExtStorageWritable()){
       		File dir = this.getExternalCacheDir();
      	 	File outFile = new File(dir, "Rssithread.txt");            
      	 	writeToFile(outFile, ""+rssiThread);                          
           }
       }
        
        try{
       	 File dir = this.getExternalCacheDir();
       	 File outFile = new File(dir, "testMac.txt");    
       	 TestMac=readFromFile(outFile);
       	 //Log.d("Read","ReadThreadSuccess "+thread);
       }catch(Exception e){
       	if (isExtStorageWritable()){
       		File dir = this.getExternalCacheDir();
          	 	File outFile = new File(dir, "testMac.txt");            
               writeToFile(outFile, TestMac);                          
           }
       }
        
        

        
        
        
        /////////////////////////////////////////
        lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "Please open GPS...", Toast.LENGTH_SHORT).show();
            //返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   
            startActivityForResult(intent,0); 
            return;
        }

        new GPSUpdater(DeviceScanActivity.this,lm);
        
       
       
        getActionBar().setTitle(R.string.title_devices);
        mHandler = new Handler();
	
        
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        
        // Checks if Bluetooth is supported on the device.
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        LocalMac=getLocalMacAddress();
        //开启蓝牙
        mBluetoothAdapter.enable(); 
        new Thread(new Runnable(){
			@Override
			public void run() {
				mLeDeviceListAdapter = new LeDeviceListAdapter(DeviceScanActivity.this);
		        setListAdapter(mLeDeviceListAdapter);
		       
				while(true){
				try {
					if(DeviceScanActivity.longitude!=0&&DeviceScanActivity.latitude!=0){
						new DataSenter("TEST",DeviceScanActivity.longitude,DeviceScanActivity.latitude,0,DeviceScanActivity.LocalMac,DeviceScanActivity.accuracy,DeviceScanActivity.this);
					}
					mLeDeviceListAdapter.clear();
			        scanLeDevice(true);
				}
			    catch (Exception e) {}
		        try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {}
				}
				
			}
        	
        }).start();
        
    }


    @Override
    protected void onResume() {
        super.onResume();
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       
    }
  
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    invalidateOptionsMenu();
                }
            }, SCAN_PERIOD);
            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        invalidateOptionsMenu();
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {

        	final iBeacon ibeacon = iBeaconClass.fromScanData(device,rssi,scanRecord);
        	runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLeDeviceListAdapter.addDevice(ibeacon);
                    mLeDeviceListAdapter.notifyDataSetChanged();
                    
                }
            });
        }
    };
    
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
    private String readFromFile(File fin) {
        StringBuilder data = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                     new FileInputStream(fin), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            ;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                ;
            }
        }
        return data.toString();
    }
    public boolean isExtStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean isExtStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    //Get local mac adress
    public String getLocalMacAddress() {  
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);  
        WifiInfo info = wifi.getConnectionInfo();  
        return info.getMacAddress();  
    }  

	
    
   
   

}