<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid" >
	<form action="#" method="post" class="form-horizontal"> 
	 	 <div class="row-fluid ">
	     	<div style="float:left;width:150px;">
	        	<select id="projGuid">
	            	<c:forEach items="${projectList}" var="project" varStatus="status">
              			<option value="${project.id}">${project.name}</option>
              	  	</c:forEach>
	            </select>
	        </div>
	        <div style="float:left;margin-left:15px;">
            	<button type="button" class="btn btn-success" onclick="sureSelect()">确定</button>
         	</div>
	     </div> 
      </form> 
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<script>
	function sureSelect() {
		var projGuid = $("#projGuid option:selected").val();
		$.ajax({
	        url: "/wbem/index/sureSelectProject.action",
	        type:"POST",
	        data: {"projGuid":projGuid},
	        dataType: "json",
	        success: function (data) {
	        	if(data == 1) {
	        		$("#projectList li",parent.document).each(function(){
	        		//parent.$("#projectList li").each(function(){
						if($(this).attr("projGuid") == projGuid) {
							$("#projectInfo span",parent.document).text($(this).text());
						}
					});
	        		var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
	        	} else {
	        		layer.alert("项目选择失败！");
	        	}
	        },
	        error: function() {
	        }
		}); 
	}
	
	$('select').select2();
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp"%>