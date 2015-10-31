package com.xujin.cms.dao;

import java.util.List;

import com.xujin.basic.dao.BaseDao;
import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Department;
import com.xujin.cms.model.Group;
import com.xujin.cms.model.People;
import com.xujin.cms.model.PeopleDepartment;
import com.xujin.cms.model.Role;
import com.xujin.cms.model.RoleType;
import com.xujin.cms.model.User;

/**
 * @Description:UserDao接口
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-10(创建日期)
 */
public interface PeopleDao extends BaseDao<People> {
	
	/**
	 * 获取人员的所有系信息
	 * @param PeopleId
	 * @return
	 */
	public List<Department> listPeopleDepartments(int PeopleId);
	/**
	 * 获取人员的所有组的id
	 * @param PeopleId
	 * @return
	 */
	public List<Integer> listPeopleDepartmentIds(int PeopleId);
	
	/**
	 * 根据人员和组获取人员组关联对象
	 * @param PeopleId
	 * @param DepartmentId
	 * @return
	 */
	public PeopleDepartment loadPeopleDepartment(int PeopleId,int DepartmentId);
	/**
	 * 根据人员名获取人员对象
	 * @param Peoplename
	 * @return
	 */
	public People loadByPeoplename(String Peoplename);
	
	/**
	 * 获取某个组中的人员对象
	 * @param gid
	 * @return
	 */
	public List<People> listDepartmentPeoples(int did);
	
	/**
	 * 获取所有系中的某个职称的人员对象
	 * @param gid
	 * @return
	 */
	public List<People> listDepartmentPeoplesByzhichen(int did,int zhichenid);
	
	/**
	 * 添加人员组对象
	 * @param People
	 * @param Department
	 */
	public void addPeopleDepartment(People People,Department department);
	
	/**
	 * 删除人员的组信息
	 * @param gid
	 */
	public void deletePeopleDepartments(int did);
	
	public Pager<People> findPeople();
	
	/**
	 * 删除人员组对象
	 * @param uid
	 * @param gid
	 */
	public void deletePeopleDepartment(int pid,int did);
}
