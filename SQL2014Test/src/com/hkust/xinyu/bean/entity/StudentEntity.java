package com.hkust.xinyu.bean.entity;

import com.hkust.xinyu.bean.Apply;
import com.hkust.xinyu.bean.Student;
import com.hkust.xinyu.factory.ApplyDAOFactory;
import com.hkust.xinyu.factory.StudentDAOFactory;

public class StudentEntity extends Student{
	
	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentEntity(int iD, String name, String userName, String password,
			float gpa, boolean gender, int grade, String major) {
		super(iD, name, userName, password, gpa, gender, grade, major);
		// TODO Auto-generated constructor stub
	}
		//Query 2 times checked 2015/12/15
		public boolean addApply(Apply apply,boolean check) throws Exception{
			if(check){
				if(apply.getStudentID()==this.ID&&apply.getApplyRank()>=RANKLOWER&&apply.getApplyRank()<=RANKUPPER){
					Apply[] applys=ApplyDAOFactory.getApplyDAOImpl().findApplyByStudentId(apply.getStudentID());
					if(applys!=null){
						if(applys.length>=RANKLIMIT) throw new Exception("You can not add project any more, the limit is "+RANKLIMIT);
						for(int i=0;i<applys.length;i++){
							if(applys[i].getApplyRank()==apply.getApplyRank()){
								throw new Exception("There is already a appliction with the same rank");
							}
						}
					}else{
						System.out.println("Return NULL");
					}
					return ApplyDAOFactory.getApplyBasicDAOImpl().addApply(apply);		
				}else{
					throw new Exception("ID or rank is wrong");
				}
			}else{
				return ApplyDAOFactory.getApplyBasicDAOImpl().addApply(apply);
			}
		}
		//Query 2 times checked 2015/12/15
		public boolean updateApply(Apply apply,boolean check) throws Exception{
			if(check){
				if(ApplyDAOFactory.getApplyBasicDAOImpl().findApplyById(apply.getID()).getStudentID()==this.ID){
					if(apply.getStudentID()==this.ID&&apply.getApplyRank()>=RANKLOWER&&apply.getApplyRank()<=RANKUPPER){					
						return ApplyDAOFactory.getApplyBasicDAOImpl().updateApply(apply);		
					}else{
						throw new Exception("ID or rank is wrong");
					}
				}else{
					throw new Exception("You have no permission to change other's applicatoin");
				}
			}else{
				return ApplyDAOFactory.getApplyBasicDAOImpl().updateApply(apply);		
			}
			
		}
		//Query 2 checked 2015/12/16
		public boolean deleteApply(int id,boolean check) throws Exception{
			if(check){
				if(ApplyDAOFactory.getApplyBasicDAOImpl().findApplyById(id).getStudentID()==this.ID){
					return ApplyDAOFactory.getApplyBasicDAOImpl().deleteApply(id);
				}else{
					throw new Exception("This is not your application");
				}
			}else{
				return ApplyDAOFactory.getApplyBasicDAOImpl().deleteApply(id);
			}
		}
		//Query 1 times checked 2015/12/15
		public boolean updateStudent(Student student, boolean check) throws Exception{
			if(check){
				if(student.getID()==this.ID){
					return StudentDAOFactory.getStudentBasicDAOImpl().updateStudent(student);
				}else{
					throw new Exception("ID is wrong");
				}
			}else{
				return StudentDAOFactory.getStudentBasicDAOImpl().updateStudent(student);
			}
		}
	

}
