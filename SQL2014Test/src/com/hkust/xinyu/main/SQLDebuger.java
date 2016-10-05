package com.hkust.xinyu.main;
import com.hkust.xinyu.factory.*;
import com.hkust.xinyu.util.sqlutil.SpecialCommand;
import com.hkust.xinyu.bean.*;
import com.hkust.xinyu.bean.entity.ManagerEntity;
import com.hkust.xinyu.bean.entity.StudentEntity;
import com.hkust.xinyu.bean.entity.TeacherEntity;
public class SQLDebuger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Teacher teacher=new Teacher();
		teacher.setID(1);
		teacher.setName("Teacher");
		teacher.setUserName("Teacher1");
		teacher.setPassword("abcd");
		TeacherDAOFactory.getTeacherDAOImpl().addTeacher(teacher);//Teacher1
		TeacherDAOFactory.getTeacherDAOImpl().addTeacher(teacher);//Teacher2
		teacher.setPassword("abcdefg");
		TeacherDAOFactory.getTeacherDAOImpl().updateTeacher(teacher);
		TeacherDAOFactory.getTeacherDAOImpl().findTeacherById(1);
		Teacher t[]=TeacherDAOFactory.getTeacherDAOImpl().findTeacherByName("Teacher");
		for(int i=0;i<t.length;i++){
			
		}
		Admit a=new Admit();
		a.setID(10);
		a.setProjectID(10);
		a.setStudentID(10);*/
		
		StudentEntity student=new StudentEntity(1,"ZHU Xinyu","xinyuzhu","51xinyu",4.3f,true,2,"CSE");
		StudentEntity student2=new StudentEntity(4,"Scenerwin","scenerwin","51scener",4.0f,true,2,"CSE");
		TeacherEntity teacher=new TeacherEntity(1,"Gary Chen","gary","gary");
		TeacherEntity teacher2=new TeacherEntity(4,"FUNG, Jimmy Chi Hung","Jimmy","jimmy");
		ManagerEntity me=new ManagerEntity(1,"xzhuah","960911NBR");//ManagerEntity me2=new ManagerEntity(2,"xzhuah","960911NBR");
		//me.addStudent(student2);
		//me.addTeacher(teacher2);
		/*try {
			teacher.updataTeacher(teacher, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			student.updateStudent(student, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			me.updateManager(me, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*Project project=new Project(2,"Math Reacher", 5, "GPA>3.6", teacher2.getID());
		try {
			teacher2.AddProject(project,true);
			//teacher.updateProject(project, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Apply apply2=new Apply(5,4,1,2);
		Apply apply1=new Apply(1,1,1,1);
		Apply apply3=new Apply(5,4,4,2);
		Apply apply4=new Apply(1,1,4,1);
		Admit admit=new Admit(1, 1, 4); 
		
		try {
			teacher2.clearProjectApplyAndAdmit(4, true);
			teacher2.deleteProject(4, true);
			//student2.deleteApply(1001, true);
			//teacher.AddAdmit(admit, true);
			//teacher.deleteAdmit(1, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			student.addApply(apply1, true);
//			student.addApply(apply2, true);
//			student2.addApply(apply3, true);
//			student2.addApply(apply4, true);
//			//student.updateApply(apply2, true);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//SpecialCommand.deleteTable("Tb_Apply", true);
		//SpecialCommand.deleteTable("Tb_Teacher", true);
		
		
	}

}
