<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mbem/mcrm/house/css/yunke.css">
<script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"> </script>	
<script type="text/javascript">
$(document).ready(function(){
	var urlpath = window.location.pathname;
	switch(urlpath){
		case "/mbem/mcrm/house/customer/backlogPage.action":
		case "/mbem/mcrm/house/customer/businessUrged.action"://�������Ѻ�ҵ��߰�ҳ���Ǵ���ͼ�����
			$("#backlog p").addClass("icon_30 icon_MyTodo select");
			break;
		case "/mbem/mcrm/house/customer/customPage.action":
			$("#custom p").addClass("icon_30 icon_Customers select");
			break;	
		case "/mbem/mcrm/house/find/findSearch.action":
			$("#find p").addClass("icon_30 icon_Find select");
			break;
		case "/mbem/mcrm/house/myPerformance/myRank.action":
			$("#rank p").addClass("icon_30 icon_Achieve select");
			break; 
			
	};
	$("#backlog").click(function(){window.location.href="/mbem/mcrm/house/customer/backlogPage.action";});
	$("#custom").click(function(){window.location.href="/mbem/mcrm/house/customer/customPage.action";});
	$("#find").click(function(){window.location.href="/mbem/mcrm/house/find/findSearch.action";});
	$("#rank").click(function(){window.location.href="/mbem/mcrm/house/myPerformance/myRank.action";});
});	
</script>
</head>
<body>
<div class="navigation_foot">
    <ul>
        <li>
            <div id="backlog">
            	<p class="icon_30 icon_MyTodo">����</p>
            </div>
        </li>
        <li>
            <div id="custom" >
            	<p class="icon_30 icon_Customers">�ͻ�</p>
            </div>
        </li>
        <li>
            <div id="find">
            	<p class="icon_30 icon_Find ">����</p>
            </div>
        </li>
        <li>
            <div id="rank">
                <p class="icon_30 icon_Achieve ">ҵ��</p>
            </div>
        </li>
    </ul>
</div>
</body>
</html>