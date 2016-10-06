<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
 <%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %> 
<!-- html code -->
<style>
	.text-center {
	text-align:center
	height: 20px;
	width: 80px;
	margin-left:100px
	}
</style>
<div class="container-fluid">
  <div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			  	<div class="control-group noborder-bottom"> 
	       			<label class="control-label" style='text-align:left;width:220px'>无效客户自动回收</label>    
			        <div class="controls">
			       		<input id="mmRadio6" type="hidden"   " value=${zsDictionaryEntityMap.get("招商无效客户自动回收")}  > 
          				<input type="radio" name="mmRadio6" id="mmRadio7" value="1" >是
	         			<input type="radio" name="mmRadio6" id="mmRadio8" value="0" >否
			        </div>
			    </div>
			    <div class="control-group noborder-bottom"  id="aa"> 
	       			<label class="control-label" style='text-align:left;width:220px'>无效回收天数</label>
			        <div class="controls">
			            <input type="text"  id="sec2"  value=${ zsDictionaryEntityMap.get("招商无效回收天数")} >天
			        </div>
			    </div>
			    <div class="control-group noborder-bottom">
        			<label class="control-label" style='text-align:left;width:220px'>逾期客户自动回收</label>
        			<div class="controls"  >
        				<input id="mmRadio0"  type="hidden"  value=${zsDictionaryEntityMap.get("招商逾期客户自动回收")} > 
          				<input type="radio" name="mmRadio0" id="mmRadio1" value="1"   >是
	         			<input type="radio" name="mmRadio0" id="mmRadio2" value="0"  >否
        			</div>
      		    </div>   
      		    <div class="control-group noborder-bottom"  id="bb1">
        		 	<label class="control-label"  style='text-align:left;width:220px'>逾期回收天数 </label>
       				<div class="controls" >
          			 	<input type="text"  id="sec0"   value=${ zsDictionaryEntityMap.get("招商逾期回收天数")}>天
        		 	</div>
     		   	</div>
      			<div class="control-group noborder-bottom"  id="bb2">
        			<label class="control-label" style='text-align:left;width:220px'>逾期天数</label>
        			<div class="controls" >
          				<input type="text" id="sec1"   value=${ zsDictionaryEntityMap.get("招商逾期天数")} >天
       				</div>
      			</div>
      			<div class="control-group noborder-bottom"  >
        		 	<label class="control-label"  style='text-align:left;width:220px'>公共客户是否允许置业顾问直接跟进 </label>
       				<div class="controls" >
          			 	<input type="hidden"  id="mmRadio3"    value=${ zsDictionaryEntityMap.get("招商公共客户是否允许置业顾问直接跟进")} >
          			 	<input type="radio"  name="mmRadio3" id="mmRadio4" value="1"   >是
	         			<input type="radio" name="mmRadio3" id="mmRadio5" value="0"  >否
          		    </div>
     		   	</div>
      			<div class="control-group noborder-bottom" id="cc">
        		 	<label class="control-label"  style='text-align:left;width:220px'>下次跟进间隔天数 </label>
       				<div class="controls" >
          			 	<input type="text" id="sec4"  value=${ zsDictionaryEntityMap.get("招商下次跟进间隔天数")}>天
        		 	</div>
     		   	</div>
			    <div class="row-fluid ">
				  <div class="control-group noborder-bottom noborder-top span5">  
	            	 <div class="controls">
	                    <button type="button" class="btn btn-success" id="saveBtn" >保存</button>
	                 </div>
	              </div>
				</div>
			  </form>
	        </div>
	    </div>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %> 
<script type="text/javascript">
	$("#aa").hide();
	$("#bb1").hide();
	$("#bb2").hide();
	$("#cc").hide();	
	if($("#mmRadio0").val()=='1'){
	    $("#mmRadio1").attr("checked","checked");
	    $("#mmRadio1").attr("value","1");
	    $("#bb1").show();
	    $("#bb2").show();
	}else{
		$("#mmRadio2").attr("checked","checked");
		$("#mmRadio2").attr("value","0");	
		$("#bb1").hide();
		$("#bb2").hide();		
	}   
   	if($("#mmRadio3").val()=='1'){
		$("#mmRadio4").attr("checked","checked");
		$("#mmRadio4").attr("value","1");
		$("#cc").show();
	}else{
		$("#mmRadio5").attr("checked","checked");
		$("#mmRadio5").attr("value","0");
		$("#cc").hide();
	}   
   	if($("#mmRadio6").val()=='1'){
		$("#mmRadio7").attr("checked","checked");
		$("#mmRadio7").attr("value","1");
		$("#aa").show();
	}else{
		$("#mmRadio8").attr("checked","checked");
		$("#mmRadio8").attr("value","0");
		$("#aa").hide();
	}   
   	$("#mmRadio1").click(function(){
   		$("#bb1").show();
		$("#bb2").show();
		$("#mmRadio0").attr("value","1");
   	}); 
   	$("#mmRadio2").click(function(){
   		$("#bb1").hide();
		$("#bb2").hide();
		$("#mmRadio0").attr("value","0");
   	}); 
   	$("#mmRadio4").click(function(){
   		$("#cc").show();
   		$("#mmRadio3").attr("value","1");
	}); 
   	$("#mmRadio5").click(function(){
   		$("#cc").hide();
   		$("#mmRadio3").attr("value","0");
	});  
   	$("#mmRadio7").click(function(){
   		$("#aa").show();
   		$("#mmRadio6").attr("value","1");
   	}); 
   	$("#mmRadio8").click(function(){
   		$("#aa").hide();
   		$("#mmRadio6").attr("value","0");
   	});  
   	$("#saveBtn").click(function(){
   		var params = {"overdueCustomerRecoveryDay":$("#sec0").val(),
   		 			  "overdueDay":$("#sec1").val(),
   		 			  "invalidClientRecoveryDay":$("#sec2").val(),
   		 			  "publicCustomerFollow":$("#mmRadio3").val(),
   		 			  "publicCustomerNextFollowDay":$("#sec4").val(),
   		 			  "overdueCustomerRecovery":$("#mmRadio0").val(),
   		 			  "invalidClientRecovery":$("#mmRadio6").val()};
   		$.ajax({
   		 	url:'/wbem/business/sysSet/updateConfigPara.action',
   		 	data:params,
   		 	type : 'post',
		    dataType : 'json',
		});
   		alert("数据保存成功");
   	});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %> 