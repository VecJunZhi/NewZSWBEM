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
</style>
<script type="text/javascript">
	var str1 = "<li name='eachFollowItem' ><div data-id='39d08b6c-e4dd-9de4-8d62-5b1f1bfd7e65' class='a_li'><a onclick=popInfo('";					
	var str2 = "')><dl><dt><span style='color:#333;font-weight:normal'>";                  
	var str3="</span><font style='text-shadow:0 0 0 #000'>";                                
	var str4="</font></dt><dd class='a_li_d1 clearfix'><font style='color:#999;font-weight:normal'>";                                     
	var str5="&nbsp;";         
	var str6 = "</font> <big class='fr'>";
	var str7 = "</big></dd> <dd class='a_li_d2'><span style='color:#999;font-weight:300'>";
	var str8="</span></dd></dl></a><a onclick=callCustom('2','";
	var str9="') class='a_url_tel' data-ac='a_li_aact' href='tel:";
	var str10 = "' oppid='39d08b6c-e4ed-cbd2-eb25-96cce6ff77c2'></a></div></li> ";
	var pageLen = 30;
	var toVisitCount = ${toVisitCount};
	var overDue3DaysCount = ${overDue3DaysCount};
	var overDueCount = ${overDueCount};
	var newAnalysisCount = ${newAnalysisCount};
	
	$(document).ready(function(){
		$("li a").click(function(){
			var page = $(this).attr("pageIndex");
			var count = $(this).children().text();
			if(count == 0 || count == undefined){
				$(this).removeClass("ui-btn-active");
				return false;
			}//没有数量时不进行跳转
			loadUrl = "/mbem/mcrm/house/customer/getXsCustomListByType.action";
			if(page == "1"){
				$("#thelist1").empty();
				pullDownId="pullDown1",pullUpId="pullUp1",wrapperId="wrapper1";
				myScroll1 = loaded();
				listId = "thelist1";
				myScroll = myScroll1;
				pageIndex1 = 1;
				pageIndex = pageIndex1;
				cstType="toVisit";
				cstCount = toVisitCount;
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
				cstType="overDue";
				cstCount = overDueCount;
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
				cstType="overDue3Days";
				cstCount = overDue3DaysCount;
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
				cstType="newAnalysisCount";
				cstCount = newAnalysisCount;
				pullUpAction();
			}	
		});
		
		$("#businessUrged").click(function(){
			window.location.href = "/mbem/mcrm/house/customer/businessUrged.action";
		});
	});
	
	function back() {
		window.location.href = "/mbem/mcrm/house/customer/backlogPage.action";
	}
	
	function joinStr(data,i) {
		str = str1+data[i].xsCst.cstGuid+"','"+data[i].xsOpp.oppGuid+str2+data[i].xsCst.cstName+str3+data[i].xsOpp.status+str4+data[i].xsOpp.lastDate+str5+data[i].xsOpp2Gjjl.gjfs+str6+data[i].xsCst.mobileTel+str7+data[i].xsOpp2Gjjl.gjContent+str8+data[i].xsCst.cstGuid+"','"+data[i].xsOpp.oppGuid+str9+data[i].xsCst.mobileTel+str10;
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
		                    <li class="select" _index="0" name="follow"><div style='text-shadow:0 0 0 #000'>跟进提醒</div></li>
		                    <li name="outdate" _index="1" id="businessUrged"><div>业务催办</div></li>
		                </ul>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
	<ul data-role="listview" data-inset="true">   
	<li><a href="#page1" data-transition="slide"  pageIndex="1" style="color:#333;font-weight:normal">今日待回访<span class="ui-li-count" style="color:#3c6">${toVisitCount}</span></a></li>
	<li><a href="#page2" data-transition="slide"  pageIndex="2" style="color:#333;font-weight:normal">逾期未跟进<span class="ui-li-count" style="color:#3c6">${overDueCount}</span></a></li>
	<li><a href="#page3" data-transition="slide"  pageIndex="3" style="color:#333;font-weight:normal">三日内逾期<span class="ui-li-count" style="color:#3c6">${overDue3DaysCount}</span></a></li>
	<li><a href="#page4" data-transition="slide"  pageIndex="4" style="color:#333;font-weight:normal">新分配客户<span class="ui-li-count" style="color:#3c6">${newAnalysisCount}</span></a></li>
	</ul>
</div>
<!-- 今日待回访列表页 -->
<div data-role="page" id="page1"> 
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>今日待回访客户</h1>
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
<!--  逾期未跟进列表页-->
<div  data-role="page" id="page2"> 
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>逾期未跟进客户</h1>
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
<!--  三日内逾期列表页-->
<div data-role="page" id="page3">
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>三日内逾期客户</h1>
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
<!--  新分配客户列表页-->
<div data-role="page" id="page4">
	<div data-role="header">
	    <a href="#" class="ui-btn ui-corner-all ui-shadow ui-icon-arrow-l ui-btn-icon-left" onclick="back()">返回</a>
	    <h1>新分配客户列表</h1>
	</div> 
	<div id="wrapper4">
		<div id="scroller4">
			<div id="pullDown4"></div>
			<ul id="thelist4" ></ul>
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
               