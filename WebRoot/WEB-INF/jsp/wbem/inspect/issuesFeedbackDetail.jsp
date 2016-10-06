<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>问题反馈记录列表</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
			<input type="hidden" id="issuesId" value="${issuesId}" >
         </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th>
						<th>反馈时间</th>
						<th>反馈内容</th>
						<th>验收人/修复人</th>
						<th>反馈状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/wbem/houses/customer/businessUrgedCommon.jsp" %>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

$(document).ready(function(){
	//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$("#resetSearch").click(function(){
		$("#userGuid").select2("val","");
		$("#telOrName").val("");
		$("input[type='checkbox']").attr("checked",false);
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"ajax": {
			type: "post",//后台指定了方式。
         	url: "/wbem/inspect/loadIssuesFeedbackList.action",
		 	data:function(d){			//外部参数传递
		 		d.issuesId = $("#issuesId").val();
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"createDate"},
         	{"data":"backContent"},
         	{"data":"repairMan"},
         	{"data":"fbType"},

         ],
         /*  "columnDefs":[
	        { "sortable": false, "targets":[0,2,4]}
          ] , 
         "ordering":true,
         "order": [1,"desc"], */	  
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