
function add_custom(){
	var cusName = $("#cusName").val();
	var tel = $("#tel").val();
	var followInfo = $("#followInfo").val();
	var intentionType = $("#sbtgroup16").val();
	if(cusName=="" || tel=="" || followInfo=="" || intentionType==""){
		alert("�뽫��������д����  (�ã���ע)");
		return false;
		}else{
			return true;
			}	
	};
	
	function callCustom(type) {//��ϵ�ͻ������Ͷ��ź����ת
		if(type == "1") {
			window.location.href = "/mbem/mcrm/business/follow.action?type=1";
		}
		if(type == "2") {
		index = layer.open({
			  title:false,
			  type: 2,
			  content: "/mbem/mcrm/business/follow.action?type=2",
			  area: ['300px', '195px'],
			  maxmin: true,
			  closeBtn:0,
			  maxmin:false
			});
			layer.full(index);
		}
	}

	function popInfo(id) {//�����ͻ��б����ͻ�����
		  index = layer.open({
		  title:false,
		  type: 2,
		  content: '/mbem/mcrm/business/personal.action?cusId='+id,
		  area: ['300px', '195px'],
		  maxmin: true,
		  closeBtn:0,
		  maxmin:false
		});
		layer.full(index);
	}