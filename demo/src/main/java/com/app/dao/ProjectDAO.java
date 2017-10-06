package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Employee;
import com.app.entity.Project;
import com.app.entity.Skill;

@Transactional
@Repository
public class ProjectDAO implements IProjectDAO {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Project> getAllProjects() {
		String hql = "From Project as project ORDER BY project.id";
		return (List<Project>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Project getProjectById(int projectId) {
		// TODO Auto-generated method stub
		return entityManager.find(Project.class,projectId);
	}

	@Override
	public void addProject(Project project) {
		// TODO Auto-generated method stub
		entityManager.persist(project);
	}

	@Override
	public void updateProject(Project project) {
		// TODO Auto-generated method stub
		Project tempproject = getProjectById(project.getId());
		tempproject.setBilling_Type(project.getBilling_Type());
		tempproject.setEmployees(project.getEmployees());
		tempproject.setCustomer(project.getCustomer());
		tempproject.setName(project.getName());
		tempproject.setDomain(project.getDomain());
		tempproject.setEnd_Date(project.getEnd_Date());
		tempproject.setManager(project.getManager());
		tempproject.setStart_Date(project.getStart_Date());
		tempproject.setType(project.getType());
		entityManager.flush();
	}

	@Override
	public void deleteProject(int projectId) {
		// TODO Auto-generated method stub
		entityManager.remove(getProjectById(projectId));
	}



	@Override
	public boolean projectExists(String id) {
		// TODO Auto-generated method stub
		String hql = "From Project as project where id = ?";
		int count = entityManager.createQuery(hql).setParameter(1, id).getResultList().size();
		return count > 0 ? true : false;
	}
	
	@Override
	public void addSkill(Project project, Skill skill){
		project.addSkill(skill);
		entityManager.flush();
	}
	
	@Override
	public void removeSkill(Project project, Skill skill){
		project.removeSkill(skill);
		entityManager.flush();
	}
	
	@Override
	public void addEmployee(Project project, Employee emp){
		project.addEmployee(emp);
		entityManager.flush();
	}
	
	@Override
	public void removeEmployee(Project project, Employee emp){
		project.removeEmployee(emp);
		entityManager.flush();
	}

}
