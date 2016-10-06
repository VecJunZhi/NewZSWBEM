<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<link rel="stylesheet" href="/wbem/css/zsdc-gx-style.css" />
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="content" style="overflow:auto; padding-top:10px; background-color:#FFF">
  <div class="wrapper">
     <!-- <hr class="line-left">
     <hr class="line-right"> -->
    <div class="main">
       <h1 class="title">兆盛信息管理系统更新日志</h1>
	   <div class="year">
		   <h2><a href="#"></a></h2>
		   <div class="list">
			<ul>
			  <c:forEach items="${showLogList}" var="showLog" varStatus="status">
				<li class="cls highlight">
					<p class="date">${showLog.logTime}</p>
					<p class="intro">${showLog.logSubject}</p>
					<p class="version">&nbsp;</p>
					<div class="more">
					<p>${showLog.logContent}</p>
					</div>
				</li>
			  </c:forEach>
			</ul>
	      </div>
	   </div> 
    </div>
  </div>
  <div class="clear">
  </div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<script>
	$(".main .year .list").each(function (e, target) {
	    var $target=  $(target),
	        $ul = $target.find("ul");
	    $target.height($ul.outerHeight()), $ul.css("position", "absolute");
	}); 
	$(".main .year>h2>a").click(function (e) {
	    e.preventDefault();
	    $(this).parents(".year").toggleClass("closes");
	});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>