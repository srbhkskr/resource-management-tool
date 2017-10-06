package com.app.dao;

import java.util.List;

import com.app.entity.Employee;
import com.app.entity.Project;
import com.app.entity.Skill;
import com.app.entity.WorkAllocation;;

public interface IEmployeeDAO {
	
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int employeeId);
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	boolean employeeExists(String Email);
	void addSkill(Employee emp, Skill skill);
	void allocateProject(Employee emp, Project project);
	void removeSkill(Employee emp, Skill skill);
	void addWorkAllocation(Employee emp, WorkAllocation workallocation);
	void removeWorkAllocation(Employee emp, WorkAllocation workallocation);
}
