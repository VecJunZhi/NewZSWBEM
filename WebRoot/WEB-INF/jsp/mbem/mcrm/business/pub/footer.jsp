<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/yunke.css">
<script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"> </script>	
<script type="text/javascript">
$(document).ready(function(){
	var urlpath = window.location.pathname;
	switch(urlpath){
		case "/mbem/mcrm/business/backlogPage.action":
			$("#backlog p").addClass("icon_30 icon_MyTodo select");
			break;
		case "/mbem/mcrm/business/customPage.action":
			$("#custom p").addClass("icon_30 icon_Customers select");
			break;	
		case "/mbem/mcrm/business/find/findPage.action":
			$("#find p").addClass("icon_30 icon_Find select");
			break; 
			
	};
	$("#backlog").click(function(){window.location.href="/mbem/mcrm/business/backlogPage.action";});
	$("#custom").click(function(){window.location.href="/mbem/mcrm/business/customPage.action";});
	$("#find").click(function(){window.location.href="/mbem/mcrm/business/find/findPage.action";});
	});	
</script>
</head>
<body>
<div class="navigation_foot">
    <ul>
        <li>
            <div id="backlog">
            	<p class="icon_30 icon_MyTodo">待办</p>
            </div>
        </li>
        <li>
            <div id="custom" >
            	<p class="icon_30 icon_Customers">客户</p>
            </div>
        </li>
        <li>
            <div id="find">
            	<p class="icon_30 icon_Find ">发现</p>
            </div>
        </li>
        <li>
            <div>
                <p class="icon_30 icon_Achieve ">业绩</p>
            </div>
        </li>
    </ul>
</div>
</body>
</html>