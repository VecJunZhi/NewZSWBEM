<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid" style="margin-top:15px;">
       <div id="tab1" class="tab-pane active"> 
	  <form action="#" method="get" class="form-horizontal">
	  	<input type="hidden" value="${issuesId}" id="issuesId"/>
	  	<input type="hidden" value="${status}" id="status"/>
        <div class="control-group noborder-bottom" style="width:400px">
      		<label class="control-label">修复人 :</label>
	       <div class="controls" style="width:300px">
	         <input type="text" id="repairMan"/><span style="color:red;margin-left:10px;">*</span>
	       </div>
	    </div>
		<div class="control-group noborder-bottom" style="width:400px"> 
      			<label class="control-label">修复描述 :</label>
	       <div class="controls" style="width:300px">
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
		var repairMan = encodeURI($('#repairMan').val());
		var backContent = encodeURI($("#backContent").val());
		var issuesId = $("#issuesId").val();
		if(repairMan == null || repairMan == ""){
			layer.alert("请将必填项填写完整！");
			return false;
		}
			
		$.ajax({
	         url : '/wbem/inspect/addRepairFeedback.action',
	         data : {"repairMan":repairMan,"backContent":backContent,"issuesId":issuesId},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
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