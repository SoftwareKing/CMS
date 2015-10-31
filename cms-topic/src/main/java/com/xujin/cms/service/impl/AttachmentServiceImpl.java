package com.xujin.cms.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.xujin.basic.model.Pager;
import com.xujin.basic.model.SystemContext;
import com.xujin.cms.dao.AttachmentDao;
import com.xujin.cms.model.Attachment;
import com.xujin.cms.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	
	public final static int IMG_WIDTH = 900;
	public final static int THUMBNAIL_WIDTH = 150;
	public final static int THUMBNAIL_HEIGHT = 110;
	public final static int THUMBNAIL_NewWIDTH = 271;
	public final static int THUMBNAIL_NewHEIGHT = 259;
	
	@Resource(name="attachmentDao")
	private AttachmentDao attachmentDao;
	
	public final static String UPLOAD_PATH="/upload/";
	
	
	
	public static void deleteAttachFiles(Attachment a) {
		String realPath = SystemContext.getRealPath();
		realPath +=UPLOAD_PATH;
		new File(realPath+a.getNewName()).delete();
	}
	
	
	@Override
	public void add(Attachment a,InputStream is) throws IOException {
		try {
			attachmentDao.add(a);
			addFile(a,is);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void addFile(Attachment a,InputStream is) throws IOException {
		//进行文件的存储
		String realPath = SystemContext.getRealPath();
		String path = realPath+"/resources/upload/";
		String thumbPath = realPath+"/resources/upload/thumbnail/";
		String NewthumbPath = realPath+"/resources/upload/Newthumbnail/";
		File fp = new File(path);
		File tfp = new File(thumbPath);
		File ntfp = new File(NewthumbPath);
//		System.out.println(fp.exists());
//		System.out.println(tfp.exists());
		if(!fp.exists()) fp.mkdirs();
		if(!tfp.exists()) tfp.mkdirs();
		if(!ntfp.exists()) ntfp.mkdirs();
		path = path+a.getNewName();
		thumbPath = thumbPath+a.getNewName();
		NewthumbPath = NewthumbPath+a.getNewName();
//		System.out.println(path+","+thumbPath);
		if(a.getIsImg()==1) {
			BufferedImage oldBi = ImageIO.read(is);
			int width = oldBi.getWidth();
			int hight=oldBi.getHeight();
			Builder<BufferedImage> bf = Thumbnails.of(oldBi);
			if(width>IMG_WIDTH) {
				bf.scale((double)IMG_WIDTH/(double)width);
			} else {
				bf.scale(1.0f);
			}
			bf.toFile(path);
			//缩略图的处理
			//1、将原图进行压缩
			
			BufferedImage tbi = Thumbnails.of(oldBi).scale((THUMBNAIL_WIDTH*1.2)/width).asBufferedImage();
			//2、进行切割并且保存
			Thumbnails.of(tbi).scale(1.0f)
				.sourceRegion(Positions.CENTER, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT)
				.toFile(thumbPath);
			
			
			//缩略图的处理--用于首页新闻图片轮播
			 Thumbnails.of(oldBi).forceSize(THUMBNAIL_NewWIDTH, THUMBNAIL_NewHEIGHT)
			 .toFile(NewthumbPath);
			
		} else {
			FileUtils.copyInputStreamToFile(is, new File(path));
		}
	}

	@Override
	public void delete(int id) {
		Attachment a = attachmentDao.load(id);
		attachmentDao.delete(id);
		deleteAttachFiles(a);
	}

	@Override
	public Attachment load(int id) {
		return attachmentDao.load(id);
	}

	@Override
	public Pager<Attachment> findNoUseAttachment() {
		return attachmentDao.findNoUseAttachment();
	}

	@Override
	public void clearNoUseAttachment() {
		attachmentDao.clearNoUseAttachment();
	}

	@Override
	public List<Attachment> listByTopic(int tid) {
		return attachmentDao.listByTopic(tid);
	}

	@Override
	public List<Attachment> listIndexPic(int num) {
		return attachmentDao.listIndexPic(num);
	}

	@Override
	public Pager<Attachment> findChannelPic(int cid) {
		return attachmentDao.findChannelPic(cid);
	}

	@Override
	public List<Attachment> listAttachByTopic(int tid) {
		return attachmentDao.listAttachByTopic(tid);
	}

	@Override
	public void updateIndexPic(int aid) {
		Attachment att = attachmentDao.load(aid);
		System.out.println(aid+"------------>");
		if(att.getIsIndexPic()==0) {
			att.setIsIndexPic(1);
		} else {
			att.setIsIndexPic(0);
		}
		attachmentDao.update(att);
	}

	@Override
	public void updateAttachInfo(int aid) {
		Attachment att = attachmentDao.load(aid);
		if(att.getIsAttach()==0) {
			att.setIsAttach(1);
		} else {
			att.setIsAttach(0);
		}
		attachmentDao.update(att);
	}

	@Override
	public Pager<Attachment> listAllPic() {
		return attachmentDao.listAllIndexPic();
	}

	@Override
	public long findNoUseAttachmentNum() {
		return attachmentDao.findNoUseAttachmentNum();
	}


	@Override
	public List<Attachment> listIndexNewPicByNum(int num) {
		// TODO Auto-generated method stub
		return this.attachmentDao.listIndexNewPicByNum(num);
	}

}
