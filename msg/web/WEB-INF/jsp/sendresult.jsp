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
    <title>提示</title>
      <link href="${pageContext.request.contextPath}/styles/css_message.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/WebCalendar.js"></script>
        <script language="javascript">
	  <!--
			function goBack()
			{
			  	window.location.href="${pageContext.request.contextPath}/sendsms.nsf?curUserID=${param.curUserID}";
			} 
	  //-->
	  </script>
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
		        <script>
		        	function tab(tabName){
						var url = "${pageContext.request.contextPath}/listshort.nsf";
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
				<table width="99%" border="0" cellspacing="0" cellpadding="0"
					class="tb_txl">
					<tr>
						<td height="450" align="center" valign="top"
							background="${pageContext.request.contextPath}/images/bg_02.gif"><!--提示信息 start-->
						<table hight width="300" border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF" class="tb_txl">
							<tr>
								<td align="center" valign="middle" class="msg_text">发送成功：1条<br>发送失败：0条
								<br>若短信发送有问题，请拨打4832或13893314000
								</td>
							</tr>
							<tr>
								<td height="50" align="center" valign="middle" onclick="goBack()"><img
									src="${pageContext.request.contextPath}/images/button_fh.gif" width="120" height="35" /></td>
							</tr>
						</table>
						<!--提示信息 end--></td>
					</tr>
				</table>
		<!-- 查询框 end -->
				</td>
			<!-- 信箱列表 end -->
		</tr>
	</table>
	</body>
</html>