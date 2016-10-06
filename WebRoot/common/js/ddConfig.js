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
						 		//alert("�ɹ�"+data);
						 		if(data.indexOf("redirect:")!=-1){
						 			data=data.substring(data.indexOf(":")+1,data.length);
						 			window.location.href=data;
						 		}else{
						 			//alert("��ʾ:��ϵ����Ա������Ȩ,��ʵ�����");
						 			window.location.href=data+".action";
						 		}
						 		//window.location.replace(data);
						 		
						 	},
						 	error:function(data){
						 		//alert("ϵͳ�쳣,����ϵ����Ա");
						 		window.location.href="/loginMBEM.action";
						 	}
						});
					},
					error : function(msg){
						//alert("��û�п�ͨ�ƿ�ϵͳ,����ϵ����Ա��"+msg);
						window.location.href="/loginMBEM.action";
				    }
		   });
		},
		onFail : function(err) {
			//alert("��������æ�������ԡ�");
			window.location.href="/loginMBEM.action";
		}
	});
});
dd.error(function(err) {
	//alert("�������Ϸ�æ�������ԡ�");
	window.location.href="/loginMBEM.action";
});