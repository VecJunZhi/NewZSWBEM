<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <div class="control-group noborder-top noborder-bottom">
	              <%-- <label class="control-label zygw" >ѡ����Ŀ</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="projGuid" onchange="changeProj(this)">
	                     <c:forEach items="${projectList}" var="project" varStatus="status">
              				<option value="${project.id}">${project.name}</option>
              	  		</c:forEach>
	                </select>
	              </div>  --%>
	              <label class="control-label zygw" >¥��</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="bldGuid" onchange="changeBld(this)">
	                	<option selected="selected"></option>
	                   <c:forEach items="${bldList}" var="bld" varStatus="status">
              			<option value="${bld.build.bldGuid}">${bld.build.bldName}</option>
             		   </c:forEach>
	                </select>
	              </div>
	              <label class="control-label zygw" >��Ԫ</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="unitGuid" >
	                	<option selected="selected"></option>
	                </select>
	              </div> 
	            </div> 
	            <div class="control-group noborder-top noborder-bottom">
	              
	              <label class="control-label zygw" >����</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="mainType" >
	                	<option value=""></option>
	                	<c:forEach items="${typeList}" var="type" varStatus="status">
	                		<option value="${type.issueCode}">${type.issueName}</option>
	                	</c:forEach>
	                </select>
	              </div>
	              <div class="input-prepend input-append" style="margin-top:10px;">
					  <span class="add-on" style="width: 103px;margin-right: 5px;">�޸�״̬</span>
					  <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="0" style="margin-left: 0;"/>δ�޸�</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="1" style="margin-left: 0;"/>�޸����</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="4" style="margin-left: 0;"/>�������</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="3" style="margin-left: 0;"/>�˻ط���</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="2" style="margin-left: 0;"/>ȷ�����</label>
				 </div>
	            </div>
	            
	            <div class="control-group noborder-top noborder-bottom">
	              <div class="control-group noborder-bottom noborder-top"> 
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>�����б�</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;"> 
				<shiro:hasPermission name="button:propertyManage:addIssues:add"><button type="button" class="btn btn-primary" onclick="addIssue()">�Ǽ�����</button></shiro:hasPermission>
				<shiro:hasPermission name="button:propertyManage:addIssues:print"><button type="button" class="btn btn-success" onclick="print()">��ӡ</button></shiro:hasPermission>
			 </div> 
         </div>
         <!--startprint1-->
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th>
						<th>¥��</th>
						<th>��Ԫ</th>
						<th>�����</th>
						<th>����λ��</th>
						<th>��������</th>
						<th>��������</th>
						<th>�����̶�</th>
						<th>����״̬</th>
						<th>��������</th>
						<th>����</th>
					</tr>
				</thead>
			</table>
		</div>
		<!--endprint1-->
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

 function addIssue(){
	//var projGuid=$('#projGuid').find('option:selected').val();
	layer.open({
        type: 2,
        title: false,
       // maxmin: true,       //���������С��
        shadeClose: false, //������ֹرղ�
        closeBtn:0,
        area : ['1200px' , '620px'],
        content: "/wbem/inspect/addIssues.action"
    });
} 

function inspectFeedback(issuesId,status) {
	layer.open({
        type: 2,
        title: status==1?'ȷ������':'�˻ط���',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['490px' , '300px'],
        content: "/wbem/inspect/inspectFeedback.action?issuesId="+issuesId+"&status="+status
    });
}
 
function issuesDetail(issuesId) {
	layer.open({
        type: 2,
        title: '���ⷴ������',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['850px' , '600px'],
        content: "/wbem/inspect/issuesFeedbackDetail.action?issuesId="+issuesId
    });
}


function print() {
	//var projGuid = $("#projGuid option:selected").val();
	var bldGuid = $("#bldGuid option:selected").val();
	var unitGuid = $("#unitGuid option:selected").val();
	var mainType = $("#mainType option:selected").val();
	var status = checkbox_CheckedValue("status").toString();
	layer.open({
        type: 2,
        title: '��ʾ',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['300px' , '200px'],
        content: "/wbem/inspect/inspectPrint.action?bldGuid="+bldGuid+"&unitGuid="+unitGuid+"&status="+status+"&mainType="+mainType
    });
}
/* function changeProj(tag){
	var projGuid = $("option:selected",tag).val();
	$.ajax({
		url: '/wbem/inspect/loadBldList.action',
		data: {'projGuid':projGuid},
		type: 'post',
		error:function(){
			
		},
		success:function(data){
			$("#bldGuid").select2("val","");
			$("#bldGuid").empty();
			var str = "<option></option>";
			for(var i=0; i<data.length; i++) {
				str += "<option value="+data[i].build.bldGuid+">"+data[i].build.bldName+"</option>";
			}
			$("#bldGuid").append(str);
		}
	})
} */
function changeBld(tag) {
	var bldGuid = $("option:selected",tag).val();
	$.ajax({
		url: '/wbem/inspect/loadUnitList.action',
		data: {'bldGuid':bldGuid},
		type: 'post',
		error:function(){
			
		},
		success:function(data){
			$("#unitGuid").select2("val","");
			$("#unitGuid").empty();
			var str = "<option></option>";
			for(var i=0; i<data.length; i++) {
				str += "<option value="+data[i].unitGuid+">"+data[i].unit+"</option>";
			}
			$("#unitGuid").append(str);
		}
	})
}

function issuesDel(issuesId) {
	var index = layer.open({
        type: 1,
        title: '��ʾ',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['350px' , '200px'],
        content: "<div style='text-align: left;margin-left: 20px;font-size: 14px;margin-top: 30px;font-weight: bold;'>ȷ��ɾ����ǰ���⣿</div>",
        btn:['ȷ��','ȡ��'],
        yes:function(){
        	$.ajax({
        		url:'/wbem/inspect/deleteIssueDetailById.action',
        		data:{"issuesId":issuesId},
        		type:'post',
        		error:function(){
        			//alert("error");
        		},
        		success:function(data){
        			//$('.data-table').DataTable().ajax.reload();
        			//var index2 = parent.layer.getFrameIndex(window.name);
        			layer.close(index);
                	parent.$('.data-table').DataTable().ajax.reload();
                	//parent.layer.close(index2);
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

		$("#bldGuid").select2("val","");
		$("#unitGuid").select2("val","");
		$("input[type='radio']").attr("checked",false);
		$("#mainType").select2("val","");
		//$("#telOrName").val("");
		//$("input[type='checkbox']").attr("checked",false);
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"lengthMenu": [15,25,50,100,200,300,500],
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
         	url: "/wbem/inspect/loadIssuesList.action",
		 	data:function(d){			//�ⲿ��������
		 		//d.projGuid=$('#projGuid').find('option:selected').val();
	         	d.bldGuid = $('#bldGuid').find('option:selected').val();
	         	d.unitGuid = $('#unitGuid').find('option:selected').val();
	         	d.status = checkbox_CheckedValue("status").toString();
	         	d.mainType = $('#mainType').find('option:selected').val();
	    		/*d.urgedType = checkbox_CheckedValue("urgedType").toString(); */
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"bldName"},
         	{"data":"unit"},
         	{"data":"room"},
         	{"data":"issuePosition"},
         	{"data":"issueType"},
         	{"data":"issueDesc"},
         	{"data":"level"},
         	{"data":"status"},
         	{"data":"createDate"},
         	{"data":"operate"},
         ],
         "columnDefs":[
            {
         		targets:10,"render":function(data, type, row, meta){
         			if(row.status == "�����޸����" || row.status == "���̸������") { 
         			 return	 "<shiro:hasPermission name='button:propertyManage:addIssues:inspectFeedback'><a style='color:#FAA732' href='#' onclick='inspectFeedback(\"" + data + "\",\"1\")'>ȷ������</a></shiro:hasPermission>&nbsp;" + 
	       				    "<shiro:hasPermission name='button:propertyManage:addIssues:inspectFeedback'><a style='color:#FAA732' href='#' onclick='inspectFeedback(\"" + data + "\",\"2\")'>�˻ط���</a></shiro:hasPermission>&nbsp;"+
	       				 "<a style='color:#FAA732' href='#' onclick='issuesDetail(\"" + data + "\")'>�鿴</a>";
	       			}
         			if(row.status == "δ�޸�") {
       					return "<shiro:hasPermission name='button:propertyManage:addIssues:delete'><a style='color:#FAA732' href='#' onclick='issuesDel(\"" + data + "\")'>ɾ��</a></shiro:hasPermission>";	
         			}
         			if(row.status == "��ҵ�˻ط���" || row.status == "��ҵȷ�����") {
         				return	 "<a style='color:#FAA732' href='#' onclick='issuesDetail(\"" + data + "\")'>�鿴</a>";
         			} 
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