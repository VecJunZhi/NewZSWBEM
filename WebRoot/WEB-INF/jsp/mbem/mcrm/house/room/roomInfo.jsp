<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK" %>
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
<link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/bmin.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/mobile/ui.css">
<title>房源信息</title>
 <script  src="/common/js/jquery-1.9.1.min.js"></script>
<!-- <script>
    var statistics={}
    statistics.start=+new Date();
</script>
<script>
        statistics.t1=+new Date()-statistics.start;
</script>-->
<script>
		$(document).ready(function(){
			$("#backTo").click(function(){
				history.back();
			});
		});    	 
 </script>
</head>
<body>
<style type="text/css">
body{
    overflow: hidden;
}
.xul{width:100%;height: auto; background-color:#FFF; padding-top: 10px;}
.xul li{width:100%; height:auto;overflow: hidden;padding-top: 15px;}
.xul li p{
	line-height: 25px;
	text-align: left;
	text-indent: 10px;
	vertical-align: middle;
	float: left;
	width: auto;
	max-width:30%;
	padding-right: 10px;
}
.xul li span{
	line-height: 25px;
	text-align: left;
	vertical-align: middle;
	float: left;
	width: auto;
	max-width:75%;
padding-right: 5px;
}
.button-box {
    position:relative;
    width:100%;
    z-index: 10;
    margin:0;
    padding:10px 7.5px;
    display: -webkit-box;
	background:#f0f0f0;
}
.button-box .btn-wrap{
    -webkit-box-flex: 1;
    box-sizing: border-box;
    padding: 0px 7.5px;
}
.button-box .btn-wrap:first-child{
}

.button-box .btn {
    width: 98%;
    height: 40px;
    line-height: 40px; }

</style>
<div class="xul">
<li><p>房间名称：</p><span>${roomResource.roomInfo.roomInfo}</span></li>
<li><p>房屋结构：</p><span>${roomResource.roomInfo.roomStru}</span></li>
<li><p>楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;层：</p><span>${roomResource.roomInfo.floor}</span></li>
<li><p>朝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;向：</p><span>${roomResource.roomInfo.west}</span></li>
<li><p>建筑面积：</p><span>${roomResource.roomInfo.bldArea}㎡</span></li>
<li><p>套内面积：</p><span>${roomResource.roomInfo.tnArea}㎡</span></li>
<li><p>建筑单价：</p><span>${roomResource.roomInfo.price}元/㎡</span></li>
<li><p>套内单价：</p><span>${roomResource.roomInfo.tnPrice}元/㎡</span></li>
<li><p>标准总价：</p><span>${roomResource.roomInfo.total}元</span></li>
 <c:if test="${not empty roomResource.bargainInfo}"> 
<li><p>客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：</p><span>
<c:forEach items="${cstInfoList}" var="cstInfo" varStatus="status">
${cstInfo.cstName}(${cstInfo.mobileTel})
</c:forEach>
</span></li>
<li><p>成交总价：</p><span>${roomResource.bargainInfo.roomTotal}元</span></li>
<li><p>建筑成交单价：</p><span>${roomResource.bargainInfo.bldCjPrice}元/㎡</span></li>
<li><p>套内成交单价：</p><span>${roomResource.bargainInfo.tnCjPrice}元/㎡</span></li>
<li><p>签署日期：</p><span>${roomResource.bargainInfo.qsDate}</span></li>
<li><p>业&nbsp;&nbsp;&nbsp;&nbsp;务&nbsp;&nbsp;员：</p><span>${roomResource.bargainInfo.ywy}</span></li>
</c:if> 
</div>
                <div class="button-box">
                    
                    <div class="btn-wrap"><input type="submit" class="btn" id="backTo" value="返回"></div>
                </div>

</body></html>