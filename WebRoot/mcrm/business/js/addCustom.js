
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