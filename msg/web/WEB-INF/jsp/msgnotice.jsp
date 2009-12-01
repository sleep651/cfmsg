<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
      <script src="${pageContext.request.contextPath}/js/jquery-latest.js"></script>
      <script src="${pageContext.request.contextPath}/js/msgnotice.js"></script>
	  <script language="javascript" type="text/javascript">
	 	function checkNewMsg(){
			var sendstr="username=${param.curUserID}";
		
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/smsstat_getjson.nsf",
				cache: false,
				data: sendstr,
				dataType: "json",
				success: function(jsonObj){
						var new_sysMsgCount = 0;
						var new_shortMsgCout = 0;
						var has_newmsg = false;
			
						 if(jsonObj==null)
						 {
							has_newmsg = false;
						 }
						 else//end of if(jsonObj==null)
						 {
							new_shortMsgCout = jsonObj.new_shortMsgCout;
							new_sysMsgCount = jsonObj.new_sysMsgCount;
							
							if(new_shortMsgCout>0 || new_sysMsgCount>0)
							{
								has_newmsg = true;
							}
						 }
						 if(has_newmsg)
						 {
						 	startNotice();
						 }
						 else
						 {
						 	stopNotice();
						 }
						 
					}//end of success: function
				});//end of $.ajax
	
			return false;
		}//end of function checkNewMsg			
		checkNewMsg();
		//每过5秒刷新一次
	    window.setInterval("checkNewMsg()",3000);
	</script>
