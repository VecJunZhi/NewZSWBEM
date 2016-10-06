<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<style>
	.label {
		font-weight:normal;
		text-shadow:0 0 0;
	}
</style>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>���������б�</h5>
			<label class="checkbox inline index" id="checklabel"></label>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0 0 0 80px;float:left;">
	            <label class="" style="display:inline;line-height:30px;color:black;text-shadow:0 0 0;">������:</label>
                <select id="mainType" style="width:150px;text-shadow:0 0 0;" onchange="typeChange()">
                	<c:forEach items="${typeList}" var="type" varStatus="status">
                		<option value="${type.issueCode}">${type.issueName}</option>
                	</c:forEach>
                </select>
	        </div>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;"> 
				<shiro:hasPermission name="page:basicSetting:issueTypeSet:add"><button type="button" class="btn btn-success" onclick="issueTypeAdd()">��������</button></shiro:hasPermission>
			 </div> 
        </div>
        <!--startprint1-->
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th>���</th>
						<th>��������</th>
						<th>����������</th>
						<th>����</th>
					</tr>
				</thead>
			</table>
		</div>
		<!--endprint1-->
	</div>
</div>
<div id="editIssueType" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom">
	       <label class="control-label">�������� :</label>
	       <div class="controls">
	         <input type="text" id="issueName" class="" value="" />
	       </div>
	     </div>
     </form>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
 function typeChange() {
	 $('.data-table').DataTable().ajax.reload();
 }

 function issueTypeAdd(){
	layer.open({
        type: 2,
        title: "������������",
       // maxmin: true,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['490px' , '350px'],
        content: "/wbem/inspect/addIssuesType.action"
    });
} 

function issueTypeEdit(issuesId,issueType) {
	$("#issueName").val(issueType)
	var index = layer.open({
        type: 1,
        title: '�༭',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['350px' , '170px'],
        content: $("#editIssueType"),
        btn: ['ȷ��', 'ȡ��'],
        yes: function(){
        	var issueName = encodeURI($("#issueName").val());
        	$.ajax({
        		url:'/wbem/inspect/updateIssuesTypeById.action',
        		data:{"issuesId":issuesId,"issueName":issueName},
        		type:'post',
        		error:function(){
        			//alert("error");
        		},
        		success:function(flag){
        			if(flag == 1){
        				$('.data-table').DataTable().ajax.reload();
        				layer.close(index);
        			}
        		}
        	});
        },
    });
}

function issueTypeDel(issuesId,mainType) {
	var content="";
	if(mainType == null || mainType == ""){
		content = "<div style='text-align: left;margin-left: 20px;font-size: 14px;margin-top: 30px;font-weight: bold;'>ȷ��ɾ����ǰ�������ͼ��������ͣ�</div>";
	}else {
		content = "<div style='text-align: left;margin-left: 20px;font-size: 14px;margin-top: 30px;font-weight: bold;'>ȷ��ɾ����ǰ�������ͣ�</div>";
	}
	var index = layer.open({
        type: 1,
        title: '��ʾ',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['350px' , '200px'],
        content: content,
        btn:['ȷ��','ȡ��'],
        yes:function(){
        	$.ajax({
        		url:'/wbem/inspect/deleteIssuesTypeById.action',
        		data:{"issuesId":issuesId},
        		type:'post',
        		error:function(){
        			//alert("error");
        		},
        		success:function(flag){
        			/* if(mainType == null || mainType == "") {
        				$.ajax({
							url: "/wbem/inspect/loadIssuesTypeList.action",
							data: {"parentCode":"0"},
							type: 'post',
							dataType: 'json',
							success : function(data) {
								var str = "";
								for(var i=0; i<data.length; i++) {
									str += "<option value="+data[i].issueCode+">"+data[i].issueName+"</option>";
								}
								parent.$('#mainType').empty();
								parent.$('#mainType').append(str);
								parent.$("#mainType").select2("val",data[i].issueName);
								parent.$('.data-table').DataTable().ajax.reload();
								parent.layer.close(index);
							}
						});
        			}else{ 
                    	parent.$('.data-table').DataTable().ajax.reload();
                    	layer.close(index);
        			}*/
        			if(flag == 2){
        				layer.close(index);
        				layer.alert("���з�������󶨸�����,����ɾ����");
        			}
        			if(flag == 1){
        				parent.$('.data-table').DataTable().ajax.reload();
                    	layer.close(index);
        			}
        			
        		}
        	});
        },
    });
	
}

$(document).ready(function(){
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$("#resetSearch").click(function(){

		$("#mainType").select2("val","");
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
         	url: "/wbem/inspect/loadTypeList.action",
		 	data:function(d){			//�ⲿ��������
	         	d.mainType = $('#mainType').find('option:selected').val();	
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"issueType"},
			{"data":"mainType"},
         	{"data":"operate"},
         ],
         "columnDefs":[
            {
         		targets:3,"render":function(data, type, row, meta){
       					return "<shiro:hasPermission name='page:basicSetting:issueTypeSet:edit'><a style='color:#FAA732' href='#' onclick='issueTypeEdit(\"" + data + "\""+",\""+row.issueType+"\")'>�༭</a></shiro:hasPermission>&nbsp;"+
       					"<shiro:hasPermission name='page:basicSetting:issueTypeSet:delete'><a style='color:#FAA732' href='#' onclick='issueTypeDel(\"" + data + "\""+",\""+row.mainType+"\")'>ɾ��</a></shiro:hasPermission>";		
         		}
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
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>