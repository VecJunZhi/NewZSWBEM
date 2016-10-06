<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/mbem/mcrm/house/pub/head.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>我的待办</title>
<link  rel="stylesheet" href="/mbem/mcrm/house/css/base.min.css">
<link  rel="stylesheet" href="/mbem/mcrm/house/css/ui.css">
<link  rel="stylesheet" href="/mbem/mcrm/house/css/yunke.css">
<link rel="stylesheet" type="text/css" href="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css"> 
<script src="/common/js/jquery-1.9.1.min.js"></script>
<script src="/mbem/mcrm/house/js/iscroll.js"></script>
<script src="/common/js/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script  src="/common/js/layer/layer.js"></script> 
<script src="/mbem/mcrm/house/js/backlogPaging.js"></script>
<script src="/mbem/mcrm/house/js/addCustom.js"></script>

<style type="text/css" media="all">
.a_li_d1 span,.a_li_d1 font{float:right; margin-right:8px;}
 #wrapper1,#wrapper2,#wrapper3,#wrapper4 {
	position:absolute;  
	z-index:1; 
	/* top:100px;  */
	top:48px;
	bottom:48px; 
	left:0;
	width:100%;
	overflow:auto; 
} 
 #scroller1,#scroller2,#scroller3,#scroller4 {
	position:relative;
	-webkit-tap-highlight-color:rgba(0,0,0,0);

	float:left;
	width:100%;
	padding:0;
} 
#pullDown1 .pullDownIcon, #pullUp1 .pullUpIcon,#pullDown2 .pullDownIcon, #pullUp2 .pullUpIcon,#pullDown3 .pullDownIcon, #pullUp3 .pullUpIcon,#pullDown4 .pullDownIcon, #pullUp4 .pullUpIcon  {
	display:block; float:left;
	width:40px; height:40px;
	background:url(pull-icon@2x.png) 0 0 no-repeat;
	-webkit-background-size:40px 80px; background-size:40px 80px;
	-webkit-transition-property:-webkit-transform; 
	-webkit-transition-duration:250ms;	
}
#pullDown1 .pullDownIcon,#pullDown2 .pullDownIcon,#pullDown3 .pullDownIcon,#pullDown4 .pullDownIcon {
	-webkit-transform:rotate(0deg) translateZ(0);
}
#pullUp1 .pullUpIcon,#pullUp2 .pullUpIcon,#pullUp3 .pullUpIcon,#pullUp4 .pullUpIcon  {
	-webkit-transform:rotate(-180deg) translateZ(0);
}

#pullDown1.flip .pullDownIcon,#pullDown2.flip .pullDownIcon,#pullDown3.flip .pullDownIcon,#pullDown4.flip .pullDownIcon {
	-webkit-transform:rotate(-180deg) translateZ(0);
}

#pullUp1.flip .pullUpIcon,#pullUp2.flip .pullUpIcon,#pullUp3.flip .pullUpIcon,#pullUp4.flip .pullUpIcon {
	-webkit-transform:rotate(0deg) translateZ(0);
}

#pullDown1.loading .pullDownIcon, #pullUp1.loading .pullUpIcon,#pullDown2.loading .pullDownIcon, #pullUp2.loading .pullUpIcon,#pullDown3.loading .pullDownIcon, #pullUp3.loading .pullUpIcon,#pullDown4.loading .pullDownIcon, #pullUp4.loading .pullUpIcon {
	background-position:0 100%;
	-webkit-transform:rotate(0deg) translateZ(0);
	-webkit-transition-duration:0ms;

	-webkit-animation-name:loading;
	-webkit-animation-duration:2s;
	-webkit-animation-iteration-count:infinite;
	-webkit-animation-timing-function:linear;
}
@-webkit-keyframes loading {
	from { -webkit-transform:rotate(0deg) translateZ(0); }
	to { -webkit-transform:rotate(360deg) translateZ(0); }
}
.table th, .table td{
	padding: 0px !important;
}
 table.dataTable tbody td { /*table.dataTable tbody th,*/
    padding: 1px 1px !important;
}
</style>
<script type="text/javascript">
	/* var str1 = "<li name='eachFollowItem' ><div data-id='39d08b6c-e4dd-9de4-8d62-5b1f1bfd7e65' class='a_li'><a onclick=urgedDetail('";					
	var str2 = "')><dl><dt><span style='color:#333;font-weight:normal'>";   
	var str3 = "</span><font style='text-shadow:0 0 0 #000;background:#DE2121'>";  
	var str4 = "</font></dt><dd style='height:27px; width:142px;'> </dd><dd class='a_li_d1 clearfix'><font style='color:#999;font-weight:normal;float:left;'>";                                     
	var str5 = "</font></dd></dl></a><a  style='width:142px;background: url() no-repeat center center;'><dl><dd class='a_li_d1 clearfix'><font>";
	var str6 = "</font></dd><dd style='height:30px; width:122px;'><img src='/mbem/mcrm/house/images/difficultCst.png' width='30px' height='30px' onclick=setCstUrgedStatusInfo(this,'";
	var str7 = "')></dd><dd class='a_li_d1'><span>";
	var str8 = "</span></dd></dl></a></div></li> "; */
	var str1="<li name='eachFollowItem'><div data-id='39d08b6c-e4dd-9de4-8d62-5b1f1bfd7e65' class='a_li'><a href='#' onclick=urgedDetail('";
	//B95297AC-851E-4DC7-80E3-021A8704B7A1','96D95D96-6C48-40B0-A579-3550CC21FEDF
	var str2="')><dl><dt><span style='max-width:150px'>";
  			//李大伟
	var str3="<font style='background-color:red;text-shadow: 0 0 0 #000;'>";
  			//逾期天数
	var str4="</font></span><span style='float:right;padding-right:5px;max-width:300px;font-size:14px'>";
  			//2015-04-01 认购
	var str5="</span></dt><dd class='a_li_d1 clearfix'><font style='float:left;margin-right:0;'>";
			//房间信息
	var str6="</font><span style='float:right;'>";
			//下次催办 2015-04-01
	var str7="</span></dd><dd class='a_li_d2'><span style='margin-top:5px;'>";
			//钱不合适现在不考虑了钱不合适现在不考虑了钱不合适现在不考虑了钱不合适现在不考虑了
	var str8="</span></dd></dl></a><i style='width:50px;height: 100%;position: absolute;bottom: 0;right: 0px;'><img src='/mbem/mcrm/house/images/bg-star.png' onclick=setCstUrgedStatusInfo(this,'";
		//96D95D96-6C48-40B0-A579-3550CC21FEDF&quot;
	var str9="') style='width: 28px; height: 28px; margin-top: 32px;'></i></div></li>"
	var pageLen = 30;
	var unPaymentCount = ${unPaymentCount};
	var unSignUpCount = ${unSignUpCount};
	var unLendingCount = ${unLendingCount};
	var toUrgedCount = ${toUrgedCount};
	var type = "";
	
	$(document).ready(function(){
		$("li a").click(function(){
			var page = $(this).attr("pageIndex");
			var count = $(this).children().text();
			if(count == 0 || count == undefined){
				$(this).removeClass("ui-btn-active");
				return false;
			}//没有数量时不进行跳转
			loadUrl = "/mbem/mcrm/house/customer/loadXsBusinessUrgedByType.action";	
			if(page == "1"){
				$("#thelist1").empty();
				pullDownId="pullDown1",pullUpId="pullUp1",wrapperId="wrapper1";
				myScroll1 = loaded();
				listId = "thelist1";
				myScroll = myScroll1;
				pageIndex1 = 1;
				pageIndex = pageIndex1;
				cstType="unPayment";
				cstCount = unPaymentCount;
				pullUpAction();
			}	
			if(page == "2")
			{
				$("#thelist2").empty();
				pullDownId="pullDown2",pullUpId="pullUp2",wrapperId="wrapper2";
				myScroll2 = loaded();
				listId = "thelist2";
				myScroll = myScroll2;
				pageIndex2 = 1;
				pageIndex = pageIndex2;
				cstType="unSignUp";
				cstCount = unSignUpCount;
				pullUpAction();
			}
			if(page == "3"){
				$("#thelist3").empty();
				pullDownId="pullDown3",pullUpId="pullUp3",wrapperId="wrapper3";
				myScroll3 = loaded();
				listId = "thelist3";
				myScroll = myScroll3;
				pageIndex3 = 1;
				pageIndex = pageIndex3;
				cstType="unLending";
				cstCount = unLendingCount;
				pullUpAction();
			}	
			if(page == "4"){
				$("#thelist4").empty();
				pullDownId="pullDown4",pullUpId="pullUp4",wrapperId="wrapper4";
				myScroll4 = loaded();
				listId = "thelist4";
				myScroll = myScroll4;
				pageIndex4 = 1;
				pageIndex = pageIndex4;
				cstType="toUrged";
				cstCount = toUrgedCount;
				pullUpAction();
			}	
		});
		
		$("#followRemind").click(function(){
			window.location.href = "/mbem/mcrm/house/customer/backlogPage.action";
		});
	});
	
	function back() {
		window.location.href = "/mbem/mcrm/house/customer/businessUrged.action";
	}
	
	function joinStr(data,i) {
		var urgedStatus;
		var level;
		if(data[i].xsGjjl.nextUrgedDate==null||data[i].xsGjjl.nextUrgedDate==""){
			urgedStatus = "未催办";
		}
		else{
			urgedStatus = "下次催办 "+data[i].xsGjjl.nextUrgedDate.substring(0,11);
		}
		if(data[i].xsGjjl.urgedContent==null)
			data[i].xsGjjl.urgedContent = "";
		switch(cstType){
			case "unPayment":
				level = data[i].xsExt.level;
				break;
			case "unSignUp":
				level = data[i].xsExt.level1;
				break;
			case "unLending":
				level = data[i].xsExt.level2;
				break;
			case "toUrged":
				switch(data[i].xsOther.toUrged){
					case "unPayment":
						level = data[i].xsExt.level;
						break;
					case "unSignUp":
						level = data[i].xsExt.level1;
						break;
					case "unLending":
						level = data[i].xsExt.level2;
						break;				
				}
				break;
		}
		if(level == 0){
			//str6 = "</font></dd><dd style='height:30px; width:122px;'><img src='/mbem/mcrm/house/images/ordinaryCst.png' width='30px' height='30px' onclick=setCstUrgedStatusInfo(this,'";
			str8="</span></dd></dl></a><i style='width:50px;height: 100%;position: absolute;bottom: 0;right: 0px;'><img src='/mbem/mcrm/house/images/ordinaryCst.png' onclick=setCstUrgedStatusInfo(this,'";
		}
		if(level == 1){
			//str6 = "</font></dd><dd style='height:30px; width:122px;'><img src='/mbem/mcrm/house/images/difficultCst.png' width='30px' height='30px' onclick=setCstUrgedStatusInfo(this,'";
			str8="</span></dd></dl></a><i style='width:50px;height: 100%;position: absolute;bottom: 0;right: 0px;'><img src='/mbem/mcrm/house/images/difficultCst.png' onclick=setCstUrgedStatusInfo(this,'";
		}
		if(cstType == "toUrged"){
			//str = str1+data[i].xsOther.toUrged+"','"+data[i].xsTrade.tradeGuid+str2+data[i].xsCst.cstName+str3+"逾"+data[i].xsOther.overDays+"天"+str4+data[i].xsRoom.roomInfo.substring(11)+str5+data[i].xsOrder.qsDate+"  "+data[i].xsOrder.orderType+str6+data[i].xsTrade.tradeGuid+"','"+data[i].xsOther.toUrged+str7+urgedStatus+str8;
			if(data[i].xsOther.overDays <= 0) {
				str = str1+data[i].xsOther.toUrged+"','"+data[i].xsTrade.tradeGuid+str2+data[i].xsCst.cstName+str3+"未逾期"+str4+data[i].xsOrder.qsDate+"  "+data[i].xsOrder.orderType+str5+data[i].xsRoom.roomInfo.substring(11)+str6+urgedStatus+str7+data[i].xsGjjl.urgedContent+str8+data[i].xsTrade.tradeGuid+"','"+data[i].xsOther.toUrged+str9;
			}else {
				str = str1+data[i].xsOther.toUrged+"','"+data[i].xsTrade.tradeGuid+str2+data[i].xsCst.cstName+str3+"逾"+data[i].xsOther.overDays+"天"+str4+data[i].xsOrder.qsDate+"  "+data[i].xsOrder.orderType+str5+data[i].xsRoom.roomInfo.substring(11)+str6+urgedStatus+str7+data[i].xsGjjl.urgedContent+str8+data[i].xsTrade.tradeGuid+"','"+data[i].xsOther.toUrged+str9;
			}
			
		}else{
			//str = str1+cstType+"','"+data[i].xsTrade.tradeGuid+str2+data[i].xsCst.cstName+str3+"逾"+data[i].xsOther.overDays+"天"+str4+data[i].xsRoom.roomInfo.substring(11)+str5+data[i].xsOrder.qsDate+"  "+data[i].xsOrder.orderType+str6+data[i].xsTrade.tradeGuid+"','"+cstType+str7+urgedStatus+str8;
			if(data[i].xsOther.overDays <= 0) {
				str = str1+cstType+"','"+data[i].xsTrade.tradeGuid+str2+data[i].xsCst.cstName+str3+"未逾期"+str4+data[i].xsOrder.qsDate+"  "+data[i].xsOrder.orderType+str5+data[i].xsRoom.roomInfo.substring(11)+str6+urgedStatus+str7+data[i].xsGjjl.urgedContent+str8+data[i].xsTrade.tradeGuid+"','"+cstType+str9;
			}else {
				str = str1+cstType+"','"+data[i].xsTrade.tradeGuid+str2+data[i].xsCst.cstName+str3+"逾"+data[i].xsOther.overDays+"天"+str4+data[i].xsOrder.qsDate+"  "+data[i].xsOrder.orderType+str5+data[i].xsRoom.roomInfo.substring(11)+str6+urgedStatus+str7+data[i].xsGjjl.urgedContent+str8+data[i].xsTrade.tradeGuid+"','"+cstType+str9;
			}
		}
	}
		
	function setCstUrgedStatusInfo(tag,tradeGuid,type) {
		var level;
		//先判断一下当前的状态(判断点击图标的样式或内容)，再决定level的值，0设置为普通，1设置为疑难
		var src = $(tag).attr("src");
		if(src == "/mbem/mcrm/house/images/ordinaryCst.png") {
			level = 1;
		}
		if(src == "/mbem/mcrm/house/images/difficultCst.png") {
			level = 0;
		}
		$.ajax({
	        url: "/mbem/mcrm/house/customer/setCstUrgedStatusInfo.action",
	        type:"POST",
	        data: {"tradeGuid":tradeGuid,"level":level,"type":type},
	        dataType: "json",
	        success: function (data) {
	        	if(data == 1) {
	        		if(level == 0){
	        			$(tag).attr("src","/mbem/mcrm/house/images/ordinaryCst.png");
	        		}
	        		if(level == 1){
	        			$(tag).attr("src","/mbem/mcrm/house/images/difficultCst.png");
	        		}	
	        	} else {
	        		//layer.alert("设置失败");
	        	}
	        },
	        error: function() {
	        }
		});    
	}
</script>
</head>
<body>
<!--  跟进提醒-->
<div data-role="page" id="main" data-transition="pop" hidden="hidden"> 
	<div data-role="header">
		<div class="containerF">
		    <div class="container">
		        <div class="padding_15">
		            <div class="header">
		                <ul class="tab " style="margin-top: 8px;">
		                    <li _index="0" name="follow" id="followRemind"><div style='text-shadow:0 0 0 #000'>跟进提醒</div></li>
		                    <li class="select" name="outdate" _index="1"><div>业务催办</div></li>
		                </ul>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
	<ul data-role="listview" data-inset="true">   
	<li><a href="#page1" data-transition="slide"  pageIndex="1" style="color:#333;font-weight:normal">未付款<span class="ui-li-count" style="color:#3c6">${unPaymentCount}</span></a></li>
	<li><a href="#page2" data-transition="slide"  pageIndex="2" style="color:#333;font-weight:normal">未签约<span class="ui-li-count" style="color:#3c6">${unSignUpCount}</span></a></li>
	<li><a href="#page3" data-transition="slide"  pageIndex="3" style="color:#333;font-weight:normal">未放款<span class="ui-li-count" style="color:#3c6">${unLendingCount}</span></a></li>
	<li><a href="#page4" data-transition="slide"  pageIndex="4" style="color:#333;font-weight:normal">今日待催办<span class="ui-li-count" style="color:#3c6">${toUrgedCount}</span></a></li>
	</ul>
</div>
<!-- 逾期未交首付客户列表页 -->
<div data-role="page" id="page1"> 
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>未付款客户</h1>
	</div>
	<div id="wrapper1">
		<div id="scroller1">
			<div id="pullDown1">
			</div>   
			<ul id="thelist1" >

			</ul>
			<div id="pullUp1" >
				<span class="pullUpIcon"></span>
				<span class="pullUpLabel"></span> 
			</div>
		</div>
	</div>
</div>
<!--  未签约客户列表页-->
<div  data-role="page" id="page2"> 
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>未签约客户</h1>
	</div>
	<div id="wrapper2">
		<div id="scroller2">
			<div id="pullDown2">
			</div> 
			<ul id="thelist2" >
			</ul>
			<div id="pullUp2">
				<span class="pullUpIcon"></span>
				<span class="pullUpLabel"></span> 
			</div>	
		</div>
	</div>
</div>
<!--  未放款客户列表页-->
<div data-role="page" id="page3">
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>未放款客户</h1>
	</div> 
	<div id="wrapper3">
		<div id="scroller3">
			<div id="pullDown3">
			</div> 
			<ul id="thelist3" >
			</ul>
			<div id="pullUp3">
				 <span class="pullUpIcon"></span>
				 <span class="pullUpLabel"></span> 
			</div>	
		</div>
	</div>
</div>
<!--  今日待催办客户列表页-->
<div data-role="page" id="page4">
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>今日待催办客户</h1>
	</div> 
	<div id="wrapper4">
		<div id="scroller4">
			<div id="pullDown4">
			</div> 
			<ul id="thelist4" >
			</ul>
			<div id="pullUp4">
				 <span class="pullUpIcon"></span>
				 <span class="pullUpLabel"></span> 
			</div>	
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="/WEB-INF/jsp/mbem/mcrm/house/pub/footer.jsp" %>                
               