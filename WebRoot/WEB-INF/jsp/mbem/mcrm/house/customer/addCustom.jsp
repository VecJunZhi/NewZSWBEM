<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<title>新增客户</title>   
<!-- <link href="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="/common/js/jquery.mobile-1.4.5/demos/_assets/css/jqm-demos.css"> -->
<link rel="stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/user.css">
<link  rel="stylesheet" href="/mbem/mcrm/house/css/common.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/datey.css" >
<link rel="stylesheet" href="/mbem/mcrm/house/dingwei/jquery.mobile-1.4.5.css" >
 
 
<script  src="/common/js/jquery-1.9.1.min.js" ></script>
<script  src="/mbem/mcrm/house/js/date.js" ></script>
<script  src="/mbem/mcrm/house/js/iscroll.js" ></script>
<script  src="/mbem/mcrm/house/js/addCustom.js"></script>
<script  src="/common/js/bootstrap/bootstrap.min.js"></script> 
<script  src="/common/js/layer/layer.js"></script>
<script  src="/common/js/validate/jquery.validate.min.js"></script>
<script src="/mbem/mcrm/house/dingwei/jquery.min.js"></script>
<script src="/mbem/mcrm/house/dingwei/index.js"></script>
<script src="/mbem/mcrm/house/dingwei/jquery.mobile-1.4.5.min.js"></script>
<script  src="/mbem/mcrm/business/js/date.js" ></script>

<style id="wrap">
	.button-wrap {
		margin-left: 5px;
		margin-right: 5px;
	}
</style>
<style id="center">
	.center {
		text-align: center;
	}
</style>
<style>
	.erji{    
	position: relative;
    border-bottom-width: 1px;
    overflow: hidden;
    }
	.erjih1{
	text-indent: 10px;
	color:#3c6;
	font-weight:100;
	}
</style>
<script>
		$( document ).on( "pagecreate", "#demo-page", function() {

			$( document ).on( "swipeleft swiperight", "#demo-page", function( e ) {
				
				// We check if there is no open panel on the page because otherwise
				// a swipe to close the left panel would also open the right panel (and v.v.).
				// We do this by checking the data that the framework stores on the page element (panel: open).
				if ( $( ".ui-page-active" ).jqmData( "panel" ) !== "open" ) {
					if ( e.type === "swipeleft" ) {
						$( "#right-panel" ).panel( "open" );
					} else if ( e.type === "swiperight" ) {
						$( "#left-panel" ).panel( "open" );
					}
				}
			});
		});
    </script>
    <style>
		/* Swipe works with mouse as well but often causes text selection. */
		/* We'll deny text selecton on everything but INPUTs and TEXTAREAs. */
		#demo-page :not(INPUT):not(TEXTAREA) {
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			-o-user-select: none;
			user-select: none;
		}
		/* Content styling. */
		dl { font-family: "Times New Roman", Times, serif; padding: 1em; }
		dt { font-size: 2em; font-weight: bold; }
		dt span { font-size: .5em; color: #777; margin-left: .5em; }
		dd { font-size: 1.25em;	margin: 1em 0 0; padding-bottom: 1em; border-bottom: 1px solid #eee; }
		.back-btn { float: right; margin: 0 2em 1em 0; }
	</style>
	
<script type="text/javascript">
	$(function(){
		$('#beginTime').date();
		$('#endTime').date({theme:"datetime"});
	});
</script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#beginTime").val("${autoGenNextFollowDate}");
		$("#tel").attr("onblur","telBlur(this)");
		$("#tel").after("<label onclick='return setDefault(this)'>默认</label>");
		$("#addInfo").append("<input id='defaultTel' name='tel' type='hidden'>");//增加隐藏域，用于存放默认手机号
		var tag1 = $("#tel").parent().parent().parent();
		if($("#telHome").attr("id") != null && typeof($("#telHome").attr("id"))!='undefined') {
			$("#telHome").after("<label onclick='return setDefault(this)'>设为默认</label>");
			tag1 = $("#telHome").parent().parent().parent();
		}
		if($("#telOffice").attr("id") != null && typeof($("#telOffice").attr("id"))!='undefined') {
			$("#telOffice").after("<label onclick='return setDefault(this)'>设为默认</label>");
			tag1 = $("#telOffice").parent().parent().parent();
		}
		if($("#telFax").attr("id") != null && typeof($("#telFax").attr("id"))!='undefined') {
			$("#telFax").after("<label onclick='return setDefault(this)'>设为默认</label>");
			tag1 = $("#telFax").parent().parent().parent();
		}
		
		$(tag1).after("<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'><span class='v'><input id='addTel' style='color:#3c6' value='添加联系电话' readonly></span></div></li>");

		$("#cancle").click(function(){
			window.location.href = "/mbem/mcrm/house/customer/cancleAddCst.action";
		});
 /*  	window.onpopstate = function(e){
			if(history.state)
			{
				window.location.href = "/mbem/mcrm/house/customer/customPage.action";
			}
		}; */
  		
  		$(".btn").click(function(){//选中子媒体类型后对主媒体类型赋值
  			var id = $(this).children().attr("id");
  			if(typeof(id)!='undefined' && id.indexOf("select2")!= -1){
  				var val = $(this).parent().prev().text();
				$("#mainMedia").val(val);
  			}
  		});
  		
		var xsNewTel = ${xsNewTel};
		if(xsNewTel != null)
			$("#tel").val(xsNewTel); 

		$("#addTel").click(function(){
			var tag = $("#addTel").parent().parent().parent();
			var str1 = "<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'>";
			var str4="</span></div></li>";
			var str2="";
			var str3="<label onclick='return setDefault(this)' style='float:left'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>";
			if($("#telHome").attr("id") == null || $("#telHome").attr("id")==undefined) {
				str2="<span class='k'>家庭电话</span><span class='v'><input type='text' placeholder='请输入' name='homeTel' id='telHome' onblur='telBlur(this)' style='width:60%;float:left'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#telOffice").attr("id") == null || $("#telOffice").attr("id")==undefined) {
				str2="<span class='k'>办公电话</span><span class='v'><input type='text' placeholder='请输入' name='officeTel' id='telOffice' onblur='telBlur(this)' style='width:60%;float:left'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#telFax").attr("id") == null || $("#telFax").attr("id")==undefined) {
				str2="<span class='k'>传真</span><span class='v'><input type='text' placeholder='请输入' name='fax' id='telFax' onblur='telBlur(this)' style='width:60%;float:left'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			} 	
		});
		/*  
		取性别的值，并更换选中性别的图片
		*/
		$("img").click(function(){
			var flag = $(this).attr("flag");
			var src = $(this).attr("src");
			if(flag == 1) {
			}
			else {
				if(src.indexOf("man")!=-1){
					$(this).attr("src","/mbem/mcrm/business/images/man.png");
					$(this).attr("flag","1");
					$("#woman").attr("src","/mbem/mcrm/business/images/woman_f.png");
					$("#woman").attr("flag","0");
					$("#sex").val("男");
				}
				if(src.indexOf("woman")!=-1){
					$(this).attr("src","/mbem/mcrm/business/images/woman.png");
					$(this).attr("flag","1");
					$("#man").attr("src","/mbem/mcrm/business/images/man_f.png");
					$("#man").attr("flag","0");
					$("#sex").val("女");
				}
			}
		});
	});
	
	/*手机失去焦点的验证  */  
	  function telBlur(tag){
		  /*	var telVal = $(tag).val();
		if(telVal==undefined || telVal == "")
			return false;
		$.ajax({
			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+telVal,    //请求的url地址
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			type:"POST",   //请求方式
				 success:function(req){
    			//请求成功时处理
    			if(req == "NO") {
    				layer.open({
    					type:0,
    					content:"该手机号已登记过，请更换号码重新输入！",
    					btn:"确定",
    					yes:function(index){
    						//$("#tel").focus();
    						//$("#tel").select();
    						$(tag).focus();
    						$(tag).select();
    						layer.close(index);
    					}
    				});
    				return false;
    			}
    			if(req == "YES")
    			{
    			}
			},
			error:function(){
    			//请求出错处理
			}
		});*/
	}  
	/* 多电话删除操作实现 
	 */
	function tagRemove(tag){
		if($(tag).prev().html() == "默认") {
			$("#telPhone").next().html("默认");
		}
		var removeTag = $(tag).parent().parent().parent();
		$(removeTag).remove();
		//time = time-1;
	}
	/* 设置默认号码的操作实现
		设为默认时首先判断是否已经为默认，若为默认则返回，否则继续判断要设为默认的手机号是否为空，若为空则返回，
		否则继续判断该手机号是否登记过，如果登记过则返回，否则更改状态为默认并将其他置为非默认
	 */
	function setDefault(tag){
		if($(tag).html() == "默认")
			return false;
		if($(tag).prev().val() == "" || $(tag).prev().val() == null) {
			layer.alert("手机号为空，请输入后再进行设置");
			return false;
		}
		var telVal = $(tag).prev().val();
		//var projGuid = ${projGuid};
		$.ajax({
 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+telVal+"&projGuid=${projGuid}",    //请求的url地址
 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
 			type:"POST",   //请求方式
			success:function(req){
	   			//请求成功时处理
	   			if(req == "NO") {
	   				$(tag).prev().focus();
	   				$(tag).prev().select();
	   				layer.alert("该手机号已登记过，请更换号码重新输入后再进行设置！");
	   			}
	   			if(req == "YES") {
	   				$("input[id^=tel]").each(function(){
						if($(this).next().html() == "默认") {
							$(this).next().html("设为默认");
						}
					});
					$(tag).html("默认");
					$("#defaultTel").val($(tag).prev().attr("name"));
				}
					
			},
	  		error:function(){
	      			//请求出错处理
	  		}
		});		
	}
	
	var repeatSubmit = true;
	function addCustom(){
		if(checkMust() == false) {
			repeatSubmit = true;
			return false;
		}
		if($("#tel").val()!=null && $("#tel").val()!='') {
			$.ajax({
	 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#tel").val()+"&projGuid=${projGuid}",    //请求的url地址
	 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
	 			type:"POST",   //请求方式
				success:function(req){
		   			//请求成功时处理
		   			if(req == "NO") {
		   				repeatSubmit = true;
		   				layer.open({
	    					type:0,
	    					content:"该手机号已登记过，请更换手机号！",
	    					btn:"确定",
	    					yes:function(index){
	    						$("#tel").focus();
	    						$("#tel").select();
	    						layer.close(index);
	    					}
	    				});
		   				return;
		   			}
		   			if(req == "YES") {
		   				if($("#telHome").val() != undefined && $("#telHome").val() !=null && $("#telHome").val()!=''){
		   					$.ajax({
		   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telHome").val()+"&projGuid=${projGuid}",    //请求的url地址
		   			 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
		   			 			type:"POST",   //请求方式
		   						success:function(req){
		   				   			//请求成功时处理
		   				   			if(req == "NO") {
		   				   				repeatSubmit = true;
		   				   				layer.open({
		   				   					type:0,
		   				   					content:"该家庭号已登记过,请更换手机号！",
		   				   					btn:"确定",
		   				   					yes:function(index){
		   				   						$("#telHome").focus();
		   				   						$("#telHome").select();
		   				   						layer.close(index);
		   				   					}
		   				   				});
		   				   				return;
		   				   			}
		   				   			if(req == "YES") {
		   				   				if($("#telOffice").val() != undefined && $("#telOffice").val() !=null && $("#telOffice").val()!='') {
			   				   				$.ajax({
			   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telOffice").val()+"&projGuid=${projGuid}",    //请求的url地址
			   			   			 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			   			   			 			type:"POST",   //请求方式
			   			   						success:function(req){
			   			   				   			//请求成功时处理
			   			   				   			if(req == "NO") {
			   			   				   				repeatSubmit = true;
			   			   				   				layer.open({
			   			   				   					type:0,
			   			   				   					content:"该办公号已登记过,请更换手机号！",
			   			   				   					btn:"确定",
			   			   				   					yes:function(index){
			   			   				   						$("#telOffice").focus();
	   			   				   								$("#telOffice").select();
	   			   				   								layer.close(index);
			   			   				   					}
			   			   				   				});
			   			   				   				return;
			   			   				   			}
			   			   				   			if(req == "YES"){
					   			   				   		if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!='') {
							   			   				   	$.ajax({
							   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //请求的url地址
							   			   			 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
							   			   			 			type:"POST",   //请求方式
							   			   						success:function(req){
							   			   				   			//请求成功时处理
							   			   				   			if(req == "NO") {
							   			   				   				repeatSubmit = true;
							   			   				   				layer.open({
							   			   				   					type:0,
							   			   				   					content:"该传真号已登记过,请更换手机号！",
							   			   				   					btn:"确定",
							   			   				   					yes:function(index){
							   			   				   						$("#telFax").focus();
							   			   				   						$("#telFax").select();
							   			   				   						layer.close(index);
							   			   				   					}
							   			   				   				});
							   			   				   				return;
							   			   				   			}
							   			   				   			if(req == "YES") {
							   			   				   				$("#addCst").submit();
							   			   				   				return;
							   			   				   			}
							   			   						}
							   			   				   	});
							   			   				}
							   			   				else{
							   			   					$("#addCst").submit();
							   			   					return;
							   			   				}
			   			   				   			}
			   			   						}
			   				   				});
		   			   					}
		   			   					else if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!='') {
			   			   					$.ajax({
			   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //请求的url地址
			   			   			 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			   			   			 			type:"POST",   //请求方式
			   			   						success:function(req){
			   			   				   			//请求成功时处理
			   			   				   			if(req == "NO") {
			   			   				   				repeatSubmit = true;
					   			   				   		layer.open({
			   			   				   					type:0,
			   			   				   					content:"该传真号已登记过,请更换手机号！",
			   			   				   					btn:"确定",
			   			   				   					yes:function(index){
			   			   				   						$("#telFax").focus();
			   			   				   						$("#telFax").select();
			   			   				   						layer.close(index);
			   			   				   					}
			   			   				   				});
			   			   				   				return;
			   			   				   			}
			   			   				   			if(req == "YES"){
			   			   				   				$("#addCst").submit();
			   			   				   				return;
			   			   				   			}
			   			   						}
			   			   				   	});
			   			   				}
			   			   				else{
			   			   					$("#addCst").submit();
			   			   					return;
			   			   				}
			   				   		}
		   						}
		   					});
		   				}
		   				else if($("#telOffice").val() != undefined && $("#telOffice").val() !=null && $("#telOffice").val()!='') {
		   					$.ajax({
			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telOffice").val()+"&projGuid=${projGuid}",    //请求的url地址
			   			 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			   			 			type:"POST",   //请求方式
			   						success:function(req){
			   				   			//请求成功时处理
			   				   			if(req == "NO") {
			   				   				repeatSubmit = true;
			   				   				layer.open({
			   				   					type:0,
			   				   					content:"该办公号已登记过,请更换手机号！",
			   				   					btn:"确定",
			   				   					yes:function(index){
			   				   						$("#telOffice").focus();
				   				   					$("#telOffice").select();
				   				   					layer.close(index);
			   				   					}
			   				   				});
			   				   				return;
			   				   			}
			   				   			if(req == "YES"){
			   				   				if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!=''){
				   				   				$.ajax({
				   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //请求的url地址
				   			   			 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
				   			   			 			type:"POST",   //请求方式
				   			   						success:function(req){
				   			   				   			//请求成功时处理
				   			   				   			if(req == "NO") {
				   			   				   				repeatSubmit = true;
						   			   				   		layer.open({
				   			   				   					type:0,
				   			   				   					content:"该传真号已登记过,请更换手机号。",
				   			   				   					btn:"确定",
				   			   				   					yes:function(index){
				   			   				   						$("#telFax").focus();
				   			   				   						$("#telFax").select();
				   			   				   						layer.close(index);
				   			   				   					}
				   			   				   				});
				   			   				   				return;
				   			   				   			}
				   			   				   			if(req == "YES"){
				   			   				   				$("#addCst").submit();
				   			   				   				return;
				   			   				   			}
				   			   						}
				   			   				   	});
			   				   				}
			   				   				else {
			   				   					$("#addCst").submit();
			   				   					return;
			   				   				}
			   				   			}
			   						}
			   				   	});
		   				}
		   				else if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!='') {
		   					$.ajax({
		   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //请求的url地址
		   			 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
		   			 			type:"POST",   //请求方式
		   						success:function(req){
		   				   			//请求成功时处理
		   				   			if(req == "NO") {
		   				   				repeatSubmit = true;
			   				   			layer.open({
			   				   					type:0,
			   				   					content:"该传真号已登记过,请更换手机号！",
			   				   					btn:"确定",
			   				   					yes:function(index){
			   				   						$("#telFax").focus();
			   				   						$("#telFax").select();
			   				   						layer.close(index);
			   				   					}
			   				   			});
		   				   				return;
		   				   			}
		   				   			if(req == "YES"){
		   				   				$("#addCst").submit();
		   				   				return;
		   				   			}
		   						}
		   				   	});
		   				}
		   				else{
		   					$("#addCst").submit();
		   					return;
		   				}
		   			}
				},
		  		error:function(){
		      			//请求出错处理
		      			alert("33333");
		  		}
			});
		}
	}
	
	function checkMust(){
		if(repeatSubmit == false){
			return false;
		}else {
			repeatSubmit = false;
		}
		var returnVal = true;
		if($("#name").val() == '' || $("#tel").val() == '' || $("#followContent").val() == '' || $("#beginTime").val()==''){
			layer.alert("请将必填项填写完整！");
			return false;
		}
			
		if(!(/^\d{11}$/.test($("#tel").val()))){ 
			layer.alert("手机号码格式有误，请重填");
			return false; 
		} 
			
		$("div[id^=select]").each(function(){
			if($(this).parent().parent().attr("data-require") == "1") {
				var flag = 0;
				var tag = $(this).next();
				$("label",tag).each(function(){
					if($(this).hasClass("active")){
						flag = 1;
						return false;
					}
				});
				if(flag == 0) {
					layer.alert("请将必填项填写完整。");
					returnVal = false;
					return false;
				}
			}
		});
		if(returnVal == false) {
			return false;
		}else {
			if($("#followcontent").val().length > 50){
				layer.alert("请将跟进内容限制在50字内！");
				return false;
			} else {
				return true;
			}
		} 
	}
</script>
</head>
<body id="insurer_list_page">

<!-- 快速定位面板 -->
<!-- 向右侧滑动面板 共用一段代码-->
<%-- 	<div data-role="panel" id="right-panel" data-display="overlay" data-position="right" data-theme="a" data-position-fixed="true">
    	<ul data-role="listview" data-inset="false" data-theme="a" data-divider-theme="a" data-icon="false" class="jqm-list ui-listview ui-group-theme-a">
			<li>
				<c:forEach items="${addCstInfoList}" var="option" varStatus="status">
					<c:if test="${(option.tagType == 'radio' || option.tagType == 'checkbox') && option.name != '性别' }">
						<c:if test="${option.isNull == 0}">
							<a href="#${option.locationId}" class="ui-btn" data-rel="close">${option.name}(必填)</a>
						</c:if>
						<c:if test="${option.isNull == 1}">
							<a href="#${option.locationId}" class="ui-btn" data-rel="close">${option.name}</a>
						</c:if>
					</c:if>
				</c:forEach>
			</li>	
		</ul>
    </div>	 --%>
    
<!-- 快速定位面板 -->

<div data-role="page" id="demo-page"  data-quicklinks="true" >
    <!--<div data-role="header" class="jqm-header "  data-position="fixed"  >
    
		<a href="#rightpanel3" class="ak47-right">快速定位</a>
  </div>-->   
<div data-role="main" class="ui-content">

<!-- 向右侧滑动面板 共用一段代码-->

<form id="addCst" class="form-content"  action="${actionUrl}" method="post" data-ajax="false">
<div id="msg_1" style="display: block;">
	<input id="defaultTel" name="defaultTel" type="hidden" value="tel">
	<input id="mainMedia" name="mainMediaName" type="hidden"><!-- 隐藏域，存放主媒体类型 -->
	<input id="projGuid" type="hidden" value="${projGuid}">
	<div class="form-group border-1px first" data-id="2"> 
    	<ul id="addInfo" class="border-1px">  
    		<c:forEach items="${addCstInfoList}" var="option" varStatus="status">  
    			<c:if test="${(option.tagType == 'radio' ||option.tagType == 'checkbox')&& option.name !='性别'}"> 			 							    
					<!-- <li class="border-1px"> -->
					<c:if test="${option.isNull == '0' }">
						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
					<c:if test="${option.isNull == '1' }">
						<li class="border-1px"  data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
						<div class="li-inner border-1px"> 
							<%-- <a name="${locationId}"></a> --%>
							<c:if test="${option.tagType =='checkbox' }">
								<div class="select-head" id="${option.id}" >${option.name}(可多选)</div>
							</c:if>
							<c:if test="${option.tagType !='checkbox' }">
								<div class="select-head" id="${option.id}" >${option.name}</div>
							</c:if>
							<div  data-toggle="buttons">
								${option.htmlStr}
							</div>
						</div>
					</li>
				</c:if>
				<c:if test="${option.tagType == 'text' || option.name=='性别' || option.tagType=='textarea'}">
					<c:if test="${option.isNull == '0' }">
						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
					<c:if test="${option.isNull == '1' }">
						<li class="border-1px"  data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
							<div class="li-inner border-1px">
								<span class="k">${option.name }</span>
									<span class="v">${option.htmlStr}</span>
							</div>
						</li>
					</c:if>
				</c:forEach>	
		</ul>
	</div> 
    <div id="datePlugin"></div>
	<div style="height:40px"></div> 
    <!-- 留个空行，防止被取消和保存挡住 -->
	<div class="form-group submit-group noswitchSinglePage">
        <a id="cancle" class="btns btns-default" href="#" style="text-align:center; color:#3c6">取消</a>
        <button class="btns btns-primary"  data-ac="active" type="button" onclick="addCustom()">保存</button>
    </div>
</div>
</form>
</div>
<!-- <div data-role="panel" id="rightpanel3" data-position="right" data-display="overlay" data-theme="a" style="height:100%;">
				<ul data-role="listview" data-inset="false" data-theme="a" data-divider-theme="a" data-icon="false" class="jqm-list ui-listview ui-group-theme-a">
					<li><a href="#select3" class="ak47" data-rel="close">住址区域</a></li>
					<li><a href="#eney" class="ak47" data-rel="close">竞争对手</a></li>
						<li><a href="#area" class="ak47" data-rel="close">意向面积</a></li>
						<li><a href="#way" class="ak47" data-rel="close">来访方式</a></li>
						<li><a href="#basic" class="ak47" data-rel="close">基本信息</a>
					</li>	
				</ul>
	</div> --> 
	<div data-role="panel" id="right-panel" data-display="push" data-position="right" data-theme="c" >
    	<ul data-role="listview" data-inset="false" data-theme="a" data-divider-theme="a" data-icon="false" class="jqm-list ui-listview ui-group-theme-a">
			<c:forEach items="${addCstInfoList}" var="option" varStatus="status">
				<c:if test="${(option.tagType == 'radio' ||option.tagType == 'checkbox')&& option.name !='性别'}">		
					<li><a href="#${option.id}" class="ak47" data-rel="close">${option.name}</a></li>
				</c:if>
			</c:forEach>	
		</ul>
    </div> 
</div>
</body>
</html>