<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>兆盛销售系统移动端更新日志</title>
<link rel="stylesheet" href="/mbem/mcrm/business/css/shu/main.css" />
<link rel="stylesheet" href="/mbem/mcrm/business/css/shu/style.css" />
<style type="text/css">
h2.top_title{border-bottom:none;background:none;text-align:center;line-height:32px; font-size:20px}
h2.top_title span{font-size:12px; color:#666;font-weight:500}
</style>
</head>
<body>
<h2 class="top_title">兆盛系统更新日志</h2>
<section id="cd-timeline" class="cd-container">
	<c:forEach items="${zsUpdateLogList}" var="project" varStatus="status">
	  <div class="cd-timeline-block">
		<div class="cd-timeline-img cd-picture">
			<img src="/mbem/mcrm/business/css/shu/img/cd-icon-picture.svg" alt="Picture">
		</div>
		<div class="cd-timeline-content">
           <h2>${project.logSubject}</h2>
		   <p>${project.logContent}</p>
		   <span class="cd-date">${project.logTime}</span>
		</div>
      </div>
    </c:forEach>	
</section>
 <div style="text-align:center">
   <!-- <a href="/mbem/mcrm/business/find/addBusUpdateLog.action" data-ac="active">
     <img src="/mbem/mcrm/business/images/new_add_default.png" alt="新增">
   </a> -->
   <!--  <a href="/mbem/mcrm/business/find/findPage.action" data-ac="active">
     <img src="/mbem/mcrm/business/images/find_esc.png" alt="返回">
   </a> -->
</div> 
<div id="footer">
    <p><a>Powered by 兆盛地产 </a></p>
</div>
</body>
</html>