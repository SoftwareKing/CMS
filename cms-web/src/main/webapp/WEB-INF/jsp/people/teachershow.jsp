<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<link href="<%=request.getContextPath() %>/resources/css/index/teacher.css" rel="stylesheet" type="text/css" />
		<script src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/index/main.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.cycle2.min.js"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/menu.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath() %>/resources/js/index/picshow.js" type="text/javascript"></script>
		<link href="<%=request.getContextPath() %>/resources/css/index/top/top.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/css/index/foot/foot.css" rel="stylesheet" type="text/css" />
		
</head>
<body>
<div>
  <div>
  <div id="warp">
    	<jsp:include page="/WEB-INF/jsp/index/top.jsp"></jsp:include>
    	<jsp:include page="/WEB-INF/jsp/index/banner.jsp"></jsp:include>
    	<jsp:include page="/WEB-INF/jsp/index/search.jsp"></jsp:include>
    	
     
    <div class="content">
            <div class="peopleleft">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tbody><tr>
                        <td>
                        <!-- 
                            <img alt="" src="uploadfiles/1109050242628330.jpg" width="150px" height="195px">
                          --> 
                        </td>
                    </tr>
                    <tr>
                        <td style="vertical-align: top;">
                            <table style="margin-top: 15px">
                                <tbody><tr>
                                    <td>
                                        <p style="font-size: 26px; font-weight: bold; text-indent: 10px; letter-spacing: 4px">
                                            ${people.name }</p>
                                    </td>
                                </tr>
                            </tbody></table>
                            <table border="0" cellpadding="0" cellspacing="0" width="100%" style="font-size: 14px;
                                line-height: 20px; margin-top: 10px;">
                                <tbody><tr>
                                    <td style="width: 60px" valign="top">
                                        系别：                                    </td>
                                    <td style="width: 180px" valign="top">
                                         <c:forEach items="${pd }" var="g">
					                        ${g.name }
				                           </c:forEach>                           
                                      </td>
                                </tr>
                                <tr>
                                    <td style="width: 60px" valign="top">
                                        职称：                                    </td>
                                    <td style="width: 180px" valign="top">
                                     <c:if test="${people.zhichen eq 0 }">
										博士生导师
									</c:if>
									<c:if test="${people.zhichen eq 1 }">
									        硕士生导师
									</c:if>
									<c:if test="${people.zhichen eq 2 }">
									        教授
									</c:if>
									<c:if test="${people.zhichen eq 3 }">
									       副 教授
									</c:if>
									<c:if test="${people.zhichen eq 4 }">
									        讲师
									</c:if>
									<c:if test="${people.zhichen eq 5 }">
									     教师队伍
									</c:if>                      
                                     </td>
                                </tr>
                                <tr>
                                    <td style="width: 60px" valign="top">
                                        职务：                                    </td>
                                    <td style="width: 180px" valign="top">
                                        ${people.zhiwu}                               
                                        
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 60px" valign="top">
                                        办公室：                                    </td>
                                    <td style="width: 180px" valign="top">
                                      ${people.bgs }                                  
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 60px" valign="top">
                                        电话：                                    </td>
                                    <td style="width: 180px" valign="top">
                                       ${people.phone }                                
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 60px" valign="top">
                                        E-mail：                                    </td>
                                    <td style="width: 180px" valign="top">
                                       ${people.email}                                  </td>
                                </tr>
                                <tr>
                                  <td style="width: 60px" valign="top">&nbsp;</td>
                                  <td style="width: 180px" valign="top">&nbsp;</td>
                                </tr>
                            </tbody></table>
                      </td>
                    </tr>
                </tbody></table>
            </div>
            <div class="peopleright">
                <div class="peopleright_nr">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="ta" align="left">
                        <tbody><tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" width="80%">
                                  <tbody>
                                    <tr>
                                        <td class="ta2">
                                            教育背景
                                        </td>
                                    </tr>
                                    <tr>
                                     <td>${people.jybg}</td>
                                    </tr>
                                   
                                </tbody>
                                </table>
                                <table border="0" cellpadding="0" cellspacing="0" width="80%">
                                    <tbody><tr>
                                        <td class="ta2">
                                            研究方向
                                        </td>
                                    </tr>
                                    <tr>
                                    <td>${people.jybg}</td>
                                    </tr>
                                </tbody></table>
                                <table border="0" cellpadding="0" cellspacing="0" width="80%">
                                    <tbody><tr>
                                        <td class="ta2" colspan="2">
                                            课程教学
                                        </td>
                                    </tr>
                                    <tr>
                                    <td>${people.kcjx}</td>
                                    </tr>
                                </tbody></table>
                                <table border="0" cellpadding="0" cellspacing="0" width="80%">
                                    <tbody><tr>
                                        <td class="ta2">
                                            学术兼职
                                        </td>
                                    </tr>
                                    <tr>
                                      <td>${people.xsjz}</td>
                                    </tr>
                                </tbody></table>
                                <table border="0" cellpadding="0" cellspacing="0" width="80%">
                                    <tbody><tr>
                                        <td class="ta2">
                                            科研项目
                                        </td>
                                    </tr>
                                    <tr>
                                    <td>${people.kyxm}</td>
                                    </tr>
                                </tbody>
                                </table>
                                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                    <tbody><tr>
                                        <td style="font-size: 16px" align="right">
                                            <a style="color:#ffff00" href="javascript:history.go(-1);">返回</a>
                                        </td>
                                    </tr>
                                </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody></table>
                </div>
            </div>
            <div class="clear">
            </div>
	</div>
     <jsp:include page="/WEB-INF/jsp/index/foot.jsp"></jsp:include>
  </div>
  </div>
  </div>

</body>
</html>


