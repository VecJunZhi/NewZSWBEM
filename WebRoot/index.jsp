<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
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
<script type="text/javascript">

	function browserRedirect(){ 
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
			/* window.location.href= '/mcrm/login.jsp';  */
			window.location.href='/loginMBEM.action';
		} else { 
			/* window.location= '/wbem/index.jsp';  */
			window.location.href='/loginWBEM.action';
		} 
	} 

browserRedirect(); 

</script>
</head>
	<body>
	</body>
</html>