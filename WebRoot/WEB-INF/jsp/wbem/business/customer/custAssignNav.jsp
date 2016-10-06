<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="navbar">
	  <div class="navbar-inner" style="margin-top:5px;">
	    <ul class="nav">
	      <%-- <li>
		      <form class="navbar-form pull-left" >
	            <select id="proId" class="span2">
	              <c:forEach items="${projectList}" var="project" varStatus="status">
              		<option value="${project.id}">${project.name}</option>
              	  </c:forEach>
	            </select>
			  </form>
	      </li>
	       <li class="divider-vertical"></li> --%>
	      <li class="active"><a href="/wbem/business/customer/custAssignNav.action?menuId=${menuId}">跟进中的客户</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/business/customer/custAssignPublic.action?menuId=${menuId}">公共客户</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/business/customer/custAssignDusBin.action?menuId=${menuId}">垃圾箱</a></li>
	      <li class="divider-vertical"></li>
	      <li>
	    </ul>
	  </div>
	</div>
	<div class="widget-box">
		<div class="widget-title">
		  <ul class="nav nav-tabs">
		    <li class="active"><a data-toggle="tab" href="#tab1" onclick="tabSwitch(this)" target="/wbem/business/customer/custAssignNav.action?menuId=${menuId}">跟进中的客户</a></li>
		    <li class="" ><a data-toggle="tab" href="#tab2" onclick="tabSwitch(this)" target="/wbem/business/customer/custAssignOverdue.action?menuId=${menuId}">逾期客户</a></li>
		    <li class="" ><a data-toggle="tab" href="#tab3" onclick="tabSwitch(this)" target="/wbem/business/customer/custAssignInvalid.action?menuId=${menuId}">无效客户</a></li>
		  </ul>
		</div>
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			  	<div class="input-prepend" style="margin-top: 10px;">
				  <div class="btn-group">
				    <button class="btn dropdown-toggle"  data-toggle="dropdown" id="dropdown_btn"> 客户电话 <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu" id="dropdown-menu">
				      <li><a href="#">客户姓名</a></li>
				      <li><a href="#">客户电话</a></li>
				    </ul>
				  </div>
				  <input class="span4" id="telOrName" type="text"><!-- <button class="btn btn-primary" style="margin-left: 5px;">搜索</button> -->
				</div>
	            <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >置业顾问</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="zygw" >
	                   <c:forEach items="${zygwList}" var="zygw" varStatus="status">
              			<option value="${zygw.userId}">${zygw.userName}</option>
             		   </c:forEach>
                         <option selected="selected"></option>
	                </select>
	              </div>
	            </div> 
	            <div class="row-fluid">
	            <div class="input-prepend input-append">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">客户状态</span>
				  <label class="checkbox inline"> <input type="checkbox" name="status" value="无效"/>无效</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="问询"/>问询</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="看房"/>看房</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="认购"/>认购</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="签约"/>签约</label>
				</div>
				</div>
	            <div class="row-fluid ">
	            <div class="input-prepend input-append" style="margin-top: 10px;">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">意向级别</span>
	                <label class="checkbox inline"	> <input type="checkbox" name="gfyx" value="必买"/>必买</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="高意向"/>高意向</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="一般意向"/>一般意向</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="无意向"/>无意向</label>
	            </div>
	            </div>
	            <div class="row-fluid ">
	              <div class="input-prepend input-append" style="margin-top: 10px;">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">新增日期</span>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="createdOn" value="0" style="margin-left: 0;" />昨日</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="createdOn" value="1" style="margin-left: 0;" />今日</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="createdOn" value="2" style="margin-left: 0;" />本周</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="createdOn" value="3" style="margin-left: 0;" />本月</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="createdOn" value="4" style="margin-left: 0;" />自定义</label>
	                <input type="text" data-date="${startDate}" data-date-format="yyyy-mm-dd" value="${startDate}" class="datepicker span2" id="dpd1" style="display:none" readonly>	   
		    		<input type="text" data-date="${endDate}" data-date-format="yyyy-mm-dd" value="${endDate}" class="datepicker span2" id="dpd2" style="display:none" readonly>
	            </div>
	            </div>
	            <div class="row-fluid ">
	            <div class="control-group noborder-bottom noborder-top span4"> 
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>跟进中客户列表</h5>
			<label class="checkbox inline index" id="checklabel" style="display: none;"><input type="checkbox" id="checkall" />选中全部</label>
			<label class="checkbox inline index" id="checklabel_after" style="display: none;"></label>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				
				<button type="button" class="btn btn-success" onclick="cstMassAssign()">批量分配</button>
				<button type="button" class="btn btn-success" onclick="cstMassReback()">批量回收</button>
				<div class="btn-group"><!-- onclick="importExcel()" -->
					<button type="button" class="btn btn-success dropdown-toggle btn-lg" data-toggle="dropdown" >客户导入</button>
					<ul class="dropdown-menu" role="menu">
					      <li><a href="#" onclick="exportExcel_Demo()">模板下载</a></li>
					      <li class="divider"></li>
					      <li><a href="#" onclick="importExcel()">导入客户</a></li>
				    </ul>
				</div>
				<div class="btn-group">
					<button type="button" class=" btn btn-warning dropdown-toggle btn-lg" data-toggle="dropdown">线下分配</button>
					<ul class="dropdown-menu" role="menu">
					      <li><a href="#" onclick="exportExcel()">导出客户资料</a></li>
					      <li class="divider"></li>
					      <li><a href="#" onclick="importExcelResult()">导入分配结果</a></li>
				    </ul>
         		</div>
			</div>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th>
						<th>姓名</th>
						<th>电话</th>
						<th>置业顾问</th>
						<th>客户状态</th>
						<th>意向级别</th>
						<th>最近跟进时间</th>
						<th>跟进方式</th>
						<th>新增日期</th>
						<th>到期日期</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<input type="hidden" id="ids" value=""/>
<%-- 公共页面--%>

<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
$(document).ready(function(){
	
	//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$("#resetSearch").click(function(){
		$("#zygw").select2("val","");
		$("#telOrName").val("");
		$("#dpd1").val("");
		$("#dpd2").val("");
		$("input[type='checkbox']").attr("checked",false);
		$("input[type='radio']").attr("checked",false);
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"ajax": {type: "post",//后台指定了方式。
                 url: "/wbem/business/customer/getZsbusiCustomFollowing.action",
                 data:function(d){			//外部参数传递
                 	//d.proId=$('#proId').find('option:selected').val();
                 	d.telOrName = encodeURI($("#telOrName").val());
                 	d.userId = $('#zygw').find('option:selected').val();
                 	d.status = encodeURI(checkbox_CheckedValue("status").toString());
                 	d.gfyx = encodeURI(checkbox_CheckedValue("gfyx").toString());
                 	d.createdOn = checkbox_CheckedValue("createdOn").toString();
                 	d.startCreate = $("#dpd1").val();
	           		d.endCreate = $("#dpd2").val();
                 },
                 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"cstName"},
         	{"data":"mobileTel"},
         	{"data":"userName"},
         	{"data":"status"},
         	{"data":"gfyx"},
         	{"data":"lastDate"},
         	{"data":"gjfs"},
         	{"data":"createdOn"},
         	{"data":"nextDate"},
         	{"data":"operate"},
         ],
           columnDefs:[
	          	 {
	         		targets:10,"render":function(data, type, row, meta){
		       			return "<a style='color:#FAA732' href='#' cusId="+data+" onclick='cstDetail(\"" + data + "\",\""+row.userName+"\")'>查看</a>&nbsp;" +
		      				"<a style='color:#FAA732' href='#' onclick='cstAssign(\"" + data + "\")'>分配</a>&nbsp;" +
		      				"<a style='color:#FAA732' href='#' onclick='cstReback(\"" + data + "\")'>回收</a>&nbsp;" +
		      				"<a style='color:#FAA732' href='#' onclick='editCustInfo(\"" + data+ "\",\""+row.cstName + "\",\""+ row.mobileTel +"\",\""+ row.userName +"\")'>编辑</a>";}
	         	},
	         	/* {  targets:2,
	         		"orderable":true,
	         		"orderSequence": [ "desc", "asc", "asc" ], 
	         		"ordering": true,
	         		"searchable":true
	         	}, */
	        	{ "sortable": false, "targets":[0,1,2,3,4,7,9,10]}
        	] , 
         "ordering":true,
         "order": [6,"asc"],		//设置默认排序字段
         "initComplete": function(settings, json) {
        	 var ids=json.ids;
       		 ids= ids.substring(1,ids.length-1);
         	 $("#ids").val((ids));
         	 $("#checklabel_after").text("已经选中"+json.assignNum+"条记录");
  		},
	});
	
	//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$('select').select2();
	
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
	
	$("input[name='createdOn']").click(function(){//自定义日期的隐藏显示
	if($(this).val() == 4) {
		$("#dpd1").show();
		$("#dpd2").show();
	}else {
		$("#dpd1").hide();
		$("#dpd2").hide();
	}
	});	
});
</script>
<%@include file="/WEB-INF/jsp/wbem/business/customer/custAssignCommon.jsp" %>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>