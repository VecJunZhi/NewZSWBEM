<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="navbar" style="margin-top: 10px;">
  <div class="navbar-inner">
    <ul class="nav">
      <li class="active"><a href="/wbem/business/customer/dailog/custDetail.action?cusid=${custLogID}&cstType=${custType}">客户信息</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custChangeLog.action">变更记录</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custAssignLog.action">分配日志</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custRecycleLog.action">回收日志</a></li>
    </ul>
  </div>
</div>
<div class="widget-box">
  <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
    <h5>基本信息</h5>
  </div>
  <div class="widget-content nopadding">
    <table class="table">
        <tr>
          <td>姓名</td>
          <td>${custDetail.cusName }</td>
          <td>姓别</td>
          <td>${custDetail.sex }</td>
        </tr>
        <tr>
          <td>电话</td>
          <td>${custDetail.tel }</td>
          <td>标签</td>
          <td>${custDetail.label }</td>
        </tr>
        <tr >
          <td>备注</td>
          <td colspan="3">${custDetail.cusDesp }</td>
        </tr>
    </table>
  </div>
</div>
<div class="widget-box">
  <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
    <h5>特征信息</h5>
  </div>
  <div class="widget-content nopadding">
    <table class="table">
        <tr>
          <td>年龄段</td>
          <td>${custDetail.age }</td>
          <td>居住区域</td>
          <td>${custDetail.addrArea }</td>
          <td>工作区域</td>
          <td>${custDetail.workComp }</td>
        </tr>
        <tr>
          <td>工作行业</td>
          <td>${custDetail.workType }</td>
          <td>来访次数</td>
          <td>${custDetail.visitTimes }</td>
          <td>招商业态</td>
          <td>${custDetail.investmentInfo }</td>
        </tr>
        <tr>
          <td>家庭结构</td>
          <td>${custDetail.familyInfo}</td>
          <td>意向面积</td>
          <td>${custDetail.intentionArea }</td>
          <td>置业次数</td>
          <td>${custDetail.buyTimes}</td>
        </tr>
        <tr>
          <td>意向价格</td>
          <td>${custDetail.intentionPrice }</td>
          <td>意向产品</td>
          <td>${custDetail.intentionProduct }</td>
          <td>客户房型</td>
          <td>${custDetail.intentionRoom }</td>
        </tr>
    </table>
  </div>
</div>
<div class="widget-box">
  <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
    <h5>其他信息</h5>
  </div>
  <div class="widget-content nopadding">
    <table class="table">
        <tr>
          <td>置业顾问</td>
          <td>${userName}</td>
          <td>创建时间</td>
          <td>${custDetail.firstDate}</td>
          <td>分配日期</td>
          <td>${custDetail.lastDate}</td>
        </tr>
        <!-- <tr>
          <td>分配人</td>
          <td>xxx</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr> -->
    </table>
  </div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>