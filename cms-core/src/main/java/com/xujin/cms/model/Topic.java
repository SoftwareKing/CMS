package com.xujin.cms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Description:文章实体
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2014-1-2(创建日期)
 */
@Entity
@Table(name="t_topic")
public class Topic {
	//文章ID
	private int id;
	
	//文章标题
	private String title;
	/**
	 * 关键字:通过|来分割不同的关键字
	 */
	private String keyword;
	/**
	 * 文章的状态，默认为0表示未发表，1表示已发布
	 */
	private int status;
	/**
	 * 是否是推荐文章,0表示不推荐，1表示推荐
	 */
	private int recommend;
	
	/**
	 * 文章的内容
	 */
	private  String content;//String字符串有数据大小限制，所以需要改数据库中的数据类型为Text
	
	/**
	 * 文章的摘要
	 */
	private String summary;//需要修改数据库中对应表的数据类型，为text
	
	/**
	 * 栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片
	 */
	private int channelPicId;
	/**
	 * 文章的发布时间，用来进行排序的
	 */
	private Date publishDate;
	/**
	 * 文章的创建时间
	 */
	private Date createDate;
	/**
	 * 文章的作者名称，用来显示用户的昵称，冗余字段
	 */
	private String author;
	/**
	 * 栏目名称冗余字段
	 */
	private String cname;
	
	/**
	 * 文章所在的频道，多对一
	 */
	private Channel channel;
	/**
	 * 文章的发布者
	 */
	private User user;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Column(name="publish_date")
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	@ManyToOne
	@JoinColumn(name="cid")//文章和栏目关系
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	@ManyToOne
	@JoinColumn(name="uid")//文章和用户的关系一对多
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="channel_pic_id")
	public int getChannelPicId() {
		return channelPicId;
	}
	public void setChannelPicId(int channelPicId) {
		this.channelPicId = channelPicId;
	}
	
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", title=" + title + "]";
	}
	
	public Topic(int id, String title,String keyword, int status,
			int recommend, Date publishDate, String author, String cname) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.status = status;
		this.recommend = recommend;
		this.publishDate = publishDate;
		this.author = author;
		this.cname = cname;
	}
	
	public Topic(int id, String title,String keyword, int status,
			int recommend, Date publishDate, String author, String cname,String summary) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.status = status;
		this.recommend = recommend;
		this.publishDate = publishDate;
		this.author = author;
		this.cname = cname;
		this.summary = summary;
	}
	
	public Topic(int id, String title, String keyword, int status,
			int recommend, String author) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.status = status;
		this.recommend = recommend;
		this.author = author;
	}
	
	public Topic() {
	}
	
}
