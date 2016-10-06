<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="navbar" style="margin-top: 10px;">
  <div class="navbar-inner">
    <ul class="nav">
      <li class="active"><a href="/wbem/houses/customer/dailog/custDetail.action?cusid=${custLogID}&cstType=${custType}">客户信息</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/houses/customer/dailog/custChangeLog.action">变更记录</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/houses/customer/dailog/custAssignLog.action">分配日志</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/houses/customer/dailog/custRecycleLog.action">回收日志</a></li>
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
          <td>${custDetail.cstName }</td>
          <td>姓别</td>
          <td>${custDetail.gender }</td>
        </tr>
        <tr>
          <td>电话</td>
          <td>${custDetail.mobileTel }</td>
          <td>家庭电话</td>
          <td>${custDetail.homeTel }</td>
        </tr>
        <!-- <tr >
          <td>备注</td>
          <td colspan="3"></td>
        </tr> -->
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
          <td>${custDetail.ageGroup }</td>
          <td>居住区域</td>
          <td>${custDetail.homeArea }</td>
          <td>工作区域</td>
          <td>${custDetail.workArea }</td>
        </tr>
        <tr>
          <td>工作行业</td>
          <td>${custDetail.industry }</td>
          <td>婚姻状况</td>
          <td>${custDetail.marriage }</td>
          <td>本地户籍</td>
          <td>${custDetail.nativePlace }</td>
        </tr>
        <tr>
          <td>家庭结构</td>
          <td>${custDetail.family }</td>
          <td>电子邮件</td>
          <td>${custDetail.email }</td>
          <td>购房用途</td>
          <td>${custDetail.gfyt }</td>
        </tr>
        <tr>
          <td>关注因素</td>
          <td>${custDetail.gzfm1 }</td>
          <td>认知途径</td>
          <td>${custDetail.mainMediaName }</td>
          <td>客户意向</td>
          <td>${custDetail.gfyx }</td>
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
          <td>${custDetail2.userName}</td>
          <td>创建时间</td>
          <td>${custDetail.createdOn}</td>
          <td>分配日期</td>
          <td>${custDetail2.lastDate}</td>
        </tr>
        <tr>
          <td>分配人</td>
          <td>${custDetail.modifyBy}</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
    </table>
  </div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>