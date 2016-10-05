package com.hkust.xinyu.dao;
import com.hkust.xinyu.basicdao.TeacherBasicDAO;
import com.hkust.xinyu.bean.Teacher;
//Checked on 2015/12/14
public interface TeacherDAO extends TeacherBasicDAO{
	//Teacher may share the same name
	public Teacher[] findTeacherByName(String name);
	//Do not allow same username when apply for it
	public Teacher findTeacherByUsername(String username);
}
