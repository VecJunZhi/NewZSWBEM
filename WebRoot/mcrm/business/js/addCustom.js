
function add_custom(){
	var cusName = $("#cusName").val();
	var tel = $("#tel").val();
	var followInfo = $("#followInfo").val();
	var intentionType = $("#sbtgroup16").val();
	if(cusName=="" || tel=="" || followInfo=="" || intentionType==""){
		alert("请将必填项填写完整  (用＊标注)");
		return false;
		}else{
			return true;
			}	
	};