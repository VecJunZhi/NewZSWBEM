<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp"%>
<style>
	.quick-actions li {
		width:11%;
		min-width:10%;
		min-height:85px;
		text-align:center;
		color:#000;
	}
	.quick-actions li a:hover {
		background:#b6b3b3;
	}
	.quick-actions li:hover {
		background:#b6b3b3;
	}
</style>

<div>
	<c:set var="type" value="1"/>
	<c:set var="num" value="1"/>
	<c:forEach items="${bldList}" var="bld" varStatus="status">
		<c:if test="${type != bld.build.areaFullName && status.index == 0}">
			<div style="border-bottom: 1px solid #868383;font-size: 16px;font-weight: bold;">${bld.build.areaFullName}</div>
			<ul class="quick-actions" style="float:left;width:100%;">
		</c:if>
		<c:if test="${type != bld.build.areaFullName && status.index != 0}">
			</ul>
			<div style="border-bottom: 1px solid #868383;font-size: 16px;font-weight: bold;">${bld.build.areaFullName}</div>
			<ul class="quick-actions" style="float:left;width:100%;">
		</c:if>
		<c:if test="${bld.buildExt.bldCheckStatus == 1}">
			<li class="bg_lg" bldGuid="${bld.build.bldGuid}" onclick="bldOpen(this)"><span style="display: block;margin-top: 35px;">${bld.build.bldName }</span> </li>
		</c:if>
		<c:if test="${bld.buildExt.bldCheckStatus == 0}">
			<shiro:hasPermission name="button:projectManage:bldOpen:open">		
				<li class="bg_lh" bldGuid="${bld.build.bldGuid}" onclick="bldOpen(this)"><span style="display: block;margin-top: 35px;">${bld.build.bldName}</span></li>
			</shiro:hasPermission>
			<shiro:lacksPermission name="button:projectManage:bldOpen:open">
				<li class="bg_lh" bldGuid="${bld.build.bldGuid}"><span style="display: block;margin-top: 35px;">${bld.build.bldName}</span></li>
			</shiro:lacksPermission>
		</c:if>
		<c:if test="${type != bld.build.areaFullName}">
			<c:set var="type" value="${bld.build.areaFullName }"/>
			<c:set var="num" value="0"/>
		</c:if>
	</c:forEach>
	</ul>
</div>
<div style="margin-left:30%">
	<div style="float:left;width:100px;height:25px;background-color:#b6b3b3;"></div><span style="float:left;margin-left:10px;font-size:14px;font-weight: bold;">未开放验房</span>
 	<div style="float:left;width:100px;height:25px;background-color:#28b779;margin-left:30px;"></div><span style="float:left;margin-left:10px;font-size:14px;font-weight: bold;">已开放验房</span>
</div>

<script>
/* $(document).ready(function(){ 
	//查询
	$(".quick-actions li").click(function(){*/
	function bldOpen(tag){
		var bldName = $(tag).text();
		if($(tag).hasClass("bg_lg")) {
			layer.alert(bldName+"已开放验房");
			return;
		}
		var bldGuid = $(tag).attr("bldGuid");
		
		//var tag = $(this);
		var content = "<div style='text-align: left;margin-left: 20px;font-size: 14px;margin-top: 30px;font-weight: bold;'><span style='color:red;'>"+bldName+"</span>开放验房？</div>";
		var index = layer.open({
	        type: 1,
	        title: '提示',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['350px' , '200px'],
	        content: content,
	        btn:['确定','取消'],
	        yes:function(){
	        	$.ajax({
	    			url: '/wbem/inspect/insertBldStatusInfo.action',
	    			data: {"bldGuid":bldGuid},
	    			type: 'post',
	    			error:function(){	
	    			},
	    			success: function(flag){
	    				if(flag == 1) {
	    					$(tag).attr("class","bg_lg");
	    					layer.close(index);
	    				}
	    			},
	    		});
	        },
		})
	}
	/*	});
 }); */
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>