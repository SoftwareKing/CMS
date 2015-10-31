package com.xujin.cms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.xujin.basic.model.Pager;
import com.xujin.cms.model.IndexPic;


public interface IndexPicService {
	
	//添加
	public void add(IndexPic indexPic);
	//更新
	public void update(IndexPic indexPic);
	//删除
	public void delete(int id);
	
	//更新状态
	public void updateStatus(int id);
	
	public IndexPic load(int id);
	/**
	 * 根据数量来获取首页图片信息
	 * @param num
	 * @return
	 */
	public List<IndexPic> listIndexPicByNum(int num);
	
	public Pager<IndexPic> findIndexPic();
	
	public List<String> listAllIndexPicName();
	
	public void cleanNoUseIndexPic(List<String> pics) throws IOException;
	
	public Map<String,Integer> getMinAdnMaxPos();
	
	public void updatePos(int id, int oldPos, int newPos);
}
