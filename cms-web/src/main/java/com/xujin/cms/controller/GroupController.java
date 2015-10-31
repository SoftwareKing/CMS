package com.xujin.cms.controller;


import java.util.List;

import com.xujin.cms.service.UserService;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xujin.cms.auth.AuthClass;
import com.xujin.cms.model.ChannelTree;
import com.xujin.cms.model.Group;
import com.xujin.cms.service.GroupService;

/**
 * @Description:GroupController(用户组)
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-27(创建日期)
 */
@RequestMapping("/admin/group")
@Controller("groupController")
@AuthClass
public class GroupController {
	
	@Resource(name="groupService")
	private GroupService groupService;
	
	@Resource(name="userService")
	private UserService userService;
	
	//用户组列表
	@RequestMapping("/groups")
	public String list(Model model) {
		model.addAttribute("datas",groupService.findGroup());
		return "group/list";
	}
	
	//添加用户组UI
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Group());
		return "group/add";
	}
	
	//添加用户组操作
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Group group,BindingResult br) {
		if(br.hasErrors()) {
			return "group/add";
		}
		groupService.add(group);
		return "redirect:/admin/group/groups";
	}
	
	//更新用户组UI
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute(groupService.load(id));
		return "group/update";
	}
	
	//更新用户组操作
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated Group group,BindingResult br) {
		if(br.hasErrors()) {
			return "group/update";
		}
		Group ug = groupService.load(id);
		ug.setDescr(group.getDescr());
		ug.setName(group.getName());
		groupService.update(ug);
		return "redirect:/admin/group/groups";
	}
	
	//删除用户组
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		groupService.delete(id);
		return "redirect:/admin/group/groups";
	}
	
	//显示用户组信息
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(groupService.load(id));
		model.addAttribute("us", userService.listGroupUsers(id));
		return "group/show";
	}
	
	//清空用户组中的用户
	@RequestMapping("/clearUsers/{id}")
	public String clearGroupUsers(@PathVariable int id) {
		groupService.deleteGroupUsers(id);
		return "redirect:/admin/group/groups";
	}
	
	
	//用户组栏目列表
	@RequestMapping("/listChannels/{gid}")
	public String listChannels(@PathVariable int gid,Model model) {
		model.addAttribute(groupService.load(gid));
		return "/group/listChannel";
	}
	
	
	@RequestMapping("/groupTree/{gid}")
	public @ResponseBody List<ChannelTree> groupTree(@PathVariable Integer gid) {
		return groupService.generateGroupChannelTree(gid);
	}
	
	@RequestMapping("/setChannels/{gid}")
	public String setChannels(@PathVariable int gid,Model model) {
		model.addAttribute(groupService.load(gid));
		model.addAttribute("cids",groupService.listGroupChannelIds(gid));
		return "/group/setChannel";
	}
}
