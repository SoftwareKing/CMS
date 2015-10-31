package com.xujin.cms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
@Entity
@Table(name="t_people")
public class People {
	
	private int id;
	/**
	 * people姓名
	 */
	private String name;
	
	
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 用户的联系电话
	 */
	private String phone;
	/**
	 * 用户的状态：0表示停用，1表示启用
	 */
	private int status;
	
	/**
	 * 创建时间 不可以更新修改
	 */
	private Date createDate;
	
	//private Department department;
	/**所在系
	@ManyToOne
	@JoinColumn(name="d_id")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}*/
	
	/**职称*/
	private int zhichen;
	/**职务*/
	private String zhiwu;
	
	/**办公室**/
	private String bgs;
	
	/**教育背景**/
	private String jybg;
	
	/**研究方向**/
	private String yjfx;
	
	/**课程教学**/
	private String kcjx;
	
	/**学术兼职**/
	private String xsjz;
	
	/**科研项目**/
	private String kyxm;
	
	/**论文著作**/
	private String lwzz;
	
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Email(message="邮件格式不正确")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="zhichen")
	public int getZhichen() {
		return zhichen;
	}
	public void setZhichen(int zhichen) {
		this.zhichen = zhichen;
	}
	
	@Column(name="zhiwu")
	public String getZhiwu() {
		return zhiwu;
	}
	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	@Column(name="bgs")
	public String getBgs() {
		return bgs;
	}

	public void setBgs(String bgs) {
		this.bgs = bgs;
	}

	@Column(name="jybg")
	public String getJybg() {
		return jybg;
	}

	public void setJybg(String jybg) {
		this.jybg = jybg;
	}

	@Column(name="yjfx")
	public String getYjfx() {
		return yjfx;
	}

	public void setYjfx(String yjfx) {
		this.yjfx = yjfx;
	}

	@Column(name="kcjx")
	public String getKcjx() {
		return kcjx;
	}

	public void setKcjx(String kcjx) {
		this.kcjx = kcjx;
	}

	@Column(name="xsjz")
	public String getXsjz() {
		return xsjz;
	}

	public void setXsjz(String xsjz) {
		this.xsjz = xsjz;
	}

	@Column(name="kyxm")
	public String getKyxm() {
		return kyxm;
	}

	public void setKyxm(String kyxm) {
		this.kyxm = kyxm;
	}

	@Column(name="lwzz")
	public String getLwzz() {
		return lwzz;
	}

	public void setLwzz(String lwzz) {
		this.lwzz = lwzz;
	}
	
	public People() {
	}
	public People(int id, String name, String email, String phone, int status,
			Date createDate, int zhichen, String zhiwu, String bgs,
			String jybg, String yjfx, String kcjx, String xsjz, String kyxm,
			String lwzz) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.createDate = createDate;
		this.zhichen = zhichen;
		this.zhiwu = zhiwu;
		this.bgs = bgs;
		this.jybg = jybg;
		this.yjfx = yjfx;
		this.kcjx = kcjx;
		this.xsjz = xsjz;
		this.kyxm = kyxm;
		this.lwzz = lwzz;
	}
	
}
