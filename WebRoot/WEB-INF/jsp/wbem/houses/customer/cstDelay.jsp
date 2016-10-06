<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div id="urgedInfo" class="container-fluid" >
	 <form action="#" method="post" class="form-horizontal">
	 	<input type="hidden" value="${tradeGuid}" id="tradeGuid">
	 	<input type="hidden" name="postponeStage" value="${postponeStage}" id="postponeStage">
	 	<div class="control-group">
	       <label class="control-label">延期至:</label>
	       <div class="controls">
	       	<input type="text"  data-date-format="yyyy-mm-dd" value="${endDate}" class="datepicker span2" id="postponeDate" readonly>
	         <!-- <input type="text" id="nextUrgedDate" class="span3" value="" /> -->
	       </div>
	     </div>
	 	 <div class="row-fluid ">
		     <div class="control-group noborder-bottom">
		       <label class="control-label">延期原因 :</label>
		       <div class="controls">
		         <textarea id="postponeReason" class="span3" value=""  rows="5"></textarea>
		       </div>
		     </div>
	     </div>
	     <div class="row-fluid" style="margin-top:50px">
	        <div class="control-group noborder-bottom noborder-top span4"> 
            	<div class="controls">
                  <button type="button" class="btn btn-success" onclick="sureDelay()">确定</button>
                  <button type="button" class="btn btn-success" onclick="cancleDelay()">取消</button>
                </div>
	        </div> 
	     </div> 
     </form>
</div>
<%@include file="/WEB-INF/jsp/wbem/houses/customer/businessUrgedCommon.jsp"%>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>