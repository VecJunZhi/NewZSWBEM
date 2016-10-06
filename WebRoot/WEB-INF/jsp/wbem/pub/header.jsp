<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>兆盛综合信息管理系统</title>
<meta charset="GBK" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/wbem/css/bootstrap.min.css" />
<link rel="stylesheet" href="/wbem/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/wbem/css/datepicker.css" />
<link rel="stylesheet" href="/wbem/css/uniform.css" />
<link rel="stylesheet" href="/wbem/css/select2.css" />
<link rel="stylesheet" href="/wbem/css/zsdc-style.css" />
<link rel="stylesheet" href="/wbem/css/zsdc-media.css" />
<link rel="stylesheet" href="/wbem/css/jquery-ui.css" />
<link rel="stylesheet" href="/wbem/css/dataTables.jqueryui.min.css" />
<link href="/wbem/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/wbem/css/fonts.css" />
<link href='' rel='stylesheet' type='text/css'>
<script src="/wbem/js/jquery.min.js"></script> 
<script src="/wbem/js/ajaxfileupload.js"></script> 
<script src="/common/layer/layer.js"></script>
<script>
$(document).ready(function(){
	$('#changePwd').on('click', function(){
		layer.open({
	        type: 2,
	        title: '修改密码',
	        shadeClose: false, //点击遮罩关闭层
	        area : ['600px' , '440px'],
	        content: '/wbem/index/changePassword.action'
	    });
	});
});
</script>
</head>
<body>
<!--Header-part-->
<div id="header">
  <h1></h1>
</div>
<!--close-Header-part--> 
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
  	<!-- <li class=""><a title="" href="/wbem/index.action"><i class="icon icon-home"></i> <span class="text">首页</span></a></li> -->
  	<li class="dropdown" id="projectInfo">
	    <a title="" href="#" data-toggle="dropdown" data-target="#projectInfo" class="dropdown-toggle">
	    	<i class="icon icon-home"></i> <span class="text tip-bottom" data-original-title="切换项目">切换项目</span><b class="caret"></b>
	    </a>
      	<ul class="dropdown-menu" id="projectList">
      		<c:forEach var="project" items="${projectList}" varStatus="i">
      			<li projGuid="${project.id}" onclick="switchProject(this)"><a href="#" ><i class="icon-tint"></i>${project.name}</a></li>
	        	<c:if test="${i.index != fn:length(projectList)-1}">
	        		<li class="divider"></li>
	        	</c:if>
      		</c:forEach>
      	</ul>
    </li>
    <li  class="dropdown" id="profile-messages" >
    	<a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
    		<i class="icon icon-user"></i>  <span class="text tip-bottom" data-original-title="切换系统">系统选择</span><b class="caret"></b>
    	</a>
      <ul class="dropdown-menu" id="systemList">
        <c:forEach var="item" items="${sysList}" varStatus="i">
	 	  <li id="tab_${item.subSystemId}" onclick="switchTab(${item.subSystemId})"><a href="#"><i class="icon-check"></i>${item.subSystemName}</a></li>
          <c:if test="${i.index != fn:length(sysList)-1}">
            <li class="divider"></li>
          </c:if>
        </c:forEach>
      </ul>
    </li>
  </ul>
</div>
<div id="right-nav" class="navbar navbar-inverse">
  <ul class="nav">
  	<li class=""><a title="" href="/wbem/index/showLogList.action"><i class="icon icon-book"></i> <span class="text">系统更新日志</span></a></li> 
<!--     <li class="dropdown" id="menu-messages1"><a href="#" data-toggle="dropdown" data-target="#menu-messages1" class="dropdown-toggle"><i class="icon icon-bell"></i> <span class="text">消息</span> <span class="label label-important">2</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> 未读通知</a></li>
        <li class="divider"></li>
        <li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> 待办事件</a></li>
        <li class="divider"></li>
        <li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> 发件箱</a></li>
        <li class="divider"></li>
        <li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> 垃圾箱</a></li>
      </ul>
    </li> -->
    <li class="dropdown" id="set">
	    <a title="" href="#" data-toggle="dropdown" data-target="#set" class="dropdown-toggle">
	    	<i class="icon icon-cog"></i> <span class="text">设置</span><b class="caret"></b>
	    </a>
      	<ul class="dropdown-menu">
	        <li><a class="sAdd" id="changePwd"  href="#" ><i class="icon-plus"></i>修改密码</a></li>
	        <li class="divider"></li>
	        <li><a class="sAdd"  href="#"><i class="icon-plus"></i>关于兆盛地产</a></li>
      	</ul>
    </li>
    <li class=""><a title="" href="/wbem/logout.action"><i class="icon icon-share-alt"></i> <span class="text">注 销</span></a></li> 
  </ul>
</div>
<!--close-top-Header-menu-->
<!--start-top-serch
<div id="search">
  <input type="text" placeholder="请在这里搜索"/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>
-->
<!--close-top-serch-->
<script>
	$(document).ready(function(){
		var projGuid = ${projGuid};
		if(projGuid==null || projGuid=="" || projGuid=="null") {
			layer.open({
		        type: 2,
		        title: '选择项目',
		        maxmin: false,       //开启最大最小化
		        shadeClose: false, //点击遮罩关闭层
		        closeBtn:0, //禁止关闭按钮
		        area : ['300px' , '280px'],
		        content: "/wbem/index/selectProject.action"
	    	});
		}else{
			$("#projectList li").each(function(){
				if($(this).attr("projGuid") == projGuid) {
					$("#projectInfo span").text($(this).text());
				}
			});
		}
	});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/menu.jsp" %>