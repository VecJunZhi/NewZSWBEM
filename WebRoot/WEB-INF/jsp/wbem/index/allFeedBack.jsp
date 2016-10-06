<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>意见与反馈</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
			 <div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;"> 
			 </div> 
         </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th> 
						<th>主题 </th>
						<th>内容</th>
						<th>发布人</th>
						<th>日期</th>
						<th>提示</th>
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
<%@include file="/WEB-INF/jsp/wbem/index/allFeedBackCommon.jsp" %>
<script>
 $(document).ready(function(){
 	
 
 
	$('.data-table').DataTable({
		"ajax": {
			type: "post",
         	url:"/wbem/index/allFeedBackList.action"
         },
         "columns":[
         	{"data":"logId"},
         	{"data":"logSubject"},
         	{"data":"logContent"},
         	{"data":"userName"},
         	{"data":"logTime"},
         	{"data":"warn"},
         	{"data":"operate"},
         ],
	     columnDefs:[
	        {targets:6,"render":function(data, type, row, meta){
         	   	return "<a style='color:#FAA732' href='#' onclick='queryReplyContent(\"" + row.logSubject+ "\",\"" + row.logContent+ "\",\"" + row.logTime+ "\")'>详情</a>&nbsp;"+
         	   		   "<a style='color:#FAA732' href='#' onclick='addReplyContent(\"" + row.logTime+ "\")'>回复</a>&nbsp;"+
         	   		   "<a style='color:#FAA732' href='#' onclick='delFeedBack(\"" + row.logTime+ "\")'>删除</a>&nbsp;"
         	   		   
         	   	}
         	} 
         ]
	});
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
}); 
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>