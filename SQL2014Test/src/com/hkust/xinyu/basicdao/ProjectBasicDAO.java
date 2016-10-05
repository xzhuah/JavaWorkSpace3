package com.hkust.xinyu.basicdao;
import com.hkust.xinyu.bean.Project;

public interface ProjectBasicDAO {
	public boolean addProject(Project project);
	public boolean updateProject(Project project);
	public boolean deleteProject(int id);
	public Project findProjectById(int id);
}