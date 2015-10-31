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
 * @Description:附件实体，文章中使用的附件
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2014-1-2(创建日期)
 */
@Entity
@Table(name="t_attachment")
public class Attachment {
	private int id;
	/**
	 * 附件上传之后的名称
	 */
	private String newName;
	/**
	 * 附件的原始名称
	 */
	private String oldName;
	/**
	 * 附件的类型，这个类型和contentType类型一致
	 */
	private String type;
	/**
	 * 附件的后缀名
	 */
	private String suffix;
	/**
	 * 附件的大小
	 */
	private long size;
	/**
	 * 该附件是否是主页图片
	 */
	private int isIndexPic;
	/**
	 * 该附件是否是图片类型,0表示不是，1表示是
	 */
	private int isImg;
	/**
	 * 附件所属文章
	 */
	private Topic topic;
	
	/**
	 * 附件所属People
	 */
	private People people;
	
	/**
	 * 是否是附件信息，0表示不是，1表示是，如果是附件信息就在文章的附件栏进行显示
	 */
	private int isAttach;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="new_name")
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	@Column(name="old_name")
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	@Column(name="is_index_pic")
	public int getIsIndexPic() {
		return isIndexPic;
	}
	public void setIsIndexPic(int isIndexPic) {
		this.isIndexPic = isIndexPic;
	}
	
	@Column(name="is_img")
	public int getIsImg() {
		return isImg;
	}
	public void setIsImg(int isImg) {
		this.isImg = isImg;
	}
	
	@ManyToOne
	@JoinColumn(name="tid")
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	@ManyToOne
	@JoinColumn(name="pid")
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	
	@Column(name="is_attach")
	public int getIsAttach() {
		return isAttach;
	}
	public void setIsAttach(int isAttach) {
		this.isAttach = isAttach;
	}
	
	public Attachment() {
	}
	public Attachment(int id, String newName, String oldName, String type,
			String suffix, long size, int isIndexPic, int isImg, int isAttach,int tid,String topicTitle,Date publishDate,String author) {
		super();
		this.id = id;
		this.newName = newName;
		this.oldName = oldName;
		this.type = type;
		this.suffix = suffix;
		this.size = size;
		this.isIndexPic = isIndexPic;
		this.isImg = isImg;
		this.isAttach = isAttach;
		this.topic = new Topic();
		this.topic.setId(tid);
		//设置文章标题
		this.topic.setTitle(topicTitle);
		//设置发布日期
		this.topic.setPublishDate(publishDate);
		//设置发布人员
		this.topic.setAuthor(author);
	}
	
	public Attachment(int id, String newName, String oldName, String type,
			String suffix, long size, int isImg, People people, int isAttach) {
		super();
		this.id = id;
		this.newName = newName;
		this.oldName = oldName;
		this.type = type;
		this.suffix = suffix;
		this.size = size;
		this.isImg = isImg;
		this.people = people;
		this.isAttach = isAttach;
	}
	public Attachment(int id, String newName, String oldName, String type,
			String suffix, long size,int isIndexPic, int isImg, int isAttach) {
		super();
		this.id = id;
		this.newName = newName;
		this.oldName = oldName;
		this.type = type;
		this.suffix = suffix;
		this.size = size;
		this.isIndexPic = isIndexPic;
		this.isImg = isImg;
		this.isAttach = isAttach;
		this.topic = new Topic();
	}
}
