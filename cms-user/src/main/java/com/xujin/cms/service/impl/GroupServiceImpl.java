package com.xujin.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.ChannelDao;
import com.xujin.cms.dao.GroupDao;
import com.xujin.cms.dao.UserDao;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.ChannelTree;
import com.xujin.cms.model.CmsException;
import com.xujin.cms.model.Group;
import com.xujin.cms.model.GroupChannel;
import com.xujin.cms.model.User;
import com.xujin.cms.service.GroupService;

/**
 * @Description:GroupService的实现类
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-14(创建日期)
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Resource(name="groupDao")
	private GroupDao groupDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	
	@Resource(name="channelDao")
	private ChannelDao channelDao;
	
	@Override
	public void add(Group group) {

		this.groupDao.add(group);
	}

	@Override
	public void delete(int id) {
		
		List<User> users=this.userDao.listGroupUsers(id);
		if(users!=null&&users.size()>0) throw new CmsException("所删除的组中还有用户,不可以删除");
		this.groupDao.delete(id);
		
		
	}

	@Override
	public Group load(int id) {
		
		return this.groupDao.load(id);
	}

	@Override
	public void update(Group group) {

		this.groupDao.update(group);
		
	}

	@Override
	public List<Group> listGroup() {
		
		return this.groupDao.listGroup();
	}

	@Override
	public Pager<Group> findGroup() {
		// TODO Auto-generated method stub
		return this.groupDao.findGroup();
	}

	@Override
	public void deleteGroupUsers(int gid) {

		this.groupDao.deleteGroupUsers(gid);
	}
	@Override
	public void addGroupChannel(int gid, int cid) {
		Group g = groupDao.load(gid);
		Channel c = channelDao.load(cid);
		if(c==null||g==null)throw new CmsException("要添加的组频道关联对象不存在");
		groupDao.addGroupChannel(g, c);
	}
	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		return groupDao.loadGroupChannel(gid, cid);
	}
	@Override
	public void clearGroupChannel(int gid) {
		groupDao.clearGroupChannel(gid);
	}
	@Override
	public void deleteGroupChannel(int gid, int cid) {
		groupDao.deleteGroupChannel(gid, cid);
	}
	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		return groupDao.listGroupChannelIds(gid);
	}
	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		return groupDao.generateGroupChannelTree(gid);
	}
	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		return groupDao.generateUserChannelTree(uid);
	}
}
