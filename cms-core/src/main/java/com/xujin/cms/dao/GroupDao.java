package com.xujin.cms.dao;

import java.util.List;

import com.xujin.basic.dao.BaseDao;
import com.xujin.basic.model.Pager;
import com.xujin.cms.model.Channel;
import com.xujin.cms.model.ChannelTree;
import com.xujin.cms.model.Group;
import com.xujin.cms.model.GroupChannel;

/**
 * @Description:GroupDao接口
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2013-12-10(创建日期)
 */
public interface GroupDao extends BaseDao<Group> {
	public List<Group> listGroup();
	public Pager<Group> findGroup();
	public void deleteGroupUsers(int gid);
	
	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	public void addGroupChannel(Group group,Channel channel);
	/**
	 * 加载GroupChannel对象
	 * @param gid
	 * @param cid
	 * @return
	 */
	public GroupChannel loadGroupChannel(int gid,int cid);
	/**
	 * 清空组所管理的栏目
	 * @param gid
	 */
	public void clearGroupChannel(int gid);
	/**
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
	/**
	 * 获取某个组的所有管理栏目的id
	 * @param gid
	 * @return
	 */
	public List<Integer> listGroupChannelIds(int gid);
	/**
	 * 获取某个组的栏目树
	 * @param gid
	 * @return
	 */
	public List<ChannelTree> generateGroupChannelTree(int gid);
	/**
	 * 获取某个用户的栏目树
	 * @param uid
	 * @return
	 */
	public List<ChannelTree> generateUserChannelTree(int uid);

}
