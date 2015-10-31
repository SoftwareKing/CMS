package com.xujin.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xujin.cms.auth.AuthClass;
import com.xujin.cms.auth.AuthMethod;
import com.xujin.cms.web.CmsSessionContext;

/**
 * @Description:CMS系统管理人员使用的Controller
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-10-14(创建日期)
 */
@Controller("adminController")
@AuthClass("login")
public class AdminController {

	@RequestMapping("/admin")
	@AuthMethod//表示只能管理员访问
	public String index() {
		return "admin/index";
	}
	
	@AuthMethod
	@RequestMapping("/admin/logout")
	public String logout(HttpSession session) {
		CmsSessionContext.removeSession(session);
		session.invalidate();
		return "redirect:/login";
		
		
	}
   
	
}
