<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/site_media/css/base/info.css">


  </head>
  
  <body>
    	<!-- 滚动条 -->
        <marquee id="ACMeOJ-marquee_message" onMouseOver="this.stop();" onMouseOut="this.start();" behavior="alternate" scrollamount="3">
        	${baseInfo.welcomeInfo}
        </marquee> 
  </body>
</html>
