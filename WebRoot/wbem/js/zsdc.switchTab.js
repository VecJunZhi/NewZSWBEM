
function switchTab(n){
	/**
	 * �л�ϵͳѡ������ʾ���ݣ�ѡ���ĸ���ʾ�ĸ�ϵͳ������
	 */
	$.ajax({
		url:"/wbem/index/selectSystem.action",
		type:"post",
		data:{"systemId":n},
		dataType:"json",
		success:function(flag){
			//alert(flag)
			var val = $("#tab_"+n+" a").text();
			$("#profile-messages span").text(val);//系统切换的显示更新
			/**
			 * ѡ��ϵͳ���л���Ӧϵͳ�Ĳ˵�����
			 */	
			$(".submenu").css("display","none");
			var i = 1;
			$(".submenu").each(function(){
				if($(this).attr("id") == n) {
					$(this).css("display","block");
					if($(window).width() > 1400) {
						if(i == 1) {//默认第一个菜单展开
							$(this).addClass("open");
							i = 0;
						}
					}
				}
			});
		},
		error:function(){},
	});
	
}

function switchProject(tag) {
	var url = window.location.href;
	var projGuid = $(tag).attr("projGuid");
	$.ajax({
		url:"/wbem/index/sureSelectProject.action",
		type:"post",
		data:{"projGuid":projGuid},
		dateType:"json",
		success:function(flag){
			if(flag == 1){
				$("#projectList li",parent.document).each(function(){
					if($(this).attr("projGuid") == projGuid) {
						$("#projectInfo span",parent.document).text($(this).text());
					}
				});
				var newUrl = "";
				if(url.indexOf("#") != -1){
					newUrl = url.substring(0,url.indexOf("#"));
				}else {
					newUrl = url;
				}
				window.location.replace(newUrl);
			}else {
				alert("切换系统失败！");
			}
		},
		error:function(){
		}
	})	
}

$(document).ready(function(){
	var menuId = $("#selectMenu").attr("menuId");
	if(menuId ==  ""|| menuId ==  null || menuId ==  undefined || menuId == 0)
		return false;
	
	var tag = $("#"+menuId);
	var parentId = $(tag).parent().parent().attr("id");

	var val = $("#tab_"+parentId+" a").text();
	$("#profile-messages span").text(val);
	
	$(".submenu").each(function(){
		if($(this).attr("id") == parentId) {
			$(this).css("display","block");
		}
	});
	$(tag).addClass("active");
	$(tag).parent().parent().addClass("open");//�˵�չ��
	$(tag).parent().parent().addClass("active");
	
	//$(tag).css("background-color","#28b779");
	var liTag = $(tag).parent().children();
	$("a",liTag).css("color","#939da8");//���˵�ѡ��
	
	var location2 = $(tag).text();
	var tagA = $(tag).parent().prev();
	var location1 = $(tagA).children(":eq(1)").html();
	var url = $(tag).children().attr("href");
	var str1 = "<a href='#' > "+location1+"</a>";
	var str2 = "<a href="+url+" > "+location2+"</a>";
	$("#breadcrumb").append(str1);
	$("#breadcrumb").append(str2);//���λ����Ϣ��ʾ
	
	$("#sidebar li").each(function(){
		$(this).hover(function(){
			if($(this).attr("id") != menuId)
			{
				$(tag).removeAttr("style");
				$(tag).children("a").removeAttr("style");//���˵�ѡ��	
			}
		});
	});
		
});

