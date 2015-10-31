<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/login.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript">
$(function(){
	$("#myForm").cmsvalidate();
});
</script>
<title>后台管理登录</title>
<script type="text/javascript">
function reCheckcode(img) {
	img.src="drawCheckCode?"+Math.random();
}

</script>
 <script type="text/javascript">
		$(function(){
			document.forms[0].username.focus();
		});
	</script>
</head>
<body>
	<div id="container">
		<div id="top">
		
		</div>
		<div id="loginBar">
			<span id="showDate">欢迎你使用齐齐哈尔大学计算机与控制工程学院网站后台管理程序，请登录</span>
		</div>
		<div id="content">
		
			<div id="loginForm">
			<form  name="form" method="post" id="myForm" action="">
					<table cellpadding="0" cellspacing="0" width="380px" id="loginTable">
						<tr height="30">
							<td CLASS="td1" align="right" width="90" >用户名:</td>
							<td align="left"><input tableindex="1" class="userName" type="text" name="username"  maxlength="19" onfocus="inputFocus(this)" onblur="inputBlur(this)"/> </td>
						</tr>
						<tr height="30">
							<td class="td1" align="right">密　码:</td>
							<td align="left"><input tableindex="2" class="password" type="password" name="password" maxlength="16" onfocus="inputFocus(this)" onblur="inputBlur(this)"/></td>
						</tr>
						<tr height="30">
							<td class="td1" align="right">验证码:</td>
							<td align="left">
							<input tableindex="3"  class="validate" type="text" name="checkcode" id="validateCode" maxlength="5" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
							${error} 
							<img style="cursor:pointer;" id="Verify"  alt="点击图片修改验证码"  src="drawCheckCode" onclick="reCheckcode(this)"/>
							</td>
						</tr>
						<tr height="30">
							<td align="center" colspan="2">
							<input tableindex="4" class="submit" value="" type="submit" />
                           &nbsp;&nbsp;&nbsp;&nbsp;
                           <input tableindex="5" class="reset" value="" type="reset" />
							</td>
							
							
						</tr>
					</table>
					</form>
			</div>
			
		</div>
	</div>	
</body>
</html>