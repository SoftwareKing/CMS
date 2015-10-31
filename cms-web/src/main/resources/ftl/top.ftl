<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="top">
    <table width="100%" cellspacing="0" cellpadding="0" border="0">
        <tbody><tr>
            <td width="75%" height="51">
                &nbsp;
            </td>
            <td width="9%">
                &nbsp;
            </td>
            <td width="8%">
                &nbsp;
            </td>
            <td width="8%">
                &nbsp;
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;
            </td>
            <td>
                <a href="<%=request.getContextPath() %>/">返回首页</a>
				 &nbsp;<span style="color:#FFFF00;">/</span>
            </td>
            <td>
               <a href="http://www.qqhru.edu.cn">学校首页</a>
			    &nbsp;<span style="color:#FFFF00;">/</span>
            </td>
            <td>
              
                <a href="<%=request.getContextPath() %>/">学院首页</a>
            
            </td>
        </tr>
    </tbody></table>
</div>
<div class="menu">
	        <ul id="jsddm">
					<#list menus as mune>
				      <li>
				    <#if mune.customLink==0>
				       <a href="<%=request.getContextPath()%>/channel/${mune.id}">${mune.name}</a>
				    <#else>  
				        <a href="${mune.customLinkUrl}">${mune.name}</a>
				     </#if>	
					       <#---->
					         <ul>
					          <#list mune.children as lc>
						        <li>
						           <#if lc.customLink==0>
					                 <a href="<%=request.getContextPath()%>/channel/${lc.id}">${lc.name}</a>
					               <#else>  
					                   <a href="${lc.customLinkUrl}">${lc.name}</a>
					               </#if>	
						        </li>
						      </#list>
									
					         </ul>
				     </li>
				  </#list>    
				     
		           

		           
		     </ul>

 
 </div>