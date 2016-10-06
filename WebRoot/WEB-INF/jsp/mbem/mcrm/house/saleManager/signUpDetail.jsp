<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html><html><head>
    <script type="text/javascript">
    var statistics={}
    statistics.start=+new Date();
</script>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/base.min.css">
<script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"></script>
    <title>转签约明细</title>
    <style>
        a,a:hover,a:visited,a:active{color:inherit;text-decoration:none;}
        body{background-color:#f0f0f0;}
        .no-data{position:absolute;min-height:100%;width:100%;text-align:center;display:-webkit-box;-webkit-box-align:center;}
        .no-data .content{width:100%;color:#888;}
        .no-data .content p{margin-top:10px;}
        .head{line-height:60px;padding:0 12px;font-size:16px;background-color:#18C9C9;color:#fff;}
        .head .date{float:right;font-size:15px;}
        .icon{display:inline-block;width:24px;height:24px;background-image:url(/Apps/Sales/View/default/Public/images/icons_details.png);background-size:50px 50px;vertical-align:middle;}
        .panel{background-color:#fff;}
        .panel-header{height:50px;line-height:50px;padding:0 12px;font-size:16px;border-bottom-width:1px;color:#18c9c9;}
        .panel-header .icon{margin-right:10px;}
        .panel-body .list{padding-bottom:3px;}
        .panel-body .list li{line-height:35px;position:relative;padding:0 12px;}
        .panel-body .list .name{color:#888;font-size:14px;}
        .panel-body .list .num{color:#000;font-size:12px;position:absolute;right:12px;}
        .panel-body .list .num em{font-size:14px;padding-right:5px;}
        .panel-body .total{padding:0 12px;text-align:right;color:#888;line-height:50px;border-top-width:1px;}
        .panel-body .total .num{color:#000;font-size:12px;}
        .panel-body .total .num em{font-size:20px;padding:0 5px;}
        .panel+.panel{margin-top:12px;}
        .icon.sets{background-position-x:-26px;}
        .green .icon{background-position-y:-26px;}
        .green .head{background-color:#5ac994;}
        .green .panel-header{color:#5ac994;}
        .powered{line-height:32px;}
    </style>
    <script type="text/javascript">
    	$(function ($) {
			if (window.history && window.history.pushState) {
			$(window).on('popstate', function () {
		        var hash = window.location.hash;
		        if (hash === '') {
		            window.location.href="/mbem/mcrm/house/saleManager/index.action";
		        }
		    });
		    var url = window.location.url;
		    window.history.pushState('forward', null, url);
		    location.hash="#1";
			}
		});    	 
    </script>
</head>
<body>
	<div class="box-wrap " style="min-height: 794px;">
	    <div class="head">
	        <span class="name">转签约明细</span>
	        <span class="date">${signUpDateRange}</span>
	    </div>
	    <div class="panel">
	    		        <div class="panel-header border-1px">
	            <i class="icon money"></i>按金额
	        </div>
	        	        <div class="panel-body">
	            <ul class="list">
	            	            		                <li><span class="name">新增转签约</span><span class="num"><em>0</em>元</span></li>
	            		            	            		            	            		            	            		            	            		            	            		            	            </ul>
	            <div class="total border-1px">
	                净转签约<span class="num"><em>0</em>元</span>
	            </div>
	        </div>
	    </div>
	    	    <div class="panel">
	        <div class="panel-header border-1px">
	            <i class="icon sets"></i>按套数
	        </div>
	        <div class="panel-body">
	            <ul class="list">
	            		            		                <li><span class="name">新增转签约</span><span class="num"><em>0</em>套</span></li>
	            		            		            		            		            		            		            		            		            		            		            		            		            </ul>
	            <div class="total border-1px">
	                净转签约<span class="num"><em>0</em>套</span>
	            </div>
	        </div>
	    </div>
	    	</div>
	<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
	<script>
	    $(function(){
	        $("body > .box-wrap").css({
	            "min-height":$(window).height()-32
	        });
	    });
	</script>

</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html>