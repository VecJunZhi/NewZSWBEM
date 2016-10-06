<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>�ͻ�</title>
<link rel="stylesheet"  href="/mbem/mcrm/business/css/iscroll.css">
<link rel="stylesheet"  href="/mbem/mcrm/business/css/base.min.css">
<link rel="stylesheet"  href="/mbem/mcrm/business/css/ui.css">
<link rel="stylesheet"  href="/mbem/mcrm/business/css/yunke.css">
<script  src="/mbem/mcrm/business/js/iscroll.js"></script>
<script  src="/common/js/jquery-1.9.1.min.js"></script>
<script  src="/mbem/mcrm/business/js/base.min.js"></script>
<script  src="/mbem/mcrm/business/js/ui.js"></script>
<script  src="/mbem/mcrm/business/js/addCustom.js"></script>
<script src="/common/layer/layer.js"></script>

<script type="text/javascript">
/* var str1 = "<li data-id='39d08b6c-e4dd-9de4-8d62-5b1f1bfd7e65' class='a_li'><a href='/mbem/mcrm/business/personal.action?cusId="; */	
var str1 = "<li data-id='39d08b6c-e4dd-9de4-8d62-5b1f1bfd7e65' class='a_li'><a href='#' onclick=popInfo('";				
var str2 = "')><dl><dt><span>";                  
var str3="</span><font>";                                
var str4="</font></dt><dd class='a_li_d1 clearfix'><font>";                                     
var str5="&nbsp;";         
var str6 = "</font> <big class='fr'>";
var str7 = "</big></dd> <dd class='a_li_d2'><span>";
var str8="</span></dd></dl></a><a onclick=callCustom('2') class='a_url_tel' data-ac='a_li_aact' href='tel:"
var str9 = "' oppid='39d08b6c-e4ed-cbd2-eb25-96cce6ff77c2'></a></li> ";
var pageLen = 10;
var pageIndex = 1;
var cstCount = ${customListCount}; 
var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;
var ajaxUrl;
var index;
/**
 * ����ˢ�� ���Զ���ʵ�ִ˷�����
 * myScroll.refresh();		// ���ݼ�����ɺ󣬵��ý�����·���
 */
function pullDownAction () {
	if(ajaxUrl == "/mbem/mcrm/business/loadZsCustomListByNameOrTel.action" || ajaxUrl == "/mbem/mcrm/business/loadZsCustomListByAdvancedSearch.action") {
		window.location.href =  "/mbem/mcrm/business/customPage.action";
	}
	else{
		setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
			$("#thelist").empty();
			pageIndex = 1;
			$.ajax({
				url: "/mbem/mcrm/business/loadZsCustomList.action",//ajaxUrl,
				type:"POST",
				data: {"page":pageIndex,"pageLen":pageLen},
				dataType: "json",
				success: function (data) {
					var str="";
					for(var i=0; i<data.length; i++) {
						if(data[i].followWayEach == null){
		            		   str += str1+data[i].cusId+str2+data[i].cusName+str3+/* data[i].cusStatus+ */str4+data[i].followDate+str5+""+str6+data[i].tel+str7+data[i].followInfo+str8+data[i].tel+str9;
		            	   }else {
		            		   str += str1+data[i].cusId+str2+data[i].cusName+str3+/* data[i].cusStatus+ */str4+data[i].followDate+str5+data[i].followWayEach+str6+data[i].tel+str7+data[i].followInfo+str8+data[i].tel+str9;
		            	   }
					}
					$("#thelist").append(str);//.listview('refresh');
					myScroll.refresh();
				},
				error: function() {
					//alert("error");
				}
			}); 
		}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
	}
}

/**
 * ������ҳ ���Զ���ʵ�ִ˷�����
 * myScroll.refresh();		// ���ݼ�����ɺ󣬵��ý�����·���
 */
function pullUpAction () {
	index = layer.load(0, {shade: false});
	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		$.ajax({
               url: ajaxUrl,
               type:"POST",
               data: {"page":pageIndex,"pageLen":pageLen},
               dataType: "json",
               success: function (data) {
               	   var str="";
	               for(var i=0; i<data.length; i++) {
	            	   if(data[i].followWayEach == null){
	            		   str += str1+data[i].cusId+str2+data[i].cusName+str3+/* data[i].cusStatus+ */str4+data[i].followDate+str5+""+str6+data[i].tel+str7+data[i].followInfo+str8+data[i].tel+str9;
	            	   }else {
	            		   str += str1+data[i].cusId+str2+data[i].cusName+str3+/* data[i].cusStatus+ */str4+data[i].followDate+str5+data[i].followWayEach+str6+data[i].tel+str7+data[i].followInfo+str8+data[i].tel+str9;
	            	   }
						//$('#wrapper > #listPanel > ul').append(str);
	               }
	               $("#thelist").append(str);//.listview('refresh');
	               myScroll.refresh();
	               layer.close(index);  
	           },
               error: function() {
               		//alert("error");
               }
		}); 
	}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
}
/**
 * ��ʼ��iScroll�ؼ�
 */
function loaded() {
	//�����ռ���ڴ�ռ�
	if(myScroll!=null){
		myScroll.destroy();
	}

	pullDownEl = document.getElementById('pullDown');
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('wrapper', {
		useTransition: true,    //Ĭ��Ϊtrue
		//useTransition: false, 
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullDownEl.className.match('loading')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '';//2016.3.2
				//pullDownEl.querySelector('.pullDownLabel').innerHTML = '����ˢ��...';
			} else if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '';//2016.3.2
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '�������ظ���...'; 2016.3.2
			}
		},
		onScrollMove: function () {
			if (this.y > 5 && !pullDownEl.className.match('flip')) {
				pullDownEl.className = 'flip';
				//pullDownEl.querySelector('.pullDownLabel').innerHTML = '���ֿ�ʼ����...';
				this.minScrollY = 0;
			} else if (this.y < 5 && pullDownEl.className.match('flip')) {
				pullDownEl.className = '';
				//pullDownEl.querySelector('.pullDownLabel').innerHTML = '����ˢ��...';
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '���ֿ�ʼ����...'; 2016.3.2
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '�������ظ���...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullDownEl.className.match('flip')) {
				pullDownEl.className = 'loading';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '������...';				
				pullDownAction();	// Execute custom function (ajax call?)
			} else if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				if(cstCount - pageIndex*pageLen > 0){
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '������...';	
					pageIndex++;			
					pullUpAction();	// Execute custom function (ajax call?)
				}else{
					pullUpEl.querySelector('.pullUpLabel').innerHTML = 'û��������...';
					setTimeout(function(){pullUpEl.querySelector('.pullUpLabel').innerHTML = '';},1000);
				}
			}
		}
	});
	
	document.getElementById('wrapper').style.left = '0'; 
}

//��ʼ����iScroll�ؼ� 
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

//document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
document.addEventListener('DOMContentLoaded', loaded, false); 


$(document).ready(function(){
	ajaxUrl = $("#ajaxUrl").val();
    pullUpAction();  
     
   /* 	$(".select-off").click(function(){
	var status = $(this).next().css("display");
	if(status == "none")
		$(this).next().css("display","block");
	if(status == "block")
		$(this).next().css("display","none");
	}); */
	$("div[id^=select]").click(function(){
	var status = $(this).children().eq(1).css("display");
	if(status == "none")
		$(this).children().eq(1).css("display","block");
	if(status == "block")
		$(this).children().eq(1).css("display","none");
	});
});

	function switchOption(tag) {
		var val = $(tag).text();
		$(tag).parent().prev().text(val);
		$(tag).parent().prev().attr("selectVal",$(tag).attr("rel"));
		/* var param="";
		var i = 0;
		$("div[id^=select]").each(function(){
			if(i != 0)
				param += "&";
			param += $(this).children().attr("name")+"=";
			param += $(this).children().attr("selectVal");
			i = 1;
		});
		pageIndex = 1;
		alert("param   "+param);
		ajaxUrl = "/mbem/mcrm/business/loadZsCustomList.action?"+param;
		pullUpAction(); */
	}
</script>

</head>
<body>
<header>
	<div class="searchpanel">
        <div>
	        <ul class="searchbox">   
		        <form action="/mbem/mcrm/business/getZsCustomListByNameOrTel.action" method="post">     
			        <li>
			        	<input class="query_btn" value="" type="submit" >
			        </li>
			        <li class="query_input">
			        	<input type="text" placeholder="������ͻ�������绰" name="cusNameOrTel">
				        <i></i>        
			        </li>  
		        </form>   
	        </ul>
        </div>
        <a data-ac="active" href="/mbem/mcrm/business/advancedSearch.action" class="adv_sea_btn fonticons-filter"></a> 
      <!--  <a data-ac="a_asign_active" href="javascript:void(0);" class="a_asign"></a> -->
	</div>
    <!-- <div class="a_guide_ico"></div> -->
</header>
<div id="wrapper">
	<div id="scroller">
		<div id="pullDown">
			<span class="pullDownIcon"></span><span class="pullDownLabel"><!-- Pull down to refresh... --></span>
		</div>
		<div id="listPanel">
			<input id="ajaxUrl" type="hidden" value="${ajaxUrl}">
		   <div class="sum" style="display: block;width:100%;position:relative;height:38px;line-height:24px;color:#aaa;font-size:12px;text-align: left;padding:7px 6px;">
                <span>
                  		  ������${customListCount}��<input id="sum" type="hidden" value="${customListCount}">
                </span><a class="add_newMember fonticons-add" data-ac="active"  onclick="javascipt:window.location.href='/mbem/mcrm/business/addCustom.action'">
                  		  ���
                </a>
                <span class="guis">����<i class="query_btn"></i></span>
                <!-- <div class="select" id="selectSort">
                   <span class="select-off" id="sortText" name="sort" selectVal="1">����ʱ�� ��</span> 
                    <div class="select-a" id="sortList" style="display:none">
                        <p rel="1" onclick="switchOption(this)"><a href="#">����ʱ�� ��</a></p>
                        <p rel="2" onclick="switchOption(this)"><a href="#">����ʱ�� ��</a></p>
                        <p rel="3" onclick="switchOption(this)"><a href="#">����ʱ�� ��</a></p>
                        <p rel="4" onclick="switchOption(this)"><a href="#">����ʱ�� ��</a></p>
                    </div>
                </div> -->
                <!-- <div class="all" id="selectOption">
                   <span class="select-off" id="all" name="type" selectVal="1">ȫ��</span> 
                    <div class="select-a" id="sortList" style="display:none">
                        <p rel="1" onclick="switchOption(this)"><a href="#">ȫ��</a></p>
                        <p rel="2" onclick="switchOption(this)"><a href="#">�ҵĹ�ע</a></p>
                        <p rel="3" onclick="switchOption(this)"><a href="#">������</a></p>
                        <p rel="4" onclick="switchOption(this)"><a href="#">һ������</a></p>
                        <p rel="5" onclick="switchOption(this)"><a href="#">������</a></p>
                    </div>
                </div>  -->
            </div>
            <div rel="07_" class="customergroup"><!-- 07�� --> </div>
		</div>
		<ul data-role="listview" data-icon="false" id="thelist">
		    
		</ul>		
		<div id="pullUp">
			<span class="pullUpIcon"></span><span class="pullUpLabel"><!-- Pull up to refresh... --></span>
		</div>
	</div>
</div>
<!-- <div data-role="footer" data-position="fixed">
	<div data-role="navbar" data-grid="a">
			<ul>
				<li><a href="index.html" class="ui-btn-active" data-ajax="false">��ҳ</a></li>
				<li><a href="two.html" data-ajax="false">�ڶ�ҳ</a></li>			
			</ul>
		</div>
</div> -->
<div data-role="footer" data-position="fixed" class="navigation_foot">
	<div data-role="navbar" data-grid="a">
	<ul>
	<!--  û������ֱ���������δ����ҵ�� -->
		<li>
		    <div onclick="javascript:window.location.href='/mbem/mcrm/business/backlogPage.action'">
		    <p class="icon_30 icon_MyTodo ">����</p>
		    </div>
		</li>
		<li>
		    <div>
		    <p class="icon_30 icon_Customers select ">�ͻ�</p>
		    </div>
		</li>
		<li>
		    <div onclick="javascript:window.location.href='/mbem/mcrm/business/find/findPage.action'">
		    <p class="icon_30 icon_Find ">����</p>
		    </div>
		</li>
		<li>
		   <div onclick="goPage('#')">
		        <p class="icon_30 icon_Achieve ">ҵ��</p>
		    </div>
		</li>
	</ul>
	</div>
</div>

<script type="text/javascript">
  //��������ֻ��Ļ�������
    window.onpageshow = function(event){
        if(event.persisted){
            window.location.reload();
        }
    }
    var MyCustomer={
        //���������
        searchBox: null,
        //��ͨ�����ؼ���
        SearchText: '',
        //��������(advanced:�߼�����)
        from:'',
        //�߼���������
        post_data:'',
        //�߼�����ҳ��
        pageindex:1,
        //�Ƿ������һҳ
        isLast:false,
        isdown:'0',
        //��ǰ����DomԪ��
        activeEl: null,
        //��ǰ������ʱ��
        timer:null,
        //����ˢ�¶���
      
        //�ж��Ƿ��Ǵ�������ˢ��ҳ��
        isSearchInput: false,
        //�Ƿ�����ִ������ˢ��
        doUpRefresh: false,
    }
   
    $(function(){
        //����绰����ת������ҳ��
        $("body").delegate('.a_li > a[oppid]','click', function(){
            var oppid = $(this).attr('oppid');
            setTimeout(function(){
                var url = "#"+oppid;
                window.location.href = url;
            }, 1000);
        });
        //��ס�������û���¼�Ĵ������������ָ����γ���
        var storage = window.localStorage;
        if(storage.getItem('guideAssign') == null || storage.getItem('guideAssign') == 'undefined'){
            storage.setItem('guideAssign', '1');
        }
        $('body').on('click', function(){
            storage.setItem('guideAssign', '0');
            MyCustomer.guideHide();
        });
        //
        $("#listPanel").delegate(".a_li[data-id]>a","tap",function(e){
            e.preventDefault();
            var me=$(this);
            savebackdata(me);
            location.href=me.attr("href");
        });

        //��ʼ���ı�������
        MyCustomer.searchBox = new MYSOFT.Ui.SearchBox('#searchbox', {
            placeholder: '������ͻ�������绰',
            onSearch: function (value) {
				if(value==''){
					MyCustomer.isdown='1';
				}
                //$('#listPanel').empty();
                $('#listPanel .a_li,#listPanel .customergroup').remove();            
                MyCustomer.from='';
                MyCustomer.pageindex=1;
                MyCustomer.isLast=false;
                MyCustomer.SearchText = value;
                $.localStore.remove("/sales/search/advanced#selected");
                MyCustomer.searchCustomer(MyCustomer.pageDownRefresh.scroller);
            }
        });
      
        
//������ѯ begin......
        var asignBtn = $('span.guis');//��ù������Ԫ��
        asignBtn.on('click', function(){
            storage.setItem('guideAssign', '0');//�û���¼������Ϊ0
		     var title = '�ͻ�������ѯ';
            var content = '<input type="tel" placeholder="�����������ĵ绰����" />';
            MYSOFT.Ui.Popup.ShowConfirmRegs(title, content, function(){
          		var telInput = $('input', $('#mysoft_popup'));
                var telVal = $.trim(telInput.val());
                  //alert("telinput "+telInput+" telval "+telVal);
                if(telVal == ''){
                    $('.error').remove();
                    var p = $('<p class="error" style="color:#f00;font-size:14px;text-align:left;padding-top:12px;">�����������ĵ绰����</p>');
                    telInput.after(p);
                    return false;
                }else{//
                    $('.error').remove();
                    //���������ѯ�绰�������״̬
                    //alert("hihi");
                    $.ajax({
                     	type : 'POST',
                     	contentType : 'application/json',
                        url: "${pageContext.request.contextPath}/mbem/mcrm/business/getZsCustomBelongingAjax.action?telVal="+telVal,
                        //data: '{"telVal":"'+telVal+'"}',/* {data:JSON.stringify({telVal:telVal})}, */
                        cache: false,
                        complete: function (jqXHR, status) {
                            if (status != 'success') {
                                MYSOFT.Ui.Toast.Show('��ѯʧ��!');
                                return;
                            }
                           /* Դ��ע�ͣ�û��
                            *MYSOFT.Ui.Popup.HidePopUp();*/
                        },
                        success: function (data) {
                       // alert("ji "+data);
                        //alert("ji "+data.redirect_url);
                        //alert(data.data[0].cst_type);
                      //var retun=data.data[0];
                      var retun=data;
					  switch(parseInt(retun.cst_type)){
                                case 0:
                                    MYSOFT.Ui.Popup.ShowConfirmRegs('��ʾ', '�ÿͻ������ڣ��Ƿ����������ͻ���', function(){
                               //alert( retun.redirect_url);
                                        window.location.href = retun.redirect_url;
                                        return false;
                                    }, function(){}, '�����ͻ�');
                                    $('#popup_btn_container a').css('width', '50%');
                                break;
                                case 1:
                                    MYSOFT.Ui.Popup.ShowConfirmRegs('��ʾ', '�ÿͻ����������£��Ƿ�鿴�ͻ���Ϣ��', function(){
                                        window.location.href = retun.redirect_url;
                                        return false;
                                    }, function(){}, '�鿴');
                                break;
                                case 2:
                                	if(parseInt(retun.is_show_saler_name)==0){
                                    	var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;">�ͻ���<span style="color:#333;">' + retun.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">�ÿͻ��ѱ�����ҵ��Ա������</p>';
                                        MYSOFT.Ui.Popup.ShowAlert('�ͻ�������ѯ ', _content,
                                                function(){}, '��֪����');
                                	}else{
                                        //var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;"><span style="color:#8d8d8d;margin-right:6%;">�ͻ���<span style="color:#333;"> ' + retun.cst_name + '</span></span>/* <span style="color:#8d8d8d;">״̬��<span style="color:#333;">' + retun.stageName + '</span></span> */</p>/* <p style="text-align:left;font-size:15px;color:#333;margin-top:10px;">' +retun.createdOn + ' ����</p> */<p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">�ÿͻ��ѱ�ҵ��Ա <span style="color:#333;">' + retun.user_name + '</span> �������Ƿ�����֪ͨ�Ӵ���</p>';
                                       	var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;"><span style="color:#8d8d8d;margin-right:6%;">�ͻ���<span style="color:#333;"> ' + retun.cst_name + '</span></span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">�ÿͻ��ѱ�ҵ��Ա <span style="color:#333;">' + retun.user_name + '</span> �������Ƿ�����֪ͨ�Ӵ���</p>';
                                        MYSOFT.Ui.Popup.ShowConfirmRegs('�ͻ�������ѯ ',_content,function(){
                                                    $('#tag_ok_popup').attr('href', 'tel:'+retun.tel);
                                                    return false;
                                                },
                                                function(){}, '����');
                                    }
                                break;
                                case 3:
                                    // cst_type =3 �����ͻ�  isown  1 ���Լ� 0 �����Լ�
									//alert("jfei");
                                    if(parseInt(retun.isown)==0 && retun.allow_gj == "1"){
                                        var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;"><span style="color:#8d8d8d;margin-right:6%;">�ͻ��� <span style="color:#333;">' + retun.cst_name + '</span></span><span style="color:#8d8d8d;">״̬��<span style="color:#333;">' + retun.stageName + '</span></p><p style="text-align:left;font-size:15px;color:#333;margin-top:10px;">' + retun.createdOn + ' ����</p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">�ÿͻ�Ϊ�����ͻ����Ƿ����������ÿͻ���</p>';

                                        MYSOFT.Ui.Popup.ShowConfirmRegs('�ͻ�������ѯ ', _content,
                                                function(){
                                                    // �����߼�
                                                    var url="/mbem/mcrm/business/follow.action?type=1";
                                                    window.location=url;
                                                }, function(){
                                                    // ȡ���߼�
                                                }, '�����ͻ�');
                                        return false;
                                    }else if(parseInt(retun.isown)==0 && retun.allow_gj == "0"){
                                    //alert("herjeij");
                                   	 MYSOFT.Ui.Popup.ShowAlert('�ͻ�������ѯ ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;">�ͻ���<span style="color:#333;">' + retun.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">�ÿͻ�Ϊ�����ͻ�������ϵ���̾������ͻ�</p>',
                                   	 function(){}, '��֪����');
                                    }
                                break;
                                case 4:
                                    MYSOFT.Ui.Popup.ShowAlert('�ͻ�������ѯ ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">�ÿͻ�Ϊ������ͻ�������ϵʵʩ���ʴ�������ݣ�</p>',
                                            function(){}, '��֪����');
                                    break;
                            }
                        }
                    });
                }
                return false;
            });
        });
   });
</script>

  
</body>
</html>