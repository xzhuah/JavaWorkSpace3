package com.hkust.xinyu.basicdao;
import com.hkust.xinyu.bean.Manager;
public interface ManagerBasicDAO {
	//Add New Manager to the database
	public boolean addManager(Manager manager);
	//update Manager information
	public boolean updateManager(Manager manager);
	//delete manager
	public boolean deleteManager(int id);
	//find manager by id
	public Manager findManagerById(int id);
	
	
}