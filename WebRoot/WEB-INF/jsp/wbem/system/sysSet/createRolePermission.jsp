<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
<p><button type="button" class="btn btn-info" id="corrRolePermission" style="float: right;" >ѡ��ȷ��</button></p>
<ul class="nav nav-tabs" id="subSystem_dd">
   		<li class="active"><a href="#" id="">��Ŀ��Ȩ</a></li> 
</ul>
<div id="all_menu_dd" >
	<!-- <div class="row"  id="s_menu" names="abc">
		<input type="checkbox" name="">�˵�
	</div>
	<div class="row" style="margin-left: -5px" id="s_subMenu">
		<input type="checkbox">�Ӳ˵�
	</div> -->
	<div class="row" style="margin-left: 13px" id="s_pagsde">
		<%-- <input type="checkbox" proId="${entity.id }">${entity.name }&nbsp;&nbsp;
		<input type="checkbox">ҳ������&nbsp;&nbsp; --%>
		<c:forEach items="${proList}" var="entity" varStatus="status">
				<c:if test="${entity.value=='checked' }">
					<input type="checkbox" checked="checked" proId="${entity.id }" name="projName">${entity.name }&nbsp;&nbsp;
				</c:if>
				<c:if test="${entity.value!='checked' }">
					<input type="checkbox" proId="${entity.id }" name="projName">${entity.name }&nbsp;&nbsp;
				</c:if>
		</c:forEach>
	</div>
	 
</div>
<br> 
<ul class="nav nav-tabs" id="subSystem">
   		<!-- <li class="active"><a href="#" id="">Home</a></li>  -->
</ul>
<div id="all_menu">
	<!--  ʾ�����
	<div class="row"  id="s_menu" names="abc">
		<input type="checkbox" name="">�˵�
	</div>
	<div class="row" style="margin-left: -5px" id="s_subMenu">
		<input type="checkbox">�Ӳ˵�
	</div>
	<div class="row" style="margin-left: 13px" id="s_pagsde">
		<input type="checkbox">ҳ������&nbsp;&nbsp;
		<input type="checkbox">ҳ������&nbsp;&nbsp;
	</div>  -->
</div>

<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<script type="text/javascript">
	var roleID='${roleID}';
	var modelId='';
	getAll();
	function getAll(){
		$.ajax({
		        data: { "parentID": '0', "permissionType": '0',url: '0'},
		        type: 'post',
		        dataType: 'json',
		        url:"/wbem/system/sysSet/getSubSystemModel.action",
		        success: function (data){
		        	$("#subSystem").empty();
		        	var pid='';
		        	modelId=data;
		        	for (var int = 0; int < data.length; int++) {
						pid=data[int].id;
						if(int==0){
							$("#subSystem").append("<li class='active'><a href='#' id='"+pid+"' onclick='getMenus(this,"+pid+")'>"+data[int].name+"</a></li>");
							getMenus('',pid);
						}else if(int>0){
							$("#subSystem").append("<li><a href='#' id='"+pid+"'onclick='getMenus(this,"+pid+")'>"+data[int].name+"</a></li>");
						}
					}
		        }
		});
	}
	function getMenus(tag,partid){
		if(tag !=''){
			$("#subSystem").find("li").removeClass("active");
			$(tag).parent().addClass("active");
		}
		pid=partid;
		var _name='';
		var ajaxFlg=true;
		$("div").each(function(){
			_name=$(this).attr("names");
			if(_name==pid && _name !=undefined){
				$(this).show();
				ajaxFlg=false;
			}else if(_name !=undefined){
				$(this).hide();
			}
		});
		if(!ajaxFlg){
			return;
		}
		$.ajax({
		        data: { "modelID": pid,"roleID":roleID},
		        type: 'post',
		        dataType: 'json',
		        url:"/wbem/system/sysSet/getAllModel.action",
		        success: function (data){
		        	//��ò˵�
	        		var menuId='';
					var menuName='';
					var subMenuId='';
					var subMenuName='';
	        		var menu=data.menu;
					var subMenu=data.subMenu;
					var pages=data.pages;
					var rolePermision=data.rolePermisionlist;
					var e_pages='';
					var e_subMenu='';
					for (var i = 0; i < menu.length; i++){
						menuId=menu[i].permissionID;
						menuName=menu[i].permissionName;
						if(rolePermision.length==0){
							$("#all_menu").append("<div class='row' names='"+pid+"' style='margin-left: 0px;'><input type='checkbox' style='margin-top: 0px;' id='"+menuId+"'>"+menuName+"</div>");//��Ӳ˵�
						}else{
							for (var b = 0; b < rolePermision.length; b++) {
								if(rolePermision[b].permissionID==menuId){
									$("#all_menu").append("<div class='row' names='"+pid+"' style='margin-left: 0px;'><input type='checkbox' style='margin-top: 0px;' checked='checked' id='"+menuId+"'>"+menuName+"</div>");//��Ӳ˵�
									break;
								}else if(b==(rolePermision.length-1)){
									$("#all_menu").append("<div class='row' names='"+pid+"' style='margin-left: 0px;'><input type='checkbox' style='margin-top: 0px;' id='"+menuId+"'>"+menuName+"</div>");//��Ӳ˵�
								}
							}
						}
						for (var j = 0; j < subMenu.length; j++){
							e_subMenu=subMenu[j];
							//alert(subMenu[j][0].parentID ==undefined);
							//alert(e_subMenu.length);
							if(e_subMenu.length>0 && subMenu[j][0].parentID==menuId){
								for (var z = 0; z < e_subMenu.length; z++){
									subMenuId=e_subMenu[z].permissionID;
									subMenuName=e_subMenu[z].permissionName;
									if(rolePermision.length==0){
										$("#all_menu").append("<div class='row' style='margin-left: 12px' names='"+pid+"'><input type='checkbox' style='margin-top: 0px;' id='"+subMenuId+"' name='"+menuId+"'>"+subMenuName+"</div>");//����Ӳ˵�
									}else {
										for (var b = 0; b < rolePermision.length; b++) {
											if(rolePermision[b].permissionID==subMenuId){
												$("#all_menu").append("<div class='row' style='margin-left: 12px' names='"+pid+"'><input type='checkbox' style='margin-top: 0px;' checked='checked' id='"+subMenuId+"' name='"+menuId+"'>"+subMenuName+"</div>");//����Ӳ˵�
												break;
											}else if(b==(rolePermision.length-1)){
												$("#all_menu").append("<div class='row' style='margin-left: 12px' names='"+pid+"'><input type='checkbox' style='margin-top: 0px;'  id='"+subMenuId+"' name='"+menuId+"'>"+subMenuName+"</div>");//����Ӳ˵�
											}
										}
									}
									for (var k = 0; k < pages.length; k++) {
										e_pages=pages[k];
										if(e_pages.length>0 && e_pages[0].parentID==subMenuId){
											$("#all_menu").append("<div class='row' style='margin-left: 23px' id='div"+subMenuId+"' name='"+subMenuId+"' names='"+pid+"'></div>");//����Ӳ˵�
											for (var s = 0; s < e_pages.length; s++) {
											if(rolePermision.length==0){
												$("#div"+subMenuId).append("<input type='checkbox' style='margin-top: 0px;' id='"+e_pages[s].permissionID+"' onclick='check(this)' >"+e_pages[s].permissionName+"&nbsp;&nbsp;");//���pages
											}else{
												for (var b = 0; b < rolePermision.length; b++) {
													if(rolePermision[b].permissionID==e_pages[s].permissionID){
														$("#div"+subMenuId).append("<input type='checkbox' style='margin-top: 0px;' checked='checked' id='"+e_pages[s].permissionID+"' onclick='check(this)' >"+e_pages[s].permissionName+"&nbsp;&nbsp;");//���pages
														break;
													}else if(b==(rolePermision.length-1)){
														$("#div"+subMenuId).append("<input type='checkbox' style='margin-top: 0px;'  id='"+e_pages[s].permissionID+"' onclick='check(this)' >"+e_pages[s].permissionName+"&nbsp;&nbsp;");//���pages
													}
												}
											  }	
										   }
										}
									}
								}
							}
						}
					}
				}
		  });
	};
	function check(tag){
		var subId=$(tag).parent().attr("name");
		$("#"+subId).prop("checked",true);
		var mid=$("#"+subId).attr("name");
		$("#"+mid).prop("checked",true);
	};

$(document).ready(function(){
	//ȷ��������Ա
	$("#corrRolePermission").click(function(){
	    var userId=[];
	    var modelIds=[];
	    var projIds=[];
	    var i=0;//����ѡ�м���
	    var flg=true;//�жϱ�־
	    var j=0;//��Ŀ��֤
	    $("input:checkbox[name='projName']").each(function(){//����ģ������ѡ�е���Ŀ
			if($(this).attr("checked") == "checked") {
				j++;
				var proId =$(this).attr("proId");
		        projIds.push(proId);
			}
		});
		if(j==0){
			layer.alert("��ѡ����Ŀ��");
			return;
		}
		
		$("input:checkbox[name!='projName']").each(function(){//����ģ������ѡ�е���Ŀ
			if($(this).attr("checked") == "checked") {
				i++;
				var uId =$(this).attr("id");
		        userId.push(uId);
			}
		});
		
		$("li a").each(function(){//����ģ������ѡ�е���Ŀ
			var _mId=$(this).attr("id");
			$("div [names='"+_mId+"']").find("input:checkbox").each(function(){//����ģ��
				if($(this).attr("checked") == "checked") {
			        userId.push(_mId);
			        modelIds.push(_mId);
			       return false;//������ǰѭ��
				}
			});
		});
		
		if(i == 0){
			layer.alert("��ѡ��");
		}else if(flg) {
			createMember(userId, roleID,modelIds,projIds);
		}
	});
	function createMember(userId,roleID,modelIds,projIds){
			$.ajax({
			        data: { "permissionID": userId.toString(), "roleID": roleID,"modelIds":modelIds.toString(),"projIds":projIds.toString()},
			        type: 'post',
			        dataType: 'json',
			        url: '/wbem/system/sysSet/correRole_Permission.action',
			        success: function (data) {
			            if(data =='1'){
		            		var index = parent.layer.getFrameIndex(window.name);
							parent.$('.data-table').DataTable().ajax.reload();
							parent.layer.close(index);
			            }else if(data=='0'){
			            	layer.alert("�����������");
			            }
			        }
			}); 
	}
});
</script>
<%@include file="/WEB-INF/jsp/wbem/pub/footer-dialog.jsp" %>
