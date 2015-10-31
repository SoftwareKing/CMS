package com.xujin.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.AttachmentDao;
import com.xujin.cms.dao.ChannelDao;
import com.xujin.cms.dao.TopicDao;
import com.xujin.cms.dao.UserDao;
import com.xujin.cms.model.Attachment;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.CmsException;
import com.xujin.cms.model.Topic;
import com.xujin.cms.model.User;
import com.xujin.cms.service.TopicService;

@Service("topicService")
public class TopicServiceImpl implements TopicService {
	
	@Resource(name="topicDao")
	private TopicDao topicDao;
	
	@Resource(name="attachmentDao")
	private AttachmentDao attachmentDao;
	
	@Resource(name="channelDao")
	private ChannelDao channelDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	
	
	//添加文章附件
	private void addTopicAtts(Topic topic,Integer[] aids) {
		if(aids!=null) {
			for(Integer aid:aids) {
				Attachment a = attachmentDao.load(aid);
				if(a==null) continue;
				a.setTopic(topic);//设置附件和文章的关系
			}
		}
	}

	//添加文章
	@Override
	public void add(Topic topic, int cid, int uid, Integer[] aids) {
		Channel c = channelDao.load(cid);//获取所要添加的栏目ID，装载栏目对象
		User u = userDao.load(uid);//根据uid装载用户
		if(c==null||u==null)throw new CmsException("要添加的文章必须有用户和栏目");
		topic.setAuthor(u.getNickname());//设置发布文章的人
		topic.setCname(c.getName());//a设置文章所在的栏目
		topic.setCreateDate(new Date());//设置文章创建时间
		topic.setChannel(c);//给文章设置栏目
		topic.setUser(u);//
		topicDao.add(topic);//添加文章
		addTopicAtts(topic, aids);//维护文章和附件的关系
	}

	@Override//添加没有附件的文章
	public void add(Topic topic, int cid, int uid) {
		add(topic,cid,uid,null);
	}

	//删除文章，同时删除附件
	@Override
	public void delete(int id) {
		//先根据文章获取所有的附件
		List<Attachment> atts = attachmentDao.listByTopic(id);
		
		//删除附件和ID关系
		attachmentDao.deleteByTopic(id);
		
		//删除文章
		topicDao.delete(id);
		
		//删除硬盘上面的文件
		for(Attachment a:atts) {
			AttachmentServiceImpl.deleteAttachFiles(a);
		}
	}

	@Override//更新文章同时更新附件
	public void update(Topic topic, int cid, Integer[] aids) {
		Channel c = channelDao.load(cid);
		if(c==null)throw new CmsException("要更新的文章必须有用户和栏目");
		topic.setCname(c.getName());
		topic.setChannel(c);
		topicDao.update(topic);
		addTopicAtts(topic, aids);
	}

	//只更新文章不更新附件
	@Override
	public void update(Topic topic, int cid) {
		update(topic,cid,null);
	}

	//根据文章ID导入文章
	@Override
	public Topic load(int id) {
		return topicDao.load(id);
	}

	//根据cid,title,status查询文章并分页
	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return topicDao.find(cid, title, status);
	}

	//根据uid,cid,title,status查询文章并分页
	@Override
	public Pager<Topic> find(Integer uid, Integer cid, String title,
			Integer status) {
		return topicDao.find(uid, cid, title, status);
	}

	//根据Keyword查询文章并分页
	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		return topicDao.searchTopicByKeyword(keyword);
	}

	//根据内容查询文章并分页
	@Override
	public Pager<Topic> searchTopic(String con) {
		return topicDao.searchTopic(con);
	}

	
	//查询推荐文章并分页
	@Override
	public Pager<Topic> findRecommendTopic(Integer ci) {
		return topicDao.findRecommendTopic(ci);
	}
	
	//更新文章状态
	@Override
	public void updateStatus(int tid) {
		Topic t = topicDao.load(tid);
		if(t.getStatus()==0) t.setStatus(1);
		else t.setStatus(0);
		topicDao.update(t);
	}
	@Override
	public List<Topic> listTopicByChannelAndNumber(int cid, int num) {
		return topicDao.listTopicByChannelAndNumber(cid, num);
	}
	@Override
	public List<Topic> listTopicByChannel(int cid) {
		return topicDao.listTopicsByChannel(cid);
	}
	@Override
	public boolean isUpdateIndex(int cid) {
		return topicDao.isUpdateIndex(cid);
	}
	
	@Override
	public Topic loadLastedTopicByColumn(int cid) {
		return topicDao.loadLastedTopicByColumn(cid);
	}

}
