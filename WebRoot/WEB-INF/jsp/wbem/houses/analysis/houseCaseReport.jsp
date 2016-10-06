<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %> 
<style>
	/* 调整统计信息的方块对齐 */
	.stat-boxes .right {
		min-width:20px;
		padding: 9px 0 7px 0;
	}
	.stat-boxes .left {
		margin-right:1px;
		padding: 9px 1px 7px 1px;
	}
	.stat-boxes li {
		margin: 0 5px 10px 0px;
	}
	.stat-boxes {
		margin: 0;
	}
</style>

<div class="container-fluid" >
	<%-- <div class="navbar" style="margin-top:10px;">
	 <div class="navbar-inner">
	  <ul class="nav">
	    <li>
		  <form class="navbar-form pull-left" >
	        <select id="projGuid" class="span2" >
              <c:forEach items="${projectList}" var="project" varStatus="status">
              	<option value="${project.id}">${project.name}</option>
              </c:forEach>
            </select>
		  </form>
	    </li>
	  </ul>
	</div>
   </div> --%>
   <div class="row-fluid" style="text-align: center;">
  	  <div class="span12 center" style="text-align: center;">					
		<ul class="stat-boxes " style="margin-left:0px">
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC">
			<div class="left peity_bar_good" style="width:60px;"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>房屋总数</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${roomStatistics.totalNum}</font></strong>套
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>已售房屋</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${roomStatistics.soldNum}</font></strong>套
			</div>
		 </li>
		 <!-- <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>全款客户</div>
			<div class="right" style="width:120px">
			  <strong><font size="3">123456789</font></strong>数量
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>贷款客户</div>
			<div class="right" style="width:120px">
			  <strong><font size="3">123456789</font></strong>数量
			</div>
		 </li> -->
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left peity_bar_good" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>剩余房屋</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${roomStatistics.unSoldNum}</font></strong>套
			</div>
		 </li>
		</ul>
	 </div>
   </div> 
   <div class="row-fluid" style="text-align: center;">
  	  <div class="span12 center" style="text-align: center;">					
		<ul class="stat-boxes " style="margin-left:0px">
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>欠款合计</div>
			<div class="right " style="width:40px">
			  <strong><font size="3">${roomStatistics.qkNum}</font></strong>户
			</div>
			<div class="right " style="width:120px">
			  <strong><font size="3">${roomStatistics.qkAmount}</font></strong>元
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>未交首付</div>
			<div class="right" style="width:40px">
			  <strong><font size="3">${unPaymentInfo.unPaymentNum}</font></strong>户
			</div>
			<div class="right right" style="width:120px">
			  <strong><font size="3">${unPaymentInfo.unPaymentAmount}</font></strong>元
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC;">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>未签约</div>
			<div class="right" style="width:40px;">
			  <strong><font size="3">${unSignUpInfo.unSignUpNum}</font></strong>户
			 </div>
			<div class="right right" style="width:120px">
			  <strong><font size="3">${unSignUpInfo.unSignUpAmount}</font></strong>元
			</div>
		</li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>未放款</div>
			<div class="right" style="width:40px">
			  <strong><font size="3">${unLendingInfo.unLendingNum}</font></strong>户
			</div>
			<div class="right right" style="width:120px">
			  <strong><font size="3">${unLendingInfo.unLendingAmount}</font></strong>元
			</div>
		 </li>
		</ul>
	 </div>
   </div>
   <div class="row-fluid" style="text-align: center;">
  	  <div class="span12 center" style="text-align: center;">					
		<ul class="stat-boxes " style="margin-left:0px">
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC">
			<div class="left peity_bar_good" style="width:60px;"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>车位总数</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${carportStatistics.totalNum}</font></strong>个
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>已售车位</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${carportStatistics.soldNum}</font></strong>个
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left peity_bar_good" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>剩余车位</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${carportStatistics.unSoldNum}</font></strong>个
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left left peity_bar_bad" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>应收款</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${carportStatistics.totalAmount}</font></strong>元
			</div>
		 </li>
		 <li style="background-color: rgba(0, 0, 0, 0);border:2px solid #DCDCDC"">
			<div class="left peity_bar_good" style="width:60px"><span><span style="display: none;">12,12,12,12,12,12,12,12</span><canvas width="50" height="24"></canvas></span>总欠款</div>
			<div class="right" style="width:160px">
			  <strong><font size="3">${carportStatistics.qkAmount}</font></strong>元
			</div>
		 </li>
		</ul>
	 </div>
   </div>
   <%-- <div class="widget-box">
		 <form action="#" method="get" class="form-horizontal">
			<div class="row-fluid ">
			  	<div class="input-prepend span3" style="margin-top: 10px;margin-left:20px; ">
				  <div class="btn-group">
				    <button class="btn dropdown-toggle"  data-toggle="dropdown"> 客户电话 <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu ">
				      <li><a href="#">客户姓名</a></li>
				      <li><a href="#">客户电话</a></li>
				    </ul>
				  </div>
				  <input class="span5" id="telOrName" type="text">
				</div>
				<div class="control-group noborder-top noborder-bottom span3" style="margin-left: 0">
	              <label class="control-label zygw" >所属团队</label>
	              <div class="controls span6" style="margin-left: 0">
	                <select id="team" >
	                  <option value="" selected></option>
	                  <c:forEach items="" var="team" varStatus="status">
              			  <option value=""></option>
             		  </c:forEach>
	                </select>
	              </div>
	            </div>
				<div class="control-group noborder-top noborder-bottom span3" style="margin-left: 0">
	              <label class="control-label zygw" >置业顾问</label>
	              <div class="controls span6" style="margin-left: 0">
	                <select id="zygw" >
	                  <option value="" selected></option>
	                  <c:forEach items="" var="zygw" varStatus="status">
              			<option value=""></option>
             		  </c:forEach>
	                </select>
	              </div>
	            </div> 
	            <div class="control-group noborder-bottom noborder-top span3" style="margin-left: 0"> 
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">查询</button>
	                  <button type="button" class="btn " id="resetSearch">撤销筛选</button>
	                </div>
	             </div> 
	        </div> 
	    </form>
	</div>
	<div class="widget-box">
	 <div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>统计列表</h5>
     </div>
	 <div class="widget-content nopadding">
	   <table class="table table-bordered data-table">
		 <thead>
	        <tr>
			 <th><label class="checkbox inline index"> <input type="checkbox" name="radios" />序号</label></th>
			 <th>姓名</th>
			 <th>联系方式</th>
			 <th>客户来源</th>
			 <th>居住区域</th>
			 <th>置业顾问</th>
			 <th>所属组织</th>
			 <th>客户状态</th>
			 <th>最近跟进时间</th>
			 <th>跟进方式</th>
		   </tr>
		</thead>
	   </table>
	 </div>
	</div> --%>
  </div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %> 
<script type="text/javascript"> 

</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %> 