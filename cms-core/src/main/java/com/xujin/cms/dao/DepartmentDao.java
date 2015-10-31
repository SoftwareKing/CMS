package com.xujin.cms.dao;

import java.util.List;

import com.xujin.basic.dao.BaseDao;
import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Department;

/**
 * @Description:DepartmentDao接口
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-10(创建日期)
 */
public interface DepartmentDao extends BaseDao<Department> {
	
	public List<Department> listDepartment();
	public Pager<Department> findDepartment();
	public void deleteDepartmentUsers(int did);
	
	
}
