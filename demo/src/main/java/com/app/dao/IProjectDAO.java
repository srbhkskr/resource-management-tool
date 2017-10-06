package com.app.dao;

import java.util.List;

import com.app.entity.Employee;
import com.app.entity.Project;
import com.app.entity.Skill;;

public interface IProjectDAO {
	
	List<Project> getAllProjects();
	Project getProjectById(int projectId);
	void addProject(Project project);
	void updateProject(Project project);
	void deleteProject(int projectId);
	boolean projectExists(String id);
	void addSkill(Project project, Skill skill);
	void removeSkill(Project project, Skill skill);
	void addEmployee(Project project, Employee emp);
	void removeEmployee(Project project, Employee emp);
}
