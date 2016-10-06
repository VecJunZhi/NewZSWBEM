<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/wbem/css/default.css" >
	<link type="text/css" rel="stylesheet" href="/common/css/jqueryui/jquery-ui.min.css">
	<link type="text/css" rel="stylesheet" href="/common/js/jqGrid/css/ui.jqgrid.css">
	<script type="text/javascript" src="/common/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/common/js/jqGrid/js/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="/common/js/jqGrid/js/i18n/grid.locale-cn.js"></script>
	<style type="text/css">
		.rigths{
			height: 100%;
			width: 100%;
			margin-top: -10px;
			background-color:#fff;
		}
	</style>
</head>
<body style="padding: 0px;margin-top:10px;margin-left: 2%"> 
	    
<!-- jqGrid 分页 div gridPager -->
<!-- <div id="gridPager"></div> -->
 	<div class="rigths">
		<dl>
			<div class="ltop">
				<span style="width:80px; font-size:11px; float: left;">岗位成员</span>
				<span class="co4" style="width:80px; font-size:11px;">移除用户</span>
				<span class="co5" style="width:80px; font-size:11px;">添加成员</span>
				<span class="co1" style="width:80px; font-size:11px;">新增用户</span>
			</div>
		</dl>
		<table id="list4"></table>
	</div>
	<div class="kfoot">
	</div> 
<input type="text" value="" id="txt_CopyLink">
</body>
  <script type="text/javascript">
function copyToClipboard(txt) {
alert("dfdde"+window.clipboardData);
    if (window.clipboardData) {
    alert("ddd");
        window.clipboardData.clearData();
        window.clipboardData.setData("Text", txt);
        alert("已经成功复制到剪帖板上！");
    } else if (navigator.userAgent.indexOf("Opera") != -1) {
    alert("eee");
        window.location = txt;
    } else if (window.netscape) {
        try {alert("dddeee");
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch (e) {
            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        alert("eeess" +clip);
        if (!clip) return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans) return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = txt;
        str.data = copytext;
        trans.setTransferData("text/unicode", str, copytext.length * 2);
        var clipid = Components.interfaces.nsIClipboard;
        if (!clip) return false;
        clip.setData(trans, null, clipid.kGlobalClipboard);
        alert("已经成功复制到剪帖板上！");
    }
}
//点击文本框复制其内容到剪贴板上
function setCopyLink() {
    $("#txt_CopyLink").val(document.URL)
    .focus(function () {
        $(this).css({ "background-color": "#ddd" }).select();
        copyToClipboard($("#txt_CopyLink").val());
    }).blur(function () {
        $(this).css({ "background-color": "#fff" });
    });
}
setCopyLink();
$("#list4").jqGrid({
	datatype: "local",
	height: 250,
   	colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
   	colModel:[
   		{name:'id',index:'id', width:60, sorttype:"int"},
   		{name:'invdate',index:'invdate', width:90, sorttype:"date"},
   		{name:'name',index:'name', width:100},
   		{name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
   		{name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},		
   		{name:'total',index:'total', width:80,align:"right",sorttype:"float"},		
   		{name:'note',index:'note', width:150, sortable:false}		
   	],
   	multiselect: true,
   	 rowNum:10,
      width:700,
       rowList:[10,20,30],
       pager: '#pgfrc1',
       sortname: 'id',
      viewrecords: true,
      sortorder: "desc",
   	caption: "Manipulating Array Data"
});
var mydata = [
		{id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
		];
for(var i=0;i<=mydata.length;i++){

$("#list4").jqGrid('addRowData',i+1,mydata[i]);

}
	function pageInitFn(girdId,url,colNameArray,colModelArray,rowNum,rowArray,pagerId,tableName){
  $(window).resize(function(){
  	$("#"+girdId).setGridWidth($(window).width()-50);
  });
  alert("ii"+$(window).width());
  jQuery("#"+girdId).jqGrid(
      {
        url : '',
        datatype : "json",
        colNames : colNameArray,
        colModel : colModelArray,
        rowNum : rowNum,
        rowList : rowArray,
        pager : '#'+pagerId,
        emptyrecords: "没有数据",
        sortname : 'id',
        mtype : "post",
        viewrecords : true,
        sortorder : "desc",
        caption : tableName,
        width:600-50,
        height:'60%',
        jsonReader : {
		      root: "rows",    // json中代表实际模型数据的入口
		      page: "page",    // json中代表当前页码的数据
		      total: "total",    // json中代表页码总数的数据
		      records: "records", // json中代表数据行总数的数据
		      repeatitems: true, // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
		      /* cell: "cell",
		      id: "id",
		      userdata: "userdata",
		      subgrid: {
		         root:"rows", 
		        repeatitems: true, 
		         cell:"cell"
			  } */
 		}
      });
      alert("ddd");
  jQuery("#"+girdId).jqGrid('navGrid', '#'+pagerId, {edit : false,add : false,del : false,search: false, refresh: false, view: false,align: "left"});
   alert("ddddd");
}
  </script>
</html>
