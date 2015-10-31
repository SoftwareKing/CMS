package com.xujin.cms.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import com.xujin.cms.service.AttachmentService;
import com.xujin.cms.service.CmsLinkService;
import com.xujin.cms.service.GroupService;
import com.xujin.cms.service.IndexPicService;
import com.xujin.cms.service.IndexService;


@RemoteProxy(name="dwrService")
public class DwrServiceImpl implements DwrService{
	
	@Resource(name="groupService")
	private GroupService groupService;

	@Resource(name="attachmentService")
	private AttachmentService attachmentService;
	
	@Resource(name="indexPicService")
	private IndexPicService indexPicService;
	
	@Resource(name="cmsLinkService")
	private CmsLinkService  cmsLinkService;
	
	@Resource(name="indexService")
	private IndexService indexService;
	

	/****************************用于用户组栏目设置管理************/
	@Override
	@RemoteMethod
	public void addGroupChannel(int gid, int cid) {
		groupService.addGroupChannel(gid, cid);
	}

	
	@Override
	@RemoteMethod
	public void deleteGroupChannel(int gid, int cid) {
		groupService.deleteGroupChannel(gid, cid);
	}
	/****************************用于用户组栏目设置管理************/
	
	/****************************dwr处理新闻中的图片是否是主页图片，在主页显示************/
	@Override
	@RemoteMethod
	public void updateIndexPic(int aid) {
		attachmentService.updateIndexPic(aid);
		this.indexService.generateNewpic();
	}
	
	/****************************dwr附件信息处理是否是附件信息************/
	@Override
	@RemoteMethod
	public void updateAttachInfo(int aid) {
		attachmentService.updateAttachInfo(aid);
	}
	
	/****************************dwr附件信息处理是删除附件************/
	@Override
	@RemoteMethod
	public void deleteAttach(int id) {
		attachmentService.delete(id);
	}
	
	@Override
	@RemoteMethod
	public void updatePicPos(int id, int oldPos, int newPos) {
		indexPicService.updatePos(id, oldPos, newPos);
	}
	
	@Override
	@RemoteMethod
	public void updateLinkPos(int id, int oldPos, int newPos) {
		cmsLinkService.updatePos(id, oldPos, newPos);
	}

	
	

}
