<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			  	<div class="control-group noborder-bottom"> 
	       			<label class="control-label">����״̬ :</label>
	       			<input type="hidden" value="${rentInfo.saleStatus}" id="saleStatus">
			       <div class="controls">
			         <input type="radio" name="saleStatus" value="����"/>����
			         <input type="radio" name="saleStatus" value="δ��"/>δ��
			         <input type="radio" name="saleStatus" value="������"/>������
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">����״̬ :</label>
	       			<input type="hidden" value="${rentInfo.shopStatus}" id="shopStatus">
			       <div class="controls">
			         <input type="radio" name="shopStatus" value="����"/>����
			         <input type="radio" name="shopStatus" value="������"/>������
			         <input type="radio" name="shopStatus" value="����"/>����
			         <input type="radio" name="shopStatus" value="��Ӫ"/>��Ӫ
			       </div>
			    </div>
				<div class="control-group noborder-bottom"> 
	       			<label class="control-label">������� :</label>
			       <div class="controls">
			         <input type="text" id="rentStatus" class="span2" value="${rentInfo.rentStatus}" />
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">����ܶ� :</label>
			       <div class="controls">
			         <input type="text" id="totalRent" class="span2" value="${rentInfo.totalRent}" />
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">��ƽ����� :</label>
			       <div class="controls">
			         <input type="text" id="dayRent" class="span2" value="${rentInfo.dayRent}" />
			       </div>
			    </div>
			    <div class="control-group noborder-bottom"> 
	       			<label class="control-label">ǩԼ���� :</label>
			       <div class="controls">
			       	<input type="text"  data-date-format="yyyy-mm-dd" value="${rentInfo.signUpDate}" class="datepicker span2" id="signUpDate" readonly>
			       </div>
			    </div>
	            <div class="control-group noborder-bottom"> 
	       			<label class="control-label">����ͻ� :</label>
			       <div class="controls">
			         <input type="text" id="intentionCst" class="span2" value="${rentInfo.intentionCst}" />
			       </div>
			    </div>
	            <div class="control-group noborder-bottom">
	       			<label class="control-label">����רԱ :</label>
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
	       			<label class="control-label">��ͻ����� :</label>
			       <div class="controls">
			         <input type="hidden" id="oldKeyAccountManager"  value="${rentInfo.keyAccountManager}" />
			         <select id="keyAccountManager">
			         	<option value=""></option>
			         	<option value="�ֹ�ƽ">�ֹ�ƽ</option>
			         	<option value="����">����</option>
			         	<!-- <option value="�����">�����</option> -->
			         </select>
			       </div>
			    </div>
	            <div class="row-fluid ">
				<div class="control-group noborder-bottom noborder-top span5">  
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="sureUpdate">ȷ��</button>
	                  <button type="button" class="btn btn-success" id="cancleUpdate">ȡ��</button>
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
	//��ѯ
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
	         data : {"data":JSON.stringify(data)},//����ǰ�datatable��һЩ�������ݴ�����̨,������ʼλ��,ÿҳ��ʾ������
	         type : 'post',
	         dataType : 'json',
	         success : function(flag) {          
	          	if(flag == 1){
	          		var index = parent.layer.getFrameIndex(window.name);
					parent.$('.data-table').DataTable().ajax.reload();
					parent.layer.close(index);
	          	}
	          	if(flag == 0){
	          		layer.alert("�༭ʧ�ܣ�");
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