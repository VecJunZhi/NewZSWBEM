<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

    
<!-- 
	1、获取当前访问域名，跟据域名识别跳转。
	mcrm.zgzsdc.com     跳转到      /marm/index.jsp
	wy.zgzsdc.com       跟转到     /wy/index.jsp	
	erp.zgzsdc.com      跳转到      /login.jsp
	
	
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">

function browserRedirect() { 

var sUserAgent= navigator.userAgent.toLowerCase(); 

var bIsIpad= sUserAgent.match(/ipad/i) == "ipad"; 

var bIsIphoneOs= sUserAgent.match(/iphone os/i) == "iphone os"; 

var bIsMidp= sUserAgent.match(/midp/i) == "midp"; 

var bIsUc7= sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4"; 

var bIsUc= sUserAgent.match(/ucweb/i) == "ucweb"; 

var bIsAndroid= sUserAgent.match(/android/i) == "android"; 

var bIsCE= sUserAgent.match(/windows ce/i) == "windows ce"; 

var bIsWM= sUserAgent.match(/windows mobile/i) == "windows mobile"; 

if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) { 

window.location.href= '/mcrm/login.jsp'; 

} else { 

window.location= '/wbem/login.jsp'; 

} 

} 

browserRedirect(); 

</script>


</head>
<body style="background-color: green;">
<div style="margin-left: 40%;margin-top: 12%;background-color:green;">
<form action="LoginAction_checkEntPasswords.action" method="post">
<table>
<caption>考勤机同步系统</caption>
<tr align="center"><td align="center">用户名</td><td><input type="text" name="username" value="${username }"></td></tr>
<tr><td align="center">密码</td><td><input type="text" name="password" value="${password }"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="同步"></td></tr>
</table>
</form>
${name}
${toTel }

</div>
</body>
</html>