package com.xujin.cms.dwr;

public interface DwrService {
	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	public void addGroupChannel(int gid,int cid);
	
	/**
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
	
	//更新主页图片
    public void updateIndexPic(int aid);
	
    //更新附件信息
	public void updateAttachInfo(int aid);
	
	//删除附件
	public void deleteAttach(int id);
	
	//更新首页图片的位置
    public void updatePicPos(int id, int oldPos, int newPos);
	
	public void updateLinkPos(int id,int oldPos,int newPos);
	
}
