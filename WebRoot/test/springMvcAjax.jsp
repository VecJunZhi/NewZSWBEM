<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'springMvcAjax.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	    <script type="text/javascript" src="/mcrm/business/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="/mcrm/business/js/date.js" ></script>
    <script type="text/javascript" src="/mcrm/business/js/iscroll.js" ></script>
  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript">
  $(document).ready(function() {  
    $("#profile").click(function() {  
        profile();  
    });  
    $("#login").click(function() {  
    alert("loji");
        login();  
    });  
});  
function profile() {  
    var url = 'http://localhost:8080/spring-json/json/person/profile/';  
    var query = $('#id').val() + '/' + $('#name').val() + '/'  
            + $('#status').val();  
    url += query;  
    alert(url);  
    $.get(url, function(data) {  
        alert("id: " + data.id + "\nname: " + data.name + "\nstatus: "  
                + data.status);  
    });  
}  
function login() {  
alert("fd");
    var mydata = '{"age":"' + $('#age').val() + '","addr":"'  
            + $('#addr').val() + '","sex":"' + $('#sex').val() + '"}';  
    alert(mydata);  
    $.ajax({  
        type : 'POST',  
        contentType : 'application/json',  
        url : 'http://localhost:8080/ajaxTest.action?age&sex',  
        processData : false,  
        dataType : 'json',  
        data : mydata,  
        success : function(data) {  
            alert("id: " + data.age + "\nname: " + data.sex + "\nstatus: "  
                    + data.addr);  
        },  
        error : function() {  
            alert('Err...');  
        }  
    });
    }
  </script>
  </head>
  
  
  <body>
    <table>  
    <tr>  
        <td>id</td>  
        <td><input id="age" value="100" /></td>  
    </tr>  
    <tr>  
        <td>name</td>  
        <td><input id="addr" value="snowolf" /></td>  
    </tr>  
    <tr>  
        <td>status</td>  
        <td><input id="sex" value="true" /></td>  
    </tr>  
    <tr>  
        <td><input type="button" id="profile" value="Profile！！GET" /></td>  
        <td><input type="button" id="login" value="Login！！POST" /></td>  
    </tr>  
</table>  
  </body>

</html>
