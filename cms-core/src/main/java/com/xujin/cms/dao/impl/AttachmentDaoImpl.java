package com.xujin.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xujin.basic.dao.impl.BaseDaoImpl;
import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.AttachmentDao;
import com.xujin.cms.model.Attachment;

/**
 * @Description:附件DAO接口实现
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2014-1-2(创建日期)
 */
@Repository("attachmentDao")
public class AttachmentDaoImpl extends BaseDaoImpl<Attachment> implements
		AttachmentDao {
	
	private String getAttachmentSelect() {
		//int id, String newName, String oldName, String type,
//		String suffix, long size, int isIndexPic, int isImg, int isAttach,int tid
		return "select new Attachment(a.id,a.newName,a.oldName,a.type," +
				"a.suffix,a.size,a.isIndexPic,a.isImg,a.isAttach,a.topic.id,a.topic.title,a.topic.publishDate,a.topic.author)";
	}


	@Override
	public Pager<Attachment> findNoUseAttachment() {
		String hql = "select a from Attachment a where a.topic is null";
		return this.find(hql);
	}

	@Override
	public void clearNoUseAttachment() {
		String hql = "delete Attachment a where a.topic is null";
		this.UpdateByHql(hql);
	}

	@Override
	public void deleteByTopic(int tid) {
		String hql = "delete Attachment a where a.topic.id=?";
		this.UpdateByHql(hql, tid);
	}

	@Override
	public List<Attachment> listByTopic(int tid) {
		String hql = getAttachmentSelect()+" from Attachment a where a.topic.id=?";
		return this.list(hql,tid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> listIndexPic(int num) {
		String hql = getAttachmentSelect()+" from Attachment a where a.isIndexPic=? and a.topic.status=1 and a.isImg=1";
		return this.getSession().createQuery(hql).setParameter(0,1)
				.setFirstResult(0).setMaxResults(num).list();
	}

	@Override
	public Pager<Attachment> findChannelPic(int cid) {
		String hql = getAttachmentSelect()+" from Attachment a where a.topic.status=1 and" +
				" a.topic.channel.id=? and a.id=a.topic.channelPicId";
		return this.find(hql, cid);
	}

	@Override
	public List<Attachment> listAttachByTopic(int tid) {
		return this.list(getAttachmentSelect()+" from Attachment a where a.topic.id=? " +
				"and a.isAttach=1",tid);
	}


	@Override
	public Pager<Attachment> listAllIndexPic() {
		String hql = getAttachmentSelect()+" from Attachment a where a.isImg=? and a.topic.status=1";
		return this.find(hql,1);
	}


	@Override
	public long findNoUseAttachmentNum() {
		String hql = "select count(*) from Attachment a where a.topic is null";
		return (Long)this.getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> listIndexNewPicByNum(int num) {
		String hql = getAttachmentSelect()+" from Attachment a where a.isImg=1 and a.isIndexPic=1 and a.topic.status=1";
		return this.getSession().createQuery(hql).setFirstResult(0).setMaxResults(num).list();
	}

}
