<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@page import="com.hirisun.msg.service.impl.MsgServiceImpl"%>
<%@page import="com.hirisun.msg.service.MsgService"%>
<%@page import="com.hirisun.msg.domain.ShortMessage"%>
<%@page import="nak.nsf.pager.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>短信查询</title>
      <link href="${pageContext.request.contextPath}/styles/css_message.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/WebCalendar.js"></script>
        <script language="javascript">
	  <!--
			function onquery()
			{
			  	document.conditionForm.submit();
			}
			
			function send()
			{
				document.sendForm.submit();
			}
	  //-->
	  </script>
  </head>
  <body>
	<table width="998" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<!-- 收发件箱切换  start -->
			<td class="bg_tab">
			  <c:choose>
			        <c:when test="${param.msgType == 'sendbox'}">
						<div class="tab_01">
							<a href="javascript:tab('sendbox')" class="a_xw">发件箱</a>
						</div>
						<div class="tab_02">
							<a href="javascript:tab('inbox')" class="a_xw">收件箱</a>
						</div>
			        </c:when>
			        <c:otherwise>
						<div class="tab_02">
							<a href="javascript:tab('sendbox')" class="a_xw">发件箱</a>
						</div>
						<div class="tab_01">
							<a href="javascript:tab('inbox')" class="a_xw">收件箱</a>
						</div>
			        </c:otherwise>
			   </c:choose>
		        <script>
		        	function tab(tabName){
						var url = "${pageContext.request.contextPath}/smsbox/main.nsf";
						document.tabForm.msgType.value = tabName;
						document.tabForm.action=url;
						document.tabForm.submit();
		        	}
		        </script>
				<form name="tabForm" action="" method="post">
		            <input type="hidden" name="pagesize" value="10" />
		            <input type="hidden" name="page" value="1" />
		            <input type="hidden" name="curUserID" value="${param.curUserID}" />
		            <input type="hidden" name="msgType" value="${param.msgType}" />		
				</form>
			</td>
			<!-- 收发件箱切换  end -->
		</tr>
		<tr>
			<!-- 信箱列表 start -->
			<td class="bg_table">
			<!-- 查询框 start -->
					<form id="sendForm" method="post" name="sendForm" action="${pageContext.request.contextPath}/smsbox/senddo.nsf">
					<input type="hidden" name="curUserID" value="${param.curUserID}" />
					<input type="hidden" name="senduserid" value="${param.curUserID}" />
					<input type="hidden" name="parentid" value="null" />
					<input type="hidden" name="type" value="10" />
					<input type="hidden" name="reply" value="true" />
					
					<table border=0 cellspacing=0 cellpadding=0 width=680>
						<tbody>
							<tr>
								<td class=bg_title><img src="${pageContext.request.contextPath}/images/p_dxzxfs.gif"
									width=194 height=30></td>
							</tr>
							<tr>
								<td>
								<table border=0 cellspacing=0 cellpadding=0 width="100%">
									<tbody>
										<tr>
											<td valign=top background=${pageContext.request.contextPath}/images/bg_02.gif align=middle>
											<table border=0 cellspacing=0 cellpadding=0 width="100%">
												<tbody>
													<tr>
														<td class=font_note height=30 valign=bottom colspan=2
															align=left>&nbsp;&nbsp;请输入收信人姓名或号码并以半角逗号分隔</td>
													</tr>
													<tr>
														<td height=30 width=60 align=right>收信人：</td>
														<td height=30 valign=bottom align=left>
														<table border=0>
															<tbody>
																<tr>
																	<td><input id=target size=43 name="receiveuserid"><script
																		type=text/javascript>
																			if(window.dialogArguments != null)
																				document.getElementById('target').value = window.dialogArguments;
																		</script></td>
																	<td><img onclick=addtarget() border=0
																		src="${pageContext.request.contextPath}/images/button_xzry.gif" width=123 height=22></td>
																</tr>
															</tbody>
														</table>
														</td>
													</tr>
													<tr>
														<td height=30 width=60 align=right>内容：</td>
														<td height=30 align=left><textarea rows=10 cols=59
															name="content" styleclass="input_fsyj_02"></textarea></td>
													</tr>
													<tr>
														<td height=20 align=right>&nbsp;<span id=showid></span>
														</td>
														<td class=font_note height=20 align=left>&nbsp;</td>
													</tr>
													<tr>
														<td height=30 width=60 align=right>发送人：</td>
														<td height=30 align=left><input size=60
															name="showsenduserid"></td>
													</tr>
												</tbody>
											</table>
											</td>
										</tr>
									</tbody>
								</table>
								</td>
							</tr>
							<tr>
								<td class=bg_bottom_line height=60 colspan=2><a
									onclick="return send();"
									href="#"><img
									border=0 src="${pageContext.request.contextPath}/images/button_fs_big.gif" width=120 height=35></a></td>
							</tr>
						</tbody>
					</table>
					</form>
			<!-- 查询框 end -->
				</td>
			<!-- 信箱列表 end -->
		</tr>
	</table>
	</body>
</html>