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
				<span class="icon"><i class="icon-th"></i></span><h5>组织架构</h5>
				<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
					<button type="button" class="btn btn-success"  id="createTeamGroup">新增</button>
					<button type="button" class="btn btn-success"  id="updateTeamGroup">修改</button>
					<button type="button" class="btn btn-success"  id="deleteTeamGroup">删除</button>
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
				<span class="icon"><i class="icon-th"></i></span><h5>岗位列表</h5>
				<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
					<!-- <input type="text" style="margin-bottom:0" id="userName_s" placeholder="姓名">
					<button class="btn btn-info" id="tableSearch">查询</button>
					<button type="button" class="btn btn-info" id="resetSearch">撤销筛选</button> -->
					<button type="button" class="btn btn-success"  id="createUser" disabled="disabled">新增岗位</button>
					<!-- <button type="button" class="btn btn-success" id="updateUser">修改成员</button>
					<button type="button" class="btn btn-success" id="deleteUser">删除成员</button> -->
				</div>
	        </div>
			<div class="widget-content nopadding">
				<table class="table table-bordered data-table" id="roleTable">
					<thead>
		                <tr> 
							<th>序号</th>
							<th>岗位名称</th>
							<th>状态</th>
							<th>描述</th>
							<th hidden="hidden">ORGID</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="widget-box">
			<div class="widget-title">
				<span class="icon"><i class="icon-th"></i></span><h5>人员列表</h5>
				<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
					<!-- <input type="text" style="margin-bottom:0" id="userName_s" placeholder="姓名">
					<button class="btn btn-info" id="tableSearch">查询</button>
					<button type="button" class="btn btn-info" id="resetSearch">撤销筛选</button> -->
					<button type="button" class="btn btn-success"  id="createUserRole" >新增人员</button>
					<!-- <button type="button" class="btn btn-success" id="updateUser">修改成员</button>
					<button type="button" class="btn btn-success" id="deleteUser">删除成员</button> -->
				</div>
	        </div>
			<div class="widget-content nopadding">
				<table class="table table-bordered data-table" id="userTable">
					<thead>
		                <tr>
							<th>序号</th>
							<th>姓名</th>
							<th>手机号码</th>
							<th>用户名</th>
							<th>Email</th>
							<th>用户状态</th>
							<th>锁定状态</th>
							<th>操作</th>
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
	       <label class="control-label" id="modelabel">公司名称/部门名称</label>
	       <div class="controls">
	         <input type="text" id="mmName" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">是否有效</label>
	       <div class="controls">
	         <input type="radio" name="mmRadio" id="mmRadio1" value="1" style="margin-top: -4px;">启用
	         <input type="radio" name="mmRadio" id="mmRadio2" value="0" style="margin-top: -4px;">禁用
	       </div>
	     </div>
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label">描述</label>
	       <div class="controls">
	         <input type="text" id="mmiaoshu" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">排序号</label>
	       <div class="controls">
	         <input type="text" id="priority" class="span4" value="" />
	       </div>
	     </div>
     </form>
</div>
<div id="up_mModelMenu" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label" id="up_modelabel">公司名称/部门名称</label>
	       <div class="controls">
	         <input type="text" id="up_mmName" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">是否有效</label>
	       <div class="controls">
	         <input type="radio" name="up_mmRadio" id="up_mmRadio1" value="1" style="margin-top: -4px;">启用
	         <input type="radio" name="up_mmRadio" id="up_mmRadio2" value="0" style="margin-top: -4px;">禁用
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="enterpriseDiv">
	       <label class="control-label">移动到</label>
	       <div class="controls">
	         <select id="enterpriseId" class="span4" name="exceptSelect">
	              <option value="">其他公司</option>
	         </select>
	       </div>
	     </div>
     </form>
</div>
 <div id="ceRole" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> 
	       <label class="control-label">岗位名称</label>
	       <div class="controls">
	         <input type="text" id="roleName" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">状态</label>
	       <div class="controls">
	         <input type="radio" name="roleRadio" id="roleRadio1" value="1" style="margin-top: -4px;">启用
	         <input type="radio" name="roleRadio" id="roleRadio2" value="0" style="margin-top: -4px;">禁用
	       </div>
	     </div>
	     <div class="control-group noborder-bottom"> 
	       <label class="control-label">岗位描述</label>
	       <div class="controls">
	         <input type="text" id="roleDesc" class="span4" value="" />
	       </div>
	     </div>
	     <!-- <div class="control-group noborder-bottom" id="systemDisplay">
	       <label class="control-label">类型</label>
	       <div class="controls">
	         <input type="radio" name="promissionTypeRadio" id="promissionTypeRadio1" value="page" style="margin-top: -4px;">页面
	         <input type="radio" name="promissionTypeRadio" id="promissionTypeRadio2" value="button" style="margin-top: -4px;">操作按钮
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
		/* if(orgid=='1'&& parentid=='0'){//点击兆盛---新增公司
			//$("#mmurlDiv").hide();
			//$("#mmpMark_show").val("subsystem:");
			$("#modelabel").text("子公司名称");
		}else if(orgid !='1'&&  parentid=='1' ){//点击公司---新增部门
			$("#modelabel").text("部门名称");
		} else if(orgid !='1' ||  parentid !='1'){//点击部门---不能新增
			$("#mmurlDiv").show();
			$("#mmpMark_show").val(permissionMark+":");
			$("#modelabel").text("子菜单名称");
		} */
		if(orgid=='1'&& parentid=='0'){//点击兆盛---新增公司
			$("#createTeamGroup").text("新增子公司").show();
			$("#createUser").attr("disabled",true);
			$("#enterpriseDiv").hide();
			$("#deleteTeamGroup").hide();
			
		}else if(orgid !='1'&&  parentid=='1'){//点击公司---新增部门
			$("#createTeamGroup").text("新增部门").show();
			$("#createUser").attr("disabled",true);
			$("#enterpriseDiv").hide();
			$("#deleteTeamGroup").show();
		}else if(orgid !='1' ||  parentid !='1'){//点击部门---不能新增
			$("#createTeamGroup").hide();
			$("#createUser").attr("disabled",false);
			$("#enterpriseDiv").show();
			$("#deleteTeamGroup").show();
			getEnterpriseName();
			
		} 
		c_roleid='0';//初始化人员表
		getUserByRoleID();//初始化人员表
		//alert("rid "+roleid+" orgid "+orgid);
		//return;
		if(table==null){
			table=$('#roleTable').DataTable({
				"ajax": {
					type: "post",//后台指定了方式。
		         	url: "/wbem/system/sysSet/getRole.action",
					data:function(d){			//外部参数传递
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
		         				return "禁用";
		         			}else if(data=="true"){
		         				return "启用";
		         			} 
		         	}},
		         	{"data":"description"},
		         	{"data":"orgid"},
		         	{"data":"roleid"}
		         ],
		         columnDefs:[
					 {
			         	targets:5,"render":function(data, type, row, meta){
			       			return  "<a style='color:#FAA732' href='#' onclick='editSysModel(\"" + data + "\",\"" + row.roleName + "\",\"" + row.available + "\",\"" + row.description + "\")'>编辑岗位</a>&nbsp;&nbsp;"+
			       					"<a style='color:#FAA732' href='#' onclick='createRolePermission(\"" + data + "\",\"" + row.orgid + "\")'>数据授权</a>";
       								
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
		    title: '数据授权',
		    skin: 'layui-layer-rim', //加上边框
		    area: ['1000px', '700px'], //宽高
		    content: '/wbem/system/sysSet/createRolePermission.action?roleID='+roleid
		});
	}
//编辑角色
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
	        title: '编辑岗位',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['540px' , '260px'],
	        btn: ['确定', '取消'],
			yes: function(index, layero){ //或者使用btn1
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
		 	          url : '/wbem/system/sysSet/updateRole.action',//这个就是请求地址对应sAjaxSource
			          data : {roleID:rid,roleName:roName,available:avaie,description:descrp},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							table.draw();
			            }else if(result==-1)  {
			              	layer.alert("已经为该系统创建分组！");
			            }else  {
			              	layer.alert("编辑失败");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("系统异常，联系管理员");
			          }
				});
			},
			cancel: function(index){ //或者使用btn2
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
	        title: '新增岗位',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['540px' , '260px'],
	        btn: ['确定', '取消'],
			yes: function(index, layero){ //或者使用btn1
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
		 	          url : '/wbem/system/sysSet/createRole.action',//这个就是请求地址对应sAjaxSource
			          data : {roleName:roName,priority:prity, available:avaie, description:descrp,orgid:orgid},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							table.draw();
			            }else if(result==-1)  {
			              	layer.alert("已经为该系统创建分组！");
			            }else  {
			              	layer.alert("编辑失败");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("系统异常，联系管理员");
			          }
				});
			},
			cancel: function(index){ //或者使用btn2
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
							"ajax": {type: "post",//后台指定了方式。
					                 url: "/wbem/system/sysSet/getUsersByRoleID.action",
					                 data:function(d){			//外部参数传递
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
								{"data":"userID","render":function(data, type, row, meta){
										return "<a style='color:#FAA732' href='#' onclick='deleteUserRole(\"" + data + "\")' >删除</a>&nbsp;";
								}}
								
								
							],
					          /* columnDefs:[{
					         	targets:0,"render":function(data, type, row, meta){
					         			return "<a style='color:#FAA732' href='#' onclick='openFollowDetails(\"" + data + "\")'>查看跟进记录</a>";
					         	}
					         }]  */
         
			});
			$('select[name!="exceptSelect"]').select2();
		}else{
			userTable.draw();
		}
	}
	function deleteUserRole(userID){
		layer.confirm('确定要删除该成员吗？', {
				  btn: ['确定','取消'] //按钮
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
			                	layer.alert("出错");
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
	                    for (var i = 0; i < length; i++){   //循环option
							str+="<option id="+data[i].id+">"+data[i].name+"</option>";
	                    };
	                 // }
	                  $("#enterpriseId").empty();
	                  $("#enterpriseId").append(str);
	             }
	     });
}
$(document).ready(function(){
//初始化加载树
	$.fn.zTree.init($("#commonTree"), setting);
//新增岗位按钮事件
	$("#createUser").on('click',function(){
		$("#ceRole").find(":input").val('');
		$("#ceRole").find(":radio").prop("checked",false);
		 ceRole();
	});
//新增人员--在相应的岗位里按钮事件
	$("#createUserRole").on('click',function(){
		if(c_roleid=='' || c_roleid==null){//点击的是项目或什么也没点击
			layer.alert("请先选中相应的岗位");
			return;
		}
		 layer.open({
		    type: 2,
		    title: '新增人员',
		    skin: 'layui-layer-rim', //加上边框
		    area: ['1000px', '700px'], //宽高
		    content: '/wbem/system/sysSet/createUserRole.action?roleID='+c_roleid
		}); 
	});
//////////////////////////////////////树的操作///////////////////////////////////////////
	//新增团队事件
	$("#createTeamGroup").on('click',function(){
	//alert(orgid);
	//alert(orgid=='');
	//alert(parentid);
		if(orgid==''&& parentid==''){//什么也没点击
				layer.alert("选中树节点");
				return;
		}
		$("#cemModelMenu").find("input").val("");//先清空内容
		$("#mmRadio1").prop("checked",true);
		if(orgid=='1'&& parentid=='0'){//点击兆盛---新增公司
			//$("#mmurlDiv").hide();
			//$("#mmpMark_show").val("subsystem:");
			$("#modelabel").text("子公司名称");
		}else if(orgid !='1'&&  parentid=='1' ){//点击公司---新增部门
			$("#modelabel").text("部门名称");
		} else if(orgid !='1' ||  parentid !='1'){//点击部门---不能新增
			
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
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['540px' , '350px'],
	        btn: ['确定', '取消'],
			yes: function(index, layero){ //或者使用btn1
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
				if(orgid=='1'&& parentid=='0'){//点击兆盛---新增公司
					//$("#mmurlDiv").hide();
					//$("#mmpMark_show").val("subsystem:");
					orgtype='2';
				}else if(orgid !='1'&&  parentid=='1' ){//点击公司---新增部门
					orgtype='3';
				}
				//alert("pname "+orgname+" mark "+descrip+" parid "+parID+" orgtype "+orgtype+" av "+avaie+" pri "+prity);
			    $.ajax({
		 	          url : '/wbem/system/sysSet/createOrganization.action',//这个就是请求地址对应sAjaxSource
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
							$("#createTeamGroup").text("新增");
			            }else  {
			              	layer.alert("编辑失败");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("系统异常，联系管理员");
			          }
				});
			},
			cancel: function(index){ //或者使用btn2
			    //
			},
	        content: $("#cemModelMenu")
	    });
	}
		//修改团队事件
	$("#updateTeamGroup").on('click',function(){
		if(orgid==''&& parentid==''){//什么也没点击
				layer.alert("选中树节点");
				return;
		}
		if(orgid=='1'&& parentid=='0'){//点击兆盛
				$("#up_modelabel").text("总公司名称");
				updateSubSystemModel("修改集团公司名称",'');
		}else if(orgid !='1'&&  parentid=='1' ){//点击子公司
			//$("#mmurlDiv").hide();
			//$("#mmpMark_show").val("subsystem:");
			$("#up_modelabel").text("子公司名称");
			updateSubSystemModel("修改子公司",''); 
		}else if(orgid !='1' ||  parentid !='1'){//点击部门
			$("#up_modelabel").text("部门名称");
			
			//getEnterpriseName();
			$("#enterpriseId").children('option').each(function(index){
				if($(this).attr("id")==parentid){
					$(this).attr("selected", "selected");
				};
			});
			var _pid=$("#enterpriseId").children('option:selected').attr("id");
			updateSubSystemModel("修改部门",_pid);
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
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['600px' , '240px'],
	        btn: ['确定', '取消'],
			yes: function(index, layero){ //或者使用btn1
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
				/* if(permissionID=='0'&& permissionType=='0' && url=='0'){//点击兆盛---不修改
					layer.alert("不能修改");
					return;
				}else if(permissionID !='0'&&  permissionType=='subsystem' ){//点击模块---修改模块
					pMark='subsystem:'+$("#mmpMark").val();
					pType="subsystem";
					parID='0';
					ul='';
					descrip='';
				}else if((permissionID !='' && permissionID !='0' && permissionID !=null)&& (permissionType=='menu')&& (url==null || url ==''|| url.lastIndexOf(".action")==-1)){//点击菜单---修改菜单
					pMark='menu:'+$("#mmpMark").val();
					pType="menu";
					parID=parentID;
					ul='';
					descrip='';
				}else if((permissionID !='' && permissionID !='0' && permissionID !=null)&& (permissionType=='menu')&& (url.lastIndexOf(".action")!=-1)){//点击子菜单---修改子菜单
					pMark=permissionMark+":"+$("#mmpMark").val();
					pType="menu";
					parID=parentID;
					ul=$("#mmurl").val();
					descrip='';
				} */
				//alert("pname "+pName+" mark "+pMark+" type "+pType+" parid "+parID+" ul "+ul+" av "+avaie+" pri "+prity);
			    $.ajax({
		 	          url : '/wbem/system/sysSet/updateOrganization.action',//这个就是请求地址对应sAjaxSource
			          data : { orgid:orgid,orgname:orgame, available:avaie,parentID:parID},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							$.fn.zTree.init($("#commonTree"), setting);
							orgid='';
							parentid='';
							$("#createUser").attr("disabled",true);
							$("#createTeamGroup").text("新增");
			            }else{
			              	layer.alert("编辑失败");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("系统异常，联系管理员");
			          }
				});
			},
			cancel: function(index){ //或者使用btn2
			    //
			},
	        content: $("#up_mModelMenu")
	    });
	}
	$("#deleteTeamGroup").on('click',function(){
		if(orgid==''&& parentid==''){//什么也没点击
				layer.alert("选中要删除的节点");
				return;
		}
		if(orgid=='1'&& parentid=='0'){//点击兆盛
				layer.alert("第一级目录不能删除");
				return;
		}
		
		layer.confirm('确定要删除该组吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(index){
				  $.ajax({
			 	          url : '/wbem/system/sysSet/deleteOrgaination.action',//这个就是请求地址对应sAjaxSource
				          data : {orgid:orgid},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result ==-1){
				          		layer.alert("该子公司下有部门存在，不能删除！");
				            }else if(result ==-2){
				          		layer.alert("该部门下面存在相应岗位，不能删除！");
				            }else if(result ==1) {
				            	layer.close(index);
				              	$.fn.zTree.init($("#commonTree"), setting);
				              	orgid='';
								parentid='';
								$("#createUser").attr("disabled",true);
								$("#createTeamGroup").text("新增");
				            }else{
				            	layer.alert("删除失败");
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统异常，联系管理员");
				          }
				});
			}, function(){
			  
		});
		
	});
}); 
</script> 
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>