package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IEmployeeDAO;
import com.app.dao.ISkillDAO;
import com.app.dao.IWorkAllocationDAO;
import com.app.dao.WorkAllocationDAO;
import com.app.entity.Employee;
import com.app.entity.Skill;
import com.app.entity.WorkAllocation;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private IEmployeeDAO employeeDAO;
	
	@Autowired
	private ISkillDAO skillDAO;
	
	@Autowired
	private IWorkAllocationDAO workAllocationDAO;
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		
		return employeeDAO.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeeById(employeeId);
	}

	@Override
	public synchronized boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		if(employeeDAO.employeeExists(employee.getEmail()))
		{
			return false;
		}
		if(null != employee.getManager() )
			if (null != employee.getManager().getId()){
				employee.setManager(getEmployeeById(employee.getManager().getId()));
			}
		employeeDAO.addEmployee(employee);
		return true;
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		if(null != employee.getManager() )
			if (null != employee.getManager().getId()){
				employee.setManager(getEmployeeById(employee.getManager().getId()));
			}
		employeeDAO.updateEmployee(employee);
		
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		employeeDAO.deleteEmployee(employeeId);
	}
	
	@Override
	public void addEmployeeSkill(Integer employeeId, Integer skillId){
		
		employeeDAO.addSkill(employeeDAO.getEmployeeById(employeeId), skillDAO.getSkillById(skillId));
		
	}
	
	@Override
	public void removeEmployeeSkill(Integer employeeId, Integer skillId){
		
		employeeDAO.removeSkill(employeeDAO.getEmployeeById(employeeId), skillDAO.getSkillById(skillId));
		
	}
	
	@Override
	public void addWorkAllocation(Integer empid,  WorkAllocation workallocation) {
		// TODO Auto-generated method stub
		
		employeeDAO.addWorkAllocation(employeeDAO.getEmployeeById(empid), workallocation);
	}
	
	@Override
	public void removeWorkAllocation(Integer empid, Integer workallocationid) {
		// TODO Auto-generated method stub
		employeeDAO.removeWorkAllocation(employeeDAO.getEmployeeById(empid), workAllocationDAO.getWorkAllocationById(workallocationid));
	}
	

}
