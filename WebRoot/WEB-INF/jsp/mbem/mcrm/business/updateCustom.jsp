<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>修改客户</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
	<link  rel="Stylesheet" href="/mbem/mcrm/business/css/base.min.css">
	<link  rel="Stylesheet" href="/mbem/mcrm/business/css/user.css">
	<script  src="/common/js/jquery-1.9.1.min.js" ></script>
	<script  src="/common/js/bootstrap/bootstrap.min.js"></script>
<script language="JavaScript" type="text/JavaScript">

<!--

function showhidediv(id){

var age=document.getElementById("msg_2");

var name=document.getElementById("msg_1");

if (id == 'name') {

   if (name.style.display=='none') {

    age.style.display='none';

    name.style.display='block';

   }

} else {

   if (age.style.display=='none') {

    name.style.display='none';

    age.style.display='block';

   }

}   

}

-->

</script>
<script>
	var _oldtel='';
	var _oldhome='';
	var _oldoffice='';
	var flag = 0;
   		$(function ($) {
	    	if (window.history && window.history.pushState) {
	        	$(window).on('popstate', function () {
	                var hash = window.location.hash;
	                //alert("hash=="+hash);
	                //alert(flag+"flag");
	                if (hash != '' && flag == 1 || (hash ==='' && flag == 0)) {
	                    window.location.href="/mbem/mcrm/business/personal.action";
	                }
	        	});
			        window.history.pushState('forward', null, "/mbem/mcrm/business/follow.action");//'./#forward');
			        location.hash="#1"; 
		       
		    }
		}); 
	
	 $(document).ready(function(){
	 
	 	 _oldtel=$("#telPhone").val();
	 	 _oldhome=$("#telhome").val();
	     _oldoffice=$("#teloffice").val();
		//alert(_oldtel+" "+_oldhome+" "+_oldoffice);
	 	$("#addInfo").append("<input id='defaultTel' name='tel' type='hidden'>");//增加隐藏域，用于存放默认手机号
	 	$("input[id^=tel] :last").parent().parent().after("<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'><span class='v'><input id='addTel' style='color:#3c6' value='添加联系电话' readonly></span></div></li>");
	 	
	 	$("#oldName").val($("#name").val());
	 	$("#oldTel").val($("#"+$("#defaultId").val()).val());
	 	
		$("label").attr("state","unselected");	//先置所有选项为未选中状态
		var i=1;
		var j=1;
		for(i=1; i<14; i++){
			var val = $("#sidselect"+i).val();
			if(val == ""|| val == null ||typeof(val)=='undefined')
				continue; 
 			var array = val.split(",");
			for(j=0;j<array.length;j++){
				$("label","#idselect"+i).each(function(){
					if($("input",this).val() == array[j]){
						$(this).attr("state","selected");
						$(this).addClass("active");
					}
				});
			}  
		}
		
		
		$("input[id^='tel']").each(function(){
			if($("#defaultId").val()=="" || $("#defaultId").val()==null){
				$(this).after("<label onclick='return setDefault(this)'>默认</label>");
				return false;
			}
			if($(this).attr("id") == "telPhone") {
				if($(this).attr("id") == $("#defaultId").val()) {
					$("#defaultTel").val($(this).val());
					$(this).after("<label onclick='return setDefault(this)'>默认</label>");
				}
				else {
					$(this).after("<label onclick='return setDefault(this)'>设为默认</label>");
				}
			}
			else{
				if($(this).attr("id") == $("#defaultId").val()) {
					$("#defaultTel").val($(this).val());
					$(this).after("<label onclick='return setDefault(this)'>默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>");
				}
				else {
					$(this).after("<label onclick='return setDefault(this)'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>");
				}
			}
		});
		
		$("#addTel").click(function(){
			var tag = $("#addTel").parent().parent().parent();
			var str1 = "<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'>";
			var str4="</span></div></li>";
			var str2="";
			var str3="<label onclick='return setDefault(this)'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>";
			if($("#telhome").attr("id") == null || $("#telhome").attr("id")==undefined) {
				str2="<span class='k'>家庭</span><span class='v'><input type='text' placeholder='请输入' name='homeTel' id='telhome'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#teloffice").attr("id") == null || $("#teloffice").attr("id")==undefined) {
				str2="<span class='k'>办公</span><span class='v'><input type='text' placeholder='请输入' name='officeTel' id='teloffice'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			/* if($("#telother").attr("id") == null || $("#telother").attr("id")==undefined) {
				str2="<span class='k'>其他</span><span class='v'><input type='text' placeholder='请输入' name='otherTel' id='telother'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}  */	
		});
	});
	
	
	/* 多电话删除操作实现 
	 */
	function tagRemove(tag){//如果删除的是默认的电话，则置手机字段为默认
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
		var telVal = $(tag).prev().val();
		$.ajax({
 			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+telVal,    //请求的url地址
 			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
 			type:"POST",   //请求方式
			success:function(req){
	   			//请求成功时处理
	   			if(req == "NO") {
	   				$(tag).prev().focus();
	   				$(tag).prev().select();
	   				alert("该手机号已登记过，请更换号码重新输入后再进行设置！");
	   			}
	   			if(req == "YES") {
	   				$("input[id^=tel]").each(function(){
						if($(this).next().html() == "默认") {
							$(this).next().html("设为默认");
							}
					});
					$(tag).html("默认");
				}
					
			},
	  		error:function(){
	      			//请求出错处理
	  		}
		});		
	}
	
		 
	function cancle(name){
		showhidediv(name);
 		$("label").each(function(){
			var state = $(this).attr("state");
			var str = $(this).attr("class");
			if(state=="selected")
			{
				if(str.indexOf("active") == -1)
				$(this).addClass("active");
			}if(state == "unselected")
			{
				if(str.indexOf("active") != -1)
				{
					$(this).removeClass("active");
				};
			};  
		});
	}
		 
	function confirm(name){
		showhidediv(name);
		$("input[id^=sidselect]").val("");	//先清除所有的内容
		$("textarea[id^=sidselect]").text("");		
 		$("#msg_2 label").each(function(){
			var str = $(this).attr("class");
			if(str.indexOf("active") != -1)
			{
				$(this).attr("state","selected");
				var id = $(this).parent().attr("id");
				//var option = $("#"+id).attr("stype");
				var option = $("input",this).attr("type");
				if(option == "radio")
					$("#"+"s"+id).val($("input",this).val());
			 	if(option == "checkbox")
				{
					var val = $("#"+"s"+id).val();
					var selVal = $("input",this).val()+",";
					var type = $("#"+"s"+id)[0].type;
					if(type=="textarea")
						$("#"+"s"+id).text(val+selVal);
					else
						$("#"+"s"+id).val(val+selVal);
				}  
			}		
		}); 
	}
		function add_custom(){//手机号失去焦点的验证
			$("input[id^=tel]").each(function(){
				if($(this).next().html() == "默认") {
					$("#defaultTel").val($(this).val());
				}
			});
			var telVal = $("#telPhone").val();
			if(!(/^\d{11}$/.test(telVal))){ 
				alert("手机号码格式有误，请重填");
				return false; 
			}
			if(telVal !=_oldtel){
			  $.ajax({
    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+telVal,    //请求的url地址
    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
    			type:"POST",   //请求方式
   				 success:function(req){
        			//请求成功时处理
        			if(req == "NO") {
        				$("#telPhone").focus();
        				$("#telPhone").select();
        				alert("该号码已登记过，请重新填入号码！");
        				return false;
        			}
        			if(req == "YES")
        			{
        				var _home=$("#telhome").val();
        				if(_home!=undefined && _home !='' && _home !=_oldhome){
	        				if(!(/^\d{11}$/.test(_home))){ 
								alert("家庭号码格式有误，请重填");
								return false; 
							}
	        				$.ajax({
				    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_home,    //请求的url地址
				    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
				    			type:"POST",   //请求方式
				   				 success:function(req){
				        			//请求成功时处理
				        			if(req == "NO") {
				        				$("#telhome").focus();
				        				$("#telhome").select();
				        				alert("该号码已登记过，请重新填入号码！");
				        				return false;
				        			}
				        			if(req == "YES")
				        			{
											var _office=$("#teloffice").val();
					        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
					        				if(!(/^\d{11}$/.test(_office))){ 
												alert("办公号码格式有误，请重填");
												return false; 
											}
						        				$.ajax({
									    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //请求的url地址
									    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
									    			type:"POST",   //请求方式
									   				 success:function(req){
									        			//请求成功时处理
									        			if(req == "NO") {
									        				$("#teloffice").focus();
									        				$("#teloffice").select();
									        				alert("该号码已登记过，请重新填入号码！");
									        				return false;
									        			}
									        			if(req == "YES")
									        			{
									        				$("#form-content").submit();
									        				return true;
									        			}
									    			},
									    			error:function(){
									        			//请求出错处理
									    			}
												});
											}		        				
				        			}
				    			},
				    			error:function(){
				        			//请求出错处理
				    			}
							});
						}else{
							var _office=$("#teloffice").val();
	        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
		        				if(!(/^\d{11}$/.test(_office))){ 
									alert("办公号码格式有误，请重填");
									return false; 
								}
		        				$.ajax({
					    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //请求的url地址
					    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
					    			type:"POST",   //请求方式
					   				 success:function(req){
					        			//请求成功时处理
					        			if(req == "NO") {
					        				$("#teloffice").focus();
					        				$("#teloffice").select();
					        				alert("该号码已登记过，请重新填入号码！");
					        				return false;
					        				
					        			}
					        			if(req == "YES")
					        			{
					        				$("#form-content").submit();
					        				return true;
					        			}
					    			},
					    			error:function(){
					        			//请求出错处理
					    			}
								});
							}else{
								$("#form-content").submit();
							}
						}
        			}
    			},
    			error:function(){
        			//请求出错处理
    			}
			});
			}else{
						var _home=$("#telhome").val();
        				if(_home!=undefined && _home !='' && _home !=_oldhome){
	        				if(!(/^\d{11}$/.test(_home))){ 
								alert("家庭号码格式有误，请重填");
								return false; 
							}
	        				$.ajax({
				    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_home,    //请求的url地址
				    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
				    			type:"POST",   //请求方式
				   				 success:function(req){
				        			//请求成功时处理
				        			if(req == "NO") {
				        				$("#telhome").focus();
				        				$("#telhome").select();
				        				alert("该号码已登记过，请重新填入号码！");
				        				return false;
				        			}
				        			if(req == "YES")
				        			{
											var _office=$("#teloffice").val();
					        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
					        				if(!(/^\d{11}$/.test(_office))){ 
												alert("办公号码格式有误，请重填");
												return false; 
											}
						        				$.ajax({
									    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //请求的url地址
									    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
									    			type:"POST",   //请求方式
									   				 success:function(req){
									        			//请求成功时处理
									        			if(req == "NO") {
									        				$("#teloffice").focus();
									        				$("#teloffice").select();
									        				alert("该号码已登记过，请重新填入号码！");
									        				return false;
									        			}
									        			if(req == "YES")
									        			{
									        				$("#form-content").submit();
									        				return true;
									        			}
									    			},
									    			error:function(){
									        			//请求出错处理
									    			}
												});
											}		        				
				        			}
				    			},
				    			error:function(){
				        			//请求出错处理
				    			}
							});
						}else{
							var _office=$("#teloffice").val();
	        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
		        				if(!(/^\d{11}$/.test(_office))){ 
									alert("办公号码格式有误，请重填");
									return false; 
								}
		        				$.ajax({
					    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //请求的url地址
					    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
					    			type:"POST",   //请求方式
					   				 success:function(req){
					        			//请求成功时处理
					        			if(req == "NO") {
					        				$("#teloffice").focus();
					        				$("#teloffice").select();
					        				alert("该号码已登记过，请重新填入号码！");
					        				return false;
					        				
					        			}
					        			if(req == "YES")
					        			{
					        				$("#form-content").submit();
					        				return true;
					        			}
					    			},
					    			error:function(){
					        			//请求出错处理
					    			}
								});
							}else{
								$("#form-content").submit();
							}
						}
        			
			}
		}
</script>
            <style>
#wrapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>
<body>

    	<form class="form-content" id="form-content" action="/mbem/mcrm/business/updateZsBasicInfoDao.action" method="post" >
    		<input type="hidden" id="defaultId" value="${defaultTelId}">
    		<input type="hidden" name="oldName" id="oldName">
    		<input type="hidden" name="oldTel" id="oldTel">
    		<div id="msg_1" style="display:block;">
    			<div class="form-group border-1px first" data-id="1">
     				<c:forEach items="${updatecustList.field}" var="optionlist1" varStatus="status"> 
    				<div class="form-group border-1px other" data-id="2"> 
    					<ul id="addInfo" class="border-1px">    
    						<c:forEach items="${optionlist1}" var="optionlist" varStatus="status"> 							    
							<li class="border-1px" ${optionlist.isnull} data-ac="active" data-tag="s_customer@cst_name" data-type="text" ${optionlist.click}>
								${optionlist.href}
            						<div class="li-inner border-1px">
            	 						<span class="k">${optionlist.name}</span>
                						<span class="v">
            								${optionlist.htmlstr} 
            							</span>
           							</div>
           						${optionlist.end}
							</li>
							</c:forEach>
						</ul>
					</div>
					</c:forEach>
					<div class="form-group border-1px other" data-id="10">
        				<ul class="border-1px">
        	 				<li class="border-1px" data-require="1" data-ac="active" data-tag="s_opp2gjjl@next_date" data-type="date">
							</li> <!-- 留个空行，防止被取消和保存挡住 -->
        				</ul> 
     				</div>
    			</div> 
     			<div id="action-mask"></div>
    			<div class="form-group submit-group noswitchSinglePage">
       				 <!-- <button class="btn btn-default" id="cancel" data-ac="active">取消</button> --> <a class="btns btns-default" href="/mbem/mcrm/business/cancleUpdateCus.action" style="text-align:center; color:#3c6">取消</a>
        			<button class="btns btns-primary" id="button" type="button" data-ac="active" onclick="add_custom()">保存</button>
    			</div>
 			</div> 
	 		<div id="msg_2" style="display:none;">
	 			<div class="switchSinglePage-wrap">
	 				<div class="switchSinglePage panel" style="-webkit-transform: translate3d(0%, 0px, 0px);">
	 					<div class="scroll">
							<div class="form-group border-1px first">
								<ul class="border-1px">
									<c:forEach items="${updatecustList.option}" var="optionlist1" varStatus="status"> 
									<li class="border-1px">
	 									<div class="li-inner border-1px"><a name="${optionlist1.href}"></a>
											<div class="select-head">${optionlist1.name}</div>
											<div id="${optionlist1.id}" stype="single" data-toggle="buttons">
									<c:forEach items="${optionlist1.value}" var="optionlist" varStatus="status">
										<label class="btn">
	      								<input type="${optionlist1.select}" name="options" id="option1" value="${optionlist.selectName}"> ${optionlist.selectName}
	   									</label>
	   								</c:forEach>
	    						</div>
	    							</li>
									</c:forEach>
	 							</ul>
								<div>
								</div> 
							</div>
						</div>
					</div>
				</div>
	         	<div id="action-mask"></div>
	        	<div class="form-group zure-group noswitchSinglePage">
	        		<input class="btns btns-default" type="button" value="取消" onclick='cancle("name")';>
	       			<input class="btns btns-primary" type="button" value="确定" onclick='confirm("name")';>  
	    		</div>
			</div>
		</form>
	        <script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#wrapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="wrapper"></div>');
    }();
</script>
</body>
</html>