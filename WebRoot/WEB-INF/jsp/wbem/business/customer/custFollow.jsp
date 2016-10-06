<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
  <form class="form-inline form-horizontal"> 
	<div class="row-fluid">
		<%-- <div class="control-group noborder-top span4">
          <label class="control-label">选择项目</label>
          <div class="controls">
            <select id="proId" >
              <c:forEach items="${projectList}" var="project" varStatus="status">
              	<option value="${project.id}">${project.name}</option>
              </c:forEach>
            </select>
          </div>
		</div> --%>
        <div class="control-group noborder-top span4 noborder-bottom">
          <label class="control-label">置业顾问</label>
          <div class="controls">
            <select id="userId" >
              <option selected="selected"></option>
              <c:forEach items="${zygwList}" var="zygw" varStatus="status">
              	<option value="${zygw.userId}">${zygw.userName}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="control-group  noborder-top span4 noborder-bottom">
          <label class="control-label">客户姓名</label>
          <div class="controls">
            <input class="span11" type="text" id="cstName"  />
          </div>
        </div>
	</div>
	<div class="row-fluid">
       <div class="control-group  noborder-top span4 noborder-bottom">
         <label class="control-label">客户电话</label>
         <div class="controls">
           <input class="span11" type="text" id="cstTel" />
         </div>
       </div>
       <div class="control-group noborder-top span4 noborder-bottom">
		  <label class="control-label">跟进日期</label>
		  <div class="controls">
		    <input type="text" data-date="${startDate}" data-date-format="yyyy-mm-dd"  class="datepicker span5" readonly id="dpd1">--
		    <input type="text" data-date="${endDate}" data-date-format="yyyy-mm-dd"  class="datepicker span5" readonly  id="dpd2">
		  </div>
	  </div>
       <div class="span4" style="padding-top:10px;" >
	       <span style="margin-left: 25px;">
	           <button type="button" class="btn btn-success" id="tableSearch">查询</button>
	           <button type="button" class=" btn " id="resetSearch">撤销筛选</button>
	       </span>
       </div>
	</div>
  </form>
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>逾期客户列表</h5>
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
								<th>姓名</th>
								<th>电话</th>
								<th>跟进时间</th>
								<th>跟进方式</th>
								<th>置业顾问</th>
								<th>跟进内容</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
//查询
//modify by jixiaohang
	var dateNow = new Date();
	var strNow = dateNow.getFullYear()+"-"+(dateNow.getMonth()+1)+"-"+dateNow.getDate();
	var dateOld = new Date(dateNow.getTime()-24*60*60*1000*7);
	var strOld = dateOld.getFullYear()+"-"+(dateOld.getMonth()+1)+"-"+dateOld.getDate();
	$("#dpd1").attr("value",strOld);
	$("#dpd2").attr("value",strNow);
//modify by jixiaohang
$("#tableSearch").click(function(){
	$('.data-table').DataTable().ajax.reload();
});

$("#resetSearch").click(function(){
	$('#userId').select2("val","");
	$("#cstName").val("");
	$("#cstTel").val("");
	$("#dpd1").val("");
	$("#dpd2").val("");
	setTimeout($('.data-table').DataTable().ajax.reload(),100);
});

$(document).ready(function(){
	
	$('.data-table').dataTable({
		"ajax": {
				type: "post",//后台指定了方式。
                 url: "/wbem/business/customer/getZsCustInfo.action",
                 data:function(d){			//外部参数传递
                 	//d.proId=$('#proId').find('option:selected').val();
                 	d.userId = $('#userId').find('option:selected').val();
                 	d.cstName = encodeURI($('#cstName').val());
                 	d.cstTel = $('#cstTel').val();
                 	d.startDate = $("#dpd1").val();
	           		d.endDate = $("#dpd2").val();
                 }
         },
         "columns":[
         	{"data":"index"},
         	{"data":"name"},
         	{"data":"tel"},
         	{"data":"followDate"},
         	{"data":"followWay"},
         	{"data":"userName"},
         	{"data":"followInfo"} 
         ]
	});
	
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$('select').select2();
	
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
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>