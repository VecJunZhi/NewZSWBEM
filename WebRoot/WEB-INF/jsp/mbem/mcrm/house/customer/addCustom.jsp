<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<title>�����ͻ�</title>   
<!-- <link href="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="/common/js/jquery.mobile-1.4.5/demos/_assets/css/jqm-demos.css"> -->
<link rel="stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/user.css">
<link  rel="stylesheet" href="/mbem/mcrm/house/css/common.css">
<link rel="stylesheet" href="/mbem/mcrm/house/css/datey.css" >
<link rel="stylesheet" href="/mbem/mcrm/house/dingwei/jquery.mobile-1.4.5.css" >
 
 
<script  src="/common/js/jquery-1.9.1.min.js" ></script>
<script  src="/mbem/mcrm/house/js/date.js" ></script>
<script  src="/mbem/mcrm/house/js/iscroll.js" ></script>
<script  src="/mbem/mcrm/house/js/addCustom.js"></script>
<script  src="/common/js/bootstrap/bootstrap.min.js"></script> 
<script  src="/common/js/layer/layer.js"></script>
<script  src="/common/js/validate/jquery.validate.min.js"></script>
<script src="/mbem/mcrm/house/dingwei/jquery.min.js"></script>
<script src="/mbem/mcrm/house/dingwei/index.js"></script>
<script src="/mbem/mcrm/house/dingwei/jquery.mobile-1.4.5.min.js"></script>
<script  src="/mbem/mcrm/business/js/date.js" ></script>

<style id="wrap">
	.button-wrap {
		margin-left: 5px;
		margin-right: 5px;
	}
</style>
<style id="center">
	.center {
		text-align: center;
	}
</style>
<style>
	.erji{    
	position: relative;
    border-bottom-width: 1px;
    overflow: hidden;
    }
	.erjih1{
	text-indent: 10px;
	color:#3c6;
	font-weight:100;
	}
</style>
<script>
		$( document ).on( "pagecreate", "#demo-page", function() {

			$( document ).on( "swipeleft swiperight", "#demo-page", function( e ) {
				
				// We check if there is no open panel on the page because otherwise
				// a swipe to close the left panel would also open the right panel (and v.v.).
				// We do this by checking the data that the framework stores on the page element (panel: open).
				if ( $( ".ui-page-active" ).jqmData( "panel" ) !== "open" ) {
					if ( e.type === "swipeleft" ) {
						$( "#right-panel" ).panel( "open" );
					} else if ( e.type === "swiperight" ) {
						$( "#left-panel" ).panel( "open" );
					}
				}
			});
		});
    </script>
    <style>
		/* Swipe works with mouse as well but often causes text selection. */
		/* We'll deny text selecton on everything but INPUTs and TEXTAREAs. */
		#demo-page :not(INPUT):not(TEXTAREA) {
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			-o-user-select: none;
			user-select: none;
		}
		/* Content styling. */
		dl { font-family: "Times New Roman", Times, serif; padding: 1em; }
		dt { font-size: 2em; font-weight: bold; }
		dt span { font-size: .5em; color: #777; margin-left: .5em; }
		dd { font-size: 1.25em;	margin: 1em 0 0; padding-bottom: 1em; border-bottom: 1px solid #eee; }
		.back-btn { float: right; margin: 0 2em 1em 0; }
	</style>
	
<script type="text/javascript">
	$(function(){
		$('#beginTime').date();
		$('#endTime').date({theme:"datetime"});
	});
</script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#beginTime").val("${autoGenNextFollowDate}");
		$("#tel").attr("onblur","telBlur(this)");
		$("#tel").after("<label onclick='return setDefault(this)'>Ĭ��</label>");
		$("#addInfo").append("<input id='defaultTel' name='tel' type='hidden'>");//�������������ڴ��Ĭ���ֻ���
		var tag1 = $("#tel").parent().parent().parent();
		if($("#telHome").attr("id") != null && typeof($("#telHome").attr("id"))!='undefined') {
			$("#telHome").after("<label onclick='return setDefault(this)'>��ΪĬ��</label>");
			tag1 = $("#telHome").parent().parent().parent();
		}
		if($("#telOffice").attr("id") != null && typeof($("#telOffice").attr("id"))!='undefined') {
			$("#telOffice").after("<label onclick='return setDefault(this)'>��ΪĬ��</label>");
			tag1 = $("#telOffice").parent().parent().parent();
		}
		if($("#telFax").attr("id") != null && typeof($("#telFax").attr("id"))!='undefined') {
			$("#telFax").after("<label onclick='return setDefault(this)'>��ΪĬ��</label>");
			tag1 = $("#telFax").parent().parent().parent();
		}
		
		$(tag1).after("<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'><span class='v'><input id='addTel' style='color:#3c6' value='�����ϵ�绰' readonly></span></div></li>");

		$("#cancle").click(function(){
			window.location.href = "/mbem/mcrm/house/customer/cancleAddCst.action";
		});
 /*  	window.onpopstate = function(e){
			if(history.state)
			{
				window.location.href = "/mbem/mcrm/house/customer/customPage.action";
			}
		}; */
  		
  		$(".btn").click(function(){//ѡ����ý�����ͺ����ý�����͸�ֵ
  			var id = $(this).children().attr("id");
  			if(typeof(id)!='undefined' && id.indexOf("select2")!= -1){
  				var val = $(this).parent().prev().text();
				$("#mainMedia").val(val);
  			}
  		});
  		
		var xsNewTel = ${xsNewTel};
		if(xsNewTel != null)
			$("#tel").val(xsNewTel); 

		$("#addTel").click(function(){
			var tag = $("#addTel").parent().parent().parent();
			var str1 = "<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'>";
			var str4="</span></div></li>";
			var str2="";
			var str3="<label onclick='return setDefault(this)' style='float:left'>��ΪĬ��</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>";
			if($("#telHome").attr("id") == null || $("#telHome").attr("id")==undefined) {
				str2="<span class='k'>��ͥ�绰</span><span class='v'><input type='text' placeholder='������' name='homeTel' id='telHome' onblur='telBlur(this)' style='width:60%;float:left'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#telOffice").attr("id") == null || $("#telOffice").attr("id")==undefined) {
				str2="<span class='k'>�칫�绰</span><span class='v'><input type='text' placeholder='������' name='officeTel' id='telOffice' onblur='telBlur(this)' style='width:60%;float:left'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#telFax").attr("id") == null || $("#telFax").attr("id")==undefined) {
				str2="<span class='k'>����</span><span class='v'><input type='text' placeholder='������' name='fax' id='telFax' onblur='telBlur(this)' style='width:60%;float:left'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			} 	
		});
		/*  
		ȡ�Ա��ֵ��������ѡ���Ա��ͼƬ
		*/
		$("img").click(function(){
			var flag = $(this).attr("flag");
			var src = $(this).attr("src");
			if(flag == 1) {
			}
			else {
				if(src.indexOf("man")!=-1){
					$(this).attr("src","/mbem/mcrm/business/images/man.png");
					$(this).attr("flag","1");
					$("#woman").attr("src","/mbem/mcrm/business/images/woman_f.png");
					$("#woman").attr("flag","0");
					$("#sex").val("��");
				}
				if(src.indexOf("woman")!=-1){
					$(this).attr("src","/mbem/mcrm/business/images/woman.png");
					$(this).attr("flag","1");
					$("#man").attr("src","/mbem/mcrm/business/images/man_f.png");
					$("#man").attr("flag","0");
					$("#sex").val("Ů");
				}
			}
		});
	});
	
	/*�ֻ�ʧȥ�������֤  */  
	  function telBlur(tag){
		  /*	var telVal = $(tag).val();
		if(telVal==undefined || telVal == "")
			return false;
		$.ajax({
			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+telVal,    //�����url��ַ
			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
			type:"POST",   //����ʽ
				 success:function(req){
    			//����ɹ�ʱ����
    			if(req == "NO") {
    				layer.open({
    					type:0,
    					content:"���ֻ����ѵǼǹ�������������������룡",
    					btn:"ȷ��",
    					yes:function(index){
    						//$("#tel").focus();
    						//$("#tel").select();
    						$(tag).focus();
    						$(tag).select();
    						layer.close(index);
    					}
    				});
    				return false;
    			}
    			if(req == "YES")
    			{
    			}
			},
			error:function(){
    			//���������
			}
		});*/
	}  
	/* ��绰ɾ������ʵ�� 
	 */
	function tagRemove(tag){
		if($(tag).prev().html() == "Ĭ��") {
			$("#telPhone").next().html("Ĭ��");
		}
		var removeTag = $(tag).parent().parent().parent();
		$(removeTag).remove();
		//time = time-1;
	}
	/* ����Ĭ�Ϻ���Ĳ���ʵ��
		��ΪĬ��ʱ�����ж��Ƿ��Ѿ�ΪĬ�ϣ���ΪĬ���򷵻أ���������ж�Ҫ��ΪĬ�ϵ��ֻ����Ƿ�Ϊ�գ���Ϊ���򷵻أ�
		��������жϸ��ֻ����Ƿ�Ǽǹ�������Ǽǹ��򷵻أ��������״̬ΪĬ�ϲ���������Ϊ��Ĭ��
	 */
	function setDefault(tag){
		if($(tag).html() == "Ĭ��")
			return false;
		if($(tag).prev().val() == "" || $(tag).prev().val() == null) {
			layer.alert("�ֻ���Ϊ�գ���������ٽ�������");
			return false;
		}
		var telVal = $(tag).prev().val();
		//var projGuid = ${projGuid};
		$.ajax({
 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+telVal+"&projGuid=${projGuid}",    //�����url��ַ
 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
 			type:"POST",   //����ʽ
			success:function(req){
	   			//����ɹ�ʱ����
	   			if(req == "NO") {
	   				$(tag).prev().focus();
	   				$(tag).prev().select();
	   				layer.alert("���ֻ����ѵǼǹ����������������������ٽ������ã�");
	   			}
	   			if(req == "YES") {
	   				$("input[id^=tel]").each(function(){
						if($(this).next().html() == "Ĭ��") {
							$(this).next().html("��ΪĬ��");
						}
					});
					$(tag).html("Ĭ��");
					$("#defaultTel").val($(tag).prev().attr("name"));
				}
					
			},
	  		error:function(){
	      			//���������
	  		}
		});		
	}
	
	var repeatSubmit = true;
	function addCustom(){
		if(checkMust() == false) {
			repeatSubmit = true;
			return false;
		}
		if($("#tel").val()!=null && $("#tel").val()!='') {
			$.ajax({
	 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#tel").val()+"&projGuid=${projGuid}",    //�����url��ַ
	 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
	 			type:"POST",   //����ʽ
				success:function(req){
		   			//����ɹ�ʱ����
		   			if(req == "NO") {
		   				repeatSubmit = true;
		   				layer.open({
	    					type:0,
	    					content:"���ֻ����ѵǼǹ���������ֻ��ţ�",
	    					btn:"ȷ��",
	    					yes:function(index){
	    						$("#tel").focus();
	    						$("#tel").select();
	    						layer.close(index);
	    					}
	    				});
		   				return;
		   			}
		   			if(req == "YES") {
		   				if($("#telHome").val() != undefined && $("#telHome").val() !=null && $("#telHome").val()!=''){
		   					$.ajax({
		   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telHome").val()+"&projGuid=${projGuid}",    //�����url��ַ
		   			 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
		   			 			type:"POST",   //����ʽ
		   						success:function(req){
		   				   			//����ɹ�ʱ����
		   				   			if(req == "NO") {
		   				   				repeatSubmit = true;
		   				   				layer.open({
		   				   					type:0,
		   				   					content:"�ü�ͥ���ѵǼǹ�,������ֻ��ţ�",
		   				   					btn:"ȷ��",
		   				   					yes:function(index){
		   				   						$("#telHome").focus();
		   				   						$("#telHome").select();
		   				   						layer.close(index);
		   				   					}
		   				   				});
		   				   				return;
		   				   			}
		   				   			if(req == "YES") {
		   				   				if($("#telOffice").val() != undefined && $("#telOffice").val() !=null && $("#telOffice").val()!='') {
			   				   				$.ajax({
			   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telOffice").val()+"&projGuid=${projGuid}",    //�����url��ַ
			   			   			 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
			   			   			 			type:"POST",   //����ʽ
			   			   						success:function(req){
			   			   				   			//����ɹ�ʱ����
			   			   				   			if(req == "NO") {
			   			   				   				repeatSubmit = true;
			   			   				   				layer.open({
			   			   				   					type:0,
			   			   				   					content:"�ð칫���ѵǼǹ�,������ֻ��ţ�",
			   			   				   					btn:"ȷ��",
			   			   				   					yes:function(index){
			   			   				   						$("#telOffice").focus();
	   			   				   								$("#telOffice").select();
	   			   				   								layer.close(index);
			   			   				   					}
			   			   				   				});
			   			   				   				return;
			   			   				   			}
			   			   				   			if(req == "YES"){
					   			   				   		if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!='') {
							   			   				   	$.ajax({
							   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //�����url��ַ
							   			   			 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
							   			   			 			type:"POST",   //����ʽ
							   			   						success:function(req){
							   			   				   			//����ɹ�ʱ����
							   			   				   			if(req == "NO") {
							   			   				   				repeatSubmit = true;
							   			   				   				layer.open({
							   			   				   					type:0,
							   			   				   					content:"�ô�����ѵǼǹ�,������ֻ��ţ�",
							   			   				   					btn:"ȷ��",
							   			   				   					yes:function(index){
							   			   				   						$("#telFax").focus();
							   			   				   						$("#telFax").select();
							   			   				   						layer.close(index);
							   			   				   					}
							   			   				   				});
							   			   				   				return;
							   			   				   			}
							   			   				   			if(req == "YES") {
							   			   				   				$("#addCst").submit();
							   			   				   				return;
							   			   				   			}
							   			   						}
							   			   				   	});
							   			   				}
							   			   				else{
							   			   					$("#addCst").submit();
							   			   					return;
							   			   				}
			   			   				   			}
			   			   						}
			   				   				});
		   			   					}
		   			   					else if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!='') {
			   			   					$.ajax({
			   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //�����url��ַ
			   			   			 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
			   			   			 			type:"POST",   //����ʽ
			   			   						success:function(req){
			   			   				   			//����ɹ�ʱ����
			   			   				   			if(req == "NO") {
			   			   				   				repeatSubmit = true;
					   			   				   		layer.open({
			   			   				   					type:0,
			   			   				   					content:"�ô�����ѵǼǹ�,������ֻ��ţ�",
			   			   				   					btn:"ȷ��",
			   			   				   					yes:function(index){
			   			   				   						$("#telFax").focus();
			   			   				   						$("#telFax").select();
			   			   				   						layer.close(index);
			   			   				   					}
			   			   				   				});
			   			   				   				return;
			   			   				   			}
			   			   				   			if(req == "YES"){
			   			   				   				$("#addCst").submit();
			   			   				   				return;
			   			   				   			}
			   			   						}
			   			   				   	});
			   			   				}
			   			   				else{
			   			   					$("#addCst").submit();
			   			   					return;
			   			   				}
			   				   		}
		   						}
		   					});
		   				}
		   				else if($("#telOffice").val() != undefined && $("#telOffice").val() !=null && $("#telOffice").val()!='') {
		   					$.ajax({
			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telOffice").val()+"&projGuid=${projGuid}",    //�����url��ַ
			   			 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
			   			 			type:"POST",   //����ʽ
			   						success:function(req){
			   				   			//����ɹ�ʱ����
			   				   			if(req == "NO") {
			   				   				repeatSubmit = true;
			   				   				layer.open({
			   				   					type:0,
			   				   					content:"�ð칫���ѵǼǹ�,������ֻ��ţ�",
			   				   					btn:"ȷ��",
			   				   					yes:function(index){
			   				   						$("#telOffice").focus();
				   				   					$("#telOffice").select();
				   				   					layer.close(index);
			   				   					}
			   				   				});
			   				   				return;
			   				   			}
			   				   			if(req == "YES"){
			   				   				if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!=''){
				   				   				$.ajax({
				   			   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //�����url��ַ
				   			   			 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
				   			   			 			type:"POST",   //����ʽ
				   			   						success:function(req){
				   			   				   			//����ɹ�ʱ����
				   			   				   			if(req == "NO") {
				   			   				   				repeatSubmit = true;
						   			   				   		layer.open({
				   			   				   					type:0,
				   			   				   					content:"�ô�����ѵǼǹ�,������ֻ��š�",
				   			   				   					btn:"ȷ��",
				   			   				   					yes:function(index){
				   			   				   						$("#telFax").focus();
				   			   				   						$("#telFax").select();
				   			   				   						layer.close(index);
				   			   				   					}
				   			   				   				});
				   			   				   				return;
				   			   				   			}
				   			   				   			if(req == "YES"){
				   			   				   				$("#addCst").submit();
				   			   				   				return;
				   			   				   			}
				   			   						}
				   			   				   	});
			   				   				}
			   				   				else {
			   				   					$("#addCst").submit();
			   				   					return;
			   				   				}
			   				   			}
			   						}
			   				   	});
		   				}
		   				else if($("#telFax").val() != undefined && $("#telFax").val() !=null && $("#telFax").val()!='') {
		   					$.ajax({
		   			 			url:"/mbem/mcrm/house/customer/checkTelValidity.action?tel="+$("#telFax").val()+"&projGuid=${projGuid}",    //�����url��ַ
		   			 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
		   			 			type:"POST",   //����ʽ
		   						success:function(req){
		   				   			//����ɹ�ʱ����
		   				   			if(req == "NO") {
		   				   				repeatSubmit = true;
			   				   			layer.open({
			   				   					type:0,
			   				   					content:"�ô�����ѵǼǹ�,������ֻ��ţ�",
			   				   					btn:"ȷ��",
			   				   					yes:function(index){
			   				   						$("#telFax").focus();
			   				   						$("#telFax").select();
			   				   						layer.close(index);
			   				   					}
			   				   			});
		   				   				return;
		   				   			}
		   				   			if(req == "YES"){
		   				   				$("#addCst").submit();
		   				   				return;
		   				   			}
		   						}
		   				   	});
		   				}
		   				else{
		   					$("#addCst").submit();
		   					return;
		   				}
		   			}
				},
		  		error:function(){
		      			//���������
		      			alert("33333");
		  		}
			});
		}
	}
	
	function checkMust(){
		if(repeatSubmit == false){
			return false;
		}else {
			repeatSubmit = false;
		}
		var returnVal = true;
		if($("#name").val() == '' || $("#tel").val() == '' || $("#followContent").val() == '' || $("#beginTime").val()==''){
			layer.alert("�뽫��������д������");
			return false;
		}
			
		if(!(/^\d{11}$/.test($("#tel").val()))){ 
			layer.alert("�ֻ������ʽ����������");
			return false; 
		} 
			
		$("div[id^=select]").each(function(){
			if($(this).parent().parent().attr("data-require") == "1") {
				var flag = 0;
				var tag = $(this).next();
				$("label",tag).each(function(){
					if($(this).hasClass("active")){
						flag = 1;
						return false;
					}
				});
				if(flag == 0) {
					layer.alert("�뽫��������д������");
					returnVal = false;
					return false;
				}
			}
		});
		if(returnVal == false) {
			return false;
		}else {
			if($("#followcontent").val().length > 50){
				layer.alert("�뽫��������������50���ڣ�");
				return false;
			} else {
				return true;
			}
		} 
	}
</script>
</head>
<body id="insurer_list_page">

<!-- ���ٶ�λ��� -->
<!-- ���Ҳ໬����� ����һ�δ���-->
<%-- 	<div data-role="panel" id="right-panel" data-display="overlay" data-position="right" data-theme="a" data-position-fixed="true">
    	<ul data-role="listview" data-inset="false" data-theme="a" data-divider-theme="a" data-icon="false" class="jqm-list ui-listview ui-group-theme-a">
			<li>
				<c:forEach items="${addCstInfoList}" var="option" varStatus="status">
					<c:if test="${(option.tagType == 'radio' || option.tagType == 'checkbox') && option.name != '�Ա�' }">
						<c:if test="${option.isNull == 0}">
							<a href="#${option.locationId}" class="ui-btn" data-rel="close">${option.name}(����)</a>
						</c:if>
						<c:if test="${option.isNull == 1}">
							<a href="#${option.locationId}" class="ui-btn" data-rel="close">${option.name}</a>
						</c:if>
					</c:if>
				</c:forEach>
			</li>	
		</ul>
    </div>	 --%>
    
<!-- ���ٶ�λ��� -->

<div data-role="page" id="demo-page"  data-quicklinks="true" >
    <!--<div data-role="header" class="jqm-header "  data-position="fixed"  >
    
		<a href="#rightpanel3" class="ak47-right">���ٶ�λ</a>
  </div>-->   
<div data-role="main" class="ui-content">

<!-- ���Ҳ໬����� ����һ�δ���-->

<form id="addCst" class="form-content"  action="${actionUrl}" method="post" data-ajax="false">
<div id="msg_1" style="display: block;">
	<input id="defaultTel" name="defaultTel" type="hidden" value="tel">
	<input id="mainMedia" name="mainMediaName" type="hidden"><!-- �����򣬴����ý������ -->
	<input id="projGuid" type="hidden" value="${projGuid}">
	<div class="form-group border-1px first" data-id="2"> 
    	<ul id="addInfo" class="border-1px">  
    		<c:forEach items="${addCstInfoList}" var="option" varStatus="status">  
    			<c:if test="${(option.tagType == 'radio' ||option.tagType == 'checkbox')&& option.name !='�Ա�'}"> 			 							    
					<!-- <li class="border-1px"> -->
					<c:if test="${option.isNull == '0' }">
						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
					<c:if test="${option.isNull == '1' }">
						<li class="border-1px"  data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
						<div class="li-inner border-1px"> 
							<%-- <a name="${locationId}"></a> --%>
							<c:if test="${option.tagType =='checkbox' }">
								<div class="select-head" id="${option.id}" >${option.name}(�ɶ�ѡ)</div>
							</c:if>
							<c:if test="${option.tagType !='checkbox' }">
								<div class="select-head" id="${option.id}" >${option.name}</div>
							</c:if>
							<div  data-toggle="buttons">
								${option.htmlStr}
							</div>
						</div>
					</li>
				</c:if>
				<c:if test="${option.tagType == 'text' || option.name=='�Ա�' || option.tagType=='textarea'}">
					<c:if test="${option.isNull == '0' }">
						<li class="border-1px" data-require="1" data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
					<c:if test="${option.isNull == '1' }">
						<li class="border-1px"  data-ac="active" data-tag="s_customer@cst_name" data-type="text">
					</c:if>
							<div class="li-inner border-1px">
								<span class="k">${option.name }</span>
									<span class="v">${option.htmlStr}</span>
							</div>
						</li>
					</c:if>
				</c:forEach>	
		</ul>
	</div> 
    <div id="datePlugin"></div>
	<div style="height:40px"></div> 
    <!-- �������У���ֹ��ȡ���ͱ��浲ס -->
	<div class="form-group submit-group noswitchSinglePage">
        <a id="cancle" class="btns btns-default" href="#" style="text-align:center; color:#3c6">ȡ��</a>
        <button class="btns btns-primary"  data-ac="active" type="button" onclick="addCustom()">����</button>
    </div>
</div>
</form>
</div>
<!-- <div data-role="panel" id="rightpanel3" data-position="right" data-display="overlay" data-theme="a" style="height:100%;">
				<ul data-role="listview" data-inset="false" data-theme="a" data-divider-theme="a" data-icon="false" class="jqm-list ui-listview ui-group-theme-a">
					<li><a href="#select3" class="ak47" data-rel="close">סַ����</a></li>
					<li><a href="#eney" class="ak47" data-rel="close">��������</a></li>
						<li><a href="#area" class="ak47" data-rel="close">�������</a></li>
						<li><a href="#way" class="ak47" data-rel="close">���÷�ʽ</a></li>
						<li><a href="#basic" class="ak47" data-rel="close">������Ϣ</a>
					</li>	
				</ul>
	</div> --> 
	<div data-role="panel" id="right-panel" data-display="push" data-position="right" data-theme="c" >
    	<ul data-role="listview" data-inset="false" data-theme="a" data-divider-theme="a" data-icon="false" class="jqm-list ui-listview ui-group-theme-a">
			<c:forEach items="${addCstInfoList}" var="option" varStatus="status">
				<c:if test="${(option.tagType == 'radio' ||option.tagType == 'checkbox')&& option.name !='�Ա�'}">		
					<li><a href="#${option.id}" class="ak47" data-rel="close">${option.name}</a></li>
				</c:if>
			</c:forEach>	
		</ul>
    </div> 
</div>
</body>
</html>