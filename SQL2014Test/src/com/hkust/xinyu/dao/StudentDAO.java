package com.hkust.xinyu.dao;

import com.hkust.xinyu.basicdao.StudentBasicDAO;
import com.hkust.xinyu.bean.Student;
//Checked on 2015/12/14
public interface StudentDAO extends StudentBasicDAO{
	//public final static short HIGHERTHAN=0,LOWERTHAN=1,EQUALTO=2;
	//Student may share same name
	public Student[] findStudentByName(String name);
	public Student findStudentByUserName(String name);
	public Student[] findStudentByGpa(double gpa,short compare);
	public Student [] findStudentByGrade(int grade);
	public Student[] findStudentByMajor(String major);
}
