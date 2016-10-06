<%@ page language="java" pageEncoding="gbk" contentType="text/html;charset=gbk"%>
<!DOCTYPE html>
<head>
<meta charset="gbk">
<html lang="zh-cmn-Hans">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>客户</title>
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
 * 下拉刷新 （自定义实现此方法）
 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
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
 * 滚动翻页 （自定义实现此方法）
 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
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
 * 初始化iScroll控件
 */
function loaded() {
	//清除所占的内存空间
	if(myScroll!=null){
		myScroll.destroy();
	}

	pullDownEl = document.getElementById('pullDown');
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('wrapper', {
		useTransition: true,    //默认为true
		//useTransition: false, 
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullDownEl.className.match('loading')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '';//2016.3.2
				//pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
			} else if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '';//2016.3.2
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...'; 2016.3.2
			}
		},
		onScrollMove: function () {
			if (this.y > 5 && !pullDownEl.className.match('flip')) {
				pullDownEl.className = 'flip';
				//pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
				this.minScrollY = 0;
			} else if (this.y < 5 && pullDownEl.className.match('flip')) {
				pullDownEl.className = '';
				//pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...'; 2016.3.2
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				//pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullDownEl.className.match('flip')) {
				pullDownEl.className = 'loading';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';				
				pullDownAction();	// Execute custom function (ajax call?)
			} else if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				if(cstCount - pageIndex*pageLen > 0){
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';	
					pageIndex++;			
					pullUpAction();	// Execute custom function (ajax call?)
				}else{
					pullUpEl.querySelector('.pullUpLabel').innerHTML = '没有数据了...';
					setTimeout(function(){pullUpEl.querySelector('.pullUpLabel').innerHTML = '';},1000);
				}
			}
		}
	});
	
	document.getElementById('wrapper').style.left = '0'; 
}

//初始化绑定iScroll控件 
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
			        	<input type="text" placeholder="请输入客户姓名或电话" name="cusNameOrTel">
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
                  		  总数：${customListCount}人<input id="sum" type="hidden" value="${customListCount}">
                </span><a class="add_newMember fonticons-add" data-ac="active"  onclick="javascipt:window.location.href='/mbem/mcrm/business/addCustom.action'">
                  		  添加
                </a>
                <span class="guis">归属<i class="query_btn"></i></span>
                <!-- <div class="select" id="selectSort">
                   <span class="select-off" id="sortText" name="sort" selectVal="1">跟进时间 ↓</span> 
                    <div class="select-a" id="sortList" style="display:none">
                        <p rel="1" onclick="switchOption(this)"><a href="#">跟进时间 ↓</a></p>
                        <p rel="2" onclick="switchOption(this)"><a href="#">跟进时间 ↑</a></p>
                        <p rel="3" onclick="switchOption(this)"><a href="#">创建时间 ↓</a></p>
                        <p rel="4" onclick="switchOption(this)"><a href="#">创建时间 ↑</a></p>
                    </div>
                </div> -->
                <!-- <div class="all" id="selectOption">
                   <span class="select-off" id="all" name="type" selectVal="1">全部</span> 
                    <div class="select-a" id="sortList" style="display:none">
                        <p rel="1" onclick="switchOption(this)"><a href="#">全部</a></p>
                        <p rel="2" onclick="switchOption(this)"><a href="#">我的关注</a></p>
                        <p rel="3" onclick="switchOption(this)"><a href="#">无意向</a></p>
                        <p rel="4" onclick="switchOption(this)"><a href="#">一般意向</a></p>
                        <p rel="5" onclick="switchOption(this)"><a href="#">高意向</a></p>
                    </div>
                </div>  -->
            </div>
            <div rel="07_" class="customergroup"><!-- 07月 --> </div>
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
				<li><a href="index.html" class="ui-btn-active" data-ajax="false">首页</a></li>
				<li><a href="two.html" data-ajax="false">第二页</a></li>			
			</ul>
		</div>
</div> -->
<div data-role="footer" data-position="fixed" class="navigation_foot">
	<div data-role="navbar" data-grid="a">
	<ul>
	<!--  没有增购直销，就隐蔽待办和业绩 -->
		<li>
		    <div onclick="javascript:window.location.href='/mbem/mcrm/business/backlogPage.action'">
		    <p class="icon_30 icon_MyTodo ">待办</p>
		    </div>
		</li>
		<li>
		    <div>
		    <p class="icon_30 icon_Customers select ">客户</p>
		    </div>
		</li>
		<li>
		    <div onclick="javascript:window.location.href='/mbem/mcrm/business/find/findPage.action'">
		    <p class="icon_30 icon_Find ">发现</p>
		    </div>
		</li>
		<li>
		   <div onclick="goPage('#')">
		        <p class="icon_30 icon_Achieve ">业绩</p>
		    </div>
		</li>
	</ul>
	</div>
</div>

<script type="text/javascript">
  //解决鲲鹏手机的缓存问题
    window.onpageshow = function(event){
        if(event.persisted){
            window.location.reload();
        }
    }
    var MyCustomer={
        //搜索框对象
        searchBox: null,
        //普通搜索关键字
        SearchText: '',
        //搜索类型(advanced:高级搜索)
        from:'',
        //高级搜索条件
        post_data:'',
        //高级搜索页码
        pageindex:1,
        //是否是最后一页
        isLast:false,
        isdown:'0',
        //当前高亮Dom元素
        activeEl: null,
        //当前高亮定时器
        timer:null,
        //下拉刷新对象
      
        //判断是否是从搜索框刷新页面
        isSearchInput: false,
        //是否正在执行下拉刷新
        doUpRefresh: false,
    }
   
    $(function(){
        //拨打电话后跳转到跟进页面
        $("body").delegate('.a_li > a[oppid]','click', function(){
            var oppid = $(this).attr('oppid');
            setTimeout(function(){
                var url = "#"+oppid;
                window.location.href = url;
            }, 1000);
        });
        //记住并保存用户登录的次数，以免归属指引多次出现
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

        //初始化文本搜索框
        MyCustomer.searchBox = new MYSOFT.Ui.SearchBox('#searchbox', {
            placeholder: '请输入客户姓名或电话',
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
      
        
//归属查询 begin......
        var asignBtn = $('span.guis');//获得归属这个元素
        asignBtn.on('click', function(){
            storage.setItem('guideAssign', '0');//用户登录次数置为0
		     var title = '客户归属查询';
            var content = '<input type="tel" placeholder="请输入完整的电话号码" />';
            MYSOFT.Ui.Popup.ShowConfirmRegs(title, content, function(){
          		var telInput = $('input', $('#mysoft_popup'));
                var telVal = $.trim(telInput.val());
                  //alert("telinput "+telInput+" telval "+telVal);
                if(telVal == ''){
                    $('.error').remove();
                    var p = $('<p class="error" style="color:#f00;font-size:14px;text-align:left;padding-top:12px;">请输入完整的电话号码</p>');
                    telInput.after(p);
                    return false;
                }else{//
                    $('.error').remove();
                    //发送请求查询电话号码归属状态
                    //alert("hihi");
                    $.ajax({
                     	type : 'POST',
                     	contentType : 'application/json',
                        url: "${pageContext.request.contextPath}/mbem/mcrm/business/getZsCustomBelongingAjax.action?telVal="+telVal,
                        //data: '{"telVal":"'+telVal+'"}',/* {data:JSON.stringify({telVal:telVal})}, */
                        cache: false,
                        complete: function (jqXHR, status) {
                            if (status != 'success') {
                                MYSOFT.Ui.Toast.Show('查询失败!');
                                return;
                            }
                           /* 源码注释，没动
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
                                    MYSOFT.Ui.Popup.ShowConfirmRegs('提示', '该客户不存在，是否立即新增客户？', function(){
                               //alert( retun.redirect_url);
                                        window.location.href = retun.redirect_url;
                                        return false;
                                    }, function(){}, '新增客户');
                                    $('#popup_btn_container a').css('width', '50%');
                                break;
                                case 1:
                                    MYSOFT.Ui.Popup.ShowConfirmRegs('提示', '该客户已在您名下，是否查看客户信息？', function(){
                                        window.location.href = retun.redirect_url;
                                        return false;
                                    }, function(){}, '查看');
                                break;
                                case 2:
                                	if(parseInt(retun.is_show_saler_name)==0){
                                    	var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;">客户：<span style="color:#333;">' + retun.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">该客户已被其他业务员跟进。</p>';
                                        MYSOFT.Ui.Popup.ShowAlert('客户归属查询 ', _content,
                                                function(){}, '我知道了');
                                	}else{
                                        //var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;"><span style="color:#8d8d8d;margin-right:6%;">客户：<span style="color:#333;"> ' + retun.cst_name + '</span></span>/* <span style="color:#8d8d8d;">状态：<span style="color:#333;">' + retun.stageName + '</span></span> */</p>/* <p style="text-align:left;font-size:15px;color:#333;margin-top:10px;">' +retun.createdOn + ' 创建</p> */<p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">该客户已被业务员 <span style="color:#333;">' + retun.user_name + '</span> 跟进，是否立即通知接待？</p>';
                                       	var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;"><span style="color:#8d8d8d;margin-right:6%;">客户：<span style="color:#333;"> ' + retun.cst_name + '</span></span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">该客户已被业务员 <span style="color:#333;">' + retun.user_name + '</span> 跟进，是否立即通知接待？</p>';
                                        MYSOFT.Ui.Popup.ShowConfirmRegs('客户归属查询 ',_content,function(){
                                                    $('#tag_ok_popup').attr('href', 'tel:'+retun.tel);
                                                    return false;
                                                },
                                                function(){}, '呼叫');
                                    }
                                break;
                                case 3:
                                    // cst_type =3 公共客户  isown  1 是自己 0 不是自己
									//alert("jfei");
                                    if(parseInt(retun.isown)==0 && retun.allow_gj == "1"){
                                        var _content = '<p style="text-align:left;font-size:15px;color:#8d8d8d;"><span style="color:#8d8d8d;margin-right:6%;">客户： <span style="color:#333;">' + retun.cst_name + '</span></span><span style="color:#8d8d8d;">状态：<span style="color:#333;">' + retun.stageName + '</span></p><p style="text-align:left;font-size:15px;color:#333;margin-top:10px;">' + retun.createdOn + ' 创建</p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">该客户为公共客户，是否立即跟进该客户？</p>';

                                        MYSOFT.Ui.Popup.ShowConfirmRegs('客户归属查询 ', _content,
                                                function(){
                                                    // 跟进逻辑
                                                    var url="/mbem/mcrm/business/follow.action?type=1";
                                                    window.location=url;
                                                }, function(){
                                                    // 取消逻辑
                                                }, '跟进客户');
                                        return false;
                                    }else if(parseInt(retun.isown)==0 && retun.allow_gj == "0"){
                                    //alert("herjeij");
                                   	 MYSOFT.Ui.Popup.ShowAlert('客户归属查询 ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;">客户：<span style="color:#333;">' + retun.cst_name + '</span></p><p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">该客户为公共客户，请联系招商经理分配客户</p>',
                                   	 function(){}, '我知道了');
                                    }
                                break;
                                case 4:
                                    MYSOFT.Ui.Popup.ShowAlert('客户归属查询 ', '<p style="text-align:left;font-size:15px;color:#8d8d8d;margin-top:10px;">该客户为垃圾箱客户，请联系实施顾问处理该数据！</p>',
                                            function(){}, '我知道了');
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