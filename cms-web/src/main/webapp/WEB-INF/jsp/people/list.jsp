<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/main.js"></script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<table width="800" cellspacing="0" cellPadding="0" id="listTable">
		<thead>
		<tr>
			<td>人员标识</td>
			<td>人员姓名</td>
			<td>人员职称</td>
			<td>人员状态</td>
			<td>操作</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${datas.datas }" var="people">
			<tr>
				<td>${people.id }&nbsp;</td>
				<td><a href="<%=request.getContextPath() %>/admin/people/${people.id }" class="list_link">${people.name}</a></td>
				<td>
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
				<td>
					<c:if test="${people.status eq 0 }">
						<span class="emp">停用</span>
						<a href="<%=request.getContextPath() %>/admin/people/updateStatus/${people.id }" class="list_op">启用</a>
					</c:if>
					<c:if test="${people.status eq 1 }">
						<span>启用</span>
						<a href="<%=request.getContextPath() %>/admin/people/updateStatus/${people.id }" class="list_op">停用</a>
					</c:if>
					&nbsp;
				</td>
				
				<td>
					<a href="<%=request.getContextPath() %>/admin/people/delete/${people.id }" title="${people.id }" class="list_op delete">删除</a>
					<a href="<%=request.getContextPath() %>/admin/people/update/${people.id }" class="list_op">更新</a>
				&nbsp;
				</td>
			</tr>
		</c:forEach>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="6" style="text-align:right;margin-right:10px;">
			<jsp:include page="/jsp/pager.jsp">
				<jsp:param value="${datas.total }" name="totalRecord"/>
				<jsp:param value="peoples" name="url"/>
			</jsp:include>
			</td>
		</tr>
		</tfoot>
	</table>
</div>
</body>
</html>