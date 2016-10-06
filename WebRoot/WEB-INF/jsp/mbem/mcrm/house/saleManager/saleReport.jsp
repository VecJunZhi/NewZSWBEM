<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<title>��������</title>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"  href="/mbem/mcrm/business/css/base.min.css">   
<link rel="stylesheet"  href="/mbem/mcrm/business/css/common.css">
<script  src="/common/js/jquery-1.9.1.min.js"></script>
 <script type="text/javascript">
    var statistics={}
    statistics.start=+new Date();
</script>
<style type="text/css">
        body{background:#efefef;overflow:visible}
        h3{background:#fff;font-size:14px;font-weight:normal;line-height:28px;padding:4px 12px 0;}
        nav{background:#f7f7f7;height:62px;z-index:10;}
        nav ol{padding:18px 0 16px;margin:0 12px;font-size:0}
        nav ol li{display:inline-block;width:24.9%;color:#aaa;height:16px;line-height:16px;
            padding:6px 0;text-align:center;position:relative;}
        nav ol li a{display:block;color:#666;text-decoration:none;font-size:14px;}
        nav ol li.a_act{background:#18c9c9;color:#fff;border-radius:30px;}
        nav ol li.a_act:after{content:"";position:absolute;bottom:-17px;left:50%;margin-left:-6px;height:0;width:0;border-width:6px;border-color:transparent transparent #E8E8E8 transparent;border-style: solid;}
        nav ol li.a_act a{color:#fff;text-decoration:none;}
        .a_items{margin-bottom:12px;}
        .a_rela{position:relative;}
        .a_dotList .a_title{background:#18c9c9;}
        .a_tit_name{background:url("/mbem/mcrm/house/images/rengou.png") no-repeat 12px center;
        background-size:25px;height:25px;padding:10px 0 9px 48px;font-size:17px;color:#fff;}
        .a_dot_ul{background:#fff;}
        .a_org_title .a_circle_innner{background:url("/mbem/mcrm/house/images/dote_yellow.png") no-repeat left top;
                                      background-size:20px;width:20px;height:20px;}
        /*.a_org_title .a_title{background:#54c994;}*/
        .a_org_title .a_tit_name{background:url("/mbem/mcrm/house/images/laifang.png") no-repeat 12px center;
        background-size:25px;}
        .a_zhiqian .a_tit_name{background:url("/mbem/mcrm/house/images/zhiqian.png") no-repeat 12px center;
         background-size:25px;}
        .a_zhuanqian .a_tit_name{background:url("/mbem/mcrm/house/images/zhuanqian.png") no-repeat 12px center;
         background-size:25px;}
        .a_laidian .a_tit_name{background:url("/mbem/mcrm/house/images/laifang.png") no-repeat 12px center;
            background-size:25px;}
        .a_zhiqian .a_tit_name{background:url("/mbem/mcrm/house/images/zhiqian.png") no-repeat 12px center;
            background-size:25px;}
        .a_rengou .a_tit_name{background:url("/mbem/mcrm/house/images/rengou.png") no-repeat 12px center;
            background-size:25px;}
        .a_renchou .a_tit_name{background:url("/mbem/mcrm/house/images/renchou.png") no-repeat 12px center;
            background-size:25px;}
        .a_dot_ul dt p{font-size:16px;color:#333;}
        .a_dot_ul dd{font-size:14px;color:#999;}
        .a_dot_ul dd f{font-size:22px;color:#18c9c9;}
        .a_dot_ul dd f.dark{color:#333;}
        .a_renchou .a_dot_ul dd f{color:#54C994}
        .a_org_title dd f{color:#5acc94;}
        .a_dot_ul dt,.a_dot_ul dd{padding-left:0px;}
/*        .a_dot_ul dt{background:url("/Apps/Sales/View/default/Public/images/dote_hui.png") no-repeat left center;
                     background-size:16px;}*/
        .a_dot_ul li{padding:12px 12px 0 12px;}
        .a_dot_ul li{border-bottom-width:1px;}
        .a_dot_ul li:last-child{padding-bottom:10px;border-bottom-width:0px;}
        .a_dot_ul .a_team{color:#8e8e8e;font-size:14px;}
        .a_dot_ul .a_team p{line-height:26px;}
        .a_dot_ul .a_team p span.fl{min-width:80px;max-width:90px;height:26px;white-space:nowrap;overflow:hidden;
                                    text-overflow:ellipsis;}
        .a_dot_ul .a_team p span.fr{width:auto;height:26px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
        .a_border_bottom{clear:both;font-size:0px;height:0;border-bottom:1px solid #ddd;margin-left:32px;
                         padding-top:10px;}
        .a_team_group{color:#999;font-size:14px;margin-left:12px;}
        .a_team_count{font-size:14px;}
        .a_team_count f{color:#ff9672;font-size:22px;}
        .a_org_title .a_team_count f{color:#ffcc99;font-size:22px;}
        .a_sale_money{color:#999;font-size:14px;}
        .a_sale_money f{color:#ff9672;font-size:22px;}
        .a_org_title .a_sale_money f{color:#ffcc99;font-size:22px;}
        .a_org_title .a_sale_money f.f_fl{display:inline-block;min-width:80px;max-width:90px;text-align:right;}
        .a_pos_border{position:absolute;font-size:0;width:0;height:100px;border-left:1px solid #ddd;top:0;
                      left:20px;z-index:-1;}
        .a_tips{width:100%;text-align:center;font-size:12px;color:#555;line-height:200px;}
        .nav-detail{height:38px;line-height:38px;border-top-width:1px;background-color:#E8E8E8;font-size:14px;padding-left:16px;}
        .nav-detail .sep{display:inline-block;width:24px;height:24px;vertical-align:0px;line-height:24px;text-align:center;border-radius:12px;background-color: #666;color:#fff;font-size:12px;margin: 0 10px;}
        .end-time{color:#aaa;background-color:#fff;padding:0 12px 10px;}
        li[data-href].active{background-color:rgba(0,0,0,0.1)}
    </style>
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
    		var type = $("#reportType").val();
    		$("#"+type).addClass("a_act"); 
    	});
    </script>
    </head>
    <body>
        <div class="a_wrap">
        <h3>�����԰</h3>
        <div class="end-time">����ͳ�ƽ�ֹ��${curTime}</div>        <div class="nav">
           <nav>
                <ol class="datelist">
                    <li id="day"><a href="/mbem/mcrm/house/saleManager/saleReport.action?reportType=day" >�ձ�</a></li>
                    <li id="week"><a href="/mbem/mcrm/house/saleManager/saleReport.action?reportType=week">�ܱ�</a></li>
                    <li id="month"><a href="/mbem/mcrm/house/saleManager/saleReport.action?reportType=month" >�±�</a></li>
                    <li range="other" id="range">
                        <a href="/mbem/mcrm/house/saleManager/saleReportSearch.action">�ڼ䱨��</a>
                    </li>
                    <input id="reportType" type="hidden" value="${reportType}">
                </ol>
            </nav>            
            <div class="nav-detail border-1px">
                <span class="stime">${beginDate}</span>
                <c:if test="${reportType!='day'}">
                 <span class="sep">��</span><span class="etime">${endDate}</span>
                </c:if>
            </div>
        </div>
                 
        <div class="a_block">
            <!--���� Start-->
                <div class="a_items">
                    <div class="a_rela a_dotList a_laidian">
                        <div class="a_title clearfix">
                            <div class="a_tit_name fl">${followInfo.call.followWayName}</div>
                        </div>
                        <div class="a_dot_ul">
                            <ul>
                                <li class="clearfix border-1px">
                                    <dl class="fl" style="height: 104px;">
                                        <dt>
                                        <p>${followInfo.call.followWayName}</p>
                                        </dt>
                                        <dd>
                                            <!--
                                            <p>�Ͽͻ�6��</p>
                                            <p>��һ������ͻ� 25 ��</p>
                                            -->
                                            <p>
                                                <span>
                                                    <f class="dark">${followInfo.call.followTimes}</f>��
                                                </span>
                                            </p>
                                        </dd>
                                    </dl>
                                    <!--<div class="fr a_sale_money">
                                        <p style="text-align:right;">
                                            <span>                                                
                                                <f style="max-width:90%;" class="f_fl">31</f>��
                                            </span>
                                        </p>
                                    </div>-->   
                                    <div class="fr a_team">
                                        <p class="clearfix">
                                                <span class="fr">${followInfo.call.groupInfo.A} ��</span>
                                                <span class="fl">����A��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.call.groupInfo.B} ��</span>
                                                <span class="fl">����B��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.call.groupInfo.C} ��</span>
                                                <span class="fl">����</span>
                                            </p><p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">������</span>
                                            </p>                                                                            </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>      

                <div class="a_items">
                    <div class="a_rela a_dotList a_laidian">
                        <div class="a_title clearfix" style="background: rgb(84, 201, 148);">
                            <div class="a_tit_name fl">${followInfo.visit.followWayName}</div>
                        </div>
                        <div class="a_dot_ul">
                            <ul>
                                <li class="clearfix border-1px">
                                    <dl class="fl" style="height: 104px;">
                                        <dt>
                                        <p>${followInfo.visit.followWayName}</p>
                                        </dt>
                                        <dd>
                                            <!--<p>�Ͽͻ�19��</p>-->
                                            <p>��һ�����ÿͻ� ${firstVisitCount} ��</p>
                                            <p>
                                                <span>
                                                    <f class="dark">${followInfo.visit.followTimes}</f>
                                                    ��
                                                </span>
                                            </p>
                                        </dd>
                                    </dl>
                                    <!--<div class="fr a_sale_money">
                                        <p style="text-align:right;">
                                            <span>                                                
                                                ��һ�����ÿͻ� <f style="max-width:90%;" class="f_fl"> 22</f> ��
                                            </span>
                                        </p>
                                        <p style="text-align:right;">
                                            <span>
                                                <f style="max-width:90%;" class="f_fl">38</f> ��
                                            </span>
                                        </p>
                                    </div>-->
                                    <div class="fr a_team">
                                        <p class="clearfix">
                                                <span class="fr">${followInfo.visit.groupInfo.A} ��</span>
                                                <span class="fl">����A��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.visit.groupInfo.B} ��</span>
                                                <span class="fl">����B��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.visit.groupInfo.C} ��</span>
                                                <span class="fl">����</span>
                                            </p><p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">������</span>
                                            </p>                                                                            </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>          

                <div class="a_items">
                    <div class="a_rela a_dotList a_laidian">
                        <div class="a_title clearfix">
                            <div class="a_tit_name fl">${followInfo.toCall.followWayName }</div>
                        </div>
                        <div class="a_dot_ul">
                            <ul>
                                <li class="clearfix border-1px">
                                    <dl class="fl" style="height: 130px;">
                                        <dt>
                                        <p>${followInfo.toCall.followWayName }</p>
                                        </dt>
                                        <dd>
                                            <p>
                                                <span>
                                                    <f class="dark">${followInfo.toCall.followTimes }</f>��
                                                </span>
                                            </p>
                                        </dd>
                                    </dl>
                                    <!--
                                    <div class="fr a_sale_money">
                                        <p style="text-align:right;">
                                            <span>                                                
                                                <f style="max-width:90%;" class="f_fl"> 821</f> ��
                                            </span>
                                        </p>
                                    </div> 
                                    -->
                                    <div class="fr a_team">
                                        <p class="clearfix">
                                                <span class="fr">${followInfo.toCall.groupInfo.A} ��</span>
                                                <span class="fl">����A��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.toCall.groupInfo.B} ��</span>
                                                <span class="fl">����B��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.toCall.groupInfo.C} ��</span>
                                                <span class="fl">����</span>
                                            </p><p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">������</span>
                                            </p>                                                                                 <p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">����</span>
                                            </p>
                                                                            </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>   

                <div class="a_items">
                    <div class="a_rela a_dotList a_laidian">
                        <div class="a_title clearfix" style="background: rgb(84, 201, 148);">
                            <div class="a_tit_name fl">${followInfo.other.followWayName}</div>
                        </div>
                        <div class="a_dot_ul">
                            <ul>
                                <li class="clearfix border-1px">
                                    <dl class="fl" style="height: 104px;">
                                        <dt>
                                        <p>${followInfo.other.followWayName}</p>
                                        </dt>
                                        <dd>
                                            <p>
                                                <span>
                                                    <f class="dark">${followInfo.other.followTimes }</f>
                                                    ��
                                                </span>
                                            </p>
                                        </dd>
                                    </dl>
                                    <!--
                                    <div class="fr a_sale_money">
                                        <p style="text-align:right;">
                                            <span>                                                
                                                <f style="max-width:90%;" class="f_fl"> 54</f> ��
                                            </span>
                                        </p>
                                    </div> 
                                    -->
                                    <div class="fr a_team">
                                        <p class="clearfix">
                                                <span class="fr">${followInfo.other.groupInfo.A} ��</span>
                                                <span class="fl">����A��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.other.groupInfo.B} ��</span>
                                                <span class="fl">����B��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">${followInfo.other.groupInfo.C} ��</span>
                                                <span class="fl">����</span>
                                            </p><p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">������</span>
                                            </p>                                                                            </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>               

                        
            <!--�ϳ� Start
                <div class="a_items">
                    <div class="a_rela a_dotList a_renchou">
                        <div class="a_title clearfix">
                            <div class="a_tit_name fl">�ϳ�</div>
                        </div>
                        <div class="a_dot_ul">
                            <ul>
                                 	<li class="clearfix border-1px" data-ac="active" >
                                    <dl class="fl" style="height: 104px;">
                                        <dt>
                                        <p>�� ��</p>
                                        </dt>
                                        <dd>
                                            <p>
                                                <span>
                                                    <f>0</f>
                                                    ��
                                                </span>
                                            </p>
                                        </dd>
                                    </dl>
                                    <div class="fr a_team">
                                        <p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">������</span>
                                            </p><p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">����</span>
                                            </p><p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">����B��</span>
                                            </p><p class="clearfix">
                                                <span class="fr">0 ��</span>
                                                <span class="fl">����A��</span>
                                            </p>                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>     -->       
                        

            <!--�Ϲ� Start-->
                   <div class="a_items">
                        <div class="a_rela a_dotList a_rengou">
                            <div class="a_title clearfix" style="background: rgb(84, 201, 148);">
                                <div class="a_tit_name fl">�� ��</div>
                            </div>
                            <div class="a_dot_ul">
                                <ul>
                                        <li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/buyDetail.action?type=week">
                                        <dl class="fl" style="height: 130px;">
                                            <dt>
                                            <p>
                                             ${saleInfo.followWayName}                                             </p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${saleInfo.money}</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${saleInfo.sets}</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${saleInfo.groupInfo.B}%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${saleInfo.groupInfo.A}%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${saleInfo.groupInfo.C}%</span>
                                                    <span class="fl">����</span>
                                                </p>                                                                                       	<p class="clearfix">
                                                <span class="fr">0%</span>
                                                <span class="fl">���ƿ��Ŷ�</span>
                                          	</p>
                                          	                                        </div>
                                    </li>
                                    <c:if test="${reportType == 'day'}">                                     
                                    <li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/buyDetail.action?type=month">
                                        <dl class="fl" style="height: 130px;">
                                            <dt>                         
                                            <p>${monthSaleInfo.followWayName}</p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${monthSaleInfo.money}</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${monthSaleInfo.sets}</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${monthSaleInfo.groupInfo.A}%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${monthSaleInfo.groupInfo.B}%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${monthSaleInfo.groupInfo.C}%</span>
                                                    <span class="fl">����</span>
                                                </p>                                                                                       	<p class="clearfix">
                                        		<span class="fr">0%</span>
                                                <span class="fl">���ƿ��Ŷ�</span>
                                          	</p>
                                          	                                        </div>
                                    </li>                                
                                		<li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/buyDetail.action?type=year">
                                        <dl class="fl" style="height: 104px;">
                                            <dt>
                                            <p>${yearSaleInfo.followWayName}</p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${yearSaleInfo.money }</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${yearSaleInfo.sets}</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${yearSaleInfo.groupInfo.A}%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${yearSaleInfo.groupInfo.B}%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${yearSaleInfo.groupInfo.C}%</span>
                                                    <span class="fl">����</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p>                                            
                                                                                    </div>
                                    </li> 
                                   		<li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/buyDetail.action?type=total">
                                        <dl class="fl" style="height: 104px;">
                                            <dt>
                                            <p>${totalSaleInfo.followWayName }</p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${totalSaleInfo.money }</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${totalSaleInfo.sets }</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${totalSaleInfo.groupInfo.A}%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${totalSaleInfo.groupInfo.B}%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${totalSaleInfo.groupInfo.C}%</span>
                                                    <span class="fl">����</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p>                                                                                    </div>
                                    </li>  
                                    </c:if>                              
                                                                   
                                </ul>
                            </div>
                        </div>
                    </div>                                            
                            </div>
                        </div>
                                            
                        <!--תǩԼ Start-->
              <div class="a_items">
                        <div class="a_rela a_dotList a_zhuanqian">
                            <div class="a_title clearfix">
                                <div class="a_tit_name fl">תǩԼ</div>
                            </div>
                            <div class="a_dot_ul">
                                <ul>
                                        <li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/signUpDetail.action?type=week">
                                        <dl class="fl" style="height: 104px;">
                                            <dt>
                                            <p>${signInfo.followWayName}</p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${signInfo.money }</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${signInfo.sets }</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${signInfo.groupInfo.B}%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${signInfo.groupInfo.A}%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${signInfo.groupInfo.C}%</span>
                                                    <span class="fl">����</span>
                                                </p>                                           	                                        </div>
                                    </li>
                                    <c:if test="${reportType == 'day'}">
                                    <li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/signUpDetail.action?type=month">
                                        <dl class="fl" style="height: 104px;">
                                            <dt>
                                            <p>${monthSignInfo.followWayName }</p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${monthSignInfo.money }</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${monthSignInfo.sets }</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${monthSignInfo.groupInfo.A }%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${monthSignInfo.groupInfo.B }%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${monthSignInfo.groupInfo.C }%</span>
                                                    <span class="fl">����</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p>                                                                                    </div>
                                    </li>
                                        <li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/signUpDetail.action?type=year">
                                        <dl class="fl" style="height: 104px;">
                                            <dt>
                                            <p>${yearSignInfo.followWayName }</p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${yearSignInfo.money }</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${yearSignInfo.sets }</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${yearSignInfo.groupInfo.A }%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${yearSignInfo.groupInfo.B }%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${yearSignInfo.groupInfo.C }%</span>
                                                    <span class="fl">����</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p>                                                                                    </div>
                                    </li>
                                        <li class="clearfix border-1px" data-ac="active" data-href="/mbem/mcrm/house/saleManager/signUpDetail.action?type=total">
                                        <dl class="fl" style="height: 104px;">
                                            <dt>
                                            <p>${totalSignInfo.followWayName }</p>
                                            </dt>
                                            <dd>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${totalSignInfo.money }</f>
                                                        ��Ԫ
                                                    </span>
                                                </p>
                                                <p>
                                                    <span>
                                                        <f class="f_fl">${totalSignInfo.sets }</f>
                                                        ��
                                                    </span>
                                                </p>
                                            </dd>
                                        </dl>
                                        <div class="fr a_team">
                                            <p class="clearfix">
                                                    <span class="fr">${totalSignInfo.groupInfo.A }%</span>
                                                    <span class="fl">����A��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${totalSignInfo.groupInfo.B }%</span>
                                                    <span class="fl">����B��</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">${totalSignInfo.groupInfo.C }%</span>
                                                    <span class="fl">����</span>
                                                </p><p class="clearfix">
                                                    <span class="fr">0%</span>
                                                    <span class="fl">������</span>
                                                </p>                                       		                                        </div>
                                    </li>  
                                    </c:if>                              
                                                                    </ul>
                            </div>
                        </div>
                    </div>        
        
        
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
<div class="powered" id="powered">
    Powered&nbsp;by&nbsp;&nbsp;<a href="#">��ʢ�ز�</a>
</div>

<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
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
        <li data-ac="active" class="border-1px"><span class="k">��ʼʱ�䣺</span><span class="v"><input class="btime" data-range="2010/1/1-2018/12/30" type="text" readonly id="mobiscroll1450162565980"></span></li>
        <li data-ac="active"><span class="k">����ʱ�䣺</span><span class="v"><input class="etime" data-range="2010/1/1-2018/12/30" type="text" readonly id="mobiscroll1450162565981"></span></li>
    </ul>
    <div class="btns">
        <span data-ac="active" class="cancel">ȡ��</span>
        <span data-ac="active" class="sure">ȷ��</span>
    </div>
</div>
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
    // ʹ��mobiscroll���
    function bindCalendar(selector){
        $(selector).mobiscroll().calendar({
            theme: '',      // Specify theme like: theme: 'ios' or omit setting to use default
            lang: 'zh',    // Specify language like: lang: 'pl' or omit setting to use default
            display: 'modal' ,  // Specify display mode like: display: 'bottom' or omit setting to use default
            mode: 'scroller',        // More info about mode: http://docs.mobiscroll.com/2-17-0/calendar#!opt-mode
            closeOnSelect: true,
            dateFormat: 'yy-mm-dd'
        });
    }

    (function($){
        var hidepanel =1;
        var timeWrap = $("#time-wrap"),callbackfunc,isclose=false;
        bindCalendar('input.btime');
        bindCalendar('input.etime');
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
    (function ($) {
        //�Զ���
        $('#a_default').on('click', function(){
            $.showTwoTime(function(btime,etime){
                setTimeout(function(){
                    window.location.replace("/c"+etime.action);
                },200);
             });
        });
        //����dl�ĸ߶Ȳ���ֵ��ʹ��߶�����silings��ֵ����һ�£���ֱ������ʾ
        var dl = $('dl.fl');
        for (var i = 0; i < dl.length; i++) {
            var ds = dl.eq(i).siblings()
                    , max = dl.eq(i).height()
                    ;
            if (dl.eq(i).siblings().height() > max) {
                max = dl.eq(i).siblings().height();
            }
            dl.eq(i).height(max);
            if (dl.eq(i).children('dd').length == 0) {
                dl.eq(i).css('line-height', max + 'px');
            }
        }
        //��phpǶ�׸�Ƕ�����˲㼶���Ҳ�����ǩ����λ���ˣ�ֻ����jsӲ�������л�ɫ��
        var a_items = $('.a_items');
        for(var i = 0;i < a_items.length;i++){
            if(i > 0 && i % 2 != '0'){
                $('.a_title', a_items.eq(i)).css('background', '#54c994');
            }
        }
        //������ת
        $(".datelist li").on('click',function(o){
            if($(this).attr('range') != 'other'){
                window.location.replace($('a', $(this)).attr('data-url'));
            }
        });
        //�������ȦȦ���ߵ��߳���
        var a_pos_border = $('.a_pos_border');
        for (var i = 0; i < a_pos_border.length; i++) {
            var entity = a_pos_border.eq(i).closest('.a_items');
            var originHeight = a_pos_border.eq(i).height();
            a_pos_border.eq(i).height($('.a_title', a_pos_border.eq(i).closest('.a_items')).height() + ($('ul', a_pos_border.eq(i).closest('.a_items')).height() - $('li', a_pos_border.eq(i).closest('.a_items')).last().height()) + ($('dt', a_pos_border.eq(i).closest('.a_items')).last().height() / 2) + 3);
        }
        $("li[data-href]").on("click",function(){
            var url = $(this).data("href");
            var params = location.search.substr(1);
            if(url.indexOf("?")!=-1){
                url = url + "&"+params;
            }else{
                url = url + "?"+params;
            }
            location.href=url;
        });
    })(Zepto)
</script>
</body><iframe id="__WeixinJSBridgeIframe_SetResult" style="display: none;"></iframe><iframe id="__WeixinJSBridgeIframe" style="display: none;"></iframe></html>