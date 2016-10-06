<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%> 
<%@page import="javax.naming.*"%> 
<%@page import="javax.sql.DataSource"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jnidTest.jsp' starting page</title>
    
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
    This is my JSP page. <br>
    
<% 
try { 
Context ctx = new InitialContext(); 
Context envContext = (Context) ctx.lookup("java:/comp/env"); 
DataSource ds = (DataSource) envContext.lookup("jdbc/sqlServer2000"); 
Connection conn = ds.getConnection(); 
out.println(conn.toString()); 
conn.close(); 
} catch (NamingException e) { 
e.printStackTrace(); 
} catch (SQLException e) { 
         e.printStackTrace(); 
} 
%> 
  </body>
</html>
