<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.min.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/ui.css">
<script  src="/common/js/jquery-1.9.1.min.js"></script>
<script  src="/mbem/mcrm/business/js/ui.js"></script>
<script  src="/common/js/layer/layer.js"></script> 
<title>�л���Ŀ</title>
    <script >
    	$(document).ready(function(){
    		$("#search_input").click(function(){
    			history.back();
    		});
    	});
    	
    	function checkAllowCopy(projName,projGuid,cstGuid,oppGuid){
    		$.ajax({
    			url:"/mbem/mcrm/house/customer/checkAllowCopy.action",
    			type:"post",
    			data:{"projGuid":projGuid,"cstGuid":cstGuid,"oppGuid":oppGuid},
    			dataType:"json",
    			success:function(data){
	    			switch(parseInt(data.cst_type)){
		    			case 0:
		                    MYSOFT.Ui.Popup.ShowConfirmRegs('��ʾ', projName+'��Ŀ�¸ÿͻ������ڣ������ƻ�����Ϣ��', function(){
		                        window.location.href = data.redirect_url;
		                        return false;
		                    }, function(){}, '����');
		                    $('#popup_btn_container a').css('width', '50%');
		                break;
		                case 1:
		                    MYSOFT.Ui.Popup.ShowAlert('��ʾ', projName+'��Ŀ�¸ÿͻ����������¡�',function(){
		                    	window.location.href = data.redirect_url;
		                    	return false;
		                    },'��֪����');
		                break;
		                case 2:
		                	if(parseInt(data.is_show_saler_name)==0){
		                    	var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;">�ͻ���<span style="color:#333;">' + data.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">'+projName+'��Ŀ�¸ÿͻ��ѱ�����ҵ��Ա������</p>';
		                        MYSOFT.Ui.Popup.ShowAlert('�ͻ�������ѯ ', _content,
		                                function(){}, '��֪����');
		                	}else{//�����
		                       	var _content = '<p style="text-align:left;font-size:15px;color:#000;margin-top:10px;">'+projName+'��Ŀ�¸ÿͻ��ѱ�ҵ��Ա <span style="color:#333;">' + data.user_name + '</span> ������</p>';
		                        MYSOFT.Ui.Popup.ShowAlert('��ʾ ',_content,function(){
		                                    window.location.href = data.tel;
		                                    return false;
		                                },
		                                 '��֪����');
		                    }
		                break;
		                case 3:
		                    if(parseInt(data.isown)==0 && data.allow_gj == "1"){//��if
		                        MYSOFT.Ui.Popup.ShowAlert("��ʾ",projName+'��Ŀ�¸ÿͻ�Ϊ�����ͻ������л���Ŀ����и�����',function(){
		                        	alert(data.stageName);
		                        	window.location.href = data.stageName;
		                        	return false;
		                        },'��֪����');
		                    }else {
		                    	MYSOFT.Ui.Popup.ShowAlert('�ͻ�������ѯ ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;">�ͻ���<span style="color:#333;">' + data.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">'+projName+'��Ŀ�¸ÿͻ�Ϊ�����ͻ�������ϵ���۾������</p>',
		    		            function(){}, '��֪����');
		                    } 
		                break;
		                case 4:
		                    MYSOFT.Ui.Popup.ShowAlert('�ͻ�������ѯ ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">'+projName+'��Ŀ�¸ÿͻ�Ϊ������ͻ�������ϵ���۾�����</p>',
		                            function(){}, '��֪����');
		                break;
	    			}
    			},
    			error:function(){
    				
    			}
    		});
    	}
    </script>
</head>
<body id="find_index_body">
	<c:if test="${flag == '2'}">
		<c:if test="${fn:length(projectList) == 1}">
			<div class="xuanzhe" style="border-left-width:0;">û�п��Կ�������ĿȨ�ޣ�</div> 
			<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="ȷ��" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
	       	</div>
		</c:if>
		<c:if test="${fn:length(projectList) != 1 }">
		 <div class="xuanzhe">��ѡ��Ҫ����������Ŀ��</div> 
		 <div class="find_index_box clearfix" style="position: relative;">
	     	<c:forEach var="project" items="${projectList}">
	     	<c:if test="${project.id != projGuid}">
	       	<div class="find_group clearfix">
	            <a class="switchgroup" href="#" onclick="return checkAllowCopy('${project.name}','${project.id}','${cstGuid}','${oppGuid}')">
	                <i class="projectCard"></i>
	                <div class="a_border_half" >
	                    <span class="tit">${project.name}</span>
	                    <!-- <span class="name">�������</span> -->
	                    <span class="arrow"></span>
	                </div>
	            </a>
	        </div>
	        </c:if>
	        </c:forEach>
	       	<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="����" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
	       	</div>
	    </div>
	    </c:if>
	</c:if>
	<c:if test="${flag == '1'}">
		<c:if test="${fn:length(projectList) == 1}">
	  		<div class="xuanzhe" style="border-left-width:0;">û�п����л�����Ŀ��</div>
	  		<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="ȷ��" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
	     	</div> 
	    </c:if>
	    <c:if test="${fn:length(projectList) != 1}">
	    <div class="find_index_box clearfix" style="position: relative;">
	    	<c:forEach var="project" items="${projectList}">
	     	<c:if test="${project.id != projGuid}">
	       	<div class="find_group clearfix">
	            <a class="switchgroup" href="/mbem/mcrm/house/find/sureSelectProject.action?projGuid=${project.id}">
	                <i class="switch"></i>
	                <div class="a_border_half" >
	                    <span class="tit">${project.name}</span>
	                    <span class="name">�������</span>
	                    <span class="arrow"></span>
	                </div>
	            </a>
	        </div>
	        </c:if>
	        </c:forEach>
	       	<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="����" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
	       	</div>
	    </div>
	    </c:if>
    </c:if>
</body>
</html>
