package com.xujin.cms.dao;

import java.util.List;

import com.xujin.basic.dao.BaseDao;
import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Group;
import com.xujin.cms.model.Role;
import com.xujin.cms.model.RoleType;
import com.xujin.cms.model.User;
import com.xujin.cms.model.UserGroup;
import com.xujin.cms.model.UserRole;

/**
 * @Description:UserDao接口
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-10(创建日期)
 */
public interface UserDao extends BaseDao<User> {
	
	/*
	//新增的增加用户
	@param user 用户的对象
	 *@param rids 用户的所有角色ID
	 *@param gids 用户的组ID
	 * 
	public void add(User user,Integer[] rids,Integer[] gids);
	
	
	//新增的增加用户
		@param user 用户的对象
		 *@param rids 用户的所有角色ID
		 *@param gids 用户的组ID
		 * 
	public void update(User user,Integer[] rids,Integer[] gids);
	*/
	
	/**
	 * 获取用户的所有角色信息
	 * @param userId
	 * @return
	 */
	public List<Role> listUserRoles(int userId);
	/**
	 * 获取用户的所有角色的id
	 * @param userId
	 * @return
	 */
	public List<Integer> listUserRoleIds(int userId);
	/**
	 * 获取用户的所有组信息
	 * @param userId
	 * @return
	 */
	public List<Group> listUserGroups(int userId);
	/**
	 * 获取用户的所有组的id
	 * @param userId
	 * @return
	 */
	public List<Integer> listUserGroupIds(int userId);
	/**
	 * 根据用户和角色获取用户角色的关联对象
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public UserRole loadUserRole(int userId,int roleId);
	/**
	 * 根据用户和组获取用户组关联对象
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public UserGroup loadUserGroup(int userId,int groupId);
	/**
	 * 根据用户名获取用户对象
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username);
	/**
	 * 根据角色id获取用户列表
	 * @param roleId
	 * @return
	 */
	public List<User> listRoleUsers(int roleId);
	/**
	 * 根据角色类型获取用户对象
	 * @param roleType
	 * @return
	 */
	public List<User> listRoleUsers(RoleType roleType);
	/**
	 * 获取某个组中的用户对象
	 * @param gid
	 * @return
	 */
	public List<User> listGroupUsers(int gid);
	/**
	 * 添加用户角色对象
	 * @param user
	 * @param role
	 */
	public void addUserRole(User user,Role role);
	/**
	 * 添加用户组对象
	 * @param user
	 * @param group
	 */
	public void addUserGroup(User user,Group group);
	/**
	 * 删除用户的角色信息
	 * @param uid
	 */
	public void deleteUserRoles(int uid);
	/**
	 * 删除用户的组信息
	 * @param gid
	 */
	public void deleteUserGroups(int gid);
	
	public Pager<User> findUser();
	/**
	 * 删除用户角色对象
	 * @param uid
	 * @param rid
	 */
	public void deleteUserRole(int uid,int rid);
	/**
	 * 删除用户组对象
	 * @param uid
	 * @param gid
	 */
	public void deleteUserGroup(int uid,int gid);
}
