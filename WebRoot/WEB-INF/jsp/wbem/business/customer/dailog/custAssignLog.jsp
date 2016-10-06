<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="navbar" style="margin-top: 10px;">
  <div class="navbar-inner">
    <ul class="nav">
      <li><a href="/wbem/business/customer/dailog/custDetail.action?cusid=${custLogID}&cstType=${custType}">客户信息</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custChangeLog.action">变更记录</a></li>
      <li class="divider-vertical"></li>
      <li class="active"><a href="/wbem/business/customer/dailog/custAssignLog.action">分配日志</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custRecycleLog.action">回收日志</a></li>
    </ul>
  </div>
</div>
<div class="widget-box">
	<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>分配日志</h5>
		<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
		<!-- 			
			<button type="button" class="btn btn-success" id="tableSearch" disabled>导出</button>
			<button type="reset" class=" btn btn-warning" id="resetSearch" disabled>导入</button> 
		-->
		</span>
    </div>
	<div class="widget-content nopadding">
		<table class="table table-bordered data-table">
			<thead>
				 <tr>
	                <th>序号</th>
	                <th>操作日期</th>
	                <th>操作人</th>
	          		<th>内容</th>
	            </tr>
			</thead>
		</table>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script>
$(document).ready(function(){
	$('.data-table').dataTable({
			"pageLength": 50,
			"ajax": {type: "post",//后台指定了方式。
                 url: "/wbem/business/customer/getCusRecordLogByCusId.action",
                 data:function(d){			//外部参数传递
                 	d.cusid="${custLogID}";
                 	d.logType="2";
                 	//d.proId="${proId}";
                 
                 	/* d.cstStatus=encodeURI(checkbox_CheckedValue("cstStatus").toString());//checkbox_CheckedValue("cstStatus").toString();//$('#cstStatus').find('checkbox:checked').val();
                 	d.gfyx=encodeURI(checkbox_CheckedValue("gfyx").toString());
                 	d.rebackReason=encodeURI(checkbox_CheckedValue("rebackReason").toString());
                	d.userGuid = $('#zygw').find('option:selected').val();
                 	d.telOrName =encodeURI($('#prependedDropdownButton').val()); */
                 }
	         },
	         "columns":[
	         	{"data":"index"},
	         	{"data":"operDate"},
	         	{"data":"operator"},
	         	{"data":"description"}
	         	]
	      });
	      $('select').select2();
});
		
		
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>