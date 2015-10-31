package com.xujin.cms.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.xujin.basic.dao.impl.BaseDaoImpl;
import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.DepartmentDao;
import com.xujin.cms.model.Department;

/**
 * @Description:DepartmentDao的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

	@Override
	public List<Department> listDepartment() {
		return this.list("from Department");
	}

	@Override
	public Pager<Department> findDepartment() {
		return this.find("from Department");
	}

	@Override
	public void deleteDepartmentUsers(int did) {
		this.UpdateByHql("delete PeopleDepartment pd where pd.department.id=?",did);
	}

	
}
