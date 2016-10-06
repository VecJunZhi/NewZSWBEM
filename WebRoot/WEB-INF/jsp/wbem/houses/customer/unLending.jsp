<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
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
	            </div>  --%>
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
				  <input class="span4" id="telOrName" type="text"><!-- <button class="btn btn-primary" style="margin-left: 5px;">����</button> -->
				</div>
				<div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >��������</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="bank" >
              			<option value=""></option>
              			<option value="�й�����">�й�����</option>
              			<option value="��������">��������</option>
              			<option value="��������">��������</option>
              			<option value="��������">��������</option>
              			<option value="ũҵ����">ũҵ����</option>
              			<option value="������">������</option>
	                </select>
	              </div>
	            </div>
				<div class="row-fluid ">
	            <div class="input-prepend input-append span5">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">�ſ�״̬</span>
				  <label class="radio inline" style="margin-left:20px;"> <input type="radio" name="lendingStatus" checked value="1"/>δ�ſ�</label>
	              <label class="radio inline" style="margin-left:20px;"> <input type="radio" name="lendingStatus" value="2"/>�ѷſ�</label>
	              <label class="radio inline" style="margin-left:20px;"> <input type="radio" name="lendingStatus" value="3"/>ȫ��</label>
				</div>
				</div>
				<div class="row-fluid ">
	            <div class="input-prepend input-append span5" style="margin-top: 10px;">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">�߰�״̬</span>
				  <label class="checkbox inline"> <input type="checkbox" name="urgedType" value="1"/>����</label>
	              <label class="checkbox inline"> <input type="checkbox" name="urgedType" value="2"/>��ͨ</label>
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>δ�ſ�ͻ��б�</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
         </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th>
						<th>����</th>
						<th>�������</th>
						<th>�ͻ�����</th>
						<th>��ϵ�绰</th>
						<th>��ҵ����</th>
						<th>�Ϲ�����</th>
						<!-- <th>��������</th>-->
						<th>������</th> 
						<th>��������</th>
						<th>Ƿ����</th>
						<th>���ѿͻ�</th>
						<th>��������</th>
						<th>�����������</th> 
						<th>����</th>
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
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$("#resetSearch").click(function(){
		$("#userGuid").select2("val","");
		$("#telOrName").val("");
		$("input[type='checkbox']").attr("checked",false);
		$("input[type='radio']:first").attr("checked",true);//��ѡĬ��ѡ�е�һ��
		$("#bank").select2("val","");
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
         	url: "/wbem/houses/customer/loadUnLendingCstList.action",
		 	data:function(d){			//�ⲿ��������
		 		//d.projGuid=$('#projGuid').find('option:selected').val();
	         	d.userGuid = $('#userGuid').find('option:selected').val();
	         	d.telOrName = encodeURI($("#telOrName").val());
	         	/* d.minQsDate = $("#dpd1").val();
	    		d.maxQsDate = $("#dpd2").val();
	    		d.minOverDueDays = $("#minOverDueDays").val();
	    		d.maxOverDueDays = $("#maxOverDueDays").val(); */
	    		d.urgedType = checkbox_CheckedValue("urgedType").toString();
	    		d.lendingStatus = $("input[type:radio]:checked").val();
	    		d.bank = encodeURI($("#bank option:selected").val());
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"roomInfo"},
         	{"data":"bldArea"},
         	{"data":"cstName"},
         	{"data":"mobileTel"},
         	{"data":"userName"},
         	{"data":"qsDate"},
         	/* {"data":"endDate"},*/
         	{"data":"postponeDate"}, 
         	{"data":"overDays"},
         	{"data":"qkTotal"},
         	{"data":"isDifficult"},
         	{"data":"bank"},
         	{"data":"urgedContent"},
         	{"data":"operate"},
         ],
         "columnDefs":[
            {
         		"targets":13,
         		"render":function(data){
	       			return	"<shiro:hasPermission name='button:urgedManage:unLending:cstUrged'><a style='color:#FAA732' href='#' onclick='cstUrged(\"" + data + "\",\"3\")'>�߰�</a></shiro:hasPermission>&nbsp;" + 
	       				    "<shiro:hasPermission name='button:urgedManage:unLending:cstDelay'><a style='color:#FAA732' href='#' onclick='cstDelay(\"" + data + "\",\"3\")'>����</a></shiro:hasPermission>&nbsp;"+
	       				 "<a style='color:#FAA732' href='#' onclick='cstDetail(\"" + data + "\")'>�鿴</a>&nbsp;";		
         		}
         	}, 
	        { "sortable": false, "targets":[0,1,3,4,5,10,11,13]}
          ] , 
         "ordering":true,
         "order": [8,"desc"],//Ĭ������	  
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
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>