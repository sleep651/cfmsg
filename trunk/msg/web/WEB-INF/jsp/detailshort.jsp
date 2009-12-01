<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@page import="com.hirisun.msg.service.impl.MsgServiceImpl"%>
<%@page import="com.hirisun.msg.service.MsgService"%>
<%@page import="com.hirisun.msg.domain.ShortMessage"%>
<%@page import="nak.nsf.pager.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	System.out.println("curUserID="+request.getParameter("curUserID"));
	System.out.println("page="+request.getParameter("page"));
	System.out.println("pagesize="+request.getParameter("pagesize"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>短信查询</title>
      <link href="${pageContext.request.contextPath}/styles/css_message.css" rel="stylesheet" type="text/css" />
      <script src="${pageContext.request.contextPath}/js/jquery-latest.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath}/js/WebCalendar.js"></script>
  </head>
  <body>
	<table width="998" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<!-- 收发件箱切换  start -->
			<td class="bg_tab">
				<div class="tab_02">
					<a href="javascript:tab('inbox')" class="a_xw">收件箱</a>
				</div>
				<div class="tab_02">
					<a href="javascript:tab('sendbox')" class="a_xw">发件箱</a>
				</div>
				<div class="tab_03">
					<a href="${pageContext.request.contextPath}/sendsms.nsf?curUserID=${param.curUserID}" class="a_op">发送消息</a>
				</div>
			</td>
			<!-- 收发件箱切换  end -->
		</tr>
		<tr>
			<!-- 信箱列表 start -->
			<td class="bg_table">

				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="bg_tb">
							<table width="100%" border="1" bordercolorlight="#AECDC9" bordercolordark="#ffffff" cellpadding="0" cellspacing="0"
								bgcolor="#ffffff">
								<tr>
									<td width="40" align="left" class="tb1_title">
										序号
									</td>
									<td align="left" class="tb1_title" width="80px">发件人</td>
									<td align="left" class="tb1_title" width="80px">收件人</td>
									<td align="left" class="tb1_title" width="140px">发送时间</td>
									<td align="left" class="tb1_title">短信内容</td>
								</tr>
							<c:forEach items="${pageResult.list}" var="datalist" varStatus="cnt">
			              		<tr height="26px">
									<td width="40" align="center" valign="middle">${cnt.count+(pageResult.pager.currentPage-1)*pageResult.pager.pageSize }&nbsp;</td>									
									<td>${datalist.senduserid}&nbsp;</td>
									<td>${datalist.receiveuserid}&nbsp;</td>
									<td><fmt:formatDate value="${datalist.sendtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;</td>
									
									<td><span> ${datalist.content}&nbsp;</span></td>
								 </tr>
							</c:forEach>
							
							<c:if test="${empty pageResult.list}">
							<tr>
							    <td height="40" colspan="6" align="center">暂时没有数据！</td>
							</tr>
							</c:if>
							</table>
							
				<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#c1dcff">
				  <tr>
				    <td align="left" class="biaoti1">共${pageResult.pager.quantityOfData }条信息  计${pageResult.pager.lastPage }页  当前第${pageResult.pager.currentPage }页</td>
				    <td align="left" class="biaoti1">
				        <script>
				        	document.write("选择跳转到<select name='select' onchange='javascript:page(this.value)'>");
				        		for(var i=1;i<=${pageResult.pager.lastPage};i++){
				        			if(${pageResult.pager.currentPage} == i){
				        				document.write("<option value='"+i+"' selected='selected'>第"+i+"页</option>");
				        			}else{
										document.write("<option value='"+i+"'>第"+i+"页</option>");
									}
				        		}
				        	document.write("</select>");
				        </script>
				        </td>
				        <td align="right">
						<c:if test="${!pageResult.pager.hasPreviousPage}">
							<a href="#" onclick="alert('已经是第一页！');"> 
						   		首页
						   </a>
						   &nbsp;
						   <a href="#" onclick="alert('已经是第一页！');"> 
						   		前一页
						   </a>
						</c:if>
						<c:if test="${pageResult.pager.hasPreviousPage}">
							<a href="javascript:page(1)"> 
						   		首页
						   </a>
						   &nbsp;
						   <a href="javascript:page(${pageResult.pager.currentPage-1 })">
					   			前一页
					   	   </a>
						</c:if>
					   <c:if test="${!pageResult.pager.hasNextPage}">
						   	 &nbsp;
						   	 <a href="#" onclick="alert('已经是最末页！');">
							    后一页
							 </a>
							 &nbsp;
							<a href="#" onclick="alert('已经是最末页！');">
							    末页
							</a>
					   </c:if>
					   <c:if test="${pageResult.pager.hasNextPage}">
						   &nbsp;
						   <a href="javascript:page(${pageResult.pager.currentPage+1 })">
							    后一页
							 </a>
							 &nbsp;
							<a href="javascript:page(${pageResult.pager.lastPage })">
							    末页
							</a>
						</c:if>
				   </td>
				  </tr>
				</table>
							
						</td>
					</tr>
				</table>
				</td>
			<!-- 信箱列表 end -->
		</tr>
		<tr>
			<!-- 信箱列表 start -->
			<td class="bg_table">
			<!-- 查询框 start -->
					<form id="sendForm" method="post" name="sendForm" action="${pageContext.request.contextPath}/senddo.nsf">
					<input type="hidden" name="curUserID" value="${param.curUserID}" />
					<input type="hidden" name="senduserid" value="${param.curUserID}" />
					<input type="hidden" name="parentid" value="${parentid}" />
					<input type="hidden" name="receiveuserid" value="${receiveuserid}" />
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
	<form name="commonForm" action="" method="post">
		<input type="hidden" name="key" value="${param.key}" />
		<input type="hidden" name="startTime" value="${param.startTime}" />
		<input type="hidden" name="endTime" value="${param.endTime}" />
        <input type="hidden" name="pagesize" value="${param.pagesize}" />
        <input type="hidden" name="page" value="${param.page}" />
        <input type="hidden" name="curUserID" value="${param.curUserID}" />
        <input type="hidden" name="msgType" value="${param.msgType}" />
        <input type="hidden" name="msgID" value="" />			
	</form>
    <script>
		function send()
		{
			document.sendForm.submit();
		}
     	function page(i){
			var url = "${pageContext.request.contextPath}/detailshort.nsf";
			document.commonForm.page.value = i;
			document.commonForm.action=url;
			document.commonForm.submit();
     	}
		
       	function tab(tabName){
			var url = "${pageContext.request.contextPath}/listshort.nsf";
			document.commonForm.msgType.value = tabName;
			document.commonForm.pagesize.value = 10;
			document.commonForm.page.value = 1;
			document.commonForm.action=url;
			document.commonForm.submit();
       	}
   	</script>
	</body>
</html>