<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid" style="margin-top:15px;">
       <div id="tab1" class="tab-pane active"> 
	  <form action="#" method="get" class="form-horizontal">
	  	<input type="hidden" value="${issuesId}" id="issuesId"/>
	  	<input type="hidden" value="${status}" id="status"/>
        <div class="control-group noborder-bottom" style="width:400px">
      		<label class="control-label">�޸��� :</label>
	       <div class="controls" style="width:300px">
	         <input type="text" id="repairMan"/><span style="color:red;margin-left:10px;">*</span>
	       </div>
	    </div>
		<div class="control-group noborder-bottom" style="width:400px"> 
      			<label class="control-label">�޸����� :</label>
	       <div class="controls" style="width:300px">
	         <textarea rows="5" id="backContent"></textarea>
	       </div>
	    </div>
           <div class="row-fluid ">
		<div class="control-group noborder-bottom noborder-top span5">  
           	<div class="controls">
           	  <button type="button" class="btn btn-success" id="sureInspect">ȷ��</button>
                 <button type="button" class="btn btn-warning" id="cancleInspect">ȡ��</button>
                 
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
	
	
	//��ѯ
	$("#cancleInspect").click(function(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
	
	$("#sureInspect").click(function(){
		var repairMan = encodeURI($('#repairMan').val());
		var backContent = encodeURI($("#backContent").val());
		var issuesId = $("#issuesId").val();
		if(repairMan == null || repairMan == ""){
			layer.alert("�뽫��������д������");
			return false;
		}
			
		$.ajax({
	         url : '/wbem/inspect/addRepairFeedback.action',
	         data : {"repairMan":repairMan,"backContent":backContent,"issuesId":issuesId},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
	         type : 'post',
	         dataType : 'json',
	         success : function(flag) {          
	          	if(flag == 1){
	          		var index = parent.layer.getFrameIndex(window.name);
					parent.$('.data-table').DataTable().ajax.reload();
					parent.layer.close(index);
	          	}
	          	if(flag == 0){
	          		layer.alert("����ʧ�ܣ�");
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