<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="widget-box">
	<div class="widget-title">
		<span class="icon"><i class="icon-th"></i></span><h5>绑定客户</h5>
		<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
		<input type="radio" name="sysradion" id="sysradion1"  checked="checked"><span style="color: #2F96B4">绑定销售</span>
		<input type="radio" name="sysradion" id="sysradion2" ><span style="color: #2F96B4">绑定招商</span>
			<input type="text" style="margin-bottom:0" id="userName" placeholder="用户名">
			<button class="btn btn-info" id="tableSearch">查询</button>
			<button class="btn btn-success" id="bingdingUser">绑定</button>
		</span>
	</div>
	<div class="widget-content nopadding">
		<table id="example" class="table table-bordered data-table">
			<thead>
				<tr>				
					<th>序号</th>
					<th>userId</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>手机号</th>
	 			</tr>  
			</thead>
		</table>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp"%>
<%@include file="/wbem/include/include_tables_js.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	/* dt初始化分为老版（1.9-）写法为$("#tableid").dataTable();这样返回的是jquery对象，新版（1.10.+）
	的写法是$("#tableid").DataTable();这个返回的是api实例，如果在使用过程中出现某某方法不存在不支持之类的，
	一般都是由于是用第一种方法初始化dt，用返回的对象去调用api的方法，所以报错误。 */
	var property='xs_userGuid';
	var table=$('.data-table').DataTable({
			"ajax": {
				type: "post",//后台指定了方式。
				url: "/wbem/system/sysSet/getPreConnectUsers.action",
				data:function(d){			//外部参数传递
		           	d.userName = encodeURI($("#userName").val());
		           	d.property=property;
		        }
			},
			"columns":[
				{"data":"index"},
				{"data":"userId"},
				{"data":"userName"},
				{"data":"realName"},
				{"data":"mobile"}
			],
		columnDefs:[
	          {
				"targets": [1],
				"visible": false,
	         } 
        ]
	});
	$('select').select2();
	//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	 $(':radio[name=sysradion]').on( 'click', function(){
   	 	if($("#sysradion1").prop('checked')){
   	 		property='xs_userGuid';
   	 	}else if($("#sysradion2").prop('checked')){
   	 		property='bs_userGuid';
   	 	}
   	 	table.draw();
	} ); 
	//绑定
	$("#bingdingUser").click(function(){
		var ids = [];
		$("tr","tbody").each(function(){
			if($("input",this).attr("checked") == "checked") {
			
				var id = table.row(this).data().userId;
				ids.push(id);
			}
		});
		if(ids.length == 0){
			layer.alert("请选择客户！");
		}else {
			bindingUser(ids);
		}
	});
	function bindingUser(ids){
    	$.ajax({
		        data: { "userId": ids.toString(),property:property},
		        type: 'post',
		        dataType: 'json',
		        url: '/wbem/system/sysSet/insertUserConnectExt.action',
		        success: function (data) {
		            if(data ==true){
		            	layer.alert("操作成功");
		            	table.draw();
		            	var index = parent.layer.getFrameIndex(window.name);
						parent.$('.data-table').DataTable().ajax.reload();
						parent.layer.close(index);
		            }else{
		            	layer.alert("在销售CRM中没有 "+data.data+",无法进行绑定");
		            }
		        }
		}); 
	}
	//动态生成select内容           
	function gettypes(){
			var str="";
			$.ajax({
				type:"post",
				async:false,
				url:"/wbem/system/sysSet/getProjectItem.action",
				success:function(data){
					if (data != null) {
						var length=data.length;
						for(var i=0;i<length;i++){ 
							if(i!=length-1)
							str+=data[i].id+":"+data[i].name+";";
							else{
							str+=data[i].id+":"+data[i].name;
							}
						} 
					}
				}
			}); 
		return str;
	}
//动态生成select内容(级联的初始化数据)
	function getgrouptypes(){
			var str = "";
	        $.ajax({
	            url: '/wbem/system/sysSet/getTeamGroupAllFromProjectInit.action',
	            type:"post",
	            async: false,
	            cache: false,
	            dataType: "json",
	            success: function(data) {
	                 if (data != null) {
	                    var length = data.length;
	                    for (var i = 0; i < length; i++){   //循环option
	                        if(i!=length-1){
								str+=data[i].id+":"+data[i].name+";";
							}else{
								str+=data[i].id+":"+data[i].name;
							}
	                    }
	                  }
	             }
	        });
	 	return str;
	}
	
});

</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>
