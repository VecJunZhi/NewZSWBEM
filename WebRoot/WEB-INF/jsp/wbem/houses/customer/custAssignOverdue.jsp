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
	      <li class="active"><a href="/wbem/houses/customer/custAssignNav.action?menuId=${menuId}">跟进中的客户</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/houses/customer/custAssignPublic.action?menuId=${menuId}">公共客户</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/houses/customer/custAssignDusBin.action?menuId=${menuId}">垃圾箱</a></li>
	      <li class="divider-vertical"></li>
	    </ul>
	  </div>
	</div>
	<div class="widget-box">
		<div class="widget-title">
		  <ul class="nav nav-tabs">
		    <li class=""><a data-toggle="tab" href="#tab1" onclick="tabSwitch(this)" target="/wbem/houses/customer/custAssignNav.action?menuId=${menuId}">跟进中的客户</a></li>
		    <li class="active" ><a data-toggle="tab" href="#tab2" onclick="tabSwitch(this)" target="/wbem/houses/customer/custAssignOverdue.action?menuId=${menuId}">逾期客户</a></li>
		    <li class="" ><a data-toggle="tab" href="#tab3" onclick="tabSwitch(this)" target="/wbem/houses/customer/custAssignInvalid.action?menuId=${menuId}">无效客户</a></li>
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
	               	 <option selected="selected"></option>
	                  <c:forEach items="${zygwList}" var="zygw" varStatus="status">
              			<option value="${zygw.value}">${zygw.userName}</option>
             		  </c:forEach>
                      
	                </select>
	              </div>
	            </div> 
	            <div class="row-fluid">
	            <div class="input-prepend input-append">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">逾期天数</span>
				   <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="0"/>1-10</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="1"/>11-20</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="2"/>20以上</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="overDays" style="margin-left: 0;" value="3"/>自定义</label>
	                <input class="text inline" type="text" id="startDays" style="display:none">
	                <input class="text inline" type="text" id="endDays" style="display:none">
				</div>
				</div> 
	           <div class="row-fluid">
	            <div class="input-prepend input-append" style="margin-top: 10px;">
				  <span class="add-on" style="width: 103px;margin-right: 5px;">客户状态</span>
				  <label class="checkbox inline"> <input type="checkbox" name="status" value="无效"/>无效</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="问询"/>问询</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="看房"/>看房</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="认购"/>认购</label>
	              <label class="checkbox inline"> <input type="checkbox" name="status" value="签约"/>签约</label>
				</div>
				</div> 
	            <div class="row-fluid">
	            <div class="input-prepend input-append" style="margin-top: 10px;">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">意向级别</span>
	              <label class="checkbox inline"	> <input type="checkbox" name="gfyx" value="必买"/>必买</label>
	              <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="高意向"/>高意向</label>
	              <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="一般意向"/>一般意向</label>
	              <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="无意向"/>无意向</label>
	            </div>
	            </div>

	            <div class="input-prepend input-append" style="margin-top: 10px;">
	            	<span class="add-on" style="width: 103px;margin-right: 5px;">最近跟进</span>
	            	<input type="text" data-date="${startDate}" data-date-format="yyyy-mm-dd" value="${startDate}" class="datepicker span2" id="dpd1" readonly>	   
		    		<!-- <i class="icon-resize-horizontal" >11111</i> -->
		    		<!-- <label style="width:10px">——</label> -->
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" data-date="${endDate}" data-date-format="yyyy-mm-dd" value="${endDate}" class="datepicker span2" id="dpd2" readonly>
	            </div>
	            <div class="row-fluid">
	            <div class="control-group noborder-bottom noborder-top span4"> 
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">查询</button>
	                  <button type="reset" class="btn btn-success" id="resetSearch">撤销筛选</button>
	                </div>
	             </div> 
	            </div> 
	          </form>
	        </div>
	    </div>
	</div>
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>逾期客户列表</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<button type="button" class="btn btn-success"  onclick="cstMassAssign()">批量分配</button>
				<button type="button" class="btn btn-success" onclick="cstMassReback()">批量回收</button>
         		<!-- <button type="reset" class=" btn btn-warning" id="" disabled>线下分配</button> -->
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
						<th>逾期天数</th>
						<th>操作</th>
						<th>客户id</th>
						<th>oppguid</th>
						<th>UserGuid</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/wbem/houses/customer/custAssignCommon.jsp" %>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
$(document).ready(function(){
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});

	$("#resetSearch").click(function(){
		$("#zygw").select2("val","");
		$("#telOrName").val("");
		$("input[type='checkbox']").attr("checked",false);
		$("input[type='radio']").attr("checked",false);
		$("#startDays").val("");
		$("#endDays").val("");
		$("#dpd1").val("");
		$("#dpd2").val("");
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});

	
	$('.data-table').dataTable({
		"ajax": {type: "post",//后台指定了方式。
                 url: "/wbem/houses/customer/getZsbusiCustomOverDue.action",
                 data:function(d){			//外部参数传递
                 	//d.proId=$('#proId').find('option:selected').val();
                 	d.userId = $('#zygw').find('option:selected').val();
                 	d.telOrName = encodeURI($("#telOrName").val());
                 	d.overDays = checkbox_CheckedValue("overDays").toString();
                 	d.startDays = $("#startDays").val();
                 	d.endDays = $("#endDays").val();
                 	d.status = encodeURI(checkbox_CheckedValue("status").toString());
                 	d.gfyx = encodeURI(checkbox_CheckedValue("gfyx").toString());
                 	d.startDate = $("#dpd1").val();
	           		d.endDate = $("#dpd2").val();
                 }
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
         	{"data":"overDays"},
         	{"data":"operate"},
         	{"data":"id"},
         	{"data":"oppGUID"},	
         	{"data":"userGuid"},
         ],
          columnDefs:[{
         	targets:9,"render":function(data, type, row, meta){

         		return "<a style='color:#FAA732' href='#' cusId="+data+" oppGUID="+row.oppGUID+" userGuid="+row.userGuid+" onclick='cstDetail(\"" + data + "\",\"cusOverDue\")'>查看</a>&nbsp;" +
       				"<a style='color:#FAA732' href='#' onclick='cstAssign(\"" + data + "\",\""+row.oppGUID + "\",\""+row.userGuid + "\")'>分配</a>&nbsp;" +
       				"<a style='color:#FAA732' href='#' onclick='cstReback(\"" + data + "\",this,\""+row.oppGUID + "\")'>回收</a>&nbsp;" +
       				"<a style='color:#FAA732' href='#' onclick='editCustInfo(\"" + row.id+ "\",\""+row.cstName + "\",\""+ row.mobileTel +"\",\""+ row.userName +"\")'>编辑</a>";
         	}
         },
         {
              "targets": [10,11,12],
               "visible": false,
          },/* {  targets:2,
	         		"orderable":true,
	         		"orderSequence": [ "desc", "asc", "asc" ], 
	         		"ordering": true,
	         		"searchable":true
	         	}, */
	        	{ "sortable": false, "targets":[0,1,2,3,4,7,9,10,11]}
        	] , 
         "ordering":true,
         "order": [6,"desc"],	  
         
	});
	
	/* $('input[type=checkbox],input[type=radio],input[type=file]').uniform(); */
	
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
	
	$("input[name='overDays']").click(function(){
		if($(this).val() == 3) {
			$("#startDays").show();
			$("#endDays").show();
		}else {
			$("#startDays").hide();
			$("#endDays").hide();
		}
	});	
});

</script>
<script>
	function tabSwitch(tab){
		window.location.href = $(tab).attr("target");
	}
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>