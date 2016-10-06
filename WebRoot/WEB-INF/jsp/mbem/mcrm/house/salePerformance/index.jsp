<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html><html>
<head lang="en-US">
    <title></title>
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/bmin.css">
</head>
<body>
<div class="body">
    <div class="datemenu">
        <ul class="datelist cls">
        	            <li data-ac="active" class="" range="all"><span>累计</span></li>
                        <li data-ac="active" class=" selected" range="week"><span>本周</span></li>
                        <li data-ac="active" class="" range="month"><span>本月</span></li>
                        <li data-ac="active" class="" range="year"><span>本年</span></li>
                        <li data-ac="active" class="" range="other"><span>自定义</span></li>
            
        </ul>
            </div>
    <div class="phone-agent cls">
        <div class="block" data-ac="active">
            <div class="wrap click_href" cst_type="call">
                <img src="/mbem/mcrm/business/images/index_call.png">
                <span class="num"> 29</span>
                <span class="txt">来电</span>
            </div>
        </div>
        <div class="block" data-ac="active">
            <span class="arrow"></span>
            <div class="wrap click_href" cst_type="visitor">
                <img src="/mbem/mcrm/business/images/index_visit.png">
                <span class="num">30</span>
                <span class="txt">来访</span>
            </div>
        </div>
    </div>
    <div class="card" id="intentContent"> <div class="title border-1px click_href" data-ac="active" cst_type="intent">
        <h4>意向客户</h4><span class="num">28</span>
        </div>
        <ul class="list cls ">
        	            <li data-ac="active" class="click_href" cst_type="intent_4">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_4.png">
                	                    <span class="num">2</span>
                    <span class="name">无意向</span>
                </div>
            </li>
                        <li data-ac="active" class="click_href" cst_type="intent_3">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_3.png">
                	                    <span class="num">19</span>
                    <span class="name">一般意向</span>
                </div>
            </li>
                        <li data-ac="active" class="click_href" cst_type="intent_2">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_2.png">
                	                    <span class="num">7</span>
                    <span class="name">高意向</span>
                </div>
            </li>
                        <li data-ac="active" class="click_href" cst_type="intent_1">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_1.png">
                	                    <span class="num">0</span>
                    <span class="name">必买</span>
                </div>
            </li>
                                </ul>
        <ul class="list2">
            <li data-ac="active" class="border-1px click_href" cst_type="lose"><span class="name">无效客户</span><span class="num">0</span></li>
            <li data-ac="active" class="border-1px click_href" cst_type="order"><span class="name">认筹客户</span><span class="num">0</span></li>
            <li data-ac="active" class="border-1px click_href" cst_type="rengou"><span class="name">认购客户</span><span class="num">2</span></li>
            <li data-ac="active" class="border-1px click_href" cst_type="qianyue"><span class="name">签约客户</span><span class="num">0</span></li>
        </ul></div>
    <div class="card card1">
        <h4 class="border-1px"><img src="/mbem/mcrm/business/images/icon_analysis.png"><span class="txt">增长趋势分析</span></h4>
        <div class="table-wrap">
            <div class="title"><span class="name">来电客户</span>
            <span class="time">本周</span></div>
            <div class="canvasdiv1" id="canvasdiv1"><div id="shell-area2d_8381501512774" style="padding: 0px; margin: 0px auto; overflow: hidden; position: relative; width: 757px; height: 473.125px;"><canvas id="area2d_8381501512774" style="width: 757px; height: 473.125px;" width="757" height="473"><p>Your browser does not support the canvas element</p></canvas><div style="position: absolute; z-index: 999; width: 0px; height: 0px; top: 44.0625px; left: 89.5px; visibility: visible; opacity: 1;"><div style="width: 604px; height: 1px; position: absolute; top: 346px; background-color: rgb(52, 204, 166);"></div><div style="width: 1px; height: 385px; position: absolute; left: 301.5px; background-color: rgb(52, 204, 166);"></div></div><div style="border: 0px solid rgb(204, 204, 204); border-radius: 5px; position: absolute; z-index: 0; color: rgb(255, 255, 255); padding: 2px 5px; opacity: 1; -webkit-transition: opacity 0.3s ease-out 0s, top 0.1s ease-out 0s, left 0.1s ease-out 0s; transition: opacity 0.3s ease-out 0s, top 0.1s ease-out 0s, left 0.1s ease-out 0s; top: 338.5625px; left: 346.5px; visibility: visible; background-color: rgb(52, 204, 166);">11-26<br>2人</div></div></div>
        </div>
        <div class="table-wrap">
            <div class="title"><span class="name">来访客户</span><span class="time">本周</span></div>
            <div class="canvasdiv2" id="canvasdiv2"><div id="shell-area2d_9868501512879" style="padding: 0px; margin: 0px auto; overflow: hidden; position: relative; width: 757px; height: 473.125px;"><canvas id="area2d_9868501512879" style="width: 757px; height: 473.125px;" width="757" height="473"><p>Your browser does not support the canvas element</p></canvas><div style="position: absolute; z-index: 999; width: 0px; height: 0px; top: 44.0625px; left: 89.5px; visibility: visible; opacity: 1;"><div style="width: 604px; height: 1px; position: absolute; top: 384.5px; background-color: rgb(52, 204, 166);"></div><div style="width: 1px; height: 385px; position: absolute; left: 301.5px; background-color: rgb(52, 204, 166);"></div></div><div style="border: 0px solid rgb(204, 204, 204); border-radius: 5px; position: absolute; z-index: 0; color: rgb(255, 255, 255); padding: 2px 5px; opacity: 1; -webkit-transition: opacity 0.3s ease-out 0s, top 0.1s ease-out 0s, left 0.1s ease-out 0s; transition: opacity 0.3s ease-out 0s, top 0.1s ease-out 0s, left 0.1s ease-out 0s; top: 377.0625px; left: 346.5px; visibility: visible; background-color: rgb(24, 201, 201);">11-26<br>0人</div></div></div>
        </div>
    </div>
</div>
<!--<script type="text/javascript" src="js/base.min.js"></script>-->
<script src="/mbem/mcrm/business/js/ichart.min.js"></script>
<!-- 自定义时间控件 -->
<style>
    .twotimehide{display:none!important;}
    .time-wrap{position:relative;z-index: 1001;display: none;width:100%;overflow:hidden;}
    .time-wrap ul{background-color:#fff;margin-top:12px;}
    .time-wrap li{height:44px;line-height:44px;margin:0 12px;color:#333;position:relative;padding-left:82px;background: url(/mbem/mcrm/business/images/handover_right_default.png) no-repeat 98% center;background-size:9px 14px;-webkit-background-size:9px 14px;}
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
<script>
    $(function(){
        var cur_url = "/Sales/CustomerAnalysis/getTrendByCall?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=week";
        var cur_url2 = "/Sales/CustomerAnalysis/getTrendByVisitor?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=week";
        var cur_url3 = "/Sales/CustomerAnalysis/getVisitorStageCount?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=week";
       
        $.ajax({
            url:cur_url3,
            dataType:"html",
            success:function(data){
            	$('#intentContent').html(data);
            	$(".click_href").on('click',function(o){
                    var num = $(this).find(".num").text();
                    if(num<=0){
                        MYSOFT.Ui.Popup.ShowAlert("提示","该项数据不存在，无法查看",null);
                        return;
                    }
            		var cst_type = $(this).attr('cst_type');
        			var url = "/Sales/CustomerAnalysis/feature?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=week"
        			+"&cst_type="+cst_type
        			window.location.href = url;
            	});
            }
        });
        
        renderChart(cur_url,"canvasdiv1","#34cca6","#d0e8d5");
        renderChart(cur_url2,"canvasdiv2","#18c9c9","#9bf5fd");
        function renderChart(cur_url,id,color1,color2){
            $.ajax({
                url:cur_url,
                dataType:"json",
                success:function(data){
                    if(data.status==0){
                        var datalist = data.data.series[0].data;
                        var cur_label = data.data.categories;
                        var cur_value= [];
                        var cur_names=[];
                        for(var i=0;i<datalist.length;i++){
                            cur_value.push(datalist[i].y);
                            cur_names.push(datalist[i].name);
                        }
                        drawChart(cur_value,cur_names,cur_label,id,color1,color2);
                    }else if(data.status==1){
                        $("#"+id).append("<p>暂无数据</p>");
                    }else{
                        MYSOFT.Ui.Popup.ShowAlert("提示","获取数据异常",null);
                    }
                }
            });
        }
        function drawChart(value,cur_names,labels,id,color1,color2){
            /*if(value.length==1&&value[0]==0){
                $("#"+id).append("<p>暂无数据</p>");
               // return;
            }
            var value=[1];*/
            var w = $(window).width();
            var data = [
                {
                    value:value,
                    color:color1,
                    area_color:color2,
                    line_width:1
                }
            ];
            var ydata ={
                position:'left',
                scale_enable:false,//禁用小横线
                listeners:{
                    parseText:function(t,x,y){
                        //return {text:t+"℃"}
                        return {text:parseInt(t,10)+"人"}
                    }
                },
                label:{color:'#8e8e8e',font:'微软雅黑'}
            }
            var max_num =0,line_num = 4;
            for(var i =0;i<data[0].value.length;i++){
                if(max_num<data[0].value[i]){
                    max_num = data[0].value[i];
                }
            }
            if(max_num<10){
                line_num = max_num+1;
                ydata.scale_space=1;
            }

            var chart = new iChart.Area2D({
                offsetx:25,
                render : id,
                border:0,
                data: data,
                width : w,
                height : w/320*200,
                tip:{
                    border:{
                        enable:true,
                        radius : 5,
                        width:0,
                        color:'#ccc'
                    },
                    style:"background-color:"+color1+";color:#fff;padding:2px 5px;",
                    enable : true,
                    listeners:{
                        //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
                        parseText:function(tip,name,value,text,i){
                            //return name+"地区<br/>:"+labels[i]+"平均温度:"+value+"℃";
                            return cur_names[i]+"<br/>"+value+"人";
                        }
                    }
                },
                crosshair:{
                    enable:true,
                    line_color:'#34cca6',
                    line_width:1
                },
                sub_option:{
                    label:false,
                    point_size:10
                },
                background_color:'#ffffff',
                coordinate:{
                    axis : {
                        color:"#E3E7ED",
                        width : [0, 0, 1, 0]
                    },
                    background_color:'#ffffff',
                    height:'85%',
                    width:"82%",
                    offsetx:-12,
                    scale2grid:false,
                    grid_line_width:1,
                    grids:{
                       horizontal:{
                            way:'share_alike',
                            value:line_num
                        }

                    },
                    scale:[ydata,{
                        position:'bottom',
                        parseText:function(t,x,y){
                            return {textY:y+10}
                        },
                        label:{color:'#8e8e8e',font:'微软雅黑'},
                        labels:labels
                    }]
                }
            });
            chart.draw();
        }
    });
    $(function(){
    	//数字点击后跳转到客户特征中
    	$(".click_href").on('click',function(o){
            var num = $(this).find(".num").text();
            if(num<=0){
                MYSOFT.Ui.Popup.ShowAlert("提示","该项数据不存在，无法查看",null);
                return;
            }
    		var cst_type = $(this).attr('cst_type');
			var url = "/Sales/CustomerAnalysis/feature?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=week"
			+"&cst_type="+cst_type
			window.location.href = url;
    	});
    	$(".datelist li").on('click',function(o){
    		var range = $(this).attr('range');
    		var appoint = null;
    		if(range=='other'){
    			//自定义时间
    			$.showTwoTime(function(btime,etime){
    				appoint = btime+'~'+etime;
    				var url = "/Sales/customerAnalysis/index?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557"
    	    			+"&range="+range+"&appoint="+appoint;
                    setTimeout(function(){window.location.replace(url);},10);

    			});
    		}else{
    			var url = "/Sales/customerAnalysis/index?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557"
        			+"&range="+range;
    			window.location.replace(url);
    		}
    	});
    })
</script>

</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html><strong></strong>