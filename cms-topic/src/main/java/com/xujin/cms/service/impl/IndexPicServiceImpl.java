package com.xujin.cms.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.xujin.basic.model.Pager;
import com.xujin.basic.model.SystemContext;
import com.xujin.cms.dao.IndexPicDao;
import com.xujin.cms.model.IndexPic;
import com.xujin.cms.service.IndexPicService;

@Service("indexPicService")
public class IndexPicServiceImpl implements IndexPicService {
	
	@Resource(name="indexPicDao")
	private IndexPicDao indexPicDao;
	
	@Override
	public void add(IndexPic indexPic) {
		indexPic.setCreateDate(new Date());
		indexPicDao.add(indexPic);
	}

	@Override
	public void update(IndexPic indexPic) {
		indexPicDao.update(indexPic);
	}

	@Override
	public void delete(int id) {
		IndexPic pic = indexPicDao.load(id);
		String rp = SystemContext.getRealPath();
		String tp = rp+"/resources/indexPic/thumbnail/"+pic.getNewName();
		String pp = rp+"/resources/indexPic/"+pic.getNewName();
		new File(tp).delete();
		new File(pp).delete();
		indexPicDao.delete(id);
	}

	@Override
	public void updateStatus(int id) {
		IndexPic ip = indexPicDao.load(id);
		if(ip.getStatus()==0) ip.setStatus(1);
		else ip.setStatus(0);
		indexPicDao.update(ip);
	}

	@Override
	public IndexPic load(int id) {
		return indexPicDao.load(id);
	}

	@Override
	public List<IndexPic> listIndexPicByNum(int num) {
		return indexPicDao.listIndexPicByNum(num);
	}

	@Override
	public Pager<IndexPic> findIndexPic() {
		return indexPicDao.findIndexPic();
	}

	@Override
	public List<String> listAllIndexPicName() {
		return indexPicDao.listAllIndexPicName();
	}

	@Override
	public void cleanNoUseIndexPic(List<String> pics) throws IOException {
		String rp = SystemContext.getRealPath();
		//首先删除临时文件夹
		File temp = new File(rp+"/resources/indexPic/temp");
		FileUtils.deleteDirectory(temp);
		//其次删除缩略图
		for(String f:pics) {
			new File(rp+"/resources/indexPic/thumbnail/"+f).delete();
			new File(rp+"/resources/indexPic/"+f).delete();
		}
	}

	@Override
	public Map<String, Integer> getMinAdnMaxPos() {
		return indexPicDao.getMinAdnMaxPos();
	}

	@Override
	public void updatePos(int id, int oldPos, int newPos) {
		indexPicDao.updatePos(id, oldPos, newPos);
	}

}
