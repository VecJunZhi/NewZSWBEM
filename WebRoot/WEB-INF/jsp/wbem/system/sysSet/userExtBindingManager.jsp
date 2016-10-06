<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12">
      <div class="widget-box">
		<div class="widget-title">
			<span class="icon"><i class="icon-th"></i></span><h5>�Ѿ��󶨳�Ա�б�</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<input type="text" style="margin-bottom:0" id="userName" placeholder="�û���">
				<button class="btn btn-info" id="tableSearch">��ѯ</button>
				<button type="button" class="btn btn-info" id="resetSearch">����ɸѡ</button>
				<button type="button" class="btn btn-success"  id="createUser">�󶨿ͻ�</button>
				<!-- <button type="button" class="btn btn-success" id="cancleBinding">���������</button> -->
			</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr> 
						<th>���</th>
						<th>UserId</th>
						<th>�û���</th>
						<th>����</th>
						<th>userlevel</th>
						<th>uerLevelId</th>
						<th>��ϵ�绰</th>
						<th>ϵͳ</th>
						<th>����</th>
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
	//�����
	function cancleBingingUser(ids){
		layer.confirm('ȷ��Ҫ�������', {
			  btn: ['ȷ��','ȡ��'] //��ť
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
			            	layer.alert("���ʧ��");
			            }
			        }
				});
			}, function(){
			  
		});
		
	}
$(document).ready(function(){
	 table=$('.data-table').DataTable({
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
			url: "/wbem/system/sysSet/getConnectedUsers.action",
			data:function(d){			//�ⲿ��������
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
					return '���۰�';
				}else {
					return "���̰�";
				}
			}},
			{"data":"id"}
		] ,
		columnDefs:[
			{
	         	targets:8,"render":function(data, type, row, meta){
	         		//var idk=row.id;
	       			return  "<a style='color:#FAA732' href='#' onclick='cancleBingingUser(\"" + data + "\")' >�����</a>&nbsp;";
	         	}
	         },
	          {
				"targets": [1,5,4],
				"visible": false,
	         } 
        ]
	});
	$('select').select2();
	//��ѯ
	$("#tableSearch").click(function(){
		table.ajax.reload();
	});
	$("#resetSearch").click(function(){
		$("#userName").val('');
		table.draw();
	});
//���û���ť�¼�
	$("#createUser").on('click',function(){
			 layer.open({
			    type: 2,
			    title: '�����û���',
			    skin: 'layui-layer-rim', //���ϱ߿�
			    area: ['1000px', '700px'], //���
			    closeBtn: 1,
			    content: '/wbem/system/sysSet/bindingUser.action'
			}); 
			
	});
	//���������--��ʱ����
	$("#cancleBinding").click(function(){
		var ids = [];
		$("tr","tbody").each(function(){
			if($("input",this).attr("checked") == "checked") {
			
				var id = table.row(this).data().id;
				ids.push(id);
			}
		});
		if(ids.length == 0){
			layer.alert("��ѡ��ͻ���");
		}else {
			cancleBingingUser(ids);
		}
	});
});

</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>

