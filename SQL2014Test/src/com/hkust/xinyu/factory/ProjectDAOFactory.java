package com.hkust.xinyu.factory;

import com.hkust.xinyu.implbasicdao.ProjectBasicDAOImpl;
import com.hkust.xinyu.impldao.ProjectDAOImpl;

public class ProjectDAOFactory {
	public static ProjectDAOImpl getProjectDAOImpl(){
		return new ProjectDAOImpl();
	}
	public static ProjectBasicDAOImpl getProjectBasicDAOImpl(){
		return new ProjectBasicDAOImpl();
	}
}
