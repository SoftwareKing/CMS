package com.xujin.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xujin.basic.model.SystemContext;
import com.xujin.cms.model.BaseInfo;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.ChannelType;
import com.xujin.cms.model.IndexTopic;
import com.xujin.cms.model.Topic;
import com.xujin.cms.service.AttachmentService;
import com.xujin.cms.service.ChannelService;
import com.xujin.cms.service.IndexPicService;
import com.xujin.cms.service.IndexService;
import com.xujin.cms.service.TopicService;
import com.xujin.cms.utils.FreemarkerUtil;
import com.xujin.cms.utils.PropertiesUtil;
import com.xujin.cms.web.BaseInfoUtil;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
	
	private String outPath;
	private FreemarkerUtil util;
	
	@Autowired(required=true)
	public IndexServiceImpl(String ftlPath, String outPath) {
		super();
		if(util==null) {
			this.outPath = outPath;
			util = FreemarkerUtil.getInstance(ftlPath);
		}
	}
    @Resource(name="channelService")
	private ChannelService channelService;
    @Resource(name="topicService")
	private TopicService topicService;
    
    @Resource(name="indexPicService")
	private IndexPicService indexPicService;
    
    @Resource(name="attachmentService")
	private AttachmentService attachmentService;
	
	
	

	@Override
	public void generateTop() {
		//System.out.println("=============重新生成了顶部信息====================");
		//List<Channel> cs = channelService.listTopNavChannel();
		List<Channel> cs = channelService.listTopNavChannel2();
		/*for (Channel channel : cs) {
			System.out.println(channel.getChildren().size());
		}*/
		//List<Channel> lch = ((Channel) cs).getChildren();
		 Map<String,Object> root = new HashMap<String,Object>();
		root.put("menus", cs);
		//root.put("lchs", lch);
		//需要baseinfo处理一下这个
		root.put("baseInfo", BaseInfoUtil.getInstacne().read());
		String outfile = SystemContext.getRealPath()+outPath+"/top.jsp";
		util.fprint(root, "/top.ftl", outfile);
	}
	//3、首页宣传图片
	@Override
	public void generatebanner() {
		String outfile = SystemContext.getRealPath()+outPath+"/banner.jsp";
		BaseInfo bi = BaseInfoUtil.getInstacne().read();
		int picnum = bi.getIndexPicNumber();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("pics", indexPicService.listIndexPicByNum(picnum));
		util.fprint(root, "/banner.ftl", outfile);
	}
	//3、首页新闻图片
	@Override
	public void generateNewpic() {
		//System.out.println("=========重新生成首页--新闻图片的信息==============");
		String outfile = SystemContext.getRealPath()+outPath+"/newpic.jsp";
		BaseInfo bi = BaseInfoUtil.getInstacne().read();
		int picnum = bi.getIndexPicNumber();
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("newpics", attachmentService.listIndexNewPicByNum(picnum));
		util.fprint(root, "/newpic.ftl", outfile);
		
	}

	@Override
	public void generateBody() {
		//System.out.println("=========重新生成首页--文章的的内容信息==============");
		//1、获取所有的首页栏目
		List<Channel> cs = channelService.listAllIndexChannel(ChannelType.TOPIC_LIST);
		//2、根据首页栏目创建相应的IndexTopic对象
		//加载indexChannel.properties
		Properties prop = PropertiesUtil.getInstance().load("indexChannel");
		Map<String,IndexTopic> topics = new HashMap<String, IndexTopic>();
		for(Channel c:cs) {
			int cid = c.getId();
			//System.out.println(cid);
			String[] xs = prop.getProperty(cid+"").split("_");
			String order = xs[0];
			int num = Integer.parseInt(xs[1]);
			IndexTopic it = new IndexTopic();
			it.setCid(cid);
			it.setCname(c.getName());
			List<Topic> tops = topicService.listTopicByChannelAndNumber(cid, num);
//			System.out.println(cid+"--"+tops);
			it.setTopics(tops);
			topics.put(order, it);
		}
		String outfile = SystemContext.getRealPath()+outPath+"/body.jsp";
		//3、更新首页图片
		Map<String,Object> root = new HashMap<String,Object>();
		//root.put("newpics", attachmentService.listIndexNewPicByNum(picnum));
		root.put("ts", topics);
		/*root.put("keywords", keywordService.getMaxTimesKeyword(12));
		root.put("xxgk", topicService.loadLastedTopicByColumn(7));*/
		util.fprint(root, "/body.ftl", outfile);
	}


	@Override
	public void generateBottom() {
		//System.out.println("=============重新生成了底部信息====================");
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("baseInfo", BaseInfoUtil.getInstacne().read());
		String outfile = SystemContext.getRealPath()+outPath+"/foot.jsp";
		util.fprint(root, "/foot.ftl", outfile);
	}

	

}
