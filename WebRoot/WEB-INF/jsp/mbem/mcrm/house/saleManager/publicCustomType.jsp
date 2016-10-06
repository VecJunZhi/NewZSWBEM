<%@ page language="java"  pageEncoding="utf-8"%> <!-- 此处想改为utf-8,如果改为gbk，传到后台的置业顾问的名字为乱码，暂时未处理 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/base.min.css">
<link rel="stylesheet" type="text/css" href="/mbem/mcrm/business/css/yunke.css">
<script type="text/javascript" src="/mbem/mcrm/business/js/base.min.js"></script>
    <title>逾期客户列表</title><!-- -->
     <style type="text/css">
    body {
        background-color: #f0f0f0;
    }
    header{
        position:fixed;
        z-index: 1001;
        top:0;
        width:100%;
        background-color:#f0f0f0;
    }
    header .head {
        padding: 12px;
        border-bottom-width: 1px;
    }
    article {
        padding:45px 0 60px;
        box-sizing: border-box;
    }
    .searchpanel1 {
        padding: 7px 12px;
        border-bottom-width: 1px;
    }

    .searchpanel1 .searchbox {
        border: none;
        height: 30px;
        border-radius: 15px;
    }
    .searchpanel1 .searchbox input {
        padding-left: 9px;
        background-color: transparent;
    }

    .searchpanel .searchbox .query_btn {
        line-height:30px;
        width:80px;
    }

    /*搜索框样式*/
    .searchbox {
        background-color: #fff;
        border-radius: 5px;
        border: 1px solid #cacaca;
        height: 32px;
        position: relative;
        box-sizing: border-box;
    }

    .searchbox li {
        float: left;
    }

    .searchbox .query_btn {
        text-align:center;
        height: 30px;
        border-radius: 5px;
        position:relative;
    }
    .searchbox .query_btn .option{
        background-color: #666;
        border-radius: 5px;
        box-shadow: 1px 1px 2px #ccc;
        width: 132px;
        color: #fff;
        margin: 3px 0 0 3px;
        position: relative;
    }
    .query_btn .option > div {
        height: 44px;
        line-height: 44px;
        background-size: 21px 21px;
        -webkit-background-size: 21px 21px;
        background-position: 21px center;
        padding-left: 55px;
        background-repeat: no-repeat;
        font-size: 14px;
        text-align:left;
    }
    .query_btn .option > div:first-child {
        background-image: url(http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/peo_default.png);
    }
    .query_btn .option > div:last-child {
        background-image: url(http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/guwen_default.png);
    }
    .query_btn .option .border-1px {
        border-bottom-width: 1px;
        height: 0;
        display: block;
        -webkit-appearance: none;
        margin: 0;
        overflow: hidden;
    }
    .searchbox .query_btn .option:after{
        content: "";
        position: absolute;
        border-style: solid;
        border-width: 6px;
        border-color: transparent transparent #666 transparent;
        left: 20px;
        top: -11px;
    }
    .query_btn .option > div.active {
        opacity: 0.7;
    }
    .searchbox .query_input {
        float: none;
        margin-left:80px;
        position: relative;
    }
    .searchpanel .searchbox .query_btn:after{
        content: "";
        position: absolute;
        border-style: solid;
        border-width: 5px;
        border-color: #b3b3b3 transparent transparent transparent;
        right: 1px;
        top: 14px;
    }
    .searchbox input {
        border: 0;
        height: 30px;
        font-size: 14px;
        -webkit-user-select: text;
        width: 100%;
        border-radius: 5px;
        border-bottom-right-radius: 5px;
        padding-right: 30px;
        color: #333;
        box-sizing: border-box;
    }

    .searchbox input::-webkit-input-placeholder {
        color: #8e8e93;
        line-height: 18px;
        font-family: "Heiti SC", "STHeitisc", "Microsoft YaHei", Helvetica, Arial, Verdana, sans-serif;
    }

    .searchbox input:focus {
        outline: none;
    }

    .searchbox i {
        position: absolute;
        width: 30px;
        height: 30px;
        background: url(http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/icon_wrong@2x.png) no-repeat center center;
        background-size: 18px;
        right: 0px;
        top: 0px;
    }
    .hide {
        display: none
    }
    .innerpanel{
        position: relative;width: 100%;
        border-bottom-width:1px;
        box-sizing:border-box;
        padding-right:36px;
    }
    .innerpanel .leftpanel{
        overflow: hidden;height: 80px;display:block;
    }
    .innerpanel .rightpanel{
        position: absolute;right:-12px;width: 42px;top:0;height:51px;overflow: hidden;text-align: center;padding-top: 29px;
    }
    .innerpanel .leftpanel .topinner{
        position: relative; width: 100%;height: 49px;
    }
    .innerpanel .leftpanel .bottominner{
        position: relative;height: 31px;
    }
    .innerpanel .leftpanel .topinner .leftinner{
        float: left;width: 100%;padding-right: 140px;overflow: hidden;box-sizing:border-box;-webkit-box-sizing:border-box;
    }
    .innerpanel .leftpanel .topinner .leftinner .cstname{
        white-space:nowrap;padding: 12px 0px;font-size: 17px;float: left;overflow:hidden;color: #222;width:80px;text-overflow: ellipsis;
    }
    .innerpanel .leftpanel .topinner .leftinner .diff{
        font-size: 14px;overflow:hidden;color: #e83737;float: left;padding: 14px 0px 14px 0px;
    }
    .innerpanel .leftpanel .topinner .rightinner{
        position: absolute;right: 0px;width: 140px;
    }
    .innerpanel .leftpanel .topinner .rightinner .lastdate{
        float: left;width: 100px;overflow: hidden;font-size: 14px;color: #999;padding: 14px 0px
    }
    .innerpanel .leftpanel .topinner .rightinner .statustext{
        float: left;width: 40px;overflow: hidden;text-align: right;font-size: 14px;color: #999;padding: 14px 0px
    }
    .innerpanel .leftpanel .bottominner .mobile{
        float: left;width: 100%;overflow: hidden;padding-right: 120px;
    }
    .innerpanel .leftpanel .bottominner .othertext{
        right: 0px;position: absolute;width: 120px;text-align: right;color: #666;overflow: hidden;
    }
    .radiolabel {
        display: inline-block;
        text-align: left;
        font-size: 15px;
        cursor: pointer;
    }
    .selected .iconradio{
        background-image: url('http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/select_full.png');
    }
    .iconradio{
        background-image: url('http://ydxs2.myscrm.cn/modules/salemanage/themes/default/images/select_empty.png');
        background-repeat:no-repeat;
        width:22px;
        height:22px;
        background-size:22px;
        vertical-align:middle;
        display:inline-block;
        margin-top:-0.2em;
    }
    .radiobutton {
        width: 20px;
        height: 20px;
        background-size: 20px;
    }
    article .loading{line-height: 40px;height:40px; display: block;width: 150px;margin: auto;text-align:center}
    .loading .pullUpIcon{
        display: inline-block;
        vertical-align:middle;
        width:16px;
        height:16px;
        background: url(http://ydxs2.myscrm.cn/public/images/loading.gif) 0 0px no-repeat;background-size:16px 16px;
        margin-top:-2px;
    }
    .loading .pullUpLabel{color:#888;padding-left:10px;}
    .scrollArticle .listpanel dl{margin-bottom:4px;}
    .scrollArticle .listpanel dd{position:relative;padding:0 12px;background-color:#fff;font-size:14px;}
    .scrollArticle .listpanel dd.active{background-color:#d4d4d4;}
    .scrollArticle .listpanel dd:last-child .innerpanel{border-bottom-width:0;}
    .scrollArticle .listpanel .customerChoose{position: absolute;right:0;top:0}
    .scrollArticle .listpanel .customerCtrl{position: absolute;right:47px;top:0;}
    .scrollArticle .listpanel .customerPhone{position: absolute;right:94px;top:0;}
    .scrollArticle .listpanel .customerName{width:100%}
    .guwentitle{position:relative;padding:0px;height:44px;background-color:#f7f7f7;border-bottom-width:1px;font-size:14px;}
    .guwentitle .title{padding:12px 0px 12px 12px;font-size:14px;color:#666;margin-right:120px;}
    .guwentitle .selectall{position:absolute;right:0;top:0;height:44px;line-height:44px;text-align:center;margin-right: 8px;width: 58px;color:#34cca6}
    .guwentitle .cancleall{position:absolute;right:0;top:0;height:44px;line-height:44px;text-align:center;margin-right: 8px;width: 58px;color:#34cca6}
    .guwentitle .callback{position:absolute;right:58px;top:0;height:44px;line-height:44px;text-align:center;color:#34cca6;width: 58px}
    .guwentitle .vertical-line{position:absolute;right:58px;top:12px;width:0;height: 20px;overflow:hidden;border-right-width:1px;}
    .guwentitle .titletext{color:#000;font-size:15px;padding-right:15px;}
    .title .triangle {
        margin-right: 5px;
        display: inline-block;
        width: 0;
        height: 0;
        vertical-align: middle;
        border-width:5px;
        border-style: solid;
        border-color:transparent transparent transparent #b3b3b3 ;
        font-size: 0;
        overflow: hidden;
    }
    .open .triangle {
        border-color:#b3b3b3 transparent transparent transparent;
    }
    .scrollArticle dl .loadingpanel,.scrollArticle dl dd{display: none}
    .scrollArticle dl.open dd{display:block;}
    .scrollArticle dl.open .loadingpanel.hide{display:none;}
    .nomore .loadingpanel{display:none!important;}
    footer {
        position: fixed;
        box-sizing: border-box;
        left: 0;
        width: 100%;
        bottom: 0;
        height: 62px;
        background-color: #f7f7f7;
        padding: 12px;
        font-size:0;
    }
    footer > div{width:49.9%;display:inline-block;padding:0 5px;box-sizing:border-box;-webkit-box-sizing: border-box;}
    footer > div:only-child{width:99.9%}
    .button,.button:hover,.button:visited{
        background-color: #34cca6;
        font-size: 16px;
        color: #fff;
        text-decoration: none;
        text-align: center;
        display: block;
        height: 38px;
        line-height: 36px;
        border-radius: 5px;
    }
    .button.active,.button:active{
        background-color: #2fb997;
        color:#fff;
    }
    .button.cancel {
        background: transparent;
        color: #33cc66;
    }
    .button.cancel.active{
        background-color: #34cca6;
        color: #fff;
    }
    .button.borderbtn{
        border: 1px solid #34cca6;
        color: #34cca6;
        box-sizing:border-box;
    }
    .fixedtitle{
        position:fixed;
        background-color: #f7f7f7;
        top: 45px;
        height: 44px;
        padding: 0px;
        z-index: 10;
        width:100%;
        border-bottom-width: 1px;
    }
    .container.reason > header,.container.reason > footer, .container.reason > .scrollArticle, .container.reason > .fixedtitle{display:none;}
    .container.reason > .recover-reason-block{display:block;}
    .recover-reason-block .textarea-wrap{padding:12px;margin-top:6px;}

    .recover-reason-block textarea::-webkit-input-placeholder{font-size:15px;}
    .recover-reason-block textarea{width:100%;border:1px solid #ddd;border:none;line-height:2em;font-size:15px;color:#333;}
    .recover-reason-block .input-info{text-align:right;color:#666;}
    .recover-reason-block .input-info .num{color:#444;}
    .recover-reason-block .textarea-wrap{background-color:#fff;}
    .recover-reason-block .btns{text-align:center;padding-top:35px;}
    .recover-reason-block .btns a{display:inline-block;width:45%;}
    .recover-reason-block .cancel{margin-right:5px;}
    .recover-reason-block .numinfo{padding:6px 6px 0;color:#888;position:relative}
    .recover-reason-block .numinfo table{font-size:13px;}
    .recover-reason-block .numinfo td{vertical-align:middle}
    .recover-reason-block .numinfo img{width:41px;height:45px;margin-right:6px;position:relative;top:4px;}
    .recover-reason-block .numinfo .num{font-size:1.3em;padding:0 3px;}
</style>
<script type="text/javascript">
$(function ($) {
	if (window.history && window.history.pushState) {
	$(window).on('popstate', function () {
        var hash = window.location.hash;
        if (hash === '') {
            window.location.href="/mbem/mcrm/house/saleManager/index.action";
        }
    });
    var url = window.location.url;
    window.history.pushState('forward', null, url);
    location.hash="#1";
	}
});    	 
</script>
</head>

<body>


    <header id="header">
    <div class="searchpanel1 border-1px">
        <form id="form-searchbox" class="searchpanel noborder" >
            <ul class="searchbox">
                <li class="query_btn">
                    <span class="showname" value="1">客　户</span><div class="option hide"><div value="1" data-ac="active" class="cus_item">客　户</div><hr class="border-1px"><div value="2" data-ac="active" class="cus_item">置业顾问</div></div>
                </li>
                <li class="query_input">
                    <input type="search" class="query-input" placeholder="请输入客户姓名或电话"> <i class="clear hide"></i>
                </li>
            </ul>
        </form>
    </div>
</header>
    <article id="scrollArticle" class="scrollArticle">
    	<div class="content">
        <div id="wrapper" class="follow" style="overflow: hidden;">
            <div class="panel_group" style="transition: transform 0ms; -webkit-transition: transform 0ms; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) scale(1) translateZ(0px);">
          
          
           		
                <div class="panel_group_content select" _index="0">
                    <ul> 
                    
	                    
	                   	 	<c:forEach items="${publiclistByYWY}" var="custom">
	                   	 		<li name="eachFollowItem">
						               <div class="a_li">
						               	<%-- <c:url  value="/mbem/mcrm/house/customer/getXsCstAndFollowInfoByCstGuidAndZygw_Count.action" var="url" scope="session">
						               		<c:param name="cusId" value="${custom.cstGuid}"></c:param>
						               		<c:param name="zygw" value="${overdue.userName}">
						               		
						               		</c:param>
						               		
						               	</c:url> --%>	
						              	<%-- <a href="${url }"> --%>
						                       <dl>
						                           <dt>
						                               <span>${custom.cstName}</span>
						                               <!-- <h6>逾1</h6> -->
						                               <h5>${custom.gjDate}</h5>
						                               <h4>${custom.gjfs }</h4>
						                               <em></em>
						                           </dt>
						                           <dt>
						                               <h3>${custom.mobileTel} </h3>
						                               <h4>${custom.status } </h4>
						                           </dt>
						                       </dl>
						                  <!--  </a> -->
						                   <i class="xuan"></i>
						               </div>
			             		 </li>
		             		 </c:forEach> 
		              </ul>
                  
                </div>
          
           
                </div>
</div>  </div>              
                </article>
                








<!-- <footer>
        
        <div>
            <a data-ac="active" id="btnCallBack" class="button cancel borderbtn" href="javascript:">回 收</a>
        </div>
        
        <div>
            <a data-ac="active" id="btnAllot" class="button" href="javascript:">分 配</a>
        </div>
    </footer> -->





    
<script>
    function goPage(url){
        if(/post_data=/.test(window.location.href)){
            window.location.href=url;
        }
        else{
            window.location.replace(url);
        }
    }
</script>

<script type="text/javascript">
    var loadingtag=1;
    //跳转tab与展开list
    settab();
    function settab(){
        var pagetab = geturlparam("pagetab");
        var pagelistindex = geturlparam("pagelistindex");
        if(pagetab&&pagelistindex){
            sessionStorage.setItem("mytodo_index",pagetab+"#"+pagelistindex);
        }
    }
    function onscrollmove(){
        var ind=getfixednum(this);
        if(ind!=-1){
            var fix_title= $("#fixed-title");
            var cur_title=$(this.wrapper).find(".panel_group_title").eq(ind);
            fix_title.html(cur_title.html()).addClass("bgshow");
            fix_title.attr("_index",ind);
            if(cur_title.hasClass("select")){
                fix_title.addClass("select");
            }else{
                fix_title.removeClass("select");
            }
            checkloading_pre(this);
        }
    }
    function checkloading_pre(scroll){
        var wrapper=$(scroll.wrapper);
        var loadings=wrapper.find(".loading").not(function(){
            if($(this).height()>0){
                return false
            }else{
                return true;
            }
        });
        if(loadings.size()){
            var cur_ind=loadings.eq(0).closest(".panel_group_content").attr("_index");
            checkloading(scroll,cur_ind);
        }
    }
    function checkloading(scroll,ind){
        var wrapper=$(scroll.wrapper),listwrap=wrapper.find(".panel_group_content").eq(ind),loadingdom=listwrap.find(".loading"),cur_url=listwrap.prev(".panel_group_title").attr("_url");
        var cur_page=(listwrap.data("page")|| 0)+1,arrcachedata=[],scrollY=0;
        var cachestr = sessionStorage.getItem("mytodo_index");
        if(cachestr){
            arrcachedata = cachestr.split("#");
        }
        if(arrcachedata.length >=3){
            cur_page = "1@"+ arrcachedata[2];
            scrollY = arrcachedata[3];
        }
        sessionStorage.removeItem("mytodo_index");
        if(loadingtag&&loadingdom.offset().top>=0&&loadingdom.offset().top<=$(window).height()){
            getdata();
        }
        function getdata(){
            loadingtag=0;
            $.ajax({
                url:cur_url+"&page="+cur_page+"&t="+ +(new Date()),
                dataType :"json",
                success:function(data){
                    if(data.code==1){
                        if(String(cur_page).indexOf("@")!=-1){
                            cur_page = cur_page.split("@")[1];
                        }
                        listwrap.data("page",cur_page).find("ul").append($(data.html));
                        scroll.refresh();
                        var cur_num=listwrap.prev(".panel_group_title").find(".font_alert").text();
                        var list_num=listwrap.find("ul > li").size();
                        if(list_num>= cur_num){
                            listwrap.addClass("nomore");
                        }
                        scrollY && scroll.scrollTo(0,scrollY);
                        setTimeout(function(){loadingtag=1},800);
                        setTimeout(function(){
                            var obj={"p1":data.p1||"","p2":data.p2||"","pageid":3};
                            sendajaxlog(obj);
                            scrollY && scroll.scrollTo(0,scrollY-1);
                          },100);
                    }else{
                        listwrap.addClass("nomore");
                        setTimeout(function(){loadingtag=1},800);
                    }
                }
            });
        }
    }
    $("#fixed-title").on("tap",function(e){
        e.stopPropagation();
        e.preventDefault();
        var cur_ind=$(this).attr("_index");
        var cur_wrap;
        if($("#wrapper").height()>0){
            cur_wrap= $("#wrapper");
        }else if($("#wrapper_r").height()>0){
            cur_wrap=$("#wrapper_r");
        }
        if(cur_wrap){
            var cur_tit= $(cur_wrap.find(".panel_group_title").get(cur_ind));
           cur_tit.trigger("tap");
            if(cur_tit.hasClass("select")){
                $(this).addClass("select");
            }else{
                $(this).removeClass("select");
            }
        }
    });
    function getfixednum(obj){
        var cur_arr=[],cur_num=-1;
        var wrapper=$(obj.wrapper);
        wrapper.find(".panel_group_title").forEach(function(dom,ind){
            var cur_dom=$(dom);
            var height=cur_dom.height()+cur_dom.next(".panel_group_content").height();
            if(ind>0){height+=cur_arr[ind-1]};
            cur_arr.push(height);
        });
        if(wrapper.find(".panel_group_title:not(select)").size()>0){
            var cur_y=Math.abs(obj.y);
            for(var i=0;i<cur_arr.length;i++){
                if(cur_y<cur_arr[i]){
                    cur_num=i;
                    break;
                }
            }
        }
        if(obj.y > 0){
           $("#fixed-title").addClass("hide");
        }else{
           $("#fixed-title").removeClass("hide");
        }
        return cur_num;
    }
    var myScroll;
    var myScroll2;
    $(function () {
        //拨打电话后跳转到跟进页面
        $('body').delegate('.a_url_tel', 'click', function(){
            var oppid = $(this).attr('oppid');
            setTimeout(function(){
                var url = "#="+oppid;
                window.location.href = url;
            }, 1000);
        });
        myScroll = new iScroll('wrapper',{
            onScrollMove:onscrollmove,
            onScrollEnd: onscrollmove,
            bounce:false
        });
        myScroll2 = new iScroll('wrapper_r',{
            onScrollMove:onscrollmove,
            onScrollEnd: onscrollmove,
            bounce:false
        });
        $(".outdate").addClass("hide");
        $(".tab li").on("click",function (event) {
            var li = $(this);
            var otherLi = li.parent().find("li.select");
            otherLi.removeClass('select');
            var otherName = otherLi.attr("name");
            $("." + otherName).addClass("hide");
            li.addClass('select');
            var name = li.attr("name");
            $("." + name).removeClass("hide");
            myScroll.refresh();
            myScroll2.refresh();
            getfixtitlecon();
            loadingtag=1;
            checkloading_pre(myScroll);
            checkloading_pre(myScroll2);

        });
        //add by 李真志 根据记录cookie设置页面状态  cookie值格式 tab索引#列表分组索引
        var index_value=sessionStorage.getItem('mytodo_index');
         if(index_value)
        {
            index_value=index_value.replace(/"/g,'');
            //分割字符串
            var arrIndex=index_value.split('#');
            //设置Tab状态
            $(".tab  li").eq(parseInt(arrIndex[0])).trigger('click');
            if(arrIndex[0]=="0")
            {
               $("#wrapper .panel_group_title.expand").eq(parseInt(arrIndex[1])).trigger('tap');
            }
            else
            {
                $("#wrapper_r .panel_group_title.expand").eq(parseInt(arrIndex[1])).trigger('tap');
            }
        }
        else{
             setTimeout(function(){
                 $("#wrapper .panel_group_title").eq(0).trigger('tap');
                 $("#wrapper_r .panel_group_title").eq(0).trigger('tap');
             },100);
        }
    });
    function goUrl(url) {
        window.location.href = url;
    }
    document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
    function setGroupIndex(index,page,scrolly){
        var tabIndex=$('.tab li.select').attr('_index');
        sessionStorage.setItem('mytodo_index',tabIndex+'#'+index+"#"+page+"#"+scrolly);
    }
    //判断titlefixe内容
    function getfixtitlecon(){
        var cur_wrap,fixed_title=$("#fixed-title");
        var cur_ind=fixed_title.attr("_index");
        if($("#wrapper").height()>0){
            cur_wrap= $("#wrapper");
        }else if($("#wrapper_r").height()>0){
            cur_wrap=$("#wrapper_r");
        }
        if(cur_wrap){
            var cur_title= $(cur_wrap.find(".panel_group_title").get(cur_ind));
            if(cur_title.hasClass("select")){
                fixed_title.addClass("select");
            }else{
                fixed_title.removeClass("select");
            }
            setTimeout(function(){
                fixed_title.html(cur_title.html()).addClass("bgshow");
            },100);
        }
    }
    function geturlparam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    setTimeout(function(){getfixtitlecon()},200);
    $(function () {
        $(".panel_group").delegate("li[name='eachFollowItem'] a.a_lis","click",function(){
            var group_index=$(this).closest(".panel_group_content").attr('_index');
            var page=$(this).closest(".panel_group_content").data("page");
            setGroupIndex(group_index,page,myScroll.y);
            var id = $(this).closest('li').attr("value");
            url = "#"+id;
            location.href = url;
        });
        $(".panel_group").delegate("li[name='eachFollowItems'] a.a_lis","click",function(){
            var group_index=$(this).closest(".panel_group_content").attr('_index');
            var page=$(this).closest(".panel_group_content").data("page");
            setGroupIndex(group_index,page,myScroll.y);
            var token = $("#token").val();
            var userid = $("#userid").val();
            var projid = $("#projid").val();
            var id = $(this).closest('li').attr("value");
            url = "#="+id;
            location.href = url;
        });
        $(".panel_group").delegate("li[name='eachFollowItems1']","click",function(){
            var group_index=$(this).closest(".panel_group_content").attr('_index');
            var page=$(this).closest(".panel_group_content").data("page");
            setGroupIndex(group_index,page,myScroll2.y);
            var token = $("#token").val();
            var userid = $("#userid").val();
            var projid = $("#projid").val();
            var id = $(this).attr("value");
            var type=$(this).attr("_type");
            url = "#"+id+"&type="+type;
            location.href = url;
        });
        $(window).bind("pageshow",function(e){
            if(e.persisted){
                location.reload();
            }
        });
    });
</script>
<script type="text/javascript">
    //panel_group_title展开收起
    $(".panel_group_title.expand").on("tap",function(){
   		var me=this;
        //myScroll.scrollTo(0,0);
        //myScroll2.scrollTo(0,0);
        setTimeout(title_click(me),100);
    });
    function title_click(me) {
    	var countNum = $.trim($(me).find(".font_alert").text()) * 1;
        //是否有数值
        var panel_group_title_Flag = (countNum == 0) ? true : false;
        if (panel_group_title_Flag)return false;
        //是否已展开
        var panel_group_content = $(me).next(".panel_group_content");
        panel_group_titleSilderFlag = 0;
        if ($(me).hasClass("select")) {
            $(".panel_group_title").addClass("select");
            $(".panel_group_content").addClass("hide");
            $(me).removeClass("select");
            $(me).next().removeClass("hide");
            try{
                myScroll.refresh();
                myScroll2.refresh();
                checkloading_pre(myScroll);
                checkloading_pre(myScroll2);
            }catch(e){

            }
        } else {
            $(me).addClass("select");
            $(me).next().addClass("hide");
            try{
                myScroll.refresh();
                myScroll2.refresh();
                checkloading_pre(myScroll);
                checkloading_pre(myScroll2);
            }catch(e){

            }
        }
    }
</script>

</body></html>