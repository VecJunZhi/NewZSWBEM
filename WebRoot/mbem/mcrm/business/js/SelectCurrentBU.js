//Modified by liubao 2007-11-16
// ���ܣ������л���˾����
// ���أ���˾����
function _selectCurrentBU(params,isLogin)
{
	var sTitle, sHeight, sFile;
	var iWidth, iHeight;
	sWidth = "360";
	sHeight = "500";
	sTitle = "ѡ��˾";
	sHeight = "";
	sFile = "/Security/SelectCurrentCompany.aspx";
	// ƴдurl�ַ���
	var url = "/FrameTemp0.aspx";
	url += "?title=" + myEscape(sTitle);
	url += "&height=" + myEscape(sHeight);
	url += "&filename=" + myEscape(sFile);
	url += "&param=" + myEscape(params);
	url += "&isLogin=" + myEscape(isLogin?isLogin:false);	
		
	var returnValue = window.showModalDialog(escapeUrl(url), "", "dialogWidth:" + sWidth + "px; dialogHeight:" + sHeight + "px; status:no; help:no; resizable:yes;");
	return returnValue;
}
