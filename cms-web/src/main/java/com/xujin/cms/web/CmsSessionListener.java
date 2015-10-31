package com.xujin.cms.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
 * 配合着解决文件上传中的由于flash的bug问题，会产生一个新的session
 * */
public class CmsSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	//正常的退出由AdminController中的方法移除session
	//但是如果浏览器直接关闭，则需要sessionDestroyed移除session，否则会常驻内存
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		CmsSessionContext.removeSession(event.getSession());
		System.out.println("移除了Session:"+event.getSession().getId());
	}

}
