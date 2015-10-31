package com.xujin.cms.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xujin.basic.model.SystemContext;
import com.xujin.cms.auth.AuthClass;
import com.xujin.cms.auth.AuthMethod;
import com.xujin.cms.dto.AjaxObj;
import com.xujin.cms.dto.TopicDto;
import com.xujin.cms.model.Attachment;
import com.xujin.cms.model.ChannelTree;
import com.xujin.cms.model.Topic;
import com.xujin.cms.model.User;
import com.xujin.cms.service.AttachmentService;
import com.xujin.cms.service.ChannelService;
import com.xujin.cms.service.KeywordService;
import com.xujin.cms.service.TopicService;
import com.xujin.cms.utils.JsonUtil;

@Controller
@AuthClass("login")
@RequestMapping("/admin/topic")
public class TopicController {
	@Resource(name="topicService")
	private TopicService topicService;
	
	@Resource(name="channelService")
	private ChannelService channelService;
	
	@Resource(name="keywordService")
	private KeywordService keywordService;
	
	@Resource(name="attachmentService")
	private AttachmentService attachmentService;
	
	//@Resource(name="indexService")
	//private IndexService indexService;
	
	//定义图片类型
	private final static List<String> imgTypes = Arrays.asList("jpg","jpeg","gif","png");
	
	
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		SystemContext.setSort("t.publishDate");
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) {
			model.addAttribute("datas",topicService.find(cid, con, status));
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", topicService.find(loginUser.getId(),cid, con, status));
		}
		if(con==null) con="";
		SystemContext.removeOrder();//移除顺序
		SystemContext.removeSort();//移除排序
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("cs",channelService.listPublishChannel());
	}
    
	//已发布审核过的文章
	@RequestMapping("/audits")
	@AuthMethod(role="ROLE_PUBLISH,ROLE_AUDIT")
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,1);
		return "topic/list";
	}
	
	//已发布未审核过的文章
	@RequestMapping("/unaudits")
	@AuthMethod(role="ROLE_PUBLISH,ROLE_AUDIT")
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,0);
		return "topic/list";
	}
	
	//审核文章
	@RequestMapping("/changeStatus/{id}")
	@AuthMethod(role="ROLE_AUDIT")
	public String changeStatus(@PathVariable int id,Integer status) {
		topicService.updateStatus(id);
		Topic t = topicService.load(id);
		/*if(topicService.isUpdateIndex(t.getChannel().getId())) {
			indexService.generateBody();
		}*/
		if(status==0) {
			return "redirect:/admin/topic/unaudits";
		} else {
			return "redirect:/admin/topic/audits";
		}
	}
	
	
	@RequestMapping("/delete/{id}")
	@AuthMethod(role="ROLE_PUBLISH")
	public String delete(@PathVariable int id,Integer status) {
		Topic t = topicService.load(id);
		topicService.delete(id);
		/*if(topicService.isUpdateIndex(t.getChannel().getId())) {
			indexService.generateBody();
		}*/
		if(status==0) {
			return "redirect:/admin/topic/unaudits";
		} else {
			return "redirect:/admin/topic/audits";
		}
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_PUBLISH")
	public String add(Model model) {
		Topic t = new Topic();
		t.setPublishDate(new Date());
		TopicDto td = new TopicDto(t);
		model.addAttribute("topicDto",td);
		return "topic/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/add";
		}
		Topic t = topicDto.getTopic();
		User loginUser = (User)session.getAttribute("loginUser");
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		t.setKeyword(keys.toString());
		topicService.add(t, topicDto.getCid(), loginUser.getId(),aids);
		/*if(topicDto.getStatus()==1&&topicService.isUpdateIndex(topicDto.getCid())) {
			indexService.generateBody();
		}*/
		return "redirect:/jsp/common/addSuc.jsp";
	}
	
	//updateUI界面，准备数据
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_PUBLISH")
	public String update(@PathVariable int id,Model model) {
		Topic t = topicService.load(id);
		String keyword = t.getKeyword();
		if(keyword!=null&&!"".equals(keyword.trim()))
			model.addAttribute("keywords",keyword.split("\\|"));
		model.addAttribute("atts",attachmentService.listByTopic(id));
		TopicDto td = new TopicDto(t,t.getChannel().getId());
		model.addAttribute("topicDto",td);
		model.addAttribute("cname",t.getChannel().getName());
		return "topic/update";
	}
	
	//update操作完入库
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/update";
		}
		Topic tt = topicService.load(id);
		Topic t = topicDto.getTopic();
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		tt.setKeyword(keys.toString());
		tt.setChannelPicId(t.getChannelPicId());
		tt.setContent(t.getContent());
		tt.setPublishDate(t.getPublishDate());
		tt.setRecommend(t.getRecommend());
		tt.setStatus(t.getStatus());
		tt.setSummary(t.getSummary());
		tt.setTitle(t.getTitle());
		topicService.update(tt, topicDto.getCid(),aids);
		/*if(topicService.isUpdateIndex(topicDto.getCid())) {
			indexService.generateBody();
		}*/
		return "redirect:/jsp/common/addSuc.jsp";
	}
	
	//显示文章，显示已发布文章的所有信息
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(topicService.load(id));
		model.addAttribute("atts",attachmentService.listByTopic(id));
		return "topic/show";
	}
	
	@RequestMapping(value="/searchKeyword")
	@AuthMethod(role="ROLE_PUBLISH")
	public @ResponseBody List<String> searchKeyword(String term) {
		return keywordService.listKeywordStringByCon(term);
	}
	
	@AuthMethod(role="ROLE_PUBLISH")
	@RequestMapping(value="/upload",method=RequestMethod.POST)//返回的是json类型的值，而uploadify只能接受字符串
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=utf-8");
		AjaxObj ao = null;
		try {
			//新建附件对象
			Attachment att = new Attachment();
			//获取文件扩展名
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());
			System.out.println(ext);
			att.setIsAttach(0);
			//判断是否是图片类型，是图片类型设置为1，不是设置为0
			if(imgTypes.contains(ext)) att.setIsImg(1);
			else att.setIsImg(0);
			//是否是主页图片默认为0，不是
			att.setIsIndexPic(0);
			//根据当前日期命名为新文件名
			att.setNewName(String.valueOf(new Date().getTime())+"."+ext);
			att.setOldName(FilenameUtils.getBaseName(attach.getOriginalFilename()));
			att.setSuffix(ext);
			att.setType(attach.getContentType());
			//由于现在没有文章所以，设置文章为空
			att.setTopic(null);
			//设置附件的大小
			att.setSize(attach.getSize());
			//把处理好的图片传到业务逻辑层处理
			attachmentService.add(att, attach.getInputStream());
			ao = new AjaxObj(1,null,att);
		} catch (IOException e) {
			ao = new AjaxObj(0,e.getMessage());
		}
		resp.getWriter().write(JsonUtil.getInstance().obj2json(ao));
	}
	
	@RequestMapping("/treeAll")
	@AuthMethod(role="ROLE_PUBLISH")
	public @ResponseBody List<ChannelTree> tree() {
		return channelService.generateTree();
	}
}
