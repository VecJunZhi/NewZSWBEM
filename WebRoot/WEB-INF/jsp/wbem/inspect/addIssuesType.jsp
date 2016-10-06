<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid" style="margin-top:15px">
       <div id="tab1" class="tab-pane active"> 
	  <form action="#" method="get" class="form-horizontal">
           <div class="control-group noborder-bottom">
      			<label class="control-label">主类型:</label>
	       <div class="controls">
	         <select id="mainType" style="width:220px">
	         	<option value="0"></option>
	         	<c:forEach items="${typeList}" var="type" varStatus="status">
	         		<option value="${type.issueCode}">${type.issueName}</option>
	         	</c:forEach>
	         </select>
	       </div>
	    </div>
		<div class="control-group noborder-bottom"> 
      			<label class="control-label">类型名称 :</label>
	       <div class="controls">
	         <input type="text" id="issueName">
	         <span style="color:red;margin-left:10px;">*</span>
	       </div>
	       
	    </div>
           <div class="row-fluid " style="margin-top:80px;">
		<div class="control-group noborder-bottom noborder-top span5">  
           	<div class="controls">
                 <button type="button" class="btn btn-success" id="sureAdd">确定</button>
                 <button type="button" class="btn btn-warning" id="cancleAdd">取消</button>
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
	$("#cancleAdd").click(function(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
	
	$("#sureAdd").click(function(){
		var issueName = encodeURI($("#issueName").val());
		var mainType = $("#mainType option:selected").val();
		
		if(issueName == null || issueName == "") {
			layer.alert("请填写类型名称！");
			return false;
		}
		$.ajax({
	         url : '/wbem/inspect/insertIssuesType.action',
	         data : {"issueName":issueName,"mainType":mainType},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	         type : 'post',
	         dataType : 'json',
	         success : function(flag) {          
	          	if(flag == 1){
	          		var index = parent.layer.getFrameIndex(window.name);
					if(mainType == 0) {
						$.ajax({
							url: "/wbem/inspect/loadIssuesTypeList.action",
							data: {"parentCode":mainType},
							type: 'post',
							dataType: 'json',
							success : function(data) {
								var str = "";
								for(var i=0; i<data.length; i++) {
									str += "<option value="+data[i].issueCode+">"+data[i].issueName+"</option>";
								}
								parent.$('#mainType').empty();
								parent.$('#mainType').append(str);
								parent.$("#mainType").select2("val",data[0].issueName);
								parent.$('.data-table').DataTable().ajax.reload();
								parent.layer.close(index);
							}
						});
					}else {
						parent.$('.data-table').DataTable().ajax.reload();
						parent.layer.close(index);
					}
	          		
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