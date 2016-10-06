<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>      
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no"> 
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"  href="/mbem/mcrm/business/css/base.min.css">
<script src="/common/js/jquery-1.9.1.min.js"></script>
<script >
    var statistics={}
    statistics.start=+new Date();
</script>

    <title></title>
    <style>
        body{background:#f0f0f0;padding:12px;box-sizing:border-box;}
        .a_rela{position:relative;}
        .container{background:#fff;border-radius:5px;}
        .container .a_title{background:url("/mbem/mcrm/business/images/_r2_c4.png") no-repeat 20px center;
            background-size:70px;padding:25px 12px;}
        .container dt{font-size:17px;color:#000;line-height:26px;}
        .container dd{color:#EB6657;line-height:24px;}
        .container dd span{color:#EB6657;font-size:16px;}
        .container dd font{color:#888;font-size:12px;}
        .container li .fl{color:#000;font-size:14px;}
        .container li .fr font{color:#666;font-size:14px;}
        .container ul{padding:12px;}
        .container li{line-height:14px;margin:10px 0;}
        .container li .fr t{color:#888;font-size:12px;}
        .a_hr{font-size:0px;line-height:0px;margin:0 12px 16px;}
        .a_note{font-size:14px;color:#000;margin:0 12px;line-height:22px;}
        .a_note_desc{font-size:12px;color:#666;padding:0 12px 12px;line-height:22px;}
        .border-1px{border-top-width:1px;}
        .a_split{background:url("/mbem/mcrm/business/images/dote.png") repeat-x center center;
            background-size:8px 3px;height:12px;font-size:0;line-height:0px;}
        .a_split span{background:#f0f0f0;border-radius:50%;height:10px;width:10px;}
        .a_split .fl{margin-left:-5px;}
        .a_split .fr{margin-right:-5px;}
        .icon-wrapper {
            position: fixed;
            top: 50%;
            width: 100%;
            margin-top: -43px;
            margin-left: -12px;
        }
        .icon_crown {
            width: 71px;
            height: 51px;
            margin: 0 auto;
            background-size: 71px 51px;
            background-image: url(/Apps/Sales/View/default/Public/images/icon_hg.png)
        }
        .no_bussness {
            text-align: center;
            margin-top :20px;
            font-size: 14px;
            line-height: 14px;
            color: #666;
        }
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
<body  style="min-height: 826px;">
<div class="a_wrap" style="min-height: 778px;">
	    <div class="container">
        <div class="a_title clearfix">
            <dl class="fr">
                <dt>净成交金额</dt>
                <dd>
                    <span>2,630.87</span>
                    <font>万元</font>
                </dd>
            </dl>
        </div>
        <div class="a_split a_rela clearfix">
            <span class="fl"></span>
            <span class="fr"></span>
        </div>
        
        <ul>
                                    <li class="clearfix">
                <span class="fl">新增认购</span>
            <span class="fr">
                <font>+31,197,200</font>
                <t>元</t>
            </span>
            </li>
                                                                                                <li class="clearfix">
                <span class="fl">价格变更差额</span>
            <span class="fr">
                <font>-378,002</font>
                <t>元</t>
            </span>
            </li>
                                                <li class="clearfix">
                <span class="fl">退房</span>
            <span class="fr">
                <font>-4,510,492</font>
                <t>元</t>
            </span>
            </li>
                                </ul>
    </div>
    </div>
<style>
    .powered{
        margin-bottom: 6px;
        font-size: 11px;
        color: #aaa;
        text-align: center;
        width: 100%;
    }
    .powered a{
        color: #aaa;
        text-decoration: none;
    }
    .powered a:hover,.powered a:visited{
        color: #aaa;
    }
    .powered a:active{
        color: #2caf56;
    }
</style>
<div class="powered" id="powered">
    Powered&nbsp;by&nbsp;&nbsp;<a href="#">兆盛地产</a>
</div>
<style>
    .powered{margin-top:6px;margin-bottom:0;}
</style>
<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
<script>
    $('body').css('min-height', document.documentElement.clientHeight);
    $('.a_wrap').css('min-height', document.documentElement.clientHeight - 48);
</script>

</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html>