<%@page import="java.util.concurrent.Delayed"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tcl.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
   //response.setHeader("Content-disposition","inline; filename=videos.xls");  
   response.setHeader("Content-disposition","attachment; filename=Question.xls");  
   //以上这行设定传送到前端浏览器时的档名为test.xls  
   //就是靠这一行，让前端浏览器以为接收到一个excel档   
%>
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
  List<String> occurtime=(List<String>)session.getAttribute("occurtime");
  List<String> plantime=(List<String>)session.getAttribute("plantime");
  List<String> closetime=(List<String>)session.getAttribute("closetime");
  %>
<body>
	<div
		style="width: 100%; margin: 1% auto; background: #fff; border: 0px solid #7ABAF2">
		<table border="1px" cellspacing="0" cellpadding="0"
			style="word-break: break-all; word-wrap: break-word; word-spacing: break-word; border-collapse: collapse; font-size: 12"
			width="100%">
			<col>
			<col>
			<col width="20%">
			<col>
			<col>
			<col width="20%">
			<col>
			<col width="25%">
			<col>
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
				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>
