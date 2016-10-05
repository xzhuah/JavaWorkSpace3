package com.hkust.xinyu.dao;

import com.hkust.xinyu.basicdao.ProjectBasicDAO;
import com.hkust.xinyu.bean.Project;

public interface ProjectDAO extends ProjectBasicDAO{
	//public final static short HIGHERTHAN=0,LOWERTHAN=1,EQUALTO=2;
	public Project findProjectByName(String name);
	public Project[] findProjectByTeacherId(int id);
	public Project[] findProjectByQuate(int quate);
	public Project[] findProjectByQuate(int quate,short compare);
}
