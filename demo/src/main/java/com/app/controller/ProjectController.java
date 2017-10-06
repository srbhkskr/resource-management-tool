package com.app.controller;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.app.entity.Employee;
import com.app.entity.Project;
import com.app.service.IProjectService;

@Controller
@RequestMapping("user")
public class ProjectController {
	@Autowired
	private IProjectService projectService;
	
	@GetMapping("project/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") Integer id)
	{
		Project project = projectService.getProjectById(id);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
		
	}
	
	@GetMapping("projects")
	public ResponseEntity<List<Project>> getAllProjects()
	{
		
		List<Project> projects = projectService.getAllProjects();
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
		
	}
	
	@PostMapping("project")
	public ResponseEntity<Void> addProject(@RequestBody Project project ,UriComponentsBuilder builder)
	{
		boolean flag = projectService.addProject(project);
		if(flag = false)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("project/{id}").buildAndExpand(project.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	@PutMapping("project")
	public ResponseEntity<Project> updateProject(@RequestBody Project project) {
		projectService.updateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@DeleteMapping("project/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable("id") Integer Id)
	{
		projectService.deleteProject(Id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("project/{projectid}/addskills")
	public ResponseEntity<Project> addProjectSkill(@RequestBody ArrayList<Integer> skills,@PathVariable("projectid") Integer projectid) {
		for (int skillid: skills){
			projectService.addProjectSkill(projectid, skillid);
		}
		Project project = projectService.getProjectById(projectid);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@PutMapping("project/{projectid}/removeskills")
	public ResponseEntity<Project> removeProjectSkill(@RequestBody ArrayList<Integer> skills,@PathVariable("projectid") Integer projectid) {
		for (int skillid: skills){
			projectService.removeProjectSkill(projectid, skillid);
		}
		Project project = projectService.getProjectById(projectid);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@PutMapping("project/{projectid}/addemployees")
	public ResponseEntity<Project> addProjectEmployee(@RequestBody ArrayList<Integer> employees,@PathVariable("projectid") Integer projectid) {
		for (int employeeid: employees){
			projectService.addProjectEmployee(projectid, employeeid);
		}
		Project project = projectService.getProjectById(projectid);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@PutMapping("project/{projectid}/removeemployees")
	public ResponseEntity<Project> removeProjectEmployee(@RequestBody ArrayList<Integer> employees,@PathVariable("projectid") Integer projectid) {
		for (int employeeid: employees){
			projectService.removeProjectEmployee(projectid, employeeid);
		}
		Project project = projectService.getProjectById(projectid);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
}
