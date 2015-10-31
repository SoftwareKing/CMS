package com.xujin.cms.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Department;
import com.xujin.cms.model.People;

public interface PeopleService {
	/**
	 * 添加用户，需要判断人员名是否存在，如果存在抛出异常
	 * @param People 人员对象
	 * @param rids 人员的所有角色信息
	 * @param gids 人员的所有系信息
	 * @throws NoSuchAlgorithmException 
	 */
	public void add(People People,Integer[]dids);
	/**
	 * 删除人员，注意需要把人员和角色和系的对应关系删除
	 * 如果人员存在相应的文章不能删除
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 人员的更新，如果rids中的角色在人员中已经存在，就不做操作
	 * 如果rids中的角色在人员中不存在就要添加，如果人员中的角色不存在于rids中需要进行删除
	 * 对于Department而已同样要做这个操作
	 * @param People
	 * @param rids
	 * @param dids
	 */
	public void update(People People,Integer[] dids);
	/**
	 * 更新人员的状态
	 * @param id
	 */
	public void updateStatus(int id);
	/**
	 * 列表人员
	 */
	public Pager<People> findPeople();
	/**
	 * 获取人员信息
	 * @param id
	 * @return
	 */
	public People load(int id);
	
	/**
	 * 获取人员的所有系信息
	 * @param id
	 * @return
	 */
	public List<Department> listPeopleDepartments(int id);
	
	public List<Integer> listPeopleDepartmentIds(int id);
	
	public List<People> listDepartmentPeoples(int did);
	
	public List<People> listAllDepartmentPeoplesByzhichen(int did,int zhichenid);
	//更新People
	public void update(People People);
	
	
}
