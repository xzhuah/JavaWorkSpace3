package com.hkust.xinyu.basicdao;
import com.hkust.xinyu.bean.Teacher;
public interface TeacherBasicDAO {
	public boolean addTeacher(Teacher teacher);
	public boolean updateTeacher(Teacher teacher);
	public boolean deleteTeacher(int id);
	public Teacher findTeacherById(int id);
}