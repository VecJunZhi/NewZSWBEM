<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
  <form class="form-inline form-horizontal"> 
	<div class="row-fluid">
		<%-- <div class="control-group noborder-top span4">
          <label class="control-label">ѡ����Ŀ</label>
          <div class="controls">
            <select id="proId" >
              <c:forEach items="${projectList}" var="project" varStatus="status">
              	<option value="${project.id}">${project.name}</option>
              </c:forEach>
            </select>
          </div>
		</div> --%>
        <div class="control-group noborder-top span4 noborder-bottom">
          <label class="control-label">��ҵ����</label>
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
          <label class="control-label">�ͻ�����</label>
          <div class="controls">
            <input class="span11" type="text" id="cstName"  />
          </div>
        </div>
	</div>
	<div class="row-fluid">
       <div class="control-group  noborder-top span4 noborder-bottom">
         <label class="control-label">�ͻ��绰</label>
         <div class="controls">
           <input class="span11" type="text" id="cstTel" />
         </div>
       </div>
       <div class="control-group noborder-top span4 noborder-bottom">
		  <label class="control-label">��������</label>
		  <div class="controls">
		    <input type="text" data-date="${startDate}" data-date-format="yyyy-mm-dd"  class="datepicker span5" readonly id="dpd1">--
		    <input type="text" data-date="${endDate}" data-date-format="yyyy-mm-dd"  class="datepicker span5" readonly  id="dpd2">
		  </div>
	  </div>
       <div class="span4" style="padding-top:10px;" >
	       <span style="margin-left: 25px;">
	           <button type="button" class="btn btn-success" id="tableSearch">��ѯ</button>
	           <button type="button" class=" btn " id="resetSearch">����ɸѡ</button>
	       </span>
       </div>
	</div>
  </form>
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>���ڿͻ��б�</h5>
					<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
					<!--  
						<button type="button" class="btn btn-success" id="tableSearch" disabled>����</button>
           				<button type="reset" class=" btn btn-warning" id="resetSearch" disabled>����</button>
           			-->
					</span>
           		</div>
				<div class="widget-content nopadding">
					<table class="table table-bordered data-table">
						<thead>
			                <tr>
								<th>���</th>
								<th>����</th>
								<th>�绰</th>
								<th>����ʱ��</th>
								<th>������ʽ</th>
								<th>��ҵ����</th>
								<th>��������</th>
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
//��ѯ
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
				type: "post",//��ָ̨���˷�ʽ��
                 url: "/wbem/business/customer/getZsCustInfo.action",
                 data:function(d){			//�ⲿ��������
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