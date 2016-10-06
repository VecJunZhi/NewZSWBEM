<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<script>
	//客户催办
	function cstUrged(tradeGuid,urgedStage){
		layer.open({
	        type: 2,
	        title: '新增催办',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['490px' , '300px'],
	        content: "/wbem/houses/customer/cstUrged.action?tradeGuid="+tradeGuid+"&urgedStage="+urgedStage
	    });
	}
	
	//客户延期
	function cstDelay(tradeGuid,postponeStage){
		layer.open({
	        type: 2,
	        title: '客户延期',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['490px' , '350px'],
	        content: "/wbem/houses/customer/cstDelay.action?tradeGuid="+tradeGuid+"&postponeStage="+postponeStage
	    });
	}
	
	//
	function cstDetail(tradeGuid){
		layer.open({
	        type: 2,
	        title: '催办记录',
	        maxmin: false,       //开启最大最小化
	        shadeClose: false, //点击遮罩关闭层
	        area : ['850px' , '600px'],
	        content: "/wbem/houses/customer/cstView.action?tradeGuid="+tradeGuid
	    });
	}
	//客户催办的确定
	function sureUrged() {
		var tradeGuid=$("#tradeGuid").val();
		var urgedContent = encodeURI($("#urgedContent").val());
		var nextUrgedDate = $("#dpd1").val();
		var urgedStage = $("#urgedStage").val();
		$.ajax({
	        url: "/wbem/houses/customer/insertCstUrgedInfo.action",
	        type:"POST",
	        data: {"tradeGuid":tradeGuid,"urgedContent":urgedContent,"urgedStage":urgedStage},
	        dataType: "json",
	        success: function (data) {
	        	if(data == 1) {
	        		var index = parent.layer.getFrameIndex(window.name);
					parent.$('.data-table').DataTable().ajax.reload();
					parent.layer.close(index);
	        	} else {
	        		layer.alert("新增催办失败！");
	        	}
	        },
	        error: function() {
	        }
		});    
		 
	}
	//客户催办的取消
	function cancleUrged(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	//客户延期的确定
	function sureDelay() {
		var tradeGuid=$("#tradeGuid").val();
		var postponeReason = encodeURI($("#postponeReason").val());
		var postponeDate = $("#postponeDate").val();
		var postponeStage=$("#postponeStage").val();
		$.ajax({
	        url: "/wbem/houses/customer/setCstDelayInfo.action",
	        type:"POST",
	        data: {"tradeGuid":tradeGuid,"postponeReason":postponeReason,"postponeDate":postponeDate,"postponeStage":postponeStage},
	        dataType: "json",
	        success: function (data) {
	        	if(data == 1) {
	        		var index = parent.layer.getFrameIndex(window.name);
					parent.$('.data-table').DataTable().ajax.reload();
					parent.layer.close(index);
	        	} else {
	        		layer.alert("新增催办失败！");
	        	}
	        },
	        error: function() {
	        }
		});    
		 
	}
	//客户延期的取消
	function cancleDelay(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>