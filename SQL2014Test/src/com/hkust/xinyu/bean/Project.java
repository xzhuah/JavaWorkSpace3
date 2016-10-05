package com.hkust.xinyu.bean;
public class Project{
	private int ID;
	private String Name;
	private int Quate;
	private String Requirement;
	private int TeacherID;
	
	public Project() {
		super();
	}
	public Project(int iD, String name, int quate, String requirement,
			int teacherID) {
		super();
		ID = iD;
		Name = name;
		Quate = quate;
		Requirement = requirement;
		TeacherID = teacherID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getQuate() {
		return Quate;
	}
	public void setQuate(int quate) {
		Quate = quate;
	}
	public String getRequirement() {
		return Requirement;
	}
	public void setRequirement(String requirement) {
		Requirement = requirement;
	}
	public int getTeacherID() {
		return TeacherID;
	}
	public void setTeacherID(int teacherID) {
		TeacherID = teacherID;
	}
	@Override
	public String toString() {	
		return "Project: ID: "+ID+" Name: "+Name+" Quate: "+Quate+" Requirement: "+Requirement+" TeacherID: "+TeacherID;
	}
	
}