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
		<script src="<%=request.getContextPath() %>/resources/js/index/index/main.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.cycle2.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/menu.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/picshow.js" type="text/javascript"></script>
		<link href="<%=request.getContextPath() %>/resources/css/index/top/top.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/css/index/foot/foot.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(function(){
				$("#search_btn1").click(function() {
					var k = $("#search_con1").val();
					if(k=="") {
						alert("请输入要检索的关键字");
					} else {
						window.location.href=$("#ctx").val()+"/search/"+k;
					}
				})
			})
	</script>
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
         
           <span style="font-size:24px;">导航栏目</span>
          
         <c:forEach var="c" items="${cs}">
          <tr>
            <td width="9%"><img src="<%=request.getContextPath() %>/resources/images/tb.jpg" width="8" height="8" /></td>

            <td width="91%"><a href="<%=request.getContextPath() %>/channel/${c.id }">${c.name }</a></td>
          </tr>
          </c:forEach>

          
        </tbody>
      </table>
    </div>

    <div class="right">
      <div class="right_bt">
        <div>
         <span>当前您的位置&nbsp;
           <a href="<%=request.getContextPath()%>/index">首页</a><span style="color:#F7CA0F">|检索:${con }</span>
		</span>
        </div>

        <div class="clear"></div>
      </div>

      <div class="right_nr">
          <div id="channel_right">
                <div id="channel_title">
					<span>检索内容:[${con }]</span>
					<span><input type="text" id="search_con1" value="${con }" size="50"/><input type="button" id="search_btn1" value="进行检索"/></span>
				</div>
				<table>
					<c:if test="${datas.total le 0 }">
						<tr><td>没有检索到任何相关文章</td></tr>
					</c:if>
					<c:if test="${datas.total gt 0 }">
					<c:forEach items="${datas.datas}" var="topic">
					<tr>
						<td>
							<a href="<%=request.getContextPath() %>/topic/${topic.id}" class="channel_right_href">
							${topic.title }</a>
						</td>
						<td>[<fmt:formatDate value="${topic.publishDate }" pattern="yyyy-MM-dd"/>]</td>
					</tr>
					</c:forEach>
					<tfoot>
					<tr>
					<td colspan="2">
						<jsp:include page="/jsp/index_pager.jsp">
							<jsp:param value="${datas.total }" name="totalRecord"/>
							<jsp:param value="" name="url"/>
						</jsp:include>
					</td>
					</tr>
					</tfoot>
					</c:if>
				</table>
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


