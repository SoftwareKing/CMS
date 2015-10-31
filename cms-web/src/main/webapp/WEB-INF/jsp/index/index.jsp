<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<script src="<%=request.getContextPath() %>/resources/js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
		<link href="<%=request.getContextPath() %>/resources/css/index/top/top.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/css/index/foot/foot.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/resources/css/index/newpics.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			jQuery(function($){
			$(window).load(function() {
			  $('#slider').nivoSlider({
			    directionNav: true,
			    captionOpacity: 0.4,
			    controlNav: true,
			    boxCols: 8,
			    boxRows: 4,
			    slices: 15,
			    effect:'random',
			    animSpeed: 500,
			    pauseTime: 3000 });
			  });
			});
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
            <div class="content_1">
                
               <jsp:include page="newpic.jsp"></jsp:include>
                
               <jsp:include page="body.jsp"></jsp:include>
              </div>
     </div>
     <jsp:include page="foot.jsp"></jsp:include>
  </div>
  </div>
  </div>

</body>
</html>


