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
                <p class="month"><span class="num"><%=new Date().getMonth()+1 %></span>��</p>
                <p><span class="num big">${budgetMonth.salesAmount }</span><span class="small">��Ԫ</span></p>
                <h3 class="title">ҵ�����</h3>
                <div class="area">
                    <div class="a_li a_rela fl"><font class="num big">�ɽ�${budgetMonth.ts}��</font>
                    </div>
                </div>
            </div>
        </a>
        <a class="row line2" data-ac="active" href="/c.action">
            <div class="row-inner">
                <div class="report-today">
                    <h3 class="title report">�����ձ�</h3>
                    
                </div>
                <div class="con">
                    <table class="info">
                       <tbody><tr>
                       <td><span class="small">�ɽ�	</span></td>
                          <td><span class="x-small">סլ${yzroomDeal}��</span></td>
                            <td><span class="x-small">������${yzcellarDeal}��</span></span></td>
                            <td><span class="x-small">��λ${yzcarDeal}��</span></td>
                        </tr>
                        <tr class="info-num">
                        <td><span class="small">�˷�</span></td>
                            <td><span class="x-small">סլ${yzroomCheckOut}��</span></td>
                            <td><span class="x-small">������${yzcellarCheckOut}��</span></span></td>
                            <td><span class="x-small">��λ${yzcarCheckOut}��</span></td>
                        </tr>                        <tr>
                        <td></td>
                            <td><span class="x-small">�ط�${toInterView}��</span></td>
                            <td><span class="x-small">����${cusTel}��</span></span></td>
                            <td><span class="x-small">����${cusAccess}��</span></td>
                        </tr>
                        
                    </tbody></table>
                    
                </div>
            </div>
        </a>
        <a class="row line3" data-ac="active" href="/xs/performance/customerAllocateData.action">
            <div class="row-inner">
                <h3 class="title">�ͻ�����</h3>
                <div class="con">
                    <table class="info">
                        <tbody>
	                        <tr>
	                            <td><span class="small">���ڿͻ�</span></td>
	                            <td><span class="num big">${overdueCsNum}</span><span class="x-small">��</span></td>
	                        </tr>
							<tr>
		                            <td><span class="small">�����ͻ�</span></td>
		                            <td><span class="num big">${publicCsNum}</span><span class="x-small">��</span></td>
		                    </tr>
	                        <!-- <tr>
	                             <td><span class="small">������ͻ�</span></td>
	                             <td><span class="num big">0</span><span class="x-small">��</span></td>
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
                            <img src="/mbem/mcrm/business/images/1.png" alt="���ͷ���">
                            <h4 class="title">���ͷ���</h4>
                      </a>
                  </li>
                    <li>
                        <a href="a1.action" data-ac="active">
                        <img src="/mbem/mcrm/business/images/2.png" alt="��������">
                        <h4 class="title">��������</h4>
                        </a>
                    </li>
                    <li>
                        <a href="#" data-ac="active">
                        <img src="/mbem/mcrm/business/images/3.png" alt="���ع���">
                        <h4 class="title">���ع���</h4>
                      </a>
                    </li>
                    <li>
                        <a href="#" data-ac="active">
                        <img src="/mbem/mcrm/business/images/4.png" alt="����">
                        <h4 class="title">����</h4>
                      </a>
                    </li>
                </ul>
            </div>
        </div>
    </div> 
    <script type="text/javascript" src="js/base.min.js"></script>
  






























  


</body></html>