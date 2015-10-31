package com.xujin.cms.dao;

import java.util.List;

import com.xujin.basic.dao.BaseDao;
import com.xujin.cms.model.Role;
/**
 * @Description:RoleDao接口
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-10(创建日期)
 */
public interface RoleDao extends BaseDao<Role> {
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
