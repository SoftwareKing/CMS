package com.xujin.cms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * People所属系对象
 * @author Administrator
 *
 */
@Entity
@Table(name="t_department")
public class Department {
	/**
	 * 组id
	 */
	private int id;
	/**
	 * 组名称
	 */
	private String name;
	/**
	 * 组描述信息
	 */
	private String descr;
	
	public Department() {
	}
	
	public Department(int id, String name) {
		
		this.id = id;
		this.name = name;
	}

	public Department(int id, String name, String descr) {
		super();
		this.id = id;
		this.name = name;
		this.descr = descr;
	}
	
	

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
