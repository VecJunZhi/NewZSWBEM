<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<link rel="stylesheet" href="/common/css/ztree/zTreeStyle.css" />
<style>
	.line {
	    height: auto;}
	a {
	    color: #fff;
	    text-decoration: initial;}
	a:hover{background-color: #fff;
	    text-decoration: initial;}
	body{margin-top:-10px;}
	#footer{font-family: "Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","WenQuanYi Micro Hei",sans-serif;
	    font-size: 12px;
	    color: #666;
	}
	#footer a{ color: #666;}
	#footer a:hover{color:#f60; background-color:#2f332a}
	.layui-layer-btn a:hover{
		color: #000;
	}
</style>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span3">
	    <div class="widget-box">
			<div class="widget-title">
				<span class="icon"><i class="icon-th"></i></span><h5>����ѡ��</h5>
				<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
					<button type="button" class="btn btn-success"  id="createTeamGroup">����</button>
					<button type="button" class="btn btn-success"  id="updateTeamGroup">�޸�</button>
					<button type="button" class="btn btn-success"  id="deleteTeamGroup">ɾ��</button>
				</div>
	        </div>
			<div class="widget-content nopadding">
				<form action="#" method="get" class="form-horizontal" >
				<ul id="commonTree" class="ztree" ></ul>
				</form>
			</div>
		</div>
    </div>
    <div class="span9">
	   <!-- <div class="widget-box">
				<div class="widget-title">
					<input type="text" style="margin-bottom:0" id="userName_s" placeholder="����">
					<button class="btn btn-success" id="tableSearch">��ѯ</button>
					<button type="button" class="btn btn-success" id="resetSearch">����ɸѡ</button>
		        </div>
	  </div> -->
      <div class="widget-box">
		<div class="widget-title">
			<span class="icon"><i class="icon-th"></i></span><h5>ͳ���б�</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<input type="text" style="margin-bottom:0" id="userName_s" placeholder="����">
				<button class="btn btn-info" id="tableSearch">��ѯ</button>
				<button type="button" class="btn btn-info" id="resetSearch">����ɸѡ</button>
				<button type="button" class="btn btn-success"  id="createUser">������Ա</button>
				<!-- <button type="button" class="btn btn-success" id="updateUser">�޸ĳ�Ա</button>
				<button type="button" class="btn btn-success" id="deleteUser">ɾ����Ա</button> -->
			</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr> 
						<th>���</th>
						<th hidden="hidden">userTeamId</th>
						<th>����</th>
						<th hidden="hidden">��Ŀid</th>
						<th>����</th>
						<th>����</th>
						<th>����</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
    </div>
  </div>
</div>

<div id="editCustInfo" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label">���� :</label>
	       <div class="controls">
	         <input type="text" id="userName" class="span2" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">��Ŀ :</label>
	       <div class="controls">
	         <select id="proId" class="span2" name="exceptSelect">
	              <c:forEach items="${projectList}" var="project" varStatus="status">
              		<option value="${project.id}">${project.name}</option>
              	  </c:forEach>
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">���� :</label>
	       <div class="controls">
	         <select id="groupId" class="span2" name="exceptSelect">
	              
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="tuanDuiDiv">
	       <label class="control-label">�Ŷ� :</label>
	       <div class="controls">
	         <select id="teamsId" class="span2" name="exceptSelect">
	              
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">���� :</label>
	       <div class="controls">
	         	<span id="radioZero"><input type="radio" name="radion" id="zero" value="0" style="margin-top: 0px;">����</span>
				<span id="radioOne"><input type="radio" name="radion" id="one"  value="1"  style="margin-top: 0px;">�鳤</span>
				<span id="radioTwo"><input type="radio" name="radion" id="two"  value="2"  style="margin-top: 0px;">��ҵ����</span>
	       </div>
	     </div>
     </form>
</div>
<div id="teamGroupDia" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label">��Ŀ����</label>
	       <div class="controls">
	         <input type="text" id="projectName" class="span2" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="systemDisplay">
	       <label class="control-label">����ϵͳ</label>
	       <div class="controls">
	         <select id="systemSel" class="span2" name="exceptSelect">
	              <option value="xs">����ϵͳ</option>
	              <option value="zs">����ϵͳ</option>
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="teamGroupInput"> <!-- noborder-bottom -->
	       <label class="control-label" id="nameChange">�Ŷ�����</label>
	       <div class="controls">
	         <input type="text" id="teamGroupName" class="span2" value="" />
	       </div>
	     </div>
     </form>
</div>

<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script type="text/javascript" src="/common/js/ztree/jquery.ztree.core-3.5.min.js" ></script>
<script type="text/javascript">
	 var setting = {
		view: {
			selectedMulti: false
		},
		async: {
			enable: true,
			url:"/wbem/system/sysSet/getTeamGroupTree.action",
			type:"post",
			autoParam:["parId=parentID","isProjectAdmin","groupType"],
		},
		callback: {
			onAsyncSuccess: onAsyncSuccess,
			onClick: zTreeOnClick
		},
		data:{
			simpleData:{
				enable:true
			}
		}
		
	}; 
	function beforeClick(treeId, treeNode, clickFlag){
	}
	var table=null;
	var teamGroupId='';
	var projectId='';
	var isProjectAdmin=null;
	var teamName=null;	
	var projectName=null;
	var groupType=null;
	function zTreeOnClick(event, treeId, treeNode) {
		teamGroupId=treeNode.id;
		projectId=treeNode.projectId;
		isProjectAdmin=treeNode.isProjectAdmin;
		teamName=treeNode.name;
		projectName=treeNode.projectName;
		groupType=treeNode.groupType;
		if(projectId==null){//���������Ŀ,����ȡ��Ա
			$("#createTeamGroup").attr("disabled",false);
			$("#createTeamGroup").text("����ϵͳ");
			$("#createTeamGroup").show();
			$("#updateTeamGroup").hide();
			$("#deleteTeamGroup").hide();
			return;
		}
		/* if(isProjectAdmin=='1'){//�������Ŀ����
			$("#radioOne").hide();
			$("#radioTwo").hide();
			$("#zero").attr("checked",true);
		} */
		if(projectId !='' && projectId !=null && isProjectAdmin==undefined){//����ľ�����Ŷӣ����������¼���� --��Ӱ�ťֵΪ����
			$("#createTeamGroup").attr("disabled",true);
			$("#createTeamGroup").hide();
			$("#updateTeamGroup").show();
			$("#deleteTeamGroup").show();
		}else if(isProjectAdmin=='1'){//�������Ŀ����
			$("#createTeamGroup").attr("disabled",false);
			$("#createTeamGroup").text("������");
			$("#createTeamGroup").show();
			$("#updateTeamGroup").show();
			$("#deleteTeamGroup").show();
		}
		if(table==null){
			table=$('.data-table').DataTable({
				"ajax": {
					type: "post",//��ָ̨���˷�ʽ��
		         	url: "/wbem/system/sysSet/getTeamGroupMember.action",
					data:function(d){			//�ⲿ��������
						d.teamGroupId=teamGroupId;
			         	d.projectId = projectId;
			         	d.userName = encodeURI($('#userName_s').val());
			         	d.property=groupType;
			        }
		         },
		         "columns":[
		         	{"data":"index"},
		         	{"data":"userTeamId"},
		         	{"data":"userName"},
		         	{"data":"projectId"},
		         	{"data":"groupName"},
		         	{"data":"userLevelId","render":function(data, type, row, meta){
		         			if(data=='0'){
		         				return "����";
		         			}else if(data=='1'){
		         				return "�鳤";
		         			}else{
		         				return "��ҵ����";
		         			} 
		         	}},
		         	{"data":"id"}
		         	
		         ],
		         columnDefs:[
					 {
			         	targets:6,"render":function(data, type, row, meta){
			       			return  "<a style='color:#FAA732' href='#' onclick='editUserMember(\"" + data + "\",\"" + row.userName + "\",\"" + row.projectId + "\",\"" + row.userLevelId + "\",\"" + row.groupName + "\")'>�༭</a>&nbsp;"+
       								"<a style='color:#FAA732' href='#' onclick='deleteUser(\"" + data + "\")' >ɾ��</a>&nbsp;";
			         	}
			         }, 
			          {
						"targets": [1,3],
						"visible": false,
			         } 
        ]
			});
			$('select[name!="exceptSelect"]').select2();
		}else{
			teamGroupId=treeNode.id;
			projectId=treeNode.projectId;
			$('.data-table').DataTable().ajax.reload();	
		}
	};
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
	};
	function filter(treeId, parentNode, childNodes) {
		return childNodes;
	}
	$(":radio[name=radion]").on("click",function(){
		if($("#zero").prop("checked")){
			$("#tuanDuiDiv").hide();
		}else {
			$("#tuanDuiDiv").show();
		}
	});	
//�༭���Ա
	function editUserMember(id,name,projectId,userLevelId,groupName){
		$("#userName").val(name).attr("disabled",true);
		if(userLevelId=='0'){
			$("#zero").prop("checked",true);
			$("#tuanDuiDiv").hide();
		}else if(userLevelId=='1'){
			$("#one").prop("checked",true);
			$("#tuanDuiDiv").show();
		}else if(userLevelId=='2' || userLevelId=='3'){
			$("#two").prop("checked",true);
			$("#tuanDuiDiv").show();
		}
		$("#proId").children('option').each(function(index){
			if($(this).val()==projectId){
				$(this).attr("selected", "selected");
				getTeamGroupFormPorject(projectId,'1','groupId','');
			};
		});
		$("#groupId").children('option').each(function(index){
			if($(this).val()==groupType){
				$(this).attr("selected", "selected");
				getTeamGroupFormPorject(projectId,'0','teamsId',groupType);
			};
		});
		$("#teamsId").children('option').each(function(index){
			if($(this).text()==groupName){
				$(this).attr("selected", "selected");
			};
		});
		layer.open({
	        type: 1,
	        title: '�༭�ͻ�',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['400px' , '360px'],
	        btn: ['ȷ��', 'ȡ��'],
	        //closeBtn: 1,
			yes: function(index, layero){ //����ʹ��btn1
			    //�ж��ֶ��Ƿ�Ϊ�գ��Ƿ����Ҫ��
			    //var proId=$("#proId").children('option:selected').val();
			    var teamGroupId='';
			    var userlevelId=null;
			    if($("#zero").prop("checked")){
			    	userlevelId="0";
			    	teamGroupId=$("#groupId").children('option:selected').attr("id");
			    	
			    }else if($("#one").prop("checked")){
			    	userlevelId="1";
			    	teamGroupId=$("#teamsId").children('option:selected').attr("id");
			    	
			    }else if($("#two").prop("checked")){
			    	userlevelId="2";
			    	teamGroupId=$("#teamsId").children('option:selected').attr("id");
			    	
			    }
			    var pro=$("#groupId").children('option:selected').val();
			    /* var data={id:id,proId:proId,teamGroupId:teamGroupId,userLevelId:userlevelId};
			    data=JSON.stringify(data); */
			    $.ajax({
		 	          url : '/wbem/system/sysSet/updateUserInTeamGroup.action',//������������ַ��ӦsAjaxSource
			          data : {id:id,teamGroupId:teamGroupId,userLevelId:userlevelId,property:pro},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) { 
			          	if(result =='1'){
							table.draw();
							layer.close(index);
			            }else if(result=='0') {
			              	layer.alert("ֻ����Ŀ����ļ���Ϊ����");
			            }else {
			              	layer.alert(result.data+" ��CRM��û�д��ˣ������Ƶ�����");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("ϵͳ�쳣����ϵ����Ա");
			          }
				});
			    
			},
			cancel: function(index){ //����ʹ��btn2
			    //
			},
	        content: $("#editCustInfo")
	    });
	    $("#editCustInfo-Tel").focus();
	}
//����Ա�Ƴ�����
	function deleteUser(userTeamId){
	layer.confirm('ȷ��Ҫɾ���ó�Ա��', {
			  btn: ['ȷ��','ȡ��'] //��ť
			}, function(index){
				  $.ajax({
	 	         url : '/wbem/system/sysSet/deleteUserFromTeamGroup.action',//������������ַ��ӦsAjaxSource
		          data : {"userTeamId":userTeamId},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
		          type : 'post',
		          dataType : 'json',
		          success : function(result) { 
		          	if(result !=0){
						//$('.data-table').DataTable().ajax.reload();
						layer.close(index);
						table.draw();
		            }else{
		              	layer.alert("ɾ��ʧ��");
		            }
		          },
		          error : function(msg) {
		          	layer.alert("ϵͳ�쳣����ϵ����Ա");
		          }
		});
			}, function(){
			  
		});
		
	}
function getTeamGroupFormPorject(projectId,isAdmin,selId,groupType){
		var str="";
		$.ajax({
	            url: '/wbem/system/sysSet/getAdminTeamGroupAllFromProject.action',
	            data: {"projectId":projectId,"isProjectAdmin":isAdmin,"groupType":groupType},
	            type:"post",
	            async: false,
	            cache: false,
	            dataType: "json",
	            success: function(data){
	                 if (data != null && groupType =='') {
	                    var length = data.length;
	                    for (var i = 0; i < length; i++){   //ѭ��option
							str+="<option value="+data[i].groupType+" id="+data[i].id+">"+data[i].name+"</option>";
	                    };
	                  }else if(data != null && groupType !=''){
	                  	var length = data.length;
	                    for (var i = 0; i < length; i++){   //ѭ��option
							str+="<option value="+data[i].groupType+" id="+data[i].id+">"+data[i].name+"</option>";
	                    };
	                  }
	                  $("#"+selId).empty();
	                  $("#"+selId).append(str);
	             }
	     });
}	
$(document).ready(function(){
	//��ʼ��������
	$.fn.zTree.init($("#commonTree"), setting);
	
	$("#tableSearch").click(function(){
		table.draw();
	});

	$("#resetSearch").click(function(){
		$('#userName').val("");
		/* setTimeout($('.data-table').DataTable().ajax.reload(),100); */
		table.draw();
	});
	//������Ա��ť�¼�
	$("#createUser").on('click',function(){
		if(projectId=='' || projectId==null){//���������Ŀ��ʲôҲû���
			layer.alert("����ѡ����Ӧ����");
			return;
		}
		 layer.open({
		    type: 2,
		    title: '�����û�',
		    skin: 'layui-layer-rim', //���ϱ߿�
		    area: ['1000px', '700px'], //���
		    content: '/wbem/system/sysSet/createTeamGroupMember.action?teamGroupId='+teamGroupId+'&isProjectAdmin='+isProjectAdmin+'&property='+groupType
		}); 
	});
	$('#proId').change(function(){ 
		var projectId=$(this).children('option:selected').val();
		getTeamGroupFormPorject(projectId,'1','groupId','');//����仯
	}); 
	$('#groupId').change(function(){ 
		var projectId=$('#proId').children('option:selected').val();
		var groupN=$(this).children('option:selected').val();
		getTeamGroupFormPorject(projectId,'0','teamsId',groupN);//����仯
	}); 
	//�����Ŷ��¼�
	$("#createTeamGroup").on('click',function(){
		$("#projectName").val(projectName);
		$("#projectName").attr('disabled',true);
		$("#teamGroupName").val('');
		var proId=projectId;
		var isProAdmin=isProjectAdmin;
		var description=encodeURI(projectName);
		var proName=encodeURI(projectName);
		var groType=groupType;
		
		if(projectId==''){//ʲôҲû���
			layer.alert("����ѡ����Ӧ����");
			return;
		}
		if(projectId==null){//���������Ŀ--������Ŀ���飬���ǹ���Ա����
			$("#projectName").val(teamName);
			$("#systemDisplay").show();
			$("#nameChange").text('����');
			proId=teamGroupId;
			isProAdmin='1';
			groType=$("#systemSel").children('option:selected').val();
		}
		if(isProjectAdmin=='1'){//���������Ŀ������ --�����Ŷӣ������鳤����Ա����
			$("#nameChange").text('����');
			isProAdmin='0';
			$("#systemDisplay").hide();
		}
		if(projectId !='' && projectId !=null && isProjectAdmin==undefined){//����ľ�����Ŷӣ����������¼����
			return;
		}
		layer.open({
	        type: 1,
	        title: '�����Ŷ�',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['400px' , '260px'],
	        btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){ //����ʹ��btn1
				var groupName=encodeURI($("#teamGroupName").val());
				if(projectId==null){//���������Ŀ--������Ŀ���飬���ǹ���Ա����
					description=encodeURI(teamName);
					proName=encodeURI(teamName);
					groType=$("#systemSel").children('option:selected').val();
				}
			    $.ajax({
		 	          url : '/wbem/system/sysSet/insertTeamGroup.action',//������������ַ��ӦsAjaxSource
			          data : {groupName:groupName,projectId:proId,isProjectAdmin:isProAdmin,description:description,projectName:proName,groupType:groType},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							$.fn.zTree.init($("#commonTree"), setting);
			            }else if(result==-1)  {
			              	layer.alert("�Ѿ�Ϊ��ϵͳ�������飡");
			            }else  {
			              	layer.alert("�༭ʧ��");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("ϵͳ�쳣����ϵ����Ա");
			          }
				});
			},
			cancel: function(index){ //����ʹ��btn2
			    //
			},
	        content: $("#teamGroupDia")
	    }); 
	});
		//�޸��Ŷ��¼�
	$("#updateTeamGroup").on('click',function(){
		$("#projectName").val(projectName);
		$("#projectName").attr('disabled',true);
		$("#teamGroupName").val(teamName);
		if(projectId==''){//ʲôҲû���
			layer.alert("����ѡ����Ӧ����");
			return;
		}
		if(projectId==null){//���������Ŀ--������Ŀ���飬���ǹ���Ա����
			layer.alert("��Ŀ�����޸�");
			return;
		}
		if(isProjectAdmin=='1'){//���������Ŀ������ --�����Ŷӣ������鳤����Ա����
			$("#systemDisplay").show();
			$("#nameChange").text('����');
			$("#systemSel").children('option').each(function(index){
				if($(this).val()==groupType){
					$(this).attr("selected", "selected");
				};
			});
		}
		if(projectId !='' && projectId !=null && isProjectAdmin==undefined){//����ľ�����Ŷӣ����������¼����
			$("#nameChange").text('����');
			$("#systemDisplay").hide();
			
		}
		layer.open({
	        type: 1,
	        title: '�޸��Ŷ�',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['400px' , '260px'],
	        btn: ['ȷ��', 'ȡ��'],
	        //closeBtn: 1,
			yes: function(index, layero){ //����ʹ��btn1
				var groupName=encodeURI($("#teamGroupName").val());
			    $.ajax({
		 	          url : '/wbem/system/sysSet/updateTeamGroup.action',//������������ַ��ӦsAjaxSource
			          data : {teamGroupId:teamGroupId,groupName:groupName},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result !=0){
			          		layer.close(index);
							$.fn.zTree.init($("#commonTree"), setting);
			            }else {
			              	layer.alert("�༭ʧ��");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("ϵͳ�쳣����ϵ����Ա");
			          }
				});
			},
			cancel: function(index){ //����ʹ��btn2
			    //
			},
	        content: $("#teamGroupDia")
	    }); 
	});
	$("#deleteTeamGroup").on('click',function(){
		if(projectId==''){//ʲôҲû���
			layer.alert("����ѡ����Ӧ����");
			return;
		}
		if(projectId==null){//���������Ŀ
			layer.alert("��Ŀ����ɾ��!");
			return;
		}
		layer.confirm('ȷ��Ҫɾ��������', {
			  btn: ['ȷ��','ȡ��'] //��ť
			}, function(index){
				  $.ajax({
			 	          url : '/wbem/system/sysSet/deleteTeamGroup.action',//������������ַ��ӦsAjaxSource
				          data : {teamGroupId:teamGroupId,projectId:projectId,groupType:groupType},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result ==-1){
				          		layer.alert("�������滹�г�Ա������ɾ����");
				            }else if(result ==-2){
				          		layer.alert("�������滹���Ŷ�С�飬����ɾ����");
				            }else if(result ==1) {
				            	layer.close(index);
				              	$.fn.zTree.init($("#commonTree"), setting);
				            }else {
				            	layer.alert("ɾ��ʧ��");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�쳣����ϵ����Ա");
				          }
				});
			}, function(){
			  
		});
		
	});
}); 
</script> 
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>