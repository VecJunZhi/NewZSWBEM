<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>�޸�����</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link  rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
    <link rel="stylesheet" href="/mbem/mcrm/house/css/user.css">
    <script  src="/common/js/jquery-1.9.1.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#saveBtn").click(function(){
				var oldPsw = $("#oldpw").val();
				if(oldPsw == "" || oldPsw == null){
					$("#errorMsg").text("ԭ���벻��Ϊ��,���������룡");
					//$("#errorMsg").attr("display","block");
					return;
				}
				var newPsw = $("#newpw").val();
				if(newPsw == "" || newPsw == null){
					$("#errorMsg").text("�����벻��Ϊ��,���������룡");
					return;
				}
				var confPsw = $("#newpw_ag").val();
				if(confPsw != newPsw){
					$("#errorMsg").text("������������벻һ��,���������룡");
					return;
				}
				$.ajax({
	                url: "/mbem/mcrm/business/find/changePassword.action",
	                type:"POST",
	                data: {"newPassword":newPsw,"oldPassword":oldPsw,"againPassword":confPsw},
	                success: function (data) {
	                	if(data == "oldPwdFailed"){
	                        $("#errorMsg").text("ԭ�������,���������룡");
	                    }
	                    if(data == "changeSuccess"){
	                        window.location.href="/mbem/mcrm/business/find/findPage.action";
	                    }
	                    if(data == "changeFailed"){
							$("#errorMsg").text("�޸�����ʧ�ܣ�");
	                    }
	                }
				});	
			});
			$("#newCancel").click(function(){
				window.location.href="/mbem/mcrm/business/find/findPage.action";
			});
		});
	</script>
</head>
<body style="background-color:#FFF">
    <div class="container" style="padding-top: 20px;">
        <form id="appform">
            <div class="form-group">
                <label style="width:65px">ԭ����</label>
                <div class="form-control">
                    <input type="password" id="oldpw" name="oldpw">
                </div>
            </div>
            <div class="form-group">
                <label style="width:65px">������</label>
                <div class="form-control">
                    <input type="password" id="newpw" name="newpw">
                </div>
            </div>
            <div class="form-group">
                <label style="width:65px">�ٴ�����</label>
                <div class="form-control">
                    <input type="password" id="newpw_ag" name="newpw_ag">
                </div>
            </div>
            <div>
            	<label id="errorMsg" style="display:block;color:red"></label>
            </div>
        </form>
    </div>
<div class="powered" id="powered">
Powered&nbsp;by&nbsp;&nbsp;<a href="#">��ʢ�ز�</a>
</div>






<div class="form-group submit-group noswitchSinglePage">
     
       
         <input type="button" class="btns btns-default" id="newCancel" value="ȡ ��" >
            <button type="button" value="ȷ ��" class="btns btns-primary" id="saveBtn">ȷ��</button>
        
        
        
        
    </div>



</body></html>
