package com.xujin.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujin.cms.dao.ChannelDao;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.ChannelTree;
import com.xujin.cms.model.ChannelType;
import com.xujin.cms.model.CmsException;
import com.xujin.cms.model.Topic;
import com.xujin.cms.service.ChannelService;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {
	
     @Resource(name="channelDao")
	 private ChannelDao channelDao;
	
    /* @Resource(name="topicService")
	 private TopicService topicService;*/
     //添加栏目
	public void add(Channel channel, Integer pid) {
		Integer orders = channelDao.getMaxOrderByParent(pid);
		if(pid!=null&&pid>0) {
			Channel pc = channelDao.load(pid);
			if(pc==null) throw new CmsException("要添加栏目的父类对象不正确!");
			channel.setParent(pc);
		}
		channel.setOrders(orders+1);
		channelDao.add(channel);
	}

	//更新栏目
	public void update(Channel channel) {
		channelDao.update(channel);
	}

	//删除栏目
	public void delete(int id) {
		//1、需要判断是否存在子栏目
		List<Channel> cs = channelDao.listByParent(id);
		if(cs!=null&&cs.size()>0) throw new CmsException("要删除的栏目还有子栏目，无法删除");
		/*//2、需要判断是否存在文章
		List<Topic> ts = topicService.listTopicByChannel(id);
		if(ts.size()>0) {
			throw new CmsException("该栏目还有相应的文章信息，不能删除");
		}
		//3、需要删除和组的关联关系
*/		channelDao.deleteChannelGroups(id);
		channelDao.delete(id);
	}

	public void clearTopic(int id) {
		/*List<Topic> tops = topicService.listTopicByChannel(id);
		for(Topic t:tops) {
			topicService.delete(t.getId());
		}*/
	}

	public Channel load(int id) {
		return channelDao.load(id);
	}

	public List<Channel> listByParent(Integer pid) {
		return channelDao.listByParent(pid);
	}
	@Override
	public List<ChannelTree> generateTree() {
		return channelDao.generateTree();
	}
	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}

	//用于拖动排序处理
    @Override
	public void updateSort(Integer[] ids) {
		channelDao.updateSort(ids);
	}
	@Override
	public List<Channel> listPublishChannel() {
		return channelDao.listPublishChannel();
	}
	@Override
	public List<Channel> listTopNavChannel() {
		return channelDao.listTopNavChannel();
	}
	@Override
	public List<Channel> listTopNavChannel2() {
		return channelDao.listTopNavChannel2();
	}
	@Override
	public List<Channel> listAllIndexChannel(ChannelType ct) {
		return channelDao.listAllIndexChannel(ct);
	}

	@Override
	public Channel loadFirstChannelByNav(int cid) {
		return channelDao.loadFirstChannelByNav(cid);
	}

	@Override
	public List<Channel> listUseChannelByParent(Integer cid) {
		return channelDao.listUseChannelByParent(cid);
	}

	@Override
	public List<Channel> listChannelByType(ChannelType ct) {
		return channelDao.listChannelByType(ct);
	}
	

}
