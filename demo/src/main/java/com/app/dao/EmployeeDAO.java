package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Employee;
import com.app.entity.Project;
import com.app.entity.Skill;
import com.app.entity.WorkAllocation;

@Transactional
@Repository
public class EmployeeDAO implements IEmployeeDAO {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Employee> getAllEmployees() {
		String hql = "From Employee as employee ORDER BY employee.id";
		//System.out.println( entityManager.createQuery(hql).getResultList()) ;
		return (List<Employee>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return entityManager.find(Employee.class,employeeId);
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		entityManager.persist(employee);

	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee emp = getEmployeeById(employee.getId());
		if(employee.getDate_of_joining() != null && employee.getDate_of_joining() != emp.getDate_of_joining()){
			emp.setDate_of_joining(employee.getDate_of_joining());
		}
		
		if(employee.getEmail() != null && employee.getEmail() != emp.getEmail()){
			emp.setEmail(employee.getEmail());
		}
		
		if(employee.getManager() != null && employee.getManager() != emp.getManager()){
			emp.setManager(employee.getManager());
		}
		
		if(employee.getName() != null && employee.getName() != emp.getName()){
			emp.setName(employee.getName());
		}
		
		if(employee.getPassword() != null && employee.getPassword() != emp.getPassword()){
			emp.setPassword(employee.getPassword());
		}
		
		if(employee.getRole() != null && employee.getRole() != emp.getRole()){
			emp.setRole(employee.getRole());
		}
		//emp.setProjectAllocations(employee.getProjectAllocations());
		if(employee.getSkills() != null && employee.getSkills() != emp.getSkills()){
			emp.setSkills(employee.getSkills());
		}
		entityManager.flush();

	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		entityManager.remove(getEmployeeById(employeeId));
	}

	@Override
	public boolean employeeExists(String email) {
		// TODO Auto-generated method stub
		String hql = "From Employee as emp where email = ?";
		int count = entityManager.createQuery(hql).setParameter(1, email).getResultList().size();
		return count > 0 ? true : false;
	}
	
	@Override
	public void addSkill(Employee emp, Skill skill){
		emp.addSkill(skill);
		entityManager.flush();
	}
	
	@Override
	public void removeSkill(Employee emp, Skill skill){
		emp.removeSkill(skill);
		entityManager.flush();
	}
	
	@Override
	public void allocateProject(Employee emp, Project project){
		emp.allocateProject(project);
		entityManager.flush();
	}

	@Override
	public void addWorkAllocation(Employee emp, WorkAllocation workallocation) {
		// TODO Auto-generated method stub
		emp.addWorkAllocation(workallocation);
		entityManager.flush();
	}
	
	@Override
	public void removeWorkAllocation(Employee emp, WorkAllocation workallocation) {
		// TODO Auto-generated method stub
		emp.removeWorkAllocation(workallocation);
		entityManager.flush();
	}
	

}
