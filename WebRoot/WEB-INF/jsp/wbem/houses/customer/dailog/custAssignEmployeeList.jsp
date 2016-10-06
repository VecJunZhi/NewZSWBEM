<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="widget-box">
	<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>�����ڶ�${cusNum}���ͻ����з���...</h5>
		<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
			<input type="text" style="margin-bottom:0" id="telOrName" placeholder="����/�ֻ�">
			<button class="btn btn-success" id="tableSearch">��ѯ</button>
		</span>
	</div>
	<div class="widget-content nopadding">
		<table id="example" class="table table-bordered data-table">
			<thead>
				<tr>				
					<th>���</th>
					<th>����</th>
					<th>�ֻ�</th>
					<th>���</th>
					<th>����</th>
	 			</tr>  
			</thead>
		</table>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script>

var proId;	
function cstAssign(id){
var index=layer.load();
	$.ajax({
          url : '/wbem/houses/customer/allocateCustomer.action',//������������ַ��ӦsAjaxSource
          data : {"data":JSON.stringify({userGuid:id/* ,projGUID:proId */})},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
          type : 'post',
          dataType : 'json',
          success : function(result) {          
          	if(result !=-1){
				layer.close(index);
				var index = parent.layer.getFrameIndex(window.name);
				parent.$('.data-table').DataTable().ajax.reload();
				parent.layer.close(index);
				
            }else{
              	layer.alert("����ʧ��");
            }
          },
          error : function(msg) {
          	layer.alert("ϵͳ�쳣���������Ա��ϵ");
          }
     });
}
$(document).ready(function(){
	//cusId = ${cusId};
	//proId = ${proId};
	//oppGUID=${oppGUID}
	//alert("cusid "+cusId);
	//table 
	$('.data-table').dataTable({
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
			url: "/wbem/houses/customer/getEmployeeList.action",
			data:function(d){			//�ⲿ��������
	           	d.telOrName = encodeURI($("#telOrName").val());
	        }
		},
		"columns":[
			{"data":"index"},
			{"data":"userName"},
			{"data":"mobile"},
			{"data":"duty"},
			{"data":"operate"}
		]
	});
	
	$("select").select2();
	
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$('#example tbody').on('mouseenter', 'tr', function () {
		$(this).attr("style","background-color:#CCCCCC");
		var tag = $("td:first",this);
		var employeeId = $("input",tag).val();
		$("td:last",this).append("<a style='color:#FAA732' href='#' onclick='cstAssign(\""+employeeId+"\")'>����</a>");
	});
	
	$('#example tbody').on('mouseleave', 'tr', function () {
		$("td:last",this).empty();
		$(this).attr("style","background-color:#F9F9F9");
		
	});
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>