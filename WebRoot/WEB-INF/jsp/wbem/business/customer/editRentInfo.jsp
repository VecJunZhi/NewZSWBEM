<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			  	<div class="control-group noborder-bottom"> 
	       			<label class="control-label">销售状态 :</label>
	       			<input type="hidden" value="${rentInfo.saleStatus}" id="saleStatus">
			       <div class="controls">
			         <input type="radio" name="saleStatus" value="已售"/>已售
			         <input type="radio" name="saleStatus" value="未售"/>未售
			         <input type="radio" name="saleStatus" value="不可售"/>不可售
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">商铺状态 :</label>
	       			<input type="hidden" value="${rentInfo.shopStatus}" id="shopStatus">
			       <div class="controls">
			         <input type="radio" name="shopStatus" value="已租"/>已租
			         <input type="radio" name="shopStatus" value="不可租"/>不可租
			         <input type="radio" name="shopStatus" value="可租"/>可租
			         <input type="radio" name="shopStatus" value="自营"/>自营
			       </div>
			    </div>
				<div class="control-group noborder-bottom"> 
	       			<label class="control-label">租赁情况 :</label>
			       <div class="controls">
			         <input type="text" id="rentStatus" class="span2" value="${rentInfo.rentStatus}" />
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">租金总额 :</label>
			       <div class="controls">
			         <input type="text" id="totalRent" class="span2" value="${rentInfo.totalRent}" />
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">日平米租金 :</label>
			       <div class="controls">
			         <input type="text" id="dayRent" class="span2" value="${rentInfo.dayRent}" />
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">签约日期 :</label>
			       <div class="controls">
			       	<input type="text"  data-date-format="yyyy-mm-dd" value="${rentInfo.signUpDate}" class="datepicker span2" id="signUpDate" readonly>
			       </div>
			    </div>
	            <div class="control-group noborder-bottom"> 
	       			<label class="control-label">意向客户 :</label>
			       <div class="controls">
			         <input type="text" id="intentionCst" class="span2" value="${rentInfo.intentionCst}" />
			       </div>
			    </div>
	            <div class="control-group noborder-bottom">
	       			<label class="control-label">招商专员 :</label>
			       <div class="controls">
			         <input type="hidden" id="oldInvestmentOfficer" value="${rentInfo.investmentOfficer}" />
			         <select id="investmentOfficer">
			         	<option value=""></option>
			         	<c:forEach items="${zygwList}" var="zygw" varStatus="status">
			         		<option value="${zygw.userName}">${zygw.userName}</option>
			         	</c:forEach>
			         	
			         </select>
			       </div>
			    </div>
	            <div class="control-group noborder-bottom">
	       			<label class="control-label">大客户经理 :</label>
			       <div class="controls">
			         <input type="hidden" id="oldKeyAccountManager"  value="${rentInfo.keyAccountManager}" />
			         <select id="keyAccountManager">
			         	<option value=""></option>
			         	<option value="贾国平">贾国平</option>
			         	<option value="边亮">边亮</option>
			         	<!-- <option value="王红军">王红军</option> -->
			         </select>
			       </div>
			    </div>
	            <div class="row-fluid ">
				<div class="control-group noborder-bottom noborder-top span5">  
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="sureUpdate">确定</button>
	                  <button type="button" class="btn btn-success" id="cancleUpdate">取消</button>
	                </div>
	            </div>
				</div>
	          </form>
	        </div>
	    </div>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

$(document).ready(function(){
	
	$("input[name='shopStatus']").each(function(){
		if($(this).val() == $("#shopStatus").val()){
			$(this).attr("checked",true);
		}
	});
	$("input[name='saleStatus']").each(function(){
		if($(this).val() == $("#saleStatus").val())
			$(this).attr("checked",true);
	});
	$("#investmentOfficer option").each(function(){
		if($(this).val() == $("#oldInvestmentOfficer").val()){
			$(this).attr("selected",true);
		}
	});
	$("#keyAccountManager option").each(function(){
		if($(this).val() == $("#oldKeyAccountManager").val()){
			$(this).attr("selected",true);
		}
	});
	//查询
	$("#cancleUpdate").click(function(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	});
	
	$("#sureUpdate").click(function(){
		//var projGuid = ${projGuid};
		var unitNo = ${unitNo};
		var saleStatus = encodeURI($("input:radio[name='saleStatus']:checked").val());
		var shopStatus = encodeURI($("input:radio[name='shopStatus']:checked").val());
		var rentStatus = encodeURI($("#rentStatus").val());
		var totalRent = encodeURI($("#totalRent").val());
		var dayRent = encodeURI($("#dayRent").val());
		var signUpDate = $("#signUpDate").val();
		var intentionCst = encodeURI($("#intentionCst").val());
		var investmentOfficer = encodeURI($("#investmentOfficer option:selected").val());
		var keyAccountManager = encodeURI($("#keyAccountManager option:selected").val());
		
		var data = {/* "projGuid":projGuid, */"unitNo":unitNo,"saleStatus":saleStatus,"shopStatus":shopStatus,"rentStatus":rentStatus,"totalRent":totalRent,"dayRent":dayRent,
				"signUpDate":signUpDate,"intentionCst":intentionCst,"investmentOfficer":investmentOfficer,"keyAccountManager":keyAccountManager};
		$.ajax({
	         url : '/wbem/business/customer/updateRentInfo.action',
	         data : {"data":JSON.stringify(data)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
	         type : 'post',
	         dataType : 'json',
	         success : function(flag) {          
	          	if(flag == 1){
	          		var index = parent.layer.getFrameIndex(window.name);
					parent.$('.data-table').DataTable().ajax.reload();
					parent.layer.close(index);
	          	}
	          	if(flag == 0){
	          		layer.alert("编辑失败！");
	          	}
	         },
	         error : function(msg) {
	          	
	         }
    	});
	});
	
	$('select').select2();
	//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>