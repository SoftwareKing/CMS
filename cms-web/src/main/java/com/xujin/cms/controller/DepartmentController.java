package com.xujin.cms.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xujin.cms.auth.AuthClass;
import com.xujin.cms.model.Department;
import com.xujin.cms.service.DepartmentService;
import com.xujin.cms.service.PeopleService;

/**
 * @Description:DepartmentController
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-27(创建日期)
 */
@RequestMapping("/admin/department")
@Controller("departmentController")
@AuthClass
public class DepartmentController {
	
	@Resource(name="peopleService")
	private PeopleService peopleService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	
	//系列表
	@RequestMapping("/departments")
	public String list(Model model) {
		model.addAttribute("datas",departmentService.findDepartment());
		return "department/list";
	}
	
	//添加用户组UI
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Department());
		return "department/add";
	}
	
	//添加用户组操作
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Department department,BindingResult br) {
		if(br.hasErrors()) {
			return "department/add";
		}
		departmentService.add(department);
		return "redirect:/admin/department/departments";
	}
	
	//更新用户组UI
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute(departmentService.load(id));
		return "department/update";
	}
	
	//更新用户组操作
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated Department department,BindingResult br) {
		if(br.hasErrors()) {
			return "department/update";
		}
		Department pd = departmentService.load(id);
		pd.setDescr(department.getDescr());
		pd.setName(department.getName());
		departmentService.update(pd);
		return "redirect:/admin/department/departments";
	}
	
	//删除系
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		departmentService.delete(id);
		return "redirect:/admin/department/departments";
	}
	
	//显示人员系的信息
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(departmentService.load(id));
		model.addAttribute("ps", peopleService.listDepartmentPeoples(id));
		return "department/show";
	}
	
	//清空用户组中的用户
	@RequestMapping("/clearPeoples/{id}")
	public String cleardepartmentPeoples(@PathVariable int id) {
		departmentService.deleteDepartmentUsers(id);
		return "redirect:/admin/department/departments";
	}
	
}
