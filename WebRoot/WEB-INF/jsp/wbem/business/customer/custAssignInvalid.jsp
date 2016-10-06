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
	      <li class="active"><a href="/wbem/business/customer/custAssignNav.action?menuId=${menuId}">�����еĿͻ�</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/business/customer/custAssignPublic.action?menuId=${menuId}">�����ͻ�</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/business/customer/custAssignDusBin.action?menuId=${menuId}">������</a></li>
	      <li class="divider-vertical"></li>
	    </ul>
	  </div>
	</div>
	<div class="widget-box">
		<div class="widget-title">
		  <ul class="nav nav-tabs">
		    <li class=""><a data-toggle="tab" href="#tab1" onclick="tabSwitch(this)" target="/wbem/business/customer/custAssignNav.action?menuId=${menuId}">�����еĿͻ�</a></li>
		    <li class="" ><a data-toggle="tab" href="#tab2" onclick="tabSwitch(this)" target="/wbem/business/customer/custAssignOverdue.action?menuId=${menuId}">���ڿͻ�</a></li>
		    <li class="active" ><a data-toggle="tab" href="#tab3" onclick="tabSwitch(this)" target="/wbem/business/customer/custAssignInvalid.action?menuId=${menuId}">��Ч�ͻ�</a></li>
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
				  <input class="span4" id="telOrName" type="text">&nbsp;&nbsp;&nbsp;
				  <button type="button" class="btn btn-success" style="margin-left: 5px;" id="tableSearch">��ѯ</button>
				  <!-- <button type="button" class="btn btn-primary" style="margin-left: 5px;" id="tableSearch">����</button> -->
				</div>
	          </form>
	        </div>
	    </div>
	</div>
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>��Ч�ͻ��б�</h5>
		<label class="checkbox inline index" id="checklabel" style="display: none;"><input type="checkbox" id="checkall" />ѡ��ȫ��</label>
			<label class="checkbox inline index" id="checklabel_after" style="display: none;">�Ѿ�ѡ��${dataNum }����¼</label>
			
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<button type="button" class="btn btn-success" onclick="cstMassAssign()">��������</button>
				<button type="button" class="btn btn-success" onclick="cstMassReback()">��������</button>
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
						<th>��Чԭ��</th>
						<th>���򼶱�</th>
						<th>�������ʱ��</th>
						<th>������ʽ</th>
						<th>����</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<input type="hidden" id="ids" value=""/>
<%-- ����ҳ��--%>

<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script>
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
$(document).ready(function(){

	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});


	$('.data-table').dataTable({
		"ajax": {type: "post",//��ָ̨���˷�ʽ��
                 url: "/wbem/business/customer/getZsbusiCustomInvalid.action",
                 data:function(d){			//�ⲿ��������
                 	//d.proName = encodeURI($('#proName').find('option:selected').text());$('#proName').find('option:selected').val()
                 	//d.proId=$("#proId").find('option:selected').val();
                 	d.telOrName = encodeURI($("#telOrName").val());	
                 }
         },
         "columns":[
         	{"data":"index"},
         	{"data":"cstName"},
         	{"data":"mobileTel"},
         	{"data":"userName"},
         	{"data":"status"},
         	{"data":"invalidReason"},
         	{"data":"gfyx"},
         	{"data":"lastDate"},
         	{"data":"gjfs"},
         	{"data":"operate"},
         ],
           columnDefs:[{
         	targets:9,"render":function(data, type, row, meta){
	     		return "<a style='color:#FAA732' href='#' cusId="+data+" onclick='cstDetail(\"" + data + "\",\""+row.userName+"\")'>�鿴</a>&nbsp;" +
	   				"<a style='color:#FAA732' href='#' onclick='cstAssign(\"" + data + "\")'>����</a>&nbsp;" +
	   				"<a style='color:#FAA732' href='#' onclick='cstReback(\"" + data + "\")'>����</a>&nbsp;" +
	   				"<a style='color:#FAA732' href='#' onclick='editCustInfo(\"" + data+ "\",\""+row.cstName + "\",\""+ row.mobileTel +"\",\""+ row.userName +"\")'>�༭</a>";
         	}
         },
	         	{  targets:5,
	         		"visible":false
	         	},
	        	{ "sortable": false, "targets":[0,1,2,3,4,5,8,9]}
        	] , 
         "ordering":true,
         "order": [6,"asc"],	
         "initComplete": function(settings, json) {
        	 var ids=json.ids;
       		 ids= ids.substring(1,ids.length-1);
         	 $("#ids").val((ids));
         	 $("#checklabel_after").text("�Ѿ�ѡ��"+json.recordsTotal+"����¼");
  		} 
         
	});
	
	//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
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

	function tabSwitch(tab){
		window.location.href = $(tab).attr("target");
	}
</script>
<%@include file="/WEB-INF/jsp/wbem/business/customer/custAssignCommon.jsp" %>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>