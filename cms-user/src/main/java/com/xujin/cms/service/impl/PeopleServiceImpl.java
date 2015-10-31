package com.xujin.cms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.DepartmentDao;
import com.xujin.cms.dao.PeopleDao;
import com.xujin.cms.model.CmsException;
import com.xujin.cms.model.Department;
import com.xujin.cms.model.People;
import com.xujin.cms.service.PeopleService;

@Service("peopleService")
public class PeopleServiceImpl implements PeopleService {

	@Resource(name="peopleDao")
	private PeopleDao peopleDao;
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	

	private void addPeopleDepartment(People People,int did) {
		Department Department = departmentDao.load(did);
		if(Department==null) throw new CmsException("要添加人员的系对象不存在");
		peopleDao.addPeopleDepartment(People, Department);
	}

	@Override
	public void add(People people, Integer[] dids)  {
		/*People tu = peopleDao.loadByPeoplename(people.getName());
		if(tu!=null)throw new CmsException("添加的人员对象已经存在，不能添加");*/
		people.setCreateDate(new Date());
		peopleDao.add(people);
		//添加系对象
		for(Integer did:dids) {
			addPeopleDepartment(people, did);
		}
	}

	@Override
	public void delete(int id) {
		//TODO 需要进行人员是否有文章的判断
		
		//1、删除人员管理的角色对象
		peopleDao.deletePeopleDepartments(id);
		//2、删除人员管理的系对象
		peopleDao.delete(id);
	}

	@Override
	public void update(People People,Integer[] dids) {
		//1、获取人员已经存在的系id和角色id
		List<Integer> edids = peopleDao.listPeopleDepartmentIds(People.getId());
		//2、判断，如果erids中不存在rids就要进行添加
		
		for(Integer did:dids) {
			if(!edids.contains(did)) {
				addPeopleDepartment(People,did);
			}
		}
		
		for(Integer edid:edids) {
			if(!ArrayUtils.contains(dids, edid)) {
				peopleDao.deletePeopleDepartment(People.getId(), edid);
			}
		}
	}

	@Override
	public void updateStatus(int id) {
		People u = peopleDao.load(id);
		if(u==null) throw new CmsException("修改状态的人员不存在");
		if(u.getStatus()==0) u.setStatus(1);
		else u.setStatus(0);
		peopleDao.update(u);
	}

	@Override
	public Pager<People> findPeople() {
		return peopleDao.findPeople();
	}

	@Override
	public People load(int id) {
		return peopleDao.load(id);
	}

	@Override
	public List<Department> listPeopleDepartments(int id) {
		return peopleDao.listPeopleDepartments(id);
	}
	
	@Override
	public List<Integer> listPeopleDepartmentIds(int id) {
		return peopleDao.listPeopleDepartmentIds(id);
	}
	@Override
	public List<People> listDepartmentPeoples(int did) {
		return peopleDao.listDepartmentPeoples(did);
	}
	
	
	@Override
	public void update(People People) {
		this.peopleDao.update(People);
	}

	@Override
	public List<People> listAllDepartmentPeoplesByzhichen(int did, int zhichenid) {
		
		return this.peopleDao.listDepartmentPeoplesByzhichen(did, zhichenid);
	}

	
	
	


}
