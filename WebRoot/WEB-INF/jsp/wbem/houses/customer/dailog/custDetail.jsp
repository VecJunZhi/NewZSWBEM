<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<div class="navbar" style="margin-top: 10px;">
  <div class="navbar-inner">
    <ul class="nav">
      <li class="active"><a href="/wbem/houses/customer/dailog/custDetail.action?cusid=${custLogID}&cstType=${custType}">�ͻ���Ϣ</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/houses/customer/dailog/custChangeLog.action">�����¼</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/houses/customer/dailog/custAssignLog.action">������־</a></li>
      <li class="divider-vertical"></li>
      <li><a href="/wbem/houses/customer/dailog/custRecycleLog.action">������־</a></li>
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
          <td>${custDetail.cstName }</td>
          <td>�ձ�</td>
          <td>${custDetail.gender }</td>
        </tr>
        <tr>
          <td>�绰</td>
          <td>${custDetail.mobileTel }</td>
          <td>��ͥ�绰</td>
          <td>${custDetail.homeTel }</td>
        </tr>
        <!-- <tr >
          <td>��ע</td>
          <td colspan="3"></td>
        </tr> -->
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
          <td>${custDetail.ageGroup }</td>
          <td>��ס����</td>
          <td>${custDetail.homeArea }</td>
          <td>��������</td>
          <td>${custDetail.workArea }</td>
        </tr>
        <tr>
          <td>������ҵ</td>
          <td>${custDetail.industry }</td>
          <td>����״��</td>
          <td>${custDetail.marriage }</td>
          <td>���ػ���</td>
          <td>${custDetail.nativePlace }</td>
        </tr>
        <tr>
          <td>��ͥ�ṹ</td>
          <td>${custDetail.family }</td>
          <td>�����ʼ�</td>
          <td>${custDetail.email }</td>
          <td>������;</td>
          <td>${custDetail.gfyt }</td>
        </tr>
        <tr>
          <td>��ע����</td>
          <td>${custDetail.gzfm1 }</td>
          <td>��֪;��</td>
          <td>${custDetail.mainMediaName }</td>
          <td>�ͻ�����</td>
          <td>${custDetail.gfyx }</td>
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
          <td>${custDetail2.userName}</td>
          <td>����ʱ��</td>
          <td>${custDetail.createdOn}</td>
          <td>��������</td>
          <td>${custDetail2.lastDate}</td>
        </tr>
        <tr>
          <td>������</td>
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