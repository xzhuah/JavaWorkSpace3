package com.hkust.xinyu.factory;

import com.hkust.xinyu.implbasicdao.TeacherBasicDAOImpl;
import com.hkust.xinyu.impldao.TeacherDAOImpl;

public class TeacherDAOFactory {
	public static TeacherDAOImpl getTeacherDAOImpl(){
		return new TeacherDAOImpl();
	}
	public static TeacherBasicDAOImpl getTeacherBasicDAOImpl(){
		return new TeacherBasicDAOImpl();
	}
}
