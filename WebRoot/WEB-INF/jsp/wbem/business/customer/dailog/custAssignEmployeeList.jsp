<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
     <div class="widget-box">
       <div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>您正在对${cusNum}个客户进行分配...</h5>
		<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
			<input type="text"  style="margin-bottom:0" id="telOrName" placeholder="姓名/手机">
               <button class="btn btn-success" id="tableSearch">查询</button>
		</span>
	</div>
       <div class="widget-content nopadding">
         <table id="example" class="table table-bordered data-table">
           <thead>
             <tr>				
			<th>序号</th>
			<th>姓名</th>
			<th>手机</th>
			<th>身份</th>
			<th>操作</th>
		  </tr>  
           </thead>
           <tbody> 	 
           </tbody>
         </table>
       </div>
     </div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script>
var index = parent.layer.getFrameIndex(window.name);
var cusId,proId;	
function cstAssign(id){
	$.ajax({
          url : '/wbem/business/customer/allocateCustomer.action',//这个就是请求地址对应sAjaxSource
          data : {"data":JSON.stringify({employeeId:id,cusId:cusId/* ,progGuid:proId */})},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
          type : 'post',
          dataType : 'json',
          success : function(result) {          
          	if(result==1){
				var index = parent.layer.getFrameIndex(window.name);
				parent.$('.data-table').DataTable().ajax.reload();
				parent.layer.close(index);
            }else{
              	layer.alert("分配失败");
            }
          },
          error : function(msg) {
          	alert("error");
          }
     });
}


$(document).ready(function(){
	//table 
	cusId=${cusId};
	//proId=${proId};
	$('.data-table').dataTable({
		"ajax": {
			type: "post",//后台指定了方式。
			url: "/wbem/business/customer/getEmployeeList.action",
			data:function(d){			//外部参数传递
	           	d.telOrName = encodeURI($("#telOrName").val());
	        }
		},
		"columns":[
			{"data":"index"},
			{"data":"userName"},
			{"data":"mobile"},
			{"data":"duty"},
			{"data":"operate"}
		],
	});
	
	$("select").select2();
		
	//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	
	$('#example tbody').on('mouseenter', 'tr', function () {
		$(this).attr("style","background-color:#CCCCCC");
		var tag = $("td:first",this);
		var employeeId = $("input",tag).val();
		$("td:last",this).append("<a style='color:#FAA732' href='#' onclick='cstAssign("+employeeId+")'>分配</a>");
	});
	
	$('#example tbody').on('mouseleave', 'tr', function () {
		$("td:last",this).empty();
		$(this).attr("style","background-color:#F9F9F9");
		
	});
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>