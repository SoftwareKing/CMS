package com.xujin.cms.model;

/**
 * 系统栏目树对象---用于把数据库中的栏目树，加载出来。
 * @author Administrator
 *
 */
public class ChannelTree {
	
	private Integer id;//树ID
	private String name;//名称
	private Integer pid;//父节点ID
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public ChannelTree() {
	}

	public ChannelTree(Integer id, String name, Integer pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "ChannelTree [id=" + id + ", name=" + name + ", pid=" + pid
				+ "]";
	}
	
}
