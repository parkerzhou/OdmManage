<%@page import="java.util.concurrent.Delayed"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>查询异常</title>

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
  request.setCharacterEncoding("UTF-8");
  %>
<body>
	<div
		style="width: 100%; margin: 1% auto; background: #fff; border: 0px solid #7ABAF2">
		<table width="100%" border="0px" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<h5 style="background: #BBFFFF; margin: 0; padding: 5px;"
						align="left">
						<font size="4">时间查询</font>
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
			<c:forEach items="${querybydata}" var="qbd">
				<% index++; %>
				<tr>
					<td width="3%" align="center"><%=index %></td>
					<td width="4%" align="center">${qbd.line.name }</td>
					<td width="3%" align="center">${qbd.client.name }</td>
					<td width="4%" align="center">${qbd.no }</td>
					<td width="4%" align="center">${qbd.pro_name }</td>
					<td width="3%" align="center">${qbd.pro_plan_num }</td>
					<td width="3%" align="center">${qbd.pro_num }</td>
					<td width="4%" align="center">${qbd.bad_ratio }</td>
					<td width="5%"><%=occurtime.get(index-1) %></td>
					<td width="8%">${qbd.body }</td>
					<td width="4%" align="center">${qbd.dept.name }</td>
					<td width="4%" align="center">${qbd.sort.name }</td>
					<td width="9%">${qbd.reason.body }</td>
					<td width="9%">${qbd.solve.tem_metnod }</td>
					<td width="4%" align="center">${qbd.solve.head }</td>
					<td width="9%">${qbd.solve.basic_method }</td>
					<td width="4%"><%=plantime.get(index-1) %></td>
					<td width="4%"><%=closetime.get(index-1) %></td>
					<td width="4%" align="center">${qbd.solve.comfirm }</td>
					<td width="4%" align="center">${qbd.solve.QA_comf }</td>
					<td width="4%"><c:if test="${qbd.state==1}">
							<a
								href="Reply?ques_id=${qbd.id}&first_id=${qbd.first_id}&second_id=${qbd.second_id}"><strong>回复</strong></a>/
    	    <a href="Alter?user_id=${qbd.user_id}&ques_id=${qbd.id}&page=2"><strong>修改</strong></a>
						</c:if></td>
				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>
