<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<style>
<!--
.container-fluid {
    padding-right: 0;
    padding-left: 0;
    padding-top:0 !important;
    }
-->
</style>
<table id="issueDetail" style="display:none">
	<tr>
	   	<td style="width:10%">
	   		<select name="issuePosition" style="width:100%">
	   			<option value=""></option>
	   			<option value="客厅">客厅</option>
	   			<option value="主卧">主卧</option>
	   			<option value="次卧">次卧</option>
	   			<option value="厨房">厨房</option>
	   			<option value="餐厅">餐厅</option>
	   			<option value="卫生间">卫生间</option>
	   			<option value="门厅">门厅</option>
	   			<option value="楼道">楼道</option>
	   			<option value="楼梯间">楼梯间</option>
	   			<option value="管道井">管道井</option>
	   		</select>
	   	</td>
	   	<td style="width:15%">
	   		<select name="mainType" onchange="changeType(this)" style="width:100%">
	   			<option value="-1"></option>
	   			<c:forEach items="${mainTypeList}" var="mainType" varStatus="status" >
	        		<option value="${mainType.issueCode}">${mainType.issueName}</option>
	        	</c:forEach>
	   		</select>
	   	</td>
	   	<td style="width:20%">
	   		<select name="subType" style="width:100%">
	   			<option value="-1"></option>
	   		</select>
	   	</td>
	   	<td style="width:10%">
	   		<select name="level" style="width:100%">
	   			<option value="0">一般</option>
	         	<option value="1">紧急</option>
	   		</select>
	   	</td>
	   	<td style="width:40%">
	   		<input name="issueDesc" type="text" style="width: 100%;padding-left: 0;padding-right: 0;font-size: 12px;" />
	   	</td>
	   	<td style="width:10%">
	   		<button type="button" class="btn btn-danger btn-mini" onclick="delIssues(this)" index="1" style="margin-left:25%">-</button>
	   	</td> 
	</tr>
</table>

<div class="container-fluid">
	<div class="widget-box">
		<div class="widget-title ">
		  <ul class="nav nav-tabs">
		    <c:forEach	items="${bldInfoList}" var="bldInfo" varStatus="status">
		    	<c:if test="${status.count == 1}">
		    		<li class="active" bldGuid="${bldInfo.build.bldGuid}" bldName="${bldInfo.build.bldName}"><a  onclick="loadUnitRoomInfo('${bldInfo.build.bldGuid}','${bldInfo.build.bldName}',2,this)" onclick="">${bldInfo.build.bldName}</a></li>
		    	</c:if>
		    	<c:if test="${status.count != 1}">
		    		<li class="" ><a  onclick="loadUnitRoomInfo('${bldInfo.build.bldGuid}','${bldInfo.build.bldName}',2,this)">${bldInfo.build.bldName}</a></li>
		    	</c:if>
		    </c:forEach>
		    <!-- <li><button type="button" class="btn btn-success btn-mini" onclick="openOrClose()" style="position:absolute;right:6px;margin-top: 6px;">展开/收回</button></li> -->
		  </ul>
		  
		</div>
		<div class="widget-content tab-content" style="background-color:#fff;border-bottom:0;padding-bottom:0">
			<div id="tab1" class="tab-pane active">  
				<form action="#" method="post" class="form-horizontal">
			 		<!-- <div class="row-fluid"> -->
			 		<%-- <input type="hidden" name="projGuid" value="${bldInfoList[0].build.projGuid}"/> --%>
			 		<input type="hidden" name="bldGuid" value="${bldInfoList[0].build.bldGuid}"/>
			 		<input type="hidden" id="status" value="${status}"/>
		     		<div>
		     		<div style="float:left;width:43%">
					<div id="unitRoomInfo">
					</div>
					<div style="margin-top:10px">
						<span class="badge" >初始状态</span>
						<span class="badge badge-warning" style="margin-left:20px;width:57px;text-align:center;">未修复</span>
						<span class="badge" style="margin-left:20px;background-color:#57ADF7;width:57px;text-align:center;">已修复</span>
					</div>
					</div>
					<div style="float:left;width:57%">
					<div class="widget-box" style="margin-top:0">
		     			<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>问题列表</h5>
							<label class="checkbox inline index" id="checklabel"> </label>
	         			</div>
						<div class="widget-content nopadding">
							<table class="table table-bordered data-table">
								<thead>
			                		<tr>
										<th>序号</th>
										<th>问题部位</th>
										<th>分类</th>
										<th>问题描述</th>
										<th>状态</th>
										<th>创建日期</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
							
							<table class=""  style="width:100%;display:none" id="editIssueInfo">
								<input type="hidden" value="" name="editIssuesId"/>
				    			<tr>
							    	<td style="width:10%">
							    		<select name="editIssuePosition" style="width:100%">
							    			<option value=""></option>
								   			<option value="客厅">客厅</option>
								   			<option value="主卧">主卧</option>
								   			<option value="次卧">次卧</option>
								   			<option value="厨房">厨房</option>
								   			<option value="餐厅">餐厅</option>
								   			<option value="卫生间">卫生间</option>
								   			<option value="门厅">门厅</option>
								   			<option value="楼道">楼道</option>
								   			<option value="楼梯间">楼梯间</option>
								   			<option value="管道井">管道井</option>
							    		</select>
							    	</td>
							    	<td style="width:15%">
							    		<select name="editMainType" onchange="editChangeType(this)" style="width:100%">
							    			<option value="-1"></option>
							    			<c:forEach items="${mainTypeList}" var="mainType" varStatus="status" >
			             						<option value="${mainType.issueCode}">${mainType.issueName}</option>
			             	  				</c:forEach>
							    		</select>
							    	</td>
							    	<td style="width:20%">
							    		<select name="editSubType" style="width:100%">
							    			<option value="-1"></option>
							    		</select>
							    	</td>
							    	<td style="width:10%">
							    		<select name="editLevel" style="width:100%">
							    			<option value="0">一般</option>
								         	<option value="1">紧急</option>
							    		</select>
							    	</td>
							    	<td style="width:37%">
							    		<input name="editIssueDesc" type="text" style="width: 100%;padding-left: 0;padding-right: 0;font-size: 12px;" />
							    	</td>
							    	<td style="width:13%">
							    		<button type="button" class="btn btn-danger btn-mini" onclick="cancleEdit()" index="0">X</button>
							   			<button type="button" class="btn btn-success btn-mini" onclick="sureEdit()" index="0" style="clear:both;">√</button>
							    	</td>
							    </tr>
				    		</table>
						</div>
					</div>
					<div class="widget-box">
					<button type="button" class="btn btn-danger" onclick="delIssues(this)" index="0" style="float:right;display:none" id="deleteIssues">X</button>
					<div class="widget-content nopadding">
          				<div class="control-group noborder-bottom" >
				        	<table class=""  style="width:100%" id="addIssueList">
				    			<tr>
							    	<th>问题部位</th>
							    	<th>问题分类</th>
							    	<th>问题子类</th>
							    	<th>紧急程度</th>
							    	<th>问题描述</th>
							    	<th><button type="button"  class="btn btn-success btn-mini" onclick="addIssues()">+</button></th>
				    			</tr>
				    			<tr>
							    	<td style="width:10%">
							    		<select name="issuePosition0" style="width:100%">
							    			<option value=""></option>
								   			<option value="客厅">客厅</option>
								   			<option value="主卧">主卧</option>
								   			<option value="次卧">次卧</option>
								   			<option value="厨房">厨房</option>
								   			<option value="餐厅">餐厅</option>
								   			<option value="卫生间">卫生间</option>
								   			<option value="门厅">门厅</option>
								   			<option value="楼道">楼道</option>
								   			<option value="楼梯间">楼梯间</option>
								   			<option value="管道井">管道井</option>
							    		</select>
							    	</td>
							    	<td style="width:15%">
							    		<select name="mainType0" onchange="changeType(this)" style="width:100%">
							    			<option value="-1"></option>
							    			<c:forEach items="${mainTypeList}" var="mainType" varStatus="status" >
			             						<option value="${mainType.issueCode}">${mainType.issueName}</option>
			             	  				</c:forEach>
							    		</select>
							    	</td>
							    	<td style="width:20%">
							    		<select name="subType0" style="width:100%">
							    			<option value="-1"></option>
							    		</select>
							    	</td>
							    	<td style="width:10%">
							    		<select name="level0" style="width:100%">
							    			<option value="0">一般</option>
								         	<option value="1">紧急</option>
							    		</select>
							    	</td>
							    	<td style="width:40%">
							    		<input name="issueDesc0" type="text" style="width: 100%;padding-left: 0;padding-right: 0;font-size: 12px;" />
							    	</td>
							    	<td style="width:10%"><button type="button" class="btn btn-danger btn-mini" onclick="delIssues(this)" index="0" style="display:none">-</button>
							    	</td>
							    </tr>
				    		</table>
				         </div>
					     <!-- <div class="control-group noborder-bottom"> 
			       			<label class="control-label">上传图片 :</label>
					       <div class="controls">
					         <input type="file" name="imgPath0">
						  <button type="button">预览</button>
					       </div>
					    </div> -->
				    </div>
				    </div>
				    </div>
				    </div>
		        	<!-- <div style="height:30px;display:none;font-size:14px" id="selectRoomInfo" class="span6"> -->
	        		</div>
        			<div class="" id="operateGroup"> 
           				<div class="controls" style="float:right">
		                  <button type="button" class="btn btn-success" id="saveClose">保存关闭</button>
		                  <button type="button" class="btn btn-primary" id="saveNew">保存新增</button>
		                  <button type="button" class="btn btn-warning" id="cancle">取消</button>
            			</div>
        			</div>
        			<input type="hidden" name="issuesStr"/>
				</form>
			</div>
		</div>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>

var issuesNum = 0;
var selectNum = 0;
var roomGuid = "init";
var indexList = new Array();

function loadUnitRoomInfo(bldGuid,bldName,type,tag) {
	var num = 0;
	$("#roomInfo input[name='roomGuid']").each(function(){
		if($(this).attr("checked")){
			num++;
		}
	});
	if(type == 2 && num != 0){	
		var index = layer.open({
	        type: 1,
	        title: '提示',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['350px' , '200px'],
	        content: "<div style='text-align: left;margin-left: 20px;font-size: 14px;margin-top: 30px;font-weight: bold;'>确定放弃当前新增问题？</div>",
	        btn:['确定','取消'],
	        yes:function(){
	        	layer.close(index);
	        	$("input[name='bldGuid']").val(bldGuid);
	        	for(var i=1; i<indexList.length; i++) {
	        		if(indexList[i] == "1") {
	        			$("select[name='mainType"+i+"']").parent().parent().remove();
	        			indexList[i] = "0";
	        		}
	        	}//删除放弃新增的n个问题
	        	$("input[name='issueDesc0']").val("");
	        	$("select[name='mainType0'] option:first").attr("selected",true);
	        	$("select[name='subType0'] option:first").attr("selected",true);
	        	$("select[name='issuePosition0'] option:first").attr("selected",true);//清空第一个问题选项
	        	
	        	$(".nav-tabs li").removeClass("active");
	        	$(tag).parent().addClass("active");
	        	
	        	$("#unitRoomInfo").css("display","block");
	        	$("#selectRoomInfo").css("display","none");
	        	selectNum = 0;
	        	roomGuid = "init";
	        	$('.data-table').DataTable().ajax.reload();
	        	
	        	$.ajax({
	        		url:'/wbem/inspect/bldUnitRoomInfo.action',
	        		data:{"bldGuid":bldGuid,"bldName":encodeURI(bldName)},
	        		type:'post',
	        		dataType:'html',
	        		error: function() {
	        			//alert('error');
	        		},
	        		success:function(data){
	        			$("#unitRoomInfo").html(data.match(/<table([\s\S]*?)<\/table>/)[0]);
	        		}
	        	});
	        },
	        
	    });
	}
	if(type == 1 || num == 0) {
		if(num == 0 && type != 1) {
			$(".nav-tabs li").removeClass("active");
	    	$(tag).parent().addClass("active");
	    	$("#unitRoomInfo").css("display","block");
        	$("#selectRoomInfo").css("display","none");
		}
    	$("input[name='bldGuid']").val(bldGuid);
		$.ajax({
			url:'/wbem/inspect/bldUnitRoomInfo.action',
			data:{"bldGuid":bldGuid,"bldName":encodeURI(bldName)},
			type:'post',
			dataType:'html',
			error: function() {
				//alert('error');
			},
			success:function(data){
				$("#unitRoomInfo").html(data.match(/<table([\s\S]*?)<\/table>/)[0]);
			}
		});		
	}
}

function openOrClose() {
	var display = $("#unitRoomInfo").css("display");
	var selectRoomInfo = "";
	if(display == "block") {
		$("#unitRoomInfo").css("display","none");
		var unit = new Array("","","","");
		$("#roomInfo td").each(function(){
			if($("input",this).attr("checked")) {
				var roomNo = $(this).text();
				var unitNo = $("input",this).attr("unitNo");
				unit[unitNo-1] += "  "+roomNo;
			}
		});
		if(unit[0] != "") {
			selectRoomInfo += "   一单元:"+unit[0];
		}
		if(unit[1] != "") {
			selectRoomInfo += "   二单元:"+unit[1];
		}
		if(unit[2] != "") {
			selectRoomInfo += "   三单元:"+unit[2];
		}
		if(unit[3] != "") {
			selectRoomInfo += "   四单元:"+unit[3];
		}
		$("#selectRoomInfo").text(selectRoomInfo);
		$("#selectRoomInfo").css("display","block");
	}
	if(display == "none") {
		$("#unitRoomInfo").css("display","block");
		$("#selectRoomInfo").css("display","none");
	}
}

function delIssues(tag) {
	var index = $(tag).attr("index");
	indexList[index]  = "0";
	$("input[name='issuesStr']").val(indexList.join(","));
	$(tag).parent().parent().remove();
}


function addIssues() {
	issuesNum++;
	$("#issueDetail button").attr("index",issuesNum);
	
	$("#issueDetail select").each(function(){
		$(this).attr("name",$(this).attr("name")+issuesNum);
	});
	$("#issueDetail input").each(function(){
		$(this).attr("name",$(this).attr("name")+issuesNum);
	});
	
	//$("#operateGroup").before($("#issueDetail").html());
	$("#addIssueList tbody").append($("#issueDetail").html().match(/<tr([\s\S]*?)<\/tr>/)[0]);
	
	indexList.push("1");
	$("input[name='issuesStr']").val(indexList.join(","));
	
	//$('select').select2();
	$("#issueDetail select").each(function() {
		var name = $(this).attr("name");
		if(issuesNum < 10) {
			$(this).attr("name",name.substring(0,name.length-1));
		}
		if(issuesNum >=10 && issuesNum < 100) {
			$(this).attr("name",name.substring(0,name.length-2));
		}
	});
	$("#issueDetail input").each(function(){
		var name = $(this).attr("name");
		if(issuesNum < 10) {
			$(this).attr("name",name.substring(0,name.length-1));
		}
		if(issuesNum >=10 && issuesNum < 100) {
			$(this).attr("name",name.substring(0,name.length-2));
		}
	});
}

function selectRoom(tag) {
	if($(tag).attr("checked")) {
		selectNum ++;
		if(selectNum == 1) {
			roomGuid = $(tag).val();
		}else {
			roomGuid = "init";
		}
	} else {
		selectNum --;
		if(selectNum == 1) {
			$("#roomInfo input[name='roomGuid']").each(function(){
				if($(this).attr("checked")){
					roomGuid = $(this).val();
				}
			});
		}else {
			roomGuid = "init";
		}
	}
	$('.data-table').DataTable().ajax.reload();
} 

function changeType(tag){
	var parentCode = $("option:selected",tag).val();
	var indexTag = $(tag).parent().parent().children(":last");
	var index = $("button",indexTag).attr("index");
	$.ajax({
		url: '/wbem/inspect/loadIssuesTypeList.action',
		data: {'parentCode':parentCode},
		type: 'post',
		error:function(){
			
		},
		success:function(data){
			var str = "";
			if($("select[name='subType"+index+"']").attr("name") == undefined) {
				str += "<select name='subType"+index+"'>";
			}
			//str += "<option></option>";	
			for(var i=0; i<data.length; i++){
				str+="<option value="+data[i].issueCode+">"+data[i].issueName+"</option>";
			}
			if($("select[name='subType"+index+"']").attr("name") == undefined) {
				str += "</select>";
				$(tag).after(str);
			}else{
				$("select[name=subType"+index+"]").empty();
				$("select[name=subType"+index+"]").append(str);
			}
		}
	});
}

function editIssue(issuesId) {
	$.ajax({
		url: '/wbem/inspect/getIssueDetailById.action',
		data: {'issuesId':issuesId},
		type: 'post',
		error:function(){
			
		},
		success:function(data){
			var parentCode;
			$("select[name='editIssuePosition'] option").each(function(){
				if($(this).val() == data.issuesDetail.position){
					$(this).attr("selected",true);
				}
			});
			
			$("select[name='editMainType'] option").each(function(){
				if($(this).text() == data.issuesType.parentCode){
					$(this).attr("selected",true);
					parentCode = $(this).val();
				}
			});
			$("select[name='editLevel'] option").each(function(){
				if($(this).val() == data.issuesDetail.level){
					$(this).attr("selected",true);
				}
			});
			$("input[name='editIssueDesc']").val(data.issuesDetail.issueDesc);
			
			$("input[name='editIssuesId']").val(issuesId);
			$.ajax({
				url: '/wbem/inspect/loadIssuesTypeList.action',
				data: {'parentCode':parentCode},
				type: 'post',
				error:function(){
					alert("error");
				},
				success:function(option){
					var str = "";
					//str += "<option></option>";	
					for(var i=0; i<option.length; i++){
						str+="<option value="+option[i].issueCode+">"+option[i].issueName+"</option>";
					}
					$("select[name='editSubType']").empty();
					$("select[name='editSubType']").append(str);
					
					$("select[name='editSubType'] option").each(function(){
						if($(this).text == data.issuesType.issueName){
							$(this).attr("selected",true);
						}
					});
					$("#editIssueInfo").css("display","block");
				}
			});
		},
	});
}


function editChangeType(tag){
	var parentCode = $("option:selected",tag).val();
	$.ajax({
		url: '/wbem/inspect/loadIssuesTypeList.action',
		data: {'parentCode':parentCode},
		type: 'post',
		error:function(){
			
		},
		success:function(data){
			var str = "";	
			for(var i=0; i<data.length; i++){
				str+="<option value="+data[i].issueCode+">"+data[i].issueName+"</option>";
			}
			
			$("select[name='editSubType']").empty();
			$("select[name='editSubType']").append(str);
		}
	});
}

function cancleEdit(){
	$("#editIssueInfo").css("display","none");
}

function sureEdit(){
	var issuesId = $("input[name='editIssuesId']").val();
	var position = encodeURI($("select[name='editIssuePosition'] option:selected").val());
	var subType = $("select[name='editSubType'] option:selected").val();
	var level = $("select[name='editLevel'] option:selected").val();
	var issueDesc = encodeURI($("input[name='editIssueDesc']").val());
	$.ajax({
		url:"/wbem/inspect/updateIssueDetailById.action",
		data:{"issuesId":issuesId,"position":position,"subType":subType,"level":level,"issueDesc":issueDesc},
		type:'post',
		error:function(){
			
		},
		success:function(data){
			if(data == 1) {
				$('.data-table').DataTable().ajax.reload();
			}
			$("#editIssueInfo").css("display","none");
		}
	});
}

$(document).ready(function(){
	
	if($("#status").val() == "close") {//处理新增关闭
		var index = parent.layer.getFrameIndex(window.name);
    	parent.$('.data-table').DataTable().ajax.reload();
    	parent.layer.close(index);
	}
	
	var bldGuid = $(".nav-tabs li:first").attr("bldGuid");
	var bldName = $(".nav-tabs li:first").attr("bldName");
	loadUnitRoomInfo(bldGuid,bldName,1,this);  
	//查询
	indexList.push("1");
	$("input[name='issuesStr']").val(indexList.join(","));
	
	$("#cancle").click(function(){//取消
		var num = 0;
		$("#roomInfo input[name='roomGuid']").each(function(){
			if($(this).attr("checked")){
				num++;
			}
		});
		if(num != 0) {
		var index = layer.open({
		        type: 1,
		        title: '提示',
		        maxmin: false,       //开启最大最小化
		        shadeClose: false, //点击遮罩关闭层
		        area : ['350px' , '200px'],
		        content: "<div style='text-align: left;margin-left: 20px;font-size: 14px;margin-top: 30px;font-weight: bold;'>确定放弃当前新增问题？</div>",
		        btn:['确定','取消'],
		        yes:function(){
		        	var index2 = parent.layer.getFrameIndex(window.name);
		        	parent.$('.data-table').DataTable().ajax.reload();
		        	parent.layer.close(index2);
		        },
		    });
		}else{
			var index3 = parent.layer.getFrameIndex(window.name);
			parent.$('.data-table').DataTable().ajax.reload();
			parent.layer.close(index3);
		}
	});
	
	$("#saveClose").click(function(){//保存关闭
		var num = 0;
		$("#roomInfo input[name='roomGuid']").each(function(){
			if($(this).attr("checked")){
				num++;
			}
		});
		if(num == 0){
			layer.alert("请选择新增问题的房间！");
			return false;
		}
		$("form").attr("action","/wbem/inspect/insertRoomIssuesInfo.action?type=1");
		$("form").submit();
	});
	
	$("#saveNew").click(function(){//保存新增
		var num = 0;
		$("#roomInfo input[name='roomGuid']").each(function(){
			if($(this).attr("checked")){
				num++;
			}
		});
		if(num == 0){
			layer.alert("请选择新增问题的房间！");
			return false;
		}
		$("form").attr("action","/wbem/inspect/insertRoomIssuesInfo.action?type=2");
		$("form").submit();
	});
	
	$('.data-table').dataTable({
		"dom":"<''r>t<'F'p>",
		"iDisplayLength":10,
		"ajax": {
			type: "post",//后台指定了方式。
         	url: "/wbem/inspect/loadRoomIssuesList.action",
         	data:function(d){			//外部参数传递
		 		d.roomGuid = roomGuid;
	        } 
         },
         "columns":[
         	{"data":"index"},
         	{"data":"issuePosition"},
         	{"data":"type"},
         	{"data":"issueDesc"},
         	{"data":"status"},
         	{"data":"createDate"},
         	{"data":"operate"},
         ],
         "columnDefs":[
            {
         		targets:6,"render":function(data){   
	       			return	"<shiro:hasPermission name='button:propertyManage:addIssues:edit'><a style='color:#FAA732' href='#' onclick='editIssue(\"" + data + "\")'>编辑</a></shiro:hasPermission>";
         		} 
         	}, 
	        { "sortable": false, "targets":[0,1,2,3,4,6]}
          ] , 
         "ordering":true,
         "order": [5,"desc"],	  
	});
	//$('select').select2(); 
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
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>