<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">  
<title>楼栋信息</title>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"  href="/mbem/mcrm/business/css/base.min.css">
<script  src="/common/js/jquery-1.9.1.min.js"></script>
<script>
        if(window.sessionStorage.getItem('goCustomer_selectBulid')==1){
            window.sessionStorage.removeItem('goCustomer_selectBulid');
            window.location.href="/Sales/Find/findmain?token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&projid=c5091504-41ae-4c2e-ae3b-69a008a5762f";
        }
    </script>
<script>
    var statistics={}
    statistics.start=+new Date();
</script>
<script>
	function new_form(){  
	    var f = document.createElement("form");  
	    document.body.appendChild(f);  
	    f.method = "post";  
	    return f;  
	}  
	  
	function create_elements(eForm,eName,eValue){  
	    var e=document.createElement("input");  
	    eForm.appendChild(e);  
	    e.type='text';  
	    e.name=eName;  
	    if(!document.all){  
	        e.style.display='none';  
	    }else{  
	        e.style.display='block';  
	        e.style.width='0px';  
	        e.style.height='0px';  
	    }  
	    e.value=eValue;  
	    return e;  
	} 
	$(function ($) {
		if (window.history && window.history.pushState) {
		$(window).on('popstate', function () {
	        var hash = window.location.hash;
	        if (hash === '') {
	            //window.location.href="/mbem/mcrm/house/saleManager/index.action";
	            window.location.href="/mbem/mcrm/house/find/findSearch.action"
	        }
	    });
	    var url = window.location.url;
	    window.history.pushState('forward', null, url);
	    location.hash="#1";
		}
	});    	 
	$(document).ready(function(){
		$(".house_box td").click(function(){
			var bldName = $("div:first",this).text();
			var bldFullName = $("input",this).attr("fullName");
			var bldGuid = $("input",this).attr("bldNo");
			//alert(bldName);
			//alert(bldFullName);
			var _f = new_form();
			create_elements(_f,"bldName",bldName);
			create_elements(_f,"bldFullName",bldFullName);
			create_elements(_f,"bldGuid",bldGuid);
			_f.action = "/mbem/mcrm/house/room/searchRoomInCurProBld.action";
			_f.submit();
		});
	});
</script>
<style>
        body{
            background-color: #f0f0f0;
        }
        body{
            margin: 0px;
            padding: 0px;
        }
        #chooseBuildingBox{
            width: 100%;
            height: 100%;
            z-index: 999;
            top: 0px;
        }
        .house_box{
            /*padding: 0px 12px 0px 12px;*/
        }
        .house_box .house_title {
            color: #333;
            background-color: transparent;
            border-bottom: 1px solid #ddd;
            font-size: 16px;
            padding: 0;
            margin:0 12px;
        }
        .house_box .house_title li{
            width: auto;
            float: left;
            border-bottom: 2px solid #33cc66;
            line-height: 22px;
            padding: 10px 0;
        }
        .housetype{
            /*padding: 12px 0px;*/
            font-size: 15px;
            color: #8e8e8e;
            margin-left:12px;
        }
        .housepanel{
        }
        .housebuilding{
            width: 100%;
        }
        .housebuilding table{
            width: 100%;
            table-layout: fixed;
            border-collapse: collapse;
        }
        .housebuilding table tr{
            border-bottom: 12px solid #F1F1F1;
        }
        .housebuilding .builditem {
            font-size: 14px;
            width: 33.33%;
            text-align: center;
            height:82px;
            color: #fff;
            text-align: center;
            vertical-align: middle;
            background-color: #33cc66;
            /*border-left: 3px solid #ff0;*/
            /*border-right: 12px solid #F1F1F1;*/
            position: relative;
            box-sizing: border-box;
            padding-bottom: 20px;
        }
        .housebuilding .builditem .build-name{
            overflow : hidden;
            text-overflow: ellipsis;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            display: -webkit-box;
            padding:0 10px;
        }
        
        .housebuilding .builditem:last-child{
            border-right: 0;
        }
        .housebuilding .builditem:active{
            background-color: #2caf56;
        }
        .housebuilding .builditem.sell-off{
            background: #e1e1e1;
            color:#888;
        }

        .build-status-selling, .build-status-sold{
            height:20px;
            width: 100%;
            font-size: 12px;
            line-height: 20px;
            position: absolute;
            bottom: 0;
        }
        .build-status-selling{
            background: rgba(51,51,51,.35);
        }
        .build-status-sold{
            color:#339754;
        }
        .build-selected{ border-bottom: 3px solid #ff0; }
        .housebuilding table{border-collapse:separate; border-spacing:12px;}
    </style>
    <title></title>
</head>
<body>
    <div id="chooseBuildingBox" style="min-height: 802px;">
        <div class="buildinginner clearfix">
                        
                <div class="house_box">
                    <ul class="house_title clearfix">
                        <li>史家庄城中村改造项目-御泽嘉园</li>
                    </ul>
                    <c:forEach items="${searchBldInfoList }" var="bldInfo" varStatus="status">
                    <div class="housepanel">
                          <div class="housetype"> ${bldInfo.name}</div>
                          <div class="housebuilding">
                        
                        	<table>
                                              <tbody>   
                       <c:forEach items="${bldInfo.value}" var="bld" varStatus="i"> 
	                      <c:if test="${i.index % 3 == 0}">
	                            <tr>
	                       </c:if>
                         <td class="builditem sell-on build-selected">
                       	<div class="build-name">${bld.name}</div>
                       	<input type="hidden" fullName="${bld.fullName}" bldNo="${bld.bldGuid}">
                         <div class="build-status-selling">剩余${bld.total}套</div>
                       	 </td>
                         <c:if test="${(i.index+4) % 3 == 0}">
                               </tr>
                       	</c:if>
						</c:forEach>
                            </tbody>
                          </table>
                        </div>
                    </div>
                    </c:forEach>
</div>
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
    <script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
    <script>
        var token_id = 'cdycid1429859342_BldGUID';
        $(function(){
            var interval=setInterval(function(){
                var wh=$(window).height();
                if(wh!=0){
                    clearInterval(interval);
                    $('#chooseBuildingBox').css('min-height',wh-24);
                }
            },100);

            if ($('.house_box .house_title li').width() + 24 == $(window).width()) {
                $('.house_box .house_title').css('border', 0);
            }
            setSelectBuild(); 
        });
        function replaceUrl(GUID){
            var domain = ".myscrm.cn";

            var hours = 1; 
            var exp = new Date(); 
            exp.setTime(exp.getTime() + hours*60*60*1000); 
            var cookie_str = "cdycid1429859342_BldGUID="+ escape (GUID) + ";expires=" + exp.toGMTString()+';path=/'; 
            if(domain!=''){
                cookie_str += ";domain="+domain;//指定可访问cookie的域 
            }
            document.cookie = cookie_str;
			Zepto.localStore.remove("/Sales/RoomStatus/search/#selected");

            window.location.href= "/mbem/mcrm/house/room/searchRoomInCurProBld.action";
        }

        function getCookie(name) {
            var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
            if (arr != null) {
                return unescape(arr[2]);
            }
            return "";
        }

        function setSelectBuild() {
            var hasSelectBuild = true;
            $("#chooseBuildingBox td").each(function(i,item){
                var id = $(this).attr("data-guid");
                var cookies_id = getCookie(token_id);
                if(cookies_id === id){
                    $(item).addClass("build-selected");
                    hasSelectBuild = false;
                    return false
                }
            })
            if(hasSelectBuild){
                $("#chooseBuildingBox td").eq(0).addClass("build-selected");
            }
        }      
    </script>

</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html>