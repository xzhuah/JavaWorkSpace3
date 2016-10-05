package com.hkust.xinyu.bean.entity;

import com.hkust.xinyu.bean.Manager;
import com.hkust.xinyu.bean.Student;
import com.hkust.xinyu.bean.Teacher;
import com.hkust.xinyu.factory.ManagerDAOFactory;
import com.hkust.xinyu.factory.StudentDAOFactory;
import com.hkust.xinyu.factory.TeacherDAOFactory;

public class ManagerEntity extends Manager{
	
	public ManagerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerEntity(int iD, String username, String password) {
		super(iD, username, password);
		// TODO Auto-generated constructor stub
	}
	//Checked 2015/12/15
	public boolean addTeacher(Teacher teacher){
		return TeacherDAOFactory.getTeacherBasicDAOImpl().addTeacher(teacher);
	}
	public boolean deleteTeacher(int id){
		return TeacherDAOFactory.getTeacherBasicDAOImpl().deleteTeacher(id);
	}
	//Checked 2015/12/15
	public boolean addStudent(Student student){
		/*boolean result= StudentDAOFactory.getStudentBasicDAOImpl().addStudent(student);
		if(result) student.setID(StudentDAOFactory.getStudentDAOImpl().findStudentByUserName(student.getUserName()).getID());
		return result;*/
		return StudentDAOFactory.getStudentBasicDAOImpl().addStudent(student);
	}
	public boolean deleteStudent(int id){
		return StudentDAOFactory.getStudentBasicDAOImpl().deleteStudent(id);
	}
	//Checked 2015/12/15
	public boolean updateManager(Manager manager,boolean check) throws Exception{
		if(check){
			if(this.ID==manager.getID()){
				return ManagerDAOFactory.getManagerBasicDAOImpl().updateManager(manager);
			}else{
				throw new Exception("The ID is not correct");
			}
		}else{
			return ManagerDAOFactory.getManagerBasicDAOImpl().updateManager(manager);
		}
		
	}
}
