<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<!-- <form class="form-inline form-horizontal"> 
		<div class="row-fluid">
	        <div class="control-group  noborder-top span4">
	          <label class="control-label">用户名</label>
	          <div class="controls">
	            <input class="span11" type="text" id="userName_s" value="" />
	          </div>
	        </div>
	        <div class="span4" style="padding-top:10px;" >
		       <span style="margin-left: 25px;">
		           <button type="button" class="btn btn-success" id="tableSearch">搜索</button>
		           <button type="button" class=" btn " id="resetSearch">重置</button>
		       </span>
	       </div>
		</div>
	 </form> -->
	<!--  <div class="widget-box">
			<div class="widget-title">
				<input type="text" style="margin-bottom:0" id="userName_s" placeholder="用户名">
				<button class="btn btn-success" id="tableSearch">查询</button>
				<button type="button" class="btn btn-success" id="resetSearch">撤销筛选</button>
	        </div>
	</div> -->
	<div class="widget-box">
		<div class="widget-title">
			<span class="icon"><i class="icon-th"></i></span><h5>用户列表</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<input type="text" style="margin-bottom:0" id="userName_s" placeholder="用户名">
				<button class="btn btn-info" id="tableSearch">查询</button>
				<button type="button" class="btn btn-info" id="resetSearch">撤销筛选</button>
				<button type="button" class="btn btn-success"  id="createUser">新增用户</button>
			</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th>序号</th>
						<th>姓名</th>
						<th>手机号码</th>
						<th>用户名</th>
						<th>Email</th>
						<th>用户状态</th>
						<th>锁定状态</th>
						<th>PSW</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<div id="editCustInfo" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">登录名:</label>
	       <div class="controls">
	         <input type="text" id="userName" class="span4" value=""/><strong style="color: red;">&nbsp;*</strong>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">姓名:</label>
	       <div class="controls">
	         <input type="text" id="realName" class="span4" value="" /><strong style="color: red;">&nbsp;*</strong>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">手机号码:</label>
	       <div class="controls">
	         <input type="text" id="mobile" class="span4" value="" /><strong style="color: red;">&nbsp;*</strong>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">Email:</label>
	       <div class="controls">
	         <input type="text" id="email" class="span4" value="" />
	       </div>
	     </div>
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">密码:</label>
	       <div class="controls">
	         <input type="password" id="password" class="span4" value="" /><strong style="color: red;">&nbsp;*</strong>
	       </div>
	     </div>
	     <!-- <div class="control-group noborder-bottom "> noborder-bottom
	       <label class="control-label">确认密码 :</label>
	       <div class="controls">
	         <input type="text" id="validPsw" class="span4" value="" />
	       </div>
	     </div> -->
     </form>
</div>



<%@include file="/wbem/include/include_base_js.jsp"%>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script>
var table=null;
var gMobile='';
var gEmail='';
	//新增组成员
	function createUserMember(){
		$("#userName").val('');
		$("#userName").attr("disabled",false);
		$("#realName").val('');
		$("#mobile").val('');
		$("#email").val('');
		$("#password").val('');
		//$("#validPsw").val('');
		layer.open({
	        type: 1,
	        title: '新增用户',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['600px' , '360px'],
	        btn: ['确定', '取消'],
			yes: function(index, layero){ //或者使用btn1
			    var userName=encodeURI($("#userName").val());
			    var realName=encodeURI($("#realName").val());
			    var mobile=encodeURI($("#mobile").val());
			    var email=encodeURI($("#email").val());
			    var password=encodeURI($("#password").val());
			    if(userName =='' || realName =='' || mobile =='' || password == ''){
			    	layer.alert("请输入必填项(带*号)");
			    	return ;
			    }
			    var bool=judgeIfExistUserName(userName);
			    if(!bool){
			     	return;
			    }
			    var bool_mobile=judgeIfExistMobile(mobile);
			    if(!bool_mobile){
			     	return;
			    }
			    var bool_email=judgeIfExistEmail(email);
			    if(!bool_email){
			     	return;
			    }
			   /*  var validPsw=encodeURI($("#validPsw").val());
			    if(password !=validPsw){
					layer.alert("两次密码输入不一致");
					return;
				} */
				createUser(index,userName, realName, password, mobile, email);
			},
			cancel: function(index){ //或者使用btn2
			    //
			},
	        content: $("#editCustInfo")
	    });
	    $("#userName").focus();
	}
	function editUserMember(userId,userName,realName,mobile,email,password){
		email=email=='null'?'':email;
		gMobile=mobile;
		gEmail=email;
		$("#userName").val(userName);
		$("#userName").attr("disabled",true);
		$("#realName").val(realName);
		$("#mobile").val(mobile);
		$("#email").val(email);
		$("#password").val(password);
		layer.open({
	        type: 1,
	        title: '编辑用户',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['600px' , '360px'],
	        btn: ['确定', '取消'],
	        //closeBtn: 1,
			yes: function(index, layero){ //或者使用btn1
			    //判断字段是否为空，是否符合要求
			    /* var data={id:id,proId:proId,teamGroupId:teamGroupId,userLevelId:userlevelId};
			    data=JSON.stringify(data); */
			    var userName=encodeURI($("#userName").val());
			    var realName=encodeURI($("#realName").val());
			    var mobile=encodeURI($("#mobile").val());
			    var email=encodeURI($("#email").val());
			    var password=encodeURI($("#password").val());
			    if(userName =='' || realName =='' || mobile =='' || password == ''){
			    	layer.alert("请输入必填项(带*号)");
			    	return;
			    }
			    /* var validPsw=encodeURI($("#validPsw").val());
			    if(password !=validPsw){
					layer.alert("两次密码输入不一致");
					return;
				} */
				if(userId !='' && userId != 'null'){
					updateUser(index,userId,userName, realName, password, mobile, email);
				}
			},
			cancel: function(index){ //或者使用btn2
			    //
			},
	        content: $("#editCustInfo")
	    });
	    $("#userName").focus();
	}
	function createUser(index,username,realName,password,mobile,email){
		$.ajax({
	 	          url : '/wbem/system/sysSet/createUser.action',//这个就是请求地址对应sAjaxSource
		          data : {userName:username,realName:realName,password:password,mobile:mobile,email:email},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
		          type : 'post',
		          dataType : 'json',
		          success : function(result) {          
		          	if(result !=0){
						table.draw();
						layer.close(index);
		            }else {
		              	layer.alert("保存失败");
		            }
		          },
		          error : function(msg) {
		          	layer.alert("系统异常，联系管理员");
		          }
		});
	
	}
	function updateUser(index,userId,username,realName,password,mobile,email){
		$.ajax({
	 	          url : '/wbem/system/sysSet/updateUser.action',//这个就是请求地址对应sAjaxSource
		          data : {userId:userId,userName:username,realName:realName,password:password,mobile:mobile,email:email},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
		          type : 'post',
		          dataType : 'json',
		          success : function(result) {          
		          	if(result !=0){
						table.draw();
						layer.close(index);
		            }else {
		              	layer.alert("编辑失败");
		            }
		          },
		          error : function(msg) {
		          	layer.alert("系统异常，联系管理员");
		          }
		});
	
	}
	
	function updateUserStatus(userId,uStatus,realName){
			var msg='';
			if(uStatus==1){
				uStatus=0;
				msg='你确定要禁用'+realName+'所有系统登录吗？';
			}else if(uStatus==0){
				uStatus=1;
				msg='你确定要启用'+realName+'所有系统登录吗？';
			}
			layer.confirm(msg, {
			  btn: ['确定','取消'] //按钮
			}, function(index){
			  $.ajax({
		 	          url : '/wbem/system/sysSet/updateUserStatus.action',//这个就是请求地址对应sAjaxSource
			          data : {userId:userId,uStatus:uStatus},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result !=0){
							table.draw();
							layer.close(index);
			            }else {
			              	layer.alert("编辑失败");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("系统异常，联系管理员");
			          }
				});
			}, function(){
			  
			});
	}
	function updateUserLocked(userId,locked,realName){
			var msg='';
			if(locked=='true'){
				locked=false;
				msg='你确定要解除'+realName+'的锁定状态？';
			}else if(locked=='false'){
				locked=true;
				msg='你确定要锁定'+realName+'的状态？';
			}
			layer.confirm(msg, {
			  btn: ['确定','取消'] //按钮
			}, function(index){
			  $.ajax({
		 	          url : '/wbem/system/sysSet/updateUserLocked.action',//这个就是请求地址对应sAjaxSource
			          data : {userId:userId,locked:locked},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result !=0){
							table.draw();
							layer.close(index);
			            }else {
			              	layer.alert("编辑失败");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("系统异常，联系管理员");
			          }
				});
			}, function(){
			
			});
	}
	$("#userName").blur(function(){
		var uName=$("#userName").val();
		judgeIfExistUserName(uName);
	});
	var  flg=true;
	function  judgeIfExistUserName(userName){
			userName=encodeURI(userName);
			if(userName!=null && userName !=''){
				$.ajax({
			 	          url : '/wbem/system/sysSet/judgeIfExistUserName.action',//这个就是请求地址对应sAjaxSource
				          data : {userName:userName},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								//layer.alert("改用户名已经存在");
								flg=false;
								layer.msg('该用户名已经存在', {time: 2000});
								$("#userName").focus();
				            }else {
				              	flg=true;
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统异常，联系管理员");
				          }
					});
			}
		return flg;
	}
	$("#mobile").blur(function(){
		var mobile=$("#mobile").val();
		if(gMobile==mobile){return;}
		judgeIfExistMobile(mobile);
	});
	var  mobileflg=true;
	function  judgeIfExistMobile(mobile){
			//userName=encodeURI(userName);
			if(mobile!=null && mobile !=''){
				$.ajax({
			 	          url : '/wbem/system/sysSet/judgeIfExistMobile.action',//这个就是请求地址对应sAjaxSource
				          data : {mobile:mobile},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								//layer.alert("改用户名已经存在");
								mobileflg=false;
								layer.msg('该手机号已经存在', {time: 2000});
								//$("#mobile").focus();
				            }else {
				              	mobileflg=true;
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统异常，联系管理员");
				          }
					});
			}
		return mobileflg;
	}
	$("#email").blur(function(){
		var email=$("#email").val();
		if(gEmail==email){return;}
		judgeIfExistEmail(email);
	});
	var  emailflg=true;
	function  judgeIfExistEmail(email){
			if(email !=null && email !=''){
				$.ajax({
			 	          url : '/wbem/system/sysSet/judgeIfExistEmail.action',//这个就是请求地址对应sAjaxSource
				          data : {email:email},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								emailflg=false;
								layer.msg('该邮箱已经存在', {time: 2000});
				            }else {
				              	emailflg=true;
				            }
				          },
				          error : function(msg) {
				          	layer.alert("系统异常，联系管理员");
				          }
					});
			}
		return emailflg;
	}
$(document).ready(function(){
	 table=$('.data-table').DataTable({
		"ajax": {type: "post",//后台指定了方式。
                 url: "/wbem/system/sysSet/getUser.action",
                 data:function(d){			//外部参数传递
                 	d.userName = encodeURI($('#userName_s').val());
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
			{"data":"password"},
			{"data":"userID","render":function(data, type, row, meta){
					var edit='<a href="#"  style="color:#FAA732" onclick="editUserMember(\''+row.userID+'\',\''+row.userName+'\',\''+row.realName+'\',\''+row.mobile+'\',\''+row.email+'\',\''+row.password+'\')">编辑</a>&nbsp;&nbsp;';
					var using='<a href="#"  style="color:#FAA732" onclick="updateUserStatus(\''+row.userID+'\',\''+row.uStatus+'\',\''+row.realName+'\')">启用</a>&nbsp;&nbsp;';
					var unusing='<a href="#" style="color:#FAA732"  onclick="updateUserStatus(\''+row.userID+'\',\''+row.uStatus+'\',\''+row.realName+'\')">禁用</a>&nbsp;&nbsp;';
					var locked='<a href="#"  style="color:#FAA732" onclick="updateUserLocked(\''+row.userID+'\',\''+row.locked+'\',\''+row.realName+'\')">锁定</a>&nbsp;&nbsp;';
					var unlocked='<a href="#" style="color:#FAA732"  onclick="updateUserLocked(\''+row.userID+'\',\''+row.locked+'\',\''+row.realName+'\')">解锁</a>&nbsp;&nbsp;';
					//alert(row.uStatus +" "+row.locked);
					if(row.uStatus==1 && row.locked=="true"){
						return edit+unusing+unlocked;
					}else if(row.uStatus==1 && row.locked=="false"){
						return edit+unusing+locked;
					}else if(row.uStatus==0 && row.locked=="true"){
						return edit+using+unlocked;
					}else if(row.uStatus==0 && row.locked=="false"){
						return edit+using+locked;
					}
			}},
			
		],
          columnDefs:[{
				"targets": [7],
				"visible": false,
	         }]  
	});
	$('select').select2();
	$("#tableSearch").click(function(){
		table.draw();
	});

	$("#resetSearch").click(function(){
		$('#userName_s').val("");
		table.draw();
	});
	$("#createUser").click(function(){
		createUserMember();
	});
	
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>