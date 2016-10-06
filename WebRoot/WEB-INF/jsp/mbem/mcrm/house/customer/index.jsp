<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html><html lang="en-US">
<head>
    <title></title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">   
	<link type="text/css" rel="Stylesheet" href="/mbem/mcrm/business/css/base.min.css">
    <style type="text/css">
		    body{
		        background-color: #f0f0f0;
		    }
		    #header {
		        background-color: #f8f8f8;
		    }
		    #header .head{padding:12px; border-bottom-width:1px;}
		    .easytab{
		        border: 1px solid #34cca6;
		        border-radius: 3px 3px 3px 3px;
		        height: 30px;
		        box-sizing: border-box;
		        display:table;
		        width: 100%;
		    }
		    .easytab li {
		        display: table-cell;
		        width: 33%;
		        height: 100%;
		        vertical-align: middle;
		        text-align: center;
		        font-size: 16px;
		        cursor: pointer;
		        border-right: 1px solid #34cca6;
		        color: #34cca6;
		    }
		    .easytab li:last-child{
		        border-right: none;
		    }
		    .easytab li.selected,.easytab li.active{
		        background: #34cca6;
		        color: white;
		    }
		    .easytab li div {
		        height: 28px;
		        line-height: 28px;
		    }
		    article {
		        top: 55px;
		        bottom: 62px;
		    }
		    .itempanel{
		        background-color: #fff;
		        height:90px;
		        margin: 12px;
		        position: relative;
		    }
		    .itempanel .itembg{
		        width: 65px;
		        height: 90px;
		        margin-left: 20px;
		        float: left;
		        overflow: hidden;
		        background-repeat: no-repeat;
		        background-size: 65px 65px;
		        background-position: center center;
		    }
		    .itempanel.yuqi .itembg{
		        background-image: url(http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/yuqi_bg.png);
		    }
		    .itempanel.yuqi .itemcount{
		        color: #ffc185;
		    }
		    .itempanel.wuxiao .itembg{
		        background-image: url(http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/wuxiao_bg.png);
		    }
		    .itempanel.wuxiao .itemcount{
		        color: #ff8c8c;
		    }
		    .itempanel.all .itembg{
		        background-image: url(http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/all_bg.png);
		    }
		    .itempanel.all .itemcount{
		        color: #57d0af;
		    }
		    .itempanel.yic .itembg{
		        background-image: url(http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/yic.png);
		    }
		    .itempanel.yic .itemcount{
		        color: #18c9c9;
		    }
		    .itempanel .itemtitle{
		        float: left;
		        overflow: hidden;
		        margin-left: 12px;
		        font-size: 17px;
		        height: 90px;
		        line-height: 90px;
		        color: #000;
		    }
		    .itempanel .itemcount{
		        float: right;
		        margin-right: 12px;
		        font-size: 17px;
		        height: 90px;
		        line-height: 90px;
		    }
		    .itempanel.active{
		        background-color: #d9d9d9;
		    }
		    .powered{
		        margin-bottom: 6px;
		        font-size: 11px;
		        color: #aaa;
		        text-align: center;
		        width: 100%;
   		 	}
		    .powered a{
		        color: #aaa;
		        text-decoration: none;
		    }
		    .powered a:hover,.powered a:visited{
		        color: #aaa;
		    }
		    .powered a:active{
		        color: #2caf56;
		    }
		    .toast_overlay{
		        position: absolute;
		        top:0;
		        left:0;
		        z-index:999998;
		        width:100%;
		        height:100%;
    		}
		    .toast{
		        position: absolute;
		        z-index: 999999;
		        top: 50%;
		        left: 50%;
		        width: 210px;
		        margin-top:-30px;
		        margin-left: -105px;
		        text-align: center;
		        display: none;
		        background-color: #111;
		        border-radius: 5px;
		        opacity: .85;
		        padding:20px 12px;
		    }
		    .toast .toasttext{
		        display: block;
		        line-height: 20px;
		        color: #fff;
		        font-size: 15px;
		    }
		    .toast_overlay.show{
		        display: block;
		    }
		    .toast_overlay.hide{
		        display: none;
		    }
		    .toast.show {
		        display: block;
		        -webkit-animation:scaleIn .25s ease-in;
		    }
		    .toast.hide {
		        display: none;
		        -webkit-animation:scaleOut .25s ease-in;
		    }
		    @-webkit-keyframes scaleIn {
		        0% {opacity: 0;-webkit-transform: scale(.5)}
		        100% {opacity: .85;-webkit-transform: scale(1)}
		    }
		    @-webkit-keyframes scaleOut {
		        0% {opacity: .85;-webkit-transform: scale(1)}
		        100% {opacity: 0;-webkit-transform: scale(.5)}
		    }
	</style>
</head>
<body >
<div class="container" id="container">
	<div class="box-wrap" id="box-wrap">
	    <header id="header" class="border-1px">
	        <div id="tabMenu" class="head border-1px">
		    	<ul class="easytab">
			        <li class="selected" data-href="#" data-ac="active">
			            <div>跟进中</div>
			        </li>
			        <li data-href="#" data-ac="active" id="publiccst">
			            <div>公共客户</div>
			        </li>
			        <li data-href="#" data-ac="active" id="duspincst">
			            <div>垃圾箱</div>
			        </li>
		    	</ul>
			</div>
	    </header>
    
		<!--逾期调整-->
	    <article id="scrollArticle">
		    <div id="minpanel">
			        <div class="itempanel yuqi" id="yuqicst">
			            <div class="itembg"></div>
			            <div class="itemtitle">逾期客户</div>
			            <div class="itemcount">${overdueCsNum}人</div>
			        </div>
			        <div class="itempanel wuxiao"  id="wuxiaocst">
			            <div class="itembg"></div>
			            <div class="itemtitle">无效客户</div>
			            <div class="itemcount">${disbinCsNum}人</div>
			        </div>
			        <div class="itempanel all" id="allcst">
			            <div class="itembg"></div>
			            <div class="itemtitle">跟进中客户</div>
			            <div class="itemcount">${followCs}人</div>
			        </div>
			 </div>
		
			<div class="powered" id="powered">
			    Powered&nbsp;by&nbsp;&nbsp;<a href="#">兆盛地产</a>
			</div>    
		</article>
	</div>
</div>

<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
<script type="text/javascript">
  //绑定逾期客户点击事件
  
        $('#yuqicst').bind('click',function(){
       		var _count=$(this).children('div .itemcount').text();
            _count= _count.substring(0,_count.length-1);
           // alert("eddijei "+_count);
           	if(parseInt(_count)==0){
               alert('没有逾期客户可分配!');
               return;
            }
            var url="/mbem/mcrm/house/saleManager/listtype.action";
            window.location.href=url;
        });
         $('#wuxiaocst').bind('click',function(){
       		var _count=$(this).children('div .itemcount').text();
            _count= _count.substring(0,_count.length-1);
          	if(parseInt(_count)==0){
               alert('没有无效客户可分配!');
               return;
            }
            var url="/mbem/mcrm/house/saleManager/invalidCustomType.action";
            window.location.href=url;
        });
         $('#allcst').bind('click',function(){
       		var _count=$(this).children('div .itemcount').text();
            _count= _count.substring(0,_count.length-1);
           // alert("eddijei "+_count);
           	if(parseInt(_count)==0){
               alert('没有跟进中客户可分配!');
               return;
            }
            var url="/mbem/mcrm/house/saleManager/followingCustomerType.action";
            window.location.href=url;
        });
        $('#publiccst').bind('click',function(){
         	var url="/mbem/mcrm/house/saleManager/publicCustomType.action";
            window.location.href=url;
        });
        $('#duspincst').bind('click',function(){
         	var url="/mbem/mcrm/house/saleManager/duspinCustomType.action";
            window.location.href=url;
        });
</script>
</body>
</html>