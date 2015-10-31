<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="rollpic">
    <div id="rollCaption"><span></span></div>
     <div id="rollPager"></div>
     <#list pics as pic>
     <a href="${pic.linkUrl}" title="${pic.title}"><img src="<%=request.getContextPath()%>/resources/indexPic/${pic.newName}" border="1"/></a>
    </#list>
 </div>
