package com.example.bluetooth.le;

import java.util.ArrayList;

import com.example.bluetooth.le.iBeaconClass.iBeacon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LeDeviceListAdapter extends BaseAdapter {

	// Adapter for holding devices found through scanning.

	private ArrayList<iBeacon> mLeDevices;
	private LayoutInflater mInflator;
	private Activity mContext;
	
	public LeDeviceListAdapter(Activity c) {
		super();
		mContext = c;
		mLeDevices = new ArrayList<iBeacon>();
		mInflator = mContext.getLayoutInflater();
	}

	public void addDevice(iBeacon device) {
		if(device==null)
			return;
		
		for(int i=0;i<mLeDevices.size();i++){
			String btAddress = mLeDevices.get(i).bluetoothAddress;
			if(btAddress.equals(device.bluetoothAddress)){
				mLeDevices.add(i+1, device);
				mLeDevices.remove(i);
				return;
			}
		}
		
		mLeDevices.add(device);
		
	}

	public iBeacon getDevice(int position) {
		return mLeDevices.get(position);
	}

	public void clear() {
		mLeDevices.clear();
	}

	@Override
	public int getCount() {
		return mLeDevices.size();
	}

	@Override
	public Object getItem(int i) {
		return mLeDevices.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		 //Log.d("Sentting", "In side getView");
		ViewHolder viewHolder;
		// General ListView optimization code.
		if (view == null) {
			view = mInflator.inflate(R.layout.listitem_device, null);
			viewHolder = new ViewHolder();
			viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);//MAC Address
			viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);//Device Name
			viewHolder.deviceUUID= (TextView)view.findViewById(R.id.device_beacon_uuid);//UUID
			viewHolder.deviceMajor_Minor=(TextView)view.findViewById(R.id.device_major_minor);//Minor Number
			viewHolder.devicetxPower_RSSI=(TextView)view.findViewById(R.id.device_txPower_rssi);//RSSI
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		iBeacon device = mLeDevices.get(i);
		final String deviceName = device.name;
		if (deviceName != null && deviceName.length() > 0)
			viewHolder.deviceName.setText(deviceName);
		else
			viewHolder.deviceName.setText(R.string.unknown_device);
		
		
		try{
			String MAC=device.bluetoothAddress;		//Get Mac address
			
			
			if(DeviceScanActivity.longitude!=0&&DeviceScanActivity.latitude!=0){
				new DataSenter(MAC,DeviceScanActivity.longitude,DeviceScanActivity.latitude,device.rssi,DeviceScanActivity.LocalMac,DeviceScanActivity.accuracy,mContext);
			}
		}catch (Exception e) {
    	  // debug=e.toString();
        	// Log.d("Sentting", e.toString());  
        	
        }
		//Show the information on the screen
		viewHolder.deviceAddress.setText(device.bluetoothAddress);
		//viewHolder.deviceUUID.setText(device.proximityUuid);
		viewHolder.deviceUUID.setText("GPS Accuracy: "+DeviceScanActivity.accuracy);
		viewHolder.deviceMajor_Minor.setText("longitude:"+DeviceScanActivity.longitude+",latitude:"+DeviceScanActivity.latitude);
		viewHolder.devicetxPower_RSSI.setText("txPower:"+device.txPower+",rssi:"+device.rssi);
		view.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//view.set
		return view;
	}
	
	
	class ViewHolder {
		TextView deviceName;
		TextView deviceAddress;
		TextView deviceUUID;
		TextView deviceMajor_Minor;
		TextView devicetxPower_RSSI;
	}
	
}
