<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
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
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/ui.css">
<script  src="/common/js/jquery-1.9.1.min.js"></script>
<script  src="/mbem/mcrm/business/js/ui.js"></script>
<script  src="/common/js/layer/layer.js"></script> 
<title>切换项目</title>
    <script >
    	$(document).ready(function(){
    		$("#search_input").click(function(){
    			history.back();
    		});
    	});
    </script>
</head>
<body id="find_index_body">
	<c:if test="${projectInfo != null}">
	 <div class="xuanzhe">${projectInfo}</div>
	 </c:if> 
     <div class="find_index_box clearfix" style="position: relative;">
     	<c:forEach var="project" items="${projectList}">
     	<%-- <c:if test="${project.id != projGuid}"> --%>
       	<div class="find_group clearfix">
            <a class="switchgroup" href="/mbem/sureSelectProject.action?projGuid=${project.id}">
                <i class="switch"></i>
                <div class="a_border_half" >
                    <span class="tit">${project.name}</span>
                    <span class="name">点击进入</span>
                    <span class="arrow"></span>
                </div>
            </a>
        </div>
        <%-- </c:if> --%>
        </c:forEach>
        
        <%-- <c:if test="${sysInfo == null}"> --%>
        	<!-- <div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
                    <div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="返回" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
        	</div> -->
         <%-- </c:if> --%>
    </div>
</body>
</html>
