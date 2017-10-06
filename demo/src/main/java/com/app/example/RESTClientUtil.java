package com.app.example;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.entity.Employee;
import com.app.entity.Skill;
import com.app.service.SkillService;

public class RESTClientUtil {

	public void getEmployeeByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/employee/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee.class, 1);
        Employee employee = responseEntity.getBody();
        System.out.println("Id:"+employee.getId()+", Email:"+employee.getEmail()
                 +", Name:"+employee.getName());      
    }
    public void getAllEmployeesDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/employees";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Employee[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee[].class);
        Employee[] employees = responseEntity.getBody();
        System.out.println(employees);
        for(Employee employee : employees) {
              System.out.println("Id:"+employee.getId()+", Name:"+employee.getName()
                      +", Email: "+employee.getEmail());
        }
    }
    public void addEmployeeDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/employee";
	Employee objEmployee = new Employee();
	objEmployee.setId(3);
	objEmployee.setName("Saurabh");
	objEmployee.setRole("Developer");
	objEmployee.setEmail("keskar-saurabh@atmecs.com");
	//objEmployee.setManager(2);
	objEmployee.setPassword("qwerty");
	String strDate = "2015-09-14";
	SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date dateObj = null;
	try {
		dateObj = oDateFormat.parse(strDate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	objEmployee.setDate_of_joining(dateObj);
	Skill skill = new SkillService().getSkillById(1);
	
	skill.addEmployee(objEmployee);
	
	objEmployee.addSkill(skill);
	
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(objEmployee, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }

    public void updateEmployeeDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/employee";
	Employee objEmployee = new Employee();
	objEmployee.setId(1);
	objEmployee.setName("Saurabh");
	objEmployee.setRole("Developer");
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(objEmployee, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteEmployeeDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/employee/{id}";
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RESTClientUtil util = new RESTClientUtil();
        util.getEmployeeByIdDemo();
        util.getAllEmployeesDemo();
    	util.addEmployeeDemo();
    	util.getAllEmployeesDemo();
    	//util.updateEmployeeDemo();
    	//util.deleteEmployeeDemo();
    }   
} 


