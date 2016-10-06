dd.config({
			agentId : _config.agentid,
			corpId : _config.corpId,
			timeStamp : _config.timeStamp,
			nonceStr : _config.nonceStr,
			signature : _config.signature,
			jsApiList : [ 'runtime.info', 'biz.contact.choose',
					'device.notification.confirm', 'device.notification.alert',
					'device.notification.prompt', 'biz.ding.post',
					'biz.util.openLink','runtime.permission.requestAuthCode']
		});
dd.ready(function() {
	dd.runtime.permission.requestAuthCode({
		corpId: _config.corpId,
		onSuccess: function(info) {
			//alert('authcode: ' + info.code);
			//alert('accessToken: ' +_config.accessToken);
			$.ajax({
				 	url : "/check.action",
				 	data : {"access_token":_config.accessToken,"code":info.code},
				 	type:'get',
				 	dataType:"json",
					success:function(data){
						//alert(data.password);
						//alert(data.userName);
						$.ajax({
							url : "/loginMBEMDD.action",
							data : {"userName":''+data.userName+'',"password":data.password+''},
							//data : {"userName":'13935526655',"password":'zs5566'},
							type:'post',
						 	success:function(data){
						 		//alert("成功"+data);
						 		if(data.indexOf("redirect:")!=-1){
						 			data=data.substring(data.indexOf(":")+1,data.length);
						 			window.location.href=data;
						 		}else{
						 			//alert("提示:联系管理员进行授权,可实现免登");
						 			window.location.href=data+".action";
						 		}
						 		//window.location.replace(data);
						 		
						 	},
						 	error:function(data){
						 		//alert("系统异常,请联系管理员");
						 		window.location.href="/loginMBEM.action";
						 	}
						});
					},
					error : function(msg){
						//alert("你没有开通云客系统,请联系管理员。"+msg);
						window.location.href="/loginMBEM.action";
				    }
		   });
		},
		onFail : function(err) {
			//alert("服务器繁忙，请重试。");
			window.location.href="/loginMBEM.action";
		}
	});
});
dd.error(function(err) {
	//alert("服务器较繁忙，请重试。");
	window.location.href="/loginMBEM.action";
});