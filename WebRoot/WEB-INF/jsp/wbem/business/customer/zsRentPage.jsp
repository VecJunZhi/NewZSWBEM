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
	                     	<c:if test="${project.name == '���ͼ�԰' }">
              					<option value="${project.id}" selected>${project.name}</option><!-- Ĭ�����ͼ�԰��ѡ�� -->
              				</c:if>
              				<c:if test="${project.name != '���ͼ�԰' }">
              					<option value="${project.id}">${project.name}</option>
              				</c:if>
              	  		</c:forEach>
	                </select>
	              </div>
	            </div>  --%>
	            <div class="row-fluid ">
	              <div class="input-prepend input-append" >
	              <span class="add-on" style="width: 103px;margin-right: 5px;">����״̬</span>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="saleStatus"  style="margin-left: 0;" value="����"/>����</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="saleStatus"  style="margin-left: 0;" value="δ��"/>δ��</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="saleStatus"  style="margin-left: 0;" value="������"/>������</label>
	            </div>
	            </div>
				<div class="row-fluid ">
	              <div class="input-prepend input-append span5" style="margin-top: 10px;">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">����״̬</span>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="����" style="margin-left: 0;" />����</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="����" style="margin-left: 0;" />����</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="������" style="margin-left: 0;" />������</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="��Ӫ" style="margin-left: 0;" />��Ӫ</label>
	                
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>���������б�</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th>
						<th>��λ��</th>
						<th>���ƺ�</th>
						<th>һ���</th>
						<th>�����</th>
						<th>�����</th>
						<th>����״̬</th>
						<th>����״̬</th>
						<th>�������</th>
						<th>����ܶ�</th>
						<th>��ƽ�����</th>
						<th>ǩԼ����</th>
						<th>����ͻ�</th>
						<th>����רԱ</th>
						<th>��ͻ�����</th>
						<th>����</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

	function editRentInfo(unitNo) {
		//var projGuid = $('#projGuid').find('option:selected').val();
		layer.open({
	        type: 2,
	        title: '������Ϣ',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['490px' , '650px'],
	        content: "/wbem/business/customer/editRentPage.action?unitNo="+unitNo
	    });
	}

$(document).ready(function(){
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$("#resetSearch").click(function(){
		$("input[type='radio']").attr("checked",false);
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
         	url: "/wbem/business/customer/loadRentList.action",
		 	data:function(d){			//�ⲿ��������
		 		//d.projGuid=$('#projGuid').find('option:selected').val();
		 		d.saleStatus = encodeURI($("input:radio[name=saleStatus]:checked").val()==undefined?"":$("input:radio[name=saleStatus]:checked").val());
		 		d.shopStatus = encodeURI($("input:radio[name=shopStatus]:checked").val()==undefined?"":$("input:radio[name=shopStatus]:checked").val());
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"unitNo"},
         	{"data":"addressNo"},
         	{"data":"firstArea"},
         	{"data":"secondArea"},
         	{"data":"totalArea"},
         	{"data":"saleStatus"},
         	{"data":"shopStatus"},
         	{"data":"rentStatus"},
         	{"data":"totalRent"},
         	{"data":"dayRent"},
         	{"data":"signUpDate"},
         	{"data":"intentionCst"},
         	{"data":"investmentOfficer"},
         	{"data":"keyAccountManager"},
         	{"data":"operate"},
         ],
          "columnDefs":[
            {
         		targets:15,"render":function(data){
	       			return	"<shiro:hasPermission name='button:rentManage:rentInfo:edit'><a style='color:#FAA732' href='#' onclick='editRentInfo(\"" + data + "\")'>�༭</a></shiro:hasPermission>" ;	
         		}
         	}, 
	        //{ "sortable": false, "targets":[0,1,3,4,5,11,12,13]}
          ] , 
        /*  "ordering":true,
         "order": [9,"desc"],	 */  
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