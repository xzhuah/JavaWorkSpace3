package com.hkust.xinyu.dao;
import com.hkust.xinyu.basicdao.ManagerBasicDAO;
import com.hkust.xinyu.bean.Manager;
public interface ManagerDAO extends ManagerBasicDAO{
	//find by username
	public Manager findManagerByUsername(String username);
	
}
