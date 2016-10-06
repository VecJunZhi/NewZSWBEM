<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="navbar">
	  <div class="navbar-inner" style="margin-top:5px;">
	    <ul class="nav">
	      <%-- <li>
		      <form class="navbar-form pull-left" >
	            <select id="proId" class="span2">
	               <c:forEach items="${projectList}" var="project" varStatus="status">
              		<option value="${project.id}">${project.name}</option>
              	  </c:forEach>
	            </select>
			  </form>
	      </li>
	      <li class="divider-vertical"></li> --%>
	      <li class="active"><a href="/wbem/houses/customer/custAssignNav.action?menuId=${menuId}">�����еĿͻ�</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/houses/customer/custAssignPublic.action?menuId=${menuId}">�����ͻ�</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/houses/customer/custAssignDusBin.action?menuId=${menuId}">������</a></li>
	      <li class="divider-vertical"></li>
	    </ul>
	  </div>
	</div>
	<div class="widget-box">
		<div class="widget-title">
		  <ul class="nav nav-tabs">
		    <li class=""><a data-toggle="tab" href="#tab1" onclick="tabSwitch(this)" target="/wbem/houses/customer/custAssignNav.action?menuId=${menuId}">�����еĿͻ�</a></li>
		    <li class="active" ><a data-toggle="tab" href="#tab2" onclick="tabSwitch(this)" target="/wbem/houses/customer/custAssignOverdue.action?menuId=${menuId}">���ڿͻ�</a></li>
		    <li class="" ><a data-toggle="tab" href="#tab3" onclick="tabSwitch(this)" target="/wbem/houses/customer/custAssignInvalid.action?menuId=${menuId}">��Ч�ͻ�</a></li>
		  </ul>
		</div>
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active">
			  <form action="#" method="get" class="form-horizontal">
			  	<div class="input-prepend" style="margin-top: 10px;">
				  <div class="btn-group">
				    <button class="btn dropdown-toggle"  data-toggle="dropdown" id="dropdown_btn"> �ͻ��绰 <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu" id="dropdown-menu">
				      <li><a href="#">�ͻ�����</a></li>
				      <li><a href="#">�ͻ��绰</a></li>
				    </ul>
				  </div>
				  <input class="span4" id="telOrName" type="text"><!-- <button class="btn btn-primary" style="margin-left: 5px;">����</button> -->
				</div>
				<div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >��ҵ����</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="zygw" >
	               	 <option selected="selected"></option>
	                  <c:forEach items="${zygwList}" var="zygw" varStatus="status">
              			<option value="${zygw.value}">${zygw.userName}</option>
             		  </c:forEach>
                      
	                </select>
	              </div>
	            </div> 
	            <div class="row-fluid">
	            <div class="input-prepend input-append">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">��������</span>
				   <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="0"/>1-10</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="1"/>11-20</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="2"/>20����</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="3"/>�Զ���</label>
	                <input class="text inline" type="text" id="startDays" style="display:none">
	                <input class="text inline" type="text" id="endDays" style="display:none">
				</div>
				</div> 
	           <div class="row-fluid">
	            <div class="input-prepend input-append" style="margin-top: 10px;">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">�ͻ�״̬</span>
				  <label class="checkbox inline"> <input type="checkbox" name="status" value="��Ч"/>��Ч</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="��ѯ"/>��ѯ</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="����"/>����</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="�Ϲ�"/>�Ϲ�</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="ǩԼ"/>ǩԼ</label>
				</div>
				</div> 
	            <div class="row-fluid">
	            <div class="input-prepend input-append" style="margin-top: 10px;">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">���򼶱�</span>
	              <label class="checkbox inline"	> <input type="checkbox" name="gfyx" value="����"/>����</label>
	              <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="������"/>������</label>
	              <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="һ������"/>һ������</label>
	              <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="������"/>������</label>
	            </div>
	            </div>

	            <div class="input-prepend input-append" style="margin-top: 10px;">
	            	<span class="add-on" style="width: 103px;margin-right: 5px;">�������</span>
	            	<input type="text" data-date="${startDate}" data-date-format="yyyy-mm-dd" value="${startDate}" class="datepicker span2" id="dpd1" readonly>	   
		    		<!-- <i class="icon-resize-horizontal" >11111</i> -->
		    		<!-- <label style="width:10px">����</label> -->
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" data-date="${endDate}" data-date-format="yyyy-mm-dd" value="${endDate}" class="datepicker span2" id="dpd2" readonly>
	            </div>
	            <div class="row-fluid">
	            <div class="control-group noborder-bottom noborder-top span4"> 
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">��ѯ</button>
	                  <button type="reset" class="btn btn-success" id="resetSearch">����ɸѡ</button>
	                </div>
	             </div> 
	            </div> 
	          </form>
	        </div>
	    </div>
	</div>
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>���ڿͻ��б�</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<button type="button" class="btn btn-success"  onclick="cstMassAssign()">��������</button>
				<button type="button" class="btn btn-success" onclick="cstMassReback()">��������</button>
         		<!-- <button type="reset" class=" btn btn-warning" id="" disabled>���·���</button> -->
         		<div class="btn-group">
					<button type="button" class=" btn btn-warning dropdown-toggle btn-lg" data-toggle="dropdown">���·���</button>
					<ul class="dropdown-menu" role="menu">
					      <li><a href="#" onclick="exportExcel()">�����ͻ�����</a></li>
					      <li class="divider"></li>
					      <li><a href="#" onclick="importExcelResult()">���������</a></li>
				    </ul>
         		</div>
			</div>
         		</div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th>
						<th>����</th>
						<th>�绰</th>
						<th>��ҵ����</th>
						<th>�ͻ�״̬</th>
						<th>���򼶱�</th>
						<th>�������ʱ��</th>
						<th>������ʽ</th>
						<th>��������</th>
						<th>����</th>
						<th>�ͻ�id</th>
						<th>oppguid</th>
						<th>UserGuid</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/wbem/houses/customer/custAssignCommon.jsp" %>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
$(document).ready(function(){
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});

	$("#resetSearch").click(function(){
		$("#zygw").select2("val","");
		$("#telOrName").val("");
		$("input[type='checkbox']").attr("checked",false);
		$("input[type='radio']").attr("checked",false);
		$("#startDays").val("");
		$("#endDays").val("");
		$("#dpd1").val("");
		$("#dpd2").val("");
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});

	
	$('.data-table').dataTable({
		"ajax": {type: "post",//��ָ̨���˷�ʽ��
                 url: "/wbem/houses/customer/getZsbusiCustomOverDue.action",
                 data:function(d){			//�ⲿ��������
                 	//d.proId=$('#proId').find('option:selected').val();
                 	d.userId = $('#zygw').find('option:selected').val();
                 	d.telOrName = encodeURI($("#telOrName").val());
                 	d.overDays = checkbox_CheckedValue("overDays").toString();
                 	d.startDays = $("#startDays").val();
                 	d.endDays = $("#endDays").val();
                 	d.status = encodeURI(checkbox_CheckedValue("status").toString());
                 	d.gfyx = encodeURI(checkbox_CheckedValue("gfyx").toString());
                 	d.startDate = $("#dpd1").val();
	           		d.endDate = $("#dpd2").val();
                 }
         },
         "columns":[
         	{"data":"index"},
         	{"data":"cstName"},
         	{"data":"mobileTel"},
         	{"data":"userName"},
         	{"data":"status"},
         	{"data":"gfyx"},
         	{"data":"lastDate"},
         	{"data":"gjfs"},
         	{"data":"overDays"},
         	{"data":"operate"},
         	{"data":"id"},
         	{"data":"oppGUID"},	
         	{"data":"userGuid"},
         ],
          columnDefs:[{
         	targets:9,"render":function(data, type, row, meta){

         		return "<a style='color:#FAA732' href='#' cusId="+data+" oppGUID="+row.oppGUID+" userGuid="+row.userGuid+" onclick='cstDetail(\"" + data + "\",\"cusOverDue\")'>�鿴</a>&nbsp;" +
       				"<a style='color:#FAA732' href='#' onclick='cstAssign(\"" + data + "\",\""+row.oppGUID + "\",\""+row.userGuid + "\")'>����</a>&nbsp;" +
       				"<a style='color:#FAA732' href='#' onclick='cstReback(\"" + data + "\",this,\""+row.oppGUID + "\")'>����</a>&nbsp;" +
       				"<a style='color:#FAA732' href='#' onclick='editCustInfo(\"" + row.id+ "\",\""+row.cstName + "\",\""+ row.mobileTel +"\",\""+ row.userName +"\")'>�༭</a>";
         	}
         },
         {
              "targets": [10,11,12],
               "visible": false,
          },/* {  targets:2,
	         		"orderable":true,
	         		"orderSequence": [ "desc", "asc", "asc" ], 
	         		"ordering": true,
	         		"searchable":true
	         	}, */
	        	{ "sortable": false, "targets":[0,1,2,3,4,7,9,10,11]}
        	] , 
         "ordering":true,
         "order": [6,"desc"],	  
         
	});
	
	/* $('input[type=checkbox],input[type=radio],input[type=file]').uniform(); */
	
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
	
	$("input[name='overDays']").click(function(){
		if($(this).val() == 3) {
			$("#startDays").show();
			$("#endDays").show();
		}else {
			$("#startDays").hide();
			$("#endDays").hide();
		}
	});	
});

</script>
<script>
	function tabSwitch(tab){
		window.location.href = $(tab).attr("target");
	}
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>