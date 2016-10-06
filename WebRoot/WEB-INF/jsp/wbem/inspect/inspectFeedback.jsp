<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid" style="margin-top:15px">
	<div id="tab1" class="tab-pane active"> 
		<form action="#" method="get" class="form-horizontal">
			<input type="hidden" value="${issuesId}" id="issuesId"/>
			<input type="hidden" value="${status}" id="status"/>
		    <div class="control-group noborder-bottom">
		   		<label class="control-label">验收人 :</label>
				<div class="controls">
					<select id="inspectMan" style="width:220px">
					 	<option value=""></option>
					 	<!-- <option value="谭春鹿">谭春鹿</option> -->
					 	<option value="姚统宇">姚统宇</option>
					 	<option value="李成龙">李成龙</option>
					 	<option value="侯君官">侯君官</option>
					 	<!-- <option value="张星岳">张星岳</option> -->
					 	<%-- <c:forEach items="${employeeList}" var="employee" varStatus="status">
					 		<option value="${employee}">${employee}</option>
					 	</c:forEach> --%>	
					</select>
					<span style="color:red;margin-left:10px;">*</span>
				</div>
		    </div>
			<div class="control-group noborder-bottom"> 
				<label class="control-label">问题描述 :</label>
				<div class="controls">
				<textarea rows="5" id="backContent"></textarea>
				</div>
			</div>
			<div class="row-fluid ">
				<div class="control-group noborder-bottom noborder-top span5">  
					<div class="controls">
			        	<button type="button" class="btn btn-success" id="sureInspect">确定</button>
			            <button type="button" class="btn btn-warning" id="cancleInspect">取消</button>
			        </div>
			    </div>
			</div>
		</form>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
$(document).ready(function(){
	//查询
	$("#cancleInspect").click(function(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
	
	$("#sureInspect").click(function(){
		var inspectMan = encodeURI($('#inspectMan').find('option:selected').val());
		var backContent = encodeURI($("#backContent").val());
		var issuesId = $("#issuesId").val();
		var status = $("#status").val();
		if(inspectMan == null || inspectMan == "") {
			layer.alert("请将必填项填写完整！");
			return false;
		}
		$.ajax({
	         url : '/wbem/inspect/addIssuesFeedback.action',
	         data : {"inspectMan":inspectMan,"backContent":backContent,"issuesId":issuesId,"status":status},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	         type : 'post',
	         dataType : 'json',
	         success : function(flag) {          
	          	if(flag == 1){
	          		var index = parent.layer.getFrameIndex(window.name);
					parent.$('.data-table').DataTable().ajax.reload();
					parent.layer.close(index);
	          	}
	          	if(flag == 0){
	          		layer.alert("新增失败！");
	          	}
	         },
	         error : function(msg) {
	          	
	         }
    	});
	});
	
	$('select').select2();
	//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>