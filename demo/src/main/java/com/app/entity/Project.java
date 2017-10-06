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
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String billing_Type;

	private String customer;

	private String domain;

	@Temporal(TemporalType.DATE)
	private Date end_Date;

	private int manager;

	private String name;

	@Temporal(TemporalType.DATE)
	private Date start_Date;

	private String type;

	//bi-directional many-to-many association to Skill
	@ManyToMany
	@JoinTable(
		name="project_skill"
		, inverseJoinColumns={
			@JoinColumn(name="ProjectId")
			}
		, joinColumns={
			@JoinColumn(name="SkillId")
			}
		)
	private Set<Skill> skills;

	//bi-directional many-to-many association to Employee
	@ManyToMany//(mappedBy="projects")
	@JoinTable(
			name="project_employee"
			, inverseJoinColumns={
				@JoinColumn(name="Employee_Id")
				}
			, joinColumns={
				@JoinColumn(name="Project_Id")
				}
			)
	//@JsonBackReference("employee")
	@JsonIgnoreProperties({"workAllocations", "projects","skills","subordinates" })
	private Set<Employee> employees;
	
	@OneToMany(mappedBy="project")
	//@JsonIgnoreProperties({"project" })
	private Set<WorkAllocation> workAllocations;

	public Project() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBilling_Type() {
		return this.billing_Type;
	}

	public void setBilling_Type(String billing_Type) {
		this.billing_Type = billing_Type;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Date getEnd_Date() {
		return this.end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public int getManager() {
		return this.manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_Date() {
		return this.start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Skill> getSkills() {
		return this.skills;
	}

	//public void setSkills(List<Skill> skills) {
	//	this.skills = skills;
	//}

	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
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
	
	public void addEmployee(Employee employee) {
		if (null == this.employees){
			this.employees = new HashSet<Employee>();
		}
		this.employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		if (null == this.skills){
			return;
		}
		this.employees.remove(employee);
	}
	
	public Set<WorkAllocation> getWorkAllocations() {
		return this.workAllocations;
	}

	public void setWorkAllocations(Set<WorkAllocation> workAllocations) {
		this.workAllocations = workAllocations;
	}

	public WorkAllocation addWorkAllocation(WorkAllocation workAllocation) {
		getWorkAllocations().add(workAllocation);
		workAllocation.setProject(this);

		return workAllocation;
	}

	public WorkAllocation removeWorkAllocation(WorkAllocation workAllocation) {
		getWorkAllocations().remove(workAllocation);
		workAllocation.setProject(null);

		return workAllocation;
	}

}