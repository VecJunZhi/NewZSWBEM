<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid">
	 <%-- <div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >选择项目</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="projGuid" >
	                     <c:forEach items="${projectList}" var="project" varStatus="status">
              				<option value="${project.id}">${project.name}</option>
              	  		</c:forEach>
	                </select>
	              </div>
	            </div> 
	             <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >置业顾问</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="userGuid" >
	                	<option selected="selected"></option>
	                   <c:forEach items="${zygwList}" var="zygw" varStatus="status">
              			<option value="${zygw.value}">${zygw.userName}</option>
             		   </c:forEach>
	                </select>
	              </div>
	            </div> 
			  	<div class="input-prepend" >
				  <div class="btn-group">
				    <button class="btn dropdown-toggle"  data-toggle="dropdown" id="dropdown_btn"> 客户姓名 <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu" id="dropdown-menu">
				      <li><a href="#">客户姓名</a></li>
				      <li><a href="#">客户电话</a></li>
				    </ul>
				  </div>
				  <input class="span4" id="telOrName" type="text"><!-- <button class="btn btn-primary" style="margin-left: 5px;">搜索</button> -->
				</div>
				<div class="row-fluid ">
	            <div class="input-prepend input-append span5" style="margin-top: 10px;">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">催办状态</span>
				  <label class="checkbox inline"> <input type="checkbox" name="urgedType" value="1"/>疑难</label>
	              <label class="checkbox inline"> <input type="checkbox" name="urgedType" value="2"/>普通</label>
	             
				</div>
				<div class="control-group noborder-bottom noborder-top span5">  
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">查询</button>
	                  <button type="button" class="btn btn-success" id="resetSearch">撤销筛选</button>
	                </div>
	            </div>
				</div>
	          </form>
	        </div>
	    </div>
	</div>  --%>
	<div class="widget-box">
		<div class="widget-title">
			<span class="icon"><i class="icon-th"></i></span><h5>催办记录列表</h5>
			<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;float: left;">
				<input type="radio" name="urgeRecord" id="urgeRecord0" style="margin-top: 0px;" checked="checked"><span style="color: #2F96B4">所有记录</span>
				<input type="radio" name="urgeRecord" id="urgeRecord1" style="margin-top: 0px;"><span style="color: #2F96B4">未交款记录</span>
				<input type="radio" name="urgeRecord" id="urgeRecord2" style="margin-top: 0px;"><span style="color: #2F96B4">未签约记录</span>
				<input type="radio" name="urgeRecord" id="urgeRecord3" style="margin-top: 0px;"><span style="color: #2F96B4">未放款记录</span>
			</span>
			<label class="checkbox inline index" id="checklabel"> </label>
			<input type="hidden" id="tradeGuid" value="${tradeGuid}" >
         </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th>
						<th>催办日期</th>
						<th>催办内容</th>
						<th>下次催办日期</th>
						<th>催办人</th>
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
	var urgedStage='';
	var table=$('.data-table').DataTable({
		"ajax": {
			type: "post",//后台指定了方式。
         	url: "/wbem/houses/customer/loadCstUrgedList.action",
		 	data:function(d){			//外部参数传递
		 		d.tradeGuid = $("#tradeGuid").val();
		 		d.urgedStage=urgedStage;
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"urgedDate"},
         	{"data":"urgedContent"},
         	{"data":"nextUrgedDate"},
         	{"data":"userName"},

         ],
          "columnDefs":[
	        { "sortable": false, "targets":[0,2,4]}
          ] , 
         "ordering":true,
         "order": [1,"desc"],	  
	});
	$('select').select2();
	//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	$(':radio[name=urgeRecord]').on( 'click', function(){
   	 	if($("#urgeRecord1").prop('checked')){
   	 		urgedStage='1';
   	 	}else if($("#urgeRecord2").prop('checked')){
   	 		urgedStage='2';
   	 	}else if($("#urgeRecord3").prop('checked')){
   	 		urgedStage='3';
   	 	}else if($("#urgeRecord0").prop('checked')){
   	 		urgedStage='';
   	 	}
   	 	table.draw();
	} ); 
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