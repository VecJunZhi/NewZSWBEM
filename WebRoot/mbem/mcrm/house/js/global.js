var _sRoot = "/"; 	//设置根url
var _iLocked = 0;
var None = 0;
var sPsrviewVer = "1.0.0.10"; 							// psrview 最新的版本。必须与 psrview 版本同步。
var htcControlExceptionMessage = "";
if (typeof (Mysoft) == "undefined")  Mysoft = {}; //定义全局命名空间
if (typeof (Mysoft.MAP) == "undefined") Mysoft.MAP = {};
if (typeof (Mysoft.MAP.Log) == "undefined") Mysoft.MAP.Log = {};
if (typeof (window.ActivexHelper) == "undefined") {
    window.ActivexHelper = {
        PBHelperDetect: function(id, detectPsrview) {
            if (!id)
                return false;
            var obj;
            if (typeof (id) == "string")
                obj = document.getElementById(id);
            else
                obj = id;
            if (!obj || typeof (obj.Psr2Xls) == "undefined") {
                window.ActivexHelper.Alert("报表基础控件");
                return false;
            }
            if (detectPsrview === true && typeof (checkPsrviewVersion) == "function")
                checkPsrviewVersion(obj);
            return true;
        },
        ProjectEditorDetect: function(id) {
            if (!id)
                return false;
            var obj;
            if (typeof (id) == "string")
                obj = document.getElementById(id);
            else
                obj = id;
            if (!obj || typeof (obj.OpenProject) == "undefined") {
                window.ActivexHelper.Alert("Project在线编辑插件");
                return false;
            }
            return true;
        },
        Alert: function(activexName) {
            window.showModalDialog("/Activex/ActivexAlert.htm?time=" + (new Date().getTime()), activexName, "dialogWidth:500px;dialogHeight:120px; status:0; help:0; resizable:0; center:1;");
        }
    }
}


function document.oncontextmenu() {
   
    var s = event.srcElement.tagName;
    // For Selection Ranges, s will be UNDEFINED
    if (s && s != "INPUT" && s != "TEXTAREA" || event.srcElement.disabled || document.selection.createRange().text.length == 0) {
        event.returnValue = false;
    }
}
function document.onselectstart() {
    var s = event.srcElement.tagName;
    if (s != "INPUT" && s != "TEXTAREA") event.returnValue = false;
}
function document.ondragstart() {
    var s = event.srcElement.tagName;
    if (s != "INPUT" && s != "TEXTAREA") event.returnValue = false;
}
// 功能：以 open 方式打开全屏窗口
function openMyFullWin(sPath, sName, isShowScrollbar) {
    var iX = window.screen.availWidth - 10;
    var iY = window.screen.availHeight - 50;
    var scrollbar = "";
    if (!sName) sName = "";
    if (isShowScrollbar) scrollbar = ",scrollbars=yes";
    try {
        return window.open(escapeUrl(sPath), sName, "left=0,top=0,width=" + iX + ",height=" + iY + ",status=1,resizable=1" + scrollbar);
    }
    catch (e) { logException(e); }
}
// 功能：以 open 方式打开窗口
function openMyWin(sPath, sName, iX, iY, isShowScrollbar) {
    return openStdWin(sPath, sName, iX, iY, isShowScrollbar);
}
function openStdWin(sPath, sName, iX, iY, isShowScrollbar) {
    if (!iX) iX = 800;
    if (!iY) iY = 552;
    if (!sName) sName = "";
    var iL, iT;
    var scrollbar = "";
    iL = (window.screen.width - iX) / 2;
    iT = (window.screen.height - iY - 80) / 2;
    if (iL < 0) iL = 0;
    if (iT < 0) iT = 0;
    if (isShowScrollbar) scrollbar = ",scrollbars=yes";
    try {
        return window.open(escapeUrl(sPath), sName, "left=" + iL + ",top=" + iT + ",width=" + iX + ",height=" + iY + ",status=1,resizable=1" + scrollbar);
    }
    catch (e) {
        logException(e);
    }
}
// 功能：以 showModalDialog 方式打开窗口
// 参数：sPath		-- 文件路径，必须包含完整的文件路径
//		 sParams	-- 后缀参数，格式如：paramname1=param1&paramname2=param2&...
function openMyDlg(sTitle, sPath, sParams, oArgs, iX, iY, bAutoFitIEVer) {
    if (!iX) iX = 500;
    if (!iY) iY = 340;
    var sURL = "/FrameTemp0.aspx";
    sURL += "?title=" + myEscape(sTitle);
    sURL += "&height=";
    sURL += "&filename=" + myEscape(sPath);
    sURL += "&param=" + myEscape(sParams);
    sURL += "&myAppName=" + myEscape(getAppName());
    sURL += "&rdm=" + Math.random();
    
    if (isNaN(iY)) {
        iY = parseFloat(iY.toString().replace(/,/g, "")) + 30;
    }
    else {
        iY = parseFloat(iY) + 30;
    }
	oArgs = __attachWindowRef(oArgs);
    var dt = perfLog.dialogDru.begin();
    var returnValue = window.showModalDialog(escapeUrl(sURL), oArgs, "dialogWidth:" + iX + "px;dialogHeight:" + iY + "px; status:0; help:0; resizable:0; center:1;");
    perfLog.dialogDru.end(dt);
    return returnValue
    //return window.showModalDialog(escapeUrl(sURL), oArgs, "dialogWidth:" + iX + "px;dialogHeight:" + iY + "px; status:0; help:0; resizable:0; center:1;");
}
function openStdDlg(sPath, oArgs, iX, iY, bAutoFitIEVer) {
    if (!iY) iY = 340;
    if (isNaN(iY)) {
        iY = parseFloat(iY.toString().replace(/,/g, ""))  + 30;
    }
    else {
        iY = parseFloat(iY)  + 30;
    }
	oArgs = __attachWindowRef(oArgs);
    var dt = perfLog.dialogDru.begin();
    var returnValue = window.showModalDialog(escapeUrl(sPath), oArgs, "dialogWidth:" + iX + "px;dialogHeight:" + iY + "px;help:0;status:0;scroll:0;center:1");
    perfLog.dialogDru.end(dt);
    return returnValue
    //return window.showModalDialog(escapeUrl(sPath), oArgs, "dialogWidth:" + iX + "px;dialogHeight:" + iY + "px;help:0;status:0;scroll:0;center:1");
}
function __attachWindowRef(o) {
    //暂时取消
    return o;
    if (!o || o == null) {
        o = new Object();
        o.window = window;
    }
    else if (typeof (o) == "string" && o == "") {
        o = new Object();
        o.window = window;
    }
    else if (typeof (o) == "object" && !o.window) {
        o.window = window;
    }
    else {
        //不处理
    }
    return o;
}
//----------------工具类函数-------------------
//功能：用showModalDialog打开窗口
//参数：sTitle		-	窗口标题
//		sFile		-	页面文件路径
//		sParamList	-	参数字符串
//		sWidth		-	窗口宽度
//		sHeight		-	窗口高度
//		sType		-	打开方式
//返回：窗口关闭后的返回值
function OpenModalWin(sTitle, sFile, sParamList, sWidth, sHeight, strdialogArguments) {
    var url;
    var sBackValue;
    url = "/FrameTemp0.aspx?title=" + myEscape(sTitle) + "&height=&filename=" + myEscape(sFile) + "&param=" + myEscape(sParamList) + "&rdm=" + Math.random();
    if (strdialogArguments != undefined) {
        sBackValue = window.showModalDialog(escapeUrl(url), strdialogArguments, "dialogHeight:" + sHeight + "px; dialogWidth:" + sWidth + "px; help:no; status:no;scroll:no;");
    }
    else {
        sBackValue = window.showModalDialog(escapeUrl(url), "", "dialogHeight:" + sHeight + "px; dialogWidth:" + sWidth + "px; help:no; status:no;scroll:no;");
    }
    return sBackValue;
}
function openFrmObj(sUrl, sName, iType) {
    var url = _sRoot + getObjUrl(iType) + sUrl; 	//根据iType从注册信息中找出编辑页面的url
    switch (Number(iType)) {
        case None:
            // don't open anything
            break;
        default:
            openStdWin(url, sName);
    }
}
function openObj(iType, sId) {
    var sUrl = "";
    if (sId) {
        sUrl += "?id=" + sId;
    }
    openFrmObj(sUrl, buildWinName(sId), iType);
}
function openObjEx(iType, iParentType, sParentId, sParams) {
    var url = "";
    if (sParentId) {
        url += "?pId=" + sParentId + "&pType=" + iParentType;
    }
    if (typeof (sParams) != "undefined") {
        url += sParams;
    }
    openFrmObj(url, buildWinName(), iType);
}
function getObjUrl(i) {
    switch (Number(i)) {
        case None:
        case AppLicense: return "";  // does not open a form
    }
    alert(i + " - 没有注册该对象代码！");          //^^
    return "";
}
function openPopup() {
    return window.createPopup();
}
// 显示 Popup 窗口
function showPopup(popup, x, y, popupWidth, popupHeight, src) {
    //获取浏览器窗口可视范围的高度和宽度
    var iWidth, iHeight;
    try {
        iWidth = top.document.body.offsetWidth;
        iHeight = top.document.body.offsetHeight;
    }
    catch (e) {
        logException(e);
        iWidth = window.screen.availWidth - 10;
        iHeight = window.screen.availHeight - 60;
    }
    //获取当前点击元素所在窗口在浏览器中的位置
    var frameLeft = 0;
    var frameTop = 0;
    var oWin = window;
    var pos;
    try {
        while (oWin.frameElement != null) {
            pos = oWin.frameElement.getBoundingClientRect();
            frameLeft += pos.left;
            frameTop += pos.top;
            oWin = oWin.parent;
        }
    }
    catch (e) { logException(e); }
    //获取当前点击元素的位置
    pos = src.getBoundingClientRect();
    var xPos = pos.left;
    var yPos = pos.top;
    //对当前点击元素的位置进行判断,是否需要调整popup的位置
    if (frameLeft + xPos + x + popupWidth > iWidth) {
        x = iWidth - frameLeft - xPos - popupWidth;
    }
    if (frameLeft + xPos + x < 0) {
        x = 0 - frameLeft - xPos;
    }
    if (frameTop + yPos + y + popupHeight > iHeight) {
        y = iHeight - frameTop - yPos - popupHeight;
    }
    if (frameTop + yPos + y < 0) {
        y = 0 - frameTop - yPos;
    }
    // 显示 Popup 窗口
    popup.document.iLeft = frameLeft + xPos + x;
    popup.document.iTop = frameTop + yPos + y;
    //二级菜单定位
    if (src.ownerDocument.iLeft) {
        xPos = src.ownerDocument.iLeft;
        yPos = yPos + src.ownerDocument.iTop;
        if (frameLeft + xPos + x + popupWidth > iWidth) {
            x = 0 - popupWidth;
        }
        /*
        if (frameTop + yPos + y + popupHeight > iHeight) {
            y = yPos - popupHeight;
        }
        if (frameTop + yPos + y < 0) {
            y = 0;
        }*/
        if (yPos + popupHeight > iHeight) {
            y = iHeight - yPos - popupHeight;
        }
    }
    popup.show(x, y, popupWidth, popupHeight, src);
}
function buildWinName(s) {
    if (s) return s.replace(/[-\{\}]/g, "");
    var d = new Date();
    return d.getTime();
}
var ERROR_STOP = 0;
var ERROR_NONE = 1;
var ERROR_CONTINUE = 2;
function handleXMLErr(xml, bContinue) {
    if (bContinue == null) bContinue = false;
    if (xml.parseError.errorCode != 0) {
        //alert("XML Parser Error: " + xml.parseError.reason);
        alert("XML解析错误: " + xml.parseError.reason);
        if (!bContinue) {
            return ERROR_STOP;
        }
        else {
            return ERROR_CONTINUE;
        }
    }
    var node = xml.selectSingleNode("/error");
    if (node) {
        if (!bContinue) {
            //openStdDlg("/_common/error/dlg_error.aspx?hresult=" + node.selectSingleNode("number").text, null, 400, 200);
            alert("发生运行时错误！");
            return ERROR_STOP;
        }
        else {
            return ERROR_CONTINUE;
        }
    }
    return ERROR_NONE;
}
//自动刷新页面
//周彤	2004-12-07
function autoRefresh(iObjectTypeCode) {
    for (var i = 0; i < document.forms.length; i++) {
        var o = document.forms[i];
        // Find all "Grids" with a matching Object Type Code
        //防止误刷新被更改后父页面
        if (iObjectTypeCode == o.ObjectTypeCode) {
            //暂时用__doPostBack()，以后调用主页面的刷新函数
            __doPostBack("REFRESH", "");
        }
    }
}
function HtmlEncode(s) {
    s = s.replace(/&/g, "&amp;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");
    return s.replace(/\"/g, "&quot;");
}
function decodeXml(s) {
    // remove all unicode encoded symbols
    try {
        s = eval('"' + s.replace(/&#x(\w\w\w\w);/g, "\\u$1").replace(/"/g, '\\"') + '"');
    }
    catch (e) { logException(e); ; }
    // remove special encoding sequences
    s = s.replace(/&lt;/g, "<");
    s = s.replace(/&gt;/g, ">");
    s = s.replace(/&apos;/g, "'");
    s = s.replace(/&quot;/g, "\"");
    s = s.replace(/&amp;/g, "&");
    return s;
}
//功能：生成有序GUID值
//参数：无
//返回：返回有序的GUID字符串，如：93a037d2-aafe-e0a1-85c7-cd5c56c0ab0e
function NewSeqGUID()
{
    try
    {
        return GetDataByXMLHTTP("/Pub_XmlHttp.aspx", "GetNewGuid", "", null);
    }
    catch (e) { logException(e); }
}
// 功能：公共删除 js 脚本
// 参数：functionid			-- 功能代码
//		 actionid			-- 动作代码
//		 dataxml			-- 要处理的数据，建议使用 oid 字符串，之间用分号(;)分隔。也可以用其他格式。
//		 title				-- 删除前的提示信息，如果没有传递该参数，默认为"确实要删除选中的记录吗？"
//		 isVerify			-- 是否只校验能否删除。如果为“true”，则只校验能否删除，而不删除数据。
// 返回："success"			-- 成功
//		 "cancel"			-- 取消删除
//		 "失败原因字符串"	-- 失败
// 注意：调用该函数的页面必须继承 AppPage
// zt	2005.09.06
function publicDelete(functionid, actionid, dataxml, title, isVerify) {
    if (!isVerify) {
        if (window.confirm((!title) ? "确实要删除选择的记录吗？" : title) == false)
            return "cancel";
    }
    return invokeByXMLHTTP(functionid, actionid, dataxml, isVerify);
}
// 功能：通过 XMLHTTP 方式调用后端函数
// 参数：functionid			-- 功能代码
//		 actionid			-- 动作代码
//		 dataxml			-- 要处理的数据，建议用 xml 格式
//		 isVerify			-- 是否只校验能否删除。如果为“true”，则只校验能否删除，而不删除数据。
// 返回："success"			-- 成功
//		 "失败原因字符串"	-- 失败
// 注意：调用该函数的页面必须继承 AppPage
// zt	2005.09.16
function invokeByXMLHTTP(functionid, actionid, dataxml, isVerify) {
    if (functionid == null)
        return "请设置功能代码！";
    if (actionid == null)
        return "请设置动作代码！";
    if (!isVerify)
        isVerify = false;
    // 打开运行提示
    openWaiting();
    var sUrl = "/ApplicationMap.aspx";
    sUrl += "?functionid=" + myEscape(functionid);
    sUrl += "&actionid=" + myEscape(actionid);
    sUrl += "&isverify=" + isVerify;
     var sDataXml="<dataxml mysessionstate=\"" + document.all["___MYSESSIONSTATE"].value + "\">" + dataxml + "<userdataxml></userdataxml></dataxml>";
     var oHTTP = MyXMLHTTPRequest(sUrl, sDataXml, null);      
    
    // 要求返回xml格式<xml result="true/flase" errormessage="错误信息！">任意正确格式的xml</xml>。
    var returnXML = oHTTP.responseText;
    delete (oHTTP);
    // 关闭运行提示
    closeWaiting();
    try {
        var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
        xmlDom.loadXML(returnXML);
        if (xmlDom.documentElement.attributes.getNamedItem("result").text == "true")		// 如果删除成功
            return "success";
        else		// 如果删除失败
        {
            if (xmlDom.documentElement.attributes.getNamedItem("errid")) {
                if (xmlDom.documentElement.attributes.getNamedItem("errid").text == "001")
                    window.navigate("/ErrPage.aspx?errid=001");
            }
            else
                return xmlDom.documentElement.attributes.getNamedItem("errormessage").text;
        }
    }
    catch (e) {
        logException(e);
        return "返回了错误格式的 xml 信息！";
    }
}
// 功能：打开提示
function openWaiting() {
    if (document.all["__divRuning"])
        document.all["__divRuning"].style.display = "";
}
// 功能：关闭提示
function closeWaiting() {
    if (document.all["__divRuning"])
        document.all["__divRuning"].style.display = "none";
}
function document.onkeydown() {
    // 屏蔽 F5 和 Ctrl + N 和 Ctrl + r
    if (event.keyCode == 116
        || ((event.keyCode == 78 || event.keyCode == 82) && event.ctrlKey && !event.altKey && !event.shiftKey))
    {
        event.keyCode = 0;
        event.returnValue = false;
    }
    else if (event.keyCode == 8) //退格键
    {
        if ((event.srcElement.tagName == "INPUT" && (event.srcElement.type == "text" || event.srcElement.type == "password" || event.srcElement.type == "file") && !event.srcElement.readOnly && !event.srcElement.disabled)
		      || (event.srcElement.tagName == "TEXTAREA" && !event.srcElement.readOnly && !event.srcElement.disabled)
		      || (event.srcElement.contentEditable != undefined && event.srcElement.contentEditable == "true")
		      ) {
        }
        else {
            event.keyCode = 0;
            event.returnValue = false;
        }
    }
}
function getParam(s) {
    var r = /helpkey=[\w]+/ig;
    if (r.test(s)) {
        return "?" + s.match(r);
    }
    else {
        r = /funcid=[\d]+|mode=[\d]+/ig;
        if (r.test(s)) {
            var ary = s.match(r);
            var p = "?";
            for (var i = 0; i < ary.length; i++) {
                if (i == ary.length - 1) {
                    p += ary[i];
                }
                else {
                    p += ary[i] + "*";
                }
            }
            return p;
        }
        else {
            return "";
        }
    }
}
function getAppName() {
    var win = window;
    try {
        if (win.top.frames["nav1"] == undefined) {
            while (win.top.frames["nav1"] == undefined) {
                if (win.top.opener == undefined) {
                    return getQueryString(win.top.location.search, "myAppName");
                }
                else {
                    win = win.top.opener;
                }
            }
        }
        return win.top.frames["nav1"].document.getElementById("tdTitle").innerText;
    }
    catch (e) {
        logException(e);
        return "";
    }
}
function getQueryStringValue(url, name) {
    url = decodeURIComponent(url);
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = url.substr(1).match(reg);
    if (r != null)
        return decodeURIComponent(r[2]);
    return "NoArgs";
}
function document.onhelp() {
    if (window.event == null || window.event == undefined)
    	return false;
    	
    window.event.returnValue = false;
    //--------------------------------------------------
    //按照url参数处理一遍，后续应该只保留这种方式
    //--------------------------------------------------
    try {
        var w;
        if (window.top.frames["stage1"] == undefined) {
            w = window.top;
        }
        else {
            w = window.top.frames["stage1"].window;
        }
        var app;
        if (getQueryStringValue(w.location.search, "funcid") != "NoArgs") {
            app = getQueryStringValue(w.location.search, "funcid").substring(0, 4);
        }
        else if (getQueryStringValue(w.location.search, "scope") != "NoArgs") {
            app = getQueryStringValue(w.location.search, "scope");
        }
        else if (getQueryStringValue(w.location.search, "Application") != "NoArgs") {
            app = getQueryStringValue(w.location.search, "Application");
        }
        else if (getQueryStringValue(w.location.search, "app") != "NoArgs") {
            var t = getQueryStringValue(w.location.search, "app");
            //以下参数不完整，后续需要规范、调整
            switch (t) {
                case "slxt": app = "0101"; break;
                case "zlxt": app = "0104"; break;
                default: app = "0101"; break;
            }
        }
        else { app = ""; }
        if (app != "" && app != "0101" && app != "0202" && app != "0201" && app != "0204" && app != "0302" && app != "0303" && app != "0205" && app != "0206" && app != "1001" && app != "3101") {
            document_onhelp(true);
            return;
        }
    }
    catch (e) {
        logException(e);
    }
    //--------------------------------------------------
    //--------------------------------------------------
    //按照导航当前系统处理一遍，后续应该去掉这种方式
    //--------------------------------------------------
    var appName = getAppName();
    //alert(appName);
    if (appName == "明源地产ERP" || appName == "系统设置" || appName == "投资分析" || appName == "租赁管理" || appName == "客户服务管理" || appName == "会员管理") {
        document_onhelp(true);
        return;
    }
    document_onhelp_new(true);
}
function document_onhelp_new(turnToOldHelp) {
    //--------------------------------------------------
    var aryPath = [];
    //从当前页面向上取父级，将全部页面的URL取出，传送给后台
    //如果取值出现异常，之可能是跨域访问，但是跨域访问对功能没有影响，因此出现异常退出即可
    var win = window;
    try {
        if (win != window.top) {
            while (win != window.top) {
                var spath = win.document.location.pathname.replace(/\\/g, "/") + getParam(win.document.location.href);
                aryPath.push(spath);
                win = win.parent;
            }
        }
        if (win == window.top) {
            var spath = win.document.location.pathname.replace(/\\/g, "/") + getParam(win.document.location.href);
            aryPath.push(spath);
        }
    }
    catch (e) {
        logException(e);
    }
    var myFileName = "";
    var oRtn = XmlHttpRequest("/Map.Ext/MyFileHelp.axd", "GETFILEHELP", "filepath=" + myEscape(aryPath.join(",")));
    //如果没有配置动态帮助，则显示页面固定帮助
    if (!oRtn) {
        if (turnToOldHelp) {
            document_onhelp(false);
            return;
        }
    }
    else {
        var obj = eval('(' + oRtn + ')');
        if (!obj || !obj.result || obj.returnValue == "") {
            if (turnToOldHelp) {
                document_onhelp(false);
                return;
            }
        }
        myFileName = obj.returnValue;
    }
    var sWidth = 750;
    var sHeight = 510;
    var iX = screen.width - 10;
    var iY = screen.height - 55;
    var iTop, iLeft;
    iTop = (iY - sHeight) / 2;
    iLeft = (iX - sWidth) / 2;
    try {
        var objWin = window.open(escapeUrl(myFileName), "_MysoftHelpWin", "top=" + iTop + ",left=" + iLeft + ",width=" + sWidth + ",height=" + sHeight + ",status=1,resizable=1,location=0,menubar=1,toolbar=1,scrollbars=1");
        objWin.document.onhelp = MyHelpWinOnhelp;
        objWin.focus();
    }
    catch (e) {
        logException(e);
        try {
            objWin = window.open(escapeUrl(myFileName), "_MysoftHelpWin", "top=" + iTop + ",left=" + iLeft + ",width=" + sWidth + ",height=" + sHeight + ",status=1,resizable=1,location=0,menubar=1,toolbar=1,scrollbars=1");
            objWin.document.onhelp = MyHelpWinOnhelp;
            objWin.focus();
        }
        catch (e) {
            logException(e);
            return false;
        }
    }
}
function document_onhelp(turnToNewHelp) {
    if (window.event == null || window.event == undefined) return false;
    window.event.returnValue = false;
    var myFileName = "";
    if (typeof (_MyHelpfile) != "undefined" && _MyHelpfile != undefined && _MyHelpfile != "") {
        myFileName = _MyHelpfile;
    }
    else {
        //处理appGrid
        //1）Grid中没有数据
        if (document.location.pathname == "/_grid/gridcontainer.aspx") {
            if (typeof (parent._MyHelpfile) != "undefined" && parent._MyHelpfile != undefined && parent._MyHelpfile != "") {
                myFileName = parent._MyHelpfile;
            }
            else {
                //处理标签页中的appGrid
                if (typeof (parent.parent._MyHelpfile) != "undefined" && parent.parent._MyHelpfile != undefined && parent.parent._MyHelpfile != "") {
                    myFileName = parent.parent._MyHelpfile;
                }
            }
        }
        //2）Grid中有数据		
        if (document.location.pathname == "/_grid/griddata.aspx") {
            if (typeof (parent.parent._MyHelpfile) != "undefined" && parent.parent._MyHelpfile != undefined && parent.parent._MyHelpfile != "") {
                myFileName = parent.parent._MyHelpfile;
            }
            else {
                //处理标签页中的appGrid
                if (typeof (parent.parent.parent._MyHelpfile) != "undefined" && parent.parent.parent._MyHelpfile != undefined && parent.parent.parent._MyHelpfile != "") {
                    myFileName = parent.parent.parent._MyHelpfile;
                }
            }
        }
    }
    if (myFileName != "") {
        if (checkFileExist(myFileName)) {
            var sWidth = 750;
            var sHeight = 510;
            var iX = screen.width - 10;
            var iY = screen.height - 55;
            var iTop, iLeft;
            iTop = (iY - sHeight) / 2;
            iLeft = (iX - sWidth) / 2;
            try {
                var objWin = window.open(escapeUrl(myFileName), "_MysoftHelpWin", "top=" + iTop + ",left=" + iLeft + ",width=" + sWidth + ",height=" + sHeight + ",status=1,resizable=1,location=0,menubar=1,toolbar=1,scrollbars=1");
                objWin.document.onhelp = MyHelpWinOnhelp;
                objWin.focus();
            }
            catch (e) {
                logException(e);
                try {
                    objWin = window.open(escapeUrl(myFileName), "_MysoftHelpWin", "top=" + iTop + ",left=" + iLeft + ",width=" + sWidth + ",height=" + sHeight + ",status=1,resizable=1,location=0,menubar=1,toolbar=1,scrollbars=1");
                    objWin.document.onhelp = MyHelpWinOnhelp;
                    objWin.focus();
                }
                catch (e) {
                    logException(e);
                    return false;
                }
            }
        }
        else {
            if (turnToNewHelp) {
                document_onhelp_new(false);
            }
        }
    }
}
//检查文件是否存在
function checkFileExist(filepath)
{
    var strFile = "/PubAnonymous_XmlHttp.aspx";
    var sReturn = GetDataByXMLHTTP(strFile, "CheckFileExist", filepath, "");
    if (sReturn == "true")
    {
        return true;
    }
    return false;
}
//功能：重写打开的帮助窗口的onhelp事件以屏蔽F1
function MyHelpWinOnhelp() {
    if (window.event != null && window.event != undefined) {
        window.event.returnValue = false;
    }
    else {
        return false;
    }
}
// 打开关于明源CRM窗口			
function openAbout() {
    window.showModalDialog("/about.aspx", "", "dialogHeight:" + 447 + "px; dialogWidth:" + 594 + "px; help:no; status:no;scroll:no;");
}
// 功能：加锁
function _lock() {
    if (_iLocked <= 0)
        _iLocked = 1;
    else
        _iLocked += 1;
}
// 功能：解锁
function _unlock() {
    if (_iLocked <= 0)
        _iLocked = 0;
    else
        _iLocked -= 1;
}
//打印屏幕报表
//传递参数：
//	a_strRptid			---		报表ID
//	a_strReportName		---		报表名称
//	a_strFilter			---		报表过滤条件
//	a_strPramValue		---		报表参数
//  a_strParmColval     ---		控件参数
function doPrintPMReport(a_strRptid, a_strReportName, a_strFilter, a_strPramValue, a_strParmColval) {
    //报表ID、报表名称、报表过滤条件、报表变量值
    if (a_strRptid == undefined || a_strReportName == undefined || a_strFilter == undefined || a_strFilter == "") return;
    //报表参数处理
    if (a_strPramValue == undefined) a_strPramValue = "";
    if (a_strParmColval == undefined) a_strParmColval = "";
    //如果报表名称、报表ID 都没有指定，则不进行任何处理
    if (a_strReportName == "" && a_strRptid == "") return;
    //“+”号从前端传递到后端时，会当成特殊字符，到后端后会不识别，先用“*plus*”替换，然后在后端再替换成“+”号
    a_strFilter = a_strFilter.replace(/\+/g, "*plus*")
    //将屏幕报表名称传递给隐藏页面，打开屏幕屏幕报表parmwhere
    var url = "/Report/ScreenRpt_Refresh.aspx?rptname=" + myEscape(a_strReportName) + "&rptid=" + myEscape(a_strRptid);
    var tmpform = attachTempForm("myTempForm", url, refreshIframe.name);
    attachHiddenInput(tmpform, "hid_where", "hid_where", a_strFilter);
    attachHiddenInput(tmpform, "hid_parmrptval", "hid_parmrptval", a_strPramValue);
    attachHiddenInput(tmpform, "hid_parmcolval", "hid_parmcolval", a_strParmColval);
    submitForm(tmpform);
    removeTempForm(tmpform);
}
//提交表单
function submitForm(form) {
    form.submit();
}
//在dom中附加一个临时表单
function attachTempForm(id, url, targetname) {
    var form = document.createElement("form");
    form.id = id;
    form.method = "post";
    form.action = escapeUrl(url);
    if (targetname == undefined || targetname == "") {
        form.target = "myTempFormName";
    }
    else {
        form.target = targetname;
    }
    document.body.appendChild(form);
    return form;
}
//在form中附加一个hidden
function attachHiddenInput(form, hidId, hidName, hidValue) {
    var hid = document.createElement("input");
    hid.type = "hidden";
    hid.id = hidId;
    hid.name = hidName;
    hid.value = hidValue;
    form.appendChild(hid);
    return hid;
}
//移除dom中的临时表单
function removeTempForm(form) {
    document.body.removeChild(form);
}
//通过XMLHTTP通道不刷新页面从后台取数
//参数：sFile					-	打开文件
//		strBusinessType			-	业务对象
//		strBusinessInfo			-	业务信息
//		strPostStream			-	大容量业务参数
function GetDataByXMLHTTP(strFile, strBusinessType, strBusinessInfo, strPostStream) {
    if (strFile == undefined || strFile == "") strFile = "/Pub_XmlHttp.aspx"
    var sUrl = strFile + "?p_businessType=" + myEscape(strBusinessType) + "&p_businessInfo=" + myEscape(strBusinessInfo);
    var oHTTP = MyXMLHTTPRequest(sUrl, strPostStream, null);
    
    var bSuccess = handleXMLErr(oHTTP.responseXML);
    if (bSuccess) {
        if (oHTTP.responseText == "服务器超时") window.navigate("/ErrPage.aspx?errid=001");
        return oHTTP.responseText;
    }
    else {
        alert("操作失败，请关闭重试！");
        return "-1";
    }
}
//通过XMLHTTP通道后台取数(不刷新页面)
//参数：strFile					-	打开文件(默认为/Pub_XmlHttp.aspx)
//		strBusinessType			-	业务对象
//		strParamStr				-	参数字符串
//		strPostStream			-	大容量业务参数
//
//************************************************************
//1、 AJAX 是一个技术名词，不应该作为函数名，请重新命名此函数！
//2、 本脚本块内已经存在一个相同功能的函数，为：GetDataByXMLHTTP(...)，建议直接使用 GetDataByXMLHTTP
//************************************************************
function XmlHttpRequest(strFile, strBusinessType, strParamStr, strPostStream) {
    if (strFile == undefined || strFile == "") strFile = "/Pub_XmlHttp.aspx"
    var strTmp = "&";
    if (strFile.indexOf("?") == -1) strTmp = "?";
    var sUrl = strFile + strTmp + "p_businessType=" + myEscape(strBusinessType);
    if (strParamStr != "") sUrl += "&" + strParamStr;
    var oHTTP = MyXMLHTTPRequest(sUrl, strPostStream, null);
    
    var bSuccess = handleXMLErr(oHTTP.responseXML);
    if (bSuccess) {
        if (oHTTP.responseText == "服务器超时") window.navigate("/ErrPage.aspx?errid=001");
        return oHTTP.responseText;
    }
    else {
        alert("操作失败，请关闭重试！");
        return "-1";
    }
}
/*浮点允许相关方法*/
function __padLeft(str, nSize, ch) {
    var len = 0;
    var s = str ? str : "";
    ch = ch ? ch : '0';
    len = s.length;
    while (len < nSize) {
        s = ch + s;
        len++;
    }
    return s;
}
function __padRight(str, nSize, ch) {
    var len = 0;
    var s = str ? str : "";
    ch = ch ? ch : '0';
    len = s.length;
    while (len < nSize) {
        s = s + ch;
        len++;
    }
    return s;
}
function __movePointLeft(str, scale) {
    var s, s1, s2, ch, ps, sign;
    ch = '.';
    sign = '';
    s = str ? str : "";
    if (scale <= 0) return s;
    ps = s.split('.');
    s1 = ps[0] ? ps[0] : "";
    s2 = ps[1] ? ps[1] : "";
    if (s1.slice(0, 1) == '-') {
        s1 = s1.slice(1);
        sign = '-';
    }
    if (s1.length <= scale) {
        ch = "0.";
        s1 = __padLeft(s1, scale);
    }
    return sign + s1.slice(0, -scale) + ch + s1.slice(-scale) + s2;
}
function __movePointRight(str, scale) {
    var s, s1, s2, ch, ps;
    ch = '.';
    s = str ? str : "";
    if (scale <= 0) return s;
    ps = s.split('.');
    s1 = ps[0] ? ps[0] : "";
    s2 = ps[1] ? ps[1] : "";
    if (s2.length <= scale) {
        ch = '';
        s2 = __padRight(s2, scale);
    }
    return s1 + s2.slice(0, scale) + ch + s2.slice(scale, s2.length);
}
function __movePoint(str, scale) {
    if (scale >= 0)
        return __movePointRight(str, scale);
    else
        return __movePointLeft(str, -scale);
}
function __expToFix(s) {
    if (!isNaN(s)) {
        if (s.indexOf("e") >= 0 || s.indexOf("E") >= 0) {
            var arr = s.toLowerCase().split("e");
            return __movePoint(arr[0], parseInt(arr[1]));
        }
    }
    return s;
}
var __FRACTION_DIGITS = 6;        //最多返回6位，否则可能出现指数，导致问题
function __checkFloatParam(num, checkString) {
    if (!(typeof num == 'number' || typeof num == 'string')) {
        throw new Error("参数类型不正确");
    }
    if (typeof num == 'number' && isNaN(num)) {
        throw new Error("参数值是NaN");
    }
    if (typeof num == 'string') {
        if (checkString == true) {
            var str = num.toString().replace(/,/g, "");
            if (isNaN(str)) {
                throw new Error("参数值不是数值");
            }
        }
    }
}
//解决浮点运算精度丢失问题
//参数：    num1:第一个运算值
//          num2:第二个运算值
//operation：运算符，只支持加减乘除
//fractionDigits：小数位，不能超过6位
function calcDoubleFix(num1, num2, operation, fractionDigits) {
    if (arguments.length < 3) throw new Error("参数错误");
    //3.0中通替过calcDoubleFix，因为无法明确使用情况，所以需要支持"a"+123的场景
    __checkFloatParam(num1, false);
    __checkFloatParam(num2, false);
    var n, n1, n2, s, s1, s2, ps;
    s1 = __expToFix(num1.toString().replace(/,/g, ""));
    ps = s1.split('.');
    n1 = ps[1] ? ps[1].length : 0;
    s2 = __expToFix(num2.toString().replace(/,/g, ""));
    ps = s2.split('.');
    n2 = ps[1] ? ps[1].length : 0;
    var blnNumber = !isNaN(s1) && s1 != "" && !isNaN(s2) && s2 != "";
    if (blnNumber) {
        switch (operation) {
            case '+':
                n = n1 > n2 ? n1 : n2;
                s = Number(__movePoint(s1, n)) + Number(__movePoint(s2, n));
                break;
            case '-':
                n = n1 > n2 ? n1 : n2;
                s = Number(__movePoint(s1, n)) - Number(__movePoint(s2, n));
                break;
            case '*':
                n = n1 + n2;
                s = Number(s1.replace('.', '')) * Number(s2.replace('.', ''));
                break;
            case '/':
                n = n1 - n2;
                s = Number(s1.replace('.', '')) / Number(s2.replace('.', ''));
                break;
            default:
                throw new Error("calcDoubleFix只支持加减乘除运算");
        }
        s = __movePoint(__expToFix(s.toString()), -n);
        return accRound(Number(s), fractionDigits);
    }
    else {
        switch (operation) {
            case '+':
                return num1 + num2;
            default:
                throw new Error("calcDoubleFix不支持字符串的乘除减运算");
        }
    }
}
//对浮点数的小数位进行截断处理。不做四舍五入，直接截断
//参数：    num:需要处理的数字
//          fractionDigits:小数保留位，值为大于等于0的整数。
//返回：    返回截断处理后的数字。
function accTruncate(num, fractionDigits) {
    if (arguments.length < 1) throw new Error("参数错误");
    __checkFloatParam(num, true);
    var r = (fractionDigits != undefined && !isNaN(fractionDigits) && fractionDigits < __FRACTION_DIGITS) ? fractionDigits : __FRACTION_DIGITS;
    if (r <= 0) r = 0;
    var s1 = __expToFix(num.toString().replace(/,/g, ""));
    if (parseInt(s1) == s1) {
        return parseInt(s1);
    }
    var s2;
    if (r == 0) {
        s2 = s1.substr(0, s1.indexOf("."));
    }
    else {
        s2 = s1.substr(0, s1.indexOf(".") + r + 1);
    }
    return parseFloat(s2);
}
//对浮点数的小数位进行四舍五入处理。
//参数：    num:需要处理的数字
//          fractionDigits:小数保留位，值为大于等于0的整数。
//返回：    返回截断处理后的数字。
function accRound(num, fractionDigits) {
    if (arguments.length < 1) throw new Error("参数错误");
    __checkFloatParam(num, true);
    var r = (fractionDigits != undefined && !isNaN(fractionDigits) && fractionDigits < __FRACTION_DIGITS) ? fractionDigits : __FRACTION_DIGITS;
    if (r <= 0) r = 0;
    var s, s1, s2, start, n;
    var s1 = __expToFix(num.toString().replace(/,/g, ""));
    start = s1.indexOf(".");
    s = __movePoint(s1, r);
    n = Number(s1);
    if (start >= 0) {
        s2 = Number(s1.substr(start + r + 1, 1));
        if (s2 >= 5 && n >= 0 || s2 < 5 && n < 0) {
            s = Math.ceil(s);
        }
        else {
            s = Math.floor(s);
        }
    }
    return Number(__movePoint(s.toString(), -r));
}
//判断Iframe页面是否加载完成。
//参数：    iframeID:Ifram的ID
//返回：    true:加载完成   false:未加载完成。
function isIframeReay(iframeID) {
    if (!document.all(iframeID)) return false;
    if (window.frames(iframeID).document.readyState == "complete") {
        return true;
    }
    else {
        return false;
    }
}
//功能	  ：通过XMLHTTP通道调用ASPX页面 －－－xiarx modi at 2005.9.9
//调用参数：sFile － 调用页面：页面地址面可跟参数
//调用参数：sType － 业务类型：
//调用参数：sSend － 可选参数，如果不为空，则需要用POST调用，并发送该字符串
//返回    ：“-1”－ 取数失败
function openXMLHTTP(sFile, sType, sSend) {
    var strTmp = "";
    if (sFile.indexOf("?") == -1) {
        strTmp = "?";
    }
    else {
        strTmp = "&";
    }
    var sUrl = sFile + strTmp + "ywtype=" + myEscape(sType);
    
    var oHTTP = MyXMLHTTPRequest(sUrl, sSend, null);
    
    var bSuccess = handleXMLErr(oHTTP.responseXML);
    if (bSuccess) {
        return oHTTP.responseText;
    }
    else {
        return "";
    }
}
//历史	  ：added by chenbo on 2011/3/23
//功能	  ：跳转内容框架页，支持跨域跳转
//参数    ：url，可选
//          如果有表示跳转到该url
//          如果没有表示用原地址表示刷新
function NavigateStageFrame(url) {
    var sUrl = "";
    if (url && url != "") {
        sUrl = url;
        top.window.__stageUrl = sUrl;
        //alert(sUrl);
    }
    else {
        try {
            sUrl = top.stage.location.href;
            //alert("reflesh");
        }
        catch (e) {
            logException(e);
            sUrl = top.window.__stageUrl;
            //alert("across reflesh");
        }
    }
    if (sUrl != "") top.stage.navigate(escapeUrl(sUrl));
}
//历史	  ：added by chenbo on 2011/3/23
//功能	  ：激发内容框架页帮助，不支持跨域激发
function FireStageHelp() {
    try {
        if (top.stage.location.href != "about:blank") {
            top.stage.window.document.fireEvent("onhelp");
        }
    }
    catch (e) {
        logException(e);
        //alert("across help");
        alert("提示：您当前访问的是集成站点页面，\r\n\r\n请直接在内容页面中按<F1>打开帮助！");
    }
}
//js异常类
function JavascriptException() {
    this.name = "";
    this.number = ""
    this.message = "";
    this.description = "";
    this.url = "";
    this.lineNumber = "";
    this.stackTrace = "";
    this.toString = function() {
        var x = new ActiveXObject("Microsoft.XMLDOM");
        x.loadXML("<xml/>");
        for (atr in this) {
            n = x.createElement(atr.toString());
            n.setAttribute("value", this[atr]);
            x.documentElement.appendChild(n);
        }
        return x.xml;
    }
}
//记录异常日志。返回值必须为false，否则未捕获的异常无法显示给客户。
function logException() {   
    try {
        if (arguments.length == 1) {
            //try catch调用
            var e = arguments[0];
            if (e.message.indexOf("数据通道调用出现异常：") == 0) return;
        }
        else {
            //onerror调用
            if (arguments[0].indexOf("数据通道调用出现异常：") == 0) return true;
        }
        //显示特定异常信息
        showError();
        
        //不记录前端异常日志 2011-11-30 liuk
        return false;
 
        var jsEx = new JavascriptException();
        var func = arguments.callee.caller;
        if (func == null) {
            jsEx.stackTrace = "顶级脚本错误";
        }
        else {
            var fs = [], str = "";
            while (func != null) {
                str = func.toString();
                fs.push(str.substr(0, str.indexOf("{")).split("\n")[0]);
                func = func.caller;
            }
            jsEx.stackTrace = fs.join(" -> ");
        }
        if (arguments.length == 1) {
            //try catch调用
            var e = arguments[0];
            jsEx.name = e.name;
            jsEx.number = e.number & 0xFFFF;
            jsEx.description = e.description;
            jsEx.message = e.message;
            jsEx.url = window.location.href;
        }
        else {
            //onerror调用
            jsEx.message = arguments[0];
            jsEx.url = window.location.href + "\t" + arguments[1];
            jsEx.lineNumber = arguments[2];
        }
        XmlHttpRequest("/PubAnonymous_XmlHttp.aspx", "logException", "", jsEx.toString());
    }
    catch (e) { }
    return false;
}
//显示特定的异常信息
function showError() {
    if (arguments.length == 1) {
        //直接调用，传入参数为异常
        var e = arguments[0];
        if (e.number == -2146827850)
        {
            if (htcControlExceptionMessage.length == 0) 
            {
                alert("控件资源加载异常或给控件赋值不合法，请修改录入的值或退出重试！");
            }
            else 
            {
                alert(htcControlExceptionMessage);
                htcControlExceptionMessage = "";
            }
        }
        else {
            if (!e.message.indexOf("数据通道调用出现异常：") == 0) {
                alert(e.message);
            }
        }    
    }
    else {
        //logException() 调用
        if (arguments.caller.length == 3) {
            //onerror调用
            var s = arguments.caller[0];
            var re = /未加载完成|赋值错误/g;
            if (re.exec(s) != null)
                alert(s);
        } 
    }
}
// 校验页面及子控件加载状态
// 参数：
//     o        要校验子控件的对象，如 Form、Table 等，子控件为该对象下所有使用平台样式的表单元素
// 返回值：
//     true     页面与子控件都已加载完成
//     false    页面或子控件未加载完成
function ValidateControlsState(o) {
    // 检查页面加载状态，__pageLoaded 在 AppPage 中定义
    if (typeof __pageLoaded == "undefined" || !__pageLoaded) {
        alert("页面未加载完成！");
        return false;
    }
    if (!o) return true;
    // 检查子控件状态
    for (var i = 0; i < o.all.length; i++) {
        var c = o.all[i];
        switch (c.tagName) {
            case "INPUT":
                var re = /\b(hid|txt|dtm|num|selectBox|rad3|lu)\b/gi;
                if (re.exec(c.className) == null) continue;
                break;
            case "TEXTAREA":
                break;
            default:
                continue;
        }
        if (typeof c.isInitialized == "undefined" || !c.isInitialized) {
            alert("子控件资源未加载完成！");
            return false;
        }
    }
    return true;
}
//XMLHTTP通道公用函数 (请统一使用此公用函数)
//参数： url  通道页面的路径及参数
//       postData  POST到后端的数据。用于传递大量数据
//       asycCall  异步回调函数。在onreadystatechange时被调用。
function MyXMLHTTPRequest(url, postData, asycCall , beforeSendCallback) {
    if (url == undefined || url == null || url == "") {
        throw new Error("请求失败：请求文件路径为空！");
    }
    var isAsyc = (typeof (asycCall) == "function" ? true : false);
    
    //将会话状态信息传入通道页面 modified by xq at 2011.12.21
    var sMySessionState = "";
    if (document.all["___MYSESSIONSTATE"]) sMySessionState = "MySessionState=" + document.all["___MYSESSIONSTATE"].value;
    if (sMySessionState != "") url = url + (url.indexOf("?") < 0 ? "?" : "&") + sMySessionState;
    var rdNum = Math.random();
    var oHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    if (url.toLowerCase().indexOf("rdnum") < 0) url = url + (url.indexOf("?") < 0 ? "?" : "&") + "rdnum=" + rdNum;
    //绑定异步回调函数
    if (isAsyc) {
        oHTTP.onreadystatechange = function() {
            if (oHTTP.readyState == 4) {
                handleResponseText(oHTTP.responseText);
            }
            asycCall(oHTTP);
        }
    }
    //发送请求
    if (postData == undefined || postData == null || postData == "") {
        oHTTP.open("GET", escapeUrl(url), isAsyc);
        setXMLHttpRequestHeader(oHTTP); //设置请求头，以便处理异常时和普通请求区分处理
        if (typeof (beforeSendCallback) == "function") {
            beforeSendCallback(oHTTP);
        }
        oHTTP.send();
    }
    else {
        oHTTP.open("POST", escapeUrl(url), isAsyc);
        setXMLHttpRequestHeader(oHTTP)
        if (typeof (beforeSendCallback) == "function") {
            beforeSendCallback(oHTTP);
        }
        //oHTTP.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        oHTTP.send(postData);
    }
    //处理同步请求结果
    if (!isAsyc) {
        //校验数据通道的返回值，如果是Global.asax.vb异常捕获后的返回值，则重定向，并直接抛出异常。
        handleResponseText(oHTTP.responseText);
        return oHTTP;
    }
}
//设置XMLHttp请求标识
function setXMLHttpRequestHeader(oHTTP) {
    oHTTP.setRequestHeader("X-Requested-With", "XMLHttpRequest");
}
//处理XMLHttp请求的未处理异常
function handleResponseText(strResponseText) {
    var msg = "", errorid = "", sParams = "";
    try {
        var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
        xmlDom.loadXML(strResponseText);
        //只处理从Global.asax.vb返回的异常信息
        var attr = xmlDom.documentElement.attributes;
        if (attr.getNamedItem("xtype") && attr.getNamedItem("result") && attr.getNamedItem("xtype").text == "XMLHttpRequest" && attr.getNamedItem("result").text == "false") {
            msg = attr.getNamedItem("errormessage").text;
            errorid = attr.getNamedItem("errorid").text;
        }
        else {
            return;
        }
    }
    catch (e) {
        return;
    }
    if (errorid == "") {
        sParams = "errmessage=" + myEscape(msg);
    }
    else {
        sParams = "errid=" + errorid;
    }
    openMyDlg("错误提示", "/ErrPage.aspx", sParams, null, 400, 190);
    throw new Error("数据通道调用出现异常：" + msg);
}
//XMLHTTP通道公用函数,不抛出异常 (请统一使用此公用函数)
//参数： url  通道页面的路径及参数
//       postData  POST到后端的数据。用于传递大量数据
//       asycCall  异步回调函数。在onreadystatechange时被调用。
function MyXMLHTTPRequestSilence(url, postData, asycCall) {
    if (url == undefined || url == null || url == "") {
        throw new Error("请求失败：请求文件路径为空！");
    }
    var isAsyc = (typeof (asycCall) == "function" ? true : false);
    //将会话状态信息传入通道页面 modified by xq at 2011.12.21
    var sMySessionState = "";
    if (document.all["___MYSESSIONSTATE"]) sMySessionState = "MySessionState=" + document.all["___MYSESSIONSTATE"].value;
    if (sMySessionState != "") url = url + (url.indexOf("?") < 0 ? "?" : "&") + sMySessionState;
    var rdNum = Math.random();
    var oHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    if (url.toLowerCase().indexOf("rdnum") < 0) url = url + (url.indexOf("?") < 0 ? "?" : "&") + "rdnum=" + rdNum;
    //绑定异步回调函数
    if (isAsyc) {
        oHTTP.onreadystatechange = function() {
            if (oHTTP.readyState == 4) {
                handleResponseTextSilence(oHTTP.responseText);
            }
            asycCall(oHTTP);
        }
    }
    //发送请求
    if (postData == undefined || postData == null || postData == "") {
        oHTTP.open("GET", escapeUrl(url), isAsyc);
        setXMLHttpRequestHeader(oHTTP); //设置请求头，以便处理异常时和普通请求区分处理
        oHTTP.send();
    }
    else {
        oHTTP.open("POST", escapeUrl(url), isAsyc);
        setXMLHttpRequestHeader(oHTTP)
        oHTTP.send(postData);
    }
    //处理同步请求结果
    if (!isAsyc) {
        //校验数据通道的返回值，如果是Global.asax.vb异常捕获后的返回值，则重定向，并直接抛出异常。
        handleResponseTextSilence(oHTTP.responseText);
        return oHTTP;
    }
}
//处理XMLHttp请求的未处理异常，不抛出新异常
function handleResponseTextSilence(strResponseText) {
    var msg = "", errorid = "", sParams = "";
    try {
        var xmlDom = new ActiveXObject("Microsoft.XMLDOM");
        xmlDom.loadXML(strResponseText);
        //只处理从Global.asax.vb返回的异常信息
        var attr = xmlDom.documentElement.attributes;
        if (attr.getNamedItem("xtype") && attr.getNamedItem("result") && attr.getNamedItem("xtype").text == "XMLHttpRequest" && attr.getNamedItem("result").text == "false") {
            msg = attr.getNamedItem("errormessage").text;
            errorid = attr.getNamedItem("errorid").text;
        }
        else {
            return;
        }
        if (errorid == "") {
            sParams = "errmessage=" + myEscape(msg);
        }
        else {
            sParams = "errid=" + errorid;
        }
        openMyDlg("错误提示", "/ErrPage.aspx", sParams, null, 400, 190);
    }
    catch (e) {
        return;
    }
}
function checkUrl(value) {
    if (value != "") {
        var reg = /^(https?):\/\/localhost((:|\/).*)?$/i;
        if (reg.test(value)) {
            return true;
        }
        else {
            reg = /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
            return reg.test(value);
        }
    }
    return true;
}
function checkEmail(value) {
    if (value != "") {
        var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
        return reg.test(value)
    }
    return true;
}
function checkMobile(value) {
    if (value != "") {
        var reg = /^1[0-9]{10}$/;
        return reg.test(value)
    }
    return true;
}
//by:chenbo on:2013/1
var perfLog = {
    xhrUrl: "/PerfLogXmlHttp.aspx",
    common: {
        getTopWindow: function(curWin, deep) {
            try {
                if (curWin.__topWin != null) return curWin.__topWin;
                if (deep == undefined) deep = 1;
                if (deep > 10) return null;
                var topWin = null;
                if (curWin.__perfLogFlag) {
                    //self
                    topWin = curWin;
                }
                else if (curWin.top.__perfLogFlag) {
                    //menu/location etc.
                    topWin = curWin.top;
                }
                else if (curWin.top.dialogArguments != undefined) {
                    //deal the error occured in MyDesktop_FunctionTree.aspx
                    //don't know why,syntax 'unknown' only in JScript
                    if (typeof (curWin.top.dialogArguments) == "unknown") return null;
                    //did not append window argument
                    if (typeof (curWin.top.dialogArguments.window) == 'undefined') return null;
                    if (typeof (curWin.top.dialogArguments.window.top) == 'undefined') return null;
                    //showdialog
                    topWin = curWin.top.dialogArguments.window.top;
                }
                else if (curWin.top.opener != undefined) {
                    //window.open
                    topWin = curWin.top.opener.top;
                }
                else {
                    topWin = curWin.top;
                }
                if (topWin.__perfLogFlag) {
                    curWin.__topWin = topWin;
                    return topWin;
                }
                else {
                    //recursion
                    return perfLog.common.getTopWindow(topWin, ++deep);
                }
            }
            catch (e) {
                return null;
            }
        },
        getMainWindow: function(curWin) {
            if (curWin.__mainWin != null) return curWin.__mainWin;
            var mainWin = null;
            if (curWin.__perfLogFlag || curWin.top.__perfLogFlag) {
                mainWin = curWin.top.stage;
            }
            else if (curWin.top.dialogArguments != undefined || curWin.top.opener != undefined) {
                mainWin = window.top;
            }
            else {
                mainWin = curWin.top.stage;
            }
            if (mainWin.location.pathname.toLowerCase().indexOf("frametemp0.aspx") >= 0) {
                mainWin = mainWin.frames[0];
            }
            curWin.__mainWin = mainWin;
            return mainWin;
        },
        getIFrameByWindow: function(main, win) {
            var iframes = main.document.getElementsByTagName("IFRAME");
            for (var i = 0; i < iframes.length; i++) {
                if (iframes.item(i).contentWindow == win) return iframe;
            }
            return undefined;
        },
        isNullOrEmpty: function(v) {
            return (v == undefined || v == null || v == "");
        },
        isIframeBlank: function(win) {
            if (win.location.href == "" || win.location.href == "about:blank") return true;
            return false;
        },
        isIframesLoaded: function(win) {
            //if (win.document.readyState != "complete") return false;
            for (var i = 0; i < win.frames.length; i++) {
                if (typeof (win.frames[i].perfLog) != 'undefined' && typeof (win.frames[i].__callMainFunc) != 'undefined' && win.frames[i].document.readyState != "complete") {
                    return false;
                }
                else {
                    return perfLog.common.isIframesLoaded(win.frames[i]);
                }
            }
            return true;
        },
        formatDateTimeString: function(dt) {
            //yyyy-MM-dd HH:mm:ss.fff
            var s = dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate() + " " + dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds() + "." + dt.getMilliseconds();
            return s;
        },
        xmlhttpRequest: function(url, postData, asycCall, key) {
            var req = new Object();
            var xhr = new ActiveXObject("Microsoft.XMLHTTP");
            req.key = key;
            req.xhr = xhr;
            var isAsyc = (typeof (asycCall) == "function" ? true : false);
            if (isAsyc) {
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4) {
                        asycCall(req);
                    }
                }
            }
            if (perfLog.common.isNullOrEmpty(postData)) {
                xhr.open("GET", escapeUrl(url), isAsyc);
                xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
                xhr.send();
            }
            else {
                xhr.open("POST", escapeUrl(url), isAsyc);
                xhr.send(postData);
            }
            if (!isAsyc) {
                return req;
            }
        }
    },
    errorMsg: {
        cchIsNull: "性能日志错误：获取客户端缓存宿主错误。",
        tsIsNegative: "性能日志错误：计算时间为负数。"
    },
    clientCache: {
        getCacheHost: function(k) {
            switch (k) {
                case "timeDiff":
                case "oprInfo":
                case "oprInfoNum":
                    return perfLog.common.getTopWindow(window);
                case "userInter":
                case "dialogDru":
                    return window;
                default:
                    return perfLog.common.getTopWindow(window);
            }
        },
        setValue: function(k, v) {
            var win = perfLog.clientCache.getCacheHost(k);
            if (win != null) {
                v = v.toString();
                if (v.indexOf("\"") >= 0) v = v.replace(/\u0022/g, "");
                eval("win.__" + k + "=\"" + v + "\";");
            } else {
                throw new Error(perfLog.errorMsg.cchIsNull);
            }
        },
        getValue: function(k) {
            var win = perfLog.clientCache.getCacheHost(k);
            if (win != null) {
                return eval("win.__" + k + ";");
            } else {
                throw new Error(perfLog.errorMsg.cchIsNull);
            }
        },
        clearValue: function(k) {
            var win = perfLog.clientCache.getCacheHost(k);
            if (win != null) {
                eval("win.__" + k + "=\"\";");
            } else {
                throw new Error(perfLog.errorMsg.cchIsNull);
            }
        }
    },
    timeDiff: {
        key: "timeDiff",
        adjust: function() {
            //calc server/client time diff
            var tc, ts, tsStr, tsAry, td;
            tsStr = perfLog.common.xmlhttpRequest(perfLog.xhrUrl + "?type=timeDiff&rnd=" + Math.random().toString(), null, null, null).xhr.responseText;
            tsAry = tsStr.split("|");
            ts = new Date(parseInt(tsAry[0]), parseInt(tsAry[1]), parseInt(tsAry[2]), parseInt(tsAry[3]), parseInt(tsAry[4]), parseInt(tsAry[5]), parseInt(tsAry[6]));
            tc = new Date();
            td = tc - ts;   //include respose time
            //calc request/response time
            var tc2, tc1, diff;
            tc1 = new Date();
            perfLog.common.xmlhttpRequest("/PerfLog.txt?rnd=" + Math.random().toString(), null, null, null);
            tc2 = new Date();
            diff = td - (tc2 - tc1) / 2;    //the txt's request/response time is nearly equal to aspx's respose time
            perfLog.clientCache.setValue(perfLog.timeDiff.key, diff.toString());
        },
        getDiff: function() {
            var td = perfLog.clientCache.getValue(perfLog.timeDiff.key);
            return (perfLog.common.isNullOrEmpty(td)) ? 0 : parseInt(td);
        }
    },
    userInter: {
        key: "userInter",
        begin: function() {
            return new Date();
        },
        end: function(dt) {
            var ts = perfLog.clientCache.getValue(perfLog.userInter.key);
            ts = (perfLog.common.isNullOrEmpty(ts)) ? 0 : parseInt(ts);
            var t = (new Date() - dt) + ts;
            perfLog.clientCache.setValue(perfLog.userInter.key, t);
        },
        reset: function() {
            perfLog.clientCache.clearValue(perfLog.userInter.key);
        },
        getTotal: function() {
            var ts = perfLog.clientCache.getValue(perfLog.userInter.key);
            ts = (perfLog.common.isNullOrEmpty(ts)) ? 0 : parseInt(ts);
            perfLog.clientCache.clearValue(perfLog.userInter.key);
            return ts;
        }
    },
    dialogDru: {
        key: "dialogDru",
        begin: function() {
            return new Date();
        },
        end: function(dt) {
            var ts = perfLog.clientCache.getValue(perfLog.dialogDru.key);
            ts = (perfLog.common.isNullOrEmpty(ts)) ? 0 : parseInt(ts);
            var t = (new Date() - dt) + ts;
            perfLog.clientCache.setValue(perfLog.dialogDru.key, t);
        },
        reset: function() {
            perfLog.clientCache.clearValue(perfLog.dialogDru.key);
        },
        getCurrent: function() {
            var ts = perfLog.clientCache.getValue(perfLog.dialogDru.key);
            ts = (perfLog.common.isNullOrEmpty(ts)) ? 0 : parseInt(ts);
            perfLog.clientCache.clearValue(perfLog.dialogDru.key);
            return ts;
        }
    },
    infoEntity: function(type, func, action, url, dt, ts, memo) {
        this.type = type;
        this.func = func;
        this.action = escapeUrl(action);
        this.url = url;
        this.dt = dt;
        this.ts = ts;
        this.memo = memo;
        function validate() {
            if (ts < 0) {
                throw new Error(perfLog.errorMsg.tsIsNegative);
            }
        }
        this.toString = function() {
            //validate();
            var s = "{{" + this.type + "|" + this.func + "|" + this.action + "|" + this.url + "|" + this.dt + "|" + this.ts + "|" + this.memo + "|" + "}}";
            return s;
        }
        this.toInnerString = function() {
            //validate();
            var s = "[" + this.type + "," + this.func + "," + this.action + "," + this.url + "," + this.dt + "," + this.ts + "," + this.memo + "," + "]";
            return s;
        }
    },
    oprInfo: {
        keyInfo: "oprInfo",
        keyInfoNum: "oprInfoNum",
        saveBatchInfoAsync: function(info) {
            var batchNum = (window.__clientCacheNum) ? parseInt(window.__clientCacheNum) : 20;
            var num = perfLog.oprInfo.getOprInfoNum();
            var cachedInfo = perfLog.clientCache.getValue(perfLog.oprInfo.keyInfo);
            if (perfLog.common.isNullOrEmpty(cachedInfo)) cachedInfo = "";
            cachedInfo += info.toString();
            perfLog.clientCache.setValue(perfLog.oprInfo.keyInfo, cachedInfo);
            perfLog.oprInfo.setOprInfoNum(++num);
            if (num != 0 && num % batchNum == 0) {
                var tmpKey = "__tmp" + (Math.random() * 10).toFixed(10).toString().replace(".", "");
                perfLog.oprInfo.tryClear(tmpKey, cachedInfo, num);
                /* send async */
                perfLog.common.xmlhttpRequest(perfLog.xhrUrl + "?type=saveInfo", cachedInfo, perfLog.oprInfo.commitClear, tmpKey);
            }
        },
        saveInfoImmediately: function() {
            if (perfLog.oprInfo.getOprInfoNum() > 0) {
                var cachedInfo = perfLog.clientCache.getValue(perfLog.oprInfo.keyInfo);
                if (perfLog.common.isNullOrEmpty(cachedInfo)) cachedInfo = "";
                /* send sync */
                var xhr = perfLog.common.xmlhttpRequest(perfLog.xhrUrl + "?type=saveInfo", cachedInfo);
                var result = parseInt(xhr.responseText);
                if (result == 0) {
                    perfLog.clientCache.clearValue(perfLog.oprInfo.keyInfo);
                    perfLog.oprInfo.setOprInfoNum(0);
                }
            }
        },
        getOprInfoNum: function() {
            var num = perfLog.clientCache.getValue(perfLog.oprInfo.keyInfoNum);
            return (perfLog.common.isNullOrEmpty(num)) ? 0 : parseInt(num);
        },
        setOprInfoNum: function(i) {
            perfLog.clientCache.setValue(perfLog.oprInfo.keyInfoNum, i);
        },
        tryClear: function(key, info, num) {
            perfLog.clientCache.setValue(key, info);
            perfLog.clientCache.setValue(key + "num", num);
            perfLog.clientCache.clearValue(perfLog.oprInfo.keyInfo);
            perfLog.oprInfo.setOprInfoNum(0);
        },
        commitClear: function(req) {
            var result = parseInt(req.xhr.responseText);
            var key = req.key;
            if (result != 0) {
                var bakInfo = perfLog.clientCache.getValue(key);
                var bakInfoNum = parseInt(perfLog.clientCache.getValue(key + "num"));
                var num = perfLog.oprInfo.getOprInfoNum();
                var cachedInfo = perfLog.clientCache.getValue(perfLog.oprInfo.keyInfo);
                if (perfLog.common.isNullOrEmpty(cachedInfo)) cachedInfo = "";
                cachedInfo += perfLog.clientCache.getValue(key);
                perfLog.clientCache.setValue(perfLog.oprInfo.keyInfo, cachedInfo);
                perfLog.oprInfo.setOprInfoNum(num + bakInfoNum);
            }
            perfLog.clientCache.clearValue(key);
            perfLog.clientCache.clearValue(key + "num");
        }
    },
    logPageRefresh: {
        end: function() {
            //exclude nav/menu/switch/location frames(based on erp frameset structure)
            var curWin = window;
            if (curWin.name == "nav" || curWin.name == "frmFuncNav" || curWin.name == "menu" || curWin.name == "switch" || curWin.name == "loc") return;
            //calculate datetime and timespan(include main and all inner iframes)
            var td, ui, dd, dt, ts, dtStr;
            dt = new Date();
            dtStr = dt.toString();
            try {
                td = perfLog.timeDiff.getDiff();
                ui = perfLog.userInter.getTotal();
                dd = perfLog.dialogDru.getCurrent();
                ts = (dt - curWin.__beginDateTime) - td - ui - dd;
            }
            catch (e) {
                if (e.description == perfLog.errorMsg.cchIsNull) {
                    //ignore
                    return;
                }
                else {
                    throw e;
                }
            }
            //get main(stage|top) window
            var mainWin = perfLog.common.getMainWindow(curWin);
            //save temporary info in main window
            //because main window always show first,so the main window object is created first
            //add num
            mainWin.__tmpIfamesNum = (!mainWin.__tmpIfamesNum) ? 1 : parseInt(mainWin.__tmpIfamesNum) + 1;
            //first dt(can't save datetime value)
            if (!mainWin.__tmpDtFirst) mainWin.__tmpDtFirst = dtStr;
            if (!mainWin.__tmpDtMsFirst) mainWin.__tmpDtMsFirst = dt.getMilliseconds();
            //last dt
            mainWin.__tmpDtLast = dtStr;
            mainWin.__tmpDtMsLast = dt.getMilliseconds();
            //last ts
            mainWin.__tmpTs = ts;
            //merge memo
            var info = new perfLog.infoEntity("ref", (curWin.__funcCode) ? curWin.__funcCode : "", "", curWin.location.href, perfLog.common.formatDateTimeString(dt), ts, "");
            mainWin.__tmpMemo = (!mainWin.__tmpMemo) ? info.toInnerString() : mainWin.__tmpMemo + info.toInnerString();
            if (perfLog.common.isIframesLoaded(mainWin) && mainWin.document.readyState == "complete") {
                var mainInfo = new perfLog.infoEntity("ref", (mainWin.__funcCode) ? mainWin.__funcCode : "", "", mainWin.location.href, null, null, "");
                if (parseInt(mainWin.__tmpIfamesNum) > 1 || mainWin != curWin) {
                    mainInfo.memo = mainWin.__tmpMemo;
                }
                mainInfo.dt = perfLog.common.formatDateTimeString(new Date(mainWin.__tmpDtFirst));
                mainInfo.ts = (new Date(mainWin.__tmpDtLast) - new Date(mainWin.__tmpDtFirst)) + (mainWin.__tmpDtMsLast - mainWin.__tmpDtMsFirst) + parseInt(mainWin.__tmpTs);
                //clear
                mainWin.__tmpIfamesNum = null;
                mainWin.__tmpDtFirst = null;
                mainWin.__tmpDtMsFirst = null;
                mainWin.__tmpDtLast = null;
                mainWin.__tmpDtMsLast = null;
                mainWin.__tmpTs = null;
                mainWin.__tmpMemo = null;
                try {
                    //alert(" td : " + td + "\r\n ui : " + ui + "\r\n dd : " + dd + "\r\n info : " + mainInfo.toString());
                    perfLog.oprInfo.saveBatchInfoAsync(mainInfo);
                }
                catch (e) {
                    if (e.description == perfLog.errorMsg.cchIsNull) {
                        //ignore
                        return;
                    }
                    else {
                        throw e;
                    }
                }
            }
        }
    },
    logMenuAction: {
        begin: function(funcid, actionid, actionName) {
            if (perfLog.common.isNullOrEmpty(funcid)) funcid = "";
            if (perfLog.common.isNullOrEmpty(actionid)) actionid = "";
            perfLog.userInter.reset();
            perfLog.dialogDru.reset();
            return new perfLog.infoEntity("mnu", funcid, actionid, window.location.href, new Date(), 0, actionName);
        },
        end: function(info) {
            try {
                var ui, dd, dt, ts;
                ui = perfLog.userInter.getTotal();
                dd = perfLog.dialogDru.getCurrent();
                dt = new Date();
                ts = (dt - info.dt) - ui - dd;
                info.ts = ts;
                info.dt = perfLog.common.formatDateTimeString(info.dt);
                //alert(" ts : " + ts + "\r\n ui : " + ui + "\r\n dd : " + dd + "\r\n info : " + info.toString());
                perfLog.oprInfo.saveBatchInfoAsync(info);
            }
            catch (e) {
                if (e.description == perfLog.errorMsg.cchIsNull) {
                    //ignore
                    return;
                }
                else {
                    throw e;
                }
            }
        }
    }
}
function __myAlert(msg) {
    var dt = perfLog.userInter.begin();
    if (!msg) msg = "";
    alert(msg);
    perfLog.userInter.end(dt);
}
function __myConfirm(msg) {
    var dt = perfLog.userInter.begin();
    if (!msg) msg = "";
    if (confirm(msg)) {
        perfLog.userInter.end(dt);
        return true;
    }
    else {
        perfLog.userInter.end(dt);
        return false;
    }
}
function __myPrompt(msg, defaultValue) {
    var dt = perfLog.userInter.begin();
    if (!msg) msg = "";
    var result = prompt(msg, defaultValue);
    perfLog.userInter.end(dt);
    return result;
}
/*
创建：lizt on 2013/2/23
功能：格式化异常输出信息、发送日志信息到服务端
*/
Mysoft.MAP.Log.ScriptException = function(msg, url, line, caller, exNum) {
    try {
        //参数模式：1（异常对象），2（字符串），3（无参数）
        var argModel = 1;
        if (arguments.length > 0) {
            //try catch调用
            var e = arguments[0];
            if (typeof(e)=="object") {
                //异常对象
                argModel = 1;
                if (e.message.indexOf("数据通道调用出现异常：") == 0) return;
            } else {
                //传参调用
                argModel = 2;
                if (e.indexOf("数据通道调用出现异常：") == 0) return;
            }
        }
        else {
            //onerror调用
            argModel = 3;
            if (arguments[0].indexOf("数据通道调用出现异常：") == 0) return true;
        }
        var jsEx = new JavascriptException();
        var func = arguments.callee.caller;
        if (func == null) {
            jsEx.stackTrace = "顶级脚本错误";
        }
        else {
            var fs = [], str = "";
            while (func != null) {
                str = func.toString();
                fs.push(str.substr(0, str.indexOf("{")).split("\n")[0]);
                func = func.caller;
            }
            jsEx.stackTrace = fs.join(" -> ");
        }
        
        if (argModel == 1) {
            //try catch调用传异常对象
            var e = arguments[0];
            jsEx.name = e.name;
            jsEx.number = e.number & 0xFFFF;
            jsEx.description = e.description;
            jsEx.message = e.message;
            jsEx.url = window.location.href;
        } else if (argModel == 2) {
            //try catch调用传字符消息
            jsEx.message = msg;
            jsEx.url = typeof (url) == "undefined" ? window.location.href : url;
            jsEx.lineNumber = typeof (line) == "undefined" ? 0 : line;
            jsEx.number = typeof (exNum) == "undefined" ? 0 : exNum;
        }
        else {
            //onerror调用
            jsEx.message = arguments[0];
            jsEx.url = window.location.href + "\t" + arguments[1];
            jsEx.lineNumber = arguments[2];
        }
        //XmlHttpRequest("/PubAnonymous_XmlHttp.aspx", "logException", "", jsEx.toString());
        //发送写日志请求
        Mysoft.MAP.Log.PostScriptException(jsEx.toString(), jsEx.message);
    }
    catch (e) { }
    return false;
}
//chenbo 2013/9/4
//更新某个容器内(如div)所有iframe已隐藏遮罩状态，触发重新渲染，以达到隐藏目的
//以解决在ie10兼容模式及以下版本的ie中“快速切换标签页导致遮罩不能消失”的问题
function __hideMask(o) {
    if (o.getElementsByTagName) {
        var iframes = o.getElementsByTagName("iframe");
        for (var i = 0; i < iframes.length; i++) {
            __hideMaskInternal(iframes[i]);
        }
    }
}
function __hideMaskInternal(f) {
    var divs = f.contentWindow.document.getElementsByTagName("div");
    for (var i = 0; i < divs.length; i++) {
        if (divs[i].name === "__divLoading" && divs[i].style.display == "none") {
            divs[i].style.width = 2020; //原始width是2000
            var ifrm = f.contentWindow.frames[divs[i].id.replace("__divLoading", "__ifrmLoading")];
            ifrm.frameElement.style.width = 2020;
        }
        if (divs[i].id === "divProgress" && divs[i].style.display == "none") {
            divs[i].style.width = divs[i].clientWidth + 10;
        }
    }
    var iframes = f.contentWindow.document.getElementsByTagName("iframe");
    for (var i = 0; i < iframes.length; i++) {
        __hideMaskInternal(iframes[i]);
    }
}
/*
创建：lizt on 2013/2/23
功能：异步发送日志信息到服务端
*/
Mysoft.MAP.Log.PostScriptException = function(exStr) {
    MyXMLHTTPRequestSilence("/ScriptLogXmlHttp.aspx", exStr, function() {
        //忽略
    });
//    if (typeof (window.IsDebugModel) != "undefined" && window.IsDebugModel == true) {
//        //推送异常消息到控制台
//        
//        Mysoft.MAP.Log.ScriptConsole.Write(arguments.length >= 2 ? arguments[1] : "脚本错误", "<xmp>  "+exStr+"</xmp>", "Error");
//    }
}
/*
创建：lizt on 2013/2/23
功能：初始化控制台显示控件
*/
Mysoft.MAP.Log.ScriptConsole = function() {
    if (typeof (window.IsDebugModel) == "undefined" || window.IsDebugModel != "true") {
        return;
    }
    if (typeof ($) == "undefined") return; //未加载Jquery则不处理。
    if ($("#ScriptConsolePanel").length != 0) return; //已存在控制台
    //控制台控件
    var _console = $("<div />").addClass("scriptconsole").appendTo("body:first");
    _console.attr("id", "ScriptConsolePanel");
    //控制入口
    var _controlpanel = $("<div />").addClass("panel").appendTo(_console).end();
    _controlpanel.append("<div class='popupwindow'><div class='panelbarbutton'><p title='展开控制台'>跟踪日志记录器<span class='console-num'>(0)</span></p><input type='hidden' name='msgNums' /></div></div>");
    //控制台显示面板
    var _consolepanel = $("<div />").addClass("panel").appendTo(_console).end();
    _consolepanel.attr("id", "console-panel");
    _consolepanel.append("<div class='console-panel-title'><a class='close-btn'>最小化</a>跟踪日志记录器</div>");
    _consolepanel.append("<div id='console-content'></div>");
    _consolepanel.append("<div id='console-root'><input type='button' id='console-reset-btn' value='停止监控' /><input type='button' id='console-clear-btn' value='清除' /></div>");
    _consolepanel.find('#console-clear-btn').bind('click', function() { Mysoft.MAP.Log.ScriptConsole.Clear(); });
    _consolepanel.find('#console-reset-btn').toggle(function() { window.IsDebugModel = "false"; $(this).val('开启监控'); }, function() { window.IsDebugModel = "true"; $(this).val('停止监控'); });
    $('#ScriptConsolePanel .close-btn').bind('click', function() { $("#console-panel").hide(); });
    $('#ScriptConsolePanel .panelbarbutton').bind('click', function() { $("#console-panel").show(); });
    Mysoft.MAP.Log.ScriptConsole.AjaxLongPoll();
   
}
Mysoft.MAP.Log.ScriptConsole.AjaxLongPoll = function() {
    //Ajax长连接
    $.ajax({
        type: "POST",
        url: "/ServerMessagePushHandler.ashx",
        dataType: "json",
        async: true,
        data: { ajax: "1", time: "60000" },
        success: function(data, textStatus) {
            //成功
            if (data[0].Success == "1") {
                //客户端处理
                //...
                //alert(data.Detail);
                try {
                    $(data).each(function() { Mysoft.MAP.Log.ScriptConsole.Write(decodeURI(this.Message), decodeURI(this.Detail), this.LogType,this.CreateTime); })
                } catch (ex) { }
                ///重新请求
                Mysoft.MAP.Log.ScriptConsole.AjaxLongPoll();
            } else {
                Mysoft.MAP.Log.ScriptConsole.AjaxLongPoll();
            }
        },
        complete: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.readyState == "4") {
                //alert(XMLHttpRequest.responseText);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //$("#ajaxMessage").text($(this).text()+" out!")
            //                            alert("error:"+textStatus);
            //                            if(textStatus=="timeout")
            //Mysoft.MAP.Log.ScriptConsole.Write(textStatus, errorThrown, "Error");
            //alert(textStatus);
            Mysoft.MAP.Log.ScriptConsole.AjaxLongPoll();
            throw new Error(errorThrown);
        }
    });
}
/*
创建：lizt on 2013/2/23
功能：控制台显示控件输出消息
*/
Mysoft.MAP.Log.ScriptConsole.Write = function(title, content, type, createtime) {
    if (typeof (window.IsDebugModel) == "undefined" || window.IsDebugModel != "true") {
        return;
    }
    var titleOld = title;
    if (title.substring(40) != '') {
        title = title.substr(0, 40) + '...';
    }
    var _contentPanel = $("#ScriptConsolePanel #console-content");
    
    var _detail = $("<div />").addClass("console-itemdetail").prependTo(_contentPanel).end();
    _detail.html(content.replace(/\\r\\n/ig, "<br/>"));
    
    var _item = $("<div />").addClass("console-item").prependTo(_contentPanel).end();
    _item.html(title).attr('title', titleOld);
    _item.addClass(type);
    _item.toggle(function() { $(this).next().show(); }, function() { $(this).next().hide(); });
    var _time = $("<span />").addClass("time-span").prependTo(_item).end();
    _time.text(typeof (createtime) == "undefined" ? new Date().toTimeString().match(/[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}/ig).toLocaleString() : createtime);
    
    $("#ScriptConsolePanel .console-num").text("(" + _contentPanel.find('.console-item').length + ")");
    $("#ScriptConsolePanel input[name='msgNums']").val(_contentPanel.find('.console-item').length);
    $("#ScriptConsolePanel input[name='msgNums']").change();
}
/*
创建：lizt on 2013/2/23
功能：控制台显示控件清除消息
*/
Mysoft.MAP.Log.ScriptConsole.Clear = function() {
    $("#ScriptConsolePanel #console-content").empty();
    $("#ScriptConsolePanel .console-num").text("(" + $('#ScriptConsolePanel .console-item').length + ")");
    $("#ScriptConsolePanel input[name='msgNums']").val($('#ScriptConsolePanel .console-item').length);
    $("#ScriptConsolePanel input[name='msgNums']").change();
}
/*
创建：lizt on 2013/2/23
功能：统一捕获前端异常
*/
window.onerror = Mysoft.MAP.Log.ScriptException;
/*
创建：2013/8/20
功能：审计日志中Cookie设置
*/
function auditLogSetCookie(name, value, exp) {
    exp = exp ? exp : 60;
    if (value) {
        var url = document.location.href;
        value += "," + encodeURIComponent(url);
    }
    var expires = new Date();
    expires.setSeconds(expires.getSeconds() + exp);
    document.cookie =name + "=" +(value) + "; path=/";
    "; expires=" + expires.toUTCString(); //+
    //((domain == null) ? "" : ("; domain=" + domain)) +
    //((path == null) ? "; path=/" : ("; path=" + path)) //+
    //((secure == true) ? "; secure" : "");
}
/*
创建：2013/8/28
功能：添加遮罩层后通过索引来获取iframe的方法。
参数：第一个参数转入的是document或window对象，第二个参数表示iframe的索引
*/
function GetIframeByIndex(obj, index) {
    if (!obj || !(obj.frames) || !obj.frames.length || index < 0) return null;
    var firstIframe = obj.frames[0];
    if (firstIframe.frameElement && firstIframe.frameElement.id && firstIframe.frameElement.id.indexOf("__ifrmLoading") == 0)
        return obj.frames[index + 1];
    return obj.frames[index];
}
/*
Map 304新增功能 Post数据到Iframe
示例代码：
Map.submit({
			url: "AjaxHandler.ashx",//form post数据的url
			dataType: "json",//返回的数据格式
			data: { a: "a12123", b: "b123123" },//需要提交的数据
			timeout: null,
			frame: "iframeName", //document.getElementById("iframeId"), //可以是ifarme对象，也可以是ifarme的name或id，也可以不指定，由程序事创建
			files: ["file1","file2"],//需要上传的文件对象，也可以是上传的文件的name或id
			success: function (result, status) {//提交成功的回调函数
				alert("success");
				//alert(result);
				alert(result.a);
				alert(result.b);
				alert(result.file1);
				alert(result.file2);
				alert(result.success);
			},
			error: function (xml, status, error) {//提交失败的回调函数
				alert(xml);
				alert("error");
			},
			complete: function () {//提交完成后的回调函数
				alert("complete");
			}
		});
*/
 
if (!this.Map) { this.Map = {}; }
(function() {
    var createUploadIframe = function(frameId) {
        var frame = null;
        //create frame
        if (window.ActiveXObject) {
            try {
                frame = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
            } catch(error) {
            }
        }
        if (!frame) {
            frame = document.createElement('iframe');
            frame.id = frameId;
            frame.name = frameId;
        }
        frame.src = 'javascript:false';
        frame.style.position = 'absolute';
        frame.style.top = '-1000px';
        frame.style.left = '-1000px';
        document.body.appendChild(frame);
        return frame;
    };
    var createUploadForm = function(formId, url) {
        //create form
        var form = document.createElement('form');
        form.id = formId;
        form.name = formId;
        form.method = "POST";
        form.action = url;
        form.style.position = 'absolute';
        form.style.top = '-1200px';
        form.style.left = '-1200px';
        document.body.appendChild(form);
        return form;
    };
    var handleError = function(option, xml, status, e) {
        if (option && option.error && typeof(option.error) == "function") {
            try {
                option.error(xml, status, e);
            } catch(e) {
            }
        }
    };
    var handleSuccess = function(option, xml, status) {
        if (option && option.success && typeof(option.success) == "function") {
            option.success(xml, status);
        }
    };
    var handleComplete = function(option, xml, status) {
        if (option && option.complete && typeof(option.complete) == "function") {
            option.complete(xml, status);
        }
    };
    var submit = function(option) {
        if (!option) return false;
        var id = new Date().getTime();
        var createIframe = !option.frame;
        var formId = 'myUploadForm' + id;
        var form = createUploadForm(formId, option.url);
        var frame;
        if (createIframe) {
            frame = createUploadIframe('UploadForm' + id);
        } else {
            frame =getElement(option.frame);
        }
        if (!frame) return false;
        var requestDone = false;
        // Create the request object
        var xml = {};
        // Wait for a response to come back
        var uploadCallback = function(isTimeout) {
            try {
                if (frame.contentWindow) {
                    xml.responseText = frame.contentWindow.document.body ? frame.contentWindow.document.body.innerHTML : null;
                    xml.responseXML = frame.contentWindow.document.XMLDocument ? frame.contentWindow.document.XMLDocument : frame.contentWindow.document;
                } else if (frame.contentDocument) {
                    xml.responseText = frame.contentDocument.document.body ? frame.contentDocument.document.body.innerHTML : null;
                    xml.responseXML = frame.contentDocument.document.XMLDocument ? frame.contentDocument.document.XMLDocument : frame.contentDocument.document;
                }
            } catch(e) {
                handleError(option, xml, null, e);
            }
            if (xml || isTimeout == "timeout") {
                requestDone = true;
                var status;
                try {
                    status = isTimeout != "timeout" ? "success" : "error";
                    // Make sure that the request was successful or notmodified
                    if (status != "error") {
                        // process the data (runs the xml through httpData regardless of callback)
                        var data = uploadHttpData(xml, option.dataType);
                        // If a local callback was specified, fire it and pass it the data
                        if (option.success)
                            handleSuccess(option, data, status);
                    } else
                        handleError(option, xml, status);
                } catch(e) {
                    status = "error";
                    handleError(option, xml, status, e);
                }
                handleComplete(option, xml, status);
                if (window.attachEvent) {
                    frame.detachEvent('onload', uploadCallback);
                } else {
                    frame.removeEventListener('load', uploadCallback, false);
                }
                setTimeout(function() {
                    try {
                        if (createIframe)
                            document.body.removeChild(frame);
                        document.body.removeChild(form);
                    } catch(e) {
                        handleError(option, xml, null, e);
                    }
                }, 100);
                xml = null;
            }
        };
        // Timeout checker
        if (option.timeout > 0) {
            setTimeout(function() {
                // Check to see if the request is still happening
                if (!requestDone) uploadCallback("timeout");
            }, option.timeout);
        }
        try {
            form.target = frame.name;
            if (option.data != null && option.data != undefined) {
                for (var p in option.data) {
                    if (typeof(option.data[p]) != "function" && typeof(option.data[p]) != "undefined") {
                        var input = document.createElement("input");
                        input.type = "hidden";
                        input.name = p;
                        input.value = option.data[p];
                        form.appendChild(input);
                    }
                }
            }
            if (option.files && option.files.length > 0) {
                if (form.encoding) {
                    form.encoding = 'multipart/form-data';
                } else {
                    form.enctype = 'multipart/form-data';
                }
                for (var index = 0; index < option.files.length; index++) {
                    var file = getElement(option.files[index]);
                    var clone = file.cloneNode(true);
                    file.parentNode.insertBefore(clone, file);
                    form.appendChild(file);
                }
            }
            form.submit();
        } catch(e) {
            handleError(option, xml, null, e);
        }
        if (window.attachEvent) {
            frame.attachEvent('onload', uploadCallback);
        } else {
            frame.addEventListener('load', uploadCallback, false);
        }
        return {
            abort: function() {
            }
        };
    };
    var getElement = function(element) {
        var dom = null;
        if (typeof(element) == "string") {
            dom = document.getElementById(element);
            if (!dom) {
                var elements = document.getElementsByName(element);
                if (elements.length > 0) {
                    dom = elements[0];
                }
            }
        } else if (typeof(element) == "object") {
            dom = element;
        }
        return dom;
    };
    var uploadHttpData = function(r, type) {
        var data = !type;
        data = type == "xml" || data ? r.responseXML : r.responseText;
        // Get the JavaScript object, if JSON is used.
        if (type == "json")
            eval("data = " + data);
        return data;
    };
    if (typeof Map.Submit !== "function") {
        Map.Submit = submit;
    }
})();
if (!this.Map) { this.Map = {}; }
// 添加通道公共方法及窗体打开方法
(function () {
    var utility = {
        // 将key/value对转换为query string
        _param: function (option) {
            if (option && typeof option !== "object") {
                return option.toString();
            }
            var s = [],
                value = null;
            for (var key in option) {
                // 如果是函数则执行，并将结果返回
                value = typeof option[key] === "function" ? option[key]() : option[key];
                s[s.length] = encodeURIComponent(key) + "=" + encodeURIComponent(value);
            }
            return s.join("&");
        },
        // 检查属性
        _inspectProperty: function (option, key, type, functionName) {
            if (!option || typeof option !== "object") {
                utility._error(functionName + ": option参数不是一个对象。");
            }
            if (typeof option[key] !== type) {
                utility._error(functionName + ": " + key + "属性未定义或类型不正确");
            }
        },
        // 对url进行合并参数，返回合并后的url
        _concatURI: function (url, data) {
            if (data) {
                url += (url.indexOf("?") !== -1 ? "&" : "?") + utility._param(data);
            }
            return url;
        },
        // 合并对象
        _merge: function (o, add) {
            var p;
            if (typeof o !== "object" || typeof add !== "object") return;
            for (p in add) {
                o[p] = add[p];
            }
            return o;
        },
        // 提示错误
        _error: function (msg) {
            throw new Error(msg);
        }
    };
    // 创建XMLHttpRequest对象
    var createXHR = function () {
        if (typeof XMLHttpRequest != "undefined") {
            return new XMLHttpRequest();
        } else if (typeof ActiveXObject != "undefined") {
            if (typeof arguments.callee.activeXString != "string") {
                var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"];
                for (var i = 0, len = versions.length; i < len; i++) {
                    try {
                        var xhr = new ActiveXObject(versions[i]);
                        arguments.callee.activeXString = versions[i];
                        return xhr;
                    } catch (e) {
                    }
                }
            }
            return new ActiveXObject(arguments.callee.activeXString);
        } else {
            utility._error("XHR._CreateXhr():创建XMLHttpRequest对象失败");
        }
    };
    // 创建XMLDocument对象
    var createXmlDocument = function () {
        if (typeof arguments.callee.activeXString != "string") {
            var versions = ["MSXML2.DOMDocument.6.0", "MSXML2.DOMDocument.3.0", "MSXML2.DOMDocument"];
            for (var i = 0, length = versions.length; i < length; i++) {
                try {
                    var xmlDom = new ActiveXObject(versions[i]);
                    arguments.callee.activeXString = versions[i];
                    return xmlDom;
                } catch (e) {
                }
            }
        }
        return new ActiveXObject(arguments.callee.activeXString);
    };
    //  对url进行合并参数，返回合并后的url
    var concatURI = function (option) {
        return utility._concatURI(option.url, option.data);
    };
    // XMLHttpRequest对象请求
    var ajax = function (option) {
        option.data = option.data || {};
        option.headers = option.headers || {};
        option.dataType = option.dataType || "text";
        var xhr = createXHR(),
            that = {};
        var setRequestHeader = function () {
            // 设置请求头
            for (var i in option.headers) {
                xhr.setRequestHeader(i, option.headers[i]);
            }
        };
        // 统一处理返回信息
        var handleResponseText = function (responseData, type) {
            var obj = (type === "json" && typeof responseData === "object" ? responseData : null),
                xmlDom = (type === "xml" && typeof responseData === "object" ? responseData : null),
                data = xmlDom ? xmlDom.xml : responseData;
            // 是对象，但是没有errorid属性,或者返回值不包含errorid,则返回
            if ((obj && !obj.errorid) || data.indexOf("errorid") === -1) return;
            if (!obj) obj = {};
            if (xmlDom == null && type !== "json") {
                xmlDom = createXmlDocument();
                xmlDom.loadXML(responseData);
            }
            //解析xml，如果出错则返回
            if (xmlDom && (xmlDom.documentElement === null || xmlDom.parseError != 0)) {
                utility._error("sendXmlHTTP():转换XML出错");
            }
            if (xmlDom) {
                //只处理从Global.asax.vb返回的异常信息
                var x = xmlDom.documentElement;
                if (x && x.tagName.toUpperCase() == "XML") {
                    var xtype = x.getAttribute("xtype");
                    var result = x.getAttribute("result");
                    if (xtype === "XMLHttpRequest" && result === "false") {
                        obj.errormsg = x.getAttribute("errormessage");
                        obj.errorid = x.getAttribute("errorid");
                    }
                }
            }
            if (obj.errorid) {
                showPostDialog({
                    url: "/ErrPage.aspx",
                    data: obj,
                    title: "错误提示",
                    dialogFeatures: "dialogWidth:400px;dialogHeight:190px; status:0; help:0; resizable:0; center:1;"
                });
                utility._error("sendXmlHTTP():数据通道调用出现异常,原因:" + obj.errormsg);
            }
        };
        var submit = function () {
            var responseData = null;
            if (!option.headers["X-Requested-With"]) {
                option.headers["X-Requested-With"] = "XMLHttpRequest";
            }
            if (option.mimeType && xhr.overrideMimeType) {
                xhr.overrideMimeType(option.mimeType);
            }
            if (option.type && option.type.toUpperCase() == "GET") {
                utility._merge(option.data, { "_t": (new Date()).getTime() });
                xhr.open("GET", utility._concatURI(option.url, option.data), false);
                setRequestHeader();
                xhr.send(null);
            } else {
                xhr.open("POST", option.url, false);
                // 现在ERP中大部分POST传递的是XML数据。为了防止改变FORM的行为，影响现程序的运行。不需要加如下content-type
                if (typeof option.data == "object") {
                    option.headers["Content-Type"] = "application/x-www-form-urlencoded";
                }
                setRequestHeader();
                xhr.send(utility._param(option.data));
            }
            if (xhr.readyState === 4) {
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304) {
                    // 作为文本返回
                    if (option.dataType === "text") {
                        responseData = xhr.responseText;
                    } else if (option.dataType === "xml") { //响应内容类型是"text/xml"或"application/xml"返回
                        responseData = xhr.responseXML || xhr.responseText;
                    } else {
                        responseData = xhr.responseText || "{}";
                        if (typeof responseData === "string") {
                            var ct = xhr.getResponseHeader("content-type") || "";
                            if (option.dataType === "json" || (!option.dataType && ct.indexOf("json") >= 0)) {
                                responseData = (new Function("return " + responseData))();
                            }
                        }
                    }
                } else {
                    if (option.error && typeof option.error === "function") {
                        option.error.call(that, xhr, xhr.status);
                    } else {
                        var error = "";
                        var start = xhr.responseText.indexOf("<title>");
                        var end = xhr.responseText.indexOf("</title>");
                        if (start > 0 && end > start)
                            error += xhr.responseText.substring(start + 7, end);
                        alert("执行通道请求时出现了异常：\r\n错误码:" + xhr.status + "\r\n原因:" + error + "\r\n地址:" + option.url);
                    }
                    utility._error("sendXmlHTTP():执行通道请求异常");
                }
            }
            if (responseData) {
                handleResponseText(responseData, option.dataType);
            }
            return xhr;
        };
        that.submit = submit;
        return that;
    };
    //---------------------------------------------------
    // 打开一个非模态窗口
    //---------------------------------------------------
    //var option = {
    //    url: "/xxxx/abc.aspx",			// （必埴）文件路径，不带任何查询字符串参数
    //    data: { a: 1, b: 2, c: 3 },		// （可选）查询字符串参数对象，注意：是一个对象，不是字符串。
    //    name: "windowName",				// （必埴）窗口名称
    //    dialogFeatures: "",				// （可选）对话框特性。如果不指定，程序自动计算
    //};
    var openWindow = function (option) {
        utility._inspectProperty(option, "url", "string", "Map.openWindow()");
        utility._inspectProperty(option, "name", "string", "Map.openWindow()");
        var url = utility._concatURI(option.url, option.data);
        var sFeatures = option.dialogFeatures;
        if (typeof (sFeatures) != "string" || sFeatures.length < 1) {
            var iX = window.screen.width;
            var iY = window.screen.height;
            sFeatures = "left:0;top:0;width:" + iX + "px;heigth:" + iY + "px;status=1;resizable:1;";
        }
        return window.open(url, option.name, sFeatures);
    };
    //var option = {
    //    url: "/xxxx/abc.aspx",			// （必埴）文件路径，不带任何查询字符串参数
    //    data: { a: 1, b: 2, c: 3 },		// （可选）查询字符串参数对象，注意：是一个对象，不是字符串。
    //    dialogArguments: {},				// （可选）传递给对话框的参数对象
    //    dialogFeatures: "",				// （可选）对话框特性。如果不指定，程序自动计算
    //};
    var showDialog = function (option) {
        utility._inspectProperty(option, "url", "string", "Map.showDialog()");
        var url = utility._concatURI(option.url, option.data);
        var sFeatures = option.dialogFeatures;
        if (typeof (sFeatures) != "string" || sFeatures.length < 1) {
            var iX = window.screen.width / 2;
            var iY = window.screen.height - 80 / 2;
            sFeatures = "dialogWidth:" + iX + "px;dialogHeight:" + iY + "px; status:0; help:0; resizable:0; center:1;";
        }
        return window.showModalDialog(url, option.dialogArguments, sFeatures);
    };
    //var option = {
    //    url: "/xxxx/abc.aspx",			// （必埴）文件路径，不带任何查询字符串参数
    //    data: { a: 1, b: 2, c: 3 },		// （可选）查询字符串参数对象，注意：是一个对象，不是字符串。
    //    title: "title",					// （可选）窗口标题
    //    transferUrl:""                    // （可选）用于重写默认的FrameTemp0.aspx地址
    //    dialogArguments: {},				// （可选）传递给对话框的参数对象
    //    dialogFeatures: "",				// （可选）对话框特性。如果不指定，程序自动计算
    //};
    var showPostDialog = function (option) {
        utility._inspectProperty(option, "url", "string", "Map.showPostDialog()");
        var transferUrl = option.transferUrl || "/FrameTemp0.aspx?";
        var urlData = { filename: option.url, title: option.title, param: utility._param(option.data) };
        var url = transferUrl + utility._param(urlData);
        var sFeatures = option.dialogFeatures;
        if (typeof (sFeatures) != "string" || sFeatures.length < 1) {
            var iX = window.screen.width / 2;
            var iY = window.screen.height - 80 / 2;
            sFeatures = "dialogWidth:" + iX + "px;dialogHeight:" + iY + "px; status:0; help:0; resizable:0; center:1;";
        }
        return window.showModalDialog(url, option.dialogArguments, sFeatures);
    };
    //---------------------------------------------------
    // 前端页面重定向
    //---------------------------------------------------
    //var option = {
    //    window: windowObject,				// （必埴）window对象
    //    url: "/xxxx/abc.aspx",			// （必埴）文件路径，不带任何查询字符串参数
    //    data: { a: 1, b: 2, c: 3 }		// （可选）查询字符串参数对象，注意：是一个对象，不是字符串。
    //};
    var pageRedirect = function (option) {
        utility._inspectProperty(option, "window", "object", "Map.PageRedirect()");
        utility._inspectProperty(option, "url", "string", "Map.PageRedirect()");
        var url = utility._concatURI(option.url, option.data);
        option.window.location.href = url;
    };
    //---------------------------------------------------
    // 同步通道调用。
    // 如果需要异步调用，请直接使用jQuery（需要在页面中添加jQuery的引用）
    //---------------------------------------------------
    //var option = {
    //    type: "GET",						// （可选）请求方式，可选值：GET, POST，默认为"GET"
    //    url: "/xxxx/abc.aspx",			// （必埴）文件路径，不带任何查询字符串参数
    //    data: { a: 1, b: 2, c: 3 },		// （可选）参数对象，注意：建议是一个对象.
    //    dataType: "text",					// （可选）预期服务器返回的数据类型。
    //    headers: {},						// （可选）一个额外的"{键:值}"对映射到请求一起发送。
    //};
    var sendXmlHTTP = function (option) {
        utility._inspectProperty(option, "url", "string", "Map.SendXmlHttp()");
        return ajax(option).submit();
    };
    if (typeof Map.openWindow !== "function") {
        Map.openWindow = openWindow;
        Map.showDialog = showDialog;
        Map.showPostDialog = showPostDialog;
        Map.pageRedirect = pageRedirect;
        Map.sendXmlHTTP = sendXmlHTTP;
        Map.createXmlDocument = createXmlDocument;
        Map.concatURI = concatURI;
    }
})();
//标识资源的全局变量
var __jsGlobal = true;
