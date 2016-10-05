package com.hkust.xinyu.factory;

import com.hkust.xinyu.implbasicdao.StudentBasicDAOImpl;
import com.hkust.xinyu.impldao.StudentDAOImpl;

public class StudentDAOFactory {
	public static StudentDAOImpl getStudentDAOImpl(){
		return new StudentDAOImpl();
	}
	public static StudentBasicDAOImpl getStudentBasicDAOImpl(){
		return new StudentBasicDAOImpl();
	}
}
