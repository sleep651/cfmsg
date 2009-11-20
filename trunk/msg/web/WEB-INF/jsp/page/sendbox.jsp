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
    <td width="682" height="249" valign="top">
	    <p>&gt;&gt;我收到的消息：</p>
	    <hr/>	
		<table width="800" border="1">
  <tr>
    <td><strong>时间</strong></td>
    <td><strong>发送者</strong></td>
    <td><strong>接收者</strong></td>
    <td><strong>内容</strong></td>
  </tr>
  <tr>
    <td>2009-11-01 11:00</td>
    <td>风雨者</td>
    <td>浪子</td>
    <td>最近忙不忙啊？</td>
  </tr>
  <tr>
    <td>2009-10-31 14:00</td>
    <td>桌子</td>
    <td>浪子</td>
    <td>干啥呢？</td>
  </tr>
  <tr>
    <td>2009-10-30 16:00</td>
    <td>唐僧</td>
    <td>浪子</td>
    <td>再玩天下贰着没</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">bottom</div></td>
  </tr>
</table>
</body>
</html>