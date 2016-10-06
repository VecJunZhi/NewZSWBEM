<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<title>催办详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link  rel="Stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link  rel="Stylesheet" href="/mbem/mcrm/house/css/personal.css">
<script  src="/mbem/mcrm/house/js/base_new.min.js"></script>
<script  src="/mbem/mcrm/house/js/addCustom.js"></script>
<script>
	var flag = 0;
	function switchTab(n){
		for(var i = 1; i <= 2; i++){
			document.getElementById("tab_" + i).className = "tab";
			document.getElementById("tab_con_" + i).style.display = "none";
		}
		document.getElementById("tab_" + n).className = "on tab active";
		document.getElementById("tab_con_" + n).style.display = "block";
		flag = 1;
	}	
	function popClose(type){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
 	$(document).ready(function(){
 
 	});
 	
 	function Show_Hidden(trid,tag){
 	    if(trid.style.display=="block"){
 	        trid.style.display='none';
 	        $(tag).children().attr("src","/mbem/mcrm/house/images/close.png");
 	    }else{
 	        trid.style.display='block';
 	        $(tag).children().attr("src","/mbem/mcrm/house/images/open.png");
 	    }
 	}
</script>
<style>
#wrapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>
</head>
<body style="background-color:#fff;">
<div id="wrapper">
  <div class="head">
	<div class="basic">
        <div class="bas-r1">
        	<div class="bas-r1-c1">    
            </div>
            <div class="bas-r1-c2">
                <div class="bas-name" style="max-width:100%"><b>${tradeInfo.xsCst.cstName}</b><span>${tradeInfo.xsCst.gender}</span><small>${tradeInfo.xsCst.mobileTel}</small></div>
            </div>
        </div>  
     </div>
        <div class="rec-p" style=" margin-top:15px;">
			<div class="rec-p-r2">
		       <dl class="active">
		            <dt><span></span></dt>
		            <dd>问询</dd>
		       </dl>
		       <dl class="active">
		            <dt><span></span></dt>
		            <dd>看房</dd>
		        </dl>
		        <c:if test="${tradeInfo.xsOrder.orderType == '认购'}" >
			    	<dl class="active">
			    		<dt><span></span></dt>
			    		<dd>认购</dd>
			    	</dl>
			    	<dl class="">
			    		<dt><span></span></dt>
			    		<dd>签约</dd>
			    	</dl>
		    	</c:if>
		    	<c:if test="${tradeInfo.xsOrder.orderType == '签约'}" >
			    	<dl class="active">
			    		<dt><span></span></dt>
			    		<dd>认购</dd>
			    	</dl>
			    	<dl class="active">
			    		<dt><span></span></dt>
			    		<dd>签约</dd>
			    	</dl>
		    	</c:if>
		    </div>
		</div>
    </div>
<div id="box">
	<ul id="tab" class="tabs">
		<li class="on tab active" id="tab_1" onclick="switchTab(1)"> 
	       <div data-ac="tab-active" data-show="rec-wrap">
	            <div>
	            	<a href="#">交易信息</a>
	            </div>
	       </div>
	    </li> 
	    <li class="on tab" id="tab_2" onclick="switchTab(2)">
			<div data-ac="tab-active" data-show="detail-wrap">
	            <div>
	            	<a href="#">催办记录</a>
	            </div>
	        </div>
		</li>
	</ul>
	<ul id="tab_con">
	<li id="tab_con_1">
	    <div class="detail-wrap" style="display:block">
	        <dl class="info ">
	            <dt class="">
	                <div class="info-c1">房间号</div>
	                <div class="info-c2 ">${tradeInfo.xsRoom.roomInfo.substring(11)}</div>
	            </dt>
	            <dt>
	                <div class="info-c1">交易日期</div>
	                <div class="info-c2 ">${tradeInfo.xsOrder.qsDate}</div>
	            </dt>
	             <dt>
	                <div class="info-c1">交易类型</div>
	                <div class="info-c2 ">${tradeInfo.xsOrder.orderType}</div>
	            </dt>
	            <c:if test="${type == 'unPayment'}">
		             <dt>
		                <div class="info-c1"> 实收款</div>
		                <div class="info-c2">${skTotal}元</div>
		                <div onclick="Show_Hidden(tr1,this)"><img alt="" src="/mbem/mcrm/house/images/close.png" style="height:30px;"></div> 
		            </dt>
		            
		            <dt id="tr1" style=" margin-top:-15px;display:none;">
		            <c:forEach items="${skInfoList}" var="skInfo" varStatus="status">
			            <div class="info-c2"  style="padding-left: 5%;font-size:0.9em;"><p style="width:40%; float:left;">${skInfo.itemName}</p>  <span style="width:60%;line-height:28px;">${skInfo.rmbAmount}元</span></div>
		            </c:forEach>    
		            </dt>
		            <dt>
		                <div class="info-c1">总欠款</div>
		                <div class="info-c2">${qkTotal}元</div>
		                <div onclick="Show_Hidden(tr2,this)"><img alt="" src="/mbem/mcrm/house/images/close.png" style="height:30px;"></div>
		            </dt>
		            
		            <dt id="tr2" style="margin-top:-15px; display:none;">
		            <c:forEach items="${qkInfoList}" var="qkInfo" varStatus="status">
		                <div class="info-c2"  style="padding-left: 5%;font-size:0.9em;"><p style="width:40%; float:left">${qkInfo.itemName}</p>  <span style="width:60%;line-height:28px;">${qkInfo.rmbAmount}元</span></div>
		            </c:forEach>
		            </dt>
	            </c:if> 
	            <c:if test="${type == 'unLending'}">
			        <dt>
		                <div class="info-c1">按揭银行</div>
		                <div class="info-c2 ">${tradeInfo.xsOther.bank}</div>
		            </dt>
	            </c:if>       
	        </dl>
	        <dl class="info border-1px">
	        </dl>
	    </div> 
	</li>   
	<li id="tab_con_2">
		<div class="rec-wrap">        
			<div class="rec-line border-1px">  
			    <c:forEach items="${urgedInfoList }" var="urgedInfo" varStatus="status">
			    <dl>
			        <dt>${urgedInfo.xsGjjl.urgedDate}<span class="rec-line-gjfs"></span></dt>
			        <dd>
			            <div class="rec-line-msg">
			                <p>${urgedInfo.xsGjjl.urgedContent}</p>
			                <p class="rec-line-msg-t">催办人：${urgedInfo.xsUser.username}</p>
			                <c:if test="${status.index == 0}">
			                <p class="rec-line-msg-t">下次催办日期：${urgedInfo.xsGjjl.nextUrgedDate}</p>
			                </c:if>
			            </div>
			        </dd>
			    </dl>  
			    </c:forEach>
			    <c:if test="${fn:length(urgedInfoList) == 0 }">
			    	<div style="width:100%; height:50px; font-size:16px; text-align:center; margin-top:10px;">最近无催办</div>
			    </c:if>
			</div>
		</div> 
	</li> 
	</ul> 
</div>
</div>  
<script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#wrapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="wrapper"></div>');
    }();
</script>    
<div class="btn-group">    
    <a onclick="popClose()" class="btn-close" data-ac="active">返回</a>
    <a class="btn-contact btn-go-addrec" href="tel:${tradeInfo.xsCst.mobileTel}" onclick="urgedCallCst('${tradeGuid}','${type}')">联系客户</a>
    <a href="/mbem/mcrm/house/customer/urgedFollow.action?tradeGuid=${tradeGuid}&type=${type}" class="btn-addrec" data-ac="active">新增催办</a>
</div>
</body>
</html>