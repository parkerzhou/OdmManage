<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>异常看板登录</title>
</head>
<body>
	<div
		style="width: 300px; margin: 10% auto; background: #fff; border: 3px solid #7ABAF2">
		<h3 style="background: #7ABAF2; margin: 0; padding: 5px;">异常看板用户登录</h3>
		<ul style="list-style: none;">
			<c:if test="${!empty error1}">
				<font color="red"><c:out value="${error1}" /></font>
			</c:if>
			<li style="margin-bottom: 15px; list-style: none;"><sf:form
					method="post" modelAttribute="user">
			用户：
			<sf:input path="tu_login_name" type="text" />
					<br>
		         密码：
		    <sf:input path="tu_login_pwd" type="password" />
					<br>
					<input type="submit" value="登录" />
					<input type="reset" value="重置" />
				</sf:form></li>
		</ul>
	</div>
</body>
</html>