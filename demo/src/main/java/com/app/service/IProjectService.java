package com.app.service;

import java.util.List;

import com.app.entity.Project;

public interface IProjectService {
	
	List<Project> getAllProjects();
    Project getProjectById(int projectId);
    boolean addProject(Project project);
    void updateProject(Project project);
    void deleteProject(int projectId);
	void addProjectSkill(Integer projectId, Integer skillId);
	void removeProjectSkill(Integer projectId, Integer skillId);
	void addProjectEmployee(Integer projectId, Integer employeeId);
	void removeProjectEmployee(Integer projectId, Integer employeeId);

}
