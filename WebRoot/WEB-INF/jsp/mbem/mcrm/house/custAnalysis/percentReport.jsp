<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.min.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
  <title></title>    
 <script  src="/common/js/jquery-1.9.1.min.js"></script>
  <script>
    $(document).ready(function(){
    /* 		var url = window.location.pathname;
    		history.pushState({},'',url);//向历史记录里添加一条信息，其实在这里的url可以随便填，主要是用了下面的事件跳转
		  	window.onpopstate = function () { //监听浏览器事件
			location.href = '/mbem/mcrm/house/saleManager/index.action';
			}  */ 
    	});
    </script>
    
    <script>
    var statistics={}
    statistics.start=+new Date();
</script>
    
<style>
    body{background:#f0f0f0;-webkit-overflow-scrolling:touch;}
    header{background:#fff;width:100%;height:42px;}
    .header{padding:12px 12px 0 12px;}
    header ul{display: -webkit-box;width:100%;border:1px solid #34CCA6;border-radius:5px;height:28px;line-height:28px;}
    header ul li{ -webkit-box-flex:1;height: 100%;vertical-align: middle;text-align: center;
        font-size: 16px;cursor: pointer;border-right: 1px solid #33cc66;color: #34CCA6;}
    header ul li a{display:block;color:#34CCA6;text-decoration:none;}
    header ul li:last-child{border:0;}
    header ul li.a_act{background:#34CCA6;color:#fff;}
    header ul li.a_act a{background:#34CCA6;color:#fff;}
    .nav{height:44px;}
    nav{background:#fff;position:static;top:54px;width:100%;height:44px;z-index:10;}
    nav ol{padding:9px 0 8px;margin:0 12px;font-size:0}
    nav ol li{display:inline-block;width:20%;font-size:14px;color:#aaa;height:16px;line-height:16px;
        padding:6px 0;text-align:center;}
    nav ol li a{display:block;color:#666;text-decoration:none;}
    nav ol li.a_act{background:#34CCA6;color:#fff;border-radius:30px;}
    nav ol li.a_act a{color:#fff;text-decoration:none;}
    figure{background:#f8f8fe;margin:0;border-top:1px solid #ddd;border-bottom:1px solid #ddd;}
    figure .a_li{width:60px;font-size: 12px;}
    figure .a_li a{display:block;width:100%;height:100%;padding:8px 0;border-bottom:1px dotted #ddd;}
    figure .a_li a div{border-right:1px dotted #ddd;padding:7px 15px 7px 0;text-align:right;}
    center dt{background:url("/Apps/Sales/View/default/Public/images/arrow-down-grey.png") no-repeat 96% center;
        background-size:15px;text-align:left;overflow:hidden;padding:14px 12px;border-bottom:1px solid #ddd;}
    center dt.on{background:url("/Apps/Sales/View/default/Public/images/arrow-up-grey.png") no-repeat 96% center;
        background-size:15px;text-align:left;overflow:hidden;padding:14px 12px;border-bottom:1px solid #ddd;}
    center dt + dd{display:none;overflow:hidden;}
    center dt.on + dd{display:block;}
    center dt t{background:red;display:inline-block;width:16px;height:16px;float:left;border-radius:50%;}
    center dt span{display:inline-block;line-height:16px;float:left;margin-left:0px;font-size:17px;}
    .newDt{background:white url("/Apps/Sales/View/default/Public/images/arrow-down-grey.png") no-repeat 96% center;
        background-size:15px;text-align:left;overflow:hidden;padding:14px 12px;border-bottom:1px solid #ddd;}
    dt.on{background:url("/Apps/Sales/View/default/Public/images/arrow-up-grey.png") no-repeat 96% center;
        background-size:15px;text-align:left;overflow:hidden;padding:14px 12px;border-bottom:1px solid #ddd;}
    .newDt + dd{display:none;overflow:hidden;}
    .newDt.on + dd{display:block;}
    .newDt t{background:red;display:inline-block;width:16px;height:16px;float:left;border-radius:50%;}
    .newDt span{display:inline-block;line-height:16px;float:left;margin-left:0px;font-size:17px;}
    center dl:nth-child(1n) t{background:#ffcfbf;}
    center dl:nth-child(2n) t{background:#d6c8ff;}
    center dl:nth-child(3n) t{background:#8de9cb;}
    center dl:nth-child(4n) t{background:#99e5ff;}
    center dl:nth-child(5n) t{background:#faafcf;}
    center dl:nth-child(6n) t{background:#ffe599;}
    center p.a_tit{padding:10px 12px;font-size:14px;color:#333;border-bottom:1px solid #ddd;}
    center p.a_tit span{float:left;width:21%; font-size: 12px;letter-spacing: -1px;}
    center p.a_tit span:first-child{text-align:left;width: 13%;}
    center p.a_tit span:last-child{text-align:right;}
    .newPp{padding:10px 12px;font-size:12px;color:#333;border-bottom:1px solid #ddd;}
    .newPp span{float:left;width:21%;letter-spacing: -1px;}
    .newPp span:first-child{text-align:left;width: 15%;}
    .newPp span:last-child{text-align:right;}
    center dl{background:#fff;margin-bottom:5px;}
    .a_count_lists i,.a_count_lists b{font-style:normal;font-weight:normal;}
    .a_count_lists li a{display:block;text-decoration:none;font-size:12px;color:#000;padding-left:12px;}
    .ac_div{padding:5px 0 5px 0px;border-bottom:1px solid #ddd;padding-bottom:5px;}
    .a_count_lists li a:last-child{border:0;}
    .a_count_lists li a i{display:block;height:18px;font-size:14px;padding:6px 0 10px;width:100%;overflow:hidden;
    white-space:nowrap;text-overflow:ellipsis;}
    .a_count_lists ul li a i{color:#55ce80;}
    .a_count_lists li a font{font-size:16px;color:#333;}
    .a_count_lists li a p{float:left;width:20%;}
    .a_count_lists li a p:first-child{text-align:left;width: 15%;}
    .a_count_lists li a p:nth-child(2){padding-right:12px;box-sizing:border-box;}
    .a_count_lists li a p:last-child{text-align:right;padding-right:12px;box-sizing:border-box;}
    .a_count_lists li a p:nth-child(n+2){text-align:right;}
    .a_count_lists li a p:nth-child(3){padding-right:12px;box-sizing:border-box;}
    .a_count_lists li a p:nth-child(4){padding-right:12px;box-sizing:border-box;}
    .a_count_lists li a.act_a{background:#ddd;}
    .a_rela{position:relative;}
    .pie { width:56px; height:56px; background-color:#efefef; border-radius:50%; position:absolute; }
    .pie1 { clip:rect(0px,auto,auto,28px);-webkit-transform:rotate(0deg);}
    .pie2 { clip:rect(0px,28px,auto,0px);-webkit-transform:rotate(0deg);}
    .hold { width:56px; height:56px; position:absolute; z-index:1; }
    .hold1 { clip:rect(0px,auto,auto,28px); }
    .hold2 { clip:rect(0px,28px,auto,0px); }
    .bg { width:56px; height:56px; background-color:#ffac9d; border-radius:50%; position:absolute; }
    .pie:after{position: absolute;  left: 0px;  top:0px;  display: block;  content: "";  height: 54px;  width: 54px;  border: 1px solid #efefef;border-radius: 32px;}
    .holdtext { width:40px; height:40px; margin:8px 0 0 8px; background-color:#fff; border-radius:25px; position:absolute; z-index:1; text-align:center;font-size: 11px; }
    .holdtext .holdvalue{display:block;font-size:14px;margin-top:13px;line-height:16px;color: #ffac9d;margin-right: 10px;}
    .holdtext .holdvalue span{font-size: 12px; position: absolute; margin-top: 2px;}
    .holdtext .holdtitle{color: #999}
    .textpanel {font-size: 12px;color: #333;bottom: 0px;position: absolute;width: 100%;text-align: center;letter-spacing: -1px;}
    .a_li:nth-child(1) .holdvalue{color:#68d9bd;}
    .a_li:nth-child(2) .holdvalue{color:#88e0f0;}
    .a_li:nth-child(3) .holdvalue{color:#b99ff4;}
    .a_li:nth-child(4) .holdvalue{color:#fcc450;}
    .a_li:nth-child(1) .bg{background:#68d9bd;}
    .a_li:nth-child(2) .bg{background:#88e0f0;}
    .a_li:nth-child(3) .bg{background:#b99ff4;}
    .a_li:nth-child(4) .bg{background:#fcc450;}
    .a_loading{margin:0 auto;min-height:60px;position:absolute;top:0;left:0;right:0;z-index:999;
        background:url(/Public/myui_v1/images/indicator_mozilla_blu.gif) center center no-repeat;}
    .a_shadow{position:fixed;top:0;left:0;right:0;bottom:0;z-index:99;}
    .a_user_name{text-align:left;padding-top:11px;}
    .a_user_name span{color:#55ce80;display:inline-block;height:18px;font-size:16px;padding:0px;max-width:70%;overflow:hidden;
        text-overflow:ellipsis;white-space:nowrap;text-overflow:ellipsis;}
    .a_count_lists li a .a_user_name i{display:inline-block;width:30%;padding:0;}
    .a_custom_time{padding-left:12px;position:relative;background-color:#E8E8E8;font-size:14px;}
    .a_custom_time:after{content:"";position:absolute;top:-14px;right:10%;width:0;border-width:8px 10px;border-style:solid;border-color:transparent transparent #e8e8e8 transparent;}
    .nav-detail{height:38px;line-height:38px;border-top-width:1px;background-color:#E8E8E8;font-size:14px;padding-left:16px;}
    .nav-detail .sep{display:inline-block;width:24px;height:24px;vertical-align:0px;line-height:24px;text-align:center;border-radius:12px;background-color: #666;color:#fff;font-size:12px;margin: 0 10px;}

</style></head>

<body>
<header>
    <div class="header">
        <ul>
            <li>
                <a href="javascript:void(0);" data-url="/Sales/SaleFollowAnalysis/followReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=today">跟进分析</a>
            </li>
            <li class="a_act">
                <a href="javascript:void(0);" data-url="/Sales/SaleFollowAnalysis/percentReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557">
                    转化率分析
                </a>
            </li>
            <li>
                <a href="javascript:void(0);" data-url="/Sales/SaleFollowAnalysis/tradeReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=today">
                    成交分析
                </a>
            </li>        </ul>
    </div>
</header>
<div class="nav">
    <nav style="position: static; top: 0px; left: 0px; right: 0px; border: 0px; z-index: 9; background-color: white;">
        <ol class="datelist">
            <li class="a_act"><a href="javascript:void(0);" data-url="/Sales/SaleFollowAnalysis/percentReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=">累计</a></li>
            <li><a href="javascript:void(0);" data-url="/Sales/SaleFollowAnalysis/percentReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=week">本周</a></li>
            <li><a href="javascript:void(0);" data-url="/Sales/SaleFollowAnalysis/percentReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=month">本月</a></li>
            <li><a href="javascript:void(0);" data-url="/Sales/SaleFollowAnalysis/percentReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=year">本年</a></li>
            <li range="other" id="a_default">
                        <a href="javascript:void(0);">自定义</a>
                    </li>
        </ol>
    </nav>
</div>
<article>
    <figure class="clearfix" style="padding:34px 0px 24px;">
        <div class="a_li a_rela fl" style="height: 90px; margin-left: 91.4px;">
            <div class="hold hold1">
                <div class="pie pie1" style="-webkit-transform: rotate(93.6deg);"></div>
            </div>
            <div class="hold hold2">
                <div class="pie pie2" style="-webkit-transform: rotate(0deg);"></div>
            </div>
            <div class="bg"></div>
            <div class="holdtext">
                <div class="holdvalue"><font>26</font><span>%</span></div>
            </div>
            <div class="textpanel">
                <span class="title">电转访</span>
            </div>
        </div>
        <div class="a_li a_rela fl" style="height: 90px; margin-left: 91.4px;">
            <div class="hold hold1">
                <div class="pie pie1" style="-webkit-transform: rotate(0deg);"></div>
            </div>
            <div class="hold hold2">
                <div class="pie pie2" style="-webkit-transform: rotate(0deg);"></div>
            </div>
            <div class="bg"></div>
            <div class="holdtext">
                <div class="holdvalue"><font>0</font><span>%</span></div>
            </div>
            <div class="textpanel">
                <span class="title">来访转认筹</span>
            </div>
        </div>
        <div class="a_li a_rela fl" style="height: 90px; margin-left: 91.4px;">
            <div class="hold hold1">
                <div class="pie pie1" style="-webkit-transform: rotate(79.2deg);"></div>
            </div>
            <div class="hold hold2">
                <div class="pie pie2" style="-webkit-transform: rotate(0deg);"></div>
            </div>
            <div class="bg"></div>
            <div class="holdtext">
                <div class="holdvalue"><font>22</font><span>%</span></div>
            </div>
            <div class="textpanel">
                <span class="title">来访转认购</span>
            </div>
        </div>
        <div class="a_li a_rela fl" style="height: 90px; margin-left: 91.4px;">
            <div class="hold hold1">
                <div class="pie pie1" style="-webkit-transform: rotate(118.8deg);"></div>
            </div>
            <div class="hold hold2">
                <div class="pie pie2" style="-webkit-transform: rotate(0deg);"></div>
            </div>
            <div class="bg"></div>
            <div class="holdtext">
                <div class="holdvalue"><font>33</font><span>%</span></div>
            </div>
            <div class="textpanel">
                <span class="title">认筹转认购</span>
            </div>
        </div>
        <div class="a_li a_rela fl" style="height: 90px; margin-left: 91.4px;">
            <div class="hold hold1">
                <div class="pie pie1" style="-webkit-transform: rotate(108deg);"></div>
            </div>
            <div class="hold hold2">
                <div class="pie pie2" style="-webkit-transform: rotate(0deg);"></div>
            </div>
            <div class="bg"></div>
            <div class="holdtext">
                <div class="holdvalue"><font>30</font><span>%</span></div>
            </div>
            <div class="textpanel">
                <span class="title">认购转签约</span>
            </div>
        </div>
    </figure>
</article>
<article>
    <center>
        <dl data-teamid="39D038E2-F465-533E-A77F-08360EF52720">
                <dt class="on" d="1" style="position: static;">
                <p class="clearfix down">
                    <!--<t></t>-->
                    <span>销售A组</span>
                </p>
                </dt>
            <dd>                <p class="a_tit clearfix" style="position: static;">                <span>电转访</span>                <span>来访转认筹</span>                <span>来访转认购</span>                <span>认筹转认购</span>                <span>认购转签约</span>        </p>        <div class="a_count_lists">                <ol>                <li>                <a class="clearfix" data-ac="act_a" href="javascript:void(0);">                <div class="ac_div clearfix">                <p>                <i>平均</i>                <b>                <font>26</font> %        </b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>18</font> %</b>        </p>                <p>                <i></i>                <b><font>40</font> %</b>        </p>                <p>                <i></i>                <b><font>47</font> %</b>        </p>        </div>        </a>        </li>        </ol>        <ul><li><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C3E-72FE-6A23-13CE-CBDB74352392"><div class="a_user_name clearfix"><span>王强</span></div><div class="ac_div clearfix"><p><b><font>32</font> %</b></p><p><b><font>2</font> %</b></p><p><b><font>27</font> %</b></p><p><b><font>33</font> %</b></p><p><b><font>21</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C3E-F149-0F90-26AF-6F4957381B7D"><div class="a_user_name clearfix"><span>王帅</span></div><div class="ac_div clearfix"><p><b><font>30</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>30</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>37</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C44-43D9-D1FB-AB6A-F31A76A16AEE"><div class="a_user_name clearfix"><span>孙朋飞</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>100</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C3F-C052-7443-74F1-F38B0A86FBC4"><div class="a_user_name clearfix"><span>李军</span></div><div class="ac_div clearfix"><p><b><font>40</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>29</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>65</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C42-B133-824B-4987-BBD59FEA308B"><div class="a_user_name clearfix"><span>王霄</span></div><div class="ac_div clearfix"><p><b><font>28</font> %</b></p><p><b><font>1</font> %</b></p><p><b><font>30</font> %</b></p><p><b><font>100</font> %</b></p><p><b><font>61</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D1505F-E7AF-2A49-04BA-43CDF8CDFF7D"><div class="a_user_name clearfix"><span>袁博玮</span></div><div class="ac_div clearfix"><p><b><font>44</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>18</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>33</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D26748-465D-7D51-1BB0-425AC9AC4900"><div class="a_user_name clearfix"><span>申海明</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D2C3EE-2A0D-8C21-97F1-A55230E5B939"><div class="a_user_name clearfix"><span>张鹏波</span></div><div class="ac_div clearfix"><p><b><font>25</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>11</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>77</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D3BE76-B144-85CF-0DC4-E73D99F52FBD"><div class="a_user_name clearfix"><span>焦龙</span></div><div class="ac_div clearfix"><p><b><font>19</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>14</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>52</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D3C394-EF63-DB0F-0093-63DDE8FBA1F6"><div class="a_user_name clearfix"><span>李鑫</span></div><div class="ac_div clearfix"><p><b><font>17</font> %</b></p><p><b><font>1</font> %</b></p><p><b><font>2</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>33</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D40B8A-8524-4A0B-76C1-B1E901D066F8"><div class="a_user_name clearfix"><span>张培龙</span></div><div class="ac_div clearfix"><p><b><font>18</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>4</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>40</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D41AC0-AA8C-65CC-0ACF-65B979C7BB74"><div class="a_user_name clearfix"><span>王金鑫</span></div><div class="ac_div clearfix"><p><b><font>16</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>2</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>33</font> %</b></p></div></a></li></ul>        </div>        </dd></dl><dl data-teamid="39D03949-6F9F-A758-8DCF-CD2B316CB291">
                <dt class="on" d="1" style="position: static;">
                <p class="clearfix down">
                    <!--<t></t>-->
                    <span>销售B组</span>
                </p>
                </dt>
            <dd>                <p class="a_tit clearfix" style="position: static;">                <span>电转访</span>                <span>来访转认筹</span>                <span>来访转认购</span>                <span>认筹转认购</span>                <span>认购转签约</span>        </p>        <div class="a_count_lists">                <ol>                <li>                <a class="clearfix" data-ac="act_a" href="javascript:void(0);">                <div class="ac_div clearfix">                <p>                <i>平均</i>                <b>                <font>36</font> %        </b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>28</font> %</b>        </p>                <p>                <i></i>                <b><font>50</font> %</b>        </p>                <p>                <i></i>                <b><font>47</font> %</b>        </p>        </div>        </a>        </li>        </ol>        <ul><li><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C3F-5489-E69D-7DD5-0FB3E4ED73C2"><div class="a_user_name clearfix"><span>陈慧明</span></div><div class="ac_div clearfix"><p><b><font>36</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>29</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>43</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C45-286A-5D86-5B63-3CE8D39C3129"><div class="a_user_name clearfix"><span>韩涛</span></div><div class="ac_div clearfix"><p><b><font>46</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>40</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>64</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C42-1192-B1C4-DFEF-25CF543D8194"><div class="a_user_name clearfix"><span>宋亚峰</span></div><div class="ac_div clearfix"><p><b><font>50</font> %</b></p><p><b><font>1</font> %</b></p><p><b><font>36</font> %</b></p><p><b><font>50</font> %</b></p><p><b><font>44</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D057E3-F92D-B35C-767E-D549B72BCF62"><div class="a_user_name clearfix"><span>王红军</span></div><div class="ac_div clearfix"><p><b><font>11</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>97</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>50</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D0AFDE-E1F0-957E-B3D0-A2F4F1DE6E28"><div class="a_user_name clearfix"><span>杨少奇</span></div><div class="ac_div clearfix"><p><b><font>50</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>100</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>26</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D15060-4E28-8605-90A3-78E28346AF6B"><div class="a_user_name clearfix"><span>张雅竹</span></div><div class="ac_div clearfix"><p><b><font>35</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>21</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>35</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D2410B-5EB5-01D6-F959-37E33AAC1498"><div class="a_user_name clearfix"><span>孙涛</span></div><div class="ac_div clearfix"><p><b><font>33</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>16</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>42</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D3806A-2BD1-9091-2EA9-69DEDB145B84"><div class="a_user_name clearfix"><span>散利晖</span></div><div class="ac_div clearfix"><p><b><font>35</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>21</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>47</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D3EE08-A7F5-CE44-77A8-32B6E9A6B94D"><div class="a_user_name clearfix"><span>郭旭亮</span></div><div class="ac_div clearfix"><p><b><font>18</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>1</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a></li></ul>        </div>        </dd></dl><dl data-teamid="39D15072-B597-15A8-8EBC-57417A73BFCB">
                <dt class="on" d="1" style="position: static;">
                <p class="clearfix down">
                    <!--<t></t>-->
                    <span>测试</span>
                </p>
                </dt>
            <dd>                <p class="a_tit clearfix" style="position: static;">                <span>电转访</span>                <span>来访转认筹</span>                <span>来访转认购</span>                <span>认筹转认购</span>                <span>认购转签约</span>        </p>        <div class="a_count_lists">                <ol>                <li>                <a class="clearfix" data-ac="act_a" href="javascript:void(0);">                <div class="ac_div clearfix">                <p>                <i>平均</i>                <b>                <font>0</font> %        </b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>100</font> %</b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>        </div>        </a>        </li>        </ol>        <ul><li><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D03F87-CF9E-79F9-1408-6B9C87C9C3EC"><div class="a_user_name clearfix"><span>其它</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>100</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a></li></ul>        </div>        </dd></dl><dl data-teamid="39D227B5-3136-D917-D1AB-04848B315950">
                <dt class="on" d="1" style="position: static;">
                <p class="clearfix down">
                    <!--<t></t>-->
                    <span>招商组</span>
                </p>
                </dt>
            <dd>                <p class="a_tit clearfix" style="position: static;">                <span>电转访</span>                <span>来访转认筹</span>                <span>来访转认购</span>                <span>认筹转认购</span>                <span>认购转签约</span>        </p>        <div class="a_count_lists">                <ol>                <li>                <a class="clearfix" data-ac="act_a" href="javascript:void(0);">                <div class="ac_div clearfix">                <p>                <i>平均</i>                <b>                <font>100</font> %        </b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>100</font> %</b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>18</font> %</b>        </p>        </div>        </a>        </li>        </ol>        <ul><li><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C3B-6D90-F931-A733-ECC52201A292"><div class="a_user_name clearfix"><span>杨承林</span></div><div class="ac_div clearfix"><p><b><font>100</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>100</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>18</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D22738-EF79-2C15-9104-4B3BECF190C8"><div class="a_user_name clearfix"><span>马涌豪</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a></li></ul>        </div>        </dd></dl><dl data-teamid="00000000-0000-0000-0000-000000000000">
                <dt class="on" d="1" style="position: static;">
                <p class="clearfix down">
                    <!--<t></t>-->
                    <span>其他</span>
                </p>
                </dt>
            <dd>                <p class="a_tit clearfix" style="position: static;">                <span>电转访</span>                <span>来访转认筹</span>                <span>来访转认购</span>                <span>认筹转认购</span>                <span>认购转签约</span>        </p>        <div class="a_count_lists">                <ol>                <li>                <a class="clearfix" data-ac="act_a" href="javascript:void(0);">                <div class="ac_div clearfix">                <p>                <i>平均</i>                <b>                <font>0</font> %        </b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>                <p>                <i></i>                <b><font>0</font> %</b>        </p>        </div>        </a>        </li>        </ol>        <ul><li><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D00C3A-6E9E-DE15-922C-FAAE996CB509"><div class="a_user_name clearfix"><span>吴念彪</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D03515-088B-1FA0-EEAF-6DA5E0918F8A"><div class="a_user_name clearfix"><span>鲁潮</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D038E0-2924-953C-3351-98B8777A758D"><div class="a_user_name clearfix"><span>王旭琪</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D03922-8AC6-4529-FE4F-C52ED0816556"><div class="a_user_name clearfix"><span>宋伟</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D04343-6537-B07B-8914-BEBFBD2C8C65"><div class="a_user_name clearfix"><span>陈瑞民</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D05809-4EBA-980D-C12F-983C300599D5"><div class="a_user_name clearfix"><span>陈瑞杰</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D03947-FE58-ACE0-39DE-473ACD99A604"><div class="a_user_name clearfix"><span>郭威</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D1B7BB-4114-7981-A85C-22E4B588B3DC"><div class="a_user_name clearfix"><span>陈瑞</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D1B805-186B-49B2-C2B2-61DD277EECDE"><div class="a_user_name clearfix"><span>申志斌</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a><a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&amp;proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&amp;userid=39d038df-a82c-96a3-e06d-3537d94b8557&amp;range=&amp;saleid=39D038DF-A82C-96A3-E06D-3537D94B8557"><div class="a_user_name clearfix"><span>贾瑞</span></div><div class="ac_div clearfix"><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p><p><b><font>0</font> %</b></p></div></a></li></ul>        </div>        </dd></dl>    </center>
</article>



<script type="text/javascript" src="/Public/dest/base.min.js"></script>
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
        <li data-ac="active" class="border-1px"><span class="k">开始时间：</span><span class="v"><input class="btime" data-range="2010/1/1-2018/12/30" type="text" readonly></span></li>
        <li data-ac="active"><span class="k">结束时间：</span><span class="v"><input class="etime" data-range="2010/1/1-2018/12/30" type="text" readonly></span></li>
    </ul>
    <div class="btns">
        <span data-ac="active" class="cancel">取消</span>
        <span data-ac="active" class="sure">确定</span>
    </div>
</div>
<style type="text/css" class="dateinput">
    .date-input-wrap{position:fixed;z-index:99999;top:0;bottom:0;left:0;width:100%;background-color:rgba(0,0,0,0.5);font-family:sans-serif;-webkit-tap-highlight-color:rgba(0,0,0,0)}
    .date-input-wrap .center{position: absolute;text-align: center;top:50%;width:100%;}
    .date-input-wrap .title{height:60px;text-align:left;line-height: 60px;color:#3c6;font-size: 18px;border-bottom:1px solid #ddd;}
    .date-input-wrap .title-icon{display:inline-block;margin:-2px 8px 0px 15px;vertical-align:middle;height:24px;width:24px;-webkit-background-size:24px 24px;background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAMAAABg3Am1AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAADNQTFRFzfPZ8/32muayQtBuZ9qLTtN4geCfW9aB2vbijeOps+3F5vrtwPDPpuq8dN2UNs1k////DqGdwwAAAbhJREFUeNqMlum6xBAMhoNai7j/qz0fbed0oZU/85S8ETFZqDxkjdIskCyDe+7SXdtbPok29ArQAiUF0wTBQboyYQisUFcmnredB6OpDySoS/FwOgAx4gmIfFk/iZDM1t0BYVnFMhCnWbkrAH3tylCqOXcGsGBv7jBfPs1B0PG5llfgZ7IBkfnhzw0oQrM/AKE4lS+gOGbaAc9L+QagZjdg7TjUA+AINaB7QAcosioCaOAMgCNWAJF1mQMQfQ/Ab+GaASKuTcV2PeoCWBTU3xkACxO5Ft1JwHMi6gZ1AEiWAPw8kAAAmgeIDYV/gK9SenGtLuV5YLvDUqalAoLVPFDfodR/1KxodlQyh1n9Fd4QYptngcQGABJOTAKWY02gPHi6zrOplnH4FZMxklvV0HNHbIZbqeGZyIrNbsu2PMiJW0LrX6lEOTBf+mGvXrS793UNlN907g/g/Yd9c+1AYdSwjvZn7j0unBvZLT75X//URdHI2PcOSYpV6LVd4dF3/e1FRIKdZR1MAm0QsPK35lKuo0B8mTXIqDZi1OFEt8zO4X04KSL65SgD1gTxNc3sqVWHk37M/gQYAOv6g+k3lRpPAAAAAElFTkSuQmCC);}
    .date-input-wrap .title > span:last-child{padding-left:8px;}
    .date-input-wrap .wrap{width:288px;border:2px solid #777;margin:-144px auto 0;background-color:#fff;text-align:center;}
    .date-input-wrap .content{width: 252px;height:178px;margin:0 auto;white-space:nowrap;font-size:0;}
    .date-input-wrap .content .iscroll-wrap{display:inline-block;box-sizing: border-box;-webkit-box-sizing: border-box;width:76px;height:178px;width:33%;position:relative;}
    .date-input-wrap .content .iscroll-wrap:nth-child(2){width: 34%;}
    .date-input-wrap .content .iscroll-wrap:after{content:"";position:absolute;pointer-events:none;top:0px;width:100%;left:0;bottom:0;background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(10%,rgba(255,255,255,1)), color-stop(38%,rgba(255,255,255,0)), color-stop(62%,rgba(255,255,255,0)), color-stop(90%,rgba(255,255,255,1)), color-stop(100%,rgba(255,255,255,1))); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(255,255,255,1) 10%,rgba(255,255,255,0) 38%,rgba(255,255,255,0) 62%,rgba(255,255,255,1) 90%,rgba(255,255,255,1) 100%); /* Chrome10+,Safari5.1+ */background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(255,255,255,1) 10%,rgba(255,255,255,0) 38%,rgba(255,255,255,0) 62%,rgba(255,255,255,1) 90%,rgba(255,255,255,1) 100%); /* W3C */}
    .date-input-wrap .iscroll{position:absolute;left:8px;top:10px;right:8px;height:156px;overflow:hidden;}
    .date-input-wrap .buttons{border-top:1px solid #ddd;}
    .date-input-wrap .date-btn{height:40px;line-height:40px;font-size:16px;padding:2px;color:#3c6;}
    .date-input-wrap .date-btn.active{background-color:#d9d9d9;}
    .date-input-wrap .date-btn span{display:block;height:40px}
    .date-input-wrap ul{padding:0px 0 52px 0;text-align:center;margin:0;}
    .date-input-wrap li{list-style:none;line-height:52px;height:52px;font-size:18px;color:#fff;z-index: 2;color:#333;}
    .date-input-wrap .content .iscroll:before,.date-input-wrap .content .iscroll:after{content:"";position: absolute;top:33%;left:8px;right:8px;height: 2px;background-color:#3c6;}
    .date-input-wrap .content .iscroll:after{top:66%;}</style>
<script class="datainput">
    (function($){
        var clicklock = 0;
        $.fn.dateinput=function(opt){
            var me=$(this), range={},ori_time={},cur_time={},get_day=new Date().getDate(),scrollid;
            var html='<div class="date-input-wrap" id="date-input-wrap"><div class="center">\
                        <div class="wrap">\
                                <div class="title"><span class="title-date"><span class="title-icon"></span><span id="cur-year"></span>-<span id="cur-month"></span>-<span id="cur-day"></span></span><span id="cur-week"></span></div>\
                        <div class="content">\
                                <div class="iscroll-wrap">\
                                <div class="year iscroll" id="date-year" targ="#cur-year"></div>\
                        </div>\
                        <div class="iscroll-wrap">\
                                <div class="month iscroll" id="date-month" targ="#cur-month"></div>\
                        </div>\
                        <div class="iscroll-wrap">\
                                <div class="day iscroll" id="date-day" targ="#cur-day"></div>\
                        </div>\
                        </div>\
                        <div class="buttons">\
                                <div class="sure date-btn" data-ac="active"><span>确&nbsp;定</span></div>\
                        </div>\
                        </div>\
                        </div></div>';
            me.bind("click",function(){
                if(clicklock) return;
                clicklock = 1;
                //使所有文本框失去焦点(解决输入法没有关闭时，点击时间控件页面错位bug)
                $('input').blur();
                setTimeout(function(){
                    getRange();
                    var panel=$(html);
                    $("body").append(panel);
                    getCurtime();
                    start(cur_time.year,cur_time.month,cur_time.day);
                    $("#date-input-wrap .buttons .current").bind("click",function(){
                        cur_time={};
                        start();
                    });
                    $("#date-input-wrap .buttons .sure").bind("click",function(){
                        me.val($("#date-input-wrap .title-date").text());
                        setTimeout(function(){
                            panel.remove();
                        },100);
                        if(ori_time.year!=cur_time.year||ori_time.month!=cur_time.month||ori_time.day!=cur_time.day){
                            me.trigger("change");
                        }
                    });
                    $("#date-input-wrap").bind("click",function(){
                        setTimeout(function(){
                            panel.remove();
                        },100);
                    });
                    $("#date-input-wrap .wrap").bind("click",function(e){
                        e.preventDefault();
                        e.stopPropagation();
                    });
                    clicklock = 0;
                },300);
            });
            function getCurtime(){
                if(me.val()){
                    var time=me.val().split("-");
                }
                ori_time["year"]=cur_time["year"]=time&&parseInt(time[0],10);
                ori_time["month"]=cur_time["month"]=time&&parseInt(time[1],10);
                ori_time["day"]=cur_time["day"]=time&&parseInt(time[2],10);
            }
            function getRange(){
                range={};
                var ran=me.attr("data-range").split("-");
                if(ran.length<2){ran=["1900","2100"]};
                range["start"]= $.map(ran[0]&&ran[0].split("/"),function(item,ind){return parseInt(item,10)});
                range["end"]= $.map(ran[1]&&ran[1].split("/"),function(item,ind){return parseInt(item,10)});
            }
            function start(){
                var iscroll_year=init_year();
                var iscroll_month=init_month();
                var iscroll_day=init_day();
                setweek();
            }
            function onscrollend(){
                var wrapper=$(this.wrapper);
                var value=getCurNum(this);
                if(wrapper.hasClass("year")){
                    cur_time["year"]=parseInt(value,10);
                    init_month();
                    setTimeout(init_day,20);
                }else if(wrapper.hasClass("month")){
                    cur_time["month"]=parseInt(value,0);
                    setTimeout(init_day,20);
                }else if(wrapper.hasClass("day")){
                    cur_time["day"]=parseInt(value,10);
                }
                setweek();
            }
            function setweek(){
                var arr=["周日","周一","周二","周三","周四","周五","周六"];
                var date=new Date(cur_time.year,cur_time.month-1,cur_time.day);
                var week=arr[date.getDay()];
                $("#cur-week").text(week);
            }
            function getCurNum(iscroll){
                var wrapper=$(iscroll.wrapper),cur_y=iscroll.y,dy=wrapper.find("li").height(),dom_tag=$(wrapper.attr("targ"));
                var ind=Math.round(Math.abs(cur_y)/dy),value=wrapper.find("li").eq(ind+1).text();
                setTimeout(function(){dom_tag.text(value)},30);
                return value;
            }
            function init_year(){
                var tmp=$("<ul><li class='pad'></li></ul>");
                var cur_year= cur_time.year||new Date().getFullYear();
                for(var i=range.start[0];i<=range.end[0];i++){
                    tmp.append($("<li val="+i+">"+i+"</li>"));
                }
                $("#date-input-wrap #date-year").html(tmp);
                var iscroll_year=new iScroll("date-year",{hScrollbar:false, vScrollbar:false,snap:"li",useTransition:true,onTouchEnd:onscrollend});
                var scrollDom=$(iscroll_year.wrapper).find("li[val='"+cur_year+"']").prev();
                cur_time.year=cur_year;
                scrollDom.size()&&iscroll_year.scrollToElement(scrollDom[0],0);
                getCurNum(iscroll_year);
                return iscroll_year;
            }
            function init_month(){
                var tmp=$("<ul><li class='pad'></li></ul>");
                var cur_month=cur_time.month||new Date().getMonth()+1;
                var floor= 1,ceil=12;
                if(cur_time.year==range.start[0]&&!!range.start[1]){
                    floor=range.start[1];
                }
                if(cur_time.year==range.end[0]&&!!range.end[1]){
                    ceil= range.end[1];
                }
                for(var i=floor;i<=ceil;i++){
                    var num=i<10?"0"+i:i;
                    tmp.append($("<li val="+i+">"+num+"</li>"));
                }
                $("#date-input-wrap #date-month").html(tmp);
                var iscroll_month=new iScroll("date-month",{hScrollbar:false, vScrollbar:false,snap:"li",useTransition:true,onTouchEnd:onscrollend});
                var scrollDom=$(iscroll_month.wrapper).find("li[val='"+cur_month+"']").prev();
                cur_time.month=cur_month;
                scrollDom.size()&&iscroll_month.scrollToElement(scrollDom[0],0);
                getCurNum(iscroll_month);
                return iscroll_month;
            }
            function init_day(){
                var arr_day=[31,28,31,30,31,30,31,31,30,31,30,31],year= cur_time.year || new Date().getFullYear(),cur_day=cur_time.day||new Date().getDate(),cur_month=cur_time.month||new Date().getMonth();
                if(year%4==0&&year%100!=0){
                    arr_day[1]=29;
                }
                var num_day=arr_day[cur_month-1];
                var floor= 1,ceil=num_day;
                if(cur_time.year==range.start[0]&&cur_time.month==range.start[1]){
                    floor=range.start[2]||floor;
                }
                if(cur_time.year==range.end[0]&&cur_time.month==range.end[1]){
                    ceil= range.end[2]||num_day;
                }
                var tmp=$("<ul><li class='pad'></li></ul>");
                for(var i=floor;i<=ceil;i++){
                    var num=i<10?"0"+i:i;
                    tmp.append($("<li val="+i+">"+num+"</li>"));
                }
                $("#date-input-wrap #date-day").html(tmp);
                var iscroll_day=new iScroll("date-day",{hScrollbar:false, vScrollbar:false,snap:"li",useTransition:true,onTouchEnd:onscrollend});
                var scrollDom=$(iscroll_day.wrapper).find("li[val='"+cur_day+"']").prev();
                cur_time.day=cur_day;
                scrollDom.size()&&iscroll_day.scrollToElement(scrollDom[0],10);
                getCurNum(iscroll_day);
            }
        }
        $(function(){
            $("input[type=text][data-range]").each(function(dom,ind){
                $(this).dateinput();
            });
        });
    })(Zepto);
</script>
<style>
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
<script>
    /**
     * @description MYSOFT 全局对象，负责前端的交互组织
     * @namespace 全局的命名空间
     */
    MYSOFT = window.MYSOFT || {};

    /**
     * @description MYSOFT界面对象，负责前端的公共控件
     * @namespace MYSOFT界面的命名空间
     */
    MYSOFT.Ui = window.MYSOFT.Ui || {};

    /**
     * @description MYSOFT全局配置
     * @namespace MYSOFT全局配置的命名空间
     */
    MYSOFT.Config = window.MYSOFT.Config || {};

    /**
     * @description MYSOFT工具
     * @namespace MYSOFT工具的命名空间
     */
    MYSOFT.Util = window.MYSOFT.Util || {};

    /**
     * @description MYSOFT Dom命名空间,负责Dom操作
     * @namespace MYSOFT Dom命名空间
     */
    MYSOFT.Dom = window.MYSOFT.Dom || {};
    /**
     * @namespace MYSOFT弹出框组件类
     */
    $.Ui={};
    $.Ui.Popup=MYSOFT.Ui.Popup = {
        //初始化Dom对象到文档
        Init: function () {
            if (!MYSOFT.Ui.Popup.DomObj) {
                //全局只有一个实例
                $('body').append('<div id="mysoft_popup"></div><div id="mysoft_popup_mask"></div>');
                MYSOFT.Ui.Popup.DomObj = $('#mysoft_popup');
                MYSOFT.Ui.Popup.MaskDomObj = $('#mysoft_popup_mask');
            }
        },
        Show: function (html,cls) {
            MYSOFT.Ui.Popup.Init();
            MYSOFT.Ui.Popup.MaskDomObj.show();
            MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass(cls).html(html).show();
            $('#tag_ok_popup').bind('click', function () {
                MYSOFT.Ui.Popup.HidePopUp();
            });
        },
        /**
         * @method 显示加载动画
         * @param text 加载文本
         */
        ShowLoading: function (text) {
            MYSOFT.Ui.Popup.Init();
            var markup = MYSOFT.Ui.Popup.Template.loading.replace('{title}', text || '正在加载');
            MYSOFT.Ui.Popup.MaskDomObj.show().css({'opacity':'0'});
            MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('loading').html(markup).show();
        },
        /**
         * @method 隐藏弹出框
         */
        HidePopUp: function () {
            MYSOFT.Ui.Popup.MaskDomObj.hide();
            MYSOFT.Ui.Popup.DomObj.hide().empty();
        },
        /**
         * @method 弹出框提示
         * @param title 提示标题
         * @param content 提示内容
         * @param okCall 点击确定回调函数
         */
        ShowAlert: function (title, content, okCall) {
            var markup = MYSOFT.Ui.Popup.Template.alert.replace('{title}', title||'提示信息').replace('{content}', content).replace('{ok}', '我知道了');
            MYSOFT.Ui.Popup.Init();
            MYSOFT.Ui.Popup.MaskDomObj.show();
            MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('alert').html(markup).show();
            $('#tag_ok_popup').bind('click', function () {
                MYSOFT.Ui.Popup.HidePopUp();
                okCall && okCall();
            });
            MYSOFT.Ui.Popup.DomObj.css('margin-top', -(MYSOFT.Ui.Popup.DomObj.height()/2));
        },
        /**
         * @method 确认框提示
         * @param title 提示标题
         * @param content 提示内容
         * @param okCall 确定回调函数
         * @param cancelCall 取消回调函数
         */
        ShowConfirm: function (title, content, okCall,cancelCall,okText,cancelText) {
            okText=okText||'确 定';
            cancelText=cancelText||'取 消';
            var markup = MYSOFT.Ui.Popup.Template.confirm.replace('{title}', title).replace('{content}', content).replace('{cancel}', cancelText).replace('{ok}', okText);
            MYSOFT.Ui.Popup.Init();
            MYSOFT.Ui.Popup.MaskDomObj.show();
            MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('confirm').html(markup).show();
            $('#tag_ok_popup').bind('click', function () {
                MYSOFT.Ui.Popup.HidePopUp();
                okCall && okCall();
            }).bind('touchstart',function(e){
                e.stopPropagation();
            });
            $('#tag_cancel_popup').bind('click', function () {
                MYSOFT.Ui.Popup.HidePopUp();
                cancelCall && cancelCall();
            }).bind('touchstart',function(e){
                e.stopPropagation();
            });
            MYSOFT.Ui.Popup.DomObj.css('margin-top', -(MYSOFT.Ui.Popup.DomObj.height() / 2));
        },
        /**
         * @object Popup模版
         */
        Template: {
            //加载动画
            loading: '<div class="img"></div><p>{title}</p>',
            //提示框
            alert: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_ok_popup">{ok}</a></div>',
            //确认提示框
            confirm: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_cancel_popup">{cancel}</a><a id="tag_ok_popup">{ok}</a></div>'
        }
    };
</script>
<script type="text/javascript">
    (function($){
        var hidepanel =1;
        var timeWrap = $("#time-wrap"),callbackfunc,isclose=false;
        $.showTwoTime= function(callback,closed){
            //第一个参数为点击 确定时候的回调函数，第二个参数为回调的时候时候关闭时间控件面板。
            $.hash.setHash("twotime","1");
            callbackfunc=callback;
            isclose = closed;
        }
        $(window).on("hashchange",function(){
            var hash = $.hash.getHash("twotime");
            if(hash){
                $("body > *").addClass("twotimehide");
                timeWrap.removeClass("twotimehide").show();
            }else if(hidepanel||isclose){
                $("body > *").removeClass("twotimehide");
                timeWrap.hide();
                $(".date-input-wrap").remove();
            }
        });
        timeWrap.find(".cancel").on("click",function(){
            history.back();
        });
        timeWrap.find(".sure").on("click",function(){
            var btime = timeWrap.find(".btime").val();
            var etime = timeWrap.find(".etime").val();
            if(btime&&etime){
                if((btime!=etime) && (getTime(etime) < getTime(btime))){
                    MYSOFT.Ui.Popup.ShowAlert("提示","结束时间要大于开始时间",null);
                }else{
                    hidepanel =0;
                    setTimeout(function(){
                        callbackfunc&&callbackfunc(btime,etime);
                    },10);
                    history.back();

                }
            }else{
                MYSOFT.Ui.Popup.ShowAlert("提示","开始、结束时间都不能为空",null);
            }
        });
        function getTime(str){
            var arr = str.split("-");
            return new Date(arr[0],arr[1]-1,arr[2],0,0,0,0);
        }
    })(Zepto);
    $(function(){
        $.hash.clearHash();
    });
</script>
<style>
    .time-wrap ul{background-color:#f0f0f0;padding-bottom:30px;}
    .time-wrap ul li{background-color:#fff;margin:0;}
    .time-wrap li .k{padding-left:12px;}
    .time-wrap .btns{margin-top:0px;}
</style>
<script>
    var Swift = {
        toggleAct: function(param, className){
            param.addClass(className).siblings().removeClass(className);
        }
        ,toggleDd: function(param, className){
            param.toggleClass(className);
        }
        ,perFilter: function(param){
            var str = param.replace(/%/g, '');
            return str;
        }
        ,isFlag: false
        ,aLoading: function(param){
            param.css('top', $(window).height() / 2 - 60).show();
        }
        ,hideLoading: function(param){
            param.remove();
        }
        ,aShadow: function(param){
            param.show();
        }
        ,hideShadow: function(param){
            param.remove();
        }
    };
    Swift.aLoading($('.a_loading'));
    Swift.aShadow($('.a_shadow'));
    (function($){
        //滑动置顶
        $(window).scroll(function(){
            var nav = $('nav');
            var dl = $('center dl'); //dl
            var header = $('header'); //头部
            var isFixedFlag = false;
            for(var i = 0; i < dl.length; i++){
                if(dl.eq(i).get(0).getBoundingClientRect().top <= 44){
                    isFixedFlag = true;
                    var dtOrigin = $('dt', dl.eq(i));
                    $('dt', dl.eq(i)).clone().appendTo($('body')).addClass('newDt').css({
                        'position': 'fixed',
                        'top': '44px',
                        'left': '0',
                        'right': '0',
                        'background-color': 'white'
                    }).on('click', function(){
                        dtOrigin.trigger('click');
                        //console.log($(this));
                        var that = $(this);
                        //显示每个团队的数据
                        if(that.hasClass('on')){
                            that.removeClass('on');
                        }else{
                            that.addClass('on');
                        }
                    });
                    $('p.a_tit', dl.eq(i)).clone().appendTo($('body')).addClass('newPp').css({
                        'position': 'fixed',
                        'top': '89px',
                        'left': '0',
                        'right': '0',
                        'background-color': 'white'
                        /* 'border-top': '1px solid #ddd'*/
                    });
                    //console.log('yes');
                }else{
                   //console.log('no');
                    if($('dt', dl.eq(i)).length == 1){
                        $('dt', dl.eq(i)).css({
                            'position': 'static'
                        });
                    }else{
                        $('dt', dl.eq(i)).css({
                            'position': 'static',
                            'border': '0'
                        });
                    }
                    $('p.a_tit', dl.eq(i)).css({
                        'position': 'static'
                    });
                }
            }
            if(isFixedFlag == false){
                $('.newDt').remove();
                $('.newPp').remove();
            }
            if(nav.get(0).getBoundingClientRect().top <= 0){
                nav.css({
                    'position': 'fixed',
                    'top': '0px',
                    'left': '0',
                    'right': '0',
                    'background-color': 'white',
                    'border-top': '1px solid #ddd',
                    'z-index': '9'
                });
            }else{
                nav.css({
                    'position': 'static',
                    'border': '0'
                });
            }
            if(header.get(0).getBoundingClientRect().top >= 0){
                nav.css({
                    'position': 'static',
                    'border': '0'
                });
            }
        });
        //自定义
        $('#a_default').on('click', function(){
            $(window).on('touchmove', preventFlase);
            $.showTwoTime(function(btime,etime){
                window.location.replace("/Sales/SaleFollowAnalysis/percentReport?token=cdycid1429859342&proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&userid=39d038df-a82c-96a3-e06d-3537d94b8557"+"&range="+btime+"/"+etime);
            });
        });
        $('.btns span').on('click', function(){
            $(window).off('touchmove', preventFlase);
        });
        function preventFlase(){
            return false;
        }
        //百分比圆环
        $('.a_li').css('margin-left', (document.documentElement.clientWidth - 300)/5);
        var percent = $('font');
        for(var i = 0;i < percent.length;i++){
            percent.eq(i).text(percent.eq(i).get(0).innerHTML.replace(/%/, ''));
        }
        //
        var dlDt = $('center dl dt');
        var temp = $('<dd>\
                <p class="a_tit clearfix">\
                <span>电转访</span>\
                <span>来访转认筹</span>\
                <span>来访转认购</span>\
                <span>认筹转认购</span>\
                <span>认购转签约</span>\
        </p>\
        <div class="a_count_lists">\
                <ol>\
                <li>\
                <a class="clearfix" data-ac="act_a" href="javascript:void(0);">\
                <div class="ac_div clearfix">\
                <p>\
                <i>平均</i>\
                <b>\
                <font>0</font> %\
        </b>\
        </p>\
                <p>\
                <i></i>\
                <b><font>0</font> %</b>\
        </p>\
                <p>\
                <i></i>\
                <b><font>0</font> %</b>\
        </p>\
                <p>\
                <i></i>\
                <b><font>0</font> %</b>\
        </p>\
                <p>\
                <i></i>\
                <b><font>0</font> %</b>\
        </p>\
        </div>\
        </a>\
        </li>\
        </ol>\
        <ul><li></li></ul>\
        </div>\
        </dd>');
        for(var i = 0;i < $('center dl').length;i++){
            temp.clone().appendTo($('center dl').eq(i));
        }
        //拉取页面数据
        $.ajax({
            url:"/Sales/SaleFollowAnalysis/ajaxGetAllUserData?token=cdycid1429859342&proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=&type=percent",
            data:{},
            type:'GET',
            success: function(data){
                var g = JSON.parse(data);
                Swift.hideLoading($('.a_loading'));
                Swift.hideShadow($('.a_shadow'));
                var i = 0;
                for(var key in g){
                    i++;
                    var center = $('center');
                    var dlEach = $('dl[data-teamid="'+key.toUpperCase()+'"]').get(0);
                    var dd = $('dd', dlEach);
                    var ol = $('ol', dd);
                    var oli = $('li', ol);
                    var total = $('a', oli).first();
                    var avg = $('a', oli).last();
                    $('font', avg).eq(0).html(Swift.perFilter(g[key]['avg_count']['call2visit'])); //电转访
                    $('font', avg).eq(1).html(Swift.perFilter(g[key]['avg_count']['visit2booking'])); //来访转认筹
                    $('font', avg).eq(2).html(Swift.perFilter(g[key]['avg_count']['visit2trade'])); //来访转认购
                    $('font', avg).eq(3).html(Swift.perFilter(g[key]['avg_count']['booking2trade'])); //认筹转认购
                    $('font', avg).eq(4).html(Swift.perFilter(g[key]['avg_count']['rengou2qianyue'])); //认购转签约
                    var uli = $('ul li', dlEach);
                    uli.html('');
                    for(var i in g[key]['user_list']){
                        var saleid = g[key]['user_list'][i]['user_id'];
                        var al = $('<a class="clearfix" data-ac="act_a" href="/Sales/SaleFollowAnalysis/personReport?token=cdycid1429859342&proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=&saleid='+saleid+'"></a>').appendTo(uli);
                        var un = $('<div class="a_user_name clearfix"><span>'+g[key]['user_list'][i]['user_name']+'</span></div>').appendTo(al);
                        var da = $('<div class="ac_div clearfix"></div>').appendTo(al);
                        var p1 = $('<p><b><font>'+Swift.perFilter(g[key]['user_list'][i]['count']['call2visit'])+'</font> %</b></p>')
                                .appendTo(da);
                        var p2 = $('<p><b><font>'+Swift.perFilter(g[key]['user_list'][i]['count']['visit2booking'])+'</font> %</b></p>').appendTo(da);
                        var p3 = $('<p><b><font>'+Swift.perFilter(g[key]['user_list'][i]['count']['visit2trade'])+'</font> %</b></p>').appendTo(da);
                        var p4 = $('<p><b><font>'+Swift.perFilter(g[key]['user_list'][i]['count']['booking2trade'])+'</font> %</b></p>').appendTo(da);
                        var p4 = $('<p><b><font>'+Swift.perFilter(g[key]['user_list'][i]['count']['rengou2qianyue'])+'</font> %</b></p>').appendTo(da);
                    }
                }
            }
        });
        dlDt.on('click', function(){
            $(this).css('position', 'static');
            var that = $(this);
            //显示每个团队的数据
            if(that.hasClass('on')){
                that.removeClass('on');
            }else{
                that.addClass('on');

            }
        });
        //链接跳转
        $(".datelist li").on('click',function(o){
            if($(this).attr('range') != 'other'){
                window.location.replace($('a', $(this)).attr('data-url'));
            }
        });
        //菜单跳转
        $("header li").on('click',function(o){
            window.location.replace($('a', $(this)).attr('data-url'));
        });
    })(Zepto);
</script>
</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html>