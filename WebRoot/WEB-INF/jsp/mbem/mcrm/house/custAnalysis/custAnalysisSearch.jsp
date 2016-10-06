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
    <link rel="stylesheet"  href="/mbem/mcrm/house/css/date.css">
	<script src="/common/js/jquery-1.9.1.min.js" ></script>
	<script  src="/common/js/bootstrap/bootstrap.min.js"></script>  
	<script  src="/mbem/mcrm/house/js/date.js" ></script>
	<script  src="/mbem/mcrm/house/js/iscroll.js" ></script>
	<script>
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

	    $(document).ready(function(){
	    	//$(".btime").date();
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
				_f.action = "/mbem/mcrm/house/custAnalysis/index.action?reportType=range";
				_f.submit(); 
			});
			$(".cancel").click(function(){
				window.location.replace("/mbem/mcrm/house/custAnalysis/index.action?reportType=week");
			});
			
		});
    </script> 
</head>
<body>
<div id="warpBox" >   
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