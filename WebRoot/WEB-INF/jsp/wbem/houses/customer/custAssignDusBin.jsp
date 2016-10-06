<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="navbar">
	  <div class="navbar-inner" style="margin-top:5px;">
	    <ul class="nav">
	      <%-- <li>
	        <form class="navbar-form pull-left">
              <select id="proId" class="span2">
                <c:forEach items="${projectList}" var="project" varStatus="status">
					<option value="${project.id}">${project.name}</option>
              	</c:forEach>
              </select>
            </form>
	      </li>
	      <li class="divider-vertical"></li> --%>
	      <li><a href="/wbem/houses/customer/custAssignNav.action?menuId=${menuId}">�����еĿͻ�</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/houses/customer/custAssignPublic.action?menuId=${menuId}">�����ͻ�</a></li>
	      <li class="divider-vertical"></li>
	      <li class="active"><a href="/wbem/houses/customer/custAssignDusBin.action?menuId=${menuId}">������</a></li>
	      <li class="divider-vertical"></li>
	    </ul>
	  </div>
	</div>
	<div class="widget-box">
		<div class="widget-content tab-content">
			<div id="tab1" class="tab-pane"> </div>
	        <div id="tab2" class="tab-pane active">
			  <form action="#" method="get" class="form-horizontal">
			  	<div class="input-prepend" style="margin-top: 10px;">
				  <div class="btn-group">
				    <button class="btn dropdown-toggle"  data-toggle="dropdown" id="dropdown_btn"> �ͻ��绰 <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu" id="dropdown-menu">
				      <li><a href="#">�ͻ�����</a></li>
				      <li><a href="#">�ͻ��绰</a></li>
				    </ul>
				  </div>
				  <input class="span4" id="prependedDropdownButton" type="text"><!-- <button class="btn btn-primary" style="margin-left: 5px;">����</button> -->
				</div>
				<div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >ԭ��ҵ����</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="zygw" >
	                	<option value="" selected></option>
	                  	<c:forEach items="${zygwList}" var="zygw" varStatus="status">
              				<option value="${zygw.value}">${zygw.userName}</option>
             			</c:forEach>
	                </select>
	              </div>
	            </div> 
	           <div class="row-fluid ">
	             <div class="input-prepend input-append" id="cstStatus">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">�ͻ�״̬</span>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="��Ч" />��Ч</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="��ѯ"/>��ѯ</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="����"/>����</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="�Ϲ�"/>�Ϲ�</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="ǩԼ"/>ǩԼ</label>
	              </div>
	            </div>
	            <div class="row-fluid ">
	             <div class="input-prepend input-append" style="margin-top: 10px;" id="intentionLevel">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">���򼶱�</span>
	                <label class="checkbox inline"	> <input type="checkbox" name="gfyx" value="����"/>����</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="������"/>������</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="һ������"/>һ������</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="������"/>������</label>
	              </div>
	             </div>
	           <!-- <div class="row-fluid ">
	             <div class="input-prepend input-append" style="margin-top: 10px;"  id="rebackReason">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">����ԭ��</span>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="1"/>���ڿͻ��Զ�����</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="2"/>��Ч�ͻ��Զ�����</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="3"/>����δ�Ϲ��ͻ��Զ�����</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="4"/>��ְ���ۿͻ��Զ�����</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="5"/>����</label>
	              </div>
	            </div> -->
	            <div class="row-fluid ">
	            <div class="control-group noborder-bottom noborder-top span4"> 
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">��ѯ</button>
	                  <button type="button" class="btn btn-success" id="resetSearch">����ɸѡ</button>
	                </div>
	             </div> 
	            </div>  
	          </form>
	        </div>
	    </div>
	</div>
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>�����ͻ��б�</h5>
			<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<button type="button" class="btn btn-success" id="tableSearch" onclick="cstMassMoveToPublic()">�������������ͻ�</button>
				<!-- <button type="button" class="btn btn-success" id="tableSearch">ȫ������</button>
				<button type="button" class="btn btn-success" id="tableSearch" disabled>���·���</button>
         		<button type="reset" class=" btn btn-warning" id="resetSearch" disabled>����������</button> -->
			</span>
         		</div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th>
						<th>����</th>
						<th>�绰</th>
						<th>ԭ��ҵ����</th>
						<th>�ͻ�״̬</th>
						<th>���򼶱�</th>
						<th>�������ʱ��</th>
						<th>������ʽ</th>
						<th>����ԭ��</th>
						<th>����ʱ��</th>
						<th>���մ���</th>
						<th>����</th>
						<th>oppguid</th>
						<th>userguid</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/wbem/houses/customer/custAssignCommon.jsp" %>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script>
	$(document).ready(function(){
		$('.data-table').dataTable({
			"ajax": {type: "post",//��ָ̨���˷�ʽ��
                 url: "/wbem/houses/customer/getCustomDusBin.action",
                 data:function(d){			//�ⲿ��������
                 	d.cstStatus=encodeURI(checkbox_CheckedValue("cstStatus").toString());//checkbox_CheckedValue("cstStatus").toString();//$('#cstStatus').find('checkbox:checked').val();
                 	d.gfyx=encodeURI(checkbox_CheckedValue("gfyx").toString());
                 	d.rebackReason=encodeURI(checkbox_CheckedValue("rebackReason").toString());
                	d.userGuid = $('#zygw').find('option:selected').val();
                 	d.telOrName =encodeURI($('#prependedDropdownButton').val());
                 	//d.proId=$('#proId').find('option:selected').val();
                 }
	         },
	         "columns":[
	         	{"data":"index"},
	         	{"data":"cstName"},
	         	{"data":"mobileTel"},
	         	{"data":"oldZygw"},
	         	{"data":"status"},
	         	{"data":"gfyx"},
	         	{"data":"lastDate"},
	         	{"data":"gjfs"},
	         	{"data":"rebackReason"},
	         	{"data":"rebackDate"},
	         	{"data":"rebackNums"},
	         	{"data":"id"},
	         	{"data":"oppGUID"},
	         	{"data":"userGuid"}
	         ],
	         columnDefs:[{
	         	targets:11,"render":function(data, type, row, meta){
	         			return "<a style='color:#FAA732' cusId="+data+" oppGUID="+row.oppGUID+ " userGuid="+row.userGuid+" href='#' onclick='cstDetail(\"" + data + "\",\"dusbin\")'>�鿴</a>&nbsp;&nbsp;"
	         				  +"<a style='color:#FAA732' href='#' onclick='moveToPublic(\"" + data + "\",\""+row.oppGUID + "\",\""+row.userGuid + "\")'>תΪ�����ͻ�</a>";
	         	}
	         },{targets:[8,9,10,12,13],
	         		"visible": false,
	         
	         },/* {  targets:2,
	         		"orderable":true,
	         		"orderSequence": [ "desc", "asc", "asc" ], 
	         		"ordering": true,
	         		"searchable":true
	         	}, */
	        	{ "sortable": false, "targets":[0,1,2,3,4,7,8,9,10,11,12]}
        	] , 
         "ordering":true,
         "order": [6,"desc"],	 
	         
		});
		
		//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
		
		$('select').select2( );
		
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
		
		//��ѯ
		$("#tableSearch").click(function(){
			$('.data-table').DataTable().draw();
		});
		$("#resetSearch").click(function(){
			$("#zygw").select2("val","");
			$("#prependedDropdownButton").val("");
			$("#dpd1").val("");
			$("#dpd2").val("");
			$("input[type='checkbox']").attr("checked",false);
			$("input[type='radio']").attr("checked",false);
			setTimeout($('.data-table').DataTable().ajax.reload(),100);
		});
	});
</script>


<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>