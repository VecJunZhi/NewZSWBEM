<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<title>ҵ�����</title>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/mbem/mcrm/business/css/yjdc.css">
<link rel="stylesheet" href="/mbem/mcrm/business/css/base.css">
<script src="/common/js/jquery-1.9.1.min.js"></script>
<script src="/mbem/mcrm/business/js/base.min.js"></script>
 <script type="text/javascript">
    var statistics={}
    statistics.start=+new Date();
</script>   
<script>
        var _type= "0";
        var _guid = "";
        var _tjwd = "";
        var m_value, m_guid, q_value, q_guid;
        //var m_ts_value, m_ts_guid, q_ts_value, q_ts_guid;
        $(function(){
            //myScroll = new iScroll('wrapper_1');
        });
        //function OpenTargetModify() {
        //    //�¶�
        //    m_guid = $("#targetM").attr("guid");
        //    m_value = $("#targetM").text().replace(/\s+/g, '').replace(/\,/g, "");
        //    //����
        //    q_guid = $("#targetQ").attr("guid");            
        //    q_value = $("#targetQ").text().replace(/\s+/g, '').replace(/\,/g, "");
                     

        //    $("#update_box").showPopWin({maskSetting:true});
        //    $("#value_m_input").val(m_value);
        //    $("#value_q_input").val(q_value);
        //}
</script>
    <script>
        statistics.t1=+new Date()-statistics.start;
    </script>
<script>
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
</script>
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
                                    <div class="budget-title">Ԥ��������(�¶�)</div>
                                    <div class="icon-budget-target-edit"> <!--�������ã����Զ�λ-->
                                    	<a href="#" data-ac="edit-active" class="icon-pen"></a>
                                     </div>
                                </div>
                                <div class="budget-detail clearfix">
                                    <!--��������������ѭ��-->

                                        <a href="/t1.action" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">���(��Ԫ)</div>
                                        <div class="list-num"><span class="num-big">${budgetMonth.salesAmount }</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                        <a href="/t2.action" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">����(��)</div>
                                        <div class="list-num"><span class="num-big">${budgetMonth.ts}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="/t3.action" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">���(�O)</div>
                                        <div class="list-num"><span class="num-big">${budgetMonth.bldArea}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="/t4.action" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">�ؿ�(��Ԫ)</div>
                                        <div class="list-num"><span class="num-big">${budgetMonth.backAmount}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <!--end-->
                                </div>
                            </li>
                                                        <li>
                                <div class="budget-head">
                                    <div class="budget-title">Ԥ��������(����)</div>
                                    <div class="icon-budget-target-edit"> <!--�������ã����Զ�λ-->
                                    	<a href="#" class="icon-pen"></a>
                                     </div>
                                </div>
                                <div class="budget-detail clearfix">
                                    <!--��������������ѭ��-->
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">���(��Ԫ)</div>
                                        <div class="list-num"><span class="num-big">${budgetSeason.salesAmount}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">����(��)</div>
                                        <div class="list-num"><span class="num-big">${budgetSeason.ts}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">���(�O)</div>
                                        <div class="list-num"><span class="num-big">${budgetSeason.bldArea}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">�ؿ�(��Ԫ)</div>
                                        <div class="list-num"><span class="num-big">${budgetSeason.backAmount}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <!--end-->
                                </div>
                            </li>
                                                        <li>
                                <div class="budget-head">
                                    <div class="budget-title">Ԥ��������(���)</div>
                                    <div class="icon-budget-target-edit"> <!--�������ã����Զ�λ-->
                                    	<a href="#" class="icon-pen"></a>
                                     </div>
                                </div>
                                <div class="budget-detail clearfix">
                                    <!--��������������ѭ��-->
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">���(��Ԫ)</div>
                                        <div class="list-num"><span class="num-big">${budgetYear.salesAmount}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="#" class="budget-list" data-ac="budget-active" style="width:50%;">
                                        <div class="list-item">����(��)</div>
                                        <div class="list-num"><span class="num-big">${budgetYear.ts}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">���(�O)</div>
                                        <div class="list-num"><span class="num-big">${budgetYear.bldArea}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                         </div>
                                    </a>
                                                                        <a href="#" style="width:50%;" class="budget-list">
                                        <div class="list-item">�ؿ�(��Ԫ)</div>
                                        <div class="list-num"><span class="num-big">${budgetYear.backAmount}</span><span class="num-small">0</span></div>
                                        <div class="progress clearfix">
                                            <div class="bar">
                                               <div class="inner-bar" style="width:0%"></div>
                                            </div>
                                            <div class="percent">0%</div>
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
            
            
            
            
            
            <!--���а� -->
	             <!--�������л���-->
	            <div class="rank-panel">
	              <div class="rank-head clearfix">
	                  <div class="rank-bg"></div>
	                  <div class="rank-banner">
	                      <div class="decorate-left"></div>
	                      <div class="decorate-right"></div>
	                      <div class="rank-title">�Ŷ��¶�ҵ�����а�</div>
	                  </div>
	              </div>
	              <div class="rank-list clearfix border-1px">
	                  <div class="rank-by active">
	                      <div class="center">���</div>
	                  </div>
	                  <div class="rank-by">
	                      <div class="center">
	                        	  ����
	                      </div>
	                  </div>
	              </div>
	              
	              <!-- ������ʼ -->
	              <div class="switch-panel">
	                  <ul class="rank-table">
		                  <c:forEach items="${TeamMonthPerformanceRankingByMoney}" var="teamRanking" varStatus="status">
		                  	  <li class="clearfix border-1px">
		                          <div class="rank-first"></div>
		                          <div class="rank-name" style="-webkit-box-flex:1;">����${teamRanking.groups }��</div>
		                          <div class="rank-score" style="webkit-box-flex:2">
		                          <span class="tb-rank-num">${teamRanking.salesAmount }</span>��Ԫ</div>
		                      </li>
		                   </c:forEach>
	           		 </ul>
	              </div>
	              <!--һ���񵥽��� -->
	              <div class="switch-panel" style="display:none">
	                  <ul class="rank-table">
	                 	 <c:forEach items="${TeamMonthPerformanceRankingByTs}" var="teamRanking" varStatus="status" >
	                  		 <li class="clearfix border-1px">
								<div class="rank-first"></div>
								<div class="rank-name" style="-webkit-box-flex:1;">${teamRanking.groups }</div>
								<div class="rank-score" style="webkit-box-flex:2">
								<span class="tb-rank-num">${teamRanking.ts }</span>��</div>
							 </li>
						 </c:forEach>
					 </ul>
	              </div>
	              <!--���� -->
	          </div>
	          	        <!--�������л���-->
	                	<!--�������л���-->
            <div class="rank-panel">
                <div class="rank-head clearfix">
                    <div class="rank-bg"></div>
                    <div class="rank-banner">
                        <div class="decorate-left"></div>
                        <div class="decorate-right"></div>
                        <div class="rank-title">�����¶�ҵ�����а�</div>
                    </div>
                </div>
                <div class="rank-list clearfix border-1px">
                    <div class="rank-by active">
                        <div class="center">���</div>
                    </div>
                    <div class="rank-by">
                        <div class="center">
                           	 ����
                        </div>
                    </div>
                </div>
                
                <!-- ������ʼ -->
                <div class="switch-panel">
                    <ul class="rank-table">
                     <c:forEach items="${YWYMonthPerformanceRankingByMoney}" var="ywyRanking" varStatus="status" >
                        <li class="clearfix border-1px">
                            <div class="rank-first"></div>
                            <div class="rank-name" style="-webkit-box-flex:1;">${ywyRanking.ywy }</div>
                            <div class="rank-score" style="webkit-box-flex:2">
                            <span class="tb-rank-num">${ywyRanking.salesAmount }</span>��Ԫ</div>
                        </li>
                     </c:forEach>     
                    </ul>
                </div>
                <!--һ���񵥽��� -->
                <div class="switch-panel" style="display:none">
                    <ul class="rank-table">
	                    <c:forEach items="${YWYMonthPerformanceRankingByTs}" var="ywyRanking" varStatus="status" >
	                        <li class="clearfix border-1px">
	                            <div class="rank-first"></div>
	                            <div class="rank-name" style="-webkit-box-flex:1;">${ywyRanking.ywy }</div>
	                            <div class="rank-score" style="webkit-box-flex:2">
	                            <span class="tb-rank-num">${ywyRanking.ts }</span>��</div>
	                        </li>
	                    </c:forEach>     
                    </ul>
                </div>
                <!--���� -->
            </div>
        </div> 
        <!--�������л���-->
<div class="powered" id="powered">
    Powered&nbsp;by&nbsp;&nbsp;<a href="#">��ʢ�ز�</a>
</div>
    </div>
    <div class="prompt-mask" style="display:none;z-index:10001;">
        <div class="prompt-sale-target">
        </div>
        <div class="prompt-slider"></div>
        <div class="prompt-iknow"></div>
    </div>
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
<!--�ٶ�ͳ�����ڹ��ٳ����⣬���µ�
<script type="text/javascript" class="baidutongji">
    var _hmt = _hmt || [];
    window.addEventListener("load",function(){

        setTimeout(function(){
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?5abe2b32625096fde52115cbcbff8b7f";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        },100);
    });
</script>-->

<script>
    //��֪Ϊ�ε��̬ʧЧ
    (function($){var idarr=[],arr_acdom=[];$("body").delegate("*[data-ac]","touchstart",function(e){var me=$(this),klass=me.data("ac");idarr.push(setTimeout(function(){me.addClass(klass);arr_acdom.push(me);},80));setTimeout(function(){me.removeClass(klass)},300);});$("body").on("touchmove touchend",function(){cleardd();setTimeout(function(){for(var i=0;i<arr_acdom.length;i++){arr_acdom[i].removeClass(arr_acdom[i].data("ac"));}arr_acdom=[];},300);});$("body").delegate("*[data-ac]","tap",function(e){var me=$(this),klass=me.data("ac");me.addClass(klass);setTimeout(function(){me.removeClass(klass)},300);});function cleardd(){for(var i=0;i<idarr.length;i++){clearTimeout(idarr[i]);};idarr=[];}})(Zepto);
    </script>
<script type="text/javascript">
//�����л�Ч��
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
        //��������ҵ��Ŀ��
    function checkTargetSetting() {
        var hasPrompted = localStorage.getItem('hasPrompted');
        var flag = true ;//��ʾû������
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
    //�л����
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

</body>
</html>