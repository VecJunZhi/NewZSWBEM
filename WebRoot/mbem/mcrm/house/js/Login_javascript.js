function $(username){
	return document.getElementById(username);
}

function user_input(){
	var name = $("username").value;
	var password = $("password").value;
	if(name=="" || password==""){
		alert("�û��������벻��Ϊ�գ�");
		return false;
		}else{
			return true;
			}	
	}
