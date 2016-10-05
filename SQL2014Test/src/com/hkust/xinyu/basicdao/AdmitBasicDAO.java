package com.hkust.xinyu.basicdao;

import com.hkust.xinyu.bean.Admit;

public interface AdmitBasicDAO {	
	public boolean addAdmit(Admit admit);
	public boolean updateAdmit(Admit admit);
	public boolean deleteAdmit(int id);	
	public Admit findAdmitById(int id);
}
