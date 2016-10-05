package com.hkust.xinyu.bean;

public class Admit {
	private int ID;
	private int ProjectID;
	private int StudentID;
	
	public Admit() {
		super();
	}
	public Admit(int iD, int projectID, int studentID) {
		super();
		ID = iD;
		ProjectID = projectID;
		StudentID = studentID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getProjectID() {
		return ProjectID;
	}
	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String toString() {	
		return "Admit: ID: "+ID+" ProjectID: "+ProjectID+" StudentID: "+StudentID;
	}
	
}
