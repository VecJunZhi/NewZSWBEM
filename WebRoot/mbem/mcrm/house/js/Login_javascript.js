function $(username){
	return document.getElementById(username);
}

function user_input(){
	var name = $("username").value;
	var password = $("password").value;
	if(name=="" || password==""){
		alert("用户名或密码不能为空！");
		return false;
		}else{
			return true;
			}	
	}
