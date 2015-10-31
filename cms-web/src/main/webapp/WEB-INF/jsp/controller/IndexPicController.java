package com.xujin.cms.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xujin.cms.dto.AjaxObj;
import com.xujin.cms.dto.IndexPicDto;
import com.xujin.cms.model.BaseInfo;
import com.xujin.cms.model.IndexPic;
import com.xujin.cms.service.AttachmentService;
import com.xujin.cms.service.IndexPicService;
import com.xujin.cms.utils.JsonUtil;


@Controller
@RequestMapping("/admin/pic")
public class IndexPicController {
	
	@Resource(name="indexPicService")
	private IndexPicService indexPicService;
	
	@Resource(name="attachmentService")
	private AttachmentService attachmentService;
	//private IndexService indexService;
	public final static String FILE_PATH="/resources/indexPic";
	public final static int T_W = 120;
	
	
	//所有新闻图片
	@RequestMapping("/newPics")
	public String listNewPic(Model model) {
		model.addAttribute("datas", attachmentService.listAllPic());
		return "pic/listNewPic";
	}

	@RequestMapping("/indexPics")
	public String listIndexPic(Model model) {
		Map<String,Integer> mm = indexPicService.getMinAdnMaxPos();
		model.addAttribute("min", mm.get("min"));
		model.addAttribute("max", mm.get("max"));
		model.addAttribute("datas",indexPicService.findIndexPic());
		return "pic/listIndexPic";
	}
	
	@RequestMapping(value="/addIndexPic",method=RequestMethod.GET)
	public String addIndexPic(Model model) {
		IndexPic ip = new IndexPic();
		ip.setStatus(1);
		model.addAttribute("indexPic", ip);
		return "pic/addIndexPic";
	}
	
	@RequestMapping(value="/addIndexPic",method=RequestMethod.POST)
	public String addIndexPic(@Validated IndexPic indexPic,BindingResult br) {
		if(br.hasFieldErrors()) {
			return "pic/addIndexPic";
		}
		indexPicService.add(indexPic);
		if(indexPic.getStatus()!=0) {
			//indexService.generateBody();
		}
		return "redirect:/jsp/common/addSuc.jsp";
	}
	
	@RequestMapping(value="/updateIndexPic/{id}",method=RequestMethod.GET)
	public String updateIndexPic(@PathVariable int id,Model model) {
		IndexPic ip = indexPicService.load(id);
		model.addAttribute("indexPic", ip);
		return "pic/updateIndexPic";
	}
	
	@RequestMapping(value="/updateIndexPic/{id}",method=RequestMethod.POST)
	public String updateIndexPic(@PathVariable int id,@Validated IndexPic indexPic,BindingResult br) {
		if(br.hasErrors()) {
			return "pic/updateIndexPic";
		}
		IndexPic tip = indexPicService.load(id);
		tip.setLinkType(indexPic.getLinkType());
		tip.setLinkUrl(indexPic.getLinkUrl());
		tip.setNewName(indexPic.getNewName());
		tip.setOldName(indexPic.getOldName());
		tip.setStatus(indexPic.getStatus());
		tip.setSubTitle(indexPic.getSubTitle());
		tip.setTitle(indexPic.getTitle());
		indexPicService.update(tip);
		//indexService.generateBody();
		return "redirect:/jsp/common/updateSuc.jsp";
	}
	
	@RequestMapping(value="/indexPic/{id}")
	public String showIndexPic(@PathVariable int id,Model model) {
		model.addAttribute("indexPic",indexPicService.load(id));
		return "pic/showIndexPic";
	}
	
	@RequestMapping(value="/deleteIndexPic/{id}")
	public String deleteIndexPic(@PathVariable int id) {
		indexPicService.delete(id);
		//indexService.generateBody();
		return "redirect:/admin/pic/indexPics";
	}
	
	@RequestMapping(value="updateIndexPicStatus/{id}")
	public String updateIndexPicStatus(@PathVariable int id) {
		indexPicService.updateStatus(id);
		//indexService.generateBody();
		return "redirect:/admin/pic/indexPics";
	}
	
	@RequestMapping(value="/uploadIndexPic",method=RequestMethod.POST)
	public void uploadIndexPic(HttpSession session,HttpServletResponse resp,MultipartFile pic) {
		resp.setContentType("text/plain;charset=utf-8");
		AjaxObj ao = new AjaxObj();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			String oldName = pic.getOriginalFilename();
			String newName = new Date().getTime()+"."+FilenameUtils.getExtension(oldName);
			String realPath = session.getServletContext().getRealPath("");
			File f = new File(realPath+FILE_PATH+"/temp");
			System.out.println(realPath+FILE_PATH+"/temp");
			if(!f.exists()) {
				f.mkdirs();
			}
			//从属性文件中获取需要设置的图片的宽度和高度
			BaseInfo baseInfo = (BaseInfo)session.getServletContext().getAttribute("baseInfo");
			double w = baseInfo.getIndexPicWidth();
			double h = baseInfo.getIndexPicHeight();
			BufferedImage bi = ImageIO.read(pic.getInputStream());
			double nw = bi.getWidth();
			double nh = bi.getHeight();
			if(nw>w&&nw/nh<w/h) {
				//图片的大小符合要求
				//判断是否进行缩放
				Builder<BufferedImage> b = Thumbnails.of(bi);
				if(nw-w>150) {
					b.scale((w+150)/nw);
				} else {
					b.scale(1.0);
				}
				BufferedImage bi2 = b.asBufferedImage();
				b.toFile(realPath+FILE_PATH+"/temp/"+newName);
				
				IndexPicDto ipd = new IndexPicDto();
				ipd.setNewName(newName);
				ipd.setOldName(oldName);
				ipd.setIndexPicHeight(new Double(h).intValue());
				ipd.setIndexPicWidth(new Double(w).intValue());
				ipd.setImgWidth(bi2.getWidth());
				ipd.setImgHeight(bi2.getHeight());
				ao.setObj(ipd);
				ao.setResult(1);
			} else {
				ao.setResult(0);
				ao.setMsg("图片的尺寸不在有效范围中");
			}
			
		} catch (IOException e) {
			ao.setResult(0);
			ao.setMsg(e.getMessage());
		}
		out.println(JsonUtil.getInstance().obj2json(ao));
		out.flush();
	}
	
	@RequestMapping(value="/confirmPic",method=RequestMethod.POST)
	public @ResponseBody AjaxObj confirmPic(HttpSession session,int x,int y,int w,int h,String newName) {
		AjaxObj ao = new AjaxObj();
		try {
			BaseInfo baseInfo = (BaseInfo)session.getServletContext().getAttribute("baseInfo");
			int pw = baseInfo.getIndexPicWidth();
			int ph = baseInfo.getIndexPicHeight();
			String path = session.getServletContext().getRealPath("");
			String tpath = path+FILE_PATH+"/temp/"+newName;
			String tpath1 = path+FILE_PATH+"/temp/";
			File f = new File(tpath1);
			if(!f.exists()) {
				f.mkdirs();
			}
			
			File tf = new File(tpath);
			BufferedImage bi = ImageIO.read(tf);
			String npath = path+FILE_PATH+"/"+newName;
			String ttpath = path+FILE_PATH+"/thumbnail/"+newName;
			String ttpath1 = path+FILE_PATH+"/thumbnail/";
			
			File f1 = new File(ttpath1);
			if(!f1.exists()) {
				f1.mkdirs();
			}
			
			Builder<BufferedImage> b = Thumbnails.of(bi);
			//写原图
			BufferedImage bi2 = b.sourceRegion(x, y, w, h).size(pw, ph).asBufferedImage();
			b.toFile(npath);
			//写缩略图
			Thumbnails.of(bi2).scale((double)T_W/(double)pw).toFile(ttpath);
			tf.delete();
			ao.setResult(1);
			return ao;
		} catch (IOException e) {
			e.printStackTrace();
			ao.setResult(0);
			ao.setMsg(e.getMessage());
		}
		return ao;
	}
}
