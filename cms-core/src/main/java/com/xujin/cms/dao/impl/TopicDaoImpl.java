package com.xujin.cms.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.xujin.basic.dao.impl.BaseDaoImpl;
import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.TopicDao;
import com.xujin.cms.model.Topic;

@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {
	

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return find(null,cid,title,status);
	}
	
	//定义方法，通过构造函数只取文章中需要的属性
	private String getTopicSelect() {
		return "select new Topic(t.id,t.title,t.keyword,t.status,t.recommend,t.publishDate,t.author,t.cname)";
	}

	@Override
	public Pager<Topic> find(Integer uid, Integer cid, String title,
			Integer status) {
		String hql = getTopicSelect()+" from Topic t where 1=1";
		if(status!=null) {
			hql+=" and t.status="+status;
		}
		if(title!=null&&!title.equals("")) {
			hql+=" and t.title like '%"+title+"%'";
		}
 		if(uid!=null&&uid>0) {
			hql+=" and t.user.id="+uid;
		}
		if(cid!=null&&cid>0) {
			hql+=" and t.channel.id="+cid;
		}
		return this.find(hql);
	}

	//根据关键字查询文章分页，并得到需要的属性 t.status=1已发布的文章
	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		String hql = getTopicSelect()+" from Topic t where t.status=1 and t.keyword like '%"+keyword+"%'";
		return this.find(hql);
	}

	@Override//通过内容查询发布的文章
	public Pager<Topic> searchTopic(String con) {
		String hql = getTopicSelect()+" from Topic t where t.status=1 and " +
				"(title like '%"+con+"%' or content like '%"+con+"%' or summary like '%"+con+"%')";
		return this.find(hql);
	}

	@Override//查找推荐的文章
	public Pager<Topic> findRecommendTopic(Integer cid) {
		String hql = getTopicSelect()+" from Topic t where t.status=1 and t.recommend=1";
		if(cid==null||cid==0) {//栏目为空就直接查询
			return this.find(hql);
		} else {
			hql +=" and t.channel.id=?";//栏目不为空，则查询根据栏目查询文章
			return this.find(hql, cid);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> listTopicByChannelAndNumber(int cid, int num) {
		String hql = getTopicSelect()+" from Topic t where t.status=1 and t.channel.id=? order by t.publishDate desc";
		return this.getSession().createQuery(hql).setParameter(0, cid)
				.setFirstResult(0).setMaxResults(num).list();
		
	}

	//通过栏目获取文章列表
	@Override
	public List<Topic> listTopicsByChannel(int cid) {
		String hql = "select t from Topic t where t.channel.id=?";
		return this.list(hql,cid);
	}

	@Override
	public boolean isUpdateIndex(int cid) {
		String hql = "select count(*) from Channel c where c.isIndex=1 and c.status=0 and c.id=?";
		Long count = (Long)this.getSession().createQuery(hql).setParameter(0, cid).uniqueResult();
		if(count==null||count<=0) return false;
		return true;
	}

	@Override
	public Topic loadLastedTopicByColumn(int cid) {
		String s = "select new Topic(t.id,t.title,t.keyword,t.status,t.recommend,t.publishDate,t.author,t.cname,t.summary)";
		String hql = s+" from Topic t where t.status=1 and t.channel.id=? order by t.publishDate desc";
		Topic topic = (Topic)this.getSession()
				.createQuery(hql).setFirstResult(0).setMaxResults(1)
				.setParameter(0, cid).uniqueResult();
		return topic;
	}

}
