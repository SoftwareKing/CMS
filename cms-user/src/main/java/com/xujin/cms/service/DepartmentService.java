package com.xujin.cms.service;

import java.util.List;

import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Department;

/**
 * @Description:GroupService接口
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
public interface DepartmentService {
	public void add(Department department);//增加系
	public void delete(int id);//删除系
	public Department load(int id);//导入系
	public void update(Department department);//更新系
	
	public List<Department> listDepartment();//查询出来的系
	public Pager<Department> findDepartment();//查询分页系
	public void deleteDepartmentUsers(int did);//根据用户删除系
	
	
}
