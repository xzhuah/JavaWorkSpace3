package com.hkust.xinyu.bean;
public class Apply{
	private int ID;
	private int ProjectID;
	private int StudentID;
	private int ApplyRank;
	
	public Apply() {
		super();
	}
	public Apply(int iD, int projectID, int studentID, int applyRank) {
		super();
		ID = iD;
		ProjectID = projectID;
		StudentID = studentID;
		ApplyRank = applyRank;
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
	public int getApplyRank() {
		return ApplyRank;
	}
	public void setApplyRank(int applyRank) {
		ApplyRank = applyRank;
	}
	@Override
	public String toString() {	
		return "Apply: ID: "+ID+" ProjectID: "+ProjectID+" StudentID: "+StudentID+" ApplyRank: "+ApplyRank;
	}
	
	
	
}