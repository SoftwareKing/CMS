package com.xujin.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujin.cms.dao.RoleDao;
import com.xujin.cms.dao.UserDao;
import com.xujin.cms.model.CmsException;
import com.xujin.cms.model.Role;
import com.xujin.cms.model.User;
import com.xujin.cms.service.RoleService;

/**
 * @Description:RoleService的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource(name="roleDao")
	private RoleDao roleDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	

	@Override
	public void add(Role role) {
       this.roleDao.add(role);
		
	}

	@Override
	public void delete(int id) {

	    List<User> users =this.userDao.listRoleUsers(id);
	    if(users!=null&&users.size()>0) throw new CmsException("删除的角色中，还有用户");
	    this.roleDao.delete(id);
	}

	@Override
	public void update(Role role) {

		this.roleDao.update(role);
	}

	@Override
	public Role load(int id) {
		
		return this.roleDao.load(id);
	}

	@Override
	public List<Role> listRole() {
		return this.roleDao.listRole();
	}

	@Override
	public void deleteRoleUsers(int rid) {
		// TODO Auto-generated method stub
      this.roleDao.deleteRoleUsers(rid);
	}

}
