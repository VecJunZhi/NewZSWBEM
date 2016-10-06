<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>新增客户</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link  rel="Stylesheet" href="/mbem/mcrm/business/css/base.min.css">
    <link  rel="Stylesheet" href="/mbem/mcrm/business/css/user.css">
    <link  rel="stylesheet" href="/mbem/mcrm/business/css/common.css">
     <link rel="stylesheet" href="/mbem/mcrm/business/css/datey.css" >  
    <script  src="/common/js/jquery-1.9.1.min.js" ></script>
    <script  src="/mbem/mcrm/business/js/date.js" ></script>
    <script  src="/mbem/mcrm/business/js/iscroll.js" ></script>
    <script  src="/mbem/mcrm/business/js/addCustom.js"></script>
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

   if (age.style.display=='none'){

    name.style.display='none';

    age.style.display='block';

   }

}   
	
}

-->

</script>
<script type="text/javascript">
$(function(){
	$('#beginTime').date();
	$('#endTime').date({theme:"datetime"});
});
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$("label").attr("state","unselected");	
		$("#beginTime").val("${autoGenNextFollowDate}");
		$("#telPhone").after("<label onclick='return setDefault(this)'>默认</label>");
		$("#addInfo").append("<input id='defaultTel' name='tel' type='hidden'>");//增加隐藏域，用于存放默认手机号
		
		var newAddTel = ${newAddTel};
		$("#telPhone").val(newAddTel);//查询到是新客户时，新增时将号码自动添加到手机字段
		
		var tag1 = $("#telPhone").parent().parent().parent();
		$(tag1).after("<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'><span class='v'><input id='addTel' style='color:#3c6' value='添加联系电话' readonly></span></div></li>");
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
		
/* 		$("#telPhone").blur(function(){//手机号失去焦点的验证
			var telVal = $("#telPhone").val();
			$.ajax({
    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+telVal,    //请求的url地址
    			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
    			type:"POST",   //请求方式
   				 success:function(req){
        			//请求成功时处理
        			if(req == "NO") {
        				$("#telPhone").focus();
        				$("#telPhone").select();
        				$("#telPhone").val('');
        				alert("该手机号已登记过，请更换号码重新输入！");
        			}
        			if(req == "YES")
        			{
        			}
    			},
    			error:function(){
        			//请求出错处理
    			}
			});
		}); */
		
		$("#addTel").click(function(){
			var tag = $("#addTel").parent().parent().parent();
			var str1 = "<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'>";
			var str4="</span></div></li>";
			var str2="";
			var str3="<label onclick='return setDefault(this)'>设为默认</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>";
			if($("#telhome").attr("id") == null || $("#telhome").attr("id")==undefined) {
				str2="<span class='k'>家庭</span><span class='v'><input type='text' placeholder='请输入' name='homeTel' id='telhome' >";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#teloffice").attr("id") == null || $("#teloffice").attr("id")==undefined) {
				str2="<span class='k'>办公</span><span class='v'><input type='text' placeholder='请输入' name='officeTel' id='teloffice' >";
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
	function tagRemove(tag){
		if($(tag).prev().html() == "默认") {
			$("#telPhone").next().html("默认");
		}
		var removeTag = $(tag).parent().parent().parent();
		$(removeTag).remove();
		//time = time-1;
	}
	/*多电话的验证*/
	function add_custom(){//手机号失去焦点的验证
			var _bool=checkMust();
			if(!_bool){
				return false;
			}
			var telVal = $("#telPhone").val();
			if(!(/^\d{11}$/.test(telVal))){ 
				alert("手机号码格式有误，请重填");
				return false; 
			}
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
        				if(_home!=undefined && _home !=''){
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
					        				if(_office!=undefined && _office !=''){
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
	        				if(_office!=undefined && _office !=''){
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

	function checkMust(){
	 	var _bool=true;
			 //$("#name").focus();
			 $("input[id^=tel]").each(function(){
				if($(this).next().html() == "默认") {
					$("#defaultTel").val($(this).val());
				}
			 });
			 $("#msg_1 li").each(function(){  
				var isnull = $(this).attr("data-require");
				//alert($("div",this).children().next().children().val());
 	 			 if(isnull == 1){
					//var val = $("input",this).val();
					var val = $("div",this).children().next().children().val();
					if(val == null || val == "" || typeof(val)=='undefined') {

						alert("请将必填项填写完整  (用＊标注)");
						//alert($(this).html());
						_bool=false;
						return false;
					}
				} 
			});
			return _bool;
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
		$("textarea[id^=sidselect]").val("");//	
		$("#msg_2 label").each(function(){
			var str = $(this).attr("class");
			if(str.indexOf("active") != -1)
			{
				$(this).attr("state","selected");
				var id = $(this).parent().attr("id");
				var option = $("input",this).attr("type");
				if(option == "radio"){
					$("#"+"s"+id).val($("input",this).val());
				}
			 	if(option == "checkbox")
				{
					var val = $("#"+"s"+id).val();
					var selVal = $("input",this).val()+",";
					$("#"+"s"+id).val(val+selVal);
				}  
			}			
		});
	}
</script>
</head>
<body>
<div id="warpBox">
<div class="container" id="container">
    <form class="form-content" id="form-content" action="${pageContext.request.contextPath}/mbem/mcrm/business/insertZsBasicInfoDao.action" method="post" >
    	<div id="msg_1" style="display:block;">
    		<c:forEach items="${addCustMap.field}" var="addCustInfo" varStatus="status"> 
    		<div class="form-group border-1px first" data-id="2"> 
    			<ul id="addInfo" class="border-1px">    
    				<c:forEach items="${addCustInfo}" var="custInfo" varStatus="status"> 							    
					<li class="border-1px" ${custInfo.isnull} data-ac="active" data-tag="s_customer@cst_name" data-type="text" ${custInfo.click}>
						${custInfo.href} 
            			<div class="li-inner border-1px">
           		 			<span class="k">${custInfo.name}</span>
               				<span class="v">${custInfo.htmlstr}</span>
           				</div>
           				${custInfo.end}  
					</li>
					</c:forEach>
				</ul>
			</div>
			</c:forEach>
            <div></div>
			<div style="height:40px"></div> <!-- 留个空行，防止被取消和保存挡住 -->
     		<div id="action-mask"></div>
    		<div class="form-group submit-group noswitchSinglePage">
        		<a class="btns btns-default" href="/mbem/mcrm/business/customPage.action" style="text-align:center; color:#3c6">取消</a>
        		<button class="btns btns-primary" id="button" data-ac="active" type="button" onclick="return add_custom()">保存</button>
    		</div>
 		</div>
	 	<div id="msg_2" style="display:none;">
			<div class="switchSinglePage-wrap">
				<div class="switchSinglePage panel" style="-webkit-transform: translate3d(0%, 0px, 0px);">
					<div class="scroll">
						<div class="form-group border-1px first">
							<ul class="border-1px">
							<c:forEach items="${addCustMap.option}" var="addCustOption" varStatus="status"> 
								<li class="border-1px">
									<div class="li-inner border-1px"> <a name="${addCustOption.href}"></a>
										<div class="select-head">${addCustOption.name}</div>
										<div id="${addCustOption.id}" stype="single" data-toggle="buttons">
										<c:forEach items="${addCustOption.value}" var="custOption" varStatus="status">
											<label class="btn">
	      										<input type="${addCustOption.select}" name="options" id="option1" value="${custOption.selectName}"> ${custOption.selectName}
	   										</label>
	   									</c:forEach>
	   									</div>
									</div>
								</li>
							</c:forEach>
	 						</ul> 
						</div>
						<div></div>
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
</div>
</div>
<div id="datePlugin"></div>
</body>
</html>