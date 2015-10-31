package com.xujin.cms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xujin.cms.model.Role;
import com.xujin.cms.model.RoleType;
import com.xujin.cms.model.User;
import com.xujin.cms.service.UserService;
import com.xujin.cms.utils.SecurityCode;
import com.xujin.cms.utils.SecurityImage;
import com.xujin.cms.web.CmsSessionContext;

@Controller("loginController")
public class LoginController {
	
	private static final long serialVersionUID = 1496691731440581303L;  
    //图片流  
    private ByteArrayInputStream imageStream;  
   
    public ByteArrayInputStream getImageStream() {  
        return imageStream;  
    }  
    public void setImageStream(ByteArrayInputStream imageStream) {  
        this.imageStream = imageStream;  
    }  
  
	
	@Resource(name="userService")
	private UserService userService;

	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,String checkcode,Model model,HttpSession session) {
		
		String cc = (String)session.getAttribute("cc");
		if(!cc.equals(checkcode)) {
			model.addAttribute("error","验证码出错，请重新输入");
			return "admin/login";
		}
		User loginUser = userService.login(username, password);
		session.setAttribute("loginUser", loginUser);
		List<Role> rs = userService.listUserRoles(loginUser.getId());
		boolean isAdmin = isRole(rs,RoleType.ROLE_ADMIN);
		session.setAttribute("isAdmin", isAdmin);
		if(!isAdmin) {
			//session.setAttribute("allActions", getAllActions(rs, session));
			session.setAttribute("isAudit", isRole(rs,RoleType.ROLE_AUDIT));
			session.setAttribute("isPublish", isRole(rs,RoleType.ROLE_PUBLISH));
		}
		session.removeAttribute("cc");
		CmsSessionContext.addSessoin(session);
		return "redirect:/admin";
	}
	
	/*@SuppressWarnings("unchecked")
	private Set<String> getAllActions(List<Role> rs,HttpSession session) {
		Set<String> actions = new HashSet<String>();
		Map<String,Set<String>> allAuths = (Map<String,Set<String>>)session.getServletContext().getAttribute("allAuths");
		actions.addAll(allAuths.get("base"));
		for(Role r:rs) {
			if(r.getRoleType()==RoleType.ROLE_ADMIN) continue;
			actions.addAll(allAuths.get(r.getRoleType().name()));
		}
		return actions;
	}*/
	
	
	private boolean isRole(List<Role> rs,RoleType rt) {
		for(Role r:rs) {
			if(r.getRoleType()==rt) return true;
		}
		return false;
	}
	
	
	@RequestMapping("/drawCheckCode")
	public void drawCheckCode(HttpServletResponse resp,HttpSession session) throws IOException {
		   //如果开启Hard模式，可以不区分大小写  
	       // String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();  
	          
	        //获取默认难度和长度的验证码  
	        String checkcode = SecurityCode.getSecurityCode();  
	        session.setAttribute("cc", checkcode);
	        OutputStream os = resp.getOutputStream();
	        ImageIO.write( SecurityImage.createImage(checkcode), "jpg", os);
	       
	      
	       
	}
}
