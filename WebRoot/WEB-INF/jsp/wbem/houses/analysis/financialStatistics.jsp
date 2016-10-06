<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %> 
<div class="container-fluid" >
	<div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <%-- <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >ѡ����Ŀ</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="projGuid" >
	                     <c:forEach items="${projectList}" var="project" varStatus="status">
              				<option value="${project.id}">${project.name}</option>
              	  		</c:forEach>
	                </select>
	              </div>
	            </div> --%> 
	             <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >��ҵ����</label>
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
				    <button class="btn dropdown-toggle"  data-toggle="dropdown" id="dropdown_btn"> �ͻ����� <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu" id="dropdown-menu">
				      <li><a href="#">�ͻ�����</a></li>
				      <li><a href="#">�ͻ��绰</a></li>
				    </ul>
				  </div>
				  <input class="span4" id="telOrName" type="text">
				</div>
				<div class="row-fluid ">
	            <div class="input-prepend input-append span5" style="margin-top: 10px;">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">����</span>
				  <label class="checkbox inline"> <input type="checkbox" name="type" value="1"/>סլ</label>
	              <label class="checkbox inline"> <input type="checkbox" name="type" value="2"/>��λ</label>
				</div>
				<div class="control-group noborder-bottom noborder-top span5">  
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
	 <div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>ͳ���б�</h5>
     </div>
	 <div class="widget-content nopadding">
	   <table class="table table-bordered data-table">
		 <thead>
		 	<tr>
		 	 <th colspan='2'></th>
		 	 <th colspan='3'>�ͻ���Ϣ</th>
		 	 <th colspan='3'>������Ϣ</th>
		 	 <th colspan='13'>������Ϣ</th>
		 	 <th colspan='1'>����</th>
		 	</tr>
	        <tr>
			 <th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th>
			 <th>����</th>
			 <th>�ͻ�</th>
			 <th>֤������</th>
			 <th>�绰</th>
			 <th>����</th>
			 <th>���</th>
			 <th>�ɽ��ܼ�</th>
			 <th>������</th>
			 <th>�Ϲ�����</th>
			 <th>��������</th>
			 <th>�׸�/ȫ����</th>
			 <th>��������</th>
			 <th>ǩԼ����</th>
			 <th>��������</th>
			 <th>����֤������</th>
			 <th>�ſ���</th>
			 <th>�ſ�����</th>
			 <th>�������</th>
			 <th>������֤������</th>
			 <th>ҵ��Ա</th>
			 <th>����</th>
		   </tr>
		</thead>
	   </table>
	 </div>
	</div> 
  </div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %> 
<script type="text/javascript"> 
$(document).ready(function(){
	
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	$("#resetSearch").click(function(){
		//$("#projGuid").select2("val","");
		$("#userGuid").select2("val","");
		$("#telOrName").val("");
		$("input[type='checkbox']").prop("checked",false);
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"ajax": {
				type: "post",//��ָ̨���˷�ʽ��
                url: "/wbem/houses/analysis/loadStatisticsList.action",
                data:function(d){
                	//d.projGuid = $('#projGuid option:selected').val();
               		d.userGuid = $('#userGuid option:selected').val();
                	d.telOrName = encodeURI($('#telOrName').val());
                	d.type = checkbox_CheckedValue("type").toString();;
                }
         },
         "columns":[
         	{"data":"index"},
         	{"data":"type"},
         	{"data":"cstName"},
         	{"data":"cardID"},
         	{"data":"mobileTel"},
         	{"data":"roomInfo"},
         	{"data":"bldArea"},
         	{"data":"totalPrice"},
         	{"data":"depositAmount"},
         	{"data":"subscribeDate"},
         	{"data":"paymentType"},
         	{"data":"paymentAmount"},
         	{"data":"paymentDate"},
         	{"data":"signUpDate"},
         	{"data":"marginDate"},
         	{"data":"recordDate"},
         	{"data":"lendingAmount"},
         	{"data":"lendingDate"},
         	{"data":"rhDate"},
         	{"data":"unRecordDate"},
         	{"data":"userName"},
         	{"data":"operate"}
         ],
         columnDefs:[{
         	targets:21,"render":function(data, type, row, meta){
         			return "<a style='color:#FAA732' href='#' cusId="+data+"  oppGUID="+row.oppGUID+" userGuid="+row.userGuid+" onclick='cstDetail(\"" + data + "\",\"public\")'>�鿴</a> ";
         	}
         },
	     { "sortable": false, "targets":[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]}
         ] , 
         /* "ordering":true,
         "order": [6,"desc"], */	 
         
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
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %> 