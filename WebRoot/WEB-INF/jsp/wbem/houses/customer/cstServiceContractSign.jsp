<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >选择项目</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="projGuid" >
	                     <c:forEach items="${projectList}" var="project" varStatus="status">
              				<option value="${project.id}">${project.name}</option>
              	  		</c:forEach>
	                </select>
	              </div>
	            </div> 
	             <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >置业顾问</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="userGuid">
	                	<option selected="selected"></option>
	                   <c:forEach items="${zygwList}" var="zygw" varStatus="status">
              			<option value="${zygw.userName}">${zygw.userName}</option>
             		   </c:forEach>
	                </select>
	              </div>
	            </div> 
			  	<div class="input-prepend" >
				  <div class="btn-group">
				    <button class="btn dropdown-toggle"  data-toggle="dropdown" id="dropdown_btn"> 客户姓名 <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu" id="dropdown-menu">
				      <li><a href="#">客户姓名</a></li>
				      <li><a href="#">客户电话</a></li>
				    </ul>
				  </div>
				  <input class="span4" id="telOrName" type="text"><!-- <button class="btn btn-primary" style="margin-left: 5px;">搜索</button> -->
				</div>
				<div class="row-fluid ">
	            <div class="input-prepend input-append span5" style="margin-top: 10px;">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">付款方式</span>
				  <label class="checkbox inline"> <input type="checkbox" name="urgedType" value="1"/>公积金</label>
	              <label class="checkbox inline"> <input type="checkbox" name="urgedType" value="2"/>贷款</label>
	              <label class="checkbox inline"> <input type="checkbox" name="urgedType" value="3"/>全款</label>
				</div>
				<div class="control-group noborder-bottom noborder-top span5"> 
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">查询</button>
	                  <button type="button" class="btn btn-success" id="resetSearch">撤销筛选</button>
	                </div>
	             </div>
				</div>
	          </form>
	        </div>
	    </div>
	</div>
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>商品房买卖合同签订表</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
         </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<!-- <th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th> -->
						<th>序号</th>
						<th>购房人名称</th>
						<th>联系电话</th>
						<th>置业顾问</th>
						<th>现楼号/单元/楼层/房号</th>
						<th>建筑面积</th>
						<th>单价</th> 
						<th>成交金额</th>
						<th>首付款</th>
						<th>贷款额</th>
						<th>贷款年限</th> 
						<th>合同编号</th>
						<th>合同签订日期</th> 
						<th>付款方式</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%-- <%@include file="/WEB-INF/jsp/wbem/houses/customer/businessUrgedCommon.jsp" %> --%>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

$(document).ready(function(){

	var table=$('.data-table').DataTable({
		"ajax": {
			type: "post",//后台指定了方式。
         	url: "/wbem/houses/customer/loadCstServiceContractSign.action",
		 	data:function(d){			//外部参数传递
		 		d.projGuid=$('#projGuid').find('option:selected').val();
	         	d.zygw = encodeURI($('#userGuid').find('option:selected').val());
	         	d.telOrName = encodeURI($("#telOrName").val());
	         	/* d.minQsDate = $("#dpd1").val();
	    		d.maxQsDate = $("#dpd2").val();
	    		d.minOverDueDays = $("#minOverDueDays").val();
	    		d.maxOverDueDays = $("#maxOverDueDays").val(); */
	    		d.payWay = checkbox_CheckedValue("urgedType").toString();
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"cstName"},
         	{"data":"mobileTel"},
         	{"data":"zygw"},
         	{"data":"roomInfo"},
         	{"data":"area"},
         	{"data":"unitPrice"}, 
         	{"data":"tradeAmount"},
         	{"data":"initPayment"},
         	{"data":"loanAmount"},
         	{"data":"loanYear"},
         	{"data":"contractNum"},
         	{"data":"contractSignDate"},
         	{"data":"paymentWay"},
         ],
         "columnDefs":[
            /* {
         		targets:12,"render":function(data){
	       			return	"<shiro:hasPermission name='button:urgedManage:unLending:cstUrged'><a style='color:#FAA732' href='#' onclick='cstUrged(\"" + data + "\")'>催办</a></shiro:hasPermission>&nbsp;" + 
	       				    "<shiro:hasPermission name='button:urgedManage:unLending:cstDelay'><a style='color:#FAA732' href='#' onclick='cstDelay(\"" + data + "\")'>延期</a></shiro:hasPermission>&nbsp;"+
	       				 "<a style='color:#FAA732' href='#' onclick='cstDetail(\"" + data + "\")'>查看</a>&nbsp;";		
         		}
         	},
	        { "sortable": false, "targets":[0,1,3,4,5,10,12]} */ 
          ] , 
        // "ordering":true,
        // "order": [8,"desc"],//默认排序	  
	});
		//查询
	$("#tableSearch").click(function(){
		table.draw();
	});
	
	$("#resetSearch").click(function(){
		$("#userGuid").select2("val","");
		$("#telOrName").val("");
		$("input[type='checkbox']").attr("checked",false);
		table.draw();
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
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>