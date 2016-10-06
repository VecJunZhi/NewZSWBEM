<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<style>
.w100 {
	height: 20px;
	width: 350px;
	margin-right: auto;
	margin-left: auto;
	padding-top: 10px;
}

.label {
	height: 20px;
	width: 100px;
	float: left;
	font-size: 12px;
	line-height: 25px;
	text-align: center;
	vertical-align: 50%;
	font-family: "Microsoft YaHei UI";
}

.input {
	height: 20px;
	width: 200px;
	float: left;
	border: 1px solid #0a82de;
}

.w200 {
	height: 30px;
	width: 160px;
	margin-right: auto;
	margin-left: auto;
	padding-top: 20px;
}

.anniu {
	cursor: pointer;
	background-color: #0a82de;
	height: 30px;
	width: 60px;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	color: #FFF;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#oldpw").focus();
		$("#saveBtn").click(function(){
			var oldPsw = $("#oldpw").val();
			if(oldPsw == "" || oldPsw == null){
				$("#errorMsg").text("ԭ���벻��Ϊ��,���������룡");
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
                url: "/wbem/index/changePassword.action",
                type:"POST",
                data: {"newPassword":newPsw,"oldPassword":oldPsw,"againPassword":confPsw,"againPassword":confPsw},
                success: function (data) {
                	if(data == "oldPwdFailed"){
                        $("#errorMsg").text("ԭ�������,���������룡");
                    }
                    if(data == "changeSuccess"){
                        $("#errorMsg").text("�����޸ĳɹ���");
                    }
                    if(data == "changeFailed"){
						$("#errorMsg").text("�޸�����ʧ�ܣ�");
                    }
                }
			});	
		});
	});
</script>

  <div class="row-fluid">
    <div class="span12">
<div class="widget-box" style="margin-top:30px">
  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
    <h5></h5>
  </div>
  <div class="widget-content nopadding">
    <form class="form-horizontal">
      <div class="control-group">
        <label class="control-label">�û��� :</label>
        <div class="controls">
          <input type="text" class="span8" value="${user.username}" readonly />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">ԭ���� :</label>
        <div class="controls">
          <input type="password" id="oldpw" class="span8" placeholder="������ԭ����" autofocus="autofocus" />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">������ :</label>
        <div class="controls">
          <input type="password" id="newpw" class="span8" placeholder="������������"  />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">ȷ�������� :</label>
        <div class="controls">
          <input type="password" id="newpw_ag" class="span8" placeholder="ȷ��������" />
        </div>
      </div>
      <div class="form-actions">
        <span id="errorMsg" class="span6"></span>
        <span class="span6">
        <button type="button" class="btn btn-success" id="saveBtn">ȷ���޸�</button>
        <button type="reset" class="btn btn-success">����</button></span>
      </div>
    </form>
  </div>
</div>
</div>
</div>

<%-- 	<form name="tform" method="post" action="">
		<div >
			<label for="f_username_txt" class="label"> �û��� </label>
			<input class="input" type="text" maxlength="50" readonly="readonly" value="${user.username}" />
		</div>
		<div >
			<label for="f_username_txt" class="label"> ԭ���� </label>
			<input class="input" type="password" id="oldpw" name="oldpw" maxlength="50" placeholder="������ԭ����" />
		</div>
		<div >
			<label for="f_username_txt" class="label"> ������ </label>
			<input class="input" type="password" id="newpw" name="newpw" maxlength="50" placeholder="�������������" />
		</div>
		<div >
			<label for="f_username_txt" class="label"> ȷ�������� </label> 
			<input class="input" type="password" id="newpw_ag" name="newpw_ag" maxlength="50" placeholder="ȷ��������" />
		</div>
		<div class="w200">
			<input type="button" class="anniu" id="saveBtn" value="ȷ��" />
			<button type="reset" class="anniu" >����</button>
		</div>
		<div >
            <label id="errorMsg" style="display:block;color:red;font-size: 12px;"></label>
        </div>
	</form> --%>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>
