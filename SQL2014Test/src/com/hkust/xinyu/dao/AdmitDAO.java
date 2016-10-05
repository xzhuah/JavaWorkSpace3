package com.hkust.xinyu.dao;

import com.hkust.xinyu.basicdao.AdmitBasicDAO;
import com.hkust.xinyu.bean.Admit;
//Checked on 2015/12/14
public interface AdmitDAO extends AdmitBasicDAO{
	public Admit[] findAdmitByProjectID(int id);
	public Admit[] findAdmitByStudentID(int id);
}
