<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<script>
	//�ͻ��߰�
	function cstUrged(tradeGuid,urgedStage){
		layer.open({
	        type: 2,
	        title: '�����߰�',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['490px' , '300px'],
	        content: "/wbem/houses/customer/cstUrged.action?tradeGuid="+tradeGuid+"&urgedStage="+urgedStage
	    });
	}
	
	//�ͻ�����
	function cstDelay(tradeGuid,postponeStage){
		layer.open({
	        type: 2,
	        title: '�ͻ�����',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['490px' , '350px'],
	        content: "/wbem/houses/customer/cstDelay.action?tradeGuid="+tradeGuid+"&postponeStage="+postponeStage
	    });
	}
	
	//
	function cstDetail(tradeGuid){
		layer.open({
	        type: 2,
	        title: '�߰��¼',
	        maxmin: false,       //���������С��
	        shadeClose: false, //������ֹرղ�
	        area : ['850px' , '600px'],
	        content: "/wbem/houses/customer/cstView.action?tradeGuid="+tradeGuid
	    });
	}
	//�ͻ��߰��ȷ��
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
	        		layer.alert("�����߰�ʧ�ܣ�");
	        	}
	        },
	        error: function() {
	        }
		});    
		 
	}
	//�ͻ��߰��ȡ��
	function cancleUrged(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	//�ͻ����ڵ�ȷ��
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
	        		layer.alert("�����߰�ʧ�ܣ�");
	        	}
	        },
	        error: function() {
	        }
		});    
		 
	}
	//�ͻ����ڵ�ȡ��
	function cancleDelay(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>