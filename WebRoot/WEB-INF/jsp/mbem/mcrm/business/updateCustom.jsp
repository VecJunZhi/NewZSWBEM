<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/business/pub/head.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <title>�޸Ŀͻ�</title>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
	<link  rel="Stylesheet" href="/mbem/mcrm/business/css/base.min.css">
	<link  rel="Stylesheet" href="/mbem/mcrm/business/css/user.css">
	<script  src="/common/js/jquery-1.9.1.min.js" ></script>
	<script  src="/common/js/bootstrap/bootstrap.min.js"></script>
<script language="JavaScript" type="text/JavaScript">

<!--

function showhidediv(id){

var age=document.getElementById("msg_2");

var name=document.getElementById("msg_1");

if (id == 'name') {

   if (name.style.display=='none') {

    age.style.display='none';

    name.style.display='block';

   }

} else {

   if (age.style.display=='none') {

    name.style.display='none';

    age.style.display='block';

   }

}   

}

-->

</script>
<script>
	var _oldtel='';
	var _oldhome='';
	var _oldoffice='';
	var flag = 0;
   		$(function ($) {
	    	if (window.history && window.history.pushState) {
	        	$(window).on('popstate', function () {
	                var hash = window.location.hash;
	                //alert("hash=="+hash);
	                //alert(flag+"flag");
	                if (hash != '' && flag == 1 || (hash ==='' && flag == 0)) {
	                    window.location.href="/mbem/mcrm/business/personal.action";
	                }
	        	});
			        window.history.pushState('forward', null, "/mbem/mcrm/business/follow.action");//'./#forward');
			        location.hash="#1"; 
		       
		    }
		}); 
	
	 $(document).ready(function(){
	 
	 	 _oldtel=$("#telPhone").val();
	 	 _oldhome=$("#telhome").val();
	     _oldoffice=$("#teloffice").val();
		//alert(_oldtel+" "+_oldhome+" "+_oldoffice);
	 	$("#addInfo").append("<input id='defaultTel' name='tel' type='hidden'>");//�������������ڴ��Ĭ���ֻ���
	 	$("input[id^=tel] :last").parent().parent().after("<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'><span class='v'><input id='addTel' style='color:#3c6' value='�����ϵ�绰' readonly></span></div></li>");
	 	
	 	$("#oldName").val($("#name").val());
	 	$("#oldTel").val($("#"+$("#defaultId").val()).val());
	 	
		$("label").attr("state","unselected");	//��������ѡ��Ϊδѡ��״̬
		var i=1;
		var j=1;
		for(i=1; i<14; i++){
			var val = $("#sidselect"+i).val();
			if(val == ""|| val == null ||typeof(val)=='undefined')
				continue; 
 			var array = val.split(",");
			for(j=0;j<array.length;j++){
				$("label","#idselect"+i).each(function(){
					if($("input",this).val() == array[j]){
						$(this).attr("state","selected");
						$(this).addClass("active");
					}
				});
			}  
		}
		
		
		$("input[id^='tel']").each(function(){
			if($("#defaultId").val()=="" || $("#defaultId").val()==null){
				$(this).after("<label onclick='return setDefault(this)'>Ĭ��</label>");
				return false;
			}
			if($(this).attr("id") == "telPhone") {
				if($(this).attr("id") == $("#defaultId").val()) {
					$("#defaultTel").val($(this).val());
					$(this).after("<label onclick='return setDefault(this)'>Ĭ��</label>");
				}
				else {
					$(this).after("<label onclick='return setDefault(this)'>��ΪĬ��</label>");
				}
			}
			else{
				if($(this).attr("id") == $("#defaultId").val()) {
					$("#defaultTel").val($(this).val());
					$(this).after("<label onclick='return setDefault(this)'>Ĭ��</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>");
				}
				else {
					$(this).after("<label onclick='return setDefault(this)'>��ΪĬ��</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>");
				}
			}
		});
		
		$("#addTel").click(function(){
			var tag = $("#addTel").parent().parent().parent();
			var str1 = "<li class='border-1px'  data-ac='active' data-tag='s_customer@cst_name' data-type='text' ><div class='li-inner border-1px'>";
			var str4="</span></div></li>";
			var str2="";
			var str3="<label onclick='return setDefault(this)'>��ΪĬ��</label><label style='color:red;float: right' onclick='return tagRemove(this)'>X</label>";
			if($("#telhome").attr("id") == null || $("#telhome").attr("id")==undefined) {
				str2="<span class='k'>��ͥ</span><span class='v'><input type='text' placeholder='������' name='homeTel' id='telhome'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			if($("#teloffice").attr("id") == null || $("#teloffice").attr("id")==undefined) {
				str2="<span class='k'>�칫</span><span class='v'><input type='text' placeholder='������' name='officeTel' id='teloffice'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}
			/* if($("#telother").attr("id") == null || $("#telother").attr("id")==undefined) {
				str2="<span class='k'>����</span><span class='v'><input type='text' placeholder='������' name='otherTel' id='telother'>";
				$(tag).before(str1+str2+str3+str4);
				return;
			}  */	
		});
	});
	
	
	/* ��绰ɾ������ʵ�� 
	 */
	function tagRemove(tag){//���ɾ������Ĭ�ϵĵ绰�������ֻ��ֶ�ΪĬ��
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
			alert("�ֻ���Ϊ�գ���������ٽ�������");
			return false;
		}
		var telVal = $(tag).prev().val();
		$.ajax({
 			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+telVal,    //�����url��ַ
 			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
 			type:"POST",   //����ʽ
			success:function(req){
	   			//����ɹ�ʱ����
	   			if(req == "NO") {
	   				$(tag).prev().focus();
	   				$(tag).prev().select();
	   				alert("���ֻ����ѵǼǹ����������������������ٽ������ã�");
	   			}
	   			if(req == "YES") {
	   				$("input[id^=tel]").each(function(){
						if($(this).next().html() == "Ĭ��") {
							$(this).next().html("��ΪĬ��");
							}
					});
					$(tag).html("Ĭ��");
				}
					
			},
	  		error:function(){
	      			//���������
	  		}
		});		
	}
	
		 
	function cancle(name){
		showhidediv(name);
 		$("label").each(function(){
			var state = $(this).attr("state");
			var str = $(this).attr("class");
			if(state=="selected")
			{
				if(str.indexOf("active") == -1)
				$(this).addClass("active");
			}if(state == "unselected")
			{
				if(str.indexOf("active") != -1)
				{
					$(this).removeClass("active");
				};
			};  
		});
	}
		 
	function confirm(name){
		showhidediv(name);
		$("input[id^=sidselect]").val("");	//��������е�����
		$("textarea[id^=sidselect]").text("");		
 		$("#msg_2 label").each(function(){
			var str = $(this).attr("class");
			if(str.indexOf("active") != -1)
			{
				$(this).attr("state","selected");
				var id = $(this).parent().attr("id");
				//var option = $("#"+id).attr("stype");
				var option = $("input",this).attr("type");
				if(option == "radio")
					$("#"+"s"+id).val($("input",this).val());
			 	if(option == "checkbox")
				{
					var val = $("#"+"s"+id).val();
					var selVal = $("input",this).val()+",";
					var type = $("#"+"s"+id)[0].type;
					if(type=="textarea")
						$("#"+"s"+id).text(val+selVal);
					else
						$("#"+"s"+id).val(val+selVal);
				}  
			}		
		}); 
	}
		function add_custom(){//�ֻ���ʧȥ�������֤
			$("input[id^=tel]").each(function(){
				if($(this).next().html() == "Ĭ��") {
					$("#defaultTel").val($(this).val());
				}
			});
			var telVal = $("#telPhone").val();
			if(!(/^\d{11}$/.test(telVal))){ 
				alert("�ֻ������ʽ����������");
				return false; 
			}
			if(telVal !=_oldtel){
			  $.ajax({
    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+telVal,    //�����url��ַ
    			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
    			type:"POST",   //����ʽ
   				 success:function(req){
        			//����ɹ�ʱ����
        			if(req == "NO") {
        				$("#telPhone").focus();
        				$("#telPhone").select();
        				alert("�ú����ѵǼǹ���������������룡");
        				return false;
        			}
        			if(req == "YES")
        			{
        				var _home=$("#telhome").val();
        				if(_home!=undefined && _home !='' && _home !=_oldhome){
	        				if(!(/^\d{11}$/.test(_home))){ 
								alert("��ͥ�����ʽ����������");
								return false; 
							}
	        				$.ajax({
				    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_home,    //�����url��ַ
				    			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
				    			type:"POST",   //����ʽ
				   				 success:function(req){
				        			//����ɹ�ʱ����
				        			if(req == "NO") {
				        				$("#telhome").focus();
				        				$("#telhome").select();
				        				alert("�ú����ѵǼǹ���������������룡");
				        				return false;
				        			}
				        			if(req == "YES")
				        			{
											var _office=$("#teloffice").val();
					        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
					        				if(!(/^\d{11}$/.test(_office))){ 
												alert("�칫�����ʽ����������");
												return false; 
											}
						        				$.ajax({
									    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //�����url��ַ
									    			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
									    			type:"POST",   //����ʽ
									   				 success:function(req){
									        			//����ɹ�ʱ����
									        			if(req == "NO") {
									        				$("#teloffice").focus();
									        				$("#teloffice").select();
									        				alert("�ú����ѵǼǹ���������������룡");
									        				return false;
									        			}
									        			if(req == "YES")
									        			{
									        				$("#form-content").submit();
									        				return true;
									        			}
									    			},
									    			error:function(){
									        			//���������
									    			}
												});
											}		        				
				        			}
				    			},
				    			error:function(){
				        			//���������
				    			}
							});
						}else{
							var _office=$("#teloffice").val();
	        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
		        				if(!(/^\d{11}$/.test(_office))){ 
									alert("�칫�����ʽ����������");
									return false; 
								}
		        				$.ajax({
					    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //�����url��ַ
					    			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
					    			type:"POST",   //����ʽ
					   				 success:function(req){
					        			//����ɹ�ʱ����
					        			if(req == "NO") {
					        				$("#teloffice").focus();
					        				$("#teloffice").select();
					        				alert("�ú����ѵǼǹ���������������룡");
					        				return false;
					        				
					        			}
					        			if(req == "YES")
					        			{
					        				$("#form-content").submit();
					        				return true;
					        			}
					    			},
					    			error:function(){
					        			//���������
					    			}
								});
							}else{
								$("#form-content").submit();
							}
						}
        			}
    			},
    			error:function(){
        			//���������
    			}
			});
			}else{
						var _home=$("#telhome").val();
        				if(_home!=undefined && _home !='' && _home !=_oldhome){
	        				if(!(/^\d{11}$/.test(_home))){ 
								alert("��ͥ�����ʽ����������");
								return false; 
							}
	        				$.ajax({
				    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_home,    //�����url��ַ
				    			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
				    			type:"POST",   //����ʽ
				   				 success:function(req){
				        			//����ɹ�ʱ����
				        			if(req == "NO") {
				        				$("#telhome").focus();
				        				$("#telhome").select();
				        				alert("�ú����ѵǼǹ���������������룡");
				        				return false;
				        			}
				        			if(req == "YES")
				        			{
											var _office=$("#teloffice").val();
					        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
					        				if(!(/^\d{11}$/.test(_office))){ 
												alert("�칫�����ʽ����������");
												return false; 
											}
						        				$.ajax({
									    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //�����url��ַ
									    			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
									    			type:"POST",   //����ʽ
									   				 success:function(req){
									        			//����ɹ�ʱ����
									        			if(req == "NO") {
									        				$("#teloffice").focus();
									        				$("#teloffice").select();
									        				alert("�ú����ѵǼǹ���������������룡");
									        				return false;
									        			}
									        			if(req == "YES")
									        			{
									        				$("#form-content").submit();
									        				return true;
									        			}
									    			},
									    			error:function(){
									        			//���������
									    			}
												});
											}		        				
				        			}
				    			},
				    			error:function(){
				        			//���������
				    			}
							});
						}else{
							var _office=$("#teloffice").val();
	        				if(_office!=undefined && _office !='' && _office !=_oldoffice){
		        				if(!(/^\d{11}$/.test(_office))){ 
									alert("�칫�����ʽ����������");
									return false; 
								}
		        				$.ajax({
					    			url:"/mbem/mcrm/business/checkTelValidity.action?tel="+_office,    //�����url��ַ
					    			async:true,//�����Ƿ��첽��Ĭ��Ϊ�첽����Ҳ��ajax��Ҫ����
					    			type:"POST",   //����ʽ
					   				 success:function(req){
					        			//����ɹ�ʱ����
					        			if(req == "NO") {
					        				$("#teloffice").focus();
					        				$("#teloffice").select();
					        				alert("�ú����ѵǼǹ���������������룡");
					        				return false;
					        				
					        			}
					        			if(req == "YES")
					        			{
					        				$("#form-content").submit();
					        				return true;
					        			}
					    			},
					    			error:function(){
					        			//���������
					    			}
								});
							}else{
								$("#form-content").submit();
							}
						}
        			
			}
		}
</script>
            <style>
#wrapper{height:100%;-webkit-overflow-scrolling:touch;overflow:auto;}
</style>
<body>

    	<form class="form-content" id="form-content" action="/mbem/mcrm/business/updateZsBasicInfoDao.action" method="post" >
    		<input type="hidden" id="defaultId" value="${defaultTelId}">
    		<input type="hidden" name="oldName" id="oldName">
    		<input type="hidden" name="oldTel" id="oldTel">
    		<div id="msg_1" style="display:block;">
    			<div class="form-group border-1px first" data-id="1">
     				<c:forEach items="${updatecustList.field}" var="optionlist1" varStatus="status"> 
    				<div class="form-group border-1px other" data-id="2"> 
    					<ul id="addInfo" class="border-1px">    
    						<c:forEach items="${optionlist1}" var="optionlist" varStatus="status"> 							    
							<li class="border-1px" ${optionlist.isnull} data-ac="active" data-tag="s_customer@cst_name" data-type="text" ${optionlist.click}>
								${optionlist.href}
            						<div class="li-inner border-1px">
            	 						<span class="k">${optionlist.name}</span>
                						<span class="v">
            								${optionlist.htmlstr} 
            							</span>
           							</div>
           						${optionlist.end}
							</li>
							</c:forEach>
						</ul>
					</div>
					</c:forEach>
					<div class="form-group border-1px other" data-id="10">
        				<ul class="border-1px">
        	 				<li class="border-1px" data-require="1" data-ac="active" data-tag="s_opp2gjjl@next_date" data-type="date">
							</li> <!-- �������У���ֹ��ȡ���ͱ��浲ס -->
        				</ul> 
     				</div>
    			</div> 
     			<div id="action-mask"></div>
    			<div class="form-group submit-group noswitchSinglePage">
       				 <!-- <button class="btn btn-default" id="cancel" data-ac="active">ȡ��</button> --> <a class="btns btns-default" href="/mbem/mcrm/business/cancleUpdateCus.action" style="text-align:center; color:#3c6">ȡ��</a>
        			<button class="btns btns-primary" id="button" type="button" data-ac="active" onclick="add_custom()">����</button>
    			</div>
 			</div> 
	 		<div id="msg_2" style="display:none;">
	 			<div class="switchSinglePage-wrap">
	 				<div class="switchSinglePage panel" style="-webkit-transform: translate3d(0%, 0px, 0px);">
	 					<div class="scroll">
							<div class="form-group border-1px first">
								<ul class="border-1px">
									<c:forEach items="${updatecustList.option}" var="optionlist1" varStatus="status"> 
									<li class="border-1px">
	 									<div class="li-inner border-1px"><a name="${optionlist1.href}"></a>
											<div class="select-head">${optionlist1.name}</div>
											<div id="${optionlist1.id}" stype="single" data-toggle="buttons">
									<c:forEach items="${optionlist1.value}" var="optionlist" varStatus="status">
										<label class="btn">
	      								<input type="${optionlist1.select}" name="options" id="option1" value="${optionlist.selectName}"> ${optionlist.selectName}
	   									</label>
	   								</c:forEach>
	    						</div>
	    							</li>
									</c:forEach>
	 							</ul>
								<div>
								</div> 
							</div>
						</div>
					</div>
				</div>
	         	<div id="action-mask"></div>
	        	<div class="form-group zure-group noswitchSinglePage">
	        		<input class="btns btns-default" type="button" value="ȡ��" onclick='cancle("name")';>
	       			<input class="btns btns-primary" type="button" value="ȷ��" onclick='confirm("name")';>  
	    		</div>
			</div>
		</form>
	        <script>
    var UA = navigator.userAgent;
    var forIOS = function(){
        if(!UA.match(/iPad/) && !UA.match(/iPhone/) && !UA.match(/iPod/)){return;}
        if($('#wrapper').length){return;}
        $('body').children().not('script').wrapAll('<div id="wrapper"></div>');
    }();
</script>
</body>
</html>