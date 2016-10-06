



<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US"><head>
    <meta charset="GBK">
    <title>自定义</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">  
	<link type="text/css" rel="Stylesheet" href="/mbem/mcrm/house/css/base.min.css">
	<link type="text/css" rel="Stylesheet" href="/mbem/mcrm/house/css/user.css">
	<script type="text/javascript" src="/common/js/jquery-1.9.1.min.js" ></script>
	<script type="text/javascript" src="/common/js/bootstrap/bootstrap.min.js"></script>
    <link href="/mbem/mcrm/house/css/date.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/mbem/mcrm/house/js/date.js" ></script>
	<script type="text/javascript" src="/mbem/mcrm/house/js/iscroll.js" ></script>
	<script type="text/javascript">
		$(function(){
			$('#beginTime1').date();
			$('#endTime').date({theme:"datetime"});
		});
	</script>
    
    
    
    <script type="text/javascript">
		$(function(){
			$('#beginTime').date();
			$('#endTime').date({theme:"datetime"});
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
	                window.location.href="/mbem/mcrm/house/saleManager/index.action";
	            }
	        });
	        var url = window.location.url;
	        window.history.pushState('forward', null, url);
	        location.hash="#1";
	    	}
	    });    	 
	    $(document).ready(function(){
			$(".sure").click(function(){
	     		var _f = new_form();
	     		var bTime;
	     		var eTime;
	     		if($("#beginTime").val().length ==9){
	     			 bTime = $("#beginTime").val().substring(0,8)+"0"+$("#beginTime").val().substring(8,9);
	     		}
	     		else
	     			bTime = $("#beginTime").val();
	     		if($("#beginTime1").val().length ==9){
	     			eTime = $("#beginTime1").val().substring(0,8)+"0"+$("#beginTime1").val().substring(8,9);
	     		}
	     		else
	     			eTime = $("#beginTime1").val();
				create_elements(_f,"beginTime",bTime);
				create_elements(_f,"endTime",eTime);
				_f.action = "/mbem/mcrm/house/saleManager/saleReport.action?reportType=range";
				_f.submit(); 
			});
			$(".cancel").click(function(){
				window.location.replace("/mbem/mcrm/house/saleManager/saleReport.action?reportType=day");
			});
			
		});
    </script> 
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
		 </div> 
                    </span>
                      
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
</div></div></div></div>
  

         <div id="action-mask"></div>       
        
		<div class="btns">
			<span data-ac="active" class="cancel">取消</span>
             <span data-ac="active" class="sure">确定</span>
		</div>
 
     </form>

 

</div>
</div><div id="datePlugin"></div>
</body></html>

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

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
   


