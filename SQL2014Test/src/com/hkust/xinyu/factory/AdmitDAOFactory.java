package com.hkust.xinyu.factory;

import com.hkust.xinyu.implbasicdao.AdmitBasicDAOImpl;
import com.hkust.xinyu.impldao.AdmitDAOImpl;

public class AdmitDAOFactory {
	public static AdmitDAOImpl getAdmitDAOImpl(){
		return new AdmitDAOImpl();
	}
	public static AdmitBasicDAOImpl getAdmitBasicDAOImpl(){
		return new AdmitBasicDAOImpl();
	}
}
