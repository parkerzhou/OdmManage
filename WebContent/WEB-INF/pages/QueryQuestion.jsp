<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.concurrent.Delayed"%>
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
<link href="resources/css/link.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript"
	src="resources/js/WdatePicker.js"></script>

<script type="text/javascript">

		function showDiv()
		{
			var xmlhttp; 
			var stime=document.getElementById("statime").value;
			var ctime=document.getElementById("comtime").value;
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {//alert(xmlhttp.readyState);
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    document.getElementById("divdis").innerHTML=xmlhttp.responseText;
			    }
			  }
			var url="<%=basePath%>QueryByData?st="+stime+"&ct="+ctime;
			//alert(url);
		
			//var url="MyJsp.jsp";
			xmlhttp.open("GET",url,true);
			//alert("1");
			xmlhttp.send(null);
			//alert("2");
		}
		function showKDiv()
		{
			var xmlhttp; 
			var kwords=document.getElementById("kwords").value;
			if (window.XMLHttpRequest)
			  {// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {// code for IE6, IE5
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			xmlhttp.onreadystatechange=function()
			  {//alert(xmlhttp.readyState);
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    document.getElementById("kdivdis").innerHTML=xmlhttp.responseText;
			    }
			  }
			var url=encodeURI("<%=basePath%>QueryBykwords?kw="+kwords);
			//alert(url);
		
			//var url="MyJsp.jsp";
			xmlhttp.open("GET",url,true);
			//alert("1");
			xmlhttp.send(null);
			//alert("2");
		}
		
		
		function dateComp()
		{
			var stime=document.getElementById("statime").value;
			var ctime=document.getElementById("comtime").value;
			if(!stime||!ctime){
				alert("请输入查询时间！");
			}
			else if(stime>ctime)
			{
				alert("结束时间必须大于开始时间！");
			}
			else
			{	
				showDiv();
			}
		}
		function keywords()
		{
			var kwords=document.getElementById("kwords").value;
			if(!kwords){
				alert("请输入关键字！");
			}
			else
			{	
				showKDiv();
			}
		}
	</script>


</head>

<body>
	<div
		style="width: 100%; margin: 1% auto; background: #fff; border: 0px solid #7ABAF2">
		<table width="100%" border="0px" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<h5 style="background: #BBFFFF; margin: 0; padding: 5px;"
						align="left">
						<font size="4">查询异常</font>
					</h5>
				</td>
				<td>
					<h5 style="background: #BBFFFF; margin: 0; padding: 5px;"
						align="right">
						<a href="Back"><font size="4"><strong>返回</strong></font></a>
					</h5>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<h5 style="margin: 0; padding: 0px;" align="left">时间查询，请输入日期：</h5>
	<table>
		<tr
			style="font-size: 12; font-style: inherit; text-align: center; height: 25px">
			<td>开始日期:</td>
			<td><input type="text" id="statime"
				onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"
				value=""></td>
			<td>结束日期:</td>
			<td><input type="text" id="comtime"
				onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"
				value=""></td>
			<td><input name="name" type="button" onClick="dateComp()"
				value="查询"></td>
		</tr>
	</table>
	<br>
	<h5 style="margin: 0; padding: 0px;" align="left">
		模糊查询，请输入关键字(生产批号，线体，客户，型号机芯的关键字)：</h5>
	<table>
		<tr
			style="font-size: 12; font-style: inherit; text-align: center; height: 25px">
			<td><input type="text" id="kwords" value=""></td>
			<td><input name="name" type="button" onClick="keywords()"
				value="查询"></td>
		</tr>
	</table>
	<br>
	</div>
	<div id="divdis"
		style="width: 100%; margin: 3% auto; background: #fff; border: 0px solid #7ABAF2"></div>
	<div id="kdivdis"
		style="width: 100%; margin: 3% auto; background: #fff; border: 0px solid #7ABAF2"></div>
</body>
</html>