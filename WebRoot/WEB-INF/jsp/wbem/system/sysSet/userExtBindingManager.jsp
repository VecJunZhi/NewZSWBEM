<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12">
      <div class="widget-box">
		<div class="widget-title">
			<span class="icon"><i class="icon-th"></i></span><h5>已经绑定成员列表</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<input type="text" style="margin-bottom:0" id="userName" placeholder="用户名">
				<button class="btn btn-info" id="tableSearch">查询</button>
				<button type="button" class="btn btn-info" id="resetSearch">撤销筛选</button>
				<button type="button" class="btn btn-success"  id="createUser">绑定客户</button>
				<!-- <button type="button" class="btn btn-success" id="cancleBinding">批量解除绑定</button> -->
			</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr> 
						<th>序号</th>
						<th>UserId</th>
						<th>用户名</th>
						<th>姓名</th>
						<th>userlevel</th>
						<th>uerLevelId</th>
						<th>联系电话</th>
						<th>系统</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
    </div>
  </div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>

<script type="text/javascript">
var table=null;
	//解除绑定
	function cancleBingingUser(ids){
		layer.confirm('确定要解除绑定吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(index){
				  $.ajax({
			        data: { "id": ids},
			        type: 'post',
			        dataType: 'json',
			        url: '/wbem/system/sysSet/deleteUserConnect.action',
			        success: function (data) {
			            if(data ==true){
			            	table.draw();
			            	layer.close(index);
			            }else {
			            	layer.alert("解绑失败");
			            }
			        }
				});
			}, function(){
			  
		});
		
	}
$(document).ready(function(){
	 table=$('.data-table').DataTable({
		"ajax": {
			type: "post",//后台指定了方式。
			url: "/wbem/system/sysSet/getConnectedUsers.action",
			data:function(d){			//外部参数传递
	           	d.userName = encodeURI($("#userName").val());
	           	d.property='';
	        }
		},
		"columns":[
			{"data":"index"},
			{"data":"userId"},
			{"data":"userName"},
			{"data":"realName"},
			{"data":"userLevel"},
			{"data":"userLevelId"},
			{"data":"mobile"},
			{"data":"groupType","render":function(data,type,row,meta){
				if(data=='xs_userGuid'){
					return '销售绑定';
				}else {
					return "招商绑定";
				}
			}},
			{"data":"id"}
		] ,
		columnDefs:[
			{
	         	targets:8,"render":function(data, type, row, meta){
	         		//var idk=row.id;
	       			return  "<a style='color:#FAA732' href='#' onclick='cancleBingingUser(\"" + data + "\")' >解除绑定</a>&nbsp;";
	         	}
	         },
	          {
				"targets": [1,5,4],
				"visible": false,
	         } 
        ]
	});
	$('select').select2();
	//查询
	$("#tableSearch").click(function(){
		table.ajax.reload();
	});
	$("#resetSearch").click(function(){
		$("#userName").val('');
		table.draw();
	});
//绑定用户按钮事件
	$("#createUser").on('click',function(){
			 layer.open({
			    type: 2,
			    title: '新增用户绑定',
			    skin: 'layui-layer-rim', //加上边框
			    area: ['1000px', '700px'], //宽高
			    closeBtn: 1,
			    content: '/wbem/system/sysSet/bindingUser.action'
			}); 
			
	});
	//批量解除绑定--暂时不用
	$("#cancleBinding").click(function(){
		var ids = [];
		$("tr","tbody").each(function(){
			if($("input",this).attr("checked") == "checked") {
			
				var id = table.row(this).data().id;
				ids.push(id);
			}
		});
		if(ids.length == 0){
			layer.alert("请选择客户！");
		}else {
			cancleBingingUser(ids);
		}
	});
});

</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>

