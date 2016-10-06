<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <title>登录</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link type="text/css" rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
    <link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/account.css">
	<script type="text/javascript" src="/mbem/mcrm/business/js/Login_javascript.js"></script>
    <!-- <script>$("input").foucus(function(){$("powered").hide();});</script> -->
</head>

<body style=" min-height:450px;">
    <div class="container">
        <div id="anim1" class="anim clearfix">
            <div class="homeIco">
                <img src="/mbem/mcrm/business/images/homeIco.png">
               <p>兆盛移动应用平台</p>
            </div>
            <form action="/loginMBEM.action" method="post" onsubmit="return user_input()">
                <div class="wrap">
                    <div class="form-group">
                        <label class="phoneLabel">用户名</label>
                        <div class="form-control">
                            <input type="text" name="userName" id="username">
                        </div>
                    </div> 
                </div>
                <div class="wrap">
                    <div class="form-group">
                        <label class="passwordLabel">密&nbsp;&nbsp;码</label>
                        <div class="form-control">
                            <input  type="password" name="password" id="password">
                          
                        </div>
                    </div>
                </div>
	            <div class="tow-Btn clearfix" style="text-align: center; width: 100%; height: 48px; margin: 19px 0 22px;">
	                <input type="submit" value="登 录" style="width: 100%" class="btn fr" >
	            </div>
	        </form>
			<p class="tc urlAnim" style="color: red;">
               ${error}
               <!-- <a href="" id="forget">忘记密码</a> -->
			</p>
        </div>
    </div>
	<div class="powered" id="powered" style=" position:fixed; margin-bottom:4px;">
	    Powered&nbsp;by&nbsp;&nbsp;<a href="#">兆盛地产</a>
	</div>
</body>
</html>