<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title></title>
	<meta charset="GBK" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="/wbem/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/wbem/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="/wbem/css/matrix-login.css" />
	<link href="/wbem/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link rel="stylesheet" href="/wbem/css/fonts.css" />
</head>
<body>
    <div id="loginbox">            
        <form id="loginform" class="form-vertical" method="post" action="/loginWBEM.action">
 			<div class="control-group normal_text"> <h3><img src="/wbem/img/logo.png" alt="Logo" /></h3></div>
            <div class="control-group">
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" name="userName" placeholder="请输入用户名" />
                    </div>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name="password" placeholder="请输入密码" />
                    </div>
                </div>
            </div>
            <div class="form-actions">
                <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
                <span class="pull-right"><button type="submit" class="btn btn-success" />登录</button></span>
            </div>
        </form>
        <form id="recoverform" action="#" class="form-vertical">
			<p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="请输入EMAIL地址" />
                </div>
            </div>
            <div class="form-actions">
                <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登录</a></span>
                <span class="pull-right"><a class="btn btn-info"/>找回密码</a></span>
            </div>
        </form>
    </div>
    <script src="/wbem/js/jquery.min.js"></script>  
    <!-- <script src="/wbem/js/matrix.login.js"></script> -->
</body>
</html>

