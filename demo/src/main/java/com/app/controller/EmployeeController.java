package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.entity.Employee;
import com.app.entity.Project;
import com.app.entity.WorkAllocation;
import com.app.service.IEmployeeService;

@RestController
@Controller
@RequestMapping("user")
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id)
	{
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		
	}
	
	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		
	}
	
	@PostMapping("employee")
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee ,UriComponentsBuilder builder)
	{
		boolean flag = employeeService.addEmployee(employee);
		if(flag = false)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("employee/{id}").buildAndExpand(employee.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	@PutMapping("employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		
		employeeService.updateEmployee(employee);
		Employee employee1 = employeeService.getEmployeeById(employee.getId());
		return new ResponseEntity<Employee>(employee1, HttpStatus.OK);
	}
	
	@DeleteMapping("employee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer Id)
	{
		employeeService.deleteEmployee(Id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("employee/{employeeid}/addskills")
	public ResponseEntity<Employee> addEmployeeSkill(@RequestBody ArrayList<Integer> skills,@PathVariable("employeeid") Integer employeeid) {
		if(null != skills){
			for (int skillid: skills){
				employeeService.addEmployeeSkill(employeeid, skillid);
			}
		}
		
		Employee employee = employeeService.getEmployeeById(employeeid);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping("employee/{employeeid}/projects")
	public ResponseEntity<Set<Project>> getEmployeeProjects(@PathVariable("employeeid") Integer employeeid) {
		Employee employee = null;
		if(null != employeeid){
			employee = employeeService.getEmployeeById(employeeid);
		}
		
		return new ResponseEntity<>(employee.getProjects(), HttpStatus.OK);
	}
	
	@PutMapping("employee/{employeeid}/removeskills")
	public ResponseEntity<Employee> removeEmployeeSkill(@RequestBody ArrayList<Integer> skills,@PathVariable("employeeid") Integer employeeid) {
		for (int skillid: skills){
			employeeService.removeEmployeeSkill(employeeid, skillid);
		}
		Employee employee = employeeService.getEmployeeById(employeeid);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
/*	@PutMapping("employee/{employeeid}/addWorkAllocation")
	public ResponseEntity<Employee> addWorkAllocation(@RequestBody WorkAllocation allocation,@PathVariable("employeeid") Integer employeeid) {
		employeeService.addWorkAllocation(employeeid, allocation);
		Employee emp = employeeService.getEmployeeById(employeeid);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
	
	@PutMapping("employee/{employeeid}/removeWorkAllocation")
	public ResponseEntity<Employee> removeWorkAllocation(@RequestBody Integer allocationid,@PathVariable("employeeid") Integer employeeid) {
		employeeService.removeWorkAllocation(employeeid, allocationid);
		Employee emp = employeeService.getEmployeeById(employeeid);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}*/
	
	
	
	
}
