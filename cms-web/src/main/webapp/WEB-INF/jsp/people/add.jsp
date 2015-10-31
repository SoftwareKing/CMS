<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/uploadify/uploadify.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/PeopleAdd.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/topicAdd.js"></script>
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>
</head>
<body>
<div id="content">
<input type="hidden" id="ctx" value="<%=request.getContextPath() %>"/>
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<sf:form method="post" modelAttribute="peopleDto" id="addForm">
	<table width="800" cellspacing="0" cellPadding="0">
		<thead><tr><td colspan="2">添加人员功能</td></tr></thead>
		<!-- 
		<tr>
			<td class="rightTd">照片附件</td>
			<td class="leftTd">
				<div id="attachs"></div>
				<input type="file" id="attach" name="attach"/>
				<input type="button" id="uploadFile" value="上传文件"/>
			</td>
		</tr>
		 -->
		<tr>
			<td class="rightTd" width="200px">姓名:</td>
			<td class="leftTd">
			  <sf:input path="name" size="30"/><sf:errors cssClass="errorContainer" path="name"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">状态:</td>
			<td>
				<sf:select path="status">
					<sf:option value="0">停用</sf:option>
					<sf:option value="1">启用</sf:option>
				</sf:select>
			</td>
		</tr>
		
		<tr>
			<td class="rightTd">系别:</td>
			<td>
				<sf:checkboxes items="${departments }" path="departmentIds" itemLabel="name" itemValue="id"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">职务:</td><td class="leftTd"><sf:input path="zhiwu" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">职称:</td>
			<td class="leftTd">
			     <sf:select path="zhichen">
					<sf:option value="0">博士生导师</sf:option>
					<sf:option value="1">硕士生导师</sf:option>
					<sf:option value="2">教授</sf:option>
					<sf:option value="3">副教授</sf:option>
					<sf:option value="4">讲师</sf:option>
					<sf:option value="5">教师队伍</sf:option>
				</sf:select>
			
			</td>
		</tr>
		<tr>
			<td class="rightTd">办公室:</td><td class="leftTd"><sf:input path="bgs" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">电话:</td><td class="leftTd"><sf:input path="phone" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">E-Mail:</td><td class="leftTd">
			<sf:input path="email" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">教育背景:</td>
			<td class="leftTd">
			 <sf:textarea path="jybg" rows="25" cols="80"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">研究方向:</td><td class="leftTd">
			 <sf:textarea path="yjfx" rows="25" cols="80"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">课程教学:</td><td class="leftTd">
			 <sf:textarea path="kcjx" rows="25" cols="80"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">学术兼职:</td><td>
			<sf:textarea path="xsjz" rows="25" cols="80"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">科研项目:</td><td>
			<sf:textarea path="kyxm" rows="25" cols="80"/>
			</td>
		</tr>
		<tr>
			<td class="rightTd">论文著作:</td><td>
		     <sf:textarea path="lwzz" rows="25" cols="80"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd">
			<input type="submit" value="添加人员"/>
			<input type="reset"/>
			 <a href="javascript:history.go(-1);"><input type="button" value="返回"/></a>
			
			</td>
		</tr>
	</table>
	</sf:form>
</div>
</body>
</html>