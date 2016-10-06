<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="widget-box">
	<div class="widget-title">
		<span class="icon"><i class="icon-th"></i></span><h5>�󶨿ͻ�</h5>
		<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
		<input type="radio" name="sysradion" id="sysradion1"  checked="checked"><span style="color: #2F96B4">������</span>
		<input type="radio" name="sysradion" id="sysradion2" ><span style="color: #2F96B4">������</span>
			<input type="text" style="margin-bottom:0" id="userName" placeholder="�û���">
			<button class="btn btn-info" id="tableSearch">��ѯ</button>
			<button class="btn btn-success" id="bingdingUser">��</button>
		</span>
	</div>
	<div class="widget-content nopadding">
		<table id="example" class="table table-bordered data-table">
			<thead>
				<tr>				
					<th>���</th>
					<th>userId</th>
					<th>�û���</th>
					<th>����</th>
					<th>�ֻ���</th>
	 			</tr>  
			</thead>
		</table>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp"%>
<%@include file="/wbem/include/include_tables_js.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	/* dt��ʼ����Ϊ�ϰ棨1.9-��д��Ϊ$("#tableid").dataTable();�������ص���jquery�����°棨1.10.+��
	��д����$("#tableid").DataTable();������ص���apiʵ���������ʹ�ù����г���ĳĳ���������ڲ�֧��֮��ģ�
	һ�㶼���������õ�һ�ַ�����ʼ��dt���÷��صĶ���ȥ����api�ķ��������Ա����� */
	var property='xs_userGuid';
	var table=$('.data-table').DataTable({
			"ajax": {
				type: "post",//��ָ̨���˷�ʽ��
				url: "/wbem/system/sysSet/getPreConnectUsers.action",
				data:function(d){			//�ⲿ��������
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
	//��ѯ
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
	//��
	$("#bingdingUser").click(function(){
		var ids = [];
		$("tr","tbody").each(function(){
			if($("input",this).attr("checked") == "checked") {
			
				var id = table.row(this).data().userId;
				ids.push(id);
			}
		});
		if(ids.length == 0){
			layer.alert("��ѡ��ͻ���");
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
		            	layer.alert("�����ɹ�");
		            	table.draw();
		            	var index = parent.layer.getFrameIndex(window.name);
						parent.$('.data-table').DataTable().ajax.reload();
						parent.layer.close(index);
		            }else{
		            	layer.alert("������CRM��û�� "+data.data+",�޷����а�");
		            }
		        }
		}); 
	}
	//��̬����select����           
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
//��̬����select����(�����ĳ�ʼ������)
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
	                    for (var i = 0; i < length; i++){   //ѭ��option
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
