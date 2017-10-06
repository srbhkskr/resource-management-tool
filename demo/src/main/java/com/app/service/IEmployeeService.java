package com.app.service;

import java.util.List;

import com.app.entity.Employee;
import com.app.entity.WorkAllocation;

public interface IEmployeeService {
	
	List<Employee> getAllEmployees();
    Employee getEmployeeById(int employeeId);
    boolean addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int employeeId);
	void addEmployeeSkill(Integer employeeId, Integer skillId);
	void removeEmployeeSkill(Integer employeeId, Integer skillId);
	void addWorkAllocation(Integer empid, WorkAllocation workallocation);
	void removeWorkAllocation(Integer empid, Integer workallocationid);

}
