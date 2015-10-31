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
		<link href="<%=request.getContextPath() %>/resources/css/index/channel.css" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.cycle2.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/menu.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/picshow.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/index/main.js" type="text/javascript"></script>
		<link href="<%=request.getContextPath() %>/resources/css/index/top/top.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/css/index/foot/foot.css" rel="stylesheet" type="text/css" />
		
</head>
<body>
<div>
  <div>
  <div id="warp">
    	<jsp:include page="top.jsp"></jsp:include>
    	<jsp:include page="banner.jsp"></jsp:include>
    	<jsp:include page="search.jsp"></jsp:include>
    	
     
     <div class="content">
    <div class="left">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
         
              <span style="font-size:24px;">${pc.name}</span>
          
          <c:forEach var="c" items="${cs}">
          <tr>
            <td width="9%"><img src="<%=request.getContextPath() %>/resources/images/tb.jpg" width="8" height="8" /></td>

            <td width="91%"><a href="${c.id }">${c.name }</a></td>
          </tr>
          </c:forEach>

          
        </tbody>
      </table>
    </div>

    <div class="right">
      <div class="right_bt">
        <div>
         <span>当前您的位置&nbsp; <a href="<%=request.getContextPath()%>/index">首页</a>|
				<a href="<%=request.getContextPath()%>/channel/${pc.id}">${pc.name }</a>|<span style="color:#F7CA0F">${channel.name }</span></span>
        </div>

        <div class="clear"></div>
      </div>

      <div class="right_nr">
          <div id="channel_right">
                <div id="channel_title">
					<span>${channel.name}</span>
				</div>
				<div id="channel_img_container">
						<c:if test="${datas.total le 0 }">
							该栏目还没有文章...
						</c:if>
						<c:if test="${datas.total gt 0 }">
							<c:forEach var="att" items="${datas.datas }">
									<div class="channel_img">
										<div class="channel_img_img">
											<a href="<%=request.getContextPath()%>/topic/${att.topic.id}" title="${att.topic.title }">
											<img src="<%=request.getContextPath()%>/resources/upload/thumbnail/${att.newName}" width="150" height="110"/>
											</a>
										</div>
										<div class="channel_img_title">
										<span>${att.topic.title }</span></div>
										<div class="channel_img_intro">
											<span>
											<fmt:formatDate value="${att.topic.publishDate }" pattern="yyyy/MM/dd"/>
											&nbsp;&nbsp;&nbsp;${att.topic.author }</span>
										</div>
									</div>
								</c:forEach>
							<div id="channel_img_pager">
								<span>
								<jsp:include page="/jsp/index_pager.jsp">
									<jsp:param value="${datas.total }" name="totalRecord"/>
									<jsp:param value="channel/${channel.id}" name="url"/>
								</jsp:include>
								</span>
							</div>
						</c:if>
				</div>
          </div>
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


