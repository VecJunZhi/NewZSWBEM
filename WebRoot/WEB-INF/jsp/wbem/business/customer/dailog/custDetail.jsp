<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="navbar" style="margin-top: 10px;">
  <div class="navbar-inner">
    <ul class="nav">
      <li class="active"><a href="/wbem/business/customer/dailog/custDetail.action?cusid=${custLogID}&cstType=${custType}">�ͻ���Ϣ</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custChangeLog.action">�����¼</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custAssignLog.action">������־</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/business/customer/dailog/custRecycleLog.action">������־</a></li>
    </ul>
  </div>
</div>
<div class="widget-box">
  <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
    <h5>������Ϣ</h5>
  </div>
  <div class="widget-content nopadding">
    <table class="table">
        <tr>
          <td>����</td>
          <td>${custDetail.cusName }</td>
          <td>�ձ�</td>
          <td>${custDetail.sex }</td>
        </tr>
        <tr>
          <td>�绰</td>
          <td>${custDetail.tel }</td>
          <td>��ǩ</td>
          <td>${custDetail.label }</td>
        </tr>
        <tr >
          <td>��ע</td>
          <td colspan="3">${custDetail.cusDesp }</td>
        </tr>
    </table>
  </div>
</div>
<div class="widget-box">
  <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
    <h5>������Ϣ</h5>
  </div>
  <div class="widget-content nopadding">
    <table class="table">
        <tr>
          <td>�����</td>
          <td>${custDetail.age }</td>
          <td>��ס����</td>
          <td>${custDetail.addrArea }</td>
          <td>��������</td>
          <td>${custDetail.workComp }</td>
        </tr>
        <tr>
          <td>������ҵ</td>
          <td>${custDetail.workType }</td>
          <td>���ô���</td>
          <td>${custDetail.visitTimes }</td>
          <td>����ҵ̬</td>
          <td>${custDetail.investmentInfo }</td>
        </tr>
        <tr>
          <td>��ͥ�ṹ</td>
          <td>${custDetail.familyInfo}</td>
          <td>�������</td>
          <td>${custDetail.intentionArea }</td>
          <td>��ҵ����</td>
          <td>${custDetail.buyTimes}</td>
        </tr>
        <tr>
          <td>����۸�</td>
          <td>${custDetail.intentionPrice }</td>
          <td>�����Ʒ</td>
          <td>${custDetail.intentionProduct }</td>
          <td>�ͻ�����</td>
          <td>${custDetail.intentionRoom }</td>
        </tr>
    </table>
  </div>
</div>
<div class="widget-box">
  <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
    <h5>������Ϣ</h5>
  </div>
  <div class="widget-content nopadding">
    <table class="table">
        <tr>
          <td>��ҵ����</td>
          <td>${userName}</td>
          <td>����ʱ��</td>
          <td>${custDetail.firstDate}</td>
          <td>��������</td>
          <td>${custDetail.lastDate}</td>
        </tr>
        <!-- <tr>
          <td>������</td>
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