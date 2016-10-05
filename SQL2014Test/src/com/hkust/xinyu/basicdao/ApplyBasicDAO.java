package com.hkust.xinyu.basicdao;

import com.hkust.xinyu.bean.Apply;

public interface ApplyBasicDAO {
	//When a student submit application
	public boolean addApply(Apply apply);
	//When a student need to change the record
	public boolean updateApply(Apply apply);
	//When a student need to withdraw the application
	public boolean deleteApply(int id);
	//For the manager to use
	public Apply findApplyById(int id);
}
