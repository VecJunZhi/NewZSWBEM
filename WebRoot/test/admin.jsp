<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    管理员页面 <br>
    <button onclick="count()" id="count">统计</button>
    <button onclick="copyToClipboard();">拷贝</button> 
<input type="text" size=20 id="source" value="测试数据">
<button onclick="alert(window.clipboardData.getData('text'));">显示</button> 
<button onclick="window.clipboardData.clearData('text');">清空</button>
<textarea cols="20" rows="10" id="biao2">用户定义的代码区域</textarea>
<input type="text" size=20 id="biao1" value="测试数据">
<input type="button" onClick="copyUrl2()" value="点击复制代码" />
</body>
  <script type="text/javascript">
  function copyUrl2()
{
var Url2=document.getElementById("biao1");
Url2.select(); // 选择对象
document.execCommand("Copy"); // 执行浏览器复制命令
alert("已复制好，可贴粘。");
}
  
   function count(){
   alert("hehe");
   window.location.href="RbacAction_count";
   
   }
   function copyToClipboard() { 
		var d = document.all("source").value; 
		alert(d);
		window.clipboardData.setData('text', d); 
		alert(window.clipboardData.getData('text'));
	} 
  </script>
  <script type="text/javascript" src="mcrm/admin.js"></script>
</html>
