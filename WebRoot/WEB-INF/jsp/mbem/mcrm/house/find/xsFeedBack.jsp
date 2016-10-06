<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<title>高级搜索</title>
<link rel="stylesheet" href="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.css">
<link rel="stylesheet" href="/common/js/jquery.mobile-1.4.5/demos/_assets/css/jqm-demos.css">
<link  rel="stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link  rel="stylesheet" href="/mbem/mcrm/house/css/yunke.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/shu/main.css" />
<link rel="stylesheet" href="/mbem/mcrm/house/css/user.css">
<script  src="/common/js/jquery-1.9.1.min.js" ></script>
<script  src="/common/js/bootstrap/bootstrap.min.js"></script>
<script  src="/common/js/layer/layer.js"></script>
<script  src="/common/js/jquery.mobile-1.4.5/demos/_assets/js/index.js"></script>
<script  src="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.js"></script>
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
	#demo-page :not(INPUT):not(TEXTAREA) {
	    -webkit-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		-o-user-select: none;
		user-select: none;
	}
	dl { font-family: "Times New Roman", Times, serif; padding: 1em; }
	dt { font-size: 2em; font-weight: bold; }
	dt span { font-size: .5em; color: #777; margin-left: .5em; }
	dd { font-size: 1.25em;	margin: 1em 0 0; padding-bottom: 1em; border-bottom: 1px solid #eee; }
	.back-btn { float: right; margin: 0 2em 1em 0; }
</style>
<script type="text/javascript">
$(document).ready(function(){ 

	$("#post").click(function(){
			data={logSubject:encodeURI($("#postSubject").val()),logContent:encodeURI($("#postContent").val()),userName:encodeURI($("#userName").val())};	
			if($("#postSubject").val()=="" || $("#postContent").val()=="" ){
				layer.alert("内容和主题不可为空");
				return;
			}
			$.ajax({
			 	      url : '/mbem/mcrm/house/find/postXsFeedBack.action',//这个就是请求地址对应sAjaxSource
				      data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
			});
			 layer.alert("发布成功");
			 window.setTimeout(function(){
			 	window.location.reload();
			 }, 2000);
	});
	
	$("#resLast").click(function(){
	window.location.href="/mbem/mcrm/house/find/findSearch.action"; 
});
	
});	
function reply(logTime,index){
/* 	alert(logTime);
	alert(index); */
	$("#resContent_"+index).show();
	$("#resConfirm_"+index).show();
	$("#confirm_"+index).click(function(){
		var replyContent = $("#resContentList_"+index).val();
		var replyName = $("#userName").val();
		if(encodeURI(replyContent)==""){
			layer.alert("请填写回复");
			return;
		}
		 $.ajax({
			 	url : '/mbem/mcrm/house/find/addXsReplyContent.action',
				data : {logTime:encodeURI(logTime),replyContent:encodeURI(replyContent),replyName:encodeURI(replyName)},
				type : 'post',
				dataType : 'json',
				success : function(result) {          
				   if(result !=0){
					 window.location.reload(); 
					}
				  },
	    });
		$("#resContent_"+index).hide();
		$("#resConfirm_"+index).hide();
	});
	
	$("#cancel_"+index).click(function(){
	 	$("#resContent_"+index).hide();
		$("#resConfirm_"+index).hide(); 
		$("#resContentList_"+index).val("");
		/* window.location.reload();  */
	});
}
 
</script>
</head>
<body>
<div data-role="page" id="demo-page" class="jqm-demos jqm-panel-page" data-quicklinks="true" >
  <div class="containerF">
	<div class="container">
	   <div class="padding_15">
		 <div class="header">
		   <ul class="tab " style="margin-top: 8px;">
		      <li class="select" _index="0" name="follow"><div style='text-shadow:0 0 0 #000'>建议与反馈</div></li>
		   </ul>
		 </div>
	  </div>
	</div>
  </div>
  <c:forEach items="${zsUpdateLogList}" var="project" varStatus="status">	
  	<div data-role="main" class="ui-content jqm-content" style="border: 0px solid red;margin-bottom: 10px;background-color: #fff;" >
  		<div class="ui-grid-a">
			 <div class="ui-block-a" style="width:22%;text-align:left">
				主题
     		</div> 
    		<div class="ui-block-b" style="width:78%;text-align:right" >
    			${project.userName} 
    		</div>
	  	</div>
		<div class="ui-grid-a" style="margin-left:px">
    		<div class="ui-block-b" style="width:100%;">
    				<div style="margin-top:10px;margin-left:30px">
    					${project.logSubject}
    				</div>
    			 <!--  <input type="text" name="" id="" placeholder="" value=${project.logSubject} readonly
    			style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none;width:100%"
    			/>  -->
    		</div> 
	  	</div> 
	  	<div class="ui-grid-a">
    		<div class="ui-block-b" style="width:100%;margin-top:10px">
    			内容 
    			<div style="margin-top:10px;margin-left:30px">
    				${project.logContent}
    			</div>
    			<%-- <textarea rows="8" cols="5"  id="" readonly  
    			style="BORDER-BOTTOM-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-TOP-STYLE: none;"
    			>${project.logContent}</textarea>  --%>
    		</div> 
	  	</div>
	  	<div class="ui-grid-a">
    		<div class="ui-block-b" style="width:100%;margin-top:10px" id="resList">
    			回复
    			<div style="margin-top:10px;margin-left:30px">
    			<c:forEach items="${project.zsReplyFeedEntityList}" var="project2" varStatus="status2">	
    			   <p style="">${project2.replyName}:${project2.replyContent}</p><br/>
    			</c:forEach></div>
    			</div> 
	  	</div>
	  	<div class="ui-grid-a">
    		<div class="ui-block-b" style="width:100%;text-align:right">
    			<a id="reply" onclick="reply('${project.logTime}' , '${status.index}')" value="发布" >回复</a>
    		</div> 
	  	</div> 
	  	<div class="ui-grid-a" id="resContent_${status.index}" hidden>
    		<div class="ui-block-b" style="width:100%"  >
    			<input  id="resContentList_${status.index}" value=""/>
    		</div> 
    	</div>
    	<div class="ui-grid-a" id="resConfirm_${status.index}" hidden>
    		<div class="ui-block-a" style="width:85%;text-align:right"  >
    			<p id="confirm_${status.index}"><a>确定 </a></p>
    		</div> 
    		<div class="ui-block-b" style="width:15%;text-align:right"  >
    			<p id="cancel_${status.index}"><a>取消 </a></p>
    		</div> 
        </div>
	</div>
  </c:forEach>
  <div data-role="main" class="ui-content jqm-content" style="border: 0px solid red;margin-bottom: 10px;background-color: #fff;" >
  	<div class="ui-grid-a" style="margin-left:px">
    		<div class="ui-block-b" style="width:100%;">
    			主题<input type="text" name="" id="postSubject" placeholder="" value="" >
    		</div> 
	  	</div> 
	  	<div class="ui-grid-a">
    		<div class="ui-block-b" style="width:100%">
    			内容<textarea rows="8" cols="5"  id="postContent" ></textarea>
    		</div> 
	  	</div>
	  	<input id="userName" value=${userName} type="hidden" />
	  	<div class="ui-grid-a">
		  	<div class="ui-block-a" style="margin-left:0px">
	    		<!-- <div class="ui-block-b" style="width:100%;text-align:center" >
	    			<p id="post"><a>取消 </a></p>
	    		</div> -->
	    		<button type="button" id="resLast" style="background-color: #33cc66;border-radius: 5px;color:#FFF;text-shadow: 0 0 0 #000;font-size : 1em;">取消</button>  
		   </div>
		   <div class="ui-block-b" style="margin-left:0px">
	    		<!-- <div class="ui-block-b" style="width:100%;text-align:center" >
	    			<p id="post"><a>发布 </a></p>
	    		</div> --> 
	    		<button type="button" id="post" style="background-color: #33cc66; border-radius: 5px;color:#FFF;text-shadow: 0 0 0 #000;font-size : 1em;">发布</button> 
		   </div>
	   </div>
  </div>	
</body>
</html>