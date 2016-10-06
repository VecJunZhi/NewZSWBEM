<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.min.css">
<script src="/common/js/jquery-1.9.1.min.js"></script>
<script>
    //返回处理
    if(window.sessionStorage.getItem('goSaleManage_selectBulid')==1){
        window.sessionStorage.removeItem('goSaleManage_selectBulid');
        window.location.href="/Sales/SaleManage/index?token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&projid=c5091504-41ae-4c2e-ae3b-69a008a5762f";
    }
</script>
<script>
    var statistics={}
    statistics.start=+new Date();
</script>
<style>
#chooseBuildingBox{ width:100%; height:100%; z-index:999; top:0px; }
.house_box{ padding:0px 12px 0px 12px; }
.house_box .house_title{ color:#333; background-color:transparent; border-bottom:1px solid #ddd; font-size:16px; padding:0px; margin-bottom:12px; }
.house_box .house_title li{ width:auto; float:left; border-bottom:2px solid #34CCA6; line-height:22px; padding:10px 0; }
.head-type{ margin:10px 12px 10px;}
.head-type li{ float: left; width: 25%; text-align:center; vertical-align: middle; height: 18px; line-height: 18px}
.build-type{ box-sizing:border-box; display: inline-block; width: 6px; height: 12px; margin: 0px 3px 0px 0;}
.build-type-ys{ background-color: #f35656;}
.build-type-ws{ background-color: #fff; border: 1px solid #d3d3d3;}
.build-type-yxk{ background-color: #ffc926;}
.build-type-wfp{ background-color: #bcbcbc;}
.c-gray{ color: #999}
.house_area{ color: #8e8e8e; height: 20px; line-height: 20px; margin-bottom: 10px;}
.house_dong_list{ display: -webkit-flex; -webkit-flex-flow: row wrap; display: flex; flex-flow: row wrap;}
.house_dong_list li{ width: 32.3%;display: inline-block;  padding:0 5px 10px; box-sizing:border-box; -webkit-box-sizing: border-box;}
.house_dong_list li:nth-child(3n+1){ padding-left:0;}
.house_dong_list li:nth-child(3n){ padding-right:0;}
.house_dong_a{ display: block; height: 81px; border: 1px solid #34cca6; color: #333; text-decoration: none}
.house_dong_a p{ height: 20px; line-height: 20px; text-align: center; color: #fff; background-color: #34cca6;}
.build-type-info{ height: 61px; background-color: #f6f6f6;}
.build-type-info span{ display: block; float: left; width: 50%; height: 30px; padding-left: 5px; line-height: 30px; box-sizing:border-box;}
.bd-bottom{ border-bottom: 1px solid #e8e8e8;}
.bd-right{ border-right: 1px solid #e8e8e8;}
.house_dong_wfp{ display: block; height: 83px; color: #8e8e8e; text-decoration: none; background:#e1e1e1 url(/mbem/mcrm/business/images/bg_wfp.png) no-repeat right bottom; background-size: 93px 82px;}
.house_dong_wfp p{ line-height: 83px; text-align: center}
.build-selected{ border-bottom: 3px solid #ff0; }
    </style>
    <title>选择楼栋</title>
    
<script type="text/javascript">
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
	           // window.location.href="/mbem/mcrm/house/saleManager/index.action";
	        	window.location.href="/mbem/mcrm/house/find/findSearch.action"
	        }
	    });
	    var url = window.location.url;
	    window.history.pushState('forward', null, url);
	    location.hash="#1";
		}
	});    	 
	$(document).ready(function(){
		
			/* var url = window.location.pathname;
    		history.pushState({},'',url);//向历史记录里添加一条信息，其实在这里的url可以随便填，主要是用了下面的事件跳转
		  	window.onpopstate = function () { //监听浏览器事件
		  	if(history.state != null)
    				return false;
			location.href = '/mbem/mcrm/house/saleManager/index.action';
			}   */
		
		$("a").click(function(){
			var bldName = $("p",this).text();
			//alert(""+bldName);
			var bldFullName = $("p",this).children().attr("fullName");
			var bldGuid = $("p",this).children().attr("bldGuid");
			var _f = new_form();
			//create_elements(_f,"bldName",bldName);
			create_elements(_f,"bldFullName",bldFullName);
			create_elements(_f,"bldGuid",bldGuid);
			_f.action = "/mbem/mcrm/house/room/selectBulidShow.action";
			_f.submit();
		});
		
		
		
	});
</script>
</head>
<body class="bg-gray">
    <div id="chooseBuildingBox" style="min-height: 802px;">
        <div class="buildinginner clearfix">
            <div class="head-type clearfix">
                <ul>
                    <li><i class="build-type build-type-ys"></i>已售<span class="c-gray">(${totalInfo.sold })</span></li>
                    <li><i class="build-type build-type-ws"></i>未售<span class="c-gray">(${totalInfo.unSold })</span></li>
                    <li><i class="build-type build-type-yxk"></i>预销控<span class="c-gray">(${totalInfo.preControl })</span></li>
                    <li><i class="build-type build-type-wfp"></i>未放盘<span class="c-gray">(0)</span></li>
                </ul>
            </div>
            <!-- <div class="legend"><div class="lengend-sell-on">可售</div><div class="lengend-sell-off">不可售</div></div> -->
            <div class="house_box">
                <div>    
                    <ul class="house_title clearfix">
                        <li>史家庄城中村改造项目-御泽嘉园</li>
                    </ul>
                    <c:forEach items="${bldInfoList}" var="bldInfo" varStatus="status">
                    <div class="house_area">${bldInfo.name}</div>                    
                    <div class="house_dong">
                        <ul class="house_dong_list">
                        <c:forEach items="${bldInfo.value}" var="bld" varStatus="status">
                             <li>
                                <a href="javascript:void(0)" class="house_dong_a build-selected" onclick="replaceUrl('b93feba3-6bea-e411-baaf-fcaa145c42f2')" data-guid="b93feba3-6bea-e411-baaf-fcaa145c42f2">
                                    <div class="build-type-info">
                                        <span class="bd-right bd-bottom"><i class="build-type build-type-ys"></i>${bld.sold}</span>
                                        <span class="bd-bottom"><i class="build-type build-type-yxk"></i>${bld.preControl}</span>
                                        <span class="bd-right"><i class="build-type build-type-ws"></i>${bld.unSold}</span>
                                        <span><i class="build-type build-type-wfp"></i>0</span>
                                    </div>
                                    <p>${bld.name}<input type="hidden" fullName="${bld.fullName}" bldGuid="${bld.bldGuid}"></p>
                                </a>
                          	</li>  
                       </c:forEach>                         
					</ul> 
                    </div>
                    
                    </c:forEach>
                </div>            </div>
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
            setSelectBuild()
        });
        function replaceUrl(GUID){
            var domain = ".myscrm.cn";
            var hours = 1; 
            var exp = new Date(); 
            exp.setTime(exp.getTime() + hours*60*60*1000); 
            cookie_str = "cdycid1429859342_BldGUID="+ escape (GUID) + ";expires=" + exp.toGMTString()+';path=/'; 
            if(domain!=''){
                cookie_str += ";domain="+domain;//指定可访问cookie的域 
            }
            document.cookie = cookie_str;
			Zepto.localStore.remove("/Sales/SalesRoomStatus/search/#selected");
            window.location.href= "/Sales/SaleRoomStatus/index?token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&projid=c5091504-41ae-4c2e-ae3b-69a008a5762f";
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
            $("#chooseBuildingBox li > a").each(function(i,item){
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

</body></html>