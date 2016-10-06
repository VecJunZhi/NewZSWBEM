<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<div id="recycleReason" class="container-fluid" style="margin-top: 20px;"  hidden="hidden">
    <b style="margin-left: 15px;">该客户将被回收为公共客户，可在公共客户中重新分配置业顾问。</b>
	<form action="#" class="form-horizontal" >
	  <div class="control-group">
	    <label class="control-label"><span style="color: red;">* </span>回收原因</label>
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
	       <label class="control-label">客户姓名 :</label>
	       <div class="controls">
	         <input type="text" id="editCustInfo-Name" class="span3" value="" />
	       </div>
	     </div>
	     <div class="control-group">
	       <label class="control-label">客户手机 :</label>
	       <div class="controls">
	         <input type="text" id="editCustInfo-Tel" class="span3" value="" />
	       </div>
	     </div>
     </form>
</div>
<div id="excelFileUpload" class="container-fluid"  hidden="hidden">
	 <!-- <form action="/wbem/business/customer/import/import_importCustomers.action" method="post"  class="form-horizontal" enctype="multipart/form-data" > -->
	     <div class="control-group noborder-bottom">
	       <label class="control-label">选中文件 :</label>
	       <div class="controls">
	         <input class="btn btn-default" id="filename" type="file" name="filename"  accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
	       </div>
	     </div>
	     <div class="control-group">
	       <div class="controls">
	         <!-- <input class="btn btn-primary" id="excel_button" type="submit" value="导入Excel"/> -->
	         <button class="btn btn-success" id="buttonUpload" onclick="return ajaxFileUpload('create','filename');">导入文件</button> 
	       </div>
	     </div>
    <!--  </form> -->
</div>
<div id="importAllocateResult" class="container-fluid"  hidden="hidden">
	 <!-- <form action="/wbem/business/customer/export/importAllocateResult.action" method="post"  class="form-horizontal" enctype="multipart/form-data" > -->
	     <div class="control-group noborder-bottom">
	       <label class="control-label">选中文件 :</label>
	       <div class="controls">
	         <input class="btn btn-default" id="filename2" type="file" name="filename"  accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
	       </div>
	     </div>
	     <div class="control-group">
	       <div class="controls">
	         <button class="btn btn-success" id="buttonUpload" onclick="return ajaxFileUpload('allocate','filename2');">导入文件</button>
	       </div>
	     </div>
    <!--  </form> -->
</div>
<script>
var index_importExcel;
function ajaxFileUpload(flg,filename){ 
	var length=$("#"+filename).val().length;
	if(length==0){
		layer.alert("请选择文件");
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
				 	         url : '/wbem/business/customer/import/import_exportDupCustomer.action',//这个就是请求地址对应sAjaxSource
					         //data : {"data":JSON.stringify({dupList:duplist})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
					          type : 'post',
					          //dataType : 'json',
					          success : function(result) {          
					          	if(result=='success'){
									layer.close(_index);
									window.location.href="/wbem/business/customer/import/import_exportExcelDemo2.action";	          	
					          	}
					          },
					          error : function(msg) {
					          	layer.alert("系统异常，联系管理员"+msg);
					          }
					});
               }else if(data=='success'){
                	$('.data-table').DataTable().ajax.reload();
					layer.close(index_importExcel);
                	layer.close(_index);
               }else{
                	if(data=='1'){
                		layer.alert("没有有效数据,请输入客户");
                	}else if(data=='2'){
                		layer.alert("存在置业顾问为空的情况，请认真核查");
                	}else if(data=='3'){
                		layer.alert("所选置业顾问没有在系统中维护，请核查");
                	}
                	layer.close(_index);
                	layer.close(index_importExcel);
                } 
            },   
            error: function (data, status, e){   
                layer.alert("系统异常，联系网络管理员");
            }   
               
        }   
    );  
    return false;   
}
//1 线下导入客户时候模板下载
function exportExcel_Demo(){
	//window.location.href="/wbem/business/customer/import/import_exportExcelDemo.action"; 
	var _index=layer.load();
	$.ajax({
 	         url : '/wbem/business/customer/import/import_exportExcelDemo.action',//这个就是请求地址对应sAjaxSource
	         //data : {"data":JSON.stringify({cusId:id,proId:proId,reason:reason})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	          type : 'post',
	          //dataType : 'json',
	          success : function(result) {          
	          	if(result=='success'){
					layer.close(_index);
					window.location.href="/wbem/business/customer/import/import_exportExcelDemo2.action";	          	
	          	}
	          },
	          error : function(msg) {
	          	layer.alert("系统异常，联系管理员"+msg);
	          }
	}); 
}
//2 线下导入客户
function importExcel(){
index_importExcel=layer.open({
        type: 1,
        title: '文件上传',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['450px' , '250px'],
        content: $("#excelFileUpload")
    });
}
//3 导出客户资料
function exportExcel(){

	//window.location.href="/wbem/business/customer/export/export_followingCustomer.action"; 
	var _index=layer.load();
	$.ajax({
		url : '/wbem/business/customer/export/export_followingCustomer.action',//这个就是请求地址对应sAjaxSource
      //data : {"data":JSON.stringify({cusId:id,proId:proId,reason:reason})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
       	type : 'post',
       //dataType : 'json',
		success : function(result) {          
        	if(result=='success'){
				layer.close(_index);
				window.location.href="/wbem/business/customer/export/export_followingCustomer2.action";
        	}
       },
       error : function(msg) {
       	layer.alert("系统异常，联系管理员");
       }
	}); 
}
//4 导入客户线下分配结果
function importExcelResult(){
index_importExcel=layer.open({
        type: 1,
        title: '文件上传',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['450px' , '250px'],
        content: $("#importAllocateResult")
    });
	//window.location.href="/wbem/houses/customer/export/importAllocateResult.action"; 
}
function cstAssign(id){
	layer.open({
        type: 2,
        title: '客户分配',
        maxmin: true,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['800px' , '600px'],
        content: "/wbem/business/customer/dailog/custAssignEmployeeList.action?cusId="+id,//+"&proId="+$("#proId").find('option:selected').val(),
    });
}

function cstMassAssign() {
	if($("#checkall").attr("checked")== "checked"){
		var ids=$("#ids").val();
		if(ids == '')
			layer.alert("请选择客户后再进行分配！");
		else{
			layer.open({
		        type: 2,
		        title: '批量分配',
		        maxmin: true,       //开启最大最小化
		        shadeClose: false, //点击遮罩关闭层
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
			layer.alert("请选择客户后再进行分配！");
		else{
			layer.open({
		        type: 2,
		        title: '批量分配',
		        maxmin: true,       //开启最大最小化
		        shadeClose: false, //点击遮罩关闭层
		        area : ['800px' , '600px'],
		        content: "/wbem/business/customer/dailog/custAssignEmployeeList.action?cusId="+cusList,//+"&proId="+$("#proId").find('option:selected').val(),
		   	});
	   	}
	}	
}
//批量回收
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
		layer.alert("请选择客户后再进行分配！");
	else{
	   	$("#recycleReason b").text("您正在对"+cusList.length+"个客户进行批量回收..");
	   	cstReback(cusList);
   	}
}
//客户回收
function cstReback(id){
	var ids=id.toString();
	if(ids.split(",").length==1){
		$("#recycleReason b").text("该客户将被回收为公共客户，可在公共客户中重新分配置业顾问。");
	}
	layer.open({
        type: 1,
        title: '客户回收',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['450px' , '250px'],
        btn: ['确定', '取消'],
		btn1: function(index, layero){
		var reason=$("#recycleReason textarea").val();
 			if(reason == ""){
				layer.alert("请填写回收原因",function(){
					cstReback(id);
				});
				return false;
			}else{
				//var proId=$("#proId").find('option:selected').val();
				reason=encodeURI(reason);
				 	$.ajax({
			 	         url : '/wbem/business/customer/reBackCustomer.action',//这个就是请求地址对应sAjaxSource
				          data : {"data":JSON.stringify({cusId:id,/* proId:proId, */reason:reason})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result==1){
								//alert("回收成功");
								$("#recycleReason textarea").val("");
								var index = parent.layer.getFrameIndex(window.name);
								parent.$('.data-table').DataTable().ajax.reload();
								parent.layer.close(index);
				            }else{
				            	$("#recycleReason textarea").val("");
				              	layer.alert("回收失败");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统发生异常，请联系管理员");
				          }
			     	}); 
			}
		},
		btn2: function(index){
		},
        content: $("#recycleReason")
    });
    $("#recycleReason textarea").focus();  //打开获取焦点
}

//编辑客户
function editCustInfo(id,name,tel,oldOperator){
	$("#editCustInfo-Name").val(name);
	$("#editCustInfo-Tel").val(tel);
	//alert(id+name+tel+oldOperator);
	layer.open({
        type: 1,
        title: '编辑客户',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['450px' , '250px'],
        btn: ['确定', '取消'],
		yes: function(index, layero){ //或者使用btn1
		    //判断字段是否为空，是否符合要求
		    var newName=$("#editCustInfo-Name").val();
		    var newTel=$("#editCustInfo-Tel").val();
		    if(name==newName && tel==newTel){
		    	layer.alert("没有修改信息");
		    	return;
		    }
		     var oldName=encodeURI(name);
		    oldOperator=encodeURI(oldOperator);
		    newName=encodeURI(newName); 
		    //var proId=$("#proId").find('option:selected').val();
		    var data={cusId:id,/* proId:proId, */oldName:oldName,newName:newName,oldTel:tel,newTel:newTel,oldOperator:oldOperator};
		    $.ajax({
			 	         url : '/wbem/business/customer/updateZsBasicInfo.action',//这个就是请求地址对应sAjaxSource
				          data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {
				          	if(result !=00){
								//alert("编辑成功");
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("编辑失败");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统发生异常，请联系管理员");
				          }
			     	});
		},
		cancel: function(index){ //或者使用btn2
		    //
		},
        content: $("#editCustInfo")
    });
    $("#editCustInfo-Tel").focus();
}

//查看客户
function cstDetail(cusid,userName){
	layer.open({
        type: 2,
        title: '查看客户',
        maxmin: true,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['800px' , '620px'],
        content: "/wbem/business/customer/dailog/custDetail.action?cusid="+ cusid + "&userName=" + encodeURI(userName),//+"&proId="+$("#proId").find('option:selected').val(),

    });
}

//批量移到垃圾箱
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
		layer.alert("请选择客户后再进行分配！");
	else{
	   	moveToDustbin(cusList);
   	}
}
//(公共客户)移至拉圾箱
function moveToDustbin(id){
	layer.confirm('确定要将选择的客户移到垃圾箱么？', {
		btn: ['确定','取消'],
		yes: function(index, layero){ //或者使用btn1
		     //var proId=$("#proId").find('option:selected').val();
				 	$.ajax({
			 	         url : '/wbem/business/customer/dusbinCustomers.action',//这个就是请求地址对应sAjaxSource
				          data : {"data":JSON.stringify({cusId:id/* ,proId:proId */})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result==1){
								//alert("成功移至垃圾箱");
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("移至垃圾箱失败");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统发生异常，请联系管理员");
				          }
			     	});
		},
		cancel: function(index){ //或者使用btn2
		    //alert("2");
		},
	});
}
/* 垃圾箱批量回收 */
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
		layer.alert("请选择客户后再进行分配！");
	else{
	   	moveToPublic(cusList);
   	}
}
/* 垃圾箱回收 */
function moveToPublic(id){
	layer.confirm('确定要将选择的客户转为公共客户吗？', {
		btn: ['确定','取消'],
		yes: function(index, layero){ //或者使用btn1
		    //var proId=$("#proId").find('option:selected').val();
				 	$.ajax({
			 	         url : '/wbem/business/customer/reBackCustomer.action',//这个就是请求地址对应sAjaxSource
				          data : {"data":JSON.stringify({cusId:id,/* proId:proId, */reason:""})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result==1){
								//alert("成功回收");
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("回收失败");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统发生异常，请联系管理员");
				          }
			     	}); 
		},
		cancel: function(index){ //或者使用btn2
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