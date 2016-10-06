<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'transferTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#test li").click(function(){
				var framesrc = $(this).attr("fsrc");
				alert(framesrc);
				$("iframe").attr("src",framesrc);
			});
		});
	</script>
  </head>
  
  <body>
    <div>
    <ul id="test">
    <li fsrc="页面1">选项1</li>
    <li fsrc="页面2">选项2</li>
    <li fsrc="页面3">选项3</li>
   </ul>
    </div>
  </body>
</html>
