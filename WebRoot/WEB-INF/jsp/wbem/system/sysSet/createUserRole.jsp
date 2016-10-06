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
					<th>����</th>
					<th>�ֻ�����</th>
					<th>�û���</th>
					<th>Email</th>
					<th>�û�״̬</th>
					<th>����״̬</th>
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
			type: "post",//��ָ̨���˷�ʽ��
			url: "/wbem/system/sysSet/getUser.action",
			data:function(d){			//�ⲿ��������
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
						return '����';
					}else if(data==0){
						return '����';
					}
			}},
			{"data":"locked","render":function(data, type, row, meta){
					if(data=="true"){
						return '����';
					}else if(data=="false") {
						return 'δ����';
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
	//��ѯ
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	//ȷ��������Ա
	$("#createMember").click(function(){
	    var userId=[];
	    var i=0;//����ѡ�м���
	    var flg=true;//�жϱ�־
		$("tr","tbody").each(function(){
			if($("input:checkbox",this).attr("checked") == "checked") {
				i++;
				var uId = table.row(this).data().userID;
		        userId.push(uId);
			}
		});
		if(i == 0){
			layer.alert("��ѡ����Ա��");
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
			            	layer.alert("�����������");
			            }else{
				            var names=data.data;
							layer.alert(names+" �Ѿ��ڸ���,�����ظ����");
			            }
			        }
			}); 
	}
});

</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>
