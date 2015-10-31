package com.xujin.cms.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.xujin.basic.dao.impl.BaseDaoImpl;
import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.GroupDao;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.ChannelTree;
import com.xujin.cms.model.Group;
import com.xujin.cms.model.GroupChannel;

/**
 * @Description:GroupDao的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {

	@Override
	public List<Group> listGroup() {
		return this.list("from Group");
	}

	@Override
	public Pager<Group> findGroup() {
		return this.find("from Group");
	}

	@Override
	public void deleteGroupUsers(int gid) {
		this.UpdateByHql("delete UserGroup ug where ug.group.id=?",gid);
	}

	@Override
	public void addGroupChannel(Group group, Channel channel) {
		GroupChannel gc = this.loadGroupChannel(group.getId(), channel.getId());
		if(gc!=null) return;
		gc = new GroupChannel();
		gc.setGroup(group);
		gc.setChannel(channel);
		this.getSession().save(gc);
	}

	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		return (GroupChannel)this.Queryobject("from GroupChannel where group.id=? and channel.id=?",
				new Object[]{gid,cid});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		String hql = "select gc.channel.id from GroupChannel gc where gc.group.id=?";
		return this.getSession().createQuery(hql).setParameter(0, gid).list();
	}

	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		String sql = "select c.id as id,c.name as name,c.pid as pid from " +
				"t_group_channel gc left join t_channel c on(gc.c_id=c.id) " +
				"where gc.g_id=?";
		List<ChannelTree> cts = this.listBySql(sql,gid,ChannelTree.class, false);
		ChannelDaoImpl.initTreeNode(cts);
		return cts;
	}

	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		String sql = "select distinct c.id as id,c.name as name,c.pid as pid from " +
				"t_group_channel gc left join t_channel c on(gc.c_id=c.id) left join t_user_group ug on(ug.g_id=gc.g_id)" +
				"where ug.u_id=?";
		List<ChannelTree> cts = this.listBySql(sql,uid,ChannelTree.class, false);
		ChannelDaoImpl.initTreeNode(cts);
		return cts;
	}

	@Override
	public void clearGroupChannel(int gid) {
		this.UpdateByHql("delete GroupChannel gc where gc.group.id=?",gid);
	}

	@Override
	public void deleteGroupChannel(int gid, int cid) {
		this.UpdateByHql("delete GroupChannel gc where gc.group.id=? and gc.channel.id=?",new Object[]{gid,cid});
	}
}
