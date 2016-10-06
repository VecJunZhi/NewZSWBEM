<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<!-- <form class="form-inline form-horizontal"> 
		<div class="row-fluid">
	        <div class="control-group  noborder-top span4">
	          <label class="control-label">�û���</label>
	          <div class="controls">
	            <input class="span11" type="text" id="userName_s" value="" />
	          </div>
	        </div>
	        <div class="span4" style="padding-top:10px;" >
		       <span style="margin-left: 25px;">
		           <button type="button" class="btn btn-success" id="tableSearch">����</button>
		           <button type="button" class=" btn " id="resetSearch">����</button>
		       </span>
	       </div>
		</div>
	 </form> -->
	<!--  <div class="widget-box">
			<div class="widget-title">
				<input type="text" style="margin-bottom:0" id="userName_s" placeholder="�û���">
				<button class="btn btn-success" id="tableSearch">��ѯ</button>
				<button type="button" class="btn btn-success" id="resetSearch">����ɸѡ</button>
	        </div>
	</div> -->
	<div class="widget-box">
		<div class="widget-title">
			<span class="icon"><i class="icon-th"></i></span><h5>�û��б�</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<input type="text" style="margin-bottom:0" id="userName_s" placeholder="�û���">
				<button class="btn btn-info" id="tableSearch">��ѯ</button>
				<button type="button" class="btn btn-info" id="resetSearch">����ɸѡ</button>
				<button type="button" class="btn btn-success"  id="createUser">�����û�</button>
			</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th>���</th>
						<th>����</th>
						<th>�ֻ�����</th>
						<th>�û���</th>
						<th>Email</th>
						<th>�û�״̬</th>
						<th>����״̬</th>
						<th>PSW</th>
						<th>����</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<div id="editCustInfo" class="container-fluid"  hidden="hidden">
	 <form action="#" method="get" class="form-horizontal">
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">��¼��:</label>
	       <div class="controls">
	         <input type="text" id="userName" class="span4" value=""/><strong style="color: red;">&nbsp;*</strong>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">����:</label>
	       <div class="controls">
	         <input type="text" id="realName" class="span4" value="" /><strong style="color: red;">&nbsp;*</strong>
	       </div>
	     </div>
	     <div class="control-group noborder-bottom "> <!-- noborder-bottom -->
	       <label class="control-label">�ֻ�����:</label>
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
	       <label class="control-label">����:</label>
	       <div class="controls">
	         <input type="password" id="password" class="span4" value="" /><strong style="color: red;">&nbsp;*</strong>
	       </div>
	     </div>
	     <!-- <div class="control-group noborder-bottom "> noborder-bottom
	       <label class="control-label">ȷ������ :</label>
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
	//�������Ա
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
	        title: '�����û�',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['600px' , '360px'],
	        btn: ['ȷ��', 'ȡ��'],
			yes: function(index, layero){ //����ʹ��btn1
			    var userName=encodeURI($("#userName").val());
			    var realName=encodeURI($("#realName").val());
			    var mobile=encodeURI($("#mobile").val());
			    var email=encodeURI($("#email").val());
			    var password=encodeURI($("#password").val());
			    if(userName =='' || realName =='' || mobile =='' || password == ''){
			    	layer.alert("�����������(��*��)");
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
					layer.alert("�����������벻һ��");
					return;
				} */
				createUser(index,userName, realName, password, mobile, email);
			},
			cancel: function(index){ //����ʹ��btn2
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
	        title: '�༭�û�',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['600px' , '360px'],
	        btn: ['ȷ��', 'ȡ��'],
	        //closeBtn: 1,
			yes: function(index, layero){ //����ʹ��btn1
			    //�ж��ֶ��Ƿ�Ϊ�գ��Ƿ����Ҫ��
			    /* var data={id:id,proId:proId,teamGroupId:teamGroupId,userLevelId:userlevelId};
			    data=JSON.stringify(data); */
			    var userName=encodeURI($("#userName").val());
			    var realName=encodeURI($("#realName").val());
			    var mobile=encodeURI($("#mobile").val());
			    var email=encodeURI($("#email").val());
			    var password=encodeURI($("#password").val());
			    if(userName =='' || realName =='' || mobile =='' || password == ''){
			    	layer.alert("�����������(��*��)");
			    	return;
			    }
			    /* var validPsw=encodeURI($("#validPsw").val());
			    if(password !=validPsw){
					layer.alert("�����������벻һ��");
					return;
				} */
				if(userId !='' && userId != 'null'){
					updateUser(index,userId,userName, realName, password, mobile, email);
				}
			},
			cancel: function(index){ //����ʹ��btn2
			    //
			},
	        content: $("#editCustInfo")
	    });
	    $("#userName").focus();
	}
	function createUser(index,username,realName,password,mobile,email){
		$.ajax({
	 	          url : '/wbem/system/sysSet/createUser.action',//������������ַ��ӦsAjaxSource
		          data : {userName:username,realName:realName,password:password,mobile:mobile,email:email},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
		          type : 'post',
		          dataType : 'json',
		          success : function(result) {          
		          	if(result !=0){
						table.draw();
						layer.close(index);
		            }else {
		              	layer.alert("����ʧ��");
		            }
		          },
		          error : function(msg) {
		          	layer.alert("ϵͳ�쳣����ϵ����Ա");
		          }
		});
	
	}
	function updateUser(index,userId,username,realName,password,mobile,email){
		$.ajax({
	 	          url : '/wbem/system/sysSet/updateUser.action',//������������ַ��ӦsAjaxSource
		          data : {userId:userId,userName:username,realName:realName,password:password,mobile:mobile,email:email},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
		          type : 'post',
		          dataType : 'json',
		          success : function(result) {          
		          	if(result !=0){
						table.draw();
						layer.close(index);
		            }else {
		              	layer.alert("�༭ʧ��");
		            }
		          },
		          error : function(msg) {
		          	layer.alert("ϵͳ�쳣����ϵ����Ա");
		          }
		});
	
	}
	
	function updateUserStatus(userId,uStatus,realName){
			var msg='';
			if(uStatus==1){
				uStatus=0;
				msg='��ȷ��Ҫ����'+realName+'����ϵͳ��¼��';
			}else if(uStatus==0){
				uStatus=1;
				msg='��ȷ��Ҫ����'+realName+'����ϵͳ��¼��';
			}
			layer.confirm(msg, {
			  btn: ['ȷ��','ȡ��'] //��ť
			}, function(index){
			  $.ajax({
		 	          url : '/wbem/system/sysSet/updateUserStatus.action',//������������ַ��ӦsAjaxSource
			          data : {userId:userId,uStatus:uStatus},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result !=0){
							table.draw();
							layer.close(index);
			            }else {
			              	layer.alert("�༭ʧ��");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("ϵͳ�쳣����ϵ����Ա");
			          }
				});
			}, function(){
			  
			});
	}
	function updateUserLocked(userId,locked,realName){
			var msg='';
			if(locked=='true'){
				locked=false;
				msg='��ȷ��Ҫ���'+realName+'������״̬��';
			}else if(locked=='false'){
				locked=true;
				msg='��ȷ��Ҫ����'+realName+'��״̬��';
			}
			layer.confirm(msg, {
			  btn: ['ȷ��','ȡ��'] //��ť
			}, function(index){
			  $.ajax({
		 	          url : '/wbem/system/sysSet/updateUserLocked.action',//������������ַ��ӦsAjaxSource
			          data : {userId:userId,locked:locked},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
			          type : 'post',
			          dataType : 'json',
			          success : function(result) {          
			          	if(result !=0){
							table.draw();
							layer.close(index);
			            }else {
			              	layer.alert("�༭ʧ��");
			            }
			          },
			          error : function(msg) {
			          	layer.alert("ϵͳ�쳣����ϵ����Ա");
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
			 	          url : '/wbem/system/sysSet/judgeIfExistUserName.action',//������������ַ��ӦsAjaxSource
				          data : {userName:userName},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								//layer.alert("���û����Ѿ�����");
								flg=false;
								layer.msg('���û����Ѿ�����', {time: 2000});
								$("#userName").focus();
				            }else {
				              	flg=true;
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�쳣����ϵ����Ա");
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
			 	          url : '/wbem/system/sysSet/judgeIfExistMobile.action',//������������ַ��ӦsAjaxSource
				          data : {mobile:mobile},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								//layer.alert("���û����Ѿ�����");
								mobileflg=false;
								layer.msg('���ֻ����Ѿ�����', {time: 2000});
								//$("#mobile").focus();
				            }else {
				              	mobileflg=true;
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�쳣����ϵ����Ա");
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
			 	          url : '/wbem/system/sysSet/judgeIfExistEmail.action',//������������ַ��ӦsAjaxSource
				          data : {email:email},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
				          type : 'post',
				          dataType : 'json',
				          success : function(result) {          
				          	if(result !=0){
								emailflg=false;
								layer.msg('�������Ѿ�����', {time: 2000});
				            }else {
				              	emailflg=true;
				            }
				          },
				          error : function(msg) {
				          	layer.alert("ϵͳ�쳣����ϵ����Ա");
				          }
					});
			}
		return emailflg;
	}
$(document).ready(function(){
	 table=$('.data-table').DataTable({
		"ajax": {type: "post",//��ָ̨���˷�ʽ��
                 url: "/wbem/system/sysSet/getUser.action",
                 data:function(d){			//�ⲿ��������
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
			{"data":"password"},
			{"data":"userID","render":function(data, type, row, meta){
					var edit='<a href="#"  style="color:#FAA732" onclick="editUserMember(\''+row.userID+'\',\''+row.userName+'\',\''+row.realName+'\',\''+row.mobile+'\',\''+row.email+'\',\''+row.password+'\')">�༭</a>&nbsp;&nbsp;';
					var using='<a href="#"  style="color:#FAA732" onclick="updateUserStatus(\''+row.userID+'\',\''+row.uStatus+'\',\''+row.realName+'\')">����</a>&nbsp;&nbsp;';
					var unusing='<a href="#" style="color:#FAA732"  onclick="updateUserStatus(\''+row.userID+'\',\''+row.uStatus+'\',\''+row.realName+'\')">����</a>&nbsp;&nbsp;';
					var locked='<a href="#"  style="color:#FAA732" onclick="updateUserLocked(\''+row.userID+'\',\''+row.locked+'\',\''+row.realName+'\')">����</a>&nbsp;&nbsp;';
					var unlocked='<a href="#" style="color:#FAA732"  onclick="updateUserLocked(\''+row.userID+'\',\''+row.locked+'\',\''+row.realName+'\')">����</a>&nbsp;&nbsp;';
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