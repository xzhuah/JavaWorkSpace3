package com.hkust.xinyu.basicdao;

import com.hkust.xinyu.bean.Student;

public interface StudentBasicDAO {
	public boolean addStudent(Student student);
	public boolean updateStudent(Student student);
	public boolean deleteStudent(int id);
	public Student findStudentById(int id);
}
