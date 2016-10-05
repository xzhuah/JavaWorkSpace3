package com.hkust.xinyu.dao;

import com.hkust.xinyu.basicdao.ApplyBasicDAO;
import com.hkust.xinyu.bean.Apply;
//Checked on 2015/12/14
public interface ApplyDAO extends ApplyBasicDAO{
	//When a student need to find his record
	public Apply[] findApplyByStudentId(int id);
	//When a professor need to find who applied for his project
	public Apply[] findApplyByProjectId(int id);
	//When a professor need to find who applied for his project as a specified selection
	public Apply[] findApplyByProjectId(int id,int rank);
	public Apply[] findApplyByRank(int rank);
}
