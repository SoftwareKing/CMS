package com.xujin.cms.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.xujin.basic.dao.impl.BaseDaoImpl;
import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.UserDao;
import com.xujin.cms.model.Group;
import com.xujin.cms.model.Role;
import com.xujin.cms.model.RoleType;
import com.xujin.cms.model.User;
import com.xujin.cms.model.UserGroup;
import com.xujin.cms.model.UserRole;

/**
 * @Description:UserDao的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	
	@Override
	public List<Role> listUserRoles(int userId) {
       String  hql="select ur.role from UserRole ur where ur.user.id=?";
       return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserRoleIds(int userId) {

		 String hql="select ur.role.id from UserRole ur where ur.user.id=?";
		 return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Group> listUserGroups(int userId) {
         String hql="select ug.group from UserGroup ug where ug.user.id=?";
         
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
		
	}

	@Override
	public List<Integer> listUserGroupIds(int userId) {

		String hql="select ug.group.id from UserGroup ug where ug.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
		
				
	}

	@Override
	public UserRole loadUserRole(int userId, int roleId) {
		String hql="select ur from UserRole ur left join fetch ur.user u left join fetch ur.role r where u.id=? and r.id=?";
		return (UserRole) this.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, roleId).uniqueResult();
	}

	@Override
	public UserGroup loadUserGroup(int userId, int groupId) {
       String hql="select ug from UserGroup ug left join fetch ug.user u left join fetch ug.group g where u.id=? and g.id=?";
       return (UserGroup) this.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, groupId).uniqueResult();
	}

	@Override
	public User loadByUsername(String username) {
        String hql ="from User where username=?";
        return (User)this.Queryobject(hql,username);
        
	}

	@Override
	public List<User> listRoleUsers(int roleId) {
       String hql = "select ur.user from UserRole ur where ur.role.id=?";
       return this.list(hql,roleId);
	}

	@Override
	public List<User> listRoleUsers(RoleType roleType) {
        String hql="select ur.user from UserRole ur where ur.role.roleType=?";
        
		return this.list(hql,roleType);
	}

	@Override
	public List<User> listGroupUsers(int gid) {
        String hql="select ug.user from UserGroup ug where ug.group.id=?";
        
		return this.list(hql,gid);
	}

	@Override
	public void addUserRole(User user, Role role) {

		UserRole ur=this.loadUserRole(user.getId(), role.getId());
		if(ur!=null) return;
		ur=new UserRole();
		ur.setRole(role);
		ur.setUser(user);
		this.getSession().save(ur);
		
		
	}

	@Override
	public void addUserGroup(User user, Group group) {

		   UserGroup ug=this.loadUserGroup(user.getId(), group.getId());
		  if(ug!=null) return;
		  ug =new UserGroup();
		  ug.setGroup(group);
		  ug.setUser(user);
		  this.getSession().save(ug);
		  
		  
	}

	@Override
	public void deleteUserRoles(int uid) {

		String hql ="delete UserRole ur where ur.user.id=?";
		this.UpdateByHql(hql, uid);
		
	}

	@Override
	public void deleteUserGroups(int gid) {

		//String hql="delete UserGroup ug where ug.group.id=?";
		//this.UpdateByHql(hql, gid);
		
		String hql = "delete UserGroup ug where ug.user.id=?";
		this.UpdateByHql(hql, gid);
		
		}

	@Override
	public Pager<User> findUser() {
		String hql="from User";
        return this.find(hql);
        
	}

	@Override
	public void deleteUserRole(int uid, int rid) {
      String hql="delete UserRole ur where ur.user.id=? and ur.role.id=?";
      this.UpdateByHql(hql, new Object[]{uid,rid});
      
	}

	@Override
	public void deleteUserGroup(int uid, int gid) {

		String hql="delete UserGroup ug where ug.user.id=? and ug.group.id=?";
		this.UpdateByHql(hql, new Object[]{uid,gid});
		
	}

	/*
	//新增的增加用户
	@Override
	public void add(User user, Integer[] rids, Integer[] gids) {
		// TODO Auto-generated method stub
		
	}*/

	
}
