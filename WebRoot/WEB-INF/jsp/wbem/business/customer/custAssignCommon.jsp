<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<div id="recycleReason" class="container-fluid" style="margin-top: 20px;"  hidden="hidden">
    <b style="margin-left: 15px;">�ÿͻ���������Ϊ�����ͻ������ڹ����ͻ������·�����ҵ���ʡ�</b>
	<form action="#" class="form-horizontal" >
	  <div class="control-group">
	    <label class="control-label"><span style="color: red;">* </span>����ԭ��</label>
	    <div class="controls">
	      <textarea rows="3" style="width:300px"></textarea>
	      <div style="color:red" id="recycleText"></div>
	    </div>
	  </div>
	</form>
</div>

<div id="editCustInfo" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom">
	       <label class="control-label">�ͻ����� :</label>
	       <div class="controls">
	         <input type="text" id="editCustInfo-Name" class="span3" value="" />
	       </div>
	     </div>
	     <div class="control-group">
	       <label class="control-label">�ͻ��ֻ� :</label>
	       <div class="controls">
	         <input type="text" id="editCustInfo-Tel" class="span3" value="" />
	       </div>
	     </div>
     </form>
</div>
<div id="excelFileUpload" class="container-fluid"  hidden="hidden">
	 <!-- <form action="/wbem/business/customer/import/import_importCustomers.action" method="post"  class="form-horizontal" enctype="multipart/form-data" > -->
	     <div class="control-group noborder-bottom">
	       <label class="control-label">ѡ���ļ� :</label>
	       <div class="controls">
	         <input class="btn btn-default" id="filename" type="file" name="filename"  accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
	       </div>
	     </div>
	     <div class="control-group">
	       <div class="controls">
	         <!-- <input class="btn btn-primary" id="excel_button" type="submit" value="����Excel"/> -->
	         <button class="btn btn-success" id="buttonUpload" onclick="return ajaxFileUpload('create','filename');">�����ļ�</button> 
	       </div>
	     </div>
    <!--  </form> -->
</div>
<div id="importAllocateResult" class="container-fluid"  hidden="hidden">
	 <!-- <form action="/wbem/business/customer/export/importAllocateResult.action" method="post"  class="form-horizontal" enctype="multipart/form-data" > -->
	     <div class="control-group noborder-bottom">
	       <label class="control-label">ѡ���ļ� :</label>
	       <div class="controls">
	         <input class="btn btn-default" id="filename2" type="file" name="filename"  accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
	       </div>
	     </div>
	     <div class="control-group">
	       <div class="controls">
	         <button class="btn btn-success" id="buttonUpload" onclick="return ajaxFileUpload('allocate','filename2');">�����ļ�</button>
	       </div>
	     </div>
    <!--  </form> -->
</div>
<script>
var index_importExcel;
function ajaxFileUpload(flg,filename){ 
	var length=$("#"+filename).val().length;
	if(length==0){
		layer.alert("��ѡ���ļ�");
		return;
	}
	var url=null;
	if(flg=='create'){
		url='/wbem/business/customer/import/import_importCustomers.action';
	}else if (flg=='allocate'){
		url='/wbem/business/customer/export/importAllocateResult.action';//?proId='+$("#proId").find('option:selected').val();
	}
    var _index=layer.load();
    $.ajaxFileUpload({   
            url:url,   
            secureuri:false,   
            fileElementId:filename,
            dataType: 'json',   
            success: function (data, status)   
            {   
               if(data=='success_dusp'){
                	$('.data-table').DataTable().ajax.reload();
					layer.close(index_importExcel);
                	layer.close(_index);
					$.ajax({
				 	         url : '/wbem/business/customer/import/import_exportDupCustomer.action',//������������ַ��ӦsAjaxSource
					         //data : {"data":JSON.stringify({dupList:duplist})},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
					          type : 'post',
					          //dataType : 'json',
					          success : function(result) {          
					          	if(result=='success'){
									layer.close(_index);
									window.location.href="/wbem/business/customer/import/import_exportExcelDemo2.action";	          	
					          	}
					          },
					          error : function(msg) {
					          	layer.alert("ϵͳ�쳣����ϵ����Ա"+msg);
					          }
					});
               }else if(data=='success'){
                	$('.data-table').DataTable().ajax.reload();
					layer.close(index_importExcel);
                	layer.close(_index);
               }else{
                	if(data=='1'){
                		layer.alert("û����Ч����,������ͻ�");
                	}else if(data=='2'){
                		layer.alert("������ҵ����Ϊ�յ������������˲�");
                	}else if(data=='3'){
                		layer.alert("��ѡ��ҵ����û����ϵͳ��ά������˲�");
                	}
                	layer.close(_index);
                	layer.close(index_importExcel);
                } 
            },   
            error: function (data, status, e){   
                layer.alert("ϵͳ�쳣����ϵ�������Ա");
            }   
               
        }   
    );  
    return false;   
}
//1 ���µ���ͻ�ʱ��ģ������
function exportExcel_Demo(){
	//window.location.href="/wbem/business/customer/import/import_exportExcelDemo.action"; 
	var _index=layer.load();
	$.ajax({
 	         url : '/wbem/business/customer/import/import_exportExcelDemo.action',//������������ַ��ӦsAjaxSource
	         //data : {"data":JSON.stringify({cusId:id,proId:proId,reason:reason})},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
	          type : 'post',
	          //dataType : 'json',
	          success : function(result) {          
	          	if(result=='success'){
					layer.close(_index);
					window.location.href="/wbem/business/customer/import/import_exportExcelDemo2.action";	          	
	          	}
	          },
	          error : function(msg) {
	          	layer.alert("ϵͳ�쳣����ϵ����Ա"+msg);
	          }
	}); 
}
//2 ���µ���ͻ�
function importExcel(){
index_importExcel=layer.open({
        type: 1,
        title: '�ļ��ϴ�',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['450px' , '250px'],
        content: $("#excelFileUpload")
    });
}
//3 �����ͻ�����
function exportExcel(){

	//window.location.href="/wbem/business/customer/export/export_followingCustomer.action"; 
	var _index=layer.load();
	$.ajax({
		url : '/wbem/business/customer/export/export_followingCustomer.action',//������������ַ��ӦsAjaxSource
      //data : {"data":JSON.stringify({cusId:id,proId:proId,reason:reason})},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
       	type : 'post',
       //dataType : 'json',
		success : function(result) {          
        	if(result=='success'){
				layer.close(_index);
				window.location.href="/wbem/business/customer/export/export_followingCustomer2.action";
        	}
       },
       error : function(msg) {
       	layer.alert("ϵͳ�쳣����ϵ����Ա");
       }
	}); 
}
//4 ����ͻ����·�����
function importExcelResult(){
index_importExcel=layer.open({
        type: 1,
        title: '�ļ��ϴ�',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['450px' , '250px'],
        content: $("#importAllocateResult")
    });
	//window.location.href="/wbem/houses/customer/export/importAllocateResult.action"; 
}
function cstAssign(id){
	layer.open({
        type: 2,
        title: '�ͻ�����',
        maxmin: true,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['800px' , '600px'],
        content: "/wbem/business/customer/dailog/custAssignEmployeeList.action?cusId="+id,//+"&proId="+$("#proId").find('option:selected').val(),
    });
}

function cstMassAssign() {
	if($("#checkall").attr("checked")== "checked"){
		var ids=$("#ids").val();
		if(ids == '')
			layer.alert("��ѡ��ͻ����ٽ��з��䣡");
		else{
			layer.open({
		        type: 2,
		        title: '��������',
		        maxmin: true,       //���������С��
		        shadeClose: false, //������ֹرղ�
		        area : ['800px' , '600px'],
		        content: "/wbem/business/customer/dailog/custAssignEmployeeList.action?cusId="+ids,//+"&proId="+$("#proId").find('option:selected').val(),
		   	});
	   	}
	}else{
		var cusList = [];
		$("tr","tbody").each(function(){
			if($("input",this).attr("checked") == "checked") {
				var tag = $("td:last",this);
				var cusId = $("a",tag).attr("cusId");
				cusList.push(cusId);
			}
		});
		if(cusList.length == 0)
			layer.alert("��ѡ��ͻ����ٽ��з��䣡");
		else{
			layer.open({
		        type: 2,
		        title: '��������',
		        maxmin: true,       //���������С��
		        shadeClose: false, //������ֹرղ�
		        area : ['800px' , '600px'],
		        content: "/wbem/business/customer/dailog/custAssignEmployeeList.action?cusId="+cusList,//+"&proId="+$("#proId").find('option:selected').val(),
		   	});
	   	}
	}	
}
//��������
function cstMassReback() {
	var cusList = [];
	$("tr","tbody").each(function(){
		if($("input",this).attr("checked") == "checked") {
			var tag = $("td:last",this);
			var cusId = $("a",tag).attr("cusId");
			cusList.push(cusId);
		}
	});
	if(cusList.length == 0)
		layer.alert("��ѡ��ͻ����ٽ��з��䣡");
	else{
	   	$("#recycleReason b").text("�����ڶ�"+cusList.length+"���ͻ�������������..");
	   	cstReback(cusList);
   	}
}
//�ͻ�����
function cstReback(id){
	var ids=id.toString();
	if(ids.split(",").length==1){
		$("#recycleReason b").text("�ÿͻ���������Ϊ�����ͻ������ڹ����ͻ������·�����ҵ���ʡ�");
	}
	layer.open({
        type: 1,
        title: '�ͻ�����',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['450px' , '250px'],
        btn: ['ȷ��', 'ȡ��'],
		btn1: function(index, layero){
		var reason=$("#recycleReason textarea").val();
 			if(reason == ""){
				layer.alert("����д����ԭ��",function(){
					cstReback(id);
				});
				return false;
			}else{
				//var proId=$("#proId").find('option:selected').val();
				reason=encodeURI(reason);
				 	$.ajax({
			 	         url : '/wbem/business/customer/reBackCustomer.action',//������������ַ��ӦsAjaxSource
				          data : {"data":JSON.stringify({cusId:id,/* proId:proId, */reason:reason})},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result==1){
								//alert("���ճɹ�");
								$("#recycleReason textarea").val("");
								var index = parent.layer.getFrameIndex(window.name);
								parent.$('.data-table').DataTable().ajax.reload();
								parent.layer.close(index);
				            }else{
				            	$("#recycleReason textarea").val("");
				              	layer.alert("����ʧ��");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�����쳣������ϵ����Ա");
				          }
			     	}); 
			}
		},
		btn2: function(index){
		},
        content: $("#recycleReason")
    });
    $("#recycleReason textarea").focus();  //�򿪻�ȡ����
}

//�༭�ͻ�
function editCustInfo(id,name,tel,oldOperator){
	$("#editCustInfo-Name").val(name);
	$("#editCustInfo-Tel").val(tel);
	//alert(id+name+tel+oldOperator);
	layer.open({
        type: 1,
        title: '�༭�ͻ�',
        maxmin: false,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['450px' , '250px'],
        btn: ['ȷ��', 'ȡ��'],
		yes: function(index, layero){ //����ʹ��btn1
		    //�ж��ֶ��Ƿ�Ϊ�գ��Ƿ����Ҫ��
		    var newName=$("#editCustInfo-Name").val();
		    var newTel=$("#editCustInfo-Tel").val();
		    if(name==newName && tel==newTel){
		    	layer.alert("û���޸���Ϣ");
		    	return;
		    }
		     var oldName=encodeURI(name);
		    oldOperator=encodeURI(oldOperator);
		    newName=encodeURI(newName); 
		    //var proId=$("#proId").find('option:selected').val();
		    var data={cusId:id,/* proId:proId, */oldName:oldName,newName:newName,oldTel:tel,newTel:newTel,oldOperator:oldOperator};
		    $.ajax({
			 	         url : '/wbem/business/customer/updateZsBasicInfo.action',//������������ַ��ӦsAjaxSource
				          data : {"data":JSON.stringify(data)},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {
				          	if(result !=00){
								//alert("�༭�ɹ�");
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("�༭ʧ��");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�����쳣������ϵ����Ա");
				          }
			     	});
		},
		cancel: function(index){ //����ʹ��btn2
		    //
		},
        content: $("#editCustInfo")
    });
    $("#editCustInfo-Tel").focus();
}

//�鿴�ͻ�
function cstDetail(cusid,userName){
	layer.open({
        type: 2,
        title: '�鿴�ͻ�',
        maxmin: true,       //���������С��
        shadeClose: false, //������ֹرղ�
        area : ['800px' , '620px'],
        content: "/wbem/business/customer/dailog/custDetail.action?cusid="+ cusid + "&userName=" + encodeURI(userName),//+"&proId="+$("#proId").find('option:selected').val(),

    });
}

//�����Ƶ�������
function cstMassMoveToDustbin() {
	var cusList = [];
	$("tr","tbody").each(function(){
		if($("input",this).attr("checked") == "checked") {
			var tag = $("td:last",this);
			var cusId = $("a",tag).attr("cusId");
			cusList.push(cusId);
		}
	});
	if(cusList.length == 0)
		layer.alert("��ѡ��ͻ����ٽ��з��䣡");
	else{
	   	moveToDustbin(cusList);
   	}
}
//(�����ͻ�)����������
function moveToDustbin(id){
	layer.confirm('ȷ��Ҫ��ѡ��Ŀͻ��Ƶ�������ô��', {
		btn: ['ȷ��','ȡ��'],
		yes: function(index, layero){ //����ʹ��btn1
		     //var proId=$("#proId").find('option:selected').val();
				 	$.ajax({
			 	         url : '/wbem/business/customer/dusbinCustomers.action',//������������ַ��ӦsAjaxSource
				          data : {"data":JSON.stringify({cusId:id/* ,proId:proId */})},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result==1){
								//alert("�ɹ�����������");
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("����������ʧ��");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�����쳣������ϵ����Ա");
				          }
			     	});
		},
		cancel: function(index){ //����ʹ��btn2
		    //alert("2");
		},
	});
}
/* �������������� */
function cstMassMoveToPublic() {
	var cusList = [];
	$("tr","tbody").each(function(){
		if($("input",this).attr("checked") == "checked") {
			var tag = $("td:last",this);
			var cusId = $("a",tag).attr("cusId");
			cusList.push(cusId);
		}
	});
	if(cusList.length == 0)
		layer.alert("��ѡ��ͻ����ٽ��з��䣡");
	else{
	   	moveToPublic(cusList);
   	}
}
/* ��������� */
function moveToPublic(id){
	layer.confirm('ȷ��Ҫ��ѡ��Ŀͻ�תΪ�����ͻ���', {
		btn: ['ȷ��','ȡ��'],
		yes: function(index, layero){ //����ʹ��btn1
		    //var proId=$("#proId").find('option:selected').val();
				 	$.ajax({
			 	         url : '/wbem/business/customer/reBackCustomer.action',//������������ַ��ӦsAjaxSource
				          data : {"data":JSON.stringify({cusId:id,/* proId:proId, */reason:""})},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result==1){
								//alert("�ɹ�����");
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("����ʧ��");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�����쳣������ϵ����Ա");
				          }
			     	}); 
		},
		cancel: function(index){ //����ʹ��btn2
		    //alert("2");
		},
	});
}
function allcheck(tag){ 
	if($(tag).attr("checked") == "checked") {
		$("#checklabel").attr("style","display:block");
			
	}else{
		$("#checklabel").attr("style","display:none");
		
	}
}
$("#checkall").click(function(){
	if($("#checkall").attr("checked")== "checked"){
		$("#checklabel_after").attr("style","display:block");
	}else{
		$("#checklabel_after").attr("style","display:none");
	}
});
$("#dropdown-menu > li").each(function (i) {
	var value='';
	switch(i){
	   case 0:
		   $(this).click(function(){
		    value=$(this ).text();
		    $("#telOrName").val("");
		     $("#dropdown_btn").html(value+"<span class='caret' style='margin-left: 20px;'></span>");
		   });
	   break;
	   case 1:
		   $(this).click(function(){
		    	value=$(this).text();
		    	$("#telOrName").val("");
		    	$("#dropdown_btn").html(value+"<span class='caret' style='margin-left: 20px;'></span>");
		   });
		break;
	
	}
});
</script>