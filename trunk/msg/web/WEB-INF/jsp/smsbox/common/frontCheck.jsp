<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
   String curUserID = (String)session.getAttribute("curUserID");
   
   String portalUID = request.getParameter("PUID");
   
   if(portalUID==null || portalUID.equals("") || portalUID.equals("null"))
   {
   		portalUID = request.getHeader("X-iv-user");
   }
   //portalUID为空
   if(portalUID==null || portalUID.equals("") || portalUID.equals("null"))
   {
   		//portalUID不为空，且curUserID也不为空
   	   if(curUserID!=null && !curUserID.equals("") && !curUserID.equals("null"))
	   {
		   portalUID = curUserID;
	   }
	   else
	   {
   		   if(portalUID==null || portalUID.equals("") || portalUID.equals("null"))
		   {
		   		response.sendRedirect(request.getContextPath() + "/smsbox/common/error.jsp?msgType=1");
		      	return;
		   }
	   }
   }
   else//portalUID不为空，且curUserID为空
   {
   		//设置当前登录用户
	   session.setAttribute("curUserID",portalUID);
	   curUserID = (String)session.getAttribute("curUserID");
   }
   //System.out.println("111----------curUserID="+curUserID+",portalUID="+portalUID);
%>

