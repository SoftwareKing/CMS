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
    <div class="left">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
          <tr>
            <td width="9%"><img src="<%=request.getContextPath() %>/resources/images/tb.jpg" width="8" height="8" /></td>

            <td width="91%"><a href="<%=request.getContextPath() %>/teacher/1">硕士生导师</a>
          </tr>

          <tr>
            <td width="9%"><img src="<%=request.getContextPath() %>/resources/images/tb.jpg" width="8" height="8" /></td>

            <td width="91%"><a href="<%=request.getContextPath() %>/teacher/2">教授</a></td>
          </tr>

          <tr>
            <td width="9%"><img src="<%=request.getContextPath() %>/resources/images/tb.jpg" width="8" height="8" /></td>

            <td width="91%"><a href="<%=request.getContextPath() %>/teacher/3">副教授</a></td>
          </tr>

          <tr>
            <td width="9%"><img src="<%=request.getContextPath() %>/resources/images/tb.jpg" width="8" height="8" /></td>

            <td width="91%"><a href="<%=request.getContextPath() %>/teacher/4">讲师</a></td>
          </tr>
          <tr>
            <td width="9%"><img src="<%=request.getContextPath() %>/resources/images/tb.jpg" width="8" height="8" /></td>

            <td width="91%"><a href="<%=request.getContextPath() %>/teacher/5">教师队伍</a></td>
          </tr>

         </tbody>
      </table>
    </div>

    <div class="right">
      <div class="right_bt">
        <div>
                       齐齐哈尔大学 计算机与控制工程学院各系师资队伍
        </div>

        <div class="clear"></div>
      </div>

      <div class="right_nr">
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="ta">
          <tbody>
            <tr>
              <td colspan="5">
                <input type="hidden" name="rptXI$ctl00$hdvalue" id="rptXI_ctl00_hdvalue" value="电子工程系" /><input type="hidden" name="rptXI$ctl01$hdvalue" id="rptXI_ctl01_hdvalue" value="光科学与工程系" /><input type="hidden" name="rptXI$ctl02$hdvalue" id="rptXI_ctl02_hdvalue" value="光源与照明工程系" /><input type="hidden" name="rptXI$ctl03$hdvalue" id="rptXI_ctl03_hdvalue" value="通信科学与工程系" /><input type="hidden" name="rptXI$ctl04$hdvalue" id="rptXI_ctl04_hdvalue" value="微电子学系" /><input type="hidden" name="rptXI$ctl05$hdvalue" id="rptXI_ctl05_hdvalue" value="微电子研究院" /><input type="hidden" name="rptXI$ctl06$hdvalue" id="rptXI_ctl06_hdvalue" value="院部" />

                <table border="0" cellpadding="0" cellspacing="0" width="76%">
                  <tbody>
                   <c:forEach items="${alld}" varStatus="st" var="att">
                    <tr>
                      <td colspan="5" class="ta2">${att.name}</td>
                    </tr>

                    <tr>
                      <td style="font-size: 14px;">
                        <table id="rptXI_ctl00_dlPeople" cellspacing="0" border="0" style="border-collapse:collapse;">
                          <tbody>
                            <tr>
                              <c:forEach items="${dplist[att.id]}" varStatus="peo" var="people">
                              <td><a href="<%=request.getContextPath() %>/teachershow/${people.id}" style="padding-right: 5px">${people.name}</a></td>

                             </c:forEach>
                            </tr>
                          </tbody>
                        </table>
                      </td>
                    </tr>
              </c:forEach>
                   
                  </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="clear"></div>
  </div>
     
     <jsp:include page="/WEB-INF/jsp/index/foot.jsp"></jsp:include>
  </div>
  </div>
  </div>

</body>
</html>


