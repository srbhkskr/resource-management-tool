package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IEmployeeDAO;
import com.app.dao.IProjectDAO;
import com.app.dao.ISkillDAO;
import com.app.entity.Project;

@Service
public class ProjectService implements IProjectService {
	
	@Autowired
	private IProjectDAO projectDAO;
	
	@Autowired
	private ISkillDAO skillDAO;
	
	@Autowired
	private IEmployeeDAO employeeDAO;
	
	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		
		return projectDAO.getAllProjects();
	}

	@Override
	public Project getProjectById(int projectId) {
		// TODO Auto-generated method stub
		return projectDAO.getProjectById(projectId);
	}

	@Override
	public synchronized boolean addProject(Project project) {
		// TODO Auto-generated method stub

		projectDAO.addProject(project);
		return true;
	}

	@Override
	public void updateProject(Project project) {
		// TODO Auto-generated method stub
		projectDAO.updateProject(project);
		
	}

	@Override
	public void deleteProject(int projectId) {
		// TODO Auto-generated method stub
		projectDAO.deleteProject(projectId);
	}
	
	@Override
	public void addProjectSkill(Integer projectId, Integer skillId){
		
		projectDAO.addSkill(projectDAO.getProjectById(projectId), skillDAO.getSkillById(skillId));
		
	}
	
	@Override
	public void removeProjectSkill(Integer projectId, Integer skillId){
		
		projectDAO.removeSkill(projectDAO.getProjectById(projectId), skillDAO.getSkillById(skillId));
		
	}
	
	@Override
	public void addProjectEmployee(Integer projectId, Integer employeeId){
		
		projectDAO.addEmployee(projectDAO.getProjectById(projectId), employeeDAO.getEmployeeById(employeeId));
		
	}
	
	@Override
	public void removeProjectEmployee(Integer projectId, Integer employeeId){
		
		projectDAO.removeEmployee(projectDAO.getProjectById(projectId), employeeDAO.getEmployeeById(employeeId));
		
	}
	

}
