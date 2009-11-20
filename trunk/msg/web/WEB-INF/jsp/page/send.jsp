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
    <td width="682" height="249" valign="top"><p>&gt;&gt;发短消息：</p>
    <hr/>
	<form action="" method="post">
	<table width="800" border="1">
  <tr>
    <td>发送给：</td>
    <td><input name="receiver" type="text">
      <input type="submit" name="Submit" value="选择接收人"></td>
    </tr>
  <tr>
    <td>发送内容：</td>
    <td><textarea name="textarea" cols="40" rows="5"></textarea></td>
    </tr>
  <tr>
    <td><div align="right"></div></td>
    <td><div align="right">
      <input type="submit" name="Submit" value="发送">
    </div></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
</table>

	</form></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">bottom</div></td>
  </tr>
</table>
</body>
</html>