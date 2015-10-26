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
<script language="JavaScript" type="text/javascript"
	src="resources/js/WdatePicker.js"></script>
<title>异常回复</title>
<%
  String workShop=(String)session.getAttribute("workShop");
  %>
</head>
<body>
	<div
		style="width: 620px; margin: 0% auto; background: #fff; border: 2px solid #7ABAF2">
		<h3 style="background: #7ABAF2; margin: 0; padding: 5px;">异常回复</h3>
		<ul style="list-style: none;">
			<li style="margin-bottom: 15px; list-style: none;">
				<form
					action="SaveReply?reasonid=${alterquestion.reason.id }&solveid=${alterquestion.solve.id }
&quesid=${alterquestion.id }&No=2"
					method="POST">
					<table>
						<tr>
							<td><strong>线体 :</strong> ${alterquestion.line.name }</td>

						</tr>
						<tr>
							<td><strong>客户 :</strong> ${alterquestion.client.name }</td>

						</tr>
						<tr>
							<td><strong>型号机芯 :</strong> ${alterquestion.pro_name }</td>

						</tr>
						<tr>
							<td><strong>生产批号 :</strong> ${alterquestion.no }</td>

						</tr>
						<tr>
							<td><strong>批次数量 :</strong> ${alterquestion.pro_plan_num }</td>

						</tr>
						<tr>
							<td><strong>已生产数 :</strong> ${alterquestion.pro_num }</td>

						</tr>
						<tr>
							<td><strong>不良比率 ：</strong> ${alterquestion.bad_ratio }</td>

						</tr>
						<tr>
							<td><strong>不良描述 :</strong> ${alterquestion.body }</td>
						</tr>
						</table><table style="width: 500px">
						<tr><h3 style=" background: #7ABAF2; margin: 0; ">工艺员/分析师/结构工程师回复:</h3></tr>
						<tr>
							<td><strong>责任部门:</strong>${alterquestion.dept.name}</td>
						</tr>
						<tr>
							<td><strong>问题分类:</strong>${alterquestion.sort.name}</td>
						</tr>
						<tr>
							<td><strong>原因分析：</strong>${alterquestion.reason.body }</td>
						</tr>
						<tr>
							<td><strong>临时方案：</strong>${alterquestion.solve.tem_metnod }</td>
						</tr>
						<tr>
							<td><strong>责任人&nbsp;&nbsp;&nbsp;&nbsp;：</strong>${alterquestion.solve.head }</td>
						</tr>
						<tr>
							<td><strong>问题状态:</strong> ${sstate}</td>
						</tr>
						<tr>
							<td><strong>确认人&nbsp;&nbsp;&nbsp;&nbsp;：</strong>${alterquestion.solve.comfirm }</td>
						</tr>
						<tr>
							<td><strong>问题确认：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${ccomfirm }</td>
						</tr>
						</table>
						<table style="width: 500px">
						<tr>
						<h3 style="background: #7ABAF2; margin: 0; ">责任部门回复:</h3>
						</tr>
						<tr>
							<td><strong>根本措施：</strong></td>
						</tr>
						<tr>
							<td><textarea name="basic_method" cols="55" rows="5">${alterquestion.solve.basic_method }</textarea></td>
						</tr>
						<tr>
							<td><strong>计划完成日期：</strong><input type="text"
								name="plan_tim" value="${plan_time }"
								onclick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"></td>
						</tr>
						
						<tr>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td colspan="2"><hr></td>
						</tr>
						<tr>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input type="reset" value="重置" />
								<input type="button" value="退出"
								onClick="window.location.href='Back'" /> <input type="submit"
								value="保存" /></td>
						</tr>
					</table>

				</form>


			</li>
		</ul>
	</div>
</body>
</html>
