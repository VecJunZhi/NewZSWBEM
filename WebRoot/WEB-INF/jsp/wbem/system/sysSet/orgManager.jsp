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
	#footer a{color: #666;}
	#footer a:hover{color:#f60; background-color:#2f332a}
	.layui-layer-btn a:hover{
		color: #000;
	}
	.table > thead > tr > .active,
.table > tbody > tr > .active,
.table > tfoot > tr > .active,
.table > thead > .active > td,
.table > tbody > .active > td,
.table > tfoot > .active > td,
.table > thead > .active > th,
.table > tbody > .active > th,
.table > tfoot > .active > th {
  background-color: #f5f5f5;
}
</style>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span3">
	    <div class="widget-box">
			<div class="widget-title">
				<span class="icon"><i class="icon-th"></i></span><h5>��֯�ܹ�</h5>
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
     	<div class="widget-box">
			<div class="widget-title">
				<span class="icon"><i class="icon-th"></i></span><h5>��λ�б�</h5>
				<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
					<!-- <input type="text" style="margin-bottom:0" id="userName_s" placeholder="����">
					<button class="btn btn-info" id="tableSearch">��ѯ</button>
					<button type="button" class="btn btn-info" id="resetSearch">����ɸѡ</button> -->
					<button type="button" class="btn btn-success"  id="createUser" disabled="disabled">������λ</button>
					<!-- <button type="button" class="btn btn-success" id="updateUser">�޸ĳ�Ա</button>
					<button type="button" class="btn btn-success" id="deleteUser">ɾ����Ա</button> -->
				</div>
	        </div>
			<div class="widget-content nopadding">
				<table class="table table-bordered data-table" id="roleTable">
					<thead>
		                <tr> 
							<th>���</th>
							<th>��λ����</th>
							<th>״̬</th>
							<th>����</th>
							<th hidden="hidden">ORGID</th>
							<th>����</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"><i class="icon-th"></i></span><h5>��Ա�б�</h5>
				<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
					<!-- <input type="text" style="margin-bottom:0" id="userName_s" placeholder="����">
					<button class="btn btn-info" id="tableSearch">��ѯ</button>
					<button type="button" class="btn btn-info" id="resetSearch">����ɸѡ</button> -->
					<button type="button" class="btn btn-success"  id="createUserRole" >������Ա</button>
					<!-- <button type="button" class="btn btn-success" id="updateUser">�޸ĳ�Ա</button>
					<button type="button" class="btn btn-success" id="deleteUser">ɾ����Ա</button> -->
				</div>
	        </div>
			<div class="widget-content nopadding">
				<table class="table table-bordered data-table" id="userTable">
					<thead>
		                <tr>
							<th>���</th>
							<th>����</th>
							<th>�ֻ�����</th>
							<th>�û���</th>
							<th>Email</th>
							<th>�û�״̬</th>
							<th>����״̬</th>
							<th>����</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
    </div>
  </div>
</div>

<div id="cemModelMenu" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label" id="modelabel">��˾����/��������</label>
	       <div class="controls">
	         <input type="text" id="mmName" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">�Ƿ���Ч</label>
	       <div class="controls">
	         <input type="radio" name="mmRadio" id="mmRadio1" value="1" style="margin-top: -4px;">����
	         <input type="radio" name="mmRadio" id="mmRadio2" value="0" style="margin-top: -4px;">����
	       </div>
	     </div>
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label">����</label>
	       <div class="controls">
	         <input type="text" id="mmiaoshu" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">�����</label>
	       <div class="controls">
	         <input type="text" id="priority" class="span4" value="" />
	       </div>
	     </div>
     </form>
</div>
<div id="up_mModelMenu" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label" id="up_modelabel">��˾����/��������</label>
	       <div class="controls">
	         <input type="text" id="up_mmName" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">�Ƿ���Ч</label>
	       <div class="controls">
	         <input type="radio" name="up_mmRadio" id="up_mmRadio1" value="1" style="margin-top: -4px;">����
	         <input type="radio" name="up_mmRadio" id="up_mmRadio2" value="0" style="margin-top: -4px;">����
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="enterpriseDiv">
	       <label class="control-label">�ƶ���</label>
	       <div class="controls">
	         <select id="enterpriseId" class="span4" name="exceptSelect">
	              <option value="">������˾</option>
	         </select>
	       </div>
	     </div>
     </form>
</div>
 <div id="ceRole" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> 
	       <label class="control-label">��λ����</label>
	       <div class="controls">
	         <input type="text" id="roleName" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">״̬</label>
	       <div class="controls">
	         <input type="radio" name="roleRadio" id="roleRadio1" value="1" style="margin-top: -4px;">����
	         <input type="radio" name="roleRadio" id="roleRadio2" value="0" style="margin-top: -4px;">����
	       </div>
	     </div>
	     <div class="control-group noborder-bottom"> 
	       <label class="control-label">��λ����</label>
	       <div class="controls">
	         <input type="text" id="roleDesc" class="span4" value="" />
	       </div>
	     </div>
	     <!-- <div class="control-group noborder-bottom" id="systemDisplay">
	       <label class="control-label">����</label>
	       <div class="controls">
	         <input type="radio" name="promissionTypeRadio" id="promissionTypeRadio1" value="page" style="margin-top: -4px;">ҳ��
	         <input type="radio" name="promissionTypeRadio" id="promissionTypeRadio2" value="button" style="margin-top: -4px;">������ť
	       </div>
	     </div> -->
	     
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
			url:"/wbem/system/sysSet/getOrganizationTree.action",
			type:"post",
			autoParam:["id=orgid","parId=parentID"],
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
	var roleid='';
	var orgid='';
	var parentid='';
	var name='';
	var available='';
	function zTreeOnClick(event, treeId, treeNode) {
		//$("#"+treeId).find("a").removeAttr("href");
		//$("#"+treeId).find("a").removeAttr("target");
		//roleid=treeNode.roleid;
		orgid=treeNode.id;
		parentid=treeNode.parId;
		name=treeNode.name;
		available=treeNode.available;
		/* permissionType=treeNode.permissionType;
		url=treeNode.url;
		permissionMark=treeNode.permissionMark;
		name=treeNode.name;
		subpermissionMark=treeNode.subpermissionMark;
		available=treeNode.available;
		priority=treeNode.priority;
		parentID=treeNode.parId; */
		/* if(orgid=='1'&& parentid=='0'){//�����ʢ---������˾
			//$("#mmurlDiv").hide();
			//$("#mmpMark_show").val("subsystem:");
			$("#modelabel").text("�ӹ�˾����");
		}else if(orgid !='1'&&  parentid=='1' ){//�����˾---��������
			$("#modelabel").text("��������");
		} else if(orgid !='1' ||  parentid !='1'){//�������---��������
			$("#mmurlDiv").show();
			$("#mmpMark_show").val(permissionMark+":");
			$("#modelabel").text("�Ӳ˵�����");
		} */
		if(orgid=='1'&& parentid=='0'){//�����ʢ---������˾
			$("#createTeamGroup").text("�����ӹ�˾").show();
			$("#createUser").attr("disabled",true);
			$("#enterpriseDiv").hide();
			$("#deleteTeamGroup").hide();
			
		}else if(orgid !='1'&&  parentid=='1'){//�����˾---��������
			$("#createTeamGroup").text("��������").show();
			$("#createUser").attr("disabled",true);
			$("#enterpriseDiv").hide();
			$("#deleteTeamGroup").show();
		}else if(orgid !='1' ||  parentid !='1'){//�������---��������
			$("#createTeamGroup").hide();
			$("#createUser").attr("disabled",false);
			$("#enterpriseDiv").show();
			$("#deleteTeamGroup").show();
			getEnterpriseName();
			
		} 
		c_roleid='0';//��ʼ����Ա��
		getUserByRoleID();//��ʼ����Ա��
		//alert("rid "+roleid+" orgid "+orgid);
		//return;
		if(table==null){
			table=$('#roleTable').DataTable({
				"ajax": {
					type: "post",//��ָ̨���˷�ʽ��
		         	url: "/wbem/system/sysSet/getRole.action",
					data:function(d){			//�ⲿ��������
						d.orgid=orgid;
						//d.roleid=roleid;
			         	//d.projectId = projectId;
			         	//d.userName = encodeURI($('#userName_s').val());
			        }
		         },
		         "columns":[
		         	{"data":"index"},
		         	{"data":"roleName"},
		         	{"data":"available","render":function(data, type, row, meta){
		         			if(data=="false"){
		         				return "����";
		         			}else if(data=="true"){
		         				return "����";
		         			} 
		         	}},
		         	{"data":"description"},
		         	{"data":"orgid"},
		         	{"data":"roleid"}
		         ],
		         columnDefs:[
					 {
			         	targets:5,"render":function(data, type, row, meta){
			       			return  "<a style='color:#FAA732' href='#' onclick='editSysModel(\"" + data + "\",\"" + row.roleName + "\",\"" + row.available + "\",\"" + row.description + "\")'>�༭��λ</a>&nbsp;&nbsp;"+
			       					"<a style='color:#FAA732' href='#' onclick='createRolePermission(\"" + data + "\",\"" + row.orgid + "\")'>������Ȩ</a>";
       								
			         	}
			         } , 
			           {
						"targets": [4],
						"visible": false,
			         }  
        ]
			});
			$('select[name!="exceptSelect"]').select2();
		}else{
			$('#roleTable').DataTable().ajax.reload();	
		}
	};
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
	};
	function filter(treeId, parentNode, childNodes) {
		return childNodes;
	}
	function createRolePermission(roleid,orgid){
		layer.open({
		    type: 2,
		    title: '������Ȩ',
		    skin: 'layui-layer-rim', //���ϱ߿�
		    area: ['1000px', '700px'], //���
		    content: '/wbem/system/sysSet/createRolePermission.action?roleID='+roleid
		});
	}
//�༭��ɫ
	function editSysModel(rid,rName,avail,desc){
		//pMark=pType+permissionMark.substring(permissionMark.lastIndexOf(":")+1, permissionMark.length)+":"+$("#promissionMark").val();
		$("#roleName").val(rName);
		$("#roleDesc").val(desc);
		//$("#promissionMark").val(permissionMark.substring(permissionMark.lastIndexOf(":")+1));
		//$("#promissionMark_show").val(permissionMark.substring(0,permissionMark.lastIndexOf(":")+1));
		//alert("av "+avail);
		//alert(avail);
		if(avail=="true"){
			$("#roleRadio1").prop("checked",true);
		}else if(avail=="false"){
			$("#roleRadio2").prop("checked",true);
		}
		//$("#permissionAvail").val(priority);
		var roName='';
		var avaie='';
		var descrp='';
		layer.open({
	        type: 1,
	        title: '�༭��λ',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['540px' , '260px'],
	        btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){ //����ʹ��btn1
				roName=encodeURI($("#roleName").val());
				//pType=$("#promissionTypeSele").children('option:selected').val();
				descrp=encodeURI($("#roleDesc").val());
				$("input:radio[name=roleRadio]").each(function(index){
						if($(this).prop("checked")==true){
							if(index=='0'){
								avaie='1';
							}else if(index=='1'){
								avaie='0';
							}
						}
					});
				//alert("pname "+pName+" mark "+pMark+" type "+pType+" parid "+parID+" ul "+ul+" av "+avaie+" pri "+prity);
			   // alert("aiv "+avaie);
			    $.ajax({
		 	          url : '/wbem/system/sysSet/updateRole.action',//������������ַ��ӦsAjaxSource
			          data : {roleID:rid,roleName:roName,available:avaie,description:descrp},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							table.draw();
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
	        content: $("#ceRole")
	    });
	}
	function ceRole (){
		var roName='';
		var avaie='';
		var descrp='';
		var prity=1;	
		layer.open({
	        type: 1,
	        title: '������λ',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['540px' , '260px'],
	        btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){ //����ʹ��btn1
				roName=encodeURI($("#roleName").val());
				descrp=encodeURI($("#roleDesc").val());
				//pType=$("#promissionTypeSele").children('option:selected').val();
				$("input:radio[name=roleRadio]").each(function(index){
						if($(this).prop("checked")==true){
							if(index=='0'){
								avaie='1';
							}else if(index=='1'){
								avaie='0';
							}
						}
					});
				//alert("pname "+pName+" mark "+pMark+" type "+pType+" parid "+parID+" ul "+ul+" av "+avaie+" pri "+prity);
			    $.ajax({
		 	          url : '/wbem/system/sysSet/createRole.action',//������������ַ��ӦsAjaxSource
			          data : {roleName:roName,priority:prity, available:avaie, description:descrp,orgid:orgid},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							table.draw();
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
	        content: $("#ceRole")
	    });
	}
	var userTable=null;
	var c_roleid='0';
	
	function getUserByRoleID(){
		if(userTable==null){
			userTable=$('#userTable').DataTable({
							"ajax": {type: "post",//��ָ̨���˷�ʽ��
					                 url: "/wbem/system/sysSet/getUsersByRoleID.action",
					                 data:function(d){			//�ⲿ��������
					                 	d.roleID = c_roleid;
					                 }
					         },
					         "columns":[
								{"data":"index"},
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
								{"data":"userID","render":function(data, type, row, meta){
										return "<a style='color:#FAA732' href='#' onclick='deleteUserRole(\"" + data + "\")' >ɾ��</a>&nbsp;";
								}}
								
								
							],
					          /* columnDefs:[{
					         	targets:0,"render":function(data, type, row, meta){
					         			return "<a style='color:#FAA732' href='#' onclick='openFollowDetails(\"" + data + "\")'>�鿴������¼</a>";
					         	}
					         }]  */
         
			});
			$('select[name!="exceptSelect"]').select2();
		}else{
			userTable.draw();
		}
	}
	function deleteUserRole(userID){
		layer.confirm('ȷ��Ҫɾ���ó�Ա��', {
				  btn: ['ȷ��','ȡ��'] //��ť
				}, function(index){
					 $.ajax({
			            url: '/wbem/system/sysSet/deleteUserRole.action',
			            data: {"userID":userID,"roleID":c_roleid},
			            type:"post",
			            async: false,
			            cache: false,
			            dataType: "json",
			            success: function(data){
			                if(data=='1'){
			                	layer.close(index);
			                	userTable.draw();
			                }else{
			                	layer.alert("����");
			                }
			                
			             }
	     			});
				}, function(){
			  
		});
		
	}
$("#roleTable").on("click","tr",function(){
	c_roleid=table.row(this).data().roleid;
	$('input[name=rradio]:radio').prop("checked",false);
	$('input[name=rradio]:radio',this).prop("checked",true);
	 var row = this;
	if ( $(row).hasClass('active') ) {  
		$(row).removeClass('active');
	}
    else {
    	table.$('tr.active').removeClass('active');
        $(row).addClass('active');
    }
	getUserByRoleID();
});	
function getEnterpriseName(){
		var str="";
		$.ajax({
	            url: '/wbem/system/sysSet/getOrganizationTree.action',
	            data: {"orgid":'1',"parentID":''},
	            type:"post",
	            async: false,
	            cache: false,
	            dataType: "json",
	            success: function(data){
	                // if (data != null && groupType =='') {
	                    var length = data.length;
	                    for (var i = 0; i < length; i++){   //ѭ��option
							str+="<option id="+data[i].id+">"+data[i].name+"</option>";
	                    };
	                 // }
	                  $("#enterpriseId").empty();
	                  $("#enterpriseId").append(str);
	             }
	     });
}
$(document).ready(function(){
//��ʼ��������
	$.fn.zTree.init($("#commonTree"), setting);
//������λ��ť�¼�
	$("#createUser").on('click',function(){
		$("#ceRole").find(":input").val('');
		$("#ceRole").find(":radio").prop("checked",false);
		 ceRole();
	});
//������Ա--����Ӧ�ĸ�λ�ﰴť�¼�
	$("#createUserRole").on('click',function(){
		if(c_roleid=='' || c_roleid==null){//���������Ŀ��ʲôҲû���
			layer.alert("����ѡ����Ӧ�ĸ�λ");
			return;
		}
		 layer.open({
		    type: 2,
		    title: '������Ա',
		    skin: 'layui-layer-rim', //���ϱ߿�
		    area: ['1000px', '700px'], //���
		    content: '/wbem/system/sysSet/createUserRole.action?roleID='+c_roleid
		}); 
	});
//////////////////////////////////////���Ĳ���///////////////////////////////////////////
	//�����Ŷ��¼�
	$("#createTeamGroup").on('click',function(){
	//alert(orgid);
	//alert(orgid=='');
	//alert(parentid);
		if(orgid==''&& parentid==''){//ʲôҲû���
				layer.alert("ѡ�����ڵ�");
				return;
		}
		$("#cemModelMenu").find("input").val("");//���������
		$("#mmRadio1").prop("checked",true);
		if(orgid=='1'&& parentid=='0'){//�����ʢ---������˾
			//$("#mmurlDiv").hide();
			//$("#mmpMark_show").val("subsystem:");
			$("#modelabel").text("�ӹ�˾����");
		}else if(orgid !='1'&&  parentid=='1' ){//�����˾---��������
			$("#modelabel").text("��������");
		} else if(orgid !='1' ||  parentid !='1'){//�������---��������
			
		} 
		createSubSystemModel();
		 
	});
	function createSubSystemModel(){
		var orgname='';
		var orgtype='';
		var parID='';
		var prity='';
		var avaie='';
		var descrip='';	
		layer.open({
	        type: 1,
	        title: $("#modelabel").text(),
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['540px' , '350px'],
	        btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){ //����ʹ��btn1
				orgname=encodeURI($("#mmName").val());
				descrip=encodeURI($("#mmiaoshu").val());
				parID=orgid;
				$("input:radio[name=mmRadio]").each(function(index){
						if($(this).prop("checked")==true){
							if(index=='0'){
								avaie='1';
							}else if(index=='1'){
								avaie='0';
							}
						}
					});
				prity=$("#priority").val();	
				if(orgid=='1'&& parentid=='0'){//�����ʢ---������˾
					//$("#mmurlDiv").hide();
					//$("#mmpMark_show").val("subsystem:");
					orgtype='2';
				}else if(orgid !='1'&&  parentid=='1' ){//�����˾---��������
					orgtype='3';
				}
				//alert("pname "+orgname+" mark "+descrip+" parid "+parID+" orgtype "+orgtype+" av "+avaie+" pri "+prity);
			    $.ajax({
		 	          url : '/wbem/system/sysSet/createOrganization.action',//������������ַ��ӦsAjaxSource
			          data : { orgname:orgname, orgtype:orgtype, description:descrip, parentid:parID, priority:prity, available:avaie},//
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							$.fn.zTree.init($("#commonTree"), setting);
							orgid='';
							parentid='';
							$("#createUser").attr("disabled",true);
							$("#createTeamGroup").text("����");
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
	        content: $("#cemModelMenu")
	    });
	}
		//�޸��Ŷ��¼�
	$("#updateTeamGroup").on('click',function(){
		if(orgid==''&& parentid==''){//ʲôҲû���
				layer.alert("ѡ�����ڵ�");
				return;
		}
		if(orgid=='1'&& parentid=='0'){//�����ʢ
				$("#up_modelabel").text("�ܹ�˾����");
				updateSubSystemModel("�޸ļ��Ź�˾����",'');
		}else if(orgid !='1'&&  parentid=='1' ){//����ӹ�˾
			//$("#mmurlDiv").hide();
			//$("#mmpMark_show").val("subsystem:");
			$("#up_modelabel").text("�ӹ�˾����");
			updateSubSystemModel("�޸��ӹ�˾",''); 
		}else if(orgid !='1' ||  parentid !='1'){//�������
			$("#up_modelabel").text("��������");
			
			//getEnterpriseName();
			$("#enterpriseId").children('option').each(function(index){
				if($(this).attr("id")==parentid){
					$(this).attr("selected", "selected");
				};
			});
			var _pid=$("#enterpriseId").children('option:selected').attr("id");
			updateSubSystemModel("�޸Ĳ���",_pid);
		}
		$("#up_mmName").val(name);
		if(available==true){
			$("#up_mmRadio1").prop("checked",true);
		}else if(available==false){
			$("#up_mmRadio2").prop("checked",true);
		}
		//$("#mmpMark").val(subpermissionMark);
		//$("#priority").val(priority);	
		//$("#mmurl").val(url);
	});
		function updateSubSystemModel(title,parID){
		var orgame='';
		var avaie='';
		//var descrip='';	
		layer.open({
	        type: 1,
	        title: title,
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['600px' , '240px'],
	        btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){ //����ʹ��btn1
				orgame=encodeURI($("#up_mmName").val());
				if(parID !=''){
					parID=$("#enterpriseId").children('option:selected').attr("id");
				}
				$("input:radio[name=up_mmRadio]").each(function(index){
						if($(this).prop("checked")==true){
							if(index=='0'){
								avaie='1';
							}else if(index=='1'){
								avaie='0';
							}
						}
					});
					//alert(avaie);
				//prity=$("#priority").val();	
				/* if(permissionID=='0'&& permissionType=='0' && url=='0'){//�����ʢ---���޸�
					layer.alert("�����޸�");
					return;
				}else if(permissionID !='0'&&  permissionType=='subsystem' ){//���ģ��---�޸�ģ��
					pMark='subsystem:'+$("#mmpMark").val();
					pType="subsystem";
					parID='0';
					ul='';
					descrip='';
				}else if((permissionID !='' && permissionID !='0' && permissionID !=null)&& (permissionType=='menu')&& (url==null || url ==''|| url.lastIndexOf(".action")==-1)){//����˵�---�޸Ĳ˵�
					pMark='menu:'+$("#mmpMark").val();
					pType="menu";
					parID=parentID;
					ul='';
					descrip='';
				}else if((permissionID !='' && permissionID !='0' && permissionID !=null)&& (permissionType=='menu')&& (url.lastIndexOf(".action")!=-1)){//����Ӳ˵�---�޸��Ӳ˵�
					pMark=permissionMark+":"+$("#mmpMark").val();
					pType="menu";
					parID=parentID;
					ul=$("#mmurl").val();
					descrip='';
				} */
				//alert("pname "+pName+" mark "+pMark+" type "+pType+" parid "+parID+" ul "+ul+" av "+avaie+" pri "+prity);
			    $.ajax({
		 	          url : '/wbem/system/sysSet/updateOrganization.action',//������������ַ��ӦsAjaxSource
			          data : { orgid:orgid,orgname:orgame, available:avaie,parentID:parID},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							$.fn.zTree.init($("#commonTree"), setting);
							orgid='';
							parentid='';
							$("#createUser").attr("disabled",true);
							$("#createTeamGroup").text("����");
			            }else{
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
	        content: $("#up_mModelMenu")
	    });
	}
	$("#deleteTeamGroup").on('click',function(){
		if(orgid==''&& parentid==''){//ʲôҲû���
				layer.alert("ѡ��Ҫɾ���Ľڵ�");
				return;
		}
		if(orgid=='1'&& parentid=='0'){//�����ʢ
				layer.alert("��һ��Ŀ¼����ɾ��");
				return;
		}
		
		layer.confirm('ȷ��Ҫɾ��������', {
			  btn: ['ȷ��','ȡ��'] //��ť
			}, function(index){
				  $.ajax({
			 	          url : '/wbem/system/sysSet/deleteOrgaination.action',//������������ַ��ӦsAjaxSource
				          data : {orgid:orgid},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result ==-1){
				          		layer.alert("���ӹ�˾���в��Ŵ��ڣ�����ɾ����");
				            }else if(result ==-2){
				          		layer.alert("�ò������������Ӧ��λ������ɾ����");
				            }else if(result ==1) {
				            	layer.close(index);
				              	$.fn.zTree.init($("#commonTree"), setting);
				              	orgid='';
								parentid='';
								$("#createUser").attr("disabled",true);
								$("#createTeamGroup").text("����");
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
		
	});
}); 
</script> 
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>