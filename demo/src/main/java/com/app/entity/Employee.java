package com.app.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	//@TableGenerator(initialValue=100, name = "EmployeeGen")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//GenerationType.TABLE, generator = "EmployeeGen")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date_of_joining;

	private String email;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="Manager_Id")
	@JsonIgnoreProperties({"workAllocations", "subordinates","skills","manager","password" })
	private Employee manager;

	@OneToMany(mappedBy="manager")
	private Set<Employee> subordinates = new HashSet<Employee>();

	private String name;

	private String password;

	private String role;

	//bi-directional many-to-many association to Project
	@ManyToMany//(mappedBy = "employees")
	@JoinTable(
		name="project_employee"
		, joinColumns={
			@JoinColumn(name="Employee_Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Project_Id")
			}
		)
	//@JsonBackReference("project")
	@JsonIgnoreProperties({"workAllocations", "employees","skills" })
	private Set<Project> projects;

	//bi-directional many-to-many association to Skill
	@ManyToMany
	@JoinTable(
		name="employee_skill"
		, joinColumns={
			@JoinColumn(name="Employee_Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Skill_Id")
			}
		)
	private Set<Skill> skills;
	
	@OneToMany(mappedBy="employee")
	private Set<WorkAllocation> workAllocations;

	public Employee() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate_of_joining() {
		return this.date_of_joining;
	}

	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee getManager() {
		return this.manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	
	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	
	public void addSkill(Skill skill) {
		if (null == this.skills){
			this.skills = new HashSet<Skill>();
		}
		this.skills.add(skill);
	}
	
	public void removeSkill(Skill skill) {
		if (null == this.skills){
			return;
		}
		this.skills.remove(skill);
	}
	
	public void allocateProject(Project project) {
		if (null == this.projects){
			this.projects = new HashSet<Project>();
		}
		this.projects.add(project);
	}
	
	public Set<WorkAllocation> getWorkAllocations() {
		return this.workAllocations;
	}

	public void setWorkAllocations(Set<WorkAllocation> workAllocations) {
		this.workAllocations = workAllocations;
	}

	public WorkAllocation addWorkAllocation(WorkAllocation workAllocation) {
		getWorkAllocations().add(workAllocation);
		workAllocation.setEmployee(this);

		return workAllocation;
	}

	public WorkAllocation removeWorkAllocation(WorkAllocation workAllocation) {
		getWorkAllocations().remove(workAllocation);
		workAllocation.setEmployee(null);

		return workAllocation;
	}

}