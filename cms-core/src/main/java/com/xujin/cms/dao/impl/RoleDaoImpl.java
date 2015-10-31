package com.xujin.cms.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.xujin.basic.dao.impl.BaseDaoImpl;
import com.xujin.cms.dao.RoleDao;
import com.xujin.cms.model.Role;
/**
 * @Description:RoleDao的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public List<Role> listRole() {
		return this.list("from Role");
	}

	@Override
	public void deleteRoleUsers(int rid) {
	this.UpdateByHql("delete UserRole ur where ur.role.id=?",rid);
	}

	
}
