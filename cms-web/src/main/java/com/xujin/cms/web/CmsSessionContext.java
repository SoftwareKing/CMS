package com.xujin.cms.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
/*
 * 配合着解决文件上传中的由于flash的bug问题，会产生一个新的session
 * */
public class CmsSessionContext {
	private static final Map<String,HttpSession> ctx = new HashMap<String,HttpSession>();
	private CmsSessionContext(){}
	
	public static void addSessoin(HttpSession session) {
		ctx.put(session.getId(), session);
	}
	
	public static void removeSession(HttpSession session) {
		ctx.remove(session.getId());
	}
	
	public static HttpSession getSession(String sessionId) {
		return ctx.get(sessionId);
	}
}
