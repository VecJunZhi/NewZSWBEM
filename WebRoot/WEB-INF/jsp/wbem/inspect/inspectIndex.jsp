<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-content tab-content">
	        <div id="tab1" class="tab-pane active"> 
			  <form action="#" method="get" class="form-horizontal">
			    <div class="control-group noborder-top noborder-bottom">
	              <%-- <label class="control-label zygw" >选择项目</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="projGuid" onchange="changeProj(this)">
	                     <c:forEach items="${projectList}" var="project" varStatus="status">
              				<option value="${project.id}">${project.name}</option>
              	  		</c:forEach>
	                </select>
	              </div>  --%>
	              <label class="control-label zygw" >楼栋</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="bldGuid" onchange="changeBld(this)">
	                	<option selected="selected"></option>
	                   <c:forEach items="${bldList}" var="bld" varStatus="status">
              			<option value="${bld.build.bldGuid}">${bld.build.bldName}</option>
             		   </c:forEach>
	                </select>
	              </div>
	              <label class="control-label zygw" >单元</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="unitGuid" >
	                	<option selected="selected"></option>
	                </select>
	              </div> 
	            </div> 
	            <div class="control-group noborder-top noborder-bottom">
	              
	              <label class="control-label zygw" >分类</label>
	              <div class="controls span5" style="margin-left: 0">
	                <select id="mainType" >
	                	<option value=""></option>
	                	<c:forEach items="${typeList}" var="type" varStatus="status">
	                		<option value="${type.issueCode}">${type.issueName}</option>
	                	</c:forEach>
	                </select>
	              </div>
	              <div class="input-prepend input-append" style="margin-top:10px;">
					  <span class="add-on" style="width: 103px;margin-right: 5px;">修复状态</span>
					  <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="0" style="margin-left: 0;"/>未修复</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="1" style="margin-left: 0;"/>修复完成</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="4" style="margin-left: 0;"/>复修完成</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="3" style="margin-left: 0;"/>退回返修</label>
		              <label class="radio inline" style="margin-right: 0px;"> <input type="radio" name="status" value="2" style="margin-left: 0;"/>确认完成</label>
				 </div>
	            </div>
	            
	            <div class="control-group noborder-top noborder-bottom">
	              <div class="control-group noborder-bottom noborder-top"> 
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
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>问题列表</h5>
			<label class="checkbox inline index" id="checklabel"> </label>
			<div class="label" style="background-color: #EFEFEF;box-shadow:0 0 0 0;margin:0;"> 
				<shiro:hasPermission name="button:propertyManage:addIssues:add"><button type="button" class="btn btn-primary" onclick="addIssue()">登记问题</button></shiro:hasPermission>
				<shiro:hasPermission name="button:propertyManage:addIssues:print"><button type="button" class="btn btn-success" onclick="print()">打印</button></shiro:hasPermission>
			 </div> 
         </div>
         <!--startprint1-->
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th>
						<th>楼栋</th>
						<th>单元</th>
						<th>房间号</th>
						<th>问题位置</th>
						<th>问题类型</th>
						<th>问题描述</th>
						<th>紧急程度</th>
						<th>问题状态</th>
						<th>创建日期</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
		<!--endprint1-->
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

 function addIssue(){
	//var projGuid=$('#projGuid').find('option:selected').val();
	layer.open({
        type: 2,
        title: false,
       // maxmin: true,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        closeBtn:0,
        area : ['1200px' , '620px'],
        content: "/wbem/inspect/addIssues.action"
    });
} 

function inspectFeedback(issuesId,status) {
	layer.open({
        type: 2,
        title: status==1?'确认验收':'退回返修',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['490px' , '300px'],
        content: "/wbem/inspect/inspectFeedback.action?issuesId="+issuesId+"&status="+status
    });
}
 
function issuesDetail(issuesId) {
	layer.open({
        type: 2,
        title: '问题反馈详情',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['850px' , '600px'],
        content: "/wbem/inspect/issuesFeedbackDetail.action?issuesId="+issuesId
    });
}


function print() {
	//var projGuid = $("#projGuid option:selected").val();
	var bldGuid = $("#bldGuid option:selected").val();
	var unitGuid = $("#unitGuid option:selected").val();
	var mainType = $("#mainType option:selected").val();
	var status = checkbox_CheckedValue("status").toString();
	layer.open({
        type: 2,
        title: '提示',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['300px' , '200px'],
        content: "/wbem/inspect/inspectPrint.action?bldGuid="+bldGuid+"&unitGuid="+unitGuid+"&status="+status+"&mainType="+mainType
    });
}
/* function changeProj(tag){
	var projGuid = $("option:selected",tag).val();
	$.ajax({
		url: '/wbem/inspect/loadBldList.action',
		data: {'projGuid':projGuid},
		type: 'post',
		error:function(){
			
		},
		success:function(data){
			$("#bldGuid").select2("val","");
			$("#bldGuid").empty();
			var str = "<option></option>";
			for(var i=0; i<data.length; i++) {
				str += "<option value="+data[i].build.bldGuid+">"+data[i].build.bldName+"</option>";
			}
			$("#bldGuid").append(str);
		}
	})
} */
function changeBld(tag) {
	var bldGuid = $("option:selected",tag).val();
	$.ajax({
		url: '/wbem/inspect/loadUnitList.action',
		data: {'bldGuid':bldGuid},
		type: 'post',
		error:function(){
			
		},
		success:function(data){
			$("#unitGuid").select2("val","");
			$("#unitGuid").empty();
			var str = "<option></option>";
			for(var i=0; i<data.length; i++) {
				str += "<option value="+data[i].unitGuid+">"+data[i].unit+"</option>";
			}
			$("#unitGuid").append(str);
		}
	})
}

function issuesDel(issuesId) {
	var index = layer.open({
        type: 1,
        title: '提示',
        maxmin: false,       //开启最大最小化
        shadeClose: false, //点击遮罩关闭层
        area : ['350px' , '200px'],
        content: "<div style='text-align: left;margin-left: 20px;font-size: 14px;margin-top: 30px;font-weight: bold;'>确定删除当前问题？</div>",
        btn:['确定','取消'],
        yes:function(){
        	$.ajax({
        		url:'/wbem/inspect/deleteIssueDetailById.action',
        		data:{"issuesId":issuesId},
        		type:'post',
        		error:function(){
        			//alert("error");
        		},
        		success:function(data){
        			//$('.data-table').DataTable().ajax.reload();
        			//var index2 = parent.layer.getFrameIndex(window.name);
        			layer.close(index);
                	parent.$('.data-table').DataTable().ajax.reload();
                	//parent.layer.close(index2);
        		}
        	});
        },
    });
	
}

$(document).ready(function(){
	//查询
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().ajax.reload();
	});
	
	$("#resetSearch").click(function(){

		$("#bldGuid").select2("val","");
		$("#unitGuid").select2("val","");
		$("input[type='radio']").attr("checked",false);
		$("#mainType").select2("val","");
		//$("#telOrName").val("");
		//$("input[type='checkbox']").attr("checked",false);
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	$('.data-table').dataTable({
		"lengthMenu": [15,25,50,100,200,300,500],
		"ajax": {
			type: "post",//后台指定了方式。
         	url: "/wbem/inspect/loadIssuesList.action",
		 	data:function(d){			//外部参数传递
		 		//d.projGuid=$('#projGuid').find('option:selected').val();
	         	d.bldGuid = $('#bldGuid').find('option:selected').val();
	         	d.unitGuid = $('#unitGuid').find('option:selected').val();
	         	d.status = checkbox_CheckedValue("status").toString();
	         	d.mainType = $('#mainType').find('option:selected').val();
	    		/*d.urgedType = checkbox_CheckedValue("urgedType").toString(); */
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"bldName"},
         	{"data":"unit"},
         	{"data":"room"},
         	{"data":"issuePosition"},
         	{"data":"issueType"},
         	{"data":"issueDesc"},
         	{"data":"level"},
         	{"data":"status"},
         	{"data":"createDate"},
         	{"data":"operate"},
         ],
         "columnDefs":[
            {
         		targets:10,"render":function(data, type, row, meta){
         			if(row.status == "工程修复完成" || row.status == "工程复修完成") { 
         			 return	 "<shiro:hasPermission name='button:propertyManage:addIssues:inspectFeedback'><a style='color:#FAA732' href='#' onclick='inspectFeedback(\"" + data + "\",\"1\")'>确认验收</a></shiro:hasPermission>&nbsp;" + 
	       				    "<shiro:hasPermission name='button:propertyManage:addIssues:inspectFeedback'><a style='color:#FAA732' href='#' onclick='inspectFeedback(\"" + data + "\",\"2\")'>退回返修</a></shiro:hasPermission>&nbsp;"+
	       				 "<a style='color:#FAA732' href='#' onclick='issuesDetail(\"" + data + "\")'>查看</a>";
	       			}
         			if(row.status == "未修复") {
       					return "<shiro:hasPermission name='button:propertyManage:addIssues:delete'><a style='color:#FAA732' href='#' onclick='issuesDel(\"" + data + "\")'>删除</a></shiro:hasPermission>";	
         			}
         			if(row.status == "物业退回返修" || row.status == "物业确认完成") {
         				return	 "<a style='color:#FAA732' href='#' onclick='issuesDetail(\"" + data + "\")'>查看</a>";
         			} 
         		}
         	}, 
	        //{ "sortable": false, "targets":[0,1,3,4,5,10,11,12]}
          ] , 
         //"ordering":true,
         //"order": [8,"desc"],//默认排序	  
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