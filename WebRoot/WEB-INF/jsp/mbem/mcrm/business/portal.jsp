<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
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
    <title>index</title>
    <style>
     </style>
    <script type="text/javascript">
        statistics.t1=+new Date()-statistics.start;
		function ShowImage(page, tag)
{
    var i = 1;
    var el;
    while (el = document.getElementById(tag + i)) {
        if (i == page)
            el.style.display = 'block';
        else
            el.style.display = 'none';
        i++;
    }
}
    </script>
</head>
<body>

    <div class="box-wrap">
        <a class="row line1" data-ac="active" href="/xs/performance/budgetEditSeasonYear.action">
            <div class="row-inner">
                <p class="month"><span class="num"><%=new Date().getMonth()+1 %></span>月</p>
                <p><span class="num big">${budgetMonth.salesAmount }</span><span class="small">万元</span></p>
                <h3 class="title">业绩达成</h3>
                <div class="area">
                    <div class="a_li a_rela fl"><font class="num big">成交${budgetMonth.ts}套</font>
                    </div>
                </div>
            </div>
        </a>
        <a class="row line2" data-ac="active" href="/c.action">
            <div class="row-inner">
                <div class="report-today">
                    <h3 class="title report">案场日报</h3>
                    
                </div>
                <div class="con">
                    <table class="info">
                       <tbody><tr>
                       <td><span class="small">成交	</span></td>
                          <td><span class="x-small">住宅${yzroomDeal}笔</span></td>
                            <td><span class="x-small">地下室${yzcellarDeal}笔</span></span></td>
                            <td><span class="x-small">车位${yzcarDeal}笔</span></td>
                        </tr>
                        <tr class="info-num">
                        <td><span class="small">退房</span></td>
                            <td><span class="x-small">住宅${yzroomCheckOut}笔</span></td>
                            <td><span class="x-small">地下室${yzcellarCheckOut}笔</span></span></td>
                            <td><span class="x-small">车位${yzcarCheckOut}笔</span></td>
                        </tr>                        <tr>
                        <td></td>
                            <td><span class="x-small">回访${toInterView}组</span></td>
                            <td><span class="x-small">来电${cusTel}组</span></span></td>
                            <td><span class="x-small">来访${cusAccess}笔</span></td>
                        </tr>
                        
                    </tbody></table>
                    
                </div>
            </div>
        </a>
        <a class="row line3" data-ac="active" href="/xs/performance/customerAllocateData.action">
            <div class="row-inner">
                <h3 class="title">客户分配</h3>
                <div class="con">
                    <table class="info">
                        <tbody>
	                        <tr>
	                            <td><span class="small">逾期客户</span></td>
	                            <td><span class="num big">${overdueCsNum}</span><span class="x-small">人</span></td>
	                        </tr>
							<tr>
		                            <td><span class="small">公共客户</span></td>
		                            <td><span class="num big">${publicCsNum}</span><span class="x-small">人</span></td>
		                    </tr>
	                        <!-- <tr>
	                             <td><span class="small">待分配客户</span></td>
	                             <td><span class="num big">0</span><span class="x-small">人</span></td>
	                        </tr> --> 
	                    </tbody>
	                </table>
                </div>
            </div>
        </a>
        <div class="row line4">
            <div class="row-inner">
                <ul class="option-list">
                    <li>
                        <a href="b1.action" data-ac="active">
                            <img src="/mbem/mcrm/business/images/1.png" alt="储客分析">
                            <h4 class="title">储客分析</h4>
                      </a>
                  </li>
                    <li>
                        <a href="a1.action" data-ac="active">
                        <img src="/mbem/mcrm/business/images/2.png" alt="跟进管理">
                        <h4 class="title">跟进管理</h4>
                        </a>
                    </li>
                    <li>
                        <a href="#" data-ac="active">
                        <img src="/mbem/mcrm/business/images/3.png" alt="销控管理">
                        <h4 class="title">销控管理</h4>
                      </a>
                    </li>
                    <li>
                        <a href="#" data-ac="active">
                        <img src="/mbem/mcrm/business/images/4.png" alt="发现">
                        <h4 class="title">发现</h4>
                      </a>
                    </li>
                </ul>
            </div>
        </div>
    </div> 
    <script type="text/javascript" src="js/base.min.js"></script>
  






























  


</body></html>