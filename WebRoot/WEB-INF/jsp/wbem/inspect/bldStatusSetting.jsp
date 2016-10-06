<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<%-- <div style="margin-top:15px;margin-left:15px;"> 
		<form action="#" method="get" class="form-horizontal">
			<div class="control-group noborder-top noborder-bottom">
				<label class="control-label zygw" >选择项目</label>
				<div class="controls span5" style="margin-left: 0">
					<select id="projGuid">
						<c:forEach items="${projectList}" var="project" varStatus="status">
						<option value="${project.id}">${project.name}</option>
						</c:forEach>
					</select>
				</div>  
				<div class="controls">
					<button type="button" class="btn btn-success" onclick="loadBldStatusInfoById()">搜索</button>
				</div>
			</div> 
		</form>
	</div> --%>
	<div id="bldStatusInfo">	
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
function loadBldStatusInfoById(){
	//var projGuid = $("#projGuid option:selected").val();
	$.ajax({
		url: '/wbem/inspect/loadBldStatusInfoById.action',
		//data: {"projGuid":projGuid},
		type: 'post',
		dataType: 'html',
		error:function(){
			
		},
		success:function(data){
			$("#bldStatusInfo").html(data);
		},
	});
}

$(document).ready(function(){
	
	loadBldStatusInfoById();
	//查询
	$('select').select2();
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>