<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<!--Action boxes-->
<div class="container-fluid">
<!--   <div class="quick-actions_homepage">
    <ul class="quick-actions">
      <li class="bg_lb"> <a href="index.html"> <i class="icon-dashboard"></i> <span class="label label-important">20</span> My Dashboard </a> </li>
      <li class="bg_lg span3"> <a href="charts.html"> <i class="icon-signal"></i> Charts</a> </li>
      <li class="bg_ly"> <a href="widgets.html"> <i class="icon-inbox"></i><span class="label label-success">101</span> Widgets </a> </li>
      <li class="bg_lo"> <a href="tables.html"> <i class="icon-th"></i> Tables</a> </li>
      <li class="bg_ls"> <a href="grid.html"> <i class="icon-fullscreen"></i> Full width</a> </li>
      <li class="bg_lo span3"> <a href="form-common.html"> <i class="icon-th-list"></i> Forms</a> </li>
      <li class="bg_ls"> <a href="buttons.html"> <i class="icon-tint"></i> Buttons</a> </li>
      <li class="bg_lb"> <a href="interface.html"> <i class="icon-pencil"></i>Elements</a> </li>
      <li class="bg_lg"> <a href="calendar.html"> <i class="icon-calendar"></i> Calendar</a> </li>
      <li class="bg_lr"> <a href="error404.html"> <i class="icon-info-sign"></i> Error</a> </li>

    </ul>
  </div> -->
</div>
<!--End-Action boxes-->    
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>
<script>
	$(document).ready(function(){
		var systemId = ${systemId};
		if(systemId != 0) {
			$(".submenu").css("display","none");
			var i = 1;
			//���µ�ǰѡ���ϵͳ������ϵͳ������ϵͳ���鷿ϵͳ��
			$("#systemList li").each(function(){
				if($(this).attr("id") == "tab_"+systemId){
					$("#profile-messages span").text($(this).text());
				}
			});
			//�������˵���,Ĭ��չ����һ���˵�
			$(".submenu").each(function(){
				if($(this).attr("id") == systemId) {
					$(this).css("display","block");
					if($(window).width() > 1400) {
						if(i == 1) {
							$(this).addClass("open");
							i = 0;
						}
					}
				}
			});
		}
	});
</script>