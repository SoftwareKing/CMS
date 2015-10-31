<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>齐齐哈尔大学计算机与控制工程学院</title>
		<meta http-equiv="keywords" content="齐齐哈尔大学计算机与控制工程学院,计算机与控制工程学院,计控学院">   
        <meta http-equiv="description" content="齐齐哈尔大学计算机与控制工程学院网站,计算机与控制工程学院网站"> 
        <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.cycle2.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/menu.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/picshow.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/index/main.js" type="text/javascript"></script>
		<link href="<%=request.getContextPath() %>/resources/css/index/top/top.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/css/index/foot/foot.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/css/index/topic.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div>
  <div>
  <div id="warp">
    	<jsp:include page="top.jsp"></jsp:include>
    	<jsp:include page="banner.jsp"></jsp:include>
    	<jsp:include page="search.jsp"></jsp:include>
     <div class="content">
       <div id="content_con">
		<div id="article_title"><span>${topic.title}</span></div>
         <div id="article_keyword">
			<a href="<%=request.getContextPath() %>/channel/${topic.channel.id}" class="article_keyword_href">来源于:${topic.channel.name}</a>
			<span>关键字:</span>
			<c:if test="${hasKey }">
				<c:forEach items="${kws }" var="k">
					<a href="<%=request.getContextPath() %>/keyword/${k}" class="article_keyword_href">${k }</a>
				</c:forEach>
			</c:if>
		</div>
		<div id="article_info">
		<span>发布时间:</span><fmt:formatDate value="${topic.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
		&nbsp;<span>发布人:</span>${topic.author }
		</div>
		<div id="article_content">
		${topic.content }
		</div>
		<div class="article_atts">
		相关附件:
		<c:if test="${!hasAtts}">该文章没有附件</c:if>
		<c:if test="${hasAtts }">
			<c:forEach items="${atts }" var="att">
				<span><a  style="color:#ffff00" href="<%=request.getContextPath()%>/resources/upload/${att.newName}" class="article_att_link">${att.oldName }</a></span>
			</c:forEach>
		</c:if>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tbody><tr>
                 <td style="font-size: 16px" align="right">
                       <a style="color:#ffff00" href="javascript:history.go(-1);">返回</a>
                 </td>
            </tr>
           </tbody>
         </table>
		</div>
		 
	</div>

         <div class="clear"></div>
     </div>
     <jsp:include page="foot.jsp"></jsp:include>
  </div>
  </div>
  </div>

</body>
</html>


