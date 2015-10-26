<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>提示</title>
<link href="resources/css/link.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:if test="${!empty error4}">
		<font color="red" size="5"><c:out value="${error4}" /></font>
	</c:if>
	<a href="Back"><font size="5">返回看板</font></a>
</body>
</html>