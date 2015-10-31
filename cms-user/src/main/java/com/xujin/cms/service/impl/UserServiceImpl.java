package com.xujin.cms.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;



import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.GroupDao;
import com.xujin.cms.dao.RoleDao;
import com.xujin.cms.dao.UserDao;
import com.xujin.cms.model.CmsException;
import com.xujin.cms.model.Group;
import com.xujin.cms.model.Role;
import com.xujin.cms.model.User;
import com.xujin.cms.service.UserService;
import com.xujin.cms.utils.SecurityUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private RoleDao roleDao;
	private GroupDao groupDao;
	

	public UserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}
	@Inject
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}
	@Inject
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	private void addUserRole(User user,int rid) {
		//1、检查角色对象是否存在，如果不存在，就抛出异常
		Role role = roleDao.load(rid);
		if(role==null) throw new CmsException("要添加的用户角色不存在");
		//2、检查用户角色对象是否已经存在，如果存在，就不添加
		userDao.addUserRole(user, role);
	}
	
	private void addUserGroup(User user,int gid) {
		Group group = groupDao.load(gid);
		if(group==null) throw new CmsException("要添加用户的组对象不存在");
		userDao.addUserGroup(user, group);
	}

	@Override
	public void add(User user, Integer[] rids, Integer[] gids)  {
		User tu = userDao.loadByUsername(user.getUsername());
		if(tu!=null)throw new CmsException("添加的用户对象已经存在，不能添加");
		user.setCreateDate(new Date());
		//采用MD5加密用户的密码
		try {
			user.setPassword(SecurityUtil.md5(user.getUsername(),user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			throw new CmsException("加密失败:"+e.getMessage());
		}
		userDao.add(user);
		//添加角色对象
		for(Integer rid:rids) {
			this.addUserRole(user, rid);
		}
		//添加用户组对象
		for(Integer gid:gids) {
			addUserGroup(user, gid);
		}
	}

	@Override
	public void delete(int id) {
		//TODO 需要进行用户是否有文章的判断
		
		//1、删除用户管理的角色对象
		userDao.deleteUserGroups(id);
		//2、删除用户管理的组对象
		userDao.deleteUserRoles(id);
		userDao.delete(id);
	}

	@Override
	public void update(User user, Integer[] rids, Integer[] gids) {
		//1、获取用户已经存在的组id和角色id
		List<Integer> erids = userDao.listUserRoleIds(user.getId());
		List<Integer> egids = userDao.listUserGroupIds(user.getId());
		//2、判断，如果erids中不存在rids就要进行添加
		for(Integer rid:rids) {
			if(!erids.contains(rid)) {
				addUserRole(user, rid);
			}
		}
		for(Integer gid:gids) {
			if(!egids.contains(gid)) {
				addUserGroup(user,gid);
			}
		}
		//3、进行删除
		for(Integer erid:erids) {
			//ArrayUtils配置pom.xml文件，需要导入一些包
			if(!ArrayUtils.contains(rids, erid)) {
				userDao.deleteUserRole(user.getId(), erid);
			}
		}
		
		for(Integer egid:egids) {
			if(!ArrayUtils.contains(gids, egid)) {
				userDao.deleteUserGroup(user.getId(), egid);
			}
		}
	}

	@Override
	public void updateStatus(int id) {
		User u = userDao.load(id);
		if(u==null) throw new CmsException("修改状态的用户不存在");
		if(u.getStatus()==0) u.setStatus(1);
		else u.setStatus(0);
		userDao.update(u);
	}

	@Override
	public Pager<User> findUser() {
		return userDao.findUser();
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public List<Role> listUserRoles(int id) {
		return userDao.listUserRoles(id);
	}

	@Override
	public List<Group> listUserGroups(int id) {
		return userDao.listUserGroups(id);
	}
	@Override
	public List<Integer> listUserRoleIds(int id) {
		return userDao.listUserRoleIds(id);
	}
	@Override
	public List<Integer> listUserGroupIds(int id) {
		return userDao.listUserGroupIds(id);
	}
	@Override
	public List<User> listGroupUsers(int gid) {
		return userDao.listGroupUsers(gid);
	}
	@Override
	public List<User> listRoleUsers(int rid) {
		return userDao.listRoleUsers(rid);
	}
	//用户登录,系统错误全部记录到日志上面去，然后把日志下载下来处理
	@Override
	public User login(String username, String password) {
		User user=this.userDao.loadByUsername(username);
		if(user==null) throw new CmsException("用户名或者密码不正确");
		try {
			if(!SecurityUtil.md5(username,password).equals(user.getPassword())) {
				throw new CmsException("用户名或者密码不正确");
			}
		} catch (NoSuchAlgorithmException e) {
			throw new CmsException("密码加密失败:"+e.getMessage());
		}
		//判断用户状态，停用状态停，不可以登录，联系管理员处理
		if(user.getStatus()==0) throw new CmsException("用户已经停用，请与管理员联系");
		return user;
	}
	@Override
	public void update(User user) {
		this.userDao.update(user);
	}
	
	@Override
	public void updatePwd(int uid, String oldPwd, String newPwd) {

		try {
			//得到uid之后取出原来的对象
			User u = userDao.load(uid);
			//得到用户名和以前的密码加密后，跟原来的旧密码比较
			if(!SecurityUtil.md5(u.getUsername(),oldPwd).equals(u.getPassword())) {
				throw new CmsException("原始密码输入不正确");
			}
			//若正确，则修改密码
			u.setPassword(SecurityUtil.md5(u.getUsername(), newPwd));
			//更新用户
			userDao.update(u);
		} catch (NoSuchAlgorithmException e) {
			throw new CmsException("更新密码失败:"+e.getMessage());
		}
	}

}
