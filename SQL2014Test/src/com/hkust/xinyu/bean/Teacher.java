package com.hkust.xinyu.bean;



public class Teacher{
	protected int ID;
	private String Name;
	private String UserName;
	private String Password;
	
	public Teacher() {
		super();
	}
	public Teacher(int iD, String name, String userName, String password) {
		super();
		ID = iD;
		Name = name;
		UserName = userName;
		Password = password;
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
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "Teacher: ID: "+ID+" Name: "+Name+" UserName: "+UserName+" Password: "+Password ;
	}
	
	
}