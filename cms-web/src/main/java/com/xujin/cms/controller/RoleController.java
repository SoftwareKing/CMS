package com.xujin.cms.controller;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xujin.cms.auth.AuthClass;
import com.xujin.cms.model.Role;
import com.xujin.cms.model.RoleType;
import com.xujin.cms.service.RoleService;
import com.xujin.cms.service.UserService;
import com.xujin.cms.utils.EnumUtils;

/**
 * @Description:角色RoleController
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-27(创建日期)
 */
@Controller
@RequestMapping("/admin/role")
@AuthClass
public class RoleController {
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="userService")
	private UserService userService;

	
   //角色列表
	@RequestMapping("/roles")
	public String list(Model model) {
		model.addAttribute("roles", roleService.listRole());
		return "role/list";
	}
	
	//显示角色
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(roleService.load(id));
		model.addAttribute("us",userService.listRoleUsers(id));
		return "role/show";
	}
	
	//删除角色
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		roleService.delete(id);
		return "redirect:/admin/role/roles";
	}
	
	//清空角色相关用户
	@RequestMapping("/clearUsers/{id}")
	public String clearUsers(@PathVariable int id) {
		roleService.deleteRoleUsers(id);
		return "redirect:/admin/role/roles";
	}
	
	//添加角色UI
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Role());
		model.addAttribute("types", EnumUtils.enum2Name(RoleType.class));
		return "role/add";
	}
	//添加角色
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Role role) {
		roleService.add(role);
		return "redirect:/admin/role/roles";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute(roleService.load(id));
		model.addAttribute("types", EnumUtils.enum2Name(RoleType.class));
		return "role/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Role role) {
		Role er = roleService.load(id);
		er.setName(role.getName());
		er.setRoleType(role.getRoleType());
		roleService.update(er);
		return "redirect:/admin/role/roles";
	}
}
