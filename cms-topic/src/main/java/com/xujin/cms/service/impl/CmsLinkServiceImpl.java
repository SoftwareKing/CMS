package com.xujin.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujin.basic.model.Pager;
import com.xujin.cms.dao.CmsLinkDao;
import com.xujin.cms.model.CmsLink;
import com.xujin.cms.service.CmsLinkService;

@Service("cmsLinkService")
public class CmsLinkServiceImpl implements CmsLinkService {
	
	@Resource(name="cmsLinkDao")
	private CmsLinkDao cmsLinkDao;

	@Override
	public void add(CmsLink cl) {
		cmsLinkDao.add(cl);
	}

	@Override
	public void delete(int id) {
		cmsLinkDao.delete(id);
	}

	@Override
	public void update(CmsLink cl) {
		cmsLinkDao.update(cl);
	}

	@Override
	public CmsLink load(int id) {
		return cmsLinkDao.load(id);
	}

	@Override
	public Pager<CmsLink> findByType(String type) {
		return cmsLinkDao.findByType(type);
	}

	@Override
	public List<CmsLink> listByType(String type) {
		return cmsLinkDao.listByType(type);
	}

	@Override
	public List<String> listAllType() {
		return cmsLinkDao.listAllType();
	}

	@Override
	public Map<String, Integer> getMinAndMaxPos() {
		return cmsLinkDao.getMinAndMaxPos();
	}

	@Override
	public void updatePos(int id, int oldPos, int newPos) {
		cmsLinkDao.updatePos(id, oldPos, newPos);
	}

}
