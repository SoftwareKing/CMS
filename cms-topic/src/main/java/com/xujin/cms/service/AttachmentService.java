package com.xujin.cms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Attachment;



public interface AttachmentService {
	public void add(Attachment a,InputStream is)throws IOException;
	public void delete(int id);
	public Attachment load(int id);
	/**
	 * 获取没有被引用的附件
	 * @return
	 */
	public Pager<Attachment> findNoUseAttachment();
	/**
	 * 清空没有被引用的附件
	 */
	public void clearNoUseAttachment();
	/**
	 * 获取某个文章的附件
	 * @param tid
	 * @return
	 */
	public List<Attachment> listByTopic(int tid);
	/**
	 * 根据一个数量获取首页图片的附件信息
	 * @param num
	 * @return
	 */
	public List<Attachment> listIndexPic(int num);
	/**
	 * 获取某个栏目中的附件图片信息
	 * @param cid
	 * @return
	 */
	public Pager<Attachment> findChannelPic(int cid);
	/**
	 * 获取某篇文章的属于附件类型的附件对象
	 * @param tid
	 * @return
	 */
	public List<Attachment> listAttachByTopic(int tid);
	
	//按数量获取首页中的新闻图片
	public List<Attachment> listIndexNewPicByNum(int num);
	
	public void updateIndexPic(int aid);
	
	public void updateAttachInfo(int aid);
	
	//获取附件中的首页新闻图片
	public Pager<Attachment> listAllPic();
	
	public long findNoUseAttachmentNum();
	
}
