<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="navbar">
	  <div class="navbar-inner" style="margin-top:5px;">
	    <ul class="nav">
	      <%-- <li>
	        <form class="navbar-form pull-left">
              <select id="proId" class="span3" >
                <c:forEach items="${projectList}" var="project" varStatus="status">
					<option value="${project.id}">${project.name}</option>
              	</c:forEach>
              </select>
            </form>
	      </li>
	      <li class="divider-vertical"></li> --%>
	      <li><a href="/wbem/houses/customer/custAssignNav.action?menuId=${menuId}">跟进中的客户</a></li>
	      <li class="divider-vertical"></li>
	      <li class="active"><a href="/wbem/houses/customer/custAssignPublic.action?menuId=${menuId}">公共客户</a></li>
	      <li class="divider-vertical"></li>
	      <li><a href="/wbem/houses/customer/custAssignDusBin.action?menuId=${menuId}">垃圾箱</a></li>
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
				  <input class="span4" id="prependedDropdownButton" type="text"><!-- <button class="btn btn-primary" style="margin-left: 5px;">搜索</button> -->
				</div>
				<div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >原置业顾问</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="zygw" >
	                	<option value="" selected></option>
	                  	<c:forEach items="${zygwList}" var="zygw" varStatus="status">
              				<option value="${zygw.value}">${zygw.userName}</option>
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
	            <div class="row-fluid" id="invalidReason" style="display:none;">
	             <div class="input-prepend input-append"  style="margin-top: 10px;">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">无效原因</span>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="1"/>已购其他项目</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="2"/>观望更好时机</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="3"/>已购买本项目</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="4"/>钱不合适暂不考虑</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="5"/>房源不合适暂不考虑</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="6"/>不能办理贷款</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="7"/>接受不了价格</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="8"/>信息重复</label>
	                <label class="checkbox inline"> <input type="checkbox" name="invalidReason" value="9"/>空号 停机</label>
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
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="逾期客户自动回收"/>逾期客户自动回收</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="无效客户自动回收"/>无效客户自动回收</label>
	                <!-- <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="3"/>到期未认购客户自动回收</label>
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="4"/>离职销售客户自动回收</label> -->
	                <label class="checkbox inline"> <input type="checkbox" name="rebackReason" value="手动回收"/>手动回收</label>
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>公共客户列表</h5>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;">
				<button type="button" class="btn btn-success"   onclick="cstMassAssign()">批量分配</button>
				<!-- <button type="button" class="btn btn-success" id="tableSearch">全部分配</button> -->
				<!-- <button type="button" class="btn btn-success" id="tableSearch" disabled>线下分配</button> -->
				<div class="btn-group">
					<button type="button" class=" btn btn-warning dropdown-toggle btn-lg" data-toggle="dropdown">线下分配</button>
					<ul class="dropdown-menu" role="menu">
					      <li><a href="#" onclick="exportExcel()">导出客户资料</a></li>
					      <li class="divider"></li>
					      <li><a href="#" onclick="importExcelResult()">导入分配结果</a></li>
				    </ul>
         		</div>
         		<button type="reset" class=" btn btn-warning" id="resetSearch"  onclick="cstMassMoveToDustbin()">移至垃圾箱</button>
			</div>
         		</div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th>
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
<script>
/* function cstDetail(cusid,cstType){
	//var pid = encodeURI($('#proName').find('option:selected').text());
	layer.open({
        type: 2,
        title: '客户跟进详情',
        maxmin: true,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['800px' , '620px'],
        content: "/wbem/houses/customer/dailog/custDetail.action?cusid="+cusid+"&cstType="+cstType,
    });
} */

$(document).ready(function(){
	$('.data-table').dataTable({
		"ajax": {type: "post",//后台指定了方式。
                url: "/wbem/houses/customer/getZsbusiCustomPublic.action",
                data:function(d){			//外部参数传递
                	d.cstStatus=encodeURI(checkbox_CheckedValue("cstStatus").toString());//checkbox_CheckedValue("cstStatus").toString();//$('#cstStatus').find('checkbox:checked').val();
                	d.gfyx=encodeURI(checkbox_CheckedValue("gfyx").toString());
                	d.rebackReason=encodeURI(checkbox_CheckedValue("rebackReason").toString());
               		d.userGuid = $('#zygw').find('option:selected').val();
                	d.telOrName =encodeURI($('#prependedDropdownButton').val());
                	//d.proId=$('#proId').find('option:selected').val();
                	d.invalidReason = checkbox_CheckedValue("invalidReason").toString();
                	d.rebackReason = encodeURI(checkbox_CheckedValue("rebackReason").toString());//2016-7-17
                }
         },
         "columns":[
         	{"data":"index"},
         	{"data":"cstName"},
         	{"data":"mobileTel"},
         	{"data":"oldZygw"},
         	{"data":"status"},
         	{"data":"gfyx"},
         	{"data":"lastDate"},
         	{"data":"gjfs"},
         	{"data":"rebackReason"},
         	{"data":"rebackDate"},
         	{"data":"rebackNums"},
         	{"data":"id"},
         	{"data":"oppGUID"},
         	{"data":"userGuid"},	
         ],
         columnDefs:[{
         	targets:11,"render":function(data, type, row, meta){
         			return "<a style='color:#FAA732' href='#' cusId="+data+"  oppGUID="+row.oppGUID+" userGuid="+row.userGuid+" onclick='cstDetail(\"" + data + "\",\"public\")'>查看</a> "
         				  +"<a style='color:#FAA732' href='#' onclick='cstAssign(\"" + data + "\",\""+row.oppGUID + "\",\""+row.userGuid + "\")'>分配</a> "
         				  +"<a style='color:#FAA732' href='#' onclick='moveToDustbin(\"" + data + "\",\""+row.oppGUID + "\")'>移至垃圾箱</a> "
         				  +"<a style='color:#FAA732' href='#' onclick='editCustInfo(\"" + row.id+ "\",\""+row.cstName + "\",\""+ row.mobileTel +"\",\"公共客户\")'>编辑</a>";
         	}
         },{
              "targets": [12,13],
               "visible": false,
          },/* {  targets:2,
	         		"orderable":true,
	         		"orderSequence": [ "desc", "asc", "asc" ], 
	         		"ordering": true,
	         		"searchable":true
	         	}, */
	       { "sortable": false, "targets":[0,1,2,3,4,7,8,9,10,11]}
        	] , 
         "ordering":true,
         "order": [6,"desc"],	 
         
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
		$("#prependedDropdownButton").val("");
		$("#dpd1").val("");
		$("#dpd2").val("");
		$("input[type='checkbox']").attr("checked",false);
		$("input[type='radio']").attr("checked",false);
		$("#invalidReason").css("display","none");//2016-7-6
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$("input[name='cstStatus']").click(function(){//2016-7-6
		if(($(this).prop("checked") == true) && $(this).val() == '无效') {
			$("input[name='invalidReason']").prop("checked",true);
			$("#invalidReason").css("display","block");
		}
		if(($(this).prop("checked") == false) && $(this).val() == '无效') {
			$("#invalidReason").css("display","none");
			$("input[name='invalidReason']").prop("checked",false);
		}
	});
	
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>