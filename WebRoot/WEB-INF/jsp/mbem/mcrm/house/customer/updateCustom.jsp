
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans"><head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>编辑客户</title>
<link rel="stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/user.css">
<link  rel="stylesheet" href="/mbem/mcrm/house/css/common.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/datey.css" >
<!--<link rel="stylesheet" href="/mbem/mcrm/house/dingwei/jquery.mobile-1.4.5.css" >-->
<script  src="/common/js/jquery-1.9.1.min.js" ></script>

<!-- <script  src="/mbem/mcrm/house/js/date.js" ></script> -->

<script  src="/mbem/mcrm/house/js/iscroll.js" ></script>
<script  src="/mbem/mcrm/house/js/addCustom.js"></script>
<script  src="/common/js/bootstrap/bootstrap.min.js"></script> 
<script  src="/common/js/layer/layer.js"></script>
<script  src="/common/js/validate/jquery.validate.min.js"></script>

<!--<script src="/mbem/mcrm/house/dingwei/jquery.min.js"></script>
<script src="/mbem/mcrm/house/dingwei/index.js"></script>
<script src="/mbem/mcrm/house/dingwei/jquery.mobile-1.4.5.min.js"></script>-->
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
	function wuxiaoRe(_tag){
   	 		/* alert("hisshi");
   	 		alert($(_tag).find("label").hasClass("active"));
   	 		alert($(_tag).find("label").children().val()); */
   	 		$(_tag).find("label").each(function(index){
   	 			//alert($(this).hasClass("active"));
   	 			//alert($(this).children().val());
   	 			if($(this).hasClass("active")==false){
   	 				if($(this).children().val()=="无效"){
   	 					$(this).parent().parent().parent().after($("#wuxiaoRe").html());
   	 				}else{
   	 					$(this).parent().parent().parent().next().remove();
   	 				}
   	 			}
   	 		});
			/* if($(this).val()=="无效"){
			alert("k ");
				$(this).parent().parent().parent().parent().after($("#wuxiaoRe").html());
			} */
   	}
</script>
<script type="text/javascript">
	var oldHomeTel = "";
	var oldOfficeTel = "";
	var oldFaxTel = "";
	$(document).ready(function(){	
	var invalidReason=$("#invaliDvalue").val();
		$("#tel").after("<label onclick='return setDefault(this)'>默认</label>");
		$("#tel").attr("onchange","telChange(this)");
		$("#oldName").val($("#name").val());
		$("#oldTel").val($("#tel").val());
		
		
		oldHomeTel = $("#telHome").val();
		oldOfficeTel = $("#telOffice").val();
		oldFaxTel = $("#telFax").val();
		//$("#beginTime").val("${autoGenNextFollowDate}");
		var tag1;
		var id = "tel";
		if($("#telHome").attr("id") != null && $("#telHome").attr("id")!=undefined) {
			$("#telHome").attr("onchange","telChange(this)");
			$("#telHome").after("<label onclick='return setDefault(this)' style='float:left'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>");
			id = "telHome";
		}
		if($("#telOffice").attr("id") != null && $("#telOffice").attr("id")!=undefined) {
			$("#telOffice").attr("onchange","telChange(this)");
			$("#telOffice").after("<label onclick='return setDefault(this)' style='float:left'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>");
			id = "telOffice";
		}
		if($("#telFax").attr("id") != null && $("#telFax").attr("id")!=undefined) {
			$("#telFax").attr("onchange","telChange(this)");
			$("#telFax").after("<label onclick='return setDefault(this)' style='float:left'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>");
			id = "telFax";
		} 
		/*增加添加联系电话按钮  */
		tag1 = $("#"+id).parent().parent().parent();
		$(tag1).after("<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'><span class='v'><input id='addTel' style='color:#3c6' value='添加联系电话' readonly></span></div></li>");
		
		
		/* $("#cancle").click(function(){
			window.location.href = "/mbem/mcrm/house/customer/cancleAddCst.action";
		}); */
		$("#cancle").click(function(){
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}); 
		/*新增联系电话  */
		$("#addTel").click(function(){
			var tag = $("#addTel").parent().parent().parent();
			var str1 = "<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'>";
			var str4="</span></div></li>";
			var str2="";
			var str3="<label onclick='return setDefault(this)' style='float:left'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>";
			if($("#telHome").attr("id") == null || $("#telHome").attr("id")==undefined) {
				str2="<span class='k'>家庭电话</span><span class='v'><input type='text' placeholder='请输入' name='homeTel' id='telHome' style='width:60%;float:left' onchange='telChange(this)'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#telOffice").attr("id") == null || $("#telOffice").attr("id")==undefined) {
				str2="<span class='k'>办公电话</span><span class='v'><input type='text' placeholder='请输入' name='officeTel' id='telOffice' style='width:60%;float:left' onchange='telChange(this)'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#telFax").attr("id") == null || $("#telFax").attr("id")==undefined) {
				str2="<span class='k'>传真</span><span class='v'><input type='text' placeholder='请输入' name='fax' id='telFax' style='width:60%;float:left' onchange='telChange(this)'>";
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
		 $("#isInvalid").next().find("label").each(function(){
		 	if($(this).hasClass("active")==true){
					if($(this).children().val()=="无效"){
						$(this).parent().parent().parent().after($("#wuxiaoRe").html());
						$(this).parent().parent().parent().next().children().children().eq(1).children().each(function(){
							var _val=$("input",this).val();
							if(_val==invalidReason){
								$(this).addClass("active");
								return false;
							}
						});
					}else{
						$(this).parent().parent().parent().next().remove();
					}
		   	 	}
		 });
	});

	/*手机号变更后进行判重验证  */
	/* $("#tel").change(function(){ */
	 function telChange(tag) {
		 /*	var telVal = $(tag).val();
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
			alert("手机号为空，请输入后再进行设置");
			return false;
		}
 		$("input[id^=tel]").each(function(){
			if($(this).next().html() == "默认") {
				$(this).next().html("设为默认");
			}
		});
		$(tag).html("默认");
		$("#defaultTel").val($(tag).prev().attr("name"));
	}
	
	var repeatSubmit = true;
	function updateCustom(){
		if(checkMust() == false) {
			repeatSubmit = true;
			return false;
		}
		var newHomeTel = $("#telHome").val();
		var newOfficeTel = $("#telOffice").val();
		var newFaxTel = $("#telFax").val();
		if(oldHomeTel != newHomeTel && newHomeTel != ''){
			$.ajax({
	 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+newHomeTel+"&projGuid=${projGuid}",    //请求的url地址
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
		   			if(req == "YES"){
		   				if(oldOfficeTel != newOfficeTel && newOfficeTel !=''){
		   					$.ajax({
		   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+newOfficeTel+"&projGuid=${projGuid}",    //请求的url地址
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
		   				   				if(oldFaxTel != newFaxTel && newFaxTel !=''){
			   				   				$.ajax({
			   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+newFaxTel+"&projGuid=${projGuid}",    //请求的url地址
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
			   			   				   				$("#updateCst").submit();
			   			   				   				return;
			   			   				   			}
			   			   						}
			   				   				});
		   				   				}else{
		   				   					$("#updateCst").submit();
		   				   					return;
		   				   				}
		   				   			}
		   						}
		   					});
		   				}
		   				else if(oldFaxTel != newFaxTel && newFaxTel != ''){
		   					$.ajax({
			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+newFaxTel+"&projGuid=${projGuid}",    //请求的url地址
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
			   				   				$("#updateCst").submit();
			   				   				return;
			   				   			}
			   						}
				   				});
		   				}
		   				else{
		   					$("#updateCst").submit();
		   					return;
		   				}
		   			}
				}
			});
		}
		else if(oldOfficeTel != newOfficeTel && newOfficeTel != ''){
			$.ajax({
		 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+newOfficeTel+"&projGuid=${projGuid}",    //请求的url地址
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
			   				if(oldFaxTel != newFaxTel && newFaxTel !=''){
				   				$.ajax({
			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+newFaxTel+"&projGuid=${projGuid}",    //请求的url地址
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
			   				   				$("#updateCst").submit();
			   				   				return;
			   				   			}
			   						}
				   				});
			   				}else{
			   					$("#updateCst").submit();
			   					return;
			   				}
			   			}
					}
				});
		}
		else if(oldFaxTel != newFaxTel && newFaxTel != ''){
			$.ajax({
		 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+newFaxTel+"&projGuid=${projGuid}",    //请求的url地址
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
			   				$("#updateCst").submit();
			   				return;
			   			}
					}
				});
		}
		else {
			$("#updateCst").submit();
			return;
		}
	}
	
	function checkMust(){
		//alert("repeatSubmit==="+repeatSubmit);
		//alert($("#tel").val().length+"===="+$("#telHome").val().length+"======"+$("#telOffice").val());	
		if(repeatSubmit == false){
			return false;
		}else {
			repeatSubmit = false;
		}
		var returnVal = true;
		if($("#name").val() == '' || $("#tel").val() == ''){
			layer.alert("请将必填项填写完整！");
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
					layer.alert("请将必填项填写完整！");
					returnVal = false;
					return false;
				}
			}
		});
		/* if(typeof($("#telHome").val()) == "undefined"){
			alert("undefined");
		} */
		
		if(typeof($("#tel").val()) != "undefined" && $("#tel").val() != ""){
			if($("#tel").val().length != 11){
				layer.alert("请填写正确格式的手机号!");
				return false;
			}
		}
		if(typeof($("#telHome").val()) != "undefined" && $("#telHome").val() != ""){
			if($("#telHome").val().length != 11){
				layer.alert("请填写正确格式的家庭号!");
				return false;
			}
		}
		if(typeof($("#telOffice").val()) != "undefined" && $("#telOffice").val() != ""){
			if($("#telOffice").val().length != 11){
				layer.alert("请填写正确格式的办公号!");
				return false;
			}
		}
		if(typeof($("#telFax").val()) != "undefined" && $("#telFax").val() != ""){
			if($("#telFax").val().length != 11){
				layer.alert("请填写正确格式的传真号!");
				return false;
			}
		}
		
		var _flg=0;
		var _boolVal=false;
		 $("#isInvalid").next().find("label").each(function(){
	 		if($(this).hasClass("active")==true && $(this).children().val()=="无效"){
	 			_boolVal=true;
				$("#isInvalid").parent().parent().next().find("label").each(function(){
					if($(this).hasClass("active")){
						_flg = 1;
						return false;
				}
				});
			}
		   	 	
	 	});
	 	if(_flg==0 && _boolVal==true){
	 		layer.alert("请选中无效原因！");
			return false;
	 	}
		if(returnVal == false) {
			return false;
		}else {
			return true;
		} 
	}
</script>
<style>
.ui-mobile, .ui-mobile body{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
#ui-page-top{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>
</head>
<body>

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


<div data-role="page" id="demo-page"  data-quicklinks="true" >
      <div data-role="main" class="ui-content">          
<form id="updateCst" class="form-content"  action="/mbem/mcrm/house/customer/insertCstUpdateInfo.action?cstGuid=${cstGuid}&oppGuid=${oppGuid}" method="post"  data-ajax="false">
<div id="msg_1" style="display: block;">
	<input id="defaultTel" name="defaultTel" type="hidden" value="mobileTel">
	<input id="mainMedia" name="mainMediaName" type="hidden" value="${mainMediaName}"><!-- 隐藏域，存放主媒体类型 -->
	<input name="oldName" id="oldName" type="hidden">
	<input name="oldTel" id="oldTel" type="hidden">	
	<div class="form-group border-1px first" data-id="2"> 
    	<ul id="addInfo" class="border-1px">  
    		<c:forEach items="${updateCstInfoList}" var="option" varStatus="status">  
    			<c:if test="${(option.tagType == 'radio' ||option.tagType == 'checkbox')&& option.name !='性别'}"> 			 							    
					<!-- <li class="border-1px"> -->
					<c:if test="${option.isNull == '0' }">
						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
					<c:if test="${option.isNull == '1' }">
						<li class="border-1px"  data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
						<div class="li-inner border-1px"> 
							<c:if test="${option.name != '是否无效'}">
								<c:if test="${option.tagType =='checkbox' }">
									<div id="${option.id}" class="select-head">${option.name}(可多选)</div>
								</c:if>
								<c:if test="${option.tagType !='checkbox' }">
									<div id="${option.id}" class="select-head">${option.name}</div>
								</c:if>
								<div   data-toggle="buttons">
									${option.htmlStr}
								</div>
							</c:if>
							<c:if test="${option.name == '是否无效'}">
								<div id="isInvalid" class="select-head" style="width:80px;float:left;">${option.name}</div>
								<div data-toggle="buttons" style="margin-left:100px" onclick="wuxiaoRe(this)">
									${option.htmlStr}
								</div>
							</c:if>
						</div>
					</li>
				</c:if>
				<c:if test="${option.tagType == 'text' || option.name=='性别' || option.tagType=='textarea' || option.tagType=='select'}">
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
				<div id="wuxiaoRe" style="display:none">
					<li class="border-1px" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
							<div class="li-inner border-1px"> 
								<div  class="select-head">无效原因</div>
								<div data-toggle="buttons">
									<label class="btn"><input type="radio" name="invalidReason" value="1">已购其他项目</label>
									<label class="btn"><input type="radio" name="invalidReason" value="2">观望更好时机</label>
									<label class="btn"><input type="radio" name="invalidReason" value="3">已购买本项目</label>
									<label class="btn"><input type="radio" name="invalidReason" value="4">钱不合适暂不考虑</label>
									<label class="btn"><input type="radio" name="invalidReason" value="5">房源不合适暂不考虑</label>
									<label class="btn"><input type="radio" name="invalidReason" value="6">不能办理贷款</label>
									<label class="btn"><input type="radio" name="invalidReason" value="7">接受不了价格</label>
									<label class="btn"><input type="radio" name="invalidReason" value="8">信息重复</label>
									<label class="btn"><input type="radio" name="invalidReason" value="9">空号 停机</label>
								</div>
							</div>
					</li>
				</div>
		</ul>
		
	</div> 
	
	<div style="height:40px"></div> <!-- 留个空行，防止被取消和保存挡住 -->
</div>

<div class="form-group submit-group noswitchSinglePage">
	<a class="btns btns-default" closeType="${closeType}" id="cancle" data-ac="active" href="#" style="text-align:center; color:#3c6">取消</a>
	<button class="btns btns-primary"  data-ac="active" type="button" onclick="updateCustom()">保存</button>
</div>


</form>
</div>
<!--<div data-role="panel" id="right-panel" data-display="push" data-position="right" data-theme="c" >

    	<ul data-role="listview" data-inset="false" data-theme="a" data-divider-theme="a" data-icon="false" class="jqm-list ui-listview ui-group-theme-a">
			<c:forEach items="${updateCstInfoList}" var="option" varStatus="status">
				<c:if test="${(option.tagType == 'radio' ||option.tagType == 'checkbox')&& option.name !='性别'}">		
					<li><a href="#${option.id}" class="ak47" data-rel="close">${option.name}</a></li>
				</c:if>
			</c:forEach>	
		</ul>
    </div>
	 -->
</div>
<input type="hidden" value="${invalidReason}" id="invaliDvalue" >

<script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#ui-page-top').length){return;}
        $('body').children().not('script').wrapAll('<div id="ui-page-top"></div>');
    }();

</script>


</body>
</html>