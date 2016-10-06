<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<title>���ͷ���</title> 
<link rel="stylesheet" href="/mbem/mcrm/business/css/bmin.css">
<script  src="/common/js/jquery-1.9.1.min.js"></script>
<script src='http://www.ichartjs.com/ichart.latest.min.js'></script>
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
    	
    
    $(document).ready(function(){
    	 	var reportType = $("#reportType").val();
    		$("#"+reportType).addClass("selected"); 
    		
    		$(".datelist li").click(function(){
    			var id = $(this).attr("id");
    			if(id != "range")
    				window.location.replace("/mbem/mcrm/house/custAnalysis/index.action?reportType="+id);
    			else
    				window.location.replace("/mbem/mcrm/house/custAnalysis/custAnalysisSearch.action");
    		});
    		$(".click_href").click(function(){
                var num = $(this).find(".num").text();
                if(num<=0){
                    MYSOFT.Ui.Popup.ShowAlert("��ʾ","�������ݲ����ڣ��޷��鿴",null);
                    return;
                }
    			var val = $(this).attr("cst_type");
    			//alert(val);
    			window.location.replace("/mbem/mcrm/house/custAnalysis/feature.action?cstType="+val+"&reportType="+reportType);
    		});
    	});
    </script>
       <script type="text/javascript">
       var callVal = ${callGraphInfo};
	 	var visitVal = ${visitGraphInfo};
	 	var labelVal = ${labelVal};
	 $(function(){
		 	//var val = [12,20,26,30,35,80];
		 	//alert(val);
		 	
			/* var data = [
			        	{
			        		name : '����',
			        		value:val,//[12,20,26,30,32,29,22],
			        		color:'#3c6',
			        		line_width:3
			        	}
			       ]; */
			var chart = new iChart.LineBasic2D({
						render : 'canvasDiv',
						data: callVal,
						title : '',
						width : 330,
						height : 300,
						coordinate:{height:'90%',width:'84%',background_color:'#fff'},
						sub_option:{
							hollow_inside:false,//����һ�������ɫ���⻷��Ч��
							point_size:16
						},
						labels:labelVal//["12-01","12-02","12-03","12-04","12-05","12-06","12-07"]
					});
			chart.draw();
		});
		 $(function(){
			var data = [
			        	{
			        		name : '�ͻ�',
			        		value:[12,20,26,30,32,29,22],
			        		color:'#3c6',
			        		line_width:3
			        	}
			       ];
			var chart = new iChart.LineBasic2D({
						render : 'canvasDiv1',
						data: visitVal,
						title : '',
						width : 330,
						height : 300,
						coordinate:{height:'90%',width:'84%',background_color:'#fff'},
						sub_option:{
							hollow_inside:false,//����һ�������ɫ���⻷��Ч��
							point_size:16
						},
						labels:labelVal//["12-01","12-02","12-03","12-04","12-05","12-06","12-07"]
					});
			chart.draw();
		});
				
			</script>
</head>
<body>

    <div class="datemenu">
        <ul class="datelist cls">
        	            <li id="total" data-ac="active" class="" range="total"><span>�ۼ�</span></li>
                        <li id="week" data-ac="active" class="" range="week"><span>����</span></li>
                        <li id="month" data-ac="active" class="" range="month"><span>����</span></li>
                        <li id="year" data-ac="active" class="" range="year"><span>����</span></li>
                        <li id="range" data-ac="active" class="" range="other"><span>�Զ���</span></li>
            			<input id="reportType" type="hidden" value="${cstReportType}">
        </ul>
            </div>
    <div class="phone-agent cls">
        <div class="block" data-ac="active">
            <div class="wrap click_href" cst_type="call">
                <img src="/mbem/mcrm/business/images/index_call.png">
                <span class="num">${analysisInfo.call}</span>
                <span class="txt">����</span>
            </div>
        </div>
        <div class="block" data-ac="active">
            <span class="arrow"></span>
            <div class="wrap click_href" cst_type="visit">
                <img src="/mbem/mcrm/business/images/index_visit.png">
                <span class="num">${analysisInfo.visit}</span>
                <span class="txt">����</span>
            </div>
        </div>
    </div>
    <div class="card" id="intentContent"> <div class="title border-1px click_href" data-ac="active" cst_type="intention">
        <h4>����ͻ�</h4><span class="num">${analysisInfo.intention}</span>
        </div>
        <ul class="list cls ">
        	            <li data-ac="active" class="click_href" cst_type="noIntention">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_4.png">
                	                    <span class="num">${analysisInfo.noIntention}</span>
                    <span class="name">������</span>
                </div>
            </li>
                        <li data-ac="active" class="click_href" cst_type="generalIntention">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_3.png">
                	                    <span class="num">${analysisInfo.generalIntention}</span>
                    <span class="name">һ������</span>
                </div>
            </li>
                        <li data-ac="active" class="click_href" cst_type="highIntention">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_2.png">
                	                    <span class="num">${analysisInfo.highIntention}</span>
                    <span class="name">������</span>
                </div>
            </li>
                        <li data-ac="active" class="click_href" cst_type="mustBuy">
                <div class="border-1px">
                	              		<img src="/mbem/mcrm/business/images/icon_general_1.png">
                	                    <span class="num">${analysisInfo.mustBuy }</span>
                    <span class="name">����</span>
                </div>
            </li>
                                </ul>
        <ul class="list2">
            <li data-ac="active" class="border-1px click_href" cst_type="invalidCst"><span class="name">��Ч�ͻ�</span><span class="num">${analysisInfo.invalidCst}</span></li>
            <li data-ac="active" class="border-1px click_href" cst_type="confessCst"><span class="name">�ϳ�ͻ�</span><span class="num">${analysisInfo.confessCst}</span></li>
            <li data-ac="active" class="border-1px click_href" cst_type="buyCst"><span class="name">�Ϲ��ͻ�</span><span class="num">${analysisInfo.buyCst}</span></li>
            <li data-ac="active" class="border-1px click_href" cst_type="signUpCst"><span class="name">ǩԼ�ͻ�</span><span class="num">${analysisInfo.signUpCst}</span></li>
        </ul></div>
    <div class="card card1">
        <h4 class="border-1px"><img src="/mbem/mcrm/business/images/icon_analysis.png"><span class="txt">�������Ʒ���</span></h4>
        <div class="table-wrap">
            <div class="title"><span class="name">����ͻ�</span>
            <span class="time">${type}</span></div>
            
            <div id='canvasDiv'></div>
            
            
            
        </div>
        <div class="table-wrap">
            <div class="title"><span class="name">���ÿͻ�</span><span class="time">${type}</span></div>
            <div id='canvasDiv1'></div>
        </div>
    </div>

<script src="/mbem/mcrm/business/js/ichart.min.js"></script>
<!-- �Զ���ʱ��ؼ� -->
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
        <li data-ac="active" class="border-1px"><span class="k">��ʼʱ�䣺</span><span class="v"><input class="btime" data-range="2010/1/1-2018/12/30" type="text" readonly></span></li>
        <li data-ac="active"><span class="k">����ʱ�䣺</span><span class="v"><input class="etime" data-range="2010/1/1-2018/12/30" type="text" readonly></span></li>
    </ul>
    <div class="btns">
        <span data-ac="active" class="cancel">ȡ��</span>
        <span data-ac="active" class="sure">ȷ��</span>
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
                                <div class="sure date-btn" data-ac="active"><span>ȷ&nbsp;��</span></div>\
                        </div>\
                        </div>\
                        </div></div>';
            me.bind("click",function(){
                if(clicklock) return;
                clicklock = 1;
                //ʹ�����ı���ʧȥ����(������뷨û�йر�ʱ�����ʱ��ؼ�ҳ���λbug)
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
                var arr=["����","��һ","�ܶ�","����","����","����","����"];
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
    /*������Ч��*/
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
     * @description MYSOFT ȫ�ֶ��󣬸���ǰ�˵Ľ�����֯
     * @namespace ȫ�ֵ������ռ�
     */
    MYSOFT = window.MYSOFT || {};

    /**
     * @description MYSOFT������󣬸���ǰ�˵Ĺ����ؼ�
     * @namespace MYSOFT����������ռ�
     */
    MYSOFT.Ui = window.MYSOFT.Ui || {};

    /**
     * @description MYSOFTȫ������
     * @namespace MYSOFTȫ�����õ������ռ�
     */
    MYSOFT.Config = window.MYSOFT.Config || {};

    /**
     * @description MYSOFT����
     * @namespace MYSOFT���ߵ������ռ�
     */
    MYSOFT.Util = window.MYSOFT.Util || {};

    /**
     * @description MYSOFT Dom�����ռ�,����Dom����
     * @namespace MYSOFT Dom�����ռ�
     */
    MYSOFT.Dom = window.MYSOFT.Dom || {};
    /**
     * @namespace MYSOFT�����������
     */
    $.Ui={};
    $.Ui.Popup=MYSOFT.Ui.Popup = {
        //��ʼ��Dom�����ĵ�
        Init: function () {
            if (!MYSOFT.Ui.Popup.DomObj) {
                //ȫ��ֻ��һ��ʵ��
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
         * @method ��ʾ���ض���
         * @param text �����ı�
         */
        ShowLoading: function (text) {
            MYSOFT.Ui.Popup.Init();
            var markup = MYSOFT.Ui.Popup.Template.loading.replace('{title}', text || '���ڼ���');
            MYSOFT.Ui.Popup.MaskDomObj.show().css({'opacity':'0'});
            MYSOFT.Ui.Popup.DomObj.removeAttr('class').addClass('loading').html(markup).show();
        },
        /**
         * @method ���ص�����
         */
        HidePopUp: function () {
            MYSOFT.Ui.Popup.MaskDomObj.hide();
            MYSOFT.Ui.Popup.DomObj.hide().empty();
        },
        /**
         * @method ��������ʾ
         * @param title ��ʾ����
         * @param content ��ʾ����
         * @param okCall ���ȷ���ص�����
         */
        ShowAlert: function (title, content, okCall) {
            var markup = MYSOFT.Ui.Popup.Template.alert.replace('{title}', title||'��ʾ��Ϣ').replace('{content}', content).replace('{ok}', '��֪����');
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
         * @method ȷ�Ͽ���ʾ
         * @param title ��ʾ����
         * @param content ��ʾ����
         * @param okCall ȷ���ص�����
         * @param cancelCall ȡ���ص�����
         */
        ShowConfirm: function (title, content, okCall,cancelCall,okText,cancelText) {
            okText=okText||'ȷ ��';
            cancelText=cancelText||'ȡ ��';
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
         * @object Popupģ��
         */
        Template: {
            //���ض���
            loading: '<div class="img"></div><p>{title}</p>',
            //��ʾ��
            alert: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_ok_popup">{ok}</a></div>',
            //ȷ����ʾ��
            confirm: '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a id="tag_cancel_popup">{cancel}</a><a id="tag_ok_popup">{ok}</a></div>'
        }
    };
</script>
<script type="text/javascript">
    (function($){
        var hidepanel =1;
        var timeWrap = $("#time-wrap"),callbackfunc,isclose=false;
        $.showTwoTime= function(callback,closed){
            //��һ������Ϊ��� ȷ��ʱ��Ļص��������ڶ�������Ϊ�ص���ʱ��ʱ��ر�ʱ��ؼ���塣
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
                    MYSOFT.Ui.Popup.ShowAlert("��ʾ","����ʱ��Ҫ���ڿ�ʼʱ��",null);
                }else{
                    hidepanel =0;
                    setTimeout(function(){
                        callbackfunc&&callbackfunc(btime,etime);
                    },10);
                    history.back();

                }
            }else{
                MYSOFT.Ui.Popup.ShowAlert("��ʾ","��ʼ������ʱ�䶼����Ϊ��",null);
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
/* $(function(){
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
                        MYSOFT.Ui.Popup.ShowAlert("��ʾ","�������ݲ����ڣ��޷��鿴",null);
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
        renderChart(cur_url2,"canvasdiv2","#18c9c9","#9bf5fd"); */
       /*  function renderChart(cur_url,id,color1,color2){
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
                        $("#"+id).append("<p>��������</p>");
                    }else{
                        MYSOFT.Ui.Popup.ShowAlert("��ʾ","��ȡ�����쳣",null);
                    }
                }
            });
        }
        function drawChart(value,cur_names,labels,id,color1,color2){ */
            /*if(value.length==1&&value[0]==0){
                $("#"+id).append("<p>��������</p>");
               // return;
            }
            var value=[1];*/
          /*   var w = $(window).width();
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
                scale_enable:false,//����С����
                listeners:{
                    parseText:function(t,x,y){
                        //return {text:t+"��"}
                        return {text:parseInt(t,10)+"��"}
                    }
                },
                label:{color:'#8e8e8e',font:'΢���ź�'}
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
                        //tip:��ʾ�����name:�������ơ�value:����ֵ��text:��ǰ�ı���i:���ݵ������
                        parseText:function(tip,name,value,text,i){
                            //return name+"����<br/>:"+labels[i]+"ƽ���¶�:"+value+"��";
                            return cur_names[i]+"<br/>"+value+"��";
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
                        label:{color:'#8e8e8e',font:'΢���ź�'},
                        labels:labels
                    }]
                }
            });
            chart.draw();
        }
    }); */
/*      $(function(){
    	//���ֵ������ת���ͻ�������
    	$(".click_href").on('click',function(o){
            var num = $(this).find(".num").text();
            if(num<=0){
                MYSOFT.Ui.Popup.ShowAlert("��ʾ","�������ݲ����ڣ��޷��鿴",null);
                return;
            }
    		var cst_type = $(this).attr('cst_type');
			var url = "/Sales/CustomerAnalysis/feature?proj_id=c5091504-41ae-4c2e-ae3b-69a008a5762f&token=cdycid1429859342&userid=39d038df-a82c-96a3-e06d-3537d94b8557&range=week"
			+"&cst_type="+cst_type
			window.location.href = url;
    	});  */
    	/* $(".datelist li").on('click',function(o){
    		var range = $(this).attr('range');
    		alert("111");
    		alert(range);
    		var appoint = null;
    		if(range=='other'){
    			//�Զ���ʱ��
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
    	}); */
   /*   })  */
</script>
</body></html>