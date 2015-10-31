package com.xujin.cms.dto;


import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.xujin.cms.model.People;

public class PeopleDto {
	
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
	
	/**
	 * 组id
	 */
	private Integer[] DepartmentIds;
	
	
	public Integer[] getDepartmentIds() {
		return DepartmentIds;
	}
	public void setDepartmentIds(Integer[] departmentIds) {
		DepartmentIds = departmentIds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotEmpty(message="用户名不能为空")
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getZhichen() {
		return zhichen;
	}
	public void setZhichen(int zhichen) {
		this.zhichen = zhichen;
	}
	public String getZhiwu() {
		return zhiwu;
	}
	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}
	public String getBgs() {
		return bgs;
	}
	public void setBgs(String bgs) {
		this.bgs = bgs;
	}
	public String getJybg() {
		return jybg;
	}
	public void setJybg(String jybg) {
		this.jybg = jybg;
	}
	public String getYjfx() {
		return yjfx;
	}
	public void setYjfx(String yjfx) {
		this.yjfx = yjfx;
	}
	public String getKcjx() {
		return kcjx;
	}
	public void setKcjx(String kcjx) {
		this.kcjx = kcjx;
	}
	public String getXsjz() {
		return xsjz;
	}
	public void setXsjz(String xsjz) {
		this.xsjz = xsjz;
	}
	public String getKyxm() {
		return kyxm;
	}
	public void setKyxm(String kyxm) {
		this.kyxm = kyxm;
	}
	public String getLwzz() {
		return lwzz;
	}
	public void setLwzz(String lwzz) {
		this.lwzz = lwzz;
	}
	
	public People getPeople() {
		People p = new People();
		p.setId(id);
		p.setBgs(bgs);
		p.setName(name);
		p.setCreateDate(createDate);
		p.setJybg(bgs);
		p.setKcjx(bgs);
	    p.setKyxm(kyxm);
		p.setLwzz(lwzz);
		p.setStatus(1);
		p.setXsjz(bgs);
		p.setYjfx(yjfx);
		p.setZhichen(zhichen);
		p.setZhiwu(zhiwu);
		p.setEmail(email);
		p.setPhone(phone);
		return p;
	}
	public PeopleDto() {
	}
	
	public PeopleDto(People people,Integer[] departmentIds) {
		
		this.setId(people.getId());
		this.setBgs(people.getBgs());
		this.setName(people.getName());
		this.setCreateDate(people.getCreateDate());
		this.setDepartmentIds(departmentIds);
		this.setJybg(people.getJybg());
		this.setKcjx(people.getKcjx());
		this.setKyxm(people.getKyxm());
		this.setLwzz(people.getLwzz());
		this.setStatus(1);
		this.setXsjz(people.getBgs());
		this.setYjfx(people.getYjfx());
		this.setZhichen(people.getZhichen());
		this.setZhiwu(people.getZhiwu());
		this.setEmail(people.getEmail());
		this.setPhone(people.getPhone());
		
	}
	public PeopleDto(People people,List<Integer> departmentIds) {
		
		this.setId(people.getId());
		this.setName(people.getName());
		this.setBgs(people.getBgs());
		this.setCreateDate(people.getCreateDate());
		this.setDepartmentIds(list2Array(departmentIds));
		this.setJybg(people.getJybg());
		this.setKcjx(people.getKcjx());
		this.setKyxm(people.getKyxm());
		this.setLwzz(people.getLwzz());
		this.setStatus(1);
		this.setXsjz(people.getBgs());
		this.setYjfx(people.getYjfx());
		this.setZhichen(people.getZhichen());
		this.setZhiwu(people.getZhiwu());
		this.setEmail(people.getEmail());
		this.setPhone(people.getPhone());
		
	}
	private Integer[] list2Array(List<Integer> datas) {
		Integer[] nums = new Integer[datas.size()];
		for(int i=0;i<datas.size();i++) {
			nums[i] = datas.get((int)i);
		}
		return nums;
	}
	
	
	
}
