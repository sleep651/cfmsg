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
			  <c:choose>
			        <c:when test="${param.msgType == 'sendbox'}">
						<div class="tab_02">
							<a href="javascript:tab('inbox')" class="a_xw">收件箱</a>
						</div>
						<div class="tab_01">
							<a href="javascript:tab('sendbox')" class="a_xw">发件箱</a>
						</div>
						<div class="tab_03">
							<a href="${pageContext.request.contextPath}/admin/sendsms.nsf?curUserID=${param.curUserID}" class="a_op">发送消息</a>
						</div>						
			        </c:when>
			        <c:otherwise>
						<div class="tab_01">
							<a href="javascript:tab('inbox')" class="a_xw">收件箱</a>
						</div>
						<div class="tab_02">
							<a href="javascript:tab('sendbox')" class="a_xw">发件箱</a>
						</div>						
						<div class="tab_03">
							<a href="${pageContext.request.contextPath}/admin/sendsms.nsf?curUserID=${param.curUserID}" class="a_op">发送消息</a>
						</div>
			        </c:otherwise>
			   </c:choose>
			</td>
			<!-- 收发件箱切换  end -->
		</tr>
		<tr>
			<!-- 信箱列表 start -->
			<td class="bg_table">
				<!-- 查询框 start -->
				
		        <table width="98%" border="0" cellspacing="0" cellpadding="0">
		         <tr>
		           <td class="bg_search">
		           
		           <img src="${pageContext.request.contextPath}/images/p_fdj.gif" width="50" height="45" align="absmiddle" />日期范围：
		             <input type="text" id="startTime" value="${param.startTime}" readonly class="input_date"
		             onClick="SelectDate(this,'yyyy-MM-dd')"/>
		             至
		             <input type="text" id="endTime" value="${param.endTime}" readonly class="input_date"
		             onClick="SelectDate(this,'yyyy-MM-dd')"/>
		             &nbsp;&nbsp;包含文字：
		             <input type="text" id="key" value="${param.key}"/>
		             &nbsp;&nbsp;&nbsp;
		             <a href="#" onclick="onquery()"><img src="${pageContext.request.contextPath}/images/button_cx.gif" width="70" height="45" align="absmiddle" border="0"/></a>
		             </td>
		         </tr>
		        </table>
		        
				<!-- 查询框 end -->
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
									<c:if test="${param.msgType == 'inbox'}">
									<td align="left" class="tb1_title" width="70px">状态</td>
									</c:if>
									<td align="left" class="tb1_title" width="70px">操作</td>
								</tr>
							<c:forEach items="${pageResult.list}" var="datalist" varStatus="cnt">

							<c:if test="${param.msgType == 'inbox'}">
								<c:choose>
									<c:when test="${datalist.isread == true}">
					              		<tr height="26px">
											<td width="40" align="center" valign="middle">${cnt.count+(pageResult.pager.currentPage-1)*pageResult.pager.pageSize }&nbsp;</td>									
											<td>${datalist.senduserid}&nbsp;</td>
											<td>${datalist.receiveuserid}&nbsp;</td>
											<td><fmt:formatDate value="${datalist.sendtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;</td>
											<td><span> ${datalist.content}&nbsp;</span></td>
											<td>已读</td>
											<td>
												<a href="#" 
												onclick="detail('${datalist.id}')">
													查看
												</a>
											</td>
										 </tr>
									</c:when>
									<c:otherwise>
					              		<tr height="26px">
											<td style="font-weight:bold;color: #D32C47;" width="40" align="center" valign="middle">${cnt.count+(pageResult.pager.currentPage-1)*pageResult.pager.pageSize }&nbsp;</td>									
											<td style="font-weight:bold;color: #D32C47;">${datalist.senduserid}&nbsp;</td>
											<td style="font-weight:bold;color: #D32C47;">${datalist.receiveuserid}&nbsp;</td>
											<td style="font-weight:bold;color: #D32C47;"><fmt:formatDate value="${datalist.sendtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;</td>
											<td style="font-weight:bold;color: #D32C47;"><span> ${datalist.content}&nbsp;</span></td>
											<td style="font-weight:bold;color: #D32C47;">未读</td>
											<td style="font-weight:bold;color: #D32C47;">
												<a href="#" 
												onclick="detail('${datalist.id}')">
													查看
												</a>
											</td>
										 </tr>
									</c:otherwise>
								</c:choose>
							</c:if>									

							<c:if test="${param.msgType == 'sendbox'}">
					              		<tr height="26px">
											<td width="40" align="center" valign="middle">${cnt.count+(pageResult.pager.currentPage-1)*pageResult.pager.pageSize }&nbsp;</td>									
											<td>${datalist.senduserid}&nbsp;</td>
											<td>${datalist.receiveuserid}&nbsp;</td>
											<td><fmt:formatDate value="${datalist.sendtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;</td>
											<td><span> ${datalist.content}&nbsp;</span></td>
											<td>
												<a href="#" 
												onclick="detail('${datalist.id}')">
													查看
												</a>
											</td>
										 </tr>
							</c:if>	
								
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
	</table>
	<form name="commonForm" id="commonForm" action="" method="post">
		<input type="hidden" name="key" value="${param.key}" />
		<input type="hidden" name="startTime" value="${param.startTime}" />
		<input type="hidden" name="endTime" value="${param.endTime}" />
        <input type="hidden" name="pagesize" value="${param.pagesize}" />
        <input type="hidden" name="page" value="${param.page}" />
        <input type="hidden" name="curUserID" value="${param.curUserID}" />
        <input type="hidden" name="msgType" value="${param.msgType}" />
        <input type="hidden" name="msgID" value="" />			
	</form>
    <script><!--
     	function page(i){
			var url = "${pageContext.request.contextPath}/admin/listsys.nsf";
			document.commonForm.page.value = i;
			document.commonForm.action=url;
			document.commonForm.submit();
     	}
		
		function detail(msgID){
			var url = "${pageContext.request.contextPath}/admin/detailsys.nsf";
			document.commonForm.msgID.value = msgID;
			document.commonForm.pagesize.value = 10;
			document.commonForm.page.value = 1;
			document.commonForm.action=url;
			document.commonForm.submit();
       	}
       	
       	function tab(tabName){
			var url = "${pageContext.request.contextPath}/admin/listsys.nsf";
			document.commonForm.msgType.value = tabName;
			document.commonForm.pagesize.value = 10;
			document.commonForm.page.value = 1;
			document.commonForm.action=url;
			document.commonForm.submit();
       	}
		function onquery()
		{
			var url = "${pageContext.request.contextPath}/admin/listsys.nsf";
			document.commonForm.startTime.value = $('#startTime').val();
			document.commonForm.endTime.value = $('#endTime').val();
			document.commonForm.key.value = $('#key').val();
			document.commonForm.pagesize.value = 10;
			document.commonForm.page.value = 1;
			document.commonForm.submit();
		} 
   	</script>
	</body>
</html>