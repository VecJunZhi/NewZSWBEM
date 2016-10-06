<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="widget-box">
	<div class="widget-title">
		<span class="icon"><i class="icon-th"></i></span><h5>�����ͻ�</h5>
		<span class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
			<input type="text" style="margin-bottom:0" id="userName" placeholder="�û���">
			<button class="btn btn-info" id="tableSearch">��ѯ</button>
			<button class="btn btn-success" id="createMember">ȷ�����</button>
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
					<th>����</th>
					<th>����id</th>
					<th>id</th>
	 			</tr>  
			</thead>
		</table>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script type="text/javascript">
	var tid='${teamGroupId}';
	var isProjectAdmin='${isProjectAdmin}';
	var property='${property}';
	var table=$('.data-table').DataTable({
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
			url: "/wbem/system/sysSet/getConnectedUsers.action",
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
			{"data":"userLevel"},
			{"data":"userLevelId"},
			{"data":"id"}
		],
		 columnDefs:[
			 {
	         	targets:4,"render":function(data, type, row, meta){
	         		//var idk=row.id;
			         		if(isProjectAdmin=='1'){//��Ŀ����
								return  '<input type="radio" name='+row.id+'  value="0" checked>����';
							}else {
								return  '<input type="radio" name='+row.id+'   value="1" >�鳤'+
										'<input type="radio" name='+row.id+'  value="2" checked="checked">��ҵ����';
							}
	         	}
	         }, 
	         {
				"targets": [1,5,6],
				"visible": false,
	         } 
        ]
	});
	$('select').select2();
$(document).ready(function(){
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	//ȷ��������Ա
	$("#createMember").click(function(){
		var userLevel=[];
	    var userId=[];
	    var i=0;//����ѡ�м���
	    var flg=true;//�жϱ�־
		$("tr","tbody").each(function(){
			if($("input:checkbox",this).attr("checked") == "checked") {
				i++;
				var _check=$("input:radio:checked",this).val();
				if(_check==undefined){
					layer.alert("ѡ�к��û�����");
					flg=false;
				}
				var userLevelId = _check;
				var uId = table.row(this).data().userId;
				userLevel.push(userLevelId);
		        userId.push(uId);
			}
		});
		if(i == 0){
			layer.alert("��ѡ��ͻ���");
		}else if(flg) {
			createMember(userId, userLevel);
		}
	});
	function createMember(userId,userLevel){
			$.ajax({
			        data: { "userId": userId.toString(), "userLevelId": userLevel.toString(),"teamGroupId":tid},
			        type: 'post',
			        dataType: 'json',
			        url: '/wbem/system/sysSet/insertUserToTeamGroup.action',
			        success: function (data) {
			            if(data ==true){
			            	layer.alert("�����ɹ�");
			            	table.draw();
		            		var index = parent.layer.getFrameIndex(window.name);
							parent.$('.data-table').DataTable().ajax.reload();
							parent.layer.close(index);
			            }else if(data==false){
			            	layer.alert("�����������");
			            }else if(data.length !=undefined && data.length!=0  ){
				            var names='';
				             for (var int2 = 0; int2 < data.length; int2++) {
								names+=data[int2].realName+",";
							 }
							layer.alert(names.substring(0, names.length-1)+" �Ѿ��ڸ���,�����ظ����");
			            }
			        }
			}); 
	}
});

</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>
