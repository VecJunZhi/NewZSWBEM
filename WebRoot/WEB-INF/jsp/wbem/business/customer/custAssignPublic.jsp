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
	      <li><a href="/wbem/business/customer/custAssignNav.action?menuId=${menuId}">跟进中的客户</a></li>
	      <li class="divider-vertical"></li>
	      <li class="active"><a href="/wbem/business/customer/custAssignPublic.action?menuId=${menuId}">公共客户</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/business/customer/custAssignDusBin.action?menuId=${menuId}">垃圾箱</a></li>
	      <li class="divider-vertical"></li>
	    </ul>
	  </div>
	</div>
	<div class="widget-box">
		<div class="widget-content tab-content">
			<div id="tab1" class="tab-pane"> </div>
	        <div id="tab2" class="tab-pane active">
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
	              <label class="control-label zygw" >原置业顾问</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="zygw" >
	                	<option value="" selected></option>
	                  	<c:forEach items="${zygwList}" var="zygw" varStatus="status">
              				<option value="${zygw.userId}">${zygw.userName}</option>
             			</c:forEach>
	                </select>
	              </div>
	            </div> 
	           <div class="row-fluid ">
	             <div class="input-prepend input-append" id="cstStatus">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">客户状态</span>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="无效" />无效</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="问询"/>问询</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="看房"/>看房</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="认购"/>认购</label>
	                <label class="checkbox inline"> <input type="checkbox" name="cstStatus" value="签约"/>签约</label>
	              </div>
	            </div>
	            <div class="row-fluid ">
	             <div class="input-prepend input-append" style="margin-top: 10px;" id="intentionLevel">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">意向级别</span>
	                <label class="checkbox inline"	> <input type="checkbox" name="gfyx" value="必买"/>必买</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="高意向"/>高意向</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="一般意向"/>一般意向</label>
	                <label class="checkbox inline"> <input type="checkbox" name="gfyx" value="无意向"/>无意向</label>
	              </div>
	             </div>
	           <div class="row-fluid ">
	             <div class="input-prepend input-append" style="margin-top: 10px;"  id="rebackReason">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">回收原因</span>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="1"/>逾期客户自动回收</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="2"/>无效客户自动回收</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="3"/>到期未认购客户自动回收</label>
	                <!-- <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="4"/>离职销售客户自动回收</label> -->
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="5"/>手动回收</label>
	              </div>
	            </div>
	             <div class="input-prepend input-append" style="margin-top: 10px;">
	            	<span class="add-on" style="width: 103px;margin-right: 5px;">最近跟进</span>
	            	<input type="text" data-date="${startDate}" data-date-format="yyyy-mm-dd" value="${startDate}" class="datepicker span2" id="dpd1" readonly>	   
		    		<!-- <i class="icon-resize-horizontal" >11111</i> -->
		    		<!-- <label style="width:10px">——</label> -->
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" data-date="${endDate}" data-date-format="yyyy-mm-dd" value="${endDate}" class="datepicker span2" id="dpd2" readonly>
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>公共客户列表</h5>
			<label class="checkbox inline index" id="checklabel" style="display: none;"><input type="checkbox" id="checkall" />选中全部</label>
			<label class="checkbox inline index" id="checklabel_after" style="display: none;"></label>
			
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<button type="button" class="btn btn-success"  onclick="cstMassAssign()">批量分配</button>
				<!-- <button type="button" class="btn btn-success" id="tableSearch">全部分配</button> -->
         		<button type="reset" class=" btn btn-warning"  onclick="cstMassMoveToDustbin()"  >移至垃圾箱</button>
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
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios"  />序号</label></th>
						<th>姓名</th>
						<th>电话</th>
						<th>原置业顾问</th>
						<th>客户状态</th>
						<th>意向级别</th>
						<th>最近跟进时间</th>
						<th>跟进方式</th>
						<th>回收原因</th>
						<th>回收时间</th>
						<th>回收次数</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<input type="hidden" id="ids" value=""/>
<%-- 公共页面--%>
<%@include file="/WEB-INF/jsp/wbem/business/customer/custAssignCommon.jsp" %>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
	$(document).ready(function(){
		$('.data-table').dataTable({
			"ajax": {type: "post",//后台指定了方式。
                 url: "/wbem/business/customer/getZsbusiCustomPublic.action",
                 data:function(d){			//外部参数传递
                 	d.cstStatus=encodeURI(checkbox_CheckedValue("cstStatus").toString());//checkbox_CheckedValue("cstStatus").toString();//$('#cstStatus').find('checkbox:checked').val();
                 	d.gfyx=encodeURI(checkbox_CheckedValue("gfyx").toString());
                 	d.rebackReason=encodeURI(checkbox_CheckedValue("rebackReason").toString());
                	d.userGuid = $('#zygw').find('option:selected').val();
                	d.userName2 = encodeURI($('#zygw').find('option:selected').text());
                 	d.telOrName =encodeURI($('#telOrName').val());
                 	//d.proId=$('#proId').find('option:selected').val();
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
	         	{"data":"rebackReason"},
	         	{"data":"rebackDate"},
	         	{"data":"rebackNums"},
	         	{"data":"id"}
	         ],
	         columnDefs:[{
	         	targets:11,"render":function(data, type, row, meta){
	         	//alert("row "+row.mobileTel);
	         			return "<a style='color:#FAA732' href='#' cusId="+data+"  onclick='cstDetail(\"" + data + "\",\""+row.userName+"\")'>查看</a> "
         				  +"<a style='color:#FAA732' href='#' onclick='cstAssign(\"" + data + "\")'>分配</a> "
         				  +"<a style='color:#FAA732' href='#' onclick='moveToDustbin(\"" + data + "\")'>移至垃圾箱</a> "
         				  +"<a style='color:#FAA732' href='#' onclick='editCustInfo(\"" + data+ "\",\""+row.cstName + "\",\""+ row.mobileTel +"\",\""+ row.userName +"\")'>编辑</a>";
	         	}
	         },
	         	/* {  targets:2,
	         		"orderable":true,
	         		"orderSequence": [ "desc", "asc", "asc" ], 
	         		"ordering": true,
	         		"searchable":true
	         	}, */
	        	{ "sortable": false, "targets":[0,1,2,4,7,8,9,10,11]}
        	] , 
         "ordering":true,
         "order": [6,"asc"],	
	         "initComplete": function(settings, json) {
        	 var ids=json.ids;
       		 ids= ids.substring(1,ids.length-1);
         	 $("#ids").val(ids);
         	 $("#checklabel_after").text("已经选中"+json.recordsTotal+"条记录");
  		}
	         
		});
		
		//$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
		
		$('select').select2( );
		
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
		
		//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().draw();
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
	});
</script>


<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>