<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">   
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">  
<title>��������</title>
<link rel="Stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link rel="Stylesheet" href="/mbem/mcrm/house/css/user.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/date.css">
<script src="/common/js/jquery-1.9.1.min.js" ></script>
<script src="/common/js/bootstrap/bootstrap.min.js"></script>
<script src="/mbem/mcrm/house/js/date.js" ></script>
<script src="/mbem/mcrm/house/js/iscroll.js" ></script>
<script  src="/common/js/layer/layer.js"></script>
<script>
	$(function(){
		$('#beginTime').date();
		$('#endTime').date({theme:"datetime"});
	});
	$(document).ready(function(){	
		$("label").click(function(){
			var id = $(this).children().attr("name");
			var val = $(this).children().val();
			$("#"+id).val(val);
			
		});
		
		
		var allowSubmit = true;
		$("#submit").click(function(){
			if($("#gjfs").val() == "" || $("#gfyx").val()=="" || $("#gjContent").val()=="" || $("#beginTime").val()=="") {
				layer.alert("�뽫��������д����!");
				return false;
			}
			if($("#gjContent").val().length > 50){
				layer.alert("�뽫��������������50���ڣ�");
				return false;
			}
			var index = layer.load(0, {
				shade: [0.1,'#fff'] //0.1͸���ȵİ�ɫ����
			});
			if(allowSubmit == true) {
				allowSubmit = false;
			}else {
				return false;
			}
		});
		
		$("#cancle").click(function(){
			var type = $(this).attr("closeType");
			if(type ==1) {
				window.location.href = "/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid=${cstGuid}&oppGuid=${oppGuid}";
			}
			if(type == 2) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
			if(type == 3){
				window.location.href = "/mbem/mcrm/house/customer/customPage.action";
			}
		});
	});
</script>
<style>
#rapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>    
<!--<style>
.c{
	display: block;
	-webkit-box-flex: 0;
	font-size: 13px;
	width: 30%;
	height: 38px;
	line-height: 38px;
	border-radius: 3px;
	border: 1px solid #ddd;
	text-align: center;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	float: left;
	margin-bottom: 10px;
	margin-right: 6px;
}
.c:active{background-color:#3c6;border-color:#3c6;color:#fff;}
.c:hover:not{background-color:#3c6;border-color:#3c6;color:#fff;}


.xz{
	display: block;
	-webkit-box-flex: 0;
	font-size: 13px;
	width: 100%;
	height: 38px;
	line-height: 38px;
	border-radius: 3px;

	text-align: center;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	float: left;
	
}
.xz:active{background-color:#3c6;border-color:#3c6;color:#fff;}
.xz:hover:not{background-color:#3c6;border-color:#3c6;color:#fff;}

.btn {

display: block;
	-webkit-box-flex: 0;
	font-size: 13px;
	width: 30%;
	height: 38px;
	line-height: 38px;
	border-radius: 3px;
	border: 1px solid #ddd;
	text-align: center;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	float: left;
	margin-bottom: 10px;
	margin-right: 10px;
 	 font-weight: normal;
  	text-align: center;
  	white-space: nowrap;
  	vertical-align: middle;
  	-ms-touch-action: manipulation;
      touch-action: manipulation;
  	cursor: pointer;
  	-webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
}
 .btn.active {
  background-color:#3c6;border-color:#3c6;color:#fff;
}
</style>  
-->
</head>
<body style="min-height:680px; height:100%;">

    <form class="form-content" id="form-content" action="/mbem/mcrm/house/customer/insertFollowInfo.action?cstGuid=${cstGuid}&oppGuid=${oppGuid}&type=${closeType}" method="post">
 		<div class="switchSinglePage-wrap">
 			<div class="switchSinglePage panel" style="-webkit-transform: translate3d(0%, 0px, 0px);">
					<div class="form-group border-1px first">
						<ul class="border-1px">
 							<%-- <c:forEach items="${xsAddFollowMap.option}" var=" optionInfo" varStatus="status">
							<li class="border-1px">
 								<div class="li-inner border-1px">
									<div class="select-head">${optionInfo.name}</div>
   									<input type="hidden"  name="${optionInfo.tagname}">
									<div id="btgroup1" stype="single"  data-toggle="buttons">
    									<c:forEach items="${optionInfo.value}" var="follow" varStatus="status">
											<label class="btn" >
   											<input type="radio" value="${follow.selectName}" name="${optionInfo.tagname}">${follow.selectName}
		 								</label>
										</c:forEach>
									</div>
								</div>
    						</li>
       						</c:forEach> --%>
       						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					            <div class="li-inner border-1px">
					                <div class="select-head">������ʽ</div>
					                <input type='hidden' id='gjfs'>
									<div id="btgroup1" stype="single"  data-toggle="buttons">
										<label class="btn" >
   											<input type="radio" value="ȥ��" name="gjfs">ȥ��
		 								</label>
		 								<label class="btn" >
   											<input type="radio" value="����" name="gjfs">����
		 								</label>
		 								<label class="btn" >
   											<input type="radio" value="����" name="gjfs">����
		 								</label>
		 								<label class="btn" >
   											<input type="radio" value="����" name="gjfs">����
		 								</label>
									</div>
					           </div>
							</li>
							<li class="border-1px" data-require="1">
					            <div class="li-inner border-1px">
					                <div class="select-head">���򼶱�</div>
					                <input type="hidden" id="gfyx">
									<div id="btgroup1" stype="single"  data-toggle="buttons">
										<label class="btn" >
   											<input type="radio" value="������" name="gfyx">������
		 								</label>
		 								<label class="btn" >
   											<input type="radio" value="һ������" name="gfyx">һ������
		 								</label>
		 								<label class="btn" >
   											<input type="radio" value="������" name="gfyx">������
		 								</label>
		 								<label class="btn" >
   											<input type="radio" value="����" name="gfyx">����
		 								</label>
									</div>
					           </div>
							</li>
    						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					            <div class="li-inner border-1px">
					                <span class="k">��������</span>
					                <span class="v"> 
					                <textarea type="text" placeholder="������" value="<%-- ${followInfo} --%>" maxlength="100"  style="height: 26px; -webkit-user-modify: read-write-plaintext-only;" name="gjContent" id="gjContent"></textarea>
 
					                </span>
					           </div>
							</li>
						    <li class="border-1px" data-require="1">
						     	<div class="li-inner border-1px">
						            <span class="k">�´θ���</span>
						            <span class="v">
						                     <!--    <input type="text" value="${nextFollowDate}" name="nextFollowDate"  onFocus="CalendarWebControl.show(this,true,this.value);">  -->
						        <div class="clearfix dome3_box">
						        <input  id="beginTime" class="kbtn" name="nextDate" value="${defaultNextFollowDate}"/>
						                </span>
						       </div>            
							</li>
						</ul>
					</div>
			</div>
            <div class="form-group noswitchSinglePage" style="display:-webkit-box;width:100%; margin-top:10px;">
         
         <button class="btns btns-default" closeType="${closeType}" id="cancle" data-ac="active" type="button">ȡ��</button>
        <button class="btns btns-primary" id="submit" data-ac="active" type="submit">����</button>
    </div>
		</div>
        
         
         <div id="datePlugin"></div>
         
                  <script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#rapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="rapper"></div>');
    }();
</script>
        
  </form>

</body></html>