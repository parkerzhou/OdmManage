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
<title>异常看板</title>
<%
  String workShop=(String)session.getAttribute("workShop");
  %>
</head>
<body>
	<div
		style="width: 620px; margin: 0% auto; background: #fff; border: 2px solid #7ABAF2">
		<h3 style="background: #7ABAF2; margin: 0; padding: 5px;">异常基本信息</h3>
		<ul style="list-style: none;">
			<c:if test="${!empty error2}">
				<font color="red"><c:out value="${error2}" /></font>
			</c:if>
			<li style="margin-bottom: 15px; list-style: none;">
				<form action="NewQuestion" method="POST">
					<table class="wwFormTable">
						<tr>
							<td>线体:</td>
							<td><select name="line" style="width: 155px">
									<option value="${alterquestion.line.id }">${alterquestion.line.name
										}</option>
									<%int i=1;%>
									<c:forEach items="${line}" var="line">
										<option value="<%=i%>">${line}</option>
										<%i++; %>
									</c:forEach>
							</select> <font color="red" size="2">*</font></td>
						</tr>
						<tr>
							<td>客户:</td>
							<td><select name="client" style="width: 155px">
									<option value=""></option>
									<%i=1;%>
									<c:forEach items="${client}" var="client">
										<option value="<%=i%>">${client}</option>
										<%i++; %>
									</c:forEach>
							</select> <font color="red" size="2">*</font></td>
						</tr>
						<tr>
							<td>型号机芯:</td>
							<td><input type="text" name="pro_name" value="" /> <font
								color="red" size="2">*示例40C720S1/3MS82AX</font></td>
						</tr>
						<tr>
							<td>生产批号:</td>
							<td><input type="text" name="no" value="" /> <font
								color="red" size="2">*</font></td>
						</tr>
						<tr>
							<td>批次数量:</td>
							<td><input type="text" name="pro_plan_num" value="" /> <font
								color="red" size="2">*必须填写数字</font></td>
						</tr>
						<tr>
							<td>已生产数:</td>
							<td><input type="text" name="pro_num" value="" /> <font
								color="red" size="2">*必须填写数字</font></td>
						</tr>
						
						<tr>
							<td>不良比率:</td>
							<td><input type="text" name="bad_ratio" value="" /> <font
								color="red" size="2">*</font></td>
						</tr>
						<tr>
							<td>不良描述:</td>
							<td><textarea name="body" cols="55" rows="5"></textarea> <font
								color="red" size="2">*</font></td>
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
							<td colspan="2" style="font-size: 12">说明: 带" <font size="2"
								color="red"> * </font> "号内容为必填项目, 谢谢!
							</td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input type="reset" value="重置" />
								<input type="button" value="退出"
								onClick="window.location.href='Back'" /> <input type="submit"
								value="保存" /></td>
						</tr>

					</table>

				</form>
              <form action="AddClient" method="POST">
			    <table>
			    <tr>
							<td>添加客户:</td>
							<td><input type="text" name="addclient" value="" /> </td>
							<td><input type="submit" value="添加"></td>
						</tr>
						</table>
			    </form>

			</li>
		</ul>
	</div>
</body>
</html>
