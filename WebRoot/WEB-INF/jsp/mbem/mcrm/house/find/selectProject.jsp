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
<title>切换项目</title>
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
		                    MYSOFT.Ui.Popup.ShowConfirmRegs('提示', projName+'项目下该客户不存在，请完善机会信息？', function(){
		                        window.location.href = data.redirect_url;
		                        return false;
		                    }, function(){}, '完善');
		                    $('#popup_btn_container a').css('width', '50%');
		                break;
		                case 1:
		                    MYSOFT.Ui.Popup.ShowAlert('提示', projName+'项目下该客户已在您名下。',function(){
		                    	window.location.href = data.redirect_url;
		                    	return false;
		                    },'我知道了');
		                break;
		                case 2:
		                	if(parseInt(data.is_show_saler_name)==0){
		                    	var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;">客户：<span style="color:#333;">' + data.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">'+projName+'项目下该客户已被其他业务员跟进。</p>';
		                        MYSOFT.Ui.Popup.ShowAlert('客户归属查询 ', _content,
		                                function(){}, '我知道了');
		                	}else{//用这个
		                       	var _content = '<p style="text-align:left;font-size:15px;color:#000;margin-top:10px;">'+projName+'项目下该客户已被业务员 <span style="color:#333;">' + data.user_name + '</span> 跟进。</p>';
		                        MYSOFT.Ui.Popup.ShowAlert('提示 ',_content,function(){
		                                    window.location.href = data.tel;
		                                    return false;
		                                },
		                                 '我知道了');
		                    }
		                break;
		                case 3:
		                    if(parseInt(data.isown)==0 && data.allow_gj == "1"){//走if
		                        MYSOFT.Ui.Popup.ShowAlert("提示",projName+'项目下该客户为公共客户，请切换项目后进行跟进。',function(){
		                        	alert(data.stageName);
		                        	window.location.href = data.stageName;
		                        	return false;
		                        },'我知道了');
		                    }else {
		                    	MYSOFT.Ui.Popup.ShowAlert('客户归属查询 ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;">客户：<span style="color:#333;">' + data.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">'+projName+'项目下该客户为公共客户，请联系销售经理分配</p>',
		    		            function(){}, '我知道了');
		                    } 
		                break;
		                case 4:
		                    MYSOFT.Ui.Popup.ShowAlert('客户归属查询 ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">'+projName+'项目下该客户为垃圾箱客户，请联系销售经理处理！</p>',
		                            function(){}, '我知道了');
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
			<div class="xuanzhe" style="border-left-width:0;">没有可以拷贝的项目权限！</div> 
			<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="确定" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
	       	</div>
		</c:if>
		<c:if test="${fn:length(projectList) != 1 }">
		 <div class="xuanzhe">请选择要拷贝到的项目：</div> 
		 <div class="find_index_box clearfix" style="position: relative;">
	     	<c:forEach var="project" items="${projectList}">
	     	<c:if test="${project.id != projGuid}">
	       	<div class="find_group clearfix">
	            <a class="switchgroup" href="#" onclick="return checkAllowCopy('${project.name}','${project.id}','${cstGuid}','${oppGuid}')">
	                <i class="projectCard"></i>
	                <div class="a_border_half" >
	                    <span class="tit">${project.name}</span>
	                    <!-- <span class="name">点击拷贝</span> -->
	                    <span class="arrow"></span>
	                </div>
	            </a>
	        </div>
	        </c:if>
	        </c:forEach>
	       	<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="返回" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
	       	</div>
	    </div>
	    </c:if>
	</c:if>
	<c:if test="${flag == '1'}">
		<c:if test="${fn:length(projectList) == 1}">
	  		<div class="xuanzhe" style="border-left-width:0;">没有可以切换的项目！</div>
	  		<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="确定" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
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
	                    <span class="name">点击进入</span>
	                    <span class="arrow"></span>
	                </div>
	            </a>
	        </div>
	        </c:if>
	        </c:forEach>
	       	<div class="button-box" style=" width:100%;position:fixed;bottom:0;"> 
	        	<div class="btn-wrap"><input type="submit" class="btn" id="search_input" value="返回" style="width:98%;height:40px;line-height:40px;margin-left: 1%;"></div>
	       	</div>
	    </div>
	    </c:if>
    </c:if>
</body>
</html>
