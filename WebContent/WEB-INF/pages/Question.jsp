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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="resources/css/link.css" rel="stylesheet" type="text/css">
</head>
<%
  String workShop=(String)session.getAttribute("workShop");
  String today=(String)session.getAttribute("today");
  String id=(String)session.getAttribute("id");
  User user=(User)session.getAttribute("user");
  List<String> occurtime=(List<String>)session.getAttribute("occurtime");
  List<String> plantime=(List<String>)session.getAttribute("plantime");
  List<String> closetime=(List<String>)session.getAttribute("closetime");
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
			<c:if test="${!empty error3}">
				<font color="red" size="5"><c:out value="${error3}" /></font>
			</c:if>
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
							href="<%= basePath%>Export?id=<%=id%>">导出EXCEL</a> | <a
							href="<%= basePath%>Switch">切换车间</a> | <a
							href="<%= basePath%>QueryQues">查询异常</a> | <a href="login">登录</a>
					</h5>
				</td>
			</tr>
		</table>
		<table border="1px" cellspacing="0" cellpadding="0"
			style="word-break: break-all; word-wrap: break-word; word-spacing: break-word; border-collapse: collapse; font-size: 12"
			width="100%">
			<tr bgcolor="#7ABAF2"
				style="font-size: 12; font-style: inherit; text-align: center; height: 25px">
				<td width="3%"><strong>序号 </strong></td>
				<td width="4%"><strong>线体</strong></td>
				<td width="3%"><strong>客户</strong></td>
				<td width="4%"><strong>生产批号</strong></td>
				<td width="4%"><strong>型号机芯</strong></td>
				<td width="3%"><strong>批次数量</strong></td>
				<td width="3%"><strong>已生产数</strong></td>
				<td width="4%"><strong>不良比率</strong></td>
				<td width="4%"><strong>发生时间</strong></td>
				<td width="9%"><strong>不良描述</strong></td>
				<td width="4%"><strong>责任部门</strong></td>
				<td width="4%"><strong>问题分类</strong></td>
				<td width="9%"><strong>原因分析</strong></td>
				<td width="9%"><strong>临时方案</strong></td>
				<td width="4%"><strong>责任人</strong></td>
				<td width="9%"><strong>根本措施</strong></td>
				<td width="4%"><strong>计划完成日期</strong></td>
				<td width="4%"><strong>问题关闭日期</strong></td>
				<td width="4%"><strong>确认人</strong></td>
				<td width="4%"><strong>QA确认人</strong></td>
				<td width="4%"><strong>操作</strong></td>
			</tr>
			<%
    	int index=0;
    	%>
			<c:forEach items="${questions}" var="question">
				<% index++; %>
				<tr>
					<td width="3%" align="center"><%=index %></td>
					<td width="4%" align="center">${question.line.name }</td>
					<td width="3%" align="center">${question.client.name }</td>
					<td width="4%" align="center">${question.no }</td>
					<td width="4%" align="center">${question.pro_name }</td>
					<td width="3%" align="center">${question.pro_plan_num }</td>
					<td width="3%" align="center">${question.pro_num }</td>
					<td width="4%" align="center">${question.bad_ratio }</td>
					<td width="5%"><%=occurtime.get(index-1) %></td>
					<td width="8%">${question.body }</td>
					<td width="4%" align="center">${question.dept.name }</td>
					<td width="4%" align="center">${question.sort.name }</td>
					<td width="9%">${question.reason.body }</td>
					<td width="9%">${question.solve.tem_metnod }</td>
					<td width="4%" align="center">${question.solve.head }</td>
					<td width="9%">${question.solve.basic_method }</td>
					<td width="4%"><%=plantime.get(index-1) %></td>
					<td width="4%"><%=closetime.get(index-1) %></td>
					<td width="4%" align="center">${question.solve.comfirm }</td>
					<td width="4%" align="center">${question.solve.QA_comf }</td>
					<td width="4%"><c:if test="${question.state==1}">
							<a
								href="Reply?ques_id=${question.id}&first_id=${question.first_id}&second_id=${question.second_id}"><strong>回复</strong></a>/
    	    <a
								href="Alter?user_id=${question.user_id}&ques_id=${question.id}&page=1"><strong>修改</strong></a>
						</c:if></td>
				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>
