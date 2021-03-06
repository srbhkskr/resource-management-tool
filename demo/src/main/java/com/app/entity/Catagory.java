package com.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the catagory database table.
 * 
 */
@Entity
@NamedQuery(name="Catagory.findAll", query="SELECT c FROM Catagory c")
public class Catagory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	public Catagory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}