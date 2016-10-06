<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="widget-box">
	<div class="widget-title">
		<span class="icon"><i class="icon-th"></i></span><h5>新增客户</h5>
		<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
			<input type="text" style="margin-bottom:0" id="userName" placeholder="用户名">
			<button class="btn btn-info" id="tableSearch">查询</button>
			<button class="btn btn-success" id="createMember">确认添加</button>
		</span>
	</div>
	<div class="widget-content nopadding">
		<table id="example" class="table table-bordered data-table">
			<thead>
				<tr>				
					<th>序号</th>
					<th>姓名</th>
					<th>手机号码</th>
					<th>用户名</th>
					<th>Email</th>
					<th>用户状态</th>
					<th>锁定状态</th>
					<th>USERID</th>
	 			</tr>  
			</thead>
		</table>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script type="text/javascript">
	var roleID='${roleID}';
	var table=$('.data-table').DataTable({
		"ajax": {
			type: "post",//后台指定了方式。
			url: "/wbem/system/sysSet/getUser.action",
			data:function(d){			//外部参数传递
	           	d.userName = encodeURI($("#userName").val());
	        }
		},
		"columns":[
			{"data":"index","render":function(data,type,row,meta){
				return data+"&nbsp;<input type='checkbox' style='margin-top: 0px;' name='radions'>";
			}},
			{"data":"realName"},
			{"data":"mobile"},
			{"data":"userName"},
			{"data":"email"},
			{"data":"uStatus","render":function(data, type, row, meta){
					if(data==1){
						return '启用';
					}else if(data==0){
						return '禁用';
					}
			}},
			{"data":"locked","render":function(data, type, row, meta){
					if(data=="true"){
						return '锁定';
					}else if(data=="false") {
						return '未锁定';
					}
			}},
			{"data":"userID"}
		],
		 columnDefs:[
	         /* {
				"targets": [1,5,6],
				"visible": false,
	         }  */
        ]
	});
	$('select').select2();
$(document).ready(function(){
	//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	//确认新增成员
	$("#createMember").click(function(){
	    var userId=[];
	    var i=0;//用于选中计数
	    var flg=true;//判断标志
		$("tr","tbody").each(function(){
			if($("input:checkbox",this).attr("checked") == "checked") {
				i++;
				var uId = table.row(this).data().userID;
		        userId.push(uId);
			}
		});
		if(i == 0){
			layer.alert("请选择人员！");
		}else if(flg) {
			createMember(userId, roleID);
		}
	});
	function createMember(userId,roleID){
			$.ajax({
			        data: { "userID": userId.toString(), "roleID": roleID},
			        type: 'post',
			        dataType: 'json',
			        url: '/wbem/system/sysSet/correUser_Role.action',
			        success: function (data) {
			            if(data =='1'){
			            	table.draw();
		            		var index = parent.layer.getFrameIndex(window.name);
							parent.$('.data-table').DataTable().ajax.reload();
							parent.layer.close(index);
			            }else if(data=='0'){
			            	layer.alert("批量插入出错");
			            }else{
				            var names=data.data;
							layer.alert(names+" 已经在该组,不能重复添加");
			            }
			        }
			}); 
	}
});

</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>
