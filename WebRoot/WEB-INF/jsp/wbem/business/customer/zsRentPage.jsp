<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <%-- <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >选择项目</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="projGuid" >
	                    <c:forEach items="${projectList}" var="project" varStatus="status">
	                     	<c:if test="${project.name == '世纪嘉园' }">
              					<option value="${project.id}" selected>${project.name}</option><!-- 默认世纪嘉园被选中 -->
              				</c:if>
              				<c:if test="${project.name != '世纪嘉园' }">
              					<option value="${project.id}">${project.name}</option>
              				</c:if>
              	  		</c:forEach>
	                </select>
	              </div>
	            </div>  --%>
	            <div class="row-fluid ">
	              <div class="input-prepend input-append" >
	              <span class="add-on" style="width: 103px;margin-right: 5px;">销售状态</span>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="saleStatus"  style="margin-left: 0;" value="已售"/>已售</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="saleStatus"  style="margin-left: 0;" value="未售"/>未售</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="saleStatus"  style="margin-left: 0;" value="不可售"/>不可售</label>
	            </div>
	            </div>
				<div class="row-fluid ">
	              <div class="input-prepend input-append span5" style="margin-top: 10px;">
	              <span class="add-on" style="width: 103px;margin-right: 5px;">商铺状态</span>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="已租" style="margin-left: 0;" />已租</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="可租" style="margin-left: 0;" />可租</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="不可租" style="margin-left: 0;" />不可租</label>
	                <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="shopStatus" value="自营" style="margin-left: 0;" />自营</label>
	                
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>租赁详情列表</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th>
						<th>铺位号</th>
						<th>门牌号</th>
						<th>一面积</th>
						<th>二面积</th>
						<th>总面积</th>
						<th>销售状态</th>
						<th>商铺状态</th>
						<th>租赁情况</th>
						<th>租金总额</th>
						<th>日平米租金</th>
						<th>签约日期</th>
						<th>意向客户</th>
						<th>招商专员</th>
						<th>大客户经理</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

	function editRentInfo(unitNo) {
		//var projGuid = $('#projGuid').find('option:selected').val();
		layer.open({
	        type: 2,
	        title: '租赁信息',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['490px' , '650px'],
	        content: "/wbem/business/customer/editRentPage.action?unitNo="+unitNo
	    });
	}

$(document).ready(function(){
	//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$("#resetSearch").click(function(){
		$("input[type='radio']").attr("checked",false);
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"ajax": {
			type: "post",//后台指定了方式。
         	url: "/wbem/business/customer/loadRentList.action",
		 	data:function(d){			//外部参数传递
		 		//d.projGuid=$('#projGuid').find('option:selected').val();
		 		d.saleStatus = encodeURI($("input:radio[name=saleStatus]:checked").val()==undefined?"":$("input:radio[name=saleStatus]:checked").val());
		 		d.shopStatus = encodeURI($("input:radio[name=shopStatus]:checked").val()==undefined?"":$("input:radio[name=shopStatus]:checked").val());
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"unitNo"},
         	{"data":"addressNo"},
         	{"data":"firstArea"},
         	{"data":"secondArea"},
         	{"data":"totalArea"},
         	{"data":"saleStatus"},
         	{"data":"shopStatus"},
         	{"data":"rentStatus"},
         	{"data":"totalRent"},
         	{"data":"dayRent"},
         	{"data":"signUpDate"},
         	{"data":"intentionCst"},
         	{"data":"investmentOfficer"},
         	{"data":"keyAccountManager"},
         	{"data":"operate"},
         ],
          "columnDefs":[
            {
         		targets:15,"render":function(data){
	       			return	"<shiro:hasPermission name='button:rentManage:rentInfo:edit'><a style='color:#FAA732' href='#' onclick='editRentInfo(\"" + data + "\")'>编辑</a></shiro:hasPermission>" ;	
         		}
         	}, 
	        //{ "sortable": false, "targets":[0,1,3,4,5,11,12,13]}
          ] , 
        /*  "ordering":true,
         "order": [9,"desc"],	 */  
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