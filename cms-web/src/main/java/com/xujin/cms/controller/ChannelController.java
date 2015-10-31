package com.xujin.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xujin.cms.auth.AuthMethod;
import com.xujin.cms.dto.AjaxObj;
import com.xujin.cms.dto.TreeDto;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.ChannelTree;
import com.xujin.cms.model.ChannelType;
import com.xujin.cms.service.ChannelService;
import com.xujin.cms.service.IndexService;
import com.xujin.cms.utils.EnumUtils;
import com.xujin.cms.utils.JsonUtil;

@RequestMapping("/admin/channel")
@Controller("channelController")
@AuthMethod
public class ChannelController {
	
	@Resource(name="channelService")
	private ChannelService channelService;
	
	@Resource(name="indexService")
	private IndexService indexService;

	@RequestMapping("/channels")
	public String list(Model model) {
	model.addAttribute("treeDatas", JsonUtil.getInstance().obj2json(channelService.generateTree()));
		return "channel/list";
	}
	
	@RequestMapping("/channels/{pid}")
	public String listChild(@PathVariable Integer pid,@Param Integer refresh,Model model) {
		Channel pc = null;
		if(refresh==null) {
			model.addAttribute("refresh",0);
		} else {
			model.addAttribute("refresh",1);
		}
		if(pid==null||pid<=0) {
			pc = new Channel();
			pc.setName(Channel.ROOT_NAME);
			pc.setId(Channel.ROOT_ID);
		} else
			pc = channelService.load(pid);
		model.addAttribute("pc", pc);
		model.addAttribute("channels",channelService.listByParent(pid));
		return "channel/list_child";
	}
	
	//异步一次加载栏目树
	@RequestMapping("/treeAll")
	public @ResponseBody List<ChannelTree> tree() {
		return channelService.generateTree();
	}
	
	
	//异步点击事件加载树
	@RequestMapping(value="/treeAs",method=RequestMethod.POST)
	public @ResponseBody List<TreeDto> tree(@Param Integer pid) {
		List<TreeDto> tds = new ArrayList<TreeDto>();
		if(pid==null||pid<=0) {
			tds.add(new TreeDto(0,"网站根栏目",1));
			return tds;
		}
		List<ChannelTree> cts = channelService.generateTreeByParent(pid);
		for(ChannelTree ct:cts) {
			tds.add(new TreeDto(ct.getId(),ct.getName(),1));
		}
		return tds;
	}
	
	private void initAdd(Model model,Integer pid) {
		if(pid==null) pid = 0;
		Channel pc = null;
		if(pid==0) {
			pc = new Channel();
			pc.setId(Channel.ROOT_ID);
			pc.setName(Channel.ROOT_NAME);
		} else {
			pc = channelService.load(pid);
		}
		model.addAttribute("pc", pc);
		model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));
	}
	
	@RequestMapping(value="/add/{pid}",method=RequestMethod.GET)
	public String add(@PathVariable Integer pid,Model model) {
		initAdd(model, pid);
		model.addAttribute(new Channel());
		return "channel/add";
	}
	
	
	@RequestMapping(value="/add/{pid}",method=RequestMethod.POST)
	public String add(@PathVariable Integer pid,Channel channel,BindingResult br,Model model) {
		if(br.hasErrors()) {
			initAdd(model, pid);
			return "channel/add";
		}
		channelService.add(channel, pid);
		indexService.generateTop();
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	@RequestMapping("/delete/{pid}/{id}")
	public String delete(@PathVariable Integer pid,@PathVariable Integer id,Model model) {
		channelService.delete(id);
		indexService.generateTop();
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model) {
		Channel c = channelService.load(id);
		model.addAttribute("channel", c);
		Channel pc = null;
		if(c.getParent()==null) {
			pc = new Channel();
			pc.setId(Channel.ROOT_ID);
			pc.setName(Channel.ROOT_NAME);
		} else {
			pc = c.getParent();
		}
		model.addAttribute("pc",pc);
		model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));
		return "channel/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,Channel channel,BindingResult br,Model model) {
		if(br.hasErrors()) {
			model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));	
			return "channel/update";
		}
		Channel tc = channelService.load(id);
		int pid = 0;
		if(tc.getParent()!=null) pid = tc.getParent().getId();
		tc.setCustomLink(channel.getCustomLink());
		tc.setCustomLinkUrl(channel.getCustomLinkUrl());
		tc.setIsIndex(channel.getIsIndex());
		tc.setIsTopNav(channel.getIsTopNav());
		tc.setName(channel.getName());
		tc.setRecommend(channel.getRecommend());
		tc.setStatus(channel.getStatus());
		tc.setType(channel.getType());
		tc.setNavOrder(channel.getNavOrder());
		channelService.update(tc);
		indexService.generateTop();
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	/*
	 * 更新排序之后怎么判断它更新成功？通过@ResponseBody返回一个特定的Josn对象，
	 * 来判断是否更新完成
	 * */
	@RequestMapping("/channels/updateSort")
	public @ResponseBody AjaxObj updateSort(@Param Integer[] ids) {
		try {
			channelService.updateSort(ids);
			indexService.generateTop();
		} catch (Exception e) {
			//处理失败，返回0，提示处理失败
			return new AjaxObj(0,e.getMessage());
		}
		//处理成功，返回1，提示处理成功
		return new AjaxObj(1);
	}
}
