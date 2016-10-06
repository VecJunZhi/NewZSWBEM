<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
	<style>
		.table tr td:last-child {
			border-right:1px solid #ddd;
		}
		.table tr th:first-child {
			border-left:0 solid #ddd !important;
		}
		/* .no-footer {
			border-bottom:0px solid #ddd !important;
		} */
		table.dataTable.no-footer {
			border-bottom:1px solid #ddd !important;
		}
		.table td {
			text-align:center !important;
		}
	</style>
	<div style="display:none;" id="printTable">
		<div class="widget-box">
		<div class="widget-title" style="text-align:center;">
			<div style="margin-top:10px;font-size:16px;font-weight:bold;">�����б�</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th>���</th>
						<th>¥��</th>
						<th>��Ԫ</th>
						<th>�����</th>
						<th>��������</th>
						<th>��������</th>
						<th>�����̶�</th>
						<th>��ע</th>
					</tr>
				</thead>
			</table>
		</div>
		<%-- <input type="hidden" value="${projGuid}" id="projGuid"/> --%>
		<input type="hidden" value="${bldGuid}" id="bldGuid"/>
		<input type="hidden" value="${unitGuid}" id="unitGuid"/>
		<input type="hidden" value="${status}" id="status"/>
		<input type="hidden" value="${mainType}" id="mainType"/>
	</div>
	</div>
	<div style="height:90px">
	<div style="height:60px;font-size: 16px;font-weight: bolder;text-align: center;padding-top: 40px;">ȷ����ӡ��</div>
	<button class="btn btn-success" onclick="queryPrint()" style="float:right;right:0">ȷ��</button> 
	<button class="btn btn-primary" onclick="canclePrint()" style="float:right;margin-right:5px">ȡ��</button>
	</div>
	
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
function queryPrint(){

 	window.document.body.innerHTML=$("#printTable").html();
	//wb.execwb(8,1);
	window.print();
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index); 
	
}

function canclePrint() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

$(document).ready(function(){
	
	$('.data-table').dataTable({
		//"dom":"<''r>t",
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
         	url: "/wbem/inspect/loadPrintIssuesList.action",
		 	data:function(d){			//�ⲿ��������
		 		//d.projGuid = $('#projGuid').val();
	         	d.bldGuid = $("#bldGuid").val();
	         	d.unitGuid = $("#unitGuid").val();
	         	d.status = $("#status").val();
	         	d.mainType = $("#mainType").val();
	        } ,
         },
         "columns":[
         	{"data":"index"},
         	{"data":"bldName"},
         	{"data":"unit"},
         	{"data":"room"},
         	{"data":"issueType"},
         	{"data":"issueDesc"},
         	{"data":"level"},
         	{"data":"remark"},
         ],
         "columnDefs":[
            {
         		/* targets:7,"render":function(data){
	       			return	"<a style='color:#FAA732' href='#' onclick='inspectFeedback(\"" + data + "\",\"1\")'>ȷ������</a>&nbsp;" + 
	       				    "<a style='color:#FAA732' href='#' onclick='inspectFeedback(\"" + data + "\",\"2\")'>�˻ط���</a>&nbsp;"+
	       				 "<a style='color:#FAA732' href='#' onclick='issuesDetail(\"" + data + "\")'>�鿴</a>&nbsp;";		
         		} */
         	}, 
	        //{ "sortable": false, "targets":[0,1,3,4,5,10,11,12]}
          ] , 
         //"ordering":true,
         //"order": [8,"desc"],//Ĭ������	  
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
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>