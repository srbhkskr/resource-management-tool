package com.app.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the work_allocation database table.
 * 
 */
@Entity
@Table(name="work_allocation")
@NamedQuery(name="WorkAllocation.findAll", query="SELECT w FROM WorkAllocation w")
public class WorkAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="billing_code")
	private String billingCode;

	@Temporal(TemporalType.DATE)
	private Date end_date;

	private double percentage;

	@Temporal(TemporalType.DATE)
	private Date start_date;

	@Column(name="type_of_work")
	private String typeOfWork;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	//@JsonBackReference("employee")
	@JsonIgnoreProperties({"workAllocations", "projects","skills","subordinates" })
	private Employee employee;

	//bi-directional many-to-one association to Project
	@ManyToOne
	//@JsonBackReference("project")
	@JsonIgnoreProperties({"workAllocations", "employees","skills" })
	private Project project;

	public WorkAllocation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillingCode() {
		return this.billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public Date getEnd_date() {
		return this.end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Date getStart_date() {
		return this.start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getTypeOfWork() {
		return this.typeOfWork;
	}

	public void setTypeOfWork(String typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}