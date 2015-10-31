package com.xujin.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.DepartmentDao;
import com.xujin.cms.dao.PeopleDao;
import com.xujin.cms.model.CmsException;
import com.xujin.cms.model.Department;
import com.xujin.cms.model.People;
import com.xujin.cms.service.DepartmentService;

/**
 * @Description:DepartmentService的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
	@Resource(name="peopleDao")
	private PeopleDao peopleDao;
	
	
	@Override
	public void add(Department department) {

		this.departmentDao.add(department);
	}

	@Override
	public void delete(int id) {
		List<People> peoples=this.peopleDao.listDepartmentPeoples(id);
		if(peoples!=null&&peoples.size()>0) throw new CmsException("所删除的系中还有人员,不可以删除");
		this.departmentDao.delete(id);
		
		
	}

	@Override
	public Department load(int id) {
		
		return this.departmentDao.load(id);
	}

	@Override
	public void update(Department department) {

		this.departmentDao.update(department);
		
	}

	@Override
	public List<Department> listDepartment() {
		
		return this.departmentDao.listDepartment();
	}

	@Override
	public Pager<Department> findDepartment() {
		return this.departmentDao.findDepartment();
	}

	@Override
	public void deleteDepartmentUsers(int did) {

		this.departmentDao.deleteDepartmentUsers(did);
	}
	
}
