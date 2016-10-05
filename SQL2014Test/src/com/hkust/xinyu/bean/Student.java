package com.hkust.xinyu.bean;


public class Student{
	protected final int RANKUPPER=2;
	protected final int RANKLOWER=1;
	protected final int RANKLIMIT=2;
	protected int ID;
	private String Name;
	private String UserName;
	private String Password;
	private float Gpa;
	private boolean Gender;//1 male 0 female
	private int Grade;
	private String Major;
	
	public Student() {
		super();
	}
	public Student(int iD, String name, String userName, String password,
			float gpa, boolean gender, int grade, String major) {
		super();
		ID = iD;
		Name = name;
		UserName = userName;
		Password = password;
		Gpa = gpa;
		Gender = gender;
		Grade = grade;
		Major = major;
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
	public float getGpa() {
		return Gpa;
	}
	public void setGpa(float gpa) {
		Gpa = gpa;
	}
	public boolean isGender() {
		return Gender;
	}
	public void setGender(boolean gender) {
		Gender = gender;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	public String getMajor() {
		return Major;
	}
	public void setMajor(String major) {
		Major = major;
	}
	@Override
	public String toString() {	
		return "Student: "+"ID: "+ID+" Name: "+Name+" UserName: "+UserName+" Password: "+Password+" GPA: "+Gpa
				+" Gender: "+(Gender?"Male":"Female")+" Grade: "+Grade+" Major: "+Major;
	}
	
	
	
	
}