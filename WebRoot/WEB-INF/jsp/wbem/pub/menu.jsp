<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!--sidebar-menu-->
<%
	String menuId = com.zs.common.util.RequestUtil.getStringParameter(request,"menuId","0");
	request.setAttribute("menuId", menuId);
 %>
<input id="selectMenu" type="hidden" menuId="${menuId }" />
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i></a>
  <ul>
	<c:forEach var="mainMenuMap" items="${menuMap}">
  	  <c:forEach var="menuMap" items="${mainMenuMap.value}">
  		<li class="submenu" id="${mainMenuMap.key}" style="display:none"> <a href="#"> <i class="icon icon-th-list"></i><span>${fn:substringBefore(menuMap.key,":")}</span> <span class="label label-important">${fn:length(menuMap.value)}</span></a>
      	  <c:if test="${fn:length(menuMap.value)>0}"> 
      		<ul>
		  	  <c:forEach var="menu" items="${menuMap.value}">
		  		<li id="${menu.menuId}"><a href="${menu.menuURL}?menuId=${menu.menuId}">${menu.menuName}</a></li>
		  	  </c:forEach>
  			</ul>
      	   </c:if> 
    	</li>	
  	  </c:forEach>   
	</c:forEach>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a href="/wbem/index.action" title="·µ»ØÊ×Ò³" class="tip-bottom"><i class="icon-map-marker"></i> Ê×Ò³</a></div>
  </div>
<!--End-breadcrumbs-->