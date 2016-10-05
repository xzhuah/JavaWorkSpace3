package com.hkust.xinyu.factory;

import com.hkust.xinyu.implbasicdao.ManagerBasicDAOImpl;
import com.hkust.xinyu.impldao.ManagerDAOImpl;

public class ManagerDAOFactory {
	public static ManagerDAOImpl getManagerDAOImpl(){
		return new ManagerDAOImpl();
	}
	public static ManagerBasicDAOImpl getManagerBasicDAOImpl(){
		return new ManagerBasicDAOImpl();
	}
}
