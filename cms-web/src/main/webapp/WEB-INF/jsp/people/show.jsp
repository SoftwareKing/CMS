<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<table width="800" cellspacing="0" cellPadding="0">
		<thead><tr><td colspan="2">显示人员功能:</td></tr></thead>
		<tr>
			<td class="rightTd" width="200px">姓名:</td><td class="leftTd">${people.name }&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">系别:</td>
			<td class="leftTd">
                  <c:forEach items="${pd }" var="g">
					${g.name }
				</c:forEach>
             </td>
		</tr>
		<tr>
			<td class="rightTd">职务:</td><td>${people.zhiwu}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">职称:</td><td>
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
			<td class="rightTd">办公室:</td><td>${people.bgs }&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">电话:</td><td>${people.phone }&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">E-Mail:</td><td>${people.email}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">状态:</td>
			<td>
				<c:if test="${people.status eq 0 }">
					<span class="emp">停用</span>
				</c:if>
				<c:if test="${people.status eq 1 }">
					<span>启用</span>
				</c:if>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="rightTd">创建时间:</td>
			<td>
				<fmt:formatDate value="${people.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td class="rightTd">教育背景:</td><td>${people.jybg}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">研究方向:</td><td>${people.yjfx}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">课程教学:</td><td>${people.kcjx}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">学术兼职:</td><td>${people.xsjz}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">科研项目:</td><td>${people.kyxm}&nbsp;</td>
		</tr>
		<tr>
			<td class="rightTd">论文著作:</td><td>${people.lwzz}&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd">
				<c:if test="${isAdmin}">
				<a href="update/${people.id }" class="list_op">修改用户</a>
				<a href="javascript:history.go(-1);">返回</a>
				</c:if>
			</td>
		</tr>
	</table>
</div>
</body>
</html>