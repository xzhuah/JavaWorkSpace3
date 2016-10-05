package com.hkust.xinyu.bean;


public class Manager{
	protected int ID;
	private String Username;
	private String Password;
	
	public Manager() {
		super();
	}
	public Manager(int iD, String username, String password) {
		super();
		ID = iD;
		Username = username;
		Password = password;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {	
		return "Manager: ID: "+ID+" Username: "+Username+" Password: "+Password;
	}
	
	
	
}
