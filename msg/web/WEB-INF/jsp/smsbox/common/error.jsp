<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%
if (request.getCharacterEncoding()==null)
	request.setCharacterEncoding("gb2312");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msgType = request.getParameter("msgType");
String msg1 = "�û�û�е�½";
String msg2 = "�û�û��Ȩ��";
String msg = "";
if(msgType==null)
{
	msg = "��û�в�����Ȩ��";
}
else
{
if(msgType.equals("1"))
   msg = msg1;
else if(msgType.equals("2"))
   msg = msg2;   
}


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>����</title>
</head>
  <body>
    <%= msg %> <br>
  </body>
</html>
