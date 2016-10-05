package com.hkust.xinyu.bean.entity;

import com.hkust.xinyu.bean.Admit;
import com.hkust.xinyu.bean.Apply;
import com.hkust.xinyu.bean.Project;
import com.hkust.xinyu.bean.Teacher;
import com.hkust.xinyu.factory.AdmitDAOFactory;
import com.hkust.xinyu.factory.ApplyDAOFactory;
import com.hkust.xinyu.factory.ProjectDAOFactory;
import com.hkust.xinyu.factory.TeacherDAOFactory;

public class TeacherEntity extends Teacher{
	
	public TeacherEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeacherEntity(int iD, String name, String userName, String password) {
		super(iD, name, userName, password);
		// TODO Auto-generated constructor stub
	}
		//Query 1 times checked on 2015/12/15
		public boolean AddProject(Project project,boolean check) throws Exception{
			if(check){
				if(project.getTeacherID()==this.ID)
				return ProjectDAOFactory.getProjectBasicDAOImpl().addProject(project);
				else{
					throw new Exception("ID is not correct");
				}
			}else{
				return ProjectDAOFactory.getProjectBasicDAOImpl().addProject(project);
			}
				
		}
		//Query 2 times checked on 2015/12/15
		public boolean updateProject(Project project,boolean check) throws Exception{
			if(check){
				if(ProjectDAOFactory.getProjectBasicDAOImpl().findProjectById(project.getID()).getTeacherID()!=this.ID){
					throw new Exception("This is not your project!");
				}
				
				if(project.getTeacherID()==this.ID)
				return ProjectDAOFactory.getProjectBasicDAOImpl().updateProject(project);
				else{
					throw new Exception("ID is not correct");
				}
			}else{
				return ProjectDAOFactory.getProjectBasicDAOImpl().updateProject(project);
			}
		}
		//Query 2 times checked 2015/12/16
		public boolean deleteProject(int id,boolean check) throws Exception{
			if(check){
				//Check if the project belongs to the teacher
				if(ProjectDAOFactory.getProjectBasicDAOImpl().findProjectById(id).getTeacherID()==this.ID){
					return ProjectDAOFactory.getProjectBasicDAOImpl().deleteProject(id);
				}else{
					throw new Exception("You do not have permission to delete that project");
				}
			}else{
				return ProjectDAOFactory.getProjectBasicDAOImpl().deleteProject(id);
			}
		}
		//Query time n checked 2015/12/16
		public boolean clearProjectApplyAndAdmit(int id,boolean check)throws Exception{
			if(check){
				if(ProjectDAOFactory.getProjectBasicDAOImpl().findProjectById(id).getTeacherID()==this.ID){
					Admit admits[]=AdmitDAOFactory.getAdmitDAOImpl().findAdmitByProjectID(id);
					for(int i=0;i<admits.length;i++){
						AdmitDAOFactory.getAdmitBasicDAOImpl().deleteAdmit(admits[i].getID());
					}
					Apply applys[]=ApplyDAOFactory.getApplyDAOImpl().findApplyByProjectId(id);
					for(int i=0;i<applys.length;i++){
						ApplyDAOFactory.getApplyBasicDAOImpl().deleteApply(applys[i].getID());
					}
					return true;
				}else{
					throw new Exception("This project is not your project");
				}
			}else{
				Admit admits[]=AdmitDAOFactory.getAdmitDAOImpl().findAdmitByProjectID(id);
				for(int i=0;i<admits.length;i++){
					AdmitDAOFactory.getAdmitBasicDAOImpl().deleteAdmit(admits[i].getID());
				}
				Apply applys[]=ApplyDAOFactory.getApplyDAOImpl().findApplyByProjectId(id);
				for(int i=0;i<applys.length;i++){
					ApplyDAOFactory.getApplyBasicDAOImpl().deleteApply(applys[i].getID());
				}
				return true;
			}
		}
		//Query 4 times checked 2015/12/16
		public boolean AddAdmit(Admit admit,boolean check) throws Exception{
			if(check){
				//Check whether the project is belong to this teacher
				Project project=ProjectDAOFactory.getProjectBasicDAOImpl().findProjectById(admit.getProjectID());
				if(project.getTeacherID()==this.ID){
					//check if the student has applied for the proejct
					Apply apply[]=ApplyDAOFactory.getApplyDAOImpl().findApplyByStudentId(admit.getStudentID());
					if(apply==null) throw new Exception("This student didn't apply for this project");
					//check if the project is full already
					if(AdmitDAOFactory.getAdmitDAOImpl().findAdmitByProjectID(admit.getProjectID()).length>=project.getQuate()){
						throw new Exception("This project is already full");
					}
					for(int i=0;i<apply.length;i++){
						if(apply[i].getProjectID()==admit.getProjectID())
						return AdmitDAOFactory.getAdmitBasicDAOImpl().addAdmit(admit);
					}	
					throw new Exception("This student didn't apply for this project");
						
				}else{
					throw new Exception("This teacher has no permission to admit this project");
				}
				
			}else{
				return AdmitDAOFactory.getAdmitBasicDAOImpl().addAdmit(admit);
			}
		}
		//Query 3 times checked 2015/12/16
		public boolean updateAdmit(Admit admit,boolean check) throws Exception{
			if(check){
				if(ProjectDAOFactory.getProjectBasicDAOImpl().findProjectById(admit.getProjectID()).getTeacherID()==this.ID){
					//check if the student has applied for the proejct
					Apply apply[]=ApplyDAOFactory.getApplyDAOImpl().findApplyByStudentId(admit.getStudentID());
					for(int i=0;i<apply.length;i++){
						if(apply[i].getProjectID()==admit.getProjectID())
						return AdmitDAOFactory.getAdmitBasicDAOImpl().updateAdmit(admit);
					}	
					throw new Exception("This student didn't apply for this project");
						
				}else{
					throw new Exception("This teacher has no permission to update this admit");
				}
			}else{
				return AdmitDAOFactory.getAdmitBasicDAOImpl().updateAdmit(admit);
			}
		}
		//Query 3 times checked 2015/12/16
		public boolean deleteAdmit(int id,boolean check) throws Exception{
			if(check){
				if(ProjectDAOFactory.getProjectBasicDAOImpl().findProjectById(AdmitDAOFactory.getAdmitBasicDAOImpl().findAdmitById(id).getProjectID()).getTeacherID()==this.ID){
					return AdmitDAOFactory.getAdmitBasicDAOImpl().deleteAdmit(id);
				}else{
					throw new Exception("You do not have permission to delete this admitting");
				}
			}else{
				return AdmitDAOFactory.getAdmitBasicDAOImpl().deleteAdmit(id);
			}
		}
		//Query 1 times checked 2015/12/15
		public boolean updataTeacher(Teacher teacher,boolean check) throws Exception{
			if(check){
				if(teacher.getID()!=this.ID){
					throw new Exception("The ID is not correct");
				}else{
					return TeacherDAOFactory.getTeacherBasicDAOImpl().updateTeacher(teacher);
				}
			}else{
				return TeacherDAOFactory.getTeacherBasicDAOImpl().updateTeacher(teacher);
			}
		}
		

}
