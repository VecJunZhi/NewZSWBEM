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
				<span class="icon"><i class="icon-th"></i></span><h5>分组选择</h5>
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
	   <!-- <div class="widget-box">
				<div class="widget-title">
					<input type="text" style="margin-bottom:0" id="userName_s" placeholder="姓名">
					<button class="btn btn-success" id="tableSearch">查询</button>
					<button type="button" class="btn btn-success" id="resetSearch">撤销筛选</button>
		        </div>
	  </div> -->
      <div class="widget-box">
		<div class="widget-title">
			<span class="icon"><i class="icon-th"></i></span><h5>统计列表</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<input type="text" style="margin-bottom:0" id="userName_s" placeholder="姓名">
				<button class="btn btn-info" id="tableSearch">查询</button>
				<button type="button" class="btn btn-info" id="resetSearch">撤销筛选</button>
				<button type="button" class="btn btn-success"  id="createUser">新增成员</button>
				<!-- <button type="button" class="btn btn-success" id="updateUser">修改成员</button>
				<button type="button" class="btn btn-success" id="deleteUser">删除成员</button> -->
			</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr> 
						<th>序号</th>
						<th hidden="hidden">userTeamId</th>
						<th>姓名</th>
						<th hidden="hidden">项目id</th>
						<th>组名</th>
						<th>级别</th>
						<th>操作</th>
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
	       <label class="control-label">姓名 :</label>
	       <div class="controls">
	         <input type="text" id="userName" class="span2" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">项目 :</label>
	       <div class="controls">
	         <select id="proId" class="span2" name="exceptSelect">
	              <c:forEach items="${projectList}" var="project" varStatus="status">
              		<option value="${project.id}">${project.name}</option>
              	  </c:forEach>
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">分组 :</label>
	       <div class="controls">
	         <select id="groupId" class="span2" name="exceptSelect">
	              
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="tuanDuiDiv">
	       <label class="control-label">团队 :</label>
	       <div class="controls">
	         <select id="teamsId" class="span2" name="exceptSelect">
	              
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom">
	       <label class="control-label">级别 :</label>
	       <div class="controls">
	         	<span id="radioZero"><input type="radio" name="radion" id="zero" value="0" style="margin-top: 0px;">经理</span>
				<span id="radioOne"><input type="radio" name="radion" id="one"  value="1"  style="margin-top: 0px;">组长</span>
				<span id="radioTwo"><input type="radio" name="radion" id="two"  value="2"  style="margin-top: 0px;">置业顾问</span>
	       </div>
	     </div>
     </form>
</div>
<div id="teamGroupDia" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom"> <!-- noborder-bottom -->
	       <label class="control-label">项目名称</label>
	       <div class="controls">
	         <input type="text" id="projectName" class="span2" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="systemDisplay">
	       <label class="control-label">隶属系统</label>
	       <div class="controls">
	         <select id="systemSel" class="span2" name="exceptSelect">
	              <option value="xs">销售系统</option>
	              <option value="zs">招商系统</option>
	         </select>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom" id="teamGroupInput"> <!-- noborder-bottom -->
	       <label class="control-label" id="nameChange">团队名称</label>
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
		if(projectId==null){//点击的是项目,不获取成员
			$("#createTeamGroup").attr("disabled",false);
			$("#createTeamGroup").text("新增系统");
			$("#createTeamGroup").show();
			$("#updateTeamGroup").hide();
			$("#deleteTeamGroup").hide();
			return;
		}
		/* if(isProjectAdmin=='1'){//点击的项目经理
			$("#radioOne").hide();
			$("#radioTwo").hide();
			$("#zero").attr("checked",true);
		} */
		if(projectId !='' && projectId !=null && isProjectAdmin==undefined){//点击的具体的团队，不能再往下级添加 --添加按钮值为禁用
			$("#createTeamGroup").attr("disabled",true);
			$("#createTeamGroup").hide();
			$("#updateTeamGroup").show();
			$("#deleteTeamGroup").show();
		}else if(isProjectAdmin=='1'){//点击的项目经理
			$("#createTeamGroup").attr("disabled",false);
			$("#createTeamGroup").text("新增组");
			$("#createTeamGroup").show();
			$("#updateTeamGroup").show();
			$("#deleteTeamGroup").show();
		}
		if(table==null){
			table=$('.data-table').DataTable({
				"ajax": {
					type: "post",//后台指定了方式。
		         	url: "/wbem/system/sysSet/getTeamGroupMember.action",
					data:function(d){			//外部参数传递
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
		         				return "经理";
		         			}else if(data=='1'){
		         				return "组长";
		         			}else{
		         				return "置业顾问";
		         			} 
		         	}},
		         	{"data":"id"}
		         	
		         ],
		         columnDefs:[
					 {
			         	targets:6,"render":function(data, type, row, meta){
			       			return  "<a style='color:#FAA732' href='#' onclick='editUserMember(\"" + data + "\",\"" + row.userName + "\",\"" + row.projectId + "\",\"" + row.userLevelId + "\",\"" + row.groupName + "\")'>编辑</a>&nbsp;"+
       								"<a style='color:#FAA732' href='#' onclick='deleteUser(\"" + data + "\")' >删除</a>&nbsp;";
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
//编辑组成员
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
	        title: '编辑客户',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['400px' , '360px'],
	        btn: ['确定', '取消'],
	        //closeBtn: 1,
			yes: function(index, layero){ //或者使用btn1
			    //判断字段是否为空，是否符合要求
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
		 	          url : '/wbem/system/sysSet/updateUserInTeamGroup.action',//这个就是请求地址对应sAjaxSource
			          data : {id:id,teamGroupId:teamGroupId,userLevelId:userlevelId,property:pro},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) { 
			          	if(result =='1'){
							table.draw();
							layer.close(index);
			            }else if(result=='0') {
			              	layer.alert("只有项目经理的级别为经理");
			            }else {
			              	layer.alert(result.data+" 在CRM中没有此人，不能移到该组");
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
	        content: $("#editCustInfo")
	    });
	    $("#editCustInfo-Tel").focus();
	}
//将成员移除该组
	function deleteUser(userTeamId){
	layer.confirm('确定要删除该成员吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(index){
				  $.ajax({
	 	         url : '/wbem/system/sysSet/deleteUserFromTeamGroup.action',//这个就是请求地址对应sAjaxSource
		          data : {"userTeamId":userTeamId},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
		          type : 'post',
		          dataType : 'json',
		          success : function(result) { 
		          	if(result !=0){
						//$('.data-table').DataTable().ajax.reload();
						layer.close(index);
						table.draw();
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
	                    for (var i = 0; i < length; i++){   //循环option
							str+="<option value="+data[i].groupType+" id="+data[i].id+">"+data[i].name+"</option>";
	                    };
	                  }else if(data != null && groupType !=''){
	                  	var length = data.length;
	                    for (var i = 0; i < length; i++){   //循环option
							str+="<option value="+data[i].groupType+" id="+data[i].id+">"+data[i].name+"</option>";
	                    };
	                  }
	                  $("#"+selId).empty();
	                  $("#"+selId).append(str);
	             }
	     });
}	
$(document).ready(function(){
	//初始化加载树
	$.fn.zTree.init($("#commonTree"), setting);
	
	$("#tableSearch").click(function(){
		table.draw();
	});

	$("#resetSearch").click(function(){
		$('#userName').val("");
		/* setTimeout($('.data-table').DataTable().ajax.reload(),100); */
		table.draw();
	});
	//新增成员按钮事件
	$("#createUser").on('click',function(){
		if(projectId=='' || projectId==null){//点击的是项目或什么也没点击
			layer.alert("请先选中相应的组");
			return;
		}
		 layer.open({
		    type: 2,
		    title: '新增用户',
		    skin: 'layui-layer-rim', //加上边框
		    area: ['1000px', '700px'], //宽高
		    content: '/wbem/system/sysSet/createTeamGroupMember.action?teamGroupId='+teamGroupId+'&isProjectAdmin='+isProjectAdmin+'&property='+groupType
		}); 
	});
	$('#proId').change(function(){ 
		var projectId=$(this).children('option:selected').val();
		getTeamGroupFormPorject(projectId,'1','groupId','');//分组变化
	}); 
	$('#groupId').change(function(){ 
		var projectId=$('#proId').children('option:selected').val();
		var groupN=$(this).children('option:selected').val();
		getTeamGroupFormPorject(projectId,'0','teamsId',groupN);//分组变化
	}); 
	//新增团队事件
	$("#createTeamGroup").on('click',function(){
		$("#projectName").val(projectName);
		$("#projectName").attr('disabled',true);
		$("#teamGroupName").val('');
		var proId=projectId;
		var isProAdmin=isProjectAdmin;
		var description=encodeURI(projectName);
		var proName=encodeURI(projectName);
		var groType=groupType;
		
		if(projectId==''){//什么也没点击
			layer.alert("请先选中相应的组");
			return;
		}
		if(projectId==null){//点击的是项目--新增项目分组，都是管理员级别
			$("#projectName").val(teamName);
			$("#systemDisplay").show();
			$("#nameChange").text('名称');
			proId=teamGroupId;
			isProAdmin='1';
			groType=$("#systemSel").children('option:selected').val();
		}
		if(isProjectAdmin=='1'){//点击的是项目经理级别 --新增团队，都是组长或组员级别
			$("#nameChange").text('组名');
			isProAdmin='0';
			$("#systemDisplay").hide();
		}
		if(projectId !='' && projectId !=null && isProjectAdmin==undefined){//点击的具体的团队，不能再往下级添加
			return;
		}
		layer.open({
	        type: 1,
	        title: '新增团队',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['400px' , '260px'],
	        btn: ['确定', '取消'],
			yes: function(index, layero){ //或者使用btn1
				var groupName=encodeURI($("#teamGroupName").val());
				if(projectId==null){//点击的是项目--新增项目分组，都是管理员级别
					description=encodeURI(teamName);
					proName=encodeURI(teamName);
					groType=$("#systemSel").children('option:selected').val();
				}
			    $.ajax({
		 	          url : '/wbem/system/sysSet/insertTeamGroup.action',//这个就是请求地址对应sAjaxSource
			          data : {groupName:groupName,projectId:proId,isProjectAdmin:isProAdmin,description:description,projectName:proName,groupType:groType},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result ==1){
			          		layer.close(index);
							$.fn.zTree.init($("#commonTree"), setting);
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
	        content: $("#teamGroupDia")
	    }); 
	});
		//修改团队事件
	$("#updateTeamGroup").on('click',function(){
		$("#projectName").val(projectName);
		$("#projectName").attr('disabled',true);
		$("#teamGroupName").val(teamName);
		if(projectId==''){//什么也没点击
			layer.alert("请先选中相应的组");
			return;
		}
		if(projectId==null){//点击的是项目--新增项目分组，都是管理员级别
			layer.alert("项目不能修改");
			return;
		}
		if(isProjectAdmin=='1'){//点击的是项目经理级别 --新增团队，都是组长或组员级别
			$("#systemDisplay").show();
			$("#nameChange").text('名称');
			$("#systemSel").children('option').each(function(index){
				if($(this).val()==groupType){
					$(this).attr("selected", "selected");
				};
			});
		}
		if(projectId !='' && projectId !=null && isProjectAdmin==undefined){//点击的具体的团队，不能再往下级添加
			$("#nameChange").text('组名');
			$("#systemDisplay").hide();
			
		}
		layer.open({
	        type: 1,
	        title: '修改团队',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['400px' , '260px'],
	        btn: ['确定', '取消'],
	        //closeBtn: 1,
			yes: function(index, layero){ //或者使用btn1
				var groupName=encodeURI($("#teamGroupName").val());
			    $.ajax({
		 	          url : '/wbem/system/sysSet/updateTeamGroup.action',//这个就是请求地址对应sAjaxSource
			          data : {teamGroupId:teamGroupId,groupName:groupName},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result !=0){
			          		layer.close(index);
							$.fn.zTree.init($("#commonTree"), setting);
			            }else {
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
	        content: $("#teamGroupDia")
	    }); 
	});
	$("#deleteTeamGroup").on('click',function(){
		if(projectId==''){//什么也没点击
			layer.alert("请先选中相应的组");
			return;
		}
		if(projectId==null){//点击的是项目
			layer.alert("项目不能删除!");
			return;
		}
		layer.confirm('确定要删除该组吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(index){
				  $.ajax({
			 	          url : '/wbem/system/sysSet/deleteTeamGroup.action',//这个就是请求地址对应sAjaxSource
				          data : {teamGroupId:teamGroupId,projectId:projectId,groupType:groupType},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result ==-1){
				          		layer.alert("该组里面还有成员，不能删除！");
				            }else if(result ==-2){
				          		layer.alert("该组里面还有团队小组，不能删除！");
				            }else if(result ==1) {
				            	layer.close(index);
				              	$.fn.zTree.init($("#commonTree"), setting);
				            }else {
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