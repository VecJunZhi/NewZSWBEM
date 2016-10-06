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
				$("#errorMsg").text("原密码不能为空,请重新输入！");
				return;
			}
			var newPsw = $("#newpw").val();
			if(newPsw == "" || newPsw == null){
				$("#errorMsg").text("新密码不能为空,请重新输入！");
				return;
			}
			var confPsw = $("#newpw_ag").val();
			if(confPsw != newPsw){
				$("#errorMsg").text("两次输入的密码不一致,请重新输入！");
				return;
			}
			$.ajax({
                url: "/wbem/index/changePassword.action",
                type:"POST",
                data: {"newPassword":newPsw,"oldPassword":oldPsw,"againPassword":confPsw,"againPassword":confPsw},
                success: function (data) {
                	if(data == "oldPwdFailed"){
                        $("#errorMsg").text("原密码错误,请重新输入！");
                    }
                    if(data == "changeSuccess"){
                        $("#errorMsg").text("密码修改成功！");
                    }
                    if(data == "changeFailed"){
						$("#errorMsg").text("修改密码失败！");
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
        <label class="control-label">用户名 :</label>
        <div class="controls">
          <input type="text" class="span8" value="${user.username}" readonly />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">原密码 :</label>
        <div class="controls">
          <input type="password" id="oldpw" class="span8" placeholder="请输入原密码" autofocus="autofocus" />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">新密码 :</label>
        <div class="controls">
          <input type="password" id="newpw" class="span8" placeholder="请输入新密码"  />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">确认新密码 :</label>
        <div class="controls">
          <input type="password" id="newpw_ag" class="span8" placeholder="确认新密码" />
        </div>
      </div>
      <div class="form-actions">
        <span id="errorMsg" class="span6"></span>
        <span class="span6">
        <button type="button" class="btn btn-success" id="saveBtn">确认修改</button>
        <button type="reset" class="btn btn-success">重设</button></span>
      </div>
    </form>
  </div>
</div>
</div>
</div>

<%-- 	<form name="tform" method="post" action="">
		<div >
			<label for="f_username_txt" class="label"> 用户名 </label>
			<input class="input" type="text" maxlength="50" readonly="readonly" value="${user.username}" />
		</div>
		<div >
			<label for="f_username_txt" class="label"> 原密码 </label>
			<input class="input" type="password" id="oldpw" name="oldpw" maxlength="50" placeholder="请输入原密码" />
		</div>
		<div >
			<label for="f_username_txt" class="label"> 新密码 </label>
			<input class="input" type="password" id="newpw" name="newpw" maxlength="50" placeholder="请办理入新密码" />
		</div>
		<div >
			<label for="f_username_txt" class="label"> 确认新密码 </label> 
			<input class="input" type="password" id="newpw_ag" name="newpw_ag" maxlength="50" placeholder="确认新密码" />
		</div>
		<div class="w200">
			<input type="button" class="anniu" id="saveBtn" value="确认" />
			<button type="reset" class="anniu" >重设</button>
		</div>
		<div >
            <label id="errorMsg" style="display:block;color:red;font-size: 12px;"></label>
        </div>
	</form> --%>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>
