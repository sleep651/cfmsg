<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息首页</title>
</head>
<body>
<table width="863" border="1" bgcolor="#ABBCE9">
  <tr >
    <td height="90" colspan="2"><p align="center">top</p>
    </td>
  </tr>
  <tr>
    <td colspan="2">当前位置：首页&gt;&gt;短信息</td>
  </tr>
  <tr>
    <td width="165" height="249" valign="top"><form name="form1" method="post" action="">
      <input type="button" name="Submit" value="发短消息" onClick="window.location.href='<%=request.getContextPath() %>/page/send.nsf';">
	  <br/>
	  <input type="button" name="Submit" value="发系统消息" onClick="window.location.href='<%=request.getContextPath() %>/page/sendsys.nsf'">
    </form>      
      <p><a href="我收到的消息.htm" target="_self">我收到的消息</a></p>
      <p>系统消息</p>
    <p>我发送的消息</p></td>
    <td width="682" height="249">显示列表内容（首次进入，默认显示“我收到的消息”列表）</td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">bottom</div></td>
  </tr>
</table>
</body>
</html>