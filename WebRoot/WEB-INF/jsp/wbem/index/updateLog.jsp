<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="navbar">
	  <div class="navbar-inner" >
	  <h3 style="margin-left:70px"></h3>  
	  <div class="widget-content tab-content" style="">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <div class="control-group noborder-top noborder-bottom">
	              <label class="control-label zygw" >������</label>
	              <div class="controls span5" style="margin-left: 0">
	              	<select style="width:150px" id="userName">
	              	   <option  ></option>
	              	   <c:forEach items="${userNameList}" var="zygw" varStatus="status">
              			  <option >${zygw.username} </option>
             		   </c:forEach> 
             		   <option ></option>
	              	</select>
	              </div>
	            </div>
				<div class="row-fluid ">
				  <div class="input-prepend input-append" style="margin-top: 10px;">
	                  <span class="add-on" style="width: 103px;margin-right: 5px;">�������</span>
	                  <label class="radio inline" style="margin-right: 0px;"><input type="hidden" id="classesAll" value="" style="margin-left: 0;" /></label>
	                  <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="logClasses" id="classesQ" value="" style="margin-left: 0;" />ǰ̨</label>
	                  <label class="radio inline" style="margin-right: 0px;"><input type="radio" name="logClasses" id="classesH" value="" style="margin-left: 0;" />��̨</label>
	              </div>
	            </div>
	            <div class="row-fluid" style="margin-left:15px">
	              <div class="control-group noborder-bottom noborder-top span4"> 
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">��ѯ</button>
	                  <button type="button" class="btn btn-success" id="resetSearch">����ɸѡ</button>
	                </div>
	             </div> 
	            </div>  
	           </div>  
	          </form>
	        </div>
	    </div>
	  </div>
	
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>��־�б�</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
			 <div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;"> 
			 <button type="button" class="btn btn-success" onclick="addUpdateLog()" style="margin-right:px">������־</button>
			 </div> 
         </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th> 
						<!-- <th>���</th> -->
						<th>��־���� </th>
						<th>��ϸ����</th>
						<th>�������</th>
						<th>������</th>
						<th>��ע</th>
						<th>����</th>
						<th>����</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
</div>
<%@include file="/WEB-INF/jsp/wbem/index/addUpdateLog.jsp" %>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
$(document).ready(function(){
	$("#classesAll").attr("value","");
	$("#classesH").click(function(){
		$("#classesAll").attr("value","��̨");
	});
	$("#classesQ").click(function(){
		$("#classesAll").attr("value","ǰ̨");
	}); 
	//��ѯ
	$("#tableSearch").click(function(){
		/* alert($("#classesAll").val());
		alert($("#userName option:selected").val()); */
		$('.data-table').DataTable().ajax.reload();
	});
	$("#resetSearch").click(function(){
		$("input[type='radio']").attr("checked",false);
		$("#userName").select2("val","");
		$("#classesAll").attr("value","");
		//$("#userName option:selected").val();���ѡ��ֵ
		/*$("input[type='checkbox']").attr("checked",false);
		  $("input[type='radio']").attr("checked",false); */
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	$('.data-table').DataTable({
		"ajax": {
			type: "post",//��ָ̨���˷�ʽ��
         	url: "/wbem/index/updateLogContent.action",
         	data:function(d){
         		d.logClasses = encodeURI($("#classesAll").val());
         		d.userName = encodeURI($("#userName option:selected").val());
         	}
         },
         "columns":[
         	{"data":"logId"},
         	{"data":"logSubject"},
         	{"data":"logContent"},
         	{"data":"logClasses"},
         	{"data":"userName"},
         	{"data":"remark"},
         	{"data":"logTime"},
         	{"data":"operate"},
         ],
	     columnDefs:[
	        {targets:7,"render":function(data, type, row, meta){
         	   	return "<a style='color:#FAA732' href='#' onclick='queryContent(\"" + row.logContent+ "\",\""+row.logSubject + "\")'>��ѯ</a>&nbsp;"+
         	   		   "<a style='color:#FAA732' href='#' onclick='editLog(\"" + row.logContent+ "\",\""+row.logSubject + "\",\""+row.logTime + "\")'>�༭</a>&nbsp;"+
         	   		   "<a style='color:#FAA732' href='#' onclick='delLog(\""+row.logTime + "\")'>ɾ��</a>&nbsp;";
         	   	}
         	}, 
         ],
    	"ordering":false, 
        "order": [6,"asc"],	  
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