<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>跟进分析</title>
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.min.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
<script src="/common/js/jquery-1.9.1.min.js"></script>
<script>
	var statistics = {}
	statistics.start = +new Date();
</script>
<script>
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
<style>
body {
	background: #f0f0f0;
	-webkit-overflow-scrolling: touch;
}

header {
	background: #fff;
	width: 100%;
	height: 42px;
}

.header {
	padding: 12px 12px 0 12px;
}

header ul {
	display: -webkit-box;
	width: 100%;
	border: 1px solid #34CCA6;
	border-radius: 5px;
	height: 28px;
	line-height: 28px;
}

header ul li {
	-webkit-box-flex: 1;
	height: 100%;
	vertical-align: middle;
	text-align: center;
	font-size: 16px;
	cursor: pointer;
	border-right: 1px solid #33cc66;
	color: #34CCA6;
}

header ul li a {
	display: block;
	color: #34CCA6;
	text-decoration: none;
}

header ul li:last-child {
	border: 0;
}

header ul li.a_act {
	background: #34CCA6;
	color: #fff;
}

header ul li.a_act a {
	background: #34CCA6;
	color: #fff;
}

.nav {
	height: 44px;
}

nav {
	background: #fff;
	position: static;
	top: 54px;
	width: 100%;
	height: 44px;
	z-index: 10;
}

nav ol {
	padding: 9px 0 8px;
	margin: 0 12px;
	font-size: 0;
}

nav ol li {
	display: inline-block;
	width: 16.5%;
	font-size: 14px;
	color: #aaa;
	height: 16px;
	line-height: 16px;
	padding: 6px 0;
	text-align: center;
}

nav ol li a {
	display: block;
	color: #666;
	text-decoration: none;
}

nav ol li.a_act {
	background: #34CCA6;
	color: #fff;
	border-radius: 30px;
}

nav ol li.a_act a {
	color: #fff;
	text-decoration: none;
}

figure .a_li:nth-child(3) a {
	border-bottom: 0;
}

figure .a_li:nth-child(4) a {
	border-bottom: 0;
}

header ol li a {
	color: #aaa;
	text-decoration: none;
}

figure {
	background: #f8f8fe;
	margin: 0;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
}

figure .a_li {
	width: 50%;
}

figure .a_li:nth-child(1) {
	background: url("/mbem/mcrm/business/images/call_in.png") no-repeat 15px
		center;
	background-size: 32px;
}

figure .a_li:nth-child(2) {
	background: url("/mbem/mcrm/business/images/visit_in.png") no-repeat
		15px center;
	background-size: 32px;
}

figure .a_li:nth-child(3) {
	background: url("/mbem/mcrm/business/images/must_buy.png?v=1") no-repeat
		15px center;
	background-size: 32px;
}

figure .a_li:nth-child(4) {
	background: url("/mbem/mcrm/business/images/overdue.png?V=1") no-repeat
		15px center;
	background-size: 32px;
}

figure .a_li a {
	display: block;
	width: 100%;
	height: 100%;
	padding: 17px 0;
	border-bottom: 1px dotted #ddd;
	color: #666;
	text-decoration: none;
}

figure .a_li a.active {
	background-color: rgba(0, 0, 0, 0.1)
}

figure .a_li a div {
	border-right: 1px dotted #ddd;
	padding: 0px 15px 0px 0;
	text-align: right;
}

center dt {
	background: white url("images/arrow-down-grey.png") no-repeat 96% center;
	background-size: 15px;
	text-align: left;
	overflow: hidden;
	padding: 14px 12px;
	border-bottom: 1px solid #ddd;
}

center dt.on {
	background: url("images/arrow-up-grey.png") no-repeat 96% center;
	background-size: 15px;
	text-align: left;
	overflow: hidden;
	padding: 14px 12px;
	border-bottom: 1px solid #ddd;
}

center dt+dd {
	display: none;
	overflow: hidden;
}

center dt.on+dd {
	display: block;
}

center dt t {
	background: red;
	display: inline-block;
	width: 16px;
	height: 16px;
	float: left;
	border-radius: 50%;
}

center dt span {
	display: inline-block;
	line-height: 16px;
	float: left;
	margin-left: 0px;
	font-size: 17px;
}

.newDt {
	background: white url("images/arrow-down-grey.png") no-repeat 96% center;
	background-size: 15px;
	text-align: left;
	overflow: hidden;
	padding: 14px 12px;
	border-bottom: 1px solid #ddd;
}

dt.on {
	background: url("images/arrow-up-grey.png") no-repeat 96% center;
	background-size: 15px;
	text-align: left;
	overflow: hidden;
	padding: 14px 12px;
	border-bottom: 1px solid #ddd;
}

.newDt+dd {
	display: none;
	overflow: hidden;
}

.newDt.on+dd {
	display: block;
}

.newDt t {
	background: red;
	display: inline-block;
	width: 16px;
	height: 16px;
	float: left;
	border-radius: 50%;
}

.newDt span {
	display: inline-block;
	line-height: 16px;
	float: left;
	margin-left: 0px;
	font-size: 17px;
}

center q {
	background: #f99;
	color: #fff;
	font-size: 12px;
	padding: 1px 6px 1px;
	font-weight: normal;
	border-radius: 5px;
	display: inline;
}

center dl:nth-child(1n) t {
	background: #ffcfbf;
}

center dl:nth-child(2n) t {
	background: #d6c8ff;
}

center dl:nth-child(3n) t {
	background: #8de9cb;
}

center dl:nth-child(4n) t {
	background: #99e5ff;
}

center dl:nth-child(5n) t {
	background: #faafcf;
}

center dl:nth-child(6n) t {
	background: #ffe599;
}

center p.a_tit {
	padding: 10px 12px;
	font-size: 14px;
	color: #333;
	border-bottom: 1px solid #ddd;
}

center p.a_tit span {
	float: left;
	width: 33%;
}

center p.a_tit span:first-child {
	text-align: left;
}

center p.a_tit span:last-child {
	text-align: right;
}

.newPp {
	padding: 10px 12px;
	font-size: 14px;
	color: #333;
	border-bottom: 1px solid #ddd;
}

.newPp span {
	float: left;
	width: 33%;
}

.newPp span:first-child {
	text-align: left;
}

.newPp span:last-child {
	text-align: right;
}

center dl {
	background: #fff;
	margin-bottom: 5px;
}

.a_count_lists i,.a_count_lists b {
	font-style: normal;
	font-weight: normal;
}

.a_count_lists li a {
	display: block;
	text-decoration: none;
	font-size: 12px;
	color: #000;
	padding-left: 12px;
}

.ac_div {
	padding: 5px 0 5px 0px;
	border-bottom: 1px solid #ddd;
	padding-bottom: 5px;
}

.a_count_lists li a i {
	display: block;
	height: 20px;
	font-size: 16px;
	padding: 6px 0 10px;
	width: 100%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	text-overflow: ellipsis;
}

.a_count_lists ul li a i {
	color: #55ce80;
}

.a_count_lists li a font {
	font-size: 18px;
	color: #333;
	vertical-align: -3px;
}

.a_count_lists li a p {
	float: left;
	width: 33%;
}

.a_count_lists li a p:first-child {
	text-align: left;
}

.a_count_lists li a p:nth-child(2) {
	padding-right: 12px;
	box-sizing: border-box;
}

.a_count_lists li a p:last-child {
	text-align: right;
	padding-right: 12px;
	box-sizing: border-box;
}

.a_count_lists li a.act_a {
	background: #ddd;
}

.a_loading {
	margin: 0 auto;
	min-height: 60px;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	z-index: 999;
	background: url(images/indicator_mozilla_blu.gif) center center
		no-repeat;
}

.a_shadow {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: 99;
}

.a_li_num p {
	line-height: 20px;
}

.a_user_name {
	text-align: left;
	padding-top: 11px;
}

.a_user_name span {
	color: #55ce80;
	display: inline-block;
	height: 18px;
	font-size: 16px;
	padding: 0px;
	max-width: 70%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	text-overflow: ellipsis;
}

.a_count_lists li a .a_user_name i {
	display: inline-block;
	width: 30%;
	padding: 0;
}

.a_custom_time {
	padding-left: 12px;
	position: relative;
	background-color: #E8E8E8;
	font-size: 14px;
}

.a_custom_time:after {
	content: "";
	position: absolute;
	top: -14px;
	right: 10%;
	width: 0;
	border-width: 8px 10px;
	border-style: solid;
	border-color: transparent transparent #e8e8e8 transparent;
}

.nav-detail {
	height: 38px;
	line-height: 38px;
	border-top-width: 1px;
	background-color: #E8E8E8;
	font-size: 14px;
	padding-left: 16px;
}

.nav-detail .sep {
	display: inline-block;
	width: 24px;
	height: 24px;
	vertical-align: 0px;
	line-height: 24px;
	text-align: center;
	border-radius: 12px;
	background-color: #666;
	color: #fff;
	font-size: 12px;
	margin: 0 10px;
}

.twotimehide {
	display: none !important;
}

.time-wrap {
	position: relative;
	z-index: 1001;
	display: none;
	width: 100%;
	overflow: hidden;
}

.time-wrap ul {
	background-color: #fff;
	margin-top: 12px;
}

.time-wrap li {
	height: 44px;
	line-height: 44px;
	margin: 0 12px;
	color: #333;
	position: relative;
	padding-left: 82px;
	background: url(/Public/myui_v1/images/handover_right_default.png)
		no-repeat 98% center;
	background-size: 9px 14px;
	-webkit-background-size: 9px 14px;
}

.time-wrap li.active {
	background-color: #d9d9d9;
}

.time-wrap li.border-1px {
	border-bottom-width: 1px;
}

.time-wrap li .k {
	position: absolute;
	left: 0;
	top: 0;
	font-size: 16px;
}

.time-wrap li .v {
	width: 100%;
	display: block;
}

.time-wrap li input {
	width: 100%;
	border: 0;
	-webkit-appearance: none;
	font-size: 16px;
	line-height: 42px;
	background: transparent;
	color: #666;
}

.time-wrap .btns {
	margin-top: 30px;
	padding: 0 12px;
	text-align: center
}

.time-wrap .btns span {
	display: inline-block;
	height: 40px;
	line-height: 40px;
	width: 45%;
	border-radius: 5px;
	font-size: 16px;
	text-align: center;
	overflow: hidden;
	box-sizing: border-box;
	vertical-align: middle;
}

.time-wrap .cancel {
	margin-right: 2%;
	color: #34CCA6;
	border: 1px solid #34CCA6;
}

.time-wrap .cancel.active {
	background-color: #34CCA6;
	color: #fff;
	border: none;
}

.time-wrap .sure {
	background-color: #34CCA6;
	color: #fff;
	border-radius: 5px;
}

.time-wrap .sure.active {
	background-color: #33A588;
}

.time-panel {
	position: fixed;
	top: 0;
	bottom: 0;
	background-color: #F7F7F7;
	z-index: 1000;
	width: 100%;
	left: 0;
}
/*弹出框效果*/
#mysoft_popup_mask {
	background-color: #222;
	display: none;
	position: fixed;
	z-index: 999998;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	opacity: 0.3;
	height: 100%;
	width: 100%;
}

#mysoft_popup {
	color: #222;
	-webkit-box-shadow: 0px 1px 10px rgba(0, 0, 0, 0.6);
	display: none;
	position: fixed;
	left: 0;
	right: 0;
	z-index: 999999;
	min-height: 50px;
}

#mysoft_popup.loading {
	top: 50%;
	left: 50%;
	margin: -50px 0 0 -62px;
	opacity: .78;
	text-align: center;
	width: 125px;
	height: 100px;
	border-radius: 5px;
	background-color: #000;
}

#mysoft_popup.loading .img {
	border: none;
	margin-top: 22px;
	width: 100%;
	height: 35px;
	background-image: url(/Public/dest/popup/images/loading35@35.gif);
	background-size: 35px;
	background-position: center center;
	background-repeat: no-repeat;
}

#mysoft_popup.alert,#mysoft_popup.confirm {
	top: 50%;
	left: 5%;
	right: 5%;
	border-radius: 5px;
	background-color: #fff;
}

#tag_close_popup {
	color: #BDC3C7;
	position: absolute;
	top: 0;
	right: 0;
	font-size: 1em;
	padding: 5px 5px 10px 10px;
}

#mysoft_popup.loading p {
	font-size: 17px;
	color: #fff;
	padding-top: 9px;
	margin: 0px;
}

#mysoft_popup.loading i.icon {
	color: #fff;
	font-size: 4em;
	line-height: 110px;
	margin: 0;
	display: inline-block;
	-webkit-animation: spinner .5s infinite linear;
}

.popup-title {
	background: #fff;
	color: #000;
	text-align: center;
	padding: 14px;
	font-size: 16px;
	font-weight: bold;
	border-radius: 5px;
}

.popup-content {
	text-align: center;
	padding: 0 14px 10px 14px;
	line-height: 1.2em;
	font-size: 14px;
	border-radius: 5px;
	color: #000;
}

#popup_btn_container {
	text-align: center;
	margin-top: 10px;
	display: -webkit-box;
	display: box;
}

#popup_btn_container>a {
	font-size: 16px;
	-webkit-box-flex: 1;
	box-flex: 1;
	padding: 12px 10px;
	display: block;
	color: #0c7bf7;
	border: 1px solid #d9d9d9;
	border-left: none;
	border-bottom: none;
	border-right: 0;
}

#popup_btn_container>a:active {
	background-color: #d9d9d9;
}

#popup_btn_container>a:first-child {
	border-right: 1px solid #d9d9d9;
	border-bottom-left-radius: 5px;
}

#popup_btn_container>a:last-child {
	border-bottom-right-radius: 5px;
}

.time-wrap ul {
	background-color: #f0f0f0;
	padding-bottom: 30px;
}

.time-wrap ul li {
	background-color: #fff;
	margin: 0;
}

.time-wrap li .k {
	padding-left: 12px;
}

.time-wrap .btns {
	margin-top: 0px;
}
</style>
<style type="text/css" class="dateinput">
.date-input-wrap {
	position: fixed;
	z-index: 99999;
	top: 0;
	bottom: 0;
	left: 0;
	width: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	font-family: sans-serif;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0)
}

.date-input-wrap .center {
	position: absolute;
	text-align: center;
	top: 50%;
	width: 100%;
}

.date-input-wrap .title {
	height: 60px;
	text-align: left;
	line-height: 60px;
	color: #3c6;
	font-size: 18px;
	border-bottom: 1px solid #ddd;
}

.date-input-wrap .title-icon {
	display: inline-block;
	margin: -2px 8px 0px 15px;
	vertical-align: middle;
	height: 24px;
	width: 24px;
	-webkit-background-size: 24px 24px;
	background-image:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAMAAABg3Am1AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAADNQTFRFzfPZ8/32muayQtBuZ9qLTtN4geCfW9aB2vbijeOps+3F5vrtwPDPpuq8dN2UNs1k////DqGdwwAAAbhJREFUeNqMlum6xBAMhoNai7j/qz0fbed0oZU/85S8ETFZqDxkjdIskCyDe+7SXdtbPok29ArQAiUF0wTBQboyYQisUFcmnredB6OpDySoS/FwOgAx4gmIfFk/iZDM1t0BYVnFMhCnWbkrAH3tylCqOXcGsGBv7jBfPs1B0PG5llfgZ7IBkfnhzw0oQrM/AKE4lS+gOGbaAc9L+QagZjdg7TjUA+AINaB7QAcosioCaOAMgCNWAJF1mQMQfQ/Ab+GaASKuTcV2PeoCWBTU3xkACxO5Ft1JwHMi6gZ1AEiWAPw8kAAAmgeIDYV/gK9SenGtLuV5YLvDUqalAoLVPFDfodR/1KxodlQyh1n9Fd4QYptngcQGABJOTAKWY02gPHi6zrOplnH4FZMxklvV0HNHbIZbqeGZyIrNbsu2PMiJW0LrX6lEOTBf+mGvXrS793UNlN907g/g/Yd9c+1AYdSwjvZn7j0unBvZLT75X//URdHI2PcOSYpV6LVd4dF3/e1FRIKdZR1MAm0QsPK35lKuo0B8mTXIqDZi1OFEt8zO4X04KSL65SgD1gTxNc3sqVWHk37M/gQYAOv6g+k3lRpPAAAAAElFTkSuQmCC);
}

.date-input-wrap .title>span:last-child {
	padding-left: 8px;
}

.date-input-wrap .wrap {
	width: 288px;
	border: 2px solid #777;
	margin: -144px auto 0;
	background-color: #fff;
	text-align: center;
}

.date-input-wrap .content {
	width: 252px;
	height: 178px;
	margin: 0 auto;
	white-space: nowrap;
	font-size: 0;
}

.date-input-wrap .content .iscroll-wrap {
	display: inline-block;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	width: 76px;
	height: 178px;
	width: 33%;
	position: relative;
}

.date-input-wrap .content .iscroll-wrap:nth-child(2) {
	width: 34%;
}

.date-input-wrap .content .iscroll-wrap:after {
	content: "";
	position: absolute;
	pointer-events: none;
	top: 0px;
	width: 100%;
	left: 0;
	bottom: 0;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(255,
		255, 255, 1)), color-stop(10%, rgba(255, 255, 255, 1)),
		color-stop(38%, rgba(255, 255, 255, 0)),
		color-stop(62%, rgba(255, 255, 255, 0)),
		color-stop(90%, rgba(255, 255, 255, 1)),
		color-stop(100%, rgba(255, 255, 255, 1))); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top, rgba(255, 255, 255, 1) 0%,
		rgba(255, 255, 255, 1) 10%, rgba(255, 255, 255, 0) 38%,
		rgba(255, 255, 255, 0) 62%, rgba(255, 255, 255, 1) 90%,
		rgba(255, 255, 255, 1) 100%); /* Chrome10+,Safari5.1+ */
	background: linear-gradient(to bottom, rgba(255, 255, 255, 1) 0%,
		rgba(255, 255, 255, 1) 10%, rgba(255, 255, 255, 0) 38%,
		rgba(255, 255, 255, 0) 62%, rgba(255, 255, 255, 1) 90%,
		rgba(255, 255, 255, 1) 100%); /* W3C */
}

.date-input-wrap .iscroll {
	position: absolute;
	left: 8px;
	top: 10px;
	right: 8px;
	height: 156px;
	overflow: hidden;
}

.date-input-wrap .buttons {
	border-top: 1px solid #ddd;
}

.date-input-wrap .date-btn {
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	padding: 2px;
	color: #3c6;
}

.date-input-wrap .date-btn.active {
	background-color: #d9d9d9;
}

.date-input-wrap .date-btn span {
	display: block;
	height: 40px
}

.date-input-wrap ul {
	padding: 0px 0 52px 0;
	text-align: center;
	margin: 0;
}

.date-input-wrap li {
	list-style: none;
	line-height: 52px;
	height: 52px;
	font-size: 18px;
	color: #fff;
	z-index: 2;
	color: #333;
}

.date-input-wrap .content .iscroll:before,.date-input-wrap .content .iscroll:after
	{
	content: "";
	position: absolute;
	top: 33%;
	left: 8px;
	right: 8px;
	height: 2px;
	background-color: #3c6;
}

.date-input-wrap .content .iscroll:after {
	top: 66%;
}
</style>
</head>

<body>
	<header>
		<div class="header">
			<ul>
				<li class="a_act"><a href="javascript:void(0);" data-url="#">跟进分析</a>
				</li>
				<!-- <li><a href="javascript:void(0);" data-url="#"> 转化率分析 </a></li> -->
				<li><a href="javascript:void(0);" data-url="/mbem/mcrm/house/custAnalysis/dealReport.action?flg=today"> 成交分析 </a></li>
			</ul>
		</div>
	</header>
	<div class="nav">
		<nav>
			<ol class="datelist">
				<li id="total"><a href="javascript:void(0);" data-url="/mbem/mcrm/house/custAnalysis/followReport.action?flg=alltime">累计</a></li>
				<li id="yesterday"><a href="javascript:void(0);"
					data-url="/mbem/mcrm/house/custAnalysis/followReport.action?flg=yesterday">昨日</a></li>
				<li class="a_act" id="today"><a href="javascript:void(0);"
					data-url="/mbem/mcrm/house/custAnalysis/followReport.action?flg=today">今日</a></li>
				<li id="curweek"><a href="javascript:void(0);"
					data-url="/mbem/mcrm/house/custAnalysis/followReport.action?flg=curweek">本周</a></li>
				<li id="curmonth"><a href="javascript:void(0);"
					data-url="/mbem/mcrm/house/custAnalysis/followReport.action?flg=curmonth">本月</a></li>
				<!-- <li range="other" id="a_default"><a href="javascript:void(0);">自定义</a>
				</li> -->
				<li id="zdy"><a href="/mbem/mcrm/house/custAnalysis/followReportzdy.action">自定义</a>
				</li>
			</ol>
		</nav>
	</div>
	<article>
		<figure class="clearfix" style="height:75px;">
			<div class="a_li fl">
				<a class="a_li_num" data-ac="active"
					href="#">
					<div>
						<p>
							<span>${totalcount.fromTel}</span>
						</p>
						<p>
							<em>来电客户</em>
						</p>
					</div>
				</a>
			</div>
			<div class="a_li fl">
				<a class="a_li_num" data-ac="active"
					href="#">
					<div>
						<p>
							<span>${totalcount.fromInterview}</span>
						</p>
						<p>
							<em>来访客户</em>
						</p>
					</div>
				</a>
			</div>
			<!-- <div class="a_li fl">
				<a class="a_li_num" data-ac="active"
					href="#">
					<div>
						<p>
							<span></span>
						</p>
						<p>
							<em>必买客户</em>
						</p>
					</div>
				</a>
			</div>
			<div class="a_li fl">
				<a class="a_li_num" data-ac="active"
					href="#">
					<div>
						<p>
							<span></span>
						</p>
						<p>
							<em>逾期客户</em>
						</p>
					</div>
				</a>
			</div> -->
		</figure>
	</article>
	<article>
		<center>
		<c:forEach items="${grouplist}" var="groups" varStatus="">

			<dl>
				<dt class="on">
					<p class="clearfix down">
						<!--<t></t>-->
						<span>销售${groups.groups}组</span>
					</p>
				</dt>
				<dd>
					<p class="a_tit clearfix">
						<span>来电</span>
						<span>来访</span>
						<!-- <span>必买</span> -->
					</p>
					<div class="a_count_lists">
						<ol>
							<li><a class="clearfix" href="javascript:void(0);">
									<div class="ac_div clearfix">
										<p>
											<i>总计</i> <b> <font>${groups.fromTel}</font> 人
											</b>
										</p>
										<p>
											<i></i> <b><font>${groups.fromInterview}</font> 人</b>
										</p>
										<!-- <p>
											<i></i> <b><font>0</font> 人</b>
										</p> -->
									</div>
							</a> 
							<!-- <a class="clearfix" href="javascript:void(0);">
									<div class="ac_div clearfix">
										<p>
											<i>平均</i> <b> <font>0</font> 人
											</b>
										</p>
										<p>
											<i></i> <b><font>0</font> 人</b>
										</p>
										<p>
											<i></i> <b><font>0</font> 人</b>
										</p>
									</div>
							</a> -->
						</li>
						</ol>
						<ul>
							<li>
							<c:forEach items="${ywy_grouplist}" var="ywylist" varStatus="status">
								<c:if test="${groups.groups==ywylist.groups }">
									<a class="clearfix" data-ac="act_a" href="#">
										<div class="a_user_name clearfix">
												<span>${ywylist.ywy}</span>
												<!-- <i><q style="display: none;">逾0</q></i> -->
										</div>
										<div class="ac_div clearfix">
											<p>
												<font>${ywylist.fromTel}</font> 人
											</p>
											<p>
												<b><font>${ywylist.fromInterview}</font> 人</b>
											</p>
											<!-- <p>
												<b><font>0</font> 人</b>
											</p> -->
										</div>
									</a>
								</c:if>
							</c:forEach>
							</li>
						</ul>
					</div>
				</dd>
			</dl>
		</c:forEach>
		</center>
	</article>
	<script  src="/mbem/mcrm/business/js/base.min.js"></script>

	<div class="time-wrap" id="time-wrap">
		<ul>
			<li data-ac="active" class="border-1px"><span class="k">开始时间：</span><span
				class="v"><input class="btime"
					data-range="2010/1/1-2018/12/30" type="text" readonly></span></li>
			<li data-ac="active"><span class="k">结束时间：</span><span class="v"><input
					class="etime" data-range="2010/1/1-2018/12/30" type="text" readonly></span></li>
		</ul>
		<div class="btns">
			<span data-ac="active" class="cancel">取消</span> <span
				data-ac="active" class="sure">确定</span>
		</div>
	</div>


	<script>
	var flg="${flg}";
	$(".datelist li").each(function(index,element){
		$(element).removeClass();
		switch (flg) {
			case "alltime":
				$("#total").addClass("a_act");
				break;
			case "yesterday":
				$("#yesterday").addClass("a_act");
				break;
			case "today":
				$("#today").addClass("a_act");
				break;
			case "curweek":
				$("#curweek").addClass("a_act");
				break;
			case "curmonth":
				$("#curmonth").addClass("a_act");
				break;
			case "zdy":
				$("#zdy").addClass("a_act");
				break;
			default:
				break;
		}
	});

		/**
		 * @description MYSOFT 全局对象，负责前端的交互组织
		 * @namespace 全局的命名空间
		 */
		MYSOFT = window.MYSOFT || {};

		/**
		 * @description MYSOFT界面对象，负责前端的公共控件
		 * @namespace MYSOFT界面的命名空间
		 */
		MYSOFT.Ui = window.MYSOFT.Ui || {};

		/**
		 * @description MYSOFT全局配置
		 * @namespace MYSOFT全局配置的命名空间
		 */
		MYSOFT.Config = window.MYSOFT.Config || {};

		/**
		 * @description MYSOFT工具
		 * @namespace MYSOFT工具的命名空间
		 */
		MYSOFT.Util = window.MYSOFT.Util || {};

		/**
		 * @description MYSOFT Dom命名空间,负责Dom操作
		 * @namespace MYSOFT Dom命名空间
		 */
		MYSOFT.Dom = window.MYSOFT.Dom || {};
		/**
		 * @namespace MYSOFT弹出框组件类
		 */
		$.Ui = {};
		$.Ui.Popup = MYSOFT.Ui.Popup = {
			//初始化Dom对象到文档
			Init : function() {
				if (!MYSOFT.Ui.Popup.DomObj) {
					//全局只有一个实例
					$('body')
							.append(
									'<div id="mysoft_popup"></div><div id="mysoft_popup_mask"></div>');
					MYSOFT.Ui.Popup.DomObj = $('#mysoft_popup');
					MYSOFT.Ui.Popup.MaskDomObj = $('#mysoft_popup_mask');
				}
			},
			Show : function(html, cls) {
				MYSOFT.Ui.Popup.Init();
				MYSOFT.Ui.Popup.MaskDomObj.show();
				MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass(cls).html(
						html).show();
				$('#tag_ok_popup').bind('click', function() {
					MYSOFT.Ui.Popup.HidePopUp();
				});
			},
			/**
			 * @method 显示加载动画
			 * @param text 加载文本
			 */
			ShowLoading : function(text) {
				MYSOFT.Ui.Popup.Init();
				var markup = MYSOFT.Ui.Popup.Template.loading.replace(
						'{title}', text || '正在加载');
				MYSOFT.Ui.Popup.MaskDomObj.show().css({
					'opacity' : '0'
				});
				MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('loading')
						.html(markup).show();
			},
			/**
			 * @method 隐藏弹出框
			 */
			HidePopUp : function() {
				MYSOFT.Ui.Popup.MaskDomObj.hide();
				MYSOFT.Ui.Popup.DomObj.hide().empty();
			},
			/**
			 * @method 弹出框提示
			 * @param title 提示标题
			 * @param content 提示内容
			 * @param okCall 点击确定回调函数
			 */
			ShowAlert : function(title, content, okCall) {
				var markup = MYSOFT.Ui.Popup.Template.alert.replace('{title}',
						title || '提示信息').replace('{content}', content).replace(
						'{ok}', '我知道了');
				MYSOFT.Ui.Popup.Init();
				MYSOFT.Ui.Popup.MaskDomObj.show();
				MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('alert')
						.html(markup).show();
				$('#tag_ok_popup').bind('click', function() {
					MYSOFT.Ui.Popup.HidePopUp();
					okCall && okCall();
				});
				MYSOFT.Ui.Popup.DomObj.css('margin-top',
						-(MYSOFT.Ui.Popup.DomObj.height() / 2));
			},
			/**
			 * @method 确认框提示
			 * @param title 提示标题
			 * @param content 提示内容
			 * @param okCall 确定回调函数
			 * @param cancelCall 取消回调函数
			 */
			ShowConfirm : function(title, content, okCall, cancelCall, okText,
					cancelText) {
				okText = okText || '确 定';
				cancelText = cancelText || '取 消';
				var markup = MYSOFT.Ui.Popup.Template.confirm.replace(
						'{title}', title).replace('{content}', content)
						.replace('{cancel}', cancelText)
						.replace('{ok}', okText);
				MYSOFT.Ui.Popup.Init();
				MYSOFT.Ui.Popup.MaskDomObj.show();
				MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('confirm')
						.html(markup).show();
				$('#tag_ok_popup').bind('click', function() {
					MYSOFT.Ui.Popup.HidePopUp();
					okCall && okCall();
				}).bind('touchstart', function(e) {
					e.stopPropagation();
				});
				$('#tag_cancel_popup').bind('click', function() {
					MYSOFT.Ui.Popup.HidePopUp();
					cancelCall && cancelCall();
				}).bind('touchstart', function(e) {
					e.stopPropagation();
				});
				MYSOFT.Ui.Popup.DomObj.css('margin-top',
						-(MYSOFT.Ui.Popup.DomObj.height() / 2));
			},
			/**
			 * @object Popup模版
			 */
			Template : {
				//加载动画
				loading : '<div class="img"></div><p>{title}</p>',
				//提示框
				alert : '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_ok_popup">{ok}</a></div>',
				//确认提示框
				confirm : '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_cancel_popup">{cancel}</a><a id="tag_ok_popup">{ok}</a></div>'
			}
		};
	</script>
	<script type="text/javascript">
		(function($) {
			var hidepanel = 1;
			var timeWrap = $("#time-wrap"), callbackfunc, isclose = false;
			$.showTwoTime = function(callback, closed) {
				//第一个参数为点击 确定时候的回调函数，第二个参数为回调的时候时候关闭时间控件面板。
				$.hash.setHash("twotime", "1");
				callbackfunc = callback;
				isclose = closed;
			};
			$(window).on("hashchange", function() {
				var hash = $.hash.getHash("twotime");
				if (hash) {
					$("body > *").addClass("twotimehide");
					timeWrap.removeClass("twotimehide").show();
				} else if (hidepanel || isclose) {
					$("body > *").removeClass("twotimehide");
					timeWrap.hide();
					$(".date-input-wrap").remove();
				}
			});
			timeWrap.find(".cancel").on("click", function() {
				history.back();
			});
			timeWrap.find(".sure").on("click", function() {
				var btime = timeWrap.find(".btime").val();
				var etime = timeWrap.find(".etime").val();
				if (btime && etime) {
					if ((btime != etime) && (getTime(etime) < getTime(btime))) {
						MYSOFT.Ui.Popup.ShowAlert("提示", "结束时间要大于开始时间", null);
					} else {
						hidepanel = 0;
						setTimeout(function() {
							callbackfunc && callbackfunc(btime, etime);
						}, 10);
						history.back();

					}
				} else {
					MYSOFT.Ui.Popup.ShowAlert("提示", "开始、结束时间都不能为空", null);
				}
			});
			function getTime(str) {
				var arr = str.split("-");
				return new Date(arr[0], arr[1] - 1, arr[2], 0, 0, 0, 0);
			}
		})(Zepto);
		$(function() {
			$.hash.clearHash();
		});
	</script>

	<script>
		(function($) {
			var Swift = {
				toggleAct : function(param, className) {
					param.addClass(className).siblings().removeClass(className);
				},
				toggleDd : function(param, className) {
					param.toggleClass(className);
				},
				aLoading : function(param) {
					param.css('top', $(window).height() / 2 - 60).show();
				},
				hideLoading : function(param) {
					param.remove();
				},
				aShadow : function(param) {
					param.show();
				},
				hideShadow : function(param) {
					param.remove();
				}
			};
			Swift.aLoading($('.a_loading'));
			Swift.aShadow($('.a_shadow'));
			//滑动置顶
			var nav = $('nav');
			var dl = $('center dl'); //dl
			var header = $('header'); //头部
			var customTime = $('.a_custom_time'); //自定义时间
			$(window)
					.scroll(
							function() {
								var isFixedFlag = false;
								for (var i = 0; i < dl.length; i++) {
									if (dl.eq(i).get(0).getBoundingClientRect().top <= 44) {
										isFixedFlag = true;
										var dtOrigin = $('dt', dl.eq(i));
										$('dt', dl.eq(i))
												.clone()
												.appendTo($('body'))
												.addClass('newDt')
												.css(
														{
															'position' : 'fixed',
															'top' : '44px',
															'left' : '0',
															'right' : '0',
															'background-color' : 'white'
														})
												.on('click', function() {
													dtOrigin.trigger('click');
													var that = $(this);
													//显示每个团队的数据
													if (that.hasClass('on')) {
														that.removeClass('on');
													} else {
														that.addClass('on');
													}
												});
										$('p.a_tit', dl.eq(i))
												.clone()
												.appendTo($('body'))
												.addClass('newPp')
												.css(
														{
															'position' : 'fixed',
															'top' : '89px',
															'left' : '0',
															'right' : '0',
															'background-color' : 'white'
														/* 'border-top': '1px solid #ddd'*/
														});
									} else {
										if ($('dt', dl.eq(i)).length == 1) {
											$('dt', dl.eq(i)).css({
												'position' : 'static'
											});
										} else {
											$('dt', dl.eq(i)).css({
												'position' : 'static',
												'border' : '0'
											});
										}
										$('p.a_tit', dl.eq(i)).css({
											'position' : 'static'
										});
									}
								}
								if (isFixedFlag == false) {
									$('.newDt').remove();
									$('.newPp').remove();
								}
								if (nav.get(0).getBoundingClientRect().top <= 0) {
									nav.css({
										'position' : 'fixed',
										'top' : '0px',
										'left' : '0',
										'right' : '0',
										'background-color' : 'white',
										'border-top' : '1px solid #ddd'
									});
								} else {
									nav.css({
										'position' : 'static',
										'border' : '0'
									});
								}
								/*            if(customTime.get(0).getBoundingClientRect().top <= 44){
								 customTime.css({
								 'position': 'fixed',
								 'top': '44px',
								 'left': '0',
								 'right': '0'
								 });
								 }else{
								 customTime.css({
								 'position': 'relative',
								 'top': '0'
								 });
								 }*/
								if (header.get(0).getBoundingClientRect().top >= 0) {
									nav.css({
										'position' : 'static',
										'border' : '0'
									});
									customTime.css({
										'position' : 'relative',
										'top' : '0'
									});
								}
							});
			//自定义
			$('#a_default')
					.on(
							'click',
							function() {
								$(window).on('touchmove', preventFlase);
								$
										.showTwoTime(function(btime, etime) {
											window.location
													.replace("#"
															+ "&range="
															+ btime
															+ "/" + etime);
										});
							});
			$('.btns span').on('click', function() {
				$(window).off('touchmove', preventFlase);
			});
			function preventFlase() {
				return false;
			}
			var dlDt = $('center dl dt');
			
			//拉取页面数据
		
			dlDt.on('click', function() {
				$(this).css('position', 'static');
				var that = $(this);
				//显示每个团队的数据
				if (that.hasClass('on')) {
					that.removeClass('on');
				} else {
					that.addClass('on');
				}
			});
			//链接跳转
			$(".datelist li").on('click', function(o) {
				if ($(this).attr('range') != 'other') {
					window.location.replace($('a', $(this)).attr('data-url'));
				}
			});
			
			//菜单跳转
			$("header li").on('click', function(o) {
				window.location.replace($('a', $(this)).attr('data-url'));
			});
		})(Zepto);
		
	</script>
</body>
<iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe>
<iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe>
</html>