<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header.jsp" %>
<div class="container-fluid">
	<div class="navbar">
	  <div class="navbar-inner" style="margin-top:5px;">
	    <ul class="nav">
	      <%-- <li>
	      <form class="navbar-form pull-left" >
            <select id="proId" class="span2">
              <c:forEach items="${projectList}" var="project" varStatus="status">
            	<option value="${project.id}">${project.name}</option>
              </c:forEach>
            </select>
		  </form>
	      </li> 
	   	  <li class="divider-vertical"></li>--%>
	      <li id="dayReport"><a href="/wbem/business/analysis/indexReport.action?reportType=dayReport&menuId=">�ձ�</a></li>
	      <li class="divider-vertical"></li>
	      <li id="weekReport"><a href="/wbem/business/analysis/indexReport.action?reportType=weekReport&menuId=">�ܱ�</a></li>
	      <li class="divider-vertical"></li>
	      <li id="monthReport"><a href="/wbem/business/analysis/indexReport.action?reportType=monthReport&menuId=">�±�</a></li>
	      <li class="divider-vertical"></li>
	      <li id="rangeReport"><a href="#">�Զ�������</a></li>
	      <li class="divider-vertical"></li>
	      <li>
	        <form class="navbar-form pull-left" id="datepicker" style="display:none" method="post" action="/wbem/business/analysis/indexReport.action?reportType=rangeReport&menuId=">
				<input type="text" data-date="${startDate}" data-date-format="yyyy-mm-dd" value="${startDate}" class="datepicker span2" id="dpd1" name="startTime" readonly>
				<i class="icon-resize-horizontal"></i>		   
			    <input type="text" data-date="${endDate}" data-date-format="yyyy-mm-dd" value="${endDate}" class="datepicker span2" id="dpd2" name="endTime" readonly>
				<button type="submit" class="btn btn-success">ȷ��</button>
			</form>
		  </li>
	    </ul>
	  </div>
	</div>

    <div class="row-fluid">
    	<div class="span1">
    		<input type="hidden" value="����" id="gjfs">
    		<input type="hidden" value="${reportType}" id="reportType">
    	</div>
    	<div class="span2">
    	<a href="#">
		    <div class="small-box bg-aqua">
		        <div class="inner">
		            <h3>${call}<sup style="font-size: 20px">��</sup></h3>
		            <p style="text-align: center;">����</p>
		        </div>
		        <div class="icon"><i class="icon-phone"></i></div>
		        <span class="small-box-footer">����鿴��ϸ <i class="icon-circle-arrow-right"></i></span>
		    </div>
		</a>
    	</div>
    	
    	<div class="span2">
    	<a href="#" >
		    <div class="small-box bg-green">
		        <div class="inner">
		            <h3>${firstCall}<sup style="font-size: 20px">��</sup></h3>
		            <p style="text-align: center;">�״�����</p>
		        </div>
		        <div class="icon"><i class="icon-user"></i></div>
		         <span class="small-box-footer">����鿴��ϸ <i class="icon-circle-arrow-right"></i></span>
		    </div>
		</a>
    	</div>
    	<div class="span2">
    		<a href="#" >
	    		<div class="small-box bg-yellow">
	                <div class="inner">
	                    <h3>${visit}<sup style="font-size: 20px">��</sup></h3>
	                    <p style="text-align: center;">����</p>
	                </div>
	                <div class="icon"><i class="icon-check"></i></div>
	                 <span class="small-box-footer">����鿴��ϸ <i class="icon-circle-arrow-right"></i></span>
	            </div>
            </a>
       	</div>
       	<div class="span2">
       	<a href="#" >
    		<div class="small-box bg-olive">
                <div class="inner">
                    <h3>${firstVisit}<sup style="font-size: 20px">��</sup></h3>
                    <p style="text-align: center;">�״�����</p>
                </div>
                <div class="icon"><i class=" icon-road"></i></div>
                 <span class="small-box-footer">����鿴��ϸ <i class="icon-circle-arrow-right"></i></span>
            </div>
        </a>
       	</div>
       	<div class="span2">
       	<a href="#" >
    		<div class="small-box bg-light-blue">
                <div class="inner">
                    <h3>${toCall}<sup style="font-size: 20px">��</sup></h3>
                    <p style="text-align: center;">ȥ��</p>
                </div>
                <div class="icon"><i class="icon-random"></i></div>
                 <span class="small-box-footer">����鿴��ϸ <i class="icon-circle-arrow-right"></i></span>
            </div>
        </a>
       	</div>
       	<div class="span1">
    	</div>
    </div>
	<div class="widget-box">
		
			  <form action="#" method="get" class="form-horizontal">
			  <div class="row-fluid ">
			  	<div class="input-prepend span4" style="margin-top: 10px;margin-left:20px; ">
				  <div class="btn-group">
				    <button class="btn dropdown-toggle"  data-toggle="dropdown"> �ͻ��绰 <span class="caret" style="margin-left: 20px;"></span></button>
				    <ul class="dropdown-menu ">
				      <li><a href="#">�ͻ�����</a></li>
				      <li><a href="#">�ͻ��绰</a></li>
				    </ul>
				  </div>
				  <input class="span5" id="telOrName" type="text">
				</div>
				<div class="control-group noborder-top noborder-bottom span4" style="margin-left: 0">
	              <label class="control-label zygw" >��ҵ����</label>
	              <div class="controls span6" style="margin-left: 0">
	                <select id="zygw" >
	                	<option value="" selected></option>
	                  	<c:forEach items="${zygwList}" var="zygw" varStatus="status">
              				<option value="${zygw.userId}">${zygw.userName}</option>
             			</c:forEach>
	                </select>
	              </div>
	            </div> 
	            
	            <div class="control-group noborder-bottom noborder-top span4" style="margin-left: 0"> 
	            	<div class="controls">
	                  <button type="button" class="btn btn-success" id="tableSearch">��ѯ</button>
	                  <button type="button" class="btn " id="resetSearch">����ɸѡ</button>
	                </div>
	             </div> 
	            </div> 
	          </form>
	</div>
	<div class="widget-box">
		<div class="widget-title"><span class="icon"><i class="icon-th"></i></span><h5>ͳ���б�</h5>
        </div>
		<div class="widget-content nopadding">
			<table class="table table-bordered data-table">
				<thead>
	                <tr>
						<th><label class="checkbox inline index"> <input type="checkbox" name="radios" />���</label></th>
						<th>����</th>
						<th>��ϵ��ʽ</th>
						<th>�ͻ���Դ</th>
						<th>��ס����</th>
						<th>��ҵ����</th>
						<th>������֯</th>
						<th>�ͻ�״̬</th>
						<th>�������ʱ��</th>
						<th>������ʽ</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>
<script>
$(document).ready(function(){
	
	//var proId = $('#proId').find('option:selected').val();
	$("li[id$=Report]").each(function(){
		var href = $("a",this).attr("href");
		if($(this).attr("id") != "rangeReport")
			$("a",this).attr("href",href+${menuId});
		
		if($(this).attr("id") == $("#reportType").val())
			$(this).addClass("active");
		if($("#reportType").val() == "rangeReport") 
			$("#datepicker").attr("style","display:block");
	});
	
	
	var action = $("#datepicker").attr("action");
	$("#datepicker").attr("action",action+${menuId});
	
	$("#rangeReport").click(function(){
		$("#datepicker").attr("style","display:block");
		$("li[id$=Report]").removeClass("active");
		$(this).addClass("active");
	});
	
	$("#tableSearch").click(function(){
		$('.data-table').DataTable().draw();
	});
	
	$("#resetSearch").click(function(){
		$("#zygw").select2("val","");
		$("#telOrName").val("");
		setTimeout($('.data-table').DataTable().ajax.reload(),100);
	});
	
	
	$('.data-table').dataTable({
		"ajax": {type: "post",//��ָ̨���˷�ʽ��
                 url: "/wbem/business/analysis/loadAnalysisData.action",
                 data:function(d){			//�ⲿ��������
                 	d.proId=$('#proId').find('option:selected').val();
                 	d.gjfs = encodeURI($("#gjfs").val());
                 	d.zygw = $('#zygw').find('option:selected').val();
                 	d.team = $('#team').find('option:selected').val();
                 	d.startTime = $("#dpd1").val();
	           		d.endTime = $("#dpd2").val();
	           		d.telOrName = encodeURI($("#telOrName").val());
                 }
         },
         "columns":[
         	{"data":"index"},
         	{"data":"cstName"},
         	{"data":"mobileTel"},
         	{"data":"oppSource"},
         	{"data":"homeArea"},
         	{"data":"zygw"},
         	{"data":"team"},
         	{"data":"status"},
         	{"data":"lastDate"},
         	{"data":"gjfs"},
         ],
	});
	$('select').select2();
	
	$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});
	$(".small-box").click(function(){
		var tag = $(this).children();
		var val = $("p",tag).text();
		$("#gjfs").val(val);
		
		$('.data-table').DataTable().ajax.reload();
	});
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer.jsp" %>