package com.xujin.cms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xujin.basic.model.Pager;
import com.xujin.basic.model.SystemContext;
import com.xujin.cms.model.Attachment;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.ChannelType;
import com.xujin.cms.model.Department;
import com.xujin.cms.model.People;
import com.xujin.cms.model.Topic;
import com.xujin.cms.service.AttachmentService;
import com.xujin.cms.service.ChannelService;
import com.xujin.cms.service.DepartmentService;
import com.xujin.cms.service.KeywordService;
import com.xujin.cms.service.PeopleService;
import com.xujin.cms.service.TopicService;
import com.xujin.cms.web.BaseInfoUtil;

@Controller
public class IndexController {
	
	@Resource(name="channelService")
	private ChannelService channelService;
	
	@Resource(name="topicService")
	private TopicService topicService;
	
	@Resource(name="attachmentService")
	private AttachmentService attachmentService;
	
	@Resource(name="keywordService")
	private KeywordService keywordService;
	
	@Resource(name="peopleService")
	private PeopleService peopleService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	

	

	@RequestMapping({"/","/index"})
	public String index(Model model) {
		model.addAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
		return "index/index";
	}
	@RequestMapping("/test")
	public String index2(Model model) {
		model.addAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
		return "back/admin/index";
	}
	@RequestMapping("/channel/{cid}")
	public String showChannel(@PathVariable int cid,Model model,HttpServletResponse resp,HttpServletRequest req) throws IOException {
		Channel c = channelService.load(cid);
		//System.out.println(c.getType());
		Channel pc = null;
		if(c.getType()==ChannelType.NAV_CHANNEL) {
			pc = c;
			//如果是导航栏目，需要获取该栏目中的第一个栏目
			c = channelService.loadFirstChannelByNav(c.getId());
			if((c.getType()==ChannelType.TOPIC_IMG)&&(c.getType()!=ChannelType.TOPIC_LIST)){
				//System.out.println(c.getType());
				SystemContext.setPageSize(16);
				SystemContext.setSort("a.topic.publishDate");
				SystemContext.setOrder("desc");
				Pager<Attachment> atts = attachmentService.findChannelPic(cid);
				model.addAttribute("datas", atts);
				SystemContext.removeSort();
				SystemContext.removeOrder();
				model.addAttribute("pc", pc);
				model.addAttribute("cs", channelService.listUseChannelByParent(pc.getId()));
				model.addAttribute("channel", c);
				return "index/channel_pic";
				
			}
			
		} else {
			pc = c.getParent();
		}
//		System.out.println(c.getType()==ChannelType.TOPIC_LIST);
//		System.out.println(c.getType());
		//如果是文章栏目或者是文章内容栏目直接显示文章
		if(c.getType()==ChannelType.TOPIC_CONTENT) {
			resp.sendRedirect(req.getContextPath()+"/topic/"+topicService.loadLastedTopicByColumn(cid).getId());
		} //如果是图片栏目，要获取这个图片中的
		else if(c.getType()==ChannelType.People_CONTENT)
		{
			resp.sendRedirect(req.getContextPath()+"/people/"+topicService.loadLastedTopicByColumn(cid).getId());
		}
		else if(c.getType()==ChannelType.TOPIC_IMG){
			SystemContext.setPageSize(16);
			SystemContext.setSort("a.topic.publishDate");
			SystemContext.setOrder("desc");
			Pager<Attachment> atts = attachmentService.findChannelPic(cid);
			model.addAttribute("datas", atts);
		} else if(c.getType()==ChannelType.TOPIC_LIST) {
			SystemContext.setSort("t.publishDate");
			SystemContext.setOrder("desc");
			//System.out.println(c.getType());
			model.addAttribute("datas", topicService.find(c.getId(),null,1));
		}
		SystemContext.removeSort();
		SystemContext.removeOrder();
		model.addAttribute("pc", pc);
		model.addAttribute("cs", channelService.listUseChannelByParent(pc.getId()));
		model.addAttribute("channel", c);
		if(c.getType()==ChannelType.TOPIC_LIST) {
			return "index/channel";
		} else {
			return "index/channel_pic";
		}
	}
	
	@RequestMapping("/people/{tid}")
	public String showTopicPeople(@PathVariable int tid,Model model) {
		Topic t = topicService.load(tid);
		model.addAttribute("topic", t);
		return "people/teacher";
	}
	@RequestMapping("/topic/{tid}")
	public String showTopic(@PathVariable int tid,Model model) {
		Topic t = topicService.load(tid);
		String keywords = t.getKeyword();
		model.addAttribute("topic", t);
		if(keywords==null||"".equals(keywords.trim())||"\\|".equals(keywords.trim())) {
			model.addAttribute("hasKey", false);
		} else {
			String[] kws = keywords.split("\\|");
			model.addAttribute("hasKey", true);
			model.addAttribute("kws",kws);
		}
		List<Attachment> atts = attachmentService.listAttachByTopic(tid);
		if(atts.size()>0) {
			model.addAttribute("hasAtts", true);
			model.addAttribute("atts", atts);
		} else {
			model.addAttribute("hasAtts",false);
		}
		return "index/topic";
	}
	
	//根据内容检索
	@RequestMapping("/search/{con}")
	public String search(@PathVariable String con,Model model) {
		SystemContext.setOrder("asc");
		SystemContext.setSort("c.orders");
		model.addAttribute("cs", channelService.listChannelByType(ChannelType.NAV_CHANNEL));
		SystemContext.setOrder("desc");
		SystemContext.setSort("t.publishDate");
		/************在搜索出来的内容上高亮显示检索内容****************************/
		Pager<Topic> topics = topicService.searchTopic(con);
		emp(topics,con);
		/****************************************/
		model.addAttribute("datas", topics);
		model.addAttribute("con", con);
		return "index/searchresult";
	}
	
	//根据关键字检索
	@RequestMapping("/keyword/{con}")
	public String keyword(@PathVariable String con,Model model) {
		//根据关键字的最大引用率检索
		model.addAttribute("kws", keywordService.getMaxTimesKeyword(9));
		SystemContext.setOrder("desc");
		SystemContext.setSort("t.publishDate");
		Pager<Topic> topics = topicService.searchTopicByKeyword(con);
		emp(topics,con);
		model.addAttribute("datas", topics);
		model.addAttribute("con", con);
		return "index/keyword";
	}

	//由于强调高亮显示的文章
	private void emp(Pager<Topic> topics, String con) {
		for(Topic t:topics.getDatas()) {
			if(t.getTitle().contains(con)) {
				String tt = t.getTitle().replaceAll(con, "<span class='emp'>"+con+"</span>");
				t.setTitle(tt);
			}
		}
	}
	
	//根据关键字检索
	@RequestMapping("/teacher/{id}")
	public String Peoplelist(@PathVariable int id,Model model) {
	     Map<Integer, List<People>> dplist = new HashMap<Integer, List<People>>();
		Pager<Department> alld=this.departmentService.findDepartment();
		List<Department> alld1=alld.getDatas();
		for (Department dep : alld1) {
				int did=dep.getId();
				dplist.put(dep.getId(),this.peopleService.listAllDepartmentPeoplesByzhichen(did, id));
	    }
		 model.addAttribute("alld", alld1);
		 model.addAttribute("dplist", dplist);
		return "people/teacher";
	}
	
	//
	@RequestMapping("/teachershow/{id}")
	public String PeopleShow(@PathVariable int id,Model model) {
		model.addAttribute(peopleService.load(id));
		model.addAttribute("pd",peopleService.listPeopleDepartments(id));
		return "people/teachershow";
		
	}
}
