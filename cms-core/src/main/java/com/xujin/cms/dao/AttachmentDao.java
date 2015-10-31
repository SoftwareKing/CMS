package com.xujin.cms.dao;

import java.util.List;

import com.xujin.basic.dao.BaseDao;
import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Attachment;

/**
 * @Description:附件DAO接口
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2014-1-2(创建日期)
 */
public interface AttachmentDao extends BaseDao<Attachment> {

    /**
     * 获取没有被引用的附件
     * 
     * @return
     */
    public Pager<Attachment> findNoUseAttachment();

    public long findNoUseAttachmentNum();

    /**
     * 清空没有被引用的附件
     */
    public void clearNoUseAttachment();

    /**
     * 删除某个文章的所有附件
     * 
     * @param tid
     */
    public void deleteByTopic(int tid);

    /**
     * 获取某个文章的附件
     * 
     * @param tid
     * @return
     */
    public List<Attachment> listByTopic(int tid);

    /**
     * 根据一个数量获取首页图片的附件信息
     * 
     * @param num
     * @return
     */
    public List<Attachment> listIndexPic(int num);

    /**
     * 获取某个栏目中的附件图片信息
     * 
     * @param cid
     * @return
     */
    public Pager<Attachment> findChannelPic(int cid);

    /**
     * 获取所有的新闻图片信息并分页处理
     * 
     * @return
     */
    public Pager<Attachment> listAllIndexPic();

    /**
     * 根据一定数量获取的新闻图片信息
     * 
     * @return
     */
    public List<Attachment> listIndexNewPicByNum(int num);

    /**
     * 获取某篇文章的属于附件类型的附件对象
     * 
     * @param tid
     * @return
     */
    public List<Attachment> listAttachByTopic(int tid);

}
