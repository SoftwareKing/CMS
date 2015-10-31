<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="MyNewpics">
	<div id="slider" class="nivoSlider">
		<#list newpics as newpic>
			 <a href="topic/${newpic.topic.id }" class="nivo-imageLink">
					<img src="<%=request.getContextPath()%>/resources/upload/Newthumbnail/${newpic.newName}" alt=""
					 title="${newpic.topic.title }" width="271" height="259">
			</a>
	 </#list>
 </div>						
</div>
