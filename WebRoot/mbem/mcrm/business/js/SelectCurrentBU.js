//Modified by liubao 2007-11-16
// 功能：弹开切换公司窗口
// 返回：公司名称
function _selectCurrentBU(params,isLogin)
{
	var sTitle, sHeight, sFile;
	var iWidth, iHeight;
	sWidth = "360";
	sHeight = "500";
	sTitle = "选择公司";
	sHeight = "";
	sFile = "/Security/SelectCurrentCompany.aspx";
	// 拼写url字符串
	var url = "/FrameTemp0.aspx";
	url += "?title=" + myEscape(sTitle);
	url += "&height=" + myEscape(sHeight);
	url += "&filename=" + myEscape(sFile);
	url += "&param=" + myEscape(params);
	url += "&isLogin=" + myEscape(isLogin?isLogin:false);	
		
	var returnValue = window.showModalDialog(escapeUrl(url), "", "dialogWidth:" + sWidth + "px; dialogHeight:" + sHeight + "px; status:no; help:no; resizable:yes;");
	return returnValue;
}
