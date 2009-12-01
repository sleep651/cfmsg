<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>短信息</title>
      <link href="${pageContext.request.contextPath}/styles/message.css" rel="stylesheet" type="text/css" />
      <script src="${pageContext.request.contextPath}/js/jquery-latest.js"></script>
      	<script type="text/javascript"> 
      	//特殊处理，隐藏打开窗口的url参数，并且同时只能打开一个窗口显示详情
		function onqueryShort()
		{
			window.open('about:blank', 'sms_detail');
			document.conditionForm.msgType.value = "inbox";
			document.conditionForm.target="sms_detail";
			document.conditionForm.action='${pageContext.request.contextPath}/listshort.nsf';
		  	document.conditionForm.submit();
		}
		
		function onquerySys()
		{
			window.open('about:blank', 'sms_detail');
			document.conditionForm.msgType.value = "inbox";
			document.conditionForm.target="sms_detail";
			document.conditionForm.action='${pageContext.request.contextPath}/listsys.nsf';
		  	document.conditionForm.submit();
		} 
	</script>
	
	<script language="javascript" type="text/javascript">

 	function topicContentList(){
 	
		var sendstr="username=${param.curUserID}";
	
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/smsstat_getjson.nsf",
			cache: false,
			data: sendstr,
			dataType: "json",
			success: function(jsonObj){
					var pagecontent = "";

					var new_sysMsgCount = 0;
					var new_shortMsgCout = 0;
		
					 if(jsonObj==null)
					 {
						//转向报错页面
						//window.location.href="testError.jsp";
						//document.getElementById('div_content').style.display="block";
						//document.getElementById('div_content').style.display="none";
						
						//$('#element');// 相当于document.getElementById("element")
						//$("#id").show()表示display:block,
						//$("#id").hide()表示display:none;

						$('#div_init_content').hide();
						$('#div_content').hide();
						$('#div_error_content').show();
						
					 }
					 else//end of if(jsonObj==null)
					 {
						new_shortMsgCout = jsonObj.new_shortMsgCout;
						new_sysMsgCount = jsonObj.new_sysMsgCount;
						
						$('#div_init_content').hide();
						$('#div_error_content').hide();
						$('#div_content').show();
					 }
					 
					document.getElementById('span_new_shortMsgCout').innerHTML = new_shortMsgCout;
					document.getElementById('span_new_sysMsgCount').innerHTML = new_sysMsgCount;

					
				}//end of success: function
			});//end of $.ajax

		return false;
		
	}//end of function topicContentList			
</SCRIPT>
<jsp:include flush="true" page="msgnotice.jsp"></jsp:include>
  </head>
  
  <body>
		<table width="220" height="112" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="message_title">&nbsp; 
				</td>
			</tr>
			<tr>
				<td class="message_body">
					<div id="div_init_content">
						<table width="90%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left"  style="padding-top:15px;">
									<span style="margin-left:30px;font-family: '宋体';font-size: 12px;color: #333333;">正在读取信息，请稍等...</span>
								</td>
							</tr>
						</table>
					</div>
					<div id="div_content"  style="display:none;">
						<table width="90%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left">
									<span class="message_info_title">短消息：</span><a href="#" onclick="onqueryShort()" 
										class="a_message">总计<span id="span_new_shortMsgCout">?</span>条</a>
								</td>
							</tr>
							<tr>
								<td class="message_info">
								</td>
							</tr>
						</table>
						<table width="90%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left">
									<span class="message_info_title">系统消息：</span><a href="#" onclick="onquerySys()"
										 class="a_message">总计<span id="span_new_sysMsgCount">?</span>条</a>
								</td>
							</tr>							
							<tr>
								<td class="message_info">
								</td>
							</tr>
						</table>
					</div>
					<div id="div_error_content" style="display:none;">
						<table width="90%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left">
									<span class="message_info_title" style="color: #ff3333;">短信平台出现异常！</span>
								</td>
							</tr>
							<tr>
								<td align="left"  style="padding-top:15px;">
									<span style="margin-left:26px;font-family: '宋体';font-size: 12px;color: #333333;">请稍后重试，若仍有问题，请拨打4832或13893314000</span>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	<form name="conditionForm" method="post">
           <input type="hidden" name="pagesize" value="10" />
           <input type="hidden" name="page" value="1" />
           <input type="hidden" name="curUserID" value="${param.curUserID}" />
           <input type="hidden" name="msgType" value="sendbox" />
	</form>		
	</body>
	<script type="text/javascript">
    	 topicContentList();
	</script>
	<script type="text/javascript"> 
	//每过30分钟刷新一次
	    window.setInterval("refreshSmsStat()",1800000);
		 function refreshSmsStat() 
		{ 
			//window.location.reload();
			topicContentList();
		} 
	</script>
</html>
