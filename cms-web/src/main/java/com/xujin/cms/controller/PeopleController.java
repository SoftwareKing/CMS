package com.xujin.cms.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xujin.cms.auth.AuthClass;
import com.xujin.cms.dto.PeopleDto;
import com.xujin.cms.model.People;
import com.xujin.cms.service.DepartmentService;
import com.xujin.cms.service.PeopleService;

@RequestMapping("/admin/people")
@Controller("peopleController")
@AuthClass("login")//登录用户可能访问
public class PeopleController {
	
	@Resource(name="peopleService")
    private PeopleService peopleService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@RequestMapping("/peoples")
	public String list(Model model)
	{
		
		model.addAttribute("datas",peopleService.findPeople());
		return "people/list";
		
		
	}
	//查询出，用户组或角色
	private void initAddUser(Model model) {
		model.addAttribute("departments", departmentService.listDepartment());
	}
	
	//添加User的UI
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("peopleDto",new PeopleDto());//user,user
		initAddUser(model);
		return "people/add";
	}
	
	//添加User操作
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid PeopleDto peopleDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			initAddUser(model);
			return "people/add";
		}
		peopleService.add(peopleDto.getPeople(), peopleDto.getDepartmentIds());
		return "redirect:/admin/people/peoples";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		People p= peopleService.load(id);
		System.out.println(p.getZhichen());
		model.addAttribute("peopleDto", 
				new PeopleDto(p,peopleService.listPeopleDepartmentIds(id)
				));
		initAddUser(model);
		return "people/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid PeopleDto peopleDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			//System.out.println(br.hasErrors());
			initAddUser(model);
			return "people/update";
		}
		People p = peopleService.load(id);//获取原来用户的对象
		//设置需要改的属性
		p.setId(peopleDto.getId());
		p.setName(peopleDto.getName());
		p.setBgs(peopleDto.getBgs());
		p.setCreateDate(peopleDto.getCreateDate());
		p.setJybg(peopleDto.getJybg());
		p.setKcjx(peopleDto.getKcjx());
		p.setKyxm(peopleDto.getKyxm());
		p.setLwzz(peopleDto.getLwzz());
		p.setXsjz(peopleDto.getXsjz());
		p.setYjfx(peopleDto.getYjfx());
		p.setZhichen(peopleDto.getZhichen());
		p.setZhiwu(peopleDto.getZhiwu());
		p.setEmail(peopleDto.getEmail());
		p.setPhone(peopleDto.getPhone());
		//设置完毕，然后更新数据库
		peopleService.update(p,peopleDto.getDepartmentIds());
		return "redirect:/admin/people/peoples";
	}
	
	//按ID删除
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		peopleService.delete(id);
		return "redirect:/admin/people/peoples";
	}
	
	@RequestMapping(value="/updateStatus/{id}",method=RequestMethod.GET)
	public String updateStatus(@PathVariable int id) {
		peopleService.updateStatus(id);
		return "redirect:/admin/people/peoples";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(peopleService.load(id));
		model.addAttribute("pd",peopleService.listPeopleDepartments(id));
		return "people/show";
	}
	
	
}
