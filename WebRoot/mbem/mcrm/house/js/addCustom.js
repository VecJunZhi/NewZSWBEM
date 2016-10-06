
function add_custom(){
	var cusName = $("#cusName").val();
	var tel = $("#tel").val();
	var followInfo = $("#followInfo").val();
	var intentionType = $("#sbtgroup16").val();
	if(cusName=="" || tel=="" || followInfo=="" || intentionType==""){
		alert("锟诫将锟斤拷锟斤拷锟斤拷锟斤拷写锟斤拷锟斤拷  (锟矫ｏ拷锟斤拷注)");
		return false;
		}else{
			return true;
			}	
	};
	
function callCustom(type,cstGuid,oppGuid) {//锟斤拷系锟酵伙拷锟斤拷锟斤拷锟酵讹拷锟脚猴拷锟斤拷锟阶�
	var url = "";
	url = "/mbem/mcrm/house/customer/follow.action?type="+type+"&cstGuid="+cstGuid+"&oppGuid="+oppGuid;
	if(type == "1") {
		window.location.href = url;
	}
	if(type == "2") {
	index = layer.open({
		  title:false,
		  type: 2,
		  content: url,
		  area: ['300px', '195px'],
		  maxmin: true,
		  closeBtn:0,
		  maxmin:false
		});
		layer.full(index);
	}
}

function popInfo(cstGuid,oppGuid) {//锟斤拷锟斤拷锟酵伙拷锟叫�锟斤拷锟酵伙拷锟斤拷锟斤拷
	  index = layer.open({
	  title:false,
	  type: 2,
	  content: '/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuid.action?cstGuid='+cstGuid+'&oppGuid='+oppGuid,
	  area: ['300px', '195px'],
	  maxmin: true,
	  closeBtn:0,
	  maxmin:false
	});
	layer.full(index);
}

function urgedDetail(type,tradeGuid) {//锟斤拷锟斤拷锟酵伙拷锟叫�锟斤拷锟酵伙拷锟斤拷锟斤拷
	  index = layer.open({
	  title:false,
	  type: 2,
	  content: '/mbem/mcrm/house/customer/urgedDetail.action?type='+type+'&tradeGuid='+tradeGuid,
	  area: ['300px', '195px'],
	  maxmin: true,
	  closeBtn:0,
	  maxmin:false
	});
	layer.full(index);
}

function urgedCallCst(tradeGuid,type) {
	window.location.href = "/mbem/mcrm/house/customer/urgedFollow.action?tradeGuid="+tradeGuid+"&type="+type;
}