package com.xujin.cms.service;

import java.util.List;

import com.xujin.cms.model.Role;


public interface RoleService {
	public void add(Role role);
	public void delete(int id);
	public void update(Role role);
	public Role load(int id);
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
