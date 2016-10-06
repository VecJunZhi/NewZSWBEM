<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="utf-8"%>

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
	     <div class="control-group" hidden="hidden">
	       <label class="control-label"></label>
	       <div class="controls">
	         <input type="text" id="editCustInfo-Tel" class="span3" value="" />
	       </div>
	     </div>
	     <div class="control-group" id="mobileTel">
	       <label class="control-label">客户手机 :</label>
	       <div class="controls">
	         <input type="text" id="mobileTelModule" class="span3" value="" />
	       </div>
	     </div>
	     <div class="control-group" id="homeTel" hidden="hidden">
	       <label class="control-label">家庭电话 :</label>
	       <div class="controls">
	         <input type="text" id="homeTelModule" class="span3" value="" />
	       </div>
	     </div>
	     <div class="control-group" id="officeTel" hidden="hidden">
	       <label class="control-label">工作电话 :</label>
	       <div class="controls">
	         <input type="text" id="officeTelModule" class="span3" value="" />
	       </div>
	     </div>
	     <div class="control-group" id="fax" hidden="hidden">
	       <label class="control-label">传真 :</label>
	       <div class="controls">
	         <input type="text" id="faxModule" class="span3" value="" />
	       </div>
	     </div>
	 </form>
</div>

<div id="excelFileUpload" class="container-fluid"  hidden="hidden">
	<!--  <form action="/wbem/houses/customer/import/import_importCustomers.action" method="post"  class="form-horizontal" enctype="multipart/form-data" > -->
	     <div class="control-group noborder-bottom">
	       <label class="control-label">选中文件 :</label>
	       <div class="controls">
	         <input class="btn btn-default" id="filename" type="file" name="filename"  accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
	       </div>
	     </div>
	     <div class="control-group">
	       <div class="controls">
	         <!-- <input class="btn btn-primary" id="excel_button" type="button" value="导入Excel"/> -->
	         <button class="btn btn-success" id="buttonUpload" onclick="return ajaxFileUpload('create','filename');">导入文件</button> 
	       </div>
	     </div>
   <!--   </form> -->
</div>
<div id="importAllocateResult" class="container-fluid"  hidden="hidden">
	<!--  <form action="/wbem/houses/customer/export/importAllocateResult.action" method="post"  class="form-horizontal" enctype="multipart/form-data" > -->
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
     <!-- </form> -->
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
		url='/wbem/houses/customer/import/import_importCustomers.action';
	}else if (flg=='allocate'){
		url='/wbem/houses/customer/export/importAllocateResult.action';
	}
    var _index=layer.load();
    $.ajaxFileUpload   
    ({   
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
				 	         url : '/wbem/houses/customer/import/import_exportDupCustomer.action',//这个就是请求地址对应sAjaxSource
					         //data : {"data":JSON.stringify({dupList:duplist})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
					          type : 'post',
					          //dataType : 'json',
					          success : function(result) {          
					          	if(result=='success'){
									layer.close(_index);
									window.location.href="/wbem/houses/customer/import/import_exportExcelDemo2.action";	          	
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
                } else{
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
            error: function (data, status, e)   
            {   
                layer.alert("系统异常，联系网络管理员");
            }   
               
        }   
    ) ;  
    return false;   
} 

function cstAssign(id,oppGUID,userGuid){
		var cusList = [];
		var oppList=[];
		var userGuidArray=[];
		cusList.push(id);
		oppList.push(oppGUID);
		userGuidArray.push(userGuid);
		$.ajax({
 	         url : '/wbem/houses/customer/receiveCustAssignEmployeeList.action',//这个就是请求地址对应sAjaxSource
	         data : {"data":JSON.stringify({cusId:cusList,oppList:oppList,userGuid:userGuidArray})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	          type : 'post',
	          dataType : 'json',
	          success : function(result) {          
	          	if(result =='1'){
					layer.open({
				        type: 2,
				        title: '客户分配',
				        maxmin: true,       //开启最大最小化
				        shadeClose: false, //点击遮罩关闭层
				        area : ['800px' , '600px'],
				        content: "/wbem/houses/customer/custAssignEmployeeList.action",
				   	});
	            }else{
	              	layer.alert("传送ID出错，联系管理员");
	            }
	          },
	          error : function(msg) {
	          	layer.alert("系统异常，联系管理员");
	          }
		});
	/* layer.open({
        type: 2,
        title: '客户分配',
        maxmin: true,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['800px' , '600px'],
        content: "/wbem/houses/customer/custAssignEmployeeList.action?cusId="+id+"&proId="+$("#proId").find('option:selected').val()+"&oppGUID="+oppGUID,
    }); */
}

function cstMassAssign(){
	var cusList = [];
	var oppList=[];
	var userGuidArray=[];
	$("tr","tbody").each(function(){
		if($("input",this).attr("checked") == "checked") {
			var tag = $("td:last",this);
			var cusId = $("a",tag).attr("cusId");
			var opp = $("a",tag).attr("oppGUID");
			var userGuid = $("a",tag).attr("userGuid");
			cusList.push(cusId);
			oppList.push(opp);
			userGuidArray.push(userGuid);
		}
	});
	if(cusList.length == 0)
		layer.alert("请选择客户后再进行分配！");
	else{//先把数据异步传送，在定位到这页
	
			$.ajax({
	 	         url : '/wbem/houses/customer/receiveCustAssignEmployeeList.action',//这个就是请求地址对应sAjaxSource
		         data : {"data":JSON.stringify({cusId:cusList,oppList:oppList,userGuid:userGuidArray})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
		          type : 'post',
		          dataType : 'json',
		          success : function(result) {          
		          	if(result =='1'){
						layer.open({
					        type: 2,
					        title: '批量分配',
					        maxmin: true,       //开启最大最小化
					        shadeClose: false, //点击遮罩关闭层
					        area : ['800px' , '600px'],
					        content: "/wbem/houses/customer/custAssignEmployeeList.action",//?proId="+$("#proId").find('option:selected').val(),
					   	});
		            }else{
		              	layer.alert("传送批量ID出错，联系管理员");
		            }
		          },
		          error : function(msg) {
		          	layer.alert("系统异常，联系管理员");
		          }
			});
		
   	}
}
//1 线下导入客户时候模板下载
function exportExcel_Demo(){
	var _index=layer.load();
	$.ajax({
 	         url : '/wbem/houses/customer/import/import_exportExcelDemo.action',//这个就是请求地址对应sAjaxSource
	         //data : {"data":JSON.stringify({cusId:id,proId:proId,reason:reason})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	          type : 'post',
	          //dataType : 'json',
	          success : function(result) {          
	          	if(result=='success'){
					layer.close(_index);
					window.location.href="/wbem/houses/customer/import/import_exportExcelDemo2.action";	          	
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
	var _index=layer.load();
	$.ajax({
 	         url : '/wbem/houses/customer/export/export_followingCustomer.action',//这个就是请求地址对应sAjaxSource
	         //data : {"data":JSON.stringify({cusId:id,proId:proId,reason:reason})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	          type : 'post',
	          //dataType : 'json',
	          success : function(result) {          
	          	if(result=='success'){
					layer.close(_index);
					window.location.href="/wbem/houses/customer/export/export_followingCustomer2.action";
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

//批量回收
function cstMassReback() {
	var cusList = [];
	var oppList=[];
	var userGuidArray=[];
	var _bool=true;
	$("tr","tbody").each(function(){
		if($("input",this).attr("checked") == "checked") {
			var tag = $("td:last",this);
			var cusId = $("a",tag).attr("cusId");
			//var stat=$("td:eq(4)",this);
			cusList.push(cusId);
			var opp = $("a",tag).attr("oppGUID");
			oppList.push(opp);
			var userGuid = $("a",tag).attr("userGuid");
			userGuidArray.push(userGuid);
			
		}
	});
	if(_bool){
		if(cusList.length == 0)
			layer.alert("请选择客户后再进行分配！");
		else{
		   	$("#recycleReason b").text("您正在对"+cusList.length+"个客户进行批量回收..");
		   	cstReback2(cusList,oppList,userGuidArray);
	   	}
   	}
}
//客户回收
function cstReback(id,tag,oppguid){
	var ids=id.toString();
	if(ids.split(",").length==1){
		$("#recycleReason b").text("该客户将被回收为公共客户，可在公共客户中重新分配置业顾问。");
		var stat=$(tag).parent().parent();
		if($("td:eq(4)",stat).text()=='认购'|| $("td:eq(4)",stat).text()=='签约'){
					layer.alert("认购和签约的客户不能回收");
					return false;
		}
		cstReback2(id,oppguid,"");
	};
}
function cstReback2(id,oppguid,userguid){

	layer.open({
        type: 1,
        title: '客户回收',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['450px' , '250px'],
        btn: ['确定', '取消'],
		btn1: function(index, layero){
		var reason=$("#recycleReason textarea").val();
		reason=encodeURI(reason);
 			if(reason == ""){
				layer.alert("请填写回收原因",function(){
					cstReback(id);
				});
				return false;
			}else{
					//var proId=$("#proId").find('option:selected').val();
					var _index=layer.load();
				 	$.ajax({
			 	         url : '/wbem/houses/customer/reBackCustomer.action',//这个就是请求地址对应sAjaxSource
				          data : {"data":JSON.stringify({cusId:id, userguid:userguid,reason:reason,oppguid:oppguid})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								layer.close(_index);
								var index = parent.layer.getFrameIndex(window.name);
								parent.$('.data-table').DataTable().ajax.reload();
								parent.layer.close(index);
								$("#recycleReason textarea").val('');
				            }else{
				              	layer.alert("该客户为认购或签约客户，不能回收");
				              	layer.close(_index);
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统异常，联系管理员");
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
	var m=tel.indexOf("M");
	var h=tel.indexOf("H");
	var o=tel.indexOf("O");
	var f=tel.indexOf("F");
	// M H O F都存在
	if(m!=-1&&h!=-1&&o!=-1&&f!=-1){
		$("#homeTel").hide();
		$("#officeTel").hide();
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,h-1);
		$("#mobileTelModule").val(mobileTel);
		var homeTel = tel.substring(h+2,o-1);
		$("#homeTelModule").val(homeTel);
		$("#homeTel").show();
		var officeTel = tel.substring(o+2,f-1);
		$("#officeTelModule").val(officeTel);
		$("#officeTel").show();
		var fax = tel.substring(f+2,tel.length);
		$("#faxModule").val(fax);
		$("#fax").show();
	}
	if(m!=-1&&h==-1&&o!=-1&&f!=-1){
		$("#homeTel").hide();
		$("#officeTel").hide();
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,o-1);
		$("#mobileTelModule").val(mobileTel);
		var officeTel = tel.substring(o+2,f-1);
		$("#officeTelModule").val(officeTel);
		$("#officeTel").show();
		var fax = tel.substring(f+2,tel.length);
		$("#faxModule").val(fax);
		$("#fax").show();
	}
	if(m!=-1&&h!=-1&&o==-1&&f!=-1){
		$("#homeTel").hide();
		$("#officeTel").hide();
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,h-1);
		$("#mobileTelModule").val(mobileTel);
		var homeTel = tel.substring(h+2,f-1);
		$("#homeTelModule").val(homeTel);
		$("#homeTel").show();
		var fax = tel.substring(f+2,tel.length);
		$("#faxModule").val(fax);
		$("#fax").show();
	}
	// M O H都存在
	if(m!=-1&&h!=-1&&o!=-1&&f==-1){ 
		$("#homeTel").hide();
		$("#officeTel").hide(); 
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,h-1);
		$("#mobileTelModule").val(mobileTel);
		var homeTel = tel.substring(h+2,o-1);
		$("#homeTelModule").val(homeTel);
		$("#homeTel").show();
		var officeTel = tel.substring(o+2,tel.length);
		$("#officeTelModule").val(officeTel);
		$("#officeTel").show(); 
	}
	//M H存在
	if(m!=-1&&h!=-1&&o==-1&&f==-1){
		$("#homeTel").hide();
		$("#officeTel").hide();
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,h-1);
		$("#mobileTelModule").val(mobileTel);
		var homeTel = tel.substring(h+2,tel.length);
		$("#homeTelModule").val(homeTel);
		$("#homeTel").show();
	}
	//M O存在
	if(m!=-1&&h==-1&&o!=-1&&f==-1){
		$("#homeTel").hide();
		$("#officeTel").hide();
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,o-1);
		$("#mobileTelModule").val(mobileTel);
		var officeTel = tel.substring(o+2,tel.length);
		$("#officeTelModule").val(officeTel);
		$("#officeTel").show();
	} 
	//M F存在
	if(m!=-1&&h==-1&&o==-1&&f!=-1){
		$("#homeTel").hide();
		$("#officeTel").hide();
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,f-1);
		$("#mobileTelModule").val(mobileTel);
		var fax = tel.substring(f+2,tel.length);
		$("#faxModule").val(officeTel);
		$("#fax").show();
	} 
	//只有M
	if(m!=-1&&h==-1&&o==-1&&f==-1){
		$("#homeTel").hide();
		$("#officeTel").hide();
		$("#fax").hide();
		var mobileTel = tel.substring(m+2,tel.length);
		$("#mobileTelModule").val(mobileTel);
	} 
	layer.open({
        type: 1,
        title: '编辑客户',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['450px' , '250px'],
        btn: ['确定', '取消'],
		yes: function(index, layero){ //或者使用btn1
			var newName=$("#editCustInfo-Name").val();
		    var oldName=encodeURI(name);
		    oldOperator=encodeURI(oldOperator);
		    newName=encodeURI(newName); 
		    var m=tel.indexOf("M");
			var h=tel.indexOf("H");
			var o=tel.indexOf("O");
			var f=tel.indexOf("F");
			var data;
			var newMobileTel='';
			var newHomeTel='';
			var newOfficeTel='';
			var newFax='';
			var oldMobileTel='';
			var oldHomeTel='';
			var oldOfficeTel='';
			var oldFax='';
			if(m!=-1&&h!=-1&&o!=-1&&f!=-1){
				 oldMobileTel=tel.substring(m+2,h-1);
		   		 oldHomeTel=tel.substring(h+2,o-1);
		    	 oldOfficeTel=tel.substring(o+2,f-1);
		    	 oldFax=tel.substring(f+2,tel.length);
		    	 newMobileTel = $("#mobileTelModule").val();
		    	 newHomeTel = $("#homeTelModule").val();
		    	 newOfficeTel = $("#officeTelModule").val();
		    	 newFax = $("#faxModule").val();
		    	if(oldName==newName&&oldMobileTel==newMobileTel&&oldHomeTel==newHomeTel&&oldOfficeTel==newOfficeTel&&oldFax==newFax){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
			}
			if(m!=-1&&h==-1&&o!=-1&&f!=-1){
				 oldMobileTel=tel.substring(m+2,o-1);
		    	 oldOfficeTel=tel.substring(o+2,f-1);
		    	 oldFax=tel.substring(f+2,tel.length);
		    	 newMobileTel = $("#mobileTelModule").val();
		    	 newOfficeTel = $("#officeTelModule").val();
		    	 newFax = $("#faxModule").val();
		    	if(oldName==newName&&oldMobileTel==newMobileTel&&oldOfficeTel==newOfficeTel&&oldFax==newFax){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
		    	//newHomeTel = $("#homeTelModule").val();
			}
			if(m!=-1&&h!=-1&&o==-1&&f!=-1){
				 oldMobileTel=tel.substring(m+2,h-1);
		    	 oldHomeTel=tel.substring(h+2,f-1);
		    	 oldFax=tel.substring(f+2,tel.length);
		    	 newMobileTel = $("#mobileTelModule").val();
		    	 newHomeTel = $("#homeTelModule").val();
		    	 newFax = $("#faxModule").val();
		    	if(oldName==newName&&oldMobileTel==newMobileTel&&oldHomeTel==newHomeTel&&oldFax==newFax){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
		    	//newOfficeTel = $("#officeTelModule").val();
			}
			if(m!=-1&&h!=-1&&o!=-1&&f==-1){
				 oldMobileTel=tel.substring(m+2,h-1);
		   		 oldHomeTel=tel.substring(h+2,o-1);
		    	 oldOfficeTel=tel.substring(o+2,tel.length);
		    	 newMobileTel = $("#mobileTelModule").val();
		    	 newHomeTel = $("#homeTelModule").val();
		    	 newOfficeTel = $("#officeTelModule").val();
		    	if(oldName==newName&&oldMobileTel==newMobileTel&&oldHomeTel==newHomeTel&&oldOfficeTel==newOfficeTel){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
		    	//newFax = $("#faxModule").val();
		    }
		    if(m!=-1&&h==-1&&o==-1&&f==-1){
		    	 oldMobileTel=tel.substring(m+2,tel.length);
		    	 newMobileTel = $("#mobileTelModule").val();
		    	if(oldName==newName&&oldMobileTel==newMobileTel){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
		    	 //newHomeTel = $("#homeTelModule").val();
		    	 //newOfficeTel = $("#officeTelModule").val();
		    	 //newFax = $("#faxModule").val();
		    }
		    if(m!=-1&&h!=-1&&o==-1&&f==-1){
		    	 oldMobileTel=tel.substring(m+2,h-1);
		   		 oldHomeTel=tel.substring(h+2,tel.length);
		   		 newMobileTel = $("#mobileTelModule").val();
		    	 newHomeTel = $("#homeTelModule").val();
		    	if(oldName==newName&&oldMobileTel==newMobileTel&&oldHomeTel==newHomeTel){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
		    	 //newOfficeTel = $("#officeTelModule").val();
		    	 //newFax = $("#faxModule").val();
		    }
		   	if(m!=-1&&h==-1&&o!=-1&&f==-1){
		   		 oldMobileTel=tel.substring(m+2,o-1);
		   		 oldOfficeTel=tel.substring(o+2,tel.length);
		   		 newMobileTel = $("#mobileTelModule").val();
		   		 newOfficeTel = $("#officeTelModule").val();
		   		if(oldName==newName&&oldMobileTel==newMobileTel&&oldOfficeTel==newOfficeTel){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
		    	// newHomeTel = $("#homeTelModule").val();
		    	// newFax = $("#faxModule").val();
		    }
		   	if(m!=-1&&h==-1&&o==-1&&f!=-1){
		   		 oldMobileTel=tel.substring(m+2,f-1);
		   		 oldFax=tel.substring(f+2,tel.length);
		   		 newMobileTel = $("#mobileTelModule").val();
		   		 newFax = $("#faxModule").val();
		   		if(oldName==newName&&oldMobileTel==newMobileTel&&oldFax==newFax){
		    		layer.alert("没有修改信息");
		    		return;
		    	}
		    	// newHomeTel = $("#homeTelModule").val();
		    	// newOfficeTel = $("#officeTelModule").val();
		    }
		    //var proId=$("#proId").find('option:selected').val();
		    data={cusId:id,/* proId:proId, */oldName:oldName,newName:newName,newMobileTel:newMobileTel,newHomeTel:newHomeTel,newOfficeTel:newOfficeTel,oldMobileTel:oldMobileTel,oldHomeTel:oldHomeTel,oldOfficeTel:oldOfficeTel,oldOperator:oldOperator,oldFax:oldFax,newFax:newFax};	
		    $.ajax({
			 	      url : '/wbem/houses/customer/updateZsBasicInfoDao.action',//这个就是请求地址对应sAjaxSource
				      data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				      type : 'post',
				      dataType : 'json',
				      success : function(result) {          
				          if(result !=0){
							$('.data-table').DataTable().ajax.reload();
							//layer.alert("保存成功");
							layer.close(index);
						  }else{
				            layer.alert("编辑失败");
				          }
				          //setTimeout("window.location.reload()",1500);
				      },
				      error : function(msg){
				         layer.alert("系统异常，联系管理员");
				         //setTimeout("window.location.reload()",1500);
				       }
				    });
		},
		cancel: function(index){ //或者使用btn2
			//window.location.reload();
		},
        content: $("#editCustInfo")
    });
    $("#editCustInfo-Tel").focus();
}

//查看客户
function cstDetail(cusid,cusType){

	layer.open({
        type: 2,
        title: '查看客户',
        maxmin: true,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['800px' , '620px'],
        content: "/wbem/houses/customer/dailog/custDetail.action?cusid=" + cusid + "&cstType=" + cusType,//+"&proId="+$("#proId").find('option:selected').val(),
    });
}

//批量移到垃圾箱
function cstMassMoveToDustbin() {
	var cusList = [];
	var oppList=[];
	$("tr","tbody").each(function(){
		if($("input",this).attr("checked") == "checked") {
			var tag = $("td:last",this);
			var cusId = $("a",tag).attr("cusId");
			cusList.push(cusId);
			var opp = $("a",tag).attr("oppGUID");
			oppList.push(opp);
			
		}
	});
	if(cusList.length == 0)
		layer.alert("请选择客户后再进行分配！");
	else{
	   	moveToDustbin(cusList,oppList);
   	}
}
//(公共客户)移至拉圾箱
function moveToDustbin(id,oppguid){
	layer.confirm('确定要将选择的客户移到垃圾箱么？', {
		btn: ['确定','取消'],
		yes: function(index, layero){ //或者使用btn1
		    //var proId=$("#proId").find('option:selected').val();
		    var _index=layer.load();
				 	$.ajax({
			 	         url : '/wbem/houses/customer/dusbinCustomers.action',//这个就是请求地址对应sAjaxSource
				          data : {"data":JSON.stringify({cusId:id,/* proId:proId, */reason:"",oppguid:oppguid})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result!=0){
								layer.close(_index);
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("移至垃圾箱失败");
				              	layer.close(_index);
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统异常，联系管理员");
				          }
			     	}); 
		},
		cancel: function(index){ //或者使用btn2
		   // alert("2");
		},
	});
}
function cstMassMoveToPublic(){
	var cusList = [];
	var oppList=[];
	var userGuidArray=[];
	$("tr","tbody").each(function(){
		if($("input",this).attr("checked") == "checked") {
			var tag = $("td:last",this);
			var cusId = $("a",tag).attr("cusId");
			cusList.push(cusId);
			var opp = $("a",tag).attr("oppGUID");
			oppList.push(opp);
			var userguid = $("a",tag).attr("userGuid");
			userGuidArray.push(userguid);
		}
	});
	if(cusList.length == 0)
		layer.alert("请选择客户后再进行分配！");
	else{
	   	moveToPublic(cusList,oppList,userGuidArray);
   	}
}
/* 从垃圾箱回收 */
function moveToPublic(id,oppguid,userguid){
	layer.confirm('确定要将选择的客户转为公共客户吗？', {
		btn: ['确定','取消'],
		yes: function(index, layero){ //或者使用btn1
		      //var proId=$("#proId").find('option:selected').val();
		      var _index=layer.load();
				 	$.ajax({
			 	         url : '/wbem/houses/customer/reBackCustomer.action',//这个就是请求地址对应sAjaxSource
				          data : {"data":JSON.stringify({cusId:id, userguid:userguid, reason:"",oppguid:oppguid})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								//alert("成功回收");
								layer.close(_index);
								$('.data-table').DataTable().ajax.reload();
								layer.close(index);
				            }else{
				              	layer.alert("签约和认购客户不能回收");
				              	layer.close(_index);
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统异常，联系管理员");
				          }
			     	}); 
		},
		cancel: function(index){ //或者使用btn2
		   
		},
	});
}
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