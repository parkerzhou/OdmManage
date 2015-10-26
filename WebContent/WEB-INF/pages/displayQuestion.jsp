<%@page import="java.util.concurrent.Delayed"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tcl.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>异常看板</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="resources/css/link.css" rel="stylesheet" type="text/css">
</head>
<%
  String workShop=(String)session.getAttribute("workShop");
  String today=(String)session.getAttribute("today");
  User user=(User)session.getAttribute("user");
  Integer todayNewNum=(Integer)session.getAttribute("todayNewNum");
  Integer unCloseQuesNum=(Integer)session.getAttribute("unCloseQuesNum");
  Integer closedQuesNum=(Integer)session.getAttribute("closedQuesNum");
  Integer allQuesNum=(Integer)session.getAttribute("allQuesNum");
  %>
<body>
	<div
		style="width: 100%; auto; background: #fff; border: 0px solid #7ABAF2">
		<p>
			<font size="4" color="#000000"><strong>车间: <%=workShop %>
			</strong></font>
		</p>
		<p>
			<font size="4" color="#8E8E8E"><strong><a href="ToChangePW"><%=user.getTu_user_name() %></a>,欢迎您!
					今天：<%=today %>(点击名字可修改密码)</strong></font>
		</p>
	</div>
	<div
		style="width: 100%; margin: 1% auto; background: #fff; border: 0px solid #7ABAF2">
		<table width="100%" border="0px" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<h5 style="background: #BBFFFF; margin: 0; padding: 5px;"
						align="left">
						<a href="<%= basePath%>Question?id=1">今日新增</a><font size="3"
							color="#0000C6"><%=todayNewNum %></font> | <a
							href="<%= basePath%>Question?id=2">未关闭事项</a><font size="3"
							color="#0000C6"><%=unCloseQuesNum %></font> | <a
							href="<%= basePath%>Question?id=3">已关闭事项</a><font size="3"
							color="#0000C6"><%=closedQuesNum %></font> | <a
							href="<%= basePath%>Question?id=4">显示全部</a><font size="3"
							color="#0000C6"><%=allQuesNum %></font>
					</h5>
				</td>
				<td>
					<h5 style="background: #BBFFFF; margin: 0; padding: 5px;"
						align="right">
						<a href="<%= basePath%>NewQuestion">新建异常</a> | <a
							href="<%= basePath%>Switch">切换车间</a> | <a
							href="<%= basePath%>QueryQues">查询异常</a> | <a href="login">登录</a>
					</h5>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
