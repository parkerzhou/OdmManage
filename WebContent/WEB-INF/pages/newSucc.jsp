<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/css/link.css" rel="stylesheet" type="text/css">
<title>异常看板</title>
</head>
<%
  String workShop=(String)session.getAttribute("workShop");
  %>
<body>
	<h3>
		<font size="5">成功！<a href="Back?ws=<%=workShop%>"><font
				size="5">返回看板</font></a></font>
	</h3>
</body>
</html>