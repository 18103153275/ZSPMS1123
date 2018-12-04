<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Date"%>
<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title></title>
<link href="images/skin.css" rel="stylesheet" type="text/css" />

</head>
<body leftmargin="0" topmargin="0">
	<table width="100%" height="64" border="0" cellpadding="0"
		cellspacing="0" class="admin_topbg">
		<tr>
			<td width="61%" height="64" valign="top"><img
				src="images/logo4.png" width="176" height="54"></td>
			<td width="39%" valign="top"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<td width="54%" height="38" class="admin_txt"><b>${userBean.realname
								}</b> &nbsp;&nbsp;&nbsp;&nbsp; <%
 	if (new Date().getHours() >= 0 && new Date().getHours() <= 12) {
 		//看看当前时间是在0点到中午12点之间
 %> 上午好 <%
 	} else {
 %> 下午好 <%
 	}
 %>&nbsp;&nbsp; <%
 	//设置刷新页面的时间，第隔1秒钟刷新一次 
 	response.setHeader("refresh", "1");
 %> <%
 	//输出当前最新的时间

 	Date thisDay = new Date();

 	out.print((thisDay.getYear() + 1900) + "年");

 	out.print((thisDay.getMonth() + 1) + "月");

 	out.print(thisDay.getDate() + "日");

 	out.print(thisDay.getHours() + "时");

 	out.print(thisDay.getMinutes() + "分");

 	out.print(thisDay.getSeconds() + "秒");

 	out.print("星期");

 	switch (thisDay.getDay())

 	{

 	case 0:

 		out.print("日");

 		break;

 	case 1:

 		out.print("一");

 		break;

 	case 2:

 		out.print("二");

 		break;

 	case 3:

 		out.print("三");

 		break;

 	case 4:

 		out.print("四");

 		break;

 	case 5:

 		out.print("五");

 		break;

 	case 6:

 		out.print("六");

 	}
 %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢登录使用！</td>
						<td width="42%"><img src="images/out.gif" alt="安全退出"
							width="46" height="20" border="0"
							onclick="javascript:window.history.go(-(history.length-1))"></td>
						<td width="4%">&nbsp;</td>
					</tr>
					<tr>
						<td height="19" colspan="3">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>