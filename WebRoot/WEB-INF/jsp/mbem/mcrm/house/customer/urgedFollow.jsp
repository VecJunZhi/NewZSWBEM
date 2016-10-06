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
<title>新增催办</title>
<link rel="Stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link rel="Stylesheet" href="/mbem/mcrm/house/css/user.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/date.css">
<script src="/common/js/jquery-1.9.1.min.js" ></script>
<script src="/common/js/bootstrap/bootstrap.min.js"></script>
<script src="/mbem/mcrm/house/js/date.js" ></script>
<script src="/mbem/mcrm/house/js/iscroll.js" ></script>
<script  src="/common/js/layer/layer.js"></script>
<script>
$(function(){
	$('#nextUrgedDate').date();
	$('#endTime').date({theme:"datetime"});
});
$(document).ready(function(){	
	var allowSubmit = true;
	$("#submit").click(function(){
		if($("#urgedContent").val() == "" || $("#nextUrgedDate").val()=="") {
			layer.alert("请将必填项填写完整!");
			return false;
		}
		var index = layer.load(0, {
			shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
		if(allowSubmit == true) {
			allowSubmit = false;
		}else {
			return false;
		}
	});
	
	$("#cancle").click(function(){
		window.location.href = "/mbem/mcrm/house/customer/urgedDetail.action?tradeGuid=${tradeGuid}&type=${type}";
	});
});
</script>
<style>
#rapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>    
</head>
<body style="background-color:#fff">

    <form class="form-content" id="form-content" action="/mbem/mcrm/house/customer/insertCstUrgedInfo.action?type=${type}" method="post" style="margin-top:40%">
 		<div class="switchSinglePage-wrap">
 			<div class="switchSinglePage panel" style="-webkit-transform: translate3d(0%, 0px, 0px);">
 					<input type="hidden" value="${tradeGuid}" name="tradeGuid">
					<div class="form-group border-1px first">
						<ul class="border-1px">

    						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					            <div class="li-inner border-1px">
					                <span class="k">催办结果</span>
					                <span class="v"> 
					                <textarea name="urgedContent" rows="2" id="urgedContent"  style="height:26px; -webkit-user-modify: read-write-plaintext-only;"  placeholder="请输入"></textarea>
 
					                </span>
					           </div>
							</li>
						    <li class="border-1px" data-require="1">
						     	<div class="li-inner border-1px">
						            <span class="k">下次催办</span>
						            <span class="v">
						        <div class="clearfix dome3_box">
						        <input  id="nextUrgedDate" class="kbtn" name="nextUrgedDate" value=""/>
						                </span>
						       </div>            
							</li>
						</ul>
					</div>
			</div>
            <div class="form-group noswitchSinglePage" style="display:-webkit-box;width:100%; margin-top:10px;">
         
         <button class="btns btns-default" closeType="${closeType}" id="cancle" data-ac="active" type="button">取消</button>
        <button class="btns btns-primary" id="submit" data-ac="active" type="submit">保存</button>
    </div>
		</div>
        
         
         <div id="datePlugin"></div>
         
<script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#rapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="rapper"></div>');
    }();
</script>       
</form>
</body>
</html>