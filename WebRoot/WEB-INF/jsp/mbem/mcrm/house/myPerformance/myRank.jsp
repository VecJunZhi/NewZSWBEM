<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="gbk">
<title>我的业绩</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/mbem/mcrm/business/css/bmin.css">
<title></title>
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
<script src="/common/js/jquery-1.9.1.min.js"></script>
    <script>
    /* $(function ($) {
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
    });    	  */
	</script>
    <style type="text/css">
    .down_arr i{right: 0px;left: 4px;background-image: url(/mbem/mcrm/business/images/yhxx_12.png) ;background-repeat:no-repeat;width: 31px;height: 31px;}
    .key_input{border: 0px;color: #a5a5a5;padding: 5px;text-align: right;}
    .font-blue{color: #4ea9ff;text-decoration:none;}
    body,html{background: #f0f0f0;}
    #wrapper_1 {background: white;margin-bottom: 20px;}
    </style>
    <script src="/mbem/mcrm/business/js/base.min.js"></script>
        <script type="text/javascript">
    var statistics={};
    statistics.start=+new Date();
</script>
    <script type="text/javascript">
        var _type= "0";
        var _guid = "";
        var _tjwd = "";
        var m_value, m_guid, q_value, q_guid;
        //var m_ts_value, m_ts_guid, q_ts_value, q_ts_guid;
        $(function(){
            //myScroll = new iScroll('wrapper_1');
        });
        //function OpenTargetModify() {
        //    //月度
        //    m_guid = $("#targetM").attr("guid");
        //    m_value = $("#targetM").text().replace(/\s+/g, '').replace(/\,/g, "");
        //    //季度
        //    q_guid = $("#targetQ").attr("guid");            
        //    q_value = $("#targetQ").text().replace(/\s+/g, '').replace(/\,/g, "");
                     

        //    $("#update_box").showPopWin({maskSetting:true});
        //    $("#value_m_input").val(m_value);
        //    $("#value_q_input").val(q_value);
        //}
</script>
    <script type="text/javascript">
        statistics.t1=+new Date()-statistics.start;
    </script>
<style>
 html,body {color:#444;}
 .border-1px{border-width:0;border-bottom-width:1px;-webkit-border-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAACRJREFUeNpivHv37n8GLIAFSjOiif9nYsABSJeA2YHhAIAAAwCMHQWlmEwsxgAAAABJRU5ErkJggg==") 2 2 stretch;}

 .budget {position: relative;}
 .icon-budget-target-edit {float:right;margin-right:12px;position:relative;top:10px;}
 .icon-pen {
    display:block;
    width:50px;
    height:50px;
    background: url(/mbem/mcrm/business/images/orangepen_default.png) no-repeat center;
    background-size:50px 50px;
}
 .icon-pen.edit-active {
    display:block;
    width:50px;
    height:50px;
    background: url(/mbem/mcrm/business/images/orangepen_focus.png) no-repeat center;
    background-size:50px 50px;
}
 .budget-list.budget-active{background:#eee;}
 .budget-head {background:#ff7c51;height:50px;}
 .budget-title{font-size:17px;line-height: 50px;color:white;padding-left:12px;float:left;}
 .budget-detail {font-size:14px;line-height: 31px;}
#scrollWrapper {width:100%;overflow:hidden;}
.scroller{width:300%;}
.scroll-list {width:100%;float:left;-webkit-box-size:border-box;}
.scroll-list>li{width:33.333%;float:left;}
.list-head{width:100%;}
.budget-list {float:left;padding-top:7px;padding-bottom:15px;padding-left:12px;box-sizing:border-box;}
.list-num .num-big{color:#FFA851;}
.list-num .num-small {font-size:13px;color:#666;}
.num-small::before {
    content:'/';
    font-size:13px;
    color:#666;
}
.progress{width:100%;padding-right:50px;box-sizing:border-box;position:relative;margin-top:4px;}
.bar{height:7px;background:#f6f6f6;border-radius:5px;padding:2px;}
.inner-bar {background: #ffb973;height:7px;border-radius: 3px;}
.percent {font-size:13px;position:absolute;right:12px;top:-10px;color:#aaa;}
.indicator-wrapper {text-align: center;height:35px;}
ul.indicator {display:inline-block;position:relative;top:8px;}
.indicator li{float: left;height:8px;width:8px;border-radius: 4px;background:#f6f6f6;margin:0 5px;}
.indicator li.active{background:#ff7c51;}
.budget-list:visited {color:#444;}
.budget-list {text-decoration: none;color:#444;display: block;}
 .budget-list1 {background:#eee;}
.budget-list1 {float:left;padding-top:7px;padding-bottom:15px;padding-left:12px;box-sizing:border-box;}
.budget-list1 {text-decoration: none;color:#444;display: block;}
.icon-pen1 {    display:block;
    width:50px;
    height:50px;
    background: url(/mbem/mcrm/business/images/orangepen_default.png) no-repeat center;
    background-size:50px 50px;
}
.icon-pen1 {    display:block;
    width:50px;
    height:50px;
    background: url(/mbem/mcrm/business/images/orangepen_focus.png) no-repeat center;
    background-size:50px 50px;
}
.scroll-list1 {width:100%;float:left;-webkit-box-size:border-box;}
</style>
</head>
<body data-pageid="7" data-php="" data-ram="" data-token="cdycid1429859342" data-projid="c5091504-41ae-4c2e-ae3b-69a008a5762f">
    <div id="wrapper_1" class="clearfix">
        <div class="clearfix">
            
            <div class="pandel_simple_list budget">
                <div id="scrollWrapper" style="overflow: hidden;">
                    <div class="scroller clearfix" style="transition: transform 0ms; -webkit-transition: transform 0ms; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) translateZ(0px);">
                        <ul class="scroll-list">
                        
                                                    <li>
                                <div class="budget-head">
                                    <div class="budget-title">月度完成情况</div>
                                    <div class="icon-budget-target-edit"> <!--提醒设置，绝对定位-->
                                    	<!-- <a href="#" data-ac="edit-active" class="icon-pen"></a> 编辑 -->
                                     </div>
                                </div>
                                <div class="budget-detail clearfix">
                                    <!--按金额套数面积等循环-->
                                        <a href="#" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">金额</div>
                                        <div class="list-num"><span class="num-big">${my_budgetMonth.salesAmount }万元</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"></div><!-- 此处应填完成进度 -->
                                         </div>
                                    </a>
                                        <a href="#" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">套数</div>
                                        <div class="list-num"><span class="num-big">${my_budgetMonth.ts}套</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"></div>
                                         </div>
                                    </a>
                                    <a href="#" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">面积</div>
                                        <div class="list-num"><span class="num-big">${my_budgetMonth.bldArea}㎡</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"></div>
                                         </div>
                                    </a>
                                                                        <a href="#" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">回款</div>
                                        <div class="list-num"><span class="num-big">${my_budgetMonth.backAmount}万元</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"></div>
                                         </div>
                                    </a>
                                                                        <!--end-->
                                </div>
                            </li>
                                                        <li>
                                <div class="budget-head">
                                    <div class="budget-title">预算完成情况(季度)</div>
                                    <div class="icon-budget-target-edit"> <!--提醒设置，绝对定位-->
                                    	<!-- <a href="#" class="icon-pen"></a> -->
                                     </div>
                                </div>
                                <div class="budget-detail clearfix">
                                    <!--按金额套数面积等循环-->
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">金额</div>
                                        <div class="list-num"><span class="num-big">${my_budgetSeason.salesAmount}万元</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"><!-- 0% --></div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">套数</div>
                                        <div class="list-num"><span class="num-big">${my_budgetSeason.ts}套</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"><!-- 0% --></div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">面积</div>
                                        <div class="list-num"><span class="num-big">${my_budgetSeason.bldArea}㎡</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"><!-- 0% --></div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">回款</div>
                                        <div class="list-num"><span class="num-big">${my_budgetSeason.backAmount}万元</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent"><!-- 0% --></div>
                                         </div>
                                    </a>
                                                                        <!--end-->
                                </div>
                            </li>
                                                        <li>
                                <div class="budget-head">
                                    <div class="budget-title">预算完成情况(年度)</div>
                                    <div class="icon-budget-target-edit"> <!--提醒设置，绝对定位-->
                                    	<!-- <a href="#" class="icon-pen"></a> -->
                                     </div>
                                </div>
                                <div class="budget-detail clearfix">
                                    <!--按金额套数面积等循环-->
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">金额</div>
                                        <div class="list-num"><span class="num-big">${my_budgetYear.salesAmount}万元</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <!-- <div class="percent">0%</div> -->
                                         </div>
                                    </a>
                                                                        <a href="#" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">套数</div>
                                        <div class="list-num"><span class="num-big">${my_budgetYear.ts}套</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <!-- <div class="percent">0%</div> -->
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">面积</div>
                                        <div class="list-num"><span class="num-big">${my_budgetYear.bldArea}㎡</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                           <!--  <div class="percent">0%</div> -->
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">回款</div>
                                        <div class="list-num"><span class="num-big">${my_budgetYear.backAmount}万元</span><!-- <span class="num-small">0</span> --></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <!-- <div class="percent">0%</div> -->
                                         </div>
                                    </a>
                                                                        <!--end-->
                                </div>
                            </li>
                                                    </ul>
                    </div>
                </div>
                <div class="indicator-wrapper">
                        <ul class="indicator clearfix">
                            <li class="active"></li>
                            <li></li>
                            <li></li>
                        </ul>
                </div>
            </div>
            
            
            
            
            
            <!--排行榜 -->
            <style>
                .rank-bg{
                    height:40px;
                    background:#f0f0f0;
                }
                .rank-banner {box-sizing:border-box;padding:0 50px;position: relative;margin-top:-26px;}
                .rank-title {background:#ffb973;font-size:17px;line-height: 32px;text-align: center;color:white;}
                .decorate-left {
                    position: absolute;
                    left:12px;
                    width: 38px;
                    height:32px;
                    background: url(/mbem/mcrm/business/images/icon_orange_left.png) no-repeat center;
                    background-size: 38px 32px;
                }
                .decorate-right {
                    position: absolute;
                    right:12px;
                    width: 38px;
                    height:32px;
                    background: url(/mbem/mcrm/business/images/icon_orange_right.png) no-repeat center;
                    background-size: 38px 32px;
                }
                .rank-by {
                    float:left;
                    width:50%;
                    height:44px;
                }
                .rank-by .center {
                    width:120px;
                    margin:0 auto;
                    font-size:16px;
                    line-height: 42px;
                    border-bottom:2px solid transparent;
                }
                .rank-by.active .center {
                    color: #ff7c51;
                    border-bottom: 2px solid #ff7c51;
                }
                .my-rank {
                    font-size: 13px;
                    text-align: center;
                    line-height: 40px;
                    background: #f7f7f7;
                }
                .my-sale,.my-rank-num{
                    color: #ff7c51;
                    padding:0 3px;
                }
                .saw-teeth {
                    height:10px;
                    background: url(/Public/myui_v1/images/icon_triangle.png) repeat-x;
                    background-size:10px 5px;
                }
            </style>
            <style>
                    .rank-table li>div:first-child {width:50px;}
                    .rank-table li {display: -webkit-flex;display:-webkit-box;:flex;font-size:15px;line-height: 50px;}
                    .rank-first {
                        background: url(/mbem/mcrm/business/images/icon_goldmedal.png) no-repeat 12px center;
                        background-size: 28px 28px;
                    }
                    .rank-second {
                        background: url(/mbem/mcrm/business/images/icon_silvermedal.png) no-repeat 12px center;
                        background-size: 28px 28px;
                    }
                    .rank-third {
                        background: url(/mbem/mcrm/business/images/icon_bronzemedal.png) no-repeat 12px center;
                        background-size: 28px 28px;
                    }
                    .rank-score {
                        text-align: right;
                        padding-right:12px;
                    }
                    .rank-default {
                        font-size: 18px;
                        color: #777;
                        text-align: center;
                    }
                    .tb-rank-num {
                        margin-right: 5px;
                    }
                    li.not-yunke-team {
                        font-size: 14px;
                        color:#888;
                    }
                    .rank-special {
                        background: url(/mbem/mcrm/business/images/icon_star.png) no-repeat center center;
                        background-size: 14px 14px;
                    }
                    li.not-yunke-team  .rank-score{
                        text-align: left;
                        padding-left: 12px;
                    }
	             </style>
	             <!--单个可切换榜单-->
	            <div class="rank-panel">
	              <div class="rank-head clearfix">
	                  <div class="rank-bg"></div>
	                  <div class="rank-banner">
	                      <div class="decorate-left"></div>
	                      <div class="decorate-right"></div>
	                      <div class="rank-title">团队月度业绩排行榜</div>
	                  </div>
	              </div>
	              <div class="rank-list clearfix border-1px">
	                  <div class="rank-by active">
	                      <div class="center">金额</div>
	                  </div>
	                  <div class="rank-by">
	                      <div class="center">
	                        	  套数
	                      </div>
	                  </div>
	              </div>
	              
	              <!-- 排名开始 -->
	              <div class="switch-panel">
	                  <ul class="rank-table">
		                  <c:forEach items="${My_TeamMonthPerformanceRankingByMoney}" var="teamRanking" varStatus="status">
		                  	  <li class="clearfix border-1px">
		                          <div class="rank-first"></div>
		                          <div class="rank-name" style="-webkit-box-flex:1;">${teamRanking.groups }</div>
		                          <div class="rank-score" style="webkit-box-flex:2">
		                          <span class="tb-rank-num">${teamRanking.salesAmount }</span>万元</div>
		                      </li>
		                   </c:forEach>
	           		 </ul>
	              </div>
	              <!--一个榜单结束 -->
	              <div class="switch-panel" style="display:none">
	                  <ul class="rank-table">
	                 	 <c:forEach items="${My_TeamMonthPerformanceRankingByTs}" var="teamRanking" varStatus="status" >
	                  		 <li class="clearfix border-1px">
								<div class="rank-first"></div>
								<div class="rank-name" style="-webkit-box-flex:1;">${teamRanking.groups }</div>
								<div class="rank-score" style="webkit-box-flex:2">
								<span class="tb-rank-num">${teamRanking.ts }</span>套</div>
							 </li>
						 </c:forEach>
					 </ul>
	              </div>
	              <!--结束 -->
	          </div>
	          	        <!--单个可切换榜单-->
	                	<!--单个可切换榜单-->
            <div class="rank-panel">
                <div class="rank-head clearfix">
                    <div class="rank-bg"></div>
                    <div class="rank-banner">
                        <div class="decorate-left"></div>
                        <div class="decorate-right"></div>
                        <div class="rank-title">个人月度业绩排行榜</div>
                    </div>
                </div>
                <div class="rank-list clearfix border-1px">
                    <div class="rank-by active">
                        <div class="center">金额</div>
                    </div>
                    <div class="rank-by">
                        <div class="center">
                           	 套数
                        </div>
                    </div>
                </div>
                
                <!-- 排名开始 -->
                <div class="switch-panel">
               	 <ul class="rank-table">
                     <c:forEach items="${My_YWYMonthPerformanceRanking}" var="ywyRanking" varStatus="status" >
                        <li class="clearfix border-1px">
                            <div class="rank-first"></div>
                            <div class="rank-name" style="-webkit-box-flex:1;">${ywyRanking.ywy }</div>
                            <div class="rank-score" style="webkit-box-flex:2">
                            <span class="tb-rank-num">${ywyRanking.salesAmount }</span>万元</div>
                        </li>
                     </c:forEach>     
                    </ul>
                </div>
                <!--一个榜单结束 -->
                <div class="switch-panel" style="display:none">
                    <ul class="rank-table">
	                    <c:forEach items="${My_YWYMonthPerformanceRanking}" var="ywyRanking" varStatus="status" >
	                        <li class="clearfix border-1px">
	                            <div class="rank-first"></div>
	                            <div class="rank-name" style="-webkit-box-flex:1;">${ywyRanking.ywy }</div>
	                            <div class="rank-score" style="webkit-box-flex:2">
	                            <span class="tb-rank-num">${ywyRanking.ts }</span>套</div>
	                        </li>
	                    </c:forEach>     
                    </ul>
                </div>
                <!--结束 -->
            </div>
        </div> 
        <!--单个可切换榜单-->
            <style>
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
</style>
<!-- <div class="powered" id="powered">
    Powered&nbsp;by&nbsp;&nbsp;<a href="#">兆盛地产</a>
</div> -->
    </div>
    <div  style="height:40px; width:100%"></div>
    <style>
        .prompt-mask {
            position: fixed;
            background:rgba(33,33,33,0.78);
            width:100%;
            height:500px;
            top:0;
            left:0;
            height: 100%;
        }
        .prompt-sale-target {
            width:232px;
            height:82px;
            background: url(/mbem/mcrm/business/images/guide_step_03.png) no-repeat top right;
            background-size: 232px 82px; 
            position:absolute;
            right:15px;
            top:13px;
        }
        .prompt-slider {
            width:100%;
            height:100px;
            background: url(/mbem/mcrm/business/images/guide_step_06.png) no-repeat  center;
            background-size: 270px 100px; 
            position:absolute;
        }
        .prompt-iknow {
            width:100%;
            height:30px;
            background: url(/mbem/mcrm/business/images/button_know.png) no-repeat  center;
            background-size: 95px 30px; 
            position:absolute;
        }
    </style>
    <div  style="display:none;z-index:10001;">
        <div class="prompt-sale-target">
        </div>
        <div class="prompt-slider"></div>
        <div class="prompt-iknow"></div>
    </div>
    <style>
        .navigation_foot{position:fixed;bottom:0;}
    </style>
    <script type="text/javascript">
    var body=$("body"),sended=0;
    var statistics=statistics||{};
    statistics.proj_id=body.attr("data-projid");
    statistics.pageid=body.attr("data-pageid");
    statistics.token=body.attr("data-token");
    statistics.p1=body.attr("data-php");
    statistics.p2=body.attr("data-ram");
    function onWeixinReady() {
        WeixinJSBridge.invoke('getNetworkType', {}, function (e) {
            WeixinJSBridge.log(e.err_msg);
            network = e.err_msg.split(":")[1];
            statistics.network= network;
            clearTimeout(statistics.setid);
            if(!sended){
                sendlog(statistics);
            }
        })}
    $(function(){
        statistics.t2= +new Date()-statistics.start;
        $(window).bind("load",function(){
            document.addEventListener("WeixinJSBridgeReady", onWeixinReady, false);
            statistics.t3=+new Date()-statistics.start;
            statistics.t4=document.documentElement.innerHTML.length;
            statistics.t5=document.cookie.length;
            statistics.navtype=performance&&performance.navigation&&performance.navigation.type;
            if(statistics.navtype==null){statistics.navtype=4}
            statistics.setid=setTimeout(function(){
                sendlog(statistics);
            },3000);
        });
    });
    function sendlog(obj){
        var url="http://112.124.8.6/stat?";
        for(var key in obj){
            if(key!="setid"&&key!="start"){
                if(key.match(/t\d/)){
                    url=url+key+"="+obj[key]/1000+"&";
                }else{
                    url=url+key+"="+obj[key]+"&";
                }
            }
        }
        var img=$('<img style="height:0;width:0;position:absolute;overflow:hidden">');
        img.attr("src",url.substr(0,url.length-1));
        $("body").append(img);
        sended=1;
    }
    function sendajaxlog(obj){
        var obj= $.extend({},obj);
        obj.proj_id=statistics.proj_id;
        obj.token=statistics.token;
        obj.navtype=statistics.navtype;
        if(statistics.network){
            obj.network=statistics.network;
        }
        var url="http://112.124.8.6/stat?";
        for(var key in obj){
            url=url+key+"="+obj[key]+"&";
        }
        var img=$('<img style="height:0;width:0;position:absolute;overflow:hidden">');
        img.attr("src",url.substr(0,url.length-1));
    }
</script>

    <script>
    //不知为何点击态失效
    (function($){var idarr=[],arr_acdom=[];$("body").delegate("*[data-ac]","touchstart",function(e){var me=$(this),klass=me.data("ac");idarr.push(setTimeout(function(){me.addClass(klass);arr_acdom.push(me);},80));setTimeout(function(){me.removeClass(klass)},300);});$("body").on("touchmove touchend",function(){cleardd();setTimeout(function(){for(var i=0;i<arr_acdom.length;i++){arr_acdom[i].removeClass(arr_acdom[i].data("ac"));}arr_acdom=[];},300);});$("body").delegate("*[data-ac]","tap",function(e){var me=$(this),klass=me.data("ac");me.addClass(klass);setTimeout(function(){me.removeClass(klass)},300);});function cleardd(){for(var i=0;i<idarr.length;i++){clearTimeout(idarr[i]);};idarr=[];}})(Zepto);
    </script>
<script type="text/javascript">
//滑动切换效果
var myScroll;
function attachScroll() {
    myScroll = new iScroll('scrollWrapper', {
        snap: 'li',
        vScroll:false,
        bounce:false,
        momentum: false,
        hScrollbar: false,
        onScrollEnd: function () {
                        document.querySelector('.indicator > li.active').className = '';
                        document.querySelector('.indicator > li:nth-child(' + (this.currPageX+1) + ')').className = 'active';
        }
     });
}
document.addEventListener('DOMContentLoaded', attachScroll, false);
</script>
    <script>
        //提醒设置业绩目标
    function checkTargetSetting() {
        var hasPrompted = localStorage.getItem('hasPrompted');
        var flag = true ;//表示没有设置
        $('.scroll-list>li').each(function() {
            var money = $(this).find('.num-small').first();
            if(money.text()!='0') {
                flag=false
            }
            if(hasPrompted!='true'&&flag) {
                var height = window.innerHeight;
                $('.prompt-mask').css('height',height + 'px');
                var pos = document.querySelector('.indicator').getBoundingClientRect();
                var topSlider = pos.top - 8;
                var topIknow = topSlider + 150;;
                document.querySelector('.prompt-slider').style.top = topSlider + 'px';
                document.querySelector('.prompt-slider').style.left = 3 + 'px';
                document.querySelector('.prompt-iknow').style.top = topIknow + 'px';
                $('.prompt-mask').show();
                localStorage.setItem('hasPrompted','true');
                $('.prompt-mask').tap(function(){$(this).hide()}).on('touchmove',function(e){e.preventDefault();});
            }
        });
    }
    checkTargetSetting();
    //切换面板
    (function($){
        $('.rank-by').tap(function(){
        var index=$(this).index();
        if(!$(this).hasClass('active')) {
            $(this).closest('.rank-panel').find('.rank-by').removeClass('active');
            $(this).addClass('active');
          $(this).closest('.rank-panel').find('.switch-panel').hide().eq(index).show();
        }
    })
})(Zepto)
            </script>

</body></html>
<%@ include file="/WEB-INF/jsp/mbem/mcrm/house/pub/footer.jsp" %>