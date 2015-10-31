<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div class="ss">
    <table width="100%" cellspacing="0" cellpadding="0" border="0">
        <tbody>
        <tr>
            <td width="66%">
                &nbsp;
            </td>
            <td width="7%" valign="middle">
             <span id="sz">全文搜索:</span>  
            </td>
            <td width="17%" valign="middle">
		          <input type="text" id="search_con" value="" name="search_con">
		    </td>
		     <td width="10%" valign="middle">
		      <input type="image" style="border-width:0px;" src="<%=request.getContextPath() %>/resources/images/ss-anniu.jpg" id="search_btn" name="search_btn">
		    </td>
        </tr>
      </tbody>
    </table>
</div>
     

