<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>自定义</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">  
	<link  rel="Stylesheet" href="/mbem/mcrm/house/css/base.min.css">
	<link  rel="Stylesheet" href="/mbem/mcrm/house/css/user.css">
    <link rel="stylesheet" href="/mbem/mcrm/house/css/date.css">
	<script src="/common/js/jquery-1.9.1.min.js" ></script>
	<script src="/common/js/bootstrap/bootstrap.min.js"></script>
	<script src="/mbem/mcrm/house/js/date.js" ></script>
	<script src="/mbem/mcrm/house/js/iscroll.js" ></script>
	<script>
		$(function(){
			$('#beginTime1').date();
			$('#endTime').date({theme:"datetime"});
		});
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
		$(document).ready(function(){		
    
			$("#submit").click(function(){
				$("div[id^=btgroup]").each(function(){
					$("label",this).each(function(){
						if($(this).hasClass("active")){
							//alert($("input",this).val());
							var val = $("input",this).val();
							$(this).parent().prev().val(val);
						}
					});
				});
				var val = $("textarea").val();
				if(val==null || val=="" || typeof(val)=='undefined'){
					alert("请将必填项填写完整(用*标注)");
					return false;
				}
				return true;
			});
		});
	</script>
    
    
    
    <script type="text/javascript">
		$(function(){
			$('#beginTime').date();
			$('#endTime').date({theme:"datetime"});
		});
		$(document).ready(function(){
			$("#submit").click(function(){
				$("div[id^=btgroup]").each(function(){
					$("label",this).each(function(){
						if($(this).hasClass("active")){
							//alert($("input",this).val());
							var val = $("input",this).val();
							$(this).parent().prev().val(val);
						}
					});
				});
				var val = $("textarea").val();
				if(val==null || val=="" || typeof(val)=='undefined'){
					alert("请将必填项填写完整(用*标注)");
					return false;
				}
				return true;
			});
		});
	</script>
    
    
    
    
<script type="text/javascript">
    $ (function ()
    {
        $ ("shijian").click (function ()
        {
            //alert("fj");
            //alert ($ (this).attr ("id"));
        })
          
    });
</script>



<style>
.c{
	display: block;
	-webkit-box-flex: 0;
	font-size: 13px;
	width: 30%;
	height: 38px;
	line-height: 38px;
	border-radius: 3px;
	border: 1px solid #ddd;
	text-align: center;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	float: left;
	margin-bottom: 10px;
	margin-right: 6px;
}
.c:active{background-color:#3c6;border-color:#3c6;color:#fff;}
.c:hover:not{background-color:#3c6;border-color:#3c6;color:#fff;}


.xz{
	display: block;
	-webkit-box-flex: 0;
	font-size: 13px;
	width: 100%;
	height: 38px;
	line-height: 38px;
	border-radius: 3px;

	text-align: center;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	float: left;
	
}
.xz:active{background-color:#3c6;border-color:#3c6;color:#fff;}
.xz:hover:not{background-color:#3c6;border-color:#3c6;color:#fff;}

.btn {

display: block;
	-webkit-box-flex: 0;
	font-size: 13px;
	width: 30%;
	height: 38px;
	line-height: 38px;
	border-radius: 3px;
	border: 1px solid #ddd;
	text-align: center;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	float: left;
	margin-bottom: 10px;
	margin-right: 10px;
 	 font-weight: normal;
  	text-align: center;
  	white-space: nowrap;
  	vertical-align: middle;
  	-ms-touch-action: manipulation;
      touch-action: manipulation;
  	cursor: pointer;
  	-webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
}
 .btn.active {
  background-color:#3c6;border-color:#3c6;color:#fff;
}
 .btns {
    margin-top: 10px;
    padding: 0 12px;
    text-align: center;}
.btns span {
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
.cancel {
    margin-right: 2%;
    color: #34CCA6;
    border: 1px solid #34CCA6;}
 .sure {
    background-color: #34CCA6;
    color: #fff;
    border-radius: 5px;
}
</style>  
</head>
<body>
<div id="warpBox">
<div class="container" id="container">
    <form class="form-content" id="form-content" action=" " method="post">   
 <div class="switchSinglePage-wrap">
 <div class="switchSinglePage panel" style="-webkit-transform: translate3d(0%, 0px, 0px);">
 <div class="scroll">
	<div class="form-group border-1px first">
		<ul class="border-1px">
			 <li class="border-1px">
		     <div class="li-inner border-1px">
		                    <span class="k">开始时间</span>
		                    <span class="v">
		        <div class="clearfix dome3_box">
		        <input  id="beginTime" class="kbtn" name="nextDate" value=""/>
				 </div> </span>
		                      
			</li>
			<li class="border-1px">
		     <div class="li-inner border-1px">
		                    <span class="k">结束时间</span>
		                    <span class="v">
		        <div class="clearfix dome3_box">
		        <input  id="beginTime1" class="kbtn" name="nextDate" value=""/>
				 </div> 
		                    </span>
		    </li>
	  </ul>
	</div>
</div></div></div>
  

         <div id="action-mask"></div>       
        
		<div class="btns" id="time-wrap">
			<span data-ac="active" class="cancel">取消</span>
             <span data-ac="active" class="sure">确定</span>
		</div>
 
     </form>
</div>
</div>

<div id="datePlugin"></div>


	<script>
	$(".cancel").on("click", function() {
	
				history.back();
			});
	$(".sure").on("click", function() {
	
				var btime =$("#beginTime").val();
				
				var etime = $("#beginTime1").val();
				if (btime && etime) {
					if ((btime != etime) && (getTime(etime) < getTime(btime))) {
						
						alert("提示:结束时间要大于开始时间");
					} else {
						hidepanel = 0;
						
						window.location.href="/mbem/mcrm/house/custAnalysis/followReport.action?flg=zdy&startTime="+btime+"&endTime="+etime;
				}
				} else {
					alert("提示:开始、结束时间都不能为空");
				}
			});
			function getTime(str) {
				var arr = str.split("-");
				return new Date(arr[0], arr[1] - 1, arr[2], 0, 0, 0, 0);
			}		
	</script>
</body></html>

