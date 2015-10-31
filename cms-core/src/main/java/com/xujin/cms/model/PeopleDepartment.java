package com.xujin.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_people_department")
public class PeopleDepartment {
  
	private int id;
	private People people;
	private Department department;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="p_id")
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	
	@ManyToOne
	@JoinColumn(name="d_id")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	
	
}
