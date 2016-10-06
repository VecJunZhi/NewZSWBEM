<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/base.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/ui.css">
<script  src="/common/js/jquery-1.9.1.min.js"></script>
<script  src="/mbem/mcrm/house/js/ui.js"></script>
    <title>发现</title>
</head>
<body id="find_index_body">
     <div class="find_index_box clearfix" >
     	<div class="find_group clearfix">
     		<a class="switchgroup" href="/mbem/mcrm/house/find/selectProject.action?flag=1">
                <i class="switch"></i>
                <div class="a_border_half" >项目切换 </div>
            </a>
            <a class="switchgroup" href="/mbem/mcrm/house/find/selectSys.action">
                <i class="switch"></i>
                <div class="a_border_half" >
                    <span class="tit">身份切换</span>
                    <span class="name"></span>
                    <span class="arrow"></span>
                </div>
            </a>
        </div>
        <%-- 
        <div class="find_group clearfix">
            <a href="#">
                <i class="myCard"></i>
                <div class="a_border_half">我的名片</div>
            </a>
            <a class="noborderbottom" href="#">
            	<i class="projectCard"></i>
            	项目名片
            </a>
        </div>
		<div class="find_group clearfix">    
            <!--
            <a href="/Sales/SaleReport/index?token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&projid=c5091504-41ae-4c2e-ae3b-69a008a5762f" class="noborderbottom" >
                <i class="report"></i>
                <div class="a_border_half">报表</div>
            </a>
        	<a href="#" class="noborderbottom">
                <i class="kehugenjin"></i>
                <div class="a_border_half">客户跟进</div>
            </a>
            <a class="noborderbottom">
                <i class="cstlib"></i>
                <div class="a_border_half">我的客户库</div>
            </a>
        </div> 
        --%>
        <div class="find_group clearfix">
       		<a href="/mbem/mcrm/house/room/index.action">
				<i class="housingInfor"></i>
                <div class="a_border_half">房源信息</div>
            </a>
            <a href="http://m.leju.com/touch/tools/house_loan.html">
                <i class="calculator"></i>
                <div class="a_border_half">房贷计算器</div>
            </a>
		</div>
        <div class="find_group clearfix">
            <a  href="/mbem/mcrm/house/find/updateLog.action">
                <i class="sns"></i>
                <div class="a_border_half">更新日志</div>
            </a>
            <a  href="/mbem/mcrm/house/find/xsFeedBack.action">
                <i class="sns"></i>
                <div class="a_border_half">意见与反馈</div>
            </a>
        </div>                  
        <div class="find_group clearfix">  
			<a href="/mbem/mcrm/house/find/changePassword.action">
				<i class="modpwd"></i>
				<div class="a_border_half"> 修改密码</div>
			</a>
            <a href="/mbem/mcrm/house/find/logouting.action" class="noborderbottom" id="exit">
                <i class="exit"></i>
                <div class="a_border_half"> 注销</div>
            </a>
        </div>
    </div>
</html>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/footer.jsp" %>