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
<title>新建异常</title>
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
				<form
					action="SaveAlter?action=${action }&reasonid=${alterquestion.reason.id }
&solveid=${alterquestion.solve.id }&quesid=${alterquestion.id }"
					method="POST">
					<table>
						<tr>
							<td><strong>线体:</strong> <select name="line" style="width: 155px">
									<option value="${alterquestion.line.id }">${alterquestion.line.name
										}</option>
									<%int i=1;%>
									<c:forEach items="${line}" var="line">
										<option value="<%=i%>">${line}</option>
										<%i++; %>
									</c:forEach>
							</select> <font color="red" size="2">*</font>
							</td>
						</tr>
						<tr>
							<td><strong>客户:</strong> <select name="client" style="width: 155px">
									<option value="${alterquestion.client.id }">${alterquestion.client.name
										}</option>
									<%i=1;%>
									<c:forEach items="${client}" var="client">
										<option value="<%=i%>">${client}</option>
										<%i++; %>
									</c:forEach>
							</select> <font color="red" size="2">*</font>
							</td>
						</tr>
						<tr>
							<td><strong>型号机芯:</strong> <input type="text" name="pro_name"
								value="${alterquestion.pro_name }" /> <font color="red"
								size="2">*示例40C720S1/3MS82AX</font>
							</td>
						</tr>
						<tr>
							<td><strong>生产批号:</strong> <input type="text" name="no"
								value="${alterquestion.no }" /> <font color="red" size="2">*</font>
							</td>
						</tr>
						<tr>
							<td><strong>批次数量:</strong> <input type="text" name="pro_plan_num"
								value="${alterquestion.pro_plan_num }" /> <font color="red"
								size="2">*必须填写数字</font>
							</td>
						</tr>
						<tr>
							<td><strong>已生产数:</strong> <input type="text" name="pro_num"
								value="${alterquestion.pro_num }" /> <font color="red" size="2">*必须填写数字</font>
							</td>
						</tr>
						<tr>
							<td><strong>不良比率:</strong> <input type="text" name="bad_ratio"
								value="${alterquestion.bad_ratio }" /> <font color="red"
								size="2">*</font>
							</td>
						</tr>

						<tr>
							<td><strong>不良描述:</strong><font color="red" size="2">*</font>
							</td>
						</tr>
						<tr>
							<td><textarea name="body" cols="55" rows="5">${alterquestion.body }</textarea>
							</td>
						</tr><tr>
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


			</li>
		</ul>
	</div>
</body>
</html>
