package com.xujin.cms.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.xujin.basic.dao.impl.BaseDaoImpl;
import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.PeopleDao;
import com.xujin.cms.model.Department;
import com.xujin.cms.model.People;
import com.xujin.cms.model.PeopleDepartment;

/**
 * @Description:UserDao的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@SuppressWarnings("unchecked")
@Repository("peopleDao")
public class PeopleDaoImpl extends BaseDaoImpl<People> implements PeopleDao {

	@Override
	public List<Department> listPeopleDepartments(int PeopleId) {
         String hql="select pd.department from PeopleDepartment pd where pd.people.id=?";
         
		return this.getSession().createQuery(hql).setParameter(0, PeopleId).list();
		
	}

	@Override
	public List<Integer> listPeopleDepartmentIds(int PeopleId) {

		String hql="select pd.department.id from PeopleDepartment pd where pd.people.id=?";
		return this.getSession().createQuery(hql).setParameter(0, PeopleId).list();
		
				
	}

	

	@Override
	public PeopleDepartment loadPeopleDepartment(int PeopleId, int DepartmentId) {
       String hql="select pd from PeopleDepartment pd left join fetch pd.people u left join fetch pd.department g where u.id=? and g.id=?";
       return (PeopleDepartment) this.getSession().createQuery(hql).setParameter(0, PeopleId).setParameter(1, DepartmentId).uniqueResult();
	}

	@Override
	public People loadByPeoplename(String Peoplename) {
        String hql ="from People where name=?";
        return (People)this.Queryobject(hql,Peoplename);
        
	}

	

	@Override
	public List<People> listDepartmentPeoples(int did) {
        String hql="select pd.people from PeopleDepartment pd where pd.department.id=?";
        return this.list(hql,did);
	}

	

	@Override
	public void addPeopleDepartment(People people, Department department) {

		   PeopleDepartment pd=this.loadPeopleDepartment(people.getId(), department.getId());
		  if(pd!=null) return;
		  pd =new PeopleDepartment();
		  pd.setDepartment(department);
		  pd.setPeople(people);
		  this.getSession().save(pd);
		  
		  
	}



	@Override
	public void deletePeopleDepartments(int did) {

		//String hql="delete PeopleDepartment pd where pd.Department.id=?";
		//this.UpdateByHql(hql, did);
		
		String hql = "delete PeopleDepartment pd where pd.people.id=?";
		this.UpdateByHql(hql, did);
		
		}

	@Override
	public Pager<People> findPeople() {
		String hql="from People";
        return this.find(hql);
        
	}
	

	

	@Override
	public void deletePeopleDepartment(int pid, int did) {

		String hql="delete PeopleDepartment pd where pd.people.id=? and pd.department.id=?";
		this.UpdateByHql(hql, new Object[]{pid,did});
		
	}

	@Override
	public List<People> listDepartmentPeoplesByzhichen(int did,int zhichenid) {
		 
		 String hql="select pd.people from PeopleDepartment pd where pd.department.id=? and pd.people.zhichen=?";
	     return this.list(hql,new Object[]{did,zhichenid});
	}


	
}
