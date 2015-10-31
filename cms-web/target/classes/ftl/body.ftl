<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <div class="zxdt">
                    <div class="bt">
                        <div class="zi_zw">
                  ${ts["1"].cname}</div>
                        <div class="zi_yw">
                            | News</div>
                        <div class="zi_gd">
                            <a href="channel/${ts['1'].cid}">更多&gt;&gt;</a></div>
                        <div class="clear">
                        </div>
                    </div>
                    <div class="nr">
                        <table width="100%" cellspacing="0" cellpadding="0" border="0">
                            
                                    <tbody>
                                   
                                    <#list ts["1"].topics as topic>
                                    <tr>
                                        <td width="4%">
                                            <img width="8" height="8" src="<%=request.getContextPath() %>/resources/images/tb.jpg">
                                        </td>
                                        <td width="96%">
                                         <a href="topic/${topic.id}">
                                          <#if topic.title?length gt 28>
							                 ${topic.title[0..28]}...
						                 <#else>
							                 ${topic.title}
						                   </#if>
                                          </a>
                                        </td>
                                    </tr>
                                   </#list>
                                   
                        </tbody></table>
                    </div>
                </div>
                
                
                <div class="xsxx">
                    <div class="bt">
                        <div class="zi_zw">
                      ${ts["2"].cname}</div>
                        <div class="zi_yw">
                            | Announcement</div>
                        <div class="zi_gd">
                            <a href="channel/${ts['2'].cid}">更多&gt;&gt;</a></div>
                        <div class="clear">
                        </div>
                    </div>
                    <div class="nr">
                        <table width="100%" cellspacing="0" cellpadding="0" border="0">
                            
                                   <tbody>
                                    <#list ts["2"].topics as topic>
                                    <tr>
                                        <td width="4%">
                                            <img width="8" height="8" src="<%=request.getContextPath() %>/resources/images/tb.jpg">
                                        </td>
                                        <td width="96%">
                                         <a href="topic/${topic.id}">
                                          <#if topic.title?length gt 24>
							                 ${topic.title[0..24]}...
						                 <#else>
							                 ${topic.title}
						                   </#if>
                                          </a>
                                        </td>
                                    </tr>
                                   </#list>
                                   
                        </tbody></table>
                    </div>
                </div>
                <div class="clear">
     </div>