package com.hkust.xinyu.factory;

import com.hkust.xinyu.implbasicdao.ApplyBasicDAOImpl;
import com.hkust.xinyu.impldao.ApplyDAOImpl;

public class ApplyDAOFactory {
	public static ApplyDAOImpl getApplyDAOImpl(){
		return new ApplyDAOImpl();
	}
	public static ApplyBasicDAOImpl getApplyBasicDAOImpl(){
		return new ApplyBasicDAOImpl();
	}
}
