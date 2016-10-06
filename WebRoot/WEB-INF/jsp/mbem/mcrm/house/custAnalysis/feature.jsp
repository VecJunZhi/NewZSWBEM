<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<title>客户特征分析</title>
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/base.min.css">
<script src='http://www.ichartjs.com/ichart.latest.min.js'></script>
<script src="/common/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="/mbem/mcrm/house/js/FengTab.min.js" type="text/javascript"></script>
<script src="/mbem/mcrm/house/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<style type="text/css"> 
<!-- 
body, div, ul, li { margin:0 auto; padding:0; } 
a:link { color:#00F; text-decoration:none; } 
a:visited { color: #00F; text-decoration:none; } 
a:hover { color: #c00; text-decoration:underline; } 
ul { list-style:none; } 
.main { clear:both; padding:8px; text-align:center; } 
/*第一种形式*/ 
#tabs0 { width:100%; height:auto; overflow:hidden;} 
.menu0 { width: 400px; } 
.menu0 li { 
    display: inline-block;
    font-size: 14px;
    padding: 0 10px;
    width: 33.3%;
    text-align: center;
    box-sizing: border-box;
    -webkit-box-sizing: border-box;

} 
.menu0 li.hover {color: #18C9C9; border-bottom: 2px solid transparent;
    border-bottom-color: #18C9C9; } 
#main0 ul { display: none; } 
#main0 ul.block { display: block; } 
--> 
</style> 
<script> 
<!-- 
/*第一种形式 第二种形式 更换显示样式*/ 
function setTab(m,n){ 
var tli=document.getElementById("menu"+m).getElementsByTagName("li"); 
var mli=document.getElementById("main"+m).getElementsByTagName("ul"); 
for(i=0;i<tli.length;i++){ 
tli[i].className=i==n?"hover":""; 
mli[i].style.display=i==n?"block":"none"; 
} 
}  
function nowtab(m,n){ 
document.getElementById("tip"+m).style.left=n*100+'px'; 
document.getElementById("main2").innerHTML=m3[n]; 
} 
//--> 
</script> 



<script type="text/javascript">
$(function ($) {
	if (window.history && window.history.pushState) {
	$(window).on('popstate', function () {
        var hash = window.location.hash;
        if (hash === '') {
            window.location.href="/mbem/mcrm/house/saleManager/index.action"; 
        }
    });
    var url = window.location.url;
    window.history.pushState('forward', null, url);
    location.hash="#1";
	}
});    	 
	$(document).ready(function(){

		var reportType = $("#reportType").val();
		$(".datelist li").removeClass("selected");
		$(".datelist li").each(function(){
			if($(this).attr("id") == reportType)
				$(this).addClass("selected");
		});
		$(".datelist li").click(function(){
			var id = $(this).attr("id");
			var cstType = $(".datelist input").val();
			//alert(cstType);
			if(id != "range")
				window.location.href="/mbem/mcrm/house/custAnalysis/feature.action?cstType="+cstType+"&reportType="+id;
			else
				window.location.href="/mbem/mcrm/house/custAnalysis/featureSearch.action";
		});
	});
</script>








<script type='text/javascript'>
				
	$(function(){
	  var data = ${mediaPieInfo}
      var chart = iChart.create({
            render:"ichart-render",
            width:440,
            height:440,
            background_color:"#fefefe",
            gradient:false,
            color_factor:0.2,
            border:{
                  color:"BCBCBC",
                  width:0
            },
            align:"center",
            offsetx:-45,
            offsety:0,
            sub_option:{
                  border:{
                        color:"#BCBCBC",
                        width:0
                  },
                  label:{
                        fontweight:500,
                        fontsize:5,
                        color:"#4572a7",
                        sign:"square",
                        sign_size:12,
                        border:{
                              color:"#BCBCBC",
                              width:0
                        },
                        background_color:"#fefefe"
                  }
            },
            shadow:true,
            shadow_color:"#666666",
            shadow_blur:2,
            showpercent:true,
            column_width:"100%",
            bar_height:"100%",
            radius:"70%",
            subtitle:{
                  text:"",
                  color:"#111111",
                  fontsize:16,
                  font:"微软雅黑",
                  textAlign:"center",
                  height:20,
                  offsetx:0,
                  offsety:0
            },
            footnote:{
                  text:"",
                  color:"#111111",
                  fontsize:12,
                  font:"微软雅黑",
                  textAlign:"right",
                  height:20,
                  offsetx:0,
                  offsety:0
            },
            legend:{
                  enable:false,
                  background_color:"#fefefe",
                  color:"#333333",
                  fontsize:12,
                  border:{
                        color:"#BCBCBC",
                        width:1
                  },
                  column:1,
                  align:"right",
                  valign:"center",
                  offsetx:0,
                  offsety:0
            },
            coordinate:{
                  width:"20%",
                  height:"20%",
                  background_color:"#ffffff",
                  axis:{
                        color:"#a5acb8",
                        width:["","","",""]
                  },
                  grid_color:"#d9d9d9",
                  label:{
                        fontweight:500,
                        color:"#666666",
                        fontsize:11
                  }
            },
            label:{
                  fontweight:500,
                  color:"#666666",
                  fontsize:11
            },
            type:"pie2d",
            data:data/* [
                 {name : '广告',value : 40.0,color:'#4572a7'},
				        	{name : '网络',value : 37.1,color:'#aa4643'},
				        	{name : '介绍',value : 13.8,color:'#89a54e'},
				        	{name : '房展',value : 1.6,color:'#80699b'},
				        	{name : '开盘',value : 1.4,color:'#92a8cd'},
				        	{name : '促销',value : 1.2,color:'#db843d'},
				        	{name : '其他',value : 4.9,color:'#a47d7c'}
            ] */
      });
      chart.draw();
});			
		
</script>


<script type='text/javascript'>
				
	$(function(){
	var data = ${agePieInfo};
      var chart = iChart.create({
            render:"ichart-render1",
            width:440,
            height:440,
            background_color:"#fefefe",
            gradient:false,
            color_factor:0.2,
            border:{
                  color:"BCBCBC",
                  width:0
            },
            align:"center",
            offsetx:-45,
            offsety:0,
            sub_option:{
                  border:{
                        color:"#BCBCBC",
                        width:0
                  },
                  label:{
                        fontweight:500,
                        fontsize:5,
                        color:"#4572a7",
                        sign:"square",
                        sign_size:12,
                        border:{
                              color:"#BCBCBC",
                              width:0
                        },
                        background_color:"#fefefe"
                  }
            },
            shadow:true,
            shadow_color:"#666666",
            shadow_blur:2,
            showpercent:true,
            column_width:"100%",
            bar_height:"100%",
            radius:"70%",
            subtitle:{
                  text:"",
                  color:"#111111",
                  fontsize:16,
                  font:"微软雅黑",
                  textAlign:"center",
                  height:20,
                  offsetx:0,
                  offsety:0
            },
            footnote:{
                  text:"",
                  color:"#111111",
                  fontsize:12,
                  font:"微软雅黑",
                  textAlign:"right",
                  height:20,
                  offsetx:0,
                  offsety:0
            },
            legend:{
                  enable:false,
                  background_color:"#fefefe",
                  color:"#333333",
                  fontsize:12,
                  border:{
                        color:"#BCBCBC",
                        width:1
                  },
                  column:1,
                  align:"right",
                  valign:"center",
                  offsetx:0,
                  offsety:0
            },
            coordinate:{
                  width:"20%",
                  height:"20%",
                  background_color:"#ffffff",
                  axis:{
                        color:"#a5acb8",
                        width:["","","",""]
                  },
                  grid_color:"#d9d9d9",
                  label:{
                        fontweight:500,
                        color:"#666666",
                        fontsize:11
                  }
            },
            label:{
                  fontweight:500,
                  color:"#666666",
                  fontsize:11
            },
            type:"pie2d",
            data:data/* [
                 {name : '广告',value : 40.0,color:'#4572a7'},
				        	{name : '网络',value : 37.1,color:'#aa4643'},
				        	{name : '介绍',value : 13.8,color:'#89a54e'},
				        	{name : '房展',value : 1.6,color:'#80699b'},
				        	{name : '开盘',value : 1.4,color:'#92a8cd'},
				        	{name : '促销',value : 1.2,color:'#db843d'},
				        	{name : '其他',value : 4.9,color:'#a47d7c'}
            ] */
      });
      chart.draw();
});			
		
</script>








<script type='text/javascript'>
				
	$(function(){
	  var data = ${workPieInfo};
      var chart = iChart.create({
            render:"ichart-render2",
            width:440,
            height:440,
            background_color:"#fefefe",
            gradient:false,
            color_factor:0.2,
            border:{
                  color:"BCBCBC",
                  width:0
            },
            align:"center",
            offsetx:-45,
            offsety:0,
            sub_option:{
                  border:{
                        color:"#BCBCBC",
                        width:0
                  },
                  label:{
                        fontweight:500,
                        fontsize:5,
                        color:"#4572a7",
                        sign:"square",
                        sign_size:12,
                        border:{
                              color:"#BCBCBC",
                              width:0
                        },
                        background_color:"#fefefe"
                  }
            },
            shadow:true,
            shadow_color:"#666666",
            shadow_blur:2,
            showpercent:true,
            column_width:"100%",
            bar_height:"100%",
            radius:"70%",
            subtitle:{
                  text:"",
                  color:"#111111",
                  fontsize:16,
                  font:"微软雅黑",
                  textAlign:"center",
                  height:20,
                  offsetx:0,
                  offsety:0
            },
            footnote:{
                  text:"",
                  color:"#111111",
                  fontsize:12,
                  font:"微软雅黑",
                  textAlign:"right",
                  height:20,
                  offsetx:0,
                  offsety:0
            },
            legend:{
                  enable:false,
                  background_color:"#fefefe",
                  color:"#333333",
                  fontsize:12,
                  border:{
                        color:"#BCBCBC",
                        width:1
                  },
                  column:1,
                  align:"right",
                  valign:"center",
                  offsetx:0,
                  offsety:0
            },
            coordinate:{
                  width:"20%",
                  height:"20%",
                  background_color:"#ffffff",
                  axis:{
                        color:"#a5acb8",
                        width:["","","",""]
                  },
                  grid_color:"#d9d9d9",
                  label:{
                        fontweight:500,
                        color:"#666666",
                        fontsize:11
                  }
            },
            label:{
                  fontweight:500,
                  color:"#666666",
                  fontsize:11
            },
            type:"pie2d",
            data:data/* [
                 {name : '广告',value : 40.0,color:'#4572a7'},
				        	{name : '网络',value : 37.1,color:'#aa4643'},
				        	{name : '介绍',value : 13.8,color:'#89a54e'},
				        	{name : '房展',value : 1.6,color:'#80699b'},
				        	{name : '开盘',value : 1.4,color:'#92a8cd'},
				        	{name : '促销',value : 1.2,color:'#db843d'},
				        	{name : '其他',value : 4.9,color:'#a47d7c'}
            ] */
      });
      chart.draw();
});			
		
</script>



















<style>
li.vl-item { 
float: left; 
width: 100%; 
} 
.app-vote ul, .app-vote ol, .app-vote li, .app-vote dl, .app-vote dt, .app-vote dd, .app-vote form, .app-vote p, .app-vote h1, .app-vote h2, .app-vote h3 { 
margin: 0; 
padding: 0; 
} 
.app-vote em { 
font-style: normal; 
} 
.app-vote ul, .app-vote ol { 
list-style: none outside none; 
} 
.vote-box-list { 
float: left; 
width: 100%;
background-color: #FFF;
padding-bottom:20px;
} 
#appVote .vote-action { 
margin-top: 30px; 
width: 60px; 
} 
.vote-box-list li { 
list-style: none outside none; 
} 
.vote-box-list li .vote-item-wrap { 
padding: 5px 0; 
} 
.vote-box-list li.over { 
background-color: #FFE57F; 
} 
.vote-box-list li h4 { 
font-size: 1em; 
font-weight: normal; 
overflow: hidden; 
text-align: right; 
width: 90px; 
word-wrap: break-word; 
} 
.vote-box-list li .litem { 
background: none repeat scroll 0 0 #EFEFEF; 
height: 14px; 
width: 60%; 
} 
.vote-box-list li p, .vote-box-list li h4 { 
float: left; 
margin: 0; 
padding: 0; 
} 
.vote-box-list li p input { 
float: left; 
margin: 0; 
} 
.vote-box-list li em, .vote-box-list li span { 
float: left; 
height: 14px; 
overflow: hidden; 
} 
.vote-box-list li .vleft, .vote-box-list li .right { 
width: 2px; 
} 
.vnum { 
text-indent: 5px; 
width: 50px; 
} 
.wsj{width: 50px; text-height:40px; margin-left:auto; margin-right:auto; margin-top: 20px;height: 50%;}
ul.vote-ctrl-act-sep { 
margin: 5px 0; 
overflow: hidden; 
} 
ul.vote-ctrl-act-sep li { 
border-bottom: medium none; 
border-right: 1px solid #000000; 
float: right; 
margin-right: -1px; 
padding: 0 10px; 
} 
#appVoteAddForm dt { 
clear: left; 
text-align: right; 
width: 150px; 
} 
#appVoteAddForm .txt { 
width: 400px; 
} 
#appVoteAddForm .vote { 
height: 300px; 
width: 400px; 
} 
#appVoteAddForm .tip { 
color: #6B6B6B; 
} 
#appVoteAddForm .back_block { 
margin: 6px 0; 
width: 550px; 
} 
#text_vote_area p.m { 
color: #355E9D; 
} 
#text_vote_area p.t { 
color: #999999; 
} 

</style>	
				
		

    <style>
        body{background-color:#F7F7F7;}
        .body{color:#666;}
        .datelist{list-style:none;padding:0 12px;}
        .datelist li{float:left;font-size:16px;width:20%;box-sizing:border-box;-webkit-box-sizing:border-box;height:48px;padding:9px 0px;text-align:center;}
        .datelist li.selected{}
        .datelist li span{height:30px;line-height:30px}
        .datelist li.selected span,.datelist li.active span{background-color:#34CCA6;display:block;border-radius: 15px 15px;color:#fff;}
        .phone-agent{padding:0 14px;color:#fff;font-size:16px;height:75px;line-height:75px;background:#33C7B6;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#34cca6), color-stop(100%,#33c3c4)); /* Chrome,Safari4+ */}
        .phone-agent .text{float:left}
        .phone-agent .num{float:right;font-size:20px;padding-right:18px;background: url(/Public/myui_v1/images/index_left.png) no-repeat 96% 30px;background-size: 9px 14px;-webkit-background-size: 9px 14px;}
        .type-menu{height:45px;font-size:0;line-height:48px;padding:0 20px;position:relative;border-bottom-width: 1px;}
        .type-menu li{display:inline-block;font-size:14px;padding:0 10px;width:33.3%;text-align:center;box-sizing:border-box;-webkit-box-sizing: border-box;}
		 .type-menu li.hover {color: #18C9C9; border-bottom: 2px solid transparent;
    border-bottom-color: #18C9C9; height:46px;} 
        .type-menu .left-btn,.type-menu .right-btn{position:absolute;height:48px;top:0;line-height:48px;width:28px;color:#fff;background-size: 9px 10px;-webkit-background-size:9px  14px;background-repeat:no-repeat;background-position:center center;background-color:#f7f7f7;}
        .type-menu .left-btn{left:0px;background-image:url(images/handover_left_default.png)}
        .type-menu .right-btn{right:0px;background-image:url(images/handover_right_default.png)}
		.type-menu .left-btn.active{background-image:url(images/handover_left_focus.png)}
		.type-menu .right-btn.active{background-image:url(images/handover_right_focus.png)}
        .type-menu ul{height:48px;overflow:hidden;white-space:nowrap;display: inline-block;}
        .type-menu li.selected{color:#18C9C9;}
        .type-menu li.selected span{display:block;height:44px;border-bottom:2px solid transparent;border-bottom-color:#18C9C9;}
		.type-menu.hidearrow{padding:0 12px;}
		.type-menu.hidearrow .left-btn,.type-menu.hidearrow .right-btn{display:none;}
        .divdatalist li{height:32px;}
        .divdatalist li{position:relative;padding-left:70px;padding-right:84px;}
        .divdatalist li .name{position:absolute;left:0;top:0;width:60px;text-align:right;line-height:14px;}
        .divdatalist li .num{position:absolute;right:0;top:0;width:74px;text-align:left;}
        .divdatalist li .bgline{display:inline-block;width:70%;height:12px;border-radius:6px 6px;background-color:#E66F4F;}
		.canvas-wrap{min-width:318px;margin:0 auto;min-height:260px;background:url(/Public/myui_v1/images/indicator_mozilla_blu.gif) center center no-repeat;background-size: 16px 16px;-webkit-background-size:16px 16px;}
		.scroll-wrap{height:48px;overflow:hidden;}
		.canvas-wrap p{line-height:318px;background-color:#f7f7f7;text-align:center;}
		.trend-date{height:30px;line-height:31px;padding-left:12px;position:relative;background-color:#959595;color:#fff;font-size:14px;}
		.trend-date:after{content:"";position:absolute;top:-14px;right:10%;width:0;border-width:8px 10px;border-style:solid;border-color:transparent transparent #959595 transparent;}	
	</style>
  </head>
  <body>
    <body>
	<input id="inp_cst_type" type="hidden" value="visitor">
    <div class="datemenu">
        <ul class="datelist cls">
          	            <li id="total" data-ac="active" range="all"><span>累计</span></li>
                        <li id="week" data-ac="active"  range="week"><span>本周</span></li>
                        <li id="month" data-ac="active" range="month"><span>本月</span></li>
                        <li id="year" data-ac="active" range="year"><span>本年</span></li>
                        <li id="range" data-ac="active" range="other"><span>自定义</span></li>
                        <input type="hidden" value="${cstType}">
                        <input id="reportType" type="hidden" value="${reportType}">
                    </ul>
		    </div>
    <div class="phone-agent">
        <span class="text">${cstTypeName}</span>     
        <span class="num click_href">${cstCount}</span>
    </div>
    
    <div id="tabs0"> 
    
        <div class="type-menu border-1px hidearrow">
            <div class="scroll-wrap" id="scroll-wrap" style="overflow: hidden;">
                <ul style="transition: transform 0ms cubic-bezier(0.33, 0.66, 0.66, 1); -webkit-transition: transform 0ms cubic-bezier(0.33, 0.66, 0.66, 1); transform-origin: 0px 0px 0px; transform: translate(0px, 0px) translateZ(0px); width:100%"  id="menu0">
	        			        <li onclick="setTab(0,0)" class="hover">认知途径</li>
		        		        <li  onclick="setTab(0,1)">年龄段</li>
		        		        <li  onclick="setTab(0,2)">工作行业</li>
		                        </ul>
            </div>
            <div data-ac="active" class="left-btn"></div>
            <div data-ac="active" class="right-btn"></div>
        </div>
       
       
       
        
        <div class="canvas-wrap"  id="main0">

<ul class="block">
 <div id='ichart-render'></div>
 
 <div class="vote-box-list clearfix"> 
 <c:if test="${mediaBarList==null}">
	<div class="wsj"><h3>暂无数据</h3></div>
</c:if>
<c:forEach items="${mediaBarList}" var="media" varStatus="status">
	<li class="vl-item" > 
	<div class="vote-item-wrap"> 
	<h4>${media.name}：</h4> 
	<p class="litem"><span style="width:${media.width}; background-color:${media.color}"></span></em> 
	</p><span class=vnum>${media.value }</span> 
	</div> 
	</li>
	</c:forEach>
</div> 
</ul>



<ul>
 <div id='ichart-render1'></div>
<div class="vote-box-list clearfix">
	<c:if test="${mediaBarList==null}">
	<div  class="wsj"><h3>暂无数据</h3></div>
</c:if> 
	<c:forEach items="${ageBarList}" var="age" varStatus="status">
	<li class="vl-item" > 
	<div class="vote-item-wrap"> 
	<h4>${age.name}：</h4> 
	<p class="litem"><span style="width:${age.width}; background-color:${age.color}"></span><em class=vright></em> 
	</p><span class=vnum>${age.value }</span> 
	</div> 
	</li>
	</c:forEach>
</div> 
</ul>


<ul>
 <div id='ichart-render2'></div>
 <div class="vote-box-list clearfix"> 
 <c:if test="${mediaBarList==null}">
	<div class="wsj"><h3>暂无数据</h3></div>
</c:if>
<c:forEach items="${workBarList}" var="work" varStatus="status">
	<li class="vl-item" > 
	<div class="vote-item-wrap"> 
	<h4>${work.name}：</h4> 
	<p class="litem"><span style="width:${work.width}; background-color:${work.color}"></span><em class=vright></em> 
	</p><span class=vnum>${work.value }</span> 
	</div> 
	</li>
	</c:forEach>
</div> 
</ul>
<div style="clear:both"></div>
  
        </div>
       
      
        
 
</div>

<script type="text/javascript" src="/mbem/mcrm/house/js/base.min.js"></script>
<!-- 自定义时间控件 -->
<style>
    .twotimehide{display:none!important;}
    .time-wrap{position:relative;z-index: 1001;display: none;width:100%;overflow:hidden;}
    .time-wrap ul{background-color:#fff;margin-top:12px;}
    .time-wrap li{height:44px;line-height:44px;margin:0 12px;color:#333;position:relative;padding-left:82px;background: url(/Public/myui_v1/images/handover_right_default.png) no-repeat 98% center;background-size:9px 14px;-webkit-background-size:9px 14px;}
    .time-wrap li.active{background-color:#d9d9d9;}
    .time-wrap li.border-1px{border-bottom-width: 1px;}
    .time-wrap li .k{position:absolute;left:0;top:0;font-size: 16px;}
    .time-wrap li .v{width:100%;display:block;}
    .time-wrap li input{width: 100%;border:0;-webkit-appearance: none;font-size:16px;line-height: 42px;
        background: transparent;color:#666;}
    .time-wrap .btns{margin-top: 30px;padding:0 12px;text-align:center}
    .time-wrap .btns span{display:inline-block;height: 40px;line-height:40px;width:45%;border-radius:5px;font-size: 16px;text-align:center;overflow:hidden;box-sizing: border-box;vertical-align: middle;}
    .time-wrap .cancel{margin-right:2%;color:#34CCA6;border:1px solid #34CCA6;}
    .time-wrap .cancel.active{background-color:#34CCA6;color:#fff;border:none;}
    .time-wrap .sure{background-color:#34CCA6;color:#fff;border-radius:5px;}
    .time-wrap .sure.active{background-color:#33A588;}
    .time-panel{position:fixed;top:0;bottom:0;background-color:#F7F7F7;z-index:1000;width:100%;left:0;}
</style>
<div class="time-wrap" id="time-wrap">
    <ul>
        <li data-ac="active" class="border-1px"><span class="k">开始时间：</span><span class="v"><input class="btime" data-range="2010/1/1-2018/12/30" type="text" readonly id="mobiscroll1450775031276"></span></li>
        <li data-ac="active"><span class="k">结束时间：</span><span class="v"><input class="etime" data-range="2010/1/1-2018/12/30" type="text" readonly id="mobiscroll1450775031277"></span></li>
    </ul>
    <div class="btns">
        <span data-ac="active" class="cancel">取消</span>
        <span data-ac="active" class="sure">确定</span>
    </div>
</div>
<!--<style>
    /*弹出框效果*/
    #mysoft_popup_mask{
        background-color: #222;
        display: none;
        position: fixed;
        z-index: 999998;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        opacity: 0.3;
        height:100%;
        width:100%;
    }
    #mysoft_popup{
        color: #222;
        -webkit-box-shadow: 0px 1px 10px rgba(0, 0, 0, 0.6);
        display: none;
        position: fixed;
        left: 0;
        right: 0;
        z-index: 999999;
        min-height: 50px;
    }
    #mysoft_popup.loading {
        top: 50%;
        left: 50%;
        margin: -50px 0 0 -62px;
        opacity: .78;
        text-align: center;
        width: 125px;
        height: 100px;
        border-radius: 5px;
        background-color: #000;
    }
    #mysoft_popup.loading .img {
        border: none;
        margin-top:22px;
        width: 100%;
        height: 35px;
        background-image: url(/Public/dest/popup/images/loading35@35.gif);
        background-size: 35px;
        background-position: center center;
        background-repeat: no-repeat;
    }
    #mysoft_popup.alert,#mysoft_popup.confirm {
        top: 50%;
        left: 5%;
        right: 5%;
        border-radius:5px;
        background-color: #fff;
    }
    #tag_close_popup{
        color: #BDC3C7;
        position: absolute;
        top: 0;
        right: 0;
        font-size: 1em;
        padding: 5px 5px 10px 10px;
    }
    #mysoft_popup.loading p{
        font-size:17px;
        color: #fff;
        padding-top:9px;
        margin: 0px;
    }
    #mysoft_popup.loading i.icon{
        color: #fff;
        font-size: 4em;
        line-height: 110px;
        margin: 0;
        display: inline-block;
        -webkit-animation: spinner .5s infinite linear;
    }
    .popup-title {
        background:#fff;
        color: #000;
        text-align:center;
        padding: 14px;
        font-size:16px;
        font-weight:bold;
        border-radius:5px;
    }
    .popup-content {
        text-align:center;
        padding: 0 14px 10px 14px;
        line-height: 1.2em;
        font-size:14px;
        border-radius:5px;
        color:#000;
    }
    #popup_btn_container {
        text-align: center;
        margin-top: 10px;
        display: -webkit-box;
        display: box;
    }
    #popup_btn_container > a {
        font-size: 16px;
        -webkit-box-flex: 1;
        box-flex: 1;
        padding: 12px 10px;
        display: block;
        color: #0c7bf7;
        border: 1px solid #d9d9d9;
        border-left: none;
        border-bottom: none;
        border-right:0;
    }
    #popup_btn_container > a:active{
        background-color: #d9d9d9;
    }
    #popup_btn_container > a:first-child {
        border-right: 1px solid #d9d9d9;
        border-bottom-left-radius: 5px;
    }
    #popup_btn_container > a:last-child {
        border-bottom-right-radius: 5px;
    }
</style>

-->
</body></html>
